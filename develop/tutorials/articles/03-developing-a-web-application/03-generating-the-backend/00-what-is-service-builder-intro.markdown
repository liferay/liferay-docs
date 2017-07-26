# What is Service Builder?

<div class="learn-path-step">
    <p>Generating the Backend<br>Step 1 of 3</p>
</div>

So far, you have a prototype application that uses Liferay's 
Model-View-Controller (MVC) portlet framework. MVC is a great design pattern for 
web applications because it splits your application into three parts (the model, 
the view, and the controller). This lets you swap out those parts if necessary. 

A *persistence* layer and a *service* layer are added to these three parts of 
your application. To get the prototype working, you used Portlet Properties to 
create a rudimentary persistence layer. Since this isn't a long-term solution, 
you'll now replace that layer by persisting your guestbooks and their entries to 
a database. 

*Service Builder* is Liferay's code generation tool for defining object models
and mapping those models to SQL databases. By defining your model in a single
XML file, you can generate your object model (the M in MVC), your service layer,
and your persistence layer all in one shot. At the same time, you can generate
web services (more on that later) and support every database @product@ supports. 

![Figure x: Service Builder generates the shaded layers of your application.](../../../images/application-layers.png)

Now you'll use Service Builder to generate create, read, update, delete, and
find operations for your application. You'll also use Service Builder to 
generate the necessary model, persistence, and service layers for your 
application. Then you can add your application's necessary business logic. 

## Guestbook Application Design

In the prototype application, you defined a single guestbook's entries and 
displayed them in a list. The full application will handle multiple Guestbooks 
and their entries. To make this work, you'll create two tables in the database: 
one for guestbooks, and one for guestbook entries. 

<!-- Insert screenshot of final application here. --> 

## Service Layer

This application is a data-driven. It uses services for storing and retrieving 
data. The application asks for data, and the service fetches it. The application 
can then display this data to the user, who reads or modifies it. If the data is 
modified, the application passes it back to the service, and the service stores 
it. The application doesn't need to know anything about how the service does 
what it does. 

To get started, you'll create a Service Builder project and populate its 
`service.xml` file with all the necessary entities to generate this code: 

1.  In @ide@, click *File* &rarr; *New* &rarr; *Liferay Module Project*.

2.  Name the project `guestbook`.

3.  Select `service-builder` for the Project Template Name.
 
4.  Click *Next*.

5.  Enter `com.liferay.docs.guestbook` for the *Package Name*.

6.  Click *Finish*.

This creates two modules: an API module (`guestbook-api`) and a service module 
(`guestbook-service`). Next, you'll learn how to use them. 

![Figure X: Your current project structure.](../../../images/guestbook-service-project.png)
