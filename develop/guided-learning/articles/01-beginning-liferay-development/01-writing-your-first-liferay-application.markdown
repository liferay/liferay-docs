# Writing Your First Liferay Application

Getting started with your first Liferay application takes only minutes. We'll
guide you through the process of creating your project and deploying your
application step-by-step. Before you know it, you'll have Liferay serving your
application next to the rest of the applications that come with Liferay. 

[insert picture of completed app here]

Our first application is simple: we'll build a portlet application that displays
the venerable *Hello World* message. You'll be surprised at how easy this is. 

## Installing Liferay Developer Studio/IDE

To develop an application, you need a developer environment. Liferay provides a
great environment with Liferay Developer Studio or Liferay IDE. So what's the
difference between the two? 

Liferay Developer Studio contains a bundled runtime of Liferay Portal EE, while
Liferay IDE does not. For that reason, Developer Studio is available as a 30 day
trial or with your Liferay EE license, while Liferay IDE is freely downloadable
for everybody. And that's where the difference stops. Otherwise, they're both
the same: built on Eclipse and integrating with all of Liferay's technology,
they make developing on Liferay's platform a familiar and pleasant experience.

Let's get your Liferay development environment installed. 

1. Download and install [Java](http://java.oracle.com). Liferay runs on Java, so
you'll need it to run everything else. 

2. Download [Liferay IDE or Liferay Developer Studio](https://www.liferay.com/downloads/liferay-projects/liferay-ide).
Installing either one is easy: unzip it to a convenient location on your system.

---

![tip](../../images/01-tip.png)If you're an Eclipse user, you can install
Liferay IDE as a plugin on your existing installation of Eclipse. 

---

3. Install a [Liferay runtime](https://www.liferay.com/downloads/liferay-portal/available-releases)
you'll use in your development environment to run your applications. Liferay
Developer Studio users with a license don't need to do this: a runtime is
already included. This is just as easy as installing Liferay IDE: unzip it to
your system. We recommend creating a `bundles` folder and unzipping it there. 

4. Install the [Plugins SDK](https://www.liferay.com/downloads/liferay-portal/additional-files).
Liferay's Plugins SDK is a basic, command-line driven development environment
that the IDE uses behind the scenes to manage your Liferay projects. Liferay
Developer Studio users with a license don't need to do this: the Plugins SDK is
already included. This is just as easy as installing everything else: unzip it
to your system. We recommend unzipping it next to the `bundles` folder you
created in the last step. 

[Diagram of directory structure so far goes here]

When you've finished installing everything, you should have a folder structure
like the one above. Next, you need to get all these components talking to each
other. Launch IDE or Development Studio. 

If you're running Liferay Developer Studio, the First Start wizard appears. This
leads you through setting up the bundled runtime. If you accept all the
defaults, you'll have a configured Liferay runtime ready to go. 

If you're running Liferay IDE, you need to perform some steps manually to
register both your Liferay runtime and the Plugins SDK. Let's start with the
runtime. 

1. Use *Window* $rarr; *Open Perspective* $rarr; *Liferay* to switch to the
Liferay perspective. 

2. The tab on the low left corner is labeled *Servers*. Click the link to create
a new server. 

![Figure 1.x: The Servers tab has no servers by default.](../../images/lds-servers.png)

3. In the dialog that appears, select the server that matches the runtime you
downloaded and installed. Click *Next*. 

![Figure 1.x: Select the Liferay server runtime you downloaded.](../../images/lds-select-server.png)

4. Browse to the Tomcat folder inside the runtime and click *Finish*. 

Your runtime is now set up. Next, you need to register your Plugins SDK. 

1. Select *Window* $rarr; *Preferences* $rarr; *Liferay* $rarr; *Installed Plugin
SDKs. 

2. Click *Add*. Browse to the folder where you unzipped the Plugins SDK and
click *OK*. 

3. Click *OK* again. 

You've now registered your Plugins SDK, and you are ready to begin! 


