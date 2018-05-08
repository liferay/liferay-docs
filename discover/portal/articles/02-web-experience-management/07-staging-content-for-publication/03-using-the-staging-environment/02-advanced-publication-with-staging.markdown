# Advanced Publication with Staging

If you'd like to further configure your publication, you can select the *Switch
to Advanced Publication* button. Opening the Advanced Publication menu would be
useful if you'd like to schedule a time to publish your content, edit the
pages/content that will be included in the publication, manage permissions, etc.
You're presented a custom publication menu, where you can perform advanced
editing to your publication process.

You have two options to choose from for the Date category:

**Now:** immediately pushes any changes to the Live Site.

**Schedule:** lets you set a specific date to publish or to set up recurring
publishing. You could use this, for example, to publish all changes made during
the week every Monday morning without any further intervention.

Subsequently, you can choose additional options:

![Figure 2: You're given additional publication options for your pages, content, deletions, and permissions.](../../../images/staging-advanced-publication.png)

**Pages** gives you the option to choose which pages to include when you
publish. You can choose the page group (Public or Private) to publish by
selecting the *Change to Public Pages* or *Change to Private Pages*. You cannot
publish both at the same time; you'll have to complete their publication
processes separately if you want to publish both page groups. You can also
choose specific pages to publish, the look and feel of those pages, etc.

**Content** lets you configure the content to be published. Selecting the
*Choose Content* option lets you filter the content to be published, based
mainly on the date range and content type. If you choose a page to be published
from the Pages menu, the portlets and their references are always published,
even if you specified differently in the Content section.

There are other filtering sub-options for certain content types. Next, you'll
look at these content filtering options.

You first must choose what content to publish based on date. Specifying a
date range lets you choose content to publish based on when it was created or
last modified. Select the option that best fits your workflow. The available
options are described in more detail below:

**All:** publishes content regardless of its creation or last modification date.

**From Last Publish Date:** publishes content that was created or modified since
the last publish date. This is the default option.

**Date Range:** publishes content based on a specified date range. You can set a
start and end date/time window. The content created or modified within that
window of time is published.

**Last:** publishes content based on a set amount of time since the current
time.  For example, you can set the date range to the past 48 hours,
starting from the current time.

Under the date options are the different types of content that can be published.
This list is populated based on the provided date range. For example, if at
least one article has been created or modified in the given date range, a Web
Content section appears in the list, and the number of articles is shown next to
the Web Content label. Otherwise, the Web Content section is absent.

*Categories* and *Page Ratings* content types are not dependent on the date
range, and are always shown in the list.

Unchecking the checkbox next to a certain content type excludes it from the
current publication to Live.

Some of the content types in the list, like Web Content and Documents and Media,
have further filtering options related to them. For instance, when the Web
Content section is present and checked, it shows a comma-separated list of
related items to be published, including the articles themselves. A sample list
of related items for web content might look like this: *Web Content(12),
Structures(3), Referenced Content, Version History*. You can remove items by
clicking the *Change* button next to the list.

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

![Figure 3: Click the *Change* button and uncheck the version history box to only publish the latest approved version of web content articles that have multiple versions.](../../../images/web-content-version-history-box.png)

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

**Deletions** lets you delete portlet metadata before publishing and delete
operations performed for content types. If the *Replicate Individual
Deletetions* selector is enabled, operations performed for content in the
staging environment are replicated to the target site.

**Permissions** allows you to include permissions for the pages and portlets
when the changes are published.

The changes on the staged site aren't visible in the live site
until you publish it to the live site. If workflow is enabled for any new
resource, the resource needs to go through the workflow process before it can be
published to the live site.

![Figure 4: The staging toolbar indicates whether you're able to publish to the live site.](../../../images/staging-publish-bar.png)

Now that you have an idea of the available staging options, click *Mark as Ready
for Publication*, and then click *Publish to Live* &rarr; *Now*. Select
*Publish* to publish your Bookmarks application to the live site.
