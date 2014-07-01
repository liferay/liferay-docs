# Creating a Layout Template Project

You can create layout templates in a Plugins SDK project or a Liferay Maven 
project. Liferay IDE lets you create either project type using command line 
tools: Ant in the Plugins SDK or Maven Archetype in Maven. 

In this tutorial, you'll learn how to create layout templates in a Plugins SDK 
project, using Liferay IDE and Ant, from the command line. To learn how to 
create Liferay Maven projects, see the tutorial 
 [Creating Liferay Maven plugins from IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial [Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial). 

With the Plugins SDK, you can deploy layout templates as plugins, and creating
layout templates with Liferay Developer Studio is easier than ever. Get started
with Developer Studio.

## Using Developer Studio to Create Layouts

The example below creates a layout template called *Columns 1 4 1*. Follow these
steps to create your own layout:

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Enter *columns-1-4-1* for the Project name and *Columns 1 4 1* for the
    Display name.
    
3.  Choose Ant for the build type and select the appropriate *Plugins SDK* and 
	*Liferay runtime*. 

4.  Select *Layout Template* as your plugin type. 

5.  Click *Finish*.

![Figure 1: Creating a new layout template project is easy with Liferay Developer Studio.](../../images/layout-template-new-project.png)

Next up, creating layouts with the terminal!

## Using the terminal to Create Layouts 

Navigate to your Plugins SDK's `layouttpl` folder, and execute the *create* 
script in your terminal. Here's the generic version of the *create* script, 
followed by operating system-specific commands:

    ./create.[sh|bat] <project-name> "<layout template title>"

1.  Example in Linux and Mac OS X:

        ./create.sh columns-1-4-1 "Columns 1 4 1"

2.  Example in Windows:

        create.bat columns-1-4-1 "Columns 1 4 1"

Developer Studio's *New Project* wizard and the *create* scripts generate layout
template projects in your Plugin SDK's `layouttpl` folder. Layout template
project names must end with *-layouttpl* so when you enter *columns-1-4-1* for
the project name, *-layouttpl* is automatically appended to the project name.
Take a closer look at the anatomy of your layout template next.

## Anatomy of a Layout Template Project

Take a look at the directory structure of a layout template project below:

- `columns-1-4-1-layouttpl/`
	- `docroot/`
		- `META-INF/`
		- `WEB-INF/`
			- `liferay-layout-templates.xml`
			- `liferay-plugin-package.properties`
		- `columns_1_4_1.png`
		- `columns_1_4_1.tpl`
		- `columns_1_4_1.wap.tpl`
	- `build.xml`

Navigate to your Plugins SDK's `layouttpl/` folder and you'll see that the
Plugins SDK automatically appended *-layouttpl* to your project's name. A layout
template project can contain multiple layout templates. The directory structure
is the same, but you'll have a `.png`, `.tpl`, and `.wap.tpl` file for each
layout template in the `docroot/` folder. The `liferay-*` files describe the
layout templates for packaging and deployment. 

Now that you're well-versed on the anatomy of a layout template, go out and
create some layouts!

## Next Steps

 [Creating Liferay Maven plugins from IDE] (https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)

 [Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)

