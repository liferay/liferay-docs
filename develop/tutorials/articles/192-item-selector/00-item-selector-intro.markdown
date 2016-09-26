# Item Selector API [](id=item-selector-api)

The Item Selector API is a framework introduced in Liferay 7 that provides a 
reusable, extendable, interface that allows users to select any kind of entity 
in a consistent manner.

For example, you may use the Item Selector to add an image to your web content 
in Liferay, as shown in the figure below:

![Figure 1:](../../images/item-selector-dialog-02.png)

The Item Selector's APIs allow to use this same framework in your own app. 

When selecting entities(items), there may be multiple views that you need to 
work with (e.g. Documents and Media, Blogs, images from a third party provider, 
dragging and dropping selections, views for video files, users, sites, etc.). 
The Item Selector API only renders the selection views that are applicable to 
the type of entity being selected, keeping things organized and consistent, 
making your users very happy.

You can use the Item Selector's APIs to select existing entities that are 
already integrated within Liferay, such as documents from Documents and Media, 
or you can create your own custom entities and views for the Item Selector.

Perhaps in your app, you would like users to be able to select images from an 
external image repository. You can customize the Item Selector to include this 
new view. Maybe you would like to create a new type of entity that isn't 
provided by default for the Item Selector. The item selector's APIs are fully
extensible, allowing you to enable this functionality.

<!-- This isn't really referenced in the tutorial at this point

You can refer to the [Item Selector Sample](https://github.com/robertoDiaz/liferay-portal/commit/4b355a8b0e93fa1a5ffa9d346e8acf8216204241)
bundle throughout this section of tutorials for an additional example. If you
wish, you may even use the sample bundle as a foundation to build your own item
selector extensions.

-->

In this section of tutorials, you'll learn how to use the Item Selector's APIs
to select exisiting entities in @product@, create custom entities, and create
custom selection views for the Item Selector.
