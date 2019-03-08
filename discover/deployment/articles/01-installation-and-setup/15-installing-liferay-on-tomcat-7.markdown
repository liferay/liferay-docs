# Installing Liferay on Tomcat 7 [](id=installing-liferay-on-tomcat-7)

*Liferay Home* is one folder above Tomcat's install location.

For this section, we will refer to your Tomcat server's installation location as
`$TOMCAT_HOME`. If you do not already have an existing Tomcat server, we
recommend you download a Liferay/Tomcat bundle from
[http://www.liferay.com/downloads](http://www.liferay.com/downloads).
If you have an existing Tomcat server or would like to install Liferay on Tomcat
manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads#additional-files](http://www.liferay.com/downloads#additional-files).
The Liferay `.war` file should be called `liferay-portal-6.2.x-[date].war` and
the dependencies file should be called
`liferay-portal-dependencies-6.2.x-[date].zip`.

Next, let's get started by addressing Liferay's library dependencies.

## Dependency Jars [](id=dependency-jars)

Liferay Portal depends on several `JAR` files found in the Liferay
Dependencies Archive. In addition to these, you need the proper driver for your
database. You can get the necessary dependencies by following these steps:

1. Create a folder named `ext` in `$TOMCAT_HOME/lib`.

2. Unzip the Liferay Dependencies and copy the .jar files to
`$TOMCAT_HOME/lib/ext`. 

3. Download the `support-tomcat.jar` file from
   [http://search.maven.org/#artifactdetails|com.liferay.portal|support-tomcat|6.2.1|jar](http://search.maven.org/#artifactdetails|com.liferay.portal|support-tomcat|6.2.1|jar)
   and copy it into your `$TOMCAT_HOME/lib/ext` directory. This `JAR` provides
   classes that extend some Tomcat-specific classes in order to support
   Liferay's runtime.

4. Next, you need to download a few third party `.jar` files that are included
   as part of the Liferay source distribution, but are not automatically included
   with Tomcat. Place these `.jar` files into `$TOMCAT_HOME/lib/ext`:

    - `jta.jar`: You can get this `.jar`, which manages transactions, from
            [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
    - `mail.jar`: You can get this `.jar` from
            [http://www.oracle.com/technetwork/java/index-138643.html](http://www.oracle.com/technetwork/java/index-138643.html)
    - `persistence.jar`: You can learn about the Java Persistence API and how
      to download it from
            [http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)

5. Make sure the JDBC driver for your database is accessible by Tomcat. In the
case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download
the latest MySQL JDBC driver from
[http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/).
Extract the JAR file and copy it to `$TOMCAT_HOME/lib/ext`.

6. There are a few other JARs that come with a typical Liferay bundle that you
   might want to download and place in your `$TOMCAT_HOME/lib/ext` folder. They
   include these:

    - `activation.jar`: You can get this `.jar` from
        [http://www.oracle.com/technetwork/java/jaf11-139815.html](http://www.oracle.com/technetwork/java/jaf11-139815.html)
    - `ccpp.jar`: You can get this `.jar` from 
        [http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)
    - `jms.jar`: You can get this `.jar` from
        [http://www.oracle.com/technetwork/java/docs-136352.html](http://www.oracle.com/technetwork/java/docs-136352.html)
    - `jutf7.jar`: You can get this `.jar` from 
          [http://sourceforge.net/projects/jutf7/](http://sourceforge.net/projects/jutf7/)
    - `junit.jar`: You can get this `.jar` from 
        [http://sourceforge.net/projects/junit/](http://sourceforge.net/projects/junit/)

    You can download each third party `.jar` listed above from the provided
    websites, then place them into your `%TOMCAT_HOME/lib/ext` directory. However,
    they're also available in the Liferay source code, so if you have access to the
    Liferay source or would like to download it for this purpose, feel free to copy
    the `.jar` files from there. Assuming your local Liferay source
    directory is `$LIFERAY_SOURCE`, you can get all the third party `.jar` files
    listed above from `$LIFERAY_SOURCE/lib/development`, with the exception of
    `ccpp.jar`, which is found in `$LIFERAY_SOURCE/lib/portal`.

Now that you have the necessary libraries in place, we'll move on to
configuring your domain.

## Tomcat Configuration [](id=tomcat-configuration)

There are several configuration steps you need to complete before Tomcat can
run Liferay. Let's get started.

1. First, you'll need to set the `CATALINA_OPTS` environment variable. Create a
`setenv.bat` (Windows) or `setenv.sh` file (Unix, Linux, Mac OS) in the
`$TOMCAT_HOME/bin` directory. Populate it with following contents:

    - `setenv.bat`:

            if exist "%CATALINA_HOME%/jre@java.version@/win" (
                if not "%JAVA_HOME%" == "" (
                    set JAVA_HOME=
                )

                set "JRE_HOME=%CATALINA_HOME%/jre@java.version@/win"
            )

            set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    - `setenv.sh`:

            CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    This sets the character encoding to UTF-8, sets the time zone to Greenwich
    Mean Time, and allocates memory to the Java Virtual Machine.

2. Next, create a context for Liferay. Create a `ROOT.xml` file in
   `$TOMCAT_HOME/conf/Catalina/localhost`. Populate it with the following contents
   to set up a portal web application:

        <Context path="" crossContext="true">

            <!-- JAAS -->

            <!--<Realm
                classNjame="org.apache.catalina.realm.JAASRealm"
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
        
    Setting `crossContext="true"` allows multiple web apps to use the same class
    loader. In the configuration above you will also find commented instructions and
    tags for configuring a JAAS realm, disabling persistent sessions and
    disabling sessions in general.

3. Next, make sure the libraries you added to `$TOMCAT_HOME/lib/ext` are loaded
when you start the server. Open `$TOMCAT_HOME/conf/catalina.properties` and
replace the line:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar

    with:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar
 
    This allows Catalina to access the dependency jars you extracted to
    `$TOMCAT_HOME/lib/ext`.

4. We also need to ensure consistent use of UTF-8 URI Encoding. Edit
`$TOMCAT_HOME/conf/server.xml` and add the attribute `URIEncoding="UTF-8"`
where you see `redirectPort=8443`, in the definition of your connectors (HTTP
and AJP). For example:

        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

5. Lastly, if you see a `support-catalina.jar` in your `$TOMCAT_HOME/webapps`
directory, delete it.

Now Tomcat is configured to run Liferay! If you want to use Liferay to manage
your database and mail session (and we recommend you do), you can skip the next
sections and move to the section titled *Enabling PACL*. Next we'll look at
configuring your database with Tomcat.

## Database Configuration [](id=database-configuration)

If you want Tomcat to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

1. Make sure your database server is installed and working. If it's installed
on a different machine, make sure it's accessible from the machine with
Liferay.

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

+$$$

**Note:** The above resource definition assumes your database name is *lportal*
and your MySQL username and password are both *root*. You'll have to update
these values with your own database name and credentials.

$$$

Your Tomcat managed data source is now configured. Next is your mail
session.

## Mail Configuration [](id=mail-configuration)

If you want to manage your mail session with Tomcat, use the following
instructions. If you want to use the built-in Liferay mail session, you can
skip this section.

Create a mail session bound to `mail/MailSession`. Edit `$TOMCAT_
HOME/conf/Catalina/localhost/ROOT.xml` and configure a mail session. Be sure to
replace the example mail session values with your own.

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

## Configuring your database and mail session [](id=configuring-your-database-and-mail-session)

In this section you'll specify appropriate properties for connecting to your
database and mail session.

1. If you are using *Tomcat* to manage your data source, add the following
   configuration to your `portal-ext.properties` file in your *Liferay Home* to
   refer to your data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source, follow
    the instructions for using the setup wizard.

2. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session in Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

    Otherwise, if you are using *Tomcat* to manage your mail session, add the
    following configuration to your `portal-ext.properties` file to reference
    that mail session:

        mail.session.jndi.name=mail/MailSession

It's just that easy! Before you deploy Liferay Portal, you should configure
Portal Access Control Language (PACL) with Liferay on Tomcat. 

## Enabling PACL [](id=enabling-pacl)

To enable PACL, you need to enable the security manager and add some required
permissions to the server policy configuration file. This entails editing
two files in `$TOMCAT_HOME` you've already edited: 

- In `$TOMCAT_HOME/bin/setenv.sh` (if on Linux, Unix, or Mac OS) or
  `setenv.bat` (if on Windows) enable the security manager by inserting the
following code into the `CATALINA_OPTS` variable (inside the quotation marks):

    `-Djava.security.manager -Djava.security.policy=$CATALINA_BASE/conf/catalina.policy`

- In `$TOMCAT_HOME/conf/catalina.policy`, add the required permissions:

        grant {
            permission java.security.AllPermission;
        };

To enable the security manager on Tomcat, the server must be started with the
`-security` command line options. Shutdown your Tomcat instance and restart it
with the following command: 

    ./startup.sh -security

Tomcat reports the message `Using Security Manager` to your terminal. 

Now you have PACL enabled and configured for your portal. 

## Adding Mojarra [](id=adding-mojarra)

If you'd like to use JSF applications in your Tomcat application server, you'll
need to add Mojarra. If you do not plan on using JSF applications in your
application server, you can skip this section. 

The typical binary ZIP version of Tomcat does not contain any JSF runtime JARs
like Mojarra or MyFaces. This is because Tomcat is not a Java EE Application
Server like Oracle GlassFish, Oracle WebLogic, JBoss AS, or IBM WebSphere. Also,
Tomcat is not a Java EE Web Profile Server like Apache TomEE or Caucho Resin. 

There are two ways to approach using Mojarra with Tomcat: upgrading Tomcat's
context classpath or upgrading Tomcat's global classpath. Both methods require
adding/editing two JARs, which can be downloaded below: 

- [`jsf-api`](https://maven.java.net/content/repositories/releases/com/sun/faces/jsf-api/2.1.29-08/)
- [`jsf-impl`](https://maven.java.net/content/repositories/releases/com/sun/faces/jsf-impl/2.1.29-08/)

The typical approach for using Mojarra with Tomcat is to include `jsf-api.jar`
and `jsf-impl.jar` in the `WEB-INF/lib` folder in each JSF project. You can do
this by specifying the `jsf-api` and `jsf-impl` artifacts without a scope, or
with the scope set as `compile` (the default) in each JSF project: 

    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-api</artifactId>
        <version>2.1.29-08</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-impl</artifactId>
        <version>2.1.29-08</version>
        <scope>compile</scope>
    </dependency>

Although it is possible to install Mojarra in the Tomcat global classpath, it
will not work properly without some small modifications to the `jsf-impl.jar`
dependency. The problem stems from the fact that the Mojarra
[`ConfigureListener`](https://github.com/javaserverfaces/mojarra/blob/2.1.29-08/jsf-ri/src/main/java/com/sun/faces/config/ConfigureListener.java)
class is automatically registered for all contexts under `tomcat/webapps`
because it is specified as a `<listener>` in the
[META-INF/jsf-jsf_core.tld](https://github.com/javaserverfaces/mojarra/blob/2.1.29-08/jsf-ri/conf/share/jsf_core.tld)
descriptor inside the `jsf-impl.jar` dependency.
Additionally, the
[META-INF/services/javax.faces.ServletContainerInitializer](https://github.com/javaserverfaces/mojarra/blob/2.1.29-08/jsf-ri/conf/share/javax.servlet.ServletContainerInitializer)
will cause the
[FacesInitializer](https://github.com/javaserverfaces/mojarra/blob/2.1.29-08/jsf-ri/src/main/java/com/sun/faces/config/FacesInitializer.java)
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

        jar xf ../jsf-impl-2.1.29-08.jar

5. Open the `META-INF/jsf_core.tld` file and remove the following lines: 

        <listener>
            <listener-class>com.sun.faces.config.ConfigureListener</listener-class>
        </listener>

6. Remove the Mojarra servlet container initializer: 

        rm META-INF/services/javax.servlet.ServletContainerInitializer

7. Overwrite the Mojarra `jsf-impl.jar` dependency by creating a new archive: 

        jar cf ../jsf-impl-2.1.29-08.jar META-INF/ com/

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
            <version>3.2.4-ga5</version>
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
            <version>2.1.29-08</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-impl</artifactId>
            <version>2.1.29-08</version>
            <scope>provided</scope>
        </dependency>

You've officially added Mojarra to your application server. 

If you're interested in configuring CDI for your JSF portlets running on Tomcat,
you'll also need to configure Weld. For more information on configuring Weld for
Tomcat, visit the
[Configuring JSF Portlets to Use CDI](/develop/tutorials/-/knowledge_base/6-2/contexts-and-dependency-injection-for-jsf-portlets#configuring-jsf-portlets-to-use-cdi)
section. 

You're now all set to deploy Liferay. 

## Deploy Liferay [](id=deploy-liferay)

It's time to deploy Liferay as an exploded web archive within your
`$TOMCAT_HOME/webapps` folder. 

1. If you are manually installing Liferay on a clean Tomcat server, delete the
   contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This removes the default
   Tomcat home page. Extract the Liferay `.war` file to
   `$TOMCAT_HOME/webapps/ROOT`.

    Now its time to launch Liferay Portal on Tomcat!

2. Start Tomcat by executing `$TOMCAT_HOME/bin/startup.bat` or
   `$TOMCAT_HOME/bin/startup.sh`.

Congratulations on successfully installing and deploying Liferay on Tomcat!
