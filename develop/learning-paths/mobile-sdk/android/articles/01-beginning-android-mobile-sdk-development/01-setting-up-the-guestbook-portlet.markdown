# Setting up the Guestbook Portlet

You need to set up a few things before you can begin developing the Guestbook 
app for Android. This article walks you through this setup with the following 
steps:

1. Installing the Java Development Kit (JDK)
2. Installing and Configuring a Local Liferay Instance
3. Deploying the Guestbook Portlet to the Local Liferay Instance

## Installing the JDK

To run Liferay and develop Android apps, you need to have the JDK installed. 
While it's possible to run Liferay on Windows with the Java Runtime Environment 
(JRE) instead, the JDK is required to run Liferay on other operating systems. 
More importantly, the JDK is required on all operating systems to develop 
Android apps. You can download and install the Java SE JDK from the Java 
[Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
page. This page also has links to the JDK installation instructions.

## Installing and Configuring a Local Liferay Instance

Next, you need to install and configure a Local Liferay instance. You can 
download a Tomcat server bundled with Liferay from [here](https://www.liferay.com/downloads/liferay-portal/available-releases). 
Simply unzip the file to the location of your choice on your machine. We 
recommend creating a `bundles` directory and unzipping it there. The bundle's 
root directory is referred to as *Liferay Home* and is named according to the 
version, edition, and specific Liferay release. For example, if you downloaded 
Liferay Portal 6.2 CE GA3 and unzipped it to a `bundles` directory on your 
machine, then the path of that bundle's Liferay Home directory is:

    bundles/liferay-portal-6.2-ce-ga3

Now you're ready to start Liferay! On the command line, navigate to the 
`[Liferay Home]/tomcat-[version]/bin` directory. The `tomcat-[version]` 
directory represents the version of Tomcat included in the bundle. For example, 
Tomcat 7.0.42 is included in the bundle with Liferay 6.2 CE GA3. The 
`tomcat-[version]` directory is therefore `tomcat-7.0.42`. The full path to the 
Tomcat `bin` directory in this example is:

    bundles/liferay-portal-6.2-ce-ga3/tomcat-7.0.42/bin
    
When you get to this directory, run `startup` if you're on Windows, or 
`./startup.sh` if you're on Mac or Linux. If you're on Mac or Linux you should 
also run `tail -f ../logs/catalina.out` immediately after the `./startup.sh` 
command executes. This ensures that the server log prints in the terminal 
window. This step isn't necessary on Windows because the server log opens in 
another window automatically.

After a minute or two, Liferay finishes starting up and should automatically 
take you to its initial setup page at [http://localhost:8080](http://localhost:8080). 
On this page, you need to provide some basic information about how to set up
your portal. Enter a name for your portal, select the default language, and then
uncheck the *Add Sample Data* box. Then enter the first name, last name, and
email address of the default administrative user. (For the purposes of this
learning path, these don't have to be real.) If you want to connect Liferay to a
separate database such as MySQL or PostgreSQL, you can configure the database
connection here. If you want to start quickly, the embedded database is fine for
your local machine. (The embedded database is not optimized for production, so
don't use it there.) Click *Finish Configuration* when you're done. When the
success message appears, click on *Go to My Portal* and accept the license
agreement. Then set a password and a password reminder query for your
administrative user. You're then taken to the default site of your portal. 

Great! Now there's just one more thing left to do: deploy the Guestbook portlet
to the Liferay installation you just set up. 

## Deploying the Guestbook Portlet

<!-- Replace link once .war file is on LDN -->
Now that your portal is set up, you can deploy the Guestbook portlet to it. 
Download the Guestbook portlet's `.war` package from [here](https://github.com/ngaskill/liferay-docs/blob/LRDOCS-1534-msdk-lp/develop/learning-paths/mobile-sdk/guestbook-portlet-war/guestbook-portlet.war) 
and place it in the `[Liferay Home]/deploy` directory. You should see messages 
in your console indicating that the Guestbook portlet has been successfully 
deployed. On the default site of your portal, click the + icon on the left side 
of the screen and then click *Applications* at the top of the menu that slides 
out. Open the *Sample* category and drag *Guestbook* onto the page. The 
Guestbook portlet should now appear with the default *Main* guestbook. Add a new 
guestbook in the portlet and an entry or two to each. When you create the 
Guestbook Android app, this ensures there's some content to display in it. The 
Guestbook portlet on your site should now look something like this: 

![Figure 1: The Guestbook portlet, with a new guestbook and some entries.](../../images/guestbook-portlet-01.png)

Stupendous! You've successfully set up a Liferay installation and added the 
Guestbook portlet to it. Now you're ready to get started with the Liferay Mobile 
SDK.
