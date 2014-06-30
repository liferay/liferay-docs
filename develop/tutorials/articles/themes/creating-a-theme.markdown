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
and give yourself a pat on the back.

## Next Steps

 [Creating Liferay Maven plugins from IDE] (https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)

 [Creating Liferay Maven Plugins from the Command Line](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
