# Installing @product@ on Tomcat [](id=installing-product-on-tomcat)

@product-ver@ bundled with Tomcat 9 is available on the
[Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/7-1)
(DXP) or
[Liferay Downloads](https://www.liferay.com/downloads)
(Portal CE). The Tomcat bundle contains JARs, scripts, and configuration files
required for installing @product@ on a clean Tomcat 9 application server.
Copying these files from a @product@ Tomcat bundle facilitates installing
@product@ on Tomcat.

Whether you copy bundle files (recommended) or download and create the files,
you must download these files for
[DXP](https://web.liferay.com/group/customer/dxp/downloads/7-1) or
[Portal CE](https://www.liferay.com/downloads):

- @product@ WAR file
- Dependencies ZIP file
- OSGi JARs ZIP file

Here are the basic steps for installing @product@ on Tomcat:

- [Installing dependencies to your application server](#installing-liferay-dependencies)
- [Configuring your application server for @product@](#tomcat-configuration)
- [Deploying the @product@ WAR file to your application server](#deploying-liferay)

[*Liferay Home*](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)
is the folder containing your Tomcat server folder. After installing and
deploying @product@, Liferay Home contains `data`, `deploy`, `license`, and
`osgi` folders. `$TOMCAT_HOME` refers to your Tomcat server folder. It is
usually named `tomcat-[version]` or `apache-tomcat-[version]`.

## Installing Dependencies [](id=installing-liferay-dependencies)

@product@ depends on many JARs included by @product@ Tomcat bundle. Some of the
bundle's JARs are not strictly required but can still be useful. If you don't
have a bundle, download the required JARs from third-parties, as described
below.

1.  Create the folder `$TOMCAT_HOME/lib/ext` if it doesn't exist and extract the
    JARs from the dependencies ZIP to it. Here are the JARs:
 
    - `com.liferay.petra.concurrent.jar`
    - `com.liferay.petra.executor.jar`
    - `com.liferay.petra.function.jar`
    - `com.liferay.petra.io.jar`
    - `com.liferay.petra.lang.jar`
    - `com.liferay.petra.memory.jar`
    - `com.liferay.petra.nio.jar`
    - `com.liferay.petra.process.jar`
    - `com.liferay.petra.reflect.jar`
    - `com.liferay.petra.string.jar`
    - `com.liferay.registry.api.jar`
    - `hsql.jar`
    - `portal-kernel.jar`
    - `portlet.jar`

2.  Download the following JARs or copy them from a @product@ Tomcat bundle to 
    the `$TOMCAT_HOME/lib/ext` folder:

    - [`activation.jar`](http://www.oracle.com/technetwork/java/javase/jaf-136260.html)
    - [`ccpp.jar`](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)
    - [`jms.jar`](http://www.oracle.com/technetwork/java/docs-136352.html)
    - [`jta.jar`](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
    - [`jutf7.jar`](http://mvnrepository.com/artifact/com.beetstra.jutf7/jutf7)
    - [`mail.jar`](http://www.oracle.com/technetwork/java/index-138643.html)
    - [`persistence.jar`](http://mvnrepository.com/artifact/org.eclipse.persistence/javax.persistence/2.1.1)
    - [`support-tomcat.jar`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.support.tomcat)

3.  Copy the JDBC driver for your database to the `$CATALINA_BASE/lib/ext` 
    folder. Here are some common drivers: 

    - [`mariadb.jar`](https://downloads.mariadb.org/)
    - [`mysql.jar`](http://dev.mysql.com/downloads/connector/j)
    - [`postgresql.jar`](https://jdbc.postgresql.org/download/postgresql-42.0.0.jar)

4.  Create an `osgi` folder in your Liferay Home. Extract the folders (i.e., 
    `configs`, `core`, and more) from OSGi ZIP file to the `osgi` folder. The
    `osgi` folder provides the necessary modules for @product@'s OSGi runtime.

Checkpoint:

1.  Your `$CATALINA_BASE/lib/ext` folder has these JARs:

    - `activation.jar`
    - `ccpp.jar`
    - `com.liferay.petra.concurrent.jar`
    - `com.liferay.petra.executor.jar`
    - `com.liferay.petra.function.jar`
    - `com.liferay.petra.io.jar`
    - `com.liferay.petra.lang.jar`
    - `com.liferay.petra.memory.jar`
    - `com.liferay.petra.nio.jar`
    - `com.liferay.petra.process.jar`
    - `com.liferay.petra.reflect.jar`
    - `com.liferay.petra.string.jar`
    - `com.liferay.registry.api.jar`
    - `hsql.jar`
    - `jms.jar`
    - `jta.jar`
    - `jutf7.jar`
    - `mail.jar`
    - `mariadb.jar`
    - `mysql.jar`
    - `persistence.jar`
    - `portal-kernel.jar`
    - `portlet.jar`
    - `postgresql.jar`
    - `support-tomcat.jar`

2. Your `[Liferay Home]/osgi` folder has these subfolders:

    - `configs`
    - `core`
    - `marketplace`
    - `modules`
    - `portal`
    - `static`
    - `test`
    - `war`

## Configuring Tomcat [](id=tomcat-configuration)

Configuring Tomcat to run @product@ includes these things:

- Setting environment variables
- Specifying a web application context for @product@
- Setting properties and descriptors

Optionally, you can configure Tomcat to manage these things for @product@:

- [Data source](#database-configuration)
- [Mail session](#mail-configuration)

Start with configuring Tomcat to run @product@. 

1.  If you have a @product@ Tomcat bundle, copy the `setenv.bat` and `setenv.sh`
    files from it to your `$CATALINA_BASE/bin` folder. If not, create these
    scripts. 

    The scripts set JVM options for Catalina, which is Tomcat's servlet
    container. Among these options is the location of the Java runtime
    environment. If this environment is not available on your server globally,
    you must set its location in in these files so Tomcat can run. Do this by
    pointing the `JAVA_HOME` environment variable to a @product@-supported JRE:

        export JAVA_HOME=/usr/lib/jvm/java-8-jdk
        export PATH=$JAVA_HOME/bin:$PATH

    Then configure Catalina's JVM options to support @product@.

    Unix: 

        CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m"

    Windows:

        set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m"

    This sets the file encoding to UTF-8, prefers an IPv4 stack over IPv6,
    prevents Tomcat from working around garbage collection bugs relating to
    static or final fields (these bugs don't exist in @product@ and working
    around them causes problems with the logging system), sets the time zone to
    GMT, gives the JVM 2GB of RAM, and limits Metaspace to 500MB. 

    After installation, tune your system (including these JVM options) for
    performance. 

2.  If you have a @product@ Tomcat bundle, copy its
    `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` file to the corresponding
    location in your application server. Create the file path if it doesn't
    exist. If you don't have a @product@ Tomcat bundle, create a `ROOT.xml`
    file. 

    The `ROOT.xml` file specifies a web application context for @product@.
    `ROOT.xml` looks like this:

        <Context crossContext="true" path="">

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

            <Resources>
                <PreResources
                    base="${catalina.base}/lib/ext/portal"
                    className="com.liferay.support.tomcat.webresources.ExtResourceSet"
                    webAppMount="/WEB-INF/lib"
                />
            </Resources>
        </Context>

     Setting `crossContext="true"` lets multiple web applications use the same
     class loader. This configuration includes commented instructions and tags
     for configuring a JAAS realm, disabling persistent sessions, and disabling
     sessions entirely.

3.  Provide Catalina access to the JARs in `$CATALINA_BASE/lib/ext` by opening
    your `$CATALINA_BASE/conf/catalina.properties` file and appending this
    value to the `common.loader` property: 

        ,"${catalina.home}/lib/ext/global","${catalina.home}/lib/ext/global/*.jar","${catalina.home}/lib/ext","${catalina.home}/lib/ext/*.jar"

4.  Make sure to use UTF-8 URI encoding consistently. If you have a @product@ 
    Tomcat bundle, copy the `$CATALINA_BASE/conf/server.xml` file to your
    server. If not, open your `$CATALINA_BASE/conf/server.xml` file and add the
    attribute `URIEncoding="UTF-8"` to HTTP and AJP connectors that use
    `redirectPort=8443`. Here are examples:

    Old:

        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />

    New:

        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

    Old:

        <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />

    New:

        <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" URIEncoding="UTF-8" />

5.  If you're on Unix, Linux, or Mac OS, make the shell scripts in your 
    `$CATALINA_HOME/bin` and `$CATALINA_BASE/bin` folders executable by running
    this command in each folder:

    `chmod a+x *.sh`

**Checkpoint:**

Your application server is configured to run @product@.

1.  The file encoding, user time-zone, and preferred protocol stack are set in 
    `setenv.sh`.

2.  The default memory available and Metaspace limit are set.

3.  `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` declares the web 
    application context.

4.  The `common.loader` property in `$CATALINA_BASE/conf/catalina.properties`
    grants Catalina access to the JARs in `$CATALINA_BASE/lib/ext`.

5.  `$CATALINA_BASE/conf/server.xml` sets UTF-8 encoding.

6.  The scripts in Tomcat's `bin` folders are executable.

### Database Configuration [](id=database-configuration)

The easiest way to handle your database configuration is to let @product@ manage
your data source. @product@'s
[Basic Configuration](/discover/deployment/-/knowledge_base/7-1/installing-liferay#using-liferays-setup-wizard)
page lets you configure @product@'s built-in data source. If you want to use the
built-in data source, skip this section.

If you want Tomcat to manage your data source, follow these steps:

1.  Make sure your database server is installed and working. If it's installed
    on a different machine, make sure it's accessible from your @product@
    machine.

2.  Open `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` and add your data 
    source as a `Resource` in your web application `Context`:

        <Context...>
            ...
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

    The resource definition above is for a MySQL database named `lportal` that
    has a user named `root` whose password is `root`. Replace these values with
    your own. 

3.  In a `portal-ext.properties` file in your Liferay Home, specify your data 
    source:

        jdbc.default.jndi.name=jdbc/LiferayPool

You created a data source for Tomcat to manage and configured @product@ to use
it. Mail session configuration is next.

### Mail Configuration [](id=mail-configuration)

As with database configuration, the easiest way to configure mail is to let
@product@ handle your mail session. If you want to use @product@'s built-in mail
session, skip this section and
[configure the mail session](/discover/deployment/-/knowledge_base/7-1/installing-liferay#configuring-mail)
in the Control Panel.

If you want to manage your mail session with Tomcat, follow these steps:

1.  Open `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` and add your mail 
    session as a `Resource` in your web application `Context`. Make sure to
    replace the example mail session values with your own.

        <Context...>
            ...
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

2.  In your `portal-ext.properties` file in Liferay Home, reference your mail
    session:
   
        mail.session.jndi.name=mail/MailSession

You've created a mail session for Tomcat to manage and configured @product@ to
use it. 

## Deploying @product@ [](id=deploying-liferay)

Now you're ready to deploy @product@ using the @product@ WAR file.

1.  If you are manually installing @product@ on a clean Tomcat server, delete 
    the contents of the `$CATALINA_BASE/webapps/ROOT` folder. This removes
    the default Tomcat home page.

2.  Extract the @product@ `.war` file to `$CATALINA_BASE/webapps/ROOT`. 

    It's time to launch @product@ on Tomcat!

3.  Start Tomcat by navigating to `$CATALINA_HOME/bin` and executing 
    `./startup.sh`. Alternatively, execute `./catalina.sh run` to tail
    @product@'s log file. The log audits startup activities and is useful for
    debugging deployment.

Congratulations on successfully installing and deploying @product@ on Tomcat!
