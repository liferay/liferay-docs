# Asset Framework [](id=asset-framework)

Liferay's asset framework is a system that allows you to add core Liferay 
features to your application. For example, if you've built an event management
application that displays a list of upcoming events, you can use the asset
framework to let users add tags, categories, or comments to make entries more
self-descriptive. 

Tags, categories, and comments are just a few of the features in Liferay's
asset framework. You'll also find it easy to use: you'll be infusing your
application with these features in no time. 

As background, the term *asset* refers to any type of content in the portal.
This could be text, a file, a URL, an image, documents, blog entries, bookmarks,
wiki pages, or anything you create in your applications. 

The asset framework tutorials assume that you've used Liferay's Service Builder
to generate your persistence layer, that you've implemented permissions on the
entities that you're persisting, and that you've enabled them for search and
indexing. You can learn more about Liferay's Service Builder and how to use it
in the
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/service-builder)
tutorial section.

<!-- If you've yet to do any of those things, you can see how each is done
in respective Learning Paths
[Writing a Data-Driven Application](/develop/learning-paths/mvc/-/knowledge_base/6-2/writing-a-data-driven-application),
[Setting Permissions](/develop/learning-paths/mvc/-/knowledge_base/6-2/setting-permissions),
and
[Enabling Search and Indexing](/develop/learning-paths/mvc/-/knowledge_base/6-2/enabling-search-and-indexing). 
Lastly, the Learning Path
[Asset Enabling Custom Entities](/develop/learning-paths/mvc/-/knowledge_base/6-2/asset-enabling-custom-entities)
takes you through the fundamentals of enabling an example application's custom
entities to use the asset framework. If you haven't traveled through that
Learning Path, we recommend you do so before continuing with the tutorials in
this section. -->

<!-- The paragraph above assumes there is a learning path to point to. Since
this content is not available currently, we're forced to just mention
prerequisites that we don't have docs for. -Cody -->

The tutorials that follow in this section explore the details of leveraging the
asset framework's various features. Here are some features that you'll give your
users as you implement them in your app: 

-  Extensively render your assets.
-  Associate tags to custom content types. Users can create and assign new
   tags or use existing tags. 
-  Associate categories to custom content types. 
-  Manage tags from the Control Panel. Administrators can even merge tags. 
-  Manage categories from the Control Panel. This includes the ability to
   create category hierarchies. 
-  Relate assets to one another. 
<!-- 
-  Associate comments with assets.
-  Rate assets, using a five star rating system. 
-  Assign social bookmarks to assets. Bookmark types include tweets, Facebook
   likes, and +1 (Google Plus). 
-  Add custom fields to assets. 
-  Flag an asset's content as inappropriate. 
-  Track the number of times an asset is viewed. 
-  Integrate workflow with assets. 
- Publish asset content using the Asset Publisher portlet. The Asset Publisher
   lets you publish dynamic asset lists or manually selected asset lists. It
   can also show an asset summary view with a link to the full view.
-->

At this point, you might be saying, "Liferay's asset framework sounds great, but
how do I leverage all of its awesome features?" Excellent question, and perfect
timing! 

Before diving head first into the tutorials, you must implement a way to let the
framework know whenever any of your custom content entries is added, updated, or
deleted. The next tutorial covers that. From that point onward, each tutorial
shows you how to leverage a particular asset framework feature in your UI.
It's time to start your asset framework training!

## Related Topics [](id=related-topics)

[What is Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)

[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-0/service-builder-persistence)

[Configuration](/develop/tutorials/-/knowledge_base/7-0/configuration)
