# Installing a Server in IntelliJ IDEA [](id=installing-a-server-in-intellij-idea)

Installing a Liferay server in IntelliJ is easy. In just a few steps, you'll
have your server up and running.

+$$$

**Note:** Tomcat is the only supported Liferay app server bundle available to
install in IntelliJ.

$$$

Follow these steps to install your server:

1.  Right-click your Liferay workspace and select *Liferay* &rarr; *InitBundle*.

    This downloads the @product@ bundle specified in your workspace's
    `gradle.properties` file. You can change the default bundle by updating the
    `liferay.workspace.bundle.url` property. The downloaded bundle is stored in
    the workspace's `bundles` folder.

2.  Navigate to the top right Configurations dropdown menu and select *Edit
    Configurations*. From here, you can configure your server's run and debug
    configurations.

    ![Figure 1: You have several options to choose from the server dropdown menu.](../../../images/intellij-server-dropdown.png)

3.  Click the *Add New Configuration* button
    (![Add Config](../../../images/icon-intellij-add-config.png)) and select
    *Liferay Server*.

4.  Give your server a better name and modify any other configurations, if
    necessary. Then select *OK* .

    ![Figure 2: Set your Liferay server's configurations in the Run/Debug Configurations menu.](../../../images/intellij-run-debug-wizard.png)

Your server is now available in IntelliJ! Make sure to select it in the
Configurations dropdown before executing the configuration buttons (below).

For reference, here's how the IntelliJ configuration buttons work with your
@product@ instance:

- *Start* (![Start Server](../../../images/icon-intellij-start-server.png)):
  Starts the server.
- *Stop* (![Stop Server](../../../images/icon-intellij-stop-server.png)): Stops
  the server.
- *Debug* (![Debug Server](../../../images/icon-intellij-debug-server.png)):
  Starts the server in debug mode. For more information on debugging in
  IntelliJ, see the
  [IntelliJ Debugging](https://www.jetbrains.com/help/idea/debugging-code.html)
  article.

Now you're ready to use your server in IntelliJ!
