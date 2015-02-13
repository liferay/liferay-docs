# Installing Liferay on WebSphere 8.5 [](id=installing-liferay-on-websphere-8-5)

+$$$

**Tip:** Throughout this installation and configuration process, WebSphere
prompts you to Click Save to apply changes to Master Configuration. Do so
intermittently to save your changes.

$$$

**Liferay Home** is in a folder called `liferay` in the home folder of the user
ID that is running WebSphere. 

To work correctly on WebSphere 8.5, IBM's PM90932 patch must be installed. You
can find more information about this patch
[here](http://www-01.ibm.com/support/docview.wss?uid=swg1PM90932). 

Please also note that the WebSphere Application Liberty Profile is not supported
by Liferay. 

## Preparing WebSphere for Liferay [](id=preparing-websphere-for-liferay)

When the application server binaries have been installed, start the **Profile
Management Tool** to create a profile appropriate for Liferay. 

1. Click on *Create...*. Choose *Application Server*. Click *Next*. 

2. Click the Advanced profile creation option and then click *Next*. Why
   Advanced? You can specify your own values for settings such as the location
   of the profile and names of the profile, node and host. You can assign your
   own ports. You can optionally choose whether to deploy the administrative
   console and sample application and also add web-server definitions if you
   wish. Web server definitions are used with IBM HTTP Server. For more
   information about these options, please see the WebSphere documentation. 

    ![Figure 1.9: Choose the Advanced profile option to specify your own settings.](../../images/websphere-01-profile.png)

3. Check the box *Deploy the administrative console*. This gives you a web-based
   UI for working with your application server. Skip the default applications.
   You'd only install these on a development machine. Click *Next*. 

4. Set profile name and location. Ensure you specify a performance tuning
   setting other than *Development*, since you're installing a server for
   production use. Please see the WebSphere documentation for further
   information about performance tuning settings. Click *Next*. 

5. Choose node, server, and host names for your server. These will be specific to your
   environment. Click *Next*. 

6. Administrative security in WebSphere is a way to restrict who has access to
   the administrative tools. You may want to have it enabled in your
   environment so that a user name and password are required to administer the
   WebSphere server. Please see WebSphere's documentation for further
   information. Click *Next*. 

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
    receives requests forwarded from the HTTP server. Please see WebSphere's
    documentation for details on this. When finished, click *Next*. 

12. The wizard then shows you a summary of what you selected, enabling you to
    keep your choices or go back and change something. When you're satisfied, click
    *Next*. 

![Figure 1.10: The Summary page shows you what you selected, giving you the chance to go back and change something if it's not exactly what you want.](../../images/websphere-03-summary.png)

WebSphere then creates your profile and finishes with a message telling you the
profile was created successfully. Awesome! Your profile is complete. There's one
more thing you'll need to configure.

In this version of WebSphere, servlet filters are not initialized on web
application startup, but rather, on first access. This can cause problems when
deploying certain plugins to Liferay Portal. To configure servlet filters to
initialize on application startup (i.e., deployment), you need to set the
following `webcontainer` custom properties in your WebSphere application server:

    com.ibm.ws.webcontainer.initFilterBeforeInitServlet = true
    com.ibm.ws.webcontainer.invokeFilterInitAtStartup = true

To set `webcontainer` custom properties in the WebSphere application server,
follow the instructions
[here](http://www-01.ibm.com/support/docview.wss?rss=180&uid=swg21284395).

You're now ready to install Liferay! 

## Copying portal dependencies [](id=copying-portal-dependencies)

Liferay ships with dependency .jars it needs to have on the global classpath.
These should be copied to WebSphere's global folder provided for this purpose: 

    [Install Location]/WebSphere/AppServer/lib/ext

If you have a JDBC database driver .jar, copy it to this location as well. 
Once you've copied the .jars, start the server profile you created for
Liferay. Once it starts, you're ready to configure your database. 

## Database Configuration [](id=database-configuration)

If you want WebSphere to manage the database connections, follow the
instructions below. Note this is not necessary if you're planning on using
Liferay's standard database configuration; in that case, skip this section.
You'll set your database information in Liferay's setup wizard after the
install. 

![Figure 1.11: WebSphere JDBC providers](../../images/02-websphere-jdbc-providers.png) 

1. Start WebSphere.

2. Open the Administrative Console and log in.

3. Click *Resources &rarr; JDBC Providers*.

4. Click *New*.

5. For name, enter the name of JDBC provider (e.g. *MySQL JDBC Provider*).

6. For Implementation Class Name, enter:

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

    ![Figure 1.12: Modifying data source properties in WebSphere](../../images/02-modifying-data-source-properties-in-websphere.png) 

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

5. Click *Security &rarr; Global Security* and de-select *Use Java 2 security to
   restrict application access to local resources* if it is selected. Click
   *Apply*.

Great! Now you're ready to deploy Liferay. 

## Deploy Liferay [](id=deploy-liferay)

1. Click *Applications* &rarr; *New Application* &rarr; *New Enterprise
   Application*.

2. Browse to the Liferay `.war` file and click *Next*.

3. Leave *Fast Path* selected and click *Next*. Ensure that *Distribute
   Application* has been checked, and click *Next* again.

4. Choose the WebSphere runtimes and/or clusters where you want Liferay
   deployed. Click *Next*.

5. Map Liferay to the root context (/) and click *Next*. 

6. Ensure that you have made all the correct choices and click *Finish*. When
   Liferay has installed, click *Save to Master Configuration*.

You've now installed Liferay, but don't start it yet. If you wish to use PACL,
you have one more thing to configure. 

## Enabling Security for Portal Access Control Lists [](id=enabling-security-for-portal-access-control-lists)

In the administrative console, go to *Security* $rarr; *Global Security*.  Check
the box to enable Java 2 security, and click *Apply*. Save to the master
configuration. 

   ![Figure 1.13: Enabling security can be done by checking one box, but it still needs to be configured. ](../../images/websphere-05-liferay-enable-security.png)

Next, you need to configure security for the Liferay profile you created. This
requires editing a text file, which can be found nested several folders deep in
WebSphere's `profiles` directory. The exact path depends on how you've named
your profile, but it will be something like this: 

    [profile_root]/config/cells/[cell_name]/nodes/[node_name]/app.policy

First, in each existing `grant` section, replace the content with
`java.security.AllPermission;`. Then add the following lines to the bottom of
the file: 

    grant codeBase "file:${was.install.root}/lib/-" {
      permission java.security.AllPermission;
    };

    grant codeBase "file:${was.install.root}/plugins/-" {
      permission java.security.AllPermission;
    };

    grant codeBase "file:${server.root}/-" {
      permission java.security.AllPermission;
    };

Save the file. You should now stop the profile and restart it. Once it comes up,
you're ready to start Liferay. 

## Start Liferay [](id=start-liferay)

1. If you plan to use Liferay's setup wizard, skip to the next step. If you wish
   to use WebSphere's data source and mail session, create a file called
   `portal-ext.properties` in your Liferay Home folder. Place the following text
   in the file: 

        jdbc.default.jndi.name=jdbc/LiferayPool
        mail.session.jndi.name=mail/MailSession
        setup.wizard.enabled=false

2. Select the Liferay application and click *Start*. 

    ![Figure 1.14: While Liferay is starting, WebSphere displays this spinny little graphic. Don't watch it too closely, or you might get hypnotized.](../../images/websphere-04-liferay-starting.png)

3. In the setup wizard, select and configure your database type. Click *Finish*
   when you're done. 

    Liferay then creates the tables it needs in the database. 

Congratulations! You've installed Liferay on WebSphere!
