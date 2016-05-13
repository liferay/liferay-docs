# Installing Liferay on JBoss 5.1 [](id=installing-liferay-on-jboss-5-1)

Note: Java 7 deprecated some classes used by JBoss 5.1. Use Java 5 or Java 6 to
run JBoss 5.1.

**Liferay Home** is one folder above JBoss's install location.

1. Download and install JBoss EAP 5.1.x into your preferred directory. This
   directory is referred to as `$JBOSS_HOME` throughout this section.

2. Download the latest version of the Liferay Portal `.war` file.

3. Download Liferay's Portal Dependencies.

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on JBoss.

## Configuring Dependencies [](id=configuring-dependencies)

First we'll take care of dependencies and potential conflicts.

1. Unzip Liferay's dependencies to `$JBOSS_HOME/server/default/lib`.

2. Download your database driver `.jar` file and put it into the folder as
   well. For demonstration purposes, we'll download the MySQL Connector/J
   driver from [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)
   and put its `.jar` file into the `$JBOSS_HOME/server/default/lib` folder.

3. Next we'll delete JBoss's Hibernate Validator and HSQL JARs to prevent
   conflicts with Liferay's JARs. Remove the following files from
   `$JBOSS_HOME/common/lib`:

        hibernate-validator.jar
        hsqldb.jar
        hsqldb-plugin.jar

Next we need to clean up the entries for the JAR files that we deleted.

1. Open `$JBOSS_HOME/server/default/conf/login-config.xml` in a text editor.

2. Comment out the blocks with the name `HsqlDBRealm` and `JmsXARealm` around
   lines 41-64.

We'll also delete some other files that can cause conflicts with Liferay when
it's deployed.

1.  Remove the following directories and files from `$JBOSS_HOME/server/default/deploy`:
    - /messaging
    - /profileservice-secured.jar
    - /uuid-key-generator.sar
    - ejb2-container-jboss-beans.xml
    - ejb2-timer-service.xml
    - ejb3-connectors-jboss-beans.xml
    - ejb3-container-jboss-beans.xml
    - ejb3-interceptors-aop.xml
    - ejb3-timerservice-jboss-beans.xml
    - hsqldb-ds.xml
    - jms-ra.rar
    - mail-ra.rar
    - mail-service.xml

2. Delete the following in `$JBOSS_HOME/server/default/deployers`:

        jboss-ejb3-endpoint-deployer.jar
        messaging-definitions-jboss-beans.xml

## Deploying Liferay [](id=deploying-liferay)

Now that we've added all of the necessary dependencies and removed unnecessary
files, it's time to deploy Liferay.

1. Navigate to `$JBOSS_HOME/server/default/deploy/ROOT.war` and delete all
   the content of the folder.

2. Extract the contents of the Liferay WAR file into this folder.

3. Create a file named `jboss-classloading.xml` in the
   `$JBOSS_HOME/server/default/ROOT.war/WEB-INF` directory and add the
   following contents to it:

        <classloading xmlns="urn:jboss:classloading:1.0"
                parent-first="false"
                domain="LiferayDomain"
                export-all="NON_EMPTY" 
                import-all="true">
        </classloading>

    This configuration file defines a domain that does not allow parent classes
    to load first. Instead, Liferay Portal's classes are exported. Since JBoss
    comes with its own Hibernate JARs, the above configuration is needed to tell
    Liferay to ignore these JARs and to use its own JARs instead. If you omit
    this configuration, you may encounter a Hibernate exception. It's also
    necessary to add a `jboss-classloading.xml` file to the `WEB-INF` folder of
    each Liferay plugin; see the *Deploying plugins* section below.

4. Create a `portal-ext.properties` file in `$LIFERAY_HOME` (one level above
    `$JBOSS_HOME`) and add the following properties:

        hibernate.validator.apply_to_ddl=false
        hibernate.validator.autoregister_listeners=false

5. Delete the following files from the `$JBOSS_HOME/server/default/deploy/ROOT.war/WEB-INF/lib`:
    - jaxrpc.jar
    - stax.jar
    - xercesImpl.jar
    - xml-apis.jar

6. Add the following lines to your `portal-ext.properties` file:

	NOTE: The autodeploy folder must be set with the full name of the folder;
	you can't use any variables to define the location.

		auto.deploy.jboss.dest.dir=$JBOSS_HOME/server/default/deploy 
		auto.deploy.deploy.dir=$LIFERAY_HOME/deploy

	The first line is the value for the default server. The path in the second
	line should point to the `deploy` folder configured for JBoss.

7. Increase the memory given to the JVM

    By deafult JBoss gives 512MB memory to the JVM. With this setting it is most
    likely that you will face the following error at startup, runtime or shutdown:

    java.lang.OutOfMemoryError: GC overhead limit exceeded

    To give the JVM more memory, edit `$JBOSS_HOME/bin/run.conf.bat` (`run.conf`
    on *nix) and change `-Xmx512M` to `-Xmx1024M`.

8. Start the JBoss Application Server.

Liferay is now successfully installed on JBoss 5.1. 

## Deploying Plugins [](id=deploying-plugins)

Add a `jboss-classloading.xml` to the `WEB-INF` folder of each plugin, with the
following content:

	<classloading xmlns="urn:jboss:classloading:1.0"
		domain="PLUGINNAME-portlet"
		parent-domain="LiferayDomain"
		parent-first="false"
		top-level-classloader="false"
		export-all="NON_EMPTY"
		import-all="false">
	</classloading>

The `LiferayDomain` referenced in the above configuration is the domain we
defined above during step 3 of the *Deploying Liferay* section. Configuring
plugins to use the Liferay domain ensures that if JBoss and Liferay have
different versions of a JAR file, the plugin will use Liferay's version. Without
this configuration, Liferay plugins might end up using the wrong versions of JAR
files. You can make this configuration either before or after the plugin WAR has
been deployed, as long as JBoss is not running. Of course, it's best to make
this configuration before deployment. Otherwise, if there's a JAR conflict,
you'll have to shut down your server, configure the plugin to use the Liferay
domain, and restart the server.

You should've already set up your hot deploy folder in the previous section. To
deploy your plugins, simply copy them into your configured Liferay Home `deploy`
folder, and they are automatically copied to JBoss's default deploy folder.

Liferay also runs on JBoss 7. Let's see how we'd get it installed on that
version of JBoss. 
