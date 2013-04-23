# Liferay IDE 

Every developer wants to choose their tools; you don't want to be forced to
using one development technology. Liferay isn't developed using just one tool;
like you, our developers want to choose from a variety of development tools for
building and developing Liferay itself. That's why we strive to provide you with
as much flexibility as possible. You can develop your Liferay-based portal with
tools ranging in complexity from IDEs like Eclipse, Netbeans or IntelliJ Idea,
to text editors like Notepad, Vim, or Emacs. You can write your persistence
layer directly using SQL and JDBC, or use advanced object-relational mapping
libraries like Hibernate or iBatis. You get the idea. 

Liferay's tool-agnosticism is great for experienced developers who understand
the strengths and weaknesses of different development technologies; it can be
overwhelming for newcomers, though. So we removed some of the options to narrow
down your choices and force you to use a tool we like, right? No! We actually
added to the list of technologies you can use by developing specific tools that
soften the learning curve for Liferay plugin development. The most significant
of these tools is Liferay IDE, a fully featured Integrated Development
Environment based on Eclipse. Even if you're a grizzled veteran of Java
development, if you're going to be doing a lot of development for your Liferay
Portal instance, consider using Liferay IDE. 

Liferay IDE is an extension for Eclipse IDE, and supports development of plugin
projects for the Liferay Portal platform. You can install Liferay IDE as a set
of Eclipse plugins from an update site. The latest version of Liferay IDE
supports develoment of portlets, hooks, layout templates, themes, and ext
plugins. To use Liferay IDE, you need the Eclipse Java EE developer package
using Helios or a later versions. 

<!--Unsure whether "update-site" is proper and sufficient in the above paragraph-->

In this chapter we'll cover the following topics:

- Installation 

- Setting Up Liferay IDE 

- Testing/Launching Liferay Tomcat Server 

- Creating New Liferay Projects 

- Deploying New Liferay Projects to Liferay Server

- Importing Existing Projects into Liferay IDE 

- Using Liferay IDE's Remote Server Adapter

- Designing workflows with Kaleo Designer for Java 

To install and set up Liferay IDE, follow the instruction in the first two
sections below. If you're already using Liferay Developer Studio, which comes
with Liferay Portal Enterprise Edition, skip to the section titled
*Testing/Launching Liferay Tomcat Server*--Liferay IDE comes preconfigured in
Developer Studio.

## Installation 

Liferay IDE is an extension of Eclipse IDE; before you install Liferay IDE,
let's make sure your Eclipse release can run Liferay IDE, and that you're using
a supported version of Java. Then we'll show you the installation process--we
give you two choices, depending on whether you want to enter an update site URL
for your Eclipse release. 

### Requirements 

Make sure you have a supported Java JRE and Eclipse release: 

- Java 5.0 JRE or greater. 

- Liferay IDE must be run in one of the following Eclipse releases: 

    [Eclipse Juno Java EE (4.2.x)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/junosr1),

    [Eclipse Indigo Java EE (3.7.x)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/indigosr2),

    [Eclipse Helios Java EE (3.6.x)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2)

### Installation steps 

To install Liferay IDE and specify an Eclipse udpate URL, follow these steps: 

1. Install Eclipse Juno, Indigo, or Helios (you can download and unzip from one
of the links provided above).

2. Run the Eclipse executable file (e.g., `eclipse.exe`).

3. When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

![Figure 8.1: Installing new software in Eclipse](../../images/ide-install-new-software.png)

4. In the *Work with* field, enter the update site URL for your Eclipse release,
then press *Enter*. Your update site URL will be one of the following: 

    Eclipse Juno Update Site: 
    [http://releases.liferay.com/tools/ide/eclipse/juno/stable/](http://releases.liferay.com/tools/ide/eclipse/juno/stable/)

    Eclipse Indigo Update Site:
    [http://releases.liferay.com/tools/ide/eclipse/indigo/stable/](http://releases.liferay.com/tools/ide/eclipse/indigo/stable/)

    Eclipse Helios Update Site:
    [http://releases.liferay.com/tools/ide/eclipse/helios/stable/](http://releases.liferay.com/tools/ide/eclipse/helios/stable/)


![Figure 8.2: Entering the information for Liferay IDE](../../images/ide-install-available-software.png)

5. Liferay IDE features should be detected. Select them and click *Next*. 

6. After calculating dependencies, click *Next* to complete the installation.

7. Restart Eclipse to verify that Liferay IDE is properly installed.

8. After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
IDE icon badge as in the screenshot below, it's properly installed. 

![Figure 8.3: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

### Alternative installation 

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

![Figure 8.3: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

<!--I don't know if I correctly added the right steps above, but I gave it a
shot. It was confusing to me so I thought it needed to be beefed up. --> 

Let's set up Liferay IDE now that you have it installed. 

## Setting Up Liferay IDE 

Now that you have Liferay IDE installed, either from a downloaded zip file or
from the update site appropriate for your Eclipse version, you need to perform
some basic setup. This section describes the setup steps to perform so you can
develop your Liferay portal and test your customizations. 

Before setting up Liferay IDE, let's make sure you have all the appropriate
software packages installed. 

### Requirements 

Before setting up Liferay IDE, you need to have appropriate versions of Liferay
Portal, Liferay Plugins SDK, and Eclipse. Make sure you satisfy these
requirements before proceeding: 

1. Liferay Portal 6.0.5 or greater is downloaded and unzipped. 

2. Liferay Plugins SDK 6.0.5 or greater is downloaded and unzipped. 

3. You've installed an appropriate Eclipse IDE version for Java EE Development,
and the Liferay IDE extension--see the *Installation* ssection if you haven't
already done this. 

 ![note](../../images/tip-pen-paper.png)**Note:** Earlier versions of Liferay
 (e.g., 5.2.x) are not supported by the Liferay IDE. 

Let's set up your Liferay Plugins SDK. 
 
### Setting Up Liferay Plugins SDK 

Before you begin creating new Liferay plugin projects, an supported Liferay
Plugins SDK and Liferay Portal must be installed and configured in your Liferay
IDE. 

1. In Eclipse, open the *Installed Plugin SDKs* dialog box--from your *Windows*
dropdown menu, click *Preferences* &rarr; *Liferay* &rarr; *Installed Plugin
SDKs*.

3.  Click *Add* to bring up the *Add SDK* Dialog. 

4. Browse to your Plugins SDK installation. The default name is the directory
name; you can change it if you want. 

5. Select *OK*, and verify that your SDK was added to the list of *Installed
Liferay Plugin SDKs*. 

    ![Figure 8.5: Installed SDKs](../../images/ide-select-installed-sdk.png)

 ![note](../../images/tip-pen-paper.png)**Note:** You can have Multiple SDKs in
 your preferences--set one as the default SDK by checking its box in the list of
 *Installed Liferay Plugin SDKs*. 

### Liferay Portal Tomcat Runtime and Server Setup 

1. In Eclipse, open the *Server Runtime Environments* dialog box--go to *Window*
&rarr; *Preferences* &rarr; *Server* &rarr; *Runtime Environments*). 

    ![Figure 8.6: Choosing a runtime environment](../../images/ide-prefs-runtime-env.png)

2. Click *Add* to add a new Liferay runtime; find *Liferay v6.1 (Tomcat 7)*
under the Liferay, Inc. category and click Next.

    ![Figure 8.7: Adding a Liferay runtime](../../images/ide-create-new-local-server.png)

3. Click *Browse* and select your `liferay-portal-6.1.x directory`. 

    ![Figure 8.8: Browsing to your Liferay portal directory](../../images/ide-select-liferay-portal-directory.png)

4. If you've selected the Liferay portal directory and a bundle JRE is present,
it will automatically be selected as the server's launch JRE. If no JRE bundle
is present then you'll have to select the JRE to use for launch by clicking
*Installed JREs...*. You'll have to do this if you're using MacOS or Linux. 

    ![Figure 8.9: Choosing the JRE bundle](../../images/ide-server-jre.png)

5. Click *Finish*; you should see your Liferay portal runtime listed in
*Preferences* &rarr; *Server Runtime Environments*.

    ![Figure 8.10: Liferay portal in the list of available runtimes](../../images/ide-add-server-runtime.png)

6. Click *OK* to save your runtime preferences. 

7. If you haven't created a server, create one now from the *Servers*
view in Liferay IDE; then you can test the server. 

    ![Figure 8.11: Adding a runtime to the Servers view](../../images/ide-add-new-server.png)

8. Scroll to the *Liferay, Inc* folder and select *Liferay v6... Server*. Choose
the *Liferay v6...* runtime environment that you just created. 

    ![Figure 8.12: Selecting Liferay for the Servers view](../../images/ide-define-new-server-type.png)

Now your server is set up.  Let's launch it and perform some tests! 

## Testing and Launching Liferay Tomcat Server 

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

![Figure 8.13: You can start Liferay using either the icon, or by right clicking on the server and 
selecting the option from the menu](../../images/ide-start-server-debug-mode.png)

Once the server is launched, you can open Liferay portal home from the *Servers*
tab by right clicking your Liferay Tomcat server and selecting *Open Liferay
Portal Home*. 

## Creating New Liferay Projects 

Your Plugins SDK and portal server are configured--let's see how to create a new
Liferay plugin project in Liferay IDE. Go to File &rarr; New Project... &rarr;
Liferay &rarr; Liferay Plugin Project. 

![Figure 8.15: Creating a plugin project](../../images/ide-new-project-liferay-project.png)

In the *New Liferay Plugin Project* dialog box, your newly configured SDK and
Liferay Runtime should be selected. The plugin type *portlet* is the default
plugin type. Type a project name and cooresponding display name, then click
*Finish*. 

![Figure 8.16: Entering the information for the new project](../../images/ide-new-liferay-project.png)

Your new plugin project should apear in the package explorer. 

![Figure 8.17: Viewing the new project in the Package Explorer view](../../images/ide-package-explorer-demo-portlet.png)

Now you need to deploy your new plugin project to your Liferay Server. 

## Deploying New Liferay Projects to Liferay Server 

You have a plugin project, but you need to deploy it onto your Liferay
Server. Let's do it now:

1. Select your new plugin project then right click the Liferay Server in
the *Servers* tab; 

2. Select *Add and Remove...*. 

![Figure 8.18: Deploying a project to the server](../../images/ide-servers-add-and-remove.png)

3. Select your plugin project and click *Add* to deploy it to the server. 

![Figure 8.19: Choosing which project to deploy](../../images/ide-add-resource-to-server.png)

4. Click *Finish*. 

You should see the project get deployed to Liferay Tomcat server; in the console
you'll see a message indicating your new portlet is available for use. 

![Figure 8.20: Verifying the output in the Console view](../../images/ide-console-portlet-available.png)

Open *Liferay Portal Home* (http://localhost:8080/ in most cases) and follow the
instructions in the setup wizard.

<!--
Log in within the following credentials: 

**User:** test@liferay.com

**Password:** test

--> 

Once you're logged in, click *Add* &rarr; *More*; expand the *Sample* categore
and click the *Add* link next to your demo application. You should see the
correctly deployed portlet on the page. 

![Figure 8.21: Adding a portlet to the page](../../images/ide-add-portlet-to-page.png)

## Importing Existing Projects into Liferay IDE 

What if you have existing projectss that you want to work on in Liferay IDE?
This section shows you how to import your existing projects into Liferay IDE so
you don't have to recreate them. 

We'll cover the following import scenarios: 

1. Importing existing Liferay projects from the Plugins SDK. 

2. Importing Eclipse projects that aren't in Liferay IDE (i.e., don't have
Liferay facet or target runtime).

3. Importing existing Liferay IDE projects from another Liferay IDE version or
workspace. 

<!-- #1 and #3 are pretty similar here.  I changed #1 to match the heading below -->

Let's start by learning how to import existing Liferay projects from Liferay
PLugins SDK. 

### Importing Existing Liferay Projects from the Plugins SDK 

You can import Liferay projects you created with the Plugins SDK, but aren't yet
in your Liferay IDE Eclipse workspace. These projects might already have
`.project` or `.classpth` files in them; regardless, we'll use the same
procedure to import them.

Do you want to create one project, or multiple projects, from your PLugins SDK?
You can do either, and we'll show you how below. Regardless, any time you import
a project into Liferay IDE, verify that it was successfully configured as a
Liferay IDE project by using the process outlined in the section following the
import instructions. 

---

 ![note](../../images/tip-pen-paper.png)**Note:** The next two sections assume
 that you've created projects with the Plugins SDK and are familiar with the
 directory structure used by the Plugins SDK. 

---

First let's look at the import process for creating a single Liferay IDE project
from a Plugins SDK project. 

#### Creating a Single Liferay IDE Project from One Plugin in an Existing Source 

If you want to create one project for a single plugin that exists inside a
Plugins SDK, follow these steps:

1. In Liferay IDE, go to *File* &rarr; *New* &rarr; *Project...* &rarr;
*Liferay* &rarr; *Liferay Project from Existing Source*. 

    ![Figure 8.22: Importing a Liferay Project](../../images/ide-select-liferay-project-from-existing.png)

    You can invoke the same wizard from the Liferay shortcut bar; just click
    the *Create a New Liferay Project* button.

    ![Figure 8.23: Alternate method of Importing a project](../../images/ide-new-proj-existing-source.png)

2. Browse to your project folder. It should be a subdirectory of one of the
plugin types (e.g. portlets, hooks, themes, etc) or it won't import correctly.
Once you select the plugin project folder, its type and SDK version values are
updated to the correct values. If the SDK isn't recent enough or the project
type is incorrect it gets marked with an error. 

    ![Figure 8.24: Setting the Project location](../../images/ide-import-project-from-existing.png)

3. After you select the plugin project folder you should see the *Liferay plugin
type* and *Liferay plugin SDK version* values updated. If your Plgins SDK is
outdated or you entered an incorrect project type, its field gets marked with an
error. 

    ![Figure 8.25: Verifying the plugin type and version](../../images/ide-plugin-type-and-version.png)

4. Select the *Liferay target runtime* to configure on the project once it's
imported. If you don't have a Liferay Portal Runtime, use the *New...* button to
create one now; Tomcat bundle is the only server runtime supported. 

    ![Figure 8.26: Choosing the target runtime](../../images/ide-target-runtime-server.png)

5. Click *Finish* to perform the import. 

Remember to verify that your project was configured correctly as a Liferay IDE
project. Verification is described right after our next topic, which is making
multiple Liferay IDE projects from a single Plugins SDK. 

#### Create multiple projects for all plugins in a Plugins SDK 

Does your Plugins SDK contain multiple plugins? You can easily batch import each
plugin in your Plugins SDK as its own Liferay IDE project. 

1. In Eclipse go to *File* &rarr; *Import*... &rarr; *Liferay* &rarr; *Liferay
Plugin SDK Projects*. 

    ![Figure 8.27: Choosing the project type for import](../../images/ide-import-from-plugin-sdk.png)

2. In the *Import Projects* window, use the combo box to select the *Liferay
Plugins SDK* you're importing plugins from. 

    ![Figure 8.28: Choosing the SDK version](../../images/ide-import-plugins-sdk.png)

---

    ![note](../../images/tip-pen-paper.png)**Note:** If your SDK isn't
    configured in Liferay IDE (i.e., it's not in the dropdown list of the
    *Import Projects* window) use the *Configure* link to add a setting that
    points to the Plugins SDK that you want to import projects from. To
    configure a Plugins SDK on the Installed SDKs on the pref page just click
    *Add* and then Browse to the directory of the Plugins SDK root directory.

---

3. Once you select your Plugins SDK in the combo box, the SDK location and
version are automatically filled in, as long as they're valid. Invalid entries
are marked with an error. 

    ![Figure 8.29: Verifying the SDK version and location](../../images/ide-select-configured-plugins-sdk.png)

4. The list of projects that are available for import are displayed in a table.
Any projects already in the workspace will be disabled. Projects available for
import have an empty check box; select each project you'd like to import. 

    ![Figure 8.30: Importing a project](../../images/ide-sample-service-builder-portlet-plugin.png)

5. Select the Liferay runtime you want to set up for the imported projects. 

6. If you don't have a liferay runtime, can add one now with the *New...*
button. 

    ![Figure 8.31: Selecting your runtime](../../images/ide-target-runtime-server.png)

7. Click *Finish*. 

Your plugins are imported! Now skip to the end of this section to verify that
they were configured correctly as Liferay IDE projects. Wwe'll talk about a
different import scenario next; importing existing eclipse projects into Liferay
IDE. 

### Importing Existing Eclipse Projects into Liferay IDE 

The steps we outlined above let you import projects not already in an Eclipse
workspace. If you have a project in your Eclipse workspace (i.e., you can see it
in Eclipse's project explorer) that you'd like to import to Liferay IDE, follow
the import steps below to convert your project. 

1. In Eclipse, right click the project in your project explorer then select
*Liferay* &rarr; *Convert to Liferay plugin project*. 

---

    ![note](../../images/tip-pen-paper.png)**Note:** If you don't have a convert
    action available it means either that the project is already a Liferay IDE
    project, or that it is not faeted (i.e., Java and Dynamic Web project facets
    are not yet configured). Configure accordingly before moving on. 

---

    ![Figure 8.32: Selecting the convert option](../../images/ide-select-convert-to-liferay-project.png)

2. In the *Convert Project* wizard, your project should be selected and the SDK
location and SDK version should be detected automatically. If they aren't valid
an error message will be displayed. 

    ![Figure 8.33: Converting a project](../../images/ide-convert-plugin-project.png)

3. Select the Liferay runtime to set up for the project. If you don't have a
Liferay Runtime defined, do so now using the *New...* action. 

    ![Figure 8.34: Selecting the runtime](../../images/ide-target-runtime-server.png)

4.  Click *Finish*. 

Remember to verify that your import was successful and that your project was
configured correctly as a Liferay IDE project; the last subsection in this topic
will show you how. We have one more import scenario to cover; improting existing
Liferay IDE projects into your Liferay IDE. 

### Importing an existing Liferay IDE project 

What if you had a Liferay IDE project in your workspace, but it's not there
anymore? Here's how you can import it into your current workspace: 

1. Open Liferay IDE, go to *File* &rarr; *Import* ... &rarr; *General* &rarr;
*Existing Projects into Workspace*. 

2. Choose the option *Select root rirectory*, then click *Browse...*. 

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

## Using Liferay IDE's Remote Server Adapter 

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

### Configuring the Remote Server Adapter 

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

    ![Figure 9.x: Define the remote Liferay
    server.](../../images/remote_server_adapter_wizard_1.png)

4. The *New Server* wizard's next page will direct you to define the Liferay
Portal runtime stub. Doing so allows projects created for your remote server to
use the runtime stub for satisfying JAR dependencies needed to compile various
Liferay projects. Select the bundle type based on the version of your local
Liferay bundle, browse to the bundle's installation directory, and click *Next*.

    ![Figure 9.x: You need to specify the location of a local Liferay Portal
    bundle that your Liferay projects can compile
    against.](../../images/remote_server_adapter_wizard_2.png)

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

    ![Figure 9.x: Once you've configured the connection settings for your remote
    Liferay Portal instance, click *Validate Connection* to make sure that your
    connection works.](../../images/remote_server_adapter_wizard_3.png)

6. Your remote Liferay Portal instance needs the Remote IDE Connector
applicaiton installed; otherwise, Liferay IDE can't connect to it. If you
haven't installed Liferay IDE Connector yet, click the *Remote IDE Connector*
link in the wizard. If you already downloaded the Remote IDE Connector
application and installed it to your remote portal, skip to the next step and
validate your connection. 

    6.1. Browse Liferay Marketplace for the Remote IDE Connector application.
    When you've found it, click *Free* to purchase it. Follow the on-screen
    prompts.

    6.2. Once you've purchased the application, navigate to the *Purchased* page
    of the Control Panel's Marketplace interface.

    ![Figure 9.x: Click *Purchased* in the Marketplace section of the Control
    Panel to download and install the Remote IDE Connector application that you
    purchased.](../../images/remote_server_adapter_marketplace_2.png)

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

    ![Figure 9.x: If you've correctly configured the connection settings, your
    remote server appears in the Servers tab of Eclipse and Eclipse's console
    displays your remote server's Liferay
    logs.](../../images/remote_server_adapter_console_1.png)

9. You can change the remote server settings at any time. Double-click on your
remote server instance in the *Servers* tab to open the configuration editor,
where you can modify the settings. 

Now that your remote Liferay Portal server is configured, let's test the remote
server adapter!

### Using the Remote Server Adapter

Once your remote Liferay Portal server is correctly configured and your local
Liferay IDE is connected to it, you can begin publishing projects to it and
using it as you would a local Liferay Portal server. 

Here's how to publish plugin projects to your remote server in Liferay IDE: 

1. Right click on the server and choose *Add and Remove...*. 

---

    ![note](../../images/tip-pen-paper.png)**Note:** Make sure you have
    available projects configured in Liferay IDE. If not, you'll get an error
    message indicating there are no available resources to add or remove from
    the server. 

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

    ![Figure 9.x: You can configure your server's publishing settings from the server configuration editor.](../../images/remote_server_adapter_configuration_editor.png)

Have you noticed that there's a lot of depth to Liferay IDE? You can easily come
across difficult questions and run into very specific problems; but someone else
might have already solved your issue or answered your question. So where would
you go to find out? Don't reinvent the wheel, vsit the [Liferay IDE Community
page](http://www.liferay.com/community/liferay-projects/liferay-ide/overview)!
On the *Forums* page, you can look up resolutions to specific errors and ask
questions. Be sure to fully describe any problems you have to ensure you get a
working asnwer. You can even track known issues from the *Issue Tracker* page. 

<!--This feels much too broad to be stuck in the ## Using the Remote Server
Adapter section. Maybe it should go in the introduction or the summary -Russ-->

In the next section, we'll explain Developer Studio's Kaleo Designer for Java
and how you can design workflows for enhanced business logic.

## Designing workflows with Kaleo Designer for Java 

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Portal includes a workflow engine called Kaleo. Kaleo allow portal
administrators to set up workflows for their oganizations needs; the workflow
calls users to participate in processes designed for them. Kaleo workflows,
called *process definitions*, are essentially an XML document. The Kaleo
workflow engine supports a host of XML element types to trigger decisive actions
in your business process instances. You can fine tune the logic of your process
definition by incorporating scripts and templates. The *Kaleo Forms EE* app from
Marketplace includes Liferay's *Kaleo Workflow Designer* that lets you create
and modify portal workflows in your browser. With *Kaleo Designer for Java*, you
can design and publish Kaleo workflows from Liferay Developer Studio! 

![Figure 8.37: Kaleo Designer for Java gives you a powerful environment for
designing, modifying, and publishing Kaleo
workflows.](../../images/kaleo-designer-for-java.png)

Kaleo Designer for Java lets you incorporate back end Java development and
scripting in your workflows. Its graphical interface lets you drag
and drop nodes into your workflow. A shortcut on each node gives you easy access
to the node's XML, letting you edit its current implementation to make subtle
modifications or inject new business logic. In addition, Liferay Developer
Studio comes bundled with a Java/Groovy editor (made available by Spring
Source), giving you the same rich editing experience to which you may be
accustomed in editing Kaleo [Groovy](http://groovy.codehaus.org/Documentation)
scripts. From there you can delegate workflow decisions to your custom business
logic APIs or access any of the Liferay Portal APIs. In Developer Studio, you
can leverage editors for Beanshell, Drl, Javascript, Python, and Ruby scripting
languages. You can leverage editors for
[FreeMarker](http://freemarker.sourceforge.net/) and
[Velocity](http://velocity.apache.org/engine/releases/velocity-1.5/user-guide.html)
template languages too. Kaleo Designer for Java gives you a rich tool set for
creating/editing workflows, manipulating workflow nodes, and implementing
business logic. But that is not all!

Kaleo Designer for Java gives you the capability to remotely add and update
workflow definitions directly to and from your Liferay server. You can publish
your workflow drafts to your portal by simply dragging the workflow file onto
your portal server listed in your *Servers* view. The Servers view not only
shows workflows you've published from studio, but also gives you access to
workflows already published on the portal server. You can edit any of these
existing workflows right in Developer Studio and republish them on your portal,
letting you create business logic locally, without having to navigate back and
forth from your portal and Developer Studio. As you can see, Kaleo Designer for
Java is a powerful application for creating, modifying, and publishing workflows
in Liferay Developer Studio.

---

![note](../../images/tip-pen-paper.png)**Note:** The Kaleo Forms EE app comes
with an existing workflow designer *within* Liferay Portal. This can be used to
design workflow configuration and is described in the [Kaleo Forms: Defining
Business
Processes](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen11-kaleo-forms-defining-business-processes-0)
chapter of *Using Liferay Portal*. Also, refer to the [Using
Workflow](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/workflow-with-kal-4)
chapter of *Using Liferay Portal* if you are unfamiliar with basic Kaleo
workflow concepts or want to know how to design your workflow within Liferay
Portal.

---

To use Kaleo Designer for Java, follow the instructions below for installing the
the *Kaleo Forms EE* apps. The Kaleo Workflow EE app
includes the `kaleo-forms-portlet`, `kaleo-designer-portlet`, and `kaleo-web`
plugins.

Install Kaleo Designer for Java:

1. Go to [Marketplace](https://www.liferay.com/marketplace).
2. Click on [EE
Marketplace](http://www.liferay.com/marketplace/-/mp/category/12729032) in the
left navigation area.
3. Download and install the *Kaleo Forms EE* app.

After downloading and installing the Kaleo Forms EE application, restart the
Liferay Server and a *Kaleo Workflows* folder automatically appears underneath
the server instance in the Servers view of Developer Studio.

![Figure 8.38: In Studio's *Servers* view, your server's Kaleo Workflows folder
shows workflows published on your portal.](../../images/kaleo-1.png)

Developer Studio retrieves all workflow definitions published on your portal
server. As mentioned previously, the Kaleo Designer for Java lets you remotely add and
update Kaleo workflow definitions directly to and from the portal server. The
*Single Approver* workflow comes preinstalled with the Kaleo app.

To open a workflow, simply double-click the workflow from the Kaleo Workflows
folder. This retrieves the workflow definition from the Liferay server letting
you edit the workflow in Studio.

---

![note](../../images/tip-pen-paper.png)**Note:** Upon opening a workflow you are
prompted on whether to switch to the Kaleo designer perspective. Click *Yes* to
use the perspective's helpful features that include the palette toolbar,
properties view, and outline view.

---

Next, let's dive into creating a workflow using the Kaleo Workflow
Designer for Java.

### Creating a workflow 

Developer Studio adds another dimension to working with your Kaleo workflows
making it easy for Java developers to enhance workflow business logic. Let's
create a workflow in Developer Studio and highlight features from Kaleo Designer
for Java (Designer) along the way.

As a fun way to demonstrate Designer features, let's create a workflow
definition for a software ticketing process. Are you ready to roll? Let's get
started!

![Figure 8.39: Check out the adventures of Liferay's mascots, Ray and Alloy,
found at http://www.liferay.com/ray.](../../images/kaleo-15.png)

Create your new workflow definition by going to *File* &rarr; *New* &rarr;
*Liferay Kaleo Workflow*. Alternatively, select *Liferay Kaleo Workflow* from
the toolbar button shown in the figure below.

![Figure 8.40: Create a new workflow definition locally by selecting *Liferay
Kaleo Workflow* from the toolbar button.](../../images/kaleo-3.png)

The new workflow wizard prompts you to enter credentials for your new workflow
definition. The snapshot below displays the setup menu for our ticket process
workflow definition.

![Figure 8.41: The wizard prompts you for information to setup your
workflow.](../../images/kaleo-5.png)

---

![note](../../images/tip-pen-paper.png)**Note:** Make sure you have a Liferay
project already in place as a home for the workflow definitions you create.

---

In the workflow wizard, select an existing project for your workflow and a
project folder in which to save your workflow definition's XML file. Fill in
your workflow's name and the name of your workflow's initial and final state
nodes. The wizard also gives you the luxury of choosing your preferred script
and template type. Upon creating node scripts and node templates, Designer
brings up the script and template editors for these default types. Click the
*Next* button and you're asked to choose an assignment type for your workflow's
initial task. The figure below shows the plethora of assignment types available:

![Figure 8.42: When choosing an assignment type for a task node, you are given
multiple options.](../../images/kaleo-4.png)

For our workflow example, select *Assign to asset creator* and name the task
`Developer`. Upon our workflow's Developer task being invoked, the creator of
the workflow's asset is assigned. In Liferay Portal, each Kaleo workflow is
associated with an asset type. Later in our exercise, we'll associate our
workflow with a Dynamic Data List (DDL). Click *Finish* to complete the initial
setup of our workflow definition.

![Figure 8.43: The workflows you create using the workflow wizard are displayed
in Designer's workflow diagram.](../../images/kaleo-9.png)

Your workflow appears as a workflow diagram, letting you interact with your
workflow graphically. Graphical features and toolbars allow you to customize
your workflow definition. The Palette view is one of Designer's most commonly
used tools. Let's explore it next.

#### Palette and floating palette 

The Palette lets you to customize your workflow with nodes and transitions.
In addition, you can choose different behaviors for your mouse pointer.

![Figure 8.44: The palette toolbar lets you customize your workflow with
additional nodes and transitions.](../../images/kaleo-6.png)

Here are your pointer options:

- *Select*: the default pointer setting used for selecting options on the
workflow diagram by simply clicking the icon.

- *Marquee*: used for dragging an invisible box around multiple icons. This is
very useful when you want to manipulate multiple nodes and/or transitions on the
workflow diagram.

Use transitions to connect one node to another. On exiting the first node,
processing continues to the node pointed to by the transition. Selecting a
*Transition* turns your pointer into a connector, letting you connect the
starting end of a transition to one node and the other end of the transition to
the next node to process.

Here are the types of nodes you can add to your workflow:

- *Condition*: directs workflow execution to an appropriate transition based on
conditional logic of the node's script.

- *Fork*: forks the workflow execution into two parallel threads.

- *Join*: joins parallel workflow threads.

- *State*: represents a workflow state.

- *Task*: represents a task that can be assigned.

Simply drag and drop nodes you want onto your workflow diagram. Each node type
supports execution of scripted actions and sending notifications that can use
templates. For additional information on the nodes, refer to the [Kaleo
Designer](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen06-kaleo-designer-0)
section of *Using Liferay Portal*.

For our ticket process workflow diagram, we left off with the simple *StartNode*
state node followed by the *Developer* task node followed by the *EndNode* state
node.

![Figure 8.45: The ticket process' initial workflow definition needs some
work!](../../images/kaleo-9.png)

We plan for a developer to approve his fix and send it to quality assurance:
*QA* and *QA Management*. The developer's fix must pass testing by a QA engineer
and get approval by a QA manager. Let's use a fork node to accurately depict
these approval tasks being done in parallel.

Drag and drop a fork node onto your workflow diagram. A wizard helps you create
your fork node. On clicking the green "plus" symbol, you can select new or
existing nodes to process in parallel threads. A drop-down menu gives you
options to select tasks to be done in your fork threads. In addition, a checkbox
lets you indicate whether to automatically add a corresponding join node to your
workflow.

![Figure 8.46: You're able to select what to fork and are given the option to
automatically create a join node.](../../images/kaleo-7.png)

On finishing your fork node in the wizard, Kaleo Designer makes a best effort in
placing your new nodes onto the workflow diagram's canvas. If you're not happy
with the location of your new nodes, simply drag them where you want them in
your diagram. Our ticket process workflow now looks something like this:

![Figure 8.47: After creating the fork and join nodes, you can reposition them
to your liking.](../../images/kaleo-8.png)

Notice, there are error markings on multiple nodes within our workflow. An error
marking is denoted by a red "X" in the bottom left of a node. These markings
indicate something is specified incorrectly or is missing with respect to your
nodes. Hover your pointer over each error marker for Studio to display hints on
how to resolve each of their problems. We'll address these error markers soon.

![Figure 8.48: Error markings notify the user that something is wrong or needs
to be specified within a node.](../../images/kaleo-26.png)

Hovering your pointer over any node displays a floating palette as a convenience
for applying quick customizations.

![Figure 8.49: A convenient floating palette appears when you hover your pointer
over any node.](../../images/kaleo-10.png)

Let's go over the features of the floating palette:

- *Add (green cross)*: lets you add an action or notification on the node.
To edit the added action or notification, bring up Designer's *Properties* view.

- *Edit Actions (paper with arrow)*: lets you edit the node's existing actions.

- *Edit Notifications (envelope)*: lets you edit existing notifications on the
node.

- *Edit Script (pencil)*: lets you edit the script of the condition node. This
feature only applies to the condition node.

- *Change Assignments (person)*: lets you assign/reassign the task. This
feature only applies to the task node.

- *Delete (red "X")*: deletes the node.

- *Show in Source (paper with folded corner and arrow)*: shows the node
specified as as a model element in the workflow definition's XML source and
switches the main editor to *Source* mode. 

- *Start Transition (black arrow)*: changes the pointer to transition mode
letting you create a workflow transition from the current node to another node.

Obviously, there is still work to be done in our workflow definition. We have
multiple error markings and the fork and join nodes aren't connected to
anything. Let's change the assignments for our two new task nodes, QA and QA
Management, by clicking the *Change Assignments* icon from the floating palette
for each node. The *Choose Assignment Type* menu appears for each node, letting
you choose their assignment type. After we assign the QA and QA Management task
nodes, the error markers disappear.

For our ticket process workflow's QA tasks, we want to assign someone other than
the asset creator. Realistically, we would assign each of these tasks to
different roles in our site. For simplicity, lets assign both the QA and QA
Management tasks to the same user. If you have a user in mind, specify that
user. Otherwise, create a user named "Joe Bloggs" with screen name "joe". For
the user to receive emails, he must be registered within Liferay Portal. If you
haven't registered Joe Bloggs ("joe") already, see the [Adding
users](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen12-adding-users-0)
section of *Using Liferay Portal* for instructions. To configure the user's
email, login to the user's account and visit *Control Panel* &rarr; *Server
Administration* &rarr; *Mail* for setup options.

Select the *Change Assignments* icon from the floating palette for each QA task
node. Then, select *Assign to a specific user* from the *Choose Assignment
Type* menu and click *Next*. You have options to enter the user's *User-id*,
*Screen name*, or *Email address*. Enter the user's screen name and click
*Finish*.

![Figure 8.50: Designer lets you assign a task to a specific user of Liferay
Portal.](../../images/kaleo-29.png)

Assigning the QA and QA Management task nodes resolved their error markings.
Later, the join node's error marking will be disappear when we connect the join
node to another task.

![Figure 8.51: The error markings for the QA and QA Management task nodes
disappear after their assignments are specified.](../../images/kaleo-27.png)

While we're using Designer's Workflow Diagram, let's go over some of its
features.

#### Workflow Diagram features 

Within the workflow diagram, there are other miscellaneous features offered by
Developer Studio. Below are lists of some of these small features that
could make a huge difference when designing your workflow.

**Workflow Diagram Actions** are available via icons in the upper right
corner toolbar of the Workflow Diagram:

-	*Zoom In*

-	*Zoom Out*

-	*Zoom Actual*

-	*Save as Image*

-	*Print*

	These toolbar icons are shown in the figure below.
	
	![Figure 8.52: The Workflow Diagram offers a toolbar with helpful options
	for zooming, saving, and printing your workflow
	diagram.](../../images/kaleo-23.png)

**More Workflow Diagram Actions** are accessible by right-clicking in the
Workflow Diagram's canvas:

- The *Layout* feature lets you arrange nodes vertically or horizontally. This
feature makes it easy to organize your workflow quickly without having to touch
a node.

	![Figure 8.53: When right-clicking the graphical interface, you are given
	options for viewing and editing your workflow
	definition.](../../images/kaleo-24.png)

- The *Rename* feature lets you rename a node or transition by simply
double-clicking its current name and typing in a new name.

- The *Surveyor's Level* feature helps you center a node, vertically or
horizontally, with respect to another node.

	![Figure 8.54: Studio's Surveyor's Level helps you design your workflow for
	an organized and professional look.](../../images/kaleo-32.png)

You have probably noticed the *Properties* view below your workflow diagram.
Let's explore what it does and how it lets you customize your workflow
definition.

#### Properties View and Outline Views 

More cool features that can help you customize your workflow are the
*Properties* and *Outline* views, located on the bottom and bottom right of
Developer Studio, respectively. The Properties view lets you edit the current
node's properties. If no node is selected or you select the workflow canvas, the
Properties view displays your workflow's general properties for you to edit.
Once you click on an individual node, its properties appear in the Properties
view.

Node properties are grouped as follows:

- *Actions*: let you execute scripts with respect to your node. Each action has a
*Name*, *Script Language*, *Execution Type*, and a *Priority* (optional).
Clicking *Edit Script* brings up the script in the default editor for the
*Script Language* you specified.

- *Notifications*: let you notify users with respect to your node. Each
notification has a *Name*, *Template Language*, *Execution Type*, and one or
more *Notification Types*.

- *Assignments*: let you assign tasks to users or roles. Click *Change Task
Assignments* to specify or change the assignment.

- *Task Timers*: let you name timers for a task and whether the timers are
blocking. Each task timer has a *Name* and *Blocking* indicator.

- *Script*: lets you edit a script for your condition node. Clicking *Edit
Script* brings up the script in the default editor for the *Script Language* you
specified.

- *Other*: lets you edit miscellaneous properties, such as *Name* and
*Description*, applicable to your node's type.

Here is what the Properties view looks like in Developer Studio:

![Figure 8.55: The Properties view gives you multiple sub-tabs to help customize
your workflow nodes.](../../images/kaleo-11.png)

Another useful tool is the Outline view. This gives a top level view of your
entire workflow definition. As you can imagine, workflows can become extremely
large over time and may not be able to fit on the viewing area of your workflow
diagram screen. The Outline view lets you view your entire workflow definition,
no matter how large it becomes. In addition, the Outline view shows what is
currently visible on your Workflow diagram by highlighting it in blue. You can
select this blue highlighted area and drag it to the area of your workflow you
want visible on your Workflow Diagram. Below is a snapshot of an example
workflow shown in the outline view in Developer Studio:

![Figure 8.56: The Outline view lets you view your entire workflow
definition.](../../images/kaleo-25.png)

As you can see, the Properties and Outline views make customizing your
workflow easier than ever! Developer Studio offers a useful programming
capability for editing your workflow scripts. We will give you a tour of
what Developer Studio has to offer for using workflow scripts!

### Using Workflow Scripts 

We will now explain how to use Developer Studio to edit your workflow scripts.
Developer Studio is configured to recognize multiple script languages to let the
developer use what they are most comfortable with. These script languages
include: Beanshell, Drl, Groovy, Javascript, Python, and Ruby. Developer Studio
offers many script editing features that can help you quickly and successfully
implement business logic in your workflows.

Let's dive back into our software ticket process workflow and create a script.
For most ticketing processes, it is not guaranteed that every ticket has a
resolution. If the issue was due to a silly user error, there is no reason to
change the product. In this case, the developer will resolve the ticket
indicating there is no resolution. Regardless, we'll have the developer fill out
an online Dynamic Data List (DDL) form to kick off processing a workflow for
each of her tickets. Once the workflow is invoked, its associated DDL record is
accessible from our workflow's context. Let's use a condition node to handle the
ticket based on the DDL record.

Drag and drop a condition node onto your workflow diagram and a *Create New
Condition Node* menu appears. Name the node *Resolution*. Notice, the menu let's
you choose a script language for the condition node. Select *Groovy* and you'll
see how easy it is to embed Java code. In our Groovy script, we'll access the
DDL record to determine whether the ticket is to be fixed. If the ticket is to
be fixed, we'll assign it to a developer via the Developer task node. If the
ticket is not to be fixed, we'll end the workflow by transitioning to the
workflow's EndNode.

From the *Create New Condition Node* menu, add two transitions -- one transition
to the Developer node and the other to the EndNode state. To add these
transitions:

1. Click the green plus sign and select the transition icon from its menu. An
entry for the transition appears in the named list of *Condition transitions*.

2. Click the browse icon in the entry and select the Developer node.

3. Repeat steps 1 and 2 to add a transition to the EndNode state.

4. Click *Finish*

Here is a snapshot of the *Create New Condition Node* menu configured for the
ticket process workflow.

![Figure 8.57: When creating a condition node, you're able to set your preferred
script language, name, and condition transitions.](../../images/kaleo-12.png)

Before adding a script to our condition node, let's make the following changes
in our workflow transitions:

- Add a transition from the *Developer* task node to the *Pass To QA* fork node.

- Add a transition from the *StartNode* state node to the *Resolution* condition
node.

- Delete the transition that currently connects the *StartNode* state node to
the *Developer* task node.

To add a transition from one node to another, do the following:

1. Click the transition icon from the palette. Your pointer's icon shows as a
plug indicating you are in *connector* mode.

2. Select a node on your workflow diagram from which the transition will start.
A dotted line appears with one end connected to the selected node and the other
end following your pointer.

3. Select a node to which the transition will end. The dotted line changes into
a fixed ray with the arrow pointing to the transition's end node.

4. To exit connector mode, hit *escape* on your keyboard and click your pointer
at empty space in your workflow diagram.

You may notice the error marking on the condition node. When you hover over
the marking, a hint indicates a script must be specified for the node.

Invoke the script editor for your *Resolution* condition node by doing one of
the following:

- Select the node and click *Edit Script* from the *Script* tab of the
Properties view.

- Click the *Edit Script* tool from the node's floating palette.

- Right-click the node and select *Edit Script*.

![Figure 8.58: You can access the *Edit Script* feature by navigating to
the *Script* tab inside the Properties view.](../../images/kaleo-13.png)

Since we set our default script language to Groovy earlier, the Java/Groovy
editor appears. To learn more about the Groovy editor, see the [Groovy User
Guide](http://groovy.codehaus.org/User+Guide). Likewise, if you set the script
language to another language, the editor for that specific language appears. The
editor runs in the context of editing the specific node you selected. So
anything you type in the script editor for this condition node is written inside
the `<script></script>` tags for the `<condition/>` element that represents our node
in our workflow definition XML file.

---

![note](../../images/tip-pen-paper.png)**Note:** Developer Studio lets your use
multiple script editors while modifying the same workflow definition XML file.

---

Also, notice the palette is different from when you were working in the workflow
diagram.

Here is a snapshot of how the palette appears with the *Context Variables*
folder open for a view of its options:

![Figure 8.59: Each script editor is associated with a palette that lets you
insert helpful snippets of code.](../../images/kaleo-14.png)

The palette associated with your Java/Groovy script editor includes folders
containing the following entities available to your script:

-	Context Variables
-	Dynamic Data Lists
-	Roles
-	Scripts
-	Status Updates

The folders can be expanded and collapsed by simply clicking their name bars.

Drag and drop any entity from your palette onto your Java/Groovy editor and code
representing that entity appears instantaneously in the editor. The inserted
code is free of compile errors and warnings because the editor is running in the
context of Liferay Portal. This means that all of the Liferay Portal APIs are
available to you. The editor lets your invoke code-assist and access built in
Kaleo workflow variables.

Let's get a hold of the DDL record that's being worked on in our workflow
process. To get the DDL record, we need the `serviceContext`. To learn more
about Service Context and its parameters, see the [Service
Context](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/service-conte-1)
section of this guide.

Let's take advantage of Designer's palette features in conjunction with our
Java/Groovy editor to implement our condition:

1. Drag and drop the *serviceContext* entity from the *Context Variables* folder
in your palette onto the script editor. This grabs the Service Context.

2. Drag and drop the *ddlRecord* entity from the *Dynamic Data Lists* folder in
your palette onto the script editor. We get the `ddlRecordId` from the Service
Context and use that ID to look up the DDL record via Liferay service utility
`DDLRecordLocalServiceUtil`.

The code now looks like this:

![Figure 8.60: You can add snippets of code by simply using the drag-and-drop
method from your palette.](../../images/kaleo-28.png)

Append the following Java code to your script:

	Field field = ddlRecord.getField("status");

	String status = GetterUtil.getString(field.getValue());
	if (status.contains("not")) {
		returnValue = "No"
	}
	else {
		returnValue = "Yes"
	}


We pull out the status from the DDL record and return a value indicating "Yes"
to continue fixing the ticket issue or "No" to transition to the workflow's
end state.

Lastly, add the following to the script's imports:

	import com.liferay.portlet.dynamicdatamapping.storage.Field;

Now, the script accurately implements the condition logic we want. As a
reminder, all of the code was injected into our workflow's XML file within the
`<condition/>` element that represents our condition node.

---

![note](../../images/tip-pen-paper.png)**Note:** Make sure to correctly name the
transitions stemming from the condition node. In our example, the "No"
transition should point to the EndNode, while the "Yes" transition should point
to the Developer. If the condition script's return values do not match the
transition names, the workflow engine will not know which transition to use.

---

Here is a snapshot of our current ticket process workflow after inserting the
condition node:

![Figure 8.61: The ticket process workflow after inserting the condition
node.](../../images/kaleo-30.png)

Our workflow will need to a valid DDL record to invoke this workflow properly.
You may be thinking "How do we set up a DDL record?" or "How does this DDL
record thingy work?" Or you may even be jumping up and down screaming "Liferay
is da bomb!" We welcome your reaction. But, don't worry; we'll address DDLs soon
in the *Configuring a DDL record* section of this chapter.

Next, we'll create a custom notification and write a template for it
using a template editor.

### Leveraging template editors for notifications 

Designer lets you leverage FreeMarker and Velocity editors in customizing
templates for your workflow notifications. A FreeMarker editor comes bundled
with Developer Studio. For editing Velocity templates, you'll need to install an
editor.

#### Add-on: Install Velocity Editor Support 

Liferay Developer Studio comes with additional add-on support for editing
velocity template notifications in the Kaleo Designer for Java. To use this
feature go to the Developer Studio 1.6 customer page
[http://www.liferay.com/group/customer/products/developer-studio/1.6](http://www.liferay.com/group/customer/products/developer-studio/1.6),
navigate to the *Add-on: Install Velocity Editor Support* section, and download
the Liferay Developer Studio Velocity update site zip file and install it with
the following steps:

1. From Liferay Developer Studio, Click *Help* &rarr; *Install New Software...*

2. Click *Add...*

3. Click *Archive*.

4. Browse to the downloaded update site zip file and select it.

5. Click *OK* to close the *Add Repository* dialog.

6. Expand the added repository and check the "Liferay Developer Studio Velocity"
feature.

7. Uncheck the option "Contact all update sites..."

8. Click *Next* to progress through the wizard.

9. After reading and accepting the license agreement, click *Finish*.

9. Restart Liferay Developer Studio to complete the install.

If you edit a notification template that is set to the Velocity type, the
template opens in the Velocity editor (a small "v" icon is in the left side of
the editor's tab).

#### Creating notifications 

To access the template editors, click on the node of your choice and select the
*Notifications* sub-tab in your *Properties* view. Create a new notification by
clicking the green "plus" sign.

![Figure 8.62: When creating a notification, Developer Studio offers several options, like choosing 
a template language.](../../images/kaleo-16.png)

Specify the following for your notification:

-	*Name*
-	*Template Language*
-	*Execution Type*
-	*Notification Type*
-	*Notification Transports*
-	*Addresses*

Clicking the pencil icon opens the editor associated with your notification's
template language. Similar to the script editor, the template editor presents
entities that you can drag and drop from your palette onto your workflow
diagram.

![Figure 8.63: The template editor's palette has helpful entities that make
creating notifications quick and easy.](../../images/kaleo-17.png)

Because Developer Studio lets you leverage full featured template editors like
FreeMarker or Velocity, content-assist is available for you to use right away.
For example, if you are using the FreeMarker editor, content-assist suggests
FreeMarker functions when you are editing your notification template. In
addition, when you're doing a FreeMarker variable insertion, the editor gives
you all the available variables that are a part of the Kaleo workflow. You can
visit the documentation pages for [FreeMarker](http://freemarker.org/docs/) and
[Velocity](http://velocity.apache.org/engine/releases/velocity-1.5/user-guide.html)
for more information on the variables and functions available in these template
languages.

---

![note](../../images/tip-pen-paper.png)**Note:** Similar to the bundled script
editors, Developer Studio lets you use FreeMarker and Velocity template editors
to customize notifications in your workflow definition.

---

Let's continue editing our ticket process workflow by setting up a notification
email that is sent to our Project Management team upon completion of the QA task
assignments. First, we'll add a new task node that transitions from our join
node. This new task node will hold our email notification. Typically, we would
assign this task to a project management role and email it to the project
management team's email alias. But for demonstration purposes, we'll assign and
email "Joe Blogs". As mentioned previously, you can specify an existing user
that has an email or create a user with screen name "joe" having your email
address. This process is similar to how we assigned our previous task nodes.

1. Drag a new task node onto the workflow diagram.

2. Name the new task node *Project Management* and select *Assign to a specific
user*.

3. Click *Next*.

3. Enter *Screen name* "joe".

4. Click *Finish*.

Now all we have to do is incorporate the Project Management node into our
workflow process. Add a transition named *Passed QA* from our join node to the
Project Management node. Lastly, add a transition named *Completed* from our
Project Management node to our *EndNode*. Here is an updated screenshot of what
your workflow diagram should look like:

![Figure 8.64: This will be the final view for the workflow diagram; however we
still have work to do!](../../images/kaleo-31.png)

For our newly created Project Management task node, we will create an email
notification. As we discussed earlier, click on the Project Management node and
then in the Properties window, select *Notifications*.

To create the email notification, follow these steps:

1. Click the green "plus" symbol to create a new notification.

2. In the *Name* text field, enter "ticket process email".

3. Select the *Freemarker* template language from the drop-down menu.

4. Select the *On entry* execution type from the drop-down menu.

5. Select *email* as the notification transport.

The figure below depicts the steps we took defining our notification:

![Figure 8.65: After editing the notification inside the Properties view, you
can access the template editor by selecting the pencil
icon.](../../images/kaleo-33.png)

Now, click the pencil icon located beneath the green "plus" symbol. The
FreeMarker template editor appears.

Insert FreeMarker code into the FreeMarker editor to specify your email
notification template. We've provided an example code snippet of what an email
notification looks like written in FreeMarker.

    <#assign comments = taskComments!"">
    <#assign portalURL = serviceContext.portalURL!"">
    <#assign wTasksURL = portalURL+"/group/control_panel/manage?p_p_id=153&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&doAsGroupId="+groupId+"&refererPlid=">

    <!-- email body -->
    <p> There is a new submission of ${entryType} waiting for review in the workflow. </p>

    <!-- personal message to assignee -->
    <p> Please review the code waiting for you in your workflow tasks.
    <#if comments != "" > <br/> Assignment comment says: <strong>${comments}</strong> </#if>
    </p>
    <p> <a href="${wTasksURL}">Click here</a> to see workflow tasks assigned to you. </p>

    <!-- signature -->
    <p>Sincerely,<br /><strong>Liferay Portal Workflow</strong></p>

Furthermore, the snapshot below gives an illustration of what the snippet sends
to the configured email recipient.

![Figure 8.66: This is how the email notification will appear when it's received.](../../images/kaleo-43.png)

You've successfully completed setting up an email notification! Now, when the
Project Management task node is activated in the workflow, the user we specified
(i.e. *joe*) will receive the notification email dressed up with your FreeMarker
template.

As you can see, template editors, such as the FreeMarker template editors
bundled with Developer Studio, make customizing your notification templates
easier than ever. And you can create Velocity templates just as easy using the
Velocity editor you installed. In the next section, you can view a list of
workflow and service context content that can be used when creating a customized
script or template.

#### Workflow Context and Service Context Variables 

The purpose of a context variable is to provide a uniform variable that can be
inserted into templates and scripts. A context variable, when executed, is
automatically deleted and replaced with the value pertaining to that key. When
creating notifications for your workflow, assigning Liferay Portal context
variables allows for a cleaner and more efficient process. Instead of having
notifications follow the exact same format for every recipient, context
variables allow for customization and a personal look and feel. The context
variables you declare in your notifications refer to your Liferay instance and
the values it holds for your declarations.

Below, we have listed a plethora of context variables and service context
content. Notice the context variables are in a separate table, followed by the
service context content for web content, blog entries, and message board
messages. Service context content is separated from the workflow context
variables because service context keys depend on asset type, while context
variables do not. Also, note the `*`'s which indicate context variables that
depend on workflow activity.

| &nbsp;*Workflow* Context Variables |||
   Key | Type | Description |
------ | ---------- | ------------------|
   `companyId` | &nbsp;&nbsp;java.lang.String&nbsp;&nbsp;&nbsp; | Primary key of the company |          
   `entryClassName` | &nbsp;&nbsp;java.lang.String | Class name for entry used by the task (e.g. com.liferay.portlet.journal.model.JournalArticle) |
   `entryClassPK` | &nbsp;&nbsp;java.lang.String | Primary key of the entry class |
   `entryType` | &nbsp;&nbsp;java.lang.String | Type of entry used by the task (e.g. Web Content, Blog Entry, MB Message) |
   `groupId` | &nbsp;&nbsp;java.lang.string | Primary key of the assigned group |
   `taskComments*` | &nbsp;&nbsp;java.lang.String | Workflow comments assigned to the task |
   `taskName*` | &nbsp;&nbsp;java.lang.String | Workflow task that activates the notification (e.g. review) |
   `transitionName*` | &nbsp;&nbsp;java.lang.String | Name of transition pointing to the task (e.g. approve) |
   `userId` | &nbsp;&nbsp;java.lang.String | Primary key of the assigned user |
---

| &nbsp;*Web Content* Service Context Variables - obtain via key serviceContext |||
   Key | Type | Description |
------ | ---------- | ------------------|
   `articleId` | &nbsp;&nbsp;java.lang.String&nbsp;&nbsp;&nbsp; | Primary key of the web content |
   `articleURL` | &nbsp;&nbsp;java.lang.String | Link to the web content in maximized mode |
   `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the web content |
   `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset link search container |
   `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset |
   `autoArticleId` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether an article ID is generated (e.g. false) |
   `classNameId` | &nbsp;&nbsp;java.lang.String | Primary key of the class name used by the task |
   `classPK` | &nbsp;&nbsp;java.lang.String | Primary key of the model entity |
   `content` | &nbsp;&nbsp;java.lang.String | Content of the web content |
   `defaultLanguageId` | &nbsp;&nbsp;java.lang.String | Primary key of the default language (e.g. en_US) |
   `description_en_US` | &nbsp;&nbsp;java.lang.String | Description of the web content (in English) |
   `displayDateDay` | &nbsp;&nbsp;java.lang.String | Calendar day the web content is set to display (e.g. 12) |
   `displayDateHour` | &nbsp;&nbsp;java.lang.String | Hour the web content is set to display (e.g. 4) |
   `displayDateMinute` | &nbsp;&nbsp;java.lang.String | Minute the web content is set to display (e.g. 26) |
   `displayDateMonth` | &nbsp;&nbsp;java.lang.String | Month the web content is set to display (e.g. 5) |
   `displayDateYear` | &nbsp;&nbsp;java.lang.String | Year the web content is set to display (e.g. 2012) |
   `doAsGroupId` | &nbsp;&nbsp;java.lang.String | Primary key of the organization associated with the web content |
   `folderId` | &nbsp;&nbsp;java.lang.String | Primary key of the web content's folder |
   `indexable` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the web content is searchable (e.g. true) |
   `indexableCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Searchable* checkbox is checked (e.g. false) |
   `inputPermissionsShowOptions` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether permission options are viewable (e.g. true) |
   `inputPermissionsViewRole` | &nbsp;&nbsp;java.lang.String | Role type that has permission to view web content (e.g. Site Member) |
   `languageId` | &nbsp;&nbsp;java.lang.String | Primary key of the selected language (e.g. en_US) |
   `localized` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Localizable* checkbox is selected (e.g. false) |
   `neverExpire` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the web content is set to expire (e.g. true) |
   `neverExpireCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Never Auto Expire* checkbox is checked (e.g. false) |
   `neverReview` | &nbsp;&nbsp;java.lang.String |  Boolean variable indicating whether the web content is set to review (e.g. true) |
   `neverReviewCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Never Review* checkbox is checked (e.g. false) |
   `refererPlid` | &nbsp;&nbsp;java.lang.String | Primary key of the page hosting the web content |
   `smallImage` | &nbsp;&nbsp;java.lang.String | Indicates whether a small image is being used (e.g. on) |
   `smallImageURL` | &nbsp;&nbsp;java.lang.String | URL for the web content's attached image |
   `structureDescription` | &nbsp;&nbsp;java.lang.String | Description of the configured structure |
   `structureId` | &nbsp;&nbsp;java.lang.String | Primary key of the configured structure |
   `structureName` | &nbsp;&nbsp;java.lang.String | Name of the configured structure |
   `structureXSD` | &nbsp;&nbsp;java.lang.String | The XML schema definition used for the configured structure |
   `templateId` | &nbsp;&nbsp;java.lang.String | Primary key of the configured template |
   `title_en_US` | &nbsp;&nbsp;java.lang.String | Title of the web content (in English) |
   `type` | &nbsp;&nbsp;java.lang.String | Categorization type associated with the web content  (e.g. Press Release) |
   `variableName` | &nbsp;&nbsp;java.lang.String | Custom variable name set for the web content's configured structure (e.g. Content) |
   `version` | &nbsp;&nbsp;java.lang.String | Current version of the web content (e.g. 1.0) |
   `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 1) |
---

| &nbsp;*Blog Entry* Service Context Variables - obtain via key serviceContext  |||
   Key | Type | Description |
------ | ---------- | ------------------|
   `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String&nbsp;&nbsp;&nbsp; | Primary keys of the asset entries linked to the blog entry |
   `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the blog entry |
   `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset (e.g. history, news, programming) |
   `attachments` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if blog entry has any attachments (e.g. true) |
   `content` | &nbsp;&nbsp;java.lang.String | Content of the blog entry |
   `description` | &nbsp;&nbsp;java.lang.String | Description of the blog entry (e.g. The comparison between two Fortune 500 companies) |
   `displayDateAmPm` | &nbsp;&nbsp;java.lang.String | Time "period" (based on the 12-hour clock) the blog entry is set to display (AM=0, PM=1) |
   `displayDateDay` | &nbsp;&nbsp;java.lang.String | Calendar day the blog entry is set to display (e.g. 3) |
   `displayDateHour` | &nbsp;&nbsp;java.lang.String | Hour the blog entry is set to display (e.g. 26) |
   `displayDateMinute` | &nbsp;&nbsp;java.lang.String | Minute the blog entry is set to display (e.g. 32) |
   `displayDateMonth` | &nbsp;&nbsp;java.lang.String | Month the blog entry is set to display (e.g. 8) |
   `displayDateYear` | &nbsp;&nbsp;java.lang.String | Year the blog entry is set to display (e.g 2012) |
   `doAsGroupId` | &nbsp;&nbsp;java.lang.String | Primary key of the organization associated with the blog entry |
   `editor` | &nbsp;&nbsp;java.lang.String | Content of the blog entry (equivalent to the `content` value) |
   `entryId` | &nbsp;&nbsp;java.lang.String | Primary key of the blog entry |
   `refererPlid` | &nbsp;&nbsp;java.lang.String | Primary key of the page hosting the blog entry |
   `smallImage` | &nbsp;&nbsp;java.lang.String | Indicates whether a small image is being used (e.g. on) |
   `smallImageURL` | &nbsp;&nbsp;java.lang.String | URL for the blog entry's attached image |
   `title` | &nbsp;&nbsp;java.lang.String | Title of the blog entry (e.g. My Blog Entry) |
   `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 2) |
---

| &nbsp;*Message Board Message* Service Context Variables - obtain via key serviceContext |||
   Key | Type | Description |
------ | ---------- | ------------------|
   `anonymous` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if message is anonymous (e.g. true) |
   `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the message |
   `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset link search container |
   `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset (e.g. sea, sailing, swimming) |
   `attachments` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the message has any attachments (e.g. false) |
   `body` | &nbsp;&nbsp;java.lang.String | Content of the message |
   `editor` | &nbsp;&nbsp;java.lang.String | Content of the message (equivalent to the `body` value) |
   `mbCategoryId` | &nbsp;&nbsp;java.lang.String | Primary key of the message's category |
   `messageId` | &nbsp;&nbsp;java.lang.String | Primary key of the message |
   `parentMessageId` | &nbsp;&nbsp;java.lang.String | Primary key of the message's parent message |
   `preview` | &nbsp;&nbsp;java.lang.Boolean&nbsp;&nbsp; | Boolean variable indicating if message is available to preview (e.g. true) |
   `question` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if message is marked as a question (e.g. false) |
   `subject` | &nbsp;&nbsp;java.lang.String | Subject line of the message (e.g. My MB Subject) |
   `threadId` | &nbsp;&nbsp;java.lang.String | Primary key of the message board thread hosting the message |
   `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 3) |
---

Next, we'll explain how to view your workflow definition XML file within
Developer Studio.

### Viewing workflow definition XML source 

It is often convenient to view the workflow definition XML file as source code
to make edits to it and review changes you've made. To view the XML
source, select the *Source* tab next to the *Diagram* tab in the main editor
view.

![Figure 8.67: Feel free to switch between *Diagram* and *Source* modes of your
workflow editor in Kaleo Designer for Java.](../../images/kaleo-20.png)

Here is a list of some cool features Developer Studio offers in Source mode:

- *Section Highlighting*: shows the XML source for the node or transition
currently selected in the workflow diagram. Navigate to the entity's XML source
by clicking the pencil tool in its floating palette or by right clicking
the entity and selecting *Show in source*.

- *Editor Validation*: displays an error marking in the editor's gutter if your
code is invalid. Clicking the error marking displays a hint for resolving the
error.

- *Content Assist*: suggests language specific functions and variables as you
edit code. In addition, when you're doing variable insertion, the editor gives
you all the available variables that are a part of the Kaleo workflow.

---

![note](../../images/tip-pen-paper.png)**Note:** *Using Liferay Portal* section
[Creating new workflow
definitions](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/creating-process-definitio-1)
explains how to define workflows via XML.

---

Showing your XML file's source code is a great way to keep track of what you're
editing while using Developer Studio's powerful graphical features.

Let's dive into saving your workflow definition and publishing it to your
Liferay server.

### Publishing workflows to the server 

After you've created your new workflow or modified an existing workflow, you'll
want to publish it onto your Liferay server for your site's members to use.
We'll publish the ticket process workflow definition onto your Liferay server.

You can publish a new workflow definition in the following manner:

1. Right-click the *Kaleo Workflows* folder listed under your Liferay server in
the *Servers* view.

2. Select *Upload new workflow...* to bring up the *workspace files* browser.

3. Browse for your workflow definition file and select it for publishing.

![Figure 8.68: To upload a workflow definition, simply select *Upload new
workflow...* from the menu.](../../images/kaleo-18.png)

Alternatively, publish your new workflow XML file by dragging it from your
*Package Explorer* view onto your Liferay server in your *Servers* view.

---

![note](../../images/tip-pen-paper.png)**Note:** To update your Kaleo Workflows
folder with the latest workflow versions from Liferay Portal (e.g. those created
or modified in Portal using Kaleo Workflow Designer from the Kaleo Forms
portlet), right-click your Liferay server's *Kaleo Workflows* and select
*Refresh*.

---

The figure below shows our completed ticket process workflow definition is
published to our Liferay server:

![Figure 8.69: Uploading a new workflow definition is quick and
easy.](../../images/kaleo-19.png)

It makes sense that you need to publish *new* workflow definitions onto the
Liferay server, but why would you need to re-publish existing workflow
definitions?

When you make changes to a workflow, the changes are never made immediately
available to the rest of the portal. In Developer Studio, when you make changes
to a workflow definition and save the changes, Studio saves them to a draft. So
you can have multiple iterations on that version. For example, you might be
working on *Version 1* of your definition and as you make changes to it, you
save them in multiple drafts. Eventually when you are finished with all of your
changes, you publish the workflow triggering automatic creation of a new version
(*Version 2*) of that workflow. The new version of the workflow is made
available on the server immediately for your workflow administrators to
associate with asset publications, DDLs, and with Kaleo Forms.

Saving and publishing your workflow definition in Developer Studio is relatively
easy. Unlike other Java editors, Developer Studio lets you test your workflow
definition as a draft. Furthermore, Studio lets you publish your workflow
definition straight to Liferay Portal for quick and easy configuration.

For a finale of our overview for using Kaleo Designer for Java in Developer
Studio, let's set up the DDL record within Liferay Portal and try out our new
workflow!

### Using workflows in Portal 

Congratulations! You have successfully used Kaleo Designer for Java to create a
workflow! Now, let's put the finishing touches on your workflow and put it to
the test in Liferay Portal. Before you can use a workflow definition in your
portal, you must activate it. Navigate to the Control Panel and select
*Workflow*. Then, in the *Definitions* tab, click on the *Actions* button, and
select *Activate*.

![Figure 8.70: Liferay portal's Control Panel lets you activate/deactivate
workflow definitions.](../../images/kaleo-34.png)

A workflow definition can be associated with publication of an asset or DDL
record. Let's associate our ticket process workflow definition with a DDL record
that lets a developer indicate whether she will fix a ticket's issue. You can
reference how to specifically create a DDL by visiting the section [Defining
data
types](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/defining-data-typ-1)
in *Using Liferay Portal*. We'll demonstrate how easy it is.

### Using DDLs with workflows 

Dynamic Data Lists can be used for many different things, but for our example we
will integrate a DDL record into our ticket process workflow. If you would like
to learn more about Dynamic Data Lists, visit [Using Web Forms and Dynamic Data
Lists](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/dynamic-data-lists-in-lifer-1)
located in *Using Liferay Portal*.

Let's create a data definition that lets the user select a status value. Drag
and drop the *Select* field from the *Fields* tab onto the canvas for the
definition.

![Figure 8.71: Creating data definitions for your DDL is a snap with Liferay's
graphical drag and drop interface.](../../images/kaleo-35.png)

Then, from within the *Settings* tab give your field the name
*status*. Also, edit the *Options* setting giving your *status* field option
values of `fix` with label "Fix" and `not` with label "Do not fix".

![Figure 8.72: Set your data definition with appropriate values to use in your
workflow definition.](../../images/kaleo-37.png)

Recall the code we inserted for our condition node:

	Field field = ddlRecord.getField("status");

	String status = GetterUtil.getString(field.getValue());
	if (status.contains("not")) {
		returnValue = "No"
	}
	else {
		returnValue = "Yes"
	}

In our code, the `getField()` method ingests the value of our DDL field named
"status". When the script is invoked, if the value for the status field contains
"not", the value "No" is returned and our workflow transitions to our EndNode
state. Otherwise, the workflow transitions to our Developer task node.

After creating the data definition, make sure to select the ticket process
workflow for our new Kaleo Forms process to use. Lastly, save the DDL.

Now, our DDL is set for use inside our ticket process workflow! Lastly, we will
use the Kaleo Forms portlet to test our new workflow definition!

#### Using Kaleo Forms to run workflows 

Let's use the Kaleo Forms portlet to invoke our workflow from Liferay Portal.
Deploy the Kaleo Forms portlet to your portal and add it to a page on your site.
You can learn how to use Kaleo Forms in the [Kaleo
Forms](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen06-kaleo-forms-0)
section of *Using Liferay Portal*. But we'll give you a jump start on Kaleo
Forms by using it with our ticket process workflow.

1. Create a new *Process* within Kaleo Forms and give it the name *Ticket Process*.

2. Select the *status* data definition we created earlier.

3. Add an *Initial Form* based on our *status* data definition.

4. Select our ticket process workflow.

5. Leave *Workflow Task Forms* unassigned for our demonstration.

![Figure 8.73: To test the ticket process workflow, you will need to indicate
the name, entry definition, and workflow when creating a new
process.](../../images/kaleo-41.png)

After saving, select the *Summary* tab in Kaleo Forms, click the *Submit New*
button, and select *Ticket Process*.

![Figure 8.74: Submitting the ticket process invokes its corresponding
workflow.](../../images/kaleo-40.png)

You're now able to interact with the DDL and progress throughout the ticket
process using Kaleo Forms. Remember, you will need to sign in as Joe Bloggs to
access the tasks assigned to him. Joe Bloggs should also receive an email when
the Project Management node is activated.

You have successfully created a workflow definition and created a workflow
process within Liferay Portal! You are officially a workflow master!

Developer Studio offers a dynamic workflow diagram, which helps you visualize
your workflow and create steps to ensure a successful business process. Also,
the script and template editors give you a convenient workspace that caters to
any preference you may have as a developer. In addition, Developer Studio's
access to your custom business logic APIs and Liferay Portal APIs makes it
simple to customize your workflow definition. Lastly, the ability to publish
directly to your existing Liferay server makes the configuration process quick
and easy. With all of these great features, the Kaleo Designer for Java in
Developer Studio is a powerful tool that couldn't be easier to use!

## Conclusion 

In this chapter we've explored the Liferay IDE and have gone through
step-by-step instructions with regards to installation and setup, creating and
deploying Liferay projects, and importing existing projects into Liferay IDE.
You must admit -- it is a slick tool-set! And we hope you take advantage of it
in using Liferay Portal and developing your Liferay plugins.

Next, we'll get up-close and personal with Liferay's APIs!
<!-- Please leave the newline below this line -->

