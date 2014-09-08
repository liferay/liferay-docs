# Creating Projects Using the New Project Wizard

If you want to create new plugin projects for Liferay Portal, then Liferay IDE 
is your new best friend. The *new project wizard* in Liferay IDE guides you 
through the process of creating new plugin projects for your portal. This 
process is streamlined into just two steps that let you choose the plugin's 
type, build system, location, and more. Even if you're not very fond of wizards, 
you'll quickly discover that the new project wizard isn't your typical 
wizard--it gives you plenty of control over the creation of your new plugin 
project. This tutorial shows you how to create new projects in Liferay IDE using 
this wizard. Ready your wands!

## Using the Wizard

To create a new Liferay plugin with the new project wizard, select 
*File* &rarr; *New* &rarr; *Liferay Plugin Project*. The first step of the 
wizard contains the bulk of the settings for your new plugin project. There are 
three sections on this screen. The first section lets you specify your project's 
name and location. Once you give your project a name, the Location field is then 
autofilled with a file path. This is the default file path of your new plugin 
project. To use a different location, uncheck the *Use default location* 
checkbox and then enter your preferred location. You can browse for a location 
by clicking the icon to the right of the Location field.

![Figure 1: The first section of the new project wizard lets you specify your project's name and location on disk.](../../images/liferay-ide-new-plugin-name-loc.png)

The next section pertains to the build type that you want to use for your new 
plugin. You can select either Ant or Maven from the Build type dropdown. 
Depending on which of these you select, a different set of options appears for 
the rest of the fields in this section. If you select Ant, you then need to 
select a Liferay Plugins SDK and runtime instance to use. If you select Maven, 
you then need to enter the artifact version, group id, and any active profile 
that you want to use. You can use the buttons to the right of the Active 
profiles field to select or create active profiles. Any active profiles that you 
create here can be found in the project `pom.xml` file. 

![Figure 2: When you select Ant as the build type, you need to also specify a Liferay Plugins SDK and runtime.](../../images/liferay-ide-build-type-ant.png)

![Figure 3: When you select Maven as the build type, enter the artifact version, group id, and active profile.](../../images/liferay-ide-build-type-maven.png)

The third section contains settings regarding the specific type of plugin that 
you're creating. You can select the plugin type from the dropdown of that name. 
You can select Portlet, Service Builder Portlet, Hook, Layout Template, Theme, 
Ext, or Web. In previous versions of Liferay IDE, these choices were present as 
radio buttons.

![Figure 4: The Plugin type dropdown lets you specify the type of plugin project to create.](../../images/liferay-ide-new-plugin-type.png)

If you select Portlet or Service Builder Portlet then you can use the Include 
sample code checkbox that appears. This instructs the new project wizard to add 
some basic sample code to your portlet. If you select Portlet, then you also get 
a checkbox for instructing the wizard to launch the New Portlet Wizard after the 
project is created. The New Portlet Wizard assists you in the creation of a 
custom class for your portlet. The final option in this section, and in the 
first step of the new project wizard, is a checkbox that lets you add your 
project to an Eclipse working set.

![Figure 5: When Portlet is selected from the dropdown, two checkboxes appear with additional options.](../../images/liferay-ide-new-plugin-portlet.png)

There are only two plugin types that require the second step of the new project 
wizard--portlet and theme. If you're creating one of these plugins, click *Next* 
to access the second step of the wizard. Otherwise, click *Finish* and go 
celebrate! If you're creating a portlet, the second step of the wizard is where 
you select the portlet's framework, name, and display name. You can select 
Liferay MVC, JSF 2.x, or Vaadin as the portlet framework. If you select JSF 2.x 
here, then you get a third step in the wizard by clicking *Next*. This lets you 
select the JSF component suite to use. You can choose from JSF standard, 
ICEfaces, Liferay Faces Alloy, PrimeFaces, and RichFaces.

![Figure 6: When creating a portlet, step two of the wizard lets you select the portlet framework to use.](../../images/liferay-ide-new-plugin-portlet-fw.png)

![Figure 7: Selecting the component suite in a JSF 2.x portlet.](../../images/liferay-ide-new-plugin-jsf.png)

If you're creating a theme, the second step of the new project wizard is for 
selecting the theme parent and theme framework. The Theme parent dropdown lets 
you choose from `_unstyled`, `_styled`, or `classic`. The Theme framework 
dropdown lets you choose from Velocity, Freemarker, or JSP.

![Figure 8: When creating a theme, step two of the wizard lets you select the theme's parent and framework.](../../images/liferay-ide-new-plugin-theme-fw.png)

Now just click *Finish*, and you're done! Granted, your plugin project doesn't 
really have any functionality yet--this comes as you develop it. However, you're 
off to a great start!

## Related Topics

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/plugins-sdk)

[Developing with Maven](/develop/tutorials/-/knowledge_base/maven)

[MVC Portlets](/develop/tutorials/-/knowledge_base/developing-jsp-portlets-using-liferay-mvc)

[Liferay Faces](/develop/tutorials/-/knowledge_base/liferay-faces-jsf-portlets)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/themes-and-layout-templates)

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/customizing-liferay-portal)
