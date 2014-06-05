# Using Service Builder to Generate a Persistence Framework

In the last learning path, you built an application using Liferay's
Model-View-Controller portlet framework. MVC is a great design pattern for web
applications, but it contains only three components: a model for your data, a
view for displaying that data, and a controller for handling page flows. The MVC
design pattern contains no facility for saving, or *persisting* an application's
data model so that it can be retrieved later and be processed or displayed. For 
that, you need more layers in your application: a *persistence* layer and a 
*service* layer. 

The persistence layer is responsible for saving and retrieving your model data. 
The service layer is like a buffer zone between your application and your 
persistence layer: having it gives you the freedom in the future to swap out 
your persistence layer for a different implementation without modifying 
anything but the calls in the service layer. This kind of loose coupling is 
good application design, and Liferay supports that in its frameworks. It does 
that by providing Service Builder, a framework for generating the model, 
service, and persistence layers of your application. 

For more information about how Service Builder works, see the 
[tutorial](../../../tutorials/articles/service-builder/xx-what-is-service- builder.markdown) 
on its design. For now, you'll jump right in and start 
creating your application's service and persistence layers. 

## Creating a service.xml file 

This learning path starts with the development environment and application you 
created in the first learning path. If you haven't followed that path, you'll 
need to create that application first, or download it from [here](somewhere). 
From here on, we'll assume you have Liferay IDE and your application all set up 
and ready to go. 

1. Right-click on your project and select New &rarr; Liferay Service Builder. 

2. For the package path, use the package `com.liferay.docs.guestbook.service`. 

3. For the Namespace, use *GB*. This prefixes the table names in the database 
   so they don't conflict with any other similarly named tables. 

4. Uncheck the *Include sample entity in new file* box and click *Finish*. 

5. You'll now see a form for configuring your services. Open the *Entities* item on
   the left and select the first, blank element. You'll see the Entity form on the
   right. Give it the name *Guestbook*. 

6. Check the *Local service* check box. 

7. Open the Guestbook entity on the left to reveal its attributes. Click the
   *Columns* attribute. 

8. Click the *Add Default Columns* link at the bottom. This adds the primary key
   for this table/entity and some other columns that Liferay uses by default, such
   as the ID of the user who created this entity, the create date, the modified
   date, and more. 

9. Click the *+* button to the right of the list of columns. This lets you add a
   new column. 

10. Give your new column the name *name* and make it a `String` type. 

11. Click the *Entities* link on the left again. You'll see that your Guestbook
    entity is already in the list. 

12. Click the *+* button to the right of the list of entities. This lets you add
    a new entity. 

13. Name your new entity *Entry*, and make sure both Local Service and Remote
    Service are checked. 

14. The Entry entity now appears in the list on the left. Expand it and click
    the *Columns* attribute. 

15. As you did before, click *Add Default Columns*. 

16. Click the *+* button to the right of the list of columns. 

17. Name your new column *name* and make it a `String`. 

18. Use the + button to add two more `String` columns: *email* and *entry*. 

![Figure 1.x: When your entities have been defined, you should have a configuration like this one.](../../images/lds-sb-1.png)

Excellent! You've now defined your entities. Since Service Builder is an
object-relational mapper, at the same time you did that, you also defined your
database tables in one shot. The next thing you have to do is define some
queries for your app. For that, you'll need to understand some Liferay-specific
concepts. 

### Understanding Liferay Portal Concepts 

When you write a Liferay application, you can make it fit into Liferay's overall
framework by giving it context. All of Liferay's built in applications take
advantage of this context. For example, if you add a Wiki to two different
sites, the data in each wiki is different for each site. In other words, one
site doesn't have access to another site's wiki, yet the application they both
use is the same. This is what Liferay calls *scope*. 

Scope in Liferay Portal has a hierarchy, depicted in the illustration below.
Here, you can see two portal instances. Each is a completely separate portal,
with different users, sites, and every other kind of construct Liferay has. Two
users are depicted in the portal instance on the left: one user is a member of
the standalone site, while the other user is a member of an organization, which
itself has a site. A wiki portlet could be placed on a page in either of these
sites, and its data would be different. In other words, it would be *scoped* to
that site. You can change an application's scope to the page, and in that case,
one site could host different wikis on different pages. 

![Figure 1.x: Scope is important in Liferay Portal.](../../images/portal-scope.png)

If you write your application to support this, Liferay handles all of it
automatically for you. You've already taken steps to support scope in your
application by adding the default columns to your entities. 
