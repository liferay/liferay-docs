# Installation

Welcome to the Liferay Quickstart Guide! This document is intended to
get you up and running with Liferay as quickly as possible. To do this,
we will walk through the steps of implementing Liferay for a corporate
Intranet, one of many applications for which Liferay Portal is used. If
this is your implementation, you will be able to directly apply the
concepts here to your Intranet. If not, you will still be able to use
these techniques to build your site, as we will be implementing many of
Liferay's built-in features, and you will be able to see how they work
firsthand.

And so, without further ado (this is a **Quick**start Guide after all),
let's get started.

## **Liferay Installation**

A prerequisite for installing Liferay is to have the JDK (Java
Development Kit) installed first. If you're running Linux or a Mac on
your machine, the JDK is generally provided by the vendor of your
operating system, either on a CD or in a downloadable software
repository.

If you're running Windows, it's a little more complicated. Go to
[http://java.sun.com](http://java.sun.com/) and download the latest
version of the JDK. It is likely labeled “Java SE” in the *Popular
Downloads* section. When you install it, don't install it in the default
location. Instead, install it to a folder near the root folder on your
machine, such as C:\\Java. Use the subfolder name that it gives you
(i.e., jdk1.6.0\_06) so that the full path looks something like
C:\\Java\\jdk1.6.0\_06. When the installer for the JRE comes up, change
the path for that too so that it is inside C:\\Java.

When you are finished, go to your Control Panel and click the System
icon. Go to Advanced, and click the Environment Variables button. Add a
system variable (not a user variable) called JAVA\_HOME, and point it to
the directory to which you installed the JDK (i.e.,
C:\\Java\\jdk1.6.0\_06). Click OK, and then edit the Path variable,
which you'll find in the list of system variables. At the beginning of
the variable, add a section which reads %JAVA\_HOME%\\bin and put a
semicolon (;) after it. Click OK until you come out of all the dialog
boxes, and then open a Command Prompt window. Type java –version and
press Enter. If you get a message telling you the version of Java,
you’ve correctly configured the JDK. If not, check your JAVA\_HOME
variable to see if you have it pointing to the location of your JDK
install correctly, and make sure your syntax is correct in your Path
variable.

![image](../../images/01-installation_html_703c63b.png)\
*Illustration 1: Setting the environment variables on Windows.*

\
Liferay Portal is provided as one of several convenient application
server bundles. This is by far the fastest and easiest way to install
Liferay, so we will use it. You can grab the recommended bundle at
Liferay's download page:
[http://www.liferay.com/web/guest/downloads/portal](http://www.liferay.com/web/guest/downloads/portal).
The link will be right there at the top of the page; click it and
download the bundle to a convenient location on the machine upon which
you want to build your site.

The recommended bundle uses the lightweight Tomcat servlet container as
an application server to host Liferay. To install it, simply unzip it.
It will create a folder and a folder structure underneath that folder.
It doesn't modify configuration files, change registry settings, or
anything like that. Uninstalling Liferay is as simple as deleting this
folder.

Once you've unzipped Liferay, you can run it immediately. It uses an
embedded database to achieve this so you can get up and running quickly;
however, the default embedded database is populated with Liferay's web
site. We're going to create our own web site, so let's start with an
empty database.

Navigate to the folder to which you installed Liferay. Inside this
folder should be a folder called *bin*; open this folder. Inside you
will find several files. Move *lportal.properties* and *lportal.log*out
of this folder, or delete them.

Now you are ready to start Liferay! If you are on Windows, double-click
on the *startup.bat* file that's in this folder. If you are on Linux or
Mac, launch the *startup.sh* file. Liferay will start and will create a
new, blank database automatically. If you are running Windows, the
Windows Firewall may pop up. Make sure you unblock Liferay, or you won't
be able to connect to it via your browser once it starts, which makes
this whole exercise a moot point.

To see your new portal, open up a browser and type the following URL
into the address box:

[http://localhost:8080](http://localhost:8080/)

You will see Liferay's default home page:

![image](../../images/01-installation_html_19104a22.png)\
*Illustration 2: Liferay's default home page.*

\
\

Congratulations! You have just installed Liferay Portal!

\
\

