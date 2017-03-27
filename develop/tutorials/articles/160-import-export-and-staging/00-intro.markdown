# Export/Import and Staging [](id=export-import-and-staging)

@product@'s Export/Import and Staging features give users the power to plan page
publication and manage content. The Export/Import feature lets users export
content from the Portal and import external content into the Portal. Providing
the export feature in your application allows users the flexibility of exporting
content they've created in your application to other places, such as another
portal instance, or to save the content for a later use. Import does the
opposite: it brings the data from a LAR file into your portal.

For instance, suppose you're managing an online education course using @product@.
Because of the nature of an online course, the site's data (grades,
assignments, etc.) is purged every semester to make way for new incoming
students. In a scenario like this, there is a need frequently to store
a complete record of all data given during a course. The institution offering
the course must usually keep records of the course's data for a minimum number
of years. To abide by these requirements, having a gradebook application with an
export/import feature would allow you to clear the application's data for a new
semester, but save the previous class's work. You could export the students'
grades as a LAR file and save it outside the course's site. If the grades ever
needed to be accessed again, you could import the LAR and view the student
records.

The Export/Import feature adds another dimension to your application by
letting you produce reusable content and import content from other places. To
learn more about using the Export/Import feature, visit the
[Exporting/Importing App Data](/discover/portal/-/knowledge_base/7-0/exporting-importing-app-data)
section of the User Guide.

Staging lets you change your site behind the scenes without affecting the live
site, and then you can publish all the changes in one fell swoop. Keep in mind
that Staging leverages the Export/Import framework, which is an essential part
of the publishing process. If you include staging support in your application,
your users can stage its content until it's ready.

For example, if you have an application that provides information intended only
during a specific holiday, supporting the Staging environment lets users save
your application's assets specific for that holiday. They'll reside
in the Staging environment until they're ready for publishing. To learn more
about Staging, visit the
[Staging Content for Publication](/discover/portal/-/knowledge_base/7-0/staging-content-for-publication)
section.

Besides configuring these features for your application, Liferay also provides
an API that allows developers to write custom code, extending Liferay's default
functionality. In this section of tutorials, you'll learn how to implement
Staging and the Export/Import framework. The main areas of code you'll focus on
are outlined below:

1. StagedModel interface - StagedModel is the cornerstone of staging. Every
content that needs to be handled in staging should implement this interface as
this provides the behavior contract for the entities staging will be using
during the process
2. StagedModelDataHandler - these types of the data handlers are reposponsible
for handling one specific entity class. Take an example from our collaboration
applications - a BookmarksEntryStagedModelDataHandler will be responsible for
handling everything the BookmarksEntry needs to do during staging: exporting,
serializing, finding existing entries and more.
3. PortletDataHandler - the portlet counterpart of the data handlers is a bit
different than the StagedModel one. One thing is common: it it handling
different aspects of a portlet publication and has a bit of configuration role
too.
4. ExportActionableDynamicQuery - this framework is a tremendous help for
developers when coding staging support. Essentially it’s purpose to query data
from the database and automatically process it for publication. It is
automatically generated so this takes away lots of work from the developer.
5. ExportImportContentProcessor and ExportImportPortletPreferencesProcessor -
advanced frameworks and only needed in special cases mostly. The former is
providing the ability to process your content during a publication process and
the latter is doing something similar for the portlet preferences - your
application’s configuration.