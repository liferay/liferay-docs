# Creating and Managing Navigation Menus

While you can stick to the default, you can also create multiple, customizable 
menus to take advantage of different features in Liferay.

## Creating a Navigation Menu

Go to *Site Administration* &rarr; *Navigation* &rarr; *Navigation Menus* to 
get started with Navigation Menus. Click on the `+` button to add a new menu. 
Give your menu a name and click *Submit*. On the next page, you'll be presented 
with a number of elements that you can add to a menu.

*  **Page**: You can select an existing page from the current site to add to 
    the navigation menu.

*  **Submenu**: You can use this to create a second level of menu navigation.

*  **URL**: You can create a link to any page outside of the current site or 
    portal with a URL link, which will appear just like any other option in 
    your menu.
    
Click on *Page* and you are taken to a view of all the current pages on the
current site. Select a page and click *Add* to add that page to the menu.

+$$$

**Note:** When you click on a page, you select that page, and multiple pages can
be selected by simply clicking on each page one at a time. To deselect a page
click on the page again.

$$$

Now you are menu management screen. From here, you can drag and drop menu 
elements to rearrange or nest them. You can also manage options for this menu by
clicking the gear icon in the top right. Let's add another item to the menu.

1.  Click the `+` icon.

2.  Select *Submenu* in the menu that pops up.

3.  Name your menu "External Links".

4. Click *Add*.

Click the `+` button again and select URL. You'll be prompted to enter a page 
name and URL.

1.  Enter a *Name*.

2.  Enter the *URL* for an external site.

3.  Click *Add*.

Drag the URL item onto the "External Links" submenu. This will nest the URL 
item in the submenu.

![Figure 1: A menu with a standard page, a submenu, and a URL link in the submenu.](../../../../images/basic-nav-menu.png)

Now that you understand the basics of creating menus, you can go into little 
deeper into menu management.

## Managing Menus

After you create a menu, you have more options available to configure it. On 
the main Navigation Menus page, you can observe the options for the menu.

*  **Title**: The name of your menu.

*  **Add New Pages**: Determines if new pages added to the main navigation will
    be automatically added to the menu.

*  **Marked As**: Can be set as *Primary Navigation*, *Secondary Navigation*, or
    *Social Navigation*.
    
*  **Author**: The user that created the menu.

*  **Create Date**: When the menu was created.

The menu types are used primarily for theme and fragment developers to 
determine how a menu should be styled. **Primary Navigation** is for menus that 
will serve as the main navigation for a page. **Secondary Navigation** is for 
menus that are designed to be used as a second level of navigation, possibly a 
side bar or a separate menu within a page. **Social Navigation** is a third 
designation designed for menus that might contain links for sharing content on 
social media or similar tasks.

### Modifying Menus

Next click on the options menu at the far left of your new navigation menu. 
You'll see these options:

![Figure 2: A menu with a standard page, a submenu, and a URL link in the submenu.](../../../../images/nav-menu-options.png)

*  **Edit**: Takes you to the interface for adding, removing, and organizing 
    menu items.

*  **Rename**: Allows you to change the name of your menu.

*  **Permissions**: The menu creator or administrator can define who can 
    view, update, delete, and manage the permissions for the menu here.
    
*  **Mark As**: Lets you change the menu type.

*  **Delete**: Deletes the menu.

These are all the tools you need to create and manage Liferay's powerful 
Navigation Menus.
