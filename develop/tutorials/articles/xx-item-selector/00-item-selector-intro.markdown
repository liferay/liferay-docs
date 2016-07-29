# Item Selector API [](id=item-selector-api)

The Item Selector API is a framework introduced in Liferay 7 that provides a 
reusable, extendable, interface that allows users to select any kind of entity 
in a consistent manner.

For example, you may use the item selector to add an image to your web content 
in Liferay, as shown in the figure below:

![Figure 1:](../../images/item-selector-dialog-02.png)

Using the item selector's APIs, you can use this same interface in your own app. 

When selecting entities(items), there may be multiple views that you need to 
work with i.e. Documents and Media, Blogs, images from a third party provider, 
dragging and dropping selections, views for video files, users, sites etc. The 
Item Selector API provides an interface that renders the views you need for the 
type of entity you selected, keeping things organized and consistent, making 
your users very happy.

You can use the Item Selector's APIs to select existing entities that are 
already integrated with Liferay, such as documents from Documents and Media or 
sites, or you can create your custom entities and views for the item selector. 
Perhaps in your app, you would like users to be able to select images from an 
external image repository. You can customize the Item Selector's selection 
experience to include this new view. Maybe you have a type of entity that isn't 
provided by default for the Item Selector. You can create new selectable 
entities that can be selected by any other third party application 
via the Item Selector.

In this section of tutorials, you'll learn how to use the Item Selector's APIs
to select exisiting entities in Portal, create custom entities, and create
custom selection views for the item selector.
