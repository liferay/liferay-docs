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

1. Select *Window* $rarr; *Preferences* $rarr; *Liferay* $rarr; *Installed Plugin
SDKs. 

2. Click *Add*. Browse to the folder where you unzipped the Plugins SDK and
click *OK*. 

3. Click *OK* again. 

You've now registered your Plugins SDK, and you are ready to begin! 

## Creating Your First Liferay Application

Now that everything's configured, let's jump right in and create your first
project. Applications in Liferay Portal are called *portlets*, so you'll create a
portlet project. 

1. Select *File* $rarr; *New* $rarr; *Liferay Plugin Project*. The dialog below
appears. 

![Figure 1.x: Liferay IDE/Developer Studio's new project wizard makes it easy to create Liferay projects.](../../images/lds-new-liferay-plugin-project-1.png)

2. Fill in the Project Name and the Display Name. Make the project name
*hello-portlet* and the display name *hello*. When done, click *Finish*. 

---

![tip](../../images/01-tip.png)The first time you create a project, make sure
you're online. In the background, Liferay's Plugins SDK downloads several .jar
files that it needs in order to operate. This keeps the initial download small,
but makes it take a long time to create your first project (about four minutes).
After the initial download, it won't happen again. 

---

Your project now appears in the Package Explorer on the left. Believe it or not,
you're done! To see your application, drag it from the Package Explorer and drop
it on your server in the Servers tab below. 

![Figure 1.x: Deploying a project is as easy as drag and drop.](../../images/lds-deploy-project.png)

Once your application deploys, you can add it to a page. Applications by default
show up in the *Sample* category in the *Add Applications* window. Find your app
and add it to a page. 

![Figure 1.x: Your first app displays a simple message.](../../images/my-first-app.png)

Okay, so maybe we cheated a little: when you create a new project, you have a
working shell, but you didn't actually do anything, did you? Let's
fix that by having you customize the message. 

1. In Liferay IDE/Developer Studio, open `view.jsp`, which you'll find in the
`docroot` folder of your project. 

2. The last line of the file says, "This is the Hello portlet." Change that line
to anything you want. You can use HTML tags to format your message. 

3. Save the file. 

Now refresh your browser, and you'll see your new message. Now you've truly
created your first app! 

Obviously, you'll want to do far more interesting things than this. Here are
some possible next steps: 

[Write a data-driven application](http://www.liferay.com)
[Use the Plugins SDK to develop Liferay applications](http://www.liferay.com)
[Create web forms with Alloy UI tag libs](http://www.liferay.com)
