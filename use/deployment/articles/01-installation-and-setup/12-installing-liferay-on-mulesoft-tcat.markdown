# Installing Liferay on Mulesoft Tcat [](id=installing-liferay-on-mulesoft-tcat)

For this section, we will refer to your Tcat server's installation location as
`[TCAT_HOME]`. If you don't already have an existing Tcat server, we
recommend you download a Liferay/Tcat bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing Tcat server on which you'd like to deploy Liferay
manually, please follow the steps below.

Your first step is to download the latest Liferay `.war` file and Liferay
Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.2.x-[date].war` and
the dependencies file should be called
`liferay-portal-dependencies-6.2.x-[date].zip`.

Next, let's get started by addressing Liferay's library dependencies.

## Dependency Jars

To run Liferay Portal on your Tcat server, you first need to make some JAR files
available on Tcat's global classpath. These include the Liferay Dependency JARs,
a JDBC driver for your database, and some other dependencies that Liferay Portal
requires. 

1. Create the folder `[TCAT_HOME]/lib/ext`.

2. Extract the Liferay dependencies file and copy the `.jars` to `[TCAT_HOME]/lib/ext`. 

3. Next, you need a few `.jar` files that are included as part of the
   Liferay Tcat bundle, but are not included with Tcat.  You'll have to download
   them yourself, so let's get started. Place these `.jar` files into
   `$TCAT_HOME/lib/ext`:

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

   - `junit.jar`: Optional: lets you run unit tests. You can get this `.jar` from 
        [http://sourceforge.net/projects/junit/](http://sourceforge.net/projects/junit/)

   Although you can get each `.jar` listed above separately, it may be more
   convenient to get them by downloading the Liferay source code and copying them
   from there. Once you have downloaded the Liferay source, unzip the source
   into a temporary folder. You can find the `.jar` files in
   `[LIFERAY_SOURCE]/lib/development`. 

4. Make sure the JDBC driver for your database is accessible by Tomcat. Obtain
   the JDBC driver for your version of the database server. In the case of
   MySQL, use `mysql-connector-java-[version]-bin.jar`. You can download the
   latest MySQL JDBC driver from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/).
   Extract the JAR file and copy it to `[TCAT_HOME]/lib/ext`.

Now that you have the necessary libraries in place, we'll move on to configuring
your domain.

## Tcat Configuration

If you're installing Liferay Portal onto an existing Tcat server, you should be
familiar with the Tcat Administration Console. The following instructions assume
you have a Tcat server with the Administration Console and a separate, managed
Tcat server instance where you'll deploy Liferay. To find information specific
to Tcat server installation and management, see [Mulesoft's Tcat Documentation](http://www.mulesoft.org/documentation/display/TCAT/Home). You have to do a few things to configure your managed Tcat server instance: 

- Set environment variables

- Create a context for your web application

- Modify the list of classes/JARs to be loaded

- Specify URI encoding

![Figure 1.2: You can log in to the Tcat Administration Console to manage your Tcat servers.](../../images/15-tcat-sign-in.png)

Next, you'll configure your managed Tcat instance. 

1. To set the `CATALINA_OPTS` environment variable, you need to add a server
profile. In the Tcat Administration Console, navigate to the *Administration*
tab and click *Server Profiles*. Name the profile appropriately (*Liferay 6.2*,
perhaps), provide a description if you wish, select the workspace where you'll
keep your profile (`/Profiles` is a logical choice), and click the *Add
Variable* button. Name it `CATALINA_OPTS` and give it the following value: 

        -Dfile.encoding=UTF8 -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

This sets the character encoding to UTF-8, sets the time zone to Greenwich
Mean Time and allocates memory to the Java virtual machine.

Apply the profile to the Tcat server where you're deploying Liferay. To do so,
go to the *Servers* tab, select the desired server, and select your profile from
the *Set Profile* dropdown menu. 

2. Create a file locally called `ROOT.xml`: 

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

<!-- Why do we have all that stuff up there commmented out? Wouldn't it be
better not to include it at all? -Rich -->

Click your server's name to edit its configuration. Using the *Files* tab,
navigate to the directory `[TCAT_HOME]/conf/Catalina/localhost` and upload your
ROOT.xml file.  Setting `crossContext="true"` allows multiple web apps to use the same class
loader. In the content above you will also find commented instructions and
tags for configuring a JAAS realm, disabling persistent sessions and
disabling sessions in general.

3. Still in your server's *Files* tab, open
`[TCAT_HOME]/conf/catalina.properties`, click the *Edit catalina.properties*
link, and replace the line:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar

with:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar

This allows Catalina to access the dependency jars you extracted to
`[TCAT_HOME]/lib/ext`.

4. To ensure consistent use of UTF-8 URI Encoding, edit
   `[TCAT_HOME]/conf/server.xml` and add the attribute `URIEncoding="UTF-8"`
   where you see `redirectPort=8443`, in the definition of your connectors (HTTP
   and AJP). For example:

    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

![Figure 1.3: You can edit your Tcat configuration files in the Administration Console.](../../images/15-tcat-server-files.png)

Excellent work! Now it's time to configure your database.

## Database Configuration

If you want Tcat to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

1. Make sure your database server is installed and working. If it's installed on
   a different machine, make sure it's accessible from your Liferay machine.

2. Using the Tcat Administration Console, add your data source as a resource in
the context of your web application specified in
`[TCAT_HOME]/conf/Catalina/localhost/ROOT.xml`:

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

Your Tcat managed data source is now configured. Let's move on to your mail
session.

## Mail Configuration

If you want to manage your mail session within Tomcat, use the following
instructions. If you want to use the built-in Liferay mail session, you can skip
this section.

Create a mail session bound to `mail/MailSession`. In the Tcat Administration
Console, edit `[TCAT_ HOME]/conf/Catalina/localhost/ROOT.xml` and configure a
mail session. Be sure to replace the mail session values with your own.

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

Super! Your mail session is configured. Next, you need to connect Liferay to the
mail session and database connections you just created. 

## Configuring your Database and Mail Session

In this section you'll specify appropriate properties for Liferay to use in
connecting to your database and mail session.

1. If you are using Tcat to manage your data source, add the following line to
   the `portal-ext.properties` file in your *Liferay Home*. This points Liferay
   Portal to your data source: 

    jdbc.default.jndi.name=jdbc/LiferayPool

   Otherwise, if you are using *Liferay Portal* to manage your data source, follow
   the instructions in the *Deploy Liferay* section for using the setup wizard.

2. If you want to use Liferay Portal to manage your mail session, you can
   configure the mail session in Liferay Portal. After starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

   If you are using Tcat to manage your mail session, add the following to your
   `portal-ext.properties` file to reference that mail session:

    mail.session.jndi.name=mail/MailSession

Before you deploy Liferay Portal, let's look at configuring Portal Access
Control Lists (PACL) with Liferay on Tomcat. 

## Enabling PACL

To enable PACL, you need to enable the security manager and add some required
permissions to the server policy configuration file. This entails editing
the `CATALINA_OPTS` variable and editing the `catalina.policy` file:

In the *Administration* tab of the Tcat Administration Console, click *Server
Profiles* and click the profile applied to your Liferay Tcat server. Click the
*Value* field of the `CATALINA_OPTS` variable created earlier, and add the
following parameter to it:

    `-Djava.security.manager -Djava.security.policy==$CATALINA_BASE/conf/catalina.policy`

The double equals sign tells the app server to use this policy file on top of
any existing security policies. 

Edit `$TCAT_HOME/conf/catalina.policy` and add the required permissions:

        grant {
            permission java.security.AllPermission;
        };

Now you have PACL enabled and configured for your portal. Let's deploy Liferay!

## Deploying Liferay

It's time to deploy Liferay as an exploded web archive in your
`$TCAT_HOME/webapps` folder. The first step is to make sure your Tcat server is
running; then follow these steps to deploy and start Liferay.

1. If you are manually installing Liferay on a clean Tcat server, delete the
   contents of the `[TCAT_HOME]/webapps/ROOT` directory. This removes the default
   home page.

2. In the Tcat Administration Console, click the *Deployments* tab and select
   *New Deployment*. Select the server where you're deploying Liferay and
   click *Upload New Webapp*. Browse to the `liferay-portal-6.2.x-[date].war` file
   you downloaded. Make sure you select *Advanced Options* while uploading
   Liferay, and under the *Name* field, type `/` to put the extracted Liferay into
   `[TCAT_HOME]/webapps/ROOT`.

   ![Figure 1.4: Upload your Liferay Portal WAR file using the Deployments tab of the Tcat Administration Console.](../../images/15-tcat-upload-webapp.png)

3. Once you've entered all the deployment details, you can select *Deploy*.
   Once you see a *Successful* message in the Tcat Administration Console,
   you're ready to launch Liferay Portal on Tcat!

Now you can navigate to Liferay and follow the setup wizard. Congratulations
on successfully installing and deploying Liferay on Mule Tcat!
