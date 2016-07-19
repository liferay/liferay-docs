# Creating a Liferay Workspace with Liferay IDE

In this tutorial, you'll learn how to generate a Liferay Workspace using Liferay
IDE, which runs on Blade tools behind the scenes. Liferay IDE gives you a
graphical interface instead of the command prompt, which can streamline your
workflow. The instructions covered in this tutorial apply to both the commercial
and open source versions of Liferay.

To create a Liferay Workspace in IDE, select *File* &rarr; *New* &rarr; *Liferay
Workspace Project*.

![Figure 1: By selecting *Liferay Workspace*, you begin the process of creating a new workspace for your Liferay projects.](../../../images/selecting-liferay-workspace.png)

A *New Liferay Workspace* popup window appears, presenting several configuration
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
to be a part of a larger working set you've already created in IDE. For more
information on working sets, visit
[Eclipse Help](http://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.platform.doc.user%2Fconcepts%2Fcworkset.htm).

5. Click *Finish* to create your Liferay Workspace.

![Figure 2: Liferay IDE provides an easy-to-follow menu to create your Liferay Workspace.](../../../images/new-workspace-menu.png)

A popup window appears prompting you to open the *Liferay Workspace*
perspective. Click *Yes*, and your perspective will switch to *Liferay
Workspace*.

The *Liferay Workspace* perspective is intended for Gradle development for
Liferay 7 modules. Since Liferay Workspaces are used for Gradle based
development and the *Liferay* workspace is intended for the Plugins SDK and Ant
based development, the two perspectives are independent of each other.

![Figure 3: The Liferay Workspace perspective is preferred for Liferay 7 and OSGi module development.](../../../images/liferay-workspace-perspective.png)

You'll find your new workspace in the Project Explorer and your Liferay server
(if you created it) in the Servers menu. It's important to note that an Eclipse
workspace can only have one Liferay Workspace project.

![Figure 4: An IDE workspace only supports one Liferay Workspace project. If you create another, you'll be given an error message.](../../../images/liferay-workspace-duplicate.png)

You can configure your workspace's module presentation by switching between the
default *Hierarchical* or *Flat* views. To do this, navigate to the Project
Explorer's *View Menu* (![View Menu](../../../images/icon-ide-view-menu.png))
and select *Projects Presentation* and then the presentation mode you'd like to
display. The Hierarchical view displays subfolders and subprojects under the
workspace project, whereas the Flat view displays the workspace's modules
separately from the workspace.

![Figure 5: The Hierarchical project presentation mode is set, by default.](../../../images/workspace-presentation.png)

If you've already created a Liferay Workspace and you'd like to import into your
existing IDE, you can do so by navigating to *File* &rarr; *Import* &rarr;
*Liferay* &rarr; *Liferay Workspace Project*. Then click *Next* and browse for
your worspace project. Once you've selected you workspace, click *Finish*.

![Figure 5: You can import an existing Liferay Workspace into your current IDE session.](../../../images/liferay-workspace-import.png)

Congratulations! You've learned how to create a Liferay Workspace using the
command line and Liferay IDE. Now that your workspace is created, you can
begin creating Liferay plugins.
