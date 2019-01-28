# Installing Liferay @ide@ [](id=installing-liferay-ide)

Liferay @ide@ is a plugin for Eclipse that brings many Liferay-specific features
to the table. You can install it into your existing Eclipse environment, or
Liferay provides a bundled version. In this tutorial, you'll learn the different
methods available for installing Liferay @ide@.

## Install the Liferay @ide@ Bundle [](id=install-the-liferay-ide-bundle)

1.  Download and install [Java](http://java.oracle.com). Liferay runs on Java, so
    you'll need it to run everything else. Because you'll be developing apps for
    Liferay Portal in Liferay @ide@, the Java Development Kit (JDK) is required.
    It is an enhanced version of the Java Environment used for developing new
    Java technology. You can download the Java SE JDK from the Java
    [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    page. 

2.  Download Liferay's latest
    [Project SDK with @ide@](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/)
    executable that correlates to your operating system. The Project SDK
    includes @ide@,
    [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace),
    and [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

3.  Run the Project SDK executable and step through the installer to install
    everything to your machine. For help with setting up proxy settings (if
    necessary), see the
    [Liferay IDE Proxy Settings](/develop/tutorials/-/knowledge_base/7-0/setting-proxy-requirements-for-liferay-ide)
    and
    [Liferay Workspace Proxy Settings](/develop/tutorials/-/knowledge_base/7-0/setting-proxy-requirements-for-liferay-workspace)
    tutorials for more information.

Congratulations! You've installed Liferay @ide@! 

## Install Liferay @ide@ into Eclipse [](id=install-liferay-ide-into-eclipse)

If you already have an Eclipse environment that you're using for other things,
it's easy to add Liferay @ide@ to your existing Eclipse installation. 

1.  In your browser, go to the
    [Liferay @ide@](https://community.liferay.com/en_GB/project/-/asset_publisher/TyF2HQPLV1b5/content/ide-installation-instructions)
    page. Copy the URL to the update site you're interested in (stable or
    milestone). 

2.  Select *Help* &rarr; *Install New Software*. 

3.  In the *Work with* field, copy in the URL.

5.  You'll see the Liferay @ide@ components in the list below. Check them off
    and click *Next*. 

6.  Accept the terms of the agreements and click *Next*, and Liferay @ide@ is
    installed. Like other Eclipse plugins you'll have to restart Eclipse to
    enable it. 

## Install Liferay @ide@ into Eclipse from a ZIP File [](id=install-liferay-ide-into-eclipse-from-a-zip-file)

To install Liferay @ide@ into Eclipse from a Zip file, follow these steps: 

1.  Go to the
    [Liferay @ide@](https://community.liferay.com/en_GB/project/-/asset_publisher/TyF2HQPLV1b5/content/ide-installation-instructions)
    downloads page. Under *Other Downloads*, select the *Liferay IDE [version]
    Archived Update-site* option to download it.

2.  In Eclipse, go to *Help* &rarr; *Install New Software...*. 

3.  In the *Add* dialog, click the *Archive* button and browse to the location
    of the downloaded Liferay @ide@ Zip file.

4.  You'll see the Liferay @ide@ components in the list below. Check them off and
    click *Next*. 

5.  Accept the terms of the agreements and click *Next*, and Liferay @ide@ is
    installed. Like other Eclipse plugins you'll have to restart Eclipse to
    enable it.

Awesome! You've installed Liferay @ide@. Now you can begin Liferay development
using a popular and supported IDE.
