# Creating an Ext Plugin

Ext plugins are powerful tools for extending Liferay. Because they increase the 
complexity of your Liferay instance, you should only use an Ext plugin if you’re 
sure you can’t accomplish your goal using a different tool. You can create Ext 
plugins in Liferay IDE or in your terminal environment. The Ext plugin is stored 
in the `ext` directory of the [Plugins SDK](/tutorials/-/knowledge_base/using-the-plugins-sdk). 
You also have the option to create an Ext plugin in a Liferay Maven project.

This tutorial covers how to create an Ext plugin in a Plugins SDK project using 
Ant. This is shown from both Liferay IDE and the terminal. To learn how to 
create Liferay Maven projects, see the tutorial 
[Creating Liferay Maven plugins from IDE](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial [Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial). 

First up, creating an Ext plugin using Liferay IDE!

## Using Liferay IDE to Create an Ext Plugin

Follow the steps below, replacing the project name with your own, and you'll 
have an Ext plugin in no time flat:

1. Go to File &rarr; New &rarr; Liferay Plugin Project. 

2. Give your project a name and display name. Here, *example* is used for both. 
   Note that the Display name field is automatically filled in with the 
   capitalized version of your Project name. 

3. Leave the *Use default location* checkbox checked. Since you're using Ant, 
   the default location is set to your Liferay Plugins SDK. If you'd like to 
   change where your plugin project is saved in your file system, uncheck the 
   box and specify an alternate location. 

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type.

5.  Your configured SDK and Liferay runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click *Configure SDKs* to
    open the *Installed Plugin SDKs* management wizard. You can also access the
    *New Server Runtime Environment* wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the *Liferay
    Portal Runtime* dropdown menu. 

6.  Select *Ext* for your Plugin type. 

7.  Click *Finish*. 

![Figure 1: You can create an Ext plugin project with Liferay IDE.](../../images/ext-create-an-ext-plugin-project.png)

The Plugins SDK automatically appended `-ext` to the project name when naming
the parent folder of your Ext plugin. In Liferay IDE, you can either create
a completely new plugin or add a new plugin to an existing plugin project. 

## Creating an Ext Plugin Using the Terminal

To create a new Ext plugin from the terminal, navigate to the *ext* directory in 
your Liferay Plugins SDK and enter the command below that is appropriate for 
your operating system. The two arguments afer the `create` command are the 
project name and display name, respectively. Here, the project name is `example` 
and the display name is `Example`. Be sure to replace these with the project 
name and display name that you want to use for your plugin.

In Linux or Mac OS, enter:

    ./create.sh example "Example"

In Windows, enter:

    create.bat example "Example"

A `BUILD SUCCESSFUL` message from Ant tells you there's a new folder named 
`example-ext` inside the `ext` folder in your Plugins SDK. The Plugins SDK
automatically appends `-ext` to the project name. 

## Anatomy of an Ext Plugin

There are a few things to note about the directory structure of Ext plugins. The 
structure of your new Ext plugin should look similar to the one here. The 
screenshot that follows this list shows the structure of the Ext plugin in 
Liferay IDE: 

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

![Figure 2: The directory structure of the Ext plugin in Liferay IDE's Package Explorer.](../../images/07-ext-plugins-2.png)

At this point, you might be wondering what these directories are for. Here's an 
explanation of the `/docroot/WEB-INF/` subdirectories in more detail: 

`ext-impl/src`: Contains the `portal-ext.properties` configuration file, custom 
implementation classes, and in advanced scenarios classes that overridecore 
classes within `portal-impl.jar`. 

`ext-lib/global`: Contains libraries that should be copied to the application 
server's global classloader upon deployment of the Ext plugin. 

`ext-lib/portal`: Contains libraries to be copied inside Liferay's main 
application. These libraries are usually necessary because they are invoked from 
the classes added in `ext-impl/src`. 

`ext-service/src`: Contains classes that should be available to other plugins. 
In advanced scenarios, this directory contains classes that overwrite the 
classes of `portal-service.jar`. Liferay's Service Builder puts the interfaces 
of each service here. 

`ext-web/docroot`: Contains the web application's configuration files, including 
`WEB-INF/struts-config-ext.xml`, which allows you to customize Liferay's core 
struts classes. However, hooks are recommended for customizing a struts action. 
Any JSPs that you're customizing also belong here. 

`ext-util-bridges`, `ext-util-java` and `ext-util-taglib`: These folders are 
needed only in scenarios where you need to customize the classes of three 
libraries provided with Liferay: `util-bridges.jar`, `util-java.jar` and
`util-taglib.jar`, respectively. Otherwise you can ignore these directories. 

By default, several files are also added to the plugin. Here are the most 
significant: 

- `build.xml`: The Ant build file for the Ext plugin project. 
- `docroot/WEB-INF/liferay-plugin-package.properties`: Contains properties of
  the plugin, including display name, version, author, and license type. 
- `docroot/WEB-INF/ext-impl/src/portal-ext.properties`: Overrides Liferay's
  configuration properties. However, you should use a hook plugin to override 
  properties whenever it's possible. An example where an Ext plugin is necessary 
  to override a property is when specifying a custom class as a portal property 
  value. You can use a `portal-ext.properties` file with each of your Ext 
  plugins, but don't override the same property from multiple 
  `portal-ext.properties` files--the loading order isn't assured and you can 
  cause unintended system behavior as a result. 
- `docroot/WEB-INF/ext-web/docroot/WEB-INF` files: 

    - `portlet-ext.xml`: Used to overwrite the definition of a Liferay portlet.
      To do this, copy the complete definition of the desired portlet from
      `portlet-custom.xml` in Liferay's source code, then apply the necessary
      changes. 
    - `liferay-portlet-ext.xml`: This file is similar to `portlet-ext.xml`, but
      is for additional definition elements specific to Liferay. To override
      these definition elements, copy the complete definition of the desired
      portlet from `liferay-portlet.xml` within Liferay's source code, then
      apply the necessary changes. 
    - `struts-config-ext.xml` and `tiles-defs-ext.xml`: These files are used to
      customize the struts actions used by Liferay's core portlets. 

---

![tip](../../images/tip-pen-paper.png) **Tip:** After creating an Ext plugin,
remove the files you don't need to customize from
`docroot/WEB-INF/ext-web/docroot/WEB-INF`. Liferay keeps track of the files
deployed by each Ext plugin and won't let you deploy multiple Ext plugins that
override the same file. If you remove unnecessary (uncustomized) files, you'll
avoid collisions with Ext plugins deployed alongside yours. 

---

Great! Now you know how to create Ext plugins using both Liferay IDE and the 
terminal. You also learned about the directory and file structure of Ext 
plugins. Just remember--Ext plugins are quite powerful and should be used with 
care.

## Related Topics

[Developing with the Plugins SDK](/tutorials/-/knowledge_base/plugins-sdk)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/liferay-ide)

[Creating Liferay Maven Plugins from Liferay IDE](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide) 
 
[Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin) 
