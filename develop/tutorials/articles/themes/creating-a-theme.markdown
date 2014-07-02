# Creating a Theme Project

You can create themes in a Plugins SDK project or a Liferay Maven project.
Liferay IDE lets you create either project type using command line tools: Ant in 
the Plugins SDK or Maven Archetype in Maven. 

In this tutorial, you'll learn how to create themes in a Plugins SDK project,
using Liferay IDE and Ant, from the command line. To learn how to create Liferay 
Maven projects, see the tutorial [Creating Liferay Maven plugins from IDE](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial [Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial). 
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
theme is empty. In future tutorials you'll learn how to change that. Custom 
themes are created by layering your customizations on top of one of Liferay's 
built-in themes. 

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

Whenever you modify your theme, redeploy it. Wait a few seconds until the theme 
deploys, then refresh your browser to see your changes. 

## Next Steps

 [Creating Liferay Maven plugins from IDE] (/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)

 [Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
