# Using Service Builder to Generate a Persistence Framework [](id=using-service-builder-to-generate-a-persistence-fr)

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
[tutorial](/develop/tutorials/-/knowledge_base/6-2/what-is-service-builder) 
on its design. For now, you'll jump right in and start 
creating your application's service and persistence layers. 

## Creating a service.xml file [](id=creating-a-service-xml-file)

This learning path starts with the development environment and application you 
created in the first learning path. If you haven't followed that path, you'll 
need to create that application first, or download it from [here](https://github.com/liferay/liferay-docs/tree/master/develop/learning-paths/mvc/code/learning-sdk). 
From here on, we'll assume you have Liferay IDE and your application all set up 
and ready to go. 

1. Right-click on your project and select *New* &rarr; *Liferay Service
   Builder*. 

2. For the package path, use the package `com.liferay.docs.guestbook`. 

3. For the Namespace, use *GB*. This prefixes the table names in the database 
   so they don't conflict with any other similarly named tables. 

4. Uncheck the *Include sample entity in new file* box and click *Finish*. 

5. You'll now see a form for configuring your services. Open the *Entities* item
   on the left and select the first, blank element. You'll see the Entity form
   on the right. Name the entity *Guestbook*. 

6. Check the *Local service* check box. 

7. Open the Guestbook entity on the left to reveal its attributes. Click the
   *Columns* attribute. 

8. Click the *Add Default Columns* link at the bottom. This adds the primary key
   for this table/entity and some other columns that Liferay uses by default,
   such as the ID of the user who created this entity, the create date, the
   modified date, and more. 

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

18. Use the + button to add two more `String` columns: *email* and *message*. 

19. Use the + button to add a column of type *long* to hold the foreign key to
    the Guestbook entity. Call this column *guestbookId*. 

![Figure 1: When your entities have been defined, you should have a configuration like this one.](../../images/lds-sb-1.png)

Excellent! You've now defined your entities. Since Service Builder is an
object-relational mapper, at the same time you did that, you also defined your
database tables in one shot. 

Now you'll want to look at your entities carefully. When you build forms for
them, you'll have to do validation on some of the fields. For example, some
fields are requirements for filling out the form. Which fields from which
entities are required? The first one that jumps to mind is the `name` field on
both the `Guestbook` and the `Entry` entity. But the `Entry` entity also has
some other required fields: what's the point of submitting a guestbook entry
without the message? And you may decide you want everyone to supply an email
address and their name, to discourage abuse of your guestbook. Because of that,
it's a good idea to make all these fields required. 

Liferay has great facilities for field validation which are covered later; right
now, you'll just prepare yourself to use them by defining custom `Exception`
classes for your entities. 

1. On the outline on the left, scroll down to the bottom. Click on *Exceptions*,
   the second item from the bottom. 

2. Click the *+* button to add an exception. Call it `GuestbookName`. 

3. Use the same procedure to add `EntryName`, `EntryMessage`, and `EntryEmail`
   exceptions. 

Great! The next thing you have to do is define some queries for your app. For
that, you'll need to understand some Liferay-specific concepts. 

### Understanding Liferay Portal Concepts [](id=understanding-liferay-portal-concepts)

When you write a Liferay application, you can make it fit into Liferay's overall
framework by providing a context for it. All of Liferay's built in applications
take advantage of this context. For example, if you add a Wiki to two different
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

![Figure 2: Scope is important in Liferay Portal.](../../images/portal-scope.png)

If you write your application to support this, Liferay handles all of it
automatically for you. You've already taken steps to support scope in your
application by adding the default columns to your entities. Those default
columns included the [Company ID](/encyclopedia/-/wiki/Main/Company+ID), 
the [Group ID](/encyclopedia/-/wiki/Main/Group+ID),
the [User ID](/encyclopedia/-/wiki/Main/User+ID), the
user name, and some metadata, such as the create and modify dates. 

The next thing you have to do is make sure your application's queries filter by
the Group ID. Generating queries, or *finders*, is what you'll do next. 

### Finding Previously Stored Data [](id=finding-previously-stored-data)

Assuming users add guestbook entries to your application, how should they be
found? To determine that, you'll need to look at your data design. 

![Figure 3: Your data design generally dictates the queries you can build.](../../images/guestbook-tables.png)

As you can see, you have two tables. The first stores Guestbooks themselves:
besides the default fields, it has only two: a primary key and a name. The
second table stores Guestbook Entries: each entry has a foreign key going back
to the guestbook to which it belongs. Since you're designing this application to
take advantage of Liferay Portal's concept of scope, all queries should be by
the `groupId`. It's easiest to tackle the queries for the simpler table, the
Guestbook table, first. 

1. In the Guestbook entity, click the *Finders* link. 

2. On the right side of the screen, click the *+* button to create a new Finder.
   Call it *GroupId*. For a return type, use *Collection*. In case you're
   wondering, yes, this is a Java Collection. 

3. Notice that a new GroupId node has now been created under Finders on the
   left. Expand it to reveal the nodes beneath it. 

4. Click the *Finder Columns* node. 

5. On the right side of the screen, click the *+* button to create a column.
   Call it *groupId*. 

6. Save your Service Builder configuration by typing Ctrl-S. 

Excellent! You've just created a finder that finds Guestbooks by the Group ID!
Now you'll want to create another one for finding all Guestbook entries, given a
particular Guestbook ID and a Group ID. This lets you show the entries from the
database in the same way you showed them from portlet preferences in the last
learning path. 

1. In the Entry entity, click the *Finders* link. 

2. On the right side of the screen, click the *+* button to create a new Finder.
   Call it *G_G* and specify its return type as *Collection*. Why in the world
   would you want to call it G_G? It's a Liferay convention. If you're creating
   a finder that uses two (or more) columns, it's named for the first letter of
   each column separated by underscore characters. This way, you can easily tell
   which fields the finder works on, and the method name Service Builder
   generates stays small. 

3. Notice that a new G_G node has now been created under Finders on the left.
   Expand it to reveal the nodes beneath it. 

4. Click the *Finder Columns* node. 

5. On the right side of the screen, click the *+* button to create a column.
   Call it *groupId*. 

6. Click the *+* button to create another column. Call it *guestbookId*. 

7. Save your Service Builder configuration by typing Ctrl-S. 

You've now defined the finders you'll need for your application. Note that you
haven't written a single line of SQL, Java, or even XML code. Next, you'll
generate all of that. 

## Generating Model, Service, and Persistence Layers [](id=generating-model-service-and-persistence-layers)

Once you've created your `service.xml` file, you're ready to generate everything
else you need to access your database. Note, however, that at the bottom of the
`service.xml` editor are three tabs. You've been using the Overview tab. The
Diagram tab lets you see a diagram of your database tables. The Source tab lets
you see the XML source code you've been generating using the GUI editor. 

Note that your `service.xml` file could just as easily have been written to the
specifications of the DTD in the header. Liferay IDE/Developer Studio just makes
it a lot easier to write. Once you've examined the source code to your
`service.xml` file, go back to the Overview tab. 

In the top right corner of the Service Builder editor is a button that looks
like binary numbers superimposed on a page. If you roll your mouse over this
button, a tooltip pops up which says that this is the Build Services button.
Click it. At this point, Liferay's Service Builder utility runs. When it
completes, check out your project's `src` folder. 

![Figure 4: Service Builder generates a number of packages containing your model, service, and persistence layers.](../../images/lds-service-builder-packages.png)

Note that your project now has errors. It's because the model class, `Entry`,
that you created in the last tutorial, was replaced. As noted above, Service
Builder, using just that one `service.xml` file, generates your model, service,
and persistence layers. Thankfully, the location of the error in the
`GuestbookPortlet` class is in code that you'll replace, now that you have a
proper service layer. 

Speaking of that service layer, what exactly happened here? Why are there so
many classes? We're glad you asked. Service Builder is based on a design
philosophy called [loose coupling](https://en.wikipedia.org/wiki/Loose_coupling). 
At a macro level, you have three layers of your application: the model, the
service, and the persistence layers. The application is designed in such a way
that you can swap out the persistence layer with little to no change in the
model and service layers. 

![Figure 5: At a macro level, Service Builder generates three layers.](../../images/application-layers.png)

At a micro level, each layer is implemented using Java Interfaces and various
implementations of those interfaces. Rather than have one `Entry` class that
represents your model, Service Builder has generated a system of classes that
include an `Entry` interface, an `EntryBaseImpl` abstract class for Service
Builder to manage, and an `EntryImpl` class that you can customize. This gives
you the flexibility of being able to customize your model, while still allowing
Service Builder to generate the code it needs. That's why Service Builder is a
code generator for code generator haters: it generates the stuff that's tedious
to write, while giving you the freedom to customize anything you want. 

For further information about Service Builder, what it's built on, and how it
works, please read the [What is Service Builder?](/develop/tutorials/-/knowledge_base/6-2/what-is-service-builder)
tutorial. 

Congratulations! You've now used Service Builder to generate a service and
persistence framework for your application. Next, you'll integrate that
framework into your application so you can use it. 
