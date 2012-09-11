# Liferay IDE [](id=liferay-i-4)

Liferay's developers use a variety of tools to develop the product and as a
consequence of that they have always tried hard to allow other developers to use
any tools they wanted for their own development. Because of this you can develop
portals based on Liferay with complex IDEs Eclipse, Netbeans or IntelliJ Idea or
just use text editors such as Notepad. You can write your persistence layer
directly using SQL and JDBC or you can use advanced object-relational mapping
libraries such as hibernate or iBatis.

But while being agnostic is great, specially for more experienced developers who
can reuse their existing knowledge, it can be overwhelming for newcomers. For
that reason Liferay has also develped specific development tools that can be
used to ease the learning curve when developing portlets with Liferay. The most
significant of these tools is Liferay IDE, a fully featured Integrated
Development Environment based on Eclipse.

Liferay IDE is an extension for the Eclipse platform that supports development
of plugin projects for the Liferay Portal platform. It is available as a set of
Eclipse plugins installable from an update-site. The latest version supports
developing 5 Liferay plugin types: portlets, hooks, layout templates, themes,
and ext plugins. Liferay IDE requires the Eclipse Java EE developer package
using Helios or later versions.

The first two sections below show how to install and set-up Liferay IDE within
your environment. If you are using a copy of Liferay Developer Studio, which
comes with Liferay Portal Enterprise Edition, you can skip directly to the
section titled *Testing/Launching Liferay Tomcat Server* since it comes already
preconfigured.

## Installation [](id=installati-6)

This section is a detailed guide to install Liferay IDE.

### Requirements [](id=lp-6-1-dgen07-requirements-0)

-   Java 5.0 JRE or greater

-   [Eclipse Indigo Java EE (3.7.1)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-
developers/indigosr1) or [Eclipse Helios Java EE (3.6.2)]
(http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2)

### Installation steps [](id=lp-6-1-dgen07-installation-steps-0)

1.  Install Eclipse Indigo or Helios (unzip download file from above).

2.  Run eclipse executable (eclipse.exe).

3.  When eclipse opens, go to *Help* &rarr; *Install New Software...*.

	![Figure 7.1: Installing new software in Eclipse](../../images/ide-install-new-software.png)

4.  In the *Work with* field, paste in one of the following update sites:

    *Eclipse Indigo Update Site*
    [http://releases.liferay.com/tools/ide/eclipse/indigo/stable/]
(http://releases.liferay.com/tools/ide/eclipse/indigo/stable/)

    [http://releases.liferay.com/tools/ide/eclipse/indigo/nightly/]
(http://releases.liferay.com/tools/ide/eclipse/indigo/nightly/)
    (Nightly builds are unstable but contain new features more quickly)

    *Eclipse Helios Update Site*
    [http://releases.liferay.com/tools/ide/eclipse/helios/stable/]
(http://releases.liferay.com/tools/ide/eclipse/helios/stable/)

    [http://releases.liferay.com/tools/ide/eclipse/helios/nightly/]
(http://releases.liferay.com/tools/ide/eclipse/helios/nightly/)
    (Nightly builds are unstable but contain new features more quickly)

5.  After pasting in URL press enter.

	![Figure 7.2: Entering the information for Liferay IDE](../../images/ide-install-available-
software.png)

6. The Liferay IDE features should be detected, select it before clicking
*Next*.

7.	After calculating dependencies, click Next to complete installation.

8.	After installation restart Eclipse to verify installation is complete.

9. After restarting Eclipse go to Help &rarr; About and you should see and icon
badge for Liferay IDE that shows you have it properly installed.

	![Figure 7.3: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

### Alternative installation [](id=lp-6-1-dgen07-alternative-installation-0)

The next set of instructions describe how to install Liferay IDE without typing
the URL of an update site:

1. Instead of typing in a URL, you can download the lastest version from the
releases page. [Eclipse Update Site](http://www.liferay.com/downloads/liferay-projects/liferay-ide)

2. In Add Site dialog, click the *Archive* button and browse to the location of
the downloaded zip file.

3.  Installation proceeds the same as previous section.

## Set up [](id=set--4)

This section describes the setup necessary to begin doing Liferay development
and testing your developments.

### Requirements [](id=lp-6-1-dgen07-requirements-1)

Before getting started you should satisfy the next requirements:

1.  Liferay Portal 6.0 (6.0.5 or greater) downloaded and unzipped.

2.  Liferay plug-ins SDK (6.0.5 or greater) downloaded and unzipped.

3. Eclipse IDE for Java EE Developers (Helios version or greater) + Liferay IDE
(see the Installation steps).

Note: Earlier versions of Liferay, eg 5.2.x, are not supported yet by the
Liferay IDE.

### Liferay Plugins SDK Setup [](id=lp-6-1-dgen07-liferay-plugins-sdk-setup-0)

Before you can begin creating new Liferay plugin projects, a Liferay Plugins SDK
and Liferay Portal must be installed and configured in the IDE.

1.  Open Eclipse with Liferay IDE installed.

2. Open Preference page for Liferay &rarr; Installed SDKs (Go to Window &rarr;
Preferences &rarr; Liferay &rarr; Installed Plugin SDKs).

	![Figure 7.4: Creating a Liferay plugin project](../../images/ide-new-project-liferay-
project.png)

3.  Add your SDK using the add button which brings up the Add SDK Dialog.

4. Browse to the location of your Plug-ins SDK installation. The default name is
the name of the directory but you can change it if you wish.

5.	Select OK and you should see your SDK in the list of Installed SDKs.

	![Figure 7.5: Installed SDKs](../../images/ide-select-installed-sdk.png)

 Note: multiple SDKs can be added to the preferences but you will need to select
 at least one SDK to be the default which is represented by the SDK that has the
 checkbox selected.

### Liferay Portal Tomcat Runtime / Server Setup [](id=lp-6-1-dgen07-liferay-portal-tomcat-
runtime--server-setup-0)

1. In Eclipse open the Runtime environments preference page (Go to Window &rarr;
Preferences &rarr; Server &rarr; Runtime environments).

	![Figure 7.6: Choosing a runtime environment](../../images/ide-prefs-runtime-env.png)

2. Click Add to add a new Liferay runtime and find *Liferay v6.1 (Tomcat 7)*
under the Liferay, Inc. category and click Next.

	![Figure 7.7: Adding a Liferay runtime](../../images/ide-create-new-local-server.png)

3. Click Browse and select the location of the liferay-portal-6.1.x directory.

	![Figure 7.8: Browsing to your Liferay portal directory](../../images/ide-select-liferay-
portal-directory.png)

4. Once you have selected the Liferay portal directory, if a bundle JRE is
present, this bundle will be automatically selected as the JRE to use for
launching the server. However, if there is no JRE bundle (Mac and Linux users)
then you will need to select the JRE to use for launch.

	![Figure 7.9: Choosing the JRE bundle](../../images/ide-server-jre.png)

5. Click finish and you should see the Liferay portal runtime in the list of
runtimes in the preference page.

	![Figure 7.10: Liferay portal in the list of available runtimes](../../images/ide-add-
server-runtime.png)

6. Click ok to save the runtime preferences.

7. If you didn't choose to create a server you will need to create one from the
servers view before you can test the server.

	![Figure 7.11: Adding a runtime to the Servers view](../../images/ide-add-new-server.png)

8. Find the Liferay, Inc category and select the Liferay v6 Server and choose
the Liferay v6 Runtime that you had previously created.

	![Figure 7.12: Selecting Liferay for the Servers view](../../images/ide-define-new-server-
type.png)

## Testing/Launching Liferay Tomcat Server [](id=testing-launching-liferay-tomcat-serv-4)

Once your Liferay Portal Server setup is complete you can launch the server from
the servers tab of Eclipse. Simply select the server and then click on the green
*Start the Server button.* Once the server is running a red *Terminate* button
will appear which you can use to stop the server.

![Figure 7.13: You can start Liferay using either the icon, or by right clicking on the server and 
selecting the option from the menu](../../images/ide-start-server-debug-mode.png)

Alternatively, you can start/stop the server by selecting *Run* or *Terminate*
from the Run menu of Eclipse.

Once the server is finish launching you can open the portal home from the
context menu of the Liferay Tomcat server in the Servers view:

![Figure 7.14: Opening Portal Home from within Liferay IDE](../../images/ide-open-portal-home.png)

## Creating New Liferay Projects [](id=creating-new-liferay-projec-4)

Now that an SDK and portal server have been configured you can create a new
Liferay plug-in project. Go to File &rarr; New Project... &rarr; Liferay &rarr;
Liferay Plug-in Project.

![Figure 7.15: Creating a plugin project](../../images/ide-new-project-liferay-project.png)

In the Liferay project wizard page, the newly configured SDK and Liferay Runtime
should be pre-selected. Also the plugin type *portlet* is the default. Simply
type in a project name and cooresponding display name and click Finish.

![Figure 7.16: Entering the information for the new project](../../images/ide-new-liferay-
project.png)

If it worked you should see a new plugin project in the package explorer. 

![Figure 7.17: Viewing the new project in the Package Explorer view](../../images/ide-package-
explorer-demo-portlet.png)

## Deploying New Liferay Projects to Liferay Server [](id=deploying-new-liferay-projects-to-
liferay-serv-4)

Now with the newly created project, right-click the Liferay Server in the
Servers view and select *Add and Remove...*.

![Figure 7.18: Deploying a project to the server](../../images/ide-servers-add-and-remove.png)

Select the new project and click *Add* to move it to be deployed to the server. 

![Figure 7.19: Choosing which project to deploy](../../images/ide-add-resource-to-server.png)

and then click *Finish*.

You should see the project get deployed to Liferay Tomcat server and in the
console see a message about 1 new portlet being available:

![Figure 7.20: Verifying the output in the Console view](../../images/ide-console-portlet-
available.png)

Open Liferay Portal Home http://localhost:8080/ in most cases and login within
the following credentials:

user: **test@liferay.com**

password: **test**

Click Add &rarr; More, expand the Sample Category and click the Add link next to
the Demo application. You should see the correctly deployed portlet on the page.

![Figure 7.21: Adding a portlet to the page](../../images/ide-add-portlet-to-page.png)

## Importing Existing Projects into Liferay IDE [](id=importing-existing-projects-into-liferay-i-4)

If you have been following the examples of this guide using the Plugins SDK or
have previous plugins developed with it that you want to keep developing with
Liferay IDE then this section is for you. It also shows other options to import.
Specifically it shows how to import from:

1.  Existing Liferay projects that are not in Eclipse workspace.

2. Projects already in Eclipse but not Liferay IDE (don't have Liferay facet or
target runtime).

3. Existing Liferay IDE projects from another version of Liferay IDE or
workspace.

 The following subsections describe the steps for each of them with more detail.

### Importing existing Liferay Project from a Plugins SDK [](id=lp-6-1-dgen07-importing-existing-
liferay-project-from-a-plugins-sdk-0)

An existing Liferay project that has been created by the Plugins SDK but has not yet been added to an Eclipse workspace or have not been added to the current workspace open in Eclipse/Liferay IDE. These projects may or may not have .project or .classpth files. Whether they do or don't we will follow the same steps and use the same wizard.

There are two options to create projects from existing sources, depending on
whether you want to create one single project or multiple projects from the same
SDK. Let's see both in detail.

Note: The two next sections assume that you have Liferay plug-ins SDK directory
structure and you have previously created projects inside of that directory
structure.

#### Create one single project from one plugin in an existing sources [](id=lp-6-1-dgen07-create-
one-single-project-from-one-plugin-in-an-existing-so-0)

This option will create one project for a single plugin that already exists
inside a Plugins SDK.

1. In Eclipse, go to File &rarr; New &rarr; Project... &rarr; Liferay &rarr;
Liferay Project from Existing Source.

	![Figure 7.22: Importing a Liferay Project](../../images/ide-select-liferay-project-from-
existing.png)

    Or you can invoke the same wizard from the Liferay shortcut bar.

	![Figure 7.23: Alternate method of Importing a project](../../images/ide-new-proj-existing-
source.png)

2. Browse to the location of the project folder. Note: the project folder should
be a sub-directory of one of the plugin types, e.g. portlets, hooks, themes,
etc. If not it will not be able to be imported correctly.Once you select the
plugin project folder you should see the plugin type and SDK version values get
updated to correct values. If the SDK is not recent enough or project type is
not correct it will be marked with an error.

	![Figure 7.24: Setting the Project location](../../images/ide-import-project-from-
existing.png)

3. Once you select the plugin project folder you should see the plugin type and
SDK version values get updated to correct values. If the SDK is not recent
enough or project type is not correct it will be marked with an error.

	![Figure 7.25: Verifying the plugin type and version](../../images/ide-plugin-type-and-
version.png)

4. Next you will need to select a Liferay Runtime to configure on the project
once it is imported. If you don't have a Liferay Runtime, use the New... button
to create a new Liferay portal runtime (Tomcat bundle only supported).

	![Figure 7.26: Choosing the target runtime](../../images/ide-target-runtime-server.png)

5.  Click Finish to perform the import.

6. Read the section below on verifying the success of an import process to make
sure that your project was configured correctly as a Liferay IDE project.

#### Create multiple projects for all plugins in a Plugins SDK [](id=lp-6-1-dgen07-create-multiple-
projects-for-all-plugins-in-a-plugins-sdk-0)

This option will transverse an existing Plugins SDK and will allow creating one
project for each of the plugins it finds inside in one single step.

1. In Eclipse go to File &rarr; Import... &rarr; Liferay &rarr; Liferay Plugin
SDK projects.

	![Figure 7.27: Choosing the project type for import](../../images/ide-import-from-plugin-
sdk.png)

2. First you must select the Plugins SDK that you want to import projects from
in the combo box at the top of the wizard.

	![Figure 7.28: Choosing the SDK version](../../images/ide-import-plugins-sdk.png)

3. If you don't have any SDKs configured in Liferay IDE use the *configure* link
to add a setting that points to the Plugins SDK that you want to import projects
from.

4. To configure a Plugins SDK on the Installed SDKs on the pref page just click
*Add* and then Browse to the directory of the Plugins SDK root directory.

5. Once you have a configured Plugins SDK, you can select it in the Combo box
and then the SDK location and version will be filled in. If either are not valid
it will be marked with an error.

	![Figure 7.29: Verifying the SDK version and location](../../images/ide-select-configured-
plugins-sdk.png)

6. After the SDK is selected the list of projects that are available for import
will be shown in the table. If the projects are already in the workspace they
will be disabled. If the project is available for import it will have a empty
checkbox that can be selected.

	![Figure 7.30: Importing a project](../../images/ide-sample-service-builder-portlet-
plugin.png)

6.  Select which projects that you wish to import.

8.  Select the Liferay runtime that you want to setup for the imported projects.

9. If you don't have a liferay runtime you can add one with the *New...* button.

	![Figure 7.31: Selecting your runtime](../../images/ide-target-runtime-server.png)

10.  Click Finish.

11. Read the section below on verifying the success of an import process to make
sure that your project was configured correctly as a Liferay IDE project.

### Importing an existing Eclipse Project that is not aware of the Liferay IDE [](id=lp-6-1-dgen07-
importing-an-existing-eclipse-project-that-is-not-aware-of--0)

If your project is not in your Eclipse workspace, you can use the first set of
steps above. If your project is already in your workspace (see it in project
explorer) but is not yet a Liferay IDE project, the following steps can be used
to convert the project.

1. In Eclipse, right click the eclipse project that you want to convert, select
Liferay &rarr; Convert to Liferay plug-in project.

    Note: If you don't have a convert action available it means the project is
    either already a Liferay IDE project or it is not a faceted project with
    Java and Dynamic Web project facets configured and will need to be
    configured accordingly.

    ![Figure 7.32: Selecting the convert option](../../images/ide-select-convert-to-liferay-
project.png)

2. When the convert dialog wizard opens your project should be auto-selected and
the SDK location and SDK version should be auto-detected. If they are not valid
an error message will be displayed.

	![Figure 7.33: Converting a project](../../images/ide-convert-plugin-project.png)

3. Select the Liferay runtime that you wish to set on the project. If you don't
have a Liferay Runtime define use the *New...* action to create one.

	![Figure 7.34: Selecting the runtime](../../images/ide-target-runtime-server.png)

4.  Click Finish.

5. Read the section below on verifying the success of an import process to make
sure that your project was configured correctly as a Liferay IDE project.

### Importing an existing Liferay IDE project [](id=lp-6-1-dgen07-importing-an-existing-liferay-
ide-project-0)

This section describes the steps that can be followed if you have previously
created or converted a Liferay IDE project in your workspace but it is no longer
in the current workspace there are a couple of options for importing this
project.

1. Open Liferay IDE, go to File &rarr; Import ... &rarr; General &rarr; Existing
Projects into Workspace.

2.  Use option Select root directory, then click Browse.

3.  Select the directory of the previous Liferay IDE project.

4.  Then in the list of projects you should see the one project you selected.

5.  Click Finish.

6. Read the section below on verifying the success of an import process to make
sure that your project was configured correctly as a Liferay IDE project.

If you have any errors, it may be that either the SDK name used in that project
or the runtime id used doesn't exist in your new workspace. You can modify the
SDK name in the Project Properties &rarr; Liferay page and you can modify the
targeted runtime in the Project properties &rarr; Targeted Runtimes page.

### Verifying that the import has succeeded [](id=lp-6-1-dgen07-verifying-that-the-import-has-
succeeded-0)

Follow the following steps to verify that either of the previous import
processes has been successful.

1. Once the project import process is finished, you should see a new project
inside Eclipse and it should have an "L" overlay image to show its a Liferay
project.

	![Figure 7.35: Verifying that the import suceeded](../../images/ide-liferay-project-w-
overlay-image.png)

2. Secondly, to make sure the project is now a *Liferay IDE* project is to check
the target runtime project property (right-click project &rarr; properties
&rarr; target runtimes) and also check the project facets to make sure both
Liferay runtime and Liferay plug-in facets are properly configured.

	![Figure 7.36: Project Facets](../../images/ide-project-facets.png)

Great! You've confirmed that your imported project is good to go.

In the next section, we will explain Developer Studio's Kaleo Designer for Java
and how you can design workflows for enhanced business logic.

## Designing workflows with Kaleo Designer for Java

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Portal includes a workflow engine called Kaleo calls users to
participate in processes designed for them. Kaleo workflows, called *process
definitions* and are essentially specified in XML. The Kaleo workflow engine
supports a host of XML element types to trigger decisive actions in your
business process instances. In addition, you can incorporate scripts and
templates to fine-tune the logic of your process definition. The *Kaleo Workflow
EE* app from Marketplace includes Liferay's *Kaleo Workflow Designer* that lets
you create and modify your portal workflows in your browser. With *Kaleo
Designer for Java* you can design and publish Kaleo workflows right from Liferay
Developer Studio!

![Figure 7.37 Kaleo Designer for Java gives you a powerful environment for
design, modifying, and publishing Kaleo
workflows.](../../images/kaleo-designer-for-java.png)

Kaleo Designer for Java facilitates back-end Java development and scripting to
incorporate in your workflows. Its easy-to-use graphical interface lets you drag
and drop nodes into your workflow. A shortcut on each node gives you easy access
to the node's XML, letting you edit its current implementation to make subtle
modifications or inject new business logic. In addition, Liferay Developer
Studio comes bundled with a [Groovy](http://groovy.codehaus.org/Documentation)
editor (made available by Spring Source), giving you the same rich editing
experience to which you may be accustomed in editing Kaleo Groovy scripts as a
Java developer make Java calls out to your custom business logic APIs and
Liferay's Portal APIs. In Developer Studio, you can leverage editors for
Beanshell, Drl, Javascript, Python, and Ruby scripting languages, and
[FreeMarker](http://freemarker.sourceforge.net/) and
[Velocity](http://velocity.apache.org/engine/releases/velocity-1.5/user-guide.html)
template languages too. Kaleo Designer for Java gives you a rich tool set for
creating/editing workflows, manipulating workflow nodes, and implementing
business logic. But that is not all!

This app gives you the capability to remotely add and update workflow
definitions directly to and from your Liferay server. You can publish your
workflow drafts to your portal by simply dragging the workflow file onto your
portal server listed in your *Servers* view. This Servers view not only shows
your published workflows but also gives you access to workflows already
published to your portal. You can edit any of these existing workflows right in
Developer Studio and republish them on your portal. As you can see, Kaleo
Designer for Java is a robust application for creating, modifying, and
publishing workflows in Liferay Developer Studio.

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

To use Kaleo Designer for Java follow the instructions below to install the
*Kaleo Workflow EE* and the *Kaleo Forms EE* apps. The Kaleo Workflow EE app
includes the `kaleo-forms-portlet`, `kaleo-designer-portlet`, and `kaleo-web
plugins`.

Install Kaleo Designer for Java:

1. Go to [Marketplace](https://www.liferay.com/marketplace).
2. Click on [EE Marketplace](http://www.liferay.com/marketplace/-/mp/category/12729032)
in the left navigation area.
3. Download and install the *Kaleo Workflow EE* app.
4. Download and install the *Kaleo Forms EE* app.

After downloading and installing the Kaleo apps, a *Kaleo Workflows*
folder automatically appears underneath your Liferay server in the Servers
view of Developer Studio.

![Figure 7.38: The Kaleo Workflow application can be found underneath your Liferay server in 
Developer Studio.](../../images/kaleo-1.png)

Developer Studio has retrieved the available workflow definitions that are
running on the portal server. As mentioned previously, the Kaleo application
provides Developer Studio the capability to remotely add or update existing
Kaleo workflow definitions directly to and from the portal server. The *Single
Approver* workflow is the default application and is preinstalled with the Kaleo
apps.

Next, we will dive into creating a workflow diagram using the Kaleo Designer for Java.

### Creating a workflow diagram

Developer Studio adds another dimension to Kaleo workflows by integrating a
creative process for Java developers to modify and enhance their business logic.
Let's go through the Kaleo Designer's features and process for creating a
workflow diagram in Developer Studio.

To view the default Single Approver workflow or your already created workflow
diagram, simply double-click the appropriate node located in the *Kaleo Workflows* folder in the Servers view. This will retrieve the
workflow definition from the Liferay server and you will be able to view your
workflow definition in the designer interface.

---

![](../../images/tip-pen-paper.png)**Note:** You will be prompted on whether or
not to switch to the Kaleo designer perspective. Press *Yes* to enable important
features like the palette toolbar, properties window, and outline view. These will be helpful in designing your workflow.

---

You will notice that your workflow will open up in a *Workflow Diagram* screen,
which is a graphical interface that modifies your workflow XML file. You can
visit *Using Liferay Portal*'s chapter on [Creating new workflow
definitions](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/creating-process-definitio-1) if you would like to learn more about creating and
modifying a workflow definition XML file. Your workflow diagram should appear
similar to the snapshot below:

![Figure 7.39: The Workflow Diagram screen displays the Kaleo workflow you've designed on your 
Liferay Portal.](../../images/kaleo-2.png)

Another helpful feature in the Kaleo Designer for Java is the ability
to create business logic locally, without having to navigate back and forth from
your portal and Developer Studio. We will go into detail later on how to upload
your new workflow to the server.

For a fun example to demonstrate some of the features for the Kaleo Workflow
Designer for Java, we will create a workflow definition for a ticketing process. Are you ready to 
roll? Let's get started!

![Figure 7.40: Liferay's mascots, Ray and Alloy, can be found at http://www.liferay.com/ray.](../../images/kaleo-15.png)

You can create your new workflow definition by selecting the *New* tab, and then
selecting *Liferay Kaleo Workflow*.

![Figure 7.41: You can create a new workflow definition locally by navigating to the appropriate 
tab.](../../images/kaleo-3.png)

A pop-up window will display, prompting you to enter credentials for a new
workflow definition. The snapshot below displays the setup menu for our ticket process workflow definition.

![Figure 7.42: You will need to specify several values and pick a location for your workflow definition to reside.](../../images/kaleo-5.png)

---

![](../../images/tip-pen-paper.png)**Note:** You will need an existing Liferay
project configured in Developer Studio to enable the creation of a new workflow
definition. This makes sense because without a project, there is no use for a
designed workflow.

---

Enter your existing project name and fill in the remaining fields with the text
of your choice. You are also given the luxury of choosing your preferred script
type and template type. This is convenient because you're given the choice of
editing style you're most comfortable with and setting the default script and
template editors to use that syntax. If you click the *Next* button, you're
asked to choose an initial assignment type. You are able to assign a plethora of
options that will customize your task. Here is a snapshot of the options you
have to choose from:

![Figure 7.43: When choosing an assignment type for a task node, you are given multiple options to choose from.](../../images/kaleo-4.png)

When creating assignment types throughout your workflow, you will be prompted
for this same screen.

For this example, we will select *Assign to asset creator* and name the task *Developer*. This 
option will send the workflow to the asset creator when the Developer task node is invoked. Lastly, 
click *Finish* to complete the initial set-up of your workflow definition.

Your workflow diagram will appear graphically in addition to features and
toolbars that allow you to customize your workflow definition. First, we will
explain how the palette works.

#### Palette and floating palette

The *Palette* toolbar allows you to customize your workflow diagram by adding
nodes and transitions. In addition, you can also customize your pointer. A
snapshot of the palette within the workflow diagram is shown below:

![Figure 7.44: The palette toolbar enables you to customize your workflow with additional nodes and transitions.](../../images/kaleo-6.png)

For pointer options, you are given two choices:

- *Select*: the default pointer setting, which is used to select options on
the workflow diagram by simply clicking the icon.

- *Marquee*: used to drag an invisible box around multiple icons. This is very
useful when wanting to select multiple nodes and/or transitions on the workflow diagram.

Furthermore, there is an option to choose *Transition*. This feature connects
existing nodes and describes the transition change between the two nodes.

Lastly, there are five *Nodes* options, which you can select from to add onto
your workflow diagram:

-	*Condition*: creates a new condition node and its transitions.

- *Fork*: forks the workflow into two parallel tasks and creates those tasks'
transitions.

-	*Join*: joins multiple nodes and their transitions into one node.

- *State*: creates a new state node that specifies its name, type, and exit
transition name.

- *Task*: creates a new task node that specifies its assignments, actions,
notifications, and transitions.

For additional information on the features located in the palette, you can
reference the [Kaleo
Designer](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen06-kaleo-designer-0)
section of *Using Liferay Portal*.

For our ticket process workflow diagram, we left off with the simple *Start* and
*End* state nodes connected by the task node *Developer*.

![Figure 7.45: The ticket process' beginning workflow diagram needs some work!](../../images/kaleo-9.png)

After the developer has approved the workflow, it is sent to the quality
assurance teams: *QA* and *QA Management*. The ticket process workflow will need
a fork node to accurately depict this sequence of events.

You can simply click and drag the node you want onto the diagram. After
selecting the fork node and dragging it onto your workflow diagram, a *Create
New Fork Node* menu will appear. You can specify how many tasks to fork by
clicking on the green "plus" sign. You will view a drop-down menu with options
on what you're forking. In addition, you have a checkbox that asks if you would
like to automatically add a join node.

![Figure 7.46: You're able to select what to fork and are given the option to automatically create a Join node.](../../images/kaleo-7.png)

After clicking *Finish*, Developer Studio will place your nodes in a spot you
probably don't like. That's OK, because you can select and drag each node to
your desired location. Our example now looks something like this:

![Figure 7.47: After creating the fork and join nodes, you can position the nodes to your liking.](../../images/kaleo-8.png)

You can see from our example that there are error markings on multiple nodes
within the workflow definition. Error markings are represented by a red "X" in
the bottom left of a node. This means something is wrong or needs to be
specified within our workflow. For help, you can hover your pointer over an
error marker and Studio will display a hint on how to resolve the problem. We'll
correct these error markings in our workflow definition soon.

![Figure 7.48: Error markings notify the user that something is wrong or needs to be specified within a node.](../../images/kaleo-26.png)

Notice when you hover your pointer over a node, a floating palette appears. This
is a convenient feature for quick customization. Here is a snapshot of what a
floating palette appears like on your workflow diagram:

![Figure 7.49: The floating palette appears when you hover your pointer over any node.](../../images/kaleo-10.png)

The floating palette's features and descriptions are listed below:

- *Add*: adds an entity. A common use is to add a new element to the model, such
as a task action or task notification.

- *Change Assignments*: brings up the *Choose Assignment Type* menu.

- *Delete*: deletes an entity. A common use is to delete an element from the
model, such as a task action or task notification.

- *Show in Source*: shows the model element associated with a given part in the
corresponding source editor.

- *Start Transition*: starts transition action.

Obviously, there is still work to be done. We have multiple error markings and
the fork and join nodes aren't connected to anything. Let's change the
assignments for our two new task nodes, QA and QA Management, by using the
floating palette. As we discussed earlier, the *Choose Assignment Type* menu
will appear and you can choose assignment types for the task nodes. After giving
assignments to the new task nodes, the error markers will disappear.

For our ticket process example, we want the the assignment types to be set to
another user that isn't the asset creator. Realistically, tasks involved in a
fork have different assigned users. However, for simplicity, we will assign the
same user for both QA and QA Management. Select the *Change Assignments* tab
from the floating palette. When the *Choose Assignment Type* menu appears,
select *Assign to a specific user*. Then, click *Next* and you have the option
to enter the user's *User-id*, *Screen name*, or *Email address*. We will enter
the user's screen name and then click *Finish*. As you can see from the snapshot
below, we are using the screen name "joe" for the user Joe Bloggs.

![Figure 7.50: You have the ability to assign a task to a specific user of Liferay Portal.](../../images/kaleo-29.png)

You must use a registered user within your Liferay Portal for the task assignments to be correctly assigned. Therefore, you will need to create the user Joe Bloggs. You can learn more about adding users and setting up their credentials in the [Adding users](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen12-adding-users-0) section of *Using Liferay Portal*.

After assigning both QA and QA Management nodes to an assignment, their error
markings will disappear.

![Figure 7.51: After the task nodes are given assignments, their error markings will disappear.](../../images/kaleo-27.png)

Within the workflow diagram, there are other miscellaneous features offered by
Developer Studio. Here is a quick list of some of these small features that
could make a huge difference when designing your workflow: 

1. In the upper right corner of the workflow diagram view, there are options
related to the graphical interface which include zoom options, saving the
diagram as an image, and printing the diagram.

	![Figure 7.52: The Workflow Diagram offers options related to the graphical
	interface.](../../images/kaleo-23.png)

2. You can access more options by right-clicking the graphical interface. You
are presented a menu which appears like this:

	![Figure 7.53: When right-clicking the graphical interface, you are given
	more options for viewing and editing your workflow
	definition.](../../images/kaleo-24.png)

	A cool feature you can access by right-clicking the workflow diagram is the
	*Layout* feature. It allows you to arrange nodes vertically and
	horizontally. This feature makes it easy to organize your workflow quickly
	without having to touch a node.

3. You're able to double-click the names for nodes and transitions and change
them to your liking.

4. Within the workflow diagram, Studio offers an assisted *Surveyor's Level*
that helps you align your nodes exactly parallel with other existing
nodes.

	![Figure 7.54: Studio's Surveyor's Level helps you design your workflow for
	an organized and professional look.](../../images/kaleo-32.png)

<!--CHECK: Not sure if there is a specifc name for the "Surveyor's Level". This
is just a name I use for the instrument-->

You have probably noticed the *Properties* tab below the workflow diagram. Let's
explore what it does and how it can help customize your workflow definition.

#### Properties and Outline features

More cool features that can help you customize your workflow are the
*Properties* and *Outline* options located on the bottom and bottom right of
Developer Studio, respectively. The Properties tab displays the properties
that can be modified for each node. Once you click on an individual node, its
properties appear in the Properties tab. There are five possible sub-tabs that
appear in the Properties window:

- *Actions*: specifies *Name*, *Script Language*, *Execution Type*, and
*Priority* options.

- *Notifications*: defines *Name*, *Template Language*, *Execution Type*, and
*Notification Types*.

- *Assignments*: allows you to change task assignments.

- *Task Timers*: specifies *Name* and *Blocking*.

- *Script*: allows you to choose default script editor and edit script.

- *Other*: defines *Name* and *Description* of current node.

Here is what the Properties window looks like in Developer Studio:

![Figure 7.55: The Properties window gives you multiple sub-tabs to help customize your workflow diagram.](../../images/kaleo-11.png) 

Another useful tool is the Outline view. This gives a top level view of your
entire definition. As you can imagine, workflows can become extremely large over
time and may not be able to fit on your workflow diagram screen. The Outline
view allows you to view your entire workflow definition, no matter how large it
becomes. Below is a snapshot of an outline view in Developer Studio:

![Figure 7.56: The Outline view lets you view your entire workflow definition.](../../images/kaleo-25.png) 

As you can see, the Properties and Outline options make customizing your
workflow easier than ever! Developer Studio offers a useful programming
capability that allows for editing workflow scripts. We will give you a tour of
what Developer Studio has to offer for using workflow scripts!

### Using Workflow Scripts

We will now explain how to use Developer Studio to edit your workflow scripts.
Developer Studio is configured to recognize multiple script languages to let the
developer use what they are most comfortable with. These script languages
include: Beanshell, Drl, Groovy, Javascript, Python, and Ruby. Developer Studio
offers many script editing features that can help you quickly and successfully
complete your workflow diagram.

Let's dive back into the ticket process workflow and create a workflow script.
For most ticketing processes, it is not a guarantee that every ticket will be
selected for fixing. Sometimes, it's more beneficial to not fix the problem.
Therefore, the ticket process would be more valuable if we were able to
incorporate this logic into the workflow definition. Thus, we will create a
condition node that will rely on a Dynamic Data List (DDL) record when deciding
whether or not the ticket is fixable. This condition node will be placed between
the StartNode state node and Developer task node.

You can drag and drop the condition node onto your workflow diagram and a
*Create New Condition Node* menu will appear. You will notice you are able to
choose a script language when editing the condition node. This feature gives
Java developers the power to choose the script language they're most comfortable
with and edit the condition node to their liking. For our current situation, we
want to check the workflow status and send the assignment to the EndNode state
if it doesn't need to be fixed. If it does need to be fixed, we want to send it
to the Developer task node. Thus, when creating a condition node, you're able to
use the drop-down menu and select where your condition transitions should send
your workflow based on its status. Here is a snapshot of the *Create New
Condition Node* menu configured for the ticket process workflow.

![Figure 7.57: When creating a condition node, you're able to set your preferred script language, name, and condition transitions.](../../images/kaleo-12.png) 

You will notice there is an error marking on the condition node. It indicates
that a script must be specified. By selecting the condition node and clicking
*Edit Script* from the Properties window, you will now be able to take full
advantage of the script editing capabilities Developer Studio offers.

![Figure 7.58: You're able to access the *Edit Script* feature by navigating to the *Script* sub-tab inside the Properties view.](../../images/kaleo-13.png)

Furthermore, you can also access the *Edit Script* tool by navigating to your
floating palette or right-clicking the node and selecting *Edit Script*.

Since our default script language was set to Groovy, the Java Groovy editor
will appear. If you'd like to learn more about the Groovy editor, you can visit
the [Groovy User Guide](http://groovy.codehaus.org/User+Guide). Likewise, if you
set the script language to another language, the editor for that specific
language will appear. The editor runs in the context of editing the specific
node you selected. So anything you type into the script editor will get set
inside the script node for the `<condition/>` element.

---

![](../../images/tip-pen-paper.png)**Note:** Developer Studio allows you to use
multiple script editors while modifying the same workflow definition XML file.

---

Also, you will probably notice the palette appears different than the normal
workflow diagram palette. When opening a Java script editor, the palette
includes features that are helpful, which include the following file folders:

-	Context Variables
-	Dynamic Data Lists
-	Roles
-	Scripts
-	Status Updates

These feature folders can be expanded and collapsed by simply clicking the
folder name bars.

Here is a snapshot of how the palette appears with the *Context Variables*
folder open for a view of its options:

![Figure 7.59: The script editor opens a new palette that allows for inserting helpful snippets of 
code.](../../images/kaleo-14.png)

You're able to simply drag and drop any of the files and the code appears
instantaneously in your script editor. There are no compile errors or warning
errors because the editor is running in the context of Liferay Portal. This
means that all of the Liferay Portal APIs are available for you to use. Another
useful feature is the ability to invoke code assist and access built in Kaleo
workflow variables.

To edit the ticket process condition node, we will need to get a hold of the
Dynamic Data List (DDL) record that's being worked on in our workflow process.
To get the DDL record, we need the `serviceContext`. If you would like to learn
more about what the Service Context parameter is used for, you can visit the
[Service Context](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/service-conte-1)
section of Liferay's Developer's Guide.

Taking advantage of Developer Studio's palette features, we will simply drag and
drop the *serviceContext* file onto the script editor, which will grab the
Service Context. Also, we will drag and drop the *ddlRecord* code snippet onto
the editor, which will grab the `ddlRecordId`. The script editor should now look
like this:

![Figure 7.60: You can add snippets of code by simply using the drag-and-drop method from your 
palette.](../../images/kaleo-28.png)

Then we will write the following Java code:

	Field field = ddlRecord.getField("status");

	String status = GetterUtil.getString(field.getValue());
	if (status.contains("not")) {
		returnValue = "No"
	}
	else {
		returnValue = "Yes"
	}

Also, make sure to add the following import: `import com.liferay.portlet.dynamicdatamapping.storage.Field;`. You can do this by typing it with the rest of the imports or you can simply press *Ctrl-Alt-O* to activate an import organizer. Then select the previously mentioned import.

We use the `DDLRecordLocalServiceUtil` API to obtain the `ddlRecordId`. Lastly,
we pull out the status and create parameters for the workflow to follow,
depending on if it will be fixed or not. The script now accurately depicts what
the condition node should accomplish. This code is injected into the workflow's
XML file within the `<condition/>` element.

---

![](../../images/tip-pen-paper.png)**Note:** Make sure to correctly name the
transitions stemming from the condition node. The "No" transition should point
to the EndNode while the "Yes" transition should point to the Developer. If
these are not stated correctly, the DDL record, when invoked, will not know
where to send the workflow status.

---

Here is a snapshot of what our current ticket process workflow looks like after
inserting the condition node:

![Figure 7.61: The ticket process workflow after inserting the condition node.](../../images/kaleo-30.png)

Now, the condition node's workflow script is successfully edited to work
properly with our workflow diagram. Next, we need to create a valid DDL record
for this workflow to successfully run. You may be thinking "How do we set up a
DDL record?" or "How does this DDL record thingy work?". Some of you may even be
jumping up and down screaming "Liferay is da bomb!". Don't worry, all of these
reactions are welcome and will be addressed later in the *Configuring a DDL
record* section. First, let's finish up the overview of what the Kaleo Workflow
for Java has to offer you within Developer Studio!

As you can see from our example, Developer Studio allows for easy customization
of workflow scripts. In the next section, we'll give an overview for creating a
notification and edit it using a template editor. Let's take a peek at how
Developer Studio uses template editors to customize your workflow definition.

### How to take advantage of the bundled template editors

As we stated earlier, Developer Studio comes bundled with Text, Velocity, and
FreeMarker template editors. This provides easy editing for Java developers when
customizing workflow properties like notifications. For a quick overview to show
how Developer Studio maximizes developer efficiency, we will demonstrate how to
set-up and edit Java code using the provided template editors.

To access the template editors, you can click on the node of your choice and in
the *Properties* window, select the *Notifications* sub-tab. You can create a
new notification by clicking the green "plus" sign. You are given multiple
options to choose from, which include:

-	*Name*
-	*Template Language*
-	*Execution Type*
-	*Notification Type*
-	*Notification Transports*
-	*Addresses*

A snapshot is given below illustrating these options:

![Figure 7.62: When creating a notification, Developer Studio offers several options, like choosing 
a template language.](../../images/kaleo-16.png)

If you click the pencil icon, you are directed to the template editor you
selected to create your notification. Similar to the script editor, the template
editor presents different palette features that can be inserted by simply using
the drag-and-drop method. Here is a snapshot to illustrate what the template
editor's palette has to offer:

![Figure 7.63: The template editor's palette has helpful file folders that makes creating 
notifications quick and easy.](../../images/kaleo-17.png)

Because Developer Studio offers a full featured template editor like FreeMarker
or Velocity, content assist is available for you to use right away. For example,
if you are using the FreeMarker editor, content assist will suggest FreeMarker
functions when you are editing your notification. In addition, when you're doing
a FreeMarker variable insertion, the editor gives you all the available
variables that are a part of the Kaleo workflow. So when the notification
executes, Kaleo will inject the FreeMarker Context Variables that you need to
make a notification. You can visit the documentation pages for
[FreeMarker](http://freemarker.org/docs/) and
[Velocity](http://velocity.apache.org/engine/releases/velocity-1.5/user-guide.html)
for more information on how to use each editor's variables and methods.

---

![](../../images/tip-pen-paper.png)**Note:** Similar to script editors,
Developer Studio allows you to use Text, FreeMarker, and Velocity template
editors while modifying the same workflow definition XML file.

---

We will continue on with editing our ticket process workflow by creating an
email notification. First, let's create a new task node which will transition
off our join node. This new task node will hold our email notification. This
process is similar to how we assigned our previous task nodes.

1. Drag a new task node onto the workflow diagram
2. Name the new task node *Project Management* and select *Assign to a specific
user*. Then, click *Next*.
3. For the Screen name, type "joe".
4. Click *Finish*.

Now, create transitions for the Project Management task node. One should
transition from the join node to the Project Management node, which is named
*Passed QA*. The other should transition from the Project Management node to the
EndNode, which is named *Completed*. Here is an updated screenshot of what the
workflow diagram looks like:

![Figure 7.64: This will be the final view for the workflow diagram; however we still have work to do!](../../images/kaleo-31.png)

<!-- I am still editing everything after this point. I may still add a thing or
two above this line, but (for the most part) it's done.-->

For our newly created Project Management task node, we will create an email
notification. As we discussed earlier, click on the Project Management node and
then in the Properties window, select *Notifications*. We will now run through the steps to create our custom email notification:

1. Press the green "plus" symbol to create a new notification.
2. In the *Name* text field, type *ticket process email*.
3. Select the *Freemarker* template language from the drop-down menu.
4. Choose the *On entry* execution type from the drop-down menu.
5. Select *email* from the *Notification Transport* menu.

This is what our Properties window looks like when we're finished:

![Figure 7.65: After editing the notification inside the Properties view, you can access the template editor by selecting the pencil icon.](../../images/kaleo-33.png)

Now, click the pencil icon located beneath the green plus symbol. The FreeMarker
template editor will appear. Insert the following email notification code:

	 <#assign refererPlid = serviceContext.getAttribute("refererPlid")!"">
	 <#assign doAsGroupId = serviceContext.getAttribute("doAsGroupId")!"">
	 <#assign comments = taskComments!"">
	 <#assign portalURL = serviceContext.portalURL!"">
	 <#assign pathCtx = portalUtil.pathContext!"NO_PATH_CTX">
	 <#assign wTasksURL = ""> <#if (portalURL?last_index_of("/") > 6)>
	 <#assign portalURL = portalURL?substring(0,portalURL?index_of("/", 7))>

	 </#if> <#if (portalURL?length > 0) && (refererPlid != "") && (doAsGroupId != "")>
	 <#if (pathCtx?length > 0)> <#assign portalURL = portalURL+pathCtx>
	 </#if> <#assign wTasksURL = portalURL+"/group/control_panel/manage?p_p_id=153&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&doAsGroupId="+doAsGroupId+"&refererPlid="+refererPlid> 
	 </#if>
	 <!-- email body -->
	 <#if (wTasksURL?length > 0)>
	 <!-- personal message to assignee -->
	 <p> Please review the code waiting for you in your workflow tasks. <#if comments != "" >
	 <br />Assignment comment says: <strong>${comments}</strong> </#if>
	 </p>
	 <p> <a href="${wTasksURL}">Click here</a> to see workflow tasks assigned to you. </p>
	 <#else>
	 <!-- general message for all involved -->
	 <p> There is a new submission of ${entryType} waiting for review in the workflow. </p>
	 </#if>
	 <!-- signature -->
	 <p>Sincerely,<br /><strong>Liferay Portal Workflow</strong></p>

For more information on writing email notifications, you can visit the Liferay
blog [Workflow in Action: Kaleo email
notifications](http://www.liferay.com/web/igor.beslic/blog/-/blogs/workflow-in-action-kaleo-email-notifications?_33_redirect=http%3A%2F%2Fwww.liferay.com%2Fweb%2Figor.beslic%2Fblog%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-2%26p_p_col_count%3D1).

You have successfully completed setting up an email notification. Now, when the
Project Management task node is activated in the workflow, Joe Bloggs will
receive a notification email. For the user to receive emails, they must be
registered within Liferay Portal. If you haven't registered Joe Bloggs ("joe")
already, you can visit the [Adding
users](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen12-adding-users-0)
section of *Using Liferay Portal* for instructions. To configure the user's
email, login to the user's account and visit *Control Panel* &rarr; *Server
Administration* &rarr; *Mail* for setup options.

<!-- TODO: Ask about email settings. Shouldn't the name, execution type,
notification type etc. element tags already be inserted after selecting them in
the Notifications tab?-->

As you can see, the use of Velocity and FreeMarker template editors makes
customizing your workflow diagram easier than ever. Next, we'll explain how to
view your workflow definition XML file within Developer Studio.

### Viewing your workflow definition's source code

Sometimes it would be nice to view the workflow definition XML file as source
code to make edits or to view your changes that you've made within Developer
Studio. Of course, since Developer Studio is a Java editor, you can view the
entire workflow definition XML file in source mode. This can be done by
navigating to the bottom left corner of your *Workflow Diagram* view and
selecting *Source*.

![Figure 7.66: Developer Studio allows you to view and edit the source code directly.](../../images/kaleo-20.png)

Here is a list of some cool features Developer Studio offers in Source mode:

- *Section Highlighting*: can be done by selecting a node or transition in your
workflow diagram. Then, using the floating palette or right clicking the entity,
select *Show in source*.

- *Editor Validation*: when typing code in any of the editors, there is an error
check to validate if the code is correct. If the code is not correct, there will
be an error marking in the gutter of the editor. If you click the error marking,
it will give you a hint on how to resolve the problem.

-	*Content Assist*: content assist will suggest editor specific
functions when you are editing code. In addition, when you're doing
a variable insertion, the editor gives you all the available
variables that are a part of the Kaleo workflow. 

Showing your XML file's source code is a great way to keep track of what you've
edited while using Developer Studio's embedded script and template editors.
Also, Developer Studio can be used as a basic Java editor, if you feel more
comfortable editing in source mode.

Let's dive into saving your workflow definition and publishing to your Liferay
server.

### Saving process and publishing to a server

After you've created your new workflow or modified your existing workflow,
you'll need to publish it onto the Liferay server. We'll publish the ticket
process workflow definition onto the Liferay server.

First, a new workflow definition can be published by right-clicking your *Kaleo
Workflows* folder in the *Servers* view and selecting *Upload new workflow*.
Then, you can select your workflow definition by navigating through the package
explorer.

![Figure 7.67: To upload a workflow definition, simply select *Upload new workflow...* from the menu.](../../images/kaleo-18.png)

Another acceptable method for adding a new workflow to the server is the
drag-and-drop method. Simply drag your workflow XML file from your Liferay
Project folder onto the Liferay server.

---

![](../../images/tip-pen-paper.png)**Note:** When right-clicking *Kaleo
Workflows*, you can also select the *Refresh* button. This will update your
Kaleo Workflows folder with the latest workflow versions from Liferay Portal
(e.g. those created or modified in Portal using Kaleo Workflow Designer from the
Kaleo Forms portlet).

---

Finally, the workflow definition you selected is displayed under the
*Kaleo Workflows* folder. For the snapshot below, we published our
completed ticket process workflow definition:

![Figure 7.68: Uploading a new workflow definition is quick and easy.](../../images/kaleo-19.png)

It makes sense that you need to publish a new workflow definition onto the
Liferay server, but why must you re-publish an already existing workflow
definition?

When you make changes to a workflow they're never made immediately available to
the rest of the portal. Actually, when you make changes to a definition and save
them, it saves them to a draft. So you can have multiple iterations on that
draft. For example, you might be working on *Version 1* of your definition and
as you make changes to it, you save them in multiple drafts. Eventually when you
are finished with all of your changes, you publish the workflow triggering
automatic creation of a new version (*Version 2*) of that workflow. The new
version of the workflow is immediately available on the server for users to
associate with asset publication and with their Kaleo Forms.

Saving and publishing your workflow definition in Developer Studio is relatively
easy. Unlike other Java editors, Developer Studio provides the capability to
test your workflow definition by saving your definition XML file in a draft.
Furthermore, you are also able to publish your workflow definition straight to
Liferay Portal for quick and easy configuration.

For a finale of our overview for using Kaleo Designer for Java in Developer
Studio, let's set up the DDL record within Liferay Portal and try out our new
workflow!

### Adding a DDL record and running workflow in Portal

Congratulations! You have successfully used Kaleo Designer for Java to create a
workflow! Now, let's put the finishing touches on your product and put it to the
test in Liferay Portal. Before you can set the DDL for the ticket process
workflow, you'll need to activate the workflow definition. Navigate to the
Control Panel and select *Workflow*. Then, under the *Definitions* tab, click on
the *Actions* tab and select *Activate*.

![Figure 7.69: The workflow definition must be activated before it can be used.](../../images/kaleo-34.png)

Now, let's set the DDL record for our *Valid Fix* condition node.

#### Configuring a DDL record

Liferay makes it extremely easy to set a DDL record. Instead of writing Java
code, you can set a DDL record inside Liferay Portal. Dynamic Data Lists can be
used for many different things, but for our example we will integrate a DDL
record into the ticket process workflow. If you would like to learn more about
Dynamic Data Lists, visit [Using Web Forms and Dynamic Data Lists](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/dynamic-data-lists-in-lifer-1)
located in *Using Liferay Portal*.

Finally, let's set up our DDL and get the ticket process workflow working. You can reference how to specifically create a DDL by visiting the section [Defining data types](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/defining-data-typ-1) in *Using Liferay Portal*.

You will need to add a new DDL named *status*. When adding a new data definition to the status DDL, you'll need to drag and drop the *Select* field from the *Fields* tab. 

![Figure 7.70: You must create a new data definition for your DDL.](../../images/kaleo-35.png)

Then, you will need to access the *Settings* tab and give your field the name *status*. Also, you will need to edit the *Options* to the names of your choice. Make sure to have the word "not" as the value for your "Do not fix" option. Recall the code we inserted for our condition node:

	Field field = ddlRecord.getField("status");

	String status = GetterUtil.getString(field.getValue());
	if (status.contains("not")) {
		returnValue = "No"
	}
	else {
		returnValue = "Yes"
	}

The `getField()` method ingests the status of the DDL you set. Because of this, the name of your field must be named "status" so the values can be recognized when the script is invoked. If the value of your option contains "not", then the workflow is transitioned to the EndNode. However, if the value of your option does not contain the word "not", the workflow is transitioned to the Developer. Therefore, it is very important that you correctly specify the options for your field accordingly. Here is an example of how your settings should appear: 

![Figure 7.71: You must set the correct settings so the data definition can be processed by our workflow definition.](../../images/kaleo-37.png)

After creating the data definition, make sure to select the ticket process workflow for our new Kaleo Forms process to use. Lastly, save the DDL.

Now, our DDL is set for use inside our ticket process workflow! Lastly, we will
use the Kaleo Forms portlet to test out our new workflow definition!

#### Using Kaleo Forms to run new workflow

Using the Kaleo Designer for Java and setting the DDL record, we have now
completed our workflow and it is ready to run! We will test it using the Kaleo
Forms portlet located inside Liferay Portal. You will need to add the Kaleo
Forms portlet to your portal if you have not already done so. You can reference how to use Kaleo Forms, if you have not already done so, by visiting the [Kaleo Forms](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen06-kaleo-forms-0) section in *Using Liferay Portal*.

You will need to create a new *Process* within Kaleo Forms to test our workflow. When creating the new process, make sure to select the *status* entry definition we created. You will also need to give it a name, create an *Initial Form*, and select the ticket process workflow. You can leave the *Workflow Task Forms* blank for our demonstration.

![Figure 7.72: To test the ticket process workflow, you will need to indicate the name, entry definition, and workflow when creating a new process.](../../images/kaleo-41.png)

After saving, you can submit the ticket process by selecting *Submit New* and clicking *Ticket Process*.

![Figure 7.73: Submitting the ticket process will activate the workflow.](../../images/kaleo-40.png)

You're now able to select the DDL and progress throughout the ticket process
using Kaleo Forms. Remember, you will need to sign in as Joe Bloggs to access
some of the tasks. Joe Bloggs should also receive an email when the Project
Management node is activated.

You have successfully created a workflow definition and created a workflow
process within Liferay Portal! You are officially a workflow master!

### Summary

Developer Studio offers a dynamic workflow diagram, which helps you visualize
your workflow and create steps to ensure a successful business process. Also,
the script and template editors give you a convenient workspace that caters to
any developer preference you may have. In addition, Developer Studio's access to
your custom business logic APIs and Liferay Portal APIs makes it simple to
customize your workflow definition. Lastly, the ability to publish directly to
your existing Liferay server makes the configuration process quick and easy.
With all of these great features, the Kaleo Designer for Java in Developer
Studio is a powerful tool that couldn't be easier to use!

<!-- TODO insert link to where Greg's video is located-->

## Conclusion [](id=conclusi-3)

In this chapter we've explored the Liferay IDE and have gone through
step-by-step instructions with regards to installation and setup, creating and
deploying Liferay projects, and importing existing projects into Liferay IDE.
You must admit -- it is a slick tool-set! And we hope you take advantage of it
in using Liferay Portal and developing your Liferay plugins.

Next, we'll get up-close and personal with Liferay's APIs!
