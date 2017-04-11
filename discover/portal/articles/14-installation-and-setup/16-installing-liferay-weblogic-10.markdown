# Installing Liferay on WebLogic 10 [](id=installing-liferay-on-weblogic-10)

**Liferay Home** is one folder above the domain to which you will be installing
Liferay. For example, if your domain location is
`/Oracle/Middleware/user_projects/domains/base_domain`, then your Liferay Home
is  `/Oracle/Middleware/user_projects/domains`.

For this section, we will refer to your WebLogic server's installation location
as `$WEBLOGIC_HOME`. 

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.1.x-<date>.zip`.

These instructions assume you have already configured a domain and server and
that you have access to the WebLogic console.

+$$$

**Note:** WebLogic 10.0 supports JDK 1.5 but does *not* support JDK 1.6.

$$$

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on WebLogic.

## Dependency Jars [](id=dependency-jars)

Liferay requires several `.jar` files including the Liferay Dependency JARs and
a JAR file for your database driver. The following steps describe how to install
these `.jar` files properly.

1. Navigate to the folder which corresponds to the domain to which you will be
   installing Liferay. Inside this folder is a `lib` folder. Unzip the Liferay
   dependencies archive to this folder so the dependency `.jar` files are
   extracted into the `lib` folder.

2. If WebLogic does not already have access to the JDBC driver for your
   database, copy the driver to your domain's `lib` folder as well.

3. You will also need the `xercesImpl.jar` copied to your domain's `lib` folder
   or you will get SAX parsing errors after you deploy Liferay. You may download
   this  from [http://xerces.apache.org](http://xerces.apache.org/).

4. Create a folder `$WEBLOGIC-HOME/jrockit_150_15/jre/lib/endorsed`. Then copy
   `commons-lang.jar, liferay-rhino.jar`, `serializer.jar` and `xalan.jar` to
   the endorsed folder you just created.

Now that you have your WebLogic installation is loaded up with JAR files for
Liferay to use, let's consider how to configure your database.

## Database Configuration [](id=database-configuration)

If you want WebLogic to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

![Figure 14.41: WebLogic Data Sources](../../images/02-weblogic-data-sources.png)

1. Browse to your WebLogic Console. Click the *Lock & Edit* button above the
   Domain Structure tree on the left side of the page.

2. From the Domain Structure tree on the left, select *Data Sources*. Then click
   the *New* button on the right side of the screen.

3. Give the Data Source a name, such as `LiferayDataSource`.

4. Define the JNDI name as `jdbc/LiferayPool`.

5. Select your Database Type, the Driver class and then click the *Next* button.

6. Accept the defaults on the next screen by clicking *Next*.

7. On the next screen, put in your *Database Name*, *Host Name*, *Database User
   Name* and *Password*. If you have been following the defaults we have been
   using so far, you would use *lportal, localhost, root,* and no password as
   the values. Click *Next*.

8. The next screen allows you to test your database configuration. Click the
   *Test Configuration* button. If the test succeeds, you have configured your
   database correctly. Select the check box of the server to which you want to
   deploy this Data Source (`AdminServer` is the default). Click *Finish*.

9. Click the *Activate Changes* button on the left, above the Domain Structure
   tree.

Great work! Your data source can now be managed from within WebLogic. Next,
let's consider the mail session for your domain.

## Mail Configuration [](id=mail-configuration)

If you want WebLogic to manage your mail sessions, use the following procedure.
If you want to use Liferay's built-in mail sessions, you can skip this section.

![Figure 14.42: WebLogic: Mail Sessions](../../images/02-weblogic-mail-sessions.png)

1. In the Domain Structure tree, select *Mail Sessions.* Then click the *Lock &
   Edit* button again to enable modifying these settings.

2. Click the *New* button which is now enabled on the right side of the screen.

3. Give the Mail Session a name, such as `LiferayMail`.

4. Select your new LiferayMail session from the list by clicking on it.

5. On the screen that appears, define the JNDI name as `mail/MailSession` and
   set your JavaMail properties. Click the *Save* button.

6. Click the *Targets* tab. Select the check box of the server to which you want
   deploy this Data Source to (AdminServer is the default).

7. Click the *Activate Changes* button on the left side of the screen, above the
   Domain Structure tree.

Now you have your mail session specified and ready for Liferay to use. 

## Domain Configuration - Continued [](id=domain-configuration-continued)

In order for special characters and other languages to display correctly, you
must set `-Dfile.encoding=utf8` under `JAVA_OPTIONS` in the `setDomainEnv` file.

Let's revisit domain configuration to make sure we'll be able to access your
data source and mail session from Liferay Portal.

1. First, navigate to the *Liferay Home* folder.

2. Then, if you are using *WebLogic* to manage your data source, add the
   following to your `portal-ext.properties` file in your *Liferay Home* to
   refer to your data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source,
    follow the instructions in the *Deploy Liferay* section for using the setup
    wizard.

3. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session within Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

    Otherwise, if you are using *WebLogic* to manage your mail session, add the
    following to your `portal-ext.properties` file to reference that mail
    session:

        mail.session.jndi.name=mail/MailSession

Liferay can now communicate with your data source and mail session. It's now
time to deploy Liferay!

## Deploy Liferay [](id=deploy-liferay)

Follow the instructions in this section to deploy Liferay Portal to your domain.

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

Now that you have enabled or disabled the setup wizard, let's move on to
deployment of Liferay Portal.

![Figure 14.43: WebLogic Deployments](../../images/02-weblogic-deployments.png)

1. In the Domain Structure tree, select *Deployments.*  Then click the *Lock &
   Edit* button above the Domain Structure tree.

2. Click the *Install* button on the right side of the screen.

3. Click the *Upload your file(s)* link.

4. Browse to where you have stored the Liferay `.war` file, select it and then
   click *Next*.

5. Select the Liferay `.war` file from the list and click *Next*.

6. Leave *Install this deployment as an application* selected and click *Next*.

7. Give the application a name (the default name is fine). Leave the other
   defaults selected and then click *Finish*.

8. WebLogic deploys Liferay. When it finishes, a summary screen is displayed.
   Click the *Activate Changes* link on the left above the Domain Structure
   tree.

9. In the Deployments screen, select the Liferay application and click the
   *Start* button. Select *Servicing All Requests* in the pop up.

10. Click *Yes* to continue on the next screen to launch Liferay Portal.

    - If the setup wizard was disabled, your site's home page opens in your
    browser at [http://localhost:7001](http://localhost:7001).

    - Otherwise, the setup wizard opens in your browser.

For more information on how to use the setup wizard, please see the section
above. 

Congratulations on your deployment of Liferay Portal on WebLogic 10!
