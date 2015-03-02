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
recommend creating a `bundles` directory and unzipping it there.

Now you're ready to start Liferay! On the command line, navigate to the 
`[liferay-home]/tomcat-[version]/bin` directory. The `[liferay-home]` directory 
is your Liferay instance's top-level folder. For example, if you downloaded 
Liferay 6.2 CE GA3 to to a `bundles` directory as suggested, then your 
`[liferay-home]` directory is `bundles/liferay-portal-6.2-ce-ga3`. The 
`tomcat-[version]` directory is the version of Tomcat included in the bundle. 
For example, the Tomcat bundle with Liferay 6.2 CE GA3 comes with version 7.0.42 
of Tomcat. The `tomcat-[version]` directory is therefore `tomcat-7.0.42`. The 
full path to the Tomcat `bin` directory in this example is therefore:

    bundles/liferay-portal-6.2-ce-ga3/tomcat-7.0.42/bin
    
    
