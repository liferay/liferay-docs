# Installing Liferay on JBoss 7 [](id=installing-liferay-on-jboss-7)

**Liferay Home** is one folder above JBoss's install location.

1. Download and install JBoss AS 7.0.x into your preferred directory. This
   directory is referred to as `$JBOSS_HOME` throughout this section.

2. Download the latest version of the Liferay Portal `.war` file.

3.	Download Liferay's Portal Dependencies.

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on JBoss.

## Dependency Jars [](id=dependency-jars)

Let's work with the dependency jar files first.

1. Create folder `$JBOSS_HOME/modules/com/liferay/portal/main` and unzip the jar
   files found in the Liferay Portal Dependencies zip file to this folder. Make
   sure the zip file's jar files are extracted into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

2. Download your database driver `.jar` file and put it into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder as well. For
   demonstration purposes, we'll download the MySQL Connector/J driver from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)
   and put its `.jar` file into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3. Create the file `module.xml` in the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the following
   contents.

		<?xml version="1.0"?>

		<module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
			<resources>
				<resource-root path="mysql-connector-java-5.1.18-bin.jar" />
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

If you're using a different database driver, replace the path of the MySQL
resource root entry with that of your database driver.
		
Great! You have your `.jar` files ready for your domain.

## Configuring JBoss [](id=configuring-jboss)

Let's make some adjustments in your configuration to support using Liferay.

You can specify the JBoss server instance's configuration in the XML file
`$JBOSS_HOME/standalone/configuration/standalone.xml`. We'll refer to this file
simply as `standalone.xml`. You must also make some modifications to your
configuration and startup scripts found in the `$JBOSS_HOME/bin/` folder. But
let's start with the changes to `standalone.xml`.

Make the following modifications to `standalone.xml`:

1. Disable the welcome root of the web subsystem's virtual server default
   host by specifying `enable-welcome-root="false"`.

		<subsystem xmlns="urn:jboss:domain:web:1.0" default-virtual-server="default-host">
			<connector name="http" scheme="http" protocol="HTTP/1.1" socket-binding="http"/>
			<virtual-server name="default-host" enable-welcome-root="false">
			   <alias name="localhost" />
			   <alias name="example.com" />
			</virtual-server>
		</subsystem>
			
2. Insert the following `<configuration>` element within the web subsystem
   element `<subsystem xmlns="urn:jboss:domain:web:1.0"
   default-virtual-server="default-host">`.

		<configuration>
			<jsp-configuration development="true" />
		</configuration>

3. Add a timeout for the deployment scanner by setting
   `deployment-timeout="120"` as seen in the excerpt below.

		<subsystem xmlns="urn:jboss:domain:deployment-scanner:1.0">
			<deployment-scanner name="default" path="deployments" scan-enabled="true" scan-interval="5000" relative-to="jboss.server.base.dir" deployment-timeout="120"/>
		</subsystem>

4. Add the following JAAS security domain to the security subsystem
   `<security-domains>` defined in element `<subsystem
   xmlns="urn:jboss:domain:security:1.0">`.

		<security-domain name="PortalRealm">
			<authentication>
				<login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required"/>				</authentication>
		</security-domain>

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

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

- On Unix, merge the following values into your settings for `JAVA_OPTS`
  replacing any matching attributes with the ones found in the assignment
  below:

	    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m
		
The prescribed script modifications are now complete for your Liferay
installation on JBoss. Next we'll consider the database and mail configuration. 

## Database Configuration [](id=database-configuration)

If you want JBoss to manage your data source, follow the instructions in this
section. If you want to use the built-in Liferay data source, you can skip this
section.

Modify `standalone.xml` adding your data source and driver within the
`<datasources>` element of your data sources subsystem.

1. First, add your data source within the `<datasources>` element.

		<datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
			<connection-url>
				jdbc:mysql://localhost/lportal
			</connection-url>
			<driver>
				mysql
			</driver>
			<security>
				<user-name>
					root
				</user-name>
				<password>
					root
				</password>
			</security>
		</datasource>

	Be sure to replace the URL database value (i.e. `lportal`), user value and
	password value with values specific to your database.

2. Then add your driver to the `<drivers>` element also found within the
   `<datasources>` element.

		<drivers>
			<driver name="mysql" module="com.liferay.portal"/>
		</drivers>

Your final data sources subsystem should look something like this:

		<subsystem xmlns="urn:jboss:domain:datasources:1.0">
			<datasources>
				<datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
					<connection-url>
						jdbc:mysql://localhost/lportal
					</connection-url>
					<driver>
						mysql
					</driver>
					<security>
						<user-name>
							root
						</user-name>
						<password>
							root
						</password>
					</security>
				</datasource>
				<drivers>
					<driver name="mysql" module="com.liferay.portal"/>
				</drivers>
			</datasources>
		</subsystem>

Now that you've configured your data source, let's go over how to configure your
mail session within JBoss.

## Mail Configuration [](id=mail-configuration)

At the time this document was written, JavaMail was not yet supported in JBoss
AS 7.0.1 - however, it was implemented in the JBoss AS 7.1 alpha (see
[https://issues.jboss.org/browse/AS7-1177](https://issues.jboss.org/browse/AS7-1177).
If you want JBoss to manage your mail session, use the following instructions
which are based on the implementation found in JBoss AS 7.1 alpha. If you want
to use the built-in Liferay mail session, you can skip this section.

Specify your mail subsystem  in `standalone.xml` as in the following example:

	<subsystem xmlns="urn:jboss:domain:mail:1.0">
		<mail-session jndi-name="java:/mail/MailSession" >
			<smtp-server address="smtp.gmail.com" port="465">
				   <login name="username" password="password"/>
			</smtp-server>
			<pop3-server address="pop.gmail.com" port="110"/>
			<imap-server address="imap.gmail.com" port="993">
				<login name="username" password="password"/>
			</imap-server>
	   </mail-session>
	</subsystem>

You've got mail! Next, we'll make sure Liferay is configured to properly connect
with your new mail session and database.

## Configuring Data Sources and Mail Sessions [](id=configuring-data-sources-and-mail-sessions)

Now that your data source and mail session are set up, you need to ensure
Liferay Portal can access them.

1. First, navigate to the Liferay Home folder, which is one folder above JBoss's
   install location (i.e. `$JBOSS/..`).

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
Now you're ready to deploy Liferay Portal.

## Deploy Liferay [](id=deploy-liferay)

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
   `eclipselink.jar`. Note, JBoss 7.0.2 has a known issue
   [http://community.jboss.org/thread/169944](http://community.jboss.org/thread/169944)
   in determining which persistence provider to use.

5.  Before you start Liferay Portal, let's consider whether you want to also
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

    Now it's time to start Liferay Portal on JBoss!

6.  Start the JBoss application server.
    - If the setup wizard was disabled, your site's home page opens
      automatically in your browser at
      [http://localhost:8080](http://localhost:8080). 
    - Otherwise, the setup wizard opens in your browser.

    See the section on the setup wizard above for how to use Liferay's setup
    wizard.

Now you are truly *the boss* when it comes to deploying Liferay Portal on JBoss!
