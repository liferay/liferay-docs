# Creating Android List Screenlets [](id=creating-android-list-screenlets)

It's very common for mobile apps to display lists. Liferay Screens 
lets you display asset lists and DDL lists in your Android app by using 
[Asset List Screenlet](/develop/reference/-/knowledge_base/7-1/assetlistscreenlet-for-android) 
and 
[DDL List Screenlet](/develop/reference/-/knowledge_base/7-1/ddllistscreenlet-for-android), 
respectively. Screens also includes list Screenlets for displaying lists of 
other Liferay entities like web content articles, images, and more. 
[The Screenlet reference documentation](/develop/reference/-/knowledge_base/7-1/screenlets-in-liferay-screens-for-android) 
lists all the Screenlets included with Liferay Screens. If there's not a list 
Screenlet for the entity you want to display in a list, you must create your 
own. A list Screenlet can display any entity from a Liferay instance. For 
example, you can create a list Screenlet that displays standard Liferay entities 
like `User`, or custom entities from custom Liferay apps. 

This tutorial uses code from the sample Bookmark List Screenlet to show you how 
to create your own list Screenlet. This Screenlet displays a list of bookmarks 
from Liferay's Bookmarks portlet. You can find this Screenlet's complete code 
[here in GitHub](https://github.com/liferay/liferay-screens/tree/master/android/samples/listbookmarkscreenlet). 

Note that because this tutorial focuses on creating a list Screenlet, it doesn't 
explain general Screenlet concepts and components. Before beginning, you should 
therefore read the following tutorials: 

- [Screens architecture tutorial](/develop/tutorials/-/knowledge_base/7-1/architecture-of-liferay-screens-for-android)
- [Basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-android-screenlets)

You'll create the list Screenlet by following these steps: 

1. [Creating the Model Class](/develop/tutorials/-/knowledge_base/7-1/creating-the-model-class)
2. [Creating the View](/develop/tutorials/-/knowledge_base/7-1/creating-the-view)
3. [Creating the Interactor](/develop/tutorials/-/knowledge_base/7-1/creating-the-interactor-0)
4. [Creating the Screenlet Class](/develop/tutorials/-/knowledge_base/7-1/creating-the-screenlet-class-0)

First though, you should understand how pagination works with list Screenlets. 

## Pagination [](id=pagination)

To ensure that users can scroll smoothly through large lists of items, list 
Screenlets support fluent pagination. Support for this is built into the list 
Screenlet framework. You'll see this as you construct your list Screenlet. 

Now you're ready to begin! 
