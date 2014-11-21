# Managing RSS Feeds in Liferay [](id=managing-rss-feeds-in-liferay)

RSS is a family of web feed formats used to publish frequently updated works
such as blog entries and news articles. RSS allows users to stay up-to-date with
your site's content without actually having to visit your site! Instead, they
can subscribe to your site's RSS feed with an RSS feed reader. Their RSS reader
reads your site's RSS feed and displays information about all the web content
that's published on your site, such as each article's title and publication
date. If one of your site's web content articles grabs their attention, then
they can follow their RSS reader's link to the article's full content on your
site. Many RSS readers are available today, including web-based readers, ones
for the Windows, Mac, and Linux platforms, and ones for mobile devices. Let's
see how to create RSS feeds in Liferay.

## Managing RSS Feeds from the Control Panel [](id=managing-rss-feeds-from-the-control-panel)

To manage a Liferay site's RSS feeds, navigate to the Site Administration &rarr;
Content page of your site and click *Web Content*. Site administrators can use
this Web Content administration portlet to manage their site's web content,
including web content structures and templates, which we examined above. Site
administrators can also use the Web Content administration portlet to manage
their site's RSS feeds. Click *Manage* &rarr; *Feeds* if you'd like to add,
edit, or delete RSS feeds.

+$$$

**Note:** The Web Content Feeds portlet is deprecated for Liferay 6.2 and will
be removed in Liferay 7.0. The portlet will be migrated to the Asset Publisher
portlet. 

$$$

![Figure 3.7: Clicking *Manage* &rarr; *Feeds* from the Control Panel's Web Content administration portlet opens a popup window which displays your site's RSS feeds. You can add or edit RSS feeds, configure their permissions, or delete them.](../../images/web-content-manage-feeds.png)

Click the *Add Feed* button to add a new feed. You need to enter a name and
select a target page for the feed. A feed's target page serves two purposes:

1. The site to which the target page belongs determines which web content
   articles appear in the feed. For example, if the target page belongs to the
   Marketing site, only web content articles belong to the Marketing site will
   appear in the feed. 

2. The target page is the page where "orphaned" web content articles will be
   displayed. "Orphaned" web content articles are articles that have been
   published in your site but have not been configured to be displayed in
   specific Web Content Display portlets. Liferay RSS feeds can provide links to
   any published web content articles, both "orphaned" articles and articles
   that have been configured to be displayed in specific Web Content Display
   portlets. For articles that have been configured to be displayed in a
   specific portlet, the RSS feeds' links point to the portal page of that
   portlet. For "orphaned" articles, the RSS feeds' links point to the feed's
   target page. When users click on such links for "orphaned" articles, the full
   content of the "orphaned" article is displayed on the target page.

![Figure 3.8: To create a new RSS feed, you only need to specify a name and a target page. Of course, you can also configure other features of the feed such as its permissions, web content constraints, and presentation settings.](../../images/web-content-new-feed.png)

To specify a target page, you need to enter the target page's friendly URL. Note
that friendly URLs do not include the host name. For example, the friendly URL
of a public page called *Welcome* belonging to a site called *Marketing* might
look like this: `/web/marketing/welcome`. Optionally, you can specify a target
portlet ID. This would be the portlet ID of a Web Content Display portlet on the
target page in which "orphaned" web content should be displayed. The portlet
must exist or else the content will not displayed. The URL field contains the
address of your RSS feed. It appears after you've actually created the feed by
clicking *Save*.

The final two sections of the *Add Feed* form allow you customize which web
content articles appear in your feed.

1. The Web Content Constraints section allows you to select a web content type
   and a structure with which to filter the articles that appear in your feed.
   You can select a particular type of web content such as *Announcements*,
   *News*, or *Press Release*. Only articles of the type you select will appear
   in your feed. You can also choose for only web content articles that have a
   particular structure to appear in your feed. This is useful since customized
   kinds of web content articles are often created using web content structures.

2. The Presentation Settings section allows you to customize additional details
   about your feed and how articles are displayed in your feed. Leave the
   Feed Item Content set to *Web Content Description* if you'd just like a
   description of each article to appear in your feed. Set it to *Rendered Web
   Content: Use Default Template* if you'd like the full content of each article
   to appear in the feed. Customizing the Feed Type allows you to choose which
   web feed language to use for your feed. You can choose *Atom 1.0* (the
   default), *RSS 1.0*, or *RSS 2.0*. Customize the *Maximum Items to Display*
   to choose the maximum number of articles should appear in your feed at one
   time. Leave the Order By Column set to *Modified Date* to have articles
   arranged in order from the last time they were published or modified. You can
   set the Order by Column to *Display Date* if you want to have articles
   arranged in order from the time they were configured to be displayed in a
   specific Web Content Display portlet. Lastly, you can leave the Order by Type
   set to *Ascending* to have the oldest articles at the top of the feed or you
   can set it to *Descending* to have the newest articles at the top of the
   feed.

When you're done configuring your RSS feed, you can click *Preview* to see how
your feed looks. If you're satisfied, click *Save* to create your feed.

Once one or more feeds have been created, they'll appear in a list in the Feeds
popup window when you click *Manage* &rarr; *Feeds*. You can edit existing feeds
using the same form used for creating them. The main difference is that when you
edit an existing feed, the URL field is populated. Copy this URL into a new
browser tab or window to test your feed. From the Feeds popup window, you can
also customize the permissions of feeds or delete feeds.

It's possible to completely disable RSS feeds at the portal level. You can do
this by setting the `rss.feeds.enabled` property to `false`. By default, it's
set to `true`. If you keep the default, RSS enabled, you can make several other
RSS property customizations. Please refer to the RSS section of your
`portal.properties` file for details.

## Using the RSS Portlet [](id=using-the-rss-portlet)

The RSS portlet allows you to display any number of RSS feeds and configure how
they are displayed. If you're looking for a web-based RSS reader, look no
further: just add the RSS portlet to one your personal site's private pages, and
voila! You have your own personal RSS reader. Open the portlet's Configuration
popup to select the feeds to be displayed and customize the display. The RSS
portlet can also be placed on sites' public or private pages to make feeds
available to guests or site members, respectively. In these cases, make sure
that only site administrators have permission to customize the RSS portlet and
select feeds to be displayed.

Once you've added the RSS portlet to a page, open the portlet's Configuration
popup window by clicking on the gear icon at the top right corner of the
portlet and selecting *Configuration*.

![Figure 3.9: The RSS portlet's configuration window lets you choose feeds to be displayed and allows you to customize the display settings.](../../images/rss-portlet-config.png)

By default, the RSS portlet displays two feeds. In the Feeds section, click on
the green plus sign to add a new feed or on the red minus sign to remove a feed.
Enter the URL of the RSS feed to display into the URL field. If you leave the
Title field blank, the feed's title appears in the RSS portlet. If you enter a
custom title into the Title field, the custom title appears instead of the
feed's title.

In the Display Settings section, use the following checkboxes to select the feed
details that should be displayed:

- Show Feed Title
- Show Feed Published Date
- Show Feed Description
- Show Feed Image
- Show Feed Item Author

You can also select the number of entries and expanded entries that should be
displayed per feed. Expanded entries show more of an article's actual content
than regular entries. By default, each feed shows eight entries per feed but
only one expanded entry per feed. You can set the feed image alignment to
control whether feed images appear to the right or left of the text. By default,
the feed image alignment is set to *Right*. Finally, you can select a header web
content and/or a footer web content. These are web content articles that appear
in the RSS portlet either above all of the feeds or below all of the feeds. You
can use these to provide an introduction, description, or footnotes about the
feeds that you've selected to be displayed.

![Figure 3.10: By default, the RSS portlet is configured to display feeds from Liferay Community Blogs, Yahoo News, and the New York Times. This image displays what the Liferay Community Blogs feed looks like in the RSS portlet.](../../images/rss-portlet-default-view.png)

Now that we've discussed how to create, manage, and use RSS feeds, let's examine
site templates and page templates. Site templates are a powerful tool for
managing many similar sites. Let's examine how they work and then we'll look at
page templates.
