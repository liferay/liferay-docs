# Creating and Deploying JSF Portlets [](id=creating-and-deploying-jsf-portlets)

<!-- The JSF calendar portlet using PrimeFaces can be found at the following:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferay-faces/creating-jsf-port/calendar/my-jsf-portlet
-->

Liferay supports developing and deploying JSF portlets on Liferay Portal by
using Liferay Faces Bridge. The bridge supports deploying JSF web applications
as portlets on any JSR 286 (Portlet 2.0) compliant portlet container, like
Liferay Portal 5.2, 6.0, 6.1, and 6.2. Liferay Faces Bridge makes developing JSF
portlets as similar as possible to JSF web app development. In this tutorial,
you'll learn the portlet development process and how to leverage Liferay Faces
Bridge's full potential with your JSF portlets. This will make it easy for you
to implement portlets using JSF. 

Liferay IDE, with its powerful portlet plugin wizard, provides you with a great
environment to create a JSF portlet project. The wizard lets you select a
component suite that's right for your project, including JSF's standard UI
component suite, ICEfaces, Liferay Faces Alloy, PrimeFaces, and RichFaces. Of
course, you can use any development environment you like for building JSF
portlets, but Liferay IDE is hard to beat. Next, you'll use Liferay IDE to
create your JSF portlet project.

## Creating a JSF Portlet Project

In this tutorial, you'll create a JSF portlet project using Liferay
IDE/Developer Studio, so you can see just how easy it is. If you don't have it
installed yet, see the
[Developing Apps with Liferay IDE](/develop/learning-paths/-/knowledge_base/6-2/developing-apps-with-liferay-ide)
learning path for full details. If you do have it installed, launch it. 

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  In the project creation wizard's first window, you'll name your project and
    specify its development and runtime environments. 

    2.1 Fill in the *Project name* and *Display name*. Project names must not
    have any spaces and it's best to specify them in lowercase. The *Display
    name*, however, can have spaces. You should specify the portlet's *Display
    name* just as you want it displayed to the user. 

    2.2. Leave the *Use default location* checkbox checked. By default, the
    default location is set to your current Plugins SDK. If you'd like to
    change where your plugin project is saved in your file system, uncheck the
    box and specify your alternate location. 

    2.3. Select the *Ant (liferay-plugins-sdk)* option for your build type. If
    you'd like to use *Maven* for your build type, navigate to the
    [Using Maven From Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/using-maven-from-liferay-ide)
    tutorial for details. 

    2.4. Your configured SDK and Liferay Runtime should already be selected.
    If you haven't yet pointed Liferay IDE to a Plugins SDK, click
    *Configure SDKs* to open the *Installed Plugin SDKs* management wizard. You
    can also access the *New Server Runtime Environment* wizard if you need to
    set up your runtime server; just click the *New Liferay Runtime* button next
    to the *Liferay Portal Runtime* dropdown menu. 

    2.5. Select *Portlet* as your Plugin type and click *Next*. 

3.  In this window, you'll select the portlet framework for your portlet and a
    UI component suite. 

    3.1 Select the *JSF 2.x* portlet framework. 

    Immediately, the wizard lists the available JSF component suites in the
    bottom section of the window. The list of component suites includes the JSF
    Standard suite, ICEfaces, Liferay Faces Alloy, PrimeFaces, and RichFaces. 

    3.2. Select the UI component suite you'd like to use and click *Finish*. 

Great! Your new JSF portlet plugin project is ready for you to develop JSF
portlets. 

![Figure 1: Liferay Faces supports using the most popular component suites with your JSF portlets.](../../images/jsf-select-primefaces-comp-suite.png)

<!-- As an alternative, we should explain how to download the JARs manually and
install them. - Jim -->

To access components from a component suite, make sure to declare that suite as
an XML namespace attribute in the `<f:view>` element of your facelet file. For
example, if you'd selected PrimeFaces as your project's UI component suite,
you'd add a namespace for it (e.g., `xmlns:p="http://primefaces.org/ui"`) in
your facelet file's `<f:view>` element. 

See how easy that was? Now that you've created a generic or modified JSF
portlet, it's time to deploy your JSF portlet to the portal and see what it
looks like. 

## Deploying JSF Portlets

Liferay provides a mechanism called auto-deploy that makes deploying JSF
portlets (and any other plugin types) a breeze. All you need to do is drop the
plugin's `.war` file into the deploy directory, and the portal makes the
necessary changes specific to Liferay and then deploys the plugin to the
application server. This is a common method of deployment used throughout the
Liferay community. 

+$$$

**Note:** Liferay supports a wide variety of
application servers. Many, such as Tomcat and JBoss, provide a simple way to
deploy web applications by just copying a file into a folder and Liferay's
auto-deploy mechanism takes advantage of that ability. You should be aware
though, that some application servers, such as WebSphere or Weblogic, require
the use of specific tools to deploy web applications; Liferay's auto-deploy
process won't work for them. 

$$$

For this part of the tutorial, you'll learn how to deploy a JSF portlet using
IDE/Developer Studio and the terminal.

**Deploying in Developer Studio**: Drag your portlet project onto your server.
When deploying your plugin, your server displays messages indicating that your
plugin was read, registered and is now available for use. 

    Reading plugin package for [your-project]
    Registering portlets for [your-project]
    1 portlet for [your-project] is available for use

If at any time you need to redeploy your JSF portlet while in Developer Studio,
right-click your portlet located underneath your server and select *Redeploy*. 

**Deploying in the terminal**: Open a terminal window in your portlet's project
directory and enter the following command. 

    ant deploy

A `BUILD SUCCESSFUL` message indicates that Ant built your portlet's WAR file
and that it's now being deployed. If you switch to the terminal window running
Liferay, within a few seconds you should see a message, like the following
message, indicating that your plugin is available to use in the portal: 

`1 portlet for [your-project] is available for use`

If you don't see such a message, double-check your plugin's configuration. 

In your web browser, log in to the portal. Click the Add button, which appears
as a *Plus* symbol in the top right hand section of your browser. Then click
*Applications*, find the portlet in the *Sample* category, and click *Add*. Your
portlet appears on the page, but Liferay Faces lets you know when a UI component
requires a page refresh to render the first time. Refresh the page and the
portal renders your JSF portlet. 

It's just that easy to create and deploy JSF portlet plugins! 

