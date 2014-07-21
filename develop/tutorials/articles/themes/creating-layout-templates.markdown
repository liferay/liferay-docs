# Creating a Layout Template Project in the Plugins SDK

Are there times when you find yourself limited by Liferay's page layout options?
Maybe your Feng Shui (pronounced *fung SHWAY*) senses are picking up on some
negative energy? Or perhaps you find yourself adding the same portlets over and
over again onto the same types of pages? Don't despair! Break the monotony by
creating your own custom layout templates. Layout template plugins let you
design layouts that flow nicely, embed commonly used portlets, and apply CSS,
Velocity, and HTML to make your pages visually pop. 

You can create layout templates in a Plugins SDK project or a Liferay Maven
project. You can create either type of project from Liferay Developer Studio,
Liferay IDE, or from the command line. This tutorial focuses on creating layout
templates in a Plugins SDK project, from Liferay IDE and from the command line. 

To learn how to create Liferay Maven projects, see the tutorial 
[Creating Liferay Maven plugins from IDE](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial
[Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial). 

Creating layout templates within Liferay IDE is easy. You can learn how, next. 

## Creating a Layout Template Project from Liferay IDE

Follow these steps to create a layout template project in the IDE:

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Fill in the Project name and Display name fields.
    
3.  Choose Ant for the build type and select the appropriate *Plugins SDK* and 
	*Liferay runtime*. 

4.  Select *Layout Template* as your plugin type. 

5.  Click *Finish*.

![Figure 1: Creating a new layout template project is easy with Liferay IDE.](../../images/layout-template-new-project.png)

Next up, you can create a layout template from the command line using Ant.

## Creating a Layout Template Project from the Command Line

From your command line terminal, navigate to your Plugins SDK's `layouttpl`
folder. To create a layout template project you must execute the *create* 
script. Here's the format to follow in executing the script: 

    create.[sh|bat] [project-name] "[Layout Template Display Name]"

On Linux and Mac OS X, you'd enter a command similar to the one in this example:

    ./create.sh my-layout "My Layout"

On WindowsX, you'd enter a command similar to the one in this example:

    create.bat my-layout "My Layout"

Liferay IDE's *New Project* wizard and the *create* scripts generate layout
template projects in your Plugin SDK's `layouttpl` folder. The Plugins SDK
automatically appends "-layouttpl" to your project name.  

Take a look at the directory structure of a layout template project below:

- `[project-name]-layouttpl/`
	- `docroot/`
		- `META-INF/`
		- `WEB-INF/`
			- `liferay-layout-templates.xml`
			- `liferay-plugin-package.properties`
		- `[project-name].png`
		- `[project-name].tpl`
		- `[project-name].wap.tpl`
	- `build.xml`

A layout template project can contain multiple layout templates. The directory
structure is the same, but you'll have a `.png`, `.tpl`, and `.wap.tpl` file for
each layout template in the `docroot/` folder. The `liferay-*` files describe
the layout templates for packaging and deployment. 

Now that you're well-versed on the anatomy of a layout template project, go out
and create some!

## Related Topics

[Creating Liferay Maven Plugins from IDE] (/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)

[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)

