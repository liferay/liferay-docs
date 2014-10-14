# Creating a Theme [](id=creating-a-theme)

The theme creation process is nearly identical to the portlet creation process
that we covered in the last chapter. Our theme will be named *Deep Blue*, so the
project name (without spaces) is *deep-blue*, and the display name (which can
have spaces) is *Deep Blue*. Let's create the theme using Liferay Developer
Studio first, and then with the terminal. 

***Using Developer Studio:*** 

1.  Go to *File* &rarr; *New* &rarr; *Liferay Project*. 

2.  Fill in *deep-blue* for the Project name and *Deep Blue* for the Display
    name. 

3.  Select the build type, Plugins SDK, and Liferay runtime. 

    If you select the Maven build type, you'll be prompted to enter an artifact
    version, group ID, and active profile for your project. See  [Using Liferay
	IDE with
	Maven](/develop/tutorials/-/knowledge_base/6-1/using-liferay-ide-with-maven)
    for more information. Otherwise, select the Ant build type, a Plugins SDK
    and a Liferay runtime.  

    For this demonstration, make the following selections: 

    - **Build type:** *Ant*
    - **Plugins SDK:** `[a configured Plugins SDK]`
    - **Liferay runtime:** `[a configured Liferay runtime]`

    For more information, see sections [Installing the
    SDK](/develop/tutorials/-/knowledge_base/6-1/installing-the-sdk)
    and [Setting Up Liferay
    IDE](/develop/tutorials/-/knowledge_base/6-1/setting-up-liferay-ide).

4.  Select *Theme* for your Plugin type. 

5.  Click *Next*. 

6.  Select a theme parent. Your theme inherits the parent theme's styling as a
    base from which to build your theme. In addition to the *_styled* theme, you
    can choose to inherit from the *_unstyled* theme, which contains no styling.
    There's also the *classic* theme that has a smooth look and feel and works
    well. For now, select *_styled* as the theme parent. 

7.  Select your theme's framework. You can select the *Freemarker* or *Velocity*
    template frameworks for your theme. Or you can select *JSP* as your theme's
    framework. 

    <!-- Provide better explanation for the concept of the theme framework,
    especially for the JSP value. - Jim -->

8.  Click *Finish*.

![Figure 5.1: Make sure to select the *Theme* plugin type for your theme.](../../images/theme-create-a-theme-project.png)

With Developer Studio, you can create a new plugin project, or if you already
have a project, create a new plugin in an existing project. A single Liferay
project can contain multiple plugins.  

***Using the terminal:*** Navigate to the `themes` directory in the Plugins SDK
and enter the appropriate command for your operating system:

1.  In Linux and Mac OS X, enter

        ./create.sh deep-blue "Deep Blue"

2.  In Windows, enter

        create.bat deep-blue "Deep Blue"

Now there's a blank theme in your `themes` folder, which the Plugins SDK
automatically named by appending "-theme" to your project name. Right now your
theme is empty. Your next step is to set a base theme that serves as the default
for your theme. 

## Setting a base Theme [](id=setting-a-base-theme)

All themes in Liferay are built on top of two base themes, named *_unstyled* and
*_styled*. Your newly created theme is based on these by default, but they
contain very limited styling. You can take advantage of an *existing* theme's
styling by setting the theme of your choice as the base for your theme.

Base themes are added in layers. First *_unstyled* is added, giving you the core
of the theme, then *_styled*, providing the most basic elements. When you set a
different base theme, it's added on top of *_styled* and overrides the default
styling wherever there are differences. After the base themes are added, your
own custom styling is added on top.

To specify a base theme, edit the `build.xml` file for your theme and change
*_styled* in `<property name="theme.parent" value="_styled>` to the name of any
existing theme that's installed or in your Plugins SDK. Now that your base theme
is set, let's deploy the theme to your portal instance.

## Deploying the Theme [](id=deploying-the-theme)

If you're already familiar with portlet deployment from reading Chapter 3, theme
deployment will be a piece of cake! You can deploy your theme in Developer
Studio or the terminal. 

***Deploying in Developer Studio:*** Click and drag your theme project onto your
server. 

 ![Figure 5.2: Drag and drop your theme onto the server](../../images/05-themes-6.png)

Upon deploying, your server outputs messages indicating your plugin is read,
registered, and available for use. 

    Reading plugin package for deep-blue-theme
    Registering themes for deep-blue-theme
    1 theme for deep-blue-theme is available for use

***Deploying in the terminal:*** Open a terminal window in your
`themes/deep-blue-theme` directory and enter

    ant deploy

A BUILD SUCCESSFUL message indicates your theme is now being deployed. If you
switch to the terminal window running Liferay, within a few seconds you will
see the message `1 theme for deep-blue-theme is available for use`.

Let's apply your theme to a page:

1.  Go to your web browser and log in to the portal. 

2.  Hover over **Manage** at the top of the page and click on *Page*. 

3.  Directly underneath the words **Manage Pages**, select the *Look and Feel*
    tab. Click on your theme to activate it.

Now that you've built and deployed a theme, let's study theme anatomy.
