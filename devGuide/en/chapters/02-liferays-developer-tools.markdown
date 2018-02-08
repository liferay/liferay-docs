# Working with Liferay's Developer Tools [](id=working-with-liferays-developer-tools-liferay-portal-6-2-dev-guide-02-en)

If you're anything like Liferay Portal's developers, you don't want to be
forced to work with one development technology. Our developers build Liferay
with the tools they prefer. That's why we strive to provide you with as much
flexibility as possible. You can develop your Liferay-based portal with tools
ranging in complexity from IDEs like Eclipse, Netbeans, or IntelliJ Idea, to
text editors like Notepad, Vim, or Emacs. You can write your persistence layer
directly using SQL and JDBC, or use advanced object-relational mapping
libraries like Hibernate or iBATIS. You get the idea. 

In this chapter, we'll explain how to set up a streamlined development
environment specifically designed for developing your Liferay Portal. Then we'll
consider how to develop plugins with other tools. We'll cover the following
topics along the way: 

- Developing Apps with Liferay IDE 
- Leveraging the Plugins SDK 
- Developing Plugins Using Maven 
- Deploying Your Plugins: Hot Deploy vs. Auto Deploy

Liferay's tool-agnosticism is great for experienced developers who understand
the strengths and weaknesses of different development technologies; it can be
overwhelming for newcomers, though. So we removed some of the options,
narrowing down your choices and forcing you to use a tool we like, right? No!
We actually added to the list of technologies you can use by developing
specific tools that soften the learning curve for Liferay plugin development,
and providing ways for you to use alternative tools. The most significant
Liferay-specific tool is Liferay IDE, a fully featured Integrated Development
Environment (IDE) based on Eclipse. There's also the Plugins Software
Development Kit (SDK), which is based on Apache Ant and can be used with any
editor or Integrated Development Environment you'd like. If you'd like, you can
also use Apache Maven archetypes; there are plenty of Liferay archetypes you can
use to develop your plugins. 

First let's consider the most robust tool for Liferay development, Liferay
IDE. 

## Developing Apps with Liferay IDE [](id=developing-apps-with-liferay-ide-liferay-portal-6-2-dev-guide-02-en)

Even if you're a grizzled veteran Java developer, if you're going to be doing a
lot of development for your Liferay Portal instance, consider using Liferay IDE.
When Liferay IDE is paired with the Plugins SDK or Maven and a Liferay runtime
environment, you have a one stop development environment where you can develop
your Liferay plugins, build them, and deploy them to your Liferay instance.

Liferay IDE is an extension for Eclipse IDE and supports development of plugin
projects for the Liferay Portal platform. You can install Liferay IDE as a set
of Eclipse plugins from an update site. The latest version of Liferay IDE
supports development of portlets, hooks, layout templates, themes, and Ext
plugins. To use Liferay IDE, you need the Eclipse Java EE developer package
using Indigo or a later version. 

In this section we'll show you how to install Liferay IDE, set up projects for
your applications, and deploy them to your portal. We'll get you started with
the basics of developing your Liferay application in Liferay IDE. The guide has
other chapters geared to each specific plugin type (e.g., the *portlets* chapter
covers portlet development, the *hooks* chapter covers hook development, etc.).
But, as we create a Liferay portlet project in this chapter, you'll get the gist
of how Liferay IDE helps you create all types of plugins easily. 

We'll also introduce you to Liferay's Service Builder. It helps you leverage
Hibernate's Object-Relational Mapping capabilities and gives you the capability
to automatically generate code to access Liferay object data. We'll point out
the various editor modes Liferay IDE provides for creating your data entities,
relating them, and building services around them. This section gives you a quick
tour, but we've dedicated an entire chapter later in this guide to give
Liferay's Service Builder the attention it deserves; check it out, we think
you'll be impressed. 

To install and set up Liferay IDE, follow the instructions in the first two
subsections below. If you're already using *Liferay Developer Studio* (the king
of Liferay's development tools), which comes with Liferay Portal Enterprise
Edition, skip to the section titled *Testing and Launching your Liferay
Server*--Liferay IDE comes preconfigured in Developer Studio. 

### Installing Liferay IDE [](id=installing-liferay-ide-liferay-portal-6-2-dev-guide-02-en)

Liferay IDE is an extension of the Eclipse IDE. You can install Eclipse
bundled with Liferay IDE or you can add it to an Eclipse instance. 

Liferay IDE requires the following software: 

- Java 6.0 JRE or greater. 
- One of the following Eclipse releases: 
    - Eclipse Kepler Java EE (4.3.x)
    - Eclipse Juno Java EE (4.2.x)
    - Eclipse Indigo Java EE (3.7.x)

If you don't already have Eclipse installed, you can install Liferay IDE bundled
with Eclipse. You can alternatively install Liferay IDE onto an existing
supported Eclipse installation. All Liferay IDE installation options are
explained in this section. 

#### Installing Liferay IDE Bundled with Eclipse [](id=installing-liferay-ide-bundled-with-ecli-liferay-portal-6-2-dev-guide-02-en)

Installing Liferay IDE and Eclipse from the same bundle is convenient and easy
to do.  

1.  Go to the
    [Liferay IDE](https://www.liferay.com/downloads/liferay-projects/liferay-ide) page.
    Under *Other Downloads*, select the Eclipse bundle for your operating system
    and click *Download*.

2.  Install Eclipse bundled with Liferay IDE by extracting its contents to a
    local folder. 

3.  To start Eclipse, execute the Eclipse executable file (e.g., `eclipse.exe`)
    from the installation folder. 

4.  Select *Window* &rarr; *Open Perspective* &rarr; *Other ...* &rarr;
    *Liferay* to use Liferay IDE. 

You've installed Eclipse and Liferay IDE together! 

You can alternatively install Liferay IDE onto an existing supported Eclipse
installation. Supported versions of Eclipse are available from the
[Eclipse](http://www.eclipse.org) website. 

To install Liferay IDE onto Eclipse, you can either access the Liferay IDE
update site from Eclipse or download a Liferay IDE archive file to access from
Eclipse. Installing Liferay IDE from the update site is the easiest way to add
it to Eclipse. 

#### Installing Liferay IDE from the Update Site onto Eclipse [](id=installing-liferay-ide-from-the-update-s-liferay-portal-6-2-dev-guide-02-en)

To install Liferay IDE and specify an Eclipse update URL, follow these steps: 

1.  Start Eclipse.

2.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

3.  In your browser, go to the
    [Liferay IDE](https://www.liferay.com/downloads/liferay-projects/liferay-ide)
    downloads page. Copy the URL of the update site you're interested in (stable
    or milestone). 

4.  In the *Work with* field, paste in the Liferay IDE update site URL and
    press *Enter*. 

5.  Make sure the Liferay IDE features are selected, then click *Next*. 

6.  After calculating dependencies, click *Next*, accept the license agreement,
    and click *Finish* to complete the installation.

7.  Restart Eclipse to verify that Liferay IDE is properly installed.

8.  After restarting Eclipse, go to *Help* &rarr; *About Eclipse*; if you see a
    Liferay IDE icon badge as in the screenshot below, it's properly installed. 

    ![Figure 2.1: Once you've installed Liferay IDE, you can find the Liferay IDE logo in Eclipse by clicking *Help* &rarr; *About Eclipse*.](../../images/ide-about-eclipse-liferay.png)

9.  Select *Window* &rarr; *Open Perspective* &rarr; *Other ...* &rarr;
    *Liferay* to use Liferay IDE. 

Alternatively, you can install Liferay IDE from a downloaded archive file. 

#### Installing Liferay IDE from an Archive File onto Eclipse [](id=installing-liferay-ide-from-an-archive-f-liferay-portal-6-2-dev-guide-02-en)

To install Liferay IDE from an archive file, follow these steps: 

1.  Go to the
    [Liferay IDE](https://www.liferay.com/downloads/liferay-projects/liferay-ide)
    downloads page. Under *Other Downloads*, select the *Liferay IDE [version]
    Archived Update-site* option and click *Download*.

2.  Start Eclipse.

3.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

4.  In the *Add Site* dialog, click the *Archive* button and browse to the
    location of the downloaded Liferay IDE archive file.

5.  Make sure the Liferay IDE features are selected, then click *Next*. 

6.  After calculating dependencies, click *Next*, accept the license agreement,
    and click *Finish* to complete the installation

7.  Restart Eclipse to verify that Liferay IDE is properly installed.

8.  Select *Window* &rarr; *Open Perspective* &rarr; *Other ...* &rarr;
    *Liferay* to use Liferay IDE. 

After restarting Eclipse, you can verify that Liferay IDE is installed by going
to *Help* &rarr; *About Eclipse* and finding the Liferay IDE icon badge. 

Congratulations on installing Liferay IDE! 

Let's set up Liferay IDE now that you have it installed. 

### Setting Up Liferay IDE [](id=setting-up-liferay-ide-liferay-portal-6-2-dev-guide-02-en)

Now that you have Liferay IDE installed, either from a downloaded zip file or
from the update site appropriate for your Eclipse version, you need to perform
some basic setup. This section describes the setup steps to perform so you can
develop your Liferay portal and test your customizations. 

Before setting up Liferay IDE, let's make sure you have all the appropriate
software packages installed. 

#### Requirements [](id=liferay-ide-alternative-requirements-liferay-portal-6-2-dev-guide-02-en-0)

Before setting up Liferay IDE, you need to have appropriate versions of Liferay
Portal, Liferay Plugins SDK and/or Maven, and Eclipse. Make sure you satisfy
these requirements before proceeding:

1.  Liferay Portal 6.0.5 or greater is downloaded and unzipped. 

2.  Liferay Plugins SDK 6.0.5 or greater is downloaded and unzipped, and/or any
    version of Maven is installed. If you're using the Plugins SDK, make sure
    the Plugins SDK version matches the Liferay Portal version. 

3.  You've installed an appropriate Eclipse IDE version for Java EE Development,
    and the Liferay IDE extension--see the *Installation* section if you haven't
    already done this. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** Earlier versions of Liferay
 (e.g., 5.2.x) are not supported by the Liferay IDE. 

---

Let's set up your Liferay Plugins SDK. 

#### Setting Up the Liferay Plugins SDK [](id=setting-up-liferay-plugins-sdk-liferay-portal-6-2-dev-guide-02-en)

Before you begin creating new Liferay plugin projects, a supported Liferay
Plugins SDK and/or Maven installation and Liferay Portal must be installed and
configured in your Liferay IDE. If you're thinking, "Wait a second, buster! You
told me earlier that the Plugins SDK and Maven could be used without Liferay
IDE!", then you're right. In the second half of this chapter, we'll explain how
to use the Plugins SDK and Maven on its own, with a text editor. Here, we
explain the easiest way to use the Plugins SDK: by running it from Liferay IDE.

1.  In Eclipse, open the *Installed Plugin SDKs* dialog box--from your *Windows*
    dropdown menu, click *Preferences* &rarr; *Liferay* &rarr; *Installed Plugin
    SDKs*.

2.  Click *Add* to bring up the *Add SDK* Dialog. 

3.  Browse to your Plugins SDK installation. The default name is the directory
    name; you can change it if you want. 

4.  Select *OK* and verify that your SDK was added to the list of *Installed
    Liferay Plugin SDKs*. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** You can have multiple Plugins
 SDKs configured. You can set the default Plugins SDK by checking its box in the
 list of *Installed Liferay Plugin SDKs*.

---

Let's set up your Liferay Portal Tomcat runtime and server. 

#### Liferay Portal Runtime and Server Setup [](id=liferay-portal-runtime-and-server-setup-liferay-portal-6-2-dev-guide-02-en)

You can run Liferay on any application server supported by Liferay Portal. Here,
for demonstration purposes, we'll set up our Liferay runtime on the Tomcat
application server. The steps you'd follow for any other supported application
server would be similar. For a list of Liferay bundles with other application
servers, please visit [Liferay's Downloads
page](https://www.liferay.com/downloads/liferay-portal/available-releases). For
instructions on installing Liferay manually on other application servers, please
refer to the [Installation and
Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
chapter of *Using Liferay Portal 6.2*. 

1.  In Eclipse, open the *Server Runtime Environments* dialog box--go to
    *Window* &rarr; *Preferences* &rarr; *Server* &rarr; *Runtime Environments*. 

    ![Figure 2.2: Liferay IDE provides wizards for creating new Liferay server runtime environments.](../../images/ide-prefs-runtime-env.png)

2.  Click *Add* to add a new Liferay runtime; find *Liferay v6.2 (Tomcat 7)*
    under the *Liferay, Inc.* category and click *Next*.

3.  Click *Browse* and select your `liferay-portal-6.2.x/tomcat-7.x` directory. 

4.  If you've selected the Liferay portal directory and a bundle JRE is present,
    it is automatically selected as the server's launch JRE. If no JRE bundle
    is present, then you must select the JRE to use for launch by clicking
    *Installed JREs...*. 

    ![Figure 2.3: If you have multiple JREs installed on your system, choose the one which should run Liferay.](../../images/ide-server-jre.png)

5.  Click *Finish*; you should see your Liferay portal runtime listed in
    *Preferences* &rarr; *Server* &rarr; *Runtime Environments*.

6.  Click *OK* to save your runtime preferences. 

7.  If you haven't created a server, create one now from the *Servers* view in 
    Liferay IDE; then you can test the server. Note that you need to be in the 
    Liferay perspective of Eclipse to see the Servers view. You can get there by 
    selecting *Window* &rarr; *Open Perspective* &rarr; *Other...* and then
    selecting *Liferay* from the list.

    ![Figure 2.4: If you haven't created a Liferay server yet, you can do so from the *Servers* tab in Liferay IDE.](../../images/ide-add-new-server.png)

8.  Scroll to the *Liferay, Inc* folder and select *Liferay v6.2... Server*.
    Choose the *Liferay v6.2...* runtime environment that you just created. 

Now your server is set up. Let's launch it and perform some tests! 

### Launching and Testing Your Liferay Server [](id=starting-your-liferay-server-liferay-portal-6-2-dev-guide-02-en)

Once your Liferay Portal Server is set up, you can launch it from the Servers
tab in Eclipse. You have a few options for launching and stopping the server
once it's selected in the Servers tab. 

From the *Servers* tab: 

- Click on the green *Start the Server* button to launch it (or use
  *Ctrl+Alt+R*). 
- Click on the red *Stop the Server* button to stop it (or use *Ctrl+Alt+S*).
  You'll only see this button if the server is running.
- Right click the server and select *Start*. 
- Right click on the server and select *Stop*. 

Once the server is launched, you can open Liferay portal home from the *Servers*
tab by right clicking your Liferay Tomcat server and selecting *Open Liferay
Portal Home*. 

Next, you'll learn to create new Liferay projects in Liferay IDE. 

### Creating New Liferay Projects [](id=creating-new-liferay-projects-liferay-portal-6-2-dev-guide-02-en)

Plugins for Liferay Portal must be created inside of a Liferay project. A
Liferay project is essentially a root directory with a standardized structure
containing the project's (and each of its plugins') necessary files. Since each
plugin type requires a different folder and file structure, let's create a
project to illustrate the process. Have you heard of the hip new social
networking site for noses, *Nose-ster*? Harold Schnozz, the site's founder,
wants to capitalize on the site's popularity by providing users with the ability
to organize local meetings and events. For instance, there's a really active
group of noses in Minneapolis, MN, who'd like to schedule a regional dance in
January, which they're calling the Frozen Boogie. Why does this concern us? Mr.
Schnozz has hired us to develop the necessary portlets to allow users to create
and view events on the Nose-ster portal. 

If you've been following our Liferay IDE configuration instructions, your
Plugins SDK and Liferay portal server have already been configured in Liferay
IDE. Now let's create a new Liferay plugin project in Liferay IDE. 

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
	default location is set to your Plugins SDK. If you'd like to change where 
	your plugin project is saved in your file system, uncheck the box and 
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
    Builder Portlet*, *Hook*, *Layout Template*, *Theme*, *Ext*, or *Web*. 
    Liferay IDE provides handy wizards for creating new Liferay projects. Our 
    demonstration project will hold service builder portlets for the Nose-ster 
    organization, so make sure *Service Builder Portlet* is selected.

    ![Figure 2.5: The wizard for creating a new service builder portlet project uses the information you specify to customize various configuration files in the new project.](../../images/ide-new-liferay-project.png)

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
            - `service.xml`
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
versions of Liferay on which your application is intended to run. Next, you need
to deploy your new plugin project to your Liferay Server. 

### Deploying New Liferay Projects to a Liferay Server [](id=deploying-new-liferay-projects-liferay-portal-6-2-dev-guide-02-en)

You have a plugin project, but you need to deploy it onto your Liferay
Server. The easiest way to deploy a plugin project is to drag the project from
the Package Explorer view onto your Liferay runtime in the Servers view.
Alternatively, you can use the following method:

1.  Select your new plugin project then right click the Liferay Server in
    the *Servers* tab. 

2.  Select *Add and Remove...*. 

3.  Select your plugin project and click *Add* to deploy it to the server. 

4.  Click *Finish*. 

Deploy your project. You should see the project get deployed to your Liferay
server; in the console you'll see a message, like the one below, indicating that
your new portlet is available for use. 

    INFO  [localhost-startStop-2][PortletHotDeployListener:490] 1 portlet for
    event-listing-portlet is available for use

Open *Liferay Portal Home* (`http://localhost:8080/` for a fresh Liferay
installation) and log in with your administrator account. If this is your first
time starting Liferay, follow the instructions in the setup wizard. For more
setup wizard details, see the [Using Liferay's Setup
Wizard](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-liferays-setup-wizard-liferay-portal-6-2-user-guide-15-en)
section of Chapter 15 in *Using Liferay Portal 6.2*.

Once you're logged in, click *Add* &rarr; *More*; expand the *Sample* category
and click the *Add* link next to your Event Listing application. Your *Event
Listing Portlet* shows on the page.

![Figure 2.6: Voila! You can add your brand new portlet (empty for now) to any page.](../../images/event-listing-portlet-with-empty-jsp-on-page.png)

Great, now you can create projects in Liferay IDE! Next, let's learn how to
create new plugins inside of existing projects in Liferay IDE. But before we do,
let's clean out the bare-bones portlet from our event-listing-portlet project. 

The portlet project wizard conveniently creates a default portlet named after
the project. However, for demonstration purposes, we want to begin creating
portlets with a clean slate in our project. Let's tear out the default Event
Listing portlet by removing its descriptors and it's JSP. 
    
1.  Open the portlet's `docroot/WEB-INF/liferay-display.xml` file
    and remove the `<portlet id="event-listing" />` tag.
    
2.  Open the `docroot/WEB-INF/liferay-portlet.xml` file and remove the
    `<portlet>...</portlet>` tags and code residing between those tags.
    
3.  Navigate to the `docroot/WEB-INF/portlet.xml` file and remove the
    `<portlet>...</portlet>` tags and code residing between those tags.	

4.  Remove the `docroot/view.jsp` file. 

Super! You've cleaned out the default portlet from the project. Now you're ready
to start creating the example plugins. 

### Creating Plugins [](id=creating-plugins-liferay-portal-6-2-dev-guide-02-en)

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
how to create both portlets in the event-listing-portlet project. 

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

    ![Figure 2.7: Liferay IDE's portlet creation wizard makes creating a portlet class is easy.](../../images/create-a-portlet-class.png)

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
    specify *html/locationlisting* as the JSP folder and flag the *Create
    resource bundle file* box.

    Here are the portlet deployment descriptor details to specify for the
    Location Listing portlet: 
    - **Name:** *locationlisting*
    - **Display name:** *Location Listing Portlet*
    - **Title:** *Location Listing Portlet*
    - **JSP folder:** *html/locationlisting*

    Click *Next*. 

    ![Figure 2.8: Liferay IDE's portlet creation wizard let's you specify the deployment descriptors for your portlets.](../../images/ide-specify-portlet-deployment-descriptors.png)

4.  The next window lets you specify portlet deployment descriptor details that 
    are specific to Liferay. You can set the file paths of your portlet's custom
    icon, main CSS file, and main JavaScript file. You can also specify a CSS
    class wrapper. Next, you can also choose the category for your portlet (it's
    categorized under *Sample* by default) and choose whether or not to add it
    to the *Control Panel* of your Liferay Portal. Accept the default, leaving
    the *Add to Control Panel* box unflagged. Click *Next*.

5.  The last step is to specify modifiers, interfaces, and method stubs to
    generate in the Portlet class. Accept the defaults and click *Finish*. 

Use the following steps to create the Event Listing portlet:

1.  Right-click your event-listing-portlet project &rarr; *New* &rarr; *Liferay
    Portlet*. Specify *EventListingPortlet* as the name of the portlet class,
    enter *com.nosester.portlet.eventlisting* as its Java package, and select
    *com.liferay.util.bridges.mvc.MVCPortlet* as it's superclass. 

    ![Figure 2.9: Creating portlet classes is simple with Liferay IDE's portlet creation wizard.](../../images/create-a-portlet-class-eventlistingportlet.png)

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
    for your portlet (it's categorized under *Sample* by default) and choose
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

Next, we'll show you how our Service Builder tool helps you generate your model,
persistence, and service layers. 

### Using the Service Builder Graphical Editor [](id=service-builder-graphical-editor-liferay-portal-6-2-dev-guide-02-en)

*Loose coupling* is a great principle to use when developing your applications.
By keeping all of your code for fetching data self contained in a service
layer, separate from the business logic of your application, you can more
easily swap out your entire service layer without disrupting the functionality
of your application.

Service Builder is a model-driven code generation tool that lets you define
custom object models called entities. Service Builder reads the contents of a
file you create called `service.xml` and automatically creates your
application's model, persistence, and service layers, freeing you to focus on
the higher level aspects of your application's code. 

Why should you use Service Builder? Because it lets different portlets access
the same data and application logic, creating an underlying framework that
supports a portal environment. If your database access code is buried in a
single application's code, it can't readily be shared with other applications,
and your efforts will be duplicated with each application you write. Service
Builder puts the generated code in a service `JAR` file inside of one plugin,
but it can be easily shared among all portlets. 

<!--I think my intro needs work. I'm trying to distill Rich's work on the
service builder chapter and need to continue the process. -->

To allow you more than one way to view and edit the `service.xml file`, Service
Builder gives you three modes to work in: 

- Overview mode provides an easy to use graphical interface in Liferay IDE
  where you can add to and edit the `service.xml` file. Overview mode also
  gives you a *Build Services* button to generate the service layer. 

- Diagram mode gives you a visualization of the relationships between service
  entities; it's often helpful to create your entities using diagram mode.

- Source mode displays the raw XML content of the `service.xml` file.
 
![Figure 2.10: Liferay IDE provides an Overview view of `service.xml` which allows you to edit the file by drilling down through a menu and editing form fields instead of editing the XML directly. It also provides a *Build Services* button for running Service Builder.](../../images/service-xml-entities-overview.png)

With Liferay IDE, generating your service layer is easy. First you'll create
`service.xml`, by selecting your project in the Package Explorer and then
selecting *File* &rarr; *New* &rarr; *Liferay Service Builder*. Service Builder
creates a `service.xml` file in your `docroot/WEB-INF` folder and displays
the file in overview mode. If you're following along with the
`event-listing-portlet`, you already have the `service.xml` file because we
created service builder portlet project during setup.

Our Service Builder chapter of this guide will lead you through filling out
`service.xml` to define the following:

- Global service information
- Service entities
- The attributes for each service entity
- Relationships between service entities
- Ordering of service entities instances
- Service entity finder methods

In the Service Builder chapter of this guide, we'll show you how our two custom
portlets, the Events Listing Portlet and the Location Listing Portlet, can be
developed more efficiently and modularly by using Service Builder. We'll
describe the contents of `service.xml` in detail and get you started using
Service Builder to develop your custom applications using our code generation
tool. And if *code generator* is a bad word to you, let us assure you that
Liferay always gives you full control over all your code, including code
generated by Service Builder. 

![Figure 2.11: Liferay IDE provides a diagram view of `service.xml` which provides a visual aid to understanding the relationships between service entities.](../../images/service-builder-relate-entities.png)

Once you've generated your `service.xml`, you can build services. When viewing
`service.xml` in overview mode, there's a button available at the top right hand
corner of the window. The button looks like a document with the numerical
sequence *010* in front of it. Alternatively, right click on your project and
select *Liferay* &rarr; *Build Services*. Once the process is finished, you'll
see a plethora of new Java classes under`docroot/WEB-INF/src` in your project
that Service Builder generated for you. Now you can get out there and write your
business logic, but make sure to check out the Service Builder chapter of this
guide for a thorough description of its capabilities. 

Now you know how to create projects and plugins from scratch and you know about
Service Builder's amazing time-saving capabilities. Let's learn how to import
existing projects into Liferay IDE. 

### Importing Existing Liferay Projects from a Plugins SDK [](id=importing-existing-liferay-projects-liferay-portal-6-2-dev-guide-02-en)

Do you want to import one or more Liferay projects into your Liferay IDE
workspace from a Liferay Plugins SDK? Liferay IDE makes it easy.  Don't worry
if the projects already contain `.project` or `.classpath` files, the process
we'll show you will still import them into your workspace. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** This section assumes that
 you've created projects with the Plugins SDK and are familiar with the
 directory structure used by the Plugins SDK. If you need to, check out the
 *Plugins SDK* section of this chapter; it comes right after this section. 

---

First, let's look at the steps for importing a single Liferay project from a
Plugins SDK project into your workspace. For these steps, we'll assume you
haven't yet configured your Plugins SDK in Liferay IDE: 

1.  In Liferay IDE, go to *File* &rarr; *New* &rarr; *Project...* &rarr;
    *Liferay* &rarr; *Liferay Project from Existing Source*. 

    You can invoke the same wizard from the Liferay shortcut bar; just click
    the *New* button and select *Liferay Project from Existing Source*.

    ![Figure 2.12: Instead of clicking *File* &rarr; *New* to create a new Liferay project from an existing source, you can click the button shown above from Liferay IDE's shortcut bar.](../../images/ide-new-proj-existing-source.png)

2.  In the *New Liferay Project* window, click the *Browse* button and 
    navigate to the project folder of the plugin you'd like to import. It should
    be a subfolder of one of the SDK's plugin type folders (e.g., portlets,
    hooks, themes, etc) or you'll get an error message stating
    that your Liferay project location is invalid.  

    On selecting the plugin project folder, the *Liferay plugin type* and
    *Liferay plugin SDK version* values are updated. If your Plugins SDK is
    outdated or you entered an incorrect project type, its field gets marked
    with an error. 

4.  Select the *Liferay target runtime* for the plugin project. If you don't
    have a Liferay Portal Runtime, use the *New...* button to create one now.  For
    more detailed instructions, see the section *Liferay Portal Runtime and Server
    Setup*, found earlier in this chapter. 

5.  Click *Finish* to complete the import. 

Any time you import a project into Liferay IDE, you can verify that it was
successfully configured as a Liferay IDE project by using the process outlined
in the section *Verifying Successful Project Import*, found later in this
chapter. 

Next, let's import multiple projects from a Liferay Plugins SDK you've already
set up in Liferay IDE. You can use these steps: 

1.  In Liferay IDE, go to *File* &rarr; *Import*... &rarr; *Liferay* &rarr;
    *Liferay Projects from Plugins SDK*. 

    ![Figure 2.13: To import projects from a Plugins SDK, choose *Liferay Projects from Plugins SDK* from the Import menu.](../../images/ide-import-from-plugin-sdk.png)

2.  In the *Import Liferay Projects* window, use the combo box to select the
    *Liferay Plugins SDK* from which you're importing plugins. 

    ---

    ![Note](../../images/tip-pen-paper.png) **Note:** If your SDK isn't
    configured in Liferay IDE (i.e., it's not in the dropdown list of the
    *Import Projects* window), use the *Configure* link to add one. To configure
    a Plugins SDK from the Installed SDKs window, just click *Add* and then
    browse to the Plugins SDK's root directory.

    ---

3.  Once you select your Plugins SDK in the combo box, the *Liferay Plugin SDK
    Location* and *Liferay Plugin SDK Version* fields are automatically filled
    in, as long as they're valid. Invalid entries are marked with an error. 

4.  The list of projects that are available for import are displayed in a list.
    Any projects already in the workspace are disabled. Projects available for
    import have an empty check box; select each project you'd like to import. 

5.  Select the Liferay runtime for the imported projects. If you don't have a
    Liferay runtime, can add one now with the *New...* button.

6.  Click *Finish*. 

You've imported your plugins into your workspace! Next, we'll discuss a
different scenario; converting existing Eclipse projects into Liferay projects. 

### Converting Existing Eclipse Projects into Liferay IDE Projects [](id=importing-existing-eclipse-projects-into-liferay-portal-6-2-dev-guide-02-en)

The steps outlined in the previous section are for importing Liferay projects
that aren't already in your Eclipse workspace. You can also import a
non-Liferay project in your Eclipse workspace (i.e., you can see it in
Eclipse's project explorer) and convert it to a Liferay project. Just follow
the steps below. 

1.  Move the project into a Liferay Plugins SDK if it is not already in one. 
    To import the project, select *File* &rarr; *Import...* and then follow the 
    import instructions that appear.

2.  In Eclipse's Project Explorer, right-click on the project and select
    *Liferay* &rarr; *Convert to Liferay plugin project*. 

    ---

    ![Note](../../images/tip-pen-paper.png) **Note:** If no convert action is
    available, either the project is already a Liferay IDE project or it is not
    faceted (i.e., Java and Dynamic Web project facets are not yet configured
    for it). For instructions on resolving these issues, see the section
    *Verifying Successful Project Import*, found later in this chapter. 

    ---

3.  In the *Convert Project* wizard, your project is selected and the SDK
    location and SDK version of your project is displayed.

    ![Figure 2.14: The *Convert Project* wizard detects your Plugin's SDK's the location and version.](../../images/ide-convert-plugin-project.png)

4.  Select the Liferay runtime to use for the project. If you don't have a
    Liferay Runtime defined, define one now by clicking *New...*. 

5.  Click *Finish*. 

Let's verify the success of your imports and ensure that they're properly
configured as Liferay IDE projects. 

### Verifying Successful Project Import [](id=verifying-successful-project-import-liferay-portal-6-2-dev-guide-02-en)

After importing projects into Liferay IDE, you'll want to verify that they
imported successfully and that they're properly configured as Liferay IDE
projects. Here's how you verify that your imports were successful: 

1.  Once the project is imported, you should see a new project inside Eclipse
    and it should have an "L" overlay image; the "L" is for Liferay! 

    ![Figure 2.15: Look for an "L" overlay image to verify that the import succeeded.](../../images/ide-liferay-project-w-overlay-image.png)

2.  Check the project's target runtime and facets to make sure it's configured
    as a *Liferay IDE* project:

    2.1. In the *Package Explorer*, right click *<project-name>* &rarr;
         *Properties* &rarr; *Targeted Runtimes*. 

    2.2. In the *Properties* window, click *Project Facets* and make sure that
    the Liferay plugin facets are properly configured. 

    ![Figure 2.16: Make sure that your project's Liferay plugin facets are properly configured.](../../images/ide-project-facets.png)

Great! You've confirmed that your import was successful; you can now make
revisions to your configured Liferay IDE project. Next, let's explore Liferay
IDE's Remote Server Adapter feature. 

### Using Liferay IDE's Remote Server Adapter [](id=using-liferay-ides-remote-server-adapter-liferay-portal-6-2-dev-guide-02-en)

The *Remote Server Adapter* is a feature that lets you deploy your Liferay
projects to a remote Liferay Portal server; it first became available in Liferay
IDE 1.6.2. Let's talk about when to use the Remote Server Adapter, then we'll
cover setting it up and using it in more detail. 

Your remote Liferay Portal instance needs to satisfy two requirements to use a
Remote Server Adapter: 

- It is version 6.1 or later. 
- It has the Remote IDE Connector application installed from Liferay
  Marketplace. The Remote IDE Connector contains the `server-manager-web` plugin
  for Liferay that provides an API for Liferay IDE's Remote Server Adapter to
  use for all its remote operations. 

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

- Download [Liferay IDE](http://www.liferay.com/downloads/liferay-projects/liferay-ide)
from Liferay's downloads page or download [Liferay Developer Studio](http://www.liferay.com/group/customer/products/developer-studio/1.6)
from the Customer Portal.
- Download [Liferay Portal CE or EE](http://www.liferay.com/downloads/liferay-portal/available-releases)
to your local development machine. 

You'll need to download [Liferay Portal CE or EE](http://www.liferay.com/downloads/liferay-portal/available-releases)
to your remote (test) server as well. 

Our demonstration uses the Remote Server Adapter on Liferay Portal bundled with
Apache Tomcat, but you can use the adapter with Liferay Portal running on any
application server Liferay Portal supports. Install Liferay Portal locally to
compile the plugins you develop. Install Liferay Portal on your remote test
server to host the plugins you'll deploy to it. 

---

 ![Important](../../images/tip-pen-paper.png) **Important:** Keep a record of
 your portal administrator login credentials (e.g., username/password) for your
 remote Liferay server; you'll need them to configure your connection from
 Liferay IDE to the remote Liferay server. 

---

Let's start by configuring the Remote Server Adapter. 

#### Configuring the Remote Server Adapter [](id=configuring-the-remote-server-adapter-liferay-portal-6-2-dev-guide-02-en)

You can use Liferay IDE's Remote Server wizard to configure the Remote
Server Adapter and install the Remote IDE Connector to your remote Liferay
instance. Alternatively, you can install the Remote IDE Connector to your remote
Liferay instance before configuring Liferay IDE's Remote Server Adapter. To
configure the Remote Server Adapter, use the following steps:

1.  Start your remote Liferay Portal instance and verify that you can log in as
    an administrator.

2.  Launch Liferay IDE and open the new server wizard by clicking *File* &rarr;
    *New* &rarr; *Other*; select *Server* in the Server category and click
    *Next*. Select *Remote Liferay Server (Liferay 6.2)* in the Liferay, Inc.
    category.

3.  Enter the IP address of the machine with the remote Liferay Portal instance
    into the *Server's host name* field. For the *Server name*, enter
    *Liferay@\[IP address\]*, then click *Next*. 

    ![Figure 2.17: Configure the remote Liferay server's information.](../../images/remote_server_adapter_wizard_1.png)

4.  The New Server wizard's next page directs you to define the Liferay
    Portal runtime stub. Doing so allows projects created for your remote server
    to use the runtime stub for satisfying JAR dependencies needed to compile
    various Liferay projects. Select the *Liferay bundle type* based on the
    version of your local Liferay bundle, browse to the *Liferay bundle
    directory* and click *Next*. 

5.  On the next page of the wizard, configure your connection to your remote
    Liferay instance: 
    - **Hostname:** Enter the IP address of your remote Liferay Portal instance's
    machine. 
    - **HTTP Port:** Enter the port it runs on (default: 8080). 
    - **Username** and **Password:** Enter your administrator credentials for the
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

    ![Figure 2.18: Click *Purchased* in the Marketplace section of the Control Panel to download and install the Remote IDE Connector application that you purchased.](../../images/remote_server_adapter_marketplace_2.png)

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

#### Using the Remote Server Adapter [](id=using-the-remote-server-adapter-liferay-portal-6-2-dev-guide-02-en)

Once your remote Liferay Portal server is correctly configured and your local
Liferay IDE is connected to it, you can begin publishing projects to it and
using it as you would a local Liferay Portal server. 

Here's how to publish plugin projects to your remote server in Liferay IDE: 

1.  Right click on the server and choose *Add and Remove...*. 

    ---

    ![Note](../../images/tip-pen-paper.png) **Note:** Make sure you have
    available projects configured in Liferay IDE. If not, you'll get an error
    message indicating there are no available resources to add or remove from
    the server. 

    ---

2.  Select the Liferay projects to publish to your remote server; click *Add* to
    add them to your remote server, then click *Finish*. Deployment begins
    immediately. If publication to the remote server was successful, your
    console displays a message that the plugin was successfully deployed!

3.  As you make changes to your plugin project, republish them so they take
    effect on the remote server. To set your remote server's publication
    behavior, double click your remote server in the *Servers* tab. You can
    choose to automatically publish resources after changes are made,
    automatically publish after a build event, or never to publish
    automatically. To manually invoke the publishing operation after having
    modified project files, right click on the server in the Servers view and
    select *Publish*. 

Next, let's examine the structure of Liferay's Plugins SDK. We'll also learn how
to use it independently of Liferay IDE.

<!-- Next we'll show you how to use tag library snippets in Liferay IDE. -->

<!-- ### Using Tag Library Snippets -->

## Leveraging the Plugins SDK [](id=leveraging-the-plugins-sdk-liferay-portal-6-2-dev-guide-02-en)

Java developers use a wide variety of tools and development environments.
Liferay makes every effort to remain tool agnostic, so you can choose the tools
that work best for you. If you don't want to use Liferay IDE, you can use
Liferay's Plugins Software Development Kit (SDK) all by itself. The Plugins SDK
is based on Apache Ant and can be used along with any editor or Integrated
Development Environment (IDE). 
 
In this section, we'll explain how to set up a Plugins SDK. We'll also discuss
its file structure and available Ant targets and share some best practices to
help you get the most out of the Plugins SDK. 

Setting up the Plugins SDK is easy. Let's get to it. 

### Installing the SDK [](id=installing-the-sdk-liferay-portal-6-2-dev-guide-02-en)

The first thing you should do is install Liferay Portal. If you haven't already
installed a Liferay bundle, follow the instructions in the [Installation and
Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
chapter of *Using Liferay Portal 6.2*. Many people use the Tomcat bundle for
development, as it's small, fast, and takes up fewer resources than most other
servlet containers. Although you can use any application server supported by
Liferay Portal for development, our examples use the Tomcat bundle. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** In Liferay Developer Studio,
 the SDK is already installed and ready to use. Liferay Portal Enterprise
 Edition (EE) comes with Liferay Developer Studio and much more (see [CE vs. EE](http://www.liferay.com/downloads/liferay-portal/overview)). Download a
 [free trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
 of Liferay Portal EE today. 

---

Installation steps:

1.   Download The Plugins SDK from our web site at
     [http://www.liferay.com](http://www.liferay.com). 

     Click the *Downloads* link at the top of the page. 

     From the *Liferay Portal 6.2 Community Edition* section, select the
     *Plugins SDK* option. 

     Click *Download*. 

2.  Unzip the archive to a folder of your choosing. Because some operating
    systems have trouble running Java applications from folders with names
    containing spaces, avoid using spaces when naming your folder.

    On Windows, to build a plugin's services (see [Generating Your Service Layer](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generating-your-service-layer-liferay-portal-6-2-dev-guide-04-en)),
    the Plugins SDK and Liferay Portal instance must be on the same drive. E.g.,
    if your Liferay Portal instance is on your `C:\` drive, your Plugins SDK
    must also be on your `C:\` drive in order for Service Builder to be able to
    run successfully. 

---

 ![Tip](../../images/tip-pen-paper.png) **Tip**: By default, Liferay Portal
 Community Edition comes bundled with many plugins. It's common to remove them
 to speed up the server start-up. Just navigate to the
 `liferay-portal-[version]/tomcat-[tomcat-version]/webapps` directory and delete
 all its subdirectories except for `ROOT`, `marketplace-portlet`, and
 `tunnel-web`. 

---

Now that you've installed the Plugins SDK, let's configure Apache Ant for use in
developing your plugins. 

#### Ant Configuration [](id=ant-configuration-liferay-portal-6-2-dev-guide-02-en)

Building projects in the Plugins SDK requires that you install Ant (version 1.8
or higher) on your machine. Download the latest version of Ant from
[http://ant.apache.org/](http://ant.apache.org/). Extract the archive's contents
into a folder of your choosing. 

Now that Ant is installed, create an `ANT_HOME` environment variable to capture
your Ant installation location. Then put Ant's `bin` directory (e.g.,
`$ANT_HOME/bin`) in your path. We'll give you examples of doing this on Linux
(Unix or Mac OS X) and Windows. 

On Unix-like systems (Linux or Mac OS X), if your Ant installation directory is
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
    - **Variable name:** *ANT_HOME*
    - **Variable value:** `[Ant installation path]` (e.g.,
    `C:\Java\apache-ant-[version]`)

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

#### Plugins SDK Configuration [](id=plugins-sdk-configuration-liferay-portal-6-2-dev-guide-02-en)

Now we have the proper tools set up. Next, we need to configure our Plugins SDK.
It needs to know the location of our Liferay installation so it can compile
plugins against Liferay's JAR files and can deploy plugins to your Liferay
instance. The Plugins SDK contains a `build.properties` file that contains the
default settings about the location of your Liferay installation and your
deployment folder. You can use this file as a reference, but you shouldn't
modify it directly (In fact, you will see the message "DO NOT EDIT THIS FILE" at
the top if you open it). In order to override the default settings, create a new
file named `build.[username].properties` in the same folder, where `[username]`
is your user ID on your machine. For example, if your user name is jbloggs, your
file name would be `build.jbloggs.properties`. 

Edit this file and add the following lines: 

    app.server.type=[the name build.properties uses for your application server type]
    app.server.parent.dir=[the directory containing your Liferay bundle]
    app.server.tomcat.dir=[the directory containing your application server]

If you are using Liferay Portal bundled with Tomcat 7.0.42 and your
bundle is in your `C:/liferay-portal-6.2` folder, you'd specify the
following lines:

    app.server.type=tomcat
    app.server.parent.dir=C:/liferay-portal-6.2
    app.server.tomcat.dir=${app.server.parent.dir}/tomcat-7.0.42

Since we're using the Tomcat application server, we specified `tomcat` as our
app server type and we specified the `app.server.tomcat.dir` property. See the
Plugins SDK's `build.properties` for the name of the app server property that
matches your app server. 

Save the file. Next, let's consider the structure of the Plugins SDK. 

### Structure of the SDK [](id=plugins-sdk-directory-structure-liferay-portal-6-2-dev-guide-02-en)

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK: 

- `liferay-plugins-<version>/` - Plugins SDK root directory. 
    - `clients/` - client applications directory. 
    - `dist/` - archived plugins for distribution and deployment. 
    - `ext/` - Ext plugins directory. See [Advanced Customization with Ext Plugins](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/advanced-customization-with-ext-plugins-liferay-portal-6-2-dev-guide-12-en). 
    - `hooks/` - hook plugins directory. See [Customizing and Extending Functionality with Hooks](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/customize-and-extend-functionality-hooks-liferay-portal-6-2-dev-guide-en). 
    - `layouttpl/` - layout templates directory. See [Creating Liferay Layout Templates](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-liferay-layout-templates-liferay-portal-6-2-dev-guide-09-en).  
    - `lib/` - commonly referenced libraries. 
    - `misc/` - development configuration files. Example, a source code
      formatting specification file. 
    - `portlets/` - portlet plugins directory. See [Developing Portlet Applications](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-portlet-applications-liferay-portal-6-2-dev-guide-03-en). 
    - `themes/` - themes plugins directory. See [Creating Liferay Themes and Layout Templates](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-themes-and-layout-templates-liferay-portal-6-2-dev-guide-09-en). 
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
For instance, a new portlet called *greeting-portlet* would reside in
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
  
You're now familiar with the Plugins SDK's structure and Ant targets. Next,
let's create a plugin using the Plugins SDK from a terminal environment. 

### Creating Plugins with Liferay SDK [](id=creating-plugins-with-liferay-sdk-liferay-portal-6-2-dev-guide-02-en)

You saw how easy it is to create and deploy Liferay plugin projects using
Liferay IDE with an installed Liferay SDK. If you don't want to use Eclipse,
you can still leverage the SDK to create your Liferay plugins. 

Let's pretend that Harold Schnozz, Nose-ster's founder, despises Eclipse. We
may not agree with his objections, but since he's paying us good money to
create portlets for his organization, we'll make do without the benefit of
Liferay IDE. 

Navigate to the `portlets` folder of your Plugins SDK and follow these steps: 

1.  On Linux and Mac OS X, enter

        ./create.sh event-listing-portlet "Event Listing"

2.  On Windows, enter

        create.bat event-listing-portlet "Event Listing"

Your terminal will display a BUILD SUCCESSFUL message from Ant, and a new
folder with your portlet plugin's directory structure will be created inside of
the `portlets` folder in your Plugins SDK. This is where you'll work to
implement your own functionality. Notice that the Plugins SDK automatically
appends "-portlet" to the project name when creating its directory.

![Figure 2.19: Creating the Event Listing Portlet.](../../images/02-terminal-build-success-plugins-sdk-project-create.png)

---

 ![Tip](../../images/tip-pen-paper.png) **Tip**: If you are using a source
 control system such as Subversion, CVS, Mercurial, Git, etc., this would be a
 good moment to do an initial check-in of your changes. After building the
 plugin for deployment, several additional files will be generated that should
 *not* be handled by the source control system. 

---

Now you have a Liferay portlet project for our Event Listing portlet. We still
need to deploy the project to our Liferay Server. With Liferay IDE you had
multiple options: drag and drop your project onto the server, or right click
the server and select *Add and Remove...*. It's almost as easy using an ant
target. Simply open a terminal window in your `portlets/event-listing-portlet`
directory and enter

    ant deploy

A BUILD SUCCESSFUL message indicates your portlet is now being deployed. If you
switch to the terminal window running Liferay, within a few seconds you should
see the message `1 portlet for my-greeting-portlet is available for use`. If
not, double-check your configuration. 

In your web browser, log in to the portal as explained earlier. Hover over
*Add* at the top of the page and click on *More*. Select the *Sample* category,
and then click *Add* next to *Event Listing*. Your portlet appears in the
page below. 

Next, let's consider some best practices for developing plugins using the SDK. 

### Best Practices [](id=plugins-sdk-best-practices-liferay-portal-6-2-dev-guide-02-en)

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

## Developing Plugins Using Maven [](id=developing-plugins-using-maven-liferay-portal-6-2-dev-guide-02-en)

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

### Installing Maven [](id=installing-maven-liferay-portal-6-2-dev-guide-02-en)

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi). We
recommend putting your Maven installation's `bin` directory in your system's
`$PATH`, so you can run the Maven executable (`mvn`) easily from your command
prompt. 

Let's learn about the types of repositories you can use with Maven projects. 

### Using Maven Repositories [](id=using-maven-repositories-liferay-portal-6-2-dev-guide-02-en)

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

#### Managing Maven Repositories [](id=managing-maven-repositories-liferay-portal-6-2-dev-guide-02-en)

You'll frequently want to share Liferay artifacts and plugins with teammates, or
manage your repositories using a GUI. For this, you'll want Nexus OSS. It's a
Maven repository management server that facilitates creating and managing
release servers, snapshot servers, and proxy servers. Release servers hold
software that has met the software provider's criteria for planned features and
quality. Snapshot servers hold software that is in a state of development. If
you're not interested in using Nexus as a repository management server, feel
free to skip this section. 

Let's create a Maven repository using Nexus OSS. If you haven't already,
download Nexus OSS from
[http://www.sonatype.org/nexus/](http://www.sonatype.org/nexus/) and follow
instructions at
[http://www.sonatype.com/books/nexus-book/reference/\_installing\_nexus.html](http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html)
to install and start it. 

To create a repository using Nexus, follow these steps: 

1.  Open your web browser; navigate to your Nexus repository server (e.g.,
    [http://localhost:8081/nexus](http://localhost:8081/nexus)) and log in. The
    default username is `admin` with password `admin123`. 

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*. 

    ![Figure 2.20: Adding a repository to hold your Liferay artifacts is easy with Nexus OSS.](../../images/maven-nexus-create-repo.png)

    ![note](../../images/tip-pen-paper.png) **Note:** To learn more about each
    type of Nexus repository, read Sonatype's *Managing Repositories* at
    [http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html](http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html).

3.  Enter repository properties appropriate to the access you'll provide its
    artifacts. We're installing release version artifacts into this repository,
    so specify *Release* as the repository policy. Below are examples of
    repository property values: 
    - **Repository ID:** *liferay-releases*
    - **Repository Name:** *Liferay Release Repository*
    - **Provider:** *Maven2*
    - **Repository Policy:** *Release*

4.  Click *Save*.

You just created a Maven repository accessible from your Nexus OSS repository
server! Congratulations! 

Let's create a Maven repository to hold snapshots of each Liferay plugin we
create. Creating a *snapshot* repository is almost identical to creating a
*release* repository. The only difference is that we'll specify *Snapshot* as
its repository policy:

1.  Go to your Nexus repository server in your web browser.

2.  Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*.

3.  Specify repository properties like the following:
    - **Repository ID:** *liferay-snapshots*
    - **Repository Name:** *Liferay Snapshot Repository*
    - **Provider:** *Maven2*
    - **Repository Policy:** *Snapshot*

4.  Click *Save*.

Voila! You not only have a repository for your Liferay releases (i.e.,
`liferay-releases`), you also have a repository for your Liferay plugin
snapshots (i.e., `liferay-snapshots`). 

Let's configure your new repository servers in your Maven environment so you can
install artifacts to them. 

#### Configuring Local Maven Settings [](id=configuring-local-maven-settings-liferay-portal-6-2-dev-guide-02-en)

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

1.  Navigate to your `[USER_HOME]/.m2/` directory. Create that directory if it
    doesn't yet exist. 

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

###  Installing Required Liferay Artifacts [](id=installing-required-liferay-artifacts-liferay-portal-6-2-dev-guide-02-en)

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

#### Installing Artifacts from a Zip File [](id=installing-artifacts-from-a-zip-file-liferay-portal-6-2-dev-guide-02-en)

Whether you're building plugins for Liferay EE or CE, you can get the Liferay
artifacts by manually installing them from a zip file.

Let's download the Liferay EE artifacts first.

**Downloading a Liferay EE Artifact Zip File:**

You can download the Liferay EE artifacts package from Liferay's Customer
Portal. Just follow these steps: 

1.  Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2.  Go to the Customer Portal by clicking your profile picture in the Dockbar
    and selecting *Customer Portal*. 

3.  Select *Liferay Portal* from the *Downloads* panel.

4.  Inside *Filter by:*, select the appropriate Liferay version in the first
    field and select the *For Developers* value in the second field.

    ![Figure 2.21: You can download the Liferay Maven EE artifacts from the Liferay Customer Portal.](../../images/maven-customer-portal.png)

5.  Click *Download* under the desired *Liferay Portal [Version] Maven*. 

    The Liferay Maven EE artifacts package downloads to your machine.

**Downloading a Liferay CE Artifact Zip File:**

You can download Liferay CE artifacts from SourceForge by following these steps:

1.  Open your browser to *Liferay Portal* on SourceForge &rarr;
    [http://sourceforge.net/projects/lportal/files/Liferay Portal/](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).

2.  Select the Liferay version for which you need Maven artifacts. For example,
    if you need Maven artifacts for Liferay Portal 6.2.0 CE GA1, select version
    *6.2.0 GA1*. 

    ![Figure 2.22: After selecting the Liferay version, simply select the Liferay Portal Maven zip file to download.](../../images/maven-select-download.png)

3.  Select the appropriate zip file. The zip files use naming convention
    `liferay-portal-maven-[version]-[date].zip`. 

    The Liferay Maven CE artifacts package downloads to your machine.

You can extract the Liferay artifacts package zip file anywhere you like.
The zip file not only includes the Liferay artifacts, but also includes a
convenient script to install and deploy the artifacts to your repositories. 

If you're using Liferay CE and you want the latest pre-release artifacts from
the Liferay CE source repository, you can get them--but you'll have to build
them yourself. Don't worry, it's easy. We'll show you how to build the artifacts
from Liferay's source code next. 

#### Building CE Maven Artifacts from Source [](id=building-ce-maven-artifacts-from-source-liferay-portal-6-2-dev-guide-02-en)

Downloading the Liferay Maven artifacts is useful if you're interested in using
the artifacts for a particular release. However, if you'd like to use the very
latest Liferay CE Maven artifacts, you can build them from source. To build the
latest Liferay CE Maven artifacts from source, follow these steps:

1.  Navigate to your local Liferay Portal CE source project. If you don't
    already have a local Liferay Portal CE source project on your machine, you
    can fork the Liferay Portal CE Github repository, found at
    [http://github.com/liferay/liferay-portal](http://github.com/liferay/liferay-portal),
    and clone it your machine.

2.  Create an `app.server.[user name].properties` file in your local Liferay
    Portal CE source project root directory. Specify the following properties in
    it:

        app.server.type=[your application server's type. Look up your app
        server's type in the app.server.properties file in the same directory.]
        app.server.parent.dir=[your application server's parent directory]
        app.server.[type].dir=[your application server's directory]

    For example, if you're using Liferay with Apache Tomcat 7.0.42 bundled
    in your `c:/bundles` folder, you'd specify the following
    properties:

        app.server.type=tomcat
        app.server.parent.dir=c:/liferay-portal-6.2
        app.server.tomcat.dir=${app.server.parent.dir}/tomcat-7.0.42

    Of course, you should specify the values appropriate to your application
    server and your bundle/parent directory. Note that your
    `app.server.[type].dir` directory doesn't need to exist yet; it is created
    by invoking an Ant target in the next step. 

3.  Run `ant -f build-dist.xml unzip-[app server name]` to unzip a copy of your
    preferred application server to the specified directory.

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
    the Liferay Portal WAR file by running 

        ant -f build-dist.xml all zip-portal-war

6.  Deploy the Liferay artifacts to your Maven repository by running 

        ant -f build-maven.xml deploy-artifacts

    If you want the Liferay artifacts to be installed locally but don't have a
    remote Maven repository or don't want the artifacts to be remotely deployed,
    you can run the install target instead of the deploy
    target: `ant -f build-maven.xml install-artifacts`. The target installs the
    Liferay artifacts you built to your local .m2 repository (e.g., to your
    `[USER_HOME]/.m2/` directory). 

---

![note](../../images/tip-pen-paper.png) **Warning:** During the process of
 packaging up the `javadoc.jar` files for your Liferay artifacts, your machine
 may experience sluggish performance or an insufficient amount of Java heap
 space. There are two solutions to this problem:
 
- *Increase the memory available for the Javadoc packaging process:*
  Navigate to `[Liferay home]/build.xml` and search for the `javadoc` target.
  Find the `maxmemory` property and increase it as desired.
- *Skip the Javadoc packaging process:* Navigate to
  `[Liferay home]/build-maven.xml` and find the `prepare-maven` target. Within
  this target, comment out the call to the `jar-javadoc` target, like below:

		<!-- <antcall target="jar-javadoc" /> -->
		
---

Great! You now know how to build Liferay CE artifacts from your local portal
source tree. As an alternative to building the artifacts, you may have
downloaded Liferay release artifacts as a zip file. Once you've downloaded
them, you'll need to install them to your Maven repository. We'll show you how
to do that in the next section. 

#### Installing Artifacts to a Repository [](id=installing-artifacts-to-a-repository-liferay-portal-6-2-dev-guide-02-en)

Let's install the Liferay release artifacts to your local Maven repository. 

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

#### Deploying Artifacts to a Repository [](id=deploying-artifacts-to-a-repository-liferay-portal-6-2-dev-guide-02-en)

You may find it worthwhile to share your Liferay artifacts with teammates.

Here's how you do it:

1.  Make sure you've created a `liferay-releases` repository server to hold the
    Liferay Maven artifacts. If you haven't, see the *Managing Maven
    Repositories* section for instructions. 

2.  Make sure the repository that will hold your Liferay artifacts is specified
    as a server in Maven's `settings.xml` file. If it isn't, see the
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

3.  Navigate to the directory holding the Liferay artifacts you want to install
    to your Maven repository. If you unzipped the artifacts from a downloaded
    zip file, the artifacts are in a directory that follows the naming
    convention `liferay-portal-maven-[version]`. If you built the artifacts
    yourself, they'll be in a time-stamped directory in your `liferay-portal`
    repository directory. 

4.  Create a `build.[user name].properties` file in this directory. In the new
    properties file, specify values for the properties `lp.maven.repository.id`
    and `lp.maven.repository.url`. These refer to your repository's ID and URL,
    respectively. 

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

To verify your artifacts are deployed, navigate to the *Repositories* page of
your Nexus OSS server and select your repository. A window appears below
displaying the Liferay artifacts now deployed to your repository.

![Figure 2.23: Your repository server now provides access to your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

Congratulations! You've downloaded the Liferay artifacts, installed them to your
local repository, and deployed them to your release repository server for
sharing with teammates. 

Did you know that Liferay has its own Maven repository for artifacts? Let's
learn how to install artifacts from Liferay's repository next.

#### Installing Artifacts from the Liferay Repository [](id=installing-ee-artifacts-from-the-liferay-liferay-portal-6-2-dev-guide-02-en)

<!-- If/when the Liferay repository becomes an EE-only artifact repository,
include "EE" in front of "Artifacts in the above heading. In addition, remove
CE references from this section. Jim --> 

If you'd like to access Liferay's CE artifacts without downloading and
installing a `.zip` file, you can configure Maven to automatically
download and install them from Liferay's own repository:
[https://repository.liferay.com](https://repository.liferay.com). The first time
you use Maven to compile a Liferay plugin project, Maven automatically downloads
the required artifacts from the Liferay Maven repository into your local
repository, if they're not found in your local repository or any of your
configured repository servers. You'll see it happen when you package your
Liferay plugins. 

In order to access artifacts from the Liferay Maven repository, you'll need to
configure Maven to look for them there.

First, specify the Liferay Repository's credentials in your project's parent
`pom.xml` file as follows:

    <repositories>
        <repository>
            <id>liferay-public-releases</id>
            <name>Liferay Public Releases</name>
            <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>liferay-public-releases</id>
            <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>

---

![note](../../images/tip-pen-paper.png) **Important:** Do not leave the Liferay
repository configured when publishing artifacts to Maven Central. You must
comment out the Liferay Repository credentials when publishing your artifacts.

--- 

Next, when interacting with the Liferay Repository, you'll need to use
specialized commands to access it. Use the following command to access the CE
repo:

    mvn archetype:generate -DarchetypeCatalog=https://repository.liferay.com/nexus/content/repositories/liferay-public-releases

<!-- You'll notice I provided the specialized command here and in the portlet
archetype example. Once everything is synced correctly and we're not solely
relying on the Liferay repo, we can remove the specialized command from the
portlet archetype exercise and only specify this way here. -Cody -->

Liferay makes its artifacts available on Maven's Central Repository as well. As
with using Liferay's Maven repository, the Maven's Central Repository enables
you to automatically download and install Liferay Maven artifacts. Let's see
how. 

#### Installing Artifacts from the Central Repository [](id=install-ce-artifacts-from-central-repo-liferay-portal-6-2-dev-guide-02-en)

---

![note](../../images/tip-pen-paper.png) **Important:** Currently, the *GA1*
Liferay Maven artifacts are not available in Maven's Central Repository. The
Central Repository is only synced to Liferay's *6.2.0-RC5* release. As a current
workaround to accessing Liferay's *6.2 GA1* artifacts, please reference the
*Installing Artifacts from the Liferay Repository* section for setup.

---

Liferay offers an option for automatic download and installation of Liferay
Maven artifacts publicly available on the *Central Repository*, located at
[http://search.maven.org/#search|ga|1|liferay
maven](http://search.maven.org/#search|ga|1|liferay%20maven). They are updated
with Liferay releases (e.g., 6.0.6, 6.1.1, 6.1.2, 6.1.20, 6.1.30, 6.2.0-RC5,
etc.). The first time you use Maven to compile a Liferay plugin project, Maven
automatically downloads the required artifacts from the Central Repository into
your local repository if they're not found in your local repository or any of
your configured repository servers. You'll see it happen when you package your
Liferay plugins. 

Now that we have our Maven artifacts set up, let's configure Liferay IDE with
Maven.

### Using Liferay IDE with Maven [](id=use-liferay-ide-with-maven-liferay-portal-6-2-dev-guide-02-en)

Wouldn't it be nice if you could manage your Liferay Maven projects from Liferay
IDE? You can! Liferay IDE 2.0 introduces the Maven project configurator
(`m2e-liferay`), or the added support of configuring Maven projects as full
Liferay IDE projects. Let's explore what the Maven project configurator does,
how to install it, and how to install its dependencies. 

#### Installing Maven Plugins for Liferay IDE [](id=install-maven-plugins-for-liferay-ide-liferay-portal-6-2-dev-guide-02-en)

In order to properly support Maven projects in the IDE, you first need a
mechanism to recognize Maven projects as Liferay IDE projects. IDE projects are
recognized in Eclipse as faceted web projects that include the appropriate
Liferay plugin facet. Therefore, all IDE projects are also Eclipse web projects
(faceted projects with web facet installed). In order for the IDE to recognize
the Maven project and for it to be able to leverage Java EE tooling features
(e.g., the *Servers* view) with the project, the project must be a flexible web
project. Liferay IDE requires that the following Eclipse plugins be installed
in order for Maven projects to meet these requirements: 

- `m2e-core` (Maven integration for Eclipse)
- `m2e-wtp` (Maven integration for WTP)

When you install the `m2e-liferay` plugin, these dependencies are installed by
default. We'll flesh out the installation process soon, but first, let's get a
deeper understanding of how these plugins work to give us our IDE/Maven
compatibility. 

The `m2e-core` plugin is the standard Maven tooling support for Eclipse. It
provides dependency resolution classpath management and an abstract project
configuration framework for adapters. Also, in order for a Liferay Maven project
to be recognized as a flexible web project, the Maven project must be mapped to
a flexible web project counterpart. The `m2e-wtp` plugin. provides project
configuration mapping between the Maven models described in the Maven project's
POMs and the corresponding flexible web project supported in Eclipse. With these
integration features in place, the only remaining requirement is making sure
that the `m2e-core` plugin can recognize the extra lifecycle metadata mappings
necessary for supporting Liferay's custom goals. Let's break down the lifecycle
mappings just a bit to get a better understanding of what this means. 

Both Maven and Eclipse have their own standard build project lifecycles that are
independent from each other. Therefore, for both to work together and run
seamlessly within Liferay IDE, you need a lifecycle mapping to link both
lifecycles into one combined lifecycle. Normally, this would have to be done
manually by the user. However, with the `m2e-liferay` plugin, the lifecycle
metadata mapping and Eclipse build lifecycles are automatically combined
providing a seamless user experience. The lifecycle mappings for your project
can be viewed by right-clicking your project and selecting *Properties* &rarr;
*Maven* &rarr; *Lifecycle Mapping*. 

![Figure 2.24: View your project's lifecycle mappings to verify successful plugin execution.](../../images/maven-lifecycle-mapping.png)

When first installing Liferay IDE, the installation startup screen lets you
select whether you'd like to install the Maven plugins automatically. Did you
miss this during setup? No problem! To install the required Maven plugins,
navigate to *Help* &rarr; *Install New Software*. In the *Work with* field,
insert the following: `Liferay IDE repository -
http://releases.liferay.com/tools/ide/latest/milestone/`. 

If the `m2e-liferay` plugin does not appear, this means you already have it
installed. To verify, uncheck the *Hide items that are already installed*
checkbox and look for `m2e-liferay` in the list of installed plugins. Also, if
you'd like to view everything that is bundled with the `m2e-liferay` plugin,
uncheck the *Group items by category* checkbox. 

![Figure 2.25: You can install the `m2e-liferay` plugin by searching for software on Liferay IDE's repository.](../../images/m2e-liferay-installation.png)

Awesome! The required Maven plugins are installed and your IDE instance is ready
to be mavenized! Next, let's learn how to configure an existing Maven project.

#### Configuring your Liferay Maven Project [](id=configure-liferay-maven-project-liferay-portal-6-2-dev-guide-02-en)

Now your Liferay IDE instance is Maven-ready and you have an existing Maven
project. Let's investigate what is going on under the hood and configure your
project. Note, if you'd like to learn how to create a new Maven project in the
IDE, visit the *Creating Liferay Plugins with Maven* section. Furthermore, you
can import an existing Maven project by navigating to *File* &rarr; *Import*
&rarr; *Maven* and selecting the location of your Maven project source code. 

---

![note](../../images/tip-pen-paper.png) **Note:** Due to the lifecycle mapping
of Eclipse and Maven, it is unsafe to manually insert or overwrite the
`.classpath` and `.project` files and `.settings` folder. IDE automatically
generates these files when a project is imported and updates them appropriately.

---

The `m2e-core` plugin delegates your Liferay Maven plugin's project
configuration to the `m2e-liferay` project configurator. The `m2e-wtp` project
configurator then converts your Liferay WAR package into an Eclipse flexible web
project. Next, the `m2e-liferay` configurator looks for the Liferay Maven plugin
to be registered on the POM effective model for WAR type packages. If no Liferay
Maven plugin is configured on the effective POM for the project, project
configuration ceases. If the plugin is configured, the project configurator
validates your project's configuration, checking it's POM, parent POM, and the
project's properties. The configurator detects invalid properties and reports
them as errors in the IDE's POM editor. There are a list of key properties that
your project must specify in order for it to become a valid Liferay IDE project.
The next section titled *Using a Parent Plugin Project* identifies these
properties and explains how they are used. 

There are various ways to satisfy these properties--the Maven profile in the
Global `settings.xml` file (recommended), in the User `settings.xml` file, in
the parent `pom.xml`, or in the project `pom.xml` directly. You can think of
these choices as a hierarchy for how your Maven plugins receive their
properties. The project `pom.xml` overrides the parent `pom.xml`, the parent
`pom.xml` overrides the User `settings.xml` file, and the User `settings.xml`
file overrides the Global `settings.xml` file. 

**Global `settings.xml`:** provides configuration for all plugins belonging to
all users on a machine. This file resides in the
`${MAVEN_HOME}/conf/settings.xml` directory.

**User `settings.xml`:** provides configuration for all plugins belonging to a
single user on a machine. This file resides in the
`${USER_HOME}/.m2/settings.xml` directory.

**Parent `pom.xml`:** provides configuration for all modules in the parent
project.

**Project `pom.xml`:** provides configuration for the single plugin project.

Note that if a profile is active from your `settings.xml`, its values will
override your properties in a POM. If you'd like to specify the properties in a
POM, see the next section *Using a Parent Plugin Project* for more details. 

Here's an example of what a Maven profile looks like inside the `settings.xml`
file.

    <profiles>
        <profile>
            <id>sample</id>
                <properties>
                    <plugin.type>portlet</plugin.type>
                    <liferay.version>6.2.0</liferay.version>
                    <liferay.maven.plugin.version>6.2.0</liferay.maven.plugin.version>
                    <liferay.auto.deploy.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\deploy</liferay.auto.deploy.dir>
                    <liferay.app.server.deploy.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\webapps</liferay.app.server.deploy.dir>
                    <liferay.app.server.lib.global.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\lib\ext</liferay.app.server.lib.global.dir>
                    <liferay.app.server.portal.dir>E:\liferay-portal-tomcat-6.2.0-ce-ga1\tomcat-7.0.42\webapps\ROOT</liferay.app.server.portal.dir>
                </properties>
        </profile>
    </profiles>

Once you have a Maven profile configured in the `${USER_HOME}/.m2/settings.xml`
file, you can activate the profile by right-clicking on your project &rarr;
*Properties* &rarr; *Maven* and entering the profile IDs that supply the
necessary settings into the *Active Maven Profiles* text field. For example, to
reference the profile and properties we listed above, you'd enter *sample* for
the Active Maven Profile. Once you've specified all the values, the configurator
(`m2e-liferay`) validates the properties. If there are errors in the `pom.xml`
file, the configurator marks them in Liferay IDE's POM editor. If you fix a
project error, update the project to persist the fix by right-clicking the
project &rarr; *Maven* &rarr; *Update Project*. 

After your POM configuration meets the requirements, the configurator installs
the Liferay plugin facet and your Maven project is officially a Liferay IDE
project! 

Once you have your Maven project configured, you may want to execute a specific
Maven goal such as `liferay:build-lang` or `liferay:build-db` that is associated
with your build phase. To access your project's Maven goals and execute them,
right-click your project &rarr; *Liferay* &rarr; *Maven* and select the goal to
execute. To learn more about Maven's build lifecycle and plugin goals, visit
Apache's [Build Lifecycle
Basics](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Build_Lifecycle_Basics)
guide.

When working with your `pom.xml` file in the IDE, you'll notice several
different viewing modes to work with:

**pom.xml:** provides an editable POM as it appears on the file system.

**Effective POM:** provides a read-only version of your project POM merged with
its parent POM(s), `settings.xml`, and the settings in Eclipse for Maven.

**Overview:** provides a graphical interface where you can add to and edit the
`pom.xml` file.

**Dependencies:** provides a graphical interface for adding and editing
dependencies in your project, as well as modifying the `dependencyManagement`
section of the `pom.xml` file.

**Dependency Hierarchy:** provides hierarchical view of project dependencies and
interactive list of resolved dependencies.

![Figure 2.26: Liferay IDE provides five interactive modes to help you edit and organize your POM.](../../images/pom-editor-features.png)

By taking advantage of these interactive modes, modifying and organizing your
POM and its dependencies has never been easier!

Next, we'll consider the benefits of using a Maven parent project with your
plugin projects. 

### Using a Parent Plugin Project [](id=using-a-parent-plugin-project-liferay-portal-6-2-dev-guide-02-en)

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

We'll create our parent project and then specify the general settings needed to
build your plugins for Liferay. The parent project is similar to the project
root of the Liferay Plugins SDK. Its `pom.xml` file can specify information to
be used by any plugin projects that refer to it. You can always specify
information in each plugin's POM, but it's more convenient to use the parent
project's POM for sharing common information. 

Let's create a parent project named `sample-parent-project`. Start by creating a
new directory for your parent project. For this example, we'll name the
directory `sample-parent-project`. You can place the directory anywhere on your
file system. 

Next, create a POM file named `pom.xml` in your `sample-parent-project`
directory. Insert the following XML code into the POM:

	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>

		<groupId>com.liferay.sample</groupId>
		<artifactId>sample-parent-project</artifactId>
		<version>1.0-SNAPSHOT</version>
		<packaging>pom</packaging>

		<name>sample-parent-project</name>
		<url>http://www.liferay.com</url>

		<properties>
			<liferay.app.server.deploy.dir>
				${liferay.app.server.deploy.dir}
			</liferay.app.server.deploy.dir>

			<liferay.app.server.lib.global.dir>
				${liferay.app.server.lib.global.dir}
			</liferay.app.server.lib.global.dir>

			<liferay.app.server.portal.dir>
				${liferay.app.server.portal.dir}
			</liferay.app.server.portal.dir>

			<liferay.auto.deploy.dir>
				${liferay.auto.deploy.dir}
			</liferay.auto.deploy.dir>

			<liferay.version>
				${liferay.version}
			</liferay.version>

			<liferay.maven.plugin.version>
				${liferay.maven.plugin.version}
			</liferay.maven.plugin.version>
		</properties>

		<dependencies>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>portal-client</artifactId>
				<version>6.2.0-GA1</version>
			</dependency>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>portal-impl</artifactId>
				<version>6.2.0-GA1</version>
				<scope>provided</scope>
			</dependency>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>portal-pacl</artifactId>
				<version>6.2.0-GA1</version>
				<scope>provided</scope>
			</dependency>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>portal-service</artifactId>
				<version>6.2.0-GA1</version>
				<scope>provided</scope>
			</dependency>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>portal-web</artifactId>
				<version>6.2.0-GA1</version>
				<type>war</type>
				<scope>provided</scope>
			</dependency>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>util-bridges</artifactId>
				<version>6.2.0-GA1</version>
			</dependency>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>util-java</artifactId>
				<version>6.2.0-GA1</version>
			</dependency>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>util-slf4j</artifactId>
				<version>6.2.0-GA1</version>
			</dependency>
			<dependency>
				<groupId>com.liferay.portal</groupId>
				<artifactId>util-taglib</artifactId>
				<version>6.2.0-GA1</version>
			</dependency>
		</dependencies>

	</project>

The POM starts by specifying the model version that Maven supports, your
project's [Maven
coordinates](http://maven.apache.org/pom.html#Maven_Coordinates), your
project's name, and your company's URL. 

Next, the POM specifies some key Liferay property elements that your plugins
require in order to be deployed to your Liferay portal. You can conveniently
specify these values in a parent project for all of your plugin projects to
leverage. A plugin project can override any of its parent's properties by
specifying the desired property explicitly in the child plugin project's POM. 

<!--Is the correct term not child project, but module?-->

Replace each Liferay property value (e.g., replace
`${liferay.app.server.deploy.dir}` and other dereferenced `liferay.*`
properties) with the appropriate value based on your Liferay environment. We've
described these key properties here: 

- `liferay.app.server.deploy.dir`: Your app server's deployment directory. 
- `liferay.app.server.lib.global.dir`: Your app server's global library
  directory. 
- `liferay.app.server.portal.dir`: The path to Liferay's deployment directory on
  the app server. 
- `liferay.auto.deploy.dir`: The path of your Liferay bundle's hot-deploy
  directory `deploy/`. By specifying your Liferay instance's deploy directory in
  the POM, you're telling Maven exactly where to deploy your plugin artifacts. 
- `liferay.maven.plugin.version`: The version of the Liferay Maven Plugin you
  are using. 
- `liferay.version`: The version of Liferay you are using. 

Here's an example where we've specified these *properties* for Liferay bundled
with Apache Tomcat in a directory `C:\liferay-portal-6.2`: 

	<properties>
		<liferay.app.server.deploy.dir>
			C:\liferay-portal-6.2\tomcat-7.0.42\webapps
		</liferay.app.server.deploy.dir>

		<liferay.app.server.lib.global.dir>
			C:\liferay-portal-6.2\tomcat-7.0.42\lib\ext
		</liferay.app.server.lib.global.dir>

		<liferay.app.server.portal.dir>
			C:\liferay-portal-6.2\tomcat-7.0.42\webapps\root
		</liferay.app.server.portal.dir>

		<liferay.auto.deploy.dir>
			C:\liferay-portal-6.2\deploy
		</liferay.auto.deploy.dir>

		<liferay.maven.plugin.version>
			6.2.0-GA1
		</liferay.maven.plugin.version>

		<liferay.version>
			6.2.0-GA1
		</liferay.version>
	</properties>

You can also specify these key properties in your Global or User `settings.xml`
file. To learn more about this method, visit the *Configuring Your Liferay Maven
Project* section.

The Liferay plugins you develop depend on several Liferay artifacts. We've
included them in individual `dependency` elements within the POM's
`dependencies` element. All of your parent project's modules (i.e., projects
that refer to this parent) can leverage these dependencies. 

---

![note](../../images/tip-pen-paper.png) **Note:** You could just as easily
include such dependencies in the POM of each of your plugin projects, but
specifying them in a parent project makes them accessible to child projects
through inheritance. 

---
 
Now that you specified your project's general information, your Liferay
environment properties, and the Liferay artifacts on which Liferay plugin
projects depend, let's create a plugin project using Liferay's archetypes. 

### Creating Liferay Plugins with Maven [](id=creating-liferay-plugins-with-maven-liferay-portal-6-2-dev-guide-02-en)

Liferay offers many archetypes to help create Maven projects for multiple plugin
types, including portlet, theme, hook, and layout template plugins. We provide
archetypes for each of these plugin types for various versions of Liferay, so
you almost certainly have the archetype you need. 

*Archetype* is Maven's project templating toolkit. Let's use it to create a
Liferay portlet project. With Archetype, you can use the same steps we detail
below to generate Liferay plugin projects of any type. 

---

![note](../../images/tip-pen-paper.png) **Note:** Make sure Maven is installed
 and that its executable is in your `$PATH` environment variable. 

---

We'll demonstrate two ways of creating Liferay plugins with Maven: using Liferay
IDE and using the command line. First, let's learn how to use Maven archetypes
to generate a Liferay plugin project using Liferay IDE:

1.  Navigate to *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2.  Assign a project name and display name. For our example, we'll use
    *sample-portlet* and *Sample* for the project name and display name,
    respectively. Notice that upon entering *sample-portlet* as the project
    name, the wizard conveniently inserts *Sample* in grayed-out text as the
    portlet's default display name. The wizard derives the default display name
    from the project name, starts it in upper-case, and leaves off the plugin
    type suffix *Portlet* because the plugin type is automatically appended to
    the display name in Liferay Portal. The IDE saves the you from repetitively
    appending the plugin type to the display name; in fact, the IDE ignores any
    plugin type suffix if you happen to append it to the display name. 

3.  Select *Maven (liferay-maven-plugin)* for the build type. Notice that some
    of the options for your plugin project changed, including the *Location* 
    field, which is set to the user's workspace by default. Choose the parent 
    directory in which you want to create the plugin project. It is a best 
    practice to create a parent project for your Maven plugins, so they can all
    share common project information. See section *Using a Parent Plugin
    Project* for details. 

4.  Specify the *Artifact version* and *Group id*. For our example, we'll use
    `1.0-SNAPSHOT` and `com.liferay.sample` for the artifact version and group
    id, respectively.

5.  Specify the active profile that you'd like your Liferay plugin project to
    use. If you don't remember your active profile or haven't created one, click
    the *Select Active Profiles ...* icon immediately to the right of the text
    field. If you have any active profiles, they will be listed in the menu on
    the left. To select an existing profile, highlight its profile ID and select
    the illuminated *right arrow* button to transfer it to the menu on the
    right. Otherwise, if you don't have any existing profile, click the green
    *addition* button to create a profile and give it an ID. 

    If you're specifying a new profile, the wizard will still create your
    plugin, but it will need further attention before it is deployable. You'll
    need to specify the necessary properties within the new profile; we'll
    demonstrate specifying these properties in the *Configuring your Liferay
    Maven Project* section of this chapter. 

    You also have the option to create a profile based on a Liferay runtime. To
    do this, select the *Create New Maven Profile Based on Liferay Runtime*
    button to the far right of the *Active profiles* text field. Specify the
    *Liferay runtime*, *New profile id*, and *Liferay version*. For the new
    profile location you can choose to specify your profile in the
    `settings.xml` (recommended) or your project's `pom.xml`. When creating your
    Maven profile based on a Liferay runtime, the IDE automatically populates
    the new profile with the required properties, and no additional profile 
    configuration is needed for the plugin. 

6.  Select the *Portlet* plugin type and then click *Finish*.

	![Figure 2.27: You can build a Liferay Plugin Project using Maven by completing the setup wizard.](../../images/create-wizard-maven-ide.png) 

Great! You've successfully created a Liferay portlet project using Maven in
Liferay IDE! Next, let's run through steps for creating your Liferay Maven
plugins using the command line.

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

        mvn archetype:generate -DarchetypeCatalog=https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/

    ---
    
    ![note](../../images/tip-pen-paper.png) **Important:** Currently, the new
    GA1 artifacts for CE and EE are only available from
    [repository.liferay.com](repository.liferay.com). Therefore, you must use
    the `-DarchetypeCatalog=...` portion to access the Liferay Repository.
    You'll also need to configure a couple other files to ensure the generation
    command completes successfully. Reference the *Installing CE Artifacts from
    the Central Repository* and *Installing EE Artifacts from the Liferay
    Repository* sections to configure Maven to access the Liferay Repository for
    CE and EE artifacts, respectively.
    
    ---

    <!-- Edit archetype generate command and remove above note when GA1 artifacts
    are available from Maven Central. -Cody -->

    Archetype starts and lists the archetypes available to you. You're prompted
    to *choose* an archetype or *filter* archetypes by group / artifact ID. The
    output looks similar to the following text: 

        ...
        4: https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/ -> com.liferay.
        maven.archetypes:liferay-portlet-jsf-archetype
        (Provides an archetype to create Liferay JSF portlets.)
        5: https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/ -> com.liferay.
        maven.archetypes:liferay-layouttpl-archetype
        (Provides an archetype to create Liferay layout templates.)
        6: https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/ -> com.liferay.
        maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        7: https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/ -> com.liferay.
        maven.archetypes:liferay-portlet-liferay-faces-alloy-archetype
        (Provides an archetype to create Liferay Faces Alloy portlets.)
        8: https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/ -> com.liferay.
        maven.archetypes:liferay-portlet-primefaces-archetype
        (Provides an archetype to create Liferay PrimeFaces portlets.)
        ...
        Choose a number or apply filter (format: [groupId:]artifactId, case sensiti
        ve contains):
        
<!--
        ...
        39: remote -> com.liferay.maven.archetypes:liferay-hook-archetype
        (Provides an archetype to create Liferay hooks.)
        40: remote -> com.liferay.maven.archetypes:liferay-layouttpl-archetype
        (Provides an archetype to create Liferay layout templates.)
        41: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
        (Provides an archetype to create Liferay portlets.)
        42: remote -> com.liferay.maven.archetypes:liferay-portlet-icefaces-archety
        pe
        (Provides an archetype to create Liferay ICEfaces portlets.)
        43: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
        (Provides an archetype to create Liferay JSF portlets.)
        ...
        Choose a number or apply filter (format: [groupId:]artifactId, case sensiti
        ve contains):
-->
<!-- Output will look similar to this once GA1 artifacts are officially
available on Maven Central/ZIP files. Add similar output back, when available
-Cody -->

3. Choose a Liferay portlet archetype by entering its number. Since we're using
the Liferay Repository, the newest archetype version is automatically selected.
(*6.2-GA1*).

<!--
3.  To find the right Liferay archetype for your project, you can either scroll
    up to find it or apply filters to narrow the set of results. Filtering on
    *liferay* as your group ID, and a plugin type (*portlet*, *hook*, *theme*,
    etc.) can help you focus on more applicable Liferay archetypes. 

    Entering `liferay:portlet` as a filter gives a listing of Liferay portlet
    archetypes: -->
<!-- Add back when filtering is relevant (using GA1 artifacts from Central -Cody
-->
<!--
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

4.  Choose an archetype by entering its number.-->
<!-- Add back when step 3. is added back -->

<!--
5.  You're prompted to choose the archetype version. Enter the number
    corresponding to the Liferay version for the archetype. However, you're not
    required to select the archetype version that corresponds with your Liferay
    instance; older archetype versions are compatible with updated Liferay
    bundles. 

        Choose com.liferay.maven.archetypes:liferay-portlet-archetype version:
        1: 6.0.2
        2: 6.0.3
        3: 6.0.4
        4: 6.0.5
        5: 6.0.6
        6: 6.1.0
        7: 6.1.1
        8: 6.1.2
        9: 6.1.10
        10: 6.1.20
        11: 6.1.30
        12: 6.2.0-B1
        13: 6.2.0-B2
        14: 6.2.0-B3
        15: 6.2.0-M5
        16: 6.2.0-M6
        17: 6.2.0-RC1
        18: 6.2.0-RC2
        19: 6.2.0-RC3
        20: 6.2.0-RC4
        21: 6.2.0-RC5
        Choose a number: 21:
-->
<!-- Add back when GA1 artifacts are available from Central. --Cody -->

4.  Enter values for the *groupId*, *artifactId*, *version*, and *package*
    coordinates (properties) of your project. Here are some examples: 

        groupId: com.liferay.sample
        artifactId: sample-portlet
        version: 1.0-SNAPSHOT
        package: com.liferay.sample

    This process is illustrated in the snapshot below:

    ![Figure 2.28: When creating your portlet plugin, you must enter your *groupId*, *artifactId*, *version*, and *package* properties.](../../images/maven-portlet-plugin-settings.png)

    For more information on defining Maven coordinates, see
    [http://maven.apache.org/pom.html#Maven_Coordinates](http://maven.apache.org/pom.html#Maven_Coordinates).

5.  Enter the letter *Y* to confirm your coordinates.

    Maven's Archetype tool creates a Liferay plugin project directory with a new
    `pom.xml` file and source code. 

---

![note](../../images/tip-pen-paper.png) **Note:** The archetype file is
downloaded and installed automatically to your local repository (e.g.,
`.m2/repository/com/liferay/maven/archetypes/[archetype]`). If you
configured a mirror pointing to your public repository on Nexus, the
plugin is installed there. 

---

Following these steps using Liferay IDE or the command line, you can use
Archetype to generate all your Liferay plugin projects! 

Plugin projects generated from a Liferay archetype are equipped with a POM
that's ready to work with a parent project. It inherits the values for
`liferay.version` and `liferay.auto.deploy.dir` properties from the parent. 

When your plugin is created, you can package and deploy your project to a
specified Liferay instance. You can even install and deploy the individual
plugin to a remote repository. 

Next we'll go through some brief examples to demonstrate deploying your plugins
to Liferay Portal using Maven. 

### Deploying Liferay Plugins with Maven [](id=deploying-liferay-plugins-with-maven-liferay-portal-6-2-dev-guide-02-en)

With Maven it's easy to deploy plugins to a Liferay Portal instance. Just follow
these steps: 

1.  Make sure you've specified the Liferay specific properties (e.g., those
    properties starting with `liferay.`) your plugin uses. See this chapter's
    section *Using a Parent Plugin Project* for descriptions of these Liferay
    properties. 

    Here's an example where we specified these *properties* for Liferay bundled
    with Apache Tomcat in a directory `C:\liferay-portal-6.2`:

		<properties>
			<liferay.app.server.deploy.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\webapps
			</liferay.app.server.deploy.dir>

			<liferay.app.server.lib.global.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\lib\ext
			</liferay.app.server.lib.global.dir>

			<liferay.app.server.portal.dir>
				C:\liferay-portal-6.2\tomcat-7.0.42\webapps\root
			</liferay.app.server.portal.dir>

			<liferay.auto.deploy.dir>
				C:\liferay-portal-6.2\deploy
			</liferay.auto.deploy.dir>

			<liferay.maven.plugin.version>
				6.2.0-GA1
			</liferay.maven.plugin.version>

			<liferay.version>
				6.2.0-GA1
			</liferay.version>
		</properties>

2.  In your command prompt, navigate to your Liferay plugin project's directory. 

3.  Package your plugin by entering 

        mvn package

    Your command output should be similar to the following output: 

        [INFO] Building war:
        E:\liferay-plugins-maven\sample-parent-project\sample-portlet\target\sample-
        portlet-1.0-SNAPSHOT.war
        ...
        [INFO] --------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] --------------------------------------------------------------------

4.  Deploy the plugin into your Liferay bundle by entering

        mvn liferay:deploy

    The command output should look similar to the following output: 

        [INFO] Deploying sample-portlet-1.0-SNAPSHOT.war to [liferay version]\deploy
        [INFO] ---------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ---------------------------------------------------------------------

    Your Liferay console output shows your plugin deploying. It looks
    like this: 

        INFO: Deploying web application directory [liferay version]\[tomcat version]
        \webapps\sample-portlet
        INFO  [pool-2-thread-2][HotDeployImpl:178] Deploying sample-portlet from que
        ue
        INFO  [pool-2-thread-2][PluginPackageUtil:1033] Reading plugin package for s
        ample-portlet

    ---
 
    ![note](../../images/tip-pen-paper.png) **Note:** If you get the following
    error after executing `mvn liferay:deploy`, make sure you're executing the
    command from your plugin's directory (e.g., `sample-portlet`)--not your
    parent project's directory. 
 
     `[ERROR] No plugin found for prefix 'liferay' in the current project and in
     the plugin groups [org.apache.maven.plugins, org.codehaus.mojo] available
     from the repositories [local (C:\Users\cdhoag\.m2\repository), central
     (http://repo.maven .apache.org/maven2)] -> [Help 1]`
    
    ---

5.  If you're deploying the plugin to a release or snapshot repository,
    specify the repository by adding a distribution management section to your
    plugin's `pom.xml` or your parent project's `pom.xml`. 

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

    ![Figure 2.29: Select the *Summary* tab of your repository to see how to specify it for distribution management in your plugin's POM.](../../images/maven-repository-summary.png) 

    Since you created the plugin as a snapshot, you'll have to deploy it to a
    snapshot repository. You can deploy a plugin as a release, but the plugin's
    POM must specify a valid release version (e.g., `<version>1.0</version>`),
    not a snapshot version (e.g., `<version>1.0-SNAPSHOT</version>`). 

6.  Deploy your plugin into your specified Nexus repository:

		mvn deploy

Your plugin is now available in your Nexus repository!

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

### Liferay Plugin Types to Develop with Maven [](id=plugin-types-to-develop-with-maven-liferay-portal-6-2-dev-guide-02-en)

You can develop all Liferay plugin types with Maven: portlets, themes,
layout templates, hooks, and Ext. Next, you'll learn how to create each plugin
type using Maven, and we'll point out where each plugin's directory structure is
different than if you created it using the Plugins SDK. We'll often refer to the
previous sections for creating and deploying these plugin types in Maven using
Liferay artifacts.  We'll also reference sections of some other chapters in this
guide, since they're still relevant to Maven developers: they explain how you
develop each type of plugin regardless of development environment. 

Let's start with portlet plugins. 


#### Creating a Portlet Plugin [](id=creating-a-portlet-plugin-maven-liferay-portal-6-2-dev-guide-02-en)

To create a Liferay portlet plugin project, follow the *Creating Liferay Plugins
with Maven* section. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your portlet project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:portlet`, to 
find the Liferay portlet archetypes more easily. 

---

##### Anatomy [](id=portlet-plugin-archetype-anatomy-liferay-portal-6-2-dev-guide-02-en)

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

Maven creates the `src/main/java/` directory automatically. It holds the
portlet's Java source code (e.g., `com.liferay.sample.SamplePortlet.java`), and
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
Portlet](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/anatomy-of-a-portlet-project-liferay-portal-6-2-dev-guide-03-en)
section in this guide. 

##### Deployment [](id=deployment-liferay-portal-6-2-dev-guide-02-en)

To deploy your portlet plugin, follow the instructions detailed above in
*Deploying Liferay Plugins with Maven*. 

Congratulations! You successfully created a Liferay portlet plugin using Maven. 

##### More Information [](id=more-information-liferay-portal-6-2-dev-guide-02-en)

For detailed information on creating portlet plugins, see
[Developing Portlet Applications](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-portlet-applications-liferay-portal-6-2-dev-guide-03-en). 

Next, let's run through a brief example for developing a theme plugin using
Maven.

#### Developing Liferay Theme Plugins with Maven [](id=develop-liferay-theme-plugins-with-maven-liferay-portal-6-2-dev-guide-02-en)

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

##### Creating a Theme Plugin [](id=create-a-theme-plugin-maven-liferay-portal-6-2-dev-guide-02-en)

Theme plugin creation is similar to portlet plugin creation. We'll start by
assuming you already created the `sample-parent-project` and its `pom.xml`. 

To create your Liferay theme plugin project follow the *Creating Liferay Plugins
with Maven* section, making sure to select *Theme* as the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your theme project, you can filter on group ID `liferay`, or
even the group ID/artifact ID combination `liferay:theme`, to more easily
find the Liferay portlet archetypes. 

---

##### Anatomy [](id=theme-archetype-anatomy-liferay-portal-6-2-dev-guide-02-en-0)

A theme project created from the
`com.liferay.maven.archetypes:liferay-theme-archetype` has the following
directory structure: 

- sample-theme/
    - pom.xml
    - src/
        - main/
            - resources/
                - resources-importer/
                    - document_library/
                    - journal/
                        - articles/
                        - structures/
                        - templates/
                    - readme.txt
                    - sitemap.json
            - webapp/
                - WEB-INF/
                    - liferay-plugin-package.properties
                    - web.xml
                - css/ \* Optionally add to hold CSS customizations
                - images/ \* Optionally add to hold custom images
                - js/ \* Optionally add to hold JavaScript customizations
                - templates/ \* Optionally add to hold template customizations

The `src/main/webapp/` folder holds your theme's customizations. If you've ever
created a theme plugin using Liferay IDE or the Plugins SDK, this folder is used
the same way as the `docroot/_diffs/` folder. For example, `custom.css` should
go in `src/main/webapp/css/custom.css`. 

Here's a table describing the directory structure differences between themes
created using Maven and themes created using the Plugins SDK: 

Location       | Maven project      | Plugins SDK project |
-------------- | ------------------ | ------------------- |
customizations | `src/main/webapp/` | `docroot/_diffs/`   |

To view the directory structure of a theme developed by Ant, visit the [Anatomy
of a Theme
Project](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-liferay-themes-liferay-portal-6-2-dev-guide-09-en)
section in this guide.

##### Theme POM [](id=theme-pom-liferay-portal-6-2-dev-guide-02-en)

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

##### Deployment [](id=deployment-liferay-portal-6-2-dev-guide-02-en-0)

To deploy your theme plugin, follow the instructions in the *Deploying Liferay
Plugins with Maven* section. 

---

![note](../../images/tip-pen-paper.png) **Note:** When you execute the *package*
goal, a WAR file is created; it's just like the Maven WAR type project.
Simultaneously, the parent theme is downloaded and copied, and your theme's
customizations are overlaid last. A thumbnail image of the theme is created and
placed in the *target* directory. Its path is
`target/[theme]/images/screenshot.png` in your theme project. 

---

##### More Information [](id=more-information-liferay-portal-6-2-dev-guide-02-en-0)

For more information on Liferay themes and its settings, see
[Creating Liferay Themes and Layout
Templates](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-themes-and-layout-templates-liferay-portal-6-2-dev-guide-09-en). 

You successfully developed a Liferay theme using Maven. Find out about
developing hook plugins next. 

#### Developing Liferay Hook Plugins with Maven [](id=developing-hook-plugins-with-maven-liferay-portal-6-2-dev-guide-02-en)

Hooks are the optimal plugin type for customizing Liferay's core features.
Creating a hook is almost identical to portlet plugin creation in Maven. Let's
take a look. 

##### Creating a Hook Plugin [](id=creating-a-hook-plugin-maven-liferay-portal-6-2-dev-guide-02-en)

To create a Liferay hook plugin project, follow the steps outlined in the
*Creating Liferay Plugins with Maven* section, making sure to select *Hook* as
the plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your hook you can filter on group ID `liferay`, or even the
group ID/artifact ID combination `liferay:hook`, to more easily find the
Liferay portlet archetypes. 

---

##### Anatomy [](id=hook-plugin-archetype-anatomy-liferay-portal-6-2-dev-guide-02-en)

A hook project created from the
`com.liferay.maven.archetypes:liferay-hook-archetype` has the following
directory structure: 

- hook-plugin/
    - src/
        - main/
            - java/
            - resources/
            - webapp/
                - WEB-INF/
                    - lib/  \* Optionally add to hold required libraries
                    - liferay-hook.xml
                    - liferay-plugin-package.properties
                    - web.xml
    - pom.xml

The `hook-plugin/src/main/java/` directory holds the hook's Java source code
(e.g., `com.liferay.sample.SampleHook.java`) and `hook-plugin/src/main/webapp`
holds the hook's web source code. If you're familiar with creating hook plugins
using the Plugins SDK, you probably noticed that Maven uses a different plugin
directory structure. 

The following table illustrates the differences in location of the Java source
and web source code for a Maven project and a Plugins SDK project: 

Location    | Maven project     | Plugins SDK project   |
----------- | ----------------- | --------------------- |
Java source | `src/main/java`   | `docroot/WEB-INF/src` |
Web source  | `src/main/webapp` | `docroot`             |

To view the directory structure of a hook developed by Ant, visit the *Anatomy
of the Hook* section of the [Creating a
Hook](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-a-hook-liferay-portal-6-2-dev-guide-10-en)
section in this guide. 

##### Deployment [](id=deployment-liferay-portal-6-2-dev-guide-02-en-1)

To deploy your hook plugin, follow the instructions from the *Deploying Liferay
Plugins with Maven* section. 

##### More Information [](id=more-information-liferay-portal-6-2-dev-guide-02-en-1)

For detailed information on creating hooks, see [Customizing and Extending
Functionality with
Hooks](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/customize-and-extend-functionality-hooks-liferay-portal-6-2-dev-guide-en).

You're nearly a Maven expert now; you're able to create portlets, themes, and
hooks. Let's round things out by learning to develop layout templates. 

#### Developing Liferay Layout Template Plugins with Maven [](id=develop-layout-templates-maven-liferay-portal-6-2-dev-guide-02-en)

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

##### Creating a Layout Template Plugin [](id=creating-layout-template-plugin-maven-liferay-portal-6-2-dev-guide-en)

To create a Liferay layout template plugin project follow the *Creating Liferay
Plugins with Maven* section, making sure to select *Layout Template* as the
plugin type. 

---

![tip](../../images/tip-pen-paper.png) **Tip**: As you use Maven's Archetype
tool to generate your layout template project, you can filter on group ID
`liferay`, or even group ID / artifact ID combination `liferay:layout`, to find
the Liferay layout template archetypes.

---

##### Anatomy [](id=layout-template-plugin-archetype-anatomy-liferay-portal-6-2-dev-guide-en)

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
Project](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-liferay-layout-templates-liferay-portal-6-2-dev-guide-09-en)
section in this guide. 

##### Deployment [](id=deployment-liferay-portal-6-2-dev-guide-02-en-2)

To deploy your layout template plugin, follow the instructions detailed above in
the *Deploying Liferay Plugins with Maven* section. 

##### More Information [](id=more-information-liferay-portal-6-2-dev-guide-02-en-2)

For detailed information on creating layout templates, see [Creating
Liferay Themes and Layout
Templates](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-themes-and-layout-templates-liferay-portal-6-2-dev-guide-09-en). 

You've passed your trial by fire (the cat thanks you), developing yet another
plugin type with Maven. Way to go! In the next section we'll cover other
Liferay-provided Maven archetypes. 

#### Developing More Liferay Plugins with Maven [](id=developing-more-liferay-plugins-with-maven-liferay-portal-6-2-dev-guide-en)

Did you think we covered all the available archetypes for developing Liferay
plugins? The Liferay team has been busy expanding our archetype list, and we're
proud to show you some additional plugins that you can create using Maven
archetypes. 

Check out these exciting archetypes that are available: 

- Liferay ServiceBuilder portlets 
- Liferay webs 
- Liferay Ext 
- JSF Portlet Archetype 
- ICEFaces Portlet Archetype 
- PrimeFaces Portlet Archetype 
- Liferay Faces Alloy Portlet Archetype 
- Liferay Rich Faces Portlet Archetype 

In addition, there are some Maven *goals* Liferay has provided: 

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

Are you wondering if we're going to make more terrible jokes that steal from
classic poetry? Quoth the Maven, "Probably." Let's move on to exploring the
differences between hot deploy and auto deploy, with respect to your plugins.

## Deploying Your Plugins: Hot Deploy vs. Auto Deploy [](id=hot-deploy-vs--auto-deploy-liferay-portal-6-2-dev-guide-02-en)

As you develop plugins you'll want to deploy them to your test servers and as
you finish developing plugins you'll want to deploy them to your production
servers. There are *hot* deploy and *auto* deploy options to use in deploying
your plugins. Most people confuse the two concepts, believing them to be one and
the same. In reality, Liferay has TWO completely separate and different concepts
for them.

How, you say? We'll give a brief synopsis of each deployment method in this
section. Let's get started by explaining the hot deployment method.

### Using Hot Deployment [](id=using-hot-deployment-liferay-portal-6-2-dev-guide-02-en)

The first deployment method we'll explore is *hot* deployment. You may be
familiar with hot deployment in the context of JEE application servers. In
summary, you place an application artifact (WAR or EAR) file into a specifically
configured directory, your application server (Tomcat, WebSphere, WebLogic,
etc.) picks up that artifact, deploys it within the application server, and
starts the application.

This model works really well for development purposes, since a server restart is
not required to view updates from your code changes. This model also works for
single node production deployments. 

This model completely breaks down when you deploy to a multi-node production
deployment. In a multi-node environment, you have many more constraints to deal
with, which require you to: 

- Ensure the application archive is available to all nodes
- Ensure the application deploys successfully across all nodes, simultaneously

Most application servers solve these constraints by using a master/slave type of
design: an admin server with multiple managed servers. When you hot deploy a
plugin, you use the admin server's user interface, or vendor console tool like
[Wsadmin](http://en.wikipedia.org/wiki/Wsadmin), to add the archive, select
which managed servers should deploy it, and start the application. Application
server vendors often have different names and tools for these modes and tools:

- JBoss "domain" mode
- WebLogic "production" mode
- WebSphere deployment manager
- Tomcat FarmWarDeployer

These modes and tools reside completely outside of Liferay Portal and are
strictly in the application server's realm. However, Liferay piggybacks off the
application server's hot deploy capability and performs additional
initialization after a given application starts (e.g., via
`javax.servlet.ServletContextListener` mechanisms). 

<!-- "This is considered more as an application lifecycle and inter-application
dependency management as opposed to hot deploy. When Liferay moves fully to OSGi
in 7.0, we will more cleanly separtate "hot deploy" and "application lifecycle"
concepts."  We should keep these statements by Mike Han in mind for 7.0.-->

There are some specific Liferay capabilities that won't work unless your
application server has hot deployment capabilities. Specifically, hot deploying
custom JSPs in hooks won't work, because Liferay's JSP hook overriding
capabilities depend on the application server's ability to:

- Deploy based on an exploded portal WAR
- Load changes to JSP files at runtime

Application servers running in "production" and "domain" modes cannot support
these abilities, because in these deployment models, most servers don't use
exploded WARs. As such, these application servers don't support JSP
reloading/recompilation in these modes. Even for Tomcat, it's generally
advisable to deactivate JSP reloading for production deployments. 

So what do you do if you use hooks to override Liferay JSPs AND you must use
non-exploded WAR deployments? The answer is simple: inject a pre-processing
stage as part of your build process. You deploy the hooks, allowing them to make
changes to the portal WAR file. Then you rebundle the portal WAR file and deploy
it using the application server's deployment tools. Of course, you still need to
deploy your hook as well, but you no longer need to worry about the JSP
overrides not being loaded by your application server.

Hopefully this whets your appetite for doing hot deployments. Stay hungry, as
it's time to explore auto deployment next. 

### Using Auto Deployment [](id=using-auto-deployment-liferay-portal-6-2-dev-guide-02-en)

The Liferay *auto* deployment feature is a mostly optional feature that works in
conjunction with the hot deployment capabilities of your application server.
Where Liferay's hot deploy leverages the hot deploy capabilities of your app
server and performs additional initializations, auto deploy injects required JAR
files and descriptors into your application's archive file. Executing `ant
deploy` invokes both hot deployment and auto deployment tasks for your plugin. 

So how does auto deployment work with Liferay plugins? Auto deployment completes
the following tasks:

1. Picks up a Liferay recognized archive (e.g., `*-portlet.*`, `*-theme.*`,
   `*-web.*`, `*.lpkg`)
2. Injects required libraries (e.g., `util-java.jar`, `util-taglib.jar`)
3. Injects dependent JAR files (specified in `liferay-plugins.properties`)
4. Injects required taglib descriptors (e.g., `liferay-theme.tld`)
5. Injects required deployment descriptors (e.g., app server specific
   descriptors)
6. Injects any missing Liferay specific deployment descriptors (e.g.,
   `liferay-portlet.xml`)

By relying on auto deployment to complete these tasks automatically, you save
time and you don't even have to learn all of Liferay's deployment descriptors.
However, this feature is incompatible with application server farms and
multi-node modes. 

So now you're probably wondering how to configure your application server in
these situations? The answer is simple: Do not use the auto deployment method at
runtime; use it at build time.

The Liferay Plugins SDK allows you to preprocess your archives and inject all
the required elements, thus, bypassing auto deployer at runtime. You simply need
to call the following Ant task:

    ant direct-deploy

The `direct-deploy` Ant task creates an exploded WAR from which you can easily
create a WAR file. The location of the exploded WAR depends on the deployment
directory of the application server you've configured in your Plugins SDK
environment. See the *Plugins SDK Configuration* section of [Leveraging the
Plugins
SDK](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/leveraging-the-plugins-sdk-liferay-portal-6-2-dev-guide-02-en)
for instructions on configuring the Plugins SDK for your app server. The Plugins
SDK's `build.properties` provides a default deployment directory value for each
supported app server. But you can override the default value by specifying your
desired value for the `app.server.[type].deploy.dir` (replace `[type]` with your
app server type) in your `build.[username].properties` file. 

If you choose not to use the Liferay Plugins SDK to do direct deployment, you
can examine the `build-common.xml` file in the Plugins SDK to see how Liferay
invokes the deployer tools.

Terrific! You now know the differences between hot deploy and auto deploy.
Understanding what's going on during the deployment of your plugins is crucial
for troubleshooting anything that goes wrong, and can help you simplify your
deployment process and make it more efficient. Let's summarize what we learned
in this chapter.

## Summary [](id=summary-liferay-portal-6-2-dev-guide-02-en)

In this chapter, we covered many of Liferay's most popular development
strategies. You covered Liferay IDE, which serves as a workspace used for
customizing your Liferay instance. You also learned that when Liferay IDE is
paired with the Plugins SDK, you have a one stop development environment where
you can develop Liferay plugins, build them, and deploy them to your Liferay
instance.

You also learned all about developing Liferay plugins with the Maven build
framework. You configured Maven locally, downloaded and installed the required
Liferay Maven artifacts, and learned to create Liferay plugins with Maven.
You're ready to create all kinds of Liferay plugins based on Liferay's plugin
archetypes. Just don't let Lenore III sleep near the fire this time. 

Lastly, you learned about Liferay's deployment process, and the difference
between hot deploy and auto deploy.

Feeling confused by the number of features provided by Eclipse and Liferay IDE?
You can easily come across difficult questions and run into very specific
problems, but someone else might have already solved your issue or answered your
question. So where would you go to find out? Don't reinvent the wheel, visit the
[Liferay IDE Community page](http://www.liferay.com/community/liferay-projects/liferay-ide/overview)!
On the *Forums* page, you can look up solutions to specific issues and ask
questions. Be sure to fully describe any problems you have to ensure you get a
working answer. You can even track known issues from the *Issue Tracker* page. 

Now that you have a firm grasp on Liferay's development tools, let's learn about
developing one of Liferay's most important components: portlets.

