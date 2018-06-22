# Deploying Projects with IntelliJ IDEA [](id=deploying-projects-with-intellij-idea)

Once you've
[created a project](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-intellij-idea)
and
[installed your Liferay server](/develop/tutorials/-/knowledge_base/7-1/installing-a-server-in-intellij-idea)
in IntelliJ, you'll want to deploy your project. Follow the steps below to do
this:

1.  Right-click your project from within the Liferay Workspace folder structure
    and select *Liferay* &rarr; *Deploy*.

    This automatically loads a build progress window viewable at the bottom of
    your IntelliJ instance.

    ![Figure 1: Verify that your project build successfully.](../../../images/intellij-project-build.png)

2.  Verify that your project builds successfully from the build progress window.
    Then navigate back to your server's window and confirm it starts in your
    configured @product@ instance. You should receive a message like this:

        INFO  [fileinstall-C:/liferay-workspace/bundles/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.docs_1.0.0 [652]

If you run into errors during the build/deploy process of your project, check to
make sure your Liferay Workspace accounts for the 
[appropriate certificates](/develop/tutorials/-/knowledge_base/7-1/configuring-a-liferay-workspace#certification-issues-in-liferay-workspace).

That's it! You've successfully deployed your project to @product@!
