---
header-id: what-is-service-builder-0
---

# What is Service Builder?

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Generating the Back-End</p><p>Step 1 of 3</p>
</div>

Now you'll use Service Builder to generate your application's Model, Service,
and Persistence layers. Then you can add your application's necessary business
logic. 

## Guestbook Application Design

The Guestbook application handles multiple Guestbooks and their entries. To make
this work, you'll create two tables in the database: one for guestbooks and one
for guestbook entries. 

![Figure 1: When you're done, the Guestbook supports multiple guestbooks and makes use of many Liferay features.](../../../images/guestbook-final.png)

## Service Layer

This application is data-driven. It uses services for storing and retrieving 
data. The application asks for data, and the service fetches it from the
persistence layer. The application can then display this data to the user, who
reads or modifies it. If the data is modified, the application passes it back
to the service, which calls the persistence layer to store it. The application
doesn't need to know anything about how the service does what it does. 

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

![Figure 2: Your current project structure.](../../../images/guestbook-service-project.png)
