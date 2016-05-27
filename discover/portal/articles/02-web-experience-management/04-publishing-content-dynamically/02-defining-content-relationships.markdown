# Defining Content Relationships [](id=defining-content-relationships)

Related Assets is a feature that enables you to connect an asset to other assets
within the same site or to global assets, even if they don't share any tags and
aren't in the same category. The Related Assets application only displays
content that meets the criteria you've specified, and also is listed as a
related asset for a piece of content that is published using the Asset
Publisher. You'll learn more about the Asset Publisher in the
[Publishing Assets](/discover/portal/-/knowledge_base/7-0/publishing-assets)
section. For now, you'll focus on how to define relationships between assets so
when you begin publishing assets, the Related Assets application can
successfully display those relationships. You'll take a closer look at the
Related Assets application next.

By default, the Related Assests application is configured to display any related
asset of the asset selected in the Asset Publisher. You can configure what
content relationships to display, if you prefer not to show every related asset
for the selected asset. To do this, go to the Related Assets app and select the
*Options* icon (![Options](../../../images/icon-options.png)) in the upper right
corner of the application and click *Configuration*. Under the *Setup* &rarr;
*Asset Selection* tab, set the type of asset(s) to display using the *Asset
Type* menu. The default value is set to *Any*. You can narrow the scope of the
app to display any single category of asset type or select multiple assets from
the menu.

Filter options let you set minimum requirements for displaying assets by their
categories, tags, and custom fields. Ordering and Grouping allows you to
organize assets using the same criteria. Display settings allow you to customize
how assets are shown in the app. They can be listed by title, in a table,
by abstract, or full content. You can convert assets to different document types
like ODT, PDF, and RTF. You can choose to show various metadata fields such as
author, modification date, tags, and view count. You can even enable RSS
subscriptions and customize their display settings. When you're finished setting
the Source and Filter options, click *Save*.

Now that you've configured the Related Assets application to display specific
content types, you need to actually define the relationships for your assets.
Next, you'll go through a simple example of defining related assets for a web
content article and then display those related assets.

Suppose you own a gift shop at the Lunar Resort, and you'd like all your shop's
assets to display when an asset is clicked. You'll need to define relationships
between your content, so when an asset is clicked, its related assets are
displayed alongside the clicked asset. For this example, create a blog entry
explaining your gift shop's new apparel and a photo of the moon, just so
consumers are aware that you offer the *only* gift shop on a desolate rock
orbiting the Earth!

Lastly, begin creating a web content article describing your shop. Once you've
given your article a title and some content, open the *Related Assets* dropdown
menu. Click *Select* and choose *Blogs Entry* and select the blog you created.
Click *Select* again and choose *Basic Document* and select the photo of the
moon. Click *Publish* to publish your web content article.

Now that those assets are created, you'll want to relate the blog entry and
photo to your web content article. Navigate to your article in Site
Administration &rarr; *Content* &rarr; *Web Content*.

You've now defined relationship with your three assets. Click the *Add* icon
(![Add](../../../images/icon-control-menu-add.png)) at the top of your page in
the Control Menu and select *Applications* and add the Related Assets and Asset
Publisher applications to the page. Wait a minute; there aren't any assets
displayed in the related assets application. Why? You cannot see any related
assets until you select an asset in the Asset Publisher.

![Figure 1: Select an asset in the Asset Publisher to see its related assets displayed in the Related Assets application.](../../../images/related-assets-app-1.png)

Once you select an asset, its related assets are displayed in the Related Assets
app, as in the image above. If you want to get even more detailed with how your
related assets are displayed, you can place two Related Assets applications on
the page and name one *Related Blogs* and the other *Related Photos*. To change
the name of an application's title, click the application's *Options* icon and
select *Look and Feel Configuration*. Select the *Use Custom Title* checkbox and
provide the custom title.

![Figure 2: Related Assets applications can be configured to display specific content.](../../../images/related-assets-app-2.png)

Next, you'll learn how to use @product@'s Geolocation feature in your assets.
