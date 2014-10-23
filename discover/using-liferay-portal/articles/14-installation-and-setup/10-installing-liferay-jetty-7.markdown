# Installing Liferay on Jetty 7 [](id=installing-liferay-on-jetty-7)

**Liferay Home** is one folder above Jetty's install location.

For this section, we'll refer to Jetty server's installation location as
`$JETTY_HOME`. If you do not already have an existing Jetty server, we recommend
you download a Liferay/Jetty bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing Jetty server or would like to install Liferay on Jetty
manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.1.x-<date>.zip`.

Now that you have all of your installation files, you're ready to start
installing and configuring Liferay on Jetty.

## Dependency Jars [](id=dependency-jars)

Let's work with the Liferay depenency jar files first.

1. Create folder `$JETTY_HOME/lib/ext/liferay`.

2. Unzip the jar files found in the Liferay Portal Dependencies zip file to your
   `$JETTY_HOME/lib/ext/liferay` folder. Take care to extract the zip file's
   `.jar` files directly into this folder.

3. Next, you need several `.jar` files which are included as part of the Liferay
   source distribution. Many application servers ship with these already on the
   class path but Jetty does not. The best way to get the appropriate versions
   of these files is to download the Liferay source code and get them from
   there. Once you have downloaded the Liferay source, unzip the source into a
   temporary folder. We'll refer to the location of the Liferay source as
   `$LIFERAY_SOURCE`.

    Copy the following jars from `$LIFERAY_SOURCE/lib/development` to your
    `$JETTY_HOME/lib/ext/liferay` folder:
	- activation.jar
    - jta.jar
    - mail.jar
    - persistence.jar

4. Make sure the JDBC driver for your database is accessible to Jetty. Obtain
   the JDBC driver for your version of the database server. In the case of
   MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download the
   latest MySQL JDBC driver from
   [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/).
   Extract the JAR file and copy it to `$JETTY_HOME/lib/ext/liferay`.

Now that your `.jar` files are in place, let's configure your domain.

## Jetty Configuration [](id=jetty-configuration)

To get Jetty ready for running Liferay Portal, you must make a number of
modifications that involve configuration files, initialization files and run
scripts.

1. Create a `start.config` file to modify the behavior of Jetty's `start.jar`.
   It's best to base your `start.config` file on the default one found in
   `start.jar`.

    1. Extract the default start config file
       `org/eclipse/jetty/start/start.config` from the `start.jar` into
       `$JETTY_HOME/etc` so you have the file `$JETTY_HOME/etc/start.config`.
	2. Add the following property assignment to `$JETTY_HOME/etc/start.config`
       to specify where Jetty should write its logs:
       `jetty.logs=$(jetty.home)/logs`
    3. Add the following directive to `$JETTY_HOME/etc/start.config` to load all
       of the `.jar` and `.zip` files found in your `$JETTY_HOME/lib/liferay`
       folder into your class path: `$(jetty.home)/lib/liferay/*`

    Now that your class loading is specified, let's create initialization files
    and run scripts that invoke these configuration directives during Jetty's
    startup process.

2.  Create initialization file: `$JETTY_HOME/bin/start.ini`

        START=../etc/start.config
        OPTIONS=Server,jsp,resources
			
        ../etc/jetty.xml
        ../etc/jetty-deploy.xml
        ../etc/jetty-webapps.xml
        ../etc/jetty-contexts.xml
        ../etc/jetty-testrealm.xml

    This initialization file does the following:
    - Sets `$JETTY_HOME/etc/start.config` as your starting configuration file.
    - Sets your server options.
    - Specifies a sequence of deployment descriptor files to be processed. 

3.  Create a run script appropriate to your operating system:

    On Windows, create: `$JETTY_HOME/bin/run.bat`

        @echo off

        if "" == "%JAVA_HOME%" goto errorJavaHome

        set "JAVA_OPTS=-Djetty.version=7.5.4 -Djetty.version.date=20111024 -Dfile.encoding=UTF8 -Djava.io.tmpdir=../temp -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

        "%JAVA_HOME%/bin/java" %JAVA_OPTS% -jar ../start.jar

        goto end

        :errorJavaHome
            echo JAVA_HOME not defined.

            goto end

        :end

    On Unix/Linux, create: `$JETTY_HOME/bin/run.sh`

        #!/bin/sh

        if [ ! $JAVA_HOME ]
        then
            echo JAVA_HOME not defined.
            exit
        fi

        export JAVA_OPTS="-Djetty.version=7.5.4 -Djetty.version.date=20111024 -Dfile.encoding=UTF8 -Djava.io.tmpdir=../temp -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

        $JAVA_HOME/bin/java $JAVA_OPTS -jar ../start.jar

4.  Create a context file `$JETTY_HOME/contexts/root.xml` to specify the context,
    classpath and resource base of your web application:

        <?xml version="1.0"?>
        <!DOCTYPE Configure PUBLIC "-//Mort Bay Consulting//DTD Configure//EN" "http://jetty.mortbay.org/configure.dtd">

        <Configure class="org.eclipse.jetty.webapp.WebAppContext">
            <Set name="contextPath">/</Set>
            <Set name="extraClasspath"><SystemProperty name="jetty.home" />/lib/jetty-server-<SystemProperty name="jetty.version" />.v<SystemProperty name="jetty.version.date" />.jar,<SystemProperty name="jetty.home" />/lib/jetty-util-<SystemProperty name="jetty.version" />.v<SystemProperty name="jetty.version.date" />.jar,<SystemProperty name="jetty.home" />/lib/jetty-webapp-<SystemProperty name="jetty.version" />.v<SystemProperty name="jetty.version.date" />.jar</Set>
            <Set name="resourceBase"><SystemProperty name="jetty.home" />/webapps/root</Set>
        </Configure>

5.  Lastly, create the following folders:
    - $JETTY_HOME/logs - for log files
    - $JETTY_HOME/temp - for temporary files. Note, this folder is specified to
    our JVM as a temporary folder in the run script you created previously.

Now that your general Jetty startup files are set in place, let's consider how
you will manage your data source. 

## Database Configuration [](id=database-configuration)

If you want to manage your data source within Jetty, continue following the
instructions in this section. If you want to use the built-in Liferay data
source, you can skip this section.

1. Management of databases in Jetty is done via the file
   `$JETTY_HOME/etc/jetty.xml`. Edit `jetty.xml` and insert the following text
   within the root element `<Configure>` to specify the data pool for your data
   source. Be sure to pass in value `jdbc/LiferayPool` as the second argument.

		<New id="LiferayPool" class="org.eclipse.jetty.plus.jndi.Resource">
			<Arg></Arg>
			<Arg>jdbc/LiferayPool</Arg>
			<Arg>
				<New class="com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource">
					<Set name="Url">jdbc:mysql://localhost/lportal?
		useUnicode=true&amp;characterEncoding=UTF-8</Set>
					<Set name="User">root</Set>
					<Set name="Password">root</Set>
				</New>
			</Arg>
		</New>

    Be sure to replace the URL database value (i.e. `lportal`), user value and
    password value with values specific to your database.

2. Your data pool needs Jetty's JNDI and Jetty Plus libraries loaded to access
   those classes at runtime. Your `$JETTY_HOME/etc/start.config` file should
   have sections that load these libraries as long as `jndi` and `plus` *options*
   are specified at startup.

    To set these options, edit your `$JETTY_HOME/bin/start.ini` file and add `jndi`
    and `plus` as values for the `OPTIONS` variable:

		OPTIONS=Server,jsp,resources,jndi,plus

Super! Now you have your database specified and ready for use with Liferay on
Jetty. Let's consider your mail session next.

## Mail Configuration [](id=mail-configuration)

If you want to manage your mail session within Jetty, use the following
instructions. If you want to use the built-in Liferay mail session, you can skip
this section.

Management of mail sessions in Jetty is done via the configuration file
`$JETTY_HOME/etc/jetty.xml`. Edit `jetty.xml` and insert the following text
within the root element `<Configure>` to specify your mail session. Be sure to
pass in value `mail/MailSession` as the first argument and to replace the mail
session values with your own.

	<New id="MailSession" class="org.eclipse.jetty.plus.jndi.Resource">
		<Arg>mail/MailSession</Arg>
		<Arg>
			<New class="org.eclipse.jetty.jndi.factories.MailSessionReference">
				<Set name="user"></Set>
				<Set name="password"></Set>
				<Set name="properties">
					<New class="java.util.Properties">
						<Put name="mail.pop3.host">pop.gmail.com</Put>
						<Put name="mail.pop3.port">110</Put>
						<Put name="mail.smtp.host">smtp.gmail.com</Put>
						<Put name="mail.smtp.password">password</Put>
						<Put name="mail.smtp.user">user</Put>
						<Put name="mail.smtp.port">465</Put>
						<Put name="mail.transport.protocol">smtp</Put>
						<Put name="mail.smtp.auth">true</Put>
						<Put name="mail.smtp.starttls.enable">true</Put>
						<Put name="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory</Put>
						<Put name="mail.imap.host">imap.gmail.com</Put>
						<Put name="mail.imap.port">993</Put>
						<Put name="mail.store.protocol">imap</Put>
					</New>
				</Set>
			</New>
		</Arg>
	</New>

Great! Now you'll be able to use this mail session with Liferay.

## Configuring Data Sources and Mail Sessions [](id=configuring-data-sources-and-mail-sessions)

Let's revisit your configuration to make sure we'll be able to access your data
source and mail session from Liferay Portal.

1. First, navigate to the *Liferay Home* folder, which is one folder above
   Jetty's install location. Create a file named `portal-ext.properties`.

2. If you are using *Jetty* to manage your data source, add the following to
   your `portal-ext.properties` file in your *Liferay Home* to refer to your
   data source:

		jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source, follow
    the instructions in the *Deploy Liferay* section for using the setup wizard.

3. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session within Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

    Otherwise, if you are using *Jetty* to manage your mail session, add the
    following to your `portal-ext.properties` file to reference that mail session:

        mail.session.jndi.name=mail/MailSession

Let's start your server and deploy Liferay Portal! 

## Deploy Liferay [](id=deploy-liferay)

Liferay can be deployed as an exploded web archive within `$JETTY_HOME/webapps`.

1.  If you already have an application folder `$JETTY_HOME/webapps/root`, delete
    it or move it to a location outside of `$JETTY_HOME/webapps`.

2.  Then extract the contents of the Liferay portal `.war` file into
    `$JETTY_HOME/webapps/root`.

3.  Before you start Liferay Portal, let's consider whether you want to also
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

    Now its time to launch Liferay Portal!

4.  Start Liferay Portal by executing `run.bat` (Windows) or `run.sh`
    (Unix/Linux) script from `$JETTY_HOME/bin`.
    - If the setup wizard was disabled, your site's home page opens in your
      browser at [http://localhost:8080](http://localhost:8080).
    - Otherwise, the setup wizard opens in your browser.

See the section on the setup wizard above for more information about the setup
wizard. 

You've just installed and deployed Liferay Portal on Jetty - way to go!
