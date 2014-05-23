# Writing Your First Liferay Application

Getting started with your first Liferay application takes only minutes. We'll
guide you through the process of creating your project and deploying your
application step-by-step. Before you know it, you'll have Liferay serving your
application next to the rest of the applications that come with Liferay. 

![Figure 1.x: You'll create this simple application.](../../images/my-first-app.png)

Your first application is simple: you'll build a portlet application that displays
the venerable *Hello World* message. You'll be surprised at how easy this is. 

## Creating Your First Liferay Application

Now that everything's configured, let's jump right in and create your first
project. Applications in Liferay Portal are called *portlets*, so you'll create a
portlet project. 

1. Select *File* &rarr; *New* &rarr; *Liferay Plugin Project*. The dialog below
appears. 

![Figure 1.x: Liferay IDE/Developer Studio's new project wizard makes it easy to create Liferay projects.](../../images/lds-new-liferay-plugin-project-1.png)

2. Fill in the Project Name and the Display Name. Make the project name
*hello-portlet* and the display name *Hello*. When done, click *Finish*. 

---

![tip](../../images/01-tip.png) The first time you create a project, make sure
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

## Next Steps

Obviously, you'll want to do far more interesting things than this. Here are
some possible next steps: 

[Write a data-driven application](http://www.liferay.com)

[Use the Plugins SDK to develop Liferay applications](http://www.liferay.com)

[Create web forms with Alloy UI tag libs](http://www.liferay.com)

