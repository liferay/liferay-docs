# Data Handlers

A common requirement for many data driven applications is to import and export
data. This *could* be accomplished by accessing your database directly and
running SQL queries to export/import data; however, this has several drawbacks:

- Working with different database vendors might require customized SQL scripts.
- Access to the database may be tightly controlled, restricting the ability to
  export/import on demand.
- You'd have to come up with your own means of storing and parsing the data. 

Liferay provides data handlers as a more convenient and reliable way to
export/import your data (as a LAR file) without accessing the database.

There are two types of data handlers:

- Portlet Data Handlers
- Staged Model Data Handlers

A Portlet Data Handler imports/exports portlet specific data to a LAR file.
These classes only have the role of querying and coordinating between staged
model data handlers. For example, the Bookmarks application's portlet data
handler tracks system events dealing with Bookmarks entities. It also configures
the Export/Import UI options for the Bookmarks application.

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
Export/Import and Staging frameworks by creating staged models. See the
[Staged Models](/developer/frameworks/-/knowledge_base/7-2/staged-models)
section for more information.























Continue in the section to learn how to develop data handlers for your app.