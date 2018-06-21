# Configuring Asset Publisher Subscriptions [](id=configuring-asset-publisher-subscriptions)

The Asset Publisher application supports two kinds of subscriptions: RSS
subscriptions and email subscriptions. To enable subscriptions, click the Asset
Publisher's Options icon and select *Configuration*. In the configuration
window, open the Setup tab's Subscriptions tab. There are two options:
*Enable RSS Subscription* and *Enable Email Subscription*.

Enabling RSS Subscription creates an RSS feed containing links to all assets
that the Asset Publisher is configured to display. A link to this RSS feed
appears at the bottom of the Asset Publisher application. This option is only
available when the *Dynamic* Asset Selection is configured.

![Figure 1: When RSS subscriptions have been enabled for an Asset Publisher application, a link to the Asset Publisher's RSS feed appears. Users can subscribe to the Asset Publisher's RSS feed using their preferred RSS reader.](../../../../images/asset-publisher-rss.png)

Enabling Email Subscription adds a *Subscribe* link to the Asset Publisher.
Users wishing to be notified of newly published assets can click on this link to
be added to the subscription list. @product@ periodically checks for new assets
and sends emails to subscribed users informing them about the new assets. By
default, Liferay performs this check every twenty-four hours.
