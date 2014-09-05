# Creating New Projects in Liferay IDE

If you want to create new plugins for Liferay Portal, then Liferay IDE is your 
new best friend. The *new project wizard* in Liferay IDE guides you through the 
process of creating new plugin projects for your portal. This process is 
streamlined into a series of steps that let you choose the plugin's type, build 
system, location, and more. Even if you have a deep-seated wariness of wizards, 
the new project wizard gives you plenty of control over your new plugin. This 
tutorial shows you how to create new plugin projects using this wizard. Read on 
to get started!

## Starting the Wizard

To create a new Liferay plugin with the new project wizard, select 
*File* &rarr; *New* &rarr; *Liferay Plugin Project*. The first screen of the 
wizard contains the bulk of the settings for your new plugin project. The first 
thing you'll want to do is give your project a name and display name. The 
project name is so that Eclipse can recognize it. The display name is the name 
that the project displays under when deployed to Liferay.                         

Once you give your project a name, you'll notice that the location field is 
filled in with a file path. This is the default file path of your plugin 
project. To enter a different location, uncheck the *Use default location* 
checkbox and enter your preferred location. 

The next section of the new project wizard pertains to the build type that you 
want to use for your plugin. You can select either Ant or Maven from the 
*Build type* dropdown. Depending on what you select, you'll get a different set 
of options for the remainder of this section. If you select Ant, you then need 
to select a Liferay Plugins SDK and runtime instance to use. If you select 
Maven, then you need to select the artifact version, group id, and any active 
profiles that you want to use.

The final section of the new project wizard lets you select the type of plugin 
that you want it to be from the *Plugin type* dropdown. You can select Portlet, 
Service Builder Portlet, Hook, Layout Template, Theme, Ext, or Web. These 
choices were present as radio buttons in previous versions of Liferay IDE.
