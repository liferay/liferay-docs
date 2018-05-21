# Using the RSS Publisher Application [](id=using-the-rss-feeds-application)

The RSS Publisher application lets you display any number of RSS feeds and
configure how they are displayed. If you're looking for a web-based RSS reader,
look no further: just add the RSS Publisher app to one your personal site's
private pages, and voila! You have your own personal RSS reader. Open the
application's Configuration menu to select the feeds to be displayed and
customize the display. The RSS Publisher app can also be placed on sites' public
or private pages to make feeds available to guests or site members,
respectively. In these cases, make sure that only site administrators have
permission to customize the RSS application and select feeds to be displayed.

+$$$

**Note:** If you are running your server behind a proxy, you must set the 
appropriate Java proxy settings (such as `http.proxyHost=` and `http.proxyPort=`) 
in your `setenv` script, or in your `system-ext.properties`. Without these 
properties, the RSS Publisher application can't access any RSS feeds.

$$$

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
