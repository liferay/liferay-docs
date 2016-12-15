# Installing @product@ on tc Server [](id=installing-liferay-on-tc-server)

The easiest way to get @product@ running on tc Server is to [download a bundle](https://web.liferay.com/group/customer/downloads/portal/product). If
that's not an option, you can install @product@ onto tc Server. In addition to a
supported version of [tc Server](https://network.pivotal.io/products/pivotal-tcserver) (version 3 or
higher), make sure you have acquired the following items (from [Liferay's customer portal](https://web.liferay.com/group/customer/downloads/portal/product))

-  A @product@ WAR file

-  A compressed file with The OSGi JARs that contain much of @product@'s functionality

-  A compressed file with the necessary @product@ dependencies and additional
   libraries you need for your installation

Once you have those pieces of the puzzle, you just need to assemble them.

Installing @product@ manually requires these basic steps:

- Installing @product@ dependencies to your application server
- Configuring your application server for @product@
- Installing @product@ by providing the WAR file to your application server and
  the OSGi folder for @product@

+$$$

**Note:** You'll see the term *Liferay Home* used in this installation guide.
*Liferay Home* refers to the folder containing your tc Server instance and some
@product@-specific folders:. `data`, `deploy`, `licenses`, and `osgi` folders. 

$$$

## Installing @product@ Dependencies [](id=installing-liferay-dependencies)

@product@ depends on some additional JARs that aren't included with tc Server by
default. There are even more JARs that you'd find in a @product@ bundle
that are not required but can be useful. If you don't have a @product@
bundle, you can download the required JARs from third parties, as
described below.

+$$$

**Note:** Many required and useful JARs are pre-installed when you build @product@ from the source code or [download a @product@ bundle](https://www.liferay.com/downloads/). If you want to acquire all of the
JARs that ship with a @product@ bundle quickly, using one of these sources might
save you time.

$$$

Here are the JARs included in the dependencies zip file: 

- `hsql.jar`

- `portal-kernel.jar`

- `portlet.jar`

One JAR you'll definitely need that is not included in the dependencies zip is
your database driver. Database drivers for *MySQL* and *PostgreSQL* can be found
in a @product@ bundle or in the @product@ source code.

There are several other dependency JARs that aren't included in the dependencies
zip. If you don't have them already on hand or have access to a @product@ bundle,
you'll have to download them yourself.

- `jta.jar`: Support for Java transactions. You can get this `.jar`, which manages transactions, from
            [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html)

- `mail.jar`: Support for the Java Mail API. You can get this `.jar` from
            [http://www.oracle.com/technetwork/java/index-138643.html](http://www.oracle.com/technetwork/java/index-138643.html)

- `persistence.jar`: Support for the Java Persistence API. You can get this
      `.jar` from
            [http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)

- `activation.jar`: This is an implementation of the Java Activation
      Framework. You can get this `.jar` from
        [http://www.oracle.com/technetwork/java/jaf11-139815.html](http://www.oracle.com/technetwork/java/jaf11-139815.html)

- `ccpp.jar`: Enables Composite Capability/Preference Profiles. You can get this `.jar` from 
        [http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)

- `jms.jar`: The Java Messaging Service. You can get this `.jar` from
        [http://www.oracle.com/technetwork/java/docs-136352.html](http://www.oracle.com/technetwork/java/docs-136352.html)

- `jutf7.jar`: Provides UTF-7 and Modified UTF-7 charsets for Java. You can get this `.jar` from 
          [http://sourceforge.net/projects/jutf7/](http://sourceforge.net/projects/jutf7/)

- `junit.jar`: Lets you run unit tests. You can get this `.jar` from 
        [http://sourceforge.net/projects/junit/](http://sourceforge.net/projects/junit/)

Once you have the necessary and desired dependencies, just copy them into your
tc Server instance's `lib` folder.

@product@ includes an OSGi runtime. Extract the OSGi ZIP file that you downloaded
and copy the `osgi` folder to your Liferay Home folder. The `osgi` folder
contains many required JAR files and a few configuration files.

## Configuring tc Server [](id=configuring-tc-server)

There are a few configuration edits to make so @product@ runs well on tc Server.
All of these configuration changes should be made in your tc Server runtime
instance.

1. Navigate to the `bin` folder. In `setenv.sh` replace this line

        JVM_OPTS="-Xmx512M -Xss256K"

    with this one

        JVM_OPTS="-Xmx1024M -Xss512K -XX:MaxMetaspaceSize=512m"

    In `setenv.bat` replace

        set JVM_OPTS=-Xmx512M -Xss256K
					
    with

        set JVM_OPTS=-Xmx1024M -Xss512K -XX:MaxMetaspaceSize=512m


2. Next, you should make sure that UTF-8 URI encoding is used consistently.
Open `conf/server.xml` and make sure the `Connector` tag includes setting the
`URIEncoding` to `UTF-8`. 

        <Connector acceptCount="100"
                   connectionTimeout="20000"
                   executor="tomcatThreadPool"
                   maxKeepAliveRequests="15"
                   port="${bio.http.port}"
                   protocol="org.apache.coyote.http11.Http11Protocol"
                   redirectPort="${bio.https.port}"
                   URIEncoding="UTF-8" />


4. If you're installing @product@ and tc Server on Windows, open
`conf/wrapper.conf` and replace

        wrapper.java.additional.8=-Xmx512M

    with

        wrapper.java.additional.8="-Xmx1024M"

    and

        wrapper.java.additional.9=-Xss256K

    with

        wrapper.java.additional.9="-Xss512K"
        wrapper.java.additional.10="-XX:MaxMetaspaceSize=256M"
        wrapper.java.additional.11="-Dfile.encoding=UTF-8"


## Database Configuration [](id=database-configuration)

The easiest way to handle your database configuration is to let @product@ manage
your data source. If you want to use @product@'s built-in data source, you can
skip this section. 
<!-- check this after using @product@ to manage the connection -->

If you want tc Server to manage your data source, use this procedure:

1. Make sure your database server is installed and working. If it's installed
   on a different machine, make sure it's accessible from your @product@ machine.

2. Add your data source as a resource in the context of your web application
specified in `/conf/Catalina/localhost/ROOT.xml` (create this file if you don't
have it already):

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

Note that the above resource definition assumes your database name is *lportal*,
that you're using MySQL, and that your MySQL *username* and *password* are both
*root*. You'll have to update these values with your own database name and
credentials.

Your data source is now configured. Next set up the mail session.

## Mail Configuration [](id=mail-configuration)

As with database configuration, the easiest way to handle mail configuration is
to let @product@ handle your mail session. If you want to use @product@'s built-in
mail session, skip this section and use @product@'s Control Panel to configure a
mail server after @product@ has been installed and started.

To create a mail session bound to `mail/MailSession`, edit
`conf/Catalina/localhost/ROOT.xml` and configure your mail session in a
`Resource` tag. Make sure to replace the example mail session values with your
own.

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

Your mail session is configured. Next, you'll make sure @product@ can 
access your mail session and database.

## Configuring a Database and Mail Session [](id=configuring-a-database-and-mail-session)

In this section you'll specify appropriate properties for @product@ to use in
connecting to your database and mail session.

1. If you are using tc Server to manage your data source, add the following line
to the `portal-ext.properties` file in your *Liferay Home*.

        jdbc.default.jndi.name=jdbc/LiferayPool

2. If you are using tc Server to manage your mail session, add the following to
    `portal-ext.properties`:

        mail.session.jndi.name=mail/MailSession

Before you deploy @product@, you should configure a Portal Access Control List
(PACL).

## Enabling PACL [](id=enabling-pacl)

To enable PACL, you need to enable Tomcat’s security manager. Make sure
`$TCSERVER_INSTANCE_HOME/conf/catalina.policy` specifies the permissions

    grant {
        permission java.security.AllPermission;
    };

Open `$TCSERVER_INSTANCE_HOME/bin/setenv.sh` if on Linux, Unix, or Mac OS, or
`setenv.bat` if on Windows. Enable the security manager by inserting the
following code into the `CATALINA_OPTS` variable (inside quotation marks):

    -Djava.security.manager -Djava.security.policy==$CATALINA_BASE/conf/catalina.policy

Now you have PACL enabled and configured for your portal.

## Deploying @product@ [](id=deploying-liferay)

Now you're ready to deploy @product@ using your @product@ WAR file.

1. If you are manually installing @product@ on a clean tc Server instance, delete
the contents of the `$TCSERVER_INSTANCE_HOME/webapps/ROOT` directory. This
removes the default Tomcat home page.

2. Extract the @product@ `.war` file to `$TCSERVER_INSTANCE_HOME/webapps/ROOT`.

    Now it's time to launch @product@!

3. Start tc Server by navigating to the runtime instance's `bin` folder and
executing `./tcruntime-tcl.sh run` or `/tcruntime-tcl.sh start`. If you're on
Windows you can use the `.bat` version of the startup script only if you
installed the tc Server instance as a Windows service. Alternatively,
you can use `./catalina.sh run` or `catalina.bat run`.

After you set up your account you can start [using @product@](/discover/portal).
