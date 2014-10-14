# Creating a Portlet [](id=creating-a-portlet)

Portlet creation using the Plugins SDK is simple. There's a `portlets` folder
inside the Plugins SDK folder, where your portlet projects reside. The first
thing to do is give your portlet a project name (without spaces) and a display
name (which can have spaces). For the greeting portlet, the project name is
`my-greeting`, and the portlet title is `My Greeting`. 

Once you've named your portlet, you're ready to begin creating the project.
There are several different ways to create this portlet. Let's try it using
Liferay Developer Studio first, then by using the terminal. 

***In Developer Studio:***

1.  Go to File &rarr; New &rarr; Liferay Project. 

2.  Fill in the *Project name* and *Display name* with *my-greeting-portlet* and 
    *My Greeting*, respectively.  
    
3.  Leave the *Use default location* checkbox checked. By default, the
	default location is set to your current workspace. If you'd like to change
	where your plugin project is saved in your file system, uncheck the box and
	specify your alternate location. 

    ![Figure 3.1: Creating portlet projects with Liferay IDE is easy.](../../images/03-new-portlet-project.png)

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type. If
	you'd like to use *Maven* for your build type, navigate to the [Using Liferay
	IDE with
	Maven](/develop/tutorials/-/knowledge_base/6-1/using-liferay-ide-with-maven)
	section for details. 

5.  Your configured SDK and Liferay Runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click *Configure SDKs* to
    open the *Installed Plugin SDKs* management wizard. You can also access the
    *New Server Runtime Environment* wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the *Liferay
    Portal Runtime* dropdown menu. 

6.  Select *Portlet* as your Plugin type. 

7.  Click *Next*. 

8. In the next window, make sure that the *Liferay MVC* framework is selected
   and click *Finish*. 

With Developer Studio, you can create a new plugin project or if you already
have a project, create a new plugin in an existing project. A single Liferay
project can contain multiple plugins. 

***Using the Terminal:*** Navigate to the `portlets` directory in the terminal
and enter the appropriate command for your operating system:

1.  In Linux and Mac OS X, enter

        ./create.sh my-greeting "My Greeting"

2.  In Windows, enter

        create.bat my-greeting "My Greeting"

You should get a BUILD SUCCESSFUL message from Ant, and there will now be a new
folder inside of the `portlets` folder in your Plugins SDK. This folder is your
new portlet project. This is where you will be implementing your own
functionality. Notice that the Plugins SDK automatically appends "-portlet" to
the project name when creating this folder.

Alternatively, if you will not be using the Plugins SDK to house your portlet
projects, you can copy your newly created portlet project into your IDE of
choice and work with it there. If you do this, you will need to make sure the
project references some `.jar` files from your Liferay installation, or you may
get compile errors. Since the Ant scripts in the Plugins SDK do this for you
automatically, you don't get these errors when working with the Plugins SDK.

To resolve the dependencies for portlet projects, see the classpath entries in
the `build-common.xml` file in the Plugins SDK project. You can determine from
the `plugin.classpath` and `portal.classpath` entries, which `.jar` files are
necessary to build your newly created portlet project. This is not a recommended
configuration, and we encourage you to keep your projects in the Plugins SDK. 


+$$$

**Tip**: If you are using a source control system such as Subversion, CVS,
Mercurial, Git, etc., this might be a good moment to do an initial check-in of
your changes. After building the plugin for deployment, several additional files
will be generated that should *not* be handled by the source control system. 

$$$

## Deploying the Portlet [](id=deploying-the-portlet)

Liferay provides a mechanism called auto-deploy that makes deploying portlets
(and any other plugin types) a breeze. All you need to do is drop the plugin's
WAR file into the deploy directory, and the portal makes the necessary changes
specific to Liferay and then deploys the plugin to the application server. This
is a method of deployment used throughout this guide.

+$$$

**Note:** Liferay supports a wide variety of application servers. Many, such as
Tomcat and Jboss, provide a simple way to deploy web applications by just
copying a file into a folder and Liferay's auto-deploy mechanism takes advantage
of that ability. You should be aware though, that some application servers, such
as Websphere or Weblogic, require the use of specific tools to deploy web
applications; Liferay's auto-deploy process won't work for them. 

$$$

***Deploying in Developer Studio***: Drag your portlet project onto your
server. When deploying your plugin, your server displays messages indicating
that your plugin was read, registered and is now available for use. 

    Reading plugin package for my-greeting-portlet
    Registering portlets for my-greeting-portlet
    1 portlet for my-greeting-portlet is available for use

If at any time you need to redeploy your portlet while in Developer Studio,
right-click your portlet located underneath your server and select *Redeploy*. 

***Deploying in the terminal***: Open a terminal window in your
`portlets/my-greeting-portlet` directory and enter

    ant deploy

A BUILD SUCCESSFUL message indicates your portlet is now being deployed. If you
switch to the terminal window running Liferay, within a few seconds you should
see the message `1 portlet for my-greeting-portlet is available for use`. If
not, double-check your configuration. 

In your web browser, log in to the portal as explained earlier. Hover over
*Add* at the top of the page and click on *More*. Select the *Sample* category,
and then click *Add* next to *My Greeting*. Your portlet appears in the
page below. 

![Figure 3.2: Adding the My Greeting portlet](../../images/portlets-add-my-greeting-portlet.png)

Congratulations, you've just created your first portlet! 
