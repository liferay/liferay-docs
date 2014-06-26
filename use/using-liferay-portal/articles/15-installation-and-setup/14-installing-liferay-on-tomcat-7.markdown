# Installing Liferay on Tomcat 7 [](id=installing-liferay-on-tomcat-7-lp-6-2-use-useportal)

*Liferay Home* is one folder above Tomcat's install location.

For this section, we will refer to your Tomcat server's installation location as
`$TOMCAT_HOME`. If you do not already have an existing Tomcat server, we
recommend you download a Liferay/Tomcat bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing Tomcat server or would like to install Liferay on Tomcat
manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.2.x-[date].war` and
the dependencies file should be called
`liferay-portal-dependencies-6.2.x-[date].zip`.

Next, let's get started by addressing Liferay's library dependencies.

## Dependency Jars

Liferay Portal depends on several `JAR` files found in the Liferay
Dependencies Archive. In addition to these, you need the proper driver for your
database. You can get the necessary dependencies by following these steps:

1. Create a folder named `ext` in `$TOMCAT_HOME/lib`.

2. Unzip the Liferay Dependencies file to `$TOMCAT_HOME/lib/ext` so that its
`.jar` files reside there. If the `.jar` files are nested in a
`liferay-portal-dependencies-6.2` directory after you extract them, copy them
into `$TOMCAT_HOME/lib/ext` and delete the empty folder.

3. Next, you need a few third party `.jar` files which are included as part of the
   Liferay source distribution, but are not automatically included with Tomcat.
   You'll have to download them yourself, so let's get started. Place these
   `.jar` files into `$TOMCAT_HOME/lib/ext`:

    - `jta.jar`: You can get this `.jar`, which manages transactions, from
            [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
    - `mail.jar`: You can get this `.jar` from
            [http://www.oracle.com/technetwork/java/index-138643.html](http://www.oracle.com/technetwork/java/index-138643.html)
    - `persistence.jar`: You can learn about the Java Persistence API and how
      to download it from
            [http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)

4. Make sure the JDBC driver for your database is accessible by Tomcat. In the
case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download
the latest MySQL JDBC driver from
[http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/).
Extract the JAR file and copy it to `$TOMCAT_HOME/lib/ext`.

5. There are a few other JARs that come with a typical Liferay bundle that you
   might want to download and place in your `$TOMCAT_HOME/lib/ext` folder. They
   include the following:

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

    You can download each third partry `.jar` listed above from the provided
    websites, then place them into your `%TOMCAT_HOME/lib/ext` directory.  However,
    they're also available in the Liferay source code, so if you have access to the
    Liferay source or would like to download it for this purpose, feel free to copy
    the `.jar` files from there. If we refer to your local Liferay source
    directory as `$LIFERAY_SOURCE`, you can get all of the third party `.jar` files
    listed above from `$LIFERAY_SOURCE/lib/development`, with the exception of
    `ccpp.jar`, which is found in `$LIFERAY_SOURCE/lib/portal`.

Now that you have the necessary libraries in place, we'll move on to
configuring your domain.

## Tomcat Configuration

There are several configuration steps you need to complete before Tomcat can
run Liferay. Let's get started.

1. First we'll need to set the `CATALINA_OPTS` environment variable. Create a
`setenv.bat` (Windows) or `setenv.sh` file (Unix, Linux, Mac OS) in the
`$TOMCAT_HOME/bin` directory. Populate it with following contents:

    - `setenv.bat`:

            if exist "%CATALINA_HOME%/jre@java.version@/win" (
                if not "%JAVA_HOME%" == "" (
                    set JAVA_HOME=
                )

                set "JRE_HOME=%CATALINA_HOME%/jre@java.version@/win"
            )

            set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    - `setenv.sh`:

            CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    This sets the character encoding to UTF-8, sets the time zone to Greenwich
    Mean Time, and allocates memory to the Java Virtual Machine.

<!--Th setenv.bat and .sh are slightly different from 6.1, I took it from
setenv.sh and .bat in a built liferay 6.2.--> 

2. Let's create a context for Liferay. Create a `ROOT.xml` file in
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
    loader. In the content above you will also find commented instructions and
    tags for configuring a JAAS realm, disabling persistent sessions and
    disabling sessions in general.

3. Let's make sure the libraries we added to `$TOMCAT_HOME/lib/ext` are loaded
when we start the server. Open `$TOMCAT_HOME/conf/catalina.properties` and
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

## Database Configuration

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
	
    ---

    ![note](../../images/01-tip.png) **Note:** The above resource definition
    assumes your database name is *lportal* and your MySQL username and password
    are both *root*. You'll have to update these values with your own database name
    and credentials.

    ---

Your Tomcat managed data source is now configured. Let's move on to your mail
session.

## Mail Configuration

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

Your mail session is configured. Next, we'll make sure Liferay will be able to
access your mail session and database.

## Configuring your database and mail session

In this section we'll specify appropriate properties for Liferay to use in
connecting to your database and mail session.

1. If you are using *Tomcat* to manage your data source, add the following to
   your `portal-ext.properties` file in your *Liferay Home* to refer to your
   data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source, follow
    the instructions in the *Deploy Liferay* section for using the setup wizard.

2. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session within Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

    Otherwise, if you are using *Tomcat* to manage your mail session, add the
    following to your `portal-ext.properties` file to reference that mail session:

        mail.session.jndi.name=mail/MailSession

It's just that easy! Before we deploy Liferay Portal on your Tomcat server,
let's look at configuring Portal Access Control Language (PACL) with Liferay on
Tomcat. 

## Enabling PACL

To enable PACL for use with your Liferay Portal when running on Tomcat, you
need to enable the security manager and add some required permissions to the
server policy configuration file. This simply entails editing two files in
`$TOMCAT_HOME` you're already familiar with if you've been following along in
setting up Liferay with a Tomcat Application Server: 

- In `$TOMCAT_HOME/bin/setenv.sh` (if on Linux, Unix, or Mac OS) or
  `setenv.bat` (if on Windows) enable the security manager by inserting the
following code into the `CATALINA_OPTS` variable (inside the quotation marks):

    `-Djava.security.manager -Djava.security.policy=$CATALINA_BASE/conf/catalina.policy`

- In `$TOMCAT_HOME/conf/catalina.policy`, add the required permissions:

        grant {
            permission java.security.AllPermission;
        };

To enable the security manager on Tomcat, the server must be started with the
`-security` commandline options. Shutdown your Tomcat instance and restart it
with the following command: 

    ./startup.sh -security

Tomcat reports the message `Using Security Manager` to your terminal. 

Now you have PACL enabled and configured for your portal. Let's deploy Liferay!

## Deploy Liferay

It's time to deploy Liferay as an exploded web archive within your
`$TOMCAT_HOME/webapps` folder, configure our setup wizard behavior, and start
Liferay!

1. If you are manually installing Liferay on a clean Tomcat server, delete the
contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This undeploys the
default Tomcat home page. Extract the Liferay `.war` file to
`$TOMCAT_HOME/webapps/ROOT`.

    Now its time to launch Liferay Portal on Tomcat!

3. Start Tomcat by executing `$TOMCAT_HOME/bin/startup.bat` or
`$TOMCAT_HOME/bin/startup.sh`.

Congratulations on successfully installing and deploying Liferay on Tomcat!
