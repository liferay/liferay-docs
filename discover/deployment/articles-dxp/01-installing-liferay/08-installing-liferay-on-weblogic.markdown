# Installing Liferay DXP on WebLogic 12c R2

This article shows you how to install Liferay DXP in WebLogic 12c R2. Note that 
although it's possible to install Liferay DXP in a WebLogic Admin Server, this 
isn't recommended. It's best practice to install web apps, including Liferay 
DXP, in a WebLogic Managed server. By deploying to a Managed Server, you’ll be 
able to startup/shutdown Liferay DXP more quickly and you’ll more easily be able 
to extend DXP into a cluster configuration. This article therefore focuses on 
installing DXP in a Managed Server. 

Before getting started, you should take care of a few things. First, it's 
assumed that your Admin and Managed Servers already exist. See 
[WebLogic's documentation](http://www.oracle.com/technetwork/middleware/weblogic/documentation/index.html) 
for instructions on setting up and configuring Admin and Managed Servers. 

You should also read the following to familiarize yourself with Liferay's 
general installation steps: 

- [Liferay Installation Overview](/discover/deployment/-/knowledge_base/7-0/liferay-installation-overview)
- [Installing Liferay Portal](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal)
- [Installing Liferay Manually](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually)

And lastly, download Liferay DXP's WAR file and dependencies from 
[http://files.liferay.com/private/ee](http://files.liferay.com/private/ee) 
or the customer portal on 
[liferay.com](https://www.liferay.com/). 
You'll need the following files:

- `liferay-dxp-digital-enterprise-[version].war`: Liferay DXP WAR file

- `liferay-dxp-digital-enterprise-dependencies-[version].zip`: Liferay DXP 
  dependencies

- `liferay-dxp-digital-enterprise-osgi-[version].zip`: Liferay DXP OSGi 
  dependencies

Without any further ado, get ready to install Liferay DXP in WebLogic! 

## Setting Liferay Properties

Before installing Liferay, you must preconfigure some of its properties in a 
`portal-ext.properties` file. This is required to set the 
[*Liferay Home*](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
folder, the autodeploy folders, and a security property required by Liferay's 
Control Panel. 

First, decide which directory you want to serve as Liferay Home. In WebLogic, 
Liferay Home is generally `$WL_HOME/user_projects/domains/your_domain_name`, 
where `your_domain_name` is the name of your WebLogic domain. Then create your 
`portal-ext.properties` file and add the following properties to it:

    liferay.home=/full/path/to/your/liferay/home/folder
    auto.deploy.deploy.dir=${liferay.home}/deploy
    auto.deploy.weblogic.dest.dir=${liferay.home}/deploywar
    redirect.url.security.mode=domain

The `liferay.home` property sets the location of Liferay Home. This property is 
then used by the `auto.deploy.*` properties to set the location that WAR plugins 
are deployed to. Note that you must create the `deploywar` folder manually. 
Lastly, the `redirect.url.security.mode` property setting is required to fix an 
issue where the buttons in Liferay's Control Panel don't work. 

Now that these properties are set, there are two ways to utilize your 
`portal-ext.properties` file:
<!-- 
If choosing option #1, where does portal-ext.properties get deployed to once 
Liferay deploys? I can't find it anywhere. If I have other properties I want to 
set after deployment, should I recreate it in Liferay Home?
-->

1. Expand the Liferay DXP WAR file and place `portal-ext.properties` in the 
   `WEB-INF/classes` folder. Later, you can deploy the expanded archive to 
   WebLogic. Alternatively, you can re-WAR the expanded archive for later 
   deployment.

2. Place `portal-ext.properties` in your Liferay Home folder.

In either case, Liferay reads your property settings once it starts up. 

Next, you'll install Liferay's dependencies.

## Installing Liferay Dependencies

Unzip the dependencies and place them in your WebLogic domain's `lib` folder. 
Generally, this is `Liferay_Home/lib`. 

Next, you must install Liferay's OSGi dependencies. Extract the OSGi 
dependencies to the `osgi` folder (create this folder if it doesn't exist) in 
your Liferay Home folder. 

<!-- Are the theme WARs required? -->

Liferay also needs the driver JAR file applicable for the database you plan to 
use for Liferay. If WebLogic does not already have access to the JDBC driver for 
your database, copy the driver JAR to your domain’s `lib` folder as well. 

## Configuring the Node Manager

WebLogic requires a Node Manager to start and stop managed servers. Before 
installing Liferay, you must configure the Node Manager included with your 
WebLogic installation. You'll do this via the 
`domains/your_domain_name/nodemanager/nodemanager.properties` file. Open this 
file, and set the `SecureListener` property to `false`: 

    SecureListener=false

This disables the SSL requirement for the Node Manager, allowing it to accept 
unencrypted connections. 
<!-- Why is this required? What if a customer has to use SSL? -->

If you're running WebLogic on Mac or Linux, you must also set the 
`NativeVersionEnabled` property to `false`:

    NativeVersionEnabled=false

## Configuring WebLogic

Next, you must set some properties in your WebLogic startup scripts. First, set 
UTF-8 file encoding in the clustering section of your domain’s 
`bin/setDomainEnv.[cmd|sh]` file. To do this, open the 
`bin/setDomainEnv.[cmd|sh]` file and change this: 

    JAVA_PROPERTIES="${JAVA_PROPERTIES} ${CLUSTER_PROPERTIES}"

To this:

    JAVA_PROPERTIES="-Dfile.encoding=utf8 ${JAVA_PROPERTIES} ${CLUSTER_PROPERTIES}"

Next, you must set your Java memory arguments and other properties in your 
managed server's startup script. To do so, set the following properties in your 
domain's `bin/startManagedWebLogic.[cmd|sh]` file. Note that you must use `set` 
instead of `export` if you're on Windows: 

    export USER_MEM_ARGS="-Xmx2048m -XX:MaxMetaspaceSize=512m"

    export MW_HOME="/your/weblogic/server/parent/directory"

    export JAVA_OPTIONS="${JAVA_OPTIONS}  -da:org.apache.lucene... -da:org.aspectj..."

These settings support Liferay's memory requirements, Lucene usage, and Aspect 
Oriented Programming via AspectJ. Make sure to set `MW_HOME` to the directory 
containing your WebLogic server on your machine. 

You must now disable WebLogic's included Derby installation. Do this by adding 
the following in your domain's `bin/startWebLogic.[cmd|sh]` file. Remember to 
use `set` instead of `export` if you're on Windows: 

    export DERBY_FLAG=false

Next, you'll configure your database. 

## Database Configuration

If you want WebLogic to manage your database for Liferay, use the following 
procedure. If you want to use Liferay’s built-in data source, you can skip this 
section. 

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

8. You must now tell Liferay about the JDBC data source. Create a 
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

## Mail Configuration

If you want WebLogic to manage your mail session, use the following procedure. 
If you want to use Liferay’s built-in mail session (recommended), you can skip 
this section. 

1. Start WebLogic and log in to your Admin Server's console.

2. Select *Services* &rarr; *Mail Sessions* from the *Domain Structure* box on 
   the left hand side of your Admin Server's console UI. 

3. Click *New* to begin creating a new mail session. 

4. Name the session *Liferay Mail*, give it the JNDI name `mail/MailSession`, 
   and click *Next*. 

5. Choose the managed server that you'll install Liferay on, and click *Finish*. 

6. Shut down the managed server you'll install Liferay on. This is required so 
   that you can set the portal property required to point Liferay to your mail 
   session. 

7. If it doesn't already exist, create a `portal-ext.properties` file in your 
   Liferay Home folder and add the following:

    mail.session.jndi.name=mail/MailSession

Liferay DXP references your WebLogic mail session via this property setting. 

## Security Configuration

## Deploying Liferay DXP

As mentioned earlier, although you can deploy Liferay DXP to a WebLogic Admin 
Server, you should instead deploy DXP to a WebLogic Managed Server. As a best 
practice, you should dedicate the Admin Server to managing other servers that 
run your apps. 

Regardless of which server(s) you target to host DXP in your WebLogic domain, 
here are steps for deploying DXP: 

1. Start your WebLogic server, if it's not already running. 

2. In your Admin Server's console UI, select *Deployments* from the *Domain 
   Structure* box on the left hand side. Then click *Install* to start a new 
   deployment. 

3. Select the Liferay DXP WAR file on your file system. Alternatively, you can 
   upload it by clicking the *Upload your file(s)* link. Click *Next*.

4. Select *Install this deployment as an application* and click *Next*.

5. Select the server you want to deploy DXP to, and click *Next*. 

6. If the default name is appropriate for your installation, keep it. Otherwise, 
   give it a name of your choosing and click *Next*.

7. Click *Finish*. After the deployment finishes, click *Save*. Liferay 
   precompiles all the JSPs, and then launches. 
