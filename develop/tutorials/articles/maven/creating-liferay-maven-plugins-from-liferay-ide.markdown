# Creating Liferay Maven Plugins from Liferay IDE [](id=creating-liferay-maven-plugins-from-liferay-ide)

Liferay offers many archetypes to help create Maven projects for multiple plugin
types. These types include portlet, theme, hook, and layout template plugins. To make sure
that you can find the archetypes you need, Liferay provides archetypes for each of
these plugin types for various versions of Liferay. *Archetype* is Maven's
project templating toolkit. This tutorial explains how to use Archetype to
create a Liferay portlet project. With Archetype, you can use the same steps we
detail below to generate Liferay plugin projects of any type. 

As a prerequisite to running Archetype, make sure Maven is installed and that
its executable is in your `$PATH` environment variable. Maven must also be fully
configured. To configure Maven, follow the steps in the
[Setting Up Maven](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/setting-up-maven)
tutorial.

There are two ways of creating Liferay plugins with Maven: using Liferay
IDE and using the command line. This tutorial demonstrates using Liferay IDE to
create them. 

## Creating Liferay Maven Plugins Using Liferay IDE

In the following steps, you'll learn how to use Maven archetypes to generate a
Liferay plugin project using Liferay IDE:

1.  Select *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2.  Assign a project name and display name.

    As you enter the project name, the wizard helps you by filling in the
    display name based on it. For example, if you specified *sample-portlet*  as
    the project name, the wizard conveniently inserts *Sample* in grayed-out
    text as the portlet's default display name. The wizard derives the default
    display name from the project name, starts it in upper-case, and leaves off
    the plugin type suffix (e.g., *Portlet*) because the plugin type is
    automatically appended to the display name in Liferay Portal. The IDE saves
    the you from repetitively appending the plugin type to the display name; in
    fact, the IDE ignores any plugin type suffix if you happen to append it to
    the display name. 

3.  Select *Maven (liferay-maven-plugin)* for the build type. Notice that some
    of the options for your plugin project changed, including the *Location* 
    field, which is set to the user's workspace by default.

    If you have a parent plugin project directory in which you want to create
    the plugin project, specify the parent plugin project directory for the
    Location. It's a best practice to create a parent project for your Maven
    plugins, so they can all share common project information. See the
    [Using Liferay Maven Parent Plugin Projects](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/using-liferay-maven-parent-plugin-projects)
    tutorial for details. 

4.  Specify the *Artifact version*. For example, you can specify `1.0-SNAPSHOT`
    to indicate that your project's artifact will be a snapshot.

5.  Specify the *Group id*. For example, you can specify your project's root
    Java package, like `com.liferay.sample`. 

6.  Specify the *Active profiles* that you'd like your Liferay plugin project to
    use. 

    If you don't remember your active profile or haven't created one, click
    the *Select Active Profiles ...* icon immediately to the right of the text
    field. Any active profiles you have are listed in the menu on the left. To
    select an existing profile, highlight its profile ID and select the
    illuminated *right arrow* button to transfer it to the menu on the right. If
    you don't have any existing profile, click on the green *addition* button to
    create a profile and give it an ID. 

    If you're specifying a new profile, the wizard will still create your
    plugin, but your project will need further attention before it is
    deployable. You'll need to specify the necessary properties within the new
    profile; we'll demonstrate specifying these properties in the *Configuring
    Your Liferay Maven Project* section of the 
    [Using Maven From Liferay IDE](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/6-2/using-maven-from-liferay-ide)
    tutorial. 

    You also have the option to create a profile based on a Liferay runtime. To
    do this, select the *Create New Maven Profile Based on Liferay Runtime*
    button to the far right of the *Active profiles* text field. Specify the
    *Liferay runtime*, *New profile id*, and *Liferay version*. For the new
    profile location you can choose to specify your profile in the
    `settings.xml` (recommended) or your project's `pom.xml`. When creating your
    Maven profile based on a Liferay runtime, the IDE automatically populates
    the new profile with the required properties and no additional profile 
    configuration is needed for the plugin. 

7.  Select your project's plugin type.

8.  Click *Finish*.

    ![Figure 1: You can build a Liferay Plugin Project using Maven by completing the setup wizard.](../../images/create-wizard-maven-ide.png) 

The Liferay Plugin Project wizard creates your new plugin project to your
specification. 

Great! You've successfully created a Liferay portlet project using Maven in
Liferay IDE! 

## Related Topics 

[Deploying Liferay Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/deploying-liferay-plugins-with-maven)

[Developing Liferay Portlets with Maven](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-portlets-with-maven)

[Developing Liferay Hook Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-hook-plugins-with-maven)

[Developing Liferay Theme Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-theme-plugins-with-maven)

[Developing Liferay Layout Template Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-layout-template-plugins-with-mav)

