# Asset Framework 

Liferay's asset framework is a system that allows you to add common
functionality to custom content types used in your application. For example, if
you've built an event management application that lets you operate on a listing
of upcoming events, it might be nice to be able to tag the events or categorize
them, to make event entries more self-descriptive. And you might also want to
let users comment on them. 

Tags, categories, and comments are just a few of the common functionalities that
Liferay's asset framework offers. And the simplicity of the asset framework
lets you infuse your application with these features in no time. 

As background, the term *asset* is a generic term that refers to any type of
content, such as text, an external file, a URL, an image, or any kind of
electronic record. When Liferay's tutorials use the term *asset*, they're
referring to some type of content. For example, assets can be documents, blog
entries, bookmarks, wiki pages, or anything you create in your applications. 

The Learning Path
[Asset Enabling Custom Entities](/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)
takes you through the fundamentals of enabling an example application's custom
entities to use the asset framework. If you haven't travelled through that
Learning Path, we recommend you do so before continuing with these tutorials, as
they explore the details of leveraging the framework's various features. 

Here are some features that your users can leverage, as you implement them
in your app using the asset framework: 

-   Associate tags to custom content types. Users can create and assign new
    tags, or use existing tags. 
-   Associate categories to custom content types. 
-   Manage tags from the Control Panel. Administrators can even merge tags. 
-   Manage categories from the Control Panel. This includes the ability to
    create category hierarchies. 
-   Associate comments with assets. 
-   Rate assets, using a five star rating system. 
-   Assign social bookmarks to assets. Bookmark types include tweets, Facebook
    likes, and +1 (Google Plus). 
-   Add custom fields to assets. 
-   Relate assets to one another. 
-   Flag an asset's content as inappropriate. 
-   Track the number of times an asset is viewed. 
-   Integrate workflow with assets. 
-   Publish asset content using the Asset Publisher portlet. The Asset Publisher
    lets you publish dynamic asset lists or manually selected asset lists. It
    can also show an asset summary view with a link to the full view. 

At this point, you might be saying, "Liferay's asset framework sounds great, but
how do I leverage all of its awesome features?" Excellent question, young
padawan, and perfect timing! 

Before diving head first into this section's tutorials that show you how to
implement the specific features, you must implement a way to let the framework
know whenever any of your custom content entries is added, updated, or deleted.
This section's next tutorial covers that. From that point onward, the tutorials
show you how to leverage the asset framework in your UI. You can use a set of
taglibs to provide widgets that allow users to exercise all of the features that
have been mentioned, like entering comments, adding tags, and adding categories
to an asset. It's time to start your asset framework training! 

## Related Topics

[Asset Enabling Custom Entities](/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)
