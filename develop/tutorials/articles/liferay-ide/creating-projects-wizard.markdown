# Creating Projects Using the New Project Wizard

If you want to create new plugin projects for Liferay Portal, then Liferay IDE 
is your new best friend. The *New Project Wizard* in Liferay IDE guides you 
through the process of creating new plugin projects for your portal. Even if 
you're not very fond of wizards, you'll quickly discover that the New Project 
Wizard isn't your typical wizard--it gives you plenty of control over the 
creation of your new plugin project. You're able to choose the plugin's type, 
build system, location, and much more. This tutorial shows you how to create new 
projects in Liferay IDE using this wizard. Ready your wands!

## Using the New Project Wizard

To create a new Liferay plugin with the New Project Wizard, select 
*File* &rarr; *New* &rarr; *Liferay Plugin Project*. The first step of the 
wizard contains the bulk of the settings for your new plugin project. There are 
three sections on this screen, each separated by a delimiter. The first section 
lets you specify your project's name, display name, and location. Once you give 
your project a name, the Location field is then autofilled with a file path. 
This is the default file path of your new plugin project. To use a different 
location, uncheck the *Use default location* checkbox and then enter your 
preferred location. You can browse for a location by clicking the icon to the 
right of the Location field.

![Figure 1: The first section of the new project wizard lets you specify your project's name, display name, and location on disk.](../../images/liferay-ide-new-plugin-name-loc.png)

The next section of the New Project Wizard lets you choose the build type for 
your new plugin. You can select either Ant or Maven from the Build Type 
dropdown. The rest of the fields in this section depend on the build type that 
you choose. If you select Ant, then you need to select a Liferay Plugins SDK and 
Liferay runtime instance to use. If you select Maven, then you need to enter the 
artifact version, group id, and the active profile that you want to use. You can 
use the buttons to the right of the Active Profiles field to select or create 
active profiles. Any active profiles that you create here can be found in the 
project's `pom.xml` file. 

![Figure 2: When you select Ant as the build type, you need to also specify a Liferay Plugins SDK and Liferay runtime.](../../images/liferay-ide-build-type-ant.png)

![Figure 3: When you select Maven as the build type, enter the artifact version, group id, and active profile.](../../images/liferay-ide-build-type-maven.png)

The third section of the New Project Wizard contains settings for the specific 
type of plugin project that you're creating. First select the plugin type from 
the dropdown of that name. You can select Portlet, Service Builder Portlet, 
Hook, Layout Template, Theme, Ext, or Web. In versions of Liferay IDE before 
2.1.1, these choices were present as radio buttons.

![Figure 4: The Plugin Type dropdown lets you specify the type of plugin project to create.](../../images/liferay-ide-new-plugin-type.png)

The New Project Wizard gives you some additional options if you select Portlet 
or Service Builder Portlet. With both portlet types, you can use the Include 
Sample Code checkbox to instruct the New Project Wizard to add some basic sample 
code to your portlet. If you select Portlet, then you also get a checkbox for 
instructing the wizard to launch the New Portlet Wizard after the project is 
created. The New Portlet Wizard guides you in the creation of a custom portlet 
class. The final option in this section, and in the first step of the New 
Project Wizard, is a checkbox that lets you add your project to an Eclipse 
working set.

![Figure 5: When Portlet is selected from the dropdown, two checkboxes appear with additional options.](../../images/liferay-ide-new-plugin-portlet.png)

Unless you're creating a portlet or theme, you're done--just click *Finish* and 
get started developing your plugin in Liferay IDE! If you're creating a portlet 
or theme, click *Next* to move on to the next step in the New Project Wizard.

## Additional Steps in the New Project Wizard

The only two plugin types that require additional steps in the New Project 
Wizard are portlet and theme. If you're creating a portlet, the second step of 
the wizard is where you select the portlet's framework, name, and display name. 
You can select Liferay MVC, JSF 2.x, or Vaadin for the portlet's framework. If 
you select JSF 2.x here, you must then click *Next* to go to the third step in 
the wizard where you select the JSF component suite to use. You can choose from 
JSF standard, ICEfaces, Liferay Faces Alloy, PrimeFaces, and RichFaces.

![Figure 6: When creating a portlet, step two of the New Project Wizard lets you select the portlet framework to use.](../../images/liferay-ide-new-plugin-portlet-fw.png)

![Figure 7: Selecting the component suite in a JSF 2.x portlet.](../../images/liferay-ide-new-plugin-jsf.png)

If you're creating a theme, the second step of the New Project Wizard is for 
selecting the theme parent and theme framework. The Theme Parent dropdown lets 
you choose from `_unstyled`, `_styled`, or `classic`. The Theme Framework 
dropdown lets you choose from Velocity, Freemarker, or JSP.

![Figure 8: When creating a theme, step two of the New Project Wizard lets you select the theme's parent and framework.](../../images/liferay-ide-new-plugin-theme-fw.png)

Once you've completed the above selections for your portlet or theme, just click 
*Finish* and get started developing your plugin in Liferay IDE! As you've seen, 
the New Project Wizard isn't so scary after all. Now you know how to use it 
anytime you want to create new plugin projects in Liferay IDE.

## Related Topics

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/plugins-sdk)

[Developing with Maven](/develop/tutorials/-/knowledge_base/maven)

[MVC Portlets](/develop/tutorials/-/knowledge_base/developing-jsp-portlets-using-liferay-mvc)

[Liferay Faces](/develop/tutorials/-/knowledge_base/liferay-faces-jsf-portlets)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/themes-and-layout-templates)

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/customizing-liferay-portal)
