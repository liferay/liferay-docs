# Installing Liferay IDE [](id=installing-liferay-ide)

Liferay IDE is a plugin for Eclipse that brings many Liferay-specific features
to the table. You can install it into your existing Eclipse environment, or
Liferay provides a bundled version. In this tutorial, you'll learn the different
methods available for installing Liferay IDE.

## Install the Liferay IDE Bundle [](id=install-the-liferay-ide-bundle)

1. Download and install [Java](http://java.oracle.com). Liferay runs on Java, so
   you'll need it to run everything else. Because you'll be developing apps for
   Liferay Portal in Liferay IDE, the Java Development Kit (JDK) is required. It
   is an enhanced version of the Java Environment, used for developing new Java
   technology. You can download the Java SE JDK from the Java
   [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
   page. 

2. Download
   [Liferay IDE](https://www.liferay.com/downloads/liferay-projects/liferay-ide).
   Installing it is easy: unzip it to a convenient location on your system. 

Congratulations! You've installed Liferay IDE! 

## Install Liferay IDE into Eclipse [](id=install-liferay-ide-into-eclipse)

If you already have an Eclipse environment that you're using for other
things, it's easy to add Liferay IDE to your existing Eclipse installation. 

1. Select *Help* &rarr; *Install New Software*. 

2. In your browser, go to the
   [Liferay IDE](https://www.liferay.com/downloads/liferay-projects/liferay-ide)
   page. Copy the URL to the update site you're interested in (stable or
   milestone). 

    ![Figure 1: Liferay provides two update sites: stable for those who want a well-tested environment, and milestone for those who like the bleeding edge.](../../../images/liferay-ide-download.png)

3. Go back to Eclipse and click the *Add* button to add a repository. 

4. Type *Liferay IDE* into the Name field and paste the URL into the Location 
   field. 

5. You'll see the Liferay IDE components in the list below. Check them off and
   click *Next*. 

6. Accept the terms of the agreements and click *Next*, and Liferay IDE is
   installed. Like other Eclipse plugins you'll have to restart Eclipse to
   enable it. 

## Install Liferay IDE into Eclipse from a ZIP File [](id=install-liferay-ide-into-eclipse-from-a-zip-file)

To install Liferay IDE into Eclipse from a `.zip` file, follow these steps: 

1. Go to the
   [Liferay IDE](https://www.liferay.com/downloads/liferay-projects/liferay-ide)
   downloads page. Under *Other Downloads*, select the *Liferay IDE [version]
   Archive Update=site* option and click *Download*.

2. In Eclipse, go to *Help* &rarr; *Install New Software...*. 

3. In the *Add Site* dialog, click the *Archive* button and browse to the
   location of the downloaded Liferay IDE `.zip` file.

4. You'll see the Liferay IDE components in the list below. Check them off and
   click *Next*. 

5. Accept the terms of the agreements and click *Next*, and Liferay IDE is
   installed. Like other Eclipse plugins you'll have to restart Eclipse to
   enable it.

Awesome! You've installed Liferay IDE. Now you can begin Liferay development
using a popular and supported IDE.
