# Installing Blade CLI [](id=installing-blade-cli)

You can install Blade CLI using the Liferay Project SDK installer. This installs
JPM and Blade CLI into your user home folder and optionally initializes a
[Liferay Workspace](/developer/reference/-/knowledge_base/7-2/liferay-workspace)
folder. Do not use the Liferay Project SDK installer to update Blade CLI;
instead, follow the instructions for
[updating Blade CLI](/developer/reference/-/knowledge_base/7-2/updating-blade-cli).

If you need to configure proxy settings for Blade CLI, follow the
[Installing Blade CLI with Proxy Requirements](/developer/reference/-/knowledge_base/7-2/setting-blade-cli-proxy-requirments)

Follow the steps below to download and install Blade CLI:

1.  Download the latest
    [Liferay Project SDK installer](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/)
    that corresponds with your operating system (e.g., Windows, MacOS, or
    Linux). The Project SDK installer is listed under *Liferay IDE*, so the
    folder versions are based on IDE releases. You can select an installer that
    does not include @ide@, if you don't intend to use it. The Project SDK
    installer is available for versions 3.2.0+. Do **not** select the large
    green download button; this downloads Liferay Portal instead.

2.  Run the installer. 

3.  Select the Java Runtime to use with Blade CLI. Then click *OK*.

4.  Click *Next* to step through the installer's introduction.

5.  If you'd like to initialize a Liferay Workspace, you can set the directory
    where it should go. 

    ![Figure 1: Determine where your Liferay Workspace should reside, if you want one.](../../../images/blade-installer-workspace-init.png)

    Select the *Don't initialize Liferay Workspace directory* option if you only
    want to install Blade CLI. Then click *Next*.

6.  If you decided to initialize a Liferay Workspace folder in the previous
    step, you'll have an additional option to select the Liferay product type
    you'll use with your workspace. Choose the product type and click *Next*.

    ![Figure 2: Select the product version you'll use with your Liferay Workspace.](../../../images/installer-workspace-type.png)

7.  Click *Next* to begin installing Blade CLI/Liferay Workspace on your
    computer.

That's it! Blade CLI is installed on your machine! If you specified a location
to initialize a Liferay Workspace folder, that is also available.

If Blade CLI doesn't work properly on your machine, visit the
[Common Errors with Blade CLI](/developer/reference/-/knowledge_base/7-2/common-errors-with-blade-cli-installation)
article for solutions to common problems.
