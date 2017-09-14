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
    [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
    if you ever decide to redownload a @product@ bundle. Furthermore, the
    @product@ bundle that was downloaded in your workspace is also copied to
    your `~/.liferay/bundles` folder, so if you decide to initialize another
    @product@ instance of the same version, the bundle is not re-downloaded. See
    the
    [Adding a Liferay Bundle to a Workspace](/develop/tutorials/-/knowledge_base/7-0/adding-a-liferay-bundle-to-a-workspace)
    for more information on this topic.

    **Important:** The token generator currently has problems with certain
    special characters contained in a password, which fails the token
    generation. This is a bug and will be fixed in the next release of Liferay
    @ide@. Visit the
    [Generating a Workspace Token Manually](#generating-a-workspace-token-manually)
    section to follow a manual process to generate a token if you're unable to
    complete it automatically using the installer.

3.  Run the installer. You may need to allow permission for the installer to run,
    depending on your operating system and where you want to install it.

4.  Click *Next* to begin the installation process. Select the installation
    folder for your Liferay @ide@ instance. Then click *Next*.

    ![Figure 1: Choose the folder your @ide@ instance should reside.](../../../images-dxp/dev-studio-install.png)

5.  Liferay @ide@ provides
    [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
    by default, which is a developer environment used to build and manage
    @product@ projects. The installer automatically installs Liferay Workspace
    and its dedicated command line tool
    ([Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli)).

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
[Using @ide@'s Liferay Workspace Installer](/develop/tutorials/-/knowledge_base/7-0/using-developer-studios-liferay-workspace-installer)
section.

## Install Liferay @ide@ into Eclipse Environment [](id=install-liferay-developer-studio-into-eclipse-environment)

**Important:** This is not available for the latest 3.1 version of @ide@.

To install Liferay @ide@ into an existing Eclipse environment, follow these
steps:

1.  Go to the
    [Liferay @ide@](https://web.liferay.com/group/customer/dxp/downloads/developer-tools)
    downloads page. From the drop-down menu, select *Archived Feature Update
    Site* and click *Download*.

    The Archived Product Update Site is used to update an existing @ide@
    environment to the most recent release. You'll cover this later.

2.  In Eclipse, go to *Help* &rarr; *Install New Software...*. 

3.  In the *Add Site* dialog, click the *Archive* button and browse to the
    location of the downloaded Liferay @ide@ `.zip` file. Then press *OK*.

4.  You'll see the @ide@ components in the list below. Check them off and click
    *Next*.

    ![Figure 3: Make sure to check all the @ide@ components you wish to install.](../../../images-dxp/dev-studio-zip-install.png)

5.  Accept the terms of the agreements and click *Next*, and Developer Studio is
    installed. Like other Eclipse plugins, you must restart Eclipse to use them.

Awesome! You've installed Liferay @ide@ in your existing Eclipse environment.

## Update Liferay @ide@ [](id=update-liferay-developer-studio)

**Important:** This is not available for the latest 3.1 version of @ide@.

If you're already using Liferay @ide@ but need to update your environment,
follow the steps below:

1.  Go to the
    [Liferay @ide@](https://web.liferay.com/group/customer/dxp/downloads/developer-tools)
    downloads page. From the selector, choose *Archived Product Update Site* and
    click *Download*.

2.  Navigate to *Help* &rarr; *Update Liferay Developer Studio...*.

3.  Select *Browse* and select the `.zip` file you downloaded in step 1. Then
    select *Update*.

You're now on the latest version of Liferay @ide@!

Now that you've learned how to install and update your Liferay @ide@
environment, you can begin developing Liferay products.

## Generating a Workspace Token Manually [](id=generating-a-workspace-token-manually)

The Liferay @ide@ 3.1 release contains a few issues with generating a token
used to store your Liferay credentials. If you run into an issue with generating
your token automatically, you can follow the steps below to manually create one.

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
[Adding a Liferay Bundle to a Workspace](/develop/tutorials/-/knowledge_base/7-0/configuring-a-liferay-workspace#adding-a-liferay-bundle-to-a-workspace)
tutorial for details.

<!-- The information below should be uncommented when Dev Studio 3.1 is
available to update from original GA release. Since Dev Studio must be
reinstalled when upgrading from 3.0 to 3.1, this should not be documented. -Cody
-->

<!--

Update Liferay @ide@

If you already have @ide@ installed, you can easily update it using an update
site URL.

1.  Select *Help* &rarr; *Install New Software*. 

2.  In your browser, go to the
    [Liferay @ide@](https://web.liferay.com/group/customer/dxp/downloads/developer-tools)
    page. Copy the URL to the update site.

    [Figure 1: Liferay provides two update sites: stable for those who want a well-tested environment, and milestone for those who like the bleeding edge.](../../../images/liferay-ide-download.png)

3.  Go back to Eclipse and click the *Add* button to add a repository. 

4.  Type *Liferay IDE* into the Name field and paste the URL into the Location 
    field. 

5.  You'll see the Liferay @ide@ components in the list below. Check them off and
    click *Next*. 

6.  Accept the terms of the agreements and click *Next*, and Liferay @ide@ is
    installed. Like other Eclipse plugins you'll have to restart Eclipse to
    enable it.
-->
