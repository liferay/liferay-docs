# Installing @product@ on WebLogic 12c R2 [](id=installing-liferay-dxp-on-weblogic-12c-r2)

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

- [@product@ Installation Overview](/discover/deployment/-/knowledge_base/7-1/liferay-installation-overview)
- [Installing @product@](/discover/deployment/-/knowledge_base/7-1/installing-liferay-portal)
- [Installing @product@ Manually](/discover/deployment/-/knowledge_base/7-1/installing-liferay-manually)

Finally, download @product@'s WAR file and dependencies from the 
[Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/7-1).
You'll need these files:

- `liferay-dxp-digital-enterprise-[version].war`: @product@ WAR file

- `liferay-dxp-digital-enterprise-dependencies-[version].zip`: @product@ 
  dependencies

- `liferay-dxp-digital-enterprise-osgi-[version].zip`: @product@ OSGi 
  dependencies

**Checkpoint**

The following jars should be present within the `liferay-dxp-digital-enterprise-dependencies-[version].zip`:

1. `com.liferay.petra.concurrent.jar`
2. `com.liferay.petra.executor.jar`
3. `com.liferay.petra.function.jar`
4. `com.liferay.petra.io.jar`
5. `com.liferay.petra.lang.jar`
6. `com.liferay.petra.memory.jar`
7. `com.liferay.petra.nio.jar`
8. `com.liferay.petra.process.jar`
9. `com.liferay.petra.reflect.jar`
10. `com.liferay.petra.string.jar`
11. `com.liferay.registry.api.jar`
12. `hsql.jar`
13. `portal-kernel.jar`
14. `portlet.jar`


The following folders should be present within the `/liferay/osgi` folder: 

1. `Configs`
2. `Core`
3. `Marketplace`
4. `Modules`
5. `Portal`
6. `Static`
7. `Test`
8. `War`


Without any further ado, get ready to install @product@ in WebLogic! 

## Configuring WebLogic's Node Manager [](id=configuring-weblogics-node-manager)

WebLogic requires a Node Manager to start and stop managed servers. Before 
installing @product@, you must configure the Node Manager included with your 
WebLogic installation. You'll do this via the 
`domains/your_domain_name/nodemanager/nodemanager.properties` file. Open this 
file and set the `SecureListener` property to `false`: 

    SecureListener=false

This setting disables the encryption (SSL) requirement for the Node Manager, 
allowing it to accept unencrypted connections. Although it's possible to run 
@product@ with this property set to `true`, you may encounter difficulties doing 
so. Also note that with `SecureListener` set to `true`, you must configure your 
machine in the Admin Server's console to accept unencrypted connections from the 
Node Manager. To do this, first log in to your Admin Server and select 
*Environment* &rarr; *Machines* from the *Domain Structure* box on the left. 
Click your machine in the table and then select the *Configuration* &rarr; *Node 
Manager* tab. In the *Type* field, select *Plain* from the selector menu, and 
then click *Save*. You must restart your Admin Server for this change to take 
effect. 

If you're running WebLogic on Mac or Linux, you may also need to set the 
`NativeVersionEnabled` property to `false`: 

    NativeVersionEnabled=false

This tells the Node Manager to start in non-native mode. This is required for 
the platforms where WebLogic doesn't provide native Node Manager libraries. 

## Configuring WebLogic [](id=configuring-weblogic)

Next, you must set some variables in two WebLogic startup scripts. These 
variables and scripts are as follows. Be sure to use `set` instead of `export` 
if you're on Windows. 

1.  `your-domain/startWebLogic.[cmd|sh]`: This is the Admin Server's startup
    script. 

2.  `your-domain/bin/startWebLogic.[cmd|sh]`: This is the startup script for
    Managed Servers. 

    Add the following variables to both `startWebLogic.[cmd|sh]` scripts:

        export DERBY_FLAG="false"
        export JAVA_OPTIONS="${JAVA_OPTIONS} -Dfile.encoding=UTF-8 -da:org.apache.lucene... -da:org.aspectj..."
        export MW_HOME="/your/weblogic/directory"
        export USER_MEM_ARGS="-Xmx2048m"

    The `DERBY_FLAG` setting disables the Derby server built in to WebLogic, as 
    @product@ doesn't require this server. The remaining settings support @product@'s 
    memory requirements, UTF-8 requirement, Lucene usage, and Aspect Oriented 
    Programming via AspectJ. Also make sure to set `MW_HOME` to the directory 
    containing your WebLogic server on your machine. For example: 

        export MW_HOME="/Users/ray/Oracle/wls12210"


3.  Some of the settings are also found in the `your-domain/bin/SetDomainEnv.[cmd|sh]` . Add the following variables (Windows):

        set WLS_MEM_ARGS_64BIT=-Xms512m -Xmx2048m 
        set WLS_MEM_ARGS_32BIT=-Xms512m -Xmx2048m

    or on Mac or Linux:

        WLS_MEM_ARGS_64BIT="-Xms512m -Xmx2048m"
        export WLS_MEM_ARGS_64BIT

        WLS_MEM_ARGS_32BIT="-Xms512m -Xmx2048m"
        export WLS_MEM_ARGS_32BIT

4.  You must also ensure that the Node Manager sets @product@'s memory
    requirements when starting the Managed Server. In the Admin Server's console
    UI, navigate to the Managed Server you want to deploy @product@ to and
    select the *Server Start* tab. Enter the following parameters into the
    *Arguments* field: 

        -Xmx2048m -XX:MaxMetaspaceSize=512m

    Click *Save* when you're finished. 

Next, you'll set some @product@-specific properties for your @product@ installation. 

## Setting @product@ Properties [](id=setting-liferay-properties)

Before installing @product@, you must set the 
[*Liferay Home*](/discover/deployment/-/knowledge_base/7-1/installing-liferay-portal#liferay-home)
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

## Installing @product@ Dependencies [](id=installing-liferay-dependencies)

You must now install @product@'s dependencies. Recall that earlier you 
downloaded two ZIP files containing these dependencies. Install their contents 
now: 

1.  `liferay-dxp-digital-enterprise-dependencies-[version].zip`: Unzip this file 
    and place its contents in your WebLogic domain's `lib` folder. 

2.  `liferay-dxp-digital-enterprise-osgi-[version].zip`: Unzip this file and 
    place its contents in the `Liferay_Home/osgi` folder (create this folder if
    it doesn't exist).

You must also add your database's driver JAR file to your domain's `lib` folder.
Note that although Hypersonic is fine for testing purposes, you **should not**
use it for production @product@ instances. 

**Checkpoint**

Your domain `lib` folder has these jars:

* `com.liferay.petra.concurrent.jar`
* `com.liferay.petra.executor.jar`
* `com.liferay.petra.function.jar`
* `com.liferay.petra.io.jar`
* `com.liferay.petra.lang.jar`
* `com.liferay.petra.memory.jar`
* `com.liferay.petra.nio.jar`
* `com.liferay.petra.process.jar`
* `com.liferay.petra.reflect.jar`
* `com.liferay.petra.string.jar`
* `com.liferay.registry.api.jar`
* `hsql.jar`
* `portal-kernel.jar`
* `portlet.jar`

A JDBC driver for your database has been added to your domain's `lib` folder.
Here are some common JDBC drivers:

* [`mariadb.jar`](https://downloads.mariadb.org/) 
* [`mysql.jar`](http://dev.mysql.com/downloads/connector/j)
* [`postgres.jar`](https://jdbc.postgresql.org/download/postgresql-42.0.0.jar)

Your `[Liferay Home]/osgi` folder has these subfolders:

* `Configs`
* `Core`
* `Marketplace`
* `Modules`
* `Portal`
* `Static`
* `Test`
* `War`

Next, you'll configure your database. 

## Database Configuration [](id=database-configuration)

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

## Mail Configuration [](id=mail-configuration)

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

## Deploying @product@ [](id=deploying-liferay-dxp)

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

+$$$

After deploying @product@, you may see an excessive amount of the following
warnings and log messages in the console involving the `PhaseOptimizer`. These
are benign and can be ignored. Make sure you adjust your app server's logging
level or log filters in order to avoid excessive log messages.

    May 2, 2018 9:12:27,295 PM GMT> <Warning> <com.google.javascript.jscomp.PhaseOptimizer> <BEA-000000> <Skipping pass checkConsts>
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass gatherExternProperties
    <May 2, 2018 9:12:27,296 PM GMT> <Warning> <com.google.javascript.jscomp.PhaseOptimizer> <BEA-000000> <Skipping pass gatherExternProperties>
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass checkControlFlow
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
    current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
    <May 2, 2018 9:12:27,295 PM GMT>

$$$
