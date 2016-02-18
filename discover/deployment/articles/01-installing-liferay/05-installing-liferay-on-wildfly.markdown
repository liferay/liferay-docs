# Installing Liferay on Wildfly 10.0

If you want a fresh installation of Liferay on Wildfly 10, simply download a
Liferay Wildfly bundle from
[https://www.liferay.com/downloads/liferay-portal/available-releases](https://www.liferay.com/downloads/liferay-portal/available-releases).
Even if you want to manually install Liferay on an existing Wildfly 10
application server, it can be helpful to download a Liferay Wildfly bundle. The
bundle contains many required dependencies and configuration files. Before
proceeding, you should also download the latest Liferay WAR file from
[https://www.liferay.com/downloads/liferay-portal/available-releases#additional-versions](https://www.liferay.com/downloads/liferay-portal/available-releases#additional-versions)
as well as the dependencies ZIP file and OSGi JARs ZIP file.

Installing Liferay manually requires these basic steps:

- Installing Liferay dependencies to your application server
- Configuring your application server for Liferay
- Installing the Liferay WAR file to your application server

**Liferay Home** is one folder above Wildfly's install location. *Liferay
Home* refers to the folder containing your Wildfly server folder. When Liferay
is installed on Wildfly, the Liferay Home folder contains the Wildfly server
folder as well as `data`, `deploy`, and `osgi` folders. You'll also
see the term `$WILDFLY_HOME` used in this guide. `$WILDFLY_HOME` refers to your
Wildfly server folder. This folder is usually named `wildfly-[version]`.

<!-- Check if "license" folder appears when Liferay is deployed via WAR. -->

## Installing Liferay Dependencies

Liferay depends on many JARs that are included in the Liferay Wildfly bundle.
Some JARs in the bundle are not strictly required but can still be useful. If
you don't have a Liferay Wildfly bundle, you can download the required JARs from
third-parties, as described below.

1. Create the folder `$WILDFLY_HOME/modules/com/liferay/portal/main`. Unzip the
   the Liferay Portal Dependencies zip file and copy the `.jar` files to this
   folder. 

2. Download your database driver `.jar` file and copy it into the
   same folder. For example, for MySQL, download the MySQL Connector/J driver
   from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)
   and put its `.jar` file into the
   `$WILDFLY_HOME/modules/com/liferay/portal/main` folder.

3. Download the remaining required JARs and insert them into the same folder. 

    - `com.liferay.osgi.service.tracker.collections.jar`
    - `com.liferay.registry.api.jar` - [https://repository.liferay.com/nexus/content/groups/liferay-ce/com/liferay/com.liferay.registry.api](https://repository.liferay.com/nexus/content/groups/liferay-ce/com/liferay/com.liferay.registry.api)

4. Create the file `module.xml` in the
   `$WILDFLY_HOME/modules/com/liferay/portal/main` folder and insert the
   following contents:

        <?xml version="1.0"?>

        <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
            <resources>
                <resource-root path="com.liferay.registry.api.jar" />
                <resource-root path="mysql-connector-java-[version]-bin.jar" />
                <resource-root path="portal-service.jar" />
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
    driver. If you are using a different database, replace the MySQL jar with 
    the driver JAR for your database (e.g., HSQL, PostgreSQL, etc.).

<!-- 5. If you downloaded a Liferay Wildfly bundle, extract the bundle to a
temporary location of your choosing. You'll copy a number of resources from this
bundle to your Wildfly server as you manually install Liferay. -->

<!-- May need to specify additional OSGi JARs as well. Keep this in mind. -->

Great! You have your `.jar` files ready. 

## Running Liferay on Wildfly 10.0 in Standalone Mode vs. Domain Mode

Wildfly 10.0 can be launched in either *standalone* mode or *domain* mode. Domain
mode allows multiple application server instances to be managed from a single
control point. A collection of such application servers is known as a *domain*.
For more information on standalone mode vs. domain mode, please refer to the
section on this topic in the
[Wildfly 10 Admin Guide](https://docs.jboss.org/author/display/WFLY10/Admin+Guide#AdminGuide-Operatingmodes).
Liferay fully supports Wildfly 10.0 when it runs in standalone mode but not when it
runs in domain mode.

You can run Liferay on Wildfly 10.0 in domain mode, but this method is not fully
supported. In particular, Liferay's hot-deploy does not work, since Wildfly 10.0
cannot deploy non-exploded `.war` files in domain mode. Instead, `.war` files
are in the `domain/data/content` directory. Deployments are only possible using
the command line interface. This prevents many Liferay plugins from working as
intended. For example, JSP hooks don't work on Wildfly 10.0 running in domain
mode, since Liferay's JSP override mechanism relies on the application server
reloading customized JSP files from the exploded plugin `.war` file location.
Other plugins, such as service or action hooks, should still work properly since
they don't require Wildfly to access anything (such as JSP files) from an
exploded `.war` file on the file system.

+$$$

**Note:** This does not prevent Liferay from running in a clustered environment
on multiple Wildfly servers. You can set up a cluster of Liferay instances
running on Wildfly 10.0 servers running in standalone mode. Please refer to the
chapter of this guide on
[Configuring Liferay for High Availability](/discover/deployment/-/knowledge_base/6-2/configuring-liferay-for-high-availability)
for information on setting up a Liferay cluster.

$$$

<!-- Replace link in note above, when available. -Cody -->

## Configuring Wildfly

Now you'll make some adjustments in your configuration to support using Liferay.

You can specify the Wildfly server instance's configuration in the XML file
`$WILDFLY_HOME/standalone/configuration/standalone.xml`. You must also make some
modifications to your configuration and startup scripts found in the
`$WILDFLY_HOME/bin/` folder.  Lastly, you'll need to make some modifications in
your `$WILDFLY_HOME/modules/`. You'll begin with making changes to
`standalone.xml`.

Make the following modifications to `standalone.xml`:

1. Locate the closing `</extensions>` tag. Directly beneath that tag, insert the
   following system properties:

        <system-properties>
            <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
            <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
        </system-properties>

2. Add a timeout for the deployment scanner by setting
`deployment-timeout="240"` as seen in the excerpt below.

        <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
            <deployment-scanner deployment-timeout="240" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" runtime-failure-causes-rollback="${jboss.deployment.scanner.rollback.on.failure:false}"/>
        </subsystem>

3. Add the following JAAS security domain to the security subsystem
   `<security-domains>` defined in element `<subsystem
   xmlns="urn:jboss:domain:security:1.2">`.

        <security-domain name="PortalRealm">
            <authentication>
                <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
            </authentication>
        </security-domain>

4. Remove the following tags (if necessary):

    - `<location name="/" handler="welcome-content"/>`
    - `<extension module="org.jboss.as.weld"/>`
    - `<subsystem xmlns="urn:jboss:domain:weld:2.0"/>`
    - `<subsystem xmlns="urn:jboss:domain:weld:3.0"/>`

5. Find the `<jsp-config/>` tag and insert the `development="true"` attribute
   into the tag. Once finished, the tag should look like the following:

        <jsp-config development="true" />

Now it's time for some changes to your configuration and startup scripts.
 
Make the following modifications to your standalone domain's configuration
script file `standalone.conf` (`standalone.conf.bat` on Windows) found in your
`$WILDFLY_HOME/bin/` folder.

These modifications change the following options: 
- Set the file encoding
- Set the user time-zone
- Set the preferred protocol stack
- Increase the default amount of memory available.

Make the following edits as applicable to your operating system:

On Windows, comment out the initial `JAVA_OPTS` assignment as demonstrated in
the following line:

        rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m"

Then add the following `JAVA_OPTS` assignment one line above the
`:JAVA_OPTS_SET` line found at end of the file:

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$WILDFLY_HOME/bin/server.policy -Dwildfly.home.dir=$WILDFLY_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxMetaspaceSize=384m -XX:MetaspaceSize=200m"

On Unix, merge the following values into your settings for `JAVA_OPTS`, 
replacing any matching attributes with the ones found in the assignment
below:

        JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$WILDFLY_HOME/bin/server.policy -Dwildfly.home.dir=$WILDFLY_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxMetaspaceSize=384m -XX:MetaspaceSize=200m

Make sure you replace the `$WILDFLY_HOME` references with the appropriate
directory. You'll notice some Java security options. You'll finish configuring
the Java security options in the *Security Configuration* section. 

+$$$

**Note:** If you plan on using the IBM JDK with your Wildfly server, you'll need
to complete some additional steps. First, navigate to the
`$WILDFLY_HOME/modules/com/liferay/portal/main/module.xml` file and insert the
following dependency within the `<dependencies>` element:

    <module name="ibm.jdk" />

Then navigate to the
`$WILDFLY_HOME/modules/system/layers/base/sun/jdk/main/module.xml` file and
insert the following path names inside the `<paths>...</paths>` element:

    <path name="com/sun/crypto" />
    <path name="com/sun/crypto/provider" />
    <path name="com/sun/image/codec/jpeg" />
    <path name="com/sun/org/apache/xml/internal/resolver" />
    <path name="com/sun/org/apache/xml/internal/resolver/tools" />

The added paths resolve issues with portal deployment exceptions and image
uploading problems on a Liferay instance running on Wildfly 10.0.x. 

$$$

The prescribed script modifications are now complete for your Liferay
installation on Wildfly. Next you'll configure mail and the database. 

## Database Configuration

<!-- Remaining from build-dist: -->


6. Create the `$WILDFLY_HOME/bin/server.policy` file and insert the following:

        grant {
            permission java.security.AllPermission;
        };







