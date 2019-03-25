---
header-id: liferay-archive-lar-file
---

# Liferay Archive (LAR) File

[TOC levels=1-4]

An easier way to export/import your application's data is to use a Liferay
ARchive (LAR) file. Liferay provides the LAR feature to address the need to
export/import data in a database agnostic manner. So what exactly is a LAR file?

A LAR file is a compressed file (ZIP archive) @product@ uses to export/import
data. LAR files can be created for single portlets, pages, or sets of pages.
Portlets that are LAR-capable provide an interface to let you control how their
data is imported/exported. There are several @product@ use cases that require
the use of LAR files:

- Backing up and restoring portlet-specific data without requiring a full
  database backup.
- Cloning sites.
- Specifying a template to be used for users' public or private pages.
- Using Local Live or Remote Live staging.

The data handler framework is available so developers don't have to
create/modify a LAR file manually. **It is strongly recommended never to modify
a LAR file.** You should always use Liferay's provided data handler APIs to
construct it.

Knowing how a LAR file is constructed, however, is beneficial to understand the
overall purpose of your application's data handlers. Next, you'll explore a LAR
file's anatomy.

## LAR File Anatomy

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

You'll dissect the anatomy structure next.

## LAR Manifest

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
LAR file's folders.

## LAR Folders

The `group` folder has two main parts:

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
