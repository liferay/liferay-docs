# Publishing Staged Content Efficiently [](id=publishing-staged-content-efficiently)

Now that you have a firm grasp on how staging works, you'll dive deeper into the
publication process and some prerequisites you should follow before publishing.
By understanding how the process works, you can make smart and informed
decisions about how you want to publish your site's content.

## Understanding the Publication Process [](id=understanding-the-publication-process)

In simple terms, publication is the process where all content, referenced
content, apps and their preferences, pages, etc. are transferred from
the staging scope to the live site. If you've enabled remote staging, this
process involves network communication with another remote site. From a low
level perspective, staging is an equivalence relation where entities are being
mirrored to a different location. From a high level perspective, you can think
of the staging publication process in three phases: export, validation, and
import. These parts are executed sequentially.

During the export phase, the publication configuration is processed, which
defines the site's contents and apps. This phase also gathers the obligatory
referenced entities that will be required on the live site. Then everything
according to the publication parameters has been processed into the instance's
own file format, and that file has been stored locally or transferred to the
remote live Liferay instance.

Next, the validation phase determines if it's possible to start the import
process. This phase verifies the file's version and its integrity, checks for
additional system information like language settings, and validates there is no
missing content referenced.

Lastly, the import phase makes any necessary updates or additions to the site's
content, layouts, and apps according to the publishing parameters. If everything
is verified and correct, the staged content is published to your live site.

A crucial factor for successfully publishing staged content is data integrity.
If anything is not successfully verified during the publication process, the
transactional database can revert the site back to its original state,
discarding the current publication. This is a necessary action to safeguard
against publishing incomplete information, which could break an otherwise
well-designed live site.

If the file system is not "database-stored" (e.g., DBStore), it's not
transactional and won't be reverted automatically if a staging failure occurs.
This could potentially cause a discrepancy between a file and where it's being
referenced from. Because of this, administrators should take great care if they
decide to stage the document library, making sure that regular backups of both
their database and their file system are being maintained.

Next, you'll learn about staging best practices and prerequisites to follow for
a seamless staging experience.

## Planning Ahead for Staging [](id=planning-ahead-for-staging)

Staging is a complex subsystem of @product@ that is designed to be flexible and
scalable. Before advanced users and administrators begin using it for their
site, it's important to plan ahead and remember a few tips for a seamless
process. There are several factors to evaluate.

The most obvious factor is the content itself, including its amount, type, and
structure. Depending on the content you'd like to use in your site, you can turn
on staging for only the necessary content types, and leave others turned off to
avoid unnecessary work. Publication can also be configured to publish only
certain types of content.

The next factor to consider is the hardware environment. You should plan your
environment according to the content types you're using. If your site operates
on large images and video files, you should contemplate whether to use a shared
network drive. For example, storing many large images in the Document Library
usually requires a faster network or local storage. If you're dealing with web
content, however, these are usually smaller and take up very little disk space.

The third major factor is possible customizations and custom logic for your
staging environment. Your organization's business logic is most likely
implemented in an app, and if you want to support staging for that app, you'll
need to write some code to accomplish this. You can also consider changing
default UI settings by writing new JSP code, if you want your staging
environment's look and feel to change.

Once you've finished planning for your site, it is advised to turn on staging at
the very beginning of the site creation process. This allows the site creator to
avoid waiting for huge publications that can take long periods to execute.
Taking smaller steps throughout the publication process forms an iterative
creative process as the site is built from the ground up, where content creators
can publish their changes immediately with no long wait times.

A few prerequisites to follow for staging are listed below:

- 4 GB of memory with 512 MB permgen
- 20 MB/s transfer rate minimum (disk)

Now that you know how the staging environment works and how to enable it for
your site, you'll begin using it in the next section.
