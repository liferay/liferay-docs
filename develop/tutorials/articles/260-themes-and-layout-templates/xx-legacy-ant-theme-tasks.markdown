# Using Legacy Ant Theme Tasks [](id=using-legacy-ant-theme-tasks)

There are several ways you can go about developing a theme for Liferay. To help
automate the theme development process, Liferay offers some build tools.

Since Liferay 7.0 and Liferay DXP, you can create themes using the 
[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator). 
The Themes generator is a Node.js based development tool that allows you to 
generate a theme quickly. Once your theme is created, you can extend it further 
using [theme gulp tasks](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks). 
The themes generator also consist of a sub-generator that allows you to extend 
your theme further, using [themelets](/develop/tutorials/-/knowledge_base/7-0/themelets).

If you prefer, you can also use Ant and the Plugins SDK to develop your theme,
just like you did in 6.2.

+$$$

**Note:** If you are unable to build a theme, you may need to update your 
`ivy-settings.xml`. Open `ivy-settings.xml` inside your Plugins SDK and remove
the following property:

    <resolver ref="liferay-private" />
 
This stops the Plugins SDK from downloading dependencies for empty repositories.
Your SDK should be working now.

$$$

This tutorial assumes that you're using a Plugins SDK to create your theme and
demonstrates how to

- Create themes with the Plugins SDK in the command line
- Use legacy ant theme tasks from the command line

Once you have your Plugins SDK set up, you can get started.

## Creating a Theme Project from the Command Line [](id=creating-a-theme-project-from-the-command-line)

From your terminal window, navigate to your Plugins SDK's `themes` folder in 
your workspace. To create a theme project, you must execute the *create* script. 
Here's the format to follow in executing the script: 

    create.[sh|bat] [project-name] "[Theme Display Name]"

On Linux and Mac OS X, you'd enter a command similar to the one in this example:

    ./create.sh my-theme "My Theme"
 
+$$$

**Note:** You may not be able to run the *create* script right away. If that is 
the case, you'll need to make the `create.sh` script executable. To make the
script executable, run the following command in the terminal, from within the 
`themes` folder of the Plugins SDK: `chmod +x create.sh`. Now you should be 
able to run the script without any problems.

$$$

On Windows, you'd enter a command similar to the one in this example:

    create.bat my-theme "My Theme"
 
Your theme project is a blank slate on which you you can exercise your creative 
talents. The Plugins SDK automatically appends "-theme" to your project name. 
Right now your theme is empty. Custom themes are created by layering your 
customizations on top of one of Liferay's built-in themes.

You'll learn about theme anatomy next.

### Plugins SDK Theme Anatomy [](id=plugins-sdk-theme-anatomy)

Once you've created your theme, you should end up with the structure below:

- `[theme-project-name]/`
    - `docroot/`
        - `_diffs/` (subfolders not created by default)
            - `css/`
            - `images/`
            - `js/`
            - `templates/`
        - `css/`
                - `_application.scss`
                - `_aui_custom.scss`
                - `_aui_variables.scss`
                - `_base.scss`
                - `_control_menu.scss`
                - `_custom_common.scss`
                - `_custom.scss`
                - `_extras.scss`
                - `_imports.scss`
                - `_layout.scss`
                - `_liferay_custom.scss`
                - `_liferay_variables_custom.scss`
                - `_liferay_variables.scss`
                - `_navigation.scss`
                - `_portal.scss`
                - `_portlet.scss`
                - `_taglib.scss`
                - `application`
        	- `aui/`
        		- (many directories)
        	- `aui.scss`
        	- `base/`
        	- `layout/`
        	- `main.scss`
        	- `navigation/`
        	- `portal/`
        	- `portlet/`
        	- `taglib/`
        - `images/`
            -   (many directories)
        - `js/`
            - `main.js`
        - `templates/`
            - `init_custom.ftl`
            - `navigation.ftl`
            - `portal_normal.ftl`
            - `portal_pop_up.ftl`
            - `portlet.ftl`
        - `WEB-INF/`
        	- `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`

If you're familiar with theme development in 6.2, you may have noticed a few
changes in the directory structure and filenames.

Starting in Liferay Portal 7.0 and Liferay DXP, all theme CSS files must follow
the SASS naming convention, using the `.scss` file extension. This means that
you can use imports and variables in all your CSS files.

The second change to note is that FreeMarker is now the default and recommended 
template language. Velocity theme templates have been deprecated for Liferay 7.0 
and Liferay DXP, which means they will be removed in the next version. It is
therefore recommended that you use FreeMarker theme templates to develop your
theme, as new theme components will not work with Velocity templates. Other than
those key changes, the development process is just as it was in 6.2. You can
read about all the theme development changes from Liferay 6.2 to Liferay 7.0 and
Liferay DXP, in the [Optimizing an Existing Theme for Liferay
7.0](/develop/tutorials/-/knowledge_base/7-0/optimizing-an-existing-theme-for-liferay-7)
tutorial.

### Theme Development Workflow [](id=theme-development-workflow)

The `_diffs` folder that's created inside the `docroot` folder of your theme
is important; this is where you place your theme's code. The `_diffs` folder
must mirror the parent theme's directory structure. Since you'll only customize
the parts of your theme that differ from the parent theme, place only the
folders and files you'll customize there. 

The other folders inside `docroot` are copied over from the parent theme in
your Liferay bundle when you deploy your theme. Use these files as the basis for 
your modifications. For example, to customize the navigation markup, copy the 
`navigation.ftl` file from `docroot/templates/navigation.ftl` into the 
`docroot/_diffs/templates` folder (you may have to create this folder first). 
You can then open this file from the `_diffs` folder and customize it to your 
liking.

For custom styles, create a folder named `css` inside your `_diffs` folder and
place a single file there called `_custom.scss`. This is where you'll define all
your new styles. Because `_custom.scss` is loaded last, styles defined there
override any styles in the parent theme.

It's a best practice to add your styles only to the `_custom.scss` file. This
keeps all your changes in one place and makes future upgrades easier, because
you won't have to manually modify your templates to add support for new Liferay
features.

Whenever you modify your theme, redeploy it. Wait a few seconds until the theme 
deploys, then refresh your browser to see your changes.

Once you're finished developing your theme, there are a few legacy Ant tasks 
that you can run for your theme, which you'll learn about next. 

### Using Ant Theme Tasks [](id=using-ant-theme-tasks)

Themes built with the Plugins SDK have access to the following Ant tasks:

- **Clean**: Removes all extraneous files.
- **Compile**: Compiles the theme's files.
- **Deploy**: Initially runs the `ant war` task, and deploys the theme to your app 
  server's `deploy` directory.
- **War**: Compresses the theme into a hot-deployable `war` file.

To run an Ant task for your theme, navigate to the theme's root folder in the 
terminal and use the following pattern:

    ant [task name]

Congrats! Now you know how to create a theme project with the Plugins SDK and 
are familiar with its structure and Ant tasks. 

## Related Topics [](id=related-topics)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/themes-and-layout-templates)

