# Creating a Theme Project in the Plugins SDK

You can create themes in a Plugins SDK project or a Liferay Maven project. You
can create either type of project from Liferay IDE/Developer Studio or from the
command line. This tutorial focuses on creating themes in a Plugins SDK project,
from Liferay IDE and from the command line. 

To learn how to create Liferay Maven projects, see the tutorial
[Creating Liferay Maven plugins from IDE](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial
[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial). 

Creating the theme using Liferay IDE is easiest to learn first.

## Creating a Theme Project from Liferay IDE

Follow these steps to create a theme project in the IDE:

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

![Figure 1: Make sure to select the *Theme* plugin type for your Liferay Plugin Project.](../../images/theme-create-a-theme-project.png)

You've just created a theme project in which to implement your new look and
feel. 

Creating a theme from the command line using Ant is simple too.

## Creating a Theme Project from the Command Line

From your command line terminal, navigate to your Plugins SDK's `themes` folder.
To create a theme project you must execute the *create* script. Here's the
format to follow in executing the script: 

    create.[sh|bat] [project-name] "[Theme Display Name]"

On Linux and Mac OS X, you'd enter a command similar to the one in this example:

        ./create.sh my-theme "My Theme"

On WindowsX, you'd enter a command similar to the one in this example:

        create.bat my-theme "My Theme"

Whether you created your theme project from the Liferay IDE or from the command
line, you end up with the same project structure. Your theme project is a blank
slate on which you you can exercise your creative talents. The Plugins SDK
automatically appends "-theme" to your project name. Right now your theme is
empty. Custom themes are created by layering your customizations on top of one
of Liferay's built-in themes. In tutorials that follow, you'll
learn how to populate your theme with your very own stylish look and feel. For
now, take a look around your theme project. 

The structure of a theme separates different types of resources into easily
accessible folders. Here's the full structure of a theme project:

- `[theme-project-name]/`
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
            
The `_diffs` folder that's created inside the `docroot` folder of your theme
is important; this is where you place your theme's code. The `_diffs` folder
must mirror the parent theme's directory structure. Since you'll only customize
the parts of your theme that differ from the parent theme, place only the
folders and files you'll customize there. 

The other folders inside `docroot` are copied over from the parent theme in
your Liferay bundle when you
[deploy](/develop/tutorials/-/knowledge_base/deploying-plugins-lp-6-2-develop-tutorial)
your theme. Use these files as the basis for your modifications. For example, to
customize the navigation, copy `navigation.vm` from
`docroot/templates/navigation.vm` into the `docroot/_diffs/templates` folder
(you may have to create this folder first). You can then open this file and
customize it to your liking. 

For custom styles, create a folder named `css` inside your `_diffs` folder and
place a single file there called `custom.css`. This is where you'll define all
your new styles. Because `custom.css` is loaded last, styles defined here
override any styles in the parent theme.

It's a best practice to add your styles only to the `custom.css` file. This
keeps all of your changes in one place and makes future upgrades easier, because
you won't have to manually modify your templates to add support for new Liferay
features. 

Whenever you modify your theme,
[redeploy](/develop/tutorials/-/knowledge_base/deploying-plugins-lp-6-2-develop-tutorial)
it. Wait a few seconds until the theme deploys, then refresh your browser to see
your changes. 

Congrats! Now you know how to create a theme project and are familiar with its
structure. 

## Related Topics

[Creating Liferay Maven Plugins from Liferay IDE] (/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)

[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
