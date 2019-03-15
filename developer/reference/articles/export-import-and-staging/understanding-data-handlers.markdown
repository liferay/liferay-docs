# Data Handler Fundamentals [](id=data-handler-fundamentals)

A common requirement for many data driven applications is to import and export
data. This *could* be accomplished by accessing your database directly and
running SQL queries to export/import data; however, this has several drawbacks:

- Working with different database vendors might require customized SQL scripts.
- Access to the database may be tightly controlled, restricting the ability to
  export/import on demand.
- You'd have to come up with your own means of storing and parsing the data. 

Liferay provides data handlers as a more convenient and reliable way to
export/import your data (as a LAR file) without accessing the database.

There are two types of data handlers: *Portlet Data Handlers* and *Staged Model
Data Handlers*.

A Portlet Data Handler imports/exports portlet specific data to a LAR file.
These classes only have the role of querying and coordinating between staged
model data handlers. For example, the Bookmarks application's portlet data
handler tracks system events dealing with Bookmarks entities. It also configures
the Export/Import UI options for the Bookmarks application.

To track each entity of an application for staging, you should create staged
models by implementing the
[StagedModel](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface. Staged models are the parent interface of an entity in the Staging
framework. For more information on staged models, see the
[Understanding Staged Models](/developer/reference/-/knowledge_base/7-2/understanding-staged-models)
tutorial.

A Staged Model Data Handler supplies information about a staged model (entity)
to the Export/Import framework, defining a display name for the UI, deleting an
entity, etc. It's also responsible for exporting referenced content. For
example, if a Bookmarks entry resides in a Bookmarks folder, the
`BookmarksEntry` staged model data handler invokes the export of the
`BookmarksFolder`.

![Figure 1: The Data Handler framework uses portlet data handlers and staged model data handlers to track and export/import portlet and staged model information, respectively.](../../images/data-handler-diagram.png)

You're not required to implement a staged model data handler for every entity in
your application, but they're necessary for any entity you want to export/import
or have the staging framework track.

Before implementing data handlers, make sure your application is ready for the
Export/Import and Staging frameworks by running Service Builder in your
application. Using Service Builder to create staged models is not required, but
is recommended since it generates many requirements for you. To ensure Service
Builder recognizes your entity as a staged model, you must set the `uuid`
attribute to `true` in your `service.xml` file and have the following columns
declared:

- `companyId`
- `groupId`
- `userId`
- `userName`
- `createDate`
- `modifiedDate`

You can learn how to create a `service.xml` file for your application by
visiting the
[Defining an Object-Relational Map with Service Builder](/developer/frameworks/-/knowledge_base/7-2/defining-an-object-relational-map-with-service-builder)
tutorial.

To learn how to develop data handlers for your app, visit the
[Developing Portlet Data Handlers](/developer/frameworks/-/knowledge_base/7-2/developing-portlet-data-handlers)
and
[Developing Staged Model Data Handlers](/developer/frameworks/-/knowledge_base/7-2/developing-staged-model-data-handlers)
tutorials.
