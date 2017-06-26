# Installing @product@ on WebLogic 12c R2 [](id=installing-liferay-dxp-on-weblogic-12c-r2)

Although it's possible to install @product@ in a WebLogic Admin
Server, this isn't recommended. It's best practice to install web apps,
including @product@, in a WebLogic Managed server. By deploying to a Managed
Server, you’ll be able to start/shutdown @product@ more quickly, and you’ll
more easily be able to extend @product@ into a cluster configuration. This article
therefore focuses on installing @product@ in a Managed Server. 

Before getting started, you should take care of a few things. First, it's 
assumed that your Admin and Managed Servers already exist. See 
[WebLogic's documentation](http://www.oracle.com/technetwork/middleware/weblogic/documentation/index.html) 
for instructions on setting up and configuring Admin and Managed Servers. 

You should also read the following articles to familiarize yourself with
@product@'s general installation steps: 

- [@product@ Installation Overview](/discover/deployment/-/knowledge_base/7-0/liferay-installation-overview)
- [Installing @product@](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal)
- [Installing @product@ Manually](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually)

And lastly, download @product@'s WAR file and dependencies from the [Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise). 
You'll need the following files:

- `liferay-dxp-digital-enterprise-[version].war`: @product@ WAR file

- `liferay-dxp-digital-enterprise-dependencies-[version].zip`: @product@ 
  dependencies

- `liferay-dxp-digital-enterprise-osgi-[version].zip`: @product@ OSGi 
  dependencies

Without any further ado, get ready to install @product@ in WebLogic! 

## Configuring WebLogic's Node Manager [](id=configuring-weblogics-node-manager)

WebLogic requires a Node Manager to start and stop managed servers. Before 
installing @product@, you must configure the Node Manager included with your 
WebLogic installation. You'll do this via the 
`domains/your_domain_name/nodemanager/nodemanager.properties` file. Open this 
file, and set the `SecureListener` property to `false`: 

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

<!-- 
Do we need a section on setting MW_HOME, the classpath, and path, as set 
in setWLSEnv.sh? Or is this something an WLS administrator will already know or 
have done? 
-->

## Configuring WebLogic [](id=configuring-weblogic)

Next, you must set some variables in two WebLogic startup scripts. These 
variables and scripts are as follows. Be sure to use `set` instead of `export` 
if you're on Windows. 

1. `your-domain/startWebLogic.[cmd|sh]`: This is the Admin Server's startup 
   script. 

2. `your-domain/bin/startWebLogic.[cmd|sh]`: This is the startup script for 
   Managed Servers. 

Add the following variables to both `startWebLogic.[cmd|sh]` scripts:

    export DERBY_FLAG="false"
    export JAVA_OPTIONS="${JAVA_OPTIONS} -Dfile.encoding=UTF-8 -da:org.apache.lucene... -da:org.aspectj..."
    export MW_HOME="/your/weblogic/directory"
    export USER_MEM_ARGS="-Xmx1024m -XX:MetaspaceSize=512m"

The `DERBY_FLAG` setting disables the Derby server built in to WebLogic, as 
@product@ doesn't require this server. The remaining settings support @product@'s 
memory requirements, UTF-8 requirement, Lucene usage, and Aspect Oriented 
Programming via AspectJ. Also make sure to set `MW_HOME` to the directory 
containing your WebLogic server on your machine. For example: 

    export MW_HOME="/Users/ray/Oracle/wls12210"

You must also ensure that the Node Manager sets @product@'s memory 
requirements when starting the Managed Server. In the Admin Server's console UI, 
navigate to the Managed Server you want to deploy @product@ to and select the *Server 
Start* tab. Enter the following into the *Arguments* field: 

    -Xmx2048m -XX:MaxMetaspaceSize=512m

Click *Save* when you're finished. 

Next, you'll set some @product@-specific properties for your @product@ installation. 

## Setting @product@ Properties [](id=setting-liferay-properties)

Before installing @product@, you must set the 
[*Liferay Home*](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
folder's location via the `liferay.home` property in a `portal-ext.properties` 
file. You can also use this file to override 
[other @product@ properties](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html) 
that you may need. 

First, decide which directory you want to serve as Liferay Home. In WebLogic, 
your domain's folder is generally Liferay Home, but you can choose any folder on 
your machine. Then create your `portal-ext.properties` file and add the 
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

1. `liferay-dxp-digital-enterprise-dependencies-[version].zip`: Unzip this file 
   and place its contents in your WebLogic domain's `lib` folder. 

2. `liferay-dxp-digital-enterprise-osgi-[version].zip`: Unzip this file and 
   place its contents in the `Liferay_Home/osgi` folder (create this folder if 
   it doesn't exist).

If you don't want to use @product@'s built-in Hypersonic database, you must also 
add your database's driver JAR file to your domain’s `lib` folder. Note that 
although Hypersonic is fine for testing purposes, you **should not** use it for 
production @product@ instances. 

Next, you'll configure your database. 

## Database Configuration [](id=database-configuration)

Use the following procedure if you want WebLogic to manage your database for 
@product@. You can skip this section if you want to use @product@'s built-in 
Hypersonic database. 

1. Log in to your AdminServer console.

2. In the *Domain Structure* tree, find your domain and navigate to 
   *Services* &rarr; *JDBC* &rarr; *Data Sources*.

3. To create a new data source, click *New*. Fill in the *Name* field with 
   `Liferay Data Source` and the *JNDI Name* field with `jdbc/LiferayPool`. 
   Select *MySQL* as the database type, and 
   *MySQL's Driver (Type 4) Versions:using com.mysql.jdbc.Driver* as the 
   database driver. Click *Next* to continue.

4. Accept the default settings on this page and click *Next* to move on. 

5. Fill in the database information for your MySQL database. 

6. Add the text 
   `?useUnicode=true&characterEncoding=UTF-8&\useFastDateParsing=false` to the 
   URL line and test the connection. If it works, click *Next*. 

7. Select the target for the data source and click *Finish*. 

8. You must now tell @product@ about the JDBC data source. Create a 
   `portal-ext.propreties` file in your Liferay Home directory, and add the line 
   `jdbc.default.jndi.name=jdbc/LiferayPool`. 

Alternatively, you can make the above configuration strictly via properties in 
the `portal-ext.properties` file. To do so, place the following properties and 
values in the file. Be sure to change the `your*` values with the values 
appropriate for your database's configuration: 

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://your.db.ip.address/yourdbname?useUnicode?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=yourdbuser
    jdbc.default.password=yourdbpassword

Next, you'll configure your mail session. 

## Mail Configuration [](id=mail-configuration)

If you want WebLogic to manage your mail session, use the following procedure. 
If you want to use Liferay’s built-in mail session (recommended), you can skip 
this section. 

1. Start WebLogic and log in to your Admin Server's console.

2. Select *Services* &rarr; *Mail Sessions* from the *Domain Structure* box on 
   the left hand side of your Admin Server's console UI. 

3. Click *New* to begin creating a new mail session. 

4. Name the session *LiferayMail* and give it the JNDI name `mail/MailSession`. 
   Then fill out the *Session Username*, *Session Password*, *Confirm Session 
   Password*, and *JavaMail Properties* fields as necessary for your mail 
   server. See the 
   [WebLogic documentation](http://docs.oracle.com/middleware/1221/wls/FMWCH/pagehelp/Mailcreatemailsessiontitle.html) 
   for more information on these fields. Click *Next* when you're done. 

5. Choose the Managed Server that you'll install @product@ on, and click *Finish*. 
   Then shut down your Managed and Admin Servers. 

6. With your Managed and Admin servers shutdown, add the following property to 
   your `portal-ext.properties` file: 

        mail.session.jndi.name=mail/MailSession

    @product@ references your WebLogic mail session via this property setting. 
    If you've already deployed @product@, you can find your 
    `portal-ext.properties` file in your domain's 
    `autodeploy/ROOT/WEB-INF/classes` folder. 

Your changes will take effect upon restarting your Managed and Admin servers. 

Next, you'll configure the security settings in your WebLogic server to work 
with @product@. 

## Security Configuration [](id=security-configuration)

When you're ready to start using 
[Liferay Marketplace](https://web.liferay.com/marketplace) 
apps, you'll want to protect your @product@ instance and your WebLogic server from 
security threats. To do so, you must enable Java Security on your WebLogic 
server and specify a security policy to grant @product@ access to your server. 

First, you'll grant @product@ access to your server. This configuration opens all 
permissions--you can fine-tune your policy's permissions later. If it doesn't 
already exist, create a policy file named `weblogic.policy` in your 
`$WL_HOME/server/lib` folder. Replace its contents with the following: 

    grant {
        permission java.security.AllPermission;
    };

To enable security on your WebLogic server and direct the server to your policy
file, open the `setDomainEnv.[cmd|sh]` file in your domain's `bin `folder. Then 
set the `-Djava.security.manager` Java option and set the property 
`-Djava.security.policy` to your `weblogic.policy` file. You can specify both 
settings on the same line like this: 

    -Djava.security.manager -Djava.security.policy==$WL_HOME/server/lib/weblogic.policy

The double equals sign tells the app server to use only this policy file. Any 
other security policies are ignored. 

For extensive information on Java SE Security Architecture, see its 
specification docs at 
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see the section 
[Understanding Plugin Security Management](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in the Developer's Guide to learn how to configure @product@ plugin access to
resources. 

## Deploying @product@ [](id=deploying-liferay-dxp)

As mentioned earlier, although you can deploy @product@ to a WebLogic Admin 
Server, you should instead deploy it to a WebLogic Managed Server. Dedicating 
the Admin Server to managing other servers that run your apps is a best 
practice. 

Follow these steps to deploy @product@ to a Managed Server: 

1. Make sure the Managed Server you want to deploy @product@ to is shut down. 

2. In your Admin Server's console UI, select *Deployments* from the *Domain 
   Structure* box on the left hand side. Then click *Install* to start a new 
   deployment. 

3. Select the @product@ WAR file or its expanded contents on your file system. 
   Alternatively, you can upload the WAR file by clicking the *Upload your 
   file(s)* link. Click *Next*. 

4. Select *Install this deployment as an application* and click *Next*.

5. Select the Managed Server you want to deploy @product@ to, and click *Next*. 

6. If the default name is appropriate for your installation, keep it. Otherwise, 
   give it a name of your choosing and click *Next*. 

7. Click *Finish*. After the deployment finishes, click *Save* if you want to 
   save the configuration.  

8. Start the Managed Server you deployed @product@ to. @product@ precompiles all the JSPs, 
   and then launches. 

Nice work! Now you're running @product@ on WebLogic. 
