# Creating Projects Using the New Project Wizard [](id=creating-projects-using-the-new-project-wizard)

If you want to create new plugin projects for Liferay Portal, then Liferay IDE
is your new best friend. The *New Project Wizard* in Liferay IDE/Developer
Studio guides you through the process of creating new plugin projects. Even if
you're not very fond of wizards, you'll quickly discover that the New Project
Wizard isn't your typical wizard--it gives you plenty of control over the
creation of your new plugin project. You're able to choose the plugin's type,
build system, location, and much more. This tutorial shows you how to create new
projects in Liferay IDE using this wizard. Ready your wands!

## Using the New Project Wizard

To create a new Liferay plugin with the New Project Wizard, select *File* &rarr;
*New* &rarr; *Liferay Plugin Project*. The first screen of the wizard contains
the bulk of the settings for your new plugin project. The screen's content is
divided into three sections. The first section lets you specify your project's
name, display name, and location. Once you give your project a name, the
Location field is then automatically populated with a file path appropriate for
your project's build framework. This is the default file path of your new plugin
project. To use a different location, uncheck the *Use default location*
checkbox and enter your preferred location. You can browse for a location by
clicking the icon to the right of the Location field.

![Figure 1: The first section of the initial new project wizard screen lets you specify your project's name, display name, and location on the file system.](../../images/liferay-ide-new-plugin-name-loc.png)

The next section of this initial screen lets you choose the build type for your
new plugin. You can select either Ant or Maven from the Build Type selector. The
rest of the fields in this section depend on the build type that you choose. If
you select Ant, then you need to select a Liferay Plugins SDK and Liferay
runtime instance to use. If you select Maven, then you need to enter the
artifact version, group ID, and the active profile that you want to use. You can
use the buttons to the right of the Active Profiles field to select or create
active profiles. Any active profiles that you create here can be found in the
project's `pom.xml` file. 

![Figure 2: When you select Ant as the build type, you must also specify a Liferay Plugins SDK and Liferay runtime.](../../images/liferay-ide-build-type-ant.png)

![Figure 3: When you select Maven as the build type, you must enter an artifact version and group ID, and you can specify active profiles.](../../images/liferay-ide-build-type-maven.png)

The third section of the New Project Wizard contains settings for the specific
type of plugin project that you're creating. First select the plugin type from
the selector of that name. You can select Portlet, Service Builder Portlet,
Hook, Layout Template, Theme, Ext, or Web. 

![Figure 4: The Plugin Type selector lets you specify the type of plugin project to create.](../../images/liferay-ide-new-plugin-type.png)

The New Project Wizard gives you some additional options if you select the
Portlet or Service Builder Portlet plugin type. With both portlet types, you can
use the Include Sample Code check box to instruct the New Project Wizard to add
some basic sample code to your plugin. If you select Portlet, then you also get
a check box for instructing the wizard to launch the New Portlet Wizard after the
project is created. The New Portlet Wizard guides you in the creation of a
custom portlet class. The final option in this section of the initial New
Project Wizard screen is a check box that lets you add your project to an Eclipse
working set.

![Figure 5: When Portlet is selected from the selector, options for including sample code and launching a New Portlet Wizard appear.](../../images/liferay-ide-new-plugin-portlet.png)

Unless you're creating a portlet or theme, you're done--just click *Finish* and 
get started developing your plugin in Liferay IDE! If you're creating a portlet 
or theme, click *Next* to move on to the next step in the New Project Wizard.

## Additional Steps in the New Project Wizard

The only two plugin types that require additional steps in the New Project
Wizard are portlet and theme plugin types. If you're creating a portlet, the
second step of the wizard is where you select the portlet's framework. If you
selected the Include Sample Code check box on the first step of the wizard, then
the second step also lets you enter the portlet's name and display name. For the
portlet's framework, you can select Liferay MVC, JSF 2.x, or Vaadin. If you
select JSF 2.x here, you must then click *Next* to go to the third step of the
wizard where you select a JSF component suite to use. You can choose the JSF
standard, ICEFaces, Liferay Faces Alloy, PrimeFaces, or RichFaces component
suite.

![Figure 6: When creating a portlet, step two of the New Project Wizard lets you select a portlet framework to use in the project.](../../images/liferay-ide-new-plugin-portlet-fw.png)

![Figure 7: You can develop JSF 2.x portlets using several popular component suites.](../../images/liferay-ide-new-plugin-jsf.png)

If you're creating a theme, the second step of the New Project Wizard is for
selecting the theme parent and theme framework. The Theme Parent selector lets
you choose the `_unstyled`, `_styled`, or `classic` theme as the parent. The
Theme Framework selector lets you choose Velocity, Freemarker, or JSP template
frameworks to use in your theme. 

![Figure 8: When creating a theme, step two of the New Project Wizard lets you select the theme's parent and framework.](../../images/liferay-ide-new-plugin-theme-fw.png)

Once you've completed the above selections for your portlet or theme, click
*Finish* and get started developing your plugin in Liferay IDE! As you've seen,
the New Project Wizard isn't so scary after all. Now you know how to use it
anytime you want to create new plugin projects for your Liferay Portal.

## Related Topics

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk)

[Developing with Maven](/develop/tutorials/-/knowledge_base/6-2/maven)

[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[Liferay Faces](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-jsf-portlets)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
