# Installing Liferay on JBoss 7.1 [](id=installing-liferay-on-jboss-7-1)

**Liferay Home** is one folder above JBoss's install location.

1. Download and install JBoss AS 7.1.x into your preferred directory. This
   directory is referred to as `$JBOSS_HOME` throughout this section.

2. Download the latest version of the Liferay Portal `.war` file.

3. Download Liferay's Portal Dependencies.

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on JBoss.

## Dependency Jars [](id=dependency-jars)

Let's work with the dependency jar files first.

1. Create the folder `$JBOSS_HOME/modules/com/liferay/portal/main`. Unzip the
   the Liferay Portal Dependencies zip file and copy the .jar files to this
   folder. 

2. Download your database driver `.jar` file and copy it into the
   same folder. For example, for MySQL, download the MySQL Connector/J driver
   from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)
   and put its `.jar` file into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3. Create the file `module.xml` in the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the following
   contents:

        <?xml version="1.0"?>

        <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
            <resources>
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
    the driver jar for your database. 

Great! You have your `.jar` files ready. 

## Running Liferay on JBoss 7.1 in Standalone Mode vs. Domain Mode [](id=running-liferay-on-jboss-7-1-in-standalone-mode-vs-domain-mode)

JBoss 7.1 can be launched in either *standalone* mode or *domain* mode. Domain
mode allows multiple application server instances to be managed from a single
control point. A collection of such application servers is known as a *domain*.
For more information on standalone mode vs. domain mode, please refer to the
section on this topic in the [JBoss 7.1 Admin Guide](https://docs.jboss.org/author/display/AS71/Admin+Guide#AdminGuide-Operatingmodes).
Liferay fully supports JBoss 7.1 when it runs in standalone mode but not when it
runs in domain mode.

You can run Liferay on JBoss 7.1 in domain mode, but this method is not fully
supported. In particular, Liferay's hot-deploy does not work, since JBoss 7.1
cannot deploy non-exploded `.war` files in domain mode. Instead, `.war` files
are in the `domain/data/content` directory. Deployments are only possible using
the command line interface. This prevents many Liferay plugins from working as
intended. For example, JSP hooks don't work on JBoss 7.1 running in domain mode, 
since Liferay's JSP override mechanism relies on the application server
reloading customized JSP files from the exploded plugin `.war` file location.
Other plugins, such as service or action hooks, should still work properly since
they don't require JBoss to access anything (such as JSP files) from an exploded
`.war` file on the file system. 

+$$$

**Note:** This does not prevent Liferay from running in a clustered environment
on multiple JBoss servers. You can set up a cluster of Liferay instances running
on JBoss 7.1 servers running in standalone mode. Please refer to the chapter of
this guide on
[Configuring Liferay for High Availability](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/configuring-liferay-for-high-availabili-liferay-portal-6-2-user-guide-20-en)
for information on setting up a Liferay cluster.

$$$

## Configuring JBoss [](id=configuring-jboss)

Let's make some adjustments in your configuration to support using Liferay.

You can specify the JBoss server instance's configuration in the XML file
`$JBOSS_HOME/standalone/configuration/standalone.xml`. You must also make some
modifications to your configuration and startup scripts found in the
`$JBOSS_HOME/bin/` folder.  Lastly, you'll need to make some modifications in
your `$JBOSS_HOME/modules/`. Let's start with the changes to `standalone.xml`.

Make the following modifications to `standalone.xml`:

1.  Add the following system properties between the `</extensions>` and
`<management>` tags:

        <system-properties>
            <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
            <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
        </system-properties>

2.  Add a timeout for the deployment scanner by setting
`deployment-timeout="240"` as seen in the excerpt below.

        <subsystem xmlns="urn:jboss:domain:deployment-scanner:1.1">
            <deployment-scanner deployment-timeout="240" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
        </subsystem>

3.  Add the following JAAS security domain to the security subsystem
`<security-domains>` defined in element `<subsystem
xmlns="urn:jboss:domain:security:1.1">`.

        <security-domain name="PortalRealm">
            <authentication>
                <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
            </authentication>
        </security-domain>

4.  Disable the welcome root of the web subsystem's virtual server default host
by specifying `enable-welcome-root="false"`.

        <subsystem xmlns="urn:jboss:domain:web:1.1" default-virtual-server="default-host">
            <connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/>
            <virtual-server name="default-host" enable-welcome-root="false">
               <alias name="localhost" />
               <alias name="example.com" />
            </virtual-server>
        </subsystem>        

5.  Insert the following `<configuration>` element inside the web subsystem
element `<subsystem xmlns="urn:jboss:domain:web:1.1"
default-virtual-server="default-host" native="false">`.

        <configuration>
            <jsp-configuration development="true" />
        </configuration>
 
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

        rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MaxPermSize=256M"

Then add the following `JAVA_OPTS` assignment one line above the
    `:JAVA_OPTS_SET` line found at end of the file:

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

On Unix, merge the following values into your settings for `JAVA_OPTS`, 
replacing any matching attributes with the ones found in the assignment
below:

        JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m

Make sure you replace the `$JBOSS_HOME` references with the appropriate
directory. You'll notice some Java security options. You'll finish configuring
the Java security options in the *Security Configuration* section. 

Lastly, navigate to the `$JBOSS_HOME/modules/sun/jdk/main/module.xml` file and
insert the following path names inside the `<paths>...</paths>` element: 

        <path name="com/sun/crypto" />
        <path name="com/sun/crypto/provider" />
        <path name="com/sun/image/codec/jpeg" />
        <path name="com/sun/org/apache/xml/internal/resolver" />
        <path name="com/sun/org/apache/xml/internal/resolver/tools" />

The added paths resolve issues with portal deployment exceptions and image
uploading problems on a Liferay Portal instance running on JBoss 7.1.x. 

The prescribed script modifications are now complete for your Liferay
installation on JBoss. Next you'll configure mail and the database. 

## Using the IBM JDK with JBoss [](id=using-the-ibm-jdk-with-jboss)

If you plan on using the IBM JDK with your JBoss application server, follow the
instructions in this section. If you plan on using another type of JDK, you can
skip this section. 

Currently, there are bugs in the
`$JBOSS_HOME/modules/org/jboss/as/server/main/jboss-as-<$JBOSS_VERSION>.Final.jar`
file regarding the IBM JVM
([LPS-39705](http://issues.liferay.com/browse/LPS-39705) and
[JBPAPP-9353](http://issues.jboss.org/browse/JBPAPP-9353)), which requires
additional steps to ensure a successful deployment with Liferay. 

Open the `$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` file and
insert the following dependency within the `<dependencies>` element: 

    <module name="ibm.jdk" />

Next, you'll need to include a patch from Liferay's source code for one of
JBoss' default `.jar` files. This can be done by downloading the
`liferay-portal` repository's ZIP file by clicking *Download ZIP* on the
repository's [GitHub page](https://github.com/liferay/liferay-portal). Once
you've downloaded the Liferay source, unzip the source into a temporary folder.
This location will be referred to as `$LIFERAY_SOURCE`. 

In summary, you'll need to update the `ServerDependenciesProcessor.class` file
in the `jboss-as-[$JBOSS_VERSION].Final.jar` file to specify the IBM JDK. The
steps to insert the patch can be referenced below. 

1. Copy the `jboss-as-[$JBOSS_VERSION].Final.jar` file from
   `$JBOSS_HOME/modules/org/jboss/as/server/main` to the
   `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` folder. 
 
2. Navigate to the
   `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` directory
   in a command prompt and enter the following statement: 
    
        jar uf jboss-as-server-[$JBOSS_VERSION].Final.jar org/jboss/as/server/deployment/module/ServerDependenciesProcessor.class

    This command inserts the `ServerDependenciesProcessor.class` file into the
    `jboss-as-[$JBOSS_VERSION].Final.jar` file's
    `org/jboss/as/server/deployment/module` folder. You can reference the
    official documentation for updating a JAR file at
    [http://docs.oracle.com/javase/tutorial/deployment/jar/update.html](http://docs.oracle.com/javase/tutorial/deployment/jar/update.html). 

3. Copy the `jboss-as-[$JBOSS_VERSION].Final.jar` file back to its original
   `$JBOSS_HOME/modules/org/jboss/as/server/main` folder. 

Lastly, you'll need to make a few changes in your `$JBOSS_HOME/modules`
directory. 

1. Create the folder `$JBOSS_HOME/modules/ibm/jdk/main`. Create a new file
called `module.xml` in that folder.

2. Insert the following contents into the
`$JBOSS_HOME/modules/ibm/jdk/main/module.xml` file: 

        <?xml version="1.0"?>

        <module xmlns="urn:jboss:module:1.1" name="ibm.jdk">
            <dependencies>
                <system export="true">
                    <paths>
                        <path name="com/ibm" />
                        <path name="com/ibm/crypto/provider" />
                        <path name="com/ibm/jvm" />
                        <path name="com/ibm/jvm/io" />
                        <path name="com/ibm/jvm/util" />
                        <path name="com/ibm/match" />
                        <path name="com/ibm/misc" />
                        <path name="com/ibm/net" />
                        <path name="com/ibm/nio" />
                        <path name="com/ibm/nio/ch" />
                        <path name="com/ibm/security/auth" />
                        <path name="com/ibm/security/bootstrap" />
                        <path name="com/ibm/security/auth/module" />
                        <path name="com/ibm/security/util" />
                        <path name="META-INF/services" />
                    </paths>
                </system>
            </dependencies>
        </module>

Your JBoss application server is now configured to use the IBM JDK. Next, you'll
learn about managing a data source with JBoss. 

## Database Configuration [](id=database-configuration)

If you want JBoss to manage your data source, follow the instructions in this
section. If you want to use the built-in Liferay data source, you can skip this
section.

Modify `standalone.xml` and add your data source and driver in the
`<datasources>` element of your data sources subsystem.

1. First, add your data source inside the `<datasources>` element.

        <datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
            <connection-url>jdbc:mysql://localhost/lportal</connection-url>
            <driver>mysql</driver>
            <security>
                <user-name>root</user-name>
                <password>root</password>
            </security>
        </datasource>

    Be sure to replace the database name (i.e. `lportal`), user, and
    password the appropriate values. 

2. Add your driver to the `<drivers>` element also found within the
   `<datasources>` element.

        <drivers>
            <driver name="mysql" module="com.liferay.portal"/>
        </drivers>

Your final data sources subsystem should look like this:

        <subsystem xmlns="urn:jboss:domain:datasources:1.0">
            <datasources>
                <datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
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
If you want to use the built-in Liferay mail session, you can skip this section.

Specify your mail subsystem in `standalone.xml` as in the following example:

    <subsystem xmlns="urn:jboss:domain:mail:1.0">
        <mail-session jndi-name="java:/mail/MailSession" >
            <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp">
                   <login name="username" password="password"/>
            </smtp-server>
            <pop3-server outbound-socket-binding-ref="mail-pop">
                <login name="username" password="password"/>
            </pop3-server>
       </mail-session>
    </subsystem>
    ...
    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <outbound-socket-binding name="mail-smtp">
            <remote-destination host="smtp.gmail.com" port="465"/>
        </outbound-socket-binding>
        <outbound-socket-binding name="mail-pop">
            <remote-destination host="pop.gmail.com" port="110"/>
        </outbound-socket-binding>
    </socket-binding-group>
 
You've got mail! Next, you'll make sure Liferay can connect using your new mail
session and database.

## Configuring data sources and mail sessions [](id=configuring-data-sources-and-mail-sessions)

Now that your data source and mail session are set up, you need to ensure
Liferay Portal can access them.

1.  First, navigate to the Liferay Home folder, which is one folder above JBoss's
    install location (i.e. `$JBOSS_HOME/..`).

2.  If you're using *JBoss* to manage your data source, add the following
    configuration to your `portal-ext.properties` file in your *Liferay Home* to
    refer to your data source:

        jdbc.default.jndi.name=java:jdbc/LiferayPool

    If you're using *Liferay Portal* to manage your data source, follow the
    instructions for using the setup wizard.

3.  If you're using *Liferay Portal* to manage your mail session, this
    configuration is done in Liferay Portal. That is, after starting your
    portal as described in the *Deploy Liferay* section, go to *Control Panel
    &rarr; Server Administration &rarr; Mail* and enter the settings for your
    mail session.

    If you're using *JBoss* to manage your mail session, add the following
    configuration to your `portal-ext.properties` file to reference that mail
    session:

        mail.session.jndi.name=java:mail/MailSession

Before you deploy Liferay Portal on your JBoss app server, you should enable and
configure Java security so you can use Liferay's plugin security manager
with your downloaded Liferay applications.

## Security Configuration [](id=security-configuration)

When you're ready to begin using other people's apps from Marketplace, you'll
want to protect your portal and your JBoss server from security threats. To do
so, you can enable Java Security on your JBoss server and specify a security
policy to grant your portal access to your server.

Remember, we set the `-Djava.security.manager` and `-Djava.security.policy` Java
options in the `standalone.conf.bat` file earlier in the *Configuring JBoss*
section. The `-Djava.security.manager` Java option enables security on 
JBoss. Likewise, the `-Djava.security.policy` Java option lists the
permissions for your server's Java security policy. If you have not set these
options, you'll need to do so before using Java security.

This configuration opens up all permissions. You can tune the permissions in
your policy later. Create the `$JBOSS_HOME/bin/server.policy` file and add the
following contents:

    grant {
        permission java.security.AllPermission;
    };

For extensive information on Java SE Security Architecture, see its
specification documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see section [*Understanding Plugin Security Management*](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in the Developer's Guide to learn how to configure Liferay plugin
access to resources.

## JSF Configuration [](id=jsf-configuration)

If you plan on using JSF applications in your application server, follow the
instructions below. In this section, you'll learn how to upgrade Mojarra and
Weld so your app server's versions are identical to the versions used by Liferay
Faces. 

### Upgrading Mojarra [](id=upgrading-mojarra)

Some versions of JBoss 7.1.x are not bundled with the correct Mojarra version
necessary to use Liferay Faces. For example, JBoss AS 7.1.1 comes with Mojarra
2.1.7 in the global classpath. Since Liferay Faces uses Mojarra 2.1.21, you'll
need to download a newer version of the `jsf-api.jar` and `jsf-impl.jar`
artifacts. 

1. Download
[`jsf-api-2.1.21.jar`](http://search.maven.org/#artifactdetails%7Ccom.sun.faces%7Cjsf-api%7C2.1.21%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/javax/faces/api/main/jsf-api-2.1.21.jar

2. Open the `$JBOSS_HOME/modules/javax/faces/api/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="jboss-jsf-api_2.1_spec-2.0.1.Final.jar"/> -->

3. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="jsf-api-2.1.21.jar"/>

4. Add the following module to the `<dependencies>` section: 

        <dependencies>
            ...
            <module name="com.sun.jsf-impl"/>
        </dependencies>

5. Download
[`jsf-impl-2.1.21.jar`](http://search.maven.org/#artifactdetails%7Ccom.sun.faces%7Cjsf-impl%7C2.1.21%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/com/sun/jsf-impl/main/jsf-impl-2.1.21.jar

6. Open the `$JBOSS_HOME/modules/com/sun/jsf-impl/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="jsf-impl-2.1.7-jbossorg-2.jar"/> -->

7. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="jsf-impl-2.1.21.jar"/>

Congratulations! You've officially upgraded Mojarra! If you'd like to verify
that you're using the correct version of Mojarra at runtime, download the
following [demo portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
and add it to a portal page. You should see a bulleted list of version info at
the bottom of the portlet.

![Figure 1: Make sure the Mojarra version displayed is the one you configured during the upgrade: Mojarra 2.1.21.](../../images/jboss-mojarra-upgrade.png)

Next you'll learn how to upgrade Weld. 

### Upgrading Weld [](id=upgrading-weld)

Some versions of JBoss 7.1.x are not bundled with the correct Weld version
necessary to use Liferay Faces. For example, JBoss AS 7.1.1 comes with Weld
1.1.5 in the global classpath. Since Liferay Faces uses Weld 1.1.10, you'll
need to download a newer version of the `weld-core.jar` artifact. 

1. Download
[`weld-core-1.1.10.Final.jar`](http://search.maven.org/#artifactdetails%7Corg.jboss.weld%7Cweld-core%7C1.1.10.Final%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/org/jboss/weld/core/main/weld-core-1.1.10.Final.jar

2. Open the `$JBOSS_HOME/modules/org/jboss/weld/core/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="weld-core-1.1.5.AS71.Final.jar"/> -->

3. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="weld-core-1.1.10.Final.jar"/>

You've successfully upgraded your Weld version. If you're interested in
configuring CDI for your JSF portlets running on JBoss, you'll need to configure
a few more things. For more information on configuring CDI, visit the
[Configuring JSF Portlets to Use CDI](/develop/tutorials/-/knowledge_base/6-2/contexts-and-dependency-injection-for-jsf-portlets#configuring-jsf-portlets-to-use-cdi)
section. 

Now you're ready to deploy Liferay Portal. 

## Deploy Liferay [](id=deploy-liferay)

1. If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists in
   your JBoss installation, delete all of its subfolders and files. Otherwise,
   create a new folder `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2. Unzip the Liferay `.war` file into the `ROOT.war` folder.

3. In the `ROOT.war` file, open the `WEB-INF/jboss-deployment-structure.xml`
   file. In this file, replace the `<module name="com.liferay.portal" />`
   dependency with the following configuration: 

        <module meta-inf="export" name="com.liferay.portal">
            <imports>
                <include path="META-INF" />
            </imports>
        </module>

    This allows OSGi plugins like Audience Targeting to work properly, by
    exposing the Portal API through the OSGi container. 

4. In the same `jboss-deployment-structure.xml` file, find the
   `<jboss-deployment-structure>` tag and update the `1.0` number within the
   `xmlns` attribute to `1.1`. 

5. To trigger deployment of `ROOT.war`, create an empty file named
   `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder.
   On startup, JBoss detects the presence of this file and deploys it as a web
   application.

6. Remove `eclipselink.jar` from
   `$JBOSS_HOME/standalone/deployments/ROOT.war/WEB-INF/lib` to insure the
   Hibernate persistence provider is used instead of the one provided in the
   `eclipselink.jar`.

    Now it's time to start Liferay Portal on JBoss!

7.  Start the JBoss application server.
 
Now you are truly *the boss* when it comes to deploying Liferay Portal on JBoss!
