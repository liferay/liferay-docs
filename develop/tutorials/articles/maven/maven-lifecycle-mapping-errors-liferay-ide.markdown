# Resolving Maven Lifecycle Mapping Errors

The `m2e-liferay` plugin for Liferay IDE automatically contributes the lifecycle 
mappings required for using Maven with your Liferay projects. If you're getting 
lifecycle mapping errors, then it's likely that you don't have the `m2e-liferay` 
plugin installed. Installation instructions and a more thorough discussion of 
this plugin are contained in the tutorial [Using Maven from Liferay IDE](/tutorials/-/knowledge_base/using-maven-from-liferay-ide). 

Since Liferay IDE won't let you create Liferay Maven projects without the 
`m2e-liferay` plugin, the most common scenario that results in lifecycle mapping 
errors is importing Liferay Maven projects without the plugin installed. 
Fortunately, Liferay IDE lets you import a Liferay Maven project *and* install 
the `m2e-liferay` plugin at the same time. This tutorial begins by showing you 
how to verify that your Maven lifecycle mapping is correct. You're then shown 
how to import a Liferay Maven project in Liferay IDE and install the 
`m2e-liferay` plugin. Now go ahead and get started! 

## Verifying Maven Lifecycle Mapping

Making sure that your project has the correct Maven lifecycle mapping is simple. 
Just right-click your project and select *Properties*. In the window that 
appears, open the *Maven* section on the left and then click 
*Lifecycle Mapping*. 

![Figure 1: The lifecycle mapping for this Liferay Maven project is correct.](../../images/maven-lifecycle-mapping-correct.png)

If the lifecycle mapping is correct, the liferay:build-css item should have a 
green checkmark next to it. The Mapping and Source fields for liferay:build-css 
should also contain configurator and extension, respectively. If the lifecycle 
mapping isn't correct, then install the `m2e-liferay` plugin using the 
instructions contained in the tutorial [Using Maven from Liferay IDE](/tutorials/-/knowledge_base/using-maven-from-liferay-ide). 
Next, you'll learn how to install the `m2e-liferay` plugin when importing a 
Maven project that has Liferay properties. 

## Importing a Liferay Maven Project that has Liferay Properties

Importing and installing the `m2e-liferay` plugin is very straightforward when 
importing a Liferay Maven project that contains Liferay properties. The plugin 
installer launches immediately after you specify the project to import, 
effectively combining these two processes. To begin, select 
*File* &rarr; *Import* in Liferay IDE. Then open the *Maven* section in the 
window that appears, select *Existing Maven Projects*, and click *Next*. 

![Figure 2: The first step in importing an existing Liferay Maven project.](../../images/maven-ide-import-01.png)

The next step is to specify the project to import. First, select the location of 
the project in the *Root Directory* field. Once you select a project its 
`pom.xml` appears in the Projects field. Make sure that the checkbox next to the 
`pom.xml` is checked. Note that the example here imports the project 
`maven-test`. Since the Liferay properties of this project are contained in a 
profile in the `pom.xml`, the profile must be specified in the Profiles field of 
the Advanced section. You don't need to specify a profile here if the Liferay 
properties are contained elsewhere in the project. For example, if the Liferay 
properties are specified for the whole project in a `<properties>` tag in the 
`pom.xml`, then you don't need to put anything in the Advanced section. Click 
*Finish* when you're done here. 

![Figure 3: Specify the Liferay Maven project that you want to import and click *Finish*.](../../images/maven-ide-import-02.png)

Now comes the magical part! Liferay IDE detects that you don't have the correct 
lifecycle mappings for the project you're importing and suggests an action; 
install Maven integration for Liferay IDE (the `m2e-liferay` plugin). Click 
*Finish* to move to the next step. 

![Figure 4: Click *Finish* to launch the installer for Maven integration in Liferay IDE.](../../images/maven-ide-import-03.png)

The next set of steps takes you through the installation process for the 
`m2e-liferay` plugin. First, make sure that the checkbox for the `m2e-liferay` 
plugin is checked and then click *Next*. This takes you to the window that lets 
you review the items you've marked for installation. Verify that the 
`m2e-liferay` plugin is listed and click *Next*. The final window presents you 
with the license agreements. Select the radio button to accept the license 
agreements and click *Finish*. The following sequence of screenshots shows these 
steps: 

![Figure 5: The first step of the installer lets you select the `m2e-liferay` plugin for installation.](../../images/maven-ide-m2e-install-01.png)

![Figure 6: Review the item that you selected for installation in the previous step of the installer.](../../images/maven-ide-m2e-install-02.png)

![Figure 7: Agree to the licenses and click *Finish* to complete the installation.](../../images/maven-ide-m2e-install-03.png)

Upon clicking Finish, the `m2e-liferay` plugin downloads and installs. Note that 
this may take a few minutes to complete. When the installation is complete, 
you're prompted to restart Eclipse. Restart Eclipse and you're all set! Next, 
you'll learn how to import a Liferay Maven project that doesn't have Liferay 
properties. 

## Importing a Liferay Maven Project that is Missing Liferay Properties

What if the project that you want to import doesn't contain Liferay properties? 
No sweat! Liferay IDE still has you covered. The initial import steps are the 
same as in the section above. The process diverges though after you specify the 
project to import. As before, Liferay IDE detects that you don't have the 
correct lifecycle mappings for the project you're importing and suggests an 
action. However, since there are no Liferay properties in the project, it lists 
an error. When you click *Finish*, you're presented with a warning that tells 
you you're about to import a project that contains errors. Don't worry! You'll 
resolve this in the following steps. Click *OK* to move on. 

![Figure 8: The error occurs because Liferay IDE can't find the correct plugin connectors.](../../images/maven-ide-no-props-01.png)

As indicated in the previous dialog box, the project imports but it contains 
errors. To resolve this you need to download and install the `m2e-liferay` 
plugin. First, open the Liferay IDE preferences by selecting 
*Window* &rarr; *Preferences*. Then open the *Maven* section on the left, select 
*Discovery*, and click the *Open Catalog* button. This opens the catalog of 
plugins that can be installed for Maven. 

![Figure 9: In Preferences, open the plugin catalog for Maven.](../../images/maven-ide-no-props-02.png)

Now just scroll down in the catalog, check the checkbox for 
*m2e connector for liferay-maven-plugin*, and click *Finish*. This launches the 
installer for the `m2e-liferay` plugin. The steps in the installer are identical 
to those in the previous section. Once the installation completes and Liferay 
IDE restarts, the error in your imported project is gone! 

![Figure 10: Select the `m2e-liferay` plugin for installation and click *Finish* to launch the installer.](../../images/maven-ide-no-props-03.png)

Great! Now you know how to resolve lifecycle mapping errors for Liferay Maven 
projects in Liferay IDE. Just remember that it's the `m2e-liferay` plugin that 
takes care of the Maven lifecycle mapping. 

## Related Topics

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/liferay-ide)

[Developing with the Plugins SDK](/tutorials/-/knowledge_base/plugins-sdk)

[MVC Portlets](/tutorials/-/knowledge_base/developing-jsp-portlets-using-liferay-mvc)

[Service Builder and Services](/tutorials/-/knowledge_base/service-builder)

[Deploying Plugins](/tutorials/-/knowledge_base/deploying-plugins)
