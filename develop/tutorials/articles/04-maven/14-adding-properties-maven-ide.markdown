# Adding Properties to a Maven Project in Liferay IDE [](id=adding-properties-to-a-maven-project-in-liferay-ide)

When working with Maven projects in Liferay IDE, you may encounter situations
where your project is missing [required Maven properties](/develop/tutorials/-/knowledge_base/6-2/using-liferay-maven-parent-plugin-projects).
Maven properties go missing when a Maven profile ID isn't associated with the
project. A project's creator may accidentally delete the profile ID from the
project or neglect to add one. Either way, Liferay IDE reports the missing
properties as errors in your `pom.xml` file's editor. Liferay IDE's Markers view
reports such errors in a section named Maven Configuration Problem. 

![Figure 1: Liferay IDE lets you know when your Maven project is missing required properties.](../../images/maven-quick-fix-01.png)

Liferay IDE, fortunately, provides a way to fix these problems: the *Quick Fix*
tool. The Quick Fix tool lets you select or create a profile for your Maven
project, so that your project can access the Maven properties it needs. This
tutorial shows you how to address missing Maven properties using the Quick Fix
tool. Read on to find out how! 

## Launching the Quick Fix Tool [](id=launching-the-quick-fix-tool)

The first thing you need to do is launch the Quick Fix tool. To do this, open
the *Maven Configuration Problem* section of the *Markers* view in Liferay IDE.
Then, right-click the error and select *Quick Fix* from the context menu. The
following screenshot shows the Quick Fix option in the context menu: 

![Figure 2: To open the Quick Fix tool, right-click the error and select *Quick Fix* from the context menu.](../../images/maven-quick-fix-02.png)

The Quick Fix tool opens, showing you information about the error. The text of
the error appears at the top of the window, followed by possible solutions. The
panel at the bottom of the tool shows the file in which the error occurs. This
screenshot shows the Quick Fix tool: 

![Figure 3: The Quick Fix tool presents you with information about Maven property errors and possible fixes.](../../images/maven-quick-fix-03.png)

In the case shown in the figure above, there are two fix options. Both options
fix the error by attaching a Maven profile to the project. The fixes differ,
however, in how they enable you to provide profiles: one solution lets you
create a new profile, while the other lets you select from existing profiles
available in your environment. The remainder of this tutorial explains how to
use these options. The next section walks you through creating a new Maven
profile using the Quick Fix tool. 

## Creating a New Maven Profile with the Quick Fix Tool [](id=creating-a-new-maven-profile-with-the-quick-fix-tool)

If you want to select an existing Maven profile for your project, you can skip
this section. Otherwise, read on! To create a new Maven profile, select that
option in the Quick Fix tool and click *Finish*. A new window opens letting you
create the new profile. You are presented with options for selecting your
Liferay runtime, profile ID, Liferay version, and profile location. 

![Figure 4: You can create a new Maven profile with the Quick Fix tool.](../../images/maven-quick-fix-04.png)

First, select your Liferay runtime from the menu. If you don't have a Liferay
runtime configured or you wish to use a new one, click the new runtime icon to
the right of the Liferay runtime combo box. Next, enter an ID for your new
profile. A default Liferay version is selected for you automatically based on
your runtime. Make sure its Liferay version matches your runtime. Last but
certainly not least, select the location for your new profile. By default, your
project's `pom.xml` is selected. Alternatively, you can select your local
`settings.xml` file. That's it! Now just click *OK*, and the missing properties
error is gone! 

## Selecting an Existing Maven Profile with the Quick Fix Tool [](id=selecting-an-existing-maven-profile-with-the-quick-fix-tool)

To resolve missing Maven properties, the Quick Fix tool also lets you select
existing Maven profiles to use with your project. To do this, select that option
in the Quick Fix tool and click *Finish*. A new window pops up that shows your
available profiles on the left and selected profiles on the right. 

![Figure 5: The Quick Fix tool also lets you pick existing Maven profiles to use with your project.](../../images/maven-quick-fix-05.png)

To select a profile to use, click it in the column on the left and then click
the *Move Right* arrow between the two columns. This moves the profile into the
column on the right, so your project can use it. If you move a profile to the
column on the right by mistake, select it and then click the red *X* button on
the right. This removes that profile from the right hand column.  That's all
there is to it! Once you've selected the profiles you want to use, just click
*OK* and your missing properties error disappears! 

As you can see, the Quick Fix tool is aptly named. With just a few steps, it
helps you create or find properties for your Maven project. 

## Related Topics [](id=related-topics)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk)

[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Deploying Plugins](/develop/tutorials/-/knowledge_base/6-2/deploying-plugins)
