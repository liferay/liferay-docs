# Installing Liferay on Oracle WebLogic 12c (12.1.2 and higher) [](id=installing-liferay-on-oracle-weblogic-12c-12-1-2-and-h)

In this section, you'll learn how to install Liferay on Oracle WebLogic 12c.
Since you're using Oracle WebLogic, you may wonder if Liferay supports XA
transactions. Liferay doesn't require XA transactions, but it supports XA.
Let's get acquainted with how Liferay fits in with your current WebLogic domain. 

Liferay by default defines *Liferay Home* as one folder above the
domain to which you will be installing Liferay. For example, if your domain
location is `/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain`, then your
Liferay Home is `/Oracle/Middleware/Oracle_Home/user_projects/domains`.

For this section, the variable `$WL_HOME` refers to your WebLogic server's
installation `/Oracle/Middleware/Oracle_Home/wlserver`. 

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file is called `liferay-portal-[version].war` and
the dependencies file is called `liferay-portal-dependencies-[version].zip`.

These instructions assume you have already configured a domain and server and
that you have access to the WebLogic console.

If you still have the `mainWebApp` module installed, remove it first.

Let's get started by installing the `.jar` files Liferay needs.

## Dependency Jars [](id=dependency-jars)

Liferay needs the driver `.jar` file applicable for your database, as well as
the `.jar` files contained in the Liferay Dependencies Archive. They should be 
on your domain's global classpath. 

1.  Navigate to the folder that corresponds to the domain where you'll 
    install Liferay. Inside this folder is a `lib` folder. Unzip the Liferay
    Dependencies Archive and copy the dependency `.jar` files to the `lib`
    folder.

2.  If WebLogic does not already have access to the JDBC driver for your
    database, copy the driver to your domain's `lib` folder as well.

So far so good. Your `.jar` files are in place and ready for Liferay.

Let's proceed with configuring WebLogic.

## Configuring WebLogic [](id=configuring-weblogic)

You need to make the following adjustments in your configuration to support
Liferay:

- Set WebLogic Server's memory arguments.
- Set the maximum size for Java's permanent generation space.
- Set the file encoding.
- Enable Lucene support.
- Enable AspectJ support.

You can set WebLogic Server's memory arguments in your `setDomainEnv.[cmd|sh]`
environment script file found in your domain's `bin` folder. For the Sun JVM,
set the WLS memory arguments for 64 bit and 32 bit architectures to
`-Xms256m -Xmx1024m` at a minimum. For all other JVMs, set the 64 bit and
32 bit WLS memory arguments to `-Xms512m -Xmx512m` respectively. 

Set the permanent generation space for 64 bit and 32 bit architectures
to `-XX:PermSize=256m`.

Lastly, make sure to specify UTF-8 for Java's file encoding, by including
`-Dfile.encoding=UTF8` as a Java property.

If you're on Windows, for example, you'd edit your `setDomainEnv.cmd` file and
find the call to the `commEnv.cmd` script. After this call, you'd update your
memory arguments and permanent generation space settings to be like the
following code: 

    ...
    if "%JAVA_VENDOR"=="Sun" (
        set WLS_MEM_ARGS_64BIT=-Xms256m -Xmx1024m
        set WLS_MEM_ARGS_32BIT=-Xms256m -Xmx1024m
    ) else (
        set WLS_MEM_ARGS_64BIT=-Xms512m -Xmx512m
        set WLS_MEM_ARGS_32BIT=-Xms512m -Xmx512m
    )
    ...
    set MEM_PERM_SIZE_64BIT=-XX:PermSize=256m
    set MEM_PERM_SIZE_32BIT=-XX:PermSize=256m

Later in the `setDomainEnv.cmd` file's clustering support section, you'd set
the UTF-8 file encoding by appending `-Dfile.encoding=UTF8` to the front of the
list of Java property values, as done in the following line of code: 

    set JAVA_PROPERTIES=-Dfile.encoding=utf8 %JAVA_PROPERTIES% %CLUSTER_PROPERTIES%

Next, you need to specify some local environment settings to support Liferay's
memory requirements, its use of the [Apache Lucene](http://en.wikipedia.org/wiki/Lucene) 
search engine library, and its use of Aspect Oriented Programming (AOP) with
[AspectJ](http://en.wikipedia.org/wiki/AspectJ).

From within domain, open the script file that starts the server that will host
the Liferay application. To deploy Liferay to a managed server (recommended)
you'll want to edit the `startManagedWebLogic.[cmd|sh]` file. If you're on
Windows, you'd add directives similar to those listed below, after the
`SETLOCAL` command:

    set "USER_MEM_ARGS=-Xmx1024m -XX:PermSize=512m"
    
    set "MW_HOME=D:\Oracle\Middleware\wlserver_12.1"
    
    set "JAVA_OPTIONS=%JAVA_OPTIONS% -da:org.apache.lucene... -da:org.aspectj..."

Make sure to set your `MW_HOME` value to your WebLogic server's location. On
Linux, you'd make similar changes replacing `%JAVA_OPTIONS%` with
`$JAVA_OPTIONS`. 

If you want to use Liferay to manage your database and mail session
(recommend), you can skip the next sections and go to the section
*Security Configuration* below. But if you want to configure your database
and/or mail session within WebLogic, start your WebLogic server and follow the
instructions in the next section. 

## Database Configuration [](id=database-configuration)

If you want WebLogic to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

1.  Select *Services &rarr; Data Sources.* Click *New &rarr; Generic Data
    Source*.

2.  Give your data source a name, such as *Liferay Data Source*. The JNDI name
    should be `jdbc/LiferayPool`.

3.  Choose the type of database and click *Next*. 

4.  Click *Next* three times. You should be on the *Connection Properties*
    screen. Enter the database name, the host name, the port, the database user
    name, and the password. WebLogic uses this information to construct the
    appropriate JDBC URL to connect to your database. Click *Next*.

5.  WebLogic prompts you to confirm the information you've specified for your
    data source. 

    Depending on the database you are using, you may need to specify additional
    parameters. If you need to access previous wizard pages to modify
    information, click *Back* to revisit those pages. 

    When you're done specifying your configuration, click *Next*. 

6.  Click *Test Configuration* to make sure WebLogic can connect to your
    database successfully. When it does, click *Finish*.

7.  You will be back to the list of data sources. Notice your new data source
    has no value in the *Target* column. Click on your data source to edit it.

8.  Click the *Targets* tab and check off the server instance(s) to which you
    wish to deploy your data source. Then click *Save*.

Your data source is set up in WebLogic Server. You'll need to configure a mail
session too. If you want to use Liferay to manage your portal's mail, then you
can skip the next section. Otherwise, you can follow the instructions in the
next section to configure a mail session to manage in WebLogic. 

## Mail Configuration [](id=mail-configuration)

If you want WebLogic to manage your mail sessions, use the following procedure.
If you want to use Liferay's built-in mail sessions, you can skip this section.

1.  Select *Mail Sessions* and create a new mail session which points to your
    mail server.

2.  Give it the name *Liferay Mail* and give it the JNDI name of
    `mail/MailSession` and click *Next*.

3.  Choose your server and then click *Finish*.

Now make sure Liferay can access this mail session.

## Domain Configuration - Continued [](id=domain-configuration-continued)

At this point, you've prepared your WebLogic server environment for installing
Liferay as an application.

If you want to use Liferay to manage your mail session (recommended), you
can configure the mail session in the Control Panel. After starting your portal
as described in the
[Deploy Liferay](/discover/deployment/-/knowledge_base/6-2/installing-liferay-on-oracle-weblogic-12c-12-1-2-and-h#deploy-liferay)
section, you can go to *Control Panel &rarr; Server
Administration &rarr; Mail* and enter the settings for your mail session.

If however you've configured WebLogic to manage your your mail session, you must
configure additional portal properties. Similarly, if you've configured WebLogic
to manage your database, you must configure additional portal properties. To
configure Liferay to use a WebLogic data source or WebLogic mail session, create
a `portal-ext.properties` file in your Liferay Home folder.

If you're using WebLogic to manage your mail session, add the following
configuration to your `portal-ext.properties` file to reference that mail
session:

    mail.session.jndi.name=mail/MailSession

If you're using WebLogic to manage your data source, add the following property
setting to your `portal-ext.properties` file:

    jdbc.default.jndi.name=jdbc/LiferayPool

Before you deploy Liferay, regardless of whether you're using Liferay or
WebLogic to manage your database or mail session, you should enable and
configure Java Security. This will enable your portal to use Liferay's plugin
security manager with the Liferay apps that you download and install from
Liferay Marketplace. 

## Security Configuration [](id=security-configuration)

When you are ready to start using apps from Marketplace, you'll want to protect
your portal and your WebLogic server from security threats. To do so, you must
enable Java Security on your WebLogic server and specify a security policy to
grant Liferay Portal access to your server. 

First, you'll grant Liferay access to your server. This configuration opens all
permissions--you can fine-tune your policy's permissions later. Create a policy
file named `weblogic.policy` in your `$WL_HOME/server/lib` folder
and add the following contents:

    grant {
        permission java.security.AllPermission;
    };

To enable security on your WebLogic server and direct the server to your policy
file, open the `setDomainEnv.[cmd|sh]` file in your domain's folder. Then set
the `-Djava.security.manager` Java option and set the property
`-Djava.security.policy==` to the location of your `weblogic.policy` file. You
can specify both settings on the same line like this: 

    -Djava.security.manager -Djava.security.policy==$WL_HOME/server/lib

The double equals sign tells the app server to use this policy file on top of
any existing security policies. 

For extensive information on Java SE Security Architecture see its specification
documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see section [*Understanding Plugin Security Management*](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in the Developer's Guide to learn how to configure Liferay plugin access to
resources. 

Next you'll learn how to configure your WebLogic application server for JSF
applications. 

## JSF Configuration [](id=jsf-configuration)

If you'd like to deploy JSF applications on your WebLogic application server,
you'll need to complete a few extra steps in your configuration process. If you
do not plan on using JSF applications in your application server, you can skip
this section. This section assumes you're using JSF 2.1 portlets. 

Complete the first section to ensure JSF applications deploy successfully to
your WebLogic application server. 

### Configuration for Deploying JSF Portlets [](id=configuration-for-deploying-jsf-portlets)

1. To avoid a `ViewExpiredException` with Ajax, disable the Liferay Portal
`ETagFilter` by adding the following property in the `portal-ext.properties`
file: 

        com.liferay.portal.servlet.filters.etag.ETagFilter=false

    For more information on this exception, refer to
    [FACES-1591](https://issues.liferay.com/browse/FACES-1591).

2. You'll need to adjust your memory settings for your environment variables.
   For your memory settings to be permanently set, they need to be hard-coded in
   the `startWebLogic.sh` script. Just above the definition of your home domain,
   add the following lines:

        export MW_HOME=$HOME/Oracle/Middleware
        export USER_MEM_ARGS="-Xms512m -Xmx1024m -XX:CompileThreshold=8000 -XX:PermSize=128m -XX:MaxPermSize=256m"

    Note that if you have many portlet WAR modules, you may need to increase
    memory. For example, the following lines reserves double the amount of
    memory: 

        export MW_HOME=$HOME/Oracle/Middleware
        export USER_MEM_ARGS="-Xms1024m -Xmx2048m -XX:CompileThreshold=8000 -XX:PermSize=256m -XX:MaxPermSize=512m"

3. If you're running the JSR 329 Portlet Bridge TCK, you'll need to include the
   `trinidad-api.jar` dependency in the global classpath (in the `lib` folder). 

4. In order for JSF 2.1 portlets to deploy correctly in WebLogic, the
   `WEB-INF/weblogic.xml` descriptor must be configured to fine-tune how class
   loading takes place. For a working example, please refer to the
   [weblogic.xml](https://github.com/liferay/liferay-faces/blob/3.2.x/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/weblogic.xml)
   descriptor from a demo JSF portlet. 

5. Due to a deficiency in the XML parser that ships with WebLogic, it is
   necessary to include a custom [Apache Xerces](http://xerces.apache.org/)
   parser as a dependency. In order to include it in the proper position in
   the WebLogic classpath, the Xerces JARs are included in the Mojarra Shared
   Library. Therefore, it is necessary to add Xerces as a dependency in the
   portlet's WEB-INF/lib folder. For example: 

        <dependencies>
            <dependency>
                <groupId>xerces</groupId>
                <artifactId>xercesImpl</artifactId>
                <version>2.11.0</version>
            </dependency>
        </dependencies>

6. If using ICEfaces, PrimeFaces, or RichFaces, all JARs related to these
   projects must exist in `WEB-INF/lib`. 

Next, you'll need to upgrade Mojarra for your WebLogic application server. 
 
### Upgrading Mojarra [](id=upgrading-mojarra)

Liferay Faces requires JSF 2.1.21. However, the version of Mojarra that comes
with WebLogic 12c is version 2.1.20. Therefore, it is necessary to upgrade
Mojarra in WebLogic by creating a new Shared Library WAR with the updated
dependencies. 

1. Make sure your `MW_HOME` environment variable is defined (completed in step 2
   of the previous section).
 
2. Build the patched version of Mojarra:

        cd liferay-faces/support
        mvn -P weblogic clean install

3. Copy the patched version of Mojarra over the version that was shipped
   out-of-the-box: 

        cp $HOME/.m2/repository/com/oracle/weblogic/glassfish.jsf_1.0.0.0_2-1-21/12.1.2-0-0/glassfish.jsf_1.0.0.0_2-1-21-12.1.2-0-0.jar $MW_HOME/wlserver/modules/glassfish.jsf_1.0.0.0_2-1-20.jar

    Since the Mojarra API and Implementation JARs are present in the global
    classpath, `jsf-api.jar` and `jsf-impl.jar` must not be included in
    `WEB-INF/lib`. 

Now it's the moment you've been waiting for: Liferay deployment! 

## Deploy Liferay [](id=deploy-liferay)

Although you can deploy Liferay on a WebLogic Admin Server, we recommend
deploying Liferay to a managed server. That way, if Liferay needs to be rolled
back to a particular state, the Admin Server can do that. In a managed server,
you'll also be able to startup / shutdown Liferay quicker and you'll more easily
be able to extend Liferay into a cluster configuration. 

Regardlesss of which server you target to host Liferay in your WebLogic server
domain, here are steps for deploying Liferay: 

1.  Start your WebLogic server if it's not already started.

2.  Go to *Deployments* and select the Liferay `.war` file from the file system,
    or click the *Upload Your File(s)* link to upload it and then click *Next*.

3.  Select *Install this deployment as an application* and click *Next*.

4.  Target the server (e.g., a managed server) on which to deploy Liferay. 

5.  If the default name is appropriate for your installation, keep it.
    Otherwise, give it a name of your choosing and click *Next*.

6.  Click *Finish*. After the deployment finishes, click *Save*.
    Liferay precompiles all the JSPs, and Liferay launches.

Congratulations! You are now running Liferay on Oracle WebLogic 12c.
