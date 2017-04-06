# Setting up the Guestbook Portlet [](id=setting-up-the-guestbook-portlet)

You need to set up a few things before you can begin developing the Guestbook 
app for Android. You'll take the following steps:

1.  Install the Java Development Kit (JDK) version 7. 
2.  Install and Configure a Local Liferay bundle. 
3.  Deploy the Guestbook Portlet to the Local Liferay Instance. 

## Installing the JDK [](id=installing-the-jdk)

To run Liferay and develop Android apps, you need to have JDK 7 installed.  The
JDK is required on all operating systems to develop Android apps. You can
download and install the Java SE JDK from the Java
[Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
page. This page also has links to the JDK installation instructions.

## Installing and Configuring a Local Liferay Bundle [](id=installing-and-configuring-a-local-liferay-bundle)

Follow the instructions
[here](/discover/deployment/-/knowledge_base/6-2/installing-a-bundle) to install
a Liferay bundle. To follow Liferay best practices, you should create a
`bundles` directory and unzip it there. The bundle's root directory is
referred to as *Liferay Home* and is named according to the version, edition,
and specific Liferay release. For example, if you downloaded Liferay Portal 6.2
CE GA4 and unzipped it to a `bundles` directory on your machine, the path of
that bundle's Liferay Home directory is:

    bundles/liferay-portal-6.2-ce-ga4

Now you're ready to start Liferay! Start the bundle as described in the link
above. If you're on Mac or Linux you should also run `tail -f
../logs/catalina.out` immediately after the `./startup.sh` command executes.
This ensures that the server log prints in the terminal window. This step isn't
necessary on Windows because the server log opens in another window
automatically.

After a minute or two, Liferay finishes starting and automatically takes you to
its initial setup page at 
[http://localhost:8080](http://localhost:8080). 
On this page, you need to provide some basic information about how to set up
your portal. Enter a name for your portal, select the default language, and then
uncheck the *Add Sample Data* box. Then enter the first name, last name, and
email address of the default administrative user. For the purposes of this
Learning Path, these don't have to be real. If you want to connect Liferay to a
separate database such as MySQL or PostgreSQL, you can configure the database
connection here. If you want to start quickly, the embedded database is fine for
your local machine. The embedded database is not optimized for production, so
don't use it there. Click *Finish Configuration* when you're done. When the
success message appears, click on *Go to My Portal*. Then set a password and a
password reminder query for your administrative user. You're then taken to the
default site of your portal. 

Great! Now there's just one more thing left to do: deploy the Guestbook portlet
to the Liferay installation you just set up. 

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

Stupendous! You've successfully set up a Liferay installation and added the 
Guestbook portlet to it. Now you're ready to get started with the Liferay Mobile 
SDK.
