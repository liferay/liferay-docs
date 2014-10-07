# Adding Properties to a Maven Project in Liferay IDE

When working with Maven projects in Liferay IDE, you may encounter situations 
where your project is missing the required Maven properties. For a description 
of these properties, please see [this tutorial](/tutorials/-/knowledge_base/6-2/using-liferay-maven-parent-plugin-projects). 
Maven properties go missing when a Maven profile id isn't associated with the 
project. This can be caused if you or the person that created the project 
neglect to add a Maven profile id to the project, or delete the profile id from 
the project. Regardless, the resulting error is reported by Liferay IDE in your 
`pom.xml` file. It also appears in the Markers tab of Liferay IDE under the 
Maven Configuration Problem section.

![Figure 1: Liferay IDE lets you know when your Maven project is missing required properties.](../../images/maven-quick-fix-01.png)

Fortunately, Liferay IDE provides a way to fix this problem: the *Quick Fix* 
tool. The Quick Fix tool lets you select or create a profile id for your Maven 
project, thereby adding the missing Maven properties. This tutorial shows you 
how to accomplish this. Read on to find out how!

## Launching the Quick Fix Tool

The first thing you need to do is launch the Quick Fix tool. To do this, first 
open the *Maven Configuration Problem* section of the *Markers* tab in Liferay 
IDE. Then right-click the error and select *Quick Fix* from the context menu. 
This is shown in the following screenshot:

![Figure 2: To open the Quick Fix tool, right-click the error and select *Quick Fix* from the context menu.](../../images/maven-quick-fix-02.png)

The Quick Fix tool then opens, showing you information about the error. The text 
of the error appears at the top of the window, followed by the possible 
solutions. The file the error occurs in is shown at the bottom of the window. 
This screenshot shows the Quick Fix tool:

![Figure 3: The Quick Fix tool presents you with information about the error, including possible fixes.](../../images/maven-quick-fix-03.png)

There are two solutions you can pick from. Both fix the error by attaching a 
Maven profile to your project. However, they differ in where this profile comes 
from: one solution lets you create a new profile, while the other lets you 
select from existing profiles. The next section walks you through creating a new 
Maven profile using the Quick Fix tool.

## Creating a New Maven Profile with the Quick Fix Tool

If you want to select an existing Maven profile for your project, you can skip 
this section. Otherwise, read on! To create a new Maven profile, select that 
option in the Quick Fix tool and click *Finish*. A new window then opens that 
lets you create the new profile. You are presented with options for selecting 
your Liferay runtime, profile id, Liferay version, and profile location.

![Figure 4: You can create a new Maven profile with the Quick Fix tool.](../../images/maven-quick-fix-04.png)

First select your Liferay runtime from the menu. If you don't have a Liferay 
runtime set up or simply wish to use a new one, you can create it by clicking 
the button to the right of the menu. Next, enter the id for your new profile. 
This can be anything you like. For example, the screenshot above uses sample-1 
as the profile id. Now make sure that the Liferay version selected in the menu 
matches that of your runtime. This is automatically selected for you based on 
your runtime. Last but certainly not least, select the location for your new 
profile. This is set to your project's `pom.xml` file by default. Alternatively, 
you can select your local `settings.xml` file. That's it! Now just click *OK* 
and the error is gone!

## Select an Existing Maven Profile with the Quick Fix Tool

The other option the Quick Fix tool gives you is selecting an existing Maven 
profile to use with your project. To do this, select that option in the Quick 
Fix tool and click *Finish*. A new window pops up that shows your available 
profiles on the left and selected profiles on the right.

![Figure 5: The Quick Fix tool also lets you pick existing Maven profiles to use with your project.](../../images/maven-quick-fix-05.png)

To select a profile to use, simply click it in the column on the left and then 
click the *Move Right* arrow between the two columns. This moves the profile 
into the column on the right, selecting it for use. If you move a profile to 
the column on the right by mistake, simply click it and then click the red *X* 
button on the right. This removes that profile from the right hand column. 
That's all there is to it! Once you've selected the profile you want to use, 
just click *OK* and your error disappears!

As you can see, the Quick Fix tool is aptly named. With just a few steps, you 
can use it to resolve the problem of missing properties in your Maven project. 

## Related Topics

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with the Plugins SDK](/tutorials/-/knowledge_base/6-2/plugins-sdk)

[MVC Portlets](/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)

[Deploying Plugins](/tutorials/-/knowledge_base/6-2/deploying-plugins)
