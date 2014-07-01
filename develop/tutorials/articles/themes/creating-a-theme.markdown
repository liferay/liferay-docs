# Creating a Theme Project

You can create themes in a Plugins SDK project or a Liferay Maven project.
Liferay IDE lets you create either project type using command line tools: Ant in 
the Plugins SDK or Maven Archetype in Maven. 

In this tutorial, you'll learn how to create themes in a Plugins SDK project,
using Liferay IDE and Ant, from the command line. To learn how to create Liferay 
Maven projects, see the tutorial [Creating Liferay Maven plugins from IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial [Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial). 
Creating the theme using Liferay Developer Studio is covered first, followed by 
creating a theme with the terminal. 

## Creating a Theme Using Developer Studio 

Follow these steps to create a theme:

1.  Go to *File* &rarr; *New* &rarr; *Liferay Project*. 

2.  Fill in the Project name and Display name fields.

3.  Select the Liferay Plugins SDK and Portal Runtime you've configured. 

4.  Select *Theme* for your Plugin type. 

5.  Click *Next*. 

6.  Select a theme parent. Your theme inherits the parent theme's styling as a
    base from which to build your theme. In addition to the *_styled* theme, you
    can choose to inherit from the *_unstyled* theme, which contains no styling.
    There's also the *classic* theme that has a smooth look and feel and works
    well. 

7.  Select your theme's framework. You can select the *Freemarker* or *Velocity*
    template frameworks for your theme. Or you can select *JSP* as your theme's
    framework. 

8.  Click *Finish*.

![Figure 1: Make sure to select the *Theme* plugin type for your theme.](../../images/theme-create-a-theme-project.png)

With Developer Studio, you can create a new plugin project, or if you already
have a project, create a new plugin in an existing project. A single Liferay
project can contain multiple plugins. 

## Using the terminal to Create a Theme

Navigate to the `themes` directory in your Plugins SDK and enter the appropriate 
command for your operating system:

1.  In Linux and Mac OS X, enter

        ./create.sh example-theme "Example Theme"

2.  In Windows, enter

        create.bat example-theme "Example Theme"

Now there's a blank theme in your `themes` folder, which the Plugins SDK
automatically named by appending "-theme" to your project name. Right now your
theme is empty. Your next step is to set a base theme that serves as the default
for your theme. 

## Setting a Base Theme

All themes in Liferay are built on top of two base themes, named *_unstyled* and
*_styled*. Your newly created theme is based on these by default, but they
contain very limited styling. You can take advantage of an *existing* theme's
styling by setting the theme of your choice as the base for your theme.

Base themes are added in layers. First *_unstyled* is added, giving you the core
of the theme, then *_styled*, providing the most basic elements. When you set a
different base theme, it's added on top of *_styled* and overrides the default
styling wherever there are differences. After the base themes are added, your
own custom styling is added on top.

By default, themes are based on the *_styled* theme, which provides only basic
styling of portlets. If you open the `build.xml` file in your theme's directory,
you see the following code:

	<?xml version="1.0"?>
	<!DOCTYPE project>

	<project name="deep-blue-theme" basedir="." default="deploy">
		<import file="../build-common-theme.xml" />

		<property name="theme.parent" value="_styled" />
	</project>

The `theme.parent` property determines the theme your theme inherits its styling
from. In addition to the *_styled* theme, you can choose to inherit from the
*_unstyled* theme, which contains no styling. This makes more work for you, but
offers full flexibility to design your CSS files from scratch. 

You can also use the default Liferay theme **Classic** as a parent theme. You'll
start with a look and feel that's already smooth and works well. But since so
much is already done for you, there's less flexibility when building your
design. It's a compromise between creating a theme as quickly as possible versus
having full control of the result. It's your choice, and another example of the
flexibility Liferay offers.

To specify a base theme, edit the `build.xml` file for your theme and change
*_styled* in `<property name="theme.parent" value="_styled>` to the name of any
existing theme that's installed or in your Plugins SDK.

Now that your base theme is set, go ahead and deploy it to your portal instance
and give yourself a pat on the back. Next you can get a closer look at the
anatomy of your newly created theme.

## Anatomy of a Theme Project [](id=anatomy-of-a-theme-project-liferay-portal-6-2-dev-guide-09-en)

Custom themes are created by layering your customizations on top of one of
Liferay's built-in themes. 

The structure of a theme separates different types of resources into easily
accessible folders. Here's the full structure of a theme called Deep Blue theme:

- `deep-blue-theme/`
    - `docroot/`
        - `_diffs/` (subfolders not created by default)
            - `css/`
            - `images/`
            - `js/`
            - `templates/`
        - `css/`
        	- `aui`
        		- (many directories)
        	- `_aui_custom.scss`
        	- `_aui_variables.scss`
        	- `_liferay_custom.scss`
            - `application.css`
            - `aui.css`
            - `base.css`
            - `custom.css`
            - `dockbar.css`
            - `extras.css`
            - `layout.css`
            - `main.css`
            - `navigation.css`
            - `portlet.css`
        - `images/`
            -   (many directories)
        - `js/`
            - `main.js`
        - `templates/`
            - `init_custom.vm`
            - `navigation.vm`
            - `portal_normal.vm`
            - `portal_pop_up.vm`
            - `portlet.vm`
        - `WEB-INF/`
        	- `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`

The `_diffs` folder that's created inside the `docroot` directory of your theme
is important; this is where you place your theme's code. The `_diffs` folder
must mirror the parent theme's directory structure. Since you'll only customize
the parts of your theme that differ from the parent theme, place only the
folders and files you'll customize there. 

The other folders inside `docroot` were copied over from the parent theme in
your Liferay bundle when you deployed your theme. Use these files as the basis
for your modifications. For example, to customize the navigation, copy
`navigation.vm` from `deep-blue-theme/docroot/templates/navigation.vm` into the 
`deep-blue-theme/docroot/_diffs/templates` folder (you may have to create this
folder first). You can then open this file and customize it to your liking. 

For custom styles, create a folder named `css` inside your `_diffs` folder and
place a single file there called `custom.css`. This is where you'll define all
your new styles. Because `custom.css` is loaded last, styles defined here
override any styles in the parent theme.

It's a best practice to add your styles only to the `custom.css` file. This
keeps all of your changes in one place and makes future upgrades easier, because
you won't have to manually modify your templates to add support for new Liferay
features. 

Whenever you modify your theme, redeploy it. Wait a few seconds until the theme 
deploys, then refresh your browser to see your changes. 

## Next Steps

 [Creating Liferay Maven plugins from IDE] (https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)

 [Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
