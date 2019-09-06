---
header-id: creating-the-web-project
---

# Creating the Web Project

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 1 of 11</p>
</div>

Your first step is to create another Liferay Module Project. Modules are the
core building blocks of @product@ applications. Every application is made from
one or more modules. Each module encapsulates a functional piece of an
application. Multiple modules form a complete application. 

Modules can be web modules or [OSGi](https://www.osgi.org/) modules. Since
you'll be creating a Liferay MVC Portlet, you'll create an OSGI module. The OSGi
container in @product@ can run any OSGi module. Each module is packaged as a JAR
file that contains a manifest file. The manifest is needed for the container to
recognize the module. Technically, a module that contains only a manifest is
still valid. Of course, such a module wouldn't be very interesting. 

You already created Service Builder modules. Now you'll create your MVC Portlet
module. For the purpose of this tutorial, you'll create your modules inside
your Liferay Workspace. 

1.  In Liferay @ide@, select *File* &rarr; *New* &rarr; *Liferay Module
    Project*. 

2.  Complete the first screen of the wizard with the following information: 

    ![Figure 1: Complete the New Module Project wizard.](../../../images/new-module-project.png)

    - Enter `guestbook-web` for the Project name. 
    - Use the *Gradle* Build type.
    - The Liferay version is *7.2*.
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

Your new module project is a *portlet* application. You'll learn what that is in
a moment, but first there's some housekeeping to do. 

In larger projects, it is important to have all of your files and modules well 
organized. Since the `guestbook-web` module really goes with your Service
Builder modules, it should be in the `guestbook` folder.

1.  In the *Project Explorer*, right-click on `guestbook-web` and select
    *Move*.

2.  In the window that appears, click *Browse*, choose the `guestbook` 
    folder and then click *OK*.
 
Your `guestbook-web` folder now appears in the structure with the other modules.

![Figure 2: After you move it, all of your modules are in the same folder..](../../../images/guestbook-refactor.png)

| **Note:** Sometimes Eclipse refuses to move your project. If that happens,
| close Eclipse, use your operating system's file manager to move the
| `guestbook-web` folder into the `guestbook` folder, and then restart Eclipse. 

You're now ready to begin writing your front-end, but first some explanation is
in order. 

## What is a Portlet?

Web applications can be simple or complex: they might display an article or
calculate your taxes. These applications run on a *platform* that provides
application developers the building blocks they need to make applications.

![Figure 3: Many Liferay applications can run at the same time on the same page.](../../../images/portlet-applications.png)

@product@ provides a platform that contains common features needed by today's
applications, including user management, security, user interfaces, services, 
and more. Portlets are one of those basic building blocks. Often a web 
application takes up the entire page. Portlets can do this or share the page
with many applications at the same time. @product@'s framework takes this into
account at every step. 

## What is a Component?

Liferay MVC Portlets are *Components*. If a module (sometimes also called
a *bundle*) encapsulates pieces of your application, a component is the object
that contains the core functionality. A Component is managed by a component
framework or container. Components are deployed inside modules, and they're
created, started, stopped, and destroyed as needed by the container. What
a perfect model for a web application! It can be made available only when
needed, and when it's not, the container can make sure it doesn't use resources
needed by other components. 

In this case, you created a Declarative Services (DS) component. With
Declarative Services, you declare that an object is a component, and you define 
data about the component so the container knows how to manage it. A default 
configuration was created for you; you'll examine it later. 

## Deploying the Application

Even though all you've done is generate it, the `guestbook-web` project is ready
to be built and deployed.

1.  Make sure that your server is running, and if it isn't, select it in 
    @ide@'s Servers pane and click the start button (![Start Server](../../../images/icon-start-server.png)).

2.  After it starts, drag and drop the `guestbook-web` project from the Project
    Explorer to the server.
 
    ![Figure 4: Drag and drop the module.](../../../images/deploy-module.gif)

3.  Open a browser and navigate to @product@
    ([http://localhost:8080](http://localhost:8080) by default).

    If this is your first time starting @product@, you'll go through a short 
    wizard to set up your server. In this wizard, make sure you use the default 
    database (Hypersonic). Although this database isn't intended for production 
    use, it works fine for development and testing. 

4.  Click the menu button at the top left and select *Site Builder* &rarr;
    *Pages*. 

5.  Click the ![add](../../../images/icon-add.png) button at the top right to
    add a Public Page. 

6.  Choose *Widget Page* and name it *Guestbook*. 

7.  Choose the *One Column* layout and click *Save*. 

8.  Click *Go to Site* on the left, and then navigate to your new Guestbook
    page. 
 
9.  Click *Add* 
    (![Add Widget](../../../images/icon-add-app.png)) in the upper right hand 
    corner.

5.  Select *Widgets*. In the Applications list, your application appears in the
    Sample category. Its name is `Guestbook`. 

![Figure 5: This is your new page with the Guestbook application that you created.](../../../images/default-guestbook-application.png)

Now you're ready to jump in and start developing your Guestbook portlet. 
