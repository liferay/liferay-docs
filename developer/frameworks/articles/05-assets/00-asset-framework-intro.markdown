# Asset Framework

The asset framework powers the core Liferay features so you can add them to your
application. For example, if you build an event management application that
displays a list of upcoming events, you can use the asset framework to let users
add tags, categories, or comments to make entries more self-descriptive. 

As background, the term *asset* refers to any type of content in the portal.
This could be text, a file, a URL, an image, documents, blog entries, bookmarks,
wiki pages, or anything you create in your applications. 

The asset framework tutorials assume that you've used Liferay's Service Builder
to generate your persistence layer, that you've implemented permissions on the
entities that you're persisting, and that you've enabled them for search and
indexing. You can learn more about Liferay's Service Builder and how to use it
in the
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)
tutorial section. After that is completed, you can get started asset enabling 
your application.

This section explores how to leverage the asset framework's various features. 
Here are some features that you'll give your users as you implement them in 
your app: 

-  Extensively render your assets.
-  Associate tags to custom content types. Users can create and assign new
   tags or use existing tags. 
-  Associate categories to custom content types. 
-  Manage tags from the Control Panel. Administrators can even merge tags. 
-  Manage categories from the Control Panel. This includes the ability to
   create category hierarchies. 
-  Relate assets to one another. 

Before diving head first into the tutorials, you must implement a way to let the
framework know whenever any of your custom content entries is added, updated, or
deleted. The next tutorial covers that. From that point onward, each tutorial
shows you how to leverage a particular asset framework feature in your UI.
It's time to start your asset framework training!


##Persistence Operations for Assets 

To use Liferay's asset framework with an entity, you must inform the 
asset framework about each entity instance you create, modify, and delete. In
this sense, it's similar to informing 
[Liferay's permissions framework](/develop/tutorials/-/knowledge_base/7-1/defining-application-permissions)
about a new resource. All you have to do is invoke a method of the asset
framework that associates an `AssetEntry` with the entity so Liferay can keep
track of the entity as an asset. When it's time to update the entity, you update
the asset at the same time. 

To leverage assets, you must also implement indexers for your portlet's
entities. Liferay's asset framework uses indexers to manage assets. 

## Rendering an Asset

Once you add your asset to the framework, you can render the asset
using the Asset Publisher application. The default render, however, only
displays the asset's title and description text. Anything else requires
additional coding. For instance, you might want these additional things:

-  An edit feature for modifying an asset.
-  View an asset in its original context (e.g., a blog
   in the Blogs application; a post in the Message Boards application).
-  Embed images, videos, and audio.
-  Restrict access to users who do not have permissions to interact with the
   asset.
-  Allow users to comment on the asset. 

You can dictate your asset's rendering capabilities by providing the *Asset
Renderer* framework. 

## Implementing Asset Categorization and Tagging

Next, you'll enable tags and categories entities in the UI through
a set of JSP tags. Before beginning, your entities should be
[asset-enabled](/develop/tutorials/-/knowledge_base/7-1/adding-updating-and-deleting-assets)
and you should have asset renderers enabled for them.

![Figure 1: Adding category and tag input options lets authors aggregate and label custom entities.](../../images/asset-fw-categories-and-tags-options.png)

## Relating Assets

Relating assets connects individual pieces of content across your site or
portal. This helps users discover related content, particularly when there's an
abundance of other available content. For example, assets related to a web
content article appear alongside that entry in the Asset Publisher application.

![Figure 1: You and your users can find it helpful to relate assets to entities, such as this blogs entry.](../../images/asset-related-content-asset-publisher.png)

Now you'll learn how to provide a way for authors to relate content.
This tutorial assumes that you've
[Adding, Updating, and Deleting Assets](/develop/tutorials/-/knowledge_base/7-1/adding-updating-and-deleting-assets)
your application. If you've already done this, go ahead and begin relating your
assets!

## Implementing Asset Priority

The 
[Asset Publisher](/discover/portal/-/knowledge_base/7-1/publishing-assets) 
lets you order assets by priority. For this to work, however, users must be able
to set the asset's priority when creating or editing the asset. For example,
when creating or editing web content, users can assign a priority in the
Metadata section's Priority field. 

![Figure 1: The Priority field lets users set an asset's priority.](../../images/web-content-categorization.png)
