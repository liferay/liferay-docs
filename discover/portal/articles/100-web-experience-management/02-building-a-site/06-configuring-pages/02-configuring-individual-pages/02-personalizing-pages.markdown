# Personalizing Pages [](id=personalizing-pages)

Administrators can designate pages or sections of Widget Pages to be 
customizable. When a user visits such a page, a notification appears stating 
that the user can customize the page. Users can make customizations only in the 
sections of pages designated by administrators. Customizations are based on the 
rows and columns of a page layout. Page customizations are only visible to the 
user who made the customizations. By default, site members can make page 
customizations but non-site members and guests can't.

## Enabling Page Customizations

To enable page customizations as an administrator:

1.  Click *Configure Page* from the *Options* button next to the Page you'd 
    like to let site members modify.
    
2.  Select the *Advanced* tab at the top of the page and expand the
    *Customization Settings* area.

3.  Click on the *Customizable* selector button to activate customizations.

![Figure X: To enable page customizations, click on the *Configure Page* button next to the page, expand the *Customization Settings* area, and click on the *Customizable* button.](../../../../../images/page-customizations.png)

4.  Select the sections of the page you'd like to enable customization for, 
    depending on the layout template of your page.
    
5.  Enable one or more of the *Customizable* sections to allow site members to 
    customize certain sections of the page. Regions that you've designated as 
    customizable are colored blue.

When site members visit your customizable page, they'll see an extended Control
Menu with a notification saying *You can customize this page*. Site members can
toggle whether to view or hide the customizable regions. If you toggle the
selector to view customizable regions, the regions on the page are color-coded
to help distinguish customizable vs. non-customizable sections of the page.

![Figure X: Customizable regions are colored green and non-customizable regions are colored red.](../../../../../images/color-coded-customizable-regions.png)

## Customization Permissions

To allow users to customize a page, administrators must grant users permission
to *Customize* pages under the Site section. This can be achieved by assigning
permission to a role, then assigning this role to the appropriate users. For
example, if you want any logged user to be able to customize your customizable
pages, you could assign the *Customize* permission to the role *User*. If you
want site members to be able to customize the customizable pages of their sites,
you would accept the default setting. By default, the *Customize* permission is
assigned to the role *Site Member*.

In addition to granting the ability to customize app configurations, the
*Customize* permission allows users to customize the look and feel of apps
and to import or export app settings.

## Customizing Pages

With Customization active, site members can access the Add menu from the top
right side of the screen when viewing their customizable page. This allows them 
to add apps to the customizable sections of the page. If they click *View
Page without my customizations*, the Add menu disappears.

Users can make two kinds of customizations to customizable regions:

1.  They can configure applications within the customizable regions.

2.  They can add apps to or remove apps from the customizable regions.

There's also a *Reset My Customizations* option available from the *Options*
button that restores a user's customized page to match the default page. This
allows users to discard one set of customizations and start a new set without
having to undo each customization that they'd previously made manually. 

![Figure X: Customizable areas are highlighted green when organizing apps on the page.](../../../../../images/customizable-regions.png)

Users can't change a non-instanceable app's configuration inside a customizable
region since those kinds of apps are tied to the site where they've been added.
If this were allowed, the customization would affect all users, not just the one
who customized the region. Therefore, changes to the app configuration in
a customizable region are only possible for instanceable apps, whose app
configuration only affects that one user.

## Viewing Customized Pages

Site members can also choose between viewing their customized page and viewing
the default page by selecting the *Options* button
(![Options](../../../../../images/icon-options.png)) from the Control Menu and
clicking the *View Page without my customizations* or *View My Customized Page*.

Administrators of customizable pages have the same two views as site members:
the *default page* view and the *customized page* view. Changes made to the
default page affect all users, whereas changes made to the customized page
affect only the administrator who made the changes. Changes made by
administrators to non-customizable sections in the default view are immediately
applied for all users. Changes made by administrators to customizable sections,
however, do *not* overwrite users' customizations.


## Customization Example

As an administrator:

1.  Go to *Page Configuration* for the *Welcome* page.

2.  Go to the *Advanced* tab, and activate Customizations.

3.  Set the main column of the Welcome page of the Lunar Resort site to be customizable.

As a regular user of the take the following steps:

1.  Navigate to the *Welcome* homepage.

2.  Click *Add* &rarr; *Applications*.

3.  Locate the *Language Selector* widget and and add it to the page.

The Language Selector application is useful to have on your home page if you
expect users who speak different languages to access your instance. Users can
select their language in the Language Selector app to view a translation of your
site into their native language. After closing the Configuration dialog box of
the Language Selector app, the customized Welcome page looks like this:

![Figure X: In this example, the user added the Language app, and changed the display style from icons to a select box.](../../../../../images/customized-portal-homepage.png)
