# Creating a Liferay MVC Portlet Project

Portlets can be created in a Plugins SDK project or a Liferay Maven 
project. Liferay IDE lets you create either project type using command line 
tools: Ant in the Plugins SDK or Maven Archetype in Maven. 

In this tutorial, you'll learn how to create  MVC portlets in a Plugins SDK 
project, using Liferay IDE and Ant, from the command line. To learn how to 
create Liferay Maven projects, see the tutorial 
 [Creating Liferay Maven plugins from IDE](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial [Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial).
If you're completely new to Liferay, you can follow along with the 
<!-- URL will need changed to proper one once the site is changed to dev.liferay.com -->
 [Beginning Liferay Development](/learning-paths/-/knowledge_base/beginning-liferay-development-lp-6-2-develop-learnpath) 
learning path.

First up is creating a portlet with Develper Studio, so go ahead and get 
started. 

## Creating a MVC Portlet In Developer Studio

Although the example below creates a portlet named *My Greeting*, the steps 
apply to any newly created portlet:

1.  Go to File &rarr; New &rarr; Liferay Project. 

2.  Fill in the *Project name* and *Display name* with *my-greeting-portlet* and
    *My Greeting*, respectively. 

3.  Leave the *Use default location* checkbox checked. By default, the
	default location is set to your current workspace. If you'd like to change
	where your plugin project is saved in your file system, uncheck the box and
	specify your alternate location. 

    ![Figure 1: Creating portlet projects with Liferay IDE is easy.](../../images/03-new-portlet-project.png)

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type.

5.  Your configured SDK and Liferay Runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click *Configure SDKs* to
    open the *Installed Plugin SDKs* management wizard. You can also access the
    *New Server Runtime Environment* wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the *Liferay
    Portal Runtime* dropdown menu. 

6.  Select *Portlet* as your Plugin type. 

7.  Click *Next*. 

8. In the next window, make sure that the *Liferay MVC* framework is selected
   and click *Finish*. 

With Developer Studio, you can create a new plugin project or if you already
have a project, create a new plugin in an existing project. A single Liferay
project can contain multiple plugins. Next you'll see how the same process is 
done using the terminal.

## Creating a MVC Portlet Using the Terminal 

Navigate to the `portlets` directory in the terminal and enter the appropriate 
command for your operating system:

1.  In Linux and Mac OS X, enter

        ./create.sh my-greeting "My Greeting"

2.  In Windows, enter

        create.bat my-greeting "My Greeting"

You should get a BUILD SUCCESSFUL message from Ant, and there will now be a new
folder inside of the `portlets` folder in your Plugins SDK. This folder is your
new portlet project. This is where you will be implementing your own
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

---

 ![tip](../../images/tip-pen-paper.png) **Tip**: If you are using a source
 control system such as Subversion, CVS, Mercurial, Git, etc., this might be
 a good moment to do an initial check-in of your changes. After building the
 plugin for deployment, several additional files will be generated that should
 *not* be handled by the source control system. 

---

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

For a more detailed description of the files generated and used in a portlet
please refer to the [Anatomy of a Portlet] (https://www.liferay.com/documentation/liferay-portal/6.0/development/-/ai/anatomy-of-a-portlet)
section in the DevGuide.

Congrats! Now you understand the ins and outs of a MVC portlet and have two
handy ways to create them in your bag O'trix!  

## Next Steps

 [Creating Liferay Maven plugins from IDE](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)
 
 [Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)

 [Beginning Liferay Development Learning Path](/learning-paths/-/knowledge_base/beginning-liferay-development-lp-6-2-develop-learnpath)

