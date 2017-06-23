# Writing Your First Liferay Application [](id=writing-your-first-liferay-application-0)

It's easy to get started with your first Liferay application. We'll guide you
through the step-by-step process of creating your project and deploying your
application to Liferay. Before you know it, you'll have your application
deployed alongside of the applications that come with Liferay.

Your first application is simple: you'll build a guestbook application that
looks like this:

![Figure x: You'll create this simple application.](../../../images/first-guestbook-portlet.png)

By default, it shows guestbook messages that various users have left on your
website. To add a message, you click the *Add Entry* button to show a form that
lets you enter and save a message.

Ready to write your first Liferay application?

## Creating Your First Liferay Application [](id=creating-your-first-liferay-application)

Let's jump right in and create your first project. We'll create a *Liferay 
Module Project.* Modules are the core building blocks of Liferay development. 
Every application is made from one or more modules. The goal of a module is to 
have each functional piece of an application encapsulated in a separate module. 
All of the code for each part of the application is contained within each 
discrete module, and you could swap out implementations of any module, more or 
less at will. Each module is bundled up and package in a JAR file. The JAR can 
then be deployed through your application server. 

As one example, if you use the Model View Controller design pattern, you could 
create one module to encapsulate the Model, one for the View, and one for the 
Controller. A new implementation of the View (or Model or Controller) could be 
swapped in at any point, without needing to make any changes to the Model or 
Controller, and without needing to repackage or redeploy them. 

A module can contain any combination of JSPs, Java classes, properties, or any 
other kind of binary or text data. The module will also need a manifest file to 
let everyone know exactly what is inside of it. In fact, technically speaking 
your module would be perfectly valid if it contained nothing but a manifest.
 
You already created your Liferay workspace so any Liferay projects you create 
will be created inside of that workspace. Follow the instructions below to
create your Liferay Module Project.

1. Right click on your workspace.
2. Select `New` &rarr; `Liferay Module Project`
3. Enter `Guestbook` for the Project name
4. Enter `com.liferay.docs.guestbook` for the package name.
5. Use the `gradle-module` Build type.
6. Select the `mvc-portlet` for Project Template Name.


This creates a project named `Guestbook`. The `mvcportlet` 
template applied some Liferay enhancements to a standard module project. 
Liferay IDE created a portlet class named NAME inside the package package 
`com.liferay.docs.guestbook` in your project's `src/main/java` folder. Your
JSP files and language properties file were also generated in your project's 
`src/main/resources` folder.

## What is a Portlet? [](id=what-is-a-portlet)

When you access a web site, you interact with an application. That application
might be simple: it might only show you one piece of information, such as an
article. The application might be complex: you could be doing your taxes
online, inputting lots of data into an application that calculates whether you
owe or are due a refund. These applications run on a *platform* that provides
application developers the building blocks they need to make applications.

![Figure x: Many Liferay applications can run at the same time on the same page.](../../../images/portlet-applications.png)

@product@s is a platform on which you can develop many types of applications. 
Portlets are a common type of application that you can develop in Liferay.
@product@ can serve many applications on the same page at the same time. Often 
a web application takes up the entire page. If you want, you can do this with 
Liferay as well. But Liferay has the added benefit of being able to run many 
applications on the same page. Liferay's framework takes this into account at 
every step. For example, features like platform-generated URLs exist to support 
Liferay's ability to serve multiple applications on the same page.

## What is a Component? [](id=what-is-a-component)

Portlets created in Liferay Module Projects are generated as Components. If the 
module is what surrounds and binds the pieces of your application, the 
component is the object which contains the actual functionality. Component is a 
big broad word which can define many different things from a Java and OSGi 
perspective, but the idea that ties all of those things together is that a 
component is some object which provides a specific functionality. Components 
are deployed inside of Modules, and they provide a well defined way to build 
features for your application. 

In this case in particular, you created a Declarative Services (DS) component. 
With Declarative Services, you "declare" that an object is a component, and 
then the services of that component are registered. If you want to then use 
that component, you simply invoke the service as defined in the Service 
Registry. 

## Deploying the Application [](id=deploying-the-application)

Even though all you've done is generate it, the Guestbook project that you
created in the previous section is ready to be built and deployed to Liferay.
Make sure that your server is running, and if it isn't, select it in the 
`Servers` pane and click the start button. After Liferay starts, drag and drop 
the `Guestbook` project to the server.

Next, check that your application is available. Open a browser,
navigate to @product@ ([http://localhost:8080](http://localhost:8080) by
default), and add your application to a page. To add an application to a page,
click on the *Add* button (it looks like a plus sign) and then on
*Applications*. In this list, your application should appear in the Sample
category. Its name is `Guestbook Portlet`. When you add it to the page, it looks
like the figure below. 

![Figure x: This is the default @product@ home page. It contains the Hello World portlet applications and the initial version of the Guestbook application that you created.](../../../images/default-portlet-application.png)

Now you're ready to jump in and start developing your Guestbook portlet.
