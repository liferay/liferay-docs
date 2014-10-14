# Creating New Liferay Projects [](id=creating-new-liferay-projects)

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
