# Asset Framework [](id=asset-framework)

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
tutorial section.

The tutorials that follow in this section explore how to leverage the asset
framework's various features. Here are some features that you'll give your users
as you implement them in your app: 

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

## Related Topics [](id=related-topics)

[What is Service Builder](/develop/tutorials/-/knowledge_base/7-1/what-is-service-builder)

[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-1/service-builder-persistence)

[Configuration](/develop/tutorials/-/knowledge_base/7-1/configuration)
