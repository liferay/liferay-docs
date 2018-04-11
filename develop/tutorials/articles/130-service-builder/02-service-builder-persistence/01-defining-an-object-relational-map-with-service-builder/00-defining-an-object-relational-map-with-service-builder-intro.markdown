# Defining an Object-Relational Map with Service Builder [](id=defining-an-object-relational-map-with-service-builder)

In this tutorial, you'll learn how to define an object relational map for your
application so that it can persist data. As an example, you'll examine the
existing Liferay Bookmarks application that uses Service Builder.

The Bookmarks application is an example portlet project that an organization can
use to bookmark assets in Liferay. The application defines two entities, or
model types, to represent an organization's bookmarks and their folders. These
entities are called *bookmark entries* and *bookmark folders*. Since a bookmark
must have a folder (even if it's a root folder), the entry entity references a
folder entity as one of its attributes. 

You can design your application's modules anyway you like, but for the Bookmarks
application, its Java sources reside in the `bookmarks-api`,
`bookmarks-service`, and `bookmarks-web` modules. Notice the
`BookmarksAdminPortlet.java` and `BookmarksPortlet.java` files in the
`com.liferay.bookmarks.web.portlet` package in the `bookmarks-web` module. These
portlet classes extend Liferay's `MVCPortlet` class. They act as the controllers
in the MVC pattern. These classes contain the business logic that invokes the
Service Builder generated bookmarks services that you'll learn how to create in
this section. The application's view layer is implemented in the JSPs in the
`bookmarks-web/src/main/resources/META-INF/resources` folder.

You can learn how to generate a generic modular application from scratch that
includes the `*api`, `*service`, and `*web` modules by default in the
[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)
tutorial. This tutorial assumes you've assembled your application's modules
similarly to the linked tutorial above. Be sure to also visit the
[Fundamentals](/develop/tutorials/-/knowledge_base/7-0/fundamentals)
tutorial for additional info on the `*api`, `*service`, and `*web` modules.

The first step in using Service Builder is to define your model classes and
their attributes in a `service.xml` file. This file's location typically resides
in the root folder of the `*-service` module, although you can configure your
build tool to recognize it from other directories. In Service Builder
terminology, your model classes are called entities. For example, the Bookmarks
application has two entities: `BookmarksEntry` and `BookmarksFolder`. The
requirements for each of these entities are defined in the `bookmarks-service`
module's
[service.xml](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/service.xml)
listed in the `<column />` elements. 

Once Service Builder reads the `service.xml` file, you can define your entities.
Liferay @ide@ makes it very easy to define entities in your application's
`service.xml` file. To define a custom entity, follow these steps:

1. Create the `service.xml` file in your project's `*-service` module. It
   resides in the root folder of that module, if one does not already exist
   there.

2. Define global information for the service.

3. Define service entities. 

4. Define the columns (attributes) for each service entity.

5. Define relationships between entities.

6. Define a default order for the entity instances to be retrieved from the
   database. 

7. Define finder methods that retrieve objects from the database based on
   specified parameters.

You'll examine these steps in detail next, starting with creating a
`service.xml` file.
