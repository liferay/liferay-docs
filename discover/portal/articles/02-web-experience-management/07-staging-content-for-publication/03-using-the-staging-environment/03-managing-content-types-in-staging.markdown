# Managing Content Types in Staging

When managing content in Staging's Advanced Publication menu, there are several
factors to consider when preparing your content for publication. As described in
the
[Advanced Publication with Staging](/discover/portal/-/knowledge_base/7-1/advanced-publication-with-staging)
article, you can navigate to the Content area of the Advanced Publication menu
to select content you want to publish. There are options attached to each
content group (e.g., Web Content) that you can manage too.

![Figure 1: Click the *Change* button for a content group to manage its specific content.](../../../images/web-content-version-history-box.png)

You'll learn about some of these options and their best practices next.

## Referenced Content

This is represented by

- Structures and templates included in web content.
- Documents and Media files (e.g., images) included in web content.
- etc.

Staging can exclude some of this content during publication or export to speed
up the process. These references are validated during the publication process or
an import, so the images must be published or imported first.

## Version History

Web content tends to be frequently updated, often more so than other kinds of
content. Sometimes this can result in high numbers of versions, into the
hundreds. This makes it take a long time to publish these articles. @product@
addresses this issue by letting you choose whether or not to publish the
*Version History*, or the past versions of the web content articles to be
published. If you disable this option, only the last **approved** version of
each web content article is published to Live. This can significantly speed up
the publication process.

You can set this option globally. If you navigate to the Control Panel &rarr;
*Configuration* &rarr; *System Settings* &rarr; *Web Content Administration*,
you can toggle the *Publish version history by default* checkbox. This sets the
default behavior. When publishing content, it is selected by default, so site
administrators must manually uncheck the *Version History* box to publish only
the latest approved version of web content articles. To change the default
behavior, enable the checkbox in System Settings.

## Previews and Thumbnails

Previews and thumbnails are automatically generated for documents. Disabling
this, though, can greatly increase your publishing speed in some cases. You
should be careful about publishing previews and thumbnails to the live site.

Imagine a scenario where a site has approximately 4000 images or documents. If
the previews and thumbnails are turned on, this could end up in 28000 physical
files on the disk. If staging is set up to publish the previews and thumbnails,
this would mean that instead of taking care of the 4000 images, it will process
seven times more files! If you still want to use the previews on your live
environment, you can set up that Liferay instance to generate them
automatically.

It depends on your environment for whether you can use the publishing of the
previews and thumbnails. Publishing them is a heavy operation, and you also have
to transfer the LAR file over the network if you use remote staging. If you
decide to generate them on the live site, understand that this will take some
time, and is also a CPU intense operation.

## Vocabularies

When working within a site, a user may select vocabularies from both the current
site as well as the global site. While this doesn't pose an issue when creating
content, it can cause issues when publishing.

For environments that use both global and local vocabularies, note that global
vocabularies must be published to the live site through global site staging. One
way to avoid confusion with vocabularies is to simply keep all vocabularies
local or global.

If both must be used, this dependency issue can be resolved by ensuring that
dependencies (e.g., categories and vocabularies) are published before publishing
the site that depends on them (whether the dependencies are local or global).

Assets like tags, categories, structures, templates, application display
templates, document types, and dynamic data lists can also be shared by a parent
to its child sites. In this case, ensure that the ancestor's dependencies are
published before the site in question.

## Deletions

The Staging framework gathers deletions (including trashed entities) in a site.
These deletions can be published to clean up the live site. If it's not a
problem to have lingering data on the live, or it is going to be processed
later, this can be turned off as well to save execution time during the process.
