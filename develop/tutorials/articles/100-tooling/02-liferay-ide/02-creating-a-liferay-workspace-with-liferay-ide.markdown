# Creating a Liferay Workspace with Liferay @ide@ [](id=creating-a-liferay-workspace-with-liferay-ide)

In this tutorial, you'll learn how to generate a Liferay Workspace using Liferay
@ide@, which runs on the
[Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli) behind the
scenes. Liferay @ide@ gives you a graphical interface instead of the command
prompt, which can streamline your workflow. To learn more about Liferay
Workspaces, visit its dedicated
[tutorial section](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace).

<div class="video-link">
<img src="../../../images/vid-ide-thumbnail.png" alt="video-thumbnail"/>
</div>

Before creating your Liferay Workspace, you should understand the available
@ide@ perspectives specifically designed for @product@ development. You'll
notice in the Perspectives view the *Liferay Workspace* and *Liferay*
perspectives. If you plan on using a Liferay Workspace for your @product@
development, you should select the *Liferay Workspace* perspective. This offers
Gradle related development tools that are helpful when using a Liferay
workspace. The *Liferay* perspective is geared towards developers who are using
Ant-based development tools such as the Plugins SDK.

To create a Liferay Workspace in @ide@, select *File* &rarr; *New* &rarr;
*Liferay Workspace Project*.

![Figure 1: By selecting *Liferay Workspace*, you begin the process of creating a new workspace for your Liferay projects.](../../../images/selecting-liferay-workspace.png)

A New Liferay Workspace dialog appears, presenting several configuration
options. Follow the instructions below to create your workspace.

1. Give your workspace a name. 

2. Choose the location where you'd like your workspace to reside. Checking the
   *Use default location* checkbox places your Liferay Workspace in the Eclipse
   workspace you're working in.

3. Check the *Download Liferay bundle* checkbox if you'd like to auto-generate a
   Liferay instance in your workspace. You'll be prompted to name the server, if
   selected. This Liferay bundle is generated the same way as described in the
   previous section.

    +$$$

    **Note:** If you'd like to configure a pre-existing Liferay bundle to your
    workspace, you can create a directory for the bundle in your workspace and
    configure it in the workspace's `gradle.properties` file by setting the
    `liferay.workspace.home.dir` property.

    $$$

4. Check the *Add project to working set* checkbox if you'd like the workspace
to be a part of a larger working set you've already created in @ide@. For more
information on working sets, visit
[Eclipse Help](http://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.platform.doc.user%2Fconcepts%2Fcworkset.htm).

5. Click *Finish* to create your Liferay Workspace.

![Figure 2: Liferay @ide@ provides an easy-to-follow menu to create your Liferay Workspace.](../../../images/new-workspace-menu.png)

A dialog appears prompting you to open the Liferay Workspace perspective.
Click *Yes*, and your perspective will switch to Liferay Workspace.

+$$$

**Note:** You can also create a Liferay Workspace during the initial start-up of
a Liferay Developer Studio instance. 

$$$

Awesome! You've successfully created a Liferay Workspace in Liferay @ide@! If
you're using Liferay Developer Studio, you can also create a workspace during
initial start-up.

## Liferay Workspace Settings in @ide@ [](id=liferay-workspace-settings-in-ide)

The Liferay Workspace perspective is intended for Gradle development for
@product-ver@ modules. Since Liferay Workspaces are used for Gradle based
development and the Liferay Plugins perspective is intended for the Plugins SDK
and Ant based development, the two perspectives are independent of each other.

![Figure 3: The Liferay Workspace perspective is preferred for @product-ver@ and OSGi module development.](../../../images/liferay-workspace-perspective.png)

You'll find your new workspace in the Project Explorer and your Liferay server
(if you created it) in the Servers menu. It's important to note that an Eclipse
workspace can only have one Liferay Workspace project.

![Figure 4: An @ide@ workspace only supports one Liferay Workspace project. If you create another, you'll be given an error message.](../../../images/liferay-workspace-duplicate.png)

You can configure your workspace's module presentation by switching between the
default *Hierarchical* or *Flat* views. To do this, navigate to the Project
Explorer's *View Menu* (![View Menu](../../../images/icon-ide-view-menu.png)),
select *Projects Presentation* and then select the presentation mode you'd like
to display. The Hierarchical view displays subfolders and subprojects under the
workspace project, whereas the Flat view displays the workspace's modules
separately from the workspace.

![Figure 5: The Hierarchical project presentation mode is set, by default.](../../../images/workspace-presentation.png)

If you've already created a Liferay Workspace and you'd like to import it into
your existing @ide@, you can do so by navigating to *File* &rarr; *Import*
&rarr; *Liferay* &rarr; *Liferay Workspace Project*. Then click *Next* and
browse for your workspace project. Once you've selected you workspace, click
*Finish*.

![Figure 6: You can import an existing Liferay Workspace into your current @ide@ session.](../../../images/liferay-workspace-import.png)

Congratulations! You've learned how to create and configure a Liferay Workspace
using Liferay @ide@. Now that your workspace is created, you can begin creating
Liferay plugins.

<div class="video-tag" data-name="Getting Started with Liferay IDE">
  <video width="100%" height="100%" controls>
    <source src="https://dev.liferay.com/documents/10184/367132/getting-started-with-liferay-ide.mp4" type="video/mp4">
    <source src="https://dev.liferay.com/documents/10184/367132/getting-started-with-liferay-ide.webm" type="video/webm">
    Your browser does not support HTML5 video.
  </video>
</div>
