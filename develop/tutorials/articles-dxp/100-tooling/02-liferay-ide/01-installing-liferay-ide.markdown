# Installing Liferay @ide@ [](id=installing-liferay-ide)

**Note:** This tutorial is targeted for developers who are interested in
installing the latest 3.2.0 Developer Studio milestone. If you're interested in
the latest 3.1.x GA release of Dev Studio, see the
[Installing Liferay Developer Studio](/develop/reference/-/knowledge_base/7-0/installing-liferay-ide)
tutorial.

Liferay @ide@ is a plugin for Eclipse that provides many Liferay-specific
features and additional enterprise only features. You can install it into your
existing Eclipse environment, or Liferay provides a bundled version. In this
tutorial, you'll learn the different methods available for installing Liferay
Dev Studio. Before beginning the installation process, view Dev Studio's
[Compatibility Matrix](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix/developer-tools)
to get acquainted with its supported Liferay versions and application servers.

**Important:** If you're installing Dev Studio into an existing Eclipse
environment, you must be on Eclipse Oxygen or newer. For instructions on
upgrading to Oxygen, see Eclipse's
[upgrade documentation](https://wiki.eclipse.org/FAQ_How_do_I_upgrade_Eclipse_IDE%3F#Upgrading_existing_Eclipse_IDE_and_Installed_Features_to_newer_release).
With this particular upgrade, you should also deactivate the current available
update sites in the *Window* &rarr; *Preferences* &rarr; *Install/Update* &rarr;
*Available Software Sites* menu to ensure a successful upgrade (e.g., Neon).

## Install the Liferay Dev Studio Bundle [](id=install-the-liferay-developer-studio-bundle)

1.  Download and install [Java](http://java.oracle.com). @product@ runs on Java,
    so you'll need it to run everything else. Because you'll be developing apps
    for @product@ in Liferay Dev Studio, the Java Development Kit (JDK) is required.
    It is an enhanced version of the Java Environment used for developing new
    Java technology. You can download the Java SE JDK from the Java
    [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    page. 

2.  Download Liferay's
    [Project SDK with @ide@](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/3.2.0%20M1/)
    executable that correlates to your operating system. The Project SDK
    includes @ide@,
    [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace),
    and [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli).

    You may be prompted for your liferay.com username and password before
    downloading the @ide@ installer. Since @ide@ includes access to @product@,
    you must verify that you have rights to use it.

    Your credentials are not saved locally; they're saved as a token in the
    `~/.liferay` folder. The token is used by your Dev Studio's
    [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
    if you ever decide to redownload a @product@ bundle. Furthermore, the
    @product@ bundle that was downloaded in your workspace is also copied to
    your `~/.liferay/bundles` folder, so if you decide to initialize another
    @product@ instance of the same version, the bundle is not re-downloaded. See
    the
    [Adding a Liferay Bundle to a Workspace](/develop/tutorials/-/knowledge_base/7-1/adding-a-liferay-bundle-to-a-workspace)
    for more information on this topic.

    **Important:** The token generator sometimes has issues generating a token
    for workspaces built behind a proxy. If you're unable to automatically
    generate a workspace token, you can
    [generate one manually](#generating-a-workspace-token-manually).

3.  Run the installer. You may need to allow permission for the installer to
    run, depending on your operating system and where you want to install it.

4.  Select the Java Runtime to use for the installation process. Then click
    *Next*.

5.  Click *Next* to begin the installation process. Select the installation
    folder for your Liferay Dev Studio instance. Then click *Next*.

    ![Figure 1: Choose the folder your Dev Studio instance should reside.](../../../images-dxp/dev-studio-install.png)

6.  Input the @product@ activation key to set up the Liferay DXP bundle packaged
    with @ide@. Then click *Next*.

    Dev Studio installs
    [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
    by default, which is a developer environment used to build and manage
    @product@ projects. The installer automatically installs Liferay Workspace
    and its dedicated command line tool
    ([Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)).

7.  Configure proxy settings for your Project SDK. If you must use Dev Studio
    behind a firewall, you may want to configure the proxy settings. See the
    [Liferay IDE Proxy Settings](/develop/tutorials/-/knowledge_base/7-1/setting-proxy-requirements-for-liferay-ide)
    and
    [Liferay Workspace Proxy Settings](/develop/tutorials/-/knowledge_base/7-1/setting-proxy-requirements-for-liferay-workspace)
    tutorials for more information. Skip this step if you don't need this.

    ![Figure 2: Configure your Project SDK's proxy settings, if necessary.](../../../images-dxp/dev-studio-proxy-settings.png)

6.  Click *Next* to finish the installation process for your Dev Studio instance.

Congratulations! You've installed Liferay @ide@! It's now available in the
folder you specified. To run Dev Studio, execute the `DeveloperStudio`
executable. A Liferay Workspace has also been initialized in that same folder.

## Install Liferay Dev Studio into Eclipse Environment [](id=install-liferay-developer-studio-into-eclipse-environment)

To install Dev Studio using an update URL, follow these steps:

1.  In Eclipse, go to *Help* &rarr; *Install New Software...*. 

2.  In the *Work with* field, copy in the URL
    http://releases.liferay.com/tools/ide/latest/milestone/.

3.  You'll see the Dev Studio components in the list below. Check them off and
    click *Next*.

4.  Accept the terms of the agreements. Click *Next*, and Dev Studio is
    installed. Like other Eclipse plugins, you must restart Eclipse to use them.

Liferay Dev Studio is now installed in your existing Eclipse environment.

## Install Liferay Dev Studio into Eclipse from a ZIP File [](id=install-liferay-ide-into-eclipse-from-a-zip-file)

To install Dev Studio using a Zip file, follow these steps:

1.  Go to the
    [Liferay @ide@](https://web.liferay.com/group/customer/dxp/downloads/developer-tools)
    downloads page. From the drop-down menu, select *Developer Studio Updatesite
    Zip* and click *Download*.

2.  In Eclipse, go to *Help* &rarr; *Install New Software...*. 

3.  In the *Add* dialog, click the *Archive* button and browse to the
    location of the downloaded Liferay Dev Studio Update Site `.zip` file. Then
    press *OK*.

4.  You'll see the Dev Studio components in the list below. Check them off and
    click *Next*.

    ![Figure 3: Make sure to check all the Dev Studio components you wish to install.](../../../images-dxp/dev-studio-zip-install.png)

5.  Accept the terms of the agreements and click *Next*, and Developer Studio is
    installed. Like other Eclipse plugins, you must restart Eclipse to use them.

Awesome! You've installed Liferay Dev Studio in your existing Eclipse environment.

## Generating a Workspace Token Manually [](id=generating-a-workspace-token-manually)

If you run into any issues with generating your token automatically, you can
follow the steps below to manually create one.

1.  Navigate to [www.liferay.com](https://www.liferay.com/) and log in to your
    account.

2.  Hover over your profile picture in the top-right corner and select *Account
    Home*.

3.  Select *Account Settings* from the left menu.

4.  Click *Authorization Tokens* from the right menu under the Miscellaneous
    heading.

    ![Figure 4: You can manually create your workspace token in the Authorization Tokens menu.](../../../images-dxp/authorization-tokens-option.png)

5.  Select *Add Token*, give it a device name, and click *Generate*. The device
    name can be set to any string; it's for bookkeeping purposes only.

6.  Create a file named `~/.liferay/token` and copy the generated token into
    that file.

    ![Figure 5: The generated token is available to copy.](../../../images-dxp/generated-token.png)

    Make sure there are no new lines or white space in the file. It should only
    be one line.

You've successfully generated your token manually and it's now available for
your installer to access. If you haven't run the installer, you can do so now.
If you've already run the installer, you can set the DXP bundle to download
in the `gradle.properties` file of your workspace. See the
[Adding a Liferay Bundle to a Workspace](/develop/tutorials/-/knowledge_base/7-1/configuring-a-liferay-workspace#adding-a-liferay-bundle-to-a-workspace)
tutorial for details.
