# Defining Application Permissions [](id=defining-application-permissions)

When you're writing an application, there are almost always parts of the
application or its data that should be protected by permissions. Some users
should access all the functions or data, but most users shouldn't. 

On many platforms, developers have to create the security model themselves. On
@product@, an application security model has been provided for you; you only
need to make use of it. 

Fortunately, no matter what your application does, access to it and to its
content can be controlled with permissions. Read on to learn about Liferay's
permissions system and how add permissions to your application.

The permissions system has three parts: *Resources*, *Actions*, and
*Permissions*. 

**Action**: An operation that can be performed by a user. For example, users can
perform these actions on the Bookmarks application: `ADD_TO_PAGE`,
`CONFIGURATION`, and `VIEW`. Users can perform these actions on Bookmarks entry
entities: `ADD_ENTRY`, `DELETE`, `PERMISSIONS`, `UPDATE`, and `VIEW`. 

**Resource**: A generic representation of any application or entity on which an
action can be performed. Resources are used for permission checking. For
example, resources could include the RSS application with instance ID `hF5f`,
a globally scoped Wiki page, a Bookmarks entry of the site X, and a Message
Boards post with the ID `5052`.

**Permission**: A flag that determines whether an action can be performed
on a resource. In the database, resources and actions are saved in pairs. Each
entry in the `ResourceAction` table contains both the name of a portlet or
entity and the name of an action. For example, the `VIEW` action with respect to
*viewing the Bookmarks application* is associated with the
`com_liferay_bookmarks_web_portlet_BookmarksPortlet` portlet ID. The `VIEW`
actions with respect to *viewing a Bookmarks Folder* or *viewing a Bookmarks
entry* are associated with the `com.liferay.bookmarks.model.BookmarksFolder` and
`com.liferay.bookmarks.model.BookmarksEntry` entities, respectively.

To do permissions, therefore, you define *Users* (Roles) who have *Permission*
to perform *Actions* on *Resources*. User definition is done by administrators
once your application is deployed; developers define resources, actions, and
default permissions. 

You can implement permissions in your applications in four steps that spell the
acronym *DRAC*: 

1. <b>D</b>efine all resources and their permissions. 

2. <b>R</b>egister all defined resources in the permissions system. 

3. <b>A</b>ssociate the necessary permissions with resources.

4. <b>C</b>heck permission before returning resources. 

The next four tutorials show these steps in detail. 

