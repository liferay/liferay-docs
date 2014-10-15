# Installing Liferay on Tomcat 7 [](id=installing-liferay-on-tomcat-7)

**Liferay Home** is one folder above Tomcat's install location.

For this section, we will refer to your Tomcat server's installation location as
`$TOMCAT_HOME`. If you do not already have an existing Tomcat server, we
recommend you download a Liferay/Tomcat bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing Tomcat server or would like to install Liferay on Tomcat
manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.1.x-<date>.zip`.

Next, let's get started by addressing Liferay's library dependencies.

## Dependency Jars [](id=dependency-jars)

Liferay Portal needs to have the Liferay Portal Dependency JARs, an appropriate
JDBC driver and a few other JARs installed.

1. Create folder `$TOMCAT_HOME/lib/ext`.

2. Extract the Liferay dependencies file to `$TOMCAT_HOME/lib/ext`. If the files
   do not extract to this directory, you can copy the dependencies archive to
   this directory, extract them and then delete the archive.

3. Next, you need several `.jar` files which are included as part of the Liferay
   source distribution. Many application servers ship with these already on the
   class path but Tomcat does not. The best way to get the appropriate versions
   of these files is to download the Liferay source code and get them from
   there. Once you have downloaded the Liferay source, unzip the source into a
   temporary folder. We'll refer to the location of the Liferay source as
   `$LIFERAY_SOURCE`.

    1. Copy the following jars from `$LIFERAY_SOURCE/lib/development` to your
	   `$TOMCAT_HOME/lib/ext` folder:
        - `activation.jar`
        - `jms.jar`
        - `jta.jar`
        - `jutf7.jar`
        - `mail.jar`
        - `persistence.jar`
    2. Copy the following jar from `$LIFERAY_SOURCE/lib/portal` to your
	  `$TOMCAT_HOME/lib/ext` folder:
        - `ccpp.jar`
        - *Note:* Tomcat 6 users should *not* copy the `ccpp.jar` file into
        their `$TOMCAT_HOME/lib/ext` folder and should delete it from this
        folder if it already exists.
    3. Copy the following jars from `$LIFERAY_SOURCE/lib/development` to your
	   `$TOMCAT_HOME/temp/liferay/com/liferay/portal/deploy/dependencies`
        folder:
        - `resin.jar`
        - `script-10.jar`

4. Make sure the JDBC driver for your database is accessible by Tomcat. Obtain
   the JDBC driver for your version of the database server. In the case of
   MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download the
   latest MySQL JDBC driver from
   [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/).
   Extract the JAR file and copy it to `$TOMCAT_HOME/lib/ext`.

5. Liferay requires an additional .jar on Tomcat installations to manage
   transactions. This is included in the bundle but you need to add it if you're
   installing Liferay manually. You may find this .jar here:
   [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html).
   Place this file in Tomcat's lib/ext folder. 

Now that you have the necessary libraries in place, we'll move on to configuring
your domain.

## Tomcat Configuration [](id=tomcat-configuration)

The steps in this section focus on:

- Setting environment variables

- Creating a context for your web application

- Modifying the list of classes/JARs to be loaded

- Specifying URI encoding

Let's get started with our configuration tasks.

1. Create a `setenv.bat` (Windows) or `setenv.sh` file (Unix, Linux, Mac OS) in
   the `$TOMCAT_HOME/bin` directory. When you start Tomcat, Catalina calls
   `setenv.bat` or `setenv.sh`. Edit the file and populate it with following
   contents:

    setenv.bat:

		if exist "%CATALINA_HOME%/jre@java.version@/win" (
			if not "%JAVA_HOME%" == "" (
				set JAVA_HOME=
			)
		
			set "JRE_HOME=%CATALINA_HOME%/jre@java.version@/win"
		)

		set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    setenv.sh:

		JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF8 -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    This sets the character encoding to UTF-8, sets the time zone to Greenwich
    Mean Time and allocates memory to the Java virtual machine.

2. Create the directory `$TOMCAT_HOME/conf/Catalina/localhost` and create a
   `ROOT.xml` file in it. Edit this file and populate it with the following
   contents to set up a portal web application:

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
		
    Setting `crossContext="true"` allows multiple web apps to use the same class
    loader. In the content above you will also find commented instructions and
    tags for configuring a JAAS realm, disabling persistent sessions and
    disabling sessions in general.

3. Open `$TOMCAT_HOME/conf/catalina.properties` and replace the line:

		common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar

    with:

		common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar
		
    This allows Catalina to access the dependency jars you extracted to
    `$TOMCAT_HOME/lib/ext`.

4. To ensure consistent use of UTF-8 URI Encoding, edit
   `$TOMCAT_HOME/conf/server.xml` and add the attribute `URIEncoding="UTF-8"` to
   your connector on port 8080. Below is an example of specifying this encoding
   on the connector:

		<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

5. Make sure there is no `support-catalina.jar` file in your
   `$TOMCAT_HOME/webapps` directory. If you find one, remove it.

Excellent work! Now let's consider configuration of your database.

## Database Configuration [](id=database-configuration)

If you want Tomcat to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

1. Make sure your database server is installed and working. If it's installed on
   a different machine, make sure it's accessible from your Liferay machine.

2. Add your data source as a resource in the context of your web application
   specified in `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml`.

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
	
    Note the above resource definition assumes your database name is *lportal*
    and your MySQL username and password are both *root*. You'll have to update
    these values with your own database name and credentials.

Your Tomcat managed data source is now configured. Let's move on to your mail
session.

## Mail Configuration [](id=mail-configuration)

If you want to manage your mail session within Tomcat, use the following
instructions. If you want to use the built-in Liferay mail session, you can skip
this section.

Create a mail session bound to `mail/MailSession`. Edit `$TOMCAT_
HOME/conf/Catalina/localhost/ROOT.xml` and configure a mail session. Be sure to
replace the mail session values with your own.

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

Super! Your mail session is configured. Next, we'll make sure Liferay will be
able to access your mail session and database.

## Configuring Your Database and Mail Session [](id=configuring-your-database-and-mail-session)

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

It's just that easy! Now it's time to deploy Liferay Portal on your Tomcat
server. 

## Deploy Liferay [](id=deploy-liferay)

We'll deploy Liferay as an exploded web archive within your
`$TOMCAT_HOME/webapps` folder.

1. If you are manually installing Liferay on a clean Tomcat server, delete the
   contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This undeploys the
   default Tomcat home page. Then extract the Liferay `.war` file to
   `$TOMCAT_HOME/webapps/ROOT`.

2.  Before you start Liferay Portal, let's consider whether you want to also
    start the setup wizard.

    **Start the setup wizard along with Liferay Portal** - Do this if you want
    to configure your portal, setup your site's administrative account and/or
    manage your database within Liferay.
		
    If this is your first time starting Liferay Portal 6.1, the setup wizard is
    invoked on server startup. If you want to re-run the wizard, specify
    `setup.wizard.enabled=true` in your properties file (e.g.
    `portal-setup-wizard.properties`).

        setup.wizard.enabled=true

    The setup wizard is invoked during server startup.

    **Start Liferay Portal without invoking the setup wizard** - Do this if want
    to preserve your current portal settings.

    To start the server without triggering the setup wizard, specify
    `setup.wizard.enabled=false` in your properties (e.g.
    `portal-setup-wizard.properties` or `portal-ext.properties` file).

        setup.wizard.enabled=false

    The `portal-setup-wizard.properties` file the setup wizard creates has
    `setup.wizard.enabled=false` conveniently specified for you.

    +$$$

    **Note:** Property values in `portal-setup-wizard.properties` override 
    property values in `portal-ext.properties`.

    $$$

    Now its time to launch Liferay Portal on Tomcat!

3. Start Tomcat by executing `$TOMCAT_HOME/bin/startup.bat` or
   `$TOMCAT_HOME/bin/startup.sh`.

    - If the setup wizard was disabled, your site's home page opens in your
    browser at [http://localhost:8080](http://localhost:8080).

    - Otherwise, the setup wizard opens in your browser.

    To use the setup wizard, please see the section above. 

Congratulations on successfully installing and deploying Liferay on Tomcat!
