# What is Service Builder?

What you've got so far is a prototype. You're using Liferay's
Model-View-Controller portlet framework. MVC is a great design pattern for web
applications, because it splits your application into three parts (the
model, the view, and the controller), which lets you swap out those parts if
necessary. 

To these three layers of your application are added a *persistence* layer and a
*service* layer. So far, you've created a rudimentary persistence layer using
Portlet Properties, just to get the prototype working. Since this isn't a
long-term solution, you'll now replace that layer by persisting your guestbooks
and their entries to a database. 

*Service Builder* is Liferay's code generation tool for defining object models
and mapping those models to SQL databases. By defining your model in a single
XML file, with Service Builder you can generate your object model (the M in
MVC), your service layer, and your persistence layer all in one shot. At the
same time, you can generate web services (more on that later) and support every
database Liferay supports. 

Now you will use Service Builder to generate create, read, update, delete, and
find operations for your application. You'll also use Service Builder to
generate the necessary model, persistence, and service layers for your
application all using one XML file. Then you can add the necessary business
logic for your application.

## Fitting your Application into Liferay

Before you dive into service, let's take a closer look at the context that 
you're working it. When you write a Liferay application, you can make it fit 
into Liferay’s overall framework. All of Liferay’s core application use 
concepts like sites, users, pages, and scopes to organize data. For example, if 
you add a Wiki to two different sites, the data in each wiki is different for 
each site. In other words, one site doesn’t have access to another site’s wiki, 
yet the application they both use is the same. This is what Liferay calls scope.

Scope in Liferay Portal has a hierarchy, depicted in the illustration below. 
Here, you can see a portal instance. Two users are depicted in the portal 
instance on the left: one user is a member of Site A, while the other user is a 
member of Site B. A wiki portlet could be placed on a page in either of these 
sites, and its data would be different. In other words, it would be scoped to 
that site. You can change an application’s scope to the page, and in that case, 
one site could host different wikis on different pages.

![Figure X: Sites, users, and scope in Liferay.](../../../images/sites-users.png)

## Service Layer

This application is a data-driven application. It uses services for storing and
retrieving data. The application asks for data, and the service fetches it. The 
application can then display this data to the user, who reads it or modifies 
it. If the data is modified, the application passes it back to the service, and 
the service stores it. The application doesn't need to know anything about how 
the service does what it does.

You'll create a Service Builder project and populate a`service.xml` file with all 
the necessary entities to generate this code.

1.  In @ide@, click *File* &rarr; *New* &rarr; *Liferay Module Project*.

2.  Name the project `guestbook`.

3.  Select `service-builder` for the Project Template Name.
 
4.  Click *Next*.

5.  Enter `com.liferay.docs.guestbook` for the *Package Name*.

6.  Click *Finish*.

This creates two modules: an API module and a service module. Next, you'll learn
how to use them.



