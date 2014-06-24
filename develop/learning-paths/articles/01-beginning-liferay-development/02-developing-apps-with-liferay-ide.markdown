# Developing Apps with Liferay IDE [](id=developing-apps-with-liferay-ide-lp-6-2-develop-learnpath)

To develop an application, you need a development environment. Liferay
provides a great environment with Liferay IDE. 

Liferay IDE is a plugin for Eclipse that brings many Liferay-specific
features to the table. You can install it into your existing Eclipse
environment, or Liferay provides a bundled version. 

## Installing Liferay IDE into Eclipse

If you already have an Eclipse environment that you're using for other
things, it's easy to add Liferay IDE to your existing Eclipse installation. 

1. Select *Help* &rarr; *Install New Software*. 

2. In your browser, go to the [Liferay IDE](https://www.liferay.com/downloads/liferay-projects/liferay-ide) page. Copy
   the URL to the update site you're interested in (stable or milestone). 
    milestone). 

    ![Figure 1: Liferay provides two update sites: stable for those who want a well-tested environment, and milestone for those who like the bleeding edge.](../../images/liferay-ide-download.png)

3. Go back to Eclipse and click the *Add* button to add a repository. 

4. Type *Liferay IDE* into the Name field and paste the URL into the Location 
   field. 

5. You'll see the Liferay IDE components in the list below. Check them off and
   click *Next*. 

6. Accept the terms of the agreements and click *Next*, and Liferay IDE is
   installed. Like other Eclipse plugins you'll have to restart Eclipse to
   enable it. 

## Installing Liferay IDE into Eclipse from a Zip File

To install Liferay IDE into Eclipse from a `.zip` file, follow these steps: 

1. Download the IDE 2.1 `.zip` file from
   [http://sourceforge.net/projects/lportal/files/Liferay IDE/2.1.0
   GA1/](http://sourceforge.net/projects/lportal/files/Liferay%20IDE/2.1.0%20GA1/)
   for your operating system. 

2. In Eclipse, go to *Help* &rarr; *Install New Software...*. 

3. In the *Add Site* dialog, click the *Archive* button and browse to the
   location of the downloaded Liferay IDE `.zip` file.

4. You'll see the Liferay IDE components in the list below. Check them off and
   click *Next*. 

5. Accept the terms of the agreements and click *Next*, and Liferay IDE is
   installed. Like other Eclipse plugins you'll have to restart Eclipse to
   enable it. 

## Installing the Liferay IDE Bundle

1. Download and install [Java](http://java.oracle.com). Liferay runs on
Java, so you'll need it to run everything else. 

2. Download [Liferay IDE](https://www.liferay.com/downloads/liferay-projects/liferay-ide).
Installing it is easy: unzip it to a convenient location on your system. 

Congratulations! You've installed Liferay IDE! 

## Setting up Liferay's Developer Tools

Liferay IDE needs a Liferay Portal runtime on which to run and debug code.
Under the hood, it also works with Liferay's Plugins SDK, a barebones,
command line driven development environment you can use with any IDE or text
editor. Because of this, your next task is to get both of these installed. 

1. Install a [Liferay Tomcat runtime](https://www.liferay.com/downloads/liferay-portal/available-releases)
you'll use in your development environment to run your applications. This is just as easy as installing Liferay IDE: unzip it to
your system. We recommend creating a `bundles` folder and unzipping it there. 

2. Install the [Plugins SDK](https://www.liferay.com/downloads/liferay-portal/additional-files).
Liferay's Plugins SDK is a basic, command-line driven development environment
that the IDE uses behind the scenes to manage your Liferay projects. This is just as easy as installing everything else: unzip it
to your system. We recommend unzipping it next to the `bundles` folder you
created in the last step. 

![Figure 2: The directory structure for Liferay development is pretty simple.](../../images/developer-directory-structure.png)

When you've finished installing everything, you should have a folder structure
like the one above. Next, you need to get all these components talking to each
other. Launch IDE.
 
1. Use *Window* &rarr; *Open Perspective* &rarr; *Liferay* to switch to the
Liferay perspective. 

2. The tab on the low left corner is labeled *Servers*. Click the link to create
a new server. 

	![Figure 3: The Servers tab has no servers by default.](../../images/lds-servers.png)

3. In the dialog that appears, select the server that matches the runtime you
downloaded and installed. Click *Next*. 

	![Figure 4: Select the Liferay server runtime you downloaded.](../../images/lds-select-server.png)

4. Browse to the Tomcat folder inside the runtime and click *Finish*. 

Now it's time to start it. Select the runtime and click the green *Play* button.
Liferay starts up inside your IDE. By the way, you could've clicked the *Bug*
button, and that would have started Liferay in debug mode. 

Once Liferay starts, go to your browser and visit
[http://localhost:8080](http://localhost:8080). On a new installation, this
shows Liferay Portal's setup wizard. If you want to connect Liferay to a
separate database such as MySQL, PostgreSQL, or similar, you can configure it
here. If you want to start quickly, the embedded database is fine for your local
machine (but it's not optimized for production, so don't use it there). Make
sure you uncheck the *Add Sample Data* box, and then click *Finish
Configuration*. 

You then get to set up the administrative password. Go ahead and do that (the
password *liferay* does nicely), answer the security question, and you're done. 

Your runtime is now set up. Next, you need to register your Plugins SDK. 

1. Select *Window* &rarr; *Preferences* &rarr; *Liferay* &rarr; *Installed Plugin
SDKs*. 

2. Click *Add*. Browse to the folder where you unzipped the Plugins SDK and
click *OK*. 

3. Click *OK* again. 

You've now registered your Plugins SDK, and you are ready to begin
developing Liferay applications! 

## Next Steps

[Writing Your First Liferay Application](https://www-ldn.liferay.com/develop/learning-paths/-/knowledge_base/writing-your-first-liferay-application-lp-6-2-develop-learnpath)

