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
XML file, you can generate your object model (the M in MVC), your service layer,
and your persistence layer all in one shot. At the same time, you can generate
web services (more on that later) and support every database Liferay supports. 

![Figure x: Service Builder generates the shaded layers of your application.](../../../images/application-layers.png)

Now you'll use Service Builder to generate create, read, update, delete, and
find operations for your application. You'll also use Service Builder to
generate the necessary model, persistence, and service layers for your
application all using one XML file. Then you can add the necessary business
logic for your application. 

## Guestbook Application Design

In the prototype, you defined Guestbook entries and displayed them in a list.
The full application can handle multiple Guestbooks and their entries. To make
this work, you'll create two tables in the database: one to store Guestbooks,
and one to store Guestbook entries. 

<!-- Insert screenshot of final application here. --> 

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

![Figure X: Your current project structure.](../../../images/guestbook-service-project.png)

This creates two modules: an API module and a service module. Next, you'll learn
how to use them.

