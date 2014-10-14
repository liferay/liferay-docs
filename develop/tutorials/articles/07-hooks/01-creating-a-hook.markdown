# Creating a Hook [](id=creating-a-hook)

Regardless of whether you use the Plugins SDK or your terminal to create hooks,
hooks projects are stored in the Plugins SDK's `hooks` directory. 

***Using Developer Studio:***

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Assign a project name and display name. To demonstrate, we'll use
    *example-hook* and *Example* for the project name and display name,
    respectively. Notice that upon entering *example-hook* as the project
    name, the wizard conveniently inserts *Example* in grayed-out text as the
    plugin's default display name. The wizard derives the default display name
    from the project name, starts it in upper-case, and leaves off the plugin
    type suffix *Hook* because the plugin type is automatically appended to
    the display name in Liferay Portal. The IDE saves the you from repetitively
    appending the plugin type to the display name; in fact, the IDE ignores any
    plugin type suffix if you happen to append it to the display name.

    Enter the following values for the project name and display name:

    - **Project name:** *example-hook*
    - **Display name:** *Example*

3.  Select the build type, Plugins SDK, and Liferay runtime. 

    If you select the Maven build type, you'll be prompted to enter an artifact
    version, group ID, and active profile for your project. See [Using Liferay
	IDE with
	Maven](/develop/tutorials/-/knowledge_base/6-1/using-liferay-ide-with-maven)
    for more information. Otherwise, select the Ant build type, a Plugins SDK
    and a Liferay runtime.  

    For this demonstration, make the following selections: 

    - **Build type:** *Ant*
    - **Plugins SDK:** `[a configured Plugins SDK]`
    - **Liferay runtime:** `[a configured Liferay runtime]`
    
    For more information, see sections [Installing the
    SDK](/develop/tutorials/-/knowledge_base/6-1/installing-the-sdk)
    and [Setting Up Liferay
    IDE](/develop/tutorials/-/knowledge_base/6-1/setting-up-liferay-ide).

4.  Select the *Hook* Plugin Type. 

5.  Click *Finish*. 

![Figure 7.1: Creating your hook plugin](../../images/06-hooks-1.png)

The Plugins SDK automatically named the hook by appending "-hook" to the project
name. With Developer Studio, you can create a completely new plugin or add a new
plugin to an existing plugin project. 

***Using the terminal:*** Navigate to your Plugins SDK directory in a terminal
and enter the appropriate command for your operating system:

1.  In Linux and Mac OS X, enter

        ./create.sh example "Example"

2.  In Windows, enter

        create.bat example "Example"

A BUILD SUCCESSFUL message from Ant tells you there's a new folder named
`example-hook` inside the Plugins SDK's `hooks` folder. The Plugins SDK
automatically named the hook by appending "-hook" to the project name.  

Now that you've created a hook, let's go ahead and deploy it.

## Deploying the Hook [](id=deploying-the-hook)

***Using Developer Studio:*** Click and drag your hook project onto your server.
Upon deployment, your server displays messages indicating that your hook was
read, registered and is now available for use. 

    Reading plugin package for example-hook
    Registering hook for example-hook
    Hook for example-hook is available for use

If you ever need to redeploy your plugin while in Developer Studio, right-click
your plugin's icon located underneath your server and select *Redeploy*. 

***Using the terminal:*** Open a terminal window in your `hooks/example-hook`
directory and enter

    ant deploy

A BUILD SUCCESSFUL message indicates your hook is now being deployed. If you
switch to the terminal window running Liferay, in a few seconds you
should see the message "Hook for example-hook is available for use". 

+$$$

**Note:** When we created portlets and themes, they were fully functional upon
deployment. Hooks aren't like that, because they're Liferay customizations, and
the default customization is the original implementation! 

$$$

Next, let's look at the anatomy of a Liferay hook. 

## Anatomy of the Hook [](id=anatomy-of-the-hook)

To make your hook useful, you need to customize something in Liferay. You begin
by mirroring the structure of Liferay's code that you plan to customize. A hook
plugin is built to contain this: 

- `example-hook/`
    - `docroot/`
        - `META-INF/`
        - `WEB-INF/`
            - `lib`
            - `liferay-hook.xml`
            - `liferay-plugin-package.properties`
    - `build.xml`

In Developer Studio's *Package Explorer*, here's what the hook structure looks
like:

![Figure 7.2: Package Explorer view of hook plugin](../../images/06-hooks-3.png)

The particular files you'll work on depend on what Liferay feature you're
overriding with your hook. Let's start making hooks for the features developers
override the most; web resources are a good place to start. 
