# Installing Liferay @ide@ [](id=installing-liferay-ide)

+$$$

**Note:** This tutorial is targeted for developers who are interested in
installing the latest 3.2.0 IDE (Dev Studio) milestone. If you're interested in
the latest 3.1.x GA release of IDE, see the
[Installing Liferay IDE](/develop/reference/-/knowledge_base/7-0/installing-liferay-ide)
tutorial.

$$$

Liferay @ide@ is a plugin for Eclipse that brings many Liferay-specific features
to the table. You can install it into your existing Eclipse environment, or
Liferay provides a bundled version included in its Project SDK. In this
tutorial, you'll learn the different methods available for installing Dev
Studio.

**Important:** If you're installing Dev Studio into an existing Eclipse
environment, you must be on Eclipse Oxygen or newer. For instructions on
upgrading to Oxygen, see Eclipse's
[upgrade documentation](https://wiki.eclipse.org/FAQ_How_do_I_upgrade_Eclipse_IDE%3F#Upgrading_existing_Eclipse_IDE_and_Installed_Features_to_newer_release).
With this particular upgrade, you should also deactivate the current available
update sites in the *Window* &rarr; *Preferences* &rarr; *Install/Update* &rarr;
*Available Software Sites* menu to ensure a successful upgrade (e.g., Neon).

## Install the Dev Studio Bundle [](id=install-the-liferay-ide-bundle)

1.  Download and install [Java](http://java.oracle.com). Liferay runs on Java,
    so you'll need it to run everything else. Because you'll be developing apps
    for Liferay Portal in Dev Studio, the Java Development Kit (JDK) is
    required. It is an enhanced version of the Java Environment used for
    developing new Java technology. You can download the Java SE JDK from the
    Java
    [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    page. 

2.  Download Liferay's
    [Project SDK with @ide@](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/3.2.0%20M1/)
    executable that correlates to your operating system. The Project SDK
    includes @ide@,
    [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace),
    and [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli).

3.  Run the Project SDK executable and step through the installer to install
    everything to your machine. For help with setting up proxy settings (if
    necessary), see the
    [Liferay IDE Proxy Settings](/develop/tutorials/-/knowledge_base/7-1/setting-proxy-requirements-for-liferay-ide)
    and
    [Liferay Workspace Proxy Settings](/develop/tutorials/-/knowledge_base/7-1/setting-proxy-requirements-for-liferay-workspace)
    tutorials for more information.

Congratulations! You've installed Liferay @ide@! It's now available in the
Project SDK folder's `liferay-developer-studio`. To run Dev Studio, execute the
`DeveloperStudio` executable. A Liferay Workspace has also been initialized in
that same folder.

Congratulations! You've installed Liferay Dev Studio! 

## Install Dev Studio into Eclipse [](id=install-liferay-ide-into-eclipse)

If you already have an Eclipse environment that you're using for other
things, it's easy to add Dev Studio to your existing Eclipse installation. 

1.  In your browser, go to the
    [Liferay @ide@](https://www.liferay.com/downloads/liferay-projects/liferay-ide)
    page. Copy the URL to the 3.2.x update site. 

    ![Figure 1: Liferay provides an update site, which provides the latest stable, well-tested environment.](../../../images/liferay-ide-download.png)

2.  Select *Help* &rarr; *Install New Software*. 

3.  In the *Work with* field, copy in the URL.

4.  You'll see the Liferay Dev Studio components in the list below. Check them
    off and click *Next*. 

5.  Accept the terms of the agreements and click *Next*, and Dev Studio is
    installed. Like other Eclipse plugins you'll have to restart Eclipse to
    enable it. 

## Install Dev Studio into Eclipse from a ZIP File [](id=install-liferay-ide-into-eclipse-from-a-zip-file)

To install Liferay Dev Studio into Eclipse from a Zip file, follow these steps: 

1.  Go to the
    [Liferay @ide@](https://www.liferay.com/downloads/liferay-projects/liferay-ide)
    downloads page. Under *Other Downloads*, select the *Liferay IDE [version]
    Archive Update-site* option and click *Download*.

2.  In Eclipse, go to *Help* &rarr; *Install New Software...*. 

3.  In the *Add* dialog, click the *Archive* button and browse to the location
    of the downloaded Dev Studio Zip file.

4.  You'll see the Dev Studio components in the list below. Check them off and
    click *Next*. 

5.  Accept the terms of the agreements and click *Next*, and Liferay Dev Studio
    is installed. Like other Eclipse plugins you'll have to restart Eclipse to
    enable it.

Awesome! You've installed Liferay Dev Studio. Now you can begin Liferay
development using a popular and supported IDE.
