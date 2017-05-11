# Writing Your First Liferay Application [](id=writing-your-first-liferay-application)

It's easy to get started with your first Liferay application. You'll learn
step-by-step how to create your project and deploy your application to
@product@.  Before you know it, you'll have your application deployed alongside
of the applications that come with Liferay.

Your first application is simple: you'll build a guestbook application that
looks like this:

![Figure x: You'll create this simple application.](../../../images/first-guestbook-portlet.png)

By default, it shows guestbook messages that various users leave on your
website. To add a message, you click the *Add Entry* button to show a form that
lets you enter and save a message.

Ready to write your first Liferay application?

## Creating Your First Liferay Application [](id=creating-your-first-liferay-application)

Your first step is to create a *Liferay Module Project.* Modules are the core
building blocks of Liferay applications. Every application is made from one or
more modules. Each module is a functional piece of an application, encapsulated,
and then multiple modules form a complete application. There's good reason for
this: modules let you swap out code implementations more or less at will. This
makes your applications easy to maintain and upgrade. Each module is packaged as
a JAR file, which is just a .zip archive containing a particular folder
structure that the container understands. 

Modules need a manifest file to tell the container about it. In fact,
technically speaking your module would be perfectly valid if it contained
nothing but a manifest.
 
You already created your Liferay workspace. For the purposes of this Learning
Path, the projects you create will be created inside that workspace. Follow
the instructions below to create your Liferay Module Project.

1. Right click on your workspace.

2. Select `New` &rarr; `Liferay Module Project`

3. Enter `Guestbook` for the Project name. 

4. Use the `gradle-module` Build type.

5. Select the `mvc-portlet` for Project Template Name. Click *Next*. 

6. Enter `com.liferay.docs.guestbook` for the package name. Click *Finish*. 

Because Gradle automatically manages dependencies for you, it may take a while
for your project to be created the first time, as its dependencies must be
downloaded from the Internet. Once it's done, you have a project named
`Guestbook`. The `mvcportlet` template configured the project with the proper
dependencies and generated all the files you need to get started: 

- The portlet class (in the package you specified)
- JSP files (in `/src/main/resources`)
- Language properties (also in `/src/main/resources`)

Next, you'll learn what a portlet is. 

## What is a Portlet? [](id=what-is-a-portlet)

When you access a web site, you interact with an application. That application
could be simple: it might only show you one piece of information, such as an
article. The application might be complex: you could be doing your taxes,
entering lots of data into an application that calculates whether you owe or
are due a refund. These applications run on a *platform* that provides
application developers the building blocks they need to make applications.

![Figure x: Many Liferay applications can run at the same time on the same page.](../../../images/portlet-applications.png)

@product@ provides a platform that contains common features needed by today's
applications, including user management, security, interfaces, services, and
more. Portlets are one of those basic building blocks. Often a web application
takes up the entire page. If you want, you can do this with @product@ as well.
Portlets, however, allow @product@ to serve many applications on the same page
at the same time. @product@'s framework takes this into account at every step.
For example, features like platform-generated URLs exist to support Liferay's
ability to serve multiple applications on the same page.

## What is a Component? [](id=what-is-a-component)

Portlets created in Liferay Module Projects are generated as Components. If the
module (sometimes also called a *bundle*) encapsulates pieces of your
application, the component is the object that contains the core
functionality. *Component* is a big broad word which can define many different
things from a Java and OSGi perspective, but the idea that ties all of those
things together is that a component is some object which provides specific
functionality. Components are deployed inside modules, and they provide a
well defined way to build features for your application. 

In this case, you created a Declarative Services (DS) component. With
Declarative Services, you "declare" that an object is a component, and then the
services of that component are registered. If you want to then use that
component, you simply invoke the service as defined in the Service Registry. 

## Deploying the Application [](id=deploying-the-application)

Even though all you've done is generate it, the Guestbook project is ready to be
built and deployed to @product@.  Make sure that your server is running, and if it
isn't, select it in the `Servers` pane and click the start button. After it
starts, drag and drop the Guestbook project to the server.

<!-- Needs an image here showing the drag and drop, because it's not intuitive
unless you see it. -Rich -->

Next, check that your application is available in Liferay. Open a browser,
navigate to your portal ([http://localhost:8080](http://localhost:8080) by
default), and add your application to a page. To add an application to a page,
click on the *Add* button (it looks like a plus sign) and then on
*Applications*. In this list, your application should appear in the Sample
category. Its name should be `guestbook-module Portlet`. You can
change this name by editing the `javax.portlet.display-name` property in your
`GuestbookModulePortlet` class. E.g., you could use

    "javax.portlet.display-name=Guestbook"

to change the display name of your application to `Guestbook`.

This property string should be added to the `property` array defined in the
`@Component` annotation of your `GuestbookModulePortlet` class.

![Figure x: This is the default Liferay homepage. It contains several portlet applications including the initial version of the Guestbook application that you created.](../../../images/default-portlet-application.png)

Now you're ready to jump in and start developing your Guestbook portlet.
