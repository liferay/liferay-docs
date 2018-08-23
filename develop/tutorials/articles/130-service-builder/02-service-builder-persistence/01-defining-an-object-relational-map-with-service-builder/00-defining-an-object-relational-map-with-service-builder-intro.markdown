# Defining an Object-Relational Map with Service Builder [](id=defining-an-object-relational-map-with-service-builder)

In this tutorial, you'll learn how to define an object relational map so your
application can persist data. As an example, you'll examine the existing
Liferay Bookmarks application that uses Service Builder.

The Bookmarks application bookmarks assets in Liferay. The application defines
two entities, or model types, to represent an organization's bookmarks and
their folders. These entities are called *bookmark entries* and *bookmark
folders*. Since a bookmark must have a folder (even if it's a root folder), the
entry entity references a folder entity as one of its attributes. 

The Bookmarks application's source code resides in the `bookmarks-api`,
`bookmarks-service`, and `bookmarks-web` modules. Notice the
`BookmarksAdminPortlet.java` and `BookmarksPortlet.java` files in the
`com.liferay.bookmarks.web.portlet` package in the `bookmarks-web` module.
These portlet classes extend Liferay's
[`MVCPortlet` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html).
They act as the controllers in the MVC pattern. These classes contain the
business logic that invokes the Service Builder generated bookmarks services
that you'll learn how to create in this section. The application's view layer is
implemented in the JSPs in the
`bookmarks-web/src/main/resources/META-INF/resources` folder.

This tutorial assumes your application has these types of modules :

- `*-api`: Service interfaces
- `*-service`: Service implementations
- `*-web`: Portlet and controller

The parent folder of these modules is the *application folder*. The
[Service Builder project template](/develop/reference/-/knowledge_base/7-1/using-the-service-builder-template)
is available for creating the `*-api` and `*-service` modules. Client UI project
templates such as the
[MVCPortlet template](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template)
are available for creating the `*-web` module. You can create projects from
both templates using either @ide@ or Blade. 

The first step in using Service Builder is to define your model classes and
their attributes in a `service.xml` file. This file typically resides
in the `*-service` module's root folder, although you can configure your
build tool to recognize it in other folders. Service Builder
terminology calls model classes *entities*. For example, the Bookmarks
application has two entities: `BookmarksEntry` and `BookmarksFolder`. The
requirements for each are defined in the `bookmarks-service` module's
[service.xml](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/service.xml)
listed in the `<column />` elements. 

Once Service Builder reads the `service.xml` file, you can define your entities.
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)
makes it easy to define entities in your application's `service.xml` file.
Follow these steps:

1. [Create the `service.xml` file.](/develop/tutorials/-/knowledge_base/7-1/creating-the-service-xml-file)

2. [Define global information for the service.](/develop/tutorials/-/knowledge_base/7-1/defining-global-service-information)

3. [Define service entities.](/develop/tutorials/-/knowledge_base/7-1/defining-service-entities) 

4. [Define the columns (attributes) for each service entity.](/develop/tutorials/-/knowledge_base/7-1/defining-the-columns-attributes-for-each-service-entity)

5. [Define relationships between entities.](/develop/tutorials/-/knowledge_base/7-1/defining-relationships-between-service-entities)

6. [Define a default order for the entity instances to be retrieved from the database.](/develop/tutorials/-/knowledge_base/7-1/defining-ordering-of-service-entity-instances) 

7. [Define finder methods that retrieve objects from the database based on specified parameters.](/develop/tutorials/-/knowledge_base/7-1/defining-service-entity-finder-methods)

Each step is explained in detail. Start with creating a `service.xml` file.
