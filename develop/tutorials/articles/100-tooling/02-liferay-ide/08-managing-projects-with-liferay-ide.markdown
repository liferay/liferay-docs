# Managing Projects with Liferay @ide@ [](id=managing-projects-with-liferay-ide)

Liferay @ide@ provides the ability to manage Liferay projects from a GUI. Before
you begin learning about managing your projects from Liferay @ide@, you should
make sure a Liferay server is configured in your Eclipse workspace so you can
deploy and run your projects. You can learn how to create a Liferay bundle and
link it to your Liferay workspace in the
[Creating a Liferay Workspace with Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/creating-a-liferay-workspace-with-liferay-ide)
tutorial.

Once you've created projects, you can deploy them using Liferay @ide@. First,
make sure your Liferay server is started by clicking the *Start Server* button
(![Start Server](../../../images/icon-start-server.png)). Then navigate to your
project from the Project Explorer and drag-and-drop it onto the configured
Liferay bundle in the *Servers* menu. If at any time you'd like to stop your
Liferay server, click the *Stop Server* button
(![Stop Server](../../../images/icon-stop-server.png)). Awesome! You've deployed
a project to your running Liferay instance!

For the deployed project, you can check if it has been deployed successfully by
using the
[Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).
Right-click the started portal in your Server view and select *Open Gogo Shell*.

![Figure 1: Select *Open Gogo Shell* to open a terminal window in @ide@ using Gogo shell.](../../../images/open-gogo-shell.png)

A Gogo shell terminal appears, allowing you to enter Gogo commands to inspect
your Liferay instance and the projects deployed to it. Enter the `lb` command to
view a list of deployed bundles. If the project status is active, then it
deployed successfully.

![Figure 2: You can check to see if your project deployed successfully to Liferay using the Gogo shell.](../../../images/gogo-deploy-successful.png)

Since the Liferay Workspace perspective in @ide@ is Gradle-based, you have some
additional Gradle features you can take advantage of. The Gradle Tasks toolbar
presents Gradle commands for your workspace that you can execute with a click of
the mouse.

![Figure 3: The Gradle Task toolbar offers Gradle tasks and their descriptions, which can be executed by double-clicking them.](../../../images/gradle-task-toolbar.png)

You can also access various Gradle build operations intended for Liferay module
projects. Right-click your module project and select *Liferay*  and then the
build command you want to execute.

![Figure 4: You can execute build operations by right-clicking the Gradle project in the Project Explorer.](../../../images/gradle-build-operations.png)

To learn more about Gradle development in Liferay @ide@, see the
[Using Gradle in Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/using-gradle-in-liferay-ide)
tutorial.

Excellent! You've learned how to manage your Gradle-based Liferay Workspace
using Liferay @ide@.
