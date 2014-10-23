# Installing Liferay on WebSphere 8.0 [](id=installing-liferay-on-websphere-8-0)

**Tip:** Throughout this installation and configuration process, WebSphere 
prompts you to Click Save to apply changes to Master Configuration. Do so 
intermittently to save your changes.

**Liferay Home** is in a folder called `liferay` in the home folder of the user
ID that is running WebSphere.

## Preparing WebSphere for Liferay [](id=preparing-websphere-for-liferay)

When the application server binaries have been installed, start the **Profile
Management Tool** to create a profile appropriate for Liferay. 

1. Click on *Create…*. Choose *Application Server*. Click *Next*. 

2. Click the Advanced profile creation option and then click *Next*. Why
   Advanced? You can specify your own values for settings such as the location
   of the profile and names of the profile, node and host. You can assign your
   own ports. You can optionally choose whether to deploy the administrative
   console and sample application and also add web-server definitions if you
   wish. Web server definitions are used with IBM HTTP Server. For more
   information about these options, please see the WebSphere documentation. 

    ![Figure 14.48: Choose the Advanced profile option to specify your own settings.](../../images/Websphere-8_html_5a8a0a4b.png)

3. Check the box *Deploy administrative console*. This gives you a web-based UI
   for working with your application server. Skip the default applications.
   You'd only install these on a development machine. Click *Next*. 

4. Set profile name and location. Ensure you specify a performance tuning
   setting other than *Development*, since you're installing a server for
   production use. Click *Next*. 

    ![Figure 14.49: Use a performance tuning setting other than Development. We've selected Standard here. Please see the WebSphere documentation for further information about performance tuning settings.](../../images/Websphere-8_html_m3feb4e9f.png)

5. Choose node and host names for your server. These will be specific to your
   environment. Click *Next*. 

    ![Figure 14.50: Choose node and host names appropriate to your environment.](../../images/Websphere-8_html_1327271c.png)

6. Administrative security in WebSphere is a way to restrict who has access to
   the administrative tools. For simplicity, we've disabled it but you may want
   to have it enabled in your environment. Please see WebSphere's documentation
   for further information. Click *Next*. 

    ![Figure 14.51: We've disabled administrative security but you may want to enable it.](../../images/Websphere-8_html_2eb820b.png)

7. Each profile needs a security certificate, which comes next in the wizard. If
   you don't have certificates already, choose the option to generate a personal
   certificate and a signing certficate and click *Next*. 

8. Once the certificates are generated, set a password for your keystore. Click
   *Next*. 

    ![Figure 14.52: Set a password for your keystore.](../../images/Websphere-8_html_m6b754c08.png)

9. Next, you can customize the ports this server profile uses. Be sure to choose
   ports that are open on your machine. When choosing ports, installation
   detects existing WebSphere installations and if it finds activity, it
   increments ports by one. 

    ![Figure 14.53: WebSphere gives you a nice user interface for customizing the ports your server uses.](../../images/Websphere-8_html_m2eef7200.png)

10. If you want WebSphere to start automatically when the machine is booted, you
	configure it next. This differs by operating system. When you're finished
   configuring this the way you want, click *Next*. 

11. WebSphere ships with IBM HTTP Server, which is a rebranded version of
    Apache. If you want to front your WebSphere server with IBM HTTP Server,
    you'd configure this next. Please see WebSphere's documentation for details
    on this. When finished, click *Next*. 

12. WebSphere then creates your profile and finishes with a message telling you
	the profile was created successfully. You're now ready to install Liferay! 

## Copying Portal Dependencies [](id=copying-portal-dependencies)

Liferay ships with dependency .jars it needs to have on the global classpath.
These should be copied to WebSphere's global folder provided for this purpose: 

	[Install Location]/WebSphere/AppServer/lib/ext

Once you've copied the .jars here, start the server profile you're planning to
use for Liferay. Once it starts, you're ready to configure your database. 

## Database Configuration [](id=database-configuration)

If you want WebSphere to manage the database connections, follow the
instructions below. Note this is not necessary if you're planning on using
Liferay's standard database configuration; in that case, skip this section.
You'll set your database information in Liferay's setup wizard after the
install. 

![Figure 14.54: WebSphere JDBC providers](../../images/02-websphere-jdbc-providers.png) 

1. Start WebSphere.

2. Open the Administrative Console and log in.

3. Click *Resources &rarr; JDBC Providers*.

4. Click *New*.

5. For name, enter the name of JDBC provider (e.g. *MySQL JDBC Provider*).

6. For Implementation class name, enter:

        com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

7. Click *Next*.

8. Clear any text in classpath. You already copied the necessary `.jar`s to a
   location on the server's class path.

9. Click *Next*.

10. Click *Finish*.

11. Click *Data Sources* under *Additional Properties*.

12. Click *New*.

13. Enter a name: *liferaydatabasesource*.

14. Enter JNDI: `jdbc/LiferayPool`.

15. Everything else should stay at the default values. Save the data source.

16. When finished, go back into the data source and click *Custom Properties*
	and then click the *Show Filter Function* button. This is the second from
    last of the small icons under the *New* and *Delete* buttons.

17. Type *user* into the search terms and click *Go*.

    ![Figure 14.55: Modifying data source properties in WebSphere](../../images/02-modifying-data-source-properties-in-websphere.png) 

18. Select the user property and give it the value of the user name to your
	database. Click *OK* and save to master configuration.

19. Do another filter search for the url property. Give it a value that points
	to your database. For example, the MySQL URL would be:
    `jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false`.
    Click *OK* and save to master configuration.

20. Do another filter search for the password property. Enter the password for
	the user ID you added earlier as the value for this property. Click *OK* and
    save to master configuration.

21. Go back to the data source page by clicking it in the breadcrumb trail.
	Click the *Test Connection* button. It should connect successfully.

Once you've set up your database, you can set up your mail session. 

## Mail Configuration [](id=mail-configuration)

If you want WebSphere to manage your mail sessions, use the following procedure.
If you want to use Liferay's built-in mail sessions, you can skip this section. 

1. Click *Resources &rarr; Mail &rarr; Mail Providers*.

2. Click the Built-In Mail Provider for your node and server.

3. Click *Mail Sessions* and then click the *New* button.

4. Give it a name of *liferaymail* and a JNDI name of `mail/MailSession`. Click
   *OK* and save to master configuration.

5. Click *Security &rarr; Global Security* and deselect *Use Java 2 security to
   restrict application access to local resources* if it is selected. Click
   *Apply*.

Great! Now you're ready to deploy Liferay. 

## Deploy Liferay [](id=deploy-liferay)

1. Click *Applications &rarr; New Application &rarr; New Enterprise
   Application*.

2. Browse to the Liferay `.war` file and click *Next*.

3. Leave *Fast Path* selected and click *Next*, then click *Next* again.

4. Make sure your server is selected and click *Next*.

5. Keep the context root set to / and click *Next*.

6. Click *Finish*. When Liferay has installed, click *Save to Master
   Configuration*.

## Start Liferay [](id=start-liferay)

1. If you plan to use Liferay's setup wizard, skip to the next step. If you wish
   to use WebSphere's data source and mail session, create a file called
   `portal-ext.properties` in your Liferay Home folder. Place the following text
   in the file: 

        jdbc.default.jndi.name=jdbc/LiferayPool
        mail.session.jndi.name=mail/MailSession
        setup.wizard.enabled=false

2. Select the Liferay application and click *Start*. 

    ![Figure 14.56: Starting Liferay on WebSphere.](../../images/Websphere-8_html_m7420ff51.png)

3. In the setup wizard, select and configure your database type. Click *Finish*
   when you're done. 

    Liferay then creates the tables it needs in the database. 

Congratulations! You've installed Liferay on WebSphere! 
