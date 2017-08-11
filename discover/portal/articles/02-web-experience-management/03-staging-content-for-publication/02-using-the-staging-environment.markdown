# Using the Staging Environment [](id=using-the-staging-environment)

After enabling staging (either Local Live or Remote Live) for a site, you'll
notice additional options provided on the top Control Menu and also in the
Menu to the left. If you haven't enabled staging for your site, see the
[Enabling Staging](/discover/portal/-/knowledge_base/7-0/enabling-staging)
section for instructions. These new menus help you manage staged pages. You'll
also notice that most of your page management options have been removed, because
now you can't directly edit live pages. You now must use the staging environment
to make changes. Click on the *Staging* button to view the staged area. Your
management options are restored and you can access some new options related to
staging. If you're following along with the Lunar Resort example, navigate back
to the News and Events page and click on *Staging* to get your page editing
capabilities back.

![Figure 1: You can see the new staging options added to the top and left of your screen.](../../../images/staging-live-page.png)

Add the Bookmarks application and then click on *Live* from the top menu. Notice
that the Bookmarks app isn't there. That's because you've staged a change to the
page but haven't published that change yet to the live site. Go back to the
staged page and observe the options you have to choose from to help in your
staging conquest.

**Site Pages Variations:** allows you to work in parallel on multiple versions
of a staged site page. You'll learn more about this later.

**Page Variations:** allows you to work in parallel on multiple versions of a
staged page. You'll learn more about this later.

**Undo/Redo:** allows you to step back/forward through recent changes to a page,
which can save you the time of manually adding or removing apps if you make a
mistake.

**History:** shows you the list of revisions of the page, based on publication
dates. You can go to any change in the revision history and see how the pages
looked at that point. To access *History*, select the *Options* icon
(![Options](../../../images/icon-options.png)) in the Staging bar.

**Ready for Publication:** After you're done making changes to the staged page,
click this button. The status of the page changes from *Draft* to *Ready for
Publication* and any changes you've made can be published to the Live Site. When
you publish a page to live, only the version which was *Marked as Ready for
Publication* is published.

When clicking the *Publish to Live* button, a popup window appears with come
configuration options for your publication. You can give your publication a name
and view the changes since last publication. If everything looks good, you can
click the *Publish to Live* button to publish your staged results to the live
site.

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

Now that you have an idea of the available staging options, click *Mark as Ready
for Publication*, and then click *Publish to Live* &rarr; *Now*. Select
*Publish* to publish your Bookmarks application to the live site.

If you create a bookmark in the staged site, it isn't visible in the live site
until you publish it to the live site. If workflow is enabled for any new
resource, the resource needs to go through the workflow process before it can be
published to the live site.

![Figure 4: The staging toolbar indicates whether you're able to publish to the live site.](../../../images/staging-publish-bar.png)

You can also manage your staging processes. Once staging is enabled, you can
navigate back to the *Staging* option located in the Menu's Publishing tab. From
here you'll see a list of staging processes that have been completed. You can
relaunch or clear any of these publications by clicking the *Actions* button
(![Options](../../../images/icon-actions.png)) next to a process. If you click
the *Scheduled* tab from above, you'll find staging processes that you've
scheduled for future publication dates.

If you find yourself repeatedly creating staging processes that are very similar
to each other, you should think about using Publish Templates. Up to this point,
you've looked at how to create custom publication processes.

Instead of manually having to customize a publication process every time you're
looking to publish pages/content, you can use a publish template. Using publish
templates provides you the convenience of storing publication process settings so
they can be reused. If you publish content frequently and usually select the same
options to publish, the task of selecting options repeatedly can become tedious.
With publish templates, you can select a custom template and immediately publish
with the options you configured.

To create a publish template, select the *Options* icon
(![Options](../../../images/icon-options.png)) from the top right corner of the
Staging screen and select *Publish Templates*. Click the *Add* button (![Add
Publish Template](../../../images/icon-add.png)) and assign the template a name
and description, and then fill out the configuration options as you would during
a custom publication process. Once you've saved your publish template, it is
available to use from the *Publish Templates* tab in the *Publish to Live* menu.
To use the template, click the *Actions* button
(![Options](../../../images/icon-actions.png)) next to the template and select
*Publish to Live*. This automatically fills the fields and options for
publishing pages and their content. All you have to do is give the publication
process a custom name. Once you confirm the configuration settings, your staging
settings are published.

+$$$

**Note:** When staging is enabled, the options available from your *Publishing
Tools* tab are modified. When in the Live environment, you're only able to
access the *Export* feature. When in the Staging environment, you're only able
to access the *Import* and *Staging* features. The features that are not
available for each environment don't make sense in that context. For example,
you shouldn't be able to import content when in the live environment; it must be
imported into the staged environment and then published before it is available
in the live site.

$$$

Now that you know how to use the staging environment, you'll learn about the
permissions involved to help manage this environment.

## Managing Permissions [](id=managing-permissions)

With the staging environment, there are many different options to use for
building and managing a site and its pages. Sometimes limiting the access to
a subset of the powerful features of staging is desired by some administrators.
You can manage access to the staging environment by creating or modifying a role
to possess certain permissions. To create/modify a role, navigate to the
*Control Panel* &rarr; *Users* &rarr; *Roles*. You can create a new role by
selecting the *Add* button (![Add](../../../images/icon-add.png)) and completing
the New Role menu. Once you have a new role created, or you've decided on the
role you'd like to modify, select the role's *Actions* icon
(![Actions](../../../images/icon-actions.png)) and select *Define Permissions*.

The most obvious permissions for staging are the general permissions that look
similar to the permissions for most Liferay apps. These permissions can be found
in the *Site Administration* &rarr; *Publishing* &rarr; *Staging* section of the
Define Permissions menu. This includes *Access in Control Panel*,
*Configuration*, *Permissions*, *Preferences*, and *View*. Also, there are some
site resource permissions that deal directly with staging. These permissions are
located in the *Control Panel* &rarr; *Sites* &rarr; *Sites* section in the
Define Permissions menu. The relevant site resource permissions related to
staging are listed below:

- Add Page Variation: hides/shows the *Add* button on the Staging bar &rarr;
  Manage Page Variations screen.
- Add Site Pages Variation: hides/shows the *Add* button on the Staging bar &rarr;
  Manage Site Page Variations screen.
- Export/Import Application Info: if the Publish Staging permission is not
  granted, hides/shows the application level Export/Import menu. The
  Configuration permission for the Export/Import app is also required
- Export/Import Pages: if the Publish Staging permission is not granted,
  hides/shows the Export/Import app in the Site Administration menu.
- Manage Staging: hides/shows the the staging configuration menu in the Staging
  app in the Site Administration menu.
<!-- Publish Application Info: Coming soon (LPS-63958) -Cody -->
- Publish Staging: hides/shows the *Publish to Live* button on the Staging bar
  and hides/shows the *Add* button in the Staging app in the Site Administration
  menu.
- View Staging: If Publish Staging, Manage Pages, Manage Staging, or Update
  permissions are not granted, hides/shows the Staging app in the Site
  Administration menu.

Notice that some of the permissions listed above are related to the
export/import functionality. Since these permissions are directly affected by
the Publish Staging permission, they are important to note. Visit the
[Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-0/importing-exporting-pages-and-content)
section for more details on importing/exporting site and page content.

One of the most powerful features of staging is page variations. Next, you'll
see how to use them to create multiple different variations of your site's pages
for different purposes.

## Using Site Pages Variations [](id=using-site-pages-variations)

Let's say you're working on a product-oriented site where you'll have several
major changes to a page or a set of pages over a short period of time. Also you
need to be working on multiple versions of the site at the same time to ensure
everything has been properly reviewed before it goes live. With staging, you can
do this using *Page Variations*.

Notice that there are two page variation options available from the Staging bar:
*Site Pages Variation* and *Page Variations*. Site Pages Variation is used to
create different variations for the set of site pages. For instance, you could
use this if you had three separate pages and wanted to modify these pages while
keeping them together as a set. The Page Variations option only works with a
single page.

For example, you can create several site pages variations, enabling the
marketing team to give your site a completely different look and feel for
Christmas. At the same time, the product management team can work on a different
version that will be published the day after Christmas for the launching of a
new product. Additionally, the product management team is considering two
different ideas for the home page of the site, so they can create several page
variations of the home page inside their product launch site.

Variations only affect pages and not the content, which means all the existing
content in your staging site is shared by all your variations. In different site
page variations, you can have different logos, different look and feel for your
pages, different applications on these pages, different configuration of these
applications and even different pages. One page can exist in just one site page
variation or in several of them. Modifying the layout type (e.g., Layout, Panel,
Embedded, etc.) or friendly URL of a page, however, **does** affect every site
page variation.

You must enable page versioning when turning on Local or Remote Live staging.
You can enable page versioning for public and private pages. When you turn
staging on with page versioning enabled, the page variation options are
available in the staging menu bar. By default, you only have one site page
variation and page variation which are both called *Main Variation*. To create a
new one, select the *Options* icon
(![Options](../../../images/icon-options.png)) in the Staging bar and select the
variation option. For example, select the *Site Pages Variation* option. This
brings you to a list of the existing site page variations for your site. Click
*Add Site Pages Variation* to create a new one.

![Figure 5: When selecting the *Site Pages Variation* link from the staging toolbar, you're able to add and manage your site pages variations.](../../../images/staging-page-variations.png)

From the *Add Site Pages Variation* screen, you can set a Name, Description, and
also set your new variation to copy the content from an existing variation.
There are several options to choose in this selector.

**All Site Pages Variations:** creates a new variation that contains the last
version marked as ready for publication from any single page existing in any
other variation.

**None (Empty Site Pages Variation):** creates a new, empty variation.

**Main Variation:** creates a new site page variation that contains only the
last version of all the pages that exist in this variation. The current
variation must be marked as ready for publication.

You are also able to rename any variation. For example, edit the Main Variation
and change its name to something that makes more sense in your site, such as
*Basic*, *Master*, or *Regular* and create a variation for Christmas.

You can switch between different variations by clicking on them from the staging
menu bar. It's also possible to set permissions on each variation, so certain
users have access to manage some, but not all variations.

You can now go to the home page of your Christmas variation and change the logo,
apply a new theme, move applications around, change the order of the pages and
configure different apps. The other variations won't be affected. You can even
delete existing pages or add new ones (remember to *Mark as Ready for
Publication* when you are finished with your changes).

<!--
When you delete a page, it is deleted only in the current variation. The same
happens when you add a new page. If you try to access a page which was deleted
in the current variation, Liferay informs you this page is not *enabled* in this
variation and you must enable it. To enable it, navigate to the Site
Administration &rarr; *Pages* &rarr; *Site Pages* screen. Here all the existing
pages for all the variations are shown in a tree. Pages not enabled for the
current variation are shown in a lighter color. You can also access Staging
options from the Site Pages screen by clicking the *Staging* drop-down menu
located above the pages tree.
----
Above paragraph feature did not work. Check if text is accurate or if feature
changed. This feature, however, does working when adding a page. -Cody -->

To publish a variation to the live site, click on *Publish to Live* in the
staging menu and then select *Publish to Live*. Publications can also be
scheduled independently for different variations. For example, you could have a
variation called *Mondays* which is published to the live site every Monday and
another one called *Day 1* which is published to the live site every first day
of each month.

You can also have variations for a single page inside a site page variation,
which allows you to work in parallel in different versions of a page. For
example, you might work on two different proposals for the design of the home
page for the Christmas variation. These page variations only exist inside a site
page variation.

To create a new page variation, click *Page Variations* on the staging toolbar.
This brings you to a list of existing page variations for the current page (by
default, there is only one called *Main Variation*). You can create more or
rename the existing one. You can switch between different page variations using
the dropdown menu on the staging toolbar. When you decide which page variation
should be published, mark it as *Ready for Publication*. Only one page variation
can be marked as ready for publication and that is the one that gets published
to the live site.

For example, you could create a page variation called Thanksgiving for the News
and Events page inside of the Christmas variation and another one called
Christmas Day to display different content on those particular days.

![Figure 6: This is an example of a Thanksgiving page variation.](../../../images/page-variation-thanksgiving.png)

Another powerful feature is the possibility of *merging* Site Pages Variations.
To merge two Site Pages Variations, you need to go to the Site Pages Variation
screen. From there, click on *Merge* on the Site Pages Variation you want to use
as the base. You will be asked to choose the Site Pages Variation to merge on
top of it. Merging works in the following way:

* New pages that don't exist in the base Variation will be added.
* If a page exists in both Site Pages Variations, and at least one version of
the page was marked as ready for publication, then the latest version marked as
ready will be added as a new Page Variation in the target page of the base
Variation. (Note that older versions or page variations not marked as ready for
publication won't be copied. Merge can be executed, however, as many times as
needed and will create the needed pages variations in the appropriate page of
the base Site Pages Variation).
* Merging does not affect content nor will overwrite anything in the base
Variation, it will just add more versions, pages and page variations as needed.

@product@'s staging environment is extremely easy to use and makes maintaining a
content-rich web site a snap. You'll learn about scheduling web content next.
