# Export/Import and Staging

Liferay's Export/Import and Staging features give users the power to plan
page publication and manage content. The Export/Import feature lets users export
content as a LAR file from Portal and import external content into Portal.
Providing the export feature in your application allows users the flexibility of
exporting content they've created in your application to other places, such as
another portal instance, or to save the content for a later use. The import
feature can be used to ingest a LAR file that was exported from Portal.

For instance, suppose you're managing an online education course using Liferay
Portal. Because of the nature of an online course, the site's data (grades,
assignments, etc.) is purged every term to make way for new incoming students.
In a scenario like this, there is a specific need to frequently store a complete
record of all data given during a course. The institution offering the course
must usually keep records of the course's data for a minimum number of years. To
abide by these requirements, having a gradebook application with an
export/import feature would fulfill the need for clearing the application's data
for a new semester, but saving the previous class's work. You could simply
export the grades of the students as a LAR file, and save it outside course's
site. If the grades ever needed to be accessed again, you could import the LAR
and view the student records.

The Export/Import feature adds another dimension to your application by
bestowing the powers to produce reusable content and ingest content from other
places. To learn more about using the Export/Import feature, visit the
[Export/Import](/discover/portal/-/knowledge_base/6-2/export-import) section of
the User Guide.

<!-- Update section to 7.0 version, when available. -Cody -->

Staging lets site administrators modify their sites behind the scenes and
publish all their updates in one fell swoop. You can develop your application to
work with Liferay's Staging environment, which adds many conveniences during
publishing. If your application can be staged, its contents can be modified
while also staying out of the limelight of your live site until it's ready. For
example, if you have an application that provides information intended only
during a specific holiday, having it compatible with the Staging environment
allows you to work and save information specific for that holiday, and have it
only reside in the Staging environment until it's ready. To learn more about
Staging, visit the
[Staging Page Publication](/discover/portal/-/knowledge_base/6-2/staging-page-publication)
chapter.

<!-- Update section to 7.0 version, when available. -Cody -->

Besides configuring these features for your application, Liferay also provides
an API that allows developers to write custom code, extending Liferay's default
functionality. In this section of tutorials, you'll learn about the various
extension points and other options that are provided to you for export/import
and staging.
