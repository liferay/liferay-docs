# Setting up the Guestbook Portlet [](id=setting-up-the-guestbook-portlet)

Before you begin developing the Guestbook app for Android, you must set up the 
Guestbook portlet in a Liferay Portal instance. To do this, follow these steps: 

1.  [Install JDK 8](/develop/tutorials/-/knowledge_base/6-2/setting-up-the-guestbook-portlet#installing-the-jdk)
2.  [Install and Configure a Local Liferay Portal Bundle](/develop/tutorials/-/knowledge_base/6-2/setting-up-the-guestbook-portlet#installing-and-configuring-a-local-liferay-bundle)
3.  [Deploy the Guestbook Portlet to the Local Liferay Portal Instance](/develop/tutorials/-/knowledge_base/6-2/setting-up-the-guestbook-portlet#deploying-the-guestbook-portlet)

## Installing the JDK [](id=installing-the-jdk)

To get started, you must have JDK 8 installed. You can download and install the 
Java SE JDK from the 
[Java downloads page](http://www.oracle.com/technetwork/java/javase/downloads/index.html). 
This page also has links to the JDK installation instructions. 

## Installing and Configuring a Local Liferay Portal Bundle [](id=installing-and-configuring-a-local-liferay-bundle)

First, download a Liferay Portal Tomcat bundle from 
[liferay.com](https://www.liferay.com/). 
Then 
[click here](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal)
and follow the instructions to install the bundle. To follow best practices, you 
should create a `bundles` directory and unzip it there. The bundle's root 
directory is referred to as *Liferay Home* and is named according to the 
version, edition, and specific Liferay Portal release. For example, if you 
downloaded Liferay Portal 6.2 CE GA6 and unzipped it to a `bundles` directory on 
your machine, the path of that bundle's Liferay Home directory is: 

    bundles/liferay-portal-6.2-ce-ga6

Now you're ready to start Liferay Portal! 
[Click here](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#running-product-for-the-first-time) 
and follow the instructions to start the bundle. If you're on Mac or Linux you 
should also run `tail -f ../logs/catalina.out` immediately after the 
`./startup.sh` command executes. This ensures that the server log prints to the 
terminal. This step isn't necessary on Windows because the server log 
automatically opens in another window. 

After a minute or two, Liferay Portal starts up and automatically takes you to 
its initial setup page at 
[http://localhost:8080](http://localhost:8080). 
On this page, you need to provide some basic information about how to set up 
your Liferay Portal instance. Enter a name for your instance, select the default 
language, and then uncheck the *Add Sample Data* box. Then enter the first name, 
last name, and email address of the default administrative user. For the 
purposes of this Learning Path, these don't have to be real. If you want to 
connect Liferay Portal to a separate database such as MySQL or PostgreSQL, you 
can configure that connection here. Note that although the default embedded 
database is fine for development on your local machine, it isn't optimized for 
production. Click *Finish Configuration* when you're done. Then set a password 
and a password reminder query for your administrative user. Your Liferay Portal 
instance then takes you to its default site. 

Great! Next, you'll deploy the Guestbook portlet to your Liferay Portal 
instance. 

## Deploying the Guestbook Portlet [](id=deploying-the-guestbook-portlet)

Now that your portal is set up, you can deploy the Guestbook portlet to it. 
Download the Guestbook portlet's `.war` package from 
[here](https://dev.liferay.com/documents/10184/598513/guestbook-portlet.war) 
and place it in the `[Liferay Home]/deploy` directory. You should see messages 
in your console indicating that the Guestbook portlet has been successfully 
deployed. On your portal's default site, click the + icon on the left side 
of the screen and then click *Applications* at the top of the menu that slides 
out. Open the *Sample* category and drag *Guestbook* onto the page. The 
Guestbook portlet should now appear with the default *Main* guestbook. Add a new 
guestbook in the portlet and an entry or two to each. When you create the 
Guestbook Android app, this ensures there's some content to display in it. The 
Guestbook portlet on your site should now look like this: 

![Figure 1: The Guestbook portlet, with a new guestbook and some entries.](../../../images/guestbook-portlet-01.png)

Stupendous! You've successfully set up a Liferay Portal installation and added 
the Guestbook portlet to it. Now you're ready to get started with the Liferay 
Mobile SDK. 
