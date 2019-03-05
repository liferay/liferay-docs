---
header-id: social-api
---

# Social API

You can use the social API to integrate @product@'s social features with your 
apps. Here, you'll learn about the following topics: 

-   [Social Bookmarks](#social-bookmarks)
-   [Adding Comments to Your App](/develop/tutorials/-/knowledge_base/7-2/adding-comments-to-your-app)
-   [Ratings](#ratings)
-   [Flagging Inappropriate Asset Content](/develop/tutorials/-/knowledge_base/7-2/flagging-inappropriate-asset-content)

## Social Bookmarks

To apply social bookmarks to your app's content, you must use the 
`liferay-social-bookmarks` taglib. This taglib contains the 
`liferay-social-bookmarks:bookmarks` tag, which adds the social bookmarks 
component. This tag contains these attributes: 

`className`: The entity's class name. 

`classPK`: The entity's primary key. 

`displayStyle`: The social bookmarks' display style. Possible values are 
`inline`, which displays them in a row, and `menu`, which hides them in a menu. 

`title`: A title for the content being shared. This attribute is often populated 
by calling the entity's `getTitle()` method (or other method that retrieves the 
title). 

`types`: A comma-delimited list of the social media services to use (e.g., 
`facebook,twitter`). To use every social media service available in the portal, 
omit this attribute or use `<%= null %>` for its value. 

`url`: A URL to the portal content being shared. The `PortalUtil` method 
`getCanonicalURL` is often called to populate this attribute. This method 
constructs an SEO-friendly URL from the page's full URL. For more information, 
see the method's
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/PortalUtil.html#getCanonicalURL-java.lang.String-com.liferay.portal.kernel.theme.ThemeDisplay-com.liferay.portal.kernel.model.Layout-). 

For instructions on using this tag, see 
[Applying Social Bookmarks](/develop/tutorials/-/knowledge_base/7-2/applying-social-bookmarks). 
For instructions on creating your own social bookmarks, see 
[Creating Social Bookmarks](/develop/tutorials/-/knowledge_base/7-2/creating-social-bookmarks). 

![Figure 1: With `displayStyle` set to `inline`, the first three social bookmarks appear in a row and the rest appear in a menu.](../../../images/social-bookmarks-inline.png)

![Figure 2: With `displayStyle` set to `menu`, all social bookmarks appear in the *Share* menu.](../../../images/social-bookmarks-menu.png)

## Ratings

[The asset framework](/develop/tutorials/-/knowledge_base/7-2/asset-framework) 
supports a content rating system. This feature appears in many of @product@'s 
built-in apps. For example, users can rate articles published in the Blogs app. 
There are three different rating types: 

-   Likes
-   Stars (five, by default)
-   Thumbs (up/down)

To enable ratings in your app, you must use the `liferay-ui:ratings` tag and set 
its `type` attribute to the rating type (`like`, `stars`, or `thumbs`). For 
instructions on this, see 
[Rating Assets](/develop/tutorials/-/knowledge_base/7-2/rating-assets). 

### Rating Type Selection

Admins can select the rating type for an app's entities via the Control Panel 
and Site Administration. Portal admins can set the default rating type for the 
portal, while Site admins can override the default rating type for their Site. 

A ratings-enabled app must define its rating type in an OSGi component that 
implements the `PortletRatingsDefinition` interface. This class declares the 
usage of ratings (specifying the portlet and the entity) and the default rating 
type (that can be overridden by portal and site admins). This interface has two 
methods that you must implement: 

`getDefaultRatingsType`: Returns the entity's default rating type, which portal 
and site admins can override. You can do this via the 
[`RatingsType`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/RatingsType.html) 
enum, which contains `LIKE`, `STARS`, or `THUMBS`. 

`getPortletId`: Returns the portlet ID of the main portlet that uses the entity. 
You can do this via the 
[`PortletKeys`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/PortletKeys.html) 
enum, which defines many constants that correspond to the portlet IDs of the 
built-in portlets. 

To add support for rating type selection in your app, follow the instructions in 
[Implementing Rating Type Selection](/develop/tutorials/-/knowledge_base/7-2/implementing-rating-type-selection). 
Once you've done so, you can configure the default rating type via the Control 
Panel at *Configuration* &rarr; *Instance Settings* &rarr; *Social*. To override 
the default values for a site, go to Site Administration (your Site's menu) 
&rarr; *Configuration* &rarr; *Site Settings* &rarr; *Social*. 

### Rating Value Transformation

The database stores normalized rating values. This permits switching between 
rating types without modifying the underlying data. When administrators change 
an entity's rating type, its best match is computed. Here's a list of the 
default transformations between rating types: 

1.  When changing from stars to: 

    **Like:** A value of 3, 4, or 5 stars is considered a like; a value of 1 
    or 2 stars is omitted. 

    **Thumbs up/down:** A value of 3, 4, or 5 stars is considered a thumbs up; 
    a value of 1 or 2 stars is considered a thumbs down.

2.  When changing from thumbs up/down to: 

    **Like:** A like is considered a thumbs up. 

    **Stars:** A thumbs down is considered 1 star; a thumbs up is considered 5 
    stars. 

3.  When changing from like to: 

    **Stars:** A like is considered 5 stars. 

    **Thumbs up/down:** A like is considered a thumbs up. 

There may be some cases, however, where you want to apply different criteria to 
determine the new rating values. A mechanism exists that permits this, but it 
modifies the stored rating values. To define such transformations, create an 
OSGi component that implements 
[`RatingsDataTransformer`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/transformer/RatingsDataTransformer.html). 

| **Note:** The portal doesn't provide a default `RatingsDataTransformer` 
| implementation. Unless you provide such an implementation, the stored rating 
| values always remain the same while the portal interprets existing values for 
| the selected rating type. 

When implementing `RatingsDataTransformer`, implement the `transformRatingsData` 
method to transform the data. This method's arguments include the `RatingsType` 
variables `fromRatingsType` and `toRatingsType`, which contain the rating type 
to transform from and to, respectively. These values let you write your custom 
transformation's logic. You can write this logic by implementing the interface 
`ActionableDynamicQuery.PerformActionMethod` as an anonymous inner class in the 
`transformRatingsData` method, implementing the `performAction` method with your 
transformation's logic. 

For instructions on implementing `RatingsDataTransformer`, see 
[Customizing Rating Value Transformation](/develop/tutorials/-/knowledge_base/7-2/customizing-rating-value-transformation). 
