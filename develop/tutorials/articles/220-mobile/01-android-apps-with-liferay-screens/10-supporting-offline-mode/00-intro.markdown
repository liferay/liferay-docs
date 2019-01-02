# Supporting Offline Mode [](id=supporting-offline-mode)

Offline mode lets Screenlets function without a network connection. For offline 
mode to work with your Screenlet, you must manually add support for it. 
Fortunately, Liferay Screens 2.0 introduced a simpler way of implementing 
offline mode support in Android Screenlets: 

1.  Create an event class (or update it if your Screenlet already has one). 
2.  Update your Screenlet's classes to leverage the offline mode cache. 

How you implement these steps depends on how your Screenlet communicates with 
the server: 

- **Write Screenlets:** Write data to a server. The Add Bookmark Screenlet 
  created in the 
  [basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-android-screenlets) 
  is a good example of a simple write Screenlet. It asks the user to enter a URL 
  and a title, which it then sends to the Bookmarks portlet in @product@ 
  to create a bookmark. 
- **Read Screenlets:** Read data from a server. The Web Content Display 
  Screenlet included with Liferay Screens is a good example of a read Screenlet. 
  It retrieves web content from @product@ for display in an Android app. 
  [Click here](/develop/reference/-/knowledge_base/7-1/webcontentdisplayscreenlet-for-android) 
  to see Web Content Display Screenlet's documentation. 

Offline mode implementation differs only slightly between write and read 
Screenlets. The tutorials in this section use a write Screenlet (Add Bookmark 
Screenlet) to show you how to support offline mode, and point out any 
differences needed for a read Screenlet. 

Before getting started, be sure to read 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-android-screenlets) 
to familiarize yourself with Add Bookmark Screenlet's code. 
