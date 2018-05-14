# Adding Applications to a Layout Page [](id=adding-applications-to-a-page)

Layout pages are composed of applications. To add an app to a page, go to the 
page and click the *Add* button  (![Add](../../../../images/icon-control-menu-add.png)) 
from the  top menu and select the *Applications* tab. You can either browse
through the categories of available apps until you find the one you want, or you
can search for apps by name. Once you've found an app, click the *Add* button to
add it to the current page. Once there, you can drag it to a new position.
Alternatively, you can drag the app directly from the Applications menu to
a specific location on the page. Follow the steps below to add some
Collaboration apps to the Lunar Resort site.

1.  From the top menu, select *Add* &rarr; *Applications*.

2.  In the menu that appears, expand the *Collaboration* category.

3.  Drag the *Blogs Aggregator* app from the Add Application window to the right
    column of your page.

4.  Next, drag the *Wiki* app to the left column.

See how easy it is to add applications to your pages? You've added the Wiki
app and Blogs Aggregator app to a page. 

![Figure 1: Your page layout options are virtually limitless with a slew of application and layout combinations.](../../../../images/app-layout-design.png)

If the default layout options provided aren't enough, you can even develop your
own. For more information about developing custom layout templates, see the
tutorial 
[Layout Templates with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/creating-layout-templates-with-the-themes-generator-0). 

Next, you'll practice personalizing pages using page customizations.

## Personalizing Pages [](id=personalizing-pages)

Administrators can designate pages or sections of pages to be customizable. When
a user visits such a page, a notification appears stating that the user can
customize the page. Users can make customizations only in the sections of pages
designated by administrators. Customizations are based on the rows and columns
of a page layout. Page customizations are only visible to the user who made the
customizations. By default, site members can make page customizations but
non-site members and guests can't.

To enable page customizations as an administrator, first click *Configure Page*
from the *Options* button next to the Page you'd like to let site members
modify. Then select the *Advanced* tab at the top of the page, expand the
*Customization Settings* area, and click on the *Customizable* selector button. 

![Figure 2: To enable page customizations, click on the *Configure Page* button next to the page, expand the *Customization Settings* area, and click on the *Customizable* button.](../../../../images/page-customizations.png)

Once you've enabled the *Customizable* selector, select the sections of
the page you'd like to enable customization for, depending on the layout
template of your page. Enable one or more of the *Customizable* sections to
allow site members to customize certain sections of the page. Regions that
you've designated as customizable are colored blue.

When site members visit your customizable page, they'll see an extended Control
Menu with a notification saying *You can customize this page*. Site members can
toggle whether to view or hide the customizable regions. If you toggle the
selector to view customizable regions, the regions on the page are color-coded
to help distinguish customizable vs. non-customizable sections of the page.

![Figure 3: Customizable regions are colored green and non-customizable regions are colored red.](../../../../images/color-coded-customizable-regions.png)

Site members can also choose between viewing their customized page and viewing
the default page by selecting the *Options* button
(![Options](../../../../images/icon-options.png)) from the Control Menu and
clicking the *View Page without my customizations* or *View My Customized Page*.

There's also a *Reset My Customizations* option available from the *Options*
button that restores a user's customized page to match the default page. This
allows users to discard one set of customizations and start a new set without
having to undo each customization that they'd previously made manually. 

Note that non-administrator site members can access the Add menu from the top
right side of the screen when viewing their customizable page even if they don't
ordinarily have permission to view this menu. This allows them to add apps to
the sections of the page that they're allowed to customize. If they click *View
Page without my customizations*, the Add menu disappears from the menu since
they're not allowed to modify the default page.

Administrators of customizable pages have the same two views as site members:
the *default page* view and the *customized page* view. Changes made to the
default page affect all users, whereas changes made to the customized page
affect only the administrator who made the changes. Changes made by
administrators to non-customizable sections in the default view are immediately
applied for all users. Changes made by administrators to customizable sections,
however, do *not* overwrite users' customizations.

Users can make two kinds of customizations to customizable regions. First, they
can configure applications within the customizable regions. Second, they can add
apps to or remove apps from the customizable regions.

![Figure 4: Customizable areas are highlighted green when organizing apps on the page.](../../../../images/customizable-regions.png)

Users can't change a non-instanceable app's configuration inside a customizable
region since those kinds of apps are tied to the site where they've been added.
If this were allowed, the customization would affect all users, not just the one
who customized the region. Therefore, changes to the app configuration in
a customizable region are only possible for instanceable apps, whose app
configuration only affects that one user.

For example, suppose that you, as an administrator, selected the right column of
the Welcome page of the Lunar Resort site to be customizable. A member of the
Lunar Resort site could take the following steps to make a personal
customization of the Welcome page:

1.  Navigate to the Welcome homepage by clicking the Site Administration's *Site
    Selector* button (![Compass](../../../../images/icon-compass.png)) and
    selecting the Lunar Resort site.

2.  Add the Language Selector app to the right column of the page by clicking
    the *Add* icon, clicking on *Applications*, searching for *Language
    Selector*, and clicking *Add* next to its name.

The Language Selector application is useful to have on your home page if you
expect users who speak different languages to access your instance. Users can
select their language in the Language Selector app to view a translation of your
site into their native language. After closing the Configuration dialog box of
the Language Selector app, the customized Welcome page looks like this:

![Figure 5: In this example, the user added the Language app, and changed the display style from icons to a select box.](../../../../images/customized-portal-homepage.png)

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
and to import or export app settings. Next, you'll look at how to change page
permissions.

## Changing Page Permissions [](id=changing-page-permissions)

By default, public pages are just that: public. They can be viewed by anybody,
logged in or not logged in. And private pages are really only private from
non-members of the site. If someone has joined your site or is a member of your
organization, that person can see all the private pages. You can, however,
modify the permissions on individual pages in either page group so only certain
users can view them.

Suppose you want to create a page only for administrators to see. You can do it
this way:

1.  Go to your site's Site Administration dropdown and select *Navigation*
    &rarr; *Private Pages*. If you don't have the *Private Pages* option
    available, select the *Options* button next to Public Pages and click *Add
    Private Page*. Remember, these pages by default are viewable only by members
    of the site.

2.  Create a page called *Admin Tips*.

3.  Click *Configure Page* from the Options button dropdown for the page in the
    left menu.

4.  Select *Permissions* from the *Options* icon
    (![Options](../../../../images/icon-options.png)) in the top right corner of
    the screen.

4.  Uncheck the *View* and *Add Discussion* permissions next to the Site Member
    role.

5.  Click the *Save* button.

![Figure 6: The Permissions offer a plethora of options for each role.](../../../../images/web-content-page-permissions.png)

Congratulations! You've changed the permissions for this page so only site
administrators can view it. Any users you add to this role can now see the page.
Other users, even members of this site, won't have permission to see it.

Pages are as flexible as pages you'd create manually without a Liferay instance.
Using a point and click interface, you can define your site any way you want.
You can create and remove pages, export and import them, set their layouts,
define how they are indexed by search engines, and more.

You now understand how to manage pages. It's time to move on to further
customizing those pages for mobile devices and building standardized pages using
custom templates.
