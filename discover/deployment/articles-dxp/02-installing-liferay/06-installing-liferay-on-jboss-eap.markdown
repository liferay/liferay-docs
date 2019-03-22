# Installing @product@ on JBoss EAP 6.4 [](id=installing-liferay-on-jboss-eap-6-4)

Installing @product@ manually requires these basic steps:

- Installing @product@ dependencies to your application server
- Configuring your application server for @product@
- Installing the @product@ WAR file to your application server

In this article, you'll step through these basic steps and install @product@ on
your existing JBoss EAP 6.4 application server. Before proceeding, you should
also download the latest dependencies ZIP file and OSGi JARs ZIP file from
[the available releases page](https://web.liferay.com/downloads/liferay-portal/available-releases)
as well as the @product@ WAR file.

**Liferay Home** is one folder above JBoss's install location.
[*Liferay Home*](/discover/deployment/-/knowledge_base/7-0/installing-product#liferay-home)
refers to the folder containing your JBoss server folder. When @product@ is
installed on JBoss, the Liferay Home folder contains the JBoss server folder as
well as `data`, `deploy`, `logs`, and `osgi` folders. You'll also see the term
`$JBOSS_HOME` used in this guide. `$JBOSS_HOME` refers to your JBoss server
folder. This folder is usually named `jboss-eap-[version]`.

## Installing @product@ Dependencies [](id=installing-liferay-dependencies)

@product@ depends on many JARs that are included in the @product@ JBoss bundle. Some
JARs in the bundle are not strictly required but can still be useful. You can
download the required JARs from third-parties, as described below.

1. Create the folder `$JBOSS_HOME/modules/com/liferay/portal/main`. Unzip the
   the @product@ Dependencies zip file and copy the `.jar` files to this
   folder. 

2. Download your database driver `.jar` file and copy it into the
   same folder. For example, for MySQL, 
   [download the MySQL Connector/J driver](http://dev.mysql.com/downloads/connector/j/).
   and put its `.jar` file into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3. Download the
   [com.liferay.registry.api.jar](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.registry.api)
   JAR and insert it into the same folder. 

4. Create the file `module.xml` in the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the
   following contents:

        <?xml version="1.0"?>

        <module name="com.liferay.portal" xmlns="urn:jboss:module:1.0">
            <resources>
                <resource-root path="com.liferay.registry.api.jar" />
                <resource-root path="mysql-connector-java-[version]-bin.jar" />
                <resource-root path="portal-kernel.jar" />
                <resource-root path="portlet.jar" />
            </resources>
            <dependencies>
                <module name="javax.api" />
                <module name="javax.mail.api" />
                <module name="javax.servlet.api" />
                <module name="javax.servlet.jsp.api" />
                <module name="javax.transaction.api" />
            </dependencies>
        </module>

    Make sure to replace `[version]` with the correct version of the MySQL JDBC
    driver. If you are using a different database, replace the MySQL `.jar` with 
    the driver JAR for your database (e.g., HSQL, PostgreSQL, etc.).

5. Create an `osgi` folder in your Liferay Home folder. Then extract the OSGi
   ZIP file that you downloaded into the `osgi` folder.

    @product@ requires an OSGi runtime, and the `osgi` folder provides this with
    many required JAR files and configuration files.


Checkpoint: 
1. Inside the `$JBOSS_HOME/modules/com/liferay/portal/main` folder,
   verify that the following are present: 

   a. `com.liferay.registry.api.jar` 
   b. `portal-kernel.jar` 
   c. `portlet.jar` 
   d. (database of your choice `jar`) (e.g. MySQL, SQL Server...)

2. Inside the `osgi` folder, verify the following folders are present:

   a. `configs` 
   b. `core` 
   c. `marketplace` 
   d. `target-platform` 
   e. `test`

## Running @product@ on JBoss EAP 6.4 in Standalone Mode vs. Domain Mode [](id=running-liferay-on-jboss-eap-6-4-in-standalone-mode-vs-domain-mode)

JBoss EAP 6.4 can be launched in either *standalone* mode or *domain* mode.
Domain mode allows multiple application server instances to be managed from a
single control point. A collection of such application servers is known as a
*domain*. For more information on standalone mode vs. domain mode, please refer
to the section on this topic in the
[JBoss EAP 6.4 Administration and Configuration Guide](https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/6.4/html/Administration_and_Configuration_Guide/About_JBoss_Enterprise_Application_Platform_6_Operating_Modes.html).
@product@ fully supports JBoss EAP 6.4 when it runs in standalone mode but not
when it runs in domain mode.

You can run @product@ on JBoss EAP 6.4 in domain mode, but this method is not
fully supported. In particular, @product@'s hot-deploy does not work, since JBoss
EAP 6.4 cannot deploy non-exploded `.war` files in domain mode. Instead, `.war`
files are in the `domain/data/content` directory. Deployments are only possible
using the command line interface. This prevents many @product@ plugins from
working as intended. For example, JSP hooks don't work on JBoss EAP 6.4 running
in domain mode, since @product@'s JSP override mechanism relies on the application
server reloading customized JSP files from the exploded plugin `.war` file
location. Other plugins, such as service or action hooks, should still work
properly since they don't require JBoss to access anything (such as JSP files)
from an exploded `.war` file on the file system.

+$$$

**Note:** This does not prevent @product@ from running in a clustered environment
on multiple JBoss servers. You can set up a cluster of @product@ instances
running on JBoss EAP 6.4 servers running in standalone mode. Please refer to the
chapter of this guide on
[Configuring @product@ for High Availability](/discover/deployment/-/knowledge_base/6-2/configuring-liferay-for-high-availability)
for information on setting up a @product@ cluster.

$$$

<!-- Replace link in note above, when available. -Cody -->

## Configuring JBoss [](id=configuring-jboss)

Now you'll make some adjustments in your configuration to support using @product@.

You can specify the JBoss server instance's configuration in the XML file
`$JBOSS_HOME/standalone/configuration/standalone.xml`. You must also make some
modifications to your configuration and startup scripts found in the
`$JBOSS_HOME/bin/` folder. Lastly, you'll need to make some modifications in
your `$JBOSS_HOME/modules/`. You'll begin with making changes to
`standalone.xml`.

Make the following modifications to `standalone.xml`:

1. Locate the closing `</extensions>` tag. Directly beneath that tag, insert the
   following system properties:

        <system-properties>
            <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
            <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
        </system-properties>

2. Add a timeout for the deployment scanner by setting
`deployment-timeout="360"` as seen in the excerpt below.

        <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
            <deployment-scanner deployment-timeout="360" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
        </subsystem>

3. Add the following JAAS security domain to the security subsystem
   `<security-domains>` defined in element `<subsystem
   xmlns="urn:jboss:domain:security:1.2">`.

        <security-domain name="PortalRealm">
            <authentication>
                <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
            </authentication>
        </security-domain>

4. Disable the default JBoss Welcome page by setting the `enable-welcome-root`
   attribute to `false`, as seen in the snippet below.

        <subsystem xmlns="urn:jboss:domain:web:2.2" default-virtual-server="default-host" native="false">
            <connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/>
            <virtual-server name="default-host" enable-welcome-root="false">
            ...

5. In the same `<subsystem ... />` element that was outlined in the previous
   step, add the following snippet above the `<connector ... />` element:

        <configuration>
            <jsp-configuration source-vm="1.8" target-vm="1.8" development="true" />
        </configuration>

Checkpoint:

1. The `standalone.xml` has been modified with the following values: 

    a. `deployment-timeout="360"` has been set. The value is in seconds.    
     b. The JAAS security domain has been added.    
     c. The property `enable-welcome-root="false"` has been set.    
     d. The property `jsp-configuration source-vm="1.8" target-vm="1.8" development="true"` has been set in the
    `<configuration>` element. This is required because @product@ runs on Java
    JDK 1.8 or else there will be a JasperException and the instance will fail
    to start.

Now it's time for some changes to your configuration and startup scripts.
 
Make the following modifications to your standalone domain's configuration
script file `standalone.conf` (`standalone.conf.bat` on Windows) found in your
`$JBOSS_HOME/bin/` folder.

These modifications change the following options: 
- Set the file encoding
- Set the user time-zone
- Set the preferred protocol stack
- Increase the default amount of memory available.

Make the following edits as applicable to your operating system:

On Windows, comment out the initial `JAVA_OPTS` assignment as demonstrated in
the following line:

    rem set "JAVA_OPTS=-Xms1G -Xmx1G -XX:MaxPermSize=256M"

Then add the following `JAVA_OPTS` assignment one line above the
`:JAVA_OPTS_SET` line found at end of the file:

    set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsecmgr -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxMetaspaceSize=384m"

On Unix, merge the following values into your settings for `JAVA_OPTS`, 
replacing any matching attributes with the ones found in the assignment
below:

    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dsecmgr -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxMetaspaceSize=384m"

+$$$

**Important:** For @product@ to work properly, the application server JVM must 
use the `GMT` time zone and `UTF-8` file encoding. 

$$$

Make sure you replace the `$JBOSS_HOME` references with the appropriate
directory. You'll notice some Java security options. You'll finish configuring
the Java security options in the *Security Configuration* section. 

+$$$

**Note:** If you plan on using the IBM JDK with your JBoss server, you'll need
to complete some additional steps. First, navigate to the
`$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` file and insert the
following dependency within the `<dependencies>` element:

    <module name="ibm.jdk" />

Then navigate to the
`$JBOSS_HOME/modules/system/layers/base/sun/jdk/main/module.xml` file and
insert the following path names inside the `<paths>...</paths>` element:

    <path name="com/sun/crypto" />
    <path name="com/sun/crypto/provider" />
    <path name="com/sun/image/codec/jpeg" />
    <path name="com/sun/org/apache/xml/internal/resolver" />
    <path name="com/sun/org/apache/xml/internal/resolver/tools" />

The added paths resolve issues with portal deployment exceptions and image
uploading problems on a @product@ instance running on JBoss EAP 6.4. 

$$$

Checkpoint:

1.  The `standalone.conf.bat` file has been updated with the following changes: 

    a. UTF-8 file encoding 
    b. The user time-zone 
    c. The preferred protocol stack 
    d. Increased the default amount of memory available.
 
2.  If using the IBM JDK with the JBoss server, `<module name="ibm.jdk" />` has
    been added to the `$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` file.

3.  The additional IBM JDK paths have been set in the
    `$JBOSS_HOME/modules/system/layers/base/sun/jdk/main/module.xml` file.

The prescribed script modifications are now complete for your @product@
installation on JBoss. Next you'll configure mail and the database. 

## Database Configuration [](id=database-configuration)

If you want JBoss to manage your data source, follow the instructions in this
section. If you want to use the built-in @product@ data source, you can skip this
section.

Modify `standalone.xml` and add your data source and driver in the
`<datasources>` element of your data sources subsystem.

1. First, add your data source inside the `<datasources>` element.

        <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
            <connection-url>jdbc:mysql://localhost/lportal</connection-url>
            <driver>mysql</driver>
            <security>
                <user-name>root</user-name>
                <password>root</password>
            </security>
        </datasource>

    Be sure to replace the database name (i.e. `lportal`), user name, and
    password with the appropriate values. 

    +$$$

    **Note:** If you'd like to change your datasource `jndi-name` to something
    different, you'll need to also edit the `datasource` element in the
    `<default-bindings>` tag.

    $$$

2. Add your driver to the `<drivers>` element also found within the
   `<datasources>` element.

        <drivers>
            <driver name="mysql" module="com.liferay.portal"/>
        </drivers>

Your final data sources subsystem should look like this:

        <subsystem xmlns="urn:jboss:domain:datasources:1.2">
            <datasources>
                <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
                    <connection-url>jdbc:mysql://localhost/lportal</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>root</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="mysql" module="com.liferay.portal"/>
                </drivers>
            </datasources>
        </subsystem>

Now that you've configured your data source, the mail session is next. 

## Mail Configuration [](id=mail-configuration)

If you want JBoss to manage your mail session, use the following instructions.
If you want to use the built-in @product@ mail session, you can skip this section.

Specify your mail subsystem in `standalone.xml` as in the following example:

    <subsystem xmlns="urn:jboss:domain:mail:1.2">
        <mail-session jndi-name="java:jboss/mail/MailSession" >
            <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp">
                <login username="USERNAME" password="PASSWORD"/>
            </smtp-server>
       </mail-session>
    </subsystem>
    ...
    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <outbound-socket-binding name="mail-smtp">
            <remote-destination host="smtp.gmail.com" port="465"/>
        </outbound-socket-binding>
    </socket-binding-group>
 
You've got mail! Next, you'll make sure @product@ can connect using your new mail
session and database.

## Configuring data sources and mail sessions [](id=configuring-data-sources-and-mail-sessions)

Now that your data source and mail session are set up, you need to ensure
@product-ver@ can access them.

1. First, navigate to the Liferay Home folder, which is one folder above
   JBoss's install location (i.e. `$JBOSS_HOME/..`).

2. If you're using *JBoss* to manage your data source, add the following
   configuration to your `portal-ext.properties` file in your *Liferay Home* to
   refer to your data source:

        jdbc.default.jndi.name=java:jboss/datasources/ExampleDS

   If you're using *@product-ver@* to manage your data source, follow the
   instructions for using the setup wizard.

3. If you're using *@product-ver@* to manage your mail session, this
   configuration is done in @product-ver@. That is, after starting your
   portal as described in the *Deploy @product@* section, go to *Control Panel*
   &rarr; *Server Administration* &rarr; *Mail* and enter the settings for your
   mail session.

    If you're using JBoss to manage your mail session, add the following
    configuration to your `portal-ext.properties` file to reference that mail
    session:

        mail.session.jndi.name=java:jboss/mail/MailSession

Before you deploy @product-ver@ on your JBoss app server, you should enable
and configure Java security so you can use @product@'s plugin security manager
with your downloaded @product@ applications.

## Security Configuration [](id=security-configuration)

When you're ready to begin using other people's apps from Marketplace, you'll
want to protect your @product@ instance and your JBoss server from security
threats. To do so, you can enable Java Security on your JBoss server and
specify a security policy to grant your @product@ instance access to your server.

Remember, you set the `-Dsecmgr` and `-Djava.security.policy` Java options in
the `standalone.conf.bat` file earlier in the *Configuring JBoss* section. The
`-Dsecmgr` Java option enables security on JBoss. Likewise, the
`-Djava.security.policy` Java option lists the permissions for your server's
Java security policy. If you have not set these options, you'll need to do so
before using Java security.

This configuration opens up all permissions. You can tune the permissions in
your policy later. Create the `$JBOSS_HOME/bin/server.policy` file and add the
following contents:

    grant {
        permission java.security.AllPermission;
    };

For extensive information on Java SE Security Architecture, see its
specification documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see the
[Plugin Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)
tutorial to learn how to configure @product@ plugin access to resources.

<!-- JSF configuration sections go here, when they've been tested for @product-ver@
+ JBoss bundles. -Cody -->

## Deploy @product@ [](id=deploy-liferay)

1. If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists
   in your JBoss installation, delete all of its subfolders and files.
   Otherwise, create a new folder
   `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2. Unzip the @product@ `.war` file into the `ROOT.war` folder.

3. To trigger deployment of `ROOT.war`, create an empty file named
   `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder.
   On startup, JBoss detects the presence of this file and deploys it as a web
   application.

4. Start the JBoss application server by navigating to `$JBOSS_HOME/bin`
   and running `standalone.bat` or `standalone.sh`.

The JBoss application server starts and deploys @product@.

+$$$

**Warning:** The JBoss application server system property 
`jboss.as.management.blocking.timeout` specifies an application container 
stability timeout (the default is `300` seconds). If container stability times
out during startup, all applications are undeployed and the container shuts
down. The error message looks like this:

    12:21:13,956 ERROR [org.jboss.as.controller.management-operation] (Controller Boot Thread) JBAS013412: Timeout after [300] seconds waiting for service container stability. Operation will roll back. Step that first updated the service container was 'add' at address '[("interface" => "management")]' 

JBoss CLI, Java options, and `.conf` files let you modify the timeout
(e.g., increase the timeout to give the container more time to stabilize).
Here's a `900` second timeout set in a `.conf` file property. 

    ...
    </extensions>
    <system-properties>
          <property name="jboss.as.management.blocking.timeout" value="900"/> 
    </system-properties>
    <management>
    ... 

[JBoss documentation](https://access.redhat.com/solutions/1190323)
has more details. 

$$$

You're now an expert when it comes to deploying @product@ on JBoss!
