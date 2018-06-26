# User Group Sites

Each user has a personal site that consists of public (Profile) and private 
(Dashboard) pages. A *user group site* determines the base pages of the user 
group members' personal sites. If the user group site pages are added to a 
user's Profile pages, then the user group site is a public site, accessible to 
anyone with the URL (`http://www.[sitename].com/web/[username]`). If the user 
group site pages are added to the user's Dashboard pages, then the user group 
site is a private site. A mixed approach can also be used, where both private 
and public pages are added for the user group site. If users belong to multiple 
user groups, all the pages from those user group sites are made part of their 
personal sites. 

[When creating a user group](liferay.com), 
you can create the user group site via the 
[site templates](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates) 
available for selection in the *My Profile* and *My Dashboard* selector menus. 
You can also create a user group site later, either manually or via a site 
template. 

## Creating User Group Sites From Site Templates

Follow these steps to create a user group site from a 
[site template](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates), 
for a user group that already exists: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and select *Control Panel* &rarr; *Users Groups*. User groups are listed 
    here in a table. 

2.  Click *Actions* 
    (![Actions](../../../images/icon-actions.png)) 
    &rarr; *Edit* for the user group you want to create a site for. This opens 
    a form that you can use to edit the user group. Note that this is the same 
    form that appears when 
    [creating a user group](liferay.com). 

3.  To use a site template to create a public profile for the users on their 
    *My Profile* site, select that site template from the *My Profile* menu. To 
    use a site template to create private pages for the users on their 
    *My Dashboard* site, select that site template from the *My Dashboard* menu. 
    Note that you can also do both. 

4.  Click *Save*. 

Now, when one of the group's users navigates to their *My Profile* or 
*My Dashboard* sites, the content of those sites will reflect the site 
template(s) you selected. 

User group site pages function similarly to regular site template pages, with an
important exception: user group site pages aren't copied for each user. They're
shown dynamically along with any custom pages that users may have on their 
personal site. For this reason, users can't modify pages inherited from the user 
group. If needed, the user group administrator can define certain areas of a 
page as customizable, like with regular sites. This lets users add and configure 
widgets in the specified area of the page. 

This flexibility lets you achieve almost any desired configuration for a user's
personal site without having to modify it directly. When a user is assigned to a
user group, they'll immediately have access to the user group's site pages from
their personal site. 

+$$$

**Note:** Site templates have an option that propagates changes made to the site 
template. If you use a site template with this option enabled, the user group 
sites update automatically when that template changes. If you disable this 
option, but enable it again later, the template's pages are copied to the users' 
sites, overwriting any changes they may have made. For more information on the 
option to automatically propagate site template changes, see the 
[documentation on site templates](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates). 

$$$

## Creating User Group Sites Manually

You can create a user group's site manually, instead of basing it on a site
template. Follow these steps to do so: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and select *Control Panel* &rarr; *Users* &rarr; *Users Groups*. User groups 
    are listed here in a table. 

2.  Click *Actions* 
    (![Actions](../../../images/icon-actions.png)) 
    &rarr; *Manage Pages* for the user group you want to create a site for. This 
    opens the *Pages* window. Note that this is the same window you use for 
    [creating pages](/discover/portal/-/knowledge_base/7-1/creating-pages). 

3.  Create the public and/or private pages that you want to use for the users' 
    *My Profile* and/or *My Dashboard* sites. Public pages you create here 
    become pages on users' *My Profile* site, while private pages become pages 
    on users' *My Dashboard* site. 

When you return to User Groups in the Control Panel, you can access a user 
group's public and/or private pages via these links in the user group's 
*Actions* button 
(![Actions](../../../images/icon-actions.png)): 

-   *Go to Profile Pages*: Opens the user group's public *My Profile* page(s) in 
    a new browser window.
-   *Go to Dashboard Pages* Opens the user group's private *My Dashboard* 
    page(s) in a new browser window. 

In the new window, you can add more pages and portlets and configure site 
settings. 

## Legacy User Group Sites Behavior

Since the inheritance of user group site pages is now dynamic, even if there are
hundreds of thousands of users, even millions, there won't be an exponential
impact in performance. Versions of Liferay Portal and Liferay DXP prior to 7.0 
required user group pages be copied to each user's personal site. If you long 
for the old days, or if you're upgrading from an older version and need to keep 
that behavior, enable it by adding the following line to your 
`portal-ext.properties` file: 

    user.groups.copy.layouts.to.user.personal.site=true

When this property is set to `true`, the template pages are copied to a user's
personal site once, and then may be modified by the user. This means that if 
changes are made to the template pages later, they will only affect users that
are added to the user group after the change is made. Users with administrative
privileges over their personal sites can modify the pages and their content if
the *Allow Site Administrators to Modify the Pages Associated with This Site
Template* box has been checked for the template. When a user is removed from a
user group, the associated pages are removed from the user's personal site. If a 
user is removed from a group and is subsequently added back, the group's 
template pages are copied to the user's site a second time. Note that if a user 
group's site is based on a site template and an administrator modifies the user 
group's site template after users have already been added to the group, those 
changes only take effect if the *Enable propagation of changes from the site 
template* box for the user group was checked. 

Note that Prior to Liferay Portal 6.1, pages from different user groups could be
combined on users' personal sites by using a naming convention. Liferay Portal 
6.1 simplifies the way user groups' sites work by disallowing page combination. 
Set the property `user.groups.copy.layouts.to.user.personal.site=true` to 
preserve the page combination functionality. 
