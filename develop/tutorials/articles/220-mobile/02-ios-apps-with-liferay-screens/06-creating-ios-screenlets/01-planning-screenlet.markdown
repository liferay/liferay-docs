# Planning Your iOS Screenlet

Before creating your Screenlet, you must determine what it needs to do and how 
you want developers to use it. This determines where you'll create your 
Screenlet and its functionality. 

Where you should create your Screenlet depends on how you plan to use it. If you
want to reuse or redistribute it, you should create it in an empty Cocoa Touch
Framework project in Xcode. You can then use CocoaPods to publish it. The 
tutorial 
[Packaging iOS Themes](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes)
explains how to publish an iOS Screenlet. Even though that tutorial refers to
Themes, the steps for preparing Screenlets for publication are the same. If you
don't plan to reuse or redistribute your Screenlet, create it in your app's
Xcode project. 

You must also determine your Screenlet's functionality and what data your 
Screenlet requires. This determines the actions your Screenlet must support and 
the Liferay remote services it must call. For example, Add Bookmark Screenlet 
only needs to respond to one action: adding a bookmark to Liferay's Bookmarks 
portlet. To add a bookmark, this Screenlet must call the Liferay instance's 
`add-entry` service for `BookmarksEntry`. If you're running a Liferay instance 
locally on port 8080, 
[click here](http://localhost:8080/api/jsonws?contextName=bookmarks&signature=%2Fbookmarks.bookmarksentry%2Fadd-entry-6-groupId-folderId-name-url-description-serviceContext) 
to see this service. To add a bookmark, this service requires the following 
parameters: 

-   `groupId`: The site ID in the Liferay instance that contains the 
    Bookmarks portlet. 

-   `folderId`: The folder ID in the Bookmarks portlet that receives the new 
    bookmark. 

-   `name`: The new bookmark's title. 

-   `url`: The new bookmark's URL. 

-   `description`: The new bookmark's description. 

-   `serviceContext`: A 
    [Liferay `ServiceContext`](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext) 
    object. 

Add Bookmark Screenlet must therefore account for each of these parameters. When 
saving a bookmark, the Screenlet asks the user to enter the bookmark's URL and 
name. The user isn't required, however, to enter any other parameters. This is 
because the app developer sets the `groupId` and `folderId` via the app's code. 
Also, the Screenlet's code automatically populates the `description` and 
`serviceContext`. 
