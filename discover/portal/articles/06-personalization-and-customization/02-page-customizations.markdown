# Page Customizations [](id=page-customizations)

Liferay 6.1 introduced the concept of page customizations. Administrators can
designate public pages or sections of public pages to be customizable. When a
user visits such a page, a notification will appear stating that the user can
customize the page. Users can make customizations only in the sections of pages
designated by administrators. Customizations are based on the rows and columns
of a page layout. Page customizations are only visible to the user who made the
customizations. By default, site members can make page customizations but
non-site members and guests can't.

To enable page customizations as an administrator, first navigate to the page
you'd like to let site members modify. Then select *Manage* &rarr; *Page
Customizations* from the Dockbar.

![Figure 6.1: To enable page customizations, select *Manage* &rarr; *Page
Customizations* from the Dockbar.](../../images/page-customizations.png)

Once you've selected *Manage* &rarr; *Page Customizations*, you'll see one or
more red regions, depending on the layout template of your page. Check one or
more of the *Customizable* boxes to allow site members to customize certain
sections of the page. Regions that you've designated as customizable are colored
green.

![Figure 6.2: Check one or more of the *Customizable* boxes to allow site
members to customize certain sections of the
page.](../../images/customizable-regions.png)

When site members visit your customizable page, they'll see a notification
saying, "You can customize this page." Site members can toggle between viewing
their customized page and viewing the default page by clicking the *View Default
Page* or *View My Customized Page* links just below the Dockbar. There's also a
*Reset My Customizations* link that restores a user's customized page to the
match the default page. This allows users to discard one set of customizations
and start a new set without having to manually undo each customization that
they'd previously made.

Note that non-administrator site members can access the Add menu from the
Dockbar when viewing their customizable page even if they don't ordinarily have
permission to view this menu. This allows them to add portlets to the sections
of the page that they're allowed to customize. If they click *View Default
Page*, the Add menu will disappear from the Dockbar since they're not allowed to
modify the default page.

![Figure 6.3: Non-administrator site members can customize their own versions of
customizble pages but can't modify the default
page.](../../images/default-customizable-page.png)

Administrators of customizable pages have the same two views as site members:
the *default page* view and the *customized page*. Changes made to the *default
page* affect all users, whereas changes made to the *customized page* affect
only the administrator who made the changes.  Changes made by administrators to
non-customizable sections in the *default view* are immediately applied for all
users. However, changes made by administrators to customizable sections do *not*
overwrite users' customizations.

Users can make two kinds of customizations to customizable regions. First, they
can configure any portlet applications within the customizable regions. Second,
they can add portlets to or remove portlets from the customizable regions. As a
simple example, suppose that you, as an administrator, selected the right column
of the default portal homepage to be customizable. A member of the default site
could take the following steps to make a personal customization of the portal
homepage:

1. Navigate to the portal homepage by clicking *Go To* &rarr; *Liferay* from the
   Dockbar. (The portal homepage belongs to an automatically created site called
*Liferay*, by default.)
2. Remove the Hello World portlet remove from the right column of the page.
3. Add the Language portlet to the right column by clicking *Add* &rarr; *More*
   in the Dockbar, expanding the *Tools* category, and clicking *Add* next to
*Language*.
4. Configure the Language portlet by clicking on the wrench icon and selecting
   *Configuration* and then opening the *Display Style* dropdown menu and
choosing *Select Box*.

The Language portlet is useful to have on your portal homepage if you expect
users who speak different languages to access your portal. Users can select
their language in the Language portlet to view a translation of the portal into
their native language. After closing the Configuration dialog box of the
Language portlet, the customized portal homepage looks like this:

![Figure 6.4: In this example, Joe Bloggs removed the Hello World portlet, added
the Language portlet, and changed the display style from icons to a select
box.](../../images/customized-portal-homepage.png)

To allow users to customize a page, administrators must grant users permission
to *Customize* pages under the Site section. This can be achieved by assigning
permission to a role, then assigning this role to the appropriate users. For
example, if we want any logged user to be able to customize our customizable
pages, we could assign the *Customize* permission to the role *User*. If we want
site members to be able to customize the customizable pages of their sites, we
would accept the default setting. By default, the *Customize* permission is
assigned to the role *Site Member*.

In addition to granting the ability to customize portlet configurations, the
*Customize* permission allows users to customize the look and feel of portlets
and to import or export portlet settings. Next, let's look at how to use
Liferay's rules engine.
