# Creating a Liferay MVC Portlet Project [](id=creating-a-liferay-mvc-portlet-project)

Portlets can be created in a Plugins SDK project or a Liferay Maven 
project. Liferay IDE lets you create either project type using command line 
tools: Ant in the Plugins SDK or Maven Archetype in Maven. 

In this tutorial, you'll learn how to create MVC portlets in a Plugins SDK
project, using Liferay IDE and Ant. To learn how to create Liferay Maven
projects, see the tutorial 
[Creating Liferay Maven plugins from IDE](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-liferay-ide) 
or the tutorial
[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-the-command-lin).
If you're completely new to Liferay, you may first want to go through the 
[Beginning Liferay Development](/develop/learning-paths/-/knowledge_base/6-2/beginning-liferay-development) 
learning path. 

First up is creating a portlet with Liferay IDE, so go ahead and get 
started. 

## Creating a Liferay MVC Portlet in Liferay IDE [](id=creating-a-liferay-mvc-portlet-in-liferay-ide)

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Fill in the *Project name* and *Display name*. Project names must have no
    spaces and it's a best practice to specify them in lowercase. The
    *Display name*, however, can have spaces. You should specify the
    portlet's *Display name* just as you want it displayed to the user. 

3.  Leave the *Use default location* checkbox checked. By default, the
    default location is set to your Plugins SDK. If you'd like to change where 
    your plugin project is saved in your file system, uncheck the box and 
    specify your alternate location. 

    ![Figure 1: Creating portlet projects with Liferay IDE is easy.](../../images/03-new-portlet-project.png)

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type.

5.  Your configured SDK and Liferay Runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click the *Configure SDKs* 
    button to the right of the *Plugins SDK* dropdown menu to open the 
    *Installed Plugin SDKs* management wizard. You can also access the
    *New Server Runtime Environment* wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the *Liferay
    Portal Runtime* dropdown menu. 

6.  Select *Portlet* as your plugin type. Alternatively, you can select 
    *Service Builder Portlet* if you know you want to use Liferay's service 
    builder for your portlet.

7.  If you want to launch the New Portlet Wizard after your project is created, 
    select the checkbox of that name. The New Portlet Wizard guides you in
    creating a custom portlet class.

8.  Click *Next* if you selected Portlet as your plugin type. If you selected 
    Service Builder Portlet as your plugin type, then click *Finish*. 

9.  If you selected Portlet as your plugin type, make sure that the *Liferay
    MVC* framework is selected in the next window and click *Finish*. 

With Liferay IDE, you can create a new plugin project or if you already have a
project, create a new plugin in an existing project. A single Liferay project
can contain multiple plugins. Next you'll see how the same process is done using
the terminal. 

## Creating a Liferay MVC Portlet Using the Terminal [](id=creating-a-liferay-mvc-portlet-using-the-terminal)

Navigate to the `portlets` directory in the terminal and enter the appropriate 
command for your operating system:

1.  In Linux and Mac OS X, enter

        ./create.sh [project-name] ["Portlet Display Name"]

2.  In Windows, enter

        create.bat [project-name] ["Portlet Display Name"]

You should get a `BUILD SUCCESSFUL` message from Ant, and there will now be a
new folder inside of the `portlets` folder in your Plugins SDK. This folder is
your new portlet project. This is where you will be implementing your own
functionality. Notice that the Plugins SDK automatically appends "-portlet" to
the project name when creating this folder.

Alternatively, if you will not be using the Plugins SDK to house your portlet
projects, you can copy your newly created portlet project into your IDE of
choice and work with it there. If you do this, you will need to make sure the
project references some `.jar` files from your Liferay installation, or you may
get compile errors. Since the Ant scripts in the Plugins SDK do this for you
automatically, you don't get these errors when working with the Plugins SDK.

To resolve the dependencies for portlet projects, see the classpath entries in
the `build-common.xml` file in the Plugins SDK project. You can determine from
the `plugin.classpath` and `portal.classpath` entries, which `.jar` files are
necessary to build your newly created portlet project. This is not a recommended
configuration, and it is encouraged that you keep your projects in the Plugins 
SDK. 

+$$$

**Tip**: If you are using a source
control system such as Subversion, CVS, Mercurial, Git, etc., this might be
a good moment to do an initial check-in of your changes. After building the
plugin for deployment, several additional files will be generated that should
*not* be handled by the source control system. 

$$$

A portlet project is made up of at least three components:

1. Java Source. 

2. Configuration files. 

3. Client-side files (`.jsp`, `.css`, `.js`, graphics files, etc.). 

When using Liferay's Plugins SDK, these files are stored in a standard directory
structure:

- `PORTLET-NAME/`
    - `build.xml`
    - `docroot/`
        - `css/`
        - `js/`
        - `META-INF/`
        - `WEB-INF/`
            - `lib/`
            - `src/` - this folder is not created by default.
            - `tld/`
            - `liferay-display.xml`
            - `liferay-plugin-package.properties`
            - `liferay-portlet.xml`
            - `portlet.xml`
            - `web.xml`
        - `icon.png`
        - `view.jsp`

By default, new portlets use the MVCPortlet framework, a light framework that
hides part of the complexity of portlets and makes the most common operations
easier. The default MVCPortlet project uses separate JSPs for each portlet
mode: each of the registered portlet modes has a corresponding JSP with the
same name as the mode. For example, 'edit.jsp' is for edit mode and 'help.jsp'
is for help mode.

The *Java Source* is stored in the `docroot/WEB-INF/src` folder. 

The *Configuration Files* are stored in the `docroot/WEB-INF` folder. Files
stored here include the standard JSR-286 portlet configuration file
`portlet.xml`, as well as three optional Liferay-specific configuration files.
The Liferay-specific configuration files, while optional, are important if your
portlets will be deployed on a Liferay Portal server. Here's a description of
the Liferay-specific files:

- `liferay-display.xml`- Describes the category the portlet appears under in the
  *Add* menu of the Dockbar (the horizontal bar that appears at the top of the
  page to all logged-in users). 
- `liferay-plugin-package.properties`- Describes the plugin to Liferay's hot
  deployer. You can configure Portal Access Control List (PACL) properties,
  `.jar` dependencies, and more. 
- `liferay-portlet.xml`- Describes Liferay-specific enhancements for JSR-286
  portlets installed on a Liferay Portal server. For example, you can set
  an image icon to represent the app, trigger a job for the scheduler, and much
  more. A complete listing of this file's settings is in its DTD in the
  `definitions` folder in the Liferay Portal source code. 

*Client Side Files* are the `.jsp`, `.css`, and `.js` files that you write to
implement your portlet's user interface. These files should go in the `docroot`
folder; `.jsp` files can be placed in the root of the folder, while `.css` and
`.js` files are given their own subfolders in `docroot`. Remember, with portlets
you're only dealing with a portion of the HTML document that is getting returned
to the browser. Any HTML code in your client side files must be free of global
tags like `<html>` or `<head>`. Additionally, namespace all CSS classes and
element IDs to prevent conflicts with other portlets. Liferay provides two
tools, a taglib and API methods, to generate a namespace for you. See the
[Using Portlet Namespacing](/develop/tutorials/-/knowledge_base/6-2/using-portlet-namespacing)
tutorial to learn more about namespacing. 

<!--
For a more detailed description of the files generated and used in a portlet
please refer to the [Anatomy of a Portlet] (https://www.liferay.com/documentation/liferay-portal/6.0/development/-/ai/anatomy-of-a-portlet)
section in the DevGuide.
-->

Congrats! Now you understand the basic structure of Liferay MVC portlets and you
have two handy ways to create them in your bag O'trix!  

## Related Topics [](id=related-topics)

[Beginning Liferay Development](/develop/learning-paths/-/knowledge_base/6-2/beginning-liferay-development)

[Writing a Data-Driven Application](/develop/learning-paths/-/knowledge_base/6-2/writing-a-data-driven-application)

[Creating Liferay Maven Plugins from IDE](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-liferay-ide)

[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-the-command-lin)

