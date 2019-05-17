---
header-id: asset-framework
---

# Asset Framework

[TOC levels=1-4]

The asset framework is behind many of Liferay's most powerful features. It 
provides tools for displaying and interacting with various types of content and 
data. For example, if you build an event management application that
displays a list of upcoming events, you can use the asset framework to let users
add tags, categories, or comments to make entries more self-descriptive. Using 
the asset framework is also the first step for integrating other important 
frameworks like Segmentation and Personalization or Workflow.

As background, the term *asset* refers to any type of content in the portal.
This could be text, a file, a URL, an image, documents, blog entries, bookmarks,
wiki pages, or anything you create in your applications. 

The asset framework tutorials assume that you've used Liferay's Service Builder
to generate your persistence layer, that you've implemented permissions on the
entities that you're persisting, and that you've enabled them for search and
indexing. You can learn more about Liferay's Service Builder and how to use it
in the
[Service Builder](/docs/7-2/appdev/-/knowledge_base/appdev/service-builder)
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

There are several steps to creating an asset an taking full advantage of the 
asset framework.

##Persistence Operations for Assets 

To use Liferay's asset framework with an entity, you must inform the 
asset framework about each entity instance you create, modify, and delete. In
this sense, it's similar to informing 
[Liferay's permissions framework](/docs/7-2/frameworks/-/knowledge_base/frameworks/defining-application-permissions)
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
Renderer* framework. There are two prerequisites for asset enabling an 
application:

1.  The application must store asset data. Applications that store a data model
    meet this requirement.

2.  The application must contain at least one non-instanceable portlet. `Edit` 
    links for the asset cannot be generated without a non-instanceable portlet.
    
Some applications may consist of only one non-instanceable portlet, while others
may consist of a both instanceable and non-instanceable portlets. If your 
application does not currently include a non-instanceable portlet, adding a 
configuration interface through a panel app both enhances the usability of 
the application, and meets the requirement for adding a non-instanceable 
portlet to the application. See our tutorial on 
[Adding Custom Panel Apps](/docs/7-2/-/knowledge_base/customizing-the-product-menu#adding-custom-panel-apps)
to learn how to add one.


After you have met all the prerequisites, there are two things you must do to 
get your asset renderer functioning properly for your asset:

1.  Create an asset renderer for your custom asset.

2.  Create an asset renderer factory to create an instance of the asset renderer
    for each asset entity.



## Asset Features

Once you have done the necessary work to have your assets in the persistance layer, and render them, you can start integrating a number of other features, like Tags, Categories, and Related Assets.

### Tags and Categories

Tags and Categories are two ways that you can organize and connect assets. Tags are simple ah hoc groups. Anything two entities that have the same tag are connected by that tag. Categories are a form of hierarchical organization where an administrator can define a number of categories for organization content, images, or other types of assets and use those categories to help users find what they're looking for.

![Figure 1: Adding category and tag input options lets authors aggregate and label custom entities.](../../images/asset-fw-categories-and-tags-options.png)

### Relating Assets

Relating assets connects individual pieces of content across your site or
portal. This helps users discover related content, particularly when there's an
abundance of other available content. For example, assets related to a web
content article appear alongside that entry in the Asset Publisher application.

![Figure 2: You and your users can find it helpful to relate assets to entities, such as this blogs entry.](../../images/asset-related-content-asset-publisher.png)

## Implementing Asset Priority

The 
[Asset Publisher](/docs/7-2/-/knowledge_base/publishing-assets) 
lets you order assets by priority. For this to work, however, users must be able
to set the asset's priority when creating or editing the asset. For example,
when creating or editing web content, users can assign a priority in the
Metadata section's Priority field. 

![Figure 3: The Priority field lets users set an asset's priority.](../../images/web-content-categorization.png)
