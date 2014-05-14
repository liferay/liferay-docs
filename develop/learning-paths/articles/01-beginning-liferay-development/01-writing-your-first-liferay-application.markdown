# Writing Your First Liferay Application

Getting started with your first Liferay application takes only minutes. We'll
guide you through the process of creating your project and deploying your
application step-by-step. Before you know it, you'll have Liferay serving your
application next to the rest of the applications that come with Liferay. 

![Figure 1.x: You'll create this simple application.](../../images/my-first-app.png)

Your first application is simple: you'll build a guest book application. You'll
be surprised at how easy this is. 

## Creating Your First Liferay Application

Now that everything's configured, let's jump right in and create your first
project. Applications in Liferay Portal are called *portlets*, so you'll create a
portlet project. 

1. Select *File* &rarr; *New* &rarr; *Liferay Plugin Project*. The dialog below
appears. 

![Figure 1.x: Liferay IDE/Developer Studio's new project wizard makes it easy to create Liferay projects.](../../images/lds-new-liferay-plugin-project-1.png)

2. Fill in the Project Name and the Display Name. Make the project name
*guestbook-portlet* and the display name *Guestbook*. 

3. Uncheck *Include Sample Code*. 

4. Check *Launch New Portlet Wizard after project is created*. 

5. When done, click *Finish*. 

---

![tip](../../images/01-tip.png) The first time you create a project, make sure
you're online. In the background, Liferay's Plugins SDK downloads several .jar
files that it needs in order to operate. This keeps the initial download small,
but makes it take a long time to create your first project (about four minutes).
After the initial download, it won't happen again. 

---

Your project now appears in the Package Explorer on the left. What you've just
done is create a blank Liferay project. Projects can have any number of
applications (called *portlets*) in them. Your next step is to create the
portlet, which the New Portlet Wizard helps you to do. 

1. For the Portlet class, enter the name `GuestbookPortlet`. 

2. All classes in Java should be properly namespaced to avoid conflicts with
other classes that could have similar names. For the Java package, enter
`com.liferay.docs.guestbook.portlet`. 

3. Click *Finish*. 

Liferay IDE/Developer Studio creates the portlet class in the proper package in
the `src` folder and creates a new folder structure for your application's JSP
files. It then opens your portlet class in an editor. 

You are now ready to work. 

## What is a portlet? 

When you access a web site, you interact with an application. That application
may be simple: it may only show you information, such as an article. The
application may be complex: you may be doing your taxes online, inputting lots
of data into an application that calculates whether you owe or are due a refund.
These applications run on a *platform* that provides application developers the
building blocks they need to make applications. 

![Figure 1.x: Many Liferay applications can run at the same time on the same page.](../../images/portlet-applications.png)

Liferay Portal is a platform, and its applications are called *portlets*. One
difference between a Liferay application and an application built on another
platform is that Liferay Portal can serve many applications at the same time on
the same page. Usually, a web application takes up the entire page, and if you
want, you can do this with Liferay as well. But Liferay has the added benefit of
being able to run many applications on the same page. For this reason, you'll
find that the framework takes this into account at every step. We're saying this
up front, because you'll be experiencing this soon, and we want you to know why
certain things, like platform-generated URLs, are necessary. 

With all of that said, the next thing to do is to jump in and create your
Guestbook portlet. 

## Adding Guestbook Entries

A guestbook application is pretty simple, right? People come to your site, type
their names and a brief message, and then post it for you. Users can read the
entries that others posted, and they can post entries themselves. 

The first thing, therefore, that we need is a landing page that displays entries
and that has a button on it that allows users to add an entry. This page was
created when you created your project: you'll find it in the
`/docroot/html/guestbook` folder of your project. It's called `view.jsp`. Open
this file. 

You'll see there's some content in it already: Liferay IDE generates a fully
functional sample portlet, but the only thing it does is display the message you
see in the `view.jsp` file. Let's give it some functionality by adding a button
that says *Add Entry*. 

1. Open the *Snippets* tab on the right side of Liferay IDE, and expand the
*Taglib Imports* category. 

2. Drag the snippet labeled *Liferay AUI Taglib Import v6.1* from the snippet
area to the line beneath the existing taglib import in `view.jsp`. Don't worry
about the Liferay version number in the snippet title: the import was changed
for version 6.1, and it is valid for Liferay versions going forward. The
following code gets added to `view.jsp`: 

    <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

This declares that we want to use Liferay's AlloyUI tags. 

3. Next, remove the default message in the JSP. 

4. Open the *Liferay AUI Taglib* snippet category and drag the snippet labeled
*button-row* onto the bottom of the page. A dialog box pops up asking for the
`cssClass` attribute. Type `guestbook-buttons` for this value, hit Enter, and
click *Insert*. 

## Next Steps

Obviously, you'll want to do far more interesting things than this. Here are
some possible next steps: 

[Write a data-driven application](http://www.liferay.com)

[Use the Plugins SDK to develop Liferay applications](http://www.liferay.com)

[Create web forms with Alloy UI tag libs](http://www.liferay.com)

