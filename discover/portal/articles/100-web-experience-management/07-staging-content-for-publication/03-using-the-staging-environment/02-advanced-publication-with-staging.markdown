# Advanced Publication with Staging [](id=advanced-publication-with-staging)

Once you've finished your changes on the staged site and want to publish them,
select the *Publish to Live* button from the Staging Bar. To configure advanced
publication options, select the *Switch to Advanced Publication* button. Opening
the Advanced Publication menu presents options for scheduling a time to publish
your content, editing the pages/content to include in the publication, managing
permissions, etc. This lets you perform advanced editing to your publication
process.

## Date [](id=date)

You have two options for the Date category:

**Now:** immediately pushes any changes to the live site.

**Schedule:** set a specific date to publish or to set up recurring publishing.
You could use this to publish all changes made during the week every Monday
morning without any further intervention.

These options let you plan staging schedules in advance.

## Pages [](id=pages)

This area of the menu gives you the option to choose which pages to include when
you publish. You can choose the page group (Public or Private) to publish by
selecting the *Change to Public Pages* or *Change to Private Pages*. You cannot
publish both at the same time; you must complete their publication processes
separately if you want to publish both page groups.

![Figure 1: You have several ways to specify the pages you want included in your publication.](../../../../images/staging-advanced-publication.png)

You can also choose specific pages to publish, and the look and feel of those
pages.

## Content [](id=content)

The Content area lets you select the content to be published. If you choose a
page to be published from the Pages menu, the portlets and their references are
always published, even if you specify differently in the Content section.

There are other filtering sub-options for certain content types. You first must
choose what content to publish based on date. Specifying a date range lets you
choose content to publish based on when it was created or last modified. Select
the option that best fits your workflow. The available options are described in
more detail below:

**All:** publishes all content regardless of its creation or last modification
date.

**From Last Publish Date:** publishes content that was created or modified since
the last publish date. This is the default option.

**Date Range:** publishes content based on a specified date range. You can set a
start and end date/time window. The content created or modified within that
window of time is published.

**Last:** publishes content based on a set amount of time since the current
time. For example, you can set the date range to the past 48 hours, starting
from the current time.

Under the date options are the different types of content that can be published.
This list is populated based on the provided date range. For example, if at
least one article is created or modified in the given date range, a Web Content
section appears in the list, and the number of articles is shown next to the Web
Content label. Otherwise, the Web Content section is absent.

The *Categories* content type is not dependent on the date range and is always
shown in the list.

+$$$

**Note:** Since comments and ratings are meant for the end user, they are not
supported in staging and can only be added to the live site.

$$$

Unchecking the checkbox next to a certain content type excludes it from the
current publication to the live site.

Some of the content types in the list, like Web Content and Documents and Media,
have further filtering options. For instance, when the Web Content section is
present and checked, it shows a comma-separated list of related items to be
published, including the articles themselves. A sample list of related items for
web content might look like this: *Web Content(12), Structures(3), Referenced
Content, Include Always, Version History*. You can remove items by clicking the
*Change* button next to the list.

See the
[Managing Content Types in Staging](/discover/portal/-/knowledge_base/7-1/managing-content-types-in-staging)
article for more information on managing content during the publication process.

## Deletions [](id=deletions)

This portion of the menu lets you delete two things: 

- portlet metadata before publishing 
- operations performed for content types. 

You have two options to manage for deletions:

**Delete Application Data Before Importing:** all data created by the
application is deleted before the import process. Ensure you understand the
ramifications of this option before selecting it. Some applications in other
pages may reference this data. This process cannot be undone. If you are unsure,
complete an export first.

**Replicate Individual Deletions:** operations performed for content in the
staging environment are replicated to the target site.

## Permissions [](id=permissions)

This area lets you include permissions for the pages and portlets when the
changes are published. Select the *Publish Permissions* checkbox to enable this
functionality.

Once you're finished configuring you advanced publication, select *Publish to
Live* to publish or schedule your publication.
