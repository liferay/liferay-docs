# Installing Liferay on JBoss 7.1 [](id=installing-liferay-on-jboss-7-1)

**Liferay Home** is one folder above JBoss's install location.

1. Download and install JBoss AS 7.1.x into your preferred directory. This
   directory is referred to as `$JBOSS_HOME` throughout this section.

2. Download the latest version of the Liferay Portal `.war` file.

3. Download Liferay's Portal Dependencies.

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on JBoss.

## Dependency Jars

Let's work with the dependency jar files first.

1. Create folder `$JBOSS_HOME/modules/com/liferay/portal/main` and unzip the jar
   files found in the Liferay Portal Dependencies zip file to this folder. Make
   sure the zip file's jar files are extracted into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

2. Download your database driver `.jar` file and put it into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder as well. To use MySQL,
   we'll download the MySQL Connector/J driver from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)
   and put its `.jar` file into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3. Create the file `module.xml` in the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the following
   contents.

        <?xml version="1.0"?>

        <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
            <resources>
                <resource-root path="mysql-connector-java-[version]-bin.jar" />
                <resource-root path="portal-service.jar" />
                <resource-root path="portlet.jar" />
            </resources>
            <dependencies>
                <module name="ibm.jdk" />
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

4. Next, you'll need to include a patch from Liferay's source code for one of
JBoss' default `.jar` files. Once you've downloaded the Liferay source, unzip
the source into a temporary folder. We'll refer to the location of the Liferay
source as `$LIFERAY_SOURCE`.

5. Currently, there are bugs in the
`$JBOSS_HOME/modules/org/jboss/as/server/main/jboss-as-<$JBOSS_VERSION>.Final.jar`
file regarding the IBM JVM
([LPS-39705](http://issues.liferay.com/browse/LPS-39705) and
[JBPAPP-9353](http://issues.jboss.org/browse/JBPAPP-9353)) which requires
additional steps to ensure a successful deployment with Liferay. In summary,
you'll need to update the `ServerDependenciesProcessor.class` file in the
`jboss-as-<$JBOSS_VERSION>.Final.jar` file to specify the IBM JDK. The steps to
insert the patch can be referenced below.

    1. Cut and paste the `jboss-as-[$JBOSS_VERSION].Final.jar` file from
    `$JBOSS_HOME/modules/org/jboss/as/server/main` to the
    `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` folder.
    
    2. Navigate to the
    `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` directory
    in a command prompt and enter the following statement:
    
            jar uf jboss-as-server-[$JBOSS_VERSION].Final.jar org/jboss/as/server/deployment/module/ServerDependenciesProcessor.class

        This command inserts the `ServerDependenciesProcessor.class` file into
        the `jboss-as-[$JBOSS_VERSION].Final.jar` file's
        `org/jboss/as/server/deployment/module` folder. You can reference the
        official documentation for updating a JAR file at
        [http://docs.oracle.com/javase/tutorial/deployment/jar/update.html](http://docs.oracle.com/javase/tutorial/deployment/jar/update.html).

    3. Cut and paste the `jboss-as-[$JBOSS_VERSION].Final.jar` file back to its
    original `$JBOSS_HOME/modules/org/jboss/as/server/main` folder.

Great! You have your `.jar` files ready for your domain.

## Running Liferay on JBoss 7.1 in Standalone Mode vs. Domain Mode

JBoss 7.1 can be launched in either *standalone* mode or *domain* mode. Domain
mode allows multiple application server instances to be managed from a single
control point. A collection of such application servers is known as a *domain*.
For more information on standalone mode vs. domain mode, please refer to the
section on this topic in the [JBoss 7.1 Admin
Guide](https://docs.jboss.org/author/display/AS71/Admin+Guide#AdminGuide-Operatingmodes).
Liferay fully supports JBoss 7.1 when it runs in standalone mode but not when it
runs in domain mode.

You can run Liferay on JBoss 7.1 in domain mode, but this method is not fully
supported. In particular, Liferay's hot-deploy does not work since JBoss 7.1
cannot deploy non-exploded `.war` files in domain mode. The `.war` files are
located in the `domain/data/content` directory. Deployments are only possible
using the command line interface. This prevents many Liferay plugins from
working as intended. For example, JSP hooks don't work on JBoss 7.1 running in
domain mode since Liferay's JSP override mechanism relies on the application
server reloading customized JSP files from the exploded plugin `.war` file
location. Other plugins, such as service or action hooks, should still work
properly since they don't require JBoss to access anything (such as JSP files)
from an exploded `.war` file on the file system. 

+$$$

**Note:** This does not prevent Liferay from
running in a clustered environment on multiple JBoss servers. You can set up a
cluster of Liferay instances running on JBoss 7.1 servers running in standalone
mode. Please refer to the chapter of this guide on [Configuring Liferay for High
Availability](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/configuring-liferay-for-high-availabili-liferay-portal-6-2-user-guide-20-en)
for information on setting up a Liferay cluster.

$$$

For multi-server production environments, the choice of running a managed domain
versus standalone servers comes down to whether you want to use the centralized
management capabilities a managed domain provides. Running a standalone server
is better suited for most development scenarios. Any individual server
configuration that can be achieved in a managed domain can also be achieved in a
standalone server. Even if the application being developed will eventually run
in production on a managed domain installation, most development can be done
using a standalone server. Therefore, the best practice when running Liferay on
JBoss 7.1 is to use standalone mode.

<!-- An excellent document on JBoss' 7.1 domain mode (written by Igor Spasic)
can be found here:
https://support.liferay.com/secure/attachment/72257/JBoss%20AS7%20Domain%20Mode.pdf
-->

## Configuring JBoss

Let's make some adjustments in your configuration to support using Liferay.

You can specify the JBoss server instance's configuration in the XML file
`$JBOSS_HOME/standalone/configuration/standalone.xml`. We'll refer to this file
simply as `standalone.xml`. You must also make some modifications to your
configuration and startup scripts found in the `$JBOSS_HOME/bin/` folder.
Lastly, you'll need to make some modifications in your `$JBOSS_HOME/modules/`.
Let's start with the changes to `standalone.xml`.

Make the following modifications to `standalone.xml`:

1. Add the following system properties between the `</extensions>` and
`<management>` tags:

        <system-properties>
            <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
            <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
        </system-properties>

2. Add a timeout for the deployment scanner by setting
`deployment-timeout="240"` as seen in the excerpt below.

        <subsystem xmlns="urn:jboss:domain:deployment-scanner:1.1">
            <deployment-scanner deployment-timeout="240" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
        </subsystem>

3. Add the following JAAS security domain to the security subsystem
`<security-domains>` defined in element `<subsystem
xmlns="urn:jboss:domain:security:1.1">`.

        <security-domain name="PortalRealm">
            <authentication>
                <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
            </authentication>
        </security-domain>

4. Disable the welcome root of the web subsystem's virtual server default host
by specifying `enable-welcome-root="false"`.

        <subsystem xmlns="urn:jboss:domain:web:1.1" default-virtual-server="default-host">
            <connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/>
            <virtual-server name="default-host" enable-welcome-root="false">
               <alias name="localhost" />
               <alias name="example.com" />
            </virtual-server>
        </subsystem>        

5. Insert the following `<configuration>` element within the web subsystem
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

- On Windows, comment out the initial `JAVA_OPTS` assignment as demonstrated in
  the following line:

        rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MaxPermSize=256M"

Then add the following `JAVA_OPTS` assignment one line above the
`:JAVA_OPTS_SET` line found at end of the file:

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

- On Unix, merge the following values into your settings for `JAVA_OPTS`
  replacing any matching attributes with the ones found in the assignment
  below:

        JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m

    Make sure you replace the `$JBOSS_HOME` references with the appropriate
    directory. You'll notice we've added some Java security options. We'll
    finish configuring the Java security options in the *Security Configuration*
    section.

The prescribed script modifications are now complete for your Liferay
installation on JBoss.

Lastly, let's make the necessary changes in your `$JBOSS_HOME/modules`
directory.

1. Create folder `$JBOSS_HOME/modules/ibm/jdk/main` and create and insert a new
`module.xml` file into that folder.

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

3. Navigate to the `$JBOSS_HOME/modules/sun/jdk/main/module.xml` file and insert
the following path names inside the &lt;paths&gt;...<\/paths> element:

        <path name="com/sun/crypto" />
        <path name="com/sun/crypto/provider" />
        <path name="com/sun/image/codec/jpeg" />
        <path name="com/sun/org/apache/xml/internal/resolver" />
        <path name="com/sun/org/apache/xml/internal/resolver/tools" />

Next we'll consider the database and mail configuration. 

## Database Configuration

If you want JBoss to manage your data source, follow the instructions in this
section. If you want to use the built-in Liferay data source, you can skip this
section.

Modify `standalone.xml` adding your data source and driver within the
`<datasources>` element of your data sources subsystem.

1. First, add your data source within the `<datasources>` element.

        <datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
            <connection-url>jdbc:mysql://localhost/lportal</connection-url>
            <driver>mysql</driver>
            <security>
                <user-name>root</user-name>
                <password>root</password>
            </security>
        </datasource>

    Be sure to replace the URL database value (i.e. `lportal`), user value and
    password value with values specific to your database.

2. Then add your driver to the `<drivers>` element also found within the
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

Now that you've configured your data source, let's go over how to configure your
mail session within JBoss.

## Mail Configuration

If you want JBoss to manage your mail session, use the following instructions.
If you want to use the built-in Liferay mail session, you can skip this section.

Specify your mail subsystem  in `standalone.xml` as in the following example:

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
    
You've got mail! Next, we'll make sure Liferay is configured to properly connect
with your new mail session and database.

## Configuring data sources and mail sessions

Now that your data source and mail session are set up, you need to ensure
Liferay Portal can access them.

1. First, navigate to the Liferay Home folder, which is one folder above JBoss's
   install location (i.e. `$JBOSS_HOME/..`).

2. If you're using *JBoss* to manage your data source, add the following to your
   `portal-ext.properties` file in your *Liferay Home* to refer to your data
   source:

        jdbc.default.jndi.name=java:jdbc/LiferayPool

    If you're using *Liferay Portal* to manage your data source, follow the
    instructions in the *Deploy Liferay* section for using the setup wizard.

3. If you're using *Liferay Portal* to manage your mail session, this
   configuration is done within Liferay Portal. That is, after starting your
   portal as described in the *Deploy Liferay* section, go to *Control Panel
   &rarr; Server Administration &rarr; Mail* and enter the settings for your
   mail session.

    If you're using *JBoss* to manage your mail session, add the following to your
    `portal-ext.properties` file to reference that mail session:

        mail.session.jndi.name=java:mail/MailSession

You've completed the steps necessary for your deployment of Liferay so Liferay
Portal can now communicate with your data source and mail session--way to go!
Before we deploy Liferay Portal on your JBoss app server, we'll teach you how to
enable and configure Java security so you can begin using Liferay's plugin
security manager with your downloaded Liferay applications.

## Security Configuration

When you're ready to begin using other people's apps from Marketplace, you'll
want to protect your portal and your JBoss server from security threats. To do
so, you can enable Java Security on your JBoss server and specify a security
policy to grant your portal access to your server.

Remember, we set the `-Djava.security.manager` and `-Djava.security.policy` Java
options in the `standalone.conf.bat` file earlier in the *Configuring JBoss*
section. The `-Djava.security.manager` Java option enables security on your
JBoss server. Likewise, the `-Djava.security.policy` Java option lists the
permissions for your server's Java security policy. If you have not set these
options, you'll need to do so before using Java security.

For now, in order to grant Liferay access to your server let's open up all
permissions. You can tune the permissions in your policy later. Create the
`$JBOSS_HOME/bin/server.policy` file and add the following contents:

    grant {
        permission java.security.AllPermission;
    };

For extensive information on Java SE Security Architecture, see its
specification documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see section [*Understanding Plugin Security
Management*](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in Chapter 12 of the Developer's Guide to learn how to configure Liferay plugin
access to resources.

Now you're ready to deploy Liferay Portal.

## Deploy Liferay

1. If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists in
   your JBoss installation, delete all of its subfolders and files. Otherwise,
   create a new folder `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2. Unzip the Liferay `.war` file into the `ROOT.war` folder.

3. To trigger deployment of `ROOT.war`, create an empty file named
   `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder.
   On startup, JBoss detects the presence of this file and deploys it as a web
   application.

4. Remove `eclipselink.jar` from
   `$JBOSS_HOME/standalone/deployments/ROOT.war/WEB-INF/lib` to assure the
   Hibernate persistence provider is used instead of the one provided in the
   `eclipselink.jar`.

    Now it's time to start Liferay Portal on JBoss!

5.  Start the JBoss application server.
 
Now you are truly *the boss* when it comes to deploying Liferay Portal on JBoss!
