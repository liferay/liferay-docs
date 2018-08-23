# Installing @product@ on Wildfly [](id=installing-product-on-wildfly)

@product-ver@ bundled with Wildfly 11 is available on the
[Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/7-1)
(DXP) or [Liferay Downloads](https://www.liferay.com/downloads) (Portal CE).
@product-ver@ supports deployment to Wildfly 10 and Wildfly 11. Even if you want
to manually install @product@ on an existing Wildfly application server, it can
be helpful to download a @product@ Wildfly bundle to make gathering the 
dependencies easier. Before proceeding, also download these files for
[DXP](https://web.liferay.com/group/customer/dxp/downloads/7-1)
and [Portal CE](https://www.liferay.com/downloads):

- @product@ WAR file
- Dependencies ZIP file
- OSGi JARs ZIP file

Installing @product@ manually takes three steps:

- [Installing dependencies to your application server](#installing-dependencies)
- [Configuring your application server for @product@](#configuring-wildfly)
- [Deploying the @product@ WAR file to your application server](#deploying-product)

[*Liferay Home*](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)
is the folder containing your Wildfly server folder. After installing and
deploying @product@, the Liferay Home folder contains the Wildfly server folder
as well as `data`, `deploy`, `logs`, and `osgi` folders. `$WILDFLY_HOME` refers
to your Wildfly server folder. It is usually named `wildfly-[version]`.

## Installing Dependencies [](id=installing-dependencies)

@product@ depends on many JARs that are included in the @product@ Wildfly
bundle. Some of the bundle's JARs are not strictly required but can still be
useful. If you don't have a @product@ Wildfly bundle, download the required JARs
from third-parties as described below.

1.  Create the folder `$WILDFLY_HOME/modules/com/liferay/portal/main` if it
    doesn't exist and extract the dependencies ZIP JARs to it:

    - `com.liferay.petra.concurrent.jar`
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
    - `hsql.jar`
    - `portal-kernel.jar`
    - `portlet.jar`

2.  Download your database driver `.jar` file and copy it into the
    same folder. For example,
    [copy MySQL's driver](http://dev.mysql.com/downloads/connector/j/)
    into the `$WILDFLY_HOME/modules/com/liferay/portal/main` folder. The
    `mariadb.jar`, `mysql.jar`, and `postgresql.jar` driver JARs are also
    available in the Wildfly bundle.

3.  Create the file `module.xml` in the
    `$WILDFLY_HOME/modules/com/liferay/portal/main` folder and insert this 
    configuration:

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

    If you use a different database, replace the MySQL `.jar` with the driver
    JAR for your database (e.g., HSQL, PostgreSQL, etc.).

4.  Create an `osgi` folder in your Liferay Home folder. Extract the OSGi ZIP
    file that you downloaded into the `osgi` folder.

    The `osgi` folder provides the necessary modules for @product@'s OSGi
    runtime.

**Checkpoint:**

1.  At this point, you should have the following files in the 
    `$WILDFLY_HOME/modules/com/liferay/portal/main` folder:

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

2. The `module.xml` has listed all JARs in the `<resource-root-path>` elements.

3. The `osgi` folder has the following subfolders:

    - `configs`
    - `core`
    - `marketplace`
    - `modules`
    - `portal`
    - `static`
    - `test`
    - `war`

Great! You have your `.jar` files ready. 

## Running @product@ on Wildfly in Standalone Mode vs. Domain Mode [](id=running-product-on-wildfly-in-standalone-mode-vs-domain-mode)

Wildfly can be launched in either *standalone* mode or *domain* mode. Domain
mode allows multiple application server instances to be managed from a single
control point. A collection of such application servers is known as a *domain*.
For more information on standalone mode vs. domain mode, please refer to the
section on this topic in the
[Wildfly Admin Guide](https://docs.jboss.org/author/display/WFLY/Admin+Guide#AdminGuide-Operatingmodes).
@product@ fully supports Wildfly in standalone mode but not in domain mode.

You can run @product@ on Wildfly in domain mode, but this method is not fully
supported. In particular, @product@'s hot-deploy does not work with a managed
deployment, since Wildfly manages the content of a managed deployment by copying
files (exploded or non-exploded). This prevents JSP hooks and Ext plugins from
working as intended. For example, JSP hooks don't work on Wildfly running in
managed domain mode, since @product@'s JSP override mechanism relies on the
application server. Since both of these features are deprecated, however, you
may not be using them.

The command line interface is recommended for domain mode deployments.

+$$$

**Note:** This does not prevent @product@ from running in a clustered
environment on multiple Wildfly servers. You can set up a cluster of @product@
instances running on Wildfly servers running in standalone mode. Please refer to
the chapter of this guide on
[@product@ Clustering](/discover/deployment/-/knowledge_base/7-1/liferay-clustering)
for information on setting up a @product@ cluster.

$$$

## Configuring Wildfly [](id=configuring-wildfly)

Configuring Wildfly to run @product@ includes these things:

- Setting environment variables
- Setting properties and descriptors
- Removing unnecessary configurations

Optionally, you can configure Wildfly to manage @product@'s data source and mail
session. 

Start with configuring Wildfly to run @product@.

Make the following modifications to
`$WILDFLY_HOME/standalone/configuration/standalone.xml`:

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
            <deployment-scanner deployment-timeout="360" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" runtime-failure-causes-rollback="${jboss.deployment.scanner.rollback.on.failure:false}"/>
        </subsystem>

4.  Add the following JAAS security domain to the security subsystem
    `<security-domains>` defined in element `<subsystem
    xmlns="urn:jboss:domain:security:2.0">`.

        <security-domain name="PortalRealm">
            <authentication>
                <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
            </authentication>
        </security-domain>

5.  Remove the following Weld-related tags:

    - `<extension module="org.jboss.as.weld"/>`
    - `<subsystem xmlns="urn:jboss:domain:weld:4.0"/>`

6.  Remove the two code snippets providing welcome content:

        <location name="/" handler="welcome-content"/>

    and

        <handlers>
            <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
        </handlers>

7.  Find the `<jsp-config/>` tag and set the `development`, `source-vm`, and
    `target-vm` attributes in the tag. Once finished, the tag should look like
    this:

        <jsp-config development="true" source-vm="1.8" target-vm="1.8" />

**Checkpoint:**

Before continuing, verify the following properties have been set in the
`standalone.xml` file:

1.  The new `<system-property>` is added.

2.  The new `<filter-spec>` is added.

3.  The `<deployment-timeout>` is set to `360`.

4.  The new `<security-domain>` is created.

5.  Weld tags are removed.

6.  Welcome content is removed.

7.  The `<jsp-config>` tag contains its new attributes.

Now you must configure your JVM and startup scripts.

In the `$WILDFLY_HOME/bin/` folder, you must make these modifications to your
standalone domain's configuration script file `standalone.conf`
(`standalone.conf.bat` on Windows):

- Set the file encoding
- Set the user time-zone
- Set the preferred protocol stack
- Increase the default amount of memory available.

Make the following edits as applicable for your operating system:

**Windows:**

1.  Comment out the initial `JAVA_OPTS` assignment like this:

        rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m"

2.  Add the following `JAVA_OPTS` assignment one line above the `:JAVA_OPTS_SET`
    line found at end of the file:

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=200m"

**Unix:**

1.  Below the `if [ "x$JAVA_OPTS" = "x" ];` statement, replace this `JAVA_OPTS`
    statement:

        JAVA_OPTS="-Xms64m -Xmx512m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m -Djava.net.preferIPv4Stack=true"

    with this:

        JAVA_OPTS="-Djava.net.preferIPv4Stack=true"

2.  Add the following statement to the bottom of the file:

        JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true  -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=200m"

+$$$

**Note:** If you plan on using the IBM JDK with your Wildfly server, you must
complete some additional steps. First, navigate to the
`$WILDFLY_HOME/modules/com/liferay/portal/main/module.xml` file and insert the
following dependency within the `<dependencies>` element:

    <module name="ibm.jdk" />

Then navigate to the
`$WILDFLY_HOME/modules/system/layers/base/sun/jdk/main/module.xml` file and
insert the following path names inside the `<paths>...</paths>` element:

    <path name="com/sun/crypto" />
    <path name="com/sun/crypto/provider" />
    <path name="com/sun/org/apache/xml/internal/resolver" />
    <path name="com/sun/org/apache/xml/internal/resolver/tools" />

The added paths resolve issues with deployment exceptions and image uploading
problems. 

$$$

**Checkpoint:**

At this point, you've finished configuring the application server's JVM
settings. 

1.  The file encoding, user time-zone, preferred protocol stack have been set in
    the `JAVA_OPTS` in the `standalone.conf.bat` file.

2.  The default amount of memory available has been increased.

The prescribed script modifications are now complete for your @product@
installation on Wildfly. Next you'll configure your database. 

### Database Configuration [](id=database-configuration)

The easiest way to handle database configuration is to let @product@ manage your
data source. The 
[Basic Configuration](/discover/deployment/-/knowledge_base/7-1/installing-liferay#using-liferays-setup-wizard)
page lets you configure @product@'s built-in data source. If you want to use the
built-in data source, skip this section.

MySQL is used as the example below. If you're using a different database, modify
the data source and driver snippets as necessary.

If you want Wildfly to manage your data source, follow these steps:

1.  Add your data source inside
    `$WILDFLY_HOME/standalone/configuration/standalone.xml` file's
    `<datasources>` element:

        <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
            <connection-url>jdbc:mysql://localhost/lportal</connection-url>
            <driver>mysql</driver>
            <security>
                <user-name>root</user-name>
                <password>root</password>
            </security>
        </datasource>

    Be sure to replace the database name (i.e., `lportal`), user name, and
    password with the appropriate values. 

    +$$$

    **Note:** If you must change your datasource `jndi-name` to something
    different, you must also edit the `datasource` element in the
    `<default-bindings>` tag.

    $$$

2.  Add your driver to the `standalone.xml` file's `<drivers>` element also
    found within the `<datasources>` element:

        <drivers>
            <driver name="mysql" module="com.liferay.portal">
                <driver-class>com.mysql.jdbc.Driver</driver-class>
            </driver>
        </drivers>

    Your final data sources subsystem should look like this:

        <subsystem xmlns="urn:jboss:domain:datasources:1.0">
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
                    <driver name="mysql" module="com.liferay.portal">
                        <driver-class>com.mysql.jdbc.Driver</driver-class>
                    </driver>
                </drivers>
            </datasources>
        </subsystem>

3.  In a `portal-ext.properties` file in your Liferay Home, specify your data
    source:

        jdbc.default.jndi.name=java:jboss/datasources/ExampleDS

Now that you've configured your data source, the mail session is next. 

### Mail Configuration [](id=mail-configuration)

As with database configuration, the easiest way to configure mail is to let
@product@ handle your mail session. If you want to use @product@'s built-in mail
session, skip this section and
[configure the mail session](/discover/deployment/-/knowledge_base/7-1/installing-liferay#configuring-mail)
in the Control Panel.

If you want to manage your mail session with Wildfly, follow these steps:

1.  Specify your mail subsystem in the
    `$WILDFLY_HOME/standalone/configuration/standalone.xml` file like this:

        <subsystem xmlns="urn:jboss:domain:mail:3.0">
            <mail-session jndi-name="java:jboss/mail/MailSession" name="mail-smtp">
                <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp" username="USERNAME" password="PASSWORD"/>
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
 
You've got mail! Next, you'll deploy @product@ to your Wildfly app server.

## Deploying @product@ [](id=deploying-product)

Now you're ready to deploy @product@ using the @product@ WAR file.

1.  If the folder `$WILDFLY_HOME/standalone/deployments/ROOT.war` already exists
    in your Wildfly installation, delete all of its subfolders and files.
    Otherwise, create a new folder called
    `$WILDFLY_HOME/standalone/deployments/ROOT.war`.

2.  Unzip the @product@ `.war` file into the `ROOT.war` folder.

3.  To trigger deployment of `ROOT.war`, create an empty file named
    `ROOT.war.dodeploy` in your `$WILDFLY_HOME/standalone/deployments/` folder.
    On startup, Wildfly detects the presence of this file and deploys it as a
    web application.

4.  Start the Wildfly application server by navigating to `$WILDFLY_HOME/bin`
    and running `standalone.bat` or `standalone.sh`.

Congratulations; you've deployed @product@ on Wildfly!
