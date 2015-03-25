# Using the Recycle Bin in Your Apps

How many times have you deleted something, only to realize it was a mistake and 
you need to restore it? Does the app you're working on have a delete function? 
If so, you can add the ability to move items into a recycle bin instead of 
deleting them immediately. Have you been looking for an easy way to implement 
this capability in your app? Fortunately, you can, with the help of Liferay's 
Recycle Bin.

While discussing these capabilities,  code snippets taken from Liferay's 
 [Jukebox Portlet](https://github.com/liferay-labs/jukebox-portlet) plugin are 
 referenced. The plugin actually bundles three separate portlets: Songs, Albums, 
 and Artists, which are integrated together. The Jukebox portlet allows you to 
 upload songs that you can play from your portal. You can categorize the songs 
 by album and artist, keeping your song collection organized. The Jukebox 
 portlet's song and album assets can be moved to and from the Recycle Bin. 

Also, the Jukebox portlet offers indexed (searchable) entities and a workflow. 
These are not mandatory to implement the Recycle Bin, but are present in this 
example portlet.
