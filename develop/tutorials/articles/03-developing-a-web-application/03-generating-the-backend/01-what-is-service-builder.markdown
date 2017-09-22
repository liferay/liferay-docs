# What is Service Builder? [](id=what-is-service-builder-0)

<div class="learn-path-step">
    <p>Generating the Back-end<br>Step 1 of 3</p>
</div>

Now you'll use Service Builder to generate create, read, update, delete, and
find operations for your application. You'll also use Service Builder to 
generate the necessary model, persistence, and service layers for your 
application. Then you can add your application's necessary business logic. 

## Guestbook Application Design [](id=guestbook-application-design)

In the prototype application, you defined a single guestbook's entries and 
displayed them in a list. The full application will handle multiple Guestbooks 
and their entries. To make this work, you'll create two tables in the database: 
one for guestbooks, and one for guestbook entries. 

<!-- Insert screenshot of final application here. --> 

## Service Layer [](id=service-layer)

This application is data-driven. It uses services for storing and retrieving 
data. The application asks for data, and the service fetches it. The application 
can then display this data to the user, who reads or modifies it. If the data is 
modified, the application passes it back to the service, and the service stores 
it. The application doesn't need to know anything about how the service does 
what it does. 

To get started, you'll create a Service Builder project and populate its 
`service.xml` file with all the necessary entities to generate this code: 

1.  In Liferay @ide@, click *File* &rarr; *New* &rarr; *Liferay Module Project*.

2.  Name the project `guestbook`.

3.  Select `service-builder` for the Project Template Name.
 
4.  Click *Next*.

5.  Enter `com.liferay.docs.guestbook` for the *Package Name*.

6.  Click *Finish*.

This creates two modules: an API module (`guestbook-api`) and a service module 
(`guestbook-service`). Next, you'll learn how to use them. 

![Figure 1: Your current project structure.](../../../images/guestbook-service-project.png)
