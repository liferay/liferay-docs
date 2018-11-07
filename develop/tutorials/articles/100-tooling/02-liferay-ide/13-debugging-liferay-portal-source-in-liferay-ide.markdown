# Debugging @product@ Source in Liferay Dev Studio [](id=debugging-product-source-in-liferay-ide)

You can use Liferay Dev Studio to debug @product@ source code to help resolve errors.
Debugging @product@ code follows most of the same techniques associated with
debugging in Eclipse. If you need some help with general debugging, you can
visit Eclipse's documentation. Here's some helpful Eclipse links to visit:

- [Debugger](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fcdebugger.htm&cp=1_2_9)
- [Local Debugging](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fclocdbug.htm&cp=1_2_11)
- [Remote Debugging](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fcremdbug.htm&cp=1_2_12)

There are a couple Liferay-specific configurations to know before debugging
@product@ code:

- [Configure your target platform.](#configure-your-target-platform)
- [Configure a Liferay server and start it in debug mode.](#configure-a-liferay-server-and-start-it-in-debug-mode)

Let's explore these Liferay-specific debugging configurations.

## Configure Your Target Platform [](id=configure-your-target-platform)

To configure your target platform, you must be developing in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).
Liferay Workspace is able to provide debugging capabilities by targeting a
specific @product@ version, which indexes the configured @product@ source code.
Without specifying a target platform, @product@'s source code
cannot be accessed by Dev Studio. See the
[Managing the Target Platform in Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace)
tutorial for more information on how this works.

**Important:** The target platform should match the Liferay server version you
configure in the next section.

Once the target platform is configured in your workspace, Eclipse has access to
all of @product@'s source code. Next, you'll configure a Liferay server and
learn how to start it in Debug mode.

## Configure a Liferay Server and Start It in Debug Mode [](id=configure-a-liferay-server-and-start-it-in-debug-mode)

Configuring your target platform gives Eclipse @product@'s source code to
reference. Now you must configure a Liferay server matching the target platform
version so you can deploy the custom code you wish to debug.

1.  Set up your @product@ server to run in Dev Studio. See the
    [Installing a Server in Liferay Dev Studio](/develop/tutorials/-/knowledge_base/7-1/installing-a-server-in-liferay-ide)
    for more details.

2.  Start the server in debug mode. To do this, click the debug button in the 
    Servers pane of Liferay Dev Studio.

    ![Figure 1: The red box in this screenshot highlights the debug button. Click this button to start the server in debug mode.](../../../images/ide-debug.png)

Awesome! You're now equipped to begin debugging in Liferay Dev Studio!
