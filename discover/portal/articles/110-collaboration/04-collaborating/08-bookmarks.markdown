# Bookmarking Sites [](id=bookmarking-sites)

You can use the Bookmarks widget to provide a list of bookmarks that benefit the 
entire community. For example, a site that focuses on auto repair could use this 
widget to provide bookmarks to technical manuals, part specifications, 
maintenance tips, and more. Administrators can create bookmarks in the widget, 
and users can access them. Both internal pages as well as external sites can be 
bookmarked. 

+$$$

**Note:** The Bookmarks widget is currently available for Liferay DXP only. 

$$$

## Creating Bookmarks and Folders [](id=creating-bookmarks-and-folders)

To use the Bookmarks widget, you must first add it to a page. Select *Add* 
(![Add](../../../images/icon-add-app.png)) 
&rarr; *Widgets* &rarr; *Community*, and then drag *Bookmarks* onto the page. 

![Figure 1: The Bookmarks widget displays bookmarks and folders for bookmarks.](../../../images/bookmarks-all.png)

The Bookmarks widget is empty at first. The current folder's name (*Home* by 
default) is displayed to the top-left of the graphic indicating there are no 
bookmarks in the current folder. The widget displays the Management Bar 
immediately above the current folder's name. The Management Bar lets you manage 
any displayed bookmarks. The Management Bar's search bar lets you search for 
folders and bookmarks in the widget. When you create folders and bookmarks, 
they're added to the current folder. Folders can have any number of subfolders. 

Follow these steps to add a bookmark: 

1.  In the Bookmarks widget, select *Add* 
    (![Add](../../../images/icon-portlet-add-control.png)) 
    &rarr; *Bookmark*. This opens the Add Bookmark form. 

2.  Name the bookmark, then enter its URL and description. 

3.  If you want to associate a tag with the bookmark, open the *Categorization* 
    section and use the *Select* button to select an existing tag. You can also 
    create a new tag by entering it in the *Tags* field and clicking *Add*. See 
    [the documentation on tags](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags) 
    for more information. 

4.  If you want to select an existing asset in the portal (e.g., a media file, 
    blog post, etc.) to relate to the bookmark, open the *Related Assets* 
    section and use the *Select* button to select that asset. 

5.  Open the *Permissions* section and set the bookmark's permissions. 

6.  When you're finished filling in the Add Bookmark form, click *Save* to 
    create the bookmark. 

You can also create folders for your bookmarks. Follow these steps to do so: 

1.  In the Bookmarks widget, select *Add* 
    (![Add](../../../images/icon-portlet-add-control.png)) 
    &rarr; *Folder*. This opens the Add Folder form. Note that this form is very 
    similar to the Add Bookmark form. 

2.  Give the folder a name and description. 

3.  Open the *Permissions* section and set the bookmark's permissions. 

4.  When you're finished filling in the Add Folder form, click *Save* to 
    create the folder. 

![Figure 2: The Add Bookmark form lets you create a bookmark.](../../../images/bookmarks-add-bookmark.png)

### Importing and Exporting Bookmarks [](id=importing-and-exporting-bookmarks)

Follow these steps to import or export bookmarks: 

1.  In the Bookmarks widget, select *Options* 
    (![Options](../../../images/icon-app-options.png)) 
    &rarr; *Export/Import*. 

2.  In the window that appears, start a new export or import process. For 
    details, 
    [see the article on importing and exporting pages and content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content). 

## Managing Bookmarks and Folders [](id=managing-bookmarks-and-folders)

The Bookmarks widget provides several ways to manage your bookmarks and folders. 
One is the Management Bar. Selecting the checkbox on the left-hand side of the 
Management Bar selects all the folders and bookmarks currently displayed in the 
widget. When you do this, the Management Bar changes to show the number of 
selected items, and replaces the buttons on its right with an Info button 
(![Info](../../../images/icon-information-dm.png)) 
and a Recycle Bin button 
(![Recycle Bin](../../../images/icon-trash.png)). 
Pressing the Recycle Bin button moves the selected items to the Recycle Bin. 
Unselect the checkbox to return the Management Bar to its initial state. In this 
state, the selector menu to the right of the checkbox lets you filter the items 
to display: 

- **All:** Display all folders and bookmarks. 
- **Recent:** Display only recent bookmarks. 
- **Mine:** Display only the bookmarks you created. 

The *View Types* button on the right side of the Management Bar lets you choose 
how to display the widget's bookmarks and folders. The View Types button's icon 
depends on the selected view type: 

-   **List** (![List](../../../images/icon-view-type-list.png)): 
    This is the default View Type. It shows items in a simple list that displays 
    the number of bookmarks and subfolders for each folder, and the number of 
    visits for each bookmark. 

-   **Table** (![Table](../../../images/icon-view-type-table.png)): 
    Shows bookmarks and folders in a table with columns for the name, URL, 
    visits, and modified date of each item. 

Regardless of View Type, each item has an Actions menu 
(![Actions](../../../images/icon-actions.png)) 
menu that lets you perform these actions on the item: 

-   **Edit:** Edit the item. 

-   **Move:** Move the item. When moving an item, the Bookmarks widget presents 
    you with the Move Entries form. In this form, the item's new folder is 
    selected for you automatically. Click *Move* to move the item to this 
    folder. To choose a different folder, click *Select*. The window that 
    appears lets you navigate your folder hierarchy, beginning with the Home 
    folder. Click any folder's name to view its subfolders. The text at the top 
    of the window indicates your position in the hierarchy. You can click the 
    folder names in this text to move back up the hierarchy. To select a 
    subfolder, click its *Choose* button. To choose the folder you're currently 
    browsing, click the *Choose This Folder* button. To create a new folder, 
    click the *Add Subfolder* button. Note that when you're in the Home folder, 
    this is instead the *Add Folder* button. The window closes once you choose a 
    folder, returning you to the Move Entries form. Click *Move* to move the 
    item to the selected folder. 

-   **Permissions:** View or change the item's permissions. 

-   **Subscribe:** Subscribe to the bookmark's changes. Note that this action is 
    only available for bookmarks. 

-   **Move to Recycle Bin:** Move the item to the Recycle Bin.

The Management Bar also contains an Info button 
(![Info](../../../images/icon-information-dm.png)) 
that you can click to slide in an info panel that displays more information 
about the current folder. This panel also gives you more management options. To 
receive notifications when a user adds a bookmark to the folder, subscribe to 
the folder by clicking the *Star* icon 
(![Star](../../../images/icon-star.png)). 
Click the *Actions* menu 
(![Actions](../../../images/icon-actions.png)) 
to reveal options for editing, moving, assigning permissions to, or deleting the 
folder. Note that you can't edit, move, or delete the Home folder. 

![Figure 3: A folder's info panel shows more information about the folder, and lets you take additional actions.](../../../images/bookmarks-info-panel.png)
