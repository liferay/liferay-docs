# Data Handlers

A common requirement for many data drive applications is the ability to import
and export data. This *could* be accomplished by accessing your database
directly and running SQL queries to import/export data; however, this has
several drawbacks:

- Working with different database vendors might require customized SQL scripts.
- Access to the database may be tightly controlled, restricting the ability to
  import/export on demand.
- Database administrators may not give you access to the Liferay database.

An easier way to import/export your application's data is to use a Liferay
ARchive (LAR) file. Liferay provides the LAR feature to address the need to
import/export data in a database agnostic manner. So what exactly is a LAR file?

A LAR file is a compressed file (ZIP archive) that can be used to export/import
data from @product@. They can be created for single portlets, pages, or sets of
pages. Portlets that are LAR capable provide an interface to let you control how
its data is imported/exported. There are several @product@ use cases that
require the use of LAR files:

- Backing up and restoring portlet specific data without requiring a full
  database backup.
- Cloning sites.
- Specifying a template to be used for users' public or private pages.
- Using Local Live or Remote Live staging.

<!-- 1) LAR files are version specific (this includes service pack levels) and
2) LARs may contain user IDs to identify the creator or modifier of data, but
they don't contain actual user data. -->

To work around database limitations and give your application the ability to
import/export a LAR file, you can implement data handling in your application.
There are two types of data handlers you'll learn about: *Staged Model Data
Handlers* and *Portlet Data Handlers*.

A portlet data handler imports/exports portlet specific data to a LAR file. For
example, the Bookmarks application's portlet data handler tracks portlet
preferences and system events dealing with Bookmarks entities. It also
configures the Export/Import UI options for the Bookmarks application.

<!-- Creating Staged Models will be its own tutorial. For now, I'm going to give
a brief intro to them here so readers have a general understanding of them,
which is required to understand Staged Model Data Handlers. -Cody -->

To track each entity of an application for staging, you should create staged
models by implementing the
[StagedModel](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface. Staged models are the parent interface of an entity being handled in
the Staging framework. For example, since the Bookmarks application manages two
entities (folders and entries), it has a staged model for both.

Staged model data handlers are responsible for importing/exporting data related
to the staged model entity.

In this tutorial, you'll learn how to create a portlet data handler for your
custom application. Then you'll create a staged model data handler for each
entity your custom application manages. You're not required to implement a
staged model data handler for every entity in your application, but they are
necessary for any entity you want to be tracked in the LAR file.

<!-- Bare bones instructions for enabling a project for Staging using Service
Builder is outlined below. This info will go into a separate tutorial at a later
date. -Cody -->

Before beginning, make sure your application is ready for the Export/Import and
Staging frameworks by running Service Builder in your application. To
automatically generate the necessary code in your application to support
staging, you must set the `uuid` attribute to `true` in your `service.xml` file
and have the following columns declared:

- `companyId`
- `groupId`
- `userId`
- `userName`
- `createDate`
- `modifiedDate`

You can learn how to create a `service.xml` file for your application by
visiting the
[Defining an Object-Relational Map with Service Builder](/develop/tutorials/-/knowledge_base/7-0/defining-an-object-relational-map-with-service-builder)
tutorial.

For an example application, you'll examine how the Bookmarks application
implements data handlers. You'll start with its portlet data handler
implementation.

## Portlet Data Handlers

The following steps create the `BookmarksPortletDataHandler` class used for the
[Bookmarks](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/bookmarks/)
application.

1.  
















## Staged Model Data Handlers



