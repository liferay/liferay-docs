# Creating a Theme Project

By this point you should already be setup with a development environment with 
either Liferay IDE or Developer Studio, so you can dive right in to creating 
the theme. 

1. Select *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2. In the Project Wizard, enter *lunar-resort-theme* for the *Project name* and 
*Lunar Resort Theme* for the *Display name*. Leave the *build type* as *Ant* and 
select the proper *Plugins SDK* and matching *Liferay runtime*.

3. Select *Theme* for the *Plugin type* and click *Next*

4. Leave the *Theme parent* and *Theme framework* as *_styled* and *Freemarker* 
and click *Finish*.

![Figure 1: The Project Wizard makes it easy to build a theme.](../../images/theme-creation-01.png)

+$$$

**Note:** The first time you create a project, make sure you're online. In the 
background, Liferay's Plugins SDK downloads several JAR files that it needs in 
order to operate. This keeps the initial Plugins SDK download small but means 
that it may take several minutes to create your first project. Subsequent 
projects are created almost instantly.

$$$

You'll now see your project in the Package Explorer on the left. What you've
just done is create a blank Freemarker theme based off of Liferay's *_styled* 
theme. 

Rather than build a theme from scratch, it is best practice to use a Liferay 
base theme as a starting point to ensure full compatibility. The changes you 
make to the theme will override the default settings of the *_styled* theme.

You can learn about the theme's anatomy next.