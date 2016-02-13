# Bookmarking Sites

Many of us enjoy collecting things we value. This could include stamps, comic 
books, sea shells, shoes, or even vintage musical instruments. The list goes on 
and on. You've probably never thought about collecting URLs, even though most of 
us collect them. Anyone that uses a browser likely has an extensive URL 
collection in the form of bookmarks. With Liferay's Bookmarks app, your Liferay 
instance can provide its users with bookmarks that benefit the entire community. 

The Bookmarks app lets users and administrators collect and manage URLs in your 
Liferay instance. They can add, edit, delete, export and import bookmarks. Users 
can use links to access regularly visited web sites. Administrators can publish 
links tailored to specific groups of users. Both internal pages as well as 
external sites can be bookmarked. 

## Creating Bookmarks and Folders

Navigate to your Liferay instance and add the Bookmarks app to a page by 
selecting *Add* &rarr; *Applications* &rarr; Community, and then dragging 
*Bookmarks* onto the page. By default, the app looks like this:

![Figure 1: Initially, no bookmarks appear in the Bookmarks app.](../../../images/bookmarks-app-empty.png)

The Bookmarks app is empty when you first add it to a page. The app greets you 
with a smiley face and a message that says there are no bookmarks in the current 
folder, the *Home* folder. The current folder's name is displayed to the 
top-left of the area with the smiley face and message. The app displays the 
*management bar* immediately above the current folder's name. The management bar 
lets you choose which bookmarks and folders to display, how to display them, and 
more. The search bar, located to the top-right of the selector bar, lets you 
search for folders and bookmarks in the app. 

To see all this functionality in action, you must first add some bookmarks and 
folders. When you mouse-over the app, another bar appears across the top that 
shows the app's name, an add button, and an options button. When you click the 
add button, a small menu pops up that lets you select *Folder* or *Bookmark*. 
Selecting *Bookmark* brings up the *Add Bookmark* form. This form lets you 
create a bookmark by filling in the *Name*, *URL*, and *Description* fields. 
Below these fields, there are additional options for Categorization, Related 
Assets, and Permissions, just like in other Liferay apps. See the 
[Publishing Content Dynamically](/discover/portal/-/knowledge_base/7-0/publishing-content-dynamically) 
section for more information on this. When you're finished filling out the Add 
Bookmark form, click *Save* to create the bookmark. If you select *Folder* from 
the add button, this brings up the *Add Folder* form. This form is very similar 
to the Add Bookmark form. Fill in the *Name* and *Description* fields, assign 
any permissions you want to use, and click *Save* to create the folder. 

![Figure 2: The Add Bookmark form lets you create a bookmark.](../../../images/bookmarks-add-bookmark.png)

![Figure 3: The Add Folder form lets you create a folder to use for organizing bookmarks.](../../../images/bookmarks-add-folder.png)

When you create folders and bookmarks, they're added to the current folder. 
Folders can have any number of subfolders. Next, you'll learn how to manage 
bookmarks and folders. 

## Managing Bookmarks and Folders

The Bookmarks app provides several ways to manage your bookmarks and folders. 
One is the management bar. Checking the checkbox on the left-hand side of the 
management bar selects all the folders and bookmarks currently displayed in the 
app. When you do this, the management bar changes to show the number of selected 
items, and replaces the buttons on its right with an info button and a delete 
button. Pressing the *Delete* button deletes the selected items. Uncheck the 
checkbox to return the management bar to its initial state. In this state, the 
selector menu to the right of the checkbox lets you filter the items to display: 

- **All:** Display all folders and bookmarks. 

- **Recent:** Display only recent bookmarks. 

- **Mine:** Display only the bookmarks you created. 

The *Info*, *Descriptive*, and *List* buttons are on the management bar's right 
side. Clicking the Info button slides in an info panel that displays more 
information about the current folder, and gives you additional management 
options. To receive notifications when a user adds a bookmark to the folder, 
subscribe to the folder by clicking the star icon. Click the actions menu 
(the three vertical dots) to reveal options for editing, moving, assigning 
permissions to, or deleting the folder. Note that you can't edit, move, or 
delete the Home folder. 

![Figure 4: A folder's info panel shows more information about the folder, and lets you take additional actions.](../../../images/bookmarks-info-panel.png)

The management bar's Descriptive and List buttons determine how the app displays 
folders and bookmarks. Click the management bar's Descriptive button to show 
each item's name, description, number of subfolders and bookmarks (folders), and 
number of visits (bookmarks). The app displays items this way by default. To 
instead show items in a simple list, click the List button. 

You can also manage each item from its *actions* menu. You can use this menu to 
edit, move, assign permissions to, or delete each item. If the item is a 
bookmark, you can also subscribe to it in its actions menu. Subscribing to a 
bookmark means that you receive a notification when the bookmark changes. 

When moving an item, the Bookmarks app presents you with the Move Entries form. 
In this form, the item's new folder is selected for you automatically. Click 
*Move* to move the item to this folder. To choose a different folder, click 
*Select*. The window that appears lets you navigate your folder hierarchy, 
beginning with the Home folder. Click any folder's name to view its subfolders. 
The text at the top of the window indicates your position in the hierarchy. You 
can click the folder names in this text to move back up the hierarchy. To select 
a subfolder, click its *Choose* button. To choose the folder you're currently 
browsing, click the *Choose This Folder* button. To create a new folder, click 
the *Add Subfolder* button. Note that when you're in the Home folder, this is 
instead the *Add Folder* button. The window closes once you choose a folder, 
returning you to the Move Entries form. Click *Move* to move the item to the 
selected folder. 

![Figure 5: Choose the folder to move the item to.](../../../images/bookmarks-move-folder.png)

# Summary

Liferay's Bookmarks app gives users and administrators a straightforward way to 
add and manage bookmarks in your Liferay instance. This lets everyone share and 
access URLs relevant to the community, including URLs for internal pages. Since 
bookmarks can be organized in folders nested to unlimited levels, the Bookmarks 
app is suitable for Liferay instances with any number of users. 
