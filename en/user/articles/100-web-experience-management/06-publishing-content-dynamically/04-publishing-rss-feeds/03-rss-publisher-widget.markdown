---
header-id: the-rss-publisher-widget
---

# The RSS Publisher Widget

[TOC levels=1-4]

The RSS Publisher widget displays RSS feeds. If you're looking for a web-based
RSS reader, look no further: just add the RSS Publisher widget to one your
personal Site's private pages, and *voila*! You have your own personal RSS reader.
You can select the RSS feeds the widget displays and how it displays them. The
RSS Publisher widget can also be placed on Sites' public or private pages to
make feeds available to guests or Site members, respectively. In these cases,
make sure that only Site administrators have permission to customize the RSS
widget and select feeds to be displayed. 

![Figure 1: The RSS Publisher widget lets you display RSS feeds of your choosing.](../../../../images/rss-widget-default-view.png)

+$$$

**Note:** If you run your server behind a proxy, you must set the appropriate
Java proxy settings (such as `http.proxyHost=` and `http.proxyPort=`) in your
`setenv` script or in your `system-ext.properties`. Without these properties,
the RSS Publisher widget can't access any RSS feeds.

$$$

Note that the RSS Publisher widget is deprecated. In Liferay CE Portal 7.1 GA2+, 
and Liferay DXP 7.1 FP4+, the widget is available from the *Add* 
(![Add](../../../../images/icon-add-app.png)) 
&rarr; *Widgets* &rarr; *News* menu. However, the widget is hidden in earlier 
releases of Liferay CE Portal 7.1 and Liferay DXP 7.1. In these releases, you 
must therefore make the widget visible via a configuration file. The next 
section shows you how to do this. 

## Using the RSS Publisher Widget

You can add the RSS Publisher widget to a page from the *Add* 
(![Add](../../../../images/icon-add-app.png)) 
&rarr; *Widgets* &rarr; *News* menu. Once you've done so, open the widget's 
Configuration menu by clicking on the *Options* icon
(![Options](../../../../images/icon-app-options.png)) at the top-right corner of the
widget and selecting *Configuration*. 

![Figure 2: The RSS Publisher widget's configuration lets you customize how the widget displays RSS feeds.](../../../../images/rss-widget-config.png)

![Figure 3: You can also use the RSS Publisher widget's configuration to specify which feeds to display.](../../../../images/rss-widget-config-feeds.png)

By default, the RSS Publisher widget displays one feed. In the *Feeds* section, 
add or remove a feed via the plus or minus buttons, respectively. To add a feed, 
enter its URL and title in the respective fields. If you leave the *Title* field 
blank, the feed's default title is used (the *Title* field is for custom 
titles). 

In the top section, use the following toggles to enable/disable the display of 
the feed's details:

-   Show Feed Title
-   Show Feed Published Date
-   Show Feed Description
-   Show Feed Image
-   Show Feed Item Author

You can also select the number of entries and expanded entries that should be
displayed per feed. Expanded entries show more of an article's actual content
than regular entries. By default, each feed shows four entries per feed and
eight expanded entries per feed. You can set the feed image alignment to
control whether feed images appear to the right or left of the text. By default,
the feed image alignment is set to *Right*. 
