# Creating Android Screenlets [](id=creating-android-screenlets)

[The Screenlets](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android)
that come with Liferay Screens cover common use cases for mobile apps that use
Liferay. They authenticate users, interact with Dynamic Data Lists, view assets,
and more. However, what if there's no Screenlet for *your* specific use case? No
sweat! You can create your own. Extensibility is a key strength of Liferay
Screens. 

This tutorial explains how to create your own Screenlets. As an example, it
references code from the sample
[Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/android/samples/addbookmarkscreenlet), 
that saves bookmarks to Liferay's Bookmarks portlet. 

In general, you use the following steps to create Screenlets: 

1. [Determine your Screenlet's location](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#determining-your-screenlets-location). 
   Where you create your Screenlet depends on how you'll use it. 

2. [Create the Screenlet's UI (its View)](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#creating-the-screenlets-ui). 
   Although this tutorial presents all the information you need to create a View 
   for your Screenlet, you may first want to learn how to 
   [create a View](/develop/tutorials/-/knowledge_base/7-0/creating-android-views). 
   For more information on Views in general, see 
   [the tutorial on using Views with Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets). 

3. [Create the Screenlet's Interactor](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#creating-the-screenlets-Interactor-class). 
   Interactors are Screenlet components that make server calls. 

4. [Define the Screenlet's attributes](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#defining-screenlet-attributes-in-your-app). 
   These are the XML attributes the app developer can set when inserting the 
   Screenlet's XML. These attributes control aspects of the Screenlet's 
   behavior. You'll add functionality to these attributes in the Screenlet 
   class. 

5. [Create the Screenlet class](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#creating-the-screenlet-class). 
   The Screenlet class is the Screenlet's central component. It controls the 
   Screenlet's behavior and is the component the app developer interacts with 
   when inserting a Screenlet. 

To understand the components that make up a Screenlet, you should first 
learn the
[architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android). 

Without further ado, let the Screenlet creation begin! 

## Determining Your Screenlet's Location [](id=determining-your-screenlets-location)

Where you should create your Screenlet depends on how you plan to use it. If you
don't plan to reuse your Screenlet in another app or don't want to redistribute
it, create it in a new package inside your Android app project. This lets you
reference and access Liferay Screens's core, in addition to all the View Sets 
you may have imported. 

If you want to reuse your Screenlet in another app, create it in a new Android 
application module. The tutorial 
[Packaging Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets) 
explains how to do this. When your Screenlet's project is in place, you can 
start by creating the Screenlet's UI. 
