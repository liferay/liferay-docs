# Installing Liferay on GlassFish 3 [](id=installing-liferay-on-glassfish-3)

*Liferay Home* is three folders above your GlassFish domain folder.

For example, if your domain location is
`/glassfish-3.1-web/glassfish3/glassfish/domains/domain1`, Liferay Home is
`/glassfish-3.1-web/glassfish3/`.

If you don't already have an existing GlassFish server, we recommend that you
download a Liferay/GlassFish bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing GlassFish server or would like to install Liferay on
GlassFish manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.1.x-<date>.zip`.

These instructions assume that you are running the latest supported version of
Glassfish (currently 3.1.2.2), have already configured a domain and server,
and that you have access to the GlassFish administrative console.

Let's start out by installing the JAR files you will need.

## Dependency Jars [](id=dependency-jars)

Liferay depends on jar files found in the Liferay Dependencies Archive. You
should also have installed your database driver.

1. Navigate to the folder that corresponds to the domain in which you'll be
   installing Liferay. Inside this folder is a sub-folder named `lib` (e.g.
   `/glassfish-3.1-web/glassfish3/glassfish/domains/domain1/lib`).

    Unzip the Liferay dependencies archive so that its `.jar` files are extracted
    into this `lib` folder.

2. Make sure the JDBC driver for your database is accessible to GlassFish as
   well. Obtain the JDBC driver for your version of the database server. In the
   case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can
   download the latest MySQL JDBC driver from
   [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/).
   Extract the JAR file and copy it to `lib`.

Terrific, you have your JAR files just where you'll need them. Next we'll
configure your domain.

### Domain Configuration [](id=domain-configuration)

There are a couple of modifications you need to make in your domain to use
Liferay Portal.

Before starting GlassFish, modify your domain's configuration to do the
following: 
- Set the file encoding
- Set the user time-zone
- Set the preferred protocol stack
- Prevent the application server from setting static fields (final or non-final)
  to `null`
- Increase the default amount of memory available.

Modify
`/glassfish-3.1-web/glassfish3/glassfish/domains/domain1/config/domain.xml` 
merging in the following JVM options into the current list of JVM options within
your `<java-config>` element: 

    <jvm-options>-Dfile.encoding=UTF8</jvm-options> 
    <jvm-options>-Djava.net.preferIPv4Stack=true</jvm-options>
    <jvm-options>-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false</jvm-options>
    <jvm-options>-Duser.timezone=GMT</jvm-options>
    <jvm-options>-Xmx1024m</jvm-options>
    <jvm-options>-XX:MaxPermSize=512m</jvm-options>

Be sure that any existing options with values such as `-Dfile.encoding`,
`-Djava.net.preferIPv4Stack`,
`-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES`,
`-Duser.timezone`, or `-XX:MaxPermSize` are replaced with the new values listed
above.

For example, replace: 

    <jvm-options>-Xmx256m</jvm-options>
	
with this: 
	
    <jvm-options>-Xmx1024m</jvm-options>

Delete, rename, or move the `domain1/docroot/index.html` file to another
location to allow your Liferay Portal default page to be displayed.

Next, let's get your database configured.

## Database Configuration [](id=database-configuration)

If you want to use GlassFish to manage your domain's data source, follow the
instructions found in this section. If you want to use Liferay Portal to manage
your data source, you can skip this section.

1. Start your domain's application server if it is not already running.

2. Go to the GlassFish console URL:
   [http://localhost:4848](http://localhost:4848/). 

3. Under *Common Tasks*, navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC
   Connection Pools*

    ![Figure 14.37: Navigate to JDBC Connection Pools](../../images/11-glassfish31-connection-pools.png)

4. Click *New...*.

5. In the first screen (Step 1 of 2), give your connection pool the name
   `LiferayPool`, the resource type of `javax.sql.ConnectionPoolDataSource` and
   select your database driver vendor (e.g. `MySQL`) as follows:

    ![Figure 14.38: Glassfish JDBC Connection Pool](../../images/11-glassfish-31-jdbc-connection-pool.png)

6. Click *Next* to advance to the next step in creating your JDBC connection
   pool.

7. On the this screen (Step 2 of 2), scroll down to the *Additional Properties*
   section.

    ![Figure 14.39: Glassfish JDBC Connection Pool Properties](../../images/11-glassfish-31-jdbc-connection-pool-props.png)  

8. Replace or add the following properties ...

    **URL:** the URL of your connection pool.

    For example,

        jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8&amp;emulateLocators=true

    Note, if you are using the above example, you should specify the name of
    your database in place of `lportal`. Likewise, if your database is not on
    the same host as GlassFish, specify your the database server's host name in
    place of `localhost`. Lastly, specify your database type in place of
    `jdbc:mysql`.

    **user:** the name of your database user.

    **password:** your database user's password.

9. Click *Finish*.

	You should now see your `LiferayPool` connection pool listed under
	*Resources* &rarr; *JDBC* &rarr; *JDBC Connection Pools*

10. Test your connection by selecting your `LiferayPool` connection pool and
    clicking *Ping*.

    If you get a message stating *Ping Succeeded*, you've succeeded in setting
    up a connection pool of your data source!

11. Now, you'll setup a JDBC resource to refer to the `LiferayPool` connection
    pool you just created.

12. Navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC Resources* to show the
    current JDBC resources listed by their JNDI names.

13. Click *New...*.

14. Set the JNDI name to `jdbc/LiferayPool` and select `LiferayPool` as the pool
    name.

15. Click *OK*.

Congratulations! You've now configured your domain's data source on GlassFish!

## Mail Configuration [](id=mail-configuration)

If you want to use GlassFish to manage your mail session, follow GlassFish's
documentation on configuring a JavaMail session with a JNDI name of
`mail/MailSession`. If you want to use Liferay Portal to manage your mail
session, you can skip this step.

## Domain Configuration - Continued [](id=domain-configuration-continued)

Let's tie up some loose ends with regards to Liferay being able to access your
database and mail session.

1. Shutdown your domain's application server if it is currently running.

2. Create a `portal-ext.properties` file in the *Liferay Home* folder mentioned
   at the beginning of this GlassFish installation section.

3. If you are using *Glassfish* to manage your data source, add the following to
   your `portal-ext.properties` file in your *Liferay Home* to refer to your
   data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source, follow
    the instructions in the *Deploy Liferay* section for using the setup wizard.

4. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session within Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

If you are using *GlassFish* to manage your mail session, add the following to
your `portal-ext.properties` file to reference that mail session:

    mail.session.jndi.name=mail/MailSession

Liferay can now communicate with your database and mail session. So let's go
ahead and deploy Liferay.

## Deploy Liferay [](id=deploy-liferay)

Here are the steps you'll need to follow to deploy Liferay Portal to your
domain's server. Before you deploy Liferay Portal, let's consider whether you
want to also start the setup wizard.

- **Start the setup wizard along with Liferay Portal** - Do this if you want to
  configure your portal, set up your site's administrative account and/or manage
  your database within Liferay.
		
If this is your first time starting Liferay Portal 6.1, the setup wizard is
automatically invoked. If you want to re-run the wizard, specify
`setup.wizard.enabled=true` in your properties file (e.g.
`portal-setup-wizard.properties`).

    setup.wizard.enabled=true

The setup wizard is then invoked during server startup.

- **Start Liferay Portal without invoking the setup wizard** - Do this if want
  to preserve your current portal settings.

To startup the server without triggering the setup wizard, specify
`setup.wizard.enabled=false` in your properties (e.g.
`portal-setup-wizard.properties` or `portal-ext.properties` file).

    setup.wizard.enabled=false

Once you run the setup wizard, the `portal-setup-wizard.properties` file it
creates already has `setup.wizard.enabled=false` conveniently specified for you.

+$$$

**Note:** Property values in `portal-setup-wizard.properties` override property 
values in `portal-ext.properties`.

$$$

1. Start your domain's application server.

2. Go to the GlassFish console URL:
   [http://localhost:4848](http://localhost:4848/)

3. Click *Applications* in the tree on the left.

4. Click *Deploy*.

5. Under *Packaged File to Be Uploaded to the Server*, click *Choose File* and
   browse to the location of the Liferay Portal `.war` file. Enter *Context
   Root:* `/`

6. Enter *Application Name:* `liferay-portal`

7. Click *OK*.

![Figure 14.40: Deploying Liferay in GlassFish 3.1.x](../../images/11-deploying-liferay-in-glassfish-31.png)

- If you disabled the setup wizard, your site's home page opens in your browser
  at [http://localhost:8080](http://localhost:8080).

- Otherwise, the setup wizard opens in your browser.

See the section on the setup wizard above for how to use the setup wizard. 

Your installation of Liferay Portal on GlassFish is complete!
