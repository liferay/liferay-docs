# Installing @product@ on tc Server [](id=installing-liferay-on-tc-server)

Installing @product@ on tc server requires the following files: 

- A @product@ WAR file

- Dependencies .zip file

- OSGi JARs .zip file

Here are the basic steps for installing @product@ on tc Server: 

- Installing @product@ dependencies to your application server

- Configuring your application server for @product@

- Deploying the @product@ WAR file to your application server

+$$$

**Note:** You'll see the term
[*Liferay Home*](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
used in this installation guide. *Liferay Home* refers to the folder containing
your tc Server instance and some @product@-specific folders:. `data`, `deploy`,
`licenses`, and `osgi` folders. 

$$$

## Creating a tc Server [](id=creating-a-tc-server)

1.  Download and unzip a tc Server, available 
    [here](https://network.pivotal.io/products/pivotal-tcserver). 
    This is referred to as `[TCSERVER_INSTANCE_HOME]`. 

2.  Create a folder called `servers` inside `[TCSERVER_INSTANCE_HOME]`. (e.g. 
    `/opt/pivotal-tc-server/servers`). This folder becomes *Liferay Home* (see
    note above), and you should not confuse the two.

3.  Next, create an instance called *dxp-server* to deploy @product@. Navigate
    to the `[TCSERVER_INSTANCE_HOME]/developer-4.0.0.RELEASE` folder and run
    this command:

        tcserver create -i ../servers dxp-server

    By default, new instances are created in the 
    `[TCSERVER_INSTANCE_HOME]/instances` folder. The `-i` specifies where to
    create the folder. The instance location can also be changed via
    a properties file. See the 
    [tc Server documentation](https://tcserver.docs.pivotal.io/4x/docs-tcserver/topics/tcserver-properties-reference.html)
    for more information.
 
Checkpoint: 

1.  A new folder called `servers` has been created. 

2.  A new folder called `dxp-server` has been created inside the `servers` 
    folder. The following folders have been created inside the `dxp-server` 
    folder: 

    - `bin` 
    - `conf` 
    - `lib` 
    - `logs` 
    - `temp` 
    - `webapps` 
    - `work`

Next you can install the required dependencies.

## Installing @product@ Dependencies [](id=installing-liferay-dependencies)

@product@ depends on additional JARs that aren't included with tc Server by
default. There are even more JARs that you'd find in a @product@ bundle that are
not required but can be useful. If you don't have a @product@ bundle, you can 
download the required JARs from third parties, as described below.

+$$$

**Note:** Many required and useful JARs are pre-installed when you build 
@product@ from the source code or 
[download a @product@ bundle](https://web.liferay.com/group/customer/dxp/downloads/7-1). 
If you want to acquire all of the JARs that ship with a @product@ bundle 
quickly, using one of these sources might save you time.

$$$

1.  Extract the JARs from the dependencies ZIP to the 
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/lib` folder. The JARs are 
    listed below:

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

2.  Download the following JARs or copy them from a @product@ bundle to the 
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/lib` folder:

    - [`activation.jar`](http://www.oracle.com/technetwork/java/javase/jaf-136260.html)
    - [`ccpp.jar`](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)
    - [`jms.jar`](http://www.oracle.com/technetwork/java/docs-136352.html)
    - [`jta.jar`](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
    - [`jutf7.jar`](http://mvnrepository.com/artifact/com.beetstra.jutf7/jutf7)
    - [`mail.jar`](http://www.oracle.com/technetwork/java/index-138643.html)
    - [`persistence.jar`](http://mvnrepository.com/artifact/org.eclipse.persistence/javax.persistence/2.1.1)
    - [`support-tomcat.jar`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.support.tomcat)

3.  Copy the JDBC driver for your database to the 
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/lib` folder. Here are some 
    common drivers: 
    
    - [`mariadb.jar`](https://downloads.mariadb.org/)
    - [`mysql.jar`](http://dev.mysql.com/downloads/connector/j)
    - [`postgresql.jar`](https://jdbc.postgresql.org/download/postgresql-42.0.0.jar)

4.  Create an `osgi` folder in your *Liferay Home*. Extract the folders 
    (i.e., `configs`, `core`, and more) from OSGi ZIP file to the `osgi` folder.
    The `osgi` folder provides the necessary modules for @product@'s OSGi
    runtime.

**Checkpoint:**

1.  Your `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/lib` folder has these 
    JARs:

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

## Configuring tc Server [](id=configuring-tc-server)

There are a few configuration edits to make so @product@ runs well on tc Server.
All of these configuration changes should be made in your tc Server runtime
instance.

1.  Navigate to the 
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/bin` folder. In `setenv.sh` 
    replace this line:

        JVM_OPTS="-Xmx512M -Xss256K"

    with this one

        JVM_OPTS="-Xmx2048M -Xss512K -XX:MaxMetaspaceSize=512m"

    In `setenv.bat` replace

        set JVM_OPTS=-Xmx512M -Xss256K
					
    with

        set JVM_OPTS=-Xmx2048M -Xss512K -XX:MaxMetaspaceSize=512m


2.  Next, you should make sure that UTF-8 URI encoding is used consistently. 
    Open `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/server.xml` and 
    make sure the `Connector` tag includes setting the `URIEncoding` to `UTF-8`.

        <Connector acceptCount="100"
                   connectionTimeout="20000"
                   executor="tomcatThreadPool"
                   maxKeepAliveRequests="15"
                   port="${bio.http.port}"
                   protocol="org.apache.coyote.http11.Http11Protocol"
                   redirectPort="${bio.https.port}"
                   URIEncoding="UTF-8" />


3.  If you're installing @product@ and tc Server on Windows, open
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/wrapper.conf` and 
    replace

        wrapper.java.additional.8=-Xss256K

    with

        wrapper.java.additional.8=-Xmx2048M

    and add these properties:

        wrapper.java.additional.9=-Xss512K
        wrapper.java.additional.10=-XX:MaxMetaspaceSize=256M
        wrapper.java.additional.11=-Dfile.encoding=UTF-8

4.  Finally, open `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/web.xml` and 
    add the following configuration after
    `<load-on-startup>3</load-on-startup>`: 

        <init-param>
            <param-name>compilerSourceVM</param-name>
            <param-value>1.8</param-value>
        </init-param>
        <init-param>
            <param-name>compilerTargetVM</param-name>
            <param-value>1.8</param-value>
        </init-param> 

### Database Configuration [](id=database-configuration)

The easiest way to handle your database configuration is to let @product@ manage 
your data source. If you want to use @product@'s built-in data source, you can 
skip this section. 

If you want tc Server to manage your data source, follow these steps:

1.  Make sure your database server is installed and working. If it's installed
    on a different machine, make sure it's accessible from your @product@ 
    machine.

2.  Add your data source as a resource in the context of your web application 
    specified in 
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/Catalina/localhost/ROOT.xml` 
    (create this file if you don't have it already). An example configuration is 
    shown below:

        <Context crossContext="true" path="">
          <Resources>
        		<PreResources
        			base="${catalina.base}/lib/ext/portal"
        			className="com.liferay.support.tomcat.webresources.ExtResourceSet"
        			webAppMount="/WEB-INF/lib"
        		/>
        	</Resources>
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

    The resource definition above is for a MySQL database named `lportal`that
    has a user named `root` whose password is `root`. Replace these values with
    your own. 

3.  In a `portal-ext.properties` file in Liferay Home, specify your data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

Your data source is now configured. Next set up the mail session.

### Mail Configuration [](id=mail-configuration)

As with database configuration, the easiest way to configure mail is to let
@product@ handle your mail session. If you want to use @product@'s built-in mail
session, skip this section and 
[configure the mail session](/discover/deployment/-/knowledge_base/7-1/installing-product#configuring-mail)
in the Control Panel.

If you want to manage your mail session with tc Server, follow these steps: 

1.  Edit 
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/Catalina/localhost/ROOT.xml` 
    and add your mail session as a `Resource` in your web application `Context`.
    Make sure to replace the example mail session values with your own. 

        <Context crossContext="true" path="">
          <Resources>
            <PreResources
              base="${catalina.base}/lib/ext/portal"
              className="com.liferay.support.tomcat.webresources.ExtResourceSet"
              webAppMount="/WEB-INF/lib"
            />
          </Resources>
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

You created a mail session for tc Server to manage and configured @product@ to
use it. 

## Deploying @product@ [](id=deploying-liferay)

Now you're ready to deploy @product@ using your @product@ WAR file.

1.  If you are manually installing @product@ on a clean tc Server instance, 
    delete the contents of the 
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/webapps/ROOT` 
    directory. This removes the default home page.

2.  Extract the @product@ `.war` file to 
    `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/webapps/ROOT`.

    Now it's time to launch @product@!

3.  Start tc Server by navigating to the runtime instance's 
    `[TCSERVER_INSTANCE_HOME]/developer-4.0.0.RELEASE` folder 
    and run the following command:

        tcserver run -i ../servers dxp-server

Congratulations on successfully installing and deploying @product@ on tc Server!
