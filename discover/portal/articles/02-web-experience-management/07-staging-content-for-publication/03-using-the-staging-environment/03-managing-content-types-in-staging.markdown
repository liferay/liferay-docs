# Managing Content Types in Staging



*Referenced Content* is represented by the Documents and Media files included in
web content articles. Documents and Media content gets referenced when a user
uses the editor to insert an image or if the article is based on a structure
that has a field of the *Documents and Media* type. 

Web content tends to be frequently updated, often more so than other kinds of
content. Sometimes this can result in high numbers of versions, into the
hundreds. This makes it take a long time to publish these articles. @product@
addresses this issue by allowing you to choose whether or not to publish the
*Version History*, or the past versions of the web content articles to be
published. If you disable this option, only the last **approved** version of
each web content article is published to Live. This can significantly speed up
the publication process.

![Figure 2: Click the *Change* button and uncheck the version history box to only publish the latest approved version of web content articles that have multiple versions.](../../../images/web-content-version-history-box.png)

You can set this option globally. If you navigate to the Control Panel &rarr;
*Configuration* &rarr; *System Settings* &rarr; *Web Content Administration*,
you can toggle the *Publish version history by default* checkbox. This sets the
default behavior. When publishing content, it is selected by default, so site
administrators must manually uncheck the *Version History* box to publish only
the latest approved version of web content articles. To change the default
behavior, enable the checkbox in System Settings.

When the *Documents and Media* section is present (because at least one document
has been created or modified in the provided date range), you can disable the
transfer to live of the previews and thumbnails associated with the documents to
be published. This can also speed up publication time.