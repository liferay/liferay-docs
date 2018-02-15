# Installing Liferay @ide@ [](id=installing-liferay-ide)

Liferay @ide@ is a plugin for Eclipse that provides many Liferay-specific
features and additional enterprise only features. You can install it into your
existing Eclipse environment, or Liferay provides a bundled version. In this
tutorial, you'll learn the different methods available for installing Liferay
@ide@. Before beginning the installation process, view @ide@'s
[Compatibility Matrix](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix/developer-tools)
to get acquainted with its supported Liferay versions and application servers.

**Important:** If you're upgrading your Liferay @ide@ instance to version 3.1,
you must install a new @ide@ bundle. You cannot install an update for this
upgrade. Your Liferay Workspace instance and its contents are backwards
compatible and can be copied to the new 3.1 version.

## Install the Liferay @ide@ Bundle [](id=install-the-liferay-developer-studio-bundle)

1.  Download and install [Java](http://java.oracle.com). @product@ runs on Java,
    so you'll need it to run everything else. Because you'll be developing apps
    for @product@ in Liferay @ide@, the Java Development Kit (JDK) is required.
    It is an enhanced version of the Java Environment used for developing new
    Java technology. You can download the Java SE JDK from the Java
    [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    page. 

2.  Download the
    [Liferay @ide@ installer](https://web.liferay.com/group/customer/dxp/downloads/developer-tools).
    Be sure to choose the installer appropriate for your operating system (e.g.,
    Windows, MacOS, Linux).

    You may be prompted for your liferay.com username and password before
    downloading the @ide@ installer. Since @ide@ includes access to @product@,
    you must verify that you have rights to use it.

    Your credentials are not saved locally; they're saved as a token in the
    `~/.liferay` folder. The token is used by your @ide@'s
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

3.  Run the installer. You may need to allow permission for the installer to run,
    depending on your operating system and where you want to install it.

4.  Click *Next* to begin the installation process. Select the installation
    folder for your Liferay @ide@ instance. Then click *Next*.

    ![Figure 1: Choose the folder your @ide@ instance should reside.](../../../images-dxp/dev-studio-install.png)

5.  Liferay @ide@ provides
    [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
    by default, which is a developer environment used to build and manage
    @product@ projects. The installer automatically installs Liferay Workspace
    and its dedicated command line tool
    ([Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)).

    You'll need to choose the Liferay bundle you plan to use in your Liferay
    Workspace: *Liferay DXP Bundle* or *Community Edition Bundle*. Then choose
    *Next*.

    ![Figure 2: Choose the Liferay bundle you plan to use.](../../../images-dxp/dev-studio-bundle-selection.png)

    If you selected *Liferay DXP Bundle*, you're also required to
    provide your liferay.com email and password.

6.  Click *Next* to finish the installation process for your @ide@ instance.

Congratulations! You've installed Liferay @ide@! It's now available in the
folder you specified. To run @ide@, execute the `DeveloperStudio` executable. A
Liferay Workspace has also been initialized in that same folder. For more
information on the Liferay Workspace installation related to this installation
process, see the
[Using @ide@'s Liferay Workspace Installer](/develop/tutorials/-/knowledge_base/7-1/using-developer-studios-liferay-workspace-installer)
section.

## Install Liferay @ide@ into Eclipse Environment [](id=install-liferay-developer-studio-into-eclipse-environment)

To install @ide@ using an update URL, follow these steps:

1.  In Eclipse, go to *Help* &rarr; *Install New Software...*. 

2.  In the *Work with* field, copy in the URL
    http://releases.liferay.com/tools/ide/latest/stable/.

3.  You'll see the @ide@ components in the list below. Check them off and click
    *Next*.

4.  Accept the terms of the agreements. Click *Next*, and @ide@ is installed.
    Like other Eclipse plugins, you must restart Eclipse to use them.

Liferay @ide@ is now installed in your existing Eclipse environment.

## Install Liferay @ide@ into Eclipse from a ZIP File [](id=install-liferay-ide-into-eclipse-from-a-zip-file)

To install @ide@ using a Zip file, follow these steps:

1.  Go to the
    [Liferay @ide@](https://web.liferay.com/group/customer/dxp/downloads/developer-tools)
    downloads page. From the drop-down menu, select *Developer Studio Update
    Site Zip* and click *Download*.

2.  In Eclipse, go to *Help* &rarr; *Install New Software...*. 

3.  In the *Add* dialog, click the *Archive* button and browse to the
    location of the downloaded Liferay @ide@ Update Site `.zip` file. Then press
    *OK*.

4.  You'll see the @ide@ components in the list below. Check them off and click
    *Next*.

    ![Figure 3: Make sure to check all the @ide@ components you wish to install.](../../../images-dxp/dev-studio-zip-install.png)

5.  Accept the terms of the agreements and click *Next*, and Developer Studio is
    installed. Like other Eclipse plugins, you must restart Eclipse to use them.

Awesome! You've installed Liferay @ide@ in your existing Eclipse environment.

## Generating a Workspace Token Manually [](id=generating-a-workspace-token-manually)

If you run into any issues with generating your token automatically, you can
follow the steps below to manually create one.

1.  Navigate to [www.liferay.com](https://www.liferay.com/) and log in to your
    account.

2.  Click the Options button (![Options](../../../images-dxp/icon-liferay-options.png))
    and select *Account Home*.

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
