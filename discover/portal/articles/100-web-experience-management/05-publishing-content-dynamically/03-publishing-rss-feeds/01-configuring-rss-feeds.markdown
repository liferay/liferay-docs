# Configuring RSS Feeds [](id=configuring-rss-feeds)

To manage a Liferay site's RSS feeds, navigate to your Site's Site
Administration &rarr; *Content* page and click *Web Content*. Site
administrators can use this Web Content menu option to manage their site's web
content, including web content structures and templates, which you learned in
the 
[Creating Web Content](/discover/portal/-/knowledge_base/7-1/creating-web-content) 
section. Site administrators can also use this option to manage their site's RSS
feeds. To add a new feed:

1.  Go to the *Feeds* tab.

2.  Click the *Add Feed* button.

3.  Enter a *Name*, select a *Target Page*, and select a *Web Content 
    Structure* for the feed.

A feed's target page serves two purposes:

1. The site the target page belongs to determines which web content articles
   appear in the feed. For example, if the target page belongs to the Marketing
   site, only web content articles belonging to the Marketing site appear in the
   feed.

2. The target page is the page where "orphaned" web content articles are
   displayed. Orphaned web content articles have been published in your Site but
   are not displayed in specific Web Content Display applications. Liferay RSS
   feeds can provide links to any published web content articles, both orphaned
   articles and articles that have been configured to be displayed in specific
   Web Content Display applications. For articles that have been configured to
   be displayed, the RSS feeds' links point to the Liferay page of that app. For
   orphaned articles, the RSS feeds' links point to the feed's target page. When
   users click on such links for orphaned articles, the full content of the
   orphaned article is displayed on the target page.

![Figure 1: To create a new RSS feed, you only need to specify a name, target page, and web content structure. Of course, you can also configure other features of the feed such as its permissions, web content constraints, and presentation settings.](../../../../images/web-content-new-feed.png)

To specify a target page, you must enter the target page's friendly URL. Note
that friendly URLs don't include the host name. For example, the friendly URL
of a public page called *Welcome* belonging to a Site called *Marketing* might
look like this: `/web/marketing/welcome`. Optionally, you can specify a target
portlet ID. This would be the portlet ID of a Web Content Display application on
the target page in which orphaned web content should be displayed. The
application must exist or else the content isn't displayed. The URL field
contains the address of your RSS feed. It appears after you've actually created
the feed by clicking *Save*.

The final two sections of the *Add Feed* form are for customizing the web
content articles that appear in your feed.

1. Web Content Constraints selects a web content structure to filter the
   articles that appear in your feed. This is useful since all web content
   articles are created using web content structures.

2. Presentation Settings customizes additional details about your feed and how
   articles are displayed in your feed. Leave the Feed Item Content set to *Web
   Content Description* if you want a description of each article to appear in
   your feed. Set it to *Rendered Web Content: Use Default Template* if you want
   the full content of each article to appear in the feed. Customizing the Feed
   Type allows you to choose which web feed language to use for your feed. You
   can choose *Atom 1.0* (the default), *RSS 1.0*, or *RSS 2.0*. Customize the
   *Maximum Items to Display* to choose the maximum number of articles should
   appear in your feed at one time. Leave the Order By Column set to *Modified
   Date* to have articles arranged in order from the last time they were
   published or modified. You can set the Order by Column to *Display Date* if
   you want to have articles arranged in order from the time they were
   configured to be displayed in a specific Web Content Display application.
   Lastly, you can leave the Order by Type set to *Ascending* to have the oldest
   articles at the top of the feed or you can set it to *Descending* to have the
   newest articles at the top of the feed.

When you're done configuring your RSS feed, click *Save* to create your feed.

Once one or more feeds have been created, they'll appear in a list in the Feeds
tab. You can edit existing feeds using the same form used for creating them. 
The main difference is that when you edit an existing feed, the URL field is 
populated. Copy this URL into a new browser tab or window to test your feed. 
From the Feeds popup window, you can also customize the permissions of feeds or 
delete feeds.

It's possible to completely disable RSS feeds at the instance level. You can do
this by setting the `rss.feeds.enabled` property to `false` in your
`portal-ext.properties` file. By default, it's set to `true`. If you keep the
default, RSS enabled, you can make several other RSS property customizations.
Please refer to the
[RSS section](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#RSS)
of your `portal.properties` file for details.
