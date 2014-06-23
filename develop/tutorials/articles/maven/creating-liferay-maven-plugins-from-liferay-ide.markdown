# Creating Liferay Maven Plugins from Liferay IDE [](id=creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)

Liferay offers many archetypes to help create Maven projects for multiple plugin
types, including portlet, theme, hook, and layout template plugins. To make sure
that you can find the archetypes you need, we provide archetypes for each of
these plugin types for various versions of Liferay. *Archetype* is Maven's
project templating toolkit. In this tutorial, we explain how to use Archetype to
create a Liferay portlet project. With Archetype, you can use the same steps we
detail below to generate Liferay plugin projects of any type. 

---

![note](../../images/tip-pen-paper.png) **Note:** Make sure Maven is installed
 and that its executable is in your `$PATH` environment variable. Maven must 
 also be fully configured. To configure Maven, follow the steps in the tutorial 
 *Developing Liferay Plugins Using Maven*.

---

We'll demonstrate two ways of creating Liferay plugins with Maven: using Liferay
IDE and using the command line. First, let's learn how to use Maven archetypes
to generate a Liferay plugin project using Liferay IDE:

1.  Select *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2.  Assign a project name and display name. For example, you can use
    *sample-portlet* and *Sample* for the project name and display name,
    respectively. Notice that upon entering *sample-portlet* as the project
    name, the wizard conveniently inserts *Sample* in grayed-out text as the
    portlet's default display name. The wizard derives the default display name
    from the project name, starts it in upper-case, and leaves off the plugin
    type suffix *Portlet* because the plugin type is automatically appended to
    the display name in Liferay Portal. The IDE saves the you from repetitively
    appending the plugin type to the display name; in fact, the IDE ignores any
    plugin type suffix if you happen to append it to the display name. 

3.  Select *Maven (liferay-maven-plugin)* for the build type. Notice that some
    of the options for your plugin project changed, including the *Location* 
    field, which is set to the user's workspace by default. Choose the parent 
    directory in which you want to create the plugin project. It is a best 
    practice to create a parent project for your Maven plugins, so they can all
    share common project information. See section *Using a Parent Plugin
    Project* for details. 

4.  Specify the *Artifact version* and *Group id*. For example, you can use
    `1.0-SNAPSHOT` and `com.liferay.sample` for the artifact version and group
    id, respectively.

5.  Specify the active profile that you'd like your Liferay plugin project to
    use. If you don't remember your active profile or haven't created one, click
    the *Select Active Profiles ...* icon immediately to the right of the text
    field. If you have any active profiles, they will be listed in the menu on
    the left. To select an existing profile, highlight its profile ID and select
    the illuminated *right arrow* button to transfer it to the menu on the
    right. If you don't have any existing profile, click on the green *addition*
    button to create a profile and give it an ID. 

    If you're specifying a new profile, the wizard will still create your
    plugin, but it will need further attention before it is deployable. You'll
    need to specify the necessary properties within the new profile; we'll
    demonstrate specifying these properties in the *Configuring your Liferay
    Maven Project* section of the *Using Maven From Liferay IDE* tutorial. 

    You also have the option to create a profile based on a Liferay runtime. To
    do this, select the *Create New Maven Profile Based on Liferay Runtime*
    button to the far right of the *Active profiles* text field. Specify the
    *Liferay runtime*, *New profile id*, and *Liferay version*. For the new
    profile location you can choose to specify your profile in the
    `settings.xml` (recommended) or your project's `pom.xml`. When creating your
    Maven profile based on a Liferay runtime, the IDE automatically populates
    the new profile with the required properties, and no additional profile 
    configuration is needed for the plugin. 

6.  Select the *Portlet* plugin type and then click *Finish*.

	![Figure 2.27: You can build a Liferay Plugin Project using Maven by completing the setup wizard.](../../images/create-wizard-maven-ide.png) 

## Next Steps [](id=next-steps-lp-6-2-develop-tutorial-3)

Great! You've successfully created a Liferay portlet project using Maven in
Liferay IDE! Next, let's learn how to create Liferay Maven plugins using the
command line.

[Creating Liferay Plugins With Maven From the Command Line](http://www.liferay.com)
