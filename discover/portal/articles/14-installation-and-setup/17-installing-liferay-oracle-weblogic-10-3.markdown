# Installing Liferay on Oracle WebLogic 10.3 [](id=installing-liferay-on-oracle-weblogic-10-3)

**Liferay Home** is one folder above the domain to which you will be installing
Liferay.

For example, if your domain location is
`/Oracle/Middleware/user_projects/domains/base_domain`, then your Liferay Home
is `/Oracle/Middleware/user_projects/domains`.

For this section, we will use `$WEBLOGIC_HOME` to refer to your WebLogic
server's installation `/Oracle/Middleware`. 

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.1.x-<date>.zip`.

These instructions assume you have already configured a domain and server and
that you have access to the WebLogic console.

If you still have the `mainWebApp` module installed, remove it first.

+$$$

**Note:** There is a known issue with the Sun and JRockit JVMs bundled with 
WebLogic 10.3.2 (see [http://bugs.sun.com/view_bug.do?bug_id=6795561](issue)). 
To resolve this, use Sun JVM 1.6.0_u24 or JRockit JVM 1.6.0_24.

$$$

Let's get started by installing the JAR files Liferay needs.

## Dependency Jars [](id=dependency-jars)

Liferay needs the JAR files contained in the Liferay Dependencies Archive and
the driver JAR file applicable for your database.

1. Navigate to the folder that corresponds to the domain to which you will be
   installing Liferay. Inside this folder is a `lib` folder. Unzip the Liferay
   Dependencies Archive to this folder so the dependency `.jar` files reside in
   the `lib` folder.

2. If WebLogic does not already have access to the JDBC driver for your
   database, copy the driver to your domain's `lib` folder as well.

So far so good. Your JAR files are in place and ready for Liferay.

Start Oracle WebLogic if you want to configure your database and/or mail session
within Oracle WebLogic.

## Database Configuration [](id=database-configuration)

If you want WebLogic to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

![Figure 14.44: WebLogic Data Sources](../../images/02-weblogic-10-3-data-sources.png)

1. Select *Services &rarr; Data Sources.* Click *New &rarr; Generic Data
   Source*.

2. Give your data source a name, such as *Liferay Data Source*. The JNDI name
   should be `jdbc/LiferayPool`.

3. Choose the type of database and click *Next*. From the screenshot, you can
   see we have chosen MySQL. The database driver class is selected
   automatically.

    ![Figure 14.45: Creating a data source in WebLogic 10.3](../../images/02-creating-a-data-source-in-weblogic.png)

4. Click *Next* three times. You should be on the *Connection Properties*
   screen. Enter the database name, the host name, the port, the database user
   name and the password. WebLogic uses this information to construct the
   appropriate JDBC URL to connect to your database. Click *Next*.

5. WebLogic next confirms the information you provided. For MySQL, some
   additional parameters must be added to the URL. Modify the JDBC URL so it has
   the proper parameters. Provide your database name, host name, user name and
   password as the values. Click *Next*.

6. Click *Test Configuration* to make sure WebLogic can connect to your database
   successfully. When it does, click *Finish*.

7. You will be back to the list of data sources. Notice your new data source has
   no value in the *Target* column. Click on your data source to edit it.

8. Click the *Targets* tab and check off the server instance(s) to which you
   wish to deploy your data source. Then click *Save*.

Next, let's configure a mail session in WebLogic.

## Mail Configuration [](id=mail-configuration)

If you want WebLogic to manage your mail sessions, use the following procedure.
If you want to use Liferay's built-in mail sessions, you can skip this section.

![Figure 14.46: WebLogic Mail Sessions](../../images/02-weblogic-10-3-mail-sessions.png)

1. Select *Mail Sessions* and create a new mail session which points to your
   mail server.

2. Give it the name Liferay Mail and give it the JNDI name of `mail/MailSession`
   and click *Next*.

3. Choose your server and then click *Finish*.

Now let's make sure Liferay can access this mail session.

## Domain Configuration - Continued [](id=domain-configuration-continued)

Let's revisit domain configuration to make sure we'll be able to access your
data source and mail session from Liferay Portal.

1. Create a `portal-ext.properties` file in the Liferay Home folder, which is
   one folder up from your domain's home folder.

    If you are using *WebLogic* to manage your data source, add the following to
    your `portal-ext.properties` file in *Liferay Home* to refer to your data
    source:

		jdbc.default.jndi.name=jdbc/LiferayPool

    If you are using *Liferay Portal* to manage your data source, follow the
    instructions in the *Deploy Liferay* section for using the setup wizard.

    If want to use *Liferay Portal* to manage your mail session, you can
    configure the mail session in the Control Panel. After starting your portal
    as described in the *Deploy Liferay* section, go to *Control Panel &rarr;
    Server Administration &rarr; Mail* and enter the settings for your mail
    session.

    If you are using *WebLogic* to manage your mail session, add the following
    to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=mail/MailSession

2. In order for special characters and other languages to display correctly, you
must set `-Dfile.encoding=utf8` under `JAVA_OPTIONS` in the `setDomainEnv` file.


3. Lastly, you must provide WebLogic a reference to Java Server Faces (JSF) to
   use that library. Insert the following deployment descriptor within the
   `<weblogic-web-app>` element of `WEB-INF/weblogic.xml` found in your Liferay
   Portal `.war` :

		<library-ref>
			<library-name>jsf</library-name>
			<specification-version>1.2</specification-version>
			<implementation-version>1.2</implementation-version>
			<exact-match>false</exact-match>
		</library-ref>

Now its the moment you've been waiting for: Liferay deployment!

## Deploy Liferay [](id=deploy-liferay)

This section provides instructions for deploying Liferay to your application
server domain.

Before you deploy Liferay Portal, consider whether you want to use the setup
wizard.

- **Start the setup wizard along with Liferay Portal** - Do this if you want to
  configure your portal, set up your site's administrative account and/or manage
  your database within Liferay.
		
  If this is your first time starting Liferay Portal 6.1, the setup wizard is
  invoked on server startup. If you want to re-run the wizard, specify
  `setup.wizard.enabled=true` in your properties file (e.g.
  `portal-setup-wizard.properties`).

	setup.wizard.enabled=true

  The setup wizard is invoked during server startup.

- **Start Liferay Portal without invoking the setup wizard** - Do this if want
  to preserve your current portal settings.

  To start the server without triggering the setup wizard, specify
  `setup.wizard.enabled=false` in your properties (e.g.
  `portal-setup-wizard.properties` or `portal-ext.properties` file).

		setup.wizard.enabled=false

  The `portal-setup-wizard.properties` file the setup wizard creates has
  `setup.wizard.enabled=false` conveniently specified for you.

+$$$

**Note:** Property values in `portal-setup-wizard.properties` override property 
values in `portal-ext.properties`.

$$$

Now, let's deploy Liferay Portal.

1. Start WebLogic.

2. Select *Deployments* and click the  *Install* button. Upload `jsf-1.2.war`
   from WebLogic's common files directory and select *Install this deployment as
   a library.*

    ![Figure 14.47: WebLogic Deployments](../../images/02-weblogic-10-3-deployments.png)

3. After installing the JSF libraries, go back to deployments and select the
   Liferay `.war` file from the file system or click the *Upload Your File(s)*
   link to upload it and then click *Next*.

4. Select *Install this deployment as an application* and click *Next*.

5. If the default name is appropriate for your installation, keep it. Otherwise,
   give it a name of your choosing and click *Next*.

6. Click *Finish*. After the deployment finishes, click *Save*.

	Liferay launches in one of the following manners:

    - If the setup wizard was disabled, your site's home page opens in your
	browser at [http://localhost:7001](http://localhost:7001).

    - Otherwise, the setup wizard opens in your browser.

Please see the section above for how to use the setup wizard. 

+$$$

**Note:** After Liferay is installed, you may see an error initializing the Web 
Proxy portlet. Because the XSL parser configured by default within WebLogic 
cannot compile a style sheet in this portlet, Liferay disables it by default. To 
re-enable this portlet, extract `xalan.jar` and `serializer.jar` from the 
Liferay `.war` archive and copy them to your JDK's endorsed folder for 
libraries. If you are using JRockit, this folder may be
`[$WEBLOGIC_HOME]/jrockit_160_05/jre/lib/ext`; if your are using Sun JDK,  this
folder may be `[$WEBLOGIC_HOME]/jdk160_24/jre/lib/ext`.

$$$

Congratulations! You are now running Liferay on Oracle WebLogic.
