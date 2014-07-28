# Defining Content Relationships [](id=defining-content-relationships-lp-6-2-use-useportal)

Related Assets was a feature introduced in Liferay 6.1 that enables you to
connect an asset to other assets within the same site or to global assets, even
if they don't share any tags and aren't in the same category. We've already seen
that you can show related assets within the display for a specific asset, and
with the Related Assets portlet you can show links to any assets which are
related to content displayed on that page.

The Related Assets portlet is based on the Asset Publisher and possesses
essentially the same interface with one key difference. The Asset publisher
displays any content that meets the criteria selected in the portlet
configuration. The Related Assets portlet only displays content that meets the
criteria, and also is listed as a related asset for a piece of content that is
currently published on the page where it is placed. Let's take a look at the
Related Assets portlet.

As a prerequisite for the Related Assets portlet to display related assets, you
have to configure it to show the content you want displayed. To do this, go to
the Asset Publisher portlet and select the *gear* icon in the upper right
corner of the portlet. Under the *Setup* tab, set type of asset(s) to display
using the *Asset Type* menu. The default value is set to *Any*. You can narrow
the scope of the portlet to display any single category of asset type or select
multiple assets from the menu.

Filter options let you set minimum requirements for displaying assets by their
categories, tags, and custom fields. Ordering and Grouping allows you to
organize assets using the same criteria. Display settings allow you to customize
how assets are shown in the portlet. They can be listed by title, in a table,
by abstract or full content. You can convert assets to different document types
like ODT, PDF, and RTF. You can choose to show various metadata fields such as
author, modification date, tags, and view count. You can even enable RSS
subscriptions and customize their display settings.

When you are finished setting the Source and Filter options, click *Save*. But
hold on a minute. You saw the message that says, `You have successfully updated
the setup`, but there still aren't any assets displayed in the related assets
portlet. Why? You cannot see any related assets until you select an
asset in the Asset Publisher.

![Figure 6.19: Select an asset in the Asset Publisher to see its related assets displayed in the Related Assets portlet. In the screenshot, we renamed the three Related Assets portlets on the left and updated their configurations.](../../images/related-assets-portlet-after.png)

Once you select an asset, its related assets are displayed in the Related Assets
portlet, as in the image above.
