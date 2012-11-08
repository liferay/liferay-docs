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

	![Figure 8.1: Installing new software in Eclipse](../../images/ide-install-new-software.png)

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

	![Figure 8.2: Entering the information for Liferay IDE](../../images/ide-install-available-
software.png)

6. The Liferay IDE features should be detected, select it before clicking
*Next*.

7.	After calculating dependencies, click Next to complete installation.

8.	After installation restart Eclipse to verify installation is complete.

9. After restarting Eclipse go to Help &rarr; About and you should see and icon
badge for Liferay IDE that shows you have it properly installed.

	![Figure 8.3: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

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

	![Figure 8.4: Creating a Liferay plugin project](../../images/ide-new-project-liferay-
project.png)

3.  Add your SDK using the add button which brings up the Add SDK Dialog.

4. Browse to the location of your Plug-ins SDK installation. The default name is
the name of the directory but you can change it if you wish.

5.	Select OK and you should see your SDK in the list of Installed SDKs.

	![Figure 8.5: Installed SDKs](../../images/ide-select-installed-sdk.png)

 Note: multiple SDKs can be added to the preferences but you will need to select
 at least one SDK to be the default which is represented by the SDK that has the
 checkbox selected.

### Liferay Portal Tomcat Runtime / Server Setup [](id=lp-6-1-dgen07-liferay-portal-tomcat-runtime--server-setup-0)

1. In Eclipse open the Runtime environments preference page (Go to Window &rarr;
Preferences &rarr; Server &rarr; Runtime environments).

	![Figure 8.6: Choosing a runtime environment](../../images/ide-prefs-runtime-env.png)

2. Click Add to add a new Liferay runtime and find *Liferay v6.1 (Tomcat 7)*
under the Liferay, Inc. category and click Next.

	![Figure 8.7: Adding a Liferay runtime](../../images/ide-create-new-local-server.png)

3. Click Browse and select the location of the liferay-portal-6.1.x directory.

	![Figure 8.8: Browsing to your Liferay portal directory](../../images/ide-select-liferay-
portal-directory.png)

4. Once you have selected the Liferay portal directory, if a bundle JRE is
present, this bundle will be automatically selected as the JRE to use for
launching the server. However, if there is no JRE bundle (Mac and Linux users)
then you will need to select the JRE to use for launch.

	![Figure 8.9: Choosing the JRE bundle](../../images/ide-server-jre.png)

5. Click finish and you should see the Liferay portal runtime in the list of
runtimes in the preference page.

	![Figure 8.10: Liferay portal in the list of available runtimes](../../images/ide-add-
server-runtime.png)

6. Click ok to save the runtime preferences.

7. If you didn't choose to create a server you will need to create one from the
servers view before you can test the server.

	![Figure 8.11: Adding a runtime to the Servers view](../../images/ide-add-new-server.png)

8. Find the Liferay, Inc category and select the Liferay v6 Server and choose
the Liferay v6 Runtime that you had previously created.

	![Figure 8.12: Selecting Liferay for the Servers view](../../images/ide-define-new-server-
type.png)

## Testing/Launching Liferay Tomcat Server [](id=testing-launching-liferay-tomcat-serv-4)

Once your Liferay Portal Server setup is complete you can launch the server from
the servers tab of Eclipse. Simply select the server and then click on the green
*Start the Server button.* Once the server is running a red *Terminate* button
will appear which you can use to stop the server.

![Figure 8.13: You can start Liferay using either the icon, or by right clicking on the server and 
selecting the option from the menu](../../images/ide-start-server-debug-mode.png)

Alternatively, you can start/stop the server by selecting *Run* or *Terminate*
from the Run menu of Eclipse.

Once the server is finish launching you can open the portal home from the
context menu of the Liferay Tomcat server in the Servers view:

![Figure 8.14: Opening Portal Home from within Liferay IDE](../../images/ide-open-portal-home.png)

## Creating New Liferay Projects [](id=creating-new-liferay-projec-4)

Now that an SDK and portal server have been configured you can create a new
Liferay plug-in project. Go to File &rarr; New Project... &rarr; Liferay &rarr;
Liferay Plug-in Project.

![Figure 8.15: Creating a plugin project](../../images/ide-new-project-liferay-project.png)

In the Liferay project wizard page, the newly configured SDK and Liferay Runtime
should be pre-selected. Also the plugin type *portlet* is the default. Simply
type in a project name and cooresponding display name and click Finish.

![Figure 8.16: Entering the information for the new project](../../images/ide-new-liferay-
project.png)

If it worked you should see a new plugin project in the package explorer. 

![Figure 8.17: Viewing the new project in the Package Explorer view](../../images/ide-package-
explorer-demo-portlet.png)

## Deploying New Liferay Projects to Liferay Server [](id=deploying-new-liferay-projects-to-liferay-serv-4)

Now with the newly created project, right-click the Liferay Server in the
Servers view and select *Add and Remove...*.

![Figure 8.18: Deploying a project to the server](../../images/ide-servers-add-and-remove.png)

Select the new project and click *Add* to move it to be deployed to the server. 

![Figure 8.19: Choosing which project to deploy](../../images/ide-add-resource-to-server.png)

and then click *Finish*.

You should see the project get deployed to Liferay Tomcat server and in the
console see a message about 1 new portlet being available:

![Figure 8.20: Verifying the output in the Console view](../../images/ide-console-portlet-
available.png)

Open Liferay Portal Home http://localhost:8080/ in most cases and login within
the following credentials:

user: **test@liferay.com**

password: **test**

Click Add &rarr; More, expand the Sample Category and click the Add link next to
the Demo application. You should see the correctly deployed portlet on the page.

![Figure 8.21: Adding a portlet to the page](../../images/ide-add-portlet-to-page.png)

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

### Importing existing Liferay Project from a Plugins SDK [](id=lp-6-1-dgen07-importing-existing-liferay-project-from-a-plugins-sdk-0)

An existing Liferay project that has been created by the Plugins SDK but has not yet been added to an Eclipse workspace or have not been added to the current workspace open in Eclipse/Liferay IDE. These projects may or may not have .project or .classpth files. Whether they do or don't we will follow the same steps and use the same wizard.

There are two options to create projects from existing sources, depending on
whether you want to create one single project or multiple projects from the same
SDK. Let's see both in detail.

Note: The two next sections assume that you have Liferay plug-ins SDK directory
structure and you have previously created projects inside of that directory
structure.

#### Create one single project from one plugin in an existing sources [](id=lp-6-1-dgen07-create-one-single-project-from-one-plugin-in-an-existing-so-0)

This option will create one project for a single plugin that already exists
inside a Plugins SDK.

1. In Eclipse, go to File &rarr; New &rarr; Project... &rarr; Liferay &rarr;
Liferay Project from Existing Source.

	![Figure 8.22: Importing a Liferay Project](../../images/ide-select-liferay-project-from-
existing.png)

    Or you can invoke the same wizard from the Liferay shortcut bar.

	![Figure 8.23: Alternate method of Importing a project](../../images/ide-new-proj-existing-
source.png)

2. Browse to the location of the project folder. Note: the project folder should
be a sub-directory of one of the plugin types, e.g. portlets, hooks, themes,
etc. If not it will not be able to be imported correctly.Once you select the
plugin project folder you should see the plugin type and SDK version values get
updated to correct values. If the SDK is not recent enough or project type is
not correct it will be marked with an error.

	![Figure 8.24: Setting the Project location](../../images/ide-import-project-from-
existing.png)

3. Once you select the plugin project folder you should see the plugin type and
SDK version values get updated to correct values. If the SDK is not recent
enough or project type is not correct it will be marked with an error.

	![Figure 8.25: Verifying the plugin type and version](../../images/ide-plugin-type-and-
version.png)

4. Next you will need to select a Liferay Runtime to configure on the project
once it is imported. If you don't have a Liferay Runtime, use the New... button
to create a new Liferay portal runtime (Tomcat bundle only supported).

	![Figure 8.26: Choosing the target runtime](../../images/ide-target-runtime-server.png)

5.  Click Finish to perform the import.

6. Read the section below on verifying the success of an import process to make
sure that your project was configured correctly as a Liferay IDE project.

#### Create multiple projects for all plugins in a Plugins SDK [](id=lp-6-1-dgen07-create-multiple-projects-for-all-plugins-in-a-plugins-sdk-0)

This option will transverse an existing Plugins SDK and will allow creating one
project for each of the plugins it finds inside in one single step.

1. In Eclipse go to File &rarr; Import... &rarr; Liferay &rarr; Liferay Plugin
SDK projects.

	![Figure 8.27: Choosing the project type for import](../../images/ide-import-from-plugin-
sdk.png)

2. First you must select the Plugins SDK that you want to import projects from
in the combo box at the top of the wizard.

	![Figure 8.28: Choosing the SDK version](../../images/ide-import-plugins-sdk.png)

3. If you don't have any SDKs configured in Liferay IDE use the *configure* link
to add a setting that points to the Plugins SDK that you want to import projects
from.

4. To configure a Plugins SDK on the Installed SDKs on the pref page just click
*Add* and then Browse to the directory of the Plugins SDK root directory.

5. Once you have a configured Plugins SDK, you can select it in the Combo box
and then the SDK location and version will be filled in. If either are not valid
it will be marked with an error.

	![Figure 8.29: Verifying the SDK version and location](../../images/ide-select-configured-
plugins-sdk.png)

6. After the SDK is selected the list of projects that are available for import
will be shown in the table. If the projects are already in the workspace they
will be disabled. If the project is available for import it will have a empty
checkbox that can be selected.

	![Figure 8.30: Importing a project](../../images/ide-sample-service-builder-portlet-
plugin.png)

6.  Select which projects that you wish to import.

8.  Select the Liferay runtime that you want to setup for the imported projects.

9. If you don't have a liferay runtime you can add one with the *New...* button.

	![Figure 8.31: Selecting your runtime](../../images/ide-target-runtime-server.png)

10.  Click Finish.

11. Read the section below on verifying the success of an import process to make
sure that your project was configured correctly as a Liferay IDE project.

### Importing an existing Eclipse Project that is not aware of the Liferay IDE [](id=lp-6-1-dgen07-importing-an-existing-eclipse-project-that-is-not-aware-of--0)

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

    ![Figure 8.32: Selecting the convert option](../../images/ide-select-convert-to-liferay-
project.png)

2. When the convert dialog wizard opens your project should be auto-selected and
the SDK location and SDK version should be auto-detected. If they are not valid
an error message will be displayed.

	![Figure 8.33: Converting a project](../../images/ide-convert-plugin-project.png)

3. Select the Liferay runtime that you wish to set on the project. If you don't
have a Liferay Runtime define use the *New...* action to create one.

	![Figure 8.34: Selecting the runtime](../../images/ide-target-runtime-server.png)

4.  Click Finish.

5. Read the section below on verifying the success of an import process to make
sure that your project was configured correctly as a Liferay IDE project.

### Importing an existing Liferay IDE project [](id=lp-6-1-dgen07-importing-an-existing-liferay-ide-project-0)

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

### Verifying that the import has succeeded [](id=lp-6-1-dgen07-verifying-that-the-import-has-succeeded-0)

Follow the following steps to verify that either of the previous import
processes has been successful.

1. Once the project import process is finished, you should see a new project
inside Eclipse and it should have an "L" overlay image to show its a Liferay
project.

	![Figure 8.35: Verifying that the import suceeded](../../images/ide-liferay-project-w-
overlay-image.png)

2. Secondly, to make sure the project is now a *Liferay IDE* project is to check
the target runtime project property (right-click project &rarr; properties
&rarr; target runtimes) and also check the project facets to make sure both
Liferay runtime and Liferay plug-in facets are properly configured.

	![Figure 8.36: Project Facets](../../images/ide-project-facets.png)

Great! You've confirmed that your imported project is good to go.

In the next section, we will explain Developer Studio's Kaleo Designer for Java
and how you can design workflows for enhanced business logic.

## Designing workflows with Kaleo Designer for Java [](id=lp-6-1-dgen08-designing-workflows-with-kaleo-designer-for-java-0)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Portal includes a workflow engine called Kaleo which calls users to
participate in processes designed for them. Kaleo workflows, called *process
definitions*, are essentially one single XML document. The Kaleo workflow engine
supports a host of XML element types to trigger decisive actions in your
business process instances. In addition, you can incorporate scripts and
templates to fine-tune the logic of your process definition. The *Kaleo Forms
EE* app from Marketplace includes Liferay's *Kaleo Workflow Designer* that lets
you create and modify portal workflows in your browser. With *Kaleo Designer for
Java*, you can design and publish Kaleo workflows from Liferay Developer Studio!

![Figure 8.37: Kaleo Designer for Java gives you a powerful environment for
designing, modifying, and publishing Kaleo
workflows.](../../images/kaleo-designer-for-java.png)

Kaleo Designer for Java facilitates back-end Java development and scripting to
incorporate in your workflows. Its easy-to-use graphical interface lets you drag
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

### Creating a workflow [](id=lp-6-1-dgen08-creating-a-workflow-0)

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

#### Palette and floating palette [](id=lp-6-1-dgen08-palette-and-floating-palette-0)

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

#### Workflow Diagram features [](id=lp-6-1-dgen08-workflow-diagram-features-0)

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

#### Properties View and Outline Views [](id=lp-6-1-dgen08-properties-view-and-outline-views-0)

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

### Using Workflow Scripts [](id=lp-6-1-dgen08-using-workflow-scripts-0)

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

### Leveraging template editors for notifications [](id=lp-6-1-dgen08-leveraging-template-editors-for-notifications-0)

Designer lets you leverage FreeMarker and Velocity editors in customizing
templates for your workflow notifications. A FreeMarker editor comes bundled
with Developer Studio. For editing Velocity templates, you'll need to install an
editor.

#### Add-on: Install Velocity Editor Support [](id=lp-6-1-dgen08-add-on-install-velocity-editor-support-0)

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

#### Creating notifications [](id=lp-6-1-dgen08-creating-notifications-0)

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

Insert FreeMarker code into the FreeMarker editor to specify your
email notification template. 

![Figure 8.66: You can edit your email template using the Freemarker
editor.](../../images/kaleo-42.png)

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

#### Workflow Context and Service Context Variables [](id=lp-6-1-dgen08-workflow-context-and-service-context-variables-0)

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

### Viewing workflow definition XML source [](id=lp-6-1-dgen08-viewing-workflow-definition-xml-source-0)

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

### Publishing workflows to the server [](id=lp-6-1-dgen08-publishing-workflows-to-the-server-0)

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

### Using workflows in Portal [](id=lp-6-1-dgen08-using-workflows-in-portal-0)

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

### Using DDLs with workflows [](id=lp-6-1-dgen08-using-ddls-with-workflows-0)

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

#### Using Kaleo Forms to run workflows [](id=lp-6-1-dgen08-using-kaleo-forms-to-run-workflows-0)

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

## Conclusion [](id=conclusi-3)

In this chapter we've explored the Liferay IDE and have gone through
step-by-step instructions with regards to installation and setup, creating and
deploying Liferay projects, and importing existing projects into Liferay IDE.
You must admit -- it is a slick tool-set! And we hope you take advantage of it
in using Liferay Portal and developing your Liferay plugins.

Next, we'll get up-close and personal with Liferay's APIs!
<!-- Please leave the newline below this line -->

