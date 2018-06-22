# Installing Blade CLI [](id=installing-blade-cli)

You can install Blade CLI using the Liferay Project SDK installer. This installs
JPM and Blade CLI into your user home folder and optionally initializes a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
folder.

+$$$

**Note:** In the past, if you've installed Blade CLI globally (e.g., using
`sudo`), you should not run the installer to *update* your Blade CLI version.
Since the installer only installs Blade CLI to your user home folder, your
previous global installation would always override the installer's installation.
Therefore, always follow the
[Updating Blade CLI](/develop/tutorials/-/knowledge_base/7-1/updating-blade-cli)
tutorial to update your Blade CLI instance.

$$$

If you need to configure proxy settings for Blade CLI, follow the
[Installing Blade CLI with Proxy Requirements](/develop/tutorials/-/knowledge_base/7-1/setting-blade-cli-proxy-requirments)

Follow the steps below to download and install Blade CLI:

1.  Download the
    [Liferay Project SDK installer](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/3.2.0%20M1/)
    that corresponds with your operating system (e.g., Windows, MacOS, or
    Linux). The Project SDK installer is currently listed under *Liferay IDE*,
    so select the latest IDE version for the latest Project SDK installer. You
    can select an installer that includes @ide@, depending on your needs. 

    <!-- The Project SDK location on SF will likely change. Update above step
    when it does. -Cody -->

2.  Run the installer. Click *Next* to step through the installer's
    introduction.

3.  If you'd like to initialize a Liferay Workspace, you can set the directory
    where it should go. 

    ![Figure 1: Determine where your Liferay Workspace should reside, if you want one.](../../../images/blade-installer-workspace-init.png)

    Select the *Don't initialize Liferay Workspace directory* option if you only
    want to install Blade CLI. Then click *Next*.

4.  If you decided to initialize a Liferay Workspace folder in the previous
    step, you'll have an additional option to select the Liferay product type
    you'll use with your workspace. Choose the product type and click *Next*.

    ![Figure 2: Select the product version you'll use with your Liferay Workspace.](../../../images/installer-workspace-type.png)

5.  Click *Next* to begin installing Blade CLI/Liferay Workspace on your
    computer.

That's it! Blade CLI is installed on your machine! If you specified a location
to initialize a Liferay Workspace folder, that is also available.

Blade CLI offers many
[templates](/develop/reference/-/knowledge_base/7-1/project-templates)
to help build @product-ver@ applications. It also offers various ways to deploy
those apps and interact with your Liferay server. Be sure to explore more Blade
CLI tutorials to learn how.

## Installer Issues on macOS [](id=installer-issues-on-macos)

If you're using macOS, you could experience an issue where the `blade` command
is not available in your terminal:

    command not found: blade

This is caused by the installer being unable to add JPM's `bin` folder to your
user path. JPM is a Java package manager used in Blade CLI. To add the required
`bin` folder, execute the following command in your terminal:

    echo 'export PATH="$PATH:$HOME/Library/PackageManager/bin"' >> ~/.bash_profile

Once you restart your terminal, the `blade` command should be available.
