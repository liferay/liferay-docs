# Configuring Asset Publisher Subscriptions [](id=configuring-asset-publisher-subscriptions)

The Asset Publisher application supports two kinds of subscriptions: RSS
subscriptions and email subscriptions. To enable subscriptions, click the Asset
Publisher's Options icon and select *Configuration*. In the configuration
window, open the Subscriptions tab of the Setup tab. There are two options:
*Enable RSS Subscription* and *Enable Email Subscription*.

Enabling RSS Subscription creates an RSS feed containing links to all of the
assets that the Asset Publisher is configured to display. A link to this RSS
feed appears at the bottom of the Asset Publisher application. This option is
only available when the *Dynamic* Asset Selection is configured.

![Figure 4: When RSS subscriptions have been enabled for an Asset Publisher application, a link to the Asset Publisher's RSS feed appears. Users can subscribe to the Asset Publisher's RSS feed using their preferred RSS reader.](../../../images/asset-publisher-rss.png)

Enabling Email Subscription adds a *Subscribe* link to the Asset Publisher.
Users wishing to be notified of newly published assets can click on this link to
be added to the subscription list. @product@ periodically checks for new assets
and sends emails to subscribed users informing them about the new assets. By
default, Liferay performs this check every twenty-four hours.

<!-- but this can be
customized by adding the following property to your `portal-ext.properties` file
and changing the number:

    asset.publisher.check.interval=24

Also by default, Liferay limits the number of assets that are retrieved from the
database during this check to twenty. If you're interested in optimizing
Liferay's performance, you can lower this limit. If you're not concerned about
performance, you can remove this limit entirely. To customize the number of
assets about which Liferay notifies subscribed users, add the following property
to your `portal-ext.properties` file and change the number:

    asset.publisher.dynamic.subscription.limit=20

-----

This info needs to be updated once available. These properties are no longer in
portal.properties, but they are not available in System Settings either. Once
they're available, we'll need to document how to change these settings. -Cody

-->

Next, you'll look at Display Pages.

## Content Display Pages [](id=content-display-pages)

If you've been using @product@ for a while, you might have noticed something about
how @product@ handles web content--content is never tied directly to a page. While
this can be useful (because it means that you don't have to recreate content if
you want to display the same thing on multiple pages), it also means that you
don't have a static URL for any web content, which is bad for search engine
optimization.

As an improvement, Liferay introduced the concept of *display pages* and
*canonical URLs*. Each web content entry in Liferay has a canonical URL,
which is the official location of the content that is referenced any time the
content is displayed. A display page can be any page with an asset publisher
configured to display any content associated with the page. When adding or
editing web content, you can select a display page, but only pages with
a configured asset publisher are available for selection.

To create a display page, you can create a page yourself, add an Asset Publisher
app, and configure it yourself. Alternatively, you can use the *Content Display
Page* page template included with @product@. If you're creating a Display Page
manually, once you've added an Asset Publisher application to the page, open its
configuration window. Then navigate to the Display Settings tab and check the
*Set as the Default Asset Publisher for This Page* box. Also, for its display
settings, set the Display Style to *Simple* and the Asset Link Behavior to *View
in Context*.

+$$$

**Note:** Web content linked in the Asset Publisher can be viewed by clicking
their asset links. With the *View in Context* behavior checked, the link
displays the web content in its configured display page. If the web content does
not have a configured display page, it is displayed in the Web Content Display
application to which the asset belongs.

$$$

You may now be thinking, "Wait, you just told me that each web content item has
its own URL, and that this is somehow related to pages where we display a whole
bunch of content on the same page?" That's right. Just watch--create a display
page called *My Web Content Display Page* somewhere in Liferay using the
*Content Display Page* template. Make sure the *Inherit Changes* selector is not
selected. Now, on a different page, add a Web Content Display application. Click
the *Add* button, enter a title and some content, click on *Display Page* at the
right, and select the Display Page you just created. Then click *Publish*.

![Figure 5: You can select a display page for a web content article when creating or editing one.](../../../images/web-content-display-page.png)

In the Asset Publisher of the *My Web Content Display Page*, click the *Read
More* link to display the full content. Notice that the canonical URL for
content appears in your browser's address bar. If you create your own custom
display page, any additional applications that you place on the page are
displayed along with the content when you access it via the canonical URL. If
you used the *Content Display Page* page template for your display page, it not
only features a configured Asset Publisher application but also a Tags
Navigation, a Categories Navigation, and a Search application. These tools help
users to quickly identify relevant content.

![Figure 6: A canonical URL for a *Lunar Resort Information* page would look similar to this screenshot.](../../../images/web-content-canonical-url.png)

Next, you'll learn about publishing RSS feeds.
