# Creating an Ext plugin [](id=creating-an-ext-plugin)

You can create Ext plugins in Liferay Developer Studio or in your terminal
environment. The Ext plugin is stored in the `ext` directory of the Plugins SDK
(see Chapter 2, The Plugins SDK). 

## Using Developer Studio [](id=using-developer-studio)

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Fill in *example* for project name and *Example* for the display name. 

3.  Leave the *Use default location* checkbox checked. By default, the
	default location is set to your current workspace. If you'd like to change
	where your plugin project is saved in your file system, uncheck the box and
	specify your alternate location. 

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type. If
	you'd like to use *Maven* for your build type, navigate to the [Using Liferay
	IDE with
	Maven](/develop/tutorials/-/knowledge_base/6-1/using-liferay-ide-with-maven)
	section for details. 

5.  Your configured SDK and Liferay Runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click *Configure SDKs* to
    open the *Installed Plugin SDKs* management wizard. You can also access the
    *New Server Runtime Environment* wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the *Liferay
    Portal Runtime* dropdown menu. 

6.  Select *Ext* for your Plugin type. 

7.  Click *Finish*. 

![Figure 8.1: You can even create an Ext plugin project with Liferay IDE.](../../images/07-ext-plugins-1.png)

The Plugins SDK automatically appended `-ext` to the project name when naming
the parent folder of your Ext plugin. In Developer Studio, you can either create
a completely new plugin or add a new plugin to an existing plugin project. 

## Using the terminal [](id=using-the-terminal)

Navigate to the *ext* directory in the Liferay Plugins SDK and enter the
appropriate command for your operating system to create a new Ext plugin: 

1.  In Linux and Mac OS, enter
	
        ./create.sh example "Example"

2.  In Windows, enter
	
        create.bat example "Example"
	
A `BUILD SUCCESSFUL` message from Ant tells you there's a new folder named
`example-ext` inside the `ext` folder in your Plugins SDK. The Plugins SDK
automatically named the EXT by appending `-ext` to the project name. 

## Anatomy of the Ext Plugin [](id=anatomy-of-the-ext-plugin)

The structure of your new `example-ext` folder looks like this: 

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

![Figure 8.2: Here's the directory structure in the Ext plugin's Package Explorer](../../images/07-ext-plugins-2.png)

Let's look at a few of the `/docroot/WEB-INF/` subdirectories in more detail: 

`ext-impl/src`: Contains the `portal-ext.properties` configuration file,
custom implementation classes, and in advanced scenarios, classes that override
core classes within `portal-impl.jar`. 

`ext-lib/global`: Contains libraries that should be copied to the
application server's global classloader upon deployment of the Ext plugin. 

`ext-lib/portal`: Contains libraries to be copied inside Liferay's main
application. These libraries are usually necessary because they are invoked from
the classes added in `ext-impl/src`. 

`ext-service/src`: Contains classes that should be available to other
plugins. In advanced scenarios, this directory contains classes that overwrite
the classes of `portal-service.jar`. Service Builder puts the interfaces of each
service here. 

`ext-web/docroot`: Contains the web application's configuration files,
including `WEB-INF/struts-config-ext.xml`, which allows you to customize
Liferay's core struts classes. However, hooks are recommended for customizing a
struts action. Any JSPs that you're customizing also belong here. 

`ext-util-bridges`, `ext-util-java` and `ext-util-taglib`: These folders are
needed only in scenarios where you need to customize the classes of three
libraries provided with Liferay: `util-bridges.jar`, `util-java.jar` and
`util-taglib.jar`, respectively. Otherwise you can ignore these directories. 

By default, several files are added to the plugin. Here are the most
significant: 

- `build.xml`: The Ant build file for the Ext plugin project. 
- `docroot/WEB-INF/liferay-plugin-package.properties`: Contains properties of
  the plugin, including display name, version, author, and license type.  
- `docroot/WEB-INF/ext-impl/src/portal-ext.properties`: Overrides Liferay's
  configuration properties--use a hook plugin to override properties whenever
  it's possible. An example where an Ext plugin is necessary to override a
  property is when specifying a custom class as a portal property value. You can
  use a `portal-ext.properties` file with each of your Ext plugins, but don't
  override the same property from multiple `portal-ext.properties` files--the
  loading order isn't assured, and you can cause unintended system behavior as a
  result. 
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

+$$$

**Tip:** After creating an Ext plugin, remove the files you don't need to
customize from `docroot/WEB-INF/ext-web/docroot/WEB-INF`. Liferay keeps track of
the files deployed by each Ext plugin and won't let you deploy multiple Ext
plugins that override the same file. If you remove unnecessary (uncustomized)
files, you'll avoid collisions with Ext plugins deployed alongside yours. 

$$$

You've now created an Ext plugin and are familiar with its directory structure
and its most significant files. Let's use your Ext plugin to customize Liferay
Portal. 
