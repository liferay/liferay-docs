# Setting up the Guestbook Portlet [](id=setting-up-the-guestbook-portlet)

Before you begin developing the Guestbook app for Android, you must set up the 
Guestbook portlet in a @product@ instance. To do this, follow these steps: 

1.  [Install JDK 8](/develop/tutorials/-/knowledge_base/7-0/setting-up-the-guestbook-portlet#installing-the-jdk)
2.  [Install and Configure a Local @product@ bundle](/develop/tutorials/-/knowledge_base/7-0/setting-up-the-guestbook-portlet#installing-and-configuring-a-local-liferay-bundle)
3.  [Deploy the Guestbook Portlet to the Local @product@ Instance](/develop/tutorials/-/knowledge_base/7-0/setting-up-the-guestbook-portlet#deploying-the-guestbook-portlet)

## Installing the JDK [](id=installing-the-jdk)

To run @product-ver@ and develop Android apps, you must have JDK 8 installed. 
This JDK is required on all operating systems. You can download and install the 
Java SE JDK from the 
[Java downloads page](http://www.oracle.com/technetwork/java/javase/downloads/index.html). 
This page also has links to the JDK installation instructions. 

## Installing and Configuring a Local @product@ Bundle [](id=installing-and-configuring-a-local-liferay-bundle)

First, download a @product@ Tomcat bundle from 
[liferay.com](https://www.liferay.com/). 
Then 
[click here](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal)
and follow the instructions to install the bundle. To follow @product@ best 
practices, you should create a `bundles` directory and unzip it there. The 
bundle's root directory is referred to as *Liferay Home* and is named according 
to the version, edition, and specific @product@ release. For example, if you 
downloaded Liferay Portal 7.0 CE GA3 and unzipped it to a `bundles` directory on 
your machine, the path of that bundle's Liferay Home directory is: 

    bundles/liferay-ce-portal-7.0-ga3

Now you're ready to start @product@! 
[Click here](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#running-product-for-the-first-time) 
and follow the instructions to start the bundle. If you're on Mac or Linux you 
should also run `tail -f ../logs/catalina.out` immediately after the 
`./startup.sh` command executes. This ensures that the server log prints to the 
terminal. This step isn't necessary on Windows because the server log 
automatically opens in another window. 

After a minute or two, @product@ starts up and automatically takes you to its 
initial setup page at 
[http://localhost:8080](http://localhost:8080). 
On this page, you need to provide some basic information about how to set up 
your @product@ instance. Enter a name for your instance, select the default 
language, and then uncheck the *Add Sample Data* box. Then enter the first name, 
last name, and email address of the default administrative user. For the 
purposes of this Learning Path, these don't have to be real. If you want to 
connect @product@ to a separate database such as MySQL or PostgreSQL, you can 
configure that connection here. Note that although the default embedded database 
is fine for development on your local machine, it isn't optimized for 
production. Click *Finish Configuration* when you're done. Then set a password 
and a password reminder query for your administrative user. Your @product@ 
instance then takes you to its default site. 

Great! Next, you'll deploy the Guestbook portlet to your @product@ instance. 

## Deploying the Guestbook Portlet [](id=deploying-the-guestbook-portlet)

Now that your @product@ instance is set up, you can deploy the Guestbook portlet 
to it. First, 
[click here](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/mobile/android/) 
and download the Guestbook portlet's modules: 

- `guestbook.api-1.0.0.jar`
- `guestbook.service-1.0.0.jar`
- `guestbook.web-1.0.0.jar`

Place these modules in your @product@ instance's `[Liferay Home]/deploy` 
directory. You should then see console messages indicating that the modules have 
successfully deployed and started. 

Now you'll add the Guestbook portlet to a page and create some guestbooks and 
entries. On your @product@ instance's default site, click the *Add* button 
(![Add](../../../images/icon-control-menu-add.png)) on the upper-right corner of 
the screen. Then click the *Applications* &rarr; *Sample* category and drag 
*Guestbook* onto the page. The Guestbook portlet should now appear containing 
the default guestbook (Main). In the portlet, add a new guestbook and an entry 
or two each from the *Add* menu 
(![Add](../../../images/icon-app-add.png)) that appears in the top right of the 
portlet's border when you mouse over the portlet. This ensures there's some 
content for the Android app to display. The Guestbook portlet on your site 
should now resemble this: 

![Figure 1: The Guestbook portlet, with a new guestbook and some entries.](../../../images/guestbook-portlet-01.png)

Stupendous! You've successfully set up a @product@ instance and added the 
Guestbook portlet to it. Now you're ready to get started with the Liferay Mobile 
SDK. 
