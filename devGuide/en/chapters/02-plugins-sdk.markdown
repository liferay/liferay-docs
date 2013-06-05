# Liferay Developer Tools 

If you're anything like Liferay Portal's developers, you don't want to be
forced to work with one development technology. Our developers build Liferay
with the tools they prefer. That's why we strive to provide you with as much
flexibility as possible. You can develop your Liferay-based portal with tools
ranging in complexity from IDEs like Eclipse, Netbeans or IntelliJ Idea, to
text editors like Notepad, Vim, or Emacs. You can write your persistence layer
directly using SQL and JDBC, or use advanced object-relational mapping
libraries like Hibernate or iBatis. You get the idea. 

Liferay's tool-agnosticism is great for experienced developers who understand
the strengths and weaknesses of different development technologies; it can be
overwhelming for newcomers, though. So we removed some of the options,
narrowing down your choices and forcing you to use a tool we like, right? No!
We actually added to the list of technologies you can use by developing
specific tools that soften the learning curve for Liferay plugin development.
The most significant of these tools is Liferay IDE, a fully featured Integrated
Development Environment based on Eclipse. Alternatively, there's the Plugins
Software Development Kit (SDK), which is based on Apache Ant and can be used
with any editor or Integrated Development Environment (IDE) you'd like. 

In this chapter we'll discuss both Liferay-specific development tools: 

- Liferay IDE 

- Plugins SDK 

First let's consider the more robust of the two tools, Liferay IDE. 

## Liferay IDE 

Even if you're a grizzled veteran of Java development, if you're going to be
doing a lot of development for your Liferay Portal instance, consider using
Liferay IDE. 

Liferay IDE is an extension for Eclipse IDE, and supports development of plugin
projects for the Liferay Portal platform. You can install Liferay IDE as a set
of Eclipse plugins from an update site. The latest version of Liferay IDE
supports development of portlets, hooks, layout templates, themes, and ext
plugins. To use Liferay IDE, you need the Eclipse Java EE developer package
using Helios or a later version. 

In this section we'll get you started by showing you how to install Liferay
IDE, but we won't stop there; we'll take you through the setup process, get
your runtime environment set up, and then develop some plugins with Liferay
IDE. Last we'll discuss Liferay IDE's Remote Server Adapter. 

To install and set up Liferay IDE, follow the instructions in the first two
subsections below. If you're already using *Liferay Developer Studio* (the king
of Liferay's development tools), which comes with Liferay Portal Enterprise
Edition, skip to the section titled *Testing/Launching Liferay Tomcat
Server*--Liferay IDE comes preconfigured in Developer Studio. 

### Installation of Liferay IDE 

Liferay IDE is an extension of Eclipse IDE; before you install Liferay IDE,
let's make sure your Eclipse release can run Liferay IDE, and that you're using
a supported version of Java. Then we'll show you the installation process--we
give you two choices, depending on whether you want to enter an update site URL
for your Eclipse release. 

#### Requirements 

Make sure you have a supported Java JRE and Eclipse release: 

- Java 5.0 JRE or greater. 

- Liferay IDE must be run in one of the following Eclipse releases: 

    [Eclipse Juno Java EE (4.2.x)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/junosr1),

    [Eclipse Indigo Java EE (3.7.x)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/indigosr2),

    [Eclipse Helios Java EE (3.6.x)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2)

Next we'll show you how to install Liferay IDE. 

#### Installation steps 

To install Liferay IDE and specify an Eclipse udpate URL, follow these steps: 

1. Install Eclipse Juno, Indigo, or Helios (you can download and unzip from one
of the links provided above).

2. Run the Eclipse executable file (e.g., `eclipse.exe`).

3. When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

4. In the *Work with* field, enter the update site URL for your Eclipse release,
then press *Enter*. Your update site URL will be one of the following: 

    Eclipse Juno Update Site: 
    [http://releases.liferay.com/tools/ide/eclipse/juno/stable/](http://releases.liferay.com/tools/ide/eclipse/juno/stable/)

    Eclipse Indigo Update Site:
    [http://releases.liferay.com/tools/ide/eclipse/indigo/stable/](http://releases.liferay.com/tools/ide/eclipse/indigo/stable/)

    Eclipse Helios Update Site:
    [http://releases.liferay.com/tools/ide/eclipse/helios/stable/](http://releases.liferay.com/tools/ide/eclipse/helios/stable/)

5. Liferay IDE features should be detected. Select them and click *Next*. 

6. After calculating dependencies, click *Next* to complete the installation.

7. Restart Eclipse to verify that Liferay IDE is properly installed.

8. After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
IDE icon badge as in the screenshot below, it's properly installed. 

![Figure 8.3: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

Next we'll show you how to install Liferay IDE without specifying an Eclipse
update URL. 

#### Alternative installation 

To install Liferay IDE without specifying an update URL for Eclipse, follow
these steps: 

1. Install Eclipse Juno, Indigo, or Helios (you can download and unzip from one
of the links provided in the *Requirements* section above).

2. Run the Eclipse executable file (e.g., `eclipse.exe`).

3. When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

4. Instead of entering a URL in the *Work with* field, download the lastest
version of Liferay IDE from the releases page: 

    [Liferay IDE Downloads
    Page](http://www.liferay.com/downloads/liferay-projects/liferay-ide)

5. In the *Add Site* dialog, click the *Archive* button and browse to the location of
   the downloaded zip file.

6. Liferay IDE features should be detected. Select them and click *Next*. 

7. After calculating dependencies, click *Next* to complete the installation.

8. Restart Eclipse to verify that Liferay IDE is properly installed.

9. After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
IDE icon badge as in the screenshot below, it's properly installed. 

<!--I don't know if I correctly added the right steps above, but I gave it a
shot. It was confusing to me so I thought it needed to be beefed up. --> 

Let's set up Liferay IDE now that you have it installed. 

### Setting Up Liferay IDE 

Now that you have Liferay IDE installed, either from a downloaded zip file or
from the update site appropriate for your Eclipse version, you need to perform
some basic setup. This section describes the setup steps to perform so you can
develop your Liferay portal and test your customizations. 

Before setting up Liferay IDE, let's make sure you have all the appropriate
software packages installed. 

#### Requirements 

Before setting up Liferay IDE, you need to have appropriate versions of Liferay
Portal, Liferay Plugins SDK, and Eclipse. Make sure you satisfy these
requirements before proceeding: 

1. Liferay Portal 6.0.5 or greater is downloaded and unzipped. 

2. Liferay Plugins SDK 6.0.5 or greater is downloaded and unzipped. 

3. You've installed an appropriate Eclipse IDE version for Java EE Development,
and the Liferay IDE extension--see the *Installation* ssection if you haven't
already done this. 

---

![note](../../images/tip-pen-paper.png)**Note:** Earlier versions of Liferay
(e.g., 5.2.x) are not supported by the Liferay IDE. 

---

Let's set up your Liferay Plugins SDK. 

#### Setting Up Liferay Plugins SDK 

Before you begin creating new Liferay plugin projects, a supported Liferay
Plugins SDK and Liferay Portal must be installed and configured in your Liferay
IDE. The second half of this chapter deals with the Plugins SDK on its own;
here we'll just discuss it from the Liferay IDE perspective. 

1. In Eclipse, open the *Installed Plugin SDKs* dialog box--from your *Windows*
dropdown menu, click *Preferences* &rarr; *Liferay* &rarr; *Installed Plugin
SDKs*.

2. Click *Add* to bring up the *Add SDK* Dialog. 

3. Browse to your Plugins SDK installation. The default name is the directory
name; you can change it if you want. 

4. Select *OK*, and verify that your SDK was added to the list of *Installed
Liferay Plugin SDKs*. 

---

![note](../../images/tip-pen-paper.png)**Note:** You can have Multiple SDKs in
your preferences--set one as the default SDK by checking its box in the list of
*Installed Liferay Plugin SDKs*. 

---

Let's set up your Liferay Portal Tomcat runtime and server. 

#### Liferay Portal Tomcat Runtime and Server Setup 

1. In Eclipse, open the *Server Runtime Environments* dialog box--go to *Window*
&rarr; *Preferences* &rarr; *Server* &rarr; *Runtime Environments*). 

    ![Figure 8.6: Choosing a runtime environment](../../images/ide-prefs-runtime-env.png)

2. Click *Add* to add a new Liferay runtime; find *Liferay v6.1 (Tomcat 7)*
under the *Liferay, Inc.* category and click Next.

3. Click *Browse* and select your `liferay-portal-6.1.x directory`. 

4. If you've selected the Liferay portal directory and a bundle JRE is present,
it will automatically be selected as the server's launch JRE. If no JRE bundle
is present then you'll have to select the JRE to use for launch by clicking
*Installed JREs...*. You'll have to do this if you're using MacOS or Linux. 

    ![Figure 8.9: Choosing the JRE bundle](../../images/ide-server-jre.png)

5. Click *Finish*; you should see your Liferay portal runtime listed in
*Preferences* &rarr; *Server Runtime Environments*.

6. Click *OK* to save your runtime preferences. 

7. If you haven't created a server, create one now from the *Servers*
view in Liferay IDE; then you can test the server. 

    ![Figure 8.11: Adding a runtime to the Servers view](../../images/ide-add-new-server.png)

8. Scroll to the *Liferay, Inc* folder and select *Liferay v6... Server*. Choose
the *Liferay v6...* runtime environment that you just created. 

Now your server is set up. Let's launch it and perform some tests! 

### Testing and Launching Liferay Tomcat Server 

Once your Liferay Portal Server is set up, you can launch it from the servers
tab in Eclipse. You have a few options for launching and stopping the server
once it's selected in the Servers tab. 

From the *Servers* tab: 

- Click on the green *Start the Server* button to launch it (or use Ctrl+Alt+R). 

- Click on the red *Stop the Server* button to stop it (or use Ctrl+Alt+S).
You'll only see this button if the server is running.

- Right click the server and select *Start*. 

- Right click on the server and select *Stop*. 

From Eclipse's *Run* menu:

- Select *Run* to start the server.

- Select *Terminate* to stop the server. 

<!--Did I get that wrong? The *Terminate* option is grayed out in my LDS's Run
menu though the server is running. -Russ -->

Once the server is launched, you can open Liferay portal home from the *Servers*
tab by right clicking your Liferay Tomcat server and selecting *Open Liferay
Portal Home*. 

Next you'll learn to create new Liferay projects in Lioferay IDE. 

### Creating New Liferay Projects 

Your Plugins SDK and portal server are configured in Liferay IDE--let's see how
to create a new Liferay plugin project in Liferay IDE. Go to File &rarr; New
Project... &rarr; Liferay &rarr; Liferay Plugin Project. 

In the *New Liferay Plugin Project* dialog box, your newly configured SDK and
Liferay Runtime should be selected. The plugin type *portlet* is the default
plugin type. Type a project name and cooresponding display name, then click
*Finish*. 

![Figure 8.16: Entering the information for the new project](../../images/ide-new-liferay-project.png)

Your new plugin project should apear in the package explorer. 

Now you need to deploy your new plugin project to your Liferay Server. 

### Deploying New Liferay Projects to Liferay Server 

You have a plugin project, but you need to deploy it onto your Liferay
Server. Let's do it now:

1. Select your new plugin project then right click the Liferay Server in
the *Servers* tab; 

2. Select *Add and Remove...*. 

3. Select your plugin project and click *Add* to deploy it to the server. 

4. Click *Finish*. 

You should see the project get deployed to Liferay Tomcat server; in the console
you'll see a message indicating your new portlet is available for use. 

![Figure 8.20: Verifying the output in the Console view](../../images/ide-console-portlet-available.png)

Open *Liferay Portal Home* (`http://localhost:8080/` in most cases) and follow
the instructions in the setup wizard. 

<!--
Log in within the following credentials: 

**User:** test@liferay.com

**Password:** test

--> 

Once you're logged in, click *Add* &rarr; *More*; expand the *Sample* category
and click the *Add* link next to your demo application. You should see the
correctly deployed portlet on the page. 

Great, you can create projects in Liferay IDE! Let's find out how to import
existing projects into Liferay IDE. 

### Importing Existing Projects into Liferay IDE 

What if you have existing projects that you want to work on in Liferay IDE?
This section shows you how to import your existing projects so you don't have
to recreate them. 

We'll cover the following import scenarios: 

1. Importing existing Liferay projects from the Plugins SDK. 

2. Importing Eclipse projects that aren't in Liferay IDE (i.e., don't have
Liferay facet or target runtime).

3. Importing existing Liferay IDE projects from another Liferay IDE version or
workspace. 

<!-- #1 and #3 are pretty similar here.  I changed #1 to match the heading below -->

Let's start by learning how to import existing Liferay projects from Liferay
PLugins SDK. 

#### Importing Existing Liferay Projects from the Plugins SDK 

You can import Liferay projects you created with the Plugins SDK, but aren't
yet in your Liferay IDE Eclipse workspace. These projects might already have
`.project` or `.classpth` files in them; regardless, we'll use the same
procedure to import them. 

Do you want to create one project, or multiple projects, from your PLugins SDK?
You can do either, and we'll show you how below. Any time you import a project
into Liferay IDE, verify that it was successfully configured as a Liferay IDE
project by using the process outlined in the section following the import
instructions. 

---

![note](../../images/tip-pen-paper.png)**Note:** This section assumes that
you've created projects with the Plugins SDK and are familiar with the
directory structure used by the Plugins SDK. If you haven't and if you aren't,
check out the *Plugins SDK* section of this chapter; it comes right after this
section. 

---

First let's look at the import steps needed for creating a single Liferay IDE
project from a Plugins SDK project: 

1. In Liferay IDE, go to *File* &rarr; *New* &rarr; *Project...* &rarr;
*Liferay* &rarr; *Liferay Project from Existing Source*. 

    You can invoke the same wizard from the Liferay shortcut bar; just click
    the *Create a New Liferay Project* button.

    ![Figure 8.23: Alternate method of Importing a project](../../images/ide-new-proj-existing-source.png)

2. Browse to your project folder. It should be a subdirectory of one of the
plugin types (e.g. portlets, hooks, themes, etc) or it won't import correctly.
Once you select the plugin project folder, its type and SDK version values are
updated to the correct values. If the SDK isn't recent enough or the project
type is incorrect, it's marked with an error. 

3. After you select the plugin project folder you should see the *Liferay plugin
type* and *Liferay plugin SDK version* values updated. If your Plugins SDK is
outdated or you entered an incorrect project type, its field gets marked with an
error. 

4. Select the *Liferay target runtime* to configure on the project once it's
imported. If you don't have a Liferay Portal Runtime, use the *New...* button to
create one now; Tomcat bundle is the only server runtime supported. 

<!--Three figures were deleted here. One figure could be created to replace
them if desirable. One figure to rule them all. This can be deleted once this
is seen and decision is made; otherwise it ruins the numbering in this
list. -Russ -->

5. Click *Finish* to perform the import. 

Remember to verify that your project was configured correctly as a Liferay IDE
project. Verification is described right after our next topic, which is making
multiple Liferay IDE projects from a single Plugins SDK. 

Next we'll show you how to import all your Plugins SDK into multiple Liferay
IDE projects. You can use these steps: 

1. In Eclipse go to *File* &rarr; *Import*... &rarr; *Liferay* &rarr; *Liferay
Plugin SDK Projects*. 

    ![Figure 8.27: Choosing the project type for import](../../images/ide-import-from-plugin-sdk.png)

2. In the *Import Projects* window, use the combo box to select the *Liferay
Plugins SDK* you're importing plugins from. 

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** If your SDK isn't
    configured in Liferay IDE (i.e., it's not in the dropdown list of the *Import
    Projects* window) use the *Configure* link to add a setting that points to the
    Plugins SDK that you want to import projects from. To configure a Plugins SDK
    on the Installed SDKs on the pref page just click *Add* and then Browse to the
    directory of the Plugins SDK root directory.

    ---

3. Once you select your Plugins SDK in the combo box, the *Liferay PLugin SDK
Location* and *Liferay Plugin SDK Version* fields are automatically filled in,
as long as they're valid. Invalid entries are marked with an error. 

4. The list of projects that are available for import are displayed in a table.
Any projects already in the workspace will be disabled. Projects available for
import have an empty check box; select each project you'd like to import. 

5. Select the Liferay runtime you want to set up for the imported projects. 

6. If you don't have a liferay runtime, can add one now with the *New...*
button. 

7. Click *Finish*. 

Your plugins are imported! Now skip to the end of this section to verify that
they were configured correctly as Liferay IDE projects. Wwe'll talk about a
different import scenario next; importing existing eclipse projects into Liferay
IDE. 

#### Importing Existing Eclipse Projects into Liferay IDE 

The steps we outlined above let you import projects not already in an Eclipse
workspace. If you have a project in your Eclipse workspace (i.e., you can see it
in Eclipse's project explorer) that you'd like to import to Liferay IDE, follow
the import steps below to convert your project. 

1. In Eclipse, right click the project in your project explorer then select
*Liferay* &rarr; *Convert to Liferay plugin project*. 

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** If you don't have a convert
    action available it means either that the project is already a Liferay IDE
    project, or that it is not faeted (i.e., Java and Dynamic Web project facets
    are not yet configured). Configure accordingly before moving on. 

    ---

2. In the *Convert Project* wizard, your project should be selected and the SDK
location and SDK version should be detected automatically. If they aren't valid
an error message will be displayed. 

    ![Figure 8.33: Converting a project](../../images/ide-convert-plugin-project.png)

3. Select the Liferay runtime to set up for the project. If you don't have a
Liferay Runtime defined, do so now by clicking *New...*. 

4. Click *Finish*. 

Remember to verify that your import was successful and that your project was
configured correctly as a Liferay IDE project; the last subsection in this topic
will show you how. We have one more import scenario to cover--importing existing
Liferay IDE projects into your Liferay IDE. 

#### Importing an existing Liferay IDE project 

What if you had a Liferay IDE project in your workspace, but it's not there
anymore? Here's how you can import it into your current workspace: 

1. Open Liferay IDE, go to *File* &rarr; *Import* ... &rarr; *General* &rarr;
*Existing Projects into Workspace*. 

2. Choose the option *Select root directory*, then click *Browse...*. 

3. Select the directory of the Liferay IDE project to import. 

4. In the *Projects:* list you should see your Liferay IDE project. 

5. Click *Finish*. 

---

![note](../../images/tip-pen-paper.png)**Note:** There are two common errors
that occur when importing existing Liferay IDE projects into your workspace:

- The SDK name used in that project doesn't exist in your new workspace. 

- The runtime id used doesn't exist in your new workspace. 

You can modify the SDK name in *Project Properties* &rarr; *Liferay* dialog
window; you can modify the targeted runtime in the *Project properties* &rarr;
*Targeted Runtimes* dialog window. 

---

Let's verify the success of your imports and ensure that they're properly
configured as Liferay IDE projects. 

### Verifying that the import has succeeded 

After importing projects into Liferay IDE, you'll want to verify that they
imported successfully, and that they're properly configured as Liferay IDE
projects. Here's how you verify that your imports were successful: 

1. Once the project is imported, you should see a new project inside Eclipse and
it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 8.35: Verifying that the import suceeded](../../images/ide-liferay-project-w-overlay-image.png)

2. Let's check the project's target runtime and facets to make sure it's
configured as a *Liferay IDE* project:

    2.1. In the *Package Explorer*, right click *<project-name>* &rarr;
    *Properties* &rarr; *Targeted Runtimes*. 

    2.2. In the *Properties* window, click *Project Facets* and make sure both
    Liferay runtime and Liferay plugin facets are properly configured. 

    ![Figure 8.36: Project Facets](../../images/ide-project-facets.png)

Great! You've confirmed that your import was successful; you can now make
revisions to your configured Liferay IDE project. Next, let's explore Liferay
IDE's Remote Server Adapter feature. 

### Using Liferay IDE's Remote Server Adapter 

The *Remote Server Adapter* is a feature that lets you deploy your Liferay
projects to a remote Liferay Portal server; it first became available in Liferay
IDE 1.6.2. Let's talk about when to use the Remote Server Adapter, then we'll
cover setting it up and using it in more detail. 

Your remote Liferay Portal instance needs to satisfy two requirements to use a
Remote Server Adapter: 

- It is version 6.1 or later. 

- It has the Remote IDE Connector application installed from Liferay
Marketplace. Remote IDE Connector contains the `server-manager-web` plugin for
Liferay that provides an API for Liferay IDE's Remote Server Adapter to use for
all its remote operations. 

The Remote Server Adapter lets developers deploy local projects to a remote
development server for testing purposes--this is its primary use case. If you're
using Liferay IDE and want to deploy projects to a remote server, just make sure
you have access to a remote server with the Remote IDE Connector application
installed. It's possible to install the Remote IDE Connector application on a
production server, but it creates an unnecessary security risk, so we don't
recommend it. Clients shouldn't update, or hot-fix, remotely deployed plugins
with the adapter; the portal system administrator should use normal mechanisms
to apply plugin updates and fixes. 

To start deploying projects to a remote server, you'll need to download and
install the following resources on your local devleopment machine:

- Download [Liferay IDE
1.6.2](http://www.liferay.com/downloads/liferay-projects/liferay-ide) from
Liferay's downloads page or download [Liferay Developer Studio
1.6.1](http://www.liferay.com/group/customer/products/developer-studio/1.6) from
the Customer Portal. 

- Download [Liferay Portal CE or
EE](http://www.liferay.com/downloads/liferay-portal/available-releases), to your
local development machine. 

You'll need to download [Liferay Portal CE or
EE](http://www.liferay.com/downloads/liferay-portal/available-releases) to your
remote (test) server as well. 

Our demonstration uses the Remote Server Adapter on Liferay Portal bundled with
Apache Tomcat, but you can use the adapter with Liferay Portal running on any
application server Liferay Portal supports. Install Liferay Portal locally to
compile the plugins you develop. Install Liferay Portal on your remote test
server to for host the plugins you'll deploy to it. 

---

![important](../../images/tip-pen-paper.png) **Important:** Keep a record of
your portal administrator login credentials (e.g., username/password) for your
remote Liferay server; you'll need them to configure your connection from
Liferay IDE to the remote Liferay server. 

---

Let's start by configuring the Remote Server Adapter. 

#### Configuring the Remote Server Adapter 

You can use Liferay IDE's Remote Server wizard to configure the Remote
Server Adapter and install the Remote IDE Connector to your remote Liferay
instance. Alternatively, you can install the Remote IDE Connector to your remote
Liferay instance before configuring Liferay IDE's Remote Server Adapter. To
configure the Remote Server Adapter, use the following steps:

1. Start your remote Liferay Portal instance--verify that you can log in as an
administrator. 

2. Launch Liferay IDE and open the new server wizard by clicking *File* &rarr;
*New* &rarr; *Other*; select *Server* in the Server category and click *Next*.
Select *Remote Liferay Server (Liferay 6.1)* in the Liferay, Inc. category.

3. Enter the IP address of the machine with the remote Liferay Portal instance
into the *Server's host name* field. For the *Server name*, enter *Liferay@[IP
address]*, then click *Next*. 

    ![Figure 9.x: Define the remote Liferay server.](../../images/remote_server_adapter_wizard_1.png)

4. The *New Server* wizard's next page will direct you to define the Liferay
Portal runtime stub. Doing so allows projects created for your remote server to
use the runtime stub for satisfying JAR dependencies needed to compile various
Liferay projects. Select the *Liferay bundle type* based on the version of your
local Liferay bundle, browse to the *Liferay bundle directory*, and click
*Next*. 

5. On the next page of the wizard, you'll configure your connection to your
remote Liferay instance. 

    5.1. *Hostname*: Enter the IP address of your remote Liferay Portal
    instance's machine.

    5.2. *HTTP Port*: Enter the port it runs on (default: 8080). 

    5.3. *Username* and *Password*: Enter your administrator credentials for the
    remote Liferay Portal instance. 

    5.4. Leave the *Liferay Portal Context Path* and *Server Manager Context
    Path* set to the defaults unless these values were changed for your remote
    Liferay Portal instance. 

6. Your remote Liferay Portal instance needs the Remote IDE Connector
applicaiton installed; otherwise, Liferay IDE can't connect to it. If you
haven't installed Liferay IDE Connector yet, click the *Remote IDE Connector*
link in the wizard. If you already downloaded the Remote IDE Connector
application and installed it to your remote portal, skip to the next step and
validate your connection. 

    6.1. Browse [Liferay Marketplace](http://www.liferay.com/marketplace) for
    the Remote IDE Connector application. When you've found it, click *Free* to
    purchase it. Follow the on-screen prompts.

    6.2. Once you've purchased the application, navigate to the *Purchased* page
    of the Control Panel's Marketplace interface.

    ![Figure 9.x: Click *Purchased* in the Marketplace section of the
ControlPanel to download and install the Remote IDE Connector application that
you purchased.](../../images/remote_server_adapter_marketplace_2.png)

    6.3. Find your application in the list of purchased products. Then click on
    the buttons to download and install the application. Once it's been
    installed on your remote portal, return to the Remote Liferay Server
    configuration wizard in Liferay IDE.

7. Click the *Validate Connection* button; if no warnings or errors appear.
your connection works! If you get any warning or error messages in the
configuration wizard, check your connection settings. 

8. Once your Liferay IDE is connected to your remote Liferay Portal instance,
click *Finish* in the Remote Liferay Server configuration wizard. 
   
    After you click *Finish*, the new remote server appears in Liferay IDE's
    *Servers* tab. This tab appears in the bottom left corner of the Eclipse
    window if you're using the Liferay perspective. If you entered your
    connection settings correctly, Eclipse connects to your remote server and
    displays the remote Liferay Portal instance's logs in the console. If your
    remote server is in debug mode, the Eclipse Java debugger is attached to the
    remote process automatically.

9. You can change the remote server settings at any time. Double-click on your
remote server instance in the *Servers* tab to open the configuration editor,
where you can modify the settings. 

Now that your remote Liferay Portal server is configured, let's test the remote
server adapter!

#### Using the Remote Server Adapter

Once your remote Liferay Portal server is correctly configured and your local
Liferay IDE is connected to it, you can begin publishing projects to it and
using it as you would a local Liferay Portal server. 

Here's how to publish plugin projects to your remote server in Liferay IDE: 

1. Right click on the server and choose *Add and Remove...*. 

---

 ![note](../../images/tip-pen-paper.png)**Note:** Make sure you have available
 projects configured in Liferay IDE. If not, you'll get an error message
 indicating there are no available resources to add or remove from the server. 

---

2. Select the Liferay projects to publish to your remote server; click *Add* to
add them to your remote server, then click *Finish*. Deployment begins
immediately. 

    Your console will display a message that the plugin was successfully
    deployed if publication to the remote server was successful.

3. As you make changes to your plugin project, republish them so they take
affect on the remote server. To set your remote server's publication behavior,
double click your remote server in the *Servers* tab. You can choose to
automatically publish resources after changes are made, automatically publish
after a buld event, or never to publish automatically. To manually invoke the
publishing operation after having modified project files, right click on the
server in the Servers view and select *Publish*. 

Have you noticed that there's a lot of depth to Liferay IDE? You can easily come
across difficult questions and run into very specific problems, but someone else
might have already solved your issue or answered your question. So where would
you go to find out? Don't reinvent the wheel, vsit the [Liferay IDE Community
page](http://www.liferay.com/community/liferay-projects/liferay-ide/overview)!
On the *Forums* page, you can look up resolutions to specific errors and ask
questions. Be sure to fully describe any problems you have to ensure you get a
working asnwer. You can even track known issues from the *Issue Tracker* page. 

<!--This feels much too broad to be stuck in the ## Using the Remote Server
Adapter section. Maybe it should go in the introduction or at the end of the
Liferay IDE section? -Russ-->

### Using the Service Builder Graphical Editor 

<!--Add content-->

### Using Tag Library Snippets 

<!--Add content-->

## The Plugins SDK 

Java developers use a wide variety of tools and development environments.
Liferay makes every effort to remain tool agnostic, so you can choose the tools
that work best for you. If you don't want to use Liferay IDE, we also provide a
Plugins Software Development Kit (SDK) based on Apache Ant that can be used
along with any editor or Integrated Development Environment (IDE). Much of this
guide uses the Plugins SDK and a text editor, but use whatever tool you're most
comfortable with. As an alternative to the Plugins SDK, in Chapter 9 we discuss
Liferay IDE, a plugin for Eclipse that simplifies development for Liferay. 

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: If you use Eclipse and intend to
 use it from the very beginning, you might want to check out Chapter 9 first
 before reading this chapter.

---

We discuss the following topics in this chapter: 

- **Initial Setup:** Install the Plugins SDK and configure it to use with your
Liferay Portal server. 

- **Structure of the SDK:** Where to develop your plugin within the SDK and what
Apache Ant targets are available. 

- **Best Practices:** Helpful ways for organizing your plugin projects. 

Setting up the Plugins SDK is easy. Let's get to it. 

### Installing the SDK

The first thing you should do is install Liferay Portal. If you haven't already
installed a Liferay bundle, follow the instructions in the [Installation and
Setup](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/installing-lifer-1)
chapter of *Using Liferay Portal*. Many people use the Tomcat bundle for
development, as it's small, fast, and takes up fewer resources than most other
servlet containers. Although you can use any application server supported by
Liferay Portal for development, our examples use the Tomcat bundle. 

---

![note](../../images/tip-pen-paper.png)**Note:** In Liferay Developer Studio,
the SDK is already installed and ready to use. Liferay Portal Enterprise
Edition (EE) comes with Liferay Developer Studio and much more (see [CE vs
EE](http://www.liferay.com/downloads/liferay-portal/overview)). Download a
[free
trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
of Liferay Portal EE today. 

---

Installation steps:

1. Download The Plugins SDK from our web site at
[http://www.liferay.com](http://www.liferay.com). 

    1.1. Click the *Downloads* link at the top of the page. 

    1.2. From the *Liferay Portal 6.1 Community Edition* section, select the
    *Plugins SDK* option. 

    1.3. Click *Download*. 

2. Unzip the archive to a folder of your choosing. Because some operating
systems have trouble running Java applications from folders with names
containing spaces, avoid using spaces when naming your folder.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: By default, Liferay Portal
 Community Edition comes bundled with many plugins. It's common to remove them
 to speed up the server start-up. Just navigate to the
 `liferay-portal-[version]/tomcat-[tomcat-version]/webapps` directory and delete
 all its subdirectories except for `ROOT` and `tunnel-web`. 

---

Now that you've installed the Plugins SDK, let's configure Apache Ant for use in
developing your plugins. 

#### Ant Configuration

Building projects in the Plugins SDK requires that you install Ant (version 1.7
or higher) on your machine. Download the latest version of Ant from
[http://ant.apache.org/](http://ant.apache.org/). Extract the archive's contents
into a folder of your choosing. 

Now that Ant is installed, set an `ANT_HOME` environment variable to your Ant
installation location. Then put Ant's `bin` directory (e.g., `$ANT_HOME/bin`) in
your path. We'll give you examples of doing this on Linux (Unix or Mac OS X) and
Windows. 

On Linux (Unix or Mac OS X), if your Ant installation directory is
`/java/apache-ant-<version>` and your shell is Bash, set `ANT_HOME` and adjust
your path by specifying the following in `.bash_profile` or from your terminal: 

    export ANT_HOME=/java/apache-ant-<version>
    export PATH=$PATH:$ANT_HOME/bin

On Windows, if your Ant installation folder is `C:\Java\apache-ant-<version>`,
set your `ANT_HOME` and path environment variables appropriately in your system
properties: 

1. Select *Start*, then right-select *Computer* &rarr; *Properties*. 

2. In the *Advanced* tab, click *Environment Variables...*. 

3. In the *System variables* section, click *New...*. 

4. Set the `ANT_HOME` variable:

    4.1. **Variable name:** `ANT_HOME`. 

    4.2. **Variable value:** *<Ant installation path>* (e.g.,
    `C:\Java\apache-ant-<version>`). 

    4.3. Click *OK*. 

5. Also in the *System variables* section, select your path variable and click
*Edit...*. 

6. Insert `%ANT_HOME%\bin;` after `%JAVA_HOME%\bin;` and click *OK*. 

7. Click *OK* to close all system property windows. 

8. Open a new command prompt for your new environment variables to take affect. 
     
To verify Ant is in your path, execute `ant -version` from your terminal to make
sure your output looks similar to this: 

    Apache Ant(TM) version <version> compiled on <date> 

If the version information doesn't display, make sure your Ant installation is
referenced in your path. 

Now that Ant is configured, let's set up your Plugins SDK environment. 

#### Plugins SDK Configuration

Now we have the proper tools, but we need to configure the Plugins SDK to deploy
into your Liferay instance. The Plugins SDK contains a `build.properties` file
that contains the default settings about the location of your Liferay
installation and your deployment folder. You can use this file as a reference,
but you shouldn't modify it directly (In fact, you will see the message "DO NOT
EDIT THIS FILE" at the top if you open it). In order to override the default
settings, create a new file named `build.[username].properties` in the same
folder, where `[username]` is your user ID on your machine. For example, if your
user name is jsmith, your file name would be `build.jsmith.properties`. 

Edit this file and add the following lines: 

    #
    # Specify the paths to an unzipped Tomcat bundle.
    #

    app.server.type=tomcat
    app.server.dir=the directory containing your application server

If you use Liferay Portal bundled with Tomcat, `app.server.dir` should be the
absolute path to your directory (e.g.
`c:/liferay-portal-[version]/bundles/tomcat-[tomcat-version]`). We specify
`app.server.type` as it's a best practice to specify the application server type
along with the application server properties we are overriding. 

Save the file. 

Next, let's consider the structure of the Plugins SDK. 

### Structure of the SDK

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK: 

- `liferay-plugins-<version>/` - Plugins SDK root directory. 

    - `clients/` - client applications directory. 

    - `dist/` - archived plugins for distribution and deployment. 

    - `ext/` - Ext plugins directory. See chapter 7 on Ext plugins. 

    - `hooks/` - hook plugins directory. See chapter 6 on hooks. 

    - `layouttpl/` - layout templates directory. See chapter 4 on creating
    Liferay Themes. 

    - `lib/` - commonly referenced libraries. 

    - `misc/` - development configuration files. Example, a source code
    formatting specification file. 

    - `portlets/` - portlet plugins directory. See chapter 3 on portlet
    development. 

    - `themes/` - themes plugins directory. See Chapter 4 on creating Liferay
    themes. 

    - `tools/` - plugin templates and utilities. 

    - `webs/` - web plugins directory. 

    - `build.properties` - default SDK properties. 

    - `build.<username>.properties` - (optional) override SDK properties. 

    - `build.xml` - contains targets to invoke in the SDK. 

    - `build-common.xml` - contains common targets and properties referenced
    throughout the SDK. 

    - `build-common-plugin.xml` - contains common targets and properties
    referenced by each plugin. 

    - `build-common-plugins.xml` - contains common targets and properties
    referenced by each plugin type. 

New plugins are placed in their own subdirectory of the appropriate plugin type.
For instance, a new portlet called "greeting-portlet" would reside in
`liferay-plugins-[version]/portlets/greeting-portlet`. 

There's an Ant build file (`build.xml`) in each of the plugins directories. Here
are some Ant targets you'll commonly use in developing your plugins:

- `build-service` - builds the service layer for a plugin, using Liferay Service
Builder. 

- `clean` - cleans the residual files created by the invocations of the
compilation, archiving, and deployment targets. 

- `compile` - compiles the plugin source code. 

- `deploy` - builds and deploys the plugin to your application server. 

- `format-source` - formats the source code per Liferay's source code
guidelines, informing you of violations that must be addressed. See the
[Development Sytle](http://www.liferay.com/community/wiki/-/wiki/Main/Development+Style#section-Development+Style-Format+Source)
community wiki page for details. 

- `format-javadoc` - formats the Javadoc per Liferay's Javadoc guidelines. See
the [Javadoc Guidelines](http://www.liferay.com/community/wiki/-/wiki/Main/Javadoc+Guidelines)
community wiki page for details.

Next, let's consider some best practices for developing plugins using the SDK. 

### Best Practices

The Plugins SDK can house all of your plugin projects enterprise-wide, or you
can have separate Plugins SDK projects for each plugin. For example, if you have
an internal Intranet using Liferay with some custom portlets, you can keep those
portlets and themes in their own Plugins SDK project in your source code
repository. If you also have an external instance of Liferay for your public
Internet web site, you can have a separate Plugins SDK that also has those
projects. Or, you can further separate your projects by having a different
Plugins SDK project for each portlet or theme project. 

It's also possible to use use the Plugins SDK as a simple cross-platform project
generator. Create a plugin project using the Plugins SDK and then copy the
resulting project folder to your IDE of choice. You'll have to manually modify
the Ant scripts, but this process makes it possible to create plugins with the
Plugins SDK while conforming to the strict standards some organizations have for
their Java projects. 

## Summary

Now you know all about two Liferay-specific development tools. Our Eclipse
based Liferay IDE is more robust and complete, so your development process is
as straightforward as can be. If you don't want to use Eclipse, you can still
leverage some of the same functionality by using the Plugins SDK to develop in
Liferay. 

Next, we'll start using these tools to develop portlets! 

---

![note](../../images/tip-pen-paper.png)**Note:** If you've read this far and
don't want to develop your plugins using Liferay IDE *or*the Plugins SDK, you
also have the option to leverage the Apache Maven build management framework.
You'll be happy to know that we have archetypes to help you build various types
of plugins including Liferay portlets, themes, and layout templates. 

To learn more about developing plugins using Maven, see the *MAven* chapter in
this guide. 

---
