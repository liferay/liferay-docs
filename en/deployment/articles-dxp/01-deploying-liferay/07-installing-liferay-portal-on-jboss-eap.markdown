---
header-id: installing-product-on-jboss-eap
---

# Installing @product@ on JBoss EAP

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-jboss-eap.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Installing @product@ on JBoss EAP 7.1 takes three steps:

- Installing dependencies to your application server
- Configuring your application server for @product@
- Installing the @product@ WAR file to your application server

| **Important:** Before installing @product@, familiarize yourself with
| [preparing for install](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install).

Now, [download the @product@ WAR and Dependency
JARs](/docs/7-2/deploy/-/knowledge_base/d/obtaining-product#downloading-the-liferay-war-and-dependency-jars):

-   @product@ WAR file
-   Dependencies ZIP file
-   OSGi Dependencies ZIP file

Not that [*Liferay Home*](/docs/7-2/deploy/-/knowledge_base/d/liferay-home)
is the folder containing your JBoss server folder. After installing and
deploying @product@, the Liferay Home folder contains the JBoss server folder as
well as `data`, `deploy`, `logs`, and `osgi` folders. `$JBOSS_HOME` refers to
your JBoss server folder. This folder is usually named `jboss-eap-[version]`.

## Installing Dependencies

@product@ depends on several Liferay-specific and third-party JARs.
Download and install the required JARs as described below.

1.  Create the folder `$JBOSS_HOME/modules/com/liferay/portal/main` if it
    doesn't exist and extract the JARs from the dependencies ZIP to it.

2.  Download your database driver `.jar` file and copy it into the same folder.

    | **Note:** The [Liferay DXP Compatibility Matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?) specifies supported databases and environments.

3.  Create the file `module.xml` in the
    `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert this
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

**Checkpoint:**

1. The dependencies files have been unzipped into the `$JBOSS_HOME/modules/com/liferay/portal/main` folder and a database jar.
1. The `module.xml` contains all JARs in the `<resource-root-path>` elements.
1. The `osgi` dependencies have been unzipped into the `osgi` folder.

## Running @product@ on JBoss EAP in Standalone Mode vs. Domain Mode

JBoss EAP can be launched in either *standalone* mode or *domain* mode. Domain
mode allows multiple application server instances to be managed from a single
control point. A collection of such application servers is known as a *domain*.
For more information on standalone mode vs. domain mode, please refer to the
section on this topic in the [JBoss EAP Product
Documentation](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.1/html/introduction_to_jboss_eap/overview_of_jboss_eap#operating_modes).

@product@ supports JBoss EAP when it runs in standalone mode but not when it
runs in domain mode. @product@'s hot-deploy does not work with a managed
deployment, since JBoss manages the content of a managed deployment by copying
files (exploded or non-exploded). This prevents JSP hooks and Ext plugins from
working as intended. For example, JSP hooks don't work on JBoss EAP running in
managed domain mode, since @product@'s JSP override mechanism relies on the
application server. Since JSP hooks and Ext plugins are deprecated, however, you
may not be using them.

The command line interface is recommended for domain mode deployments.

| **Note:** This does not prevent @product@ from running in a clustered
| environment on multiple JBoss servers. You can set up a cluster of @product@
| instances running on JBoss EAP servers running in standalone mode. Please
| refer to the [@product@ clustering articles](/docs/7-2/deploy/-/knowledge_base/d/liferay-clustering)
| for more information.

## Configuring JBoss

Configuring JBoss to run @product@ includes these things:

- Setting environment variables
- Setting properties and descriptors
- Removing unnecessary configurations

Optionally, you can configure JBoss to manage @product@'s data source and mail
session.

Start with configuring JBoss to run @product@.

Make the following modifications to
`$JBOSS_HOME/standalone/configuration/standalone.xml`:

1.  Locate the closing `</extensions>` tag. Directly beneath that tag, insert
    these system properties:

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
        <deployment-scanner deployment-timeout="600" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
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
    <jsp-config development="true" source-vm="1.8" target-vm="1.8"/>
    ```

**Checkpoint:**

Before continuing, verify the following properties have been set in the
`standalone.xml` file:

1.  The new `<system-property>` is added.

2.  The new `<filter-spec>` is added.

3.  The `<deployment-timeout>` is set to `360`.

4.  The new `<security-domain>` is created.

5.  Welcome content is removed.

6.  The `<jsp-config>` tag contains its new attributes.

Now you should configure your JVM and startup scripts.

In the `$WILDFLY_HOME/bin/` folder, modify your standalone domain's
configuration script file `standalone.conf` (`standalone.conf.bat` on Windows):

- Set the file encoding to `UTF-8`
- Set the user time zone to `GMT`
- Set the preferred protocol stack
- Increase the default amount of memory available.

| **Important:** For @product@ to work properly, the application server JVM must
| use the `GMT` time zone and `UTF-8` file encoding.

Make the following edits as applicable to your operating system:

**Windows**

1.  Comment out the initial `JAVA_OPTS` assignment as demonstrated in the
    following line:

    ```bash
    rem set "JAVA_OPTS=-Xms1G -Xmx1G -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m"
    ```

2.  Add the following `JAVA_OPTS` assignment one line above the `:JAVA_OPTS_SET`
    line found at end of the file:

    ```bash
    set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=768m"
    ```

**Unix**

1.  Below the `if [ "x$JAVA_OPTS" = "x" ];` statement, replace this `JAVA_OPTS`
    statement:

    ```bash
    JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2560m -Djava.net.preferIPv4Stack=true"
    ```

    with this:

    ```bash
    JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
    ```

2.  Add the following statement to the bottom of the file:

    ```bash
    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m"
    ```


On JDK 11, it's recommended to add this JVM argument to display four-digit
years.

```bash
-Djava.locale.providers=JRE,COMPAT,CLDR
```

| **Note:** If you plan on using the IBM JDK with your JBoss server, you must
| complete some additional steps. First, navigate to the
| `$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` file and insert the
| following dependency within the `<dependencies>` element:
|
|     <module name="ibm.jdk" />
|
| Then navigate to the
| `$JBOSS_HOME/modules/system/layers/base/sun/jdk/main/module.xml` file and
| insert the following path names inside the `<paths>...</paths>` element:
|
|     <path name="com/sun/crypto" />
|     <path name="com/sun/crypto/provider" />
|     <path name="com/sun/image/codec/jpeg" />
|     <path name="com/sun/org/apache/xml/internal/resolver" />
|     <path name="com/sun/org/apache/xml/internal/resolver/tools" />
|
| The added paths resolve issues with portal deployment exceptions and image
| uploading problems.

**Checkpoint:**

At this point, you've finished configuring the application server's JVM
settings.

1.  The file encoding, user time-zone, preferred protocol stack have been set in
    the `JAVA_OPTS` in the `standalone.conf.bat` file.

2.  The default amount of memory available has been increased.

The prescribed script modifications are now complete for your @product@
installation on JBoss. Next you'll configure the database and mail.

## Database Configuration

The easiest way to handle your database configuration is to let @product@ manage
your data source. The [Basic
Configuration](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install#using-the-built-in-data-source)
page lets you configure @product@'s built-in data source. If you want to use the
built-in data source, skip this section.

This section demonstrates configuring a MySQL database. If you're using a
different database, modify the data source and driver snippets as necessary.

If using JBoss to manage the data source, follow these steps:

1. Add the data source inside the `$JBOSS_HOME/standalone/configuration/standalone.xml` file's the `<datasources>` element.

    ```xml
    <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
        <connection-url>[place the URL to your database here]</connection-url>
        <driver>[place the driver name here]</driver>
        <security>
            <user-name>[place your user name here]</user-name>
            <password>[place your password here]</password>
        </security>
    </datasource>
    ```

    Make sure to replace the database URL, user name, and password with the appropriate values.

    | **Note:** If the data source `jndi-name` must be changed, edit the `datasource` element in the `<default-bindings>` tag.

2.  Add your driver to the `standalone.xml` file's `<drivers>` element also
    found within the `<datasources>` element.

    ```xml
    <drivers>
        <driver name="[name of driver must match name above]" module="com.liferay.portal">
            <driver-class>[place your JDBC driver class here]</driver-class>
        </driver>
    </drivers>
    ```

    A final data sources subsystem that uses MySQL should look like this:

    ```xml
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
    ```

3.  In a
    [`portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    file in your Liferay Home, specify your data source:

    ```properties
    jdbc.default.jndi.name=java:jboss/datasources/ExampleDS
    ```

Now that you've configured your data source, the mail session is next.

## Mail Configuration

As with database configuration, the easiest way to configure mail is to let
@product@ handle your mail session. If you want to use @product@'s built-in mail
session, skip this section and
[configure the mail session](/docs/7-2/deploy/-/knowledge_base/d/configuring-mail)
in the Control Panel.

If you want to manage your mail session with JBoss, follow these steps:

1.  Specify your mail subsystem in the
    `$JBOSS_HOME/standalone/configuration/standalone.xml` file like this:

    ```xml
    <subsystem xmlns="urn:jboss:domain:mail:3.0">
        <mail-session jndi-name="java:jboss/mail/MailSession" >
            <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp">
                <login username="[place user name here]" password="[place password here]"/>
            </smtp-server>
       </mail-session>
    </subsystem>
    ...
    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <outbound-socket-binding name="mail-smtp">
            <remote-destination host="[place SMTP mail host here]" port="[place mail port here]"/>
        </outbound-socket-binding>
    </socket-binding-group>
    ```

2.  In your `portal-ext.properties` file in Liferay Home, reference your mail
    session:

    ```properties
    mail.session.jndi.name=java:jboss/mail/MailSession
    ```

You've got mail! Next, you'll deploy @product@ to your JBoss app server.

## Deploy Liferay

Now you're ready to deploy @product@ using the @product@ WAR file.

1.  If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists
    in your JBoss installation, delete all of its subfolders and files.
    Otherwise, create a new folder called
    `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2.  Unzip the @product@ `.war` file into the `ROOT.war` folder.

3.  To trigger deployment of `ROOT.war`, create an empty file named
    `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder.
    On startup, JBoss detects this file and deploys it as a web application.

4.  Start the JBoss application server by navigating to `$JBOSS_HOME/bin`
    and running `standalone.bat` or `standalone.sh`.

Congratulations; you've now deployed @product@ on JBoss!

| After deploying @product@, you may see excessive warnings and log messages,
| such as the ones below, involving `PhaseOptimizer`. These are benign and can
| be ignored. Make sure to adjust your app server's logging level or log
| filters to avoid excessive benign log messages.
|
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass gatherExternProperties
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass checkControlFlow
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
|     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
