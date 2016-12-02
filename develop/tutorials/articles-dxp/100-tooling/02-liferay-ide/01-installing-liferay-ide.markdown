# Installing Liferay @ide@ [](id=installing-liferay-ide)

Liferay @ide@ is a plugin for Eclipse that provides many Liferay-specific
features and additional enterprise only features. You can install it into your
existing Eclipse environment, or Liferay provides a bundled version. In this
tutorial, you'll learn the different methods available for installing Liferay
@ide@. Before beginning the installation process, view @ide@'s
[Compatibility Matrix](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix/developer-tools)
to get acquainted with its supported Liferay versions and application servers.

## Install the Liferay @ide@ Bundle [](id=install-the-liferay-developer-studio-bundle)

1.  Download and install [Java](http://java.oracle.com). @product@ runs on Java,
    so you'll need it to run everything else. Because you'll be developing apps
    for @product@ in Liferay @ide@, the Java Development Kit (JDK) is required.
    It is an enhanced version of the Java Environment used for developing new
    Java technology. You can download the Java SE JDK from the Java
    [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    page. 

2.  Download
    [Liferay @ide@](https://web.liferay.com/group/customer/dxp/downloads/developer-tools).
    Installing it is easy: unzip it to a convenient location on your system. 

3.  To run @ide@, execute the `DeveloperStudioShortcut` executable.

Congratulations! You've installed Liferay @ide@! 

## Install Liferay @ide@ into Eclipse Environment [](id=install-liferay-developer-studio-into-eclipse-environment)

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

    ![Figure 1: Make sure to check all the @ide@ components you wish to install.](../../../images-dxp/dev-studio-zip-install.png)

5.  Accept the terms of the agreements and click *Next*, and Developer Studio is
    installed. Like other Eclipse plugins, you must restart Eclipse to use them.

Awesome! You've installed Liferay @ide@ in your existing Eclipse environment.

## Update Liferay @ide@ [](id=update-liferay-developer-studio)

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
