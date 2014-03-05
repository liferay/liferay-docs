# Liferay IDE and Liferay Developer Studio [](id=liferay-i-4)

If you've been writing software for a while, you probably have a set of tools
that you regularly use. This set of tools has been carefully collected over the
years, and every tool is there for one reason: to contribute to your
productivity. 

Many of us at Liferay are like that, which is why our SDKs are designed to work
with any text editor or IDE. You can be productive on the Liferay platform using
any tool you want. To jumpstart your efforts, however, we also provide an IDE
environment fully integrated with Eclipse. We've designed this carefully, so
that it integrates well with our existing SDKs, Maven, and other tools that are
out there. Liferay IDE helps you manage many features of Liferay's platform,
making them easier to use. There are editors for Service Builder files, workflow
definitions, layout templates, and more. You'll find wizards for creating every
kind of Liferay project there is, snippets for tag libraries, and auto-deploy of
changes to plugins. 

Whether you've been using Liferay for a long time or are just getting started,
it's worth your time to check out Liferay IDE and see whether it's another of
those tools you can add to your toolbox. We think you'll be glad you did. 

In this chapter we'll cover the following topics:

- Installation 
- Setting Up Liferay IDE 
- Testing/Launching Liferay Tomcat Server 
- Creating New Liferay Projects 
- Deploying Liferay Projects 
- Importing Existing Projects into Liferay IDE 
- Using Liferay IDE's Remote Server Adapter
- Designing workflows with Kaleo Designer for Java 

To install and set up Liferay IDE, follow the instructions below. If you're
using Liferay Developer Studio (which comes with Liferay Portal Enterprise
Edition), skip to the section titled *Testing/Launching Liferay Tomcat
Server*--Liferay IDE is already configured in Developer Studio. 

## Installation [](id=installati-6)

Liferay IDE is a plugin for Eclipse. You can install it in two ways: bundled
with Eclipse (this is the easiest way) or into an existing Eclipse installation. 

If you want to install the bundled version, a prerequisite is a version of Java
supported by Eclipse. As of this writing, the bundled version requires JDK 6 or
above.

Once you've satisfied the prerequisite, it's an easy matter to install Liferay
IDE. Go to Liferay's [downloads page](http://www.liferay.com/downloads) and
choose the *Liferay IDE* option. There's a separate download for each of the
32-bit and 64-bit variants of Windows, Linux, and Mac OS X. Choose the one that
matches your system and click the *Download* button. You'll receive a `.zip`
file. To install Liferay IDE on your system, all you need to do is unzip it to
the folder of your choice. 

If you already have Eclipse installed, you can add Liferay IDE to it. You can
do this in one of two ways: install Liferay IDE via a URL to the update site
or install from a `.zip` file. 

Make sure you have a supported Java JRE and Eclipse release: 

-   Java 6.0 JRE or greater  
-   Liferay IDE supports the following Eclipse releases: 

    Eclipse Kepler Java EE (4.3.x)

    Eclipse Juno Java EE (4.2.x)

    Eclipse Indigo Java EE (3.7.x)

To install Liferay IDE and specify an Eclipse update URL, follow these steps: 

1.  Install Eclipse Kepler, Juno, or Indigo from the
    [Eclipse](http://www.eclipse.org) website. 

2.  Run the Eclipse executable file (e.g., `eclipse.exe`).

3.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

4.  In the *Work with* field, enter the update site URL
    `http://sourceforge.net/projects/lportal/files/Liferay IDE/2.0.0
    GA1/updatesite/` and press *Enter*.

5.  Liferay IDE features should be detected. Select them and click *Next*. 

6.  After calculating dependencies, click *Next*, accept the license agreement,
    and click *Finish* to complete the installation. 

7.  Restart Eclipse to verify that Liferay IDE is properly installed.

8.  After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
    IDE icon badge as in the screenshot below, it's properly installed. 

![Figure 10.1: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

To install Liferay IDE from a `.zip` file, follow these steps:

1.  Install Eclipse Kepler, Juno, or Indigo from the
    [Eclipse](http://www.eclipse.org) website. 

2.  Download the IDE 2.0 `.zip` file from
    [http://sourceforge.net/projects/lportal/files/Liferay IDE/2.0.0
    GA1/](http://sourceforge.net/projects/lportal/files/Liferay%20IDE/2.0.0%20GA1/)
    for your operating system. 

3.  Run the Eclipse executable file (e.g., `eclipse.exe`).

4.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

5.  In the *Add Site* dialog, click the *Archive* button and browse to the
    location of the downloaded Liferay IDE `.zip` file.

6.  Liferay IDE features should be detected. Select them and click *Next*. 

7.  After calculating dependencies, click *Next*, accept the license agreement,
    and click *Finish* to complete the installation.

8.  Restart Eclipse to verify that Liferay IDE is properly installed.

9.  After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
    IDE icon badge as in the screenshot below, it's properly installed. 

<!-- This whole section needed some help, as we now provide a bundled version
that wasn't covered at all. Additionally, all the links were wrong, so I rewrote
it in a more maintainable way. --> 

Let's set up Liferay IDE now that you have it installed. 

## Setting Up Liferay IDE [](id=set--4)

Now that you have Liferay IDE installed, either from a downloaded zip file or
from the update site appropriate for your Eclipse version, you need to perform
some basic setup. This section describes the setup steps to perform so you can
develop your Liferay portal and test your customizations. 

Before setting up Liferay IDE, let's make sure you have all the appropriate
software packages installed. 

#### Requirements [](id=liferay-ide-requirements-liferay-portal-6-1-dev-guide-en)

Before setting up Liferay IDE, you need to have appropriate versions of Liferay
Portal, Liferay Plugins SDK and/or Maven, and Eclipse. Make sure you satisfy
these requirements before proceeding:

1.  Liferay Portal 6.0.5 or greater is downloaded and unzipped

2.  Liferay Plugins SDK 6.0.5 or greater is downloaded and unzipped, and/or any
    version of Maven is installed. If you're using the Plugins SDK, make sure
    the Plugins SDK version matches the Liferay Portal version.

3.  You've installed an appropriate Eclipse IDE version for Java EE Development,
    and the Liferay IDE extension--see the *Installation* section if you haven't
    already done this. 

---

 ![note](../../images/tip-pen-paper.png) **Note:** Earlier versions of Liferay
 (e.g., 5.2.x) are not supported by Liferay IDE. 

---

Let's set up your Liferay Plugins SDK. 

### Registering the Liferay Plugins SDK with Liferay IDE [](id=lp-6-1-dgen07-liferay-plugins-sdk-setup-0)

Before you begin creating new Liferay plugin projects, a supported Liferay
Plugins SDK and/or Maven installation and Liferay Portal must be installed and
configured in your Liferay IDE. If you're thinking, "Wait a second, buster! I
thought the Plugins SDK and Maven could be used without Liferay IDE!", then
you're right. In the [The Plugins
SDK](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/the-plugins-s-3)
chapter, we'll explain how to use the Plugins SDK on its own, with a text
editor. Also, we'll explain how to use Maven on its own in the [Developing
plugins using
Maven](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/developing-plugins-using-maven)
chapter. Here, we explain the easiest way to use the Plugins SDK: by running it
from Liferay IDE. 

1.  In Eclipse, click *Window* &rarr; *Preferences* &rarr; *Liferay* &rarr;
    *Installed Plugin SDKs*.

2.  Click *Add* to bring up the *Add SDK* Dialog. 

3.  Browse to your Plugins SDK installation. The default name is the directory
    name; you can change it if you want. 

4.  Select *OK* and verify that your SDK was added to the list of *Installed
    Liferay Plugin SDKs*. 

---

 ![note](../../images/tip-pen-paper.png) **Note:** You can have Multiple SDKs in
 your preferences. Set one as the default SDK by checking its box in the list of
 *Installed Liferay Plugin SDKs*. 

---

Let's set up your Liferay Portal Tomcat runtime and server. 

### Liferay Portal Tomcat Runtime and Server Setup [](id=lp-6-1-dgen07-liferay-portal-tomcat-runtime--server-setup-0)

1.  In Eclipse, go to *Window* &rarr; *Preferences* &rarr; *Server* &rarr;
    *Runtime Environments*. 

    ![Figure 10.2: Choosing a runtime environment](../../images/ide-prefs-runtime-env.png)

2.  Click *Add* to add a new Liferay runtime; find *Liferay v6.1 (Tomcat 7)*
    under the *Liferay, Inc.* category and click *Next*.

3.  Click *Browse* and select your `liferay-portal-6.1.x directory`. 

4.  If you've selected the Liferay portal directory and a bundle JRE is present,
    it is automatically selected as the server's launch JRE. If no JRE bundle
    is present, then you must select the JRE to use for launch by clicking
    *Installed JREs...*. 

    ![Figure 10.3: Choosing the JRE bundle](../../images/ide-server-jre.png)

5.  Click *Finish*; you should see your Liferay portal runtime listed in
    *Preferences* &rarr; *Server* &rarr; *Runtime Environments*.

6.  Click *OK* to save your runtime preferences. 

7.  If you haven't created a server, create one now from the *Servers*
    view in Liferay IDE; then you can test the server. 

    ![Figure 10.4: Adding a runtime to the Servers view](../../images/ide-add-new-server.png)

8.  Scroll to the *Liferay, Inc* folder and select *Liferay v6... Server*.
    Choose the *Liferay v6...* runtime environment that you just created. 

Now your server is set up.  Let's launch it and perform some tests! 

## Testing and Launching Liferay Tomcat Server [](id=testing-launching-liferay-tomcat-serv-4)

Once your Liferay Portal Server is set up, you can launch it from the Servers
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

<!--Did I get that wrong? The *Terminate* option is grayed out in my LDS's Run
menu though the server is running. -Russ -->

<!-- You should never terminate the server. That kills the process without
closing everything (transactions, database connections, etc.) correctly. -Rich--> 

<!-- We should explain how to properly shutdown a server -Jim -->

Once the server is launched, you can open Liferay portal home from the *Servers*
tab by right clicking your Liferay Tomcat server and selecting *Open Liferay
Portal Home*. 

Next, you'll learn to create new Liferay projects in Liferay IDE. 

## Creating New Liferay Projects [](id=creating-new-liferay-projec-4)

Creating Liferay projects is a straightforward process, similar to creating
other Eclipse project types. Plugins for Liferay Portal must be created inside
of a Liferay project. A Liferay project is essentially a root directory with a
standardized structure containing the project's (and each of its plugins')
necessary files. Since each plugin type requires a different folder and file
structure, let's create a project to illustrate the process.

If you've been following our Liferay IDE configuration instructions, your
Plugins SDK and Liferay portal server have already been configured in Liferay
IDE. Now let's create a new Liferay plugin project in Liferay IDE. We'll create
the Event Listing portlet project, which we'll use throughout this guide.

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  In the project creation wizard, you'll name and configure your project.

	We'll create a plugin project that we'll use throughout this guide.
	First, we'll create a bare bones plugin project; then, we'll manually add an
	additional plugin to the project and add additional configurations. 
	
	2.1. Provide both a *Project Name*, which is used to name the project's
	directory, and a *Display Name*, which is used to identify the plugin when
	adding it to a page in Liferay Portal. Our demonstration project will have
	the project name *event-listing-portlet* and the display name *Event
	Listing*.
	
	2.2. Leave the *Use default location* checkbox checked. By default, the
	default location is set to your current workspace. If you'd like to change
	where your plugin project is saved in your file system, uncheck the box and
	specify your alternate location. 
	
	2.3. Select the *Ant (liferay-plugins-sdk)* option for your build type. If
	you'd like to use *Maven* for your build type, navigate to the *Developing
	Plugins Using Maven* section for details.

    2.4. Your newly configured SDK and Liferay Runtime should already be
    selected. If you haven't yet pointed Liferay IDE to a Plugins SDK, click
    *Configure SDKs* to open the *Installed Plugin SDKs* management wizard. You
    can also access the *New Server Runtime Environment* wizard if you need to
    set up your runtime server; just click the *New Liferay Runtime* button next
    to the *Liferay Portal Runtime* dropdown menu.

    2.5. Under *Plugin Type*, indicate which plugin type your project will hold
    by selecting one from the list. You can choose from *Portlet*, *Service
    Builder Portlet*, *Hook*, *Layout Template*, *Theme*, or *Ext*. Liferay IDE
    provides handy wizards for creating new Liferay projects. Our demonstration
    project will hold service builder portlets for the Nose-ster organization,
    so make sure *Service Builder Portlet* is selected.

    ![Figure 10.5: The wizard for creating a new service builder portlet project uses the information you specify to customize various configuration files in the new project.](../../images/ide-new-liferay-project.png)

Great! You've created a Liferay portlet project! 

You can find more information on Liferay's plugin frameworks in the chapter on
portlet development. In that chapter, we'll discuss the plugin creation wizard
in more detail. 

Note: We're creating the event-listing-portlet project now so that we can
highlight how Liferay IDE simplifies project creation. For more information on
creating portlets, please see the chapter of this guide on portlets. Similarly,
for more information on themes, layout templates, hooks, or Ext plugins, please
refer to the appropriate chapter of this guide. 

Our *event-listing-portlet* plugin project should appear in the Eclipse package
explorer. The project was created in the plugins SDK you configured, under the
directory corresponding to the plugin type the project contains. Here's the
generalized directory structure for portlet projects created in Liferay
IDE/Developer Studio: 

- `PROJECT-NAME/`
    - `docroot/WEB-INF/src`
    - `build.xml` **- Common project file**
    - `docroot/`
        - `css/`
            - `main.css`
        - `view.jsp`
        - `js/`
            - `main.js`
        - `META-INF/`
            - `MANIFEST.MF`
        - `WEB-INF/`
            - `lib/`
            - `tld/`
                - `aui.tld`
                - `liferay-portlet-ext.tld`
                - `liferay-portlet.tld`
                - `liferay-security.tld`
                - `liferay-theme.tld`
                - `liferay-ui.tld`
                - `liferay-util.tld`
            - `liferay-display.xml`
            - `liferay-plugin-package.properties` **- Common project file**
            - `liferay-portlet.xml`
            - `portlet.xml`
            - `web.xml`
        - `icon.png`
        - `view.jsp`

All projects, regardless of type, are created with a `build.xml` and a
`liferay-plugin-package.properties` file--we've highlighted each of them with
the note **Common project file** in the directory structure above. The
`build.xml` file allows Liferay IDE to use Ant to automatically compile and
deploy your plugins. Another default file is
`liferay-plugin-package.properties`. This file contains important metadata for
your project. Liferay IDE's *properties* view gives you a simple interface to
inspect or specify the file's fields, including your project's dependencies and
deployment context, display name, and Liferay version. If you publish your
project as an app to Liferay Marketplace, the value of the `name` property in
`liferay-plugin-package.properties` is used as the app's name. The value of the
`liferay-versions` property is used on Liferay Marketplace to specify the
versions of Liferay on which your application is intended to run.

If you're following along with the creation of the event-listing-portlet, we're
not done yet! We still need to add additional plugins to our project, but first,
let's deploy your new plugin project to your Liferay Server. 

## Deploying New Liferay Projects to Liferay Server [](id=deploying-new-liferay-projects-to-liferay-serv-4)

You have a plugin project, but you need to deploy it onto your Liferay
Server. Let's do it now. There are two ways to do it, and the first is very
easy. Select your project, drag it down, and drop it on your server. Voila! Your
plugin is deployed. 

Alternatively, you can use the following procedure: 

1.  Select your new plugin project and right click the Liferay Server in
    the *Servers* tab; 

2.  Select *Add and Remove...*. 

3.  Select your plugin project and click *Add* to deploy it to the server. 

4.  Click *Finish*. 

You should see the project get deployed to Liferay Tomcat server; in the console
is a message indicating your new portlet is available for use. 

![Figure 10.6: Verifying the output in the Console view](../../images/ide-console-portlet-available.png)

If you've never started this instance of Liferay before, open *Liferay Portal
Home* (`http://localhost:8080/` in most cases) and follow the instructions in
the setup wizard. 

Once you're logged in, click *Add* &rarr; *More*, expand the *Sample* category,
and click the *Add* link next to your demo application. Your portlet then
appears on the page. 

Great, now you can create projects in Liferay IDE! Next, let's learn how to
create new plugins inside of existing projects in Liferay IDE. 

## Creating Plugins [](id=create-plugins-liferay-portal-6-1-dev-guide-en)

Liferay projects can contain multiple plugins. If you've followed the
instructions from the earlier section on creating new Liferay projects, you
should already have created the event-listing-portlet project. In this section
we'll add two portlets to the event-listing-portlet project: the Location
Listing portlet and the Event Listing portlet. This illustrates the general
process for creating plugins inside of an existing Liferay project. Later in
this guide, when we complete developing the Event Listing and Location Listing
portlets, they'll allow users to add, edit, or remove events or locations,
display lists of events or locations, search for particular events or locations,
and view the details of individual events or locations. For now, we'll show you
how to create both portlets in the event-listing-portlet project. But before we
do, let's clean out the bare-bones default portlet from our
event-listing-portlet project. 

The portlet project wizard conveniently creates a default portlet named
*event-listing-portlet* after the project's name. The portlets we'll create in
this section will be named *locationlisting* and *eventlisting*. To avoid any
confusion between the project's default portlet named *event-listing-portlet*
and the portlet we'll be creating named *eventlisting*, let's remove the default
portlet by removing its descriptors and it's JSP. 
    
1.  Open the portlet's `docroot/WEB-INF/liferay-display.xml` file
    and remove the `<portlet id="event-listing" />` tag.
    
2.  Open the `docroot/WEB-INF/liferay-portlet.xml` file and remove the
    `<portlet>...</portlet>` tags and code residing between those tags.
    
3.  Navigate to the `docroot/WEB-INF/portlet.xml` file and remove the
    `<portlet>...</portlet>` tags and code residing between those tags.	

4.  Remove the `docroot/view.jsp` file. 

Super! You've cleaned out the default portlet from the project. Now you're ready
to start creating the example plugins. 

Your Liferay IDE's Package Explorer shows your Event Listing plugin project.
Since it's a portlet type project it has a skeleton in place for supporting more
portlet plugins. Let's start by creating the Location Listing portlet. 

Use the following steps to create the Location Listing portlet:

1.  Right click on your `event-listing-portlet` project in Liferay IDE's 
    *Package Explorer* and select *New* &rarr; *Liferay Portlet*. 

2.  The *New Liferay Portlet* dialog box appears with your plugin project 
    *event-listing-portlet* selected as the *Portlet plugin project* by default.
    It's a good idea to name your *Portlet class* after the name of your
    portlet. We'll name the class *LocationListingPortlet* in this example. Name
    your *Java package* after the plugin's parent project, so it will be
    *com.nosester.portlet.eventlisting*, and leave the *Superclass* as
    *com.liferay.util.bridges.mvc.MVCPortlet*. Alternatively, you could have
    selected *com.liferay.portal.kernel.portlet.LiferayPortlet* or
    *javax.portlet.GenericPortlet* for your superclass. 

    ![Figure 10.7: Liferay IDE's portlet creation wizard makes creating a portlet class is easy.](../../images/create-a-portlet-class.png)

    Here are the portlet class values to specify for the example Location
    Listing portlet: 

    - **Portlet plugin project:** *event-listing-portlet*
    - **Source folder:** */event-listing-portlet/docroot/WEB-INF/src*
    - **Portlet class:** *LocationListingPortlet*
    - **Java package:** *com.nosester.portlet.eventlisting*
    - **Superclass:** *com.liferay.util.bridges.mvc.MVCPortlet*

    Click *Next*.

3.  In the next window of the *New Liferay Portlet* wizard, you'll specify
    deployment descriptor details for your portlet. First enter the *Name* of
    your portlet--in our example, this will be *locationlisting*. Next, enter
    the portlet's *Display name* and *Title*; we'll specify both as *Location
    Listing Portlet*. In this window, you can also specify which portlet modes
    you'd like your portlet to have. *View* mode is automatically selected.
    There are also options for creating resources: you can specify the folder
    where JSP files will be created as well as whether or not a resource bundle
    file will be created. We'll leave the *Create JSP files* box flagged,
    specify *html/locationlisting* as the JSP folder, and flag the *Create
    resource bundle file* box.

    Here are the portlet deployment descriptor details to specify for the
    Location Listing portlet: 

    - **Name:** *locationlisting*
    - **Display name:** *Location Listing Portlet*
    - **Title:** *Location Listing Portlet*
    - **JSP folder:** *html/locationlisting*

    Click *Next*. 

    ![Figure 10.8: Liferay IDE's portlet creation wizard let's you specify the deployment descriptors for your portlets.](../../images/ide-specify-portlet-deployment-descriptors.png)

4.  This window lets you specify portlet deployment descriptor details that are
    specific to Liferay. You can set the file paths of your portlet's custom
    icon, main CSS file, and main JavaScript file. You can also specify a CSS
    class wrapper. Next, you can also choose the category for your portlet (it's
    categorized under *Sample* by default), and choose whether or not to add it
    to the *Control Panel* of your Liferay Portal. Accept the default, leaving
    the *Add to Control Panel* box unflagged. 

5.  The last step is to specify modifiers, interfaces, and method stubs to
    generate in the Portlet class. Accept the defaults and click *Finish*. 

Use the following steps to create the Event Listing portlet:

1.  Right-click your event-listing-portlet project &rarr; *New* &rarr; *Liferay
    Portlet*. Specify *EventListingPortlet* as the name of the portlet class,
    enter *com.nosester.portlet.eventlisting* as its Java package, and select
    *com.liferay.util.bridges.mvc.MVCPortlet* as it's superclass. 

    ![Figure 10.9: Creating portlet classes is simple with Liferay IDE's portlet creation wizard.](../../images/create-a-portlet-class-eventlistingportlet.png)

    Here are the portlet class values to specify for the example Event
    Listing portlet: 

    - **Portlet plugin project:** *event-listing-portlet*
    - **Source folder:** */event-listing-portlet/docroot/WEB-INF/src*
    - **Portlet class:** *EventListingPortlet*
    - **Java package:** *com.nosester.portlet.eventlisting*
    - **Superclass:** *com.liferay.util.bridges.mvc.MVCPortlet*

    Click *Next*. 

2.  In this window we'll specify the portlet's deployment descriptor details. 

    Here are the portlet deployment descriptor details to specify for the
    Event Listing portlet: 

    - **Name:** *eventlisting*
    - **Display name:** *Event Listing Portlet*
    - **Title:** *Event Listing Portlet*
    - **JSP folder:** *html/eventlisting*

    Click *Next*. 

3.  This window lets you specify portlet deployment descriptor details that are
    specific to Liferay. You can set the file paths of your portlet's custom
    icon, main CSS file, and main JavaScript file. You can also specify a CSS
    class wrapper. In the *Liferay Display* section, you can choose the category
    for your portlet (it's categorized under *Sample* by default), and choose
    whether or not to add it to the *Control Panel* of your Liferay Portal.
    Accept the default, leaving the *Add to Control Panel* box unflagged and
    click *Next*. 

4.  The last step in creating your portlet with the wizard is to specify
    modifiers, interfaces, and method stubs to generate in the Portlet class.
    Accept the defaults and click *Finish*. 

By default, new portlets use the MVCPortlet framework, a light framework that
hides part of the complexity of portlets and makes the most common operations
easier. The default MVCPortlet project uses separate JSPs for each portlet
mode: each of the registered portlet modes has a corresponding JSP with the
same name as the mode. For example, `edit.jsp` is for edit mode and `help.jsp`
is for help mode. 

Let's redeploy the plugin project to make our portlet plugins available in the
portal. In the *Servers* tab, simply right click the *event-listing-portlet*
project, then click *Redeploy*.

Now you've created and deployed the *Location Listing* portlet and the
*Event Listing* portlet from the same project. Eventually, when the Location
Listing portlet is complete it will allow users to enter viable event locations.

If you've been following along to create the Event Listing portlet project,
you're done! Next, let's find out how to import existing projects into Liferay
IDE. 

## Importing Existing Projects into Liferay IDE [](id=importing-existing-projects-into-liferay-i-4)

What if you have existing projects that you want to work on in Liferay IDE?
You can import your existing projects into Liferay IDE to take advantage of
Liferay IDE's developer tools. 

We'll cover the following import scenarios: 

1.  Importing existing Liferay projects from the Plugins SDK. 

2.  Importing Eclipse projects that aren't in Liferay IDE (i.e., don't have
    the Liferay facet or target runtime).

3.  Importing existing Liferay IDE projects from another Liferay IDE version or
    workspace. 

Let's start by learning how to import existing Liferay projects from Liferay
Plugins SDK. 

You can import Liferay projects you created with the Plugins SDK, but aren't yet
in your Liferay IDE Eclipse workspace. These projects might already have
`.project` or `.classpath` files in them; regardless, we'll use the same
procedure to import them.

---

 ![note](../../images/tip-pen-paper.png) **Note:** The next two sections assume
 that you've created projects with the Plugins SDK and are familiar with the
 directory structure used by the Plugins SDK. 

---

First let's look at the import process for creating a single Liferay IDE project
from a Plugins SDK project. Follow these steps:

1.  In Liferay IDE, go to *File* &rarr; *New* &rarr; *Project...* &rarr;
    *Liferay* &rarr; *Liferay Project from Existing Source*. 

    You can invoke the same wizard from the Liferay shortcut bar; just click
    the *Create a New Liferay Project* button.

    ![Figure 10.10: Alternate method of Importing a project](../../images/ide-new-proj-existing-source.png)

2.  Browse to your project folder. It should be a subdirectory of one of the
    plugin types (e.g., portlets, hooks, themes, etc) or it won't import
    correctly. Once you select the plugin project folder, its type and SDK
    version values are updated to the correct values. If the SDK isn't recent
    enough or the project type is incorrect, it gets marked with an error. 

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

Next we'll show you how to import all your Plugins SDK projects into Liferay
IDE. 

### Batch Importing Multiple Projects into Liferay IDE [](id=lp-6-1-dgen07-create-multiple-projects-for-all-plugins-in-a-plugins-sdk-0)

Does your Plugins SDK contain multiple plugins? You can easily batch import each
plugin in your Plugins SDK as its own Liferay IDE project. 

1.  In Eclipse go to *File* &rarr; *Import*... &rarr; *Liferay* &rarr; *Liferay
    Projects from Plugins SDK*. 

    ![Figure 10.11: Choosing the project type for import](../../images/ide-import-from-plugin-sdk.png)

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

Your plugins are imported! Next, we'll talk about importing existing Eclipse
projects into Liferay IDE. 

### Importing Existing Eclipse Projects into Liferay IDE [](id=lp-6-1-dgen07-importing-an-existing-eclipse-project-that-is-not-aware-of--0)

The steps we outlined above let you import projects that have never been opened
in Eclipse. If you've been using Eclipse without Liferay IDE, you may already
have a project in your Eclipse workspace. Follow the import steps below to
convert your project. 

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

    ![Figure 10.12: Converting a project](../../images/ide-convert-plugin-project.png)

3.  Select the Liferay runtime to use for the project. If you don't have a
    Liferay Runtime defined, do so now by clicking *New...*. 

4.  Click *Finish*. 

Remember to verify that your import was successful and that your project was
configured correctly as a Liferay IDE project. We have one more import scenario
to cover--importing existing Liferay IDE projects into your Liferay IDE. 

### Importing an existing Liferay IDE project [](id=lp-6-1-dgen07-importing-an-existing-liferay-ide-project-0)

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

### Verifying that the import has succeeded [](id=lp-6-1-dgen07-verifying-that-the-import-has-succeeded-0)

After importing projects into Liferay IDE, you'll want to verify that they
imported successfully, and that they're properly configured as Liferay IDE
projects. Here's how you verify that your imports were successful: 

1.  Once the project is imported, you should see a new project inside Eclipse
    and it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 10.13: Verifying that the import succeeded](../../images/ide-liferay-project-w-overlay-image.png)

2.  Let's check the project's target runtime and facets to make sure it's
    configured as a *Liferay IDE* project:

    2.1. In the *Package Explorer*, right click *<project-name>* &rarr;
         *Properties* &rarr; *Targeted Runtimes*. 

    2.2. In the *Properties* window, click *Project Facets* and make sure both
         Liferay runtime and Liferay plugin facets are properly configured. 

    ![Figure 10.14: Project Facets](../../images/ide-project-facets.png)

Great! You've confirmed that your import was successful; you can now make
revisions to your configured Liferay IDE project. Next, let's explore Liferay
IDE's Remote Server Adapter feature. 

## Using Liferay IDE's Remote Server Adapter  [](id=liferay-portal-6-1-liferay-ide-remote-server-adapter)

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

---

![important](../../images/tip-pen-paper.png) **Important:** Keep a record of
your portal administrator login credentials (e.g., username/password) for your
remote Liferay server; you'll need them to configure your connection from
Liferay IDE to the remote Liferay server. 

---

Let's start by configuring the Remote Server Adapter. 

### Configuring the Remote Server Adapter  [](id=liferay-portal-6-1-liferay-ide-configure-remote-server-adapter)

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

### Using the Remote Server Adapter [](id=liferay-portal-6-1-how-to-use-remote-server-adapter)

Once your remote Liferay Portal server is correctly configured and Liferay IDE
is connected to it, you can begin publishing projects to it and using it as you
would a local Liferay Portal server. 

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

## Designing workflows with Kaleo Designer for Java [](id=lp-6-1-dgen08-designing-workflows-with-kaleo-designer-for-java-0)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Portal includes a workflow engine called *Kaleo Designer for Java*.
Kaleo allows portal administrators to set up workflows for their organization's
needs; the workflow calls users to participate in processes designed for them.
Kaleo workflows, called *process definitions*, are essentially XML documents.
Kaleo supports a host of XML element types to trigger decisive actions in your
business process instances. You can fine tune the logic of your process
definition by incorporating scripts and templates. The *Kaleo Forms EE* app from
Marketplace includes Liferay's *Kaleo Workflow Designer* that lets you create
and modify portal workflows in your browser. With Kaleo Designer for Java, you
can design and publish Kaleo workflows from Liferay Developer Studio! 

![Figure 10.17: Kaleo Designer for Java gives you a powerful environment for designing, modifying, and publishing Kaleo workflows.](../../images/kaleo-designer-for-java.png)

Kaleo Designer for Java lets you incorporate back end Java development and
scripting in your workflows. Its graphical interface lets you drag and drop
nodes into your workflow. A shortcut on each node gives you easy access to the
node's XML, letting you edit its current implementation to make subtle
modifications or inject new business logic. In addition, Liferay Developer
Studio comes bundled with a Java/Groovy editor (made available by Spring
Source), giving you the same rich editing experience you're accustomed in
editing Kaleo [Groovy](http://groovy.codehaus.org/Documentation) scripts. From
the editor you can delegate workflow decisions to your custom business logic
APIs or access any of the Liferay Portal APIs. In Developer Studio, you can
leverage editors for Beanshell, Drl, JavaScript, Python, and Ruby scripting
languages. You can leverage editors for
[FreeMarker](http://freemarker.sourceforge.net/) and
[Velocity](http://velocity.apache.org/engine/releases/velocity-1.5/user-guide.html)
template languages too. Kaleo Designer for Java gives you a rich tool set for
creating/editing workflows, manipulating workflow nodes, and implementing
business logic. But there's more!

With Kaleo Designer for Java, you can remotely add and update workflow
definitions directly to and from your Liferay server. You can publish your
workflow drafts to your portal by simply dragging the workflow file onto your
portal server in Developer Studio's *Servers* tab. The *Servers* tab shows
workflows you've published from studio and gives you access to workflows already
published on the portal server. You can edit existing workflows and create
custom business logic in Developer Studio locally, then republish them on your
portal; you don't have to navigate back and forth from your portal and Developer
Studio to complete these tasks. As you can see, Kaleo Designer for Java is a
powerful application for creating, modifying, and publishing workflows in
Liferay Developer Studio. 

<!--I only lightly edited the introduction to Kaleo. I don't think I understand
enough about how it works from a development perspective; The above might be
perfectly clear to a developer, but it's not to me. -Russ -->

---

![note](../../images/tip-pen-paper.png) **Note:** The *Kaleo Forms EE* app comes
with an existing workflow designer that's used *within* Liferay Portal. It's
used to design workflow configuration and is described in the [Kaleo Forms:
Defining Business
Processes](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen11-kaleo-forms-defining-business-processes-0)
chapter of *Using Liferay Portal*. Refer to the [Using
Workflow](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/workflow-with-kal-4)
chapter of *Using Liferay Portal* if you're unfamiliar with basic Kaleo
workflow concepts or want to know how to design your workflow within Liferay
Portal. 

---

To use Kaleo Designer for Java, install the *Kaleo Forms EE* app from Liferay
Marketplace. The app includes three plugins--`kaleo-forms-portlet`,
`kaleo-designer-portlet`, and `kaleo-web` plugins.

Here's how to download and install *Kaleo Forms EE*:

1.  Go to [Liferay Marketplace](https://www.liferay.com/marketplace).

2.  Click on [EE
    Marketplace](http://www.liferay.com/marketplace/-/mp/category/12729032) in
    the left navigation area.

3.  Download and install the *Kaleo Forms EE* app.

After downloading and installing the Kaleo Forms EE application, restart the
Liferay Server and a *Kaleo Workflows* folder automatically appears underneath
the server instance in the Servers view of Developer Studio.

![Figure 10.18: In Studio's *Servers* view, your server's Kaleo Workflows folder shows workflows published on your portal.](../../images/kaleo-1.png)

Developer Studio retrieves all workflow definitions published on your portal
server. As mentioned previously, the Kaleo Designer for Java lets you remotely
add and update Kaleo workflow definitions directly to and from the portal
server. The *Single Approver* workflow comes already installed with the Kaleo
app. 

To open a workflow, double click it in the Kaleo Workflows folder. This
retrieves the workflow definition from the Liferay server so you can edit it in
Developer Studio. 

---

![note](../../images/tip-pen-paper.png) **Note:** When you open a workflow,
you'll be prompted to choose whether to switch to the Kaleo designer
perspective. Clicking *Yes* lets you use the perspective's helpful features,
including the palette toolbar, properties view, and outline view. 

---

Next, let's get into the flow (pun intended) by creating our own workflow using
the Kaleo Workflow Designer for Java. 

### Creating a workflow [](id=lp-6-1-dgen08-creating-a-workflow-0)

Developer Studio makes it easy for you to write custom business logic that
enhances Kaleo workflows. Let's create our own workflow in Developer Studio and
highlight features from Kaleo Designer for Java (Designer) along the way.

To demonstrate Designer's features, let's create a workflow definition for a
software ticketing process. Are you ready to roll? Let's get started! 

![Figure 10.19: Check out the adventures of Liferay's mascots, Ray and Alloy, found at http://www.liferay.com/ray.](../../images/kaleo-15.png)

1.  Create your new workflow definition by going to *File* &rarr; *New* &rarr;
    *Liferay Kaleo Workflow*. Alternatively, you can select *Liferay Kaleo
    Workflow* from the toolbar button shown in the figure below. 

    ![Figure 10.20: Create a new workflow definition locally by selecting *Liferay Kaleo Workflow* from the toolbar button.](../../images/kaleo-3.png)

2.  The *Create New Kaleo Workflow File* wizard will guide you through the steps
    necessary to complete the initial setup of your new workflow definition. 

    The first window you'll see is the *Create Kaleo Workflow* form. There are
    several fields here:  

    *Project*: Specify an existing Liferay project to house your workflow
    definition. 

    *Folder*: Specify where in the project the workflow definition XML file will
    be stored.

    *Name*: Give your workflow definition a descriptive name. We've chosen
    *Ticket Process* for our example. 

    *Initial state name* and *Final state name*: Name your workflow's initial
    and final state nodes.

    *Default script type*: Choose a default script type; Designer will bring
    its editor up when you're done creating the workflow.

    *Default template type*: Choose a default template editor; Designer will
    open it when you're done creating the workflow. 

    The snapshot below displays the setup menu for our
    ticket process workflow definition.

    When you're finished in this window click *Next*. 

    You'll be directed to the *Choose Assignment Type* window next. Here you'll
    provide an *Initial task name*, then choose an assignment type from the list
    of options.  
    
    For our workflow example, select *Assign to asset creator* and name the task
    `Developer`. When our workflow's `Developer` task is invoked, the creator of
    the workflow's asset is assigned to it. In Liferay Portal, each Kaleo
    workflow is associated with an asset type. Later in our exercise, we'll
    associate our workflow with a Dynamic Data List (DDL). 

    ![Figure 10.21: When choosing an assignment type for a task node, you are given multiple options.](../../images/kaleo-4.png)

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** You must specify a Liferay
    project as a home for the workflow definitions you create. 

    ---

3.  Click *Finish* to complete the initial setup of our workflow definition. 

<!--This type of instruction, in step 2, with heirarchical steps actually looks
bad in my Markdown plugin for Jedit; very crammed -->

Your workflow appears as a workflow diagram, letting you interact with your
workflow graphically. Graphical features and toolbars allow you to customize
your workflow definition. The *Palette* view is one of Designer's most commonly
used tools. Let's explore it next. 

#### Palette and Floating Palette [](id=lp-6-1-dgen08-palette-and-floating-palette-0)

The *Palette* lets you graphically customize your workflow with nodes and
transitions. In addition, you can choose different behaviors for your mouse
pointer. 

![Figure 10.22: The palette toolbar lets you customize your workflow with additional nodes and transitions.](../../images/kaleo-6.png)

Here are your pointer options: 

- *Select*: The default pointer setting used for selecting options on the
  workflow diagram by clicking the icon. 
- *Marquee*: Used for drawing an invisible selection box around multiple icons.
  This is useful when you want to manipulate multiple nodes and/or transitions
  on the workflow diagram. 

Transitions connect one node to another. On exiting the first node, processing
follows the transition to the node it points to. Selecting a *Transition* turns
your pointer into a connector; you connect the starting end of a transition to
one node and the other end to the next node in your process. 

In addition to *Start* and *End* node types, there are five node types you can
use in your workflow: 

- *Condition*: Directs workflow execution to an appropriate transition based on
  conditional logic of the node's script. 
- *Fork*: Forks the workflow execution into two parallel threads. 
- *Join*: Joins parallel workflow threads. 
- *State*: Represents a workflow state. 
- *Task*: Represents a task that can be assigned. 

Drag and drop any nodes you need onto your workflow diagram. Each node type
supports execution of scripted actions and sending notifications that can use
templates. For additional information on the node types, refer to the [Kaleo
Designer](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen06-kaleo-designer-0)
section of *Using Liferay Portal*. 

For our `ticket-process-definition` workflow diagram, we have a simple
`StartNode` *State* node, followed by the `Developer` *Task* node, followed by
the `EndNode` *State* node. There are two transitions, from `StartNode` &rarr;
`Developer` and from `Developer` &rarr; `EndNode`. 

We want a developer to approve his fix and send it for quality assurance to
*QA*, where it must pass testing by a QA engineer. Then it'll go to *QA
Management*, where it must be approved by a QA manager. Let's use a *Fork* node
to accurately depict these parallel approval tasks. 

Drag and drop a *Fork* node onto your workflow diagram. A wizard helps you create
your node. Click the green plus symbol to select new or
existing nodes to process in parallel threads. A drop-down menu gives you
options to select tasks to be done in your fork threads. In addition, a checkbox
lets you indicate whether to automatically add a corresponding join node to your
workflow.

![Figure 10.23: You can select what to fork and whether to automatically create a join node.](../../images/kaleo-7.png) 

On finishing your fork node in the wizard, Kaleo Designer places your new nodes
onto the workflow diagram's canvas. If you're not happy with the location of
your new nodes, drag them to place them where you want on your canvas. Our
ticket process workflow now looks something like this: 

![Figure 10.24: After creating the fork and join nodes, you can reposition them to your liking.](../../images/kaleo-8.png)

Why is there a red "X" in the bottom left of multiple nodes within our workflow?
These are error markers! Errors indicate something is specified incorrectly or is
missing from your nodes. Hover your pointer over each error marker; Developer
Studio displays hints on resolving each problem. Don't worry, we'll address
these error markers momentarily. 

Hovering your pointer over a node brings up a floating palette; use it to make
quick, convenient customizations to a node. 

The floating palette has several features you can use: 

- *Add* (green cross): Add an action or notification on the node. To edit the
  added action or notification, bring up Designer's *Properties* view. 
- *Edit Actions* (paper with arrow): Edit the node's existing actions. 
- *Edit Notifications* (envelope): Edit existing notifications on the node. 
- *Edit Script* (pencil): Edit the script of the condition node. This feature
  only applies to the condition node. 
- *Change Assignments* (person): Assign or reassign a task. This feature only
  applies to the *Task* node type. 
- *Delete* (red "X"): Delete the node. 
- *Show in Source* (paper with folded corner and arrow): Show the node specified
  as as a model element in the workflow definition's XML source and switches the
  main editor to *Source* mode. 
- *Start Transition* (black arrow): Change the pointer to transition mode
  letting you create a workflow transition from the current node to another
  node.

Obviously, there is still work to be done in our workflow definition. We have
multiple error markings and the fork and join nodes aren't connected to
anything. Let's change the assignments for our two new task nodes, QA and QA
Management, by clicking the *Change Assignments* icon from the floating palette
for each node. The *Choose Assignment Type* menu appears for each node, letting
you choose their assignment type. After we assign the QA and QA Management task
nodes, the error markers disappear.

For our `ticket process` workflow's QA tasks, let's assign someone other than
the asset creator. Realistically, each of these tasks would be assigned to
different site roles. For simplicity, lets assign both the QA and QA Management
tasks to the same user. If you have a user in mind, specify that user.
Otherwise, create a user named "Joe Bloggs" with screen name "joe". For the user
to receive emails, he must be registered within Liferay Portal. If you haven't
registered Joe Bloggs ("joe") already, see the [Adding
users](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen12-adding-users-0)
section of *Using Liferay Portal* for instructions. To configure the user's
email, login to the user's account and visit *Control Panel* &rarr; *Server
Administration* &rarr; *Mail* for setup options. 

Select the *Change Assignments* icon from the floating palette for each QA task
node. Then, select *Assign to a specific user* from the *Choose Assignment
Type* menu and click *Next*. You have options to enter the user's *User-id*,
*Screen name*, or *Email address*. Enter the user's screen name and click
*Finish*.

![Figure 10.25: Designer lets you assign a task to a specific user of Liferay Portal.](../../images/kaleo-29.png)

Assigning the QA and QA Management task nodes resolved their error markings (no
more red "X"!). The join node's error marking won't disappear until you connect
the join node to another task. 

While we're using Designer's Workflow Diagram, let's go over some of its
features. 

#### Workflow Diagram Features [](id=lp-6-1-dgen08-workflow-diagram-features-0)

Developer Studio provides you with additional features within the workflow
diagram. Below we list some of these features; they can greatly enhance your
workflow designing experience. 

- *Workflow Diagram Actions* are available via the toolbar in the upper right
  corner of the Workflow Diagram: 
    - *Zoom In*
    - *Zoom Out*
    - *Zoom Actual*
    - *Save as Image*
    - *Print*

	These toolbar icons are shown in the figure below.

- *More Workflow Diagram Actions* are accessible by right clicking on the
  Workflow Diagram's canvas:
    - The *Layout* feature lets you arrange nodes vertically or horizontally, so
      it's easy to organize your workflow quickly without having to touch a node. 
    - The *Rename* feature lets you rename a node or transition; select by
      double clicking its current name and typing in a new name.
    - The *Surveyor's Level* feature helps you center a node, vertically or
      horizontally, with respect to another node.

You've probably noticed the *Properties* and *Outline* views below your workflow
diagram. Let's explore them next. 

#### Properties View and Outline View [](id=lp-6-1-dgen08-properties-view-and-outline-views-0)

The *Properties* and *Outline* views contain more cool features you can use to
customize your workflow; they're located on the bottom and bottom right of
Developer Studio, respectively. 

The *Properties* view lets you edit the current node's properties. If no node is
selected or you select the workflow canvas, the Properties view displays your
workflow's general properties; you can edit these, too. Once you click on an
individual node, its properties appear. 

Node properties are grouped as follows:

- *Actions*: Execute scripts with respect to your node. Each action has a
  *Name*, *Script Language*, *Execution Type*, and a *Priority* (optional).
  Clicking *Edit Script* brings up the script in the default editor for the
  *Script Language* you specified. 
- *Notifications*: Notify users with respect to your node. Each notification has
  a *Name*, *Template Language*, *Execution Type*, and one or more *Notification
  Types*. 
- *Assignments*: Assign tasks to users or roles. Click *Change Task Assignments*
  to specify or change the assignment. 
- *Task Timers*: Name timers for a task and whether the timers are blocking.
  Each task timer has a *Name* and *Blocking* indicator. 
- *Script*: Edit a script for your condition node. Clicking *Edit Script* brings
  up the script in the default editor for the *Script Language* you specified. 
- *Other*: Edit miscellaneous properties like *Name* and *Description*,
  applicable to your node's type. 

Here's what the Properties view looks like in Developer Studio: 

![Figure 10.26: The Properties view gives you multiple sub-tabs to help customize your workflow nodes.](../../images/kaleo-11.png)

Workflows frequently become too large to view in entirety on the workflow
diagram screen; the *Outline* view is a huge asset when this happens. It's a top
level view that displays your entire workflow definition, no matter how large it
becomes. In addition, it highlights what you're currently viewing on your
Workflow diagram, giving you a picture of where you're located in the broader
picture of your workflow. You can use the *Outline* view to change your position
in the Workflow Diagram by dragging the highlighted box where you'd like to
focus. 

Developer Studio's *Properties* and *Outline* views make customizing your
workflow easier than ever! Developer Studio also offers a convenient way to edit
your workflow scripts, which is our next topic. 

### Using Workflow Scripts [](id=lp-6-1-dgen08-using-workflow-scripts-0)

You can use Developer Studio to edit workflow scripts; it recognizes multiple
script languages, so you can choose one you're comfortable with. Developer
Studio provides you many script editing features so you can quickly implement
business logic in your workflows. 

Developer Studio supports several script languages: 

- Beanshell 
- Drl 
- Groovy 
- JavaScript 
- Python 
- Ruby 

Let's dive back into our `software ticket` workflow definition and create a
script. It's not guaranteed that every ticket submitted has a resolution. If the
issue was due to a silly user error, there's no reason to change the product. In
such cases the developer will resolve the ticket and indicate there is no
resolution in the product (i.e., no modifications are were made). Regardless,
we'll have the developer fill out an online Dynamic Data List (DDL) form to
initiate a workflow for each of her tickets. Once the workflow is invoked, its
associated DDL record is accessible from our workflow's context. Let's use a
condition node to handle the ticket based on the DDL record. 

To set up the workflow process we described above, we'll need to add a
*Condition* node and two transitions. 

1.  Drag and drop a *Condition* node onto your workflow diagram. A *Create New
    Condition Node* menu should appear.

2.  Name the node *Resolution*.

3.  Choose a script language for the condition node. Select *Groovy* and you'll
    see how easy it is to embed Java code. In our Groovy script, we'll access
    the DDL record to determine whether the ticket warrants a modification to
    the product. If it does, we'll assign it to a developer via the Developer
    task node. Otherwise we'll end the workflow by transitioning to the
    workflow's EndNode.  

4.  From the *Create New Condition Node* menu, add two transitions--one to the
    *Developer* node and the other to the *EndNode* state. We'll add the
    transition to the *Developer* node first. 

    Click the green plus sign and select the transition icon from its menu. An
    entry for the transition appears in the named list of *Condition
    transitions*. 

    Click the browse icon in the entry and select the *Developer* node.

5.  Add a transition to the *EndNode* state in the same manner that added the
    transition to the *Developer* node in the previous step.

6.  Click *Finish*. 

<!--When I select the green plus sign I get a list to choose from; Existing
Node, New Condition, New Fork, New Join, New State, New Task. I do not see a
transition icon to select. -->

Here's a snapshot of the *Create New Condition Node* menu configured for the
ticket process workflow. 

![Figure 10.27: When creating a condition node, you can set your preferred script language, name, and condition transitions.](../../images/kaleo-12.png) 

Before adding a script to our condition node, let's make some changes to our
workflow transitions: 

- Add a transition from the *Developer* task node to the *Pass To QA* fork node.
- Add a transition from the *StartNode* state node to the *Resolution* condition
  node.
- Delete the transition that currently connects the *StartNode* state node to
  the *Developer* task node.

To add a transition from one node to another, do the following:

1.  Click the transition icon from the palette. Your pointer's icon shows as a
    plug indicating you are in *connector* mode.

2.  Select a node on your workflow diagram from which the transition will start.
    A dotted line appears with one end connected to the selected node and the
    other end following your pointer.

3.  Select a node to which the transition will end. The dotted line changes into
    a fixed ray with the arrow pointing to the transition's end node.

4.  To exit connector mode, hit *Escape* on your keyboard and click your pointer
    at empty space in your workflow diagram.

You may notice the error marking on the condition node. When you hover over
the marking, a hint indicates a script must be specified for the node.

Open the script editor for your *Resolution* condition node by doing one of the
following: 

- Select the node and click *Edit Script* from the *Script* tab of the
  Properties view. 
- Click the *Edit Script* tool from the node's floating palette. 
- Right-click the node and select *Edit Script*. 

We set our default script language to Groovy, so the Java/Groovy editor appears.
To learn more about the Groovy editor, see the [Groovy User
Guide](http://groovy.codehaus.org/User+Guide). If you set the script language to
another language, the editor for that specific language appears. The editor runs
in the context of editing the specific node you selected. Anything you type in
the script editor for this condition node is written inside the
`<script></script>` tags for the `<condition/>` element that represents our node
in our workflow definition's XML file (in our case,
`ticket-process-definition.xml`). 

---

![note](../../images/tip-pen-paper.png) **Note:** Developer Studio lets you use
multiple script editors even while modifying the same workflow definition XML
file. 

---

The *Palette* view is much different from when you were working in the workflow
diagram; it's associated with your Java/Groovy script editor now and includes
folders containing the following entities for your script: 

- Context Variables 
- Dynamic Data Lists 
- Roles 
- Scripts 
- Status Updates 

You can expand and collapse a folder by clicking its name bar. 

Here's a snapshot of the palette with the *Context Variables* folder open:

![Figure 10.28: Each script editor is associated with a palette that contains helpful snippets of code you can insert.](../../images/kaleo-14.png) 

Drag and drop an entity from your palette onto your Java/Groovy editor and code
representing that entity appears in the editor. The inserted code is free of
compile errors and warnings because the editor is running in the context of
Liferay Portal. All of the Liferay Portal APIs are available to you. In the
editor you can invoke code-assist and access built in Kaleo workflow variables. 

Let's get the DDL record that's being worked on in our workflow process. We'll
need the `serviceContext` entity, under `Context Variables` in the palette. To
learn more about Service Context and its parameters, see the [Service
Context](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/service-conte-1)
section of this guide.

Let's use Designer's palette features in conjunction with our Java/Groovy editor
to implement our condition: 

1.  Drag and drop the *serviceContext* entity from the *Context Variables*
    folder in your palette onto the script editor. This grabs the Service
    Context. 

2.  Drag and drop the *ddlRecord* entity from the *Dynamic Data Lists* folder in
    your palette onto the script editor. We get the `ddlRecordId` from the
    Service Context and use that ID to look up the DDL record via Liferay
    service utility `DDLRecordLocalServiceUtil`.

Append the following Java code to the `DDLRecordLocalServiceUtil` script: 

	Field field = ddlRecord.getField("status");

	String status = GetterUtil.getString(field.getValue());
	if (status.contains("not")) {
		returnValue = "No"
	}
	else {
		returnValue = "Yes"
	}


We're pulling out the status from the DDL record and returning a value
indicating "Yes" to continue fixing the ticket issue or "No" to transition to
the workflow's end state. 

Add the following to the script's imports to finish things up: 

	import com.liferay.portlet.dynamicdatamapping.storage.Field;

Now the script accurately implements the condition logic we want. As a reminder,
all of the code was injected into our workflow's XML file within the
`<condition/>` element that represents our condition node. 

---

 ![note](../../images/tip-pen-paper.png) **Note:** Make sure you correctly name
 the transitions stemming from the condition node. The "No" transition should
 point to the EndNode, while the "Yes" transition should point to the Developer.
 If the condition script's return values don't match the transition names, the
 workflow engine won't know which transition to use. 

---

Here's a snapshot of our current ticket process workflow after inserting the
condition node: 

![Figure 10.29: The ticket process workflow after inserting the condition node.](../../images/kaleo-30.png)

We need to create a valid DDL record to invoke this workflow properly. If you're
thinking "How do we set up a DDL record?" or "How does this DDL record thingy
work?", you're on the right track. If you're jumping up and down screaming
"Liferay is da bomb!" We welcome your reaction, too. Regardless, we'll address
DDLs soon in the *Configuring a DDL record* section of this chapter. 

Next let's create a custom notification and write a template for it using a
template editor. 

### Leveraging Template Editors for Notifications [](id=lp-6-1-dgen08-leveraging-template-editors-for-notifications-0)

Designer lets you leverage FreeMarker and Velocity editors to customize
templates for your workflow notifications. A FreeMarker editor comes bundled
with Developer Studio. To edit Velocity templates, you'll need to install an
editor. Don't worry, Developer Studio makes it easy, and we'll show you how. 

#### Add-on: Install Velocity Editor Support [](id=lp-6-1-dgen08-add-on-install-velocity-editor-support-0)

Liferay Developer Studio comes with additional add-on support for editing
Velocity template notifications in the Kaleo Designer for Java. To use this
feature go to the Developer Studio 1.6 customer page
[http://www.liferay.com/group/customer/products/developer-studio/1.6](http://www.liferay.com/group/customer/products/developer-studio/1.6),
navigate to the *Add-on: Install Velocity Editor Support* section, and download
the Liferay Developer Studio Velocity update site zip file and install it. You
can follow these steps: 

1.  From Liferay Developer Studio, Click *Help* &rarr; *Install New Software...*

2.  Click *Add...*

3.  Click *Archive*.

4.  Browse to the downloaded update site zip file and select it.

5.  Click *OK* to close the *Add Repository* dialog.

6.  Expand the added repository and check the *Liferay Developer Studio
    Velocity* feature.

7.  Uncheck the option *Contact all update sites...*

8.  Click *Next* to progress through the wizard.

9.  After reading and accepting the license agreement, click *Finish*.

10. Restart Liferay Developer Studio to complete the install.

When you edit a notification template set to the Velocity type, the template
opens in the Velocity editor (a small "v" icon is in the left side of the
editor's tab). 

#### Creating Notifications [](id=lp-6-1-dgen08-creating-notifications-0)

To access the template editors, click on the node of your choice and select the
*Notifications* sub-tab in the *Properties* view. Create a new notification by
clicking the green "plus" symbol. 

![Figure 10.30: When creating a notification, Developer Studio offers several options, like choosing  a template language.](../../images/kaleo-16.png)

There are several fields to fill in for your notification: 

- **Name**
- **Template Language**
- **Execution Type**
- **Notification Type**
- **Notification Transports**
- **Addresses**

<!--This is a field list, so bold, right? -->

Click the pencil icon to open the editor associated with your notification's
template language. Like the script editor, the template editor's *Palette* view
lists entities that you can drag and drop onto your workflow diagram. 

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

![note](../../images/tip-pen-paper.png) **Note:** Similar to the bundled script
editors, Developer Studio lets you use FreeMarker and Velocity template editors
to customize notifications in your workflow definition.

---

Let's continue editing our ticket process workflow. After completion of the QA
task assignments, our Project Management team should be notified. To set up a
notification email, we'll add a new task node that transitions from our join
node. This new task node will hold our email notification. Typically, we'd
assign this task to a project management role and email it to the project
management team's email alias. For demonstration purposes, we'll use "Joe Blogs"
for both purposes. As mentioned previously, you can specify an existing user
that has an email or create a user with screen name "joe" having your email
address. This process is similar to how we assigned our previous task nodes. 

1.  Drag a new *Task* node onto your workflow diagram. 

2.  Name the new node *Project Management* and select *Assign to a specific
    user*. 

3.  Click *Next*. 

4.  Enter *Screen name* "joe". 

5.  Click *Finish*. 

Now we just need to incorporate the Project Management node into our
workflow process. Add a transition named *Passed QA* from our join node to the
Project Management node. Lastly, add a transition named *Completed* from our
Project Management node to our *EndNode*. Here's an updated screenshot of what
your workflow diagram should look like: 

![Figure 10.31: Our workflow diagram is busy now; but we're not finished yet!](../../images/kaleo-31.png) 

Let's create our email notification for our Project Management task node next.
Click on the Project Management node and select *Notifications* in the
Properties window. 

To create the email notification, follow these steps: 

1.  Click the green "plus" symbol to create a new notification. 

2.  In the **Name** text field, enter "ticket process email".

3.  Select *FreeMarker* from the **Template Language** drop-down menu.

4.  Select *On entry* from the **Execution type** drop-down menu.

5.  Select *email* under **Notification transports**.

<!--Bolded field items. -->

Now open the FreeMarker template editor by clicking the pencil icon beneath the
green "plus" symbol. 

Insert FreeMarker code into the FreeMarker editor to specify your email
notification template. We've provided an example email notification code snippet
written in FreeMarker. 

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

The snapshot below illustrates what the snippet sends to the configured email
recipient. 

![Figure 10.32: This is how the email notification will appear when it's received.](../../images/kaleo-43.png) 

Your email notification is set up! Now, when the Project Management task node is
activated in the workflow, the specified user (i.e. *joe*) will receive the
notification email, all dressed up with your FreeMarker template (you might say
it's all dressed up with somewhere to go). 

With template editors, customizing your notification templates is easier than
ever. FreeMarker comes bundled with Developer Studio so it's obviously the
simplest solution, but you can create Velocity templates just as easy by using
the Velocity editor you installed. 

In the next section you'll see a list of workflow and service context content
you can use when creating a customized script or template. 

#### Workflow Context and Service Context Variables [](id=lp-6-1-dgen08-workflow-context-and-service-context-variables-0)

A context variable provides a uniform variable to insert into your templates and
scripts. When executed, a context variable is automatically deleted and replaced
with the value pertaining to that key. When you create notifications for a
workflow, assign Liferay Portal context variables for a cleaner and more
efficient process. With context variables, your notifications become more
customizable, rather than following the same format for every recipient. The
context variables you declare in your notifications refer to your Liferay
instance and the values it holds for your declarations. 

Below you'll see tables listing numerous context variables and service context
content. The context variables are the first table, followed by the service
context content for web content, blog entries, and message board messages. We've
separated service context content from the workflow context variables because
service context keys depend on asset type, while context variables don't. Also,
note the asterisks (`*`); they're used to flag context variables that depend on
workflow activity. 


***Workflow* Context Variables**

 Key | Type | Description |
---- | ---- | ----------- |
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

***Web Content* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
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

***Blog Entry* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
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

***Message Board Message* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
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

<!--Rich says change these to HTML. -Russ -->

Next you'll learn how to view your workflow definition XML file in Developer
Studio. 

### Viewing workflow definition XML source [](id=lp-6-1-dgen08-viewing-workflow-definition-xml-source-0)

The workflow diagram view of your workflow definition is convenient; sometimes
you'll also want to edit and review your workflow definition's XML source code.
Selecting the *Source* tab next to the *Diagram* tab in the main editor view
takes you to the XML, and you can easily switch contexts as you need. 

![Figure 10.33: Feel free to switch between *Diagram* and *Source* modes of your workflow editor in Kaleo Designer for Java.](../../images/kaleo-20.png)

*Source* mode offers you its own cool features: 

- *Section Highlighting*: Shows XML source code for the node or transition
  currently selected in the workflow diagram. Click the document icon in an
  entity's floating palette or right click the entity and select *Show in
  source*.  
- *Editor Validation*: Displays an error marking in the editor's gutter if your
  code is invalid. Click the error marking to see hint for resolving the error. 
- *Content Assist*: Suggests language specific functions and variables as you
  edit code. When you're inserting variables, it also lists all available
  variables that are a part of the Kaleo workflow. 

---

![note](../../images/tip-pen-paper.png) **Note:** *Using Liferay Portal* section
[Creating new workflow
definitions](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/creating-process-definitio-1)
explains how to define workflows via XML.

---

With the *Source* view, you can keep track of your edits while using Developer
Studio's powerful graphical features. 

Let's save your workflow definition and publish it to your Liferay server. 

### Publishing workflows to the server [](id=lp-6-1-dgen08-publishing-workflows-to-the-server-0)

After you create a new workflow or modify an existing one, you'll have to
publish it onto your Liferay server before your site's members can use it. Let's
publish the `ticket process` workflow definition onto your Liferay server. 

To publish your `ticket process` workflow definition: 

1.  Right-click the *Kaleo Workflows* folder listed under your Liferay server in
    the *Servers* view. 

2.  Select *Upload new workflow...* to bring up the *workspace files* browser. 

3.  Browse for your workflow definition file and select it for publishing. 

Alternatively, you can publish your new workflow XML file by dragging it from
your *Package Explorer* view onto your Liferay server in your *Servers* view. 

---

![note](../../images/tip-pen-paper.png) **Note:** To update your Kaleo Workflows
folder with the latest workflow versions created or modified in Liferay Portal
using Kaleo Workflow Designer from the *Kaleo Forms* portlet, right click *Kaleo
Workflows* under your server and select *Refresh*. 

---

You probably understand why it's necessary to publish new workflow definitions
onto the Liferay server; it might be less clear why you need to republish
existing workflow definitions that you've modified. When you save changes to a
workflow, they're not immediately available in your portal; it's still using the
previous version of the workflow. Developer Studio saves the workflow as a
draft, so you can work on multiple iterations of the same version until you're
ready to publish your changes. Once you publish, you now have a new version to
make changes on top of. For example, you might be working on *Version 1* of your
workflow definition; as you make changes, you save them in multiple drafts. When
you are finished with all of your changes, you publish the workflow triggering
creation of a new version (*Version 2*) of the workflow. The new version is made
available on the server immediately for your workflow administrators to
associate with asset publications, DDLs, and with Kaleo Forms. 

Unlike other Java editors, Developer Studio lets you test your workflow
definition as a draft. You can also publish your workflow definition straight to
Liferay Portal for quick and easy configuration. 

Are you ready for our *Kaleo Designer for Java* finale? You just have to
activate the workflow in your Liferay Portal, then we'll set up the DDL record
and try out our new workflow. Let's activate! 

### Using workflows in Liferay Portal [](id=lp-6-1-dgen08-using-workflows-in-portal-0)

Let's put some finishing touches on your workflow and test drive it in Liferay
Portal. Before you can use a workflow definition, it must be activated in your
Liferay Portal. Navigate to the *Control Panel* and select *Workflow*. Then, in
the *Definitions* tab, click on the *Actions* button and select *Activate*. 

A workflow definition can be associated with publication of an asset or DDL
record. Let's associate our ticket process workflow definition with a DDL record
that lets a developer indicate whether she'll fix a ticket's issue. You can
find detailed instructions for creating a DDL by visiting the section [Defining
data
types](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/defining-data-typ-1)
in *Using Liferay Portal*. We'll demonstrate how easy it is. 

### Using DDLs with workflows [](id=lp-6-1-dgen08-using-ddls-with-workflows-0)

Let's associate our workflow with a Dynamic Data List (DDL) record. To learn
more about DDLs, visit [Using Web Forms and Dynamic Data
Lists](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/dynamic-data-lists-in-lifer-1)
in *Using Liferay Portal*. 

First we'll create a data definition that lets the user select a status value. 

1.  In Liferay Portal, go to *Control Panel* &rarr; *Dynamic Data Lists*.

2.  Click the *Manage Data Definitions* link, then *Add* a new data definition.

3.  In the *Fields* tab, drag and drop the *Select* field onto the canvas. 

    ![Figure 10.34: Creating data definitions for your DDL is a snap with Liferay's graphical drag and drop interface.](../../images/kaleo-35.png)

4.  In the *Settings* tab, double click the *Name* property to open the property
    editor--enter *status* as the value. 

5.  Edit the *Options* setting; give your *status* field option values of `fix`
    with label "Fix" and `not` with label "Do not fix". 

6.  Click *Save*. 

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
"status". When the script is invoked, if the value for the status field is
`not`, the value *No* is returned and our workflow transitions to our EndNode
state. Otherwise, the workflow transitions to our Developer task node. 

After you create the data definition, make sure you select the ticket process
workflow for our new Kaleo Forms process to use. *Save* the DDL. 

Now our DDL is set for use inside our ticket process workflow! Let's use the
Kaleo Forms portlet to test our new workflow definition! 

#### Using Kaleo Forms to run workflows [](id=lp-6-1-dgen08-using-kaleo-forms-to-run-workflows-0)

Let's use the Kaleo Forms portlet to invoke our workflow from Liferay Portal.
Deploy the Kaleo Forms portlet to your portal and add it to a page on your site.
You can learn how to use Kaleo Forms in the [Kaleo
Forms](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen06-kaleo-forms-0)
section of *Using Liferay Portal*; we'll demonstrate its use here by using it
with our ticket process workflow. 

1.  Create a new *Process* in Kaleo Forms; name it *Ticket Process*. 

2.  Select the *status* data definition we created earlier. 

3.  Add an *Initial Form* based on our *status* data definition. 

4.  Select our ticket process workflow. 

5.  Leave *Workflow Task Forms* unassigned for our demonstration. 

![Figure 10.35: To test the ticket process workflow, create a new Kaleo Forms *Process*--provide a name, entry definition, and workflow.](../../images/kaleo-41.png)

After saving, select the *Summary* tab in Kaleo Forms, click the *Submit New*
button, and select *Ticket Process*.

Now you can interact with the DDL and progress throughout the ticket process
using Kaleo Forms. Remember to sign in as Joe Bloggs to access the tasks
assigned to him. Joe Bloggs should also receive an email when the Project
Management node is activated. 

You successfully created a workflow definition and created a workflow process
within Liferay Portal! You're officially a workflow master (you can get your
framed certification at the front desk on your way out--tell them we sent you)! 

Have you noticed that there's a lot of depth to Liferay IDE? You can easily come
across difficult questions and run into very specific problems, but someone else
might have already solved your issue or answered your question. So where would
you go to find out? Don't reinvent the wheel, visit the [Liferay IDE Community
page](http://www.liferay.com/community/liferay-projects/liferay-ide/overview)!
On the *Forums* page, you can look up resolutions to specific errors and ask
questions. Be sure to fully describe any problems you have to ensure you get a
working answer. You can even track known issues from the *Issue Tracker* page. 

## Summary [](id=conclusi-3)

In this chapter we explored Liferay IDE, an extension of Eclipse IDE. You
learned to to install and set up Liferay IDE, then to create and deploy Liferay
projects, as well as import existing projects into Liferay IDE. Admit it--it's a
slick set of tools! We hope you take advantage of it in using Liferay Portal and
developing your Liferay plugins. 

After learning all about Liferay IDE we looked at managing workflows using Kaleo
Designer for Java in Liferay Developer Studio. With Developer Studio's dynamic
workflow diagram, you can visualize your workflow and create steps to ensure a
successful business process. The script and template editors give you a
convenient workspace to further enhance your workflow development environment.

Because Developer Studio has access to your custom business logic APIs and
Liferay Portal APIs, it's simple to customize your workflow definition. To get
your workflows running in Liferay, you can publish directly to your existing
Liferay server; the configuration process is quick and easy. With all of its
features, the Kaleo Designer for Java in Developer Studio is a powerful yet
simple tool. 

Next, let's get up-close and personal with Liferay's APIs!
<!-- Please leave the newline below this line -->

