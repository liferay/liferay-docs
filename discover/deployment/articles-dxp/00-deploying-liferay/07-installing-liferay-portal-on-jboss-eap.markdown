# Installing @product@ on JBoss EAP

Installing @product@ manually on JBoss EAP 7.1 requires these basic steps:

- [Installing @product@ dependencies to your application server](#installing-dependencies)
- [Configuring your application server for @product@](#configuring-jboss)
- [Installing the @product@ WAR file to your application server](#deploying-product)

Before proceeding, you should download the following files from the
[Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise):

- @product@ WAR file
- Dependencies ZIP file
- OSGi JARs ZIP file

[*Liferay Home*](/discover/deployment/-/knowledge_base/7-0/installing-product#liferay-home)
is the folder containing your JBoss server folder. After installing and
deploying @product@, the Liferay Home folder contains the JBoss server folder as
well as `data`, `deploy`, `logs`, and `osgi` folders. `$JBOSS_HOME` refers to
your JBoss server folder. This folder is usually named `jboss-eap-[version]`.

## Installing Dependencies [](id=installing-dependencies)

@product@ depends on several Liferay-specific and third-party JARs. You can
download and install the required JARs, as described below.

1.  Create the folder `$JBOSS_HOME/modules/com/liferay/portal/main` if it
    doesn't exist and extract the JARs from the dependencies ZIP to it. Here are
    the JARs:

        - `com.liferay.registry.api.jar`
        - `hsql.jar`
        - `portal-kernel.jar`
        - `portlet.jar`

2.  Download your database driver `.jar` file and copy it into the
    same folder. For example, for MySQL, 
    [download the MySQL Connector/J driver](http://dev.mysql.com/downloads/connector/j/).
    and put its `.jar` file into the
    `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3.  Download the remaining required JARs and insert them into the same folder.

    - `com.liferay.petra.concurrent` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.concurrent/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.concurrent/)
    - `com.liferay.petra.executor.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.executor/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.executor/)
    - `com.liferay.petra.function.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.function/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.function/)
    - `com.liferay.petra.io.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.io/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.io/)
    - `com.liferay.petra.lang.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.lang/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.lang/)
    - `com.liferay.petra.memory.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.memory/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.memory/)
    - `com.liferay.petra.nio.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.nio/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.nio/)
    - `com.liferay.petra.process.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.process/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.process/)
    - `com.liferay.petra.reflect.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.reflect/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.reflect/)
    - `com.liferay.petra.string.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.string/](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.petra.string/)

4.  Create the file `module.xml` in the
    `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the
    following contents:

        <?xml version="1.0"?>

        <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
            <resources>
                <resource-root path="com.liferay.petra.concurrent.jar" />
                <resource-root path="com.liferay.petra.executor.jar" />
                <resource-root path="com.liferay.petra.function.jar" />
                <resource-root path="com.liferay.petra.io.jar" />
                <resource-root path="com.liferay.petra.lang.jar" />
                <resource-root path="com.liferay.petra.memory.jar" />
                <resource-root path="com.liferay.petra.nio.jar" />
                <resource-root path="com.liferay.petra.process.jar" />
                <resource-root path="com.liferay.petra.reflect.jar" />
                <resource-root path="com.liferay.petra.string.jar" />
                <resource-root path="com.liferay.registry.api.jar" />
                <resource-root path="hsql.jar" />
                <resource-root path="mysql.jar" />
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

    If you are using a different database, replace the MySQL `.jar` with 
    the driver JAR for your database (e.g., HSQL, PostgreSQL, etc.).

5.  Create an `osgi` folder in your Liferay Home folder. Then extract the OSGi
    ZIP file that you downloaded into the `osgi` folder.

    @product@ requires an OSGi runtime, and the `osgi` folder provides this with
    many required JAR files and configuration files.

**Checkpoint:**

1.  Verify the `$JBOSS_HOME/modules/com/liferay/portal/main` folder has the
    following files: 

    - `com.liferay.petra.concurrent`
    - `com.liferay.petra.executor.jar`
    - `com.liferay.petra.function.jar`
    - `com.liferay.petra.io.jar`
    - `com.liferay.petra.lang.jar`
    - `com.liferay.petra.memory.jar`
    - `com.liferay.petra.nio.jar`
    - `com.liferay.petra.process.jar`
    - `com.liferay.petra.reflect.jar`
    - `com.liferay.petra.string.jar`
    - `com.liferay.registry.api.jar`
    - `portal-kernel.jar`
    - `portlet.jar`
    - a database JAR such as the MySQL Connector.

2.  The `module.xml` has listed all JARs in the `<resource-root-path>` elements.

3.  The `osgi` folder has the following subfolders:

    - `configs`
    - `core`
    - `marketplace`
    - `modules`
    - `portal`
    - `static`
    - `test`
    - `war`

Great! You have your `.jar` files ready.

## Running @product@ on JBoss EAP in Standalone Mode vs. Domain Mode

JBoss EAP can be launched in either *standalone* mode or *domain* mode. Domain
mode allows multiple application server instances to be managed from a single
control point. A collection of such application servers is known as a *domain*.
For more information on standalone mode vs. domain mode, please refer to the
section on this topic in the
[JBoss EAP Product Documentation](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.1/html/introduction_to_jboss_eap/overview_of_jboss_eap#operating_modes).
@product@ fully supports JBoss EAP when it runs in standalone mode but not when
it runs in domain mode.

You can run @product@ on JBoss EAP in domain mode, but this method is not fully
supported. In particular, @product@'s hot-deploy does not work with a managed
deployment, since JBoss manages the content of a managed deployment by copying
files (exploded or non-exploded). This prevents many @product@ plugins from
working as intended. For example, JSP hooks don't work on JBoss EAP running in
managed domain mode, since @product@'s JSP override mechanism relies on the
application server. Other plugins, such as service or action hooks, should still
work properly.

Using the command line interface is recommended for domain mode deployments.

+$$$

**Note:** This does not prevent @product@ from running in a clustered
environment on multiple JBoss servers. You can set up a cluster of @product@
instances running on JBoss EAP servers running in standalone mode. Please refer
to the
[@product@ Clustering](/discover/deployment/-/knowledge_base/7-1/liferay-clustering)
section for information on setting up a @product@ cluster.

$$$

## Configuring JBoss [](id=configuring-jboss)

Configuring JBoss to run @product@ includes these things:

- Setting environment variables
- Setting properties and descriptors
- Removing unnecessary configurations
- etc.

Optionally, you can configure JBoss to manage these things for @product@:

- [Data source](#database-configuration)
- [Mail session](#mail-configuration)

Start with configuring JBoss to run @product@.

Make the following modifications to
`$JBOSS_HOME/standalone/configuration/standalone.xml`:

1.  Locate the closing `</extensions>` tag. Directly beneath that tag, insert
    the following system properties:

        <system-properties>
            <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
            <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
        </system-properties>

2.  Add the following `<filter-spec>` tag within the `<console-handler>` tag,
    directly below the `<level name="INFO"/>` tag:
        
        <filter-spec value="not(any(match(&quot;WFLYSRV0059&quot;),match(&quot;WFLYEE0007&quot;)))" />

3.  Add a timeout for the deployment scanner by setting
    `deployment-timeout="360"` as seen in the excerpt below.

        <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
            <deployment-scanner deployment-timeout="360" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
        </subsystem>

4.  Add the following JAAS security domain to the security subsystem
    `<security-domains>` defined in element `<subsystem
    xmlns="urn:jboss:domain:security:2.0">`.

        <security-domain name="PortalRealm">
            <authentication>
                <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
            </authentication>
        </security-domain>

5.  Remove the two code snippets providing welcome content:

        <location name="/" handler="welcome-content"/>

    and

        <handlers>
            <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
        </handlers>

6.  Find the `<jsp-config/>` tag and set the `development`, `source-vm`, and
    `target-vm` attributes in the tag. Once finished, the tag should look like
    this:

        <jsp-config development="true" source-vm="1.8" target-vm="1.8"/>

**Checkpoint:**

Before continuing, verify the following properties have been set in the
`standalone.xml` file:

1.  The new `<system-property>` is created.

2.  The new `<filter-spec>` is added.

3.  The `<deployment-timeout>` is set to `360`.

4.  The new `<security-domain>` is created.

5.  Welcome content is removed.

6.  The `<jsp-config>` tag contains its new attributes.

Now it's time for some changes to your configuration and startup scripts.
 
You must make a few modifications to your standalone domain's configuration
script file `standalone.conf` (`standalone.conf.bat` on Windows) found in your
`$JBOSS_HOME/bin/` folder.

These modifications change the following options: 

- Set the file encoding
- Set the user time-zone
- Set the preferred protocol stack
- Increase the default amount of memory available.

Make the following edits as applicable to your operating system:

**Windows**

1.  Comment out the initial `JAVA_OPTS` assignment as demonstrated in the
    following line:

        rem set "JAVA_OPTS=-Xms1G -Xmx1G -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m"

2.  Add the following `JAVA_OPTS` assignment one line above the `:JAVA_OPTS_SET`
    line found at end of the file:

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xmx1024m -XX:MaxMetaspaceSize=384m"

**Unix**

1.  Below the `if [ "x$JAVA_OPTS" = "x" ];` statement, replace this `JAVA_OPTS`
    statement:

        JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m -Djava.net.preferIPv4Stack=true

    with this:

        JAVA_OPTS="-Djava.net.preferIPv4Stack=true"

2.  Add the following statement to the bottom of the file:

        JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xmx1024m -XX:MaxMetaspaceSize=512m"

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
uploading problems on a @product@ instance running on JBoss EAP 7.1. 

$$$

**Checkpoint:**

At this point, you've finished configuring the application server's JVM
settings. 

1.  The file encoding, user time-zone, preferred protocol stack have been set in
    the `JAVA_OPTS` in the `standalone.conf.bat` file.

2.  The default amount of memory available has been increased.

The prescribed script modifications are now complete for your @product@
installation on JBoss. Next you'll configure mail and the database. 

## Database Configuration [](id=database-configuration)

The easiest way to handle your database configuration is to let @product@ manage
your data source. @product@'s
[Basic Configuration](/discover/deployment/-/knowledge_base/7-1/installing-product#using-liferays-setup-wizard)
page lets you configure @product@'s built-in data source. If you want to use the
built-in data source, skip this section.

If you want JBoss to manage your data source, follow these steps:

1.  Add your data source inside the `<datasources>` element.

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

2.  Add your driver to the `standalone.xml` file's `<drivers>` element also
    found within the `<datasources>` element.

        <drivers>
            <driver name="mysql" module="com.liferay.portal">
                <driver-class>com.mysql.jdbc.Driver</driver-class>
            </driver>
        </drivers>

    Your final data sources subsystem should look like this:

        <subsystem xmlns="urn:jboss:domain:datasources:5.0">
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

3.  In a `portal-ext.properties` file in your Liferay Home, specify your data
    source:

        jdbc.default.jndi.name=java:jboss/datasources/ExampleDS

Now that you've configured your data source, the mail session is next. 

## Mail Configuration [](id=mail-configuration)

As with database configuration, the easiest way to configure mail is to let
@product@ handle your mail session. If you want to use @product@'s built-in mail
session, skip this section and
[configure the mail session](/discover/deployment/-/knowledge_base/7-1/installing-product#configuring-mail)
in the Control Panel.

If you want to manage your mail session with JBoss, follow these steps:

1.  Specify your mail subsystem in the
    `$WILDFLY_HOME/standalone/configuration/standalone.xml` file like this:

        <subsystem xmlns="urn:jboss:domain:mail:3.0">
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

2.  In your `portal-ext.properties` file in Liferay Home, reference your mail
    session:

        mail.session.jndi.name=java:jboss/mail/MailSession
 
You've got mail! Next, you'll deploy @product@ to your JBoss app server.

## Deploying @product@ [](id=deploying-product)

1.  If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists
    in your JBoss installation, delete all of its subfolders and files.
    Otherwise, create a new folder called
    `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2.  Unzip the @product@ `.war` file into the `ROOT.war` folder.

3.  To trigger deployment of `ROOT.war`, create an empty file named
    `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder.
    On startup, JBoss detects the presence of this file and deploys it as a web
    application.

4.  Start the JBoss application server by navigating to `$JBOSS_HOME/bin`
    and running `standalone.bat` or `standalone.sh`.

You're now an expert when it comes to deploying @product@ on JBoss!