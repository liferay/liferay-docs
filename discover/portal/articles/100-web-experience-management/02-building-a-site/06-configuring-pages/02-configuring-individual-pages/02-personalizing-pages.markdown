# Personalizing Pages [](id=personalizing-pages)

Administrators can designate pages or sections of Widget Pages as customizable.
When a user visits such a page, a notification appears stating that the user can
customize the page. Users can make customizations only in the sections of pages
designated by administrators. Customizations are based on the rows and columns
of a page layout. Page customizations are only visible to the user who made the
customizations. By default, Site members can make page customizations but
non-Site members and guests can't.

## Enabling Page Customizations [](id=enabling-page-customizations)

To enable page customizations as an administrator,

1.  Click *Configure Page* from the *Options* button next to the Page you want
    to let Site members modify.
 
2.  Select the *Advanced* tab at the top of the page and expand the
    *Customization Settings* area.

3.  Click the *Customizable* selector button to activate customizations.

    ![Figure 1: To enable page customizations, click on the *Configure Page* button next to the page, expand the *Customization Settings* area, and click on the *Customizable* button.](../../../../../images/page-customizations.png)

4.  Select the sections of the page that should be customizable.
 
5.  Enable one or more of the *Customizable* sections so Site members can 
    customize sections of the page. Regions that you've designated as
    customizable are colored blue.

When Site members visit your customizable page, they see an extended Control
Menu with a notification saying *You can customize this page*. Site members can
toggle whether to view or hide the customizable regions. If you toggle the
selector to view customizable regions, the regions on the page are color-coded
to help distinguish customizable vs. non-customizable sections of the page.

![Figure 2: Customizable regions are colored green and non-customizable regions are colored red.](../../../../../images/color-coded-customizable-regions.png)

## Customization Permissions [](id=customization-permissions)

Administrators must grant users permission to customize pages under the Site
section. This can be achieved by assigning permission to a Role, then assigning
this Role to the appropriate users. For example, if you want users to be able to
customize your customizable pages, assign the *Customize* permission to the Role
*User*. If you want Site members to be able to customize their Sites'
customizable pages, accept the default setting. By default, the *Customize*
permission is assigned to the Role *Site Member*.

The *Customize* permission also lets users customize the look and feel of
apps and import or export app settings.

## Customizing Pages [](id=customizing-pages)

With customization active, Site members can access the Add menu from the top
right side of the screen when viewing their customizable page, which lets them
add apps to the customizable sections of the page. If they click *View Page
without my customizations*, the Add menu disappears.

Users can make two kinds of customizations to customizable regions:

1.  They can configure applications within the customizable regions.

2.  They can add apps to or remove apps from the customizable regions.

*Reset My Customizations* from the *Options* button restores a user's customized
page to match the default page, discarding their customizations so they can
start anew. 

![Figure 3: Customizable areas are highlighted green when organizing apps on the page.](../../../../../images/customizable-regions.png)

Users can't change a non-instanceable app's configuration inside a customizable
region since those apps are tied to the Site where they've been added. 

## Viewing Customized Pages [](id=viewing-customized-pages)

Site members can also choose between viewing their customized page and viewing
the default page by selecting the *Options* button
(![Options](../../../../../images/icon-options.png)) from the Control Menu and
clicking the *View Page without my customizations* or *View My Customized Page*.

Administrators of customizable pages have the same two views as Site members:
the *default page* view and the *customized page* view. Changes made to the
default page affect all users, whereas changes made to the customized page
affect only the administrator who made the changes. Changes made by
administrators to non-customizable sections in the default view are immediately
applied for all users. Changes made by administrators to customizable sections,
however, do *not* overwrite users' customizations.

## Customization Example [](id=customization-example)

As an administrator,

1.  Go to *Page Configuration* for the *Welcome* page.

2.  Go to the *Advanced* tab, and activate Customizations.

3.  Set the main column of the Welcome page of the Lunar Resort Site to be customizable.

As a regular user,

1.  Navigate to the *Welcome* page.

2.  Click *Add* &rarr; *Applications*.

3.  Locate the *Language Selector* widget and add it to the page.

The Language Selector application lets users select their language to view
a translation of your Site into their native language. After closing the
Configuration dialog box of the Language Selector app, the customized Welcome
page looks like this:

![Figure 4: In this example, the user added the Language app, and changed the display style from icons to a select box.](../../../../../images/customized-portal-homepage.png)
