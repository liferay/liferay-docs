# Installing Liferay DXP on WebSphere 8.5.5 [](id=installing-liferay-dxp-on-websphere-8-5-5)

+$$$

**Tip:** Throughout this installation and configuration process, WebSphere
prompts you to click *Save* to apply changes to the Master Configuration. Do so
intermittently to save your changes. 

$$$

For Liferay DXP to work correctly, WebSphere 8.5.5 Fix Pack 9 must be installed. 
You can find more information about this fix pack 
[here](http://www-01.ibm.com/support/docview.wss?uid=swg24041819). 

Please also note that the WebSphere Application Liberty Profile isn't supported
by Liferay. 

You should also read the following articles to familiarize yourself with
Liferay DXP's general installation steps: 

- [Liferay Installation Overview](/discover/deployment/-/knowledge_base/7-0/liferay-installation-overview)
- [Installing Liferay DXP](/discover/deployment/-/knowledge_base/7-0/installing-liferay-dxp)
- [Installing Liferay Manually](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually)

Note that the 
[*Liferay Home* folder](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home) 
is important to the operation of DXP. In Liferay Home, DXP creates certain files 
and folders that it needs to run. On WebSphere, Liferay Home is typically 
`[Install Location]/WebSphere/AppServer/profiles/your-profile/liferay`. 

And lastly, download Liferay DXP's WAR file and dependencies from the customer 
portal on 
[liferay.com](https://www.liferay.com/). 
You'll need the following files:

- `liferay-dxp-digital-enterprise-[version].war`: Liferay DXP WAR file

- `liferay-dxp-digital-enterprise-dependencies-[version].zip`: Liferay DXP 
  dependencies

- `liferay-dxp-digital-enterprise-osgi-[version].zip`: Liferay DXP OSGi 
  dependencies

Without any further ado, get ready to install Liferay DXP in WebSphere! 

## Preparing WebSphere for Liferay DXP [](id=preparing-websphere-for-liferay-dxp)

When the application server binaries have been installed, start the *Profile
Management Tool* to create a profile appropriate for Liferay. 

1. Click *Create...*, choose *Application Server*, and then click *Next*. 

2. Click the Advanced profile creation option and then click *Next*. Why 
   Advanced? You can specify your own values for settings such as the location
   of the profile and names of the profile, node and host. You can assign your
   own ports. You can optionally choose whether to deploy the administrative
   console and sample application and also add web-server definitions if you
   wish. Web server definitions are used with IBM HTTP Server. See the WebSphere 
   documentation for more information about these options. 

    ![Figure 1: Choose the Advanced profile option to specify your own settings.](../../images-dxp/websphere-01-profile.png)

3. Check the box *Deploy the administrative console*. This gives you a web-based
   UI for working with your application server. Skip the default applications.
   You'd only install these on a development machine. Click *Next*. 

4. Set the profile name and location. Ensure you specify a performance tuning
   setting other than *Development*, since you're installing a production 
   server. See the WebSphere documentation for more information about 
   performance tuning settings. Click *Next*. 

5. Choose node, server, and host names for your server. These will be specific 
   to your environment. Click *Next*. 

6. Administrative security in WebSphere is a way to restrict who has access to
   the administrative tools. You may want to have it enabled in your environment 
   so that a user name and password are required to administer the WebSphere 
   server. See WebSphere's documentation for more information. Click *Next*. 

7. Each profile needs a security certificate, which comes next in the wizard. If
   you don't have certificates already, choose the option to generate a personal
   certificate and a signing certificate and click *Next*. 

8. Once the certificates are generated, set a password for your keystore. Click
   *Next*. 

9. Next, you can customize the ports this server profile uses. Be sure to choose
   ports that are open on your machine. When choosing ports, the wizard
   detects existing WebSphere installations and if it finds activity, it
   increments ports by one. 

10. Choose whether you want this profile started when the machine starts. Click
    *Next*. 

11. WebSphere ships with IBM HTTP Server, which is a rebranded version of
    Apache. Choose whether you want a web server definition, so that this JVM
    receives requests forwarded from the HTTP server. See WebSphere's 
    documentation for details on this. When finished, click *Next*. 

12. The wizard then shows you a summary of what you selected, enabling you to
    keep your choices or go back and change something. When you're satisfied, 
    click *Next*. 

WebSphere then creates your profile and finishes with a message telling you the
profile was created successfully. Awesome! Your profile is complete. Now there's 
a few things you need to configure in your application server. 

In this version of WebSphere, servlet filters are not initialized on web
application startup, but rather, on first access. This can cause problems when
deploying certain apps to Liferay DXP. To configure servlet filters to 
initialize on application startup (i.e., deployment), you need to set the 
following `webcontainer` custom properties in your WebSphere application server: 

    com.ibm.ws.webcontainer.initFilterBeforeInitServlet = true
    com.ibm.ws.webcontainer.invokeFilterInitAtStartup = true 

To set `webcontainer` custom properties in the WebSphere application server, 
follow the instructions 
[here in WebSphere's documentation](http://www-01.ibm.com/support/docview.wss?rss=180&uid=swg21284395). 

Next, in the WebSphere profile you created for Liferay, you must set an argument 
that supports Liferay DXP's Java memory requirements. Do this now using the 
following instructions: 

- `[Install Location]/WebSphere/AppServer/profiles/your-profile/config/cells/your-cell/nodes/your-node/servers/your-server/server.xml`: 
  Add `maximumHeapSize="1024"` inside the `jvmEntries` tag. For example:

        <jvmEntries xmi:id="JavaVirtualMachine_1183122130078" ... maximumHeapSize="1024">

Lastly, in the same profile, you should delete a problematic 
`secureSessionCookie` tag that can cause DXP startup errors. Do this now using 
the following instructions: 

- `[Install Location]/WebSphere/AppServer/profiles/your-profile/config/cells/your-cell/cell.xml`: 
  Delete the `secureSessionCookie` tag containing 
  `xmi:id="SecureSessionCookie_1"`. 

Great! Now you're ready to install Liferay DXP. 

## Installing Liferay DXP's Dependencies [](id=installing-liferay-dxps-dependencies)

You must now install Liferay DXP's dependencies. Recall that earlier you 
downloaded two ZIP files containing these dependencies. Install their contents 
now: 

1. `liferay-dxp-digital-enterprise-dependencies-[version].zip`: Unzip this file 
   and place its contents in your WebSphere application server's 
   `[Install Location]/WebSphere/AppServer/lib/ext` folder. If you have a JDBC 
   database driver `JAR`, copy it to this location as well. 

2. `liferay-dxp-digital-enterprise-osgi-[version].zip`: Unzip this file and 
   place its contents in the `[Liferay Home]/osgi` folder (create this folder if 
   it doesn't exist). This is typically 
   `[Install Location]/WebSphere/AppServer/profiles/your-profile/liferay/osgi`. 

Once you've installed these dependencies, start the server profile you created 
for Liferay DXP. Once it starts, you're ready to configure your database. 

## Database Configuration [](id=database-configuration)

If you want WebSphere to manage the database connections, follow the 
instructions below. Note this is not necessary if you're planning on using 
Liferay's standard database configuration; in that case, skip this section. 
You'll set your database information in Liferay DXP's setup wizard after the 
install. 

+$$$

**Note:** Although Liferay DXP's default database is fine for testing purposes, 
you **should not** use it for production Liferay DXP instances. 

$$$

![Figure 2: WebSphere JDBC providers](../../images-dxp/websphere-jdbc-providers.png) 

1. Start WebSphere. 

2. Open the Administrative Console and log in. 

3. Click *Resources &rarr; JDBC Providers*. 

4. Select a scope, and then click *New*. 

5. Select your database type, provider type, and implementation type. If you 
   select a predefined database, the wizard fills in the name and description 
   fields for you. If the database you want to use isn't listed, select 
   *User-defined* from the *Database type* field, and then fill in the 
   *Implementation Class Name*. For example, if you're using MySQL, select 
   *Database type* &rarr; *User-defined*, and then enter 
   `com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource` in 
   *Implementation Class Name*. Click *Next* when you're finished. 

6. Clear any text in the classpath settings. You already copied the necessary 
   JARs to a location on the server's classpath. Click *Next*. 

7. Review your settings, and click *Finish*. 

8. Click your new provider configuration when it appears in the table, and then 
    click *Data Sources* under *Additional Properties*. Click *New*.

9. Enter *liferaydatabasesource* in the *Data source name* field, and 
    `jdbc/LiferayPool` in the *JNDI name* field. Click *Next*.

10. Click *Next* in the remaining screens of the wizard to accept the default 
    values. Then review your changes and click *Finish*.

11. Click the data source when it appears in the table and then click *Custom 
    Properties*. Now click the *Show Filter Function* button. This is the second 
    from last of the small icons under the *New* and *Delete* buttons. 

12. Type *user* into the search terms and click *Go*.

    ![Figure 3: Modifying data source properties in WebSphere](../../images-dxp/websphere-database-properties.png) 

13. Select the user property and give it the value of the user name to your
    database. Click *OK* and save to master configuration.

14. Do another filter search for the url property. Give this property a value 
    that points to your database. For example, the MySQL URL would be:
    `jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false`. 
    Click *OK* and save to master configuration. 

15. Do another filter search for the password property. Enter the password for
    the user ID you added earlier as the value for this property. Click *OK* and
    save to master configuration. 

16. Go back to the data source page by clicking it in the breadcrumb trail. 
    Click the *Test Connection* button. It should connect successfully. 

Once you've set up your database, you can set up your mail session. 

## Mail Configuration [](id=mail-configuration)

If you want WebSphere to manage your mail sessions, use the following procedure.
If you want to use Liferay's built-in mail sessions, you can skip this section. 

1. Click *Resources &rarr; Mail &rarr; Mail Providers*.

2. Click the Built-In Mail Provider for your node and server.

3. Click *Mail Sessions* and then click the *New* button.

4. Give your mail session a name of *liferaymail* and a JNDI name of 
   `mail/MailSession`. Fill in the correct information for your mail server in 
   the sections *Outgoing Mail Properties* and *Incoming Mail Properties*. Click 
   *OK* and then save to the master configuration. 

5. Click your mail session when it appears in the table, and select *Custom 
   Properties* under the *Additional Properties* section. Set any other JavaMail 
   properties required by your mail server, such as the protocol, ports, whether 
   to use SSL, and so on. 

5. Click *Security &rarr; Global Security* and de-select *Use Java 2 security to
   restrict application access to local resources* if it is selected. Click
   *Apply*. 

Note that you may also need to retrieve a SSL certificate from your mail server 
and add it to WebSphere's trust store. See WebSphere's documentation for 
instructions on this. 

## Deploy Liferay DXP [](id=deploy-liferay-dxp)

Now you're ready to deploy Liferay DXP! Use these instructions to do so:

1. In WebSphere's administrative console, click *Applications* &rarr; *New 
   Application* &rarr; *New Enterprise Application*. 

2. Browse to the DXP `.war` file and click *Next*. 

3. Leave *Fast Path* selected and click *Next*. Ensure that *Distribute
   Application* has been checked, and click *Next* again. 

4. Choose the WebSphere runtimes and/or clusters where you want DXP deployed. 
   Click *Next*. 

5. Select the virtual host to deploy DXP on, and click *Next*. 

6. Map DXP to the root context (/) and click *Next*. 

7. Select the *metadata-complete attribute* setting that you want to use, and 
   click *Next*. 

8. Ensure that you have made all the correct choices and click *Finish*. When
   DXP has installed, click *Save to Master Configuration*. 

    ![Figure 4: Review your deployment options before deploying.](../../images-dxp/websphere-deploy-dxp.png)

You've now installed Liferay DXP!

## Enable Security for Portal Access Control Lists [](id=enable-security-for-portal-access-control-lists)

When you are ready to start using apps from Liferay Marketplace, you'll want to 
protect your portal and your WebSphere server from security threats. To do so, 
you must enable Java Security on your WebSphere server and specify a security 
policy to grant Liferay DXP access to your server. 

In WebSphere's administrative console, go to *Security* &rarr; 
*Global Security*. Check the box to enable Java 2 security, and click *Apply*. 
Save to the master configuration. 

![Figure 5: Enabling security can be done by checking one box, but it still needs to be configured.](../../images-dxp/websphere-security.png)

Now you must configure the security policy for the IBM JRE that WebSphere runs 
on. With your WebSphere server shut down, open the following security policy 
file: 

    [your-WebSphere-installation-directory]/java_1.8_64/jre/lib/security/java.policy

Replace this file's contents with the following: 

    grant {
        permission java.security.AllPermission;
    };

This configuration opens all permissions--you can fine-tune your policy's 
permissions later. 

Great! Now you're ready to start Liferay. 

## Start Liferay DXP [](id=start-liferay-dxp)

1. If you plan to use Liferay's setup wizard, skip to the next step. If you wish
   to use WebSphere's data source and mail session, create a file called
   `portal-ext.properties` in your Liferay Home folder. Place the following text
   in the file: 

        jdbc.default.jndi.name=jdbc/LiferayPool
        mail.session.jndi.name=mail/MailSession
        setup.wizard.enabled=false

2. In the WebSphere administrative console, navigate to *Enterprise 
   Applications*, select the Liferay DXP application, and click *Start*. While 
   DXP is starting, WebSphere displays a spinny little graphic. Don't watch it 
   too closely, or you might get hypnotized. 

3. In DXP's setup wizard, select and configure your database type. Click 
   *Finish* when you're done. Liferay then creates the tables it needs in the 
   database. 

Congratulations! You've installed Liferay on WebSphere!
