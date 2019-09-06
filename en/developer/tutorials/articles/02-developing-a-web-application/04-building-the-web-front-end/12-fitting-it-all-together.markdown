---
header-id: fitting-it-all-together
---

# Fitting it All Together

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 11 of 11</p>
</div>

You've created a complete data-driven application from the back-end to the 
display. It's a great time to review how everything connects together. 

## The Back-End

The first thing you did was generate back-end services for your application
using Liferay's object-relational mapper, Service Builder. 

![Figure 1: Service Builder makes generating your database entities and your Java objects a snap.](../../../images/service-builder-guestbook.png)

Using a single `service.xml` file, you generated your object model, SQL to
create your tables, a persistence layer to perform all CRUD operations on your
data, and a service layer for your business logic. Then you added business logic
to your service layer to expose the persistence layer's functionality according
to your business rules. 

With all of that ready, it was time to build a front-end client. 

## The Front-End

Once you completed your back-end, you decided to develop a web front-end
using Liferay's MVC Portlet framework. You generated a Liferay MVC Portlet
project and used its Model View Controller development paradigm to implement
a user interface for your back-end functionality. 

![Figure 2: The controller directs page flow in an MVC Portlet application.](../../../images/guestbook-mvc-diagram-1.png)

First, you created a default view in `view.jsp`. You created a button there that
links to `edit_entry.jsp`, which is used for both adding and editing Guestbook
entries (though you haven't implemented editing yet). Here, you created a form
to capture information from Users adding Guestbook entries. The form's Action
URL directs processing to your controller's portlet action of the same name.
This action extracts the data from the form (in the `ActionRequest` object) and
passes it to the business logic you created in your service layer. 

After the business logic runs, your controller passes processing back to
`view.jsp`, where the new Guestbook entry is displayed. 

Now that you've built the application and you can see a clear picture of how it 
all works, it's time to test it. 

## Deploying and Testing the Application

1.  Drag and drop the `guestbook-api` module onto the server.

2.  Drag and drop the `guestbook-service` module onto the server.

3.  Look for the STARTED messages from the console. 

4.  Go to your @product@ instance at `localhost:8080` in your browser to test 
    your updated application. 

5.  Like you did before, use your administrative account to remove the Guestbook
    from the page and add it again. 

6.  Click *Add Entry*.

7.  Enter a *Name*, *Message*, and *Email Address*.

8.  Click *Submit*.

9.  Verify that your entry appears.

![Figure 3: Your first guestbook and entry appears. Nice job!](../../../images/guestbook-entry-test.png)

## What's Next?

You've created a working web application and deployed it on @product@. If you've 
created web applications before, though, you know that it's missing some 
important features: security, front-end validation, and an interface for 
administrators to create multiple guestbooks per portlet. In the next section, 
you'll begin adding these features. 

