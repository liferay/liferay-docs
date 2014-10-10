# Creating a Layout Template Project in the Plugins SDK [](id=creating-a-layout-template-project-in-the-plugins-sdk)

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
[Creating Liferay Maven plugins from IDE](/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-liferay-ide) 
or the tutorial
[Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-the-command-lin). 

Creating layout templates in Liferay IDE is easy. You can learn how next. 

## Creating a Layout Template Project from Liferay IDE

Follow these steps to create a layout template project in the IDE:

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Fill in the Project name and Display name fields.
 
3.  Choose Ant for the build type and select the appropriate *Plugins SDK* and 
	*Liferay runtime*. 

4.  Select *Layout Template* as your plugin type. 

5.  Click *Finish*.

![Figure 1: Creating a new layout template project is easy with Liferay IDE.](../../images/layout-template-new-project.png)

You can also create a layout template from the command line using Ant.

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

Look at the directory structure of a layout template project below:

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
structure is the same, but there is a `.png`, `.tpl`, and `.wap.tpl` file for
each layout template in the `docroot/` folder. The `liferay-*` files describe
the layout templates for packaging and deployment. 

Check out what each template file does: 

- `[project-name].tpl`: Generates the HTML structure of the template. 
- `[project-name].wap.tpl`: Variant template for mobile devices. WAP stands for
  Wireless Application Protocol. 
- `[project-name].png`: Thumbnail representation of the template that you see in
  Liferay Portal from the Page Layout screen. You'll have to create a custom
  thumbnail image to draw attention to your layout, but you can use the default
  thumbnail PNG file as a starting point.

![Figure 2: This is an example of a default layout template thumbnail.](../../images/blank_columns.png)

In addition to the three template-specific files, a layout template project has
two Liferay configuration files:

- `liferay-layout-templates.xml`: Specifies the name of the layout templates and
  the location of their TPL and PNG files. 
- `liferay-plugin-package.properties`: Describes the plugin project to Liferay's
  hot deployer. 

Now that you're well-versed on the anatomy of a layout template project, go out
and create some!

## Related Topics

[Creating Liferay Maven Plugins from IDE] (/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-liferay-ide)

[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-maven-plugins-from-the-command-lin)

