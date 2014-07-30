# Creating a Hook

You can create hooks in a Plugins SDK project or a Liferay Maven project.
Liferay IDE lets you create either project type using command line tools: Ant in 
the Plugins SDK or Maven Archetype in Maven. 

In this tutorial, you'll learn how to create hooks in a Plugins SDK project,
using Liferay IDE and Ant, from the command line. Regardless of whether you use 
Liferay IDE or your terminal to create hooks, hooks projects are stored in the 
Plugins SDK's `hooks` directory. To learn how to create Liferay Maven projects, 
see the tutorial [Creating Liferay Maven plugins from IDE](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial) 
or the tutorial [Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial).

Go ahead and jump right in and start with Liferay IDE. 

## Using Liferay IDE

Follow these steps and you'll have a hook made in IDE in no time:

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2.  Assign a project name and display name. Notice that upon entering text into 
	the project name field, the wizard conveniently inserts grayed-out text as 
	the plugin's default display name. The wizard derives the default display 
	name from the project name, starts it in upper-case, and leaves off the 
	plugin type suffix *Hook* because the plugin type is automatically appended 
	to the display name in Liferay Portal. The IDE saves you from repetitively
    appending the plugin type to the display name; in fact, the IDE ignores any
    plugin type suffix if you happen to append it to the display name.

3.  Select the build type, Plugins SDK, and Liferay runtime. 

    Select the Ant build type, a Plugins SDK and a Liferay runtime.
    
    For more information, see sections *Setting Up the Liferay Plugins SDK* and *Liferay Portal
    Runtime and Server Setup* from  
    [Developing Apps with Liferay IDE](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-apps-with-liferay-ide-liferay-portal-6-2-dev-guide-02-en) 

4.  Select the *Hook* Plugin Type. 

5.  Click *Finish*. 

Figure 1 shows a hook plugin with filled in values.

![Figure 1: Creating a hook plugin is easy with Liferay IDE. Just click *File* &rarr; *New* &rarr; *Liferay Plugin Project*, enter a project name and display name, select a build type, Plugins SDK, and Liferay Runtime, select *Hook*, and then click *Finish*.](../../images/hooks-create-hook-project-with-sdk.png)

The Plugins SDK automatically names the hook by appending "-hook" to the project
name. With Liferay IDE, you can create a hook in a completely new plugin project
or create a hook in an existing plugin project. Use *File* &rarr; *New* &rarr;
*Liferay Plugin Project* to create a new plugin project and *File* &rarr; 
*New* &rarr; *Liferay Hook Configuration* to create a hook in an existing plugin 
project.

## Using the terminal

Navigate to your Plugins SDK directory in a terminal and enter the appropriate 
command for your operating system:

The following example below uses "example" as the name; make sure you replace it
with your own.

1.  In Linux and Mac OS X, enter

        ./create.sh example "Example"

2.  In Windows, enter

        create.bat example "Example"

A BUILD SUCCESSFUL message from Ant tells you there's a new folder named
`example-hook` inside the Plugins SDK's `hooks` folder. The Plugins SDK
automatically named the hook by appending "-hook" to the project name. 

Now that you've created a hook, it's time to go ahead and deploy it.

## Deploying the Hook

***Using Liferay IDE:*** Click and drag your hook project onto your server.
Upon deployment, your server displays messages indicating that your hook was
read, registered and is now available for use. 

    Reading plugin package for example-hook
    Registering hook for example-hook
    Hook for example-hook is available for use

Voila! Your hook deployed.

---

 ![Note](../../images/tip-pen-paper.png) **Note:** If the Liferay server
 prints the following message to your console, the *Marketplace Portlet* and 
 *Portal Compatibility Hook* must not already be deployed on your server.

    Plugin example-hook requires marketplace-portlet, portal-compat-hook

 For Liferay 6.2.0 CE GA1, you can fork and clone Liferay's *liferay-plugins*
 project from GitHub, checkout the respective branch and/or tag, and deploy
 each plugin. You can install the Plugins SDK in Liferay IDE and import each
 plugin and deploy them. Here is information on each of the plugins: 

- *Marketplace Portlet* (`marketplace-portlet`) - is available at
`liferay-plugins/portlets/marketplace-portlet`.
- *Portal Compatibility Hook* (`portal-compat-hook`) - is available at 
`liferay-plugins/hooks/portal-compat-hook`.

---

If you ever need to redeploy your plugin while in Liferay IDE, right-click
your plugin's icon located underneath your server and select *Redeploy*. 

***Using the terminal:*** Open a terminal window in your `hooks/example-hook`
directory of your Plugins SDK and enter

    ant deploy

A BUILD SUCCESSFUL message indicates your hook is now being deployed. If you
switch to the terminal window running Liferay, in a few seconds you
should see the message "Hook for example-hook is available for use". 

---
 ![Note](../../images/tip-pen-paper.png) **Note:** When you created portlets and
 themes, they were fully functional upon deployment. Hooks aren't like that
 because they're Liferay customizations. The default customization is the
 original implementation! 
---

Now that you know how to create a hook using Liferay IDE and Ant, it's time to 
get out there and create!

## Next Steps

 [Performing a Custom Action Using a Hook] (/tutorials/-/knowledge_base/performing-a-custom-action-using-a-hook-lp-6-2-develop-tutorial)

 [Creating Liferay Maven plugins from IDE] (/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide-lp-6-2-develop-tutorial)

 [Creating Liferay Maven Plugins from the Command Line](/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin-lp-6-2-develop-tutorial)
