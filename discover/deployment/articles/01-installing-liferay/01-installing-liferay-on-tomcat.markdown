# Installing Liferay on Tomcat 7

If you want a fresh installation of Liferay on Tomcat 7, simply download a
Liferay Tomcat bundle from
[https://www.liferay.com/downloads/liferay-portal/available-releases](https://www.liferay.com/downloads/liferay-portal/available-releases).
Even if you want to manually install Liferay on an existing Tomcat 7
application server, you should still download a Liferay Tomcat bundle. The
bundle contains many required dependencies and configuration files. Before
proceeding, you should also download the latest Liferay WAR file from
[https://www.liferay.com/downloads/liferay-portal/available-releases#additional-versions](https://www.liferay.com/downloads/liferay-portal/available-releases#additional-versions).

Installing Liferay manually requires these basic steps:

- Installing Liferay dependencies to your application server
- Configuring your application server for Liferay
- Installing the Liferay WAR file to your application server

You'll see the term *Liferay Home* used in this installation guide. *Liferay
Home* refers to the folder containing your Tomcat server folder. When Liferay
is installed on Tomcat, the Liferay Home folder typically contains the Tomcat
server folder as well as `data`, `deploy`, `license`, and `osgi` folders.
You'll also see the term `$TOMCAT_HOME` used in this guide. `TOMCAT_HOME`
refers to your Tomcat server folder. This folder is usually named
`tomcat-[version]` or `apache-tomcat-[version]`.

## Installing Liferay Dependencies

Liferay depends on many JARs that are included in the Liferay Tomcat bundle.
Some JARs in the bundle are not strictly required but can still be useful.

1. Extract the Liferay bundle that you downloaded to a temporary location of
   your choosing. You'll copy a number of resources from this bundle to your
   Tomcat server as you manually install Liferay on your Tomcat server.

2. First, copy all the JARs from your bundle's `TOMCAT_HOME/lib/ext` folder to
   your application server's `TOMCAT_HOME/lib/ext` folder. If the
   `TOMCAT_HOME/lib/ext` folder doesn't exist on your application server,
   create it.

    Here's a list of the JARs you should copy:

    - `activation.jar`
    - `ccpp.jar`
    - `com.liferay.osgi.service.tracker.collections.jar`
    - `com.liferay.registry.api.jar`
    - `hsql.jar`
    - `jms.jar`
    - `jta.jar`
    - `jutf7.jar`
    - `mail.jar`
    - `mysql.jar`
    - `persistence.jar`
    - `portal-service.jar`
    - `portlet.jar`
    - `postgresql.jar`
    - `support-tomcat.jar`

3. Make sure that Tomcat can access the JDBC driver for your database. The list
   of JARs above includes `mysql.jar` and `postgresql.jar`. If you're using a
   database whose JDBC driver is not included in the list above, download the
   driver and copy it to your `TOMCAT_HOME/lib/ext` folder.

4. Liferay includes an OSGi runtime. Copy the `osgi` folder from your Liferay
   bundle to your Liferay Home folder. This folder contains many required JAR
   files and a few configuration files.

## Tomcat Configuration

Next, you need to configure Tomcat for running Liferay.

1. Copy the `setenv.bat` and `setenv.sh` files from your bundle to your
   `TOMCAT_HOME/bin` folder. `setenv.bat` looks like this:

        if exist "%CATALINA_HOME%/jre1.6.0_20/win" (
            if not "%JAVA_HOME%" == "" (
                set JAVA_HOME=
            )

            set "JRE_HOME=%CATALINA_HOME%/jre1.6.0_20/win"
        )

        set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true  -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=384m"

    `setenv.sh` looks like this:

        CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true  -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=384m"

These files set a number of JVM options for Catalina. Catalina is Tomcat's
servlet container.

2. Copy the `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml` file from your
   bundle to the corresponding location in your application server. This
   `ROOT.xml` file creates a web application context for Liferay. `ROOT.xml`
   looks like this:

        <Context path="" crossContext="true">

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
        </Context>

    Setting `crossContext="true"` allows multiple web applications to use the
    same class loader. The configuration above includes commented instructions
    and tags for configuring a JAAS realm, disabling persistent sessions, and
    disabling sessions entirely.

3. Next, you should make sure that the libraries you copied to
   `$TOMCAT_HOME/lib/ext` are loaded when you start your server. Copy the
   `$TOMCAT_HOME/conf/catalina.properties` from your bundle to your server.

    Alternatively, if you have already customized your `catalina.properties`
    file, you can simply replace one line. This allows you to preserve any
    customizations that were made to your `catalina.properties` file.
    
    To take this approach, open `$TOMCAT_HOME/conf/catalina.properties` and
    replace the line:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar

    with this one:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar

    This allows Catalina to access the JARs that you copied to
    `$TOMCAT_HOME/lib/ext`.

4. Copy the `$TOMCAT_HOME/conf/catalina.policy` file from your bundle to your
   server. Alternatively, just replace the contents of the
   `$TOMCAT_HOME/conf/catalina.policy` file with this:

        grant {
            permission java.security.AllPermission;
        };

    If you enable PACL for Liferay, you'll enable Tomcat's security manager and
    instruct Catalina to use the `$TOMCAT_HOME/conf/catalina.policy` file. See
    the Enabling PACL section for more information.

5. Next, you should make sure that UTF-8 URI encoding is used consistently.
   Copy the `$TOMCAT_HOME/conf/server.xml` file to your server.

    Alternatively, if you have already customized your `server.xml` file, you
    can make a few simple edits instead. Edit your
    `$TOMCAT_HOME/conf/server.xml` file and add the attribute
    `URIEncoding="UTF-8"` whereever you see `redirectPort=8443`, in the
    definition of your connectors (HTTP and AJP). For example:

        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />

    should become

        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

    And

        <Connector port="8080" protocol="HTTP/1.1"
                   connectionTimeout="20000"
                   redirectPort="8443" />

    should become

        <Connector port="8080" protocol="HTTP/1.1"
                   connectionTimeout="20000"
                   redirectPort="8443" URIEncoding="UTF-8" />

6. If you're on Unix or Linux, navigate to your `$TOMCAT_HOME/bin` folder and
   run the following command

        chmod a+x *.sh

    This command makes the shell scripts in this folder executable.

## Deploying Liferay

Now you're ready to deploy Liferay using your Liferay WAR file.

1. If you are manually installing Liferay on a clean Tomcat server, delete the
   contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This removes the default
   Tomcat home page.

2. Extract the Liferay `.war` file to `$TOMCAT_HOME/webapps/ROOT`.

    Now it's time to launch Liferay Portal on Tomcat!

3. Start Tomcat by navigating to `TOMCAT_HOME/bin` and executing `./startup.sh`
   or `startup.bat`. Alternatively, you can use `./catalina.sh run` or
   `catalina.bat run`. Using one of the latter commands makes your terminal or
   command prompt tail Liferay's log file. This can be useful if you want to
   see the startup activities performed by Liferay.

Congratulations on successfully installing and deploying Liferay on Tomcat!
