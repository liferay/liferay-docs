# Creating a Theme Project [](id=creating-a-theme-project)

The rest of this Learning Path assumes you've followed the instructions for
setting up [Liferay IDE](/develop/learning-paths/mvc/-/knowledge_base/6-2/developing-apps-with-liferay-ide)
or [Liferay Developer Studio](/develop/learning-paths/mvc/-/knowledge_base/6-2/developing-applications-with-liferay-developer-stu). 
If you've done that, you're ready to begin! 

1. Select *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2. In the Project Wizard, enter *lunar-resort-theme* for the *Project name* and 
*Lunar Resort Theme* for the *Display name*. Leave the *build type* as *Ant* and 
select the proper Plugins SDK and matching Liferay runtime.

3. Select *Theme* for the *Plugin type* and click *Next*

4. Leave the *Theme parent* and *Theme framework* as *_styled* and *Freemarker* 
and click *Finish*.

![Figure 1: The Project Wizard makes it easy to build a theme.](../../../images/theme-creation-01.png)

+$$$

**Note:** The first time you create a project, make sure you're online. In the 
background, Liferay's Plugins SDK downloads several necessary JAR files. This
keeps the initial Plugins SDK download small, but it may take several minutes to
create your first project. Subsequent projects are created almost instantly.

$$$

You'll now see your project in the Package Explorer on the left. What you've
just done is create a blank Liferay theme based off of Liferay's *_styled* 
theme. 

Rather than build a theme from scratch, it is best practice to use a Liferay 
base theme as a starting point to ensure full compatibility. The changes you 
make to the theme override the default settings of the *_styled* theme.

You can learn about the theme's anatomy next.
