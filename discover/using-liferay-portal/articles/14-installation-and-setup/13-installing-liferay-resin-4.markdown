# Installing Liferay on Resin 4 [](id=installing-liferay-on-resin-4)

**Liferay Home** is one folder above Resin's install location.

For this section, we will refer to your Resin server's installation location as
`$RESIN_HOME`. If you do not already have an existing Resin server, we recommend
you download a Liferay/Resin bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing Resin server or would like to install Liferay on Resin
manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.1.x-<date>.zip`.

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on Resin.

## Dependency Jars [](id=dependency-jars)

Let's work with the depenency jar files first.

1. Unzip the jar files found in the Liferay Portal Dependencies zip file to
	your `$RESIN_HOME/ext-lib` folder. Take care to extract the zip file's
   `.jar` files directly into this folder.

2. Next, you need several `.jar` files which are included as part of the Liferay
   source distribution. Many application servers ship with these already on the
   class path but Resin does not. The best way to get the appropriate versions
   of these files is to download the Liferay source code and get them from
   there. Once you have downloaded the Liferay source, unzip the source into a
   temporary folder. We'll refer to the location of the Liferay source as
   `$LIFERAY_SOURCE`.

    1. Go to `$LIFERAY_SOURCE/lib/development` and copy `saxpath.jar` into
	   `$RESIN_HOME/lib`.
    2. Go to `$LIFERAY_SOURCE/lib/portal` and copy `log4j.jar`, `slf4j-api.jar`
	   and `slf4j-log4j12.jar` into `$RESIN_HOME/lib`.
    3. If folder `$RESIN_HOME/extlib` doesn't exist, create it.
    4. Make sure the JDBC driver for your database is accessible by Resin.
       Obtain the JDBC driver for the database you want to use. In the case of
       MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download
       the latest MySQL JDBC driver from
       [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/).
       Extract the JAR file and copy it to `$RESIN_HOME/extlib`.

Great! now you have your `.jar` files in place. Next, let's configure Resin for
Liferay. 

## Configuring Resin [](id=configuring-resin)

The primary file used in configuring your domain is
`$RESIN_HOME/conf/resin.xml`. You need to make common modifications necessary to
support Liferay Portal. You'll also create a run script and add a folder to hold
Resin's logs. But let's start with the changes to `resin.xml`.

1.  Make the following modifications to your `resin.xml`. These modifications to
    your application cluster make the following configuration changes:
    - Set the file encoding
    - Set the preferred protocol stack
    - Set the user time-zon
    - Increase the default amount of memory available. 

    To accomplish this, insert the following `<jvm-arg>` elements as server
    defaults for your main application cluster. Please see the following
    example:

        <cluster id="app-tier">
            ...
            <server-default>
                ...
                <jvm-arg>-Dfile.encoding=UTF-8</jvm-arg>
                <jvm-arg>-Djava.net.preferIPv4Stack=true</jvm-arg>
                <jvm-arg>-Duser.timezone=GMT</jvm-arg>
                <jvm-arg>-Xmx1024m</jvm-arg>
                <jvm-arg>-XX:MaxPermSize=256m</jvm-arg>
                ...
            </server-default>
        </cluster>

2.  Create an appropriate script in `$RESIN_HOME/bin` to help you start Resin.

    If you're on Windows, create a batch script `$RESIN_HOME/bin/run.bat` and
    insert the following text in the script:

        ..\resin.exe console

    If you're on Unix/Linux, create shell script `$RESIN_HOME/bin/run.sh` and
    insert the following text in the script:

        #!/bin/sh

        ./resin.sh $

3.  Create the folder `$RESIN_HOME/log` if it doesn't already exist. As you run
    Resin, the server generates log files `access`, `jvm-default` and
    `watchdog-manager` in this folder.

Now that you've completed some important common configuration tasks to support
Liferay, let's consider database configuration. 

## Database Configuration [](id=database-configuration)

If you want to manage your data source within Resin, continue following the
instructions in this section. If you want to use the built-in Liferay data
source, you can skip this section.

Management of databases in Resin is done via the configuration file
`$RESIN_HOME/conf/resin.xml`. Edit `resin.xml` and insert a `<database>` element
for your database. Be sure to give it the JNDI name `jdbc/LiferayPool` and add
it within the application tier cluster element as in the example below:

	<cluster id="app-tier">
		...
		<database>
			<jndi-name>jdbc/LiferayPool</jndi-name>
			<driver type="com.mysql.jdbc.Driver">
				<url>jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8</url>
				<user>root</user>
				<password>root</password>
            </driver>
            <prepared-statement-cache-size>8</prepared-statement-cache-size>
            <max-connections>20</max-connections>
            <max-idle-time>30s</max-idle-time>
          </database>
          ...
	</cluster>

Be sure to replace the URL database value (i.e. `lportal`), user value and
password value with values specific to your database. 

Resin is now managing your database connection. Let's consider next how to
configure mail.

## Mail Configuration [](id=mail-configuration)

If you want to manage your mail session within Resin, use the following
instructions. If you want to use the built-in Liferay mail session, you can skip
this section.

Management of mail sessions in Resin is done via the configuration file
`$RESIN_HOME/conf/resin.xml`. Edit `resin.xml` and insert a `<mail>` element
that specifies your mail session. Be sure to give it the JNDI name
`mail/MailSession`. Add your mail element within the application tier cluster
element. Use the example below, replacing its values with the values of your
mail session.

	<cluster id="app-tier">
		...
		<mail jndi-name="mail/MailSession">
			<properties>
				mail.pop3.host=pop.gmail.com
				mail.pop3.port=110
				mail.pop3.user=
				mail.pop3.password=
				mail.smtp.host=smtp.gmail.com
				mail.smtp.password=password
				mail.smtp.user=user
				mail.smtp.port=465
				mail.transport.protocol=smtp
				mail.smtp.auth=true
				mail.smtp.starttls.enable=true
				mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
				mail.imap.host=imap.gmail.com
				mail.imap.port=993
				mail.store.protocol=imap
			</properties>
		</mail>
          ...
	</cluster>

You can specify additional properties for your mail session as needed.

Now that your mail session is squared away, we'll make sure Liferay can access
it.

## Configuring Data Source and Mail Session [](id=configuring-data-source-and-mail-session)

Let's make sure Liferay's connected to your data source and mail session.

1. First, navigate to the *Liferay Home* folder, which is one folder above
   Resin's install location (i.e. `$RESIN_HOME/..`).

2. If you're using *Resin* to manage your data source, add the following to your
   `portal-ext.properties` file in your *Liferay Home* to refer to your data
   source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    If you're using *Liferay Portal* to manage your data source, follow the
    instructions in the *Deploy Liferay* section for using the setup wizard.

3. If want to use *Liferay Portal* to manage your mail session, configure the
   mail session within Liferay Portal. That is, after starting your portal as
   described in the *Deploy Liferay* section, go to *Control Panel &rarr; Server
   Administration &rarr; Mail* and enter the settings for your mail session.

    If you're using *Resin* to manage your mail session, add the following to your
    `portal-ext.properties` file to reference that mail session:

        mail.session.jndi.name=mail/MailSession

Great! Now Liferay can access your database and your mail session. Now, let's
deploy Liferay.

## Deploy Liferay [](id=deploy-liferay)

Liferay can be deployed as an exploded web archive within `$RESIN_HOME/webapps`.

1. If you already have an application folder `$RESIN_HOME/webapps/ROOT`, delete
   it or move it to a location outside of `$RESIN_HOME/webapps`.

2. Extract the contents of the Liferay portal `.war` file into
   `RESIN_HOME/webapps/ROOT`. The following files should now exist in your
   `RESIN_HOME/webapps/ROOT` folder:
    - dtd (folder)
    - errors (folder)
    - html (folder)
    - layouttpl (folder)
    - META-INF (folder)
    - wap (folder)
    - WEB-INF (folder)
    - index.jsp

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

    Now its time to launch Liferay Portal on Resin!

4.  Start Liferay Portal by executing your `run.bat` (Windows) or `run.sh`
    (Unix/Linux) script from `$RESIN_HOME/bin`.
    - If the setup wizard was disabled, your site's home page opens in your
    browser at [http://localhost:8080](http://localhost:8080).
    - Otherwise, the setup wizard opens in your browser.

    Please see the section above describing how to use the setup wizard. 

Congratulations! You've installed Liferay Portal on Resin and have it up and
running.
