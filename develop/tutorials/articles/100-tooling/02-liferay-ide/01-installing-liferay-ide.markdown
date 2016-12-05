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

2.  Download
    [Liferay @ide@](https://www.liferay.com/downloads/liferay-projects/liferay-ide).
    Installing it is easy: unzip it to a convenient location on your system. 

3. To run @ide@, execute the `eclipse` executable.

Congratulations! You've installed Liferay @ide@! 

## Install Liferay @ide@ into Eclipse [](id=install-liferay-ide-into-eclipse)

If you already have an Eclipse environment that you're using for other
things, it's easy to add Liferay @ide@ to your existing Eclipse installation. 

1.  Select *Help* &rarr; *Install New Software*. 

2.  In your browser, go to the
    [Liferay @ide@](https://www.liferay.com/downloads/liferay-projects/liferay-ide)
    page. Copy the URL to the update site you're interested in (stable or
    milestone). 

    ![Figure 1: Liferay provides two update sites: stable for those who want a well-tested environment, and milestone for those who like the bleeding edge.](../../../images/liferay-ide-download.png)

3.  Go back to Eclipse and click the *Add* button to add a repository. 

4.  Type *Liferay IDE* into the Name field and paste the URL into the Location 
    field. 

5.  You'll see the Liferay @ide@ components in the list below. Check them off and
    click *Next*. 

6.  Accept the terms of the agreements and click *Next*, and Liferay @ide@ is
    installed. Like other Eclipse plugins you'll have to restart Eclipse to
    enable it. 

## Install Liferay @ide@ into Eclipse from a ZIP File [](id=install-liferay-ide-into-eclipse-from-a-zip-file)

To install Liferay @ide@ into Eclipse from a `.zip` file, follow these steps: 

1.  Go to the
    [Liferay @ide@](https://www.liferay.com/downloads/liferay-projects/liferay-ide)
    downloads page. Under *Other Downloads*, select the *Liferay IDE [version]
    Archive Update-site* option and click *Download*.

2.  In Eclipse, go to *Help* &rarr; *Install New Software...*. 

3.  In the *Add Site* dialog, click the *Archive* button and browse to the
    location of the downloaded Liferay @ide@ `.zip` file.

4.  You'll see the Liferay @ide@ components in the list below. Check them off and
    click *Next*. 

5.  Accept the terms of the agreements and click *Next*, and Liferay @ide@ is
    installed. Like other Eclipse plugins you'll have to restart Eclipse to
    enable it.

Awesome! You've installed Liferay @ide@. Now you can begin Liferay development
using a popular and supported IDE.
