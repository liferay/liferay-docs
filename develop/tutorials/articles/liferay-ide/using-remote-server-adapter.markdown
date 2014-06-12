# Using Liferay IDE's Remote Server Adapter

The *Remote Server Adapter* lets you deploy local plugin projects to remote
servers. You can use it to deploy apps you're developing to a test server for
your QA team or to a beta server for your users. It's easy to set up too.
Your server administrator simply installs Liferay (version 6.1 or newer),
installs the *Remote IDE Connector* app from [Liferay
Marketplace](http://www.liferay.com/marketplace), and creates a portal
administrator account for you. On your local
workstation, you simply make sure you have the same version of Liferay installed
and you connect to the remote server, via the Remote Server Adapter built into
your [Liferay 
IDE](http://www.liferay.com/downloads/liferay-projects/liferay-ide) or
[Developer
Studio](http://www.liferay.com/group/customer/products/developer-studio). How's
that for convenience! 

Of course, it's possible to install the Remote IDE Connector application on a
production server, but it creates an unnecessary security risk, so we don't
recommend it. On a production server, you shouldn't update, or hot-fix, plugins
using the adapter; your portal system administrator should use normal [plugin
management](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/plugin-management-liferay-portal-6-2-user-guide-14-en)
mechanism to apply plugin updates and fixes. 

This tutorial uses the Remote Server Adapter on Liferay Portal bundled with
Apache Tomcat, but you can use the adapter with Liferay Portal running on any
application server Liferay Portal supports. 

Now you're ready to configure and use the Remote Server Adapter! 

## Configuring and Using the Remote Server Adapter

To configure and use the Remote Server Adapter, follow these steps: 

1.  Make sure your remote Liferay Portal instance is running and that you can
    log in as a portal administrator with your account. 

2.  Launch Liferay IDE and open the new server wizard by clicking *File* &rarr;
    *New* &rarr; *Other*; select *Server* in the Server category and click
    *Next*. Select *Remote Liferay Server (Liferay 6.1)* in the Liferay, Inc.
    category.

3.  Enter the IP address of the remote Liferay Portal host into the *Server's
host name* field. Enter an arbitrary name for the *Server name*, like *Liferay @
IP_address_of_remote_server*, then click *Next*. 

    ![Figure 2: Configure the remote Liferay server's information.](../../images/remote_server_adapter_wizard_1.png)

4.  The New Server wizard's next page directs you to define the Liferay Portal
    runtime stub. Doing so enables plugin projects to use the runtime stub for
    satisfying their JAR file dependencies. Select the *Liferay bundle type*
    based on the version of your local Liferay bundle, browse to the *Liferay
    bundle directory*, select it, and click *Next*. 

5.  On the next page of the wizard, configure your connection to the remote
    Liferay instance: 
    - **Hostname:** Enter the IP address of your remote Liferay Portal
    instance's machine. 
    - **HTTP Port:** Enter the port it runs on (default: 8080). 
    - **Username** and **Password:** Enter your administrator credentials for
    the remote Liferay Portal instance. 

    The *Liferay Portal Context Path* and *Server Manager Context Path*
    default values reflect the defaults set in Liferay Portal
    instances. If a portal administrator changed these values on the remote
    portal instance, then enter those new values instead. Otherwise, keep the
    default values set in the wizard. 

6.  Click the *Validate Connection* button; if no warnings or errors appear,
    your connection works! If you get any warning or error messages in the
    configuration wizard, check your connection settings. 

7.  Once Liferay IDE connects to your remote Liferay Portal instance,
    click *Finish* in the Remote Liferay Server configuration wizard. 

    The new remote server appears in Liferay IDE's *Servers* tab. This tab
    appears in the bottom left corner of the Liferay IDE if you're using the
    Liferay perspective. The IDE connects to your remote server and displays the
    remote Liferay Portal instance's logs in the console. If your remote server
    is in debug mode, the adapter attaches Eclipse's Java debugger to the remote
    process automatically. 

8.  You can change the remote server settings at any time. 

    To change the settings, double-click on your remote server instance in the
    *Servers* tab to open the configuration editor, where you can modify the
    settings. The configuration editor also contains settings for publishing
    resources. You can choose to automatically publish resources after changes
    are made, automatically publish resources after a build event, or never to
    publish them automatically. 

    ![Figure 3: The remote server's configuration editor lets you modify remote settings, specify how resources are published, and set time limits for server operations.](../../images/remote_server_configuration_editor.png)

9.  As you desire, deploy plugins to the remote portal instance. 

As you deploy plugins to the remote server, your console displays messages
confirming their deployment. As you make changes to your plugin project,
you can republish the project for the changes take effect on the remote server.
To manually publish changes, right click on the server in the Servers view and
select *Publish*. 

Great! Now that you know how to use Liferay IDE to deploy plugin projects to 
remote servers, you can more easily integrate your remote portal instances into 
your testing workflow. 

