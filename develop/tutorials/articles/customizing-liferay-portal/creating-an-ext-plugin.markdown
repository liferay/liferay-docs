# Creating an Ext Plugin

You can create Ext plugins in Liferay Developer Studio or in your terminal
environment. The Ext plugin is stored in the `ext` directory of the Plugins SDK
(see the tutorial on [Using the Plugins SDK](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/using-the-plugins-sdk-lp-6-2-develop-tutorial)). 
You also have the option to create an Ext plugin in a Liferay Maven project.

This tutorial covers how to create an Ext plugin in a Plugins SDK project, using
Liferay IDE and Ant, from the command line. To learn how to create Liferay Maven 
projects, see the tutorial [Creating Liferay Maven plugins from IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial [Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial). 

First up, creating an Ext plugin using Developer Studio!

## Using Developer Studio to Create an Ext Plugin

Follow the steps below, replacing the name with your own, and you'll have an Ext 
plugin in no time flat:

1.  Go to File &rarr; New &rarr; Liferay Project. 

2.  Fill in *example* for project name and *Example* for the display name. 

3.  Leave the *Use default location* checkbox checked. By default, the
	default location is set to your current workspace. If you'd like to change
	where your plugin project is saved in your file system, uncheck the box and
	specify your alternate location. 

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type.

5.  Your configured SDK and Liferay Runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click *Configure SDKs* to
    open the *Installed Plugin SDKs* management wizard. You can also access the
    *New Server Runtime Environment* wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the *Liferay
    Portal Runtime* dropdown menu. 

6.  Select *Ext* for your Plugin type. 

7.  Click *Finish*. 

![Figure 1: You can even create an Ext plugin project with Liferay IDE.](../../images/ext-create-an-ext-plugin-project.png)

The Plugins SDK automatically appended `-ext` to the project name when naming
the parent folder of your Ext plugin. In Developer Studio, you can either create
a completely new plugin or add a new plugin to an existing plugin project. 

## Creating an EXT Plugin Using the Terminal

Navigate to the *ext* directory in the Liferay Plugins SDK and enter the
appropriate command for your operating system to create a new Ext plugin: 

1.  In Linux and Mac OS, enter

        ./create.sh example "Example"

2.  In Windows, enter

        create.bat example "Example"

A `BUILD SUCCESSFUL` message from Ant tells you there's a new folder named
`example-ext` inside the `ext` folder in your Plugins SDK. The Plugins SDK
automatically named the EXT by appending `-ext` to the project name. 

The structure of your new EXT plugin should look similar to the one below: 

- `example-ext/`
    - `docroot/`
        - `WEB-INF/`
            - `ext-impl/`
                - `src/`
            -  `ext-lib/`
                - `global/`
                - `portal/`
            - `ext-service/`
                - `src/`
            - `ext-util-bridges/`
                - `src/`
            - `ext-util-java/`
                - `src/`
            - `ext-util-taglib/`
                - `src/`
            - `ext-web/`
                - `docroot/`

![Figure 2: Here's the directory structure in the Ext plugin's Package Explorer](../../images/07-ext-plugins-2.png)

For a look at the `/docroot/WEB-INF/` subdirectories in more detail please refer
to the [Ext Plugin Anatomy](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-an-ext-plugin-liferay-portal-6-2-dev-guide-12-en) 
DevGuide section.

---

 ![tip](../../images/tip-pen-paper.png) **Tip:** After creating an Ext plugin,
 remove the files you don't need to customize from
 `docroot/WEB-INF/ext-web/docroot/WEB-INF`. Liferay keeps track of the files
 deployed by each Ext plugin and won't let you deploy multiple Ext plugins that
 override the same file. If you remove unnecessary (uncustomized) files, you'll
 avoid collisions with Ext plugins deployed alongside yours. 

---

You've now created an Ext plugin and are familiar with its directory structure! 

## Next Steps

 [Using the Plugins SDK](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/using-the-plugins-sdk-lp-6-2-develop-tutorial)
 
 [Creating Liferay Maven plugins from IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
 
 [Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial). 
