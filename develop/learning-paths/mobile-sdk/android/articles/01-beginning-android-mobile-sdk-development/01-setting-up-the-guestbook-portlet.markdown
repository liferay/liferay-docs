# Setting up the Guestbook Portlet

You need to set up a few things before you can begin developing the Guestbook 
Android app. This article walks you through the setup with the following steps:

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
download a Tomcat server bundle with Liferay preinstalled from [here](https://www.liferay.com/downloads/liferay-portal/available-releases). 
Simply unzip the file to the location of your choice on your machine. We 
recommend creating a `bundles` directory and unzipping it there. The bundle's 
root directory is referred to as *Liferay Home* and is named according to the 
version, edition, and specific Liferay release. For example, if you downloaded 
Liferay Portal 6.2 CE GA3 and unzipped it to a `bundles` directory on your 
machine, then the path of the bundle's Liferay Home directory is:

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
also run `tail -f ../logs/catalina.out` immediately following the `./startup.sh` 
command. This ensures that the server log prints in the terminal window. This 
step isn't necessary on Windows because the server log opens in another window 
automatically.

After a minute or two, Liferay finishes starting up and should automatically 
take you to its initial setup page at [http://localhost:8080](http://localhost:8080). 
On this page you need to provide some basic information about how you want your 
portal to operate. Give your portal a name, select the default language, and 
then uncheck the *Add Sample Data* box. Then enter the first name, last name, 
and email address of the administrative user (for the purposes of this learning 
path, these don't have to be real). If you want to connect Liferay to a separate 
database such as MySQL, PostgreSQL, or similar, you can also configure it here. 
If you want to start quickly, the embedded database is fine for your local 
machine (but it’s not optimized for production, so don’t use it there). Click 
*Finish Configuration* when you're done. When the success message displays, 
click *Go to My Portal* and accept the license agreement. Then set a password 
and a password reminder query for your administrative user. You're then taken to 
the default site of your portal. 

Great! Now there's just one thing left to do: deploy the Guestbook portlet to 
the Liferay instance you just set up.

## Deploying the Guestbook Portlet

<!-- Replace link once .war file is on GitHub -->
Now that your portal is set up, you can deploy the Guestbook portlet to it. 
Download the Guestbook portlet's `.war` package from [here](http://www.liferay.com/) 
and place it in the `[Liferay Home]/deploy` directory. You should see messages 
in your console indicating that the Guestbook portlet has been successfully 
deployed.

<!-- Add steps for adding the Guestbook portlet to a page and creating some entries/guestbooks -->
