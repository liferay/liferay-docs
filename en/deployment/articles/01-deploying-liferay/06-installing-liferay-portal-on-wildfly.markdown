---
header-id: installing-product-on-wildfly
---

# Installing @product@ on Wildfly

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-wildfly.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Installing @product@ on Wildfly 11 takes three steps:

-   [Installing dependencies to your application server](#installing-dependencies)
-   [Configuring your application server for @product@](#configuring-wildfly)
-   [Deploying the @product@ WAR file to your application server](#deploying-product)

| **Important:** Before installing @product@, familiarize yourself with
| [preparing for install](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install).

Now, [download the @product@ WAR and Dependency
JARs](/docs/7-2/deploy/-/knowledge_base/d/obtaining-product#downloading-the-liferay-war-and-dependency-jars):

-   @product@ WAR file
-   Dependencies ZIP file
-   OSGi Dependencies ZIP file

Note that [*Liferay Home*](/docs/7-2/deploy/-/knowledge_base/d/liferay-home) is
the folder containing your Wildfly server folder. After installing and deploying
@product@, the Liferay Home folder contains the Wildfly server folder as well as
`data`, `deploy`, `logs`, and `osgi` folders. `$WILDFLY_HOME` refers to your
Wildfly server folder. It is usually named `wildfly-[version]`.

## Installing Dependencies

@product@ depends on a driver for your database and the JARs in the Dependencies
ZIP and OSGi Dependencies ZIP files you downloaded. Here's how to install them:

1.  Create the folder `$WILDFLY_HOME/modules/com/liferay/portal/main` if it
    doesn't exist and extract the Dependencies ZIP JARs to it.

2.  Download your database driver `.jar` file and copy it into the same folder.

    | **Note:** The [Liferay DXP Compatibility Matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?) specifies supported databases and environments.

3.  Create the file `module.xml` in the
    `$WILDFLY_HOME/modules/com/liferay/portal/main` folder and insert this
    configuration:

    ```xml
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
            <resource-root path="[place your database vendor's jar here]" />
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
    ```

    Replace `[place your database vendor's jar here]` with the driver JAR for your database.

4.  Create an `osgi` folder in your [Liferay
    Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home) folder. Extract the
    OSGi Dependencies ZIP file that you downloaded into the `[Liferay
    Home]/osgi` folder.

    The `osgi` folder provides the necessary modules for @product@'s OSGi
    runtime.

## Running @product@ on Wildfly in Standalone Mode vs. Domain Mode

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
application server. Since JSP hooks and Ext plugins are deprecated, however, you
may not be using them.

The command line interface is recommended for domain mode deployments.

| **Note:** This does not prevent @product@ from running in a clustered
| environment on multiple Wildfly servers. You can set up a cluster of @product@
| instances running on Wildfly servers running in standalone mode. Please refer
| to the [@product@ clustering articles](/docs/7-2/deploy/-/knowledge_base/d/liferay-clustering)
| for more information.

## Configuring Wildfly

Configuring Wildfly to run @product@ includes these things:

-   Setting environment variables
-   Setting properties and descriptors
-   Removing unnecessary configurations

Optionally, you can configure Wildfly to manage @product@'s data source and mail
session.

Start with configuring Wildfly to run @product@.

Make the following modifications to
`$WILDFLY_HOME/standalone/configuration/standalone.xml`:

1.  Locate the closing `</extensions>` tag. Directly beneath that tag, insert
    the following system properties:

    ```xml
    <system-properties>
        <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
        <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
    </system-properties>
    ```

2.  Add the following `<filter-spec>` tag within the `<console-handler>` tag,
    directly below the `<level name="INFO"/>` tag:

    ```xml
    <filter-spec value="not(any(match(&quot;WFLYSRV0059&quot;),match(&quot;WFLYEE0007&quot;)))" />
    ```

3.  Add a timeout for the deployment scanner by setting
    `deployment-timeout="600"` as seen in the excerpt below.

    ```xml
    <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
        <deployment-scanner deployment-timeout="600" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" runtime-failure-causes-rollback="${jboss.deployment.scanner.rollback.on.failure:false}"/>
    </subsystem>
    ```

4.  Add the following JAAS security domain to the security subsystem
    `<security-domains>` defined in element `<subsystem
    xmlns="urn:jboss:domain:security:2.0">`.

    ```xml
    <security-domain name="PortalRealm">
        <authentication>
            <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
        </authentication>
    </security-domain>
    ```

5.  Remove the welcome content code snippets:

    ```xml
    <location name="/" handler="welcome-content"/>
    ```

    and

    ```xml
    <handlers>
        <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
    </handlers>
    ```

6.  Find the `<jsp-config/>` tag and set the `development`, `source-vm`, and
    `target-vm` attributes in the tag. Once finished, the tag should look like
    this:

    ```xml
    <jsp-config development="true" source-vm="1.8" target-vm="1.8" />
    ```

**Checkpoint:**

Before continuing, verify the following properties have been set in the
`standalone.xml` file:

1.  The new `<system-property>` is added.

2.  The new `<filter-spec>` is added.

3.  The `<deployment-timeout>` is set to `600`.

4.  The new `<security-domain>` is created.

5.  Welcome content is removed.

6.  The `<jsp-config>` tag contains its new attributes.

Now you must configure your JVM and startup scripts.

In the `$WILDFLY_HOME/bin/` folder, modify your standalone domain's
configuration script file `standalone.conf` (`standalone.conf.bat` on Windows):

- Set the file encoding to `UTF-8`
- Set the user time zone to `GMT`
- Set the preferred protocol stack
- Increase the default amount of memory available.

| **Important:** For @product@ to work properly, the application server JVM must
| use the `GMT` time zone and `UTF-8` file encoding.

Make the following edits as applicable for your operating system:

**Windows:**

1.  Comment out the initial `JAVA_OPTS` assignment like this:

    ```bash
    rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m"
    ```

2.  Add the following `JAVA_OPTS` assignment one line above the `:JAVA_OPTS_SET`
    line found at end of the file:

    ```bash
    set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=200m"
    ```

**Unix:**

1.  Below the `if [ "x$JAVA_OPTS" = "x" ];` statement, replace this `JAVA_OPTS`
    statement:

    ```bash
    JAVA_OPTS="-Xms64m -Xmx512m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m -Djava.net.preferIPv4Stack=true"
    ```

    with this:

    ```bash
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
    ```

2.  Add the following statement to the bottom of the file:

    ```bash
    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=200m"
    ```

This sets the file encoding to UTF-8, prefers an IPv4 stack over IPv6, sets the
time zone to GMT, gives the JVM 2GB of RAM, and limits Metaspace to 512MB.

On JDK 11, it's recommended to add this JVM argument to display four-digit
years.

```bash
-Djava.locale.providers=JRE,COMPAT,CLDR
```

After installation, tune your system (including these JVM options) for
performance.

| **Important:** For @product@ to work properly, the application server JVM must
| use the `GMT` time zone and `UTF-8` file encoding.

| **Note:** If you plan on using the IBM JDK with your Wildfly server, you must
| complete some additional steps. First, navigate to the
| `$WILDFLY_HOME/modules/com/liferay/portal/main/module.xml` file and insert the
| following dependency within the `<dependencies>` element:
|
|     <module name="ibm.jdk" />
|
| Then navigate to the
| `$WILDFLY_HOME/modules/system/layers/base/sun/jdk/main/module.xml` file and
| insert the following path names inside the `<paths>...</paths>` element:
|
|     <path name="com/sun/crypto" />
|     <path name="com/sun/crypto/provider" />
|     <path name="com/sun/org/apache/xml/internal/resolver" />
|     <path name="com/sun/org/apache/xml/internal/resolver/tools" />
|
| The added paths resolve issues with deployment exceptions and image uploading
| problems.

**Checkpoint:**

You've configured the application server's JVM settings.

1.  The file encoding, user time-zone, preferred protocol stack have been set in
    the `JAVA_OPTS` in the `standalone.conf.bat` file.

2.  The default amount of memory available has been increased.

The prescribed script modifications are now complete for your @product@
installation on Wildfly. Next you'll configure your database.

### Database Configuration

The easiest way to handle database configuration is to let @product@ manage your
data source. The
[Basic Configuration](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install#using-the-built-in-data-source)
page lets you configure @product@'s built-in data source. If you want to use the
built-in data source, skip this section.

If using WildFly to manage the data source, follow these steps:

1. Add the data source inside the `$WILDFLY_HOME/standalone/configuration/standalone.xml` file's `<datasources>` element:

    ```xml
    <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
        <connection-url>[place the URL to your database here]</connection-url>
        <driver>[place your driver name here]</driver>
        <security>
            <user-name>[place your user name here]</user-name>
            <password>[place your password here]</password>
        </security>
    </datasource>
    ```

    Make sure to replace the database URL, user name, and password with the appropriate values. For example JDBC connection values, please see [Database Templates](/docs/7-2/deploy/-/knowledge_base/d/database-templates)

    | **Note:** If the data source `jndi-name` must be changed, edit the `datasource` element in the `<default-bindings>` tag.

1. Add the driver to the `standalone.xml` file's `<drivers>` element also found within the `<datasources>` element.

    ```xml
    <drivers>
        <driver name="[name of database driver]" module="com.liferay.portal">
            <driver-class>[JDBC driver class]</driver-class>
        </driver>
    </drivers>
    ```

    A final data sources subsystem that uses MySQL should look like this:

    ```xml
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
                    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
                </driver>
            </drivers>
        </datasources>
    </subsystem>
    ```

3.  In a
    [`portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    file in your Liferay Home, specify your data source:

    ```properties
    jdbc.default.jndi.name=java:jboss/datasources/ExampleDS
    ```

Now that you've configured your data source, the mail session is next.

### Mail Configuration

As with database configuration, the easiest way to configure mail is to let
@product@ handle your mail session. If you want to use @product@'s built-in mail
session, skip this section and
[configure the mail session](/docs/7-2/deploy/-/knowledge_base/d/configuring-mail)
in the Control Panel.

If you want to manage your mail session with Wildfly, follow these steps:

1.  Specify your mail subsystem in the
    `$WILDFLY_HOME/standalone/configuration/standalone.xml` file like this:

    ```xml
    <subsystem xmlns="urn:jboss:domain:mail:3.0">
        <mail-session jndi-name="java:jboss/mail/MailSession" name="mail-smtp">
            <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp" username="USERNAME" password="PASSWORD"/>
       </mail-session>
    </subsystem>
    ...
    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <outbound-socket-binding name="mail-smtp">
            <remote-destination host="[place SMTP host here]" port="[place SMTP port here]"/>
        </outbound-socket-binding>
    </socket-binding-group>
    ```

2.  In your
    [`portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    file in Liferay Home, reference your mail session:

    ```properties
    mail.session.jndi.name=java:jboss/mail/MailSession
    ```

Next, you'll deploy @product@ to your Wildfly app server.

## Deploying @product@

Now you're ready to deploy @product@ using the @product@ WAR file.

1.  If the folder `$WILDFLY_HOME/standalone/deployments/ROOT.war` already exists
    in your Wildfly installation, delete all of its subfolders and files.
    Otherwise, create a new folder called
    `$WILDFLY_HOME/standalone/deployments/ROOT.war`.

2.  Unzip the @product@ `.war` file into the `ROOT.war` folder.

3.  To trigger deployment of `ROOT.war`, create an empty file named
    `ROOT.war.dodeploy` in your `$WILDFLY_HOME/standalone/deployments/` folder.
    On startup, Wildfly detects this file and deploys it as a web application.

4.  Start the Wildfly application server by navigating to `$WILDFLY_HOME/bin`
    and running `standalone.bat` or `standalone.sh`.

Congratulations; you've deployed @product@ on Wildfly!

| **Note:** After deploying @product@, you may see excessive warnings and log
| messages, such as the ones below, involving `PhaseOptimizer`. These are
| benign and can be ignored. Make sure to adjust your app server's logging level
| or log filters to avoid excessive benign log messages.
|
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass gatherExternProperties
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass checkControlFlow
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
|     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
