# Installing Liferay on Tomcat 8 [](id=installing-liferay-on-tomcat-8)

If you want a fresh installation of Liferay on Tomcat 8, simply download a
Liferay Tomcat bundle from
[https://www.liferay.com/downloads/liferay-portal/available-releases](https://www.liferay.com/downloads/liferay-portal/available-releases).
Even if you want to manually install Liferay on an existing Tomcat 8
application server, it can be helpful to download a Liferay Tomcat bundle. The
bundle contains many required dependencies and configuration files. Before
proceeding, you should also download the latest Liferay WAR file from
[https://web.liferay.com/downloads/liferay-portal/available-releases#additional-versions](https://web.liferay.com/downloads/liferay-portal/available-releases#additional-versions)
as well as the dependencies ZIP file and OSGi JARs ZIP file.

Installing Liferay manually requires these basic steps:

- Installing Liferay dependencies to your application server
- Configuring your application server for Liferay
- Installing the Liferay WAR file to your application server

You'll see the term *Liferay Home* used in this installation guide. *Liferay
Home* refers to the folder containing your Tomcat server folder. When Liferay
is installed on Tomcat, the Liferay Home folder contains the Tomcat server
folder as well as `data`, `deploy`, `license`, and `osgi` folders. You'll also
see the term `$TOMCAT_HOME` used in this guide. `$TOMCAT_HOME` refers to your
Tomcat server folder. This folder is usually named `tomcat-[version]` or
`apache-tomcat-[version]`.

## Installing Liferay Dependencies [](id=installing-liferay-dependencies)

Liferay depends on many JARs that are included in the Liferay Tomcat bundle.
Some JARs in the bundle are not strictly required but can still be useful. If
you don't have a Liferay Tomcat bundle, you can download the required JARs from
third-parties, as described below.

1. If you downloaded a Liferay Tomcat bundle, extract the bundle to a temporary
   location of your choosing. You'll copy a number of resources from this
   bundle to your Tomcat server as you manually install Liferay.

2. If you have a Liferay Tomcat bundle, copy all the JARs from your bundle's
   `$TOMCAT_HOME/lib/ext` folder to your application server's
   `$TOMCAT_HOME/lib/ext` folder. If the `$TOMCAT_HOME/lib/ext` folder doesn't
   exist on your application server, create it. If you don't have a Liferay
   Tomcat bundle, you'll have to individually download the JARs listed below.

    Here's a list of the JARs that you need to copy or download to your
    `$TOMCAT_HOME/lib/ext` folder:

    - `activation.jar` - [http://www.oracle.com/technetwork/java/jaf11-139815.html](http://www.oracle.com/technetwork/java/jaf11-139815.html)
    - `ccpp.jar` - [http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)
    - `com.liferay.osgi.service.tracker.collections.jar` - [http://mvnrepository.com/artifact/com.liferay/com.liferay.osgi.service.tracker.collections](http://mvnrepository.com/artifact/com.liferay/com.liferay.osgi.service.tracker.collections)
    - `com.liferay.registry.api.jar` - [https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.registry.api](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.registry.api)
    - `hsql.jar` - [http://hsqldb.org/doc/src/org/hsqldb/jdbc/JDBCDriver.html](http://hsqldb.org/doc/src/org/hsqldb/jdbc/JDBCDriver.html)
    - `jms.jar`- [http://www.oracle.com/technetwork/java/docs-136352.html](http://www.oracle.com/technetwork/java/docs-136352.html)
    - `jta.jar`- [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
    - `jutf7.jar` - [http://sourceforge.net/projects/jutf7](http://sourceforge.net/projects/jutf7)
    - `mail.jar` - [http://www.oracle.com/technetwork/java/index-138643.html](http://www.oracle.com/technetwork/java/index-138643.html)
    - `mysql.jar` - [http://dev.mysql.com/downloads/connector/j](http://dev.mysql.com/downloads/connector/j)
    - `persistence.jar`- [http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)
    - `portal-kernel.jar` - [http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.portal.kernel](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.portal.kernel)
    - `portlet.jar` - [http://mvnrepository.com/artifact/javax.portlet/portlet-api](http://mvnrepository.com/artifact/javax.portlet/portlet-api)
    - `postgresql.jar` - [https://jdbc.postgresql.org/download.html](https://jdbc.postgresql.org/download.html)
    - `support-tomcat.jar` - [http://repo1.maven.org/maven2/com/liferay/portal/support-tomcat](http://repo1.maven.org/maven2/com/liferay/portal/support-tomcat)

3. Make sure that Tomcat can access the JDBC driver for your database. The list
   of JARs above includes `mysql.jar` and `postgresql.jar`. If you're using a
   database whose JDBC driver is not included in the list above, download the
   driver and copy it to your `$TOMCAT_HOME/lib/ext` folder.

4. Create an `osgi` folder in your Liferay Home folder. Then extract the OSGi
   ZIP file that you downloaded into the `osgi` folder.

    Liferay requires an OSGi runtime, and the `osgi` folder provides this with
    many required JAR files and configuration files.

## Tomcat Configuration [](id=tomcat-configuration)

Next, you need to configure Tomcat for running Liferay.

1. If you're working with a bundle, copy the `setenv.bat` and `setenv.sh` files
   from your bundle to your `$TOMCAT_HOME/bin` folder. If not, create these
   files. `setenv.bat` looks like this:

        if exist "%CATALINA_HOME%/jre1.6.0_20/win" (
            if not "%JAVA_HOME%" == "" (
                set JAVA_HOME=
            )

            set "JRE_HOME=%CATALINA_HOME%/jre1.6.0_20/win"
        )

        set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true  -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=384m"

    `setenv.sh` looks like this:

        CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true  -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=384m"

These files set a number of JVM options for Catalina. Catalina is Tomcat's
servlet container.

2. If you're working with a bundle, copy the
   `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml` file from your bundle to the
   corresponding location in your application server. If not, create this file.
   The `ROOT.xml` file creates a web application context for Liferay.
   `ROOT.xml` looks like this:

        <Context path="" crossContext="true">

            <!-- JAAS -->

            <!--<Realm
                className="org.apache.catalina.realm.JAASRealm"
                appName="PortalRealm"
                userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
                roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
            />-->

            <!--
            Uncomment the following to disable persistent sessions across reboots.
            -->

            <!--<Manager pathname="" />-->

            <!--
            Uncomment the following to not use sessions. See the property
            "session.disabled" in portal.properties.
            -->

            <!--<Manager className="com.liferay.support.tomcat.session.SessionLessManagerBase" />-->
        </Context>

    Setting `crossContext="true"` allows multiple web applications to use the
    same class loader. The configuration above includes commented instructions
    and tags for configuring a JAAS realm, disabling persistent sessions, and
    disabling sessions entirely.

3. Next, you should make sure that the libraries you copied to
   `$TOMCAT_HOME/lib/ext` are loaded when you start your server. If you're
   working with a bundle, copy the `$TOMCAT_HOME/conf/catalina.properties` file
   from your bundle to your server. If not, open
   `$TOMCAT_HOME/conf/catalina.properties` and replace the line

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar

    with this one:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar

    This allows Catalina to access the JARs that you copied to
    `$TOMCAT_HOME/lib/ext`.

4. If you're working with a bundle, copy the
   `$TOMCAT_HOME/conf/catalina.policy` file from your bundle to your server. If
   not, just replace the contents of the `$TOMCAT_HOME/conf/catalina.policy`
   file with this:

        grant {
            permission java.security.AllPermission;
        };

    If you want to enable PACL for Liferay, you have to enable Tomcat's
    security manager and instruct Catalina to use the
    `$TOMCAT_HOME/conf/catalina.policy` file. See the Enabling PACL section for
    more information.

5. Next, you should make sure that UTF-8 URI encoding is used consistently. If
   you're working with a bundle, copy the `$TOMCAT_HOME/conf/server.xml` file
   to your server. If not, you can simply make a few edits to `server.xml`.
   Edit your `$TOMCAT_HOME/conf/server.xml` file and add the attribute
   `URIEncoding="UTF-8"` wherever you see `redirectPort=8443`, in the
   definition of your connectors (HTTP and AJP). For example:

        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />

    should become

        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

    And

        <Connector port="8080" protocol="HTTP/1.1"
                   connectionTimeout="20000"
                   redirectPort="8443" />

    should become

        <Connector port="8080" protocol="HTTP/1.1"
                   connectionTimeout="20000"
                   redirectPort="8443" URIEncoding="UTF-8" />

6. If you're on Unix, Linux, or Mac OS, navigate to your `$TOMCAT_HOME/bin`
   folder and run the following command

        chmod a+x *.sh

    This command makes the shell scripts in Tomcat's `bin` folder executable.

## Tomcat Database Configuration [](id=tomcat-database-configuration)

The easiest way to handle your database configuration is to let Liferay manage
your data source. If you want to use Liferay's built-in data source, you can
skip this section. When you first Liferay, you can enter the required database
configuration information on the Basic Configuration page.

If you want Tomcat to manage your data source, use this procedure:

1. Make sure your database server is installed and working. If it's installed
   on a different machine, make sure it's accessible from your Liferay machine.

2. Add your data source as a resource in the context of your web application
   specified in `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml`:

        <Context...>
            <Resource
                name="jdbc/LiferayPool"
                auth="Container"
                type="javax.sql.DataSource"
                driverClassName="com.mysql.jdbc.Driver"
                url="jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8"
                username="root"
                password="root"
                maxActive="100"
                maxIdle="30"
                maxWait="10000"
            />
        </Context>

Note that the above resource definition assumes your database name is
*lportal*, that you're using MySQL, and that your MySQL username and password
are both *root*. You'll have to update these values with your own database name
and credentials.

Your Tomcat managed data source is now configured. Next, let's consider your
mail session.

## Tomcat Mail Configuration [](id=tomcat-mail-configuration)

As with database configuration, the easiest way to handle mail configuration is
to let Liferay handle your mail session. If you want to use Liferay's built-in
mail session, skip this section and use Liferay's Control Panel to configure a
mail server after Liferay has been installed and started.

If you want to manage your mail session with Tomcat, use these instructions:

To create a mail session bound to `mail/MailSession`, edit `$TOMCAT_
HOME/conf/Catalina/localhost/ROOT.xml` and configure your mail session. Make
sure to replace the example mail session values with your own.

    <Context...>
        <Resource
            name="mail/MailSession"
            auth="Container"
            type="javax.mail.Session"
            mail.pop3.host="pop.gmail.com"
            mail.pop3.port="110"
            mail.smtp.host="smtp.gmail.com"
            mail.smtp.port="465"
            mail.smtp.user="user"
            mail.smtp.password="password"
            mail.smtp.auth="true"
            mail.smtp.starttls.enable="true"
            mail.smtp.socketFactory.class="javax.net.ssl.SSLSocketFactory"
            mail.imap.host="imap.gmail.com"
            mail.imap.port="993"
            mail.transport.protocol="smtp"
            mail.store.protocol="imap"
        />
    </Context>

Your mail session is configured. Next, you'll make sure Liferay can 
access your mail session and database.

## Configuring Tomcat-managed Database and Mail Sessions [](id=configuring-tomcat-managed-database-and-mail-sessions)

In this section, you'll specify appropriate properties for connecting to your
database and mail session.

1. If you will use *Liferay Portal* to manage your data source, simply follow
   the instructions on the Basic Configuration page that appears when you first
   start Liferay.

    If you are using *Tomcat* to manage your data source, add the following
    line to your `portal-ext.properties` file in your *Liferay Home* folder to
    specify your data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

2. If you will use *Liferay Portal* to manage your mail session, you can
   configure the mail session once Liferay has started. That is, after starting
   your portal as described in the *Deploying Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the information
   required to configure your mail session.

    If you are using *Tomcat* to manage your mail session, add the following
    configuration to your `portal-ext.properties` file to reference that mail
    session:

        mail.session.jndi.name=mail/MailSession

It's just that easy! Before you deploy Liferay Portal, you should configure
Portal Access Control Language (PACL) with Liferay on Tomcat.

## Enabling PACL [](id=enabling-pacl)

To enable PACL, you need to enable Tomcat's security manager. In the Tomcat
Configuration section above, you already added the required permissions to the
Tomcat policy configuration file, `catalina.policy`.

- Edit your `$TOMCAT_HOME/bin/setenv.sh` (if on Linux, Unix, or Mac OS) or
  `setenv.bat` (if on Windows) and enable the security manager by inserting the
  following code into the `CATALINA_OPTS` variable (inside the quotation
  marks):

    `-Djava.security.manager -Djava.security.policy=$CATALINA_BASE/conf/catalina.policy`

- Check that your `$TOMCAT_HOME/conf/catalina.policy` file specifies the
  required permissions (you should have already addressed this in the
  Configuring Tomcat section):

        grant {
            permission java.security.AllPermission;
        };

To enable the security manager on Tomcat, the server must be started with the
`-security` command line option. Shut down your Tomcat instance and then
restart it with the following command:

    ./startup.sh -security

Tomcat reports the message `Using Security Manager` to your terminal.

Now you have PACL enabled and configured for your portal.

## Adding Mojarra [](id=adding-mojarra)

If you'd like to use JSF applications in your Tomcat application server, you'll
need to add Mojarra. If you do not plan on using JSF applications in your
application server, you can skip this section.

The typical binary ZIP version of Tomcat does not contain any JSF runtime JARs
like Mojarra or MyFaces. This is because Tomcat is not a Java EE Application
Server like Oracle GlassFish, Oracle WebLogic, JBoss AS, or IBM WebSphere.
Also, Tomcat is not a Java EE Web Profile Server like Apache TomEE or Caucho
Resin.

There are two ways to approach using Mojarra with Tomcat: upgrading Tomcat's
context classpath or upgrading Tomcat's global classpath. Both methods require
adding/editing two JARs, which can be downloaded below:

- [`jsf-api`](https://maven.java.net/content/repositories/releases/com/sun/faces/jsf-api/2.1.21/)
- [`jsf-impl`](https://maven.java.net/content/repositories/releases/com/sun/faces/jsf-impl/2.1.21/)

The typical approach for using Mojarra with Tomcat is to include `jsf-api.jar`
and `jsf-impl.jar` in the `WEB-INF/lib` folder in each JSF project. You can do
this by specifying the `jsf-api` and `jsf-impl` artifacts without a scope, or
with the scope set as `compile` (the default) in each JSF project:

    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-api</artifactId>
        <version>2.1.21</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-impl</artifactId>
        <version>2.1.21</version>
        <scope>compile</scope>
    </dependency>

Although it is possible to install Mojarra in the Tomcat global classpath, it
will not work properly without some small modifications to the `jsf-impl.jar`
dependency. The problem stems from the fact that the Mojarra
[`ConfigureListener`](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/src/main/java/com/sun/faces/config/ConfigureListener.java)
class is automatically registered for all contexts under `tomcat/webapps`
because it is specified as a `<listener>` in the
[META-INF/jsf-jsf_core.tld](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/conf/share/jsf_core.tld)
descriptor inside the `jsf-impl.jar` dependency.
Additionally, the
[META-INF/services/javax.faces.ServletContainerInitializer](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/conf/share/javax.servlet.ServletContainerInitializer)
will cause the
[FacesInitializer](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/src/main/java/com/sun/faces/config/FacesInitializer.java)
class to auto-register the `ConfigureListener` as well. Consequently, every
request issued in all contexts invokes the Mojarra `ConfigureListener`. This
can be a potential performance problem in a webapp environment and causes
incompatibilities with a portlet environment. Therefore, it is necessary to
disable automatic registration of the Mojarra `ConfigureListener` by modifying
the contents of the `jsf-impl.jar` dependency.

To upgrade Tomcat's global classpath, follow the steps below:

1. Copy `jsf-api.jar` and `jsf-impl.jar` to the `tomcat/lib` folder.

2. Open a terminal window and navigate to the `tomcat/lib` folder:

        cd tomcat/lib

3. Create a temporary folder named `jsf-impl` and navigate into it:

        mkdir jsf-impl
        cd jsf-impl

4. Extract the Mojarra `jsf-impl.jar` dependency into the temporary folder:

        jar xf ../jsf-impl-2.1.21.jar

        5. Open the `META-INF/jsf_core.tld` file and remove the following lines:

        <listener>
            <listener-class>com.sun.faces.config.ConfigureListener</listener-class>
        </listener>

6. Remove the Mojarra servlet container initializer:

        rm META-INF/services/javax.servlet.ServletContainerInitializer

7. Overwrite the Mojarra `jsf-impl.jar` dependency by creating a new archive:

        jar cf ../jsf-impl-2.1.21.jar META-INF/ com/

8. Remove the temporary folder:

        cd ../
        rm -rf jsf-impl/

9. Follow only *one* of the following sub-steps below, depending on preference.

    9.1 Specify the `liferay-faces-init.jar` dependency in each JSF project in
    order for the Mojarra `ConfigureListener` to be automatically started by
    Tomcat:

        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>liferay-faces-init</artifactId>
            <version>3.1.3-ga4</version>
        </dependency>

    9.2 Specify the Mojarra `ConfigureListener` as a listener in the
    `WEB-INF/web.xml` descriptor in each JSF project:

        <listener>
            <listener-class>com.sun.faces.config.ConfigureListener</listener-class>
        </listener>

10. Specify the `jsf-api` and `jsf-impl` dependencies as provided in each JSF
   project:

        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-api</artifactId>
            <version>2.1.21</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-impl</artifactId>
            <version>2.1.21</version>
            <scope>provided</scope>
        </dependency>

You've officially added Mojarra to your application server.

If you're interested in configuring CDI for your JSF portlets running on Tomcat,
you'll also need to configure Weld. For more information on configuring Weld for
Tomcat, visit the
[Configuring JSF Portlets to Use CDI](/develop/tutorials/-/knowledge_base/6-2/contexts-and-dependency-injection-for-jsf-portlets#configuring-jsf-portlets-to-use-cdi)
section.

## Deploying Liferay [](id=deploying-liferay)

Now you're ready to deploy Liferay using your Liferay WAR file.

1. If you are manually installing Liferay on a clean Tomcat server, delete the
   contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This removes the default
   Tomcat home page.

2. Extract the Liferay `.war` file to `$TOMCAT_HOME/webapps/ROOT`.

    Now it's time to launch Liferay Portal on Tomcat!

3. Start Tomcat by navigating to `$TOMCAT_HOME/bin` and executing `./startup.sh`
   or `startup.bat`. Alternatively, you can use `./catalina.sh run` or
   `catalina.bat run`. Using one of the latter commands makes your terminal or
   command prompt tail Liferay's log file. This can be useful if you want to
   see the startup activities performed by Liferay.

Congratulations on successfully installing and deploying Liferay on Tomcat!
