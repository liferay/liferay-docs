# Creating a Hook Project in the Plugins SDK [](id=creating-a-hook-project-in-the-plugins-sdk)

Hook plugins are a special type of Liferay plugin that is designed for
customizing Liferay in some way. You can do anything from customizing a service,
to modifying a core JSP, to changing properties, to adding language keys, and
more. 

You can create hooks in a Plugins SDK project or a Liferay Maven project, using
Liferay IDE/Developer Studio or the command line. This tutorial focuses on
creating hooks in a Plugins SDK project from Liferay IDE and from the command
line. 

To learn how to create Liferay Maven projects, see the tutorial
[Creating Liferay Maven plugins from IDE](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide) 
or the tutorial
[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin). 

Creating a hook using Liferay IDE is easiest to learn first. So go ahead and
jump right in by creating your hook project with Liferay IDE. 

## Creating a Hook Project from Liferay IDE

Follow these steps to create a hook in IDE in no time:

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*.

2.  Fill in the project name and display name fields.
 
3.  Choose Ant for the build type and select the appropriate *Plugins SDK* and 
	*Liferay runtime*. 

4.  Select *Hook* as your plugin type. 

5.  Click *Finish*.

Figure 1 shows the New Liferay Plugin Project wizard with filled in values for a
hook plugin project. 

![Figure 1: Creating a hook plugin is easy with Liferay IDE. Just click *File* &rarr; *New* &rarr; *Liferay Plugin Project*, enter a project name and display name, select a build type, Plugins SDK, and Liferay Runtime, select *Hook*, and then click *Finish*.](../../images/hooks-create-hook-project-with-sdk.png)

The Plugins SDK automatically names the hook by appending "-hook" to the project
name. With Liferay IDE, you can create a hook in a completely new plugin project
or create a hook in an existing plugin project. Use *File* &rarr; *New* &rarr;
*Liferay Plugin Project* to create a new plugin project or *File* &rarr; 
*New* &rarr; *Liferay Hook Configuration* to create a hook in an existing plugin 
project.

## Creating a Hook Project from the Command Line

From your command line terminal, navigate to your Plugins SDK's `hooks` folder.
To create a hook project, you must execute the *create* script. Here's the format
to follow in executing the script: 

    create.[sh|bat] [project-name] "[Hook Display Name]"

On Linux and Mac OS X, you'd enter a command similar to the one in this example:

    ./create.sh my-hook "My Hook"

On Windows, you'd enter a command similar to the one in this example:

    create.bat my-hook "My Hook"

Liferay IDE's *New Project* wizard and the *create* scripts generate hook
projects in your Plugin SDK's `hooks` folder. The Plugins SDK automatically
appends "-hook" to your project name. 

Whether you created your hook project from the Liferay IDE or from the command
line, you end up with the same project structure. Hooks are designed to
customize something in Liferay. You begin by mirroring the structure of
Liferay's code that you plan to customize. A hook plugin is built to contain
this: 

- `[project-name]-hook/`
    - `docroot/WEB-INF/src/`
    - `docroot/`
        - `META-INF/`
            - `MANIFEST.MF`
        - `WEB-INF/`
            - `lib/`
            - `liferay-hook.xml`
            - `liferay-plugin-package.properties`
            - `web.xml`
    - `build.xml`

The particular files you'll work on depend on the Liferay features you're
overriding with your hook.

+$$$

**Note:** If the Liferay server
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

$$$


+$$$

**Note:** When you created portlets and themes,
they were fully functional upon deployment. Hooks aren't like that because
they're Liferay customizations. The default customization is the original
implementation! 

$$$

Now that you know how to create a hook using Liferay IDE and Ant, it's time to 
get out there and create your customizations!

