# Rendering an Asset [](id=rendering-an-asset)

Before you create a way to render your asset, make sure it's added to the asset
framework by following the
[Adding, Updating, and Deleting Assets](/develop/tutorials/-/knowledge_base/7-1/adding-updating-and-deleting-assets-for-custom-entities)
tutorial. Once you add your asset to the framework, you can render the asset
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
Renderer* framework. There are two things you must do to get your asset
renderer functioning properly for your asset:

1.  Create an asset renderer for your custom asset.

2.  Create an asset renderer factory to create an instance of the asset renderer
    for each asset entity.

![Figure 1: The asset renderer factory creates an asset renderer for each asset instance.](../../../images/asset-renderer-diagram.png)

You'll learn how to create an asset renderer and an asset renderer factory by
studying a Liferay asset that already uses both by default: Blogs. The Blogs
application offers many different ways to access and render a blogs asset.
You'll learn how a blogs asset provides an edit feature, comment section,
original context viewing (i.e., viewing an asset from the Blogs application),
workflow, and more. You'll also learn how it uses JSP templates to display
various blog views. The Blogs application is an extensive example of how an
asset renderer can be customized to fit your needs. 

If you want to create an asset and make it do more than display its title and
description, read on!

## Prerequisites for Asset Enabling and Application [](id=prerequisites-for-asset-enabling-and-application)
To asset-enable your application, you need two things: 

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
[Adding Custom Panel Apps](/knowledge_base/7-1/customizing-the-product-menu#adding-custom-panel-apps)
to learn how to add one.

Now you're ready to create an Asset Renderer.
