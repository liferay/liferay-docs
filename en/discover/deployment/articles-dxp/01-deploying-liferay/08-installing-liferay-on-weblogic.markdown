---
header-id: installing-liferay-dxp-on-weblogic-12c-r2
---

# Installing @product@ on WebLogic 12c R2

[TOC levels=1-4]

Although you can install @product@ in a WebLogic Admin Server, this isn't
recommended. It's a best practice to install web apps, including @product@, in
a WebLogic Managed server. Deploying to a Managed Server lets you start or shut
down @product@ more quickly and facilitates transitioning into a cluster
configuration. This article therefore focuses on installing @product@ in
a Managed Server.

Before getting started, create your Admin and Managed Servers. See
[WebLogic's documentation](http://www.oracle.com/technetwork/middleware/weblogic/documentation/index.html)
for instructions on setting up and configuring Admin and Managed Servers. 

You should also read the following articles to familiarize yourself with
@product@'s general installation steps: 

- [Installing @product@](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay)
- [Preparing for Install](/docs/7-1/deploy/-/knowledge_base/d/preparing-for-install)
- [Installing @product@ Manually](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay-manually)

Before proceeding, you must
[download](https://customer.liferay.com/downloads) 
these *Additional Files*: 

- @product@ WAR file
- Dependencies ZIP file
- OSGi Dependencies ZIP file

@product@ requires a Java JDK 8 or 11.

| **Note:** Please see [the compatibility matrix](https://www.liferay.com/documents/10182/246659966/Liferay+DXP+7.1+Compatibility+Matrix.pdf/c8805b72-c693-1f26-3f2d-731ffc301366) for information on supported JDKs, databases, and environments.

Without any further ado, get ready to install @product@ in WebLogic! 

## Configuring WebLogic's Node Manager

WebLogic's Node Manager starts and stops managed servers.

If you're running WebLogic on a UNIX system other than Solaris or Linux, use the Java Node Manager, instead of the native version of the Node Manager, by configuring these Node Manager properties in the `domains/your_domain_name/nodemanager/nodemanager.properties` file:

```properties
NativeVersionEnabled=false

StartScriptEnabled=true
```

| **Note:**   By default, SSL is used with Node Manager. If you want to disable SSL during development, for example, set `SecureListener=false` in your `nodemanager.properties` file.

See Oracle's [Configuring Java Node Manager](https://docs.oracle.com/middleware/1212/wls/NODEM/java_nodemgr.htm#NODEM173) documentation for details.

## Configuring WebLogic

Configure the JVM and other options in a `setUserOverridesLate` WebLogic startup script and in your Managed Server UI.

1. Create a `setUserOverridesLate.sh` script in `[Your Domain]/bin`.

1. Add the following settings.

    ```bash
    export DERBY_FLAG="false"
    export JAVA_OPTIONS="${JAVA_OPTIONS} -Dfile.encoding=UTF-8 -Duser.timezone=GMT -da:org.apache.lucene... -da:org.aspectj..."
    export JAVA_PROPERTIES="-Dfile.encoding=UTF-8 ${JAVA_PROPERTIES} ${CLUSTER_PROPERTIES}"
    export MW_HOME="[place your WebLogic Server folder path here]"
    export USER_MEM_ARGS="-Xms512m -Xmx2048m"
    export WLS_MEM_ARGS_64BIT="-Xms512m -Xmx2048m"
    export WLS_MEM_ARGS_32BIT="-Xms512m -Xmx2048m"
    ```

    | **Important:** For @product@ to work properly, the application server JVM
    | must use the `GMT` time zone and `UTF-8` file encoding.

    | **Important:** On JDK 11, the setting `-Djava.locale.providers=JRE,COMPAT,CLDR` is required to display four-digit years. Since JDK 9, the Unicode Common Locale Data Repository (CLDR) is the default locales provider. CLDR does not provide years in a four-digit format (see [LPS-87191](https://issues.liferay.com/browse/LPS-87191)). This setting works around the issue by using JDK 8's default locales provider.
    
    The `DERBY_FLAG` setting disables the Derby server built in to WebLogic, as DXP does not require this server.
    
    `JAVA_OPTIONS` sets DXP's UTF-8 requirement, Lucene usage, and Aspect Oriented Programming via AspectJ.

    `JAVA_PROPERTIES` also sets DXP's UTF-8 requirement.

    Set `MW_HOME` to the folder containing the WebLogic server on the machine. For example,

    ```bash
    export MW_HOME="/Users/ray/Oracle/wls12210"
    ```
        
    The `*_MEM_ARGS` variables set DXP's starting and maximum heap memory capacity.

5.  You must also ensure that the Node Manager sets @product@'s memory
    requirements when starting the Managed Server. In the Admin Server's console
    UI, navigate to the Managed Server you want to deploy @product@ to and
    select the *Server Start* tab. Enter the following parameters into the
    *Arguments* field: 

        -Xmx2048m -XX:MaxMetaspaceSize=512m

    Click *Save* when you're finished. 

Next, you'll set some @product@-specific properties for your @product@ installation. 

## Setting @product@ Properties

Before installing @product@, you must set the 
[*Liferay Home*](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)
folder's location via the `liferay.home` property in a `portal-ext.properties` 
file. You can also use this file to override 
[other @product@ properties](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html) 
that you may need. 

First, decide which folder you want to serve as Liferay Home. In WebLogic, your
domain's folder is generally Liferay Home, but you can choose any folder on your
machine. Then create your `portal-ext.properties` file and add the
`liferay.home` property: 

    liferay.home=/full/path/to/your/liferay/home/folder

Remember to change this file path to the location on your machine that you want 
to serve as Liferay Home. 

Now that you've created your `portal-ext.properties` file, you must put it 
inside the @product@ WAR file. Expand the @product@ WAR file and place 
`portal-ext.properties` in the `WEB-INF/classes` folder. Later, you can deploy 
the expanded archive to WebLogic. Alternatively, you can re-WAR the expanded 
archive for later deployment. In either case, @product@ reads your property settings 
once it starts up. 

If you need to make any changes to `portal-ext.properties` after @product@ 
deploys, you can find it in your domain's `autodeploy/ROOT/WEB-INF/classes` 
folder. Note that the `autodeploy/ROOT` folder contains the @product@ 
deployment. 

Next, you'll install @product@'s dependencies. 

## Installing @product@ Dependencies

You must now install @product@'s dependencies. Recall that earlier you 
downloaded two ZIP files containing these dependencies. Install their contents 
now: 

1.  `liferay-dxp-dependencies-[version].zip`: Unzip this file 
    and place its contents in your WebLogic domain's `lib` folder. 

2.  `liferay-dxp-osgi-[version].zip`: Unzip this file and 
    place its contents in the `[Liferay Home]/osgi` folder (create this folder if
    it doesn't exist).

3.  Download your database driver `.jar` file and copy it to your domain's `lib` folder. Please see the [compatibility matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.1+Compatibility+Matrix/9f9c917a-c620-427b-865d-5c4b4a00be85) for a list of supported databases.

Next, you'll configure your database. 

## Database Configuration

Use the following procedure if you want WebLogic to manage your database for 
@product@. You can skip this section if you want to use @product@'s built-in 
Hypersonic database. 

1.  Log in to your AdminServer console.

2.  In the *Domain Structure* tree, find your domain and navigate to *Services*
    &rarr; *JDBC* &rarr; *Data Sources*.

3.  To create a new data source, click *New*. Fill in the *Name* field with 
    `Liferay Data Source` and the *JNDI Name* field with `jdbc/LiferayPool`.
    Select your database type and driver. For example, MySQL is *MySQL's Driver
    (Type 4) Versions:using com.mysql.jdbc.Driver*. Click *Next* to continue.

4.  Accept the default settings on this page and click *Next* to move on. 

5.  Fill in your database information for your MySQL database. 

6.  If using MySQL, add the text 
    `?useUnicode=true&characterEncoding=UTF-8&\useFastDateParsing=false` to the
    URL line and test the connection. If it works, click *Next*. 

7.  Select the target for the data source and click *Finish*. 

8.  You must now tell @product@ about the JDBC data source. Create a 
    `portal-ext.propreties` file in your Liferay Home directory, and add the line 
    `jdbc.default.jndi.name=jdbc/LiferayPool`. 

Alternatively, you can make the above configuration strictly via properties in 
the `portal-ext.properties` file. To do so, place the following properties and 
values in the file. Be sure to change the `your*` values with the values 
appropriate for your database's configuration (if using MySQL): 

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://your.db.ip.address/yourdbname?useUnicode?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=yourdbuser
    jdbc.default.password=yourdbpassword

Next, you'll configure your mail session. 

## Mail Configuration

If you want WebLogic to manage your mail session, use the following procedure. 
If you want to use Liferay's built-in mail session (recommended), skip this
section. 

1.  Start WebLogic and log in to your Admin Server's console.

2.  Select *Services* &rarr; *Mail Sessions* from the *Domain Structure* box on
    the left hand side of your Admin Server's console UI. 

3.  Click *New* to begin creating a new mail session. 

4.  Name the session *LiferayMail* and give it the JNDI name `mail/MailSession`.
    Then fill out the *Session Username*, *Session Password*, *Confirm Session
    Password*, and *JavaMail Properties* fields as necessary for your mail
    server. See the 
    [WebLogic documentation](http://docs.oracle.com/middleware/1221/wls/FMWCH/pagehelp/Mailcreatemailsessiontitle.html)
    for more information on these fields. Click *Next* when you're done. 

5.  Choose the Managed Server that you'll install @product@ on, and click
    *Finish*. Then shut down your Managed and Admin Servers. 

6.  With your Managed and Admin servers shut down, add the following property to
    your `portal-ext.properties` file in Liferay Home: 

        mail.session.jndi.name=mail/MailSession

    @product@ references your WebLogic mail session via this property setting. 
    If you've already deployed @product@, you can find your 
    `portal-ext.properties` file in your domain's 
    `autodeploy/ROOT/WEB-INF/classes` folder. 

Your changes take effect upon restarting your Managed and Admin servers. 

## Deploying @product@

As mentioned earlier, although you can deploy @product@ to a WebLogic Admin 
Server, you should instead deploy it to a WebLogic Managed Server. Dedicating 
the Admin Server to managing other servers that run your apps is a best 
practice. 

Follow these steps to deploy @product@ to a Managed Server: 

1.  Make sure the Managed Server you want to deploy @product@ to is shut down. 

2.  In your Admin Server's console UI, select *Deployments* from the *Domain 
    Structure* box on the left hand side. Then click *Install* to start a new
    deployment. 

3.  Select the @product@ WAR file or its expanded contents on your file system.
    Alternatively, you can upload the WAR file by clicking the *Upload your
    file(s)* link. Click *Next*. 

4.  Select *Install this deployment as an application* and click *Next*.

5.  Select the Managed Server you want to deploy @product@ to and click *Next*. 

6.  If the default name is appropriate for your installation, keep it.
    Otherwise, give it a name of your choosing and click *Next*. 

7.  Click *Finish*. After the deployment finishes, click *Save* if you want to
    save the configuration. 

8.  Start the Managed Server where you deployed @product@. @product@ precompiles
    all the JSPs and then launches. 

Nice work! Now you're running @product@ on WebLogic. 

| After deploying @product@, you may see excessive warnings and log messages, such
| as the ones below, involving `PhaseOptimizer`. These are benign and can be
| ignored. Make sure to adjust your app server's logging level or log filters to
| avoid excessive benign log messages.
| 
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass gatherExternProperties
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass checkControlFlow
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
|     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
