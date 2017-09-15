# Writing Your First @product@ Application [](id=writing-your-first-liferay-application)

<div class="learn-path-step">
    <p>Developing Your First Portlet<br>Step 1 of 8</p>
</div>

It's easy to get started developing your first @product@ application. Here, 
you'll learn step-by-step how to create your project and deploy your application 
to @product@. Before you know it, you'll have your application deployed 
alongside those that come with @product@. 

Your first application is simple: you'll build a guestbook application that 
looks like this: 

![Figure 1: You'll create this simple application.](../../../images/first-guestbook-portlet.png)

By default, it shows guestbook messages that various users leave on your 
website. To add a message, you click the *Add Entry* button to show a form that
lets you enter and save a message. 

Ready to write your first @product@ application?

## Creating Your First @product@ Application [](id=creating-your-first-liferay-application)

Your first step is to create a *Liferay Module Project*. Modules are the core 
building blocks of @product@ applications. Every application is made from one or 
more modules. Each module encapsulates a functional piece of an application, and 
then multiple modules form a complete application. There's good reason for this: 
modules let you swap out code implementations more or less at will. This makes 
your applications easy to maintain and upgrade.  

These modules are 
[OSGi](https://www.osgi.org/) modules. The OSGi container in @product@ can run 
any OSGi module. Each module is packaged as a JAR file that contains a manifest 
file. The manifest is needed for the container to recognize the module. 
Technically, a module that contains only a manifest is still valid. Of course, 
such a module wouldn't be very interesting. 

Now you'll create your first module. For the purpose of this Learning Path, 
you'll create your modules inside your Liferay Workspace. Follow these 
instructions to create your first Liferay Module Project: 

1.  In the Project Explorer in Liferay @ide@, right click on your Liferay 
    Workspace and select *New* &rarr; *Liferay Module Project*. 

2.  Complete the first screen of the wizard with the following information: 

    - Enter `guestbook-web` for the Project name. 
    - Use the *Gradle* Build type.
    - Select `mvc-portlet` for the Project Template. 

    Click *Next*. 

5.  On the second screen of the wizard, enter `Guestbook` for the component 
    class name, and `com.liferay.docs.guestbook.portlet` for the package name. 
    Click *Finish*. 

Note that it may take a while for @ide@ to create your project, because Gradle 
downloads your project's dependencies for you during project creation. Once this 
is done, you have a module project named `guestbook-web`. The `mvc-portlet` 
template configured the project with the proper dependencies and generated all 
the files you need to get started: 

- The portlet class (in the package you specified)
- JSP files (in `/src/main/resources`)
- Language properties (also in `/src/main/resources`)

![Figure 2: Your new module project appears in your Liferay Workspace's `modules` folder.](../../../images/guestbook-web-project.png)

Your new module project is a *portlet* application. Next, you'll learn exactly 
what a portlet is. 

## What is a Portlet? [](id=what-is-a-portlet)

When you access a web site, you interact with an application. That application
could be simple: it might only show you one piece of information, such as an
article. The application might be complex: you could be doing your taxes,
entering lots of data into an application that calculates whether you owe or
are due a refund. These applications run on a *platform* that provides
application developers the building blocks they need to make applications.

![Figure 3: Many Liferay applications can run at the same time on the same page.](../../../images/portlet-applications.png)

@product@ provides a platform that contains common features needed by today's
applications, including user management, security, user interfaces, services, 
and more. Portlets are one of those basic building blocks. Often a web 
application takes up the entire page. If you want, you can do this with 
applications in @product@ as well. Portlets, however, allow @product@ to serve 
many applications on the same page at the same time. @product@'s framework takes 
this into account at every step. For example, features like platform-generated 
URLs exist to support Liferay's ability to serve multiple applications on the 
same page. 

## What is a Component? [](id=what-is-a-component)

Portlets created in Liferay Module Projects are generated as *Components*. If 
the module (sometimes also called a *bundle*) encapsulates pieces of your 
application, the component is the object that contains the core functionality. A 
Component is an object that is managed by a component framework or container. 
Components are deployed inside modules, and they're created, started, stopped,
and destroyed as needed by the container. What a perfect model for a web
application! It can be made available only when needed, and when it's not, the
container can make sure it doesn't use any resources needed by other components. 

In this case, you created a Declarative Services (DS) component. With 
Declarative Services, you declare that an object is a component, and you define 
some data about the component so the container knows how to manage it. A default 
configuration was created for you; you'll examine it later. 

## Deploying the Application [](id=deploying-the-application)

Even though all you've done is generate it, the `guestbook-web` project is ready 
to be built and deployed to @product@. Make sure that your server is running, 
and if it isn't, select it in @ide@'s Servers pane and click the start button. 
After it starts, drag and drop the `guestbook-web` project from the Project 
Explorer to the server. If this is your first time starting @product@, you'll go 
through a short wizard to set up your server. In this wizard, make sure you use 
the default database (Hypersonic). Although this database isn't intended for 
production use, it works fine for development and testing. 

<!-- Needs an image here showing the drag and drop, because it's not intuitive
unless you see it. -Rich -->

Next, check that your application is available in @product@. Open a browser,
navigate to your portal ([http://localhost:8080](http://localhost:8080) by
default), and add your application to a page. To add an application to a page,
click the *Add* button in the upper right hand corner (it looks like a plus 
sign), and then select *Applications*. In the Applications list, your 
application should appear in the Sample category. Its name should be 
`guestbook-web-module Portlet`. 

![Figure 4: This is the default Liferay homepage. It contains several portlets, including the initial version of the Guestbook application that you created.](../../../images/default-portlet-application.png)

Now you're ready to jump in and start developing your Guestbook portlet. 
