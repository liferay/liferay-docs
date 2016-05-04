# Publishing RSS Feeds [](id=publishing-rss-feeds)

RSS is a family of web feed formats used to publish frequently updated works
such as blog entries and news articles. RSS allows users to stay up-to-date with
your site's content without actually having to visit your site! Instead, they
can subscribe to your site's RSS feed with an RSS feed reader. Their RSS reader
reads your site's RSS feed and displays information about all the web content
that's published on your site, such as each article's title and publication
date. If one of your site's web content articles grabs their attention, then
they can follow their RSS reader's link to the article's full content on your
site. Many RSS readers are available today, including web-based readers, ones
for the Windows, Mac, and Linux platforms, and ones for mobile devices. You'll
see how to create RSS feeds in @product@ next.

## Configuring RSS Feeds [](id=configuring-rss-feeds)

To manage a Liferay site's RSS feeds, navigate to the Site Administration &rarr;
*Content* page of your site and click *Web Content*. Site administrators can use
this Web Content menu option to manage their site's web content, including web
content structures and templates, which you learned in the 
[Creating Web Content](/discover/portal/-/knowledge_base/7-0/creating-web-content)
section. Site administrators can also use this option to manage their site's RSS
feeds. Click the *Options* icon (![Options](../../../images/icon-options.png))
at the top right of your screen and then *Feeds* if you'd like to add, edit, or
delete RSS feeds.

![Figure 1: Clicking *Options* &rarr; *Feeds* from the Site Administration's Web Content option opens a popup window which displays your site's RSS feeds. You can add or edit RSS feeds, configure their permissions, or delete them.](../../../images/web-content-manage-feeds.png)

Click the *Add Feed* button to add a new feed. You need to enter a name, select
a target page, and select a web content structure for the feed. A feed's target
page serves two purposes:

1. The site to which the target page belongs determines which web content
   articles appear in the feed. For example, if the target page belongs to the
   Marketing site, only web content articles belonging to the Marketing site
   will appear in the feed.

2. The target page is the page where "orphaned" web content articles will be
   displayed. Orphaned web content articles are articles that have been
   published in your site but have not been configured to be displayed in
   specific Web Content Display applications. Liferay RSS feeds can provide
   links to any published web content articles, both orphaned articles and
   articles that have been configured to be displayed in specific Web Content
   Display applications. For articles that have been configured to be displayed in a
   specific application, the RSS feeds' links point to the Liferay page of that
   app. For orphaned articles, the RSS feeds' links point to the feed's target
   page. When users click on such links for orphaned articles, the full content
   of the orphaned article is displayed on the target page.

![Figure 2: To create a new RSS feed, you only need to specify a name, target page, and web content structure. Of course, you can also configure other features of the feed such as its permissions, web content constraints, and presentation settings.](../../../images/web-content-new-feed.png)

To specify a target page, you need to enter the target page's friendly URL. Note
that friendly URLs do not include the host name. For example, the friendly URL
of a public page called *Welcome* belonging to a site called *Marketing* might
look like this: `/web/marketing/welcome`. Optionally, you can specify a target
portlet ID. This would be the portlet ID of a Web Content Display application on
the target page in which orphaned web content should be displayed. The
application must exist or else the content will not displayed. The URL field
contains the address of your RSS feed. It appears after you've actually created
the feed by clicking *Save*.

The final two sections of the *Add Feed* form allow you customize which web
content articles appear in your feed.

1. The Web Content Constraints section allows you to select a web content
   structure with which to filter the articles that appear in your feed.
   This is useful since all web content articles are created using web content
   structures.

2. The Presentation Settings section allows you to customize additional details
   about your feed and how articles are displayed in your feed. Leave the Feed
   Item Content set to *Web Content Description* if you'd just like a
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
   specific Web Content Display application. Lastly, you can leave the Order by
   Type set to *Ascending* to have the oldest articles at the top of the feed or
   you can set it to *Descending* to have the newest articles at the top of the
   feed.

When you're done configuring your RSS feed, click *Save* to create your feed.

Once one or more feeds have been created, they'll appear in a list in the Feeds
popup window when you click *Options* &rarr; *Feeds*. You can edit existing
feeds using the same form used for creating them. The main difference is that
when you edit an existing feed, the URL field is populated. Copy this URL into a
new browser tab or window to test your feed. From the Feeds popup window, you
can also customize the permissions of feeds or delete feeds.

It's possible to completely disable RSS feeds at the instance level. You can do
this by setting the `rss.feeds.enabled` property to `false` in your
`portal-ext.properties` file. By default, it's set to `true`. If you keep the
default, RSS enabled, you can make several other RSS property customizations.
Please refer to the
[RSS section](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#RSS)
of your `portal.properties` file for details.

## Using the RSS Publisher Application [](id=using-the-rss-feeds-application)

The RSS Publisher application lets you display any number of RSS feeds and
configure how they are displayed. If you're looking for a web-based RSS reader,
look no further: just add the RSS Publisher app to one your personal site's
private pages, and voila! You have your own personal RSS reader. Open the
application's Configuration menu to select the feeds to be displayed and
customize the display. The RSS Publisher app can also be placed on sites' public
or private pages to make feeds available to guests or site members,
respectively. In these cases, make sure that only site administrators have
permission to customize the RSS application and select feeds to be displayed.

Once you've added the RSS Publisher app to a page, open the application's
Configuration menu by clicking on the *Options* icon
(![Options](../../../images/icon-options.png)) at the top right corner of the
application and selecting *Configuration*.

![Figure 3: The RSS Publisher app's configuration window lets you choose feeds to be displayed and allows you to customize the display settings.](../../../images/rss-application-config.png)

By default, the RSS application displays one feed. In the Feeds section, click
on the plus sign to add a new feed or on the minus sign to remove a
feed. Enter the URL of the RSS feed to display into the URL field. If you leave
the Title field blank, the feed's title appears in the RSS application. If you
enter a custom title into the Title field, the custom title appears instead of
the feed's title.

In the top section, use the following selector buttons to select the feed
details that should be displayed:

- Show Feed Title
- Show Feed Published Date
- Show Feed Description
- Show Feed Image
- Show Feed Item Author

You can also select the number of entries and expanded entries that should be
displayed per feed. Expanded entries show more of an article's actual content
than regular entries. By default, each feed shows four entries per feed and
eight expanded entries per feed. You can set the feed image alignment to
control whether feed images appear to the right or left of the text. By default,
the feed image alignment is set to *Right*.

![Figure 4: By default, the RSS Publisher app is configured to display feeds from the Financial Time. This image displays what the Financial Times feed looks like in the RSS Publisher app.](../../../images/rss-application-default-view.png)

You've learned how to create, manage, and use RSS feeds. Great job!

In this chapter, you explored @product@'s asset framework. Any type of content in
Liferay is considered an asset and can utilize the features provided by the
asset framework: tags, categories, comments, ratings, and relationships. You
examined the Asset Publisher application and looked at the many configuration
options for choosing what kinds of assets to display and how to display them.
You saw that the Asset Publisher application is designed to integrate with the
Tags Navigation and Categories navigation applications to allow users to browse
content more easily. You also learned about the Display Page attribute of web
content, the Content Display Page page template, and canonical URLs for assets.
Assets can have display page associated with them so that the full view of the
asset is displayed on the display page. The display page of an asset is used in
the asset's canonical URL.
