# Managing Module Projects with Liferay IDE [](id=managing-module-projects-with-liferay-ide)

Liferay IDE provides the ability to manage Liferay module projects from a GUI.
Before you begin learning about managing your modules from Liferay IDE, you
should make sure a Liferay server is configured in your Eclipse workspace so you
can deploy and run your projects. You can learn how to create a Liferay bundle
and link it to your Liferay workspace in the [Creating a Liferay Workspace with Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-liferay-ide)
tutorial.

Once you've created modules, you can deploy them using Liferay IDE. First, make
sure your Liferay server is started by clicking the *Start Server* button
(![Start Server](../../../images/icon-start-server.png)). Then navigate to your
module project from the Project Explorer and drag-and-drop the project onto the
configured Liferay bundle in the *Servers* menu. If at any time you'd like to
stop your Liferay server, click the *Stop Server* button
(![Stop Server](../../../images/icon-stop-server.png)). Awesome! You've deployed
a module to your running Liferay instance!

For the deployed module project, you can check if it has been deployed
successfully by using
[Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell).
Right-click the started portal in server view and select
*Open Gogo Shell*.

![Figure 1: Select *Open Gogo Shell* to open a terminal window in IDE using Gogo shell.](../../../images/open-gogo-shell.png)

A Gogo shell terminal appears, allowing you to enter Gogo commands to inspect
your Liferay instance and the modules deployed to it. Enter the `lb` command to
view a list of deployed modules. If the project status is active, then it
deployed successfully.

![Figure 2: You can check to see if your module deployed successfully to Liferay using the Gogo shell.](../../../images/gogo-deploy-successful.png)

Since the Liferay Workspace perspective in IDE is Gradle-based, you have some
additional Gradle features you can take advantage of. The Gradle Tasks toolbar
presents Gradle commands for your workspace that you can execute with a click of
the mouse.

![Figure 3: The Gradle Task toolbar offers Gradle tasks and their descriptions, which can be executed by double-clicking them.](../../../images/gradle-task-toolbar.png)

You can also access various Gradle build operations intended for Liferay module
projects. Right-click your module project and select *Liferay* &rarr; *Gradle*
and then the build command you want to execute.

![Figure 4: You can execute build operations by right-clicking the Gradle project in the Project Explorer.](../../../images/gradle-build-operations.png)

To learn more about Gradle development in Liferay IDE, see the
[Using Gradle in Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/using-gradle-in-liferay-ide)
tutorial.

Excellent! You've learned how to manage your Gradle-based Liferay Workspace
using Liferay IDE.
