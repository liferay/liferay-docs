# Installing Liferay Workspace [](id=installing-liferay-workspace)

You can install Liferay Workspace using the Liferay Project SDK installer. This
installs JPM and
[Blade CLI](/docs/reference/7-2/-/knowledge_base/reference/blade-cli) into your user
home folder and optionally initializes a Liferay Workspace folder. This is the
same installer used to install Blade CLI, which is covered in the
[Installing Blade CLI](/docs/reference/7-2/-/knowledge_base/reference/installing-blade-cli)
article.

Follow the steps below to download and install Liferay Workspace:

1.  Download the latest
    [Liferay Project SDK installer](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/)
    that corresponds with your operating system (e.g., Windows, MacOS, or
    Linux). The Project SDK installer is listed under *Liferay IDE*, so the
    folder versions are based on IDE releases. You can select an installer that
    does not include @ide@, if you don't intend to use it. The Project SDK
    installer is available for versions 3.2.0+. Do **not** select the large
    green download button; this downloads Liferay Portal instead.

2.  Run the installer. Click *Next* to step through the installer's
    introduction.

3.  Set the directory where your Liferay Workspace should be initialized.

    ![Figure 1: Determine where your Liferay Workspace should reside.](../../../images/blade-installer-workspace-init.png)

    Then click *Next*.

4.  Choose the Liferay product type you intend to use with the workspace. Then
    click *Next*.

    ![Figure 2: Select the product version you'll use with your Liferay Workspace.](../../../images/installer-workspace-type.png)

    | **Note:** You'll be prompted for your liferay.com username and password
    | before downloading the Liferay DXP bundle. Your credentials are not saved
    | locally; they're saved as a token in the `~/.liferay` folder. The token is
    | used by your workspace if you ever decide to redownload a DXP bundle.
    | Furthermore, the bundle that is downloaded in your workspace is also
    | copied to your `~/.liferay/bundles` folder, so if you decide to initialize
    | another @product@ instance of the same version, the bundle is not
    | re-downloaded. See the
    | [Adding a Liferay Bundle to Liferay Workspace](/docs/reference/7-2/-/knowledge_base/reference/adding-a-liferay-bundle-to-liferay-workspace)
    | for more information on this topic.

5.  Click *Next* to begin installing Liferay Workspace on your machine.

That's it! Liferay Workspace is now installed on your machine!
