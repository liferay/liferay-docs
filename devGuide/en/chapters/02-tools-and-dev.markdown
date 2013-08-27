# Working with Liferay's Developer Tools 

If you're anything like Liferay Portal's developers, you don't want to be
forced to work with one development technology. Our developers build Liferay
with the tools they prefer. That's why we strive to provide you with as much
flexibility as possible. You can develop your Liferay-based portal with tools
ranging in complexity from IDEs like Eclipse, Netbeans or IntelliJ Idea, to
text editors like Notepad, Vim, or Emacs. You can write your persistence layer
directly using SQL and JDBC, or use advanced object-relational mapping
libraries like Hibernate or iBatis. You get the idea. 

In this chapter we'll introduce and show you how to set up a streamlined
development environment specifically designed for developing your Liferay
Portal, then  we'll look at developing your plugins with other tools. Here are
the topics we'll cover along the way: 

- Developing Apps with Liferay IDE 
- Leveraging the Plugins SDK 
- Developing Plugins Using Maven 
- Liferay's Deprecation Policy 

Liferay's tool-agnosticism is great for experienced developers who understand
the strengths and weaknesses of different development technologies; it can be
overwhelming for newcomers, though. So we removed some of the options,
narrowing down your choices and forcing you to use a tool we like, right? No!
We actually added to the list of technologies you can use by developing
specific tools that soften the learning curve for Liferay plugin development,
and providing ways for you to use alternative tools. The most significant
Liferay-specific tool is Liferay IDE, a fully featured Integrated Development
Environment based on Eclipse. Alternatively, there's the Plugins Software
Development Kit (SDK), which is based on Apache Ant and can be used with any
editor or Integrated Development Environment (IDE) you'd like. If you'd like,
you can also use Apache Maven Archetypes; there are plenty of Liferay
archetypes you can use to develop your plugins. 

First let's consider the most robust tool for Liferay development, Liferay
IDE. 

## Developing Apps with Liferay IDE 

Even if you're a grizzled veteran of Java development, if you're going to be
doing a lot of development for your Liferay Portal instance, consider using
Liferay IDE. When Liferay IDE is mated with the Plugins SDK and a Liferay
runtime environment, you have a one stop development environment, where you can
develop your Liferay plugins, build them, and deploy them onto your Liferay
instance. 

Liferay IDE is an extension for Eclipse IDE, and supports development of plugin
projects for the Liferay Portal platform. You can install Liferay IDE as a set
of Eclipse plugins from an update site. The latest version of Liferay IDE
supports development of portlets, hooks, layout templates, themes, and ext
plugins. To use Liferay IDE, you need the Eclipse Java EE developer package
using Helios or a later version. 

In this section we'll get you started by showing you how to install Liferay
IDE, but we won't stop there; we'll take you through the setup process, get
your runtime environment set up, and start introducing you to some of Liferay
IDE's cool features. More complete demonstrations of Liferay IDE and it's
features can be found in the chapters specifically describing the development
of a plugin type (e.g., the *portlets* chapter). There's a helpful code
generation tool called Service Builder that we'll introduce here, but we've
given it a separate chapter in this guide because it deserves a through
description; check it out, we think you'll be impressed. how to develop
in-depth then develop some plugins with Liferay IDE. Last we'll discuss Liferay
IDE's Remote Server Adapter. 

To install and set up Liferay IDE, follow the instructions in the first two
subsections below. If you're already using *Liferay Developer Studio* (the king
of Liferay's development tools), which comes with Liferay Portal Enterprise
Edition, skip to the section titled *Testing/Launching Liferay Tomcat
Server*--Liferay IDE comes preconfigured in Developer Studio. 

### Installing Liferay IDE 

Liferay IDE is an extension of Eclipse IDE; before you install Liferay IDE,
let's make sure your Eclipse release can run Liferay IDE, and that you're using
a supported version of Java. Then we'll show you the installation process--we
give you two choices, depending on whether you want to enter an update site URL
for your Eclipse release. 

#### Requirements 

Make sure you have a supported Java JRE and Eclipse release: 

- Java 5.0 JRE or greater. 
- Liferay IDE must be run in one of the following Eclipse releases: 

    Eclipse Juno Java EE (4.2.x)

    Eclipse Indigo Java EE (3.7.x)

    Eclipse Helios Java EE (3.6.x)

Next we'll show you how to install Liferay IDE. 

#### Installation Steps 

To install Liferay IDE and specify an Eclipse update URL, follow these steps: 

1.  Install Eclipse Juno, Indigo, or Helios from the
    [Eclipse](http://www.eclipse.org) website. 

2.  Run the Eclipse executable file (e.g., `eclipse.exe`).

3.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

4.  In the *Work with* field, enter the update site URL for your Eclipse release
    and press *Enter*. Your update site URL is one of the following: 

    Eclipse Juno Update Site: 
    [http://releases.liferay.com/tools/ide/eclipse/juno/stable/](http://releases.liferay.com/tools/ide/eclipse/juno/stable/)

    Eclipse Indigo Update Site:
    [http://releases.liferay.com/tools/ide/eclipse/indigo/stable/](http://releases.liferay.com/tools/ide/eclipse/indigo/stable/)

    Eclipse Helios Update Site:
    [http://releases.liferay.com/tools/ide/eclipse/helios/stable/](http://releases.liferay.com/tools/ide/eclipse/helios/stable/)

5.  Liferay IDE features should be detected. Select them and click *Next*. 

6.  After calculating dependencies, click *Next* to complete the installation.

7.  Restart Eclipse to verify that Liferay IDE is properly installed.

8.  After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
    IDE icon badge as in the screenshot below, it's properly installed. 

![Figure 2.1: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

Next we'll show you how to install Liferay IDE without specifying an Eclipse
update URL. 

#### Alternative Installation 

To install Liferay IDE without specifying an update URL for Eclipse, follow
these steps: 

1.  Install Eclipse Juno, Indigo, or Helios (you can download and unzip from one
    of the links provided in the *Requirements* section above).

2.  Run the Eclipse executable file (e.g., `eclipse.exe`).

3.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

4.  Instead of entering a URL in the *Work with* field, download the latest
    version of Liferay IDE from the releases page: 

    [Liferay IDE Downloads
    Page](http://www.liferay.com/downloads/liferay-projects/liferay-ide)

5.  In the *Add Site* dialog, click the *Archive* button and browse to the location of
    the downloaded zip file.

6.  Liferay IDE features should be detected. Select them and click *Next*. 

7.  After calculating dependencies, click *Next* to complete the installation.

8.  Restart Eclipse to verify that Liferay IDE is properly installed.

9.  After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
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

1.  Liferay Portal 6.0.5 or greater is downloaded and unzipped. 

2.  Liferay Plugins SDK 6.0.5 or greater is downloaded and unzipped. 

3.  You've installed an appropriate Eclipse IDE version for Java EE Development,
    and the Liferay IDE extension--see the *Installation* section if you haven't
    already done this. 

---

![note](../../images/tip-pen-paper.png) **Note:** Earlier versions of Liferay
(e.g., 5.2.x) are not supported by the Liferay IDE. 

---

Let's set up your Liferay Plugins SDK. 

#### Setting Up Liferay Plugins SDK 

Before you begin creating new Liferay plugin projects, a supported Liferay
Plugins SDK and Liferay Portal must be installed and configured in your Liferay
IDE. If you're thinking, "Wait a second buster, you told me in the chapter
introduction that the Plguins SDK was an alternative to Liferay IDE!", then
hold on to yor hat for a minute and let us explain. In the second half of this
chapter we'll cover the Plugins SDK on its own, as used with a text editor;
here we're just interested in getting it running inside Liferay IDE. 

1.  In Eclipse, open the *Installed Plugin SDKs* dialog box--from your *Windows*
    dropdown menu, click *Preferences* &rarr; *Liferay* &rarr; *Installed Plugin
    SDKs*.

2.  Click *Add* to bring up the *Add SDK* Dialog. 

3.  Browse to your Plugins SDK installation. The default name is the directory
    name; you can change it if you want. 

4.  Select *OK* and verify that your SDK was added to the list of *Installed
    Liferay Plugin SDKs*. 

---

![note](../../images/tip-pen-paper.png) **Note:** You can have Multiple SDKs in
your preferences--set one as the default SDK by checking its box in the list of
*Installed Liferay Plugin SDKs*. 

---

Let's set up your Liferay Portal Tomcat runtime and server. 

#### Liferay Portal Tomcat Runtime and Server Setup 

1.  In Eclipse, open the *Server Runtime Environments* dialog box--go to
    *Window* &rarr; *Preferences* &rarr; *Server* &rarr; *Runtime Environments*. 

    ![Figure 2.2: Choosing a runtime environment](../../images/ide-prefs-runtime-env.png)

2.  Click *Add* to add a new Liferay runtime; find *Liferay v6.1 (Tomcat 7)*
    under the *Liferay, Inc.* category and click *Next*.

3.  Click *Browse* and select your `liferay-portal-6.1.x directory`. 

4.  If you've selected the Liferay portal directory and a bundle JRE is present,
    it is automatically selected as the server's launch JRE. If no JRE bundle
    is present, then you must select the JRE to use for launch by clicking
    *Installed JREs...*. 

    ![Figure 2.3: Choosing the JRE bundle](../../images/ide-server-jre.png)

5.  Click *Finish*; you should see your Liferay portal runtime listed in
    *Preferences* &rarr; *Server Runtime Environments*.

6.  Click *OK* to save your runtime preferences. 

7.  If you haven't created a server, create one now from the *Servers*
    view in Liferay IDE; then you can test the server. 

    ![Figure 2.4: Adding a runtime to the Servers view](../../images/ide-add-new-server.png)

8.  Scroll to the *Liferay, Inc* folder and select *Liferay v6... Server*.
    Choose the *Liferay v6...* runtime environment that you just created. 

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

Let's create a project to illustrate the process; have you heard of the hip new
social networking site for noses, *Nose-ster*? Well, the site's founder, Harry
Schnozz, wants us to develop some plugins for him, so let's create an
*event-listing-portlet* project to hold the site's portlet plugins. 

Your Plugins SDK and portal server are configured in Liferay IDE--let's create
a new Liferay plugin project in Liferay IDE. 

1. Go to File &rarr; New Project...  &rarr; Liferay &rarr; Liferay Plugin
   Project. 

2. In the *New Liferay Plugin Project* dialog box, your newly configured SDK
   and Liferay Runtime should be selected. The plugin type *portlet* is the
default plugin type. Here we'll create a portlet that will be used throughout
htis guide, and we'll call it *event-listing-portlet* in the *Project Name*
field. It should have a *Display Name* of *Event Listing*. Click *Finish*. 

    ![Figure 2.5: Entering the information for the new project](../../images/ide-new-liferay-project.png)

Our *event-listing-portlet* plugin project should appear in the package
explorer. Here's the project's directory structure: 

- `PORTLET-NAME/`
    - `docroot/WEB-INF/src`
    - `build.xml`
    - `docroot/`
        - `css/`
        - `js/`
        - `WEB-INF/`
            - `src/` - this folder is not created by default.
            - `liferay-display.xml`
            - `liferay-plugin-package.properties`
            - `liferay-portlet.xml`
            - `portlet.xml`
            - `web.xml` - this file is not created by default.
        - `icon.png`
        - `view.jsp`

<!--I pulled the above structure from the portlets chapter. In IDE when I
created the project though, there's also the docroot/WEB-INF/src folder and
three libraries (is that the right word):JRE System Library, Liferay
v6.2CE(Tomcat7), and Lifeay Portlet Plugin API. Should those be included here
for completeness? I added docroot/WEB-INF/src already.-->

Now you need to deploy your new plugin project to your Liferay Server. 

### Deploying New Liferay Projects to Liferay Server 

You have a plugin project, but you need to deploy it onto your Liferay
Server. Let's do it now: 

1.  Select your new plugin project then right click the Liferay Server in
    the *Servers* tab. 

2.  Select *Add and Remove...*. 

3.  Select your plugin project and click *Add* to deploy it to the server. 

4.  Click *Finish*. 

You should see the project get deployed to Liferay Tomcat server; in the console
you'll see a message indicating your new portlet is available for use. 

![Figure 2.6: Verifying the output in the Console view](../../images/ide-console-portlet-available.png)

Open *Liferay Portal Home* (`http://localhost:8080/` in most cases), log in,
and follow the instructions in the setup wizard. 

<!--
Log in within the following credentials: 

**User:** test@liferay.com

**Password:** test

--> 

Once you're logged in, click *Add* &rarr; *More*; expand the *Sample* category
and click the *Add* link next to your Event Listing application. You should see
the correctly deployed portlet on the page. 

Great, you can create projects in Liferay IDE! Let's find out how to import
existing projects into Liferay IDE. 

### Creating Plugins 

Now your Liferay IDE contains a plugin project with the skeleton of the Event
Listing portlet.  Along with the Event Listing portlet, let's create a Location
Listing porlet inside the same project: 

1. Go to File &rarr; New  &rarr; Liferay Portlet. 

    Alternatively, right click on your `events-listing-portlet` project in
    Liferay IDE's *Package Explorer*, and select *New* &rarr; *Liferay
    Portlet*. 

2. The *New Liferay Portlet* dialog box will appear, with your newly created
plugin project *event-listing-portlet* selected as the *Portlet plugin project*
by default. We recommend naming your *Portlet class* after the name of your
portlet. We'll name the class *LocationListingPorlet* in this example.  We'll
name our *Java package* after the plugin's parent project, so it will be
*com.nosester.portlet.eventListing* for our example. You can deviate from the
conventions we used here if you like. 

    Click *Next*.

3. In the Next window of the *New Liferay Portlet* wizard, you'll specify
deployment descriptor details for your portlet. First enter the *Name* of your
portlet--in our example this will be *locationlisting*.  Subsequently enter the
portlet's *Display name* and *Title*, which we'll specify as *Location Listing
Portlet*. Also in this window you can specify which portlet modes youd like
your portlet to have.  *View* mode is automatically selected. Click *Next*.

4. In the next window, set the location for your portlets custom icon, CSS,
and JavaScript. You can also choose the category for your portlet (it's
categorized under *Sample* by default), and add it to the *Control Panel* of
your Liferay Portal. Click *Next*. 

5. The last step is to specify modifiers, interfaces, and method stubs to
   generate in the Portlet class. Once you're done, click *Finish*. 

![Figure 2.7: Liferay IDE's portlet creation wizard.](../../images/ide-portlet-plugin-wizard-1.png)

By default, new portlets use the MVCPortlet framework, a light framework that
hides part of the complexity of portlets and makes the most common operations
easier. The default MVCPortlet project uses separate JSPs for each portlet
mode: each of the registered portlet modes has a corresponding JSP with the
same name as the mode. For example, 'edit.jsp' is for edit mode and 'help.jsp'
is for help mode. 

To finish, we'll redeploy the plugin project. In the *Servers* tab, simply
right click the *event-listing-portlet* project, then click *Redeploy*. 

Now you've created and deployed the *Location Listing* portlet. Next we'll show
you how to import existing projects into Liferay IDE. 

### Importing Existing Projects into Liferay IDE 

What if you have existing projects that you want to work on in Liferay IDE?
This section shows you how to import your existing projects so you don't have
to recreate them. 

We'll cover the following import scenarios: 

1.  Importing existing Liferay projects from the Plugins SDK. 

2.  Importing Eclipse projects that aren't in Liferay IDE (i.e., don't have
    the Liferay facet or target runtime).

3.  Importing existing Liferay IDE projects from another Liferay IDE version or
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

![note](../../images/tip-pen-paper.png) **Note:** This section assumes that
you've created projects with the Plugins SDK and are familiar with the
directory structure used by the Plugins SDK. If you haven't and if you aren't,
check out the *Plugins SDK* section of this chapter; it comes right after this
section. 

---

First let's look at the import steps needed for creating a single Liferay IDE
project from a Plugins SDK project: 

1.  In Liferay IDE, go to *File* &rarr; *New* &rarr; *Project...* &rarr;
    *Liferay* &rarr; *Liferay Project from Existing Source*. 

    You can invoke the same wizard from the Liferay shortcut bar; just click
    the *Create a New Liferay Project* button.

    ![Figure 2.8: Alternate method of Importing a project](../../images/ide-new-proj-existing-source.png)

2.  Browse to your project folder. It should be a subdirectory of one of the
    plugin types (e.g., portlets, hooks, themes, etc) or it won't import
    correctly. Once you select the plugin project folder, its type and SDK
    version values are updated to the correct values. If the SDK isn't recent
    enough or the project type is incorrect, it's marked with an error. 

3.  After you select the plugin project folder, the *Liferay plugin type* and
    *Liferay plugin SDK version* values are updated. If your Plugins SDK is
    outdated or you entered an incorrect project type, its field gets marked
    with an error. 

4.  Select the *Liferay target runtime* to configure on the project once it's
    imported. If you don't have a Liferay Portal Runtime, use the *New...*
    button to create one now. 

5.  Click *Finish* to perform the import. 

Remember to verify that your project was configured correctly as a Liferay IDE
project. Verification is described right after our next topic, which is making
multiple Liferay IDE projects from a single Plugins SDK. 

Next we'll show you how to import all your Plugins SDK into multiple Liferay
IDE projects. You can use these steps: 

1.  In Eclipse go to *File* &rarr; *Import*... &rarr; *Liferay* &rarr; *Liferay
    Projects from Plugins SDK*. 

    ![Figure 2.9: Choosing the project type for import](../../images/ide-import-from-plugin-sdk.png)

2.  In the *Import Projects* window, use the combo box to select the *Liferay
    Plugins SDK* you're importing plugins from. 

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** If your SDK isn't
    configured in Liferay IDE (i.e., it's not in the dropdown list of the
    *Import Projects* window), use the *Configure* link to add one. To
    configure a Plugins SDK on the Installed SDKs on the pref page just click
    *Add* and then Browse to the directory of the Plugins SDK root directory.

    ---

3.  Once you select your Plugins SDK in the combo box, the *Liferay Plugin SDK
    Location* and *Liferay Plugin SDK Version* fields are automatically filled
    in, as long as they're valid. Invalid entries are marked with an error. 

4.  The list of projects that are available for import are displayed in a table.
    Any projects already in the workspace are disabled. Projects available for
    import have an empty check box; select each project you'd like to import. 

5.  Select the Liferay runtime you want to set up for the imported projects. 

6.  If you don't have a Liferay runtime, can add one now with the *New...*
    button. 

7.  Click *Finish*. 

Your plugins are imported! Now skip to the end of this section to verify that
they were configured correctly as Liferay IDE projects. We'll talk about a
different import scenario next; importing existing eclipse projects into Liferay
IDE. 

#### Importing Existing Eclipse Projects into Liferay IDE 

The steps we outlined above let you import projects not already in an Eclipse
workspace. If you have a project in your Eclipse workspace (i.e., you can see it
in Eclipse's project explorer) that you'd like to import to Liferay IDE, follow
the import steps below to convert your project. 

1.  In Eclipse, right-click the project in your Project Explorer, and select
    *Liferay* &rarr; *Convert to Liferay plugin project*. 

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** If you don't have a convert
    action available it means either that the project is already a Liferay IDE
    project, or that it is not faceted (i.e., Java and Dynamic Web project facets
    are not yet configured). Configure accordingly before moving on. 

    ---

2.  In the *Convert Project* wizard, your project should be selected and the SDK
    location and SDK version should be detected automatically. If they aren't
    valid, an error message is displayed. 

    ![Figure 2.10: Converting a project](../../images/ide-convert-plugin-project.png)

3.  Select the Liferay runtime to use for the project. If you don't have a
    Liferay Runtime defined, do so now by clicking *New...*. 

4.  Click *Finish*. 

Remember to verify that your import was successful and that your project was
configured correctly as a Liferay IDE project; the last subsection in this topic
will show you how. We have one more import scenario to cover--importing existing
Liferay IDE projects into your Liferay IDE. 

#### Importing an Existing Liferay IDE Project 

What if you had a Liferay IDE project in your workspace, but it's not there
anymore? Here's how you can import it into your current workspace: 

1.  Open Liferay IDE, go to *File* &rarr; *Import* ... &rarr; *General* &rarr;
    *Existing Projects into Workspace*. 

2.  Choose the option *Select root directory*, then click *Browse...*. 

3.  Select the directory of the Liferay IDE project to import. 

4.  In the *Projects:* list you should see your Liferay IDE project. 

5.  Click *Finish*. 

---

![note](../../images/tip-pen-paper.png) **Note:** There are two common errors
that occur when importing existing Liferay IDE projects into your workspace:

- The SDK name used in that project doesn't exist in your new workspace. 
- The runtime id used doesn't exist in your new workspace. 

You can modify the SDK name in *Project Properties* &rarr; *Liferay* dialog
window; you can modify the targeted runtime in the *Project properties* &rarr;
*Targeted Runtimes* dialog window. 

---

Let's verify the success of your imports and ensure that they're properly
configured as Liferay IDE projects. 

### Verifying That the Import Has Succeeded 

After importing projects into Liferay IDE, you'll want to verify that they
imported successfully, and that they're properly configured as Liferay IDE
projects. Here's how you verify that your imports were successful: 

1.  Once the project is imported, you should see a new project inside Eclipse
    and it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 2.11: Verifying that the import succeeded](../../images/ide-liferay-project-w-overlay-image.png)

2.  Let's check the project's target runtime and facets to make sure it's
    configured as a *Liferay IDE* project:

    2.1. In the *Package Explorer*, right click *<project-name>* &rarr;
         *Properties* &rarr; *Targeted Runtimes*. 

    2.2. In the *Properties* window, click *Project Facets* and make sure both
         Liferay runtime and Liferay plugin facets are properly configured. 

    ![Figure 2.12: Project Facets](../../images/ide-project-facets.png)

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
  Liferay that provides an API for Liferay IDE's Remote Server Adapter to use
  for all its remote operations. 

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
install the following resources on your local development machine:

- Download [Liferay
  IDE](http://www.liferay.com/downloads/liferay-projects/liferay-ide) from
  Liferay's downloads page or download [Liferay Developer
  Studio](http://www.liferay.com/group/customer/products/developer-studio/1.6)
  from the Customer Portal.
- Download [Liferay Portal CE or
  EE](http://www.liferay.com/downloads/liferay-portal/available-releases), to
  your local development machine. 

You'll need to download [Liferay Portal CE or
EE](http://www.liferay.com/downloads/liferay-portal/available-releases) to your
remote (test) server as well. 

Our demonstration uses the Remote Server Adapter on Liferay Portal bundled with
Apache Tomcat, but you can use the adapter with Liferay Portal running on any
application server Liferay Portal supports. Install Liferay Portal locally to
compile the plugins you develop. Install Liferay Portal on your remote test
server to host the plugins you'll deploy to it. 

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

1.  Start your remote Liferay Portal instance--verify that you can log in as an
    administrator. 

2.  Launch Liferay IDE and open the new server wizard by clicking *File* &rarr;
    *New* &rarr; *Other*; select *Server* in the Server category and click
    *Next*. Select *Remote Liferay Server (Liferay 6.1)* in the Liferay, Inc.
    category.

3.  Enter the IP address of the machine with the remote Liferay Portal instance
    into the *Server's host name* field. For the *Server name*, enter
    *Liferay@[IP address]*, then click *Next*. 

    ![Figure 2.13: Define the remote Liferay server.](../../images/remote_server_adapter_wizard_1.png)

4.  The New Server wizard's next page directs you to define the Liferay
    Portal runtime stub. Doing so allows projects created for your remote server
    to use the runtime stub for satisfying JAR dependencies needed to compile
    various Liferay projects. Select the *Liferay bundle type* based on the
    version of your local Liferay bundle, browse to the *Liferay bundle
    directory*, and click *Next*. 

5.  On the next page of the wizard, configure your connection to your remote
    Liferay instance: 

    *Hostname*: Enter the IP address of your remote Liferay Portal instance's
    machine. 

    *HTTP Port*: Enter the port it runs on (default: 8080). 

    *Username* and *Password*: Enter your administrator credentials for the
    remote Liferay Portal instance. 

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

    ![Figure 2.14: Click *Purchased* in the Marketplace section of the Control Panel to download and install the Remote IDE Connector application that you purchased.](../../images/remote_server_adapter_marketplace_2.png)

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

#### Using the Remote Server Adapter

Once your remote Liferay Portal server is correctly configured and your local
Liferay IDE is connected to it, you can begin publishing projects to it and
using it as you would a local Liferay Portal server. 

Here's how to publish plugin projects to your remote server in Liferay IDE: 

1.  Right click on the server and choose *Add and Remove...*. 

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** Make sure you have available
    projects configured in Liferay IDE. If not, you'll get an error message
    indicating there are no available resources to add or remove from the server. 

    ---

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

Have you noticed that there's a lot of depth to Liferay IDE? You can easily come
across difficult questions and run into very specific problems, but someone else
might have already solved your issue or answered your question. So where would
you go to find out? Don't reinvent the wheel, visit the [Liferay IDE Community
page](http://www.liferay.com/community/liferay-projects/liferay-ide/overview)!
On the *Forums* page, you can look up resolutions to specific errors and ask
questions. Be sure to fully describe any problems you have to ensure you get a
working answer. You can even track known issues from the *Issue Tracker* page. 

<!--This last paragraph feels too broad to be stuck in the ## Using the Remote
Server Adapter section. Maybe it should go in the introduction or at the end of
the Liferay IDE section? -Russ-->

Next let's talk about Liferay's Service Builder code generation tool,

### Using the Service Builder Graphical Editor 

Service Builder is a model-based code generation tool that lets you define
custom object models called entities. Service Builder reads the contents of a
file you create called `service.xml` and automatically creates your
application's model, persistence, and service layers, freeing you to focus on
the higher level aspects of your applications code. 

Why should you use Service Builder? Because it lets different portlets access
the same data and application logic, creating an underlying framework that
supports a portal environment. If your database access code is buried in a
single application's code, it can't readily be shared with other applications,
and your efforts will be duplicated with each application you write. Service
Builder puts the generated code in a service `JAR` file inside of one plugin,
but it can be easily shared among all portlets. 

In the Service Builder chapter of this guide we'll show you how two custom
portlets, the Events Listing Portlet and the Location Listing Portlet, can be
developed more efficiently and modularly by using Service Builder. We'll
describe the contents of `service.xml` in detail, and get you started using
Service Builder to develop your custom applications using our code generation
tool. And if *code generator* is a bad word to you, let us assure you that
Liferay always gives you full control over your code, even code generated by
Serivce Builder. 

<!--Next we'll show you how to use Tag Libraery Snippets in Liferay IDE. -->

<!--### Using Tag Library Snippets -->

<!--Add content-->

## Leveraging the Plugins SDK 

Java developers use a wide variety of tools and development environments.
Liferay makes every effort to remain tool agnostic, so you can choose the tools
that work best for you. If you don't want to use Liferay IDE, we also provide a
Plugins Software Development Kit (SDK), the Plugins SDK is based on Apache Ant,
and can be used along with any editor or Integrated Development Environment
(IDE). 
 
In this section we'll get you set up with the Plugins SDK, discuss its file
structure and available ant targets, and share some best practices to help you
get the most out of the Plugins SDK. 

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

![note](../../images/tip-pen-paper.png) **Note:** In Liferay Developer Studio,
the SDK is already installed and ready to use. Liferay Portal Enterprise
Edition (EE) comes with Liferay Developer Studio and much more (see [CE vs
EE](http://www.liferay.com/downloads/liferay-portal/overview)). Download a
[free
trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
of Liferay Portal EE today. 

---

Installation steps:

1.   Download The Plugins SDK from our web site at
     [http://www.liferay.com](http://www.liferay.com). 

     Click the *Downloads* link at the top of the page. 

     From the *Liferay Portal 6.1 Community Edition* section, select the
     *Plugins SDK* option. 

     Click *Download*. 

2.  Unzip the archive to a folder of your choosing. Because some operating
    systems have trouble running Java applications from folders with names
    containing spaces, avoid using spaces when naming your folder.

---

![tip](../../images/tip-pen-paper.png) **Tip**: By default, Liferay Portal
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

1.  Select *Start*, then right-select *Computer* &rarr; *Properties*. 

2.  In the *Advanced* tab, click *Environment Variables...*. 

3.  In the *System variables* section, click *New...*. 

4.  Set the `ANT_HOME` variable:

    *Variable name:* `ANT_HOME`. 

    *Variable value:* *<Ant installation path>* (e.g.,
    `C:\Java\apache-ant-<version>`). 

    Click *OK*. 

5.  Also in the *System variables* section, select your path variable and click
    *Edit...*. 

6.  Insert `%ANT_HOME%\bin;` after `%JAVA_HOME%\bin;` and click *OK*. 

7.  Click *OK* to close all system property windows. 

8.  Open a new command prompt for your new environment variables to take affect. 

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
user name is *jsmith*, your file name would be `build.jsmith.properties`. 

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
    - `ext/` - Ext plugins directory. See Chapter 12 on Ext plugins. 

    - `hooks/` - hook plugins directory. See Chapter 10 on hooks. 
    - `layouttpl/` - layout templates directory. See Chapter 9 on creating
      Liferay Themes and Layout Templates. 
    - `lib/` - commonly referenced libraries. 
    - `misc/` - development configuration files. Example, a source code
      formatting specification file. 
    - `portlets/` - portlet plugins directory. See Chapter 3 on portlet
      development. 
    - `themes/` - themes plugins directory. See Chapter 9 on creating Liferay
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

There's an Ant build file called `build.xml` in each of the plugins
directories. Here are some Ant targets you'll commonly use in developing your
plugins: 

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
projects. Or, you can further separate your projects by having a different
Plugins SDK project for each portlet or theme project. 

It's also possible to use the Plugins SDK as a simple cross-platform project
generator. Create a plugin project using the Plugins SDK and then copy the
resulting project folder to your IDE of choice. You'll have to manually modify
the Ant scripts, but this process makes it possible to create plugins with the
Plugins SDK while conforming to the strict standards some organizations have for
their Java projects. 

Now you know of two Liferay-specific tools that streamline development in
Liferay. But both use the Apache Ant build tool. If that's a deal breaker for
you, consider using Liferay's Apache Maven archetypes to build your custom
Liferay plugins. We'll look at Maven next, and we'll have some fun with classic
poetry while doing it. 

## Developing Plugins Using Maven 

"Once upon a midnight dreary, while I pondered weak and weary..."

Here's the scene--you're sitting in a luxurious armchair next to a dancing fire,
hot beverage in hand. Shadows dance on the tapestry-covered wall, and your cat
*Lenore II* is purring softly from her favorite perch atop the mantle.

"Ah, distinctly I remember it was in the bleak December..."

At least you're passing this cold December night in grand style (in front of
your computer customizing Liferay Portal, of course). 

"Eagerly I wished the morrow;--vainly I had sought to borrow 
From *Liferay* surcease of sorrow--sorrow for my last Lenore--" 

We're sorry to hear your previous cat, the original *Lenore*, has passed away.
Just take good care of Lenore II, would you? 

"And the silken sad uncertain rustling of each purple curtain
Thrilled me--filled me with *Ant*astic terrors never felt before;"

Okay now you're being melodramatic; nobody can disdain *Apache Ant* that
vehemently. What about customizing Liferay Portal using the Ant-based Plugins
SDK could make you feel sadness and terror? 

"Deep into that darkness peering, long I stood there wondering, fearing..."

We get it! You don't want to use our Ant-based Plugins SDK. Give us surcease
from the melodrama, okay? 

"Open here I flung the shutter, when, with many a flirt and flutter,
In there stepped a stately *Maven* of the saintly days of yore."

So, you'd rather use Apache Maven to develop your Liferay plugins? 

"But *Apache Maven* still beguiling all my sad soul into smiling..."

Edgar Allen Poe liked Maven too, so you're in good company. Trust us; we know.
But if your soul was made sad because you thought you had to use Liferay's
Ant-based Plugins SDK to develop your plugins, Apache Maven will make your sad
soul smile. And while you're at it, take care of Lenore II for all of us animal
lovers, would you? 

Quoth the Maven, "Let us proceed undaunted in exploration of these topics:" 

- Installing Maven 
- Using Maven Repositories
- Installing Required Liferay Artifacts 
- Using a Parent Plugin Project
- Creating Liferay Plugins with Maven
- Deploying Liferay Plugins with Maven
- Liferay Plugin Types to Develop with Maven

As an alternative to developing plugins using the Plugins SDK, you can leverage
the Apache Maven build management framework. Here's a list of some exciting
Maven features: 

- Offers a simple build process. 
- Features a project object model. 
- Has a defined project life cycle. 
- Provides a dependency management system. 

Maven's core installation is lightweight; there are core plugins for compiling
source code and creating distributions, and there is an abundance of non-core
plugins, letting you extend Maven easily for your customizations. 

Many developers are switching from Ant to Maven because it offers a common

Many developers are switching from Ant to Maven because it offers a common
interface for project builds. Maven's universal directory structure makes it
easier for you to understand another developer's project more quickly. With
Maven, there's a simple process to build, install, and deploy project artifacts. 

Maven uses a *project object model (POM)* to describe a software project. The
POM is specified as XML in a file named `pom.xml`. Think of `pom.xml` as a
blueprint for your entire project; it describes your project's directories,
required plugins, build sequence, and dependencies. The POM is your project's
sole descriptive declaration. Once you create the `pom.xml` file and invoke the
build process, Maven does the rest, downloading your project's inferred
dependencies and building your project artifacts. If you're not already familiar
with how Maven works, you can get familiar with Maven's project object model by
reading Sonatype's documentation for it at
[http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html](http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html). 

Maven provides a clear definition of a project's structure and manages a project
from a single piece of information--its POM. Understanding a Maven project can
be much easier than understanding an Ant-based project's various build files.
Maven forces projects to conform to a standard build process, whereas Ant
projects can be built differently from project to project. Also, Maven provides
an easy way to share artifacts (e.g., JARs, WARs, etc.) across projects via
public repositories.

There are disadvantages to using Maven. You might find the Maven project
structure too restrictive, or decide that reorganizing your projects to work
with Maven is too cumbersome. Maven is intended primarily for Java-based
projects, so it can be difficult to manage your project's non-Java source code.
Consider Maven's advantages and disadvantages, then decide how you want to
manage your projects. After you're finished reading about Maven here, you can
read an in-depth book about Maven at *Maven: The Complete Reference* by
Sonatype, Inc. at
[http://www.sonatype.com/books/mvnref-book/reference/](http://www.sonatype.com/books/mvnref-book/reference/). 

Liferay provides Maven archetypes to help you build plugins of various types,
including Liferay portlets, themes, hooks, layout templates, web plugins, and
more. You can also install and deploy Liferay artifacts to your repositories.
We'll dive into all these topics in this chapter.

"Straight I wheeled a cushioned seat in front of computer desk once more;
Then, upon the velvet falling, I betook to Maven installing..."

### Installing Maven 

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi). We
recommend putting your Maven installation's `bin` directory in your system's
path, so you can run the Maven executable (`mvn`) easily from your command
prompt. 

Let's learn about the types of repositories you can use with Maven projects. 

### Using Maven Repositories 

Wouldn't it be nice to install and deploy your Liferay artifacts to a
repository? Great news! Maven lets you install your artifacts to your machine's
local repository and even deploy them to remote repositories; so you can share
them privately with your team or with the public for general consumption.
Your *local* repository holds your downloaded artifacts and those artifacts you
install to it. *Remote* repositories are for sharing artifacts either privately
(e.g., within your development team) or publicly. To learn more about using
artifact repositories see
[http://maven.apache.org/guides/introduction/introduction-to-repositories.html](http://maven.apache.org/guides/introduction/introduction-to-repositories.html). 

Maven also lets you configure a proxy server; it mediates your requests to
public Maven repositories and caches artifacts locally. Using a local
proxy/repository helps you build projects faster and more reliably. You want
this for two reasons: accessing remote repositories is slower, and remote
repositories are sometimes unavailable. Most Maven proxy servers can also host
private repositories that hold only your private artifacts. If you're interested
in running your repository behind a proxy, see
[http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html](http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html). 

Now that you've been introduced to Maven repositories and proxy servers, let's
consider using a repository management server to create and manage your Maven
repositories. 

#### Managing Maven Repositories 

You'll frequently want to share Liferay artifacts and plugins with teammates, or
manage your repositories using a GUI. For this, you'll want Nexus OSS. It's a
Maven repository management server that facilitates creating and managing
release servers, snapshot servers, and proxy servers. If you're not interested
in using Nexus as a repository management server, feel free to skip this
section.

Let's create a Maven repository using Nexus OSS. If you haven't already,
download Nexus OSS from
[http://www.sonatype.org/nexus/](http://www.sonatype.org/nexus/) and follow
instructions at
[http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html](http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html)
to install and start it. 

To create a repository using Nexus, follow these steps: 

1.  Open your web browser; navigate to your Nexus repository server (e.g.,
    [http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in. 

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*. 

    ![Figure 2.15: Adding a repository to hold your Liferay artifacts is easy with Nexus OSS.](../../images/maven-nexus-create-repo.png)

    ![note](../../images/tip-pen-paper.png) **Note:** To learn more about each
    type of Nexus repository, read Sonatype's *Managing Repositories* at
    [http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html](http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html).

3.  Enter repository properties appropriate to the access you'll provide its
    artifacts. We're installing release version artifacts into this repository,
    so specify *Release* as the repository policy. Below are examples of
    repository property values: 

    *Repository ID:* `liferay-releases`

    *Repository Name:* `Liferay Release Repository`

    *Provider:* `Maven2`

    *Repository Policy:* `Release`

4.  Click *Save*.

You just created a Maven repository accessible from your Nexus OSS repository
server! Congratulations! 

Let's create a Maven repository to hold snapshots of each Liferay plugin we
create. Creating a *snapshot* repository is almost identical to creating a
*release* repository. The only difference is that we'll specify *Snapshot* as
its repository policy:

<!-- What is a snapshot? Sounds like Maven terminology that the reader might not
know. -->

1.  Go to your Nexus repository server in your web browser.

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*.

3.  Specify repository properties like the following:

    *Repository ID:* `liferay-snapshots`

    *Repository Name:* `Liferay Snapshot Repository`

    *Provider:* `Maven2`

    *Repository Policy:* `Snapshot`

4.  Click *Save*.

Voila! You not only have a repository for your Liferay releases (i.e.,
`liferay-releases`), you also have a repository for your Liferay plugin
snapshots (i.e., `liferay-snapshots`). 

Let's configure your new repository servers in your Maven environment so you can
install artifacts to the them. 

#### Configuring Local Maven Settings 

Before using your repository servers and/or any repository mirrors, you must
specify them in your Maven environment settings. Your repository settings enable
Maven to find the repository and get access to it for retrieving and installing
artifacts. 

---

![note](../../images/tip-pen-paper.png) **Note:** You only need to configure a
repository server if you're installing downloaded Liferay CE/EE artifacts from
a zip file or if you want to share artifacts (e.g., Liferay artifacts and/or
your plugins) with others. If you're automatically installing Liferay CE
artifacts from the Central Repository and aren't interested in sharing
artifacts, you don't need a repository server specified in your Maven settings.

However, configuring a mirror in your Maven settings is recommended as a best
practice. Get more information on mirrors and their purpose in Maven's guide on
mirrors at
[http://maven.apache.org/guides/mini/guide-mirror-settings.html](http://maven.apache.org/guides/mini/guide-mirror-settings.html). 

---

To configure your Maven environment to access your `liferay-releases` repository
server, do the following:

1.  Navigate to your `${USER_HOME}/.m2/` directory.

2.  Open your `settings.xml` file. If it doesn't yet exist, create it. 

3.  Provide settings for your repository servers. Here are contents from a
    `settings.xml` file that has `liferay-releases` and `liferay-snapshots`
    repository servers configured: 

        <?xml version="1.0"?>
        <settings>
            <servers>
                <server>
                    <id>liferay-releases</id>
                    <username>admin</username>
                    <password>admin123</password>
                </server>
                <server>
                    <id>liferay-snapshots</id>
                    <username>admin</username>
                    <password>admin123</password>
                </server>
            </servers>
        </settings>

---

![note](../../images/tip-pen-paper.png) **Note:** The username `admin` and
password `admin123` are the credentials of the default Nexus OSS administrator
account. If you changed these credentials for your Nexus server, make sure to
update `settings.xml` with these changes.

---

Now that your repositories are configured, they're ready to receive all the
Liferay Maven artifacts you'll download and the Liferay plugin artifacts you'll
create!

Now, let's install the Liferay artifacts you'll need to create your plugins.

###  Installing Required Liferay Artifacts 

To create Liferay plugins using Maven, you'll need the archives required by
Liferay (e.g., required JAR and WAR files). This won't be problem--Liferay
provides them as Maven artifacts. 

So how do you get the Liferay artifacts? The exact process depends on whether
you're building plugins for Liferay EE or Liferay CE. If you're building plugins
for Liferay EE, you'll have to install the Liferay Artifacts manually from a zip
file. You can do the same if you're building plugins for Liferay CE, but there's
a simpler option available: you can automatically install CE artifacts
from the Central Repository. Alternatively for Liferay CE, if you absolutely
must the latest pre-release changes from our Liferay CE source repository, you
can build the Liferay CE artifacts yourself. We'll demonstrate each of these
options. 

---

![note](../../images/tip-pen-paper.png) **Note:** The EE and CE zip files are a
means to *install* the artifacts to a Maven repository of your choice. In the
next few sections, we'll demonstrate the zip file and Central Repository
installation options. 

---

Let's look at the manual process first, by downloading and installing Liferay
artifacts from a zip file. 

#### Installing EE/CE Artifacts from a Zip File 

Whether you're building plugins for Liferay EE or CE, you can get the Liferay
artifacts by manually installing them from a zip file. Let's download the
Liferay EE artifacts first.

You can download the Liferay EE artifacts package from Liferay's Customer
Portal. Just follow these steps: 

1.  Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2.  Go to *Places* &rarr; *Customer Portal*.

3.  Select *Liferay Portal* from the *Downloads* panel.

4.  Inside *Filter by:*, select the appropriate Liferay version in the first
    field and select the *Development* value in the second field.

    ![Figure 2.16: You can download the Liferay Maven EE artifacts from the Liferay Customer Portal.](../../images/maven-customer-portal.png)

5.  Click *Download* under the desired *Liferay Portal [Version] Maven*. 

    The Liferay Maven EE artifacts package downloads to your machine.

Get the artifacts for Liferay CE from SourceForge by following these steps:

1.  Open your browser to *Liferay Portal* on SourceForge &rarr;
    [http://sourceforge.net/projects/lportal/files/Liferay%20Portal/](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).

2.  Select the Liferay version for which you need Maven artifacts. For example,
    if you need Maven artifacts for Liferay Portal 6.1.1 CE GA2, select version
    *6.1.1 GA2*. 

    ![Figure 2.17: After selecting the Liferay version, simply select the Liferay Portal Maven zip file to download.](../../images/maven-select-download.png)

3.  Select the appropriate zip file. The zip files use naming convention
    `liferay-portal-maven-[version]-[date].zip`. 

    The Liferay Maven CE artifacts package downloads to your machine.

You can extract the Liferay EE/CE artifacts package zip file anywhere you like.
The zip file not only includes the Liferay artifacts, but also includes a
convenient script to install and deploy the artifacts to your repositories. 

If you're using a Liferay CE and you want the latest pre-release artifacts from
the Liferay CE source repository, you can get them--but you'll have to build
them yourself. Don't worry, it's easy. We'll show you how to build the artifacts
from Liferay's source code next. 

#### Building CE Maven Artifacts from Source

Downloading the Liferay Maven artifacts from Liferay's customer portal (EE) or
from SourceForge (CE) is useful if you're interested in using the artifacts for
a particular release. However, if you'd like to use the very latest Liferay CE
Maven artifacts, you can build them from source. To build the latest Liferay CE
Maven artifacts from source, follow these steps:

1.  Navigate to your local Liferay Portal CE source project. If you don't
    already have a local Liferay Portal CE source project on your machine, you
    can fork the Liferay Portal CE Github repository, found at
    [http://github.com/liferay/liferay-portal](http://github.com/liferay/liferay-portal),
    and clone it your machine.

2.  Create a `app.server.[user name].properties` file in your local Liferay
    Portal CE source project root directory. Specify the following properties in
    it:

        app.server.parent.dir=[app server parent directory]

        app.server.[app server name].dir=[app server directory]

    Of course, add the path of your application server's parent directory and
    the path of your application server itself after the appropriate equals
    signs. Also, replace `[app server name]` with the name of your application
    server. Note that your `app.server.[app server name].dir` directory doesn't
    need to exist yet; you can create it by invoking an Ant target in the next
    step. For example, if you're running Apache Tomcat and your
    Liferay home directory is `/home/jbloggs/liferay/bundles/ce-6.1.x`, use
    the following properties:

        app.server.parent.dir=/home/jbloggs/liferay/bundles/ce-6.1.x

        app.server.tomcat.dir=
            /home/jbloggs/liferay/bundles/ce-6.1.x/[tomcat version]

3.  If an application server doesn't already exist at the directory specified by
    your `app.server.[app server name].dir` property, run `ant -f build-dist.xml
    unzip-[app server name]` to unzip a copy of your preferred application
    server to the specified directory.
   
       For example, to unzip Apache Tomcat to the directory specified by your
       `app.server.tomcat.dir` property, run:
   
           ant -f build-dist.xml unzip-tomcat

4.  Create a `releases.[user name].properties` in your local Liferay Portal CE
    source project root directory and specify the following properties:

        gpg.keyname=[GPG key name]

        gpg.passphrase=[GPG passphrase]

        lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-snapshots

        lp.maven.repository.id=liferay-snapshots

    Of course, replace the values specified above with your own GPG and Maven
    repository credentials.

    If you don't have GPG installed and don't have a public/private GPG key, you
    should visit [http://www.gnupg.org](http://www.gnupg.org) and install GPG
    before continuing. Once you've installed GPG, generate a GPG key by running
    `gpg --gen-key` and following the instructions. Once you've generated a GPG
    key, you can find your GPG keyname by running `gpg --list-keys`.
    
    ---

    ![note](../../images/tip-pen-paper.png) **Note**: The `releases.[user
    name].properties` is not required if you only plan to install the Liferay
    artifacts locally and not deploy them. 

    ---

5.  Open a command prompt, navigate to your Liferay home directory, and build
    the Liferay artifacts by running

        ant clean start jar

6.  Build the Liferay Portal WAR file by running 

        ant -f build-dist.xml all zip-portal-war

7.  Deploy the Liferay artifacts to your Maven repository by running 

        ant -f build-maven.xml deploy-artifacts

    If you want the Liferay artifacts to be installed locally but don't have a
    remote Maven repository or don't want the artifacts to be remotely deployed,
    you can run the install target instead of the deploy
    target: `ant -f build-maven.xml install-artifacts`. Once the Ant target
    finishes, you should have a time-stamped directory containing the artifacts
    in your Local Liferay Portal CE source project's root directory (e.g.,
    `liferay-portal/20121105174417071`).

---

![note](../../images/tip-pen-paper.png) **Warning:** During the process of
 packaging up the `javadoc.jar` files for your Liferay artifacts, your machine
 may experience sluggish performance or an insuffient amount of Java heap space.
 There are two solutions to this problem:
 
- *Increase the memory available for the Javadoc packaging process:*
  Navigate to `[Liferay home]/build.xml` and search for the `javadoc` target.
  Find the `maxmemory` property and increase it as desired.
- *Skip the Javadoc packaging process:* Navigate to
  `[Liferay home]/build-maven.xml` and find the `prepare-maven` target. Within
  this target, comment out the call to the `jar-javadoc` target, like below:

		<!-- <antcall target="jar-javadoc" /> -->
		
---

Next, we'll show you how to install the required Liferay release artifacts to
your repositories. These steps are applicable for installing artifacts that you
downloaded and extracted from a zip file, and for installing artifacts that you
built from source. 

##### Installing Artifacts to a Repository

Let's install the Liferay release artifacts to your local Maven repository for
sharing with your team.

1.  If you downloaded a Liferay artifacts zip file, navigate to the
    `liferay-portal-maven-[version]`directory. This is the root directory
    extracted from the Liferay artifacts zip file. If you built the artifacts
    from source, navigate to the time-stamped directory containing the artifacts
    in your Local Liferay Portal CE source project's root directory, (e.g.,
    `liferay-portal/20121105174417071`). 

2.  To install the artifacts to your local repository, execute

        ant install

    Your console shows output from the artifacts being installed from the
    Liferay Maven package into your local repository, typically located in your
    `${USER_HOME}/.m2/repository` directory.

Your local repository now contains the Liferay artifacts required to build
Liferay plugins. Wasn't that easy?

If you want to share your Liferay artifacts with teammates, you'll have to
deploy them to a release repository server. 

##### Deploying Artifacts to a Repository 

You may find it worthwhile to share your Liferay artifacts with teammates.

Here's how you do it:

1.  Make sure you've created a `liferay-releases` repository server to hold the
    Liferay Maven artifacts. If you haven't, see the *Managing Maven
    Repositories* section for instructions. 

2.  Ensure the repository to hold your Liferay artifacts is specified as a
    server in Maven's `settings.xml` file. If it isn't already, see the
    *Configuring Local Maven Settings* section for instructions on adding an
    entry for the server. 

    Here's an example setting for a repository server named *liferay-releases*: 

        <servers>
            ...
            <server>
                <id>liferay-releases</id>
                <username>admin</username>
                <password>admin123</password>
            </server>
            ...
        </servers>

3.  Using your command prompt, navigate to the `liferay-portal-maven-[version]`
    directory, or to your time-stamped Liferay Portal artifacts directory. This
    is the root directory extracted from the Liferay artifacts zip file. Or
    navigate to your time-stamped Liferay Portal artifacts directory, if you
    built the artifacts from source. 

4.  Create a `build.[user name].properties` file (e.g.,
    `build.jbloggs.properties`) in your `liferay-portal-maven-[version]`
    directory. In the new properties file, specify values for the properties
    `lp.maven.repository.id` and `lp.maven.repository.url`. These refer to your
    repository's ID and URL, respectively.

    Here are some example property values:

        lp.maven.repository.id=liferay-releases
        lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-releases

    Note, if you created a repository in Nexus OSS, as demonstrated in the
    section *Managing Maven Repositories*, you can specify that repository's ID
    and URL.

5.  To deploy to your release repository server, execute

        ant deploy

    Your console shows output from the artifacts being deployed into your
    repository server.

    To verify your artifacts are deployed, navigate to the *Repositories* page
    of your Nexus OSS server and select your repository.

    ![Figure 2.18: You can easily navigate to your Liferay release repository in Nexus.](../../images/maven-select-repository2.png)

    Notice a window appears below displaying the Liferay artifacts now deployed
    to your repository.

![Figure 2.19: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates. 

If you're working with Liferay CE, there's an alternative method of obtaining
the necessary Liferay Maven artifacts: you can let Maven download them
automatically. Let's see how. 

#### Installing CE Artifacts from the Central Repository 

Liferay offers an option for automatic download and installation of Liferay CE
Maven artifacts. They're publicly available on the *Central Repository*, located
at
[http://search.maven.org/#search|ga|1|liferay%20maven](http://search.maven.org/#search|ga|1|liferay%20maven),
and are updated with each Liferay release (e.g., 6.1.0, 6.1.10, 6.1.20, etc.).
The first time you use Maven to compile a Liferay plugin project, Maven
automatically downloads the required artifacts from the Central Repository into
your local repository if they're not found in your local repository or any of
your configured repository servers. You'll see it happen when you package your
Liferay CE plugins. 

Next, we'll consider the benefits of using a Maven parent project with your
plugin projects. 

### Using a Parent Plugin Project 

Maven supports project inheritance. You can create a *parent* project that
contains properties child projects have in common, and *child* projects
inherit those properties from the parent project. This saves time, since you
don't need to specify those properties in each project. If you develop more
than one project, it makes sense to leverage project inheritance so that all
projects can share properties they have in common. 

Our example demonstrates project inheritance; we'll build a project with a
parent/child relationship. Even if you're not going to leverage Maven's project
inheritance capabilities when you build your Liferay plugins with Maven, the
process is the same for creating any Liferay plugin with Maven's Liferay
artifacts. For more information on project inheritance, see Maven's
documentation at
[http://maven.apache.org/pom.html#Inheritance](http://maven.apache.org/pom.html#Inheritance). 

Let's create our parent project, and then specify the general settings you'll
need to build your plugins for Liferay. The parent project is similar to the
project root of the Liferay Plugins SDK. Its `pom.xml` file can specify
information to be used by any plugin projects that refer to it. You can always
specify information in each plugin's POM, but it's more convenient to use the
parent project's POM for sharing common information. 

Let's create a parent project named `sample-parent-project`: 

1.  Create a new directory for your parent project. For this example, we'll name
    the directory `sample-parent-project`. You can place the directory anywhere
    on your file system. 

2.  Inside the `sample-parent-project` directory, create a `pom.xml` file and
    insert the following lines: 

        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
            <modelVersion>4.0.0</modelVersion>

            <groupId>com.liferay.sample</groupId>
            <artifactId>sample-parent-project</artifactId>
            <version>1.0-SNAPSHOT</version>
            <packaging>pom</packaging>

            <name>sample-parent-project</name>
            <url>http://www.liferay.com</url>

            <properties>
                <liferay.auto.deploy.dir>${liferay.auto.deploy.dir}</liferay.auto.deploy.dir>
                <liferay.version>${liferay.version}</liferay.version>
            </properties>

            <dependencies>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-client</artifactId>
                    <version>6.1.20</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-impl</artifactId>
                    <version>6.1.20</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-service</artifactId>
                    <version>6.1.20</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-web</artifactId>
                    <version>6.1.20</version>
                    <type>war</type>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-bridges</artifactId>
                    <version>6.1.20</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-java</artifactId>
                    <version>6.1.20</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-taglib</artifactId>
                    <version>6.1.20</version>
                </dependency>
            </dependencies>

        </project>

    If you use this example POM, replace the `<version>` values with the version
    of Liferay applicable to the plugins you're developing. 

    <!--Change to 6.2 after release -->
 
3.  Modify the values of the *properties* to match your Liferay environment.

    Fill in the `<liferay.auto.deploy.dir>...</liferay.auto.deploy.dir>` tags
    with the path of your Liferay bundle's `deploy` directory. This is the
    auto-deploy directory you will eventually copy your plugin to deploy on
    Liferay.

    Fill in the `<liferay.version>...</liferay.version>` tags with the version
    of Liferay you are using.

    Your POM's *properties* should look similar to the following:
    
        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.20-ee-ga2\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.20</liferay.version>
        </properties>

By specifying your Liferay instance's deploy directory in the POM, you're
telling Maven exactly where to deploy your plugin artifacts. 

Your parent project now specifies common dependencies on required Liferay Maven
artifacts. All your parent project's modules (i.e., projects that refer to this
parent) can leverage these dependencies. 

---

![note](../../images/tip-pen-paper.png) **Note:** You could just as easily
include such dependencies in the POM of each of your plugin projects, but
specifying them in a parent project makes them accessible to child projects
through inheritance. 

---
 
Now you're able to specify dependencies on required Liferay artifacts; let's
create a Liferay plugin project using the archetypes provided by Liferay. 

### Creating Liferay Plugins with Maven 

Liferay offers many archetypes to help create Maven projects for multiple plugin
types, including portlet, theme, hook, and layout template plugins. We provide
archetypes for each of these plugin types for various versions of Liferay, so
you almost certainly have the archetype you need. 

*Archetype* is Maven's project templating toolkit. Let's use it to create a
Liferay portlet project. With Archetype, you can use the same steps we detail
below to generate Liferay plugin projects of any type. 

---

![note](../../images/tip-pen-paper.png) **Note:** Make sure Maven is installed
 and that it's executable is in your path environment variable. 

---

Here's how you use Maven archetypes to generate a Liferay plugin project:

1.  Open the command prompt and navigate to the parent directory in which you
    want to create the plugin project. Archetype will create a sub-directory for
    the plugin project you create.

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** If you haven't already
    created a parent project, you may want to consider creating one to share
    common project information. See section *Using a Parent Plugin Project* for
    details.

    ---

2.  Execute the command

        mvn archetype:generate

    Archetype starts and lists the archetypes available to you. You'll be
    prompted to *choose* an archetype or *filter* archetypes by group / artifact
    ID. The output looks similar to the following text: 
    
        ...
        39: remote -> com.liferay.maven.archetypes:liferay-hook-archetype
        (Provides an archetype to create Liferay hooks.)
        40: remote -> com.liferay.maven.archetypes:liferay-layouttpl-archetype
        (Provides an archetype to create Liferay layout templates.)
        41: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        42: remote -> com.liferay.maven.archetypes:liferay-portlet-icefaces-archetype
        (Provides an archetype to create Liferay ICEfaces portlets.)
        43: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
        (Provides an archetype to create Liferay JSF portlets.)
        ...
        Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains):

3.  To find the right Liferay archetype for your project, you can either scroll
    up to find it or apply filters to narrow the set of results. Filtering on
    *liferay* as your group ID, and a plugin type (*portlet*, *hook*, *theme*,
    etc.) can help you focus on more applicable Liferay archetypes. 

    Entering `liferay:portlet` as a filter gives a listing of Liferay portlet
    archetypes: 

        Choose a number or apply filter (format: [groupId:]artifactId, case
        sensitive contains): : liferay:portlet Choose archetype:
        1: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        2: remote ->
        com.liferay.maven.archetypes:liferay-portlet-icefaces-archetype (Pr
        ovides an archetype to create Liferay ICEfaces portlets.)
        3: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
        (Provide s an archetype to create Liferay JSF portlets.)
        4: remote ->
        com.liferay.maven.archetypes:liferay-portlet-liferay-faces-alloy-ar
        chetype (Provides an archetype to create Liferay Faces Alloy portlets.)
        5: remote ->
        com.liferay.maven.archetypes:liferay-portlet-primefaces-archetype (
        Provides an archetype to create Liferay PrimeFaces portlets.)
        6: remote ->
        com.liferay.maven.archetypes:liferay-portlet-richfaces-archetype (P
        rovides an archetype to create Liferay RichFaces portlets.) Choose a
        number or apply filter (format: [groupId:]artifactId, case sensitive co
        ntains): :

4.  Choose an archetype by entering its number.

5.  You're next prompted to choose the archetype version. Enter the number
    corresponding to the Liferay version for the archetype. However, you're not
    required to select the archetype version that corresponds with your Liferay
    instance; older archetype versions are compatible with updated Liferay
    bundles. 

    The snapshot below illustrates choosing the archetype version: 

    ![Figure 2.20: You're prompted by Maven Archetype to enter the archetype version.](../../images/maven-archetype-version.png)

6.  Enter values for the *groupId*, *artifactId*, *version*, and *package*
    coordinates (properties) of your project. Here are some examples: 

        groupId: com.liferay.sample
        artifactId: sample-portlet
        version: 1.0-SNAPSHOT
        package: com.liferay.sample

    This process is illustrated in the snapshot below:

    ![Figure 2.21: When creating your portlet plugin, you must enter your *groupId*, *artifactId*, *version*, and *package* properties.](../../images/maven-portlet-plugin-settings.png)

    For more information on defining Maven coordinates, see
    [http://maven.apache.org/pom.html#Maven_Coordinates](http://maven.apache.org/pom.html#Maven_Coordinates).

7.  Enter the letter *Y* to confirm your coordinates.

    Maven's Archetype tool creates a Liferay plugin project directory with a new
    `pom.xml` file and source code. 

---

![note](../../images/tip-pen-paper.png) **Note:** The archetype file is
downloaded and installed automatically to your local repository (e.g.,
`.m2/repository/com/liferay/maven/archetypes/<archetype>`). If you
configured the mirror pointing to your public repository on Nexus, the
plugin is installed there. 

---

![Figure 2.22: Your archetype and its dependencies are now available in your local repository.](../../images/maven-public-plugin.png)

Following these steps, you can use Archetype to generate all your Liferay
plugins! 

Plugin projects generated from a Liferay archetype are equipped with a POM
that's ready to work with a parent project. It inherits the values for
`liferay.version` and `liferay.auto.deploy.dir` properties from the parent. 

When your plugin is created, you can package and deploy your project to a
specified Liferay instance. You can even install and deploy the individual
plugin to a remote repository. 

Next we'll go through some brief examples to demonstrate deploying your plugins
to Liferay Portal using Maven. 

### Deploying Liferay Plugins with Maven 

With Maven it's easy to deploy plugins to a Liferay Portal instance. Just follow
these steps: 

1.  Make sure your parent POM's Liferay-specific properties specify the correct
    Liferay version and your Liferay Portal's deploy directory path. 

    Here's an example of these properties using Liferay 6.1.20 and a deploy
    directory path of `E:\liferay-portal-6.1.20-ee-ga2\deploy`: 

        <properties>
            <liferay.auto.deploy.dir>E:\liferay-portal-6.1.20-ee-ga2\deploy</liferay.auto.deploy.dir>
            <liferay.version>6.1.20</liferay.version>
        </properties>

    If you haven't already created a parent plugin project, see *Using a Parent
    Plugin Project* for details. 

2.  In your command prompt, navigate to your Liferay plugin project's directory. 

3.  Package your plugin by entering 

        mvn package

    Your command output should be similar to the following output: 

        [INFO] Building war:
        E:\ce-plugins-maven\sample-parent-project\sample-portlet\target\sample-portlet-1.0-SNAPSHOT.war
        ...
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

4.  Deploy the plugin into your Liferay bundle by entering

        mvn liferay:deploy

    The command output should look similar to the following output: 

        [INFO] Deploying sample-portlet-1.0-SNAPSHOT.war to [liferay version]\deploy
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------

    Your Liferay console output shows your plugin deploying. It looks
    like this: 

        INFO: Deploying web application directory [liferay version]\[tomcat version]\webapps\sample-portlet
        INFO  [pool-2-thread-2][HotDeployImpl:178] Deploying sample-portlet from queue
        INFO  [pool-2-thread-2][PluginPackageUtil:1033] Reading plugin package for sample-portlet

    ---
 
    ![note](../../images/tip-pen-paper.png) **Note:** If you get the following
    error after executing `mvn liferay:deploy`, make sure you're executing the
    command from your plugin's directory (e.g., `sample-portlet`). 
 
     `[ERROR] No plugin found for prefix 'liferay' in the current project and in
     the plugin groups [org.apache.maven.plugins, org.codehaus.mojo] available
     from the repositories [local (C:\Users\cdhoag\.m2\repository), central
     (http://repo.maven .apache.org/maven2)] -> [Help 1]`
    
    ---

5.  If you're deploying the plugin to a release or snapshot repository,
    specify the repository by adding a distribution management section to your
    plugin's `pom.xml`. 

    Here's an example distribution management section for a snapshot repository: 

        <distributionManagement>
            <repository>
                <id>liferay-releases</id>
                <url>http://localhost:8081/nexus/content/repositories/liferay-releases</url>
            </repository>
            <snapshotRepository>
                <id>liferay-snapshots</id>
                <name>Liferay Snapshots Repository</name>
                <url>http://localhost:8081/nexus/content/repositories/liferay-snapshots</url>
            </snapshotRepository>
        </distributionManagement>

    The proper contents for your `<distributionManagement>` element can be found
    in the *Summary* tab for each of your repositories. 

    ![Figure 2.23: Select the *Summary* tab of your repository to see how to specify it for distribution management in your plugin's POM.](../../images/maven-repository-summary.png) 

    Since you created the plugin as a snapshot, you'll have to deploy it to a
    snapshot repository. You can deploy a plugin as a release, but the plugin's
    POM must specify a valid release version (e.g., `<version>1.0</version>`),
    not a snapshot version (e.g., `<version>1.0-SNAPSHOT</version>`). 

6.  Deploy your plugin into your specified Nexus repository:

		mvn deploy

---

![note](../../images/tip-pen-paper.png) **Note:** There are three build phases
you'll use when developing plugins with Maven: 

- In Maven's *compile* phase, explicit dependencies are downloaded to your
  local repository (i.e., `.m2/repository/com/liferay/portal`).
- In Maven's *package* phase, the plugin's inferred dependencies are downloaded
  to your local repository (i.e., `.m2/repository`). 
- In Maven's *install* phase, your plugin is installed to your local
  repository. 

---

Now that you've deployed a plugin using Maven, let's consider the types of
Liferay plugins you can develop with Liferay Maven archetypes. 

### Liferay Plugin Types to Develop with Maven 

You can develop all Liferay plugin types with Maven: portlets, themes,
layout templates, hooks, and Ext. Next, you'll learn how to create each plugin
type using Maven, and we'll point out where each plugin's directory structure is
different than if you created it using the Plugins SDK. We'll often refer to the
previous sections for creating and deploying these plugin types in Maven using
Liferay artifacts.  We'll also reference sections of some other chapters in this
guide, since they're still relevant to Maven developers: they explain how you
develop each type of plugin regardless of development environment. 

Let's start with portlet plugins. 


#### Creating a Portlet Plugin 

To create a Liferay portlet plugin project, follow the *Creating Liferay Plugins
with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your portlet project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:portlet`, to 
find the Liferay portlet archetypes more easily. 

---

##### Anatomy 

A portlet project created from the
`com.liferay.maven.archetypes:liferay-portlet-archetype` has the following
directory structure: 

- portlet-plugin/
    - src/
        - main/
            - java/
            - resources/
            - webapp/
                - css/
                    - main.css
                - js/
                    - main.js
                - WEB-INF/
                    - liferay-display.xml
                    - liferay-plugin-package.properties
                    - liferay-portlet.xml
                    - portlet.xml
                    - web.xml
                - icon.png
                - view.jsp
    - pom.xml

The `portlet-plugin/src/main/java/` directory holds the portlet's Java source
code (e.g., `com.liferay.sample.SamplePortlet.java`), and
`portlet-plugin/src/main/webapp` holds its web source code. If you've created
any portlet plugins using the Plugins SDK, you might have noted it uses a
different directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the full directory structure of a portlet developed by Ant, visit our
[Anatomy of a
Portlet](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/anatomy-of-a-portl-4)
section in this guide. 

##### Deployment 

To deploy your portlet plugin, follow the instructions detailed above in
*Deploying Liferay Plugins with Maven*. 

Congratulations! You successfully created a Liferay portlet plugin using Maven. 

##### More Information 

For detailed information on creating portlet plugins, see Chapter 3, 
[Portlet Development](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/portlet-developme-3). 

Next, let's run through a brief example for developing a theme plugin using
Maven.

#### Developing Liferay Theme Plugins with Maven 

So you're sitting in your armchair next to the fire, just as we described in our
chapter introduction; shadows dance on the tapestry-covered wall, and Lenore II
(your cat) is purring atop the mantle. Yes, you're passing this cold winter's
night in grand style (in front of your computer, of course). Now imagine
yourself sitting on a cold hard wooden chair inside an off-white cubicle with
empty walls (you're still in front of your computer, of course). These two
descriptions paint two very different pictures, but both describe what you're
doing (sitting and computing). Changing the "scenery" of your portal sets the
mood for your users. We'll show you how to develop your own theme plugin (i.e.,
your "scenery") using Maven so your portal has a lasting impression on anyone
who visits. 

##### Creating a Theme Plugin 

Theme plugin creation is similar to portlet plugin creation. We'll start by
assuming you already created the `sample-parent-project` and its `pom.xml`. 

To create your Liferay theme plugin project follow the *Creating Liferay Plugins
with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your theme project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:portlet`, to more easily
find the Liferay portlet archetypes. 

---

##### Anatomy 

A theme project created from the
`com.liferay.maven.archetypes:liferay-theme-archetype` has the following
directory structure: 

- sample-theme/
    - pom.xml
    - src/
        - main/
            - resources/
            - webapp/
                - WEB-INF/
                    - liferay-plugin-package.properties
                    - web.xml
                - css/
                - images/
                - js/
                - templates/

Several of the directories listed in the structure above are not created
automatically; you'll create them as needed, depending on the customizations
you're making. Here's a list of these directories, with a brief description of
each: 

- `src/main/css/`: Holds your CSS customizations.
- `src/main/images/`: Holds custom images for your theme.
- `src/main/js/`: Holds your JavaScript customizations.
- `src/main/templates/`: Holds your template customizations. 

The `src/main/webapp/` folder contains your theme's customizations. If you've
ever created a theme plugin using the Plugins SDK, this folder is used the same
way as the `docroot/_diffs/` folder. For example, `custom.css` should go in
`src/main/webapp/css/custom.css`. 

Here's a table describing the directory structure differences between themes
created using Maven and themes created using the Plugins SDK: 

Location       | Maven project     | Plugins SDK project |
-------------- | ----------------- | ------------------- |
customizations | `src/main/webapp` | `docroot/_diffs`    |

To view the directory structure of a theme developed by Ant, visit the [Anatomy
of a
Theme](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/anatomy-of-a-the-4)
section in this guide. 

##### Theme POM 

The theme plugin project POM has two additional properties: 

- `liferay.theme.parent`: Sets the parent theme. You can define almost any WAR
  artifact as the parent using the syntax `groupId:artifactId:version`, or use
  the core themes by specifying `_unstyled`, `_styled`, `classic`, or
  `control_panel`. 
- `liferay.theme.type`: Sets the template theme language. 

    The default settings for the two theme properties look like this:
        
        <properties>
            <liferay.theme.parent>_styled</liferay.theme.parent>
            <liferay.theme.type>vm</liferay.theme.type>
        </properties>

##### Deployment

To deploy your theme plugin, follow the instructions in the *Deploying Liferay
Plugins with Maven* section. 

---

![note](../../images/tip-pen-paper.png) **Note:** When you execute the *package*
goal, a WAR file is created; it's just like the Maven WAR type project.
Simultaneously, the parent theme is downloaded and copied, and your theme's
customizations are overlaid last. A thumbnail image of the theme is created and
placed in the *target* directory. Its path is
`target/<theme>/images/screenshot.png` in your theme project. 

---

##### More Information 

For more information on Liferay themes and its settings, see Chapter 9,
[Creating Liferay Themes and Layout
Templates](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/creating-liferay-them-7). 

You successfully developed a Liferay theme using Maven. Find out about
developing hook plugins next. 

#### Developing Liferay Hook Plugins with Maven 

Hooks are the optimal plugin type for customizing Liferay's core features.
Creating a hook is almost identical to portlet plugin creation in Maven. Let's
take a look. 

##### Creating a Hook Plugin 

To create a Liferay hook plugin project, follow the steps outlined in the
*Creating Liferay Plugins with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your hook you can filter on group ID `liferay`, or even the
group ID/artifact ID combination `liferay:portlet`, to more easily find the
Liferay portlet archetypes. 

---

##### Anatomy 

A hook project created from the
`com.liferay.maven.archetypes:liferay-hook-archetype` has the following
directory structure: 

- hook-plugin/
    - src/
        - main/
            - java/ \* Optionally add to hold Java source
            - webapp/
                - WEB-INF/
                    - liferay-hook.xml
                    - liferay-plugin-package.properties
                    - web.xml
    - pom.xml

The `hook-plugin/src/main/java/` directory holds the hook's Java source code
(e.g., `com.liferay.sample.SampleHook.java`), and `hook-plugin/src/main/webapp`
holds its web source code. If you're familiar with creating hook plugins using
the Plugins SDK, you probably noticed that Maven uses a different plugin
directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the directory structure of a hook developed by Ant, visit the *Anatomy
of a Hook* section of the [Creating a
Hook](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/creating-a-ho-4)
section in this guide. 

##### Deployment 

To deploy your hook plugin, follow the instructions from the *Deploying Liferay
Plugins with Maven* section. 

##### More Information 

For detailed information on creating hooks, see the
[Hooks](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/hoo-4)
Chapter of this guide. 

You're nearly a Maven expert now; you're able to create portlets, themes, and
hooks. Let's round things out by learning to develop layout templates. 

#### Developing Liferay Layout Template Plugins with Maven 

You can create layout templates to customize the display of portlets on your
page and to embed commonly used portlets. In our introduction to themes, we
described a nice scene where you're relaxing in a luxurious chair, computer in
your lap, Lenore II (your cat) purring on the mantle above a dancing fire.
Sounds nice, doesn't it? It would be, but the chair's too small, so your knees
are up in the air when your feet are flat on the ground, and your laptop is
balanced precariously on top of them. The fire is also surprisingly large for
that fireplace. In fact, its flames are already licking at the bottom of the
mantle--which is made of wood! Remember Lenore II, softly purring on the mantle?
She's going to cook just like the original Lenore if we don't do something!
But it's so hard to get out of this tiny chair. Someone save Lenore II! 

"Tell this soul with sorrow laden if, within the distant Aidenn,
It shall clasp a sainted kitten whom the angels named Lenore II--"

In memory of the late, now crispy Lenore II, let's create a layout template
plugin with Maven. 

##### Creating a Layout Template Plugin 

To create a Liferay layout template plugin project follow the *Creating Liferay
Plugins with Maven* section.

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your layout template project, you can filter on group ID
`liferay`, or even group ID / artifact ID combination `liferay:layout`, to find
the Liferay layout template archetypes.

---

##### Anatomy 

A layout template project created from the
`com.liferay.maven.archetypes:liferay-layouttpl-archetype` has the following
directory structure:

- layouttpl-plugin/
    - src/
        - main/
            - resources/
            - webapp/
                - WEB-INF/
                    - liferay-layout-templates.xml
                    - liferay-plugin-package.properties
                    - web.xml
                - sample-layout.png
                - sample-layout.tpl
                - sample-layout.wap.tpl
    - pom.xml

There's a directory structure difference between plugin projects created using
Liferay Maven archetypes and those created using the Liferay Plugins SDK. The
following table illustrates this difference: 

Location   | Maven project     | Plugins SDK project |
---------- | ----------------- | ------------------- |
Web source | `src/main/webapp` | `docroot` |

To view the directory structure of a layout template developed by Ant, visit the
[Anatomy of a Layout Template
Project](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen05-anatomy-of-a-layout-template-0)
section in this guide. 

##### Deployment 

To deploy your layout template plugin, follow the instructions detailed above in
the *Deploying Liferay Plugins with Maven* section. 

##### More Information 

For detailed information on creating layout templates, see Chapter 9, [Creating
Liferay Themes and Layout
Templates](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen05-creating-liferay-layout-templates-0). 

You've passed your trial by fire (the cat thanks you), developing yet another
plugin type with Maven. Way to go! In the next section we'll cover other
Liferay-provided Maven archetypes. 

#### Developing More Liferay Plugins with Maven 

Did you think we covered all the available archetypes for developing Liferay
plugins? The Liferay team has been busy expanding our archetype list, and we're
proud to show you some additional plugins that you can create using Maven
archetypes. 

Check out these exciting archetypes that are now available: 

- Liferay ServiceBuilder portlets 
- Liferay webs 
- Liferay Ext 
- JSF Portlet Archetype 
- ICEFaces Portlet Archetype 
- PrimeFaces Portlet Archetype 
- Liferay Faces Alloy Portlet Archetype 
- Liferay Rich Faces Portlet Archetype 

In addition, there are two new Maven *goals* now available: 

- DBBuilder - The `build-db` goal lets you execute the DBBuilder to generate SQL
  files. 
- SassToCSSBuilder - The `build-css` goal precompiles SASS in your css; this
  goal has been added to theme archetype. 

You now have plenty of archetypes at your disposal! 

"But the chair whose violet lining with the lamp-light gloating o'er,
Lenore II shall press, ah, nevermore!"

Lenore II didn't make it through the Maven section, but you did. You can
develop all your Liferay plugins using Maven; there's a standard process for
generating the archetypes and selecting your plugin options for each plugin
type. You can then customize the archetype to your liking. Using Maven to
develop plugins offers an easy and effective way to customize your Liferay
Portal. 

---

![note](../../images/tip-pen-paper.png) **Note:** Are you wondering if we're
going to make more terrible jokes that steal from classic poetry? Quoth the
Maven, "Probably." 

---

<!-- I'm not sure this Maven conclusion works stuck in here and I'm not sure
what to do with it.  -->

No matter which tool you use to develop your custom plugins, you'll need to
understand Liferay's deprecation policy. That way you'll know when methods from
our API's are deprecated, and you can make any necessary changes. We'll
describe the deprecation policy next. 

## Liferay's Deprecation Policy

Methods in Liferay's APIs are deprecated when they're no longer called by
Liferay internally. Method deprecation occurs during major releases of Liferay.
You'll know when there's a major release if you understand Liferay's
release version notation. Liferay version numbers consist of a three digit
number. Knowing what each digit represents is key, so let's consider a case
where you were using Liferay 6.0.2, and Liferay 6.0.3 has just been released:

- Digit 1 (**6**.0.3): The first digit represents the major release version,
  which is a *feature release*. Along with changes to Liferay's architecture,
internal schema, and the introduction of new functionality, API methods get
deprecated during feature releases. In our example, this number hasn't changed,
so any API methods we use in our custom development are intact. 

- Digit 2 (6.**0**.3): The second digit indicates the minor release. Minor
  releases involve the introduction of new features and bug fixes. While
customization might be affected when installing, API methods don't get
deprecated. For our example, this didn't change.  feature release. It also did
not change in our example. 

- Digit 3 (6.0.**3**): The third digit represents the maintenance release, or
  fix pack release. In our case, the third digit changed from *2* to *3*, so we
discovered a maintenance release. In terms of API method deprecation, you're in
the clear; in acoordince with our policy, methods you used in your custom
development haven't been deprecated. 

<!-- I think this whole section is incorrect. It should be synced with this
(focusing on the deprecation policy, of course): https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/editions-of-lifer-4 -->

<!--I updated it based on the user guide. Please review -Russ -->

## Summary

<!-- Summary needs to include Liferay IDE and Plugins SDK -->

You also learned all about developing Liferay plugins with the Maven build
framework. You configured Maven locally, downloaded and installed the required
Liferay Maven artifacts, and learned to create Liferay plugins with Maven.
You're ready to create all kinds of Liferay plugins based on Liferay's plugin
archetypes. Just don't let Lenore III sleep near the fire this time. 

<!-- Missing transition into portlets chapter -->

