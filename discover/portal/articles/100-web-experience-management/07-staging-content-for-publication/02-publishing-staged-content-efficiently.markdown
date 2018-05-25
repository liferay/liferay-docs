# Publishing Staged Content Efficiently [](id=publishing-staged-content-efficiently)

Now that you understand how staging works, you'll dive deeper into the
publication process and some prerequisites you should follow before publishing.
By understanding how the process works, you can make smart and informed
decisions about how you want to publish your site's content.

## Understanding the Publication Process [](id=understanding-the-publication-process)

In simple terms, a publication is the process where all content, referenced
content, apps and their preferences, pages, etc. are transferred from the
staging scope to the live site. If you've enabled remote staging, this process
involves network communication with another remote site. From a low level
perspective, staging is an equivalence relation where entities are mirrored to
a different location. From a high level perspective, the staging process happens
in three phases:

1.  **Export:** processes the publication configuration, which defines the
    site's content and apps. This phase also gathers the obligatory referenced
    entities that are required on the live site. Then everything according to
    the publication parameters is processed into the instance's own file format,
    and that file is stored locally or transferred to the remote live Liferay
    instance.

2.  **Validation:** determines if it's possible to start the import process.
    This phase verifies the file's version and its integrity, checks for
    additional system information like language settings, and validates there is
    no missing content referenced.

3.  **Import:** makes any necessary updates or additions to the site's content,
    layouts, and apps according to the publishing parameters. If everything is
    verified and correct, the staged content is published to your live site.

These phases are executed sequentially.

A crucial factor for successfully publishing staged content is data integrity.
If anything is not verified during the publication process, the transactional
database reverts the site back to its original state, discarding the current
publication. This is a necessary action to safeguard against publishing
incomplete information, which could break an otherwise well-designed live site.

If the file system is not *database-stored* (e.g., DBStore), it's not
transactional and isn't reverted if a staging failure occurs. This could
potentially cause a discrepancy between a file and its reference in the
database. Because of this, administrators should take great care with staging
the document library, making sure that regular backups of both database and file
system are being maintained.

Next, you'll learn about staging best practices and prerequisites to follow for
a seamless staging experience.

## Planning Ahead for Staging [](id=planning-ahead-for-staging)

Staging is a complex subsystem that's flexible and scalable. Before advanced
users and administrators begin using it for their site, it's important to plan
ahead and remember a few tips for a seamless process. There are several factors
to evaluate.

- **Content (amount, type, and structure):** Depending on the content in your
  site, you can turn on staging for only the necessary content types, leaving
  others turned off to avoid unnecessary work. Publication can also be
  configured to publish only certain types of content. See the 
  [Managing Content Types](/discover/portal/-/knowledge_base/7-1/managing-content-types-in-staging)
  article for more information.

- **Hardware Environment:** You should plan your environment according to your
  content types. If your site operates on large images and video files, decide
  if a shared network drive is the best option. Storing many large images in the
  Document Library usually requires a faster network or local storage. If you're
  dealing with web content, however, these are usually smaller and take up very
  little disk space.

- **Customizations and Custom Logic for Your Staging Environment:** Your
  organization's business logic is most likely implemented in an app, and if you
  want to support staging for that app, you must 
  [write some code](/develop/tutorials/-/knowledge_base/7-1/export-import-and-staging) 
  to accomplish this. You can also consider changing default UI settings by
  writing new JSP code if you want your staging environment's look and feel to
  change.

Once you've finished planning for your site, you should turn on staging at the
very beginning of the site creation process. This allows the site creator to
avoid waiting for huge publications that can take long periods to execute.
Taking smaller steps throughout the publication process forms an iterative
creative process as the site is built from the ground up, where content creators
can publish their changes immediately, avoiding long wait times.

Here are some JVM/network configuration recommendations for Staging:

- 4 GB of memory 

- 20 MB/s transfer rate minimum (disk)

Now that you know how the staging environment works and how to enable it for
your site, you'll begin using it in the next section.
