---
header-id: installing-liferay-dev-studio
---

# Installing Liferay Dev Studio

[TOC levels=1-4]

Liferay Dev Studio is a plugin for Eclipse that brings many Liferay-specific
features to the table. You can install it into your existing Eclipse
environment, or Liferay provides a bundled version included in its Project SDK.
Here you'll learn the different methods available for installing Dev Studio:

- [install the Dev Studio bundle from scratch](#install-the-dev-studio-bundle)
- [install Dev Studio into an existing Eclipse instance using an update URL](#install-dev-studio-into-eclipse)
- [install Dev Studio into an existing Eclipse instance using a ZIP file](#install-dev-studio-into-eclipse-from-a-zip-file)

**Important:** If you're installing Dev Studio into an existing Eclipse
environment, you must be on Eclipse Photon or newer. For instructions on
upgrading to Photon, see Eclipse's
[upgrade documentation](https://wiki.eclipse.org/FAQ_How_do_I_upgrade_Eclipse_IDE%3F#Upgrading_existing_Eclipse_IDE_and_Installed_Features_to_newer_release).
With this particular upgrade, you should also deactivate the current available
update sites in the *Window* &rarr; *Preferences* &rarr; *Install/Update* &rarr;
*Available Software Sites* menu to ensure a successful upgrade (e.g., Oxygen).

## Install the Dev Studio Bundle

1.  Download and install [Java](http://java.oracle.com). Liferay runs on Java,
    so you'll need it to run everything else. Because you'll be developing apps
    for @product@ in Dev Studio, the Java Development Kit (JDK) is required. It
    is an enhanced version of the Java Environment used for developing new Java
    technology. You can download the Java SE JDK from the Java
    [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    page. 

2.  Download Liferay's latest
    [Project SDK with Dev Studio](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/).
    Go to the [Downloads](https://customer.liferay.com/group/customer/downloads)
    page in Liferay's Help Center. Select *Developer Tools* in the Product
    drop-down and *Developer Studio* for the file type. Then select the
    executable that correlates to your operating system.

    The Project SDK
    includes @ide@,
    [Liferay Workspace](/developer/reference/-/knowledge_base/7-2/liferay-workspace),
    and [Blade CLI](/developer/reference/-/knowledge_base/7-2/blade-cli).

3.  Run the Project SDK executable and step through the installer to install
    everything to your machine. For help with setting up proxy settings (if
    necessary), see the
    [Dev Studio Proxy Settings](/developer/reference/-/knowledge_base/7-2/setting-proxy-requirements-for-liferay-ide)
    and
    [Liferay Workspace Proxy Settings](/developer/reference/-/knowledge_base/7-2/setting-proxy-requirements-for-liferay-workspace)
    articles for more information.

Congratulations! You've installed Liferay Dev Studio! It's now available in the
Project SDK folder's `liferay-developer-studio`. To run Dev Studio, execute the
`DeveloperStudio` executable. A Liferay Workspace has also been initialized in
that same folder.

## Install Dev Studio into Eclipse

If you already have an Eclipse environment that you're using for other
things, it's easy to add Dev Studio to your existing Eclipse installation. 

1.  In Dev Studio, select *Help* &rarr; *Install New Software*. 

2.  In the *Work with* field, copy in the following URL:
    https://releases.liferay.com/tools/ide/latest/stable/

3.  You'll see the Liferay Dev Studio components in the list below. Check them
    off and click *Next*. 

4.  Accept the terms of the agreements and click *Next*, and Dev Studio is
    installed. Like other Eclipse plugins you'll have to restart Eclipse to
    enable it. 

## Install Dev Studio into Eclipse from a ZIP File

To install Liferay Dev Studio into Eclipse from a Zip file, follow these steps: 

1.  Go to the
    [Dev Studio](https://community.liferay.com/en_GB/project/-/asset_publisher/TyF2HQPLV1b5/content/ide-installation-instructions)
    downloads page. Under *Other Downloads*, select the *Liferay IDE [version]
    Archived Update-site* option to download it.

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
