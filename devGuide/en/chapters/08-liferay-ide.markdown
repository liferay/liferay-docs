
# Liferay IDE [](id=liferay-i-4)

Liferay's developers use a variety of tools to develop the product and as a consequence of that they have always tried hard to allow other developers to use any tools they wanted for their own development. Because of this you can develop portals based on Liferay with complex IDEs Eclipse, Netbeans or IntelliJ Idea or just use text editors such as Notepad. You can write your persistence layer directly using SQL and JDBC or you can use advanced object-relational mapping libraries such as hibernate or iBatis.

But while being agnostic is great, specially for more experienced developers who can reuse their existing knowledge, it can be overwhelming for newcomers. For that reason Liferay has also develped specific development tools that can be used to ease the learning curve when developing portlets with Liferay. The most significant of these tools is Liferay IDE, a fully featured Integrated Development Environment based on Eclipse.

Liferay IDE is an extension for the Eclipse platform that supports development of plugin projects for the Liferay Portal platform. It is available as a set of Eclipse plugins installable from an update-site. The latest version supports developing 5 Liferay plugin types: portlets, hooks, layout templates, themes, and ext plugins. Liferay IDE requires the Eclipse Java EE developer package using Helios or later versions.

The first two sections below show how to install and set-up Liferay IDE within your environment. If you are using a copy of Liferay Developer Studio, which comes with Liferay Portal Enterprise Edition, you can skip directly to the section titled *Testing/Launching Liferay Tomcat Server* since it comes already preconfigured.

## Installation [](id=installati-6)

This section is a detailed guide to install Liferay IDE.

### Requirements [](id=lp-6-1-dgen07-requirements-0)

-   Java 5.0 JRE or greater

-   [Eclipse Indigo Java EE (3.7.1)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/indigosr1) or [Eclipse Helios Java EE (3.6.2)](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/heliossr2)

### Installation steps [](id=lp-6-1-dgen07-installation-steps-0)

1.  Install Eclipse Indigo or Helios (unzip download file from above).

2.  Run eclipse executable (eclipse.exe).

3.  When eclipse opens, go to *Help* &rarr; *Install New Software...*.

	![Figure 7.1: Installing new software in Eclipse](../../images/ide-install-new-software.png)

4.  In the *Work with* field, paste in one of the following update sites:

    *Eclipse Indigo Update Site*
    [http://releases.liferay.com/tools/ide/eclipse/indigo/stable/](http://releases.liferay.com/tools/ide/eclipse/indigo/stable/)

    [http://releases.liferay.com/tools/ide/eclipse/indigo/nightly/](http://releases.liferay.com/tools/ide/eclipse/indigo/nightly/)
    (Nightly builds are unstable but contain new features more quickly)

    *Eclipse Helios Update Site*
    [http://releases.liferay.com/tools/ide/eclipse/helios/stable/](http://releases.liferay.com/tools/ide/eclipse/helios/stable/)

    [http://releases.liferay.com/tools/ide/eclipse/helios/nightly/](http://releases.liferay.com/tools/ide/eclipse/helios/nightly/)
    (Nightly builds are unstable but contain new features more quickly)

5.  After pasting in URL press enter.

	![Figure 7.2: Entering the information for Liferay IDE](../../images/ide-install-available-software.png)

6.  The Liferay IDE features should be detected, select it before clicking *Next*.

7.	After calculating dependencies, click Next to complete installation.

8.	After installation restart Eclipse to verify installation is complete.

9.	After restarting Eclipse go to Help &rarr; About and you should see and icon badge for Liferay IDE that shows you have it properly installed.

	![Figure 7.3: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

### Alternative installation [](id=lp-6-1-dgen07-alternative-installation-0)

The next set of instructions describe how to install Liferay IDE without typing the URL of an update site:

1.  Instead of typing in a URL, you can download the lastest version from the releases page. [Eclipse Update Site]( http://www.liferay.com/downloads/liferay-projects/liferay-ide) 

2.  In Add Site dialog, click the *Archive* button and browse to the location of the downloaded zip file.

3.  Installation proceeds the same as previous section.

## Set up [](id=set--4)

This section describes the setup necessary to begin doing Liferay development and testing your developments.

### Requirements [](id=lp-6-1-dgen07-requirements-1)

Before getting started you should satisfy the next requirements:

1.  Liferay Portal 6.0 (6.0.5 or greater) downloaded and unzipped.

2.  Liferay plug-ins SDK (6.0.5 or greater) downloaded and unzipped.

3.  Eclipse IDE for Java EE Developers (Helios version or greater) + Liferay IDE (see the Installation steps).

Note: Earlier versions of Liferay, eg 5.2.x, are not supported yet by the Liferay IDE.

### Liferay Plugins SDK Setup [](id=lp-6-1-dgen07-liferay-plugins-sdk-setup-0)

Before you can begin creating new Liferay plugin projects, a Liferay Plugins SDK and Liferay Portal must be installed and configured in the IDE.

1.  Open Eclipse with Liferay IDE installed.

2.  Open Preference page for Liferay &rarr; Installed SDKs (Go to Window &rarr; Preferences &rarr; Liferay &rarr; Installed Plugin SDKs).

	![Figure 7.4: Creating a Liferay plugin project](../../images/ide-new-project-liferay-project.png)

3.  Add your SDK using the add button which brings up the Add SDK Dialog.

4.	Browse to the location of your Plug-ins SDK installation. The default name is the name of the directory but you can change it if you wish.

5.	Select OK and you should see your SDK in the list of Installed SDKs.

	![Figure 7.5: Installed SDKs](../../images/ide-select-installed-sdk.png)

 Note: multiple SDKs can be added to the preferences but you will need to select at least one SDK to be the default which is represented by the SDK that has the checkbox selected.

### Liferay Portal Tomcat Runtime / Server Setup [](id=lp-6-1-dgen07-liferay-portal-tomcat-runtime--server-setup-0)

1. In Eclipse open the Runtime environments preference page (Go to Window &rarr; Preferences &rarr; Server &rarr; Runtime environments).

	![Figure 7.6: Choosing a runtime environment](../../images/ide-prefs-runtime-env.png)

2. Click Add to add a new Liferay runtime and find *Liferay v6.1 (Tomcat 7)* under the Liferay, Inc. category and click Next.

	![Figure 7.7: Adding a Liferay runtime](../../images/ide-create-new-local-server.png)

3. Click Browse and select the location of the liferay-portal-6.1.x directory.

	![Figure 7.8: Browsing to your Liferay portal directory](../../images/ide-select-liferay-portal-directory.png)

4. Once you have selected the Liferay portal directory, if a bundle JRE is present, this bundle will be automatically selected as the JRE to use for launching the server. However, if there is no JRE bundle (Mac and Linux users) then you will need to select the JRE to use for launch.

	![Figure 7.9: Choosing the JRE bundle](../../images/ide-server-jre.png)

5. Click finish and you should see the Liferay portal runtime in the list of runtimes in the preference page.

	![Figure 7.10: Liferay portal in the list of available runtimes](../../images/ide-add-server-runtime.png)

6. Click ok to save the runtime preferences.

7. If you didn't choose to create a server you will need to create one from the servers view before you can test the server.

	![Figure 7.11: Adding a runtime to the Servers view](../../images/ide-add-new-server.png)

8. Find the Liferay, Inc category and select the Liferay v6 Server and choose the Liferay v6 Runtime that you had previously created.

	![Figure 7.12: Selecting Liferay for the Servers view](../../images/ide-define-new-server-type.png)

## Testing/Launching Liferay Tomcat Server [](id=testing-launching-liferay-tomcat-serv-4)

Once your Liferay Portal Server setup is complete you can launch the server from the servers tab of Eclipse. Simply select the server and then click on the green *Start the Server button.* Once the server is running a red *Terminate* button will appear which you can use to stop the server.

![Figure 7.13: You can start Liferay using either the icon, or by right clicking on the server and selecting the option from the menu](../../images/ide-start-server-debug-mode.png)

Alternatively, you can start/stop the server by selecting *Run* or *Terminate* from the Run menu of Eclipse.

Once the server is finish launching you can open the portal home from the context menu of the Liferay Tomcat server in the Servers view:

![Figure 7.14: Opening Portal Home from within Liferay IDE](../../images/ide-open-portal-home.png)

## Creating New Liferay Projects [](id=creating-new-liferay-projec-4)

Now that an SDK and portal server have been configured you can create a new Liferay plug-in project. Go to File &rarr; New Project... &rarr; Liferay &rarr; Liferay Plug-in Project. 

![Figure 7.15: Creating a plugin project](../../images/ide-new-project-liferay-project.png)

In the Liferay project wizard page, the newly configured SDK and Liferay Runtime should be pre-selected. Also the plugin type *portlet* is the default. Simply type in a project name and cooresponding display name and click Finish.

![Figure 7.16: Entering the information for the new project](../../images/ide-new-liferay-project.png)

If it worked you should see a new plugin project in the package explorer. 

![Figure 7.17: Viewing the new project in the Package Explorer view](../../images/ide-package-explorer-demo-portlet.png)

## Deploying New Liferay Projects to Liferay Server [](id=deploying-new-liferay-projects-to-liferay-serv-4)

Now with the newly created project, right-click the Liferay Server in the Servers view and select *Add and Remove...*.

![Figure 7.18: Deploying a project to the server](../../images/ide-servers-add-and-remove.png)

Select the new project and click *Add* to move it to be deployed to the server. 

![Figure 7.19: Choosing which project to deploy](../../images/ide-add-resource-to-server.png)

and then click *Finish*.

You should see the project get deployed to Liferay Tomcat server and in the console see a message about 1 new portlet being available:

![Figure 7.20: Verifying the output in the Console view](../../images/ide-console-portlet-available.png)

Open Liferay Portal Home http://localhost:8080/ in most cases and login within the following credentials:

user: **test@liferay.com**

password: **test**

Click Add &rarr; More, expand the Sample Category and click the Add link next to the Demo application. You should see the correctly deployed portlet on the page.

![Figure 7.21: Adding a portlet to the page](../../images/ide-add-portlet-to-page.png)

## Importing Existing Projects into Liferay IDE [](id=importing-existing-projects-into-liferay-i-4)

If you have been following the examples of this guide using the Plugins SDK or have previous plugins developed with it that you want to keep developing with Liferay IDE then this section is for you. It also shows other options to import. Specifically it shows how to import from:

1.  Existing Liferay projects that are not in Eclipse workspace.

2.  Projects already in Eclipse but not Liferay IDE (don't have Liferay facet or target runtime).

3.  Existing Liferay IDE projects from another version of Liferay IDE or workspace.

 The following subsections describe the steps for each of them with more detail.

### Importing existing Liferay Project from a Plugins SDK [](id=lp-6-1-dgen07-importing-existing-liferay-project-from-a-plugins-sdk-0)

An existing Liferay project that has been created by the Plugins SDK but has not yet been added to an Eclipse workspace or have not been added to the current workspace open in Eclipse/Liferay IDE. These projects may or may not have .project or .classpath files. Whether they do or don’t we will follow the same steps and use the same wizard.

There are two options to create projects from existing sources, depending on whether you want to create one single project or multiple projects from the same SDK. Let's see both in detail.

Note: The two next sections assume that you have Liferay plug-ins SDK directory structure and you have previously created projects inside of that directory structure.

#### Create one single project from one plugin in an existing sources [](id=lp-6-1-dgen07-create-one-single-project-from-one-plugin-in-an-existing-so-0)

This option will create one project for a single plugin that already exists inside a Plugins SDK.

1.  In Eclipse, go to File &rarr; New &rarr; Project... &rarr; Liferay &rarr; Liferay Project from Existing Source.

	![Figure 7.22: Importing a Liferay Project](../../images/ide-select-liferay-project-from-existing.png)

    Or you can invoke the same wizard from the Liferay shortcut bar.

	![Figure 7.23: Alternate method of Importing a project](../../images/ide-new-proj-existing-source.png)

2.  Browse to the location of the project folder. Note: the project folder should be a sub-directory of one of the plugin types, e.g. portlets, hooks, themes, etc. If not it will not be able to be imported correctly.Once you select the plugin project folder you should see the plugin type and SDK version values get updated to correct values. If the SDK is not recent enough or project type is not correct it will be marked with an error.

	![Figure 7.24: Setting the Project location](../../images/ide-import-project-from-existing.png)

3.  Once you select the plugin project folder you should see the plugin type and SDK version values get updated to correct values. If the SDK is not recent enough or project type is not correct it will be marked with an error.

	![Figure 7.25: Verifying the plugin type and version](../../images/ide-plugin-type-and-version.png)

4.  Next you will need to select a Liferay Runtime to configure on the project once it is imported. If you don't have a Liferay Runtime, use the New... button to create a new Liferay portal runtime (Tomcat bundle only supported).

	![Figure 7.26: Choosing the target runtime](../../images/ide-target-runtime-server.png)

5.  Click Finish to perform the import.

6.  Read the section below on verifying the success of an import process to make sure that your project was configured correctly as a Liferay IDE project.

#### Create multiple projects for all plugins in a Plugins SDK [](id=lp-6-1-dgen07-create-multiple-projects-for-all-plugins-in-a-plugins-sdk-0)

This option will transverse an existing Plugins SDK and will allow creating one project for each of the plugins it finds inside in one single step.

1.  In Eclipse go to File &rarr; Import... &rarr; Liferay &rarr; Liferay Plugin SDK projects.

	![Figure 7.27: Choosing the project type for import](../../images/ide-import-from-plugin-sdk.png)

2.  First you must select the Plugins SDK that you want to import projects from in the combo box at the top of the wizard.

	![Figure 7.28: Choosing the SDK version](../../images/ide-import-plugins-sdk.png)

3.  If you don't have any SDKs configured in Liferay IDE use the *configure* link to add a setting that points to the Plugins SDK that you want to import projects from.

4.  To configure a Plugins SDK on the Installed SDKs on the pref page just click *Add* and then Browse to the directory of the Plugins SDK root directory.

5.  Once you have a configured Plugins SDK, you can select it in the Combo box and then the SDK location and version will be filled in. If either are not valid it will be marked with an error.

	![Figure 7.29: Verifying the SDK version and location](../../images/ide-select-configured-plugins-sdk.png)

6.  After the SDK is selected the list of projects that are available for import will be shown in the table. If the projects are already in the workspace they will be disabled. If the project is available for import it will have a empty checkbox that can be selected.

	![Figure 7.30: Importing a project](../../images/ide-sample-service-builder-portlet-plugin.png)

6.  Select which projects that you wish to import.

8.  Select the Liferay runtime that you want to setup for the imported projects.

9.  If you don't have a liferay runtime you can add one with the *New...* button.

	![Figure 7.31: Selecting your runtime](../../images/ide-target-runtime-server.png)

10.  Click Finish.

11. Read the section below on verifying the success of an import process to make sure that your project was configured correctly as a Liferay IDE project.

### Importing an existing Eclipse Project that is not aware of the Liferay IDE [](id=lp-6-1-dgen07-importing-an-existing-eclipse-project-that-is-not-aware-of--0)

If your project is not in your Eclipse workspace, you can use the first set of steps above. If your project is already in your workspace (see it in project explorer) but is not yet a Liferay IDE project, the following steps can be used to convert the project.

1.  In Eclipse, right click the eclipse project that you want to convert, select Liferay &rarr; Convert to Liferay plug-in project.

    Note: If you don't have a convert action available it means the project is either already a Liferay IDE project or it is not a faceted project with Java and Dynamic Web project facets configured and will need to be configured accordingly.

    ![Figure 7.32: Selecting the convert option](../../images/ide-select-convert-to-liferay-project.png)

2.  When the convert dialog wizard opens your project should be auto-selected and the SDK location and SDK version should be auto-detected. If they are not valid an error message will be displayed.

	![Figure 7.33: Converting a project](../../images/ide-convert-plugin-project.png)

3.  Select the Liferay runtime that you wish to set on the project. If you don't have a Liferay Runtime define use the *New...* action to create one.

	![Figure 7.34: Selecting the runtime](../../images/ide-target-runtime-server.png)

4.  Click Finish.

5.  Read the section below on verifying the success of an import process to make sure that your project was configured correctly as a Liferay IDE project.

### Importing an existing Liferay IDE project [](id=lp-6-1-dgen07-importing-an-existing-liferay-ide-project-0)

This section describes the steps that can be followed if you have previously created or converted a Liferay IDE project in your workspace but it is no longer in the current workspace there are a couple of options for importing this project.

1.  Open Liferay IDE, go to File &rarr; Import ... &rarr; General &rarr; Existing Projects into Workspace.

2.  Use option Select root directory, then click Browse.

3.  Select the directory of the previous Liferay IDE project.

4.  Then in the list of projects you should see the one project you selected.

5.  Click Finish.

6.  Read the section below on verifying the success of an import process to make sure that your project was configured correctly as a Liferay IDE project.

If you have any errors, it may be that either the SDK name used in that project or the runtime id used doesn't exist in your new workspace. You can modify the SDK name in the Project Properties &rarr; Liferay page and you can modify the targeted runtime in the Project properties &rarr; Targeted Runtimes page.

### Verifying that the import has succeeded [](id=lp-6-1-dgen07-verifying-that-the-import-has-succeeded-0)

Follow the following steps to verify that either of the previous import processes has been successful.

1.  Once the project import process is finished, you should see a new project inside Eclipse and it should have an "L" overlay image to show its a Liferay project.

	![Figure 7.35: Verifying that the import suceeded](../../images/ide-liferay-project-w-overlay-image.png)

2.  Secondly, to make sure the project is now a *Liferay IDE* project is to check the target runtime project property (right-click project &rarr; properties &rarr; target runtimes) and also check the project facets to make sure both Liferay runtime and Liferay plug-in facets are properly configured.

	![Figure 7.36: Project Facets](../../images/ide-project-facets.png)

Great! You've confirmed that your imported project is good to go.

## Conclusion [](id=conclusi-3)

In this chapter we've explored the Liferay IDE and have gone through step-by-step instructions with regards to installation and setup, creating and deploying Liferay projects, and importing existing projects into Liferay IDE. You must admit -- it is a slick tool-set! And we hope you take advantage of it in using Liferay Portal and developing your Liferay plugins.

Next, we'll get up-close and personal with Liferay's APIs!
