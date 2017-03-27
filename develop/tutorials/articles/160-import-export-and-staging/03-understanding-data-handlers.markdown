# Understanding Data Handlers [](id=understanding-data-handlers)

A common requirement for many data driven applications is to import and export
data. This *could* be accomplished by accessing your database directly and
running SQL queries to export/import data; however, this has several drawbacks:

- Working with different database vendors might require customized SQL scripts.
- Access to the database may be tightly controlled, restricting the ability to
  export/import on demand.
- You'd have to come up with your own means of storing and parsing the data. 

Liferay provides a more convenient and reliable way to export/import your data
without accessing the database.

## Liferay Archive (LAR) File [](id=liferay-archive-lar-file)

An easier way to export/import your application's data is to use a Liferay
ARchive (LAR) file. Liferay provides the LAR feature to address the need to
export/import data in a database agnostic manner. So what exactly is a LAR file?

A LAR file is a compressed file (ZIP archive) @product@ uses to export/import
data. LAR files can be created for single portlets, pages, or sets of pages.
Portlets that are LAR-capable provide an interface to let you control how their
data are imported/exported. There are several @product@ use cases that require
the use of LAR files:

- Backing up and restoring portlet-specific data without requiring a full
  database backup.
- Cloning sites.
- Specifying a template to be used for users' public or private pages.
- Using Local Live or Remote Live staging.

Liferay provides the data handler framework so developers don't have to
create/modify a LAR file manually. **It is strongly recommended never to modify
a LAR file.** You should always use Liferay's provided data handler APIs to
construct it.

Knowing how a LAR file is constructed, however, is beneficial to understand the
overall purpose of your application's data handlers. Next, you'll explore a LAR
file's anatomy.

### LAR File Anatomy [](id=lar-file-anatomy)

What is a LAR file? You know the general concept for *why* it's used, but you
may want to know what lives inside to make your export/import processes work.
With a fundamental understanding for how a LAR file is constructed, you can
better understand what your data handlers generate behind the scenes.

Below is the structure of a simple LAR file. It illustrates the exportation of a
single Bookmarks entry and the portlet's configuration:

- `Bookmarks_Admin-201701091904.portlet.lar`
    - `group`
        - `20143`
            - `com.liferay.bookmarks.model.BookmarksEntry`
                - `35005.xml`
            - `portlet`
                - `com_liferay_bookmarks_web_portlet_BookmarksAdminPortlet`
                    - `20137`
                        - `portlet.xml`
                    - `20143`
                        - `portlet-data.xml`
    - `manifest.xml`

You can tell from the LAR's generated name what information is contained in
the LAR: the Bookmarks Admin app's data. The `manifest.xml` file sits at the
root of the LAR file. It provides essential information about the export
process. The `manifest.xml` for the sample Bookmarks LAR is pretty bare since
it's not exporting much content, but this file can become large when exporting
pages of content. There are four main parts (tags) to a `manifest.xml` file.

- `header`: contains information about the LAR file, current process,
  and site you're exporting (if necessary). For example, it can include
  locales, build information, export date, company ID, group ID, layouts,
  themes, etc.
- `missing-references`: lists entities that must be validated during import. For
  example, suppose you're exporting a web content article that references an
  image (e.g., an embedded image residing in the document library). If the image
  was not selected for export, the image must already exist in the site where
  the article is imported. Therefore, the image would be flagged as a missing
  reference in the LAR file. If the missing reference does not exist in the site
  when the LAR is imported, the import process fails. If your import fails, the
  Import UI shows you the missing references that weren't validated. 
- `portlets`: defines the portlets (i.e., portlet data) exported in the LAR.
  Each portlet definition has basic information on the exported portlet and
  points to the generated `portlet.xml` for more specialized portlet
  information.
- `manifest-summary`: contains information on what has been exported. The
   Staging and Export frameworks export or publish some entities even
   though they weren't marked for it, because the process respects data
   integrity. This section holds information for all the entities that have
   been processed. The entities defining a non-zero `addition-count` attribute
   are displayed in the Export/Import UI.

The `manifest.xml` file also defines layout information if you've exported pages
in your LAR. For example, your manifest could have `LayoutSet`, `Layout`, and
`LayoutFriendlyURL` tags specifying staged models and their various references
in an exported page.

Now that you've learned about the LAR's `manifest.xml` and how it's used to
store high-level data about your export process, you can dive deeper into the
LAR file's `group` folder. The `group` folder has two main parts:

- Entities
- Portlets

If you look at the anatomy of the sample Bookmarks LAR, you'll notice that
`group/[groupId]` folder holds a folder named after the entity you're exporting
(e.g., `com.liferay.bookmarks.model.BookmarksEntry`) and a `portlet` folder
holding a folder named after the portlet from which you're exporting (e.g.,
`com_liferay_bookmarks_web_portlet_BookmarksAdminPortlet`). For each
entity/portlet you export, there are subsequent folders holding data about them.
Entities and portlets can also be stored in a `company` folder. Although the
majority of entities belong to a group, some exist outside of a group scope
(e.g., users).

If you open the
`/group/20143/com.liferay.bookmarks.model.BookmarksEntry/35005.xml` file, you'll
find serialized data about the entity, which is similar to what is stored
in the database.

The `portlet` folder holds all the portlets you exported. Each portlet has its
own folder that holds various XML files with data describing the exported
content. There are three main XML files that can be generated for a single
portlet:

- `portlet.xml`: provides essential information about the portlet, similar to a
  manifest file. For example, this can include the portlet ID, high-level entity
  information stored in the portlet (e.g., web content articles in a web content
  portlet), permissioning, etc.
- `portlet-data.xml`: describes specific entity data stored in the portlet. For
  example, for the web content portlet, articles stored in the portlet are
  defined in `staged-model` tags and are linked to their serialized entity XML
  files.
- `portlet-preferences.xml`: defines the settings of the portlet. For example,
   this can include portlet preferences like the portlet owner, default user,
   article IDs, etc.

Note that when you import a LAR, it only includes the portlet data. You have to
deploy the portlet to be able to use it. 

You now know how exported entities, portlets, and pages are defined in a LAR
file. For a summarized outline of what you've learned about LAR file
construction, see the diagram below.

![Figure 1: Entities, Portlets, and Pages are defined in a LAR in different places.](../../images/lar-diagram.png)

Excellent! You now have a fundamental understanding for how a LAR file is
generated and how it's structured.

Next, you'll learn about data handler fundamentals and the prerequisites
required to implement them.

## Data Handler Fundamentals [](id=data-handler-fundamentals)

To leverage the Export/Import framework's ability to export/import a LAR file,
you can implement Data Handlers in your application. There are two types of data
handlers: *Portlet Data Handlers* and *Staged Model Data Handlers*.

A Portlet Data Handler imports/exports portlet specific data to a LAR file.
These classes only have the role of querying and coordinating between staged
model data handlers. For example, the Bookmarks application's portlet data
handler tracks system events dealing with Bookmarks entities. It also configures
the Export/Import UI options for the Bookmarks application.

<!-- Creating Staged Models will be its own tutorial. For now, I'm going to give
a brief intro to them here so readers have a general understanding of them,
which is required to understand Staged Model Data Handlers. -Cody -->

To track each entity of an application for staging, you should create staged
models by implementing the
[StagedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface. Staged models are the parent interface of an entity in the Staging
framework. For example, the Bookmarks application manages
[BookmarksEntry](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntry.html)s
and
[BookmarksFolder](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksFolder.html)s,
and both implement the `StagedModel` interface.

A Staged Model Data Handler supplies information about a staged model (entity)
to the Export/Import framework, defining a display name for the UI, deleting an
entity, etc. It's also responsible for exporting referenced content. For
example, if a Bookmarks entry resides in a Bookmarks folder, the
`BookmarksEntry` staged model data handler invokes the export of the
`BookmarksFolder`.

![Figure 2: The Data Handler framework uses portlet data handlers and staged model data handlers to track and export/import portlet and staged model information, respectively.](../../images/data-handler-diagram.png)

You're not required to implement a staged model data handler for every entity in
your application, but they're necessary for any entity you want to export/import
or have the staging framework track.

<!-- Bare bones instructions for enabling a project for Staging using Service
Builder is outlined below. This info will go into a separate tutorial at a later
date. -Cody -->

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
[Defining an Object-Relational Map with Service Builder](/develop/tutorials/-/knowledge_base/7-0/defining-an-object-relational-map-with-service-builder)
tutorial.

To learn how to develop data handlers for your app, visit the
[Developing Data Handlers](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/data-handlers)
tutorial.
