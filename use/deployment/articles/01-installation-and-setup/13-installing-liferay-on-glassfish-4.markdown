# Installing Liferay on GlassFish 4 [](id=installing-liferay-on-glassfish-4)

*Liferay Home* is three folders above your GlassFish domain folder.

For example, if your domain location is
`/glassfish-4.0-web/glassfish4/glassfish/domains/domain1`, Liferay Home is
`/glassfish-4.0-web/glassfish4/`.

If you don't already have an existing GlassFish server, we recommend that you
download a Liferay/GlassFish bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing GlassFish server or would like to install Liferay on
GlassFish manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.2.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.2.x-<date>.zip`.

These instructions assume that you are running the latest supported version of
Glassfish (currently 4.0), have already configured a domain and server, and that
you have access to the GlassFish administrative console.

Let's start out by installing the JAR files you will need.

## Dependency Jars

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

Terrific, you have your JAR files just where you need them. Next, we'll
configure your domain.

#### Domain Configuration

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
`glassfish4/glassfish/domains/domain1/config/domain.xml`,
merging in the following JVM options into the current lists of JVM options
within any `<java-config>` element in the file: 

    <jvm-options>-Dfile.encoding=UTF8</jvm-options> 
    <jvm-options>-Djava.net.preferIPv4Stack=true</jvm-options>
    <jvm-options>-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false</jvm-options>
    <jvm-options>-Duser.timezone=GMT</jvm-options>
    <jvm-options>-Xmx1024m</jvm-options>
    <jvm-options>-XX:MaxPermSize=512m</jvm-options>

There may be multiple lists of JVM options in your `domain.xml` file. For
example, by default, GlassFish's
`glassfish4/glassfish/domains/domain1/config/domain.xml` file contains two lists
of JVM options. There's one list inside of the `<config name="server-config">`
element's `<java-config>` element and another inside of the `<config
name="default-config">` element's `<java-config>` element. In both lists of JVM
options, make sure that any existing options with values such as
`-Dfile.encoding`, `-Djava.net.preferIPv4Stack`,
`-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES`,
`-Duser.timezone`, or `-XX:MaxPermSize` are replaced with the new values listed
above.

For example, replace: 

    <jvm-options>-Xmx256m</jvm-options>

with this: 

    <jvm-options>-Xmx1024m</jvm-options>

Edit your `domain1/config/config/server-policy.xml` and append the following
lines to the end of the file:

    grant {
        permission java.security.AllPermission;
    };

Delete, rename, or move the `domain1/docroot/index.html` file to another
location to allow your Liferay Portal default page to be displayed.

Next, let's get your database configured.

## Database Configuration

If you want to use GlassFish to manage your domain's data source, follow the
instructions found in this section. If you want to use Liferay Portal to manage
your data source, you can skip this section.

1. Start your domain's application server if it is not already running.

2. Go to the GlassFish console URL:
   [http://localhost:4848](http://localhost:4848/). 

3. Under *Common Tasks*, navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC
   Connection Pools*

    ![Figure 1.5: In the GlassFish administration console, navigate to JDBC Connection Pools.](../../images/11-glassfish31-connection-pools.png)

4. Click *New...*.

5. On the first screen (Step 1 of 2), enter the name `LiferayPool` for your
   connection pool, select the `javax.sql.ConnectionPoolDataSource` resource
   type, and select your database driver vendor (e.g. `MySQL`). See the
   following figure:

    ![Figure 1.6: It's easy to configure a new Glassfish JDBC Connection Pool. Just enter a pool name, select a resource type, and specify a database driver vendor.](../../images/11-glassfish-31-jdbc-connection-pool.png)

6. Click *Next* to advance to the next step in creating your JDBC connection
   pool.

7. From the top of this screen (Step 2 of 2), scroll down to the *Additional
   Properties* section.

    ![Figure 1.7: GlassFish JDBC Connection Pool Properties](../../images/11-glassfish-31-jdbc-connection-pool-props.png)  

8. Replace or add the following properties ...

    **Url:** the URL of your connection pool.

    For example,

        jdbc:mysql://localhost:3306/lportal?useUnicode=true&amp;characterEncoding=UTF-8&amp;emulateLocators=true

    Note, if you are using the above example, you should specify the name of
    your database in place of `lportal`. Likewise, if your database is not on
    the same host as GlassFish, specify your the database server's host name in
    place of `localhost`. Lastly, specify your database type in place of
    `jdbc:mysql` and use the correct port (3306 is for MySQL).

    **User:** the name of your database user.

    **Password:** your database user's password.

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

## Mail Configuration

If you want to use GlassFish to manage your mail session, follow GlassFish's
documentation on configuring a JavaMail session with a JNDI name of
`mail/MailSession`. If you want to use Liferay Portal to manage your mail
session, you can skip this step.

## Domain Configuration - Continued

Let's tie up some loose ends with regards to Liferay being able to access your
database and mail session.

1. Shutdown your domain's application server if it is currently running.

2. If you are using *Glassfish* to manage your data source, create a
   `portal-ext.properties` file in the *Liferay Home* folder mentioned at the
   beginning of this GlassFish installation section and add the following to
   your `portal-ext.properties` file in your *Liferay Home* to refer to your
   data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source,
    follow the instructions in the *Deploy Liferay* section for using the setup
    wizard.

3. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session within Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

    Otherwise, if you are using *GlassFish* to manage your mail session, add the
    following to your `portal-ext.properties` file to reference that mail
    session:

        mail.session.jndi.name=mail/MailSession

Liferay will now be able to communicate with your database and mail session.

## PACL Configuration

To enable PACL on GlassFish, you need to make some security configurations.
First, enable the security manager by editing
`glassfish/domains/domain1/config/domain.xml` and make sure it contains the
following:

    <java-config ...>
        ...
        <jvm-options>-Djava.security.manager</jvm-options>
        ...
    </java-config>

Next, add the required permissions to the server policy configuration file:
`glassfish/domains/domain1/config/server.policy`. These include the following:

    grant {
        permission java.security.AllPermission;
    };

Now let's go ahead and deploy Liferay.

## Deploy Liferay

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

![Figure 1.8: GlassFish provides an administrative console which you can use to deploy Liferay.](../../images/11-deploying-liferay-in-glassfish-4.0.png)

Once you have deployed Liferay via GlassFish's administrative console, restart
GlassFish.

Your installation of Liferay Portal on GlassFish is complete!
