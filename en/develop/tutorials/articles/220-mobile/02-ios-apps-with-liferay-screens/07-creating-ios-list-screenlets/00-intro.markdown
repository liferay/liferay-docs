---
header-id: creating-ios-list-screenlets
---

# Creating iOS List Screenlets

[TOC levels=1-4]

It's very common for mobile apps to display lists. Liferay Screens 
lets you display asset lists and DDL lists in your iOS app by using 
[Asset List Screenlet](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-ios) 
and 
[DDL List Screenlet](/docs/7-1/reference/-/knowledge_base/r/ddllistscreenlet-for-ios), 
respectively. Screens also includes list Screenlets for displaying lists of 
other Liferay entities like web content articles, images, and more. 
[The Screenlet reference documentation](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) 
lists all the Screenlets included with Liferay Screens. If there's not a list 
Screenlet for the entity you want to display in a list, you must create your own 
list Screenlet. A list Screenlet can display any entity from a Liferay instance. 
For example, you can create a list Screenlet that displays standard Liferay 
entities like `User`, or custom entities from custom Liferay apps. 

The tutorials in this section use code from the sample Bookmark List Screenlet 
to show you how to create your own list Screenlet. This Screenlet displays a 
list of bookmarks from Liferay's Bookmarks portlet. You can find this 
Screenlet's complete code 
[here in GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet). 

Note that because this tutorial focuses on creating a list Screenlet, it doesn't 
explain general Screenlet concepts and components. Before beginning, you should 
therefore read the following: 

- [Creating iOS Screenlets](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)
- [Supporting Multiple Themes in Your Screenlet](/docs/7-1/tutorials/-/knowledge_base/t/supporting-multiple-themes-in-your-ios-screenlet)
- [Create and Use a Connector with Your Screenlet ](/docs/7-1/tutorials/-/knowledge_base/t/create-and-use-a-connector-with-your-screenlet)
- [Add a Screenlet Delegate](/docs/7-1/tutorials/-/knowledge_base/t/add-a-screenlet-delegate)
- [Creating and Using Your Screenlet's Model Class](/docs/7-1/tutorials/-/knowledge_base/t/creating-and-using-your-screenlets-model-class)

You'll create a list Screenlet by following these steps: 

1. Creating the Model class
2. Creating the Theme
3. Creating the Connector
4. Creating the Interactor
5. Creating the Delegate
6. Creating the Screenlet class

First though, you should understand how pagination works with list Screenlets. 

## Pagination

To ensure that users can scroll smoothly through large lists of items, list 
Screenlets support 
[fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination). 
Support for this is built into the list Screenlet framework. You'll see this as 
you construct your list Screenlet. 

## Creating the Model Class

Recall that a model class transforms each `[String:AnyObject]` entity Screens 
receives into a model object that represents the corresponding Liferay entity. 
For instructions on creating your model class, see the tutorial 
[Creating and Using Your Screenlet's Model Class](/docs/7-1/tutorials/-/knowledge_base/t/creating-and-using-your-screenlets-model-class). 
The example model class in that tutorial is identical to Bookmark List 
Screenlet's. 

Next, you'll create your list Screenlet's theme. 
