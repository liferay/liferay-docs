# Using Liferay IDE's Remote Server Adapter [](id=using-liferay-ides-remote-server-adapter)

The Remote Server Adapter is a feature that lets you deploy your Liferay
projects to a remote Liferay Portal server. If you need to work with a 
development instance of Liferay that's on another machine, then the remote
server adapter is your solution. Your remote Liferay Portal instance must
satisfy two requirements to use a Remote Server Adapter: 

- It is version 6.1 or later. 
- It has the Remote IDE Connector application installed from Liferay
  Marketplace. Remote IDE Connector contains the `server-manager-web` plugin
  that provides an API for Liferay IDE's Remote Server Adapter to use for all
  its remote operations. 

The Remote Server Adapter lets developers deploy local projects to a remote
development server for testing purposes. If you're using Liferay IDE and want to
deploy projects to a remote server, the Remote IDE Connector application must be
installed on this server. Do not install the Remote IDE Connector
application on a production server: it creates an unnecessary security risk.
It is meant for use on development servers only. 

To start deploying projects to a remote server, download and install the
following resources on your local development machine:

- [Liferay
  IDE](http://www.liferay.com/downloads/liferay-projects/liferay-ide) from
  Liferay's downloads page or download Liferay Developer Studio from
  the Customer Portal. 
- [Liferay Portal CE or
  EE](http://www.liferay.com/downloads/liferay-portal/available-releases), to
  your local development machine. 

Of course, we're assuming you have a development server up and running. Our
demonstration uses the Remote Server Adapter on Liferay Portal bundled with
Apache Tomcat, but you can use the adapter with Liferay Portal on any
application server Liferay Portal supports. 

You still need to install a full Liferay development environment locally on your
machine before using the Remote Server Adapter, so that you can compile plugins
for deployment. Follow the instructions earlier in the chapter to get your
Liferay IDE or Liferay Developer Studio environment up and running. 

+$$$

**Important:** Keep a record of your portal administrator login credentials
(e.g., username/password) for your remote Liferay server; you'll need them to
configure your connection from Liferay IDE to the remote Liferay server. 

$$$

Let's start by configuring the Remote Server Adapter. 

## Configuring the Remote Server Adapter [](id=configuring-the-remote-server-adapter)

You can use Liferay IDE's Remote Server wizard to configure the Remote
Server Adapter and install the Remote IDE Connector to your Liferay instance.
Alternatively, you can install the Remote IDE Connector to your Liferay instance
before configuring Liferay IDE's Remote Server Adapter. To configure the Remote
Server Adapter, use the following steps:

1.  Start your remote Liferay Portal instance--verify that you can log in as an
    administrator. 

2.  Launch Liferay IDE and open the new server wizard by clicking *File* &rarr;
    *New* &rarr; *Other*; select *Server* in the Server category and click
    *Next*. Select *Remote Liferay Server (Liferay 6.1)* in the Liferay, Inc.
    category.

3.  Enter the IP address of the machine with the remote Liferay Portal instance
    into the *Server's host name* field. For the *Server name*, enter
    *Liferay@[IP address]*, then click *Next*. 

    ![Figure 10.15: Define the remote Liferay server.](../../images/remote_server_adapter_wizard_1.png)

4.  The New Server wizard's next page directs you to define the Liferay
    Portal runtime stub. Doing so allows projects created for your remote server
    to use the runtime stub for satisfying JAR dependencies needed to compile
    various Liferay projects. Select the *Liferay bundle type* based on the
    version of your local Liferay bundle, browse to the *Liferay bundle
    directory*, and click *Next*. 

5.  On the next page of the wizard, configure your connection to your remote
    Liferay instance:  

    - **Hostname:** Enter the IP address of your remote Liferay Portal instance's
    machine. 
    - **HTTP Port:** Enter the port it runs on (default: 8080). 
    - **Username** and **Password:** Enter your administrator credentials for
    the remote Liferay Portal instance.  

    Leave the *Liferay Portal Context Path* and *Server Manager Context Path*
    set to the defaults unless these values were changed for your remote Liferay
    Portal instance.  

6.  Your remote Liferay Portal instance needs the Remote IDE Connector
    application installed; otherwise, Liferay IDE can't connect to it. If you
    haven't installed Liferay IDE Connector yet, click the *Remote IDE Connector*
    link in the wizard. If you already downloaded the Remote IDE Connector
    application and installed it to your remote portal, skip to the next step and
    validate your connection. 

    Browse [Liferay Marketplace](http://www.liferay.com/marketplace) for the
    Remote IDE Connector application. When you've found it, click *Free* to
    purchase it. Follow the on-screen prompts. 

    Once you've purchased the application, navigate to the *Purchased* page of
    the Control Panel's Marketplace interface. 

    ![Figure 10.16: Click *Purchased* in the Marketplace section of the Control Panel to download and install the Remote IDE Connector application that you purchased.](../../images/remote_server_adapter_marketplace_2.png)

    Find your application in the list of purchased products. Then click on the
    buttons to download and install the application. Once it's been installed on
    your remote portal, return to the Remote Liferay Server configuration wizard
    in Liferay IDE. 

7.  Click the *Validate Connection* button; if no warnings or errors appear.
    your connection works! If you get any warning or error messages in the
    configuration wizard, check your connection settings. 

8.  Once Liferay IDE is connected to your remote Liferay Portal instance,
    click *Finish* in the Remote Liferay Server configuration wizard. 
   
    After you click *Finish*, the new remote server appears in Liferay IDE's
    *Servers* tab. This tab appears in the bottom left corner of the Eclipse
    window if you're using the Liferay perspective. If you entered your
    connection settings correctly, Eclipse connects to your remote server and
    displays the remote Liferay Portal instance's logs in the console. If your
    remote server is in debug mode, the Eclipse Java debugger is attached to the
    remote process automatically.

9.  You can change the remote server settings at any time. Double-click on your
    remote server instance in the *Servers* tab to open the configuration
    editor, where you can modify the settings. 

Now that your remote Liferay Portal server is configured, let's test the remote
server adapter!

## Using the Remote Server Adapter [](id=using-the-remote-server-adapter)

Once your remote Liferay Portal server is correctly configured and Liferay IDE
is connected to it, you can begin publishing projects to it and using it as you
would a local Liferay Portal server. 

Here's how to publish plugin projects to your remote server in Liferay IDE: 

1.  Right click on the server and choose *Add and Remove...*. 

    +$$$

    **Note:** Make sure you have available projects configured in Liferay IDE.
    If not, you'll get an error message indicating there are no available
    resources to add or remove from the server. 

    $$$

2.  Select the Liferay projects to publish to your remote server; click *Add* to
    add them to your remote server, then click *Finish*. Deployment begins
    immediately. 

    Your console displays a message that the plugin was successfully
    deployed if publication to the remote server was successful.

3.  As you make changes to your plugin project, republish them so they take
    effect on the remote server. To set your remote server's publication
    behavior, double click your remote server in the *Servers* tab. You can
    choose to automatically publish resources after changes are made,
    automatically publish after a build event, or never to publish
    automatically. To manually invoke the publishing operation after having
    modified project files, right click on the server in the Servers view and
    select *Publish*. 

Now that you've seen how you can deploy projects in Liferay IDE and Liferay
Developer Studio, we can begin delving into the features it offers. Next, we'll
explain Developer Studio's Kaleo Designer for Java and how you can design
workflows for enhanced business logic.

<!-- The rest of this chapter all has to do with Kaleo Designer. It's like we
completely skipped the features for which Liferay IDE was mainly designed: to
make writing Liferay plugins easier. We need to add the following topics to this
chapter: 

- Coverage of each plugin type: portlets, themes, hooks, Ext
- The Service Builder graphical editor
- The Layout Template graphial editor
- The tag library snippets
- JSF portlet projects
- Vaadin portlet projects

(those last two are lower priority, and JSF is a higher priority than Vaadin,
because LiferayFaces is a Liferay project.) 

I'd like to see the above topics added to this chapter, and then have the Kaleo
Designer broken out into its own chapter or included with the Workflow coverage,
since it's also mostly about creating workflows. -Rich -->
