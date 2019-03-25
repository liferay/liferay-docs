---
header-id: staging
---

# Staging

[TOC levels=1-4]

Staging lets you change your Site behind the scenes without affecting the live
Site, and then you can publish all the changes in one fell swoop. If you include
staging support in your application, your users can stage its content until it's
ready.

For example, if you have an application that provides information intended only
during a specific holiday, supporting the Staging environment lets users save
your application's assets specific for that holiday. They'll reside
in the Staging environment until they're ready for publishing. To learn more
about Staging, visit the
[Staging Content for Publication](/discover/portal/-/knowledge_base/7-2/staging-content-for-publication)
section.

Staging and Export/Import share the same base framework. When publishing your
staged content to the live Site, you're essentially importing content from the
staged Site and exporting it to the live Site. This means that implementing
Staging in your app is *almost* the same as implementing the Export/Import
framework. You can visit the
[Export/Import](/developer/frameworks/-/knowledge_base/7-2/export-import)
framework's articles for the base APIs that both it and the Staging frameworks
share.

If your app supports Export/Import, it's automatically tracked by Staging. There
are some Staging-specific configurations you can add that are not shared by
Export/Import. You'll learn about them in this section's articles.
