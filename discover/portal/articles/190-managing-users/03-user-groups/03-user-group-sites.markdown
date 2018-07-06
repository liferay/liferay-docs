# User Group Sites [](id=user-group-sites)

Each user has a personal Site that consists of public (Profile) and private 
(Dashboard) pages. A *user group Site* determines the base pages of the user 
group members' personal Sites. If the user group Site pages are added to a 
user's Profile pages, then the user group Site is a public Site, accessible to 
anyone with the URL (`http://www.[sitename].com/web/[username]`). If the user 
group Site pages are added to the user's Dashboard pages, then the user group 
Site is a private Site. A mixed approach can also be used, where both private 
and public pages are added for the user group Site. If users belong to multiple 
user groups, all the pages from those user group Sites are made part of their 
personal Sites. 

[When creating a user group](/discover/portal/-/knowledge_base/7-1/creating-a-user-group), 
you can create the user group Site via the 
[Site Templates](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates) 
available for selection in the *My Profile* and *My Dashboard* selector menus. 
You can also create a user group Site later, either manually or via a Site 
Template. 

## Creating User Group Sites From Site Templates [](id=creating-user-group-sites-from-site-templates)

Follow these steps to create a user group Site from a 
[Site Template](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates), 
for a user group that already exists: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and select *Control Panel* &rarr; *Users Groups*. User groups are listed 
    here in a table. 

2.  Click *Actions* 
    (![Actions](../../../images/icon-actions.png)) 
    &rarr; *Edit* for the user group you want to create a Site for. This opens 
    a form that you can use to edit the user group. Note that this is the same 
    form that appears when 
    [creating a user group](/discover/portal/-/knowledge_base/7-1/creating-a-user-group). 

3.  To use a Site Template to create a public profile for the users on their 
    *My Profile* Site, select that Site Template from the *My Profile* menu. To 
    use a Site Template to create private pages for the users on their 
    *My Dashboard* Site, select that Site Template from the *My Dashboard* menu. 
    Note that you can also do both. 

4.  Click *Save*. 

Now, when one of the group's users navigates to their *My Profile* or 
*My Dashboard* Sites, the content of those Sites reflect the Site Template(s)
you selected. 

User group Site pages function similarly to regular Site Template pages, with an
important exception: user group Site pages aren't copied for each user. They're
shown dynamically along with any custom pages that users may have on their 
personal Site. For this reason, users can't modify pages inherited from the user 
group. If needed, the user group administrator can define certain areas of a 
page as customizable, like with regular Sites. This lets users add and configure 
widgets in the specified area of the page. 

This flexibility lets you achieve almost any desired configuration for a user's
personal Site without having to modify it directly. When users are assigned to
a user group, they'll immediately have access to the user group's Site pages
from their personal Sites. 

+$$$

**Note:** Site Templates have an option that propagates changes made to the Site 
Template. If you use a Site Template with this option enabled, the user group 
Sites update automatically when that template changes. If you disable this 
option but enable it again later, the template's pages are copied to the users' 
Sites, overwriting any changes they may have made. For more information on the
automatically propagating Site Template changes, see 
[Site Templates](/discover/portal/-/knowledge_base/7-1/building-sites-from-templates).

$$$

## Creating User Group Sites Manually [](id=creating-user-group-sites-manually)

You can create a user group's Site manually, instead of basing it on a Site
Template. Follow these steps: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and select *Control Panel* &rarr; *Users* &rarr; *Users Groups*. User groups 
    are listed here in a table. 

2.  Click *Actions* 
    (![Actions](../../../images/icon-actions.png)) 
    &rarr; *Manage Pages* for the user group you want to create a Site for. This 
    opens the *Pages* window. Note that this is the same window you use for 
    [creating pages](/discover/portal/-/knowledge_base/7-1/creating-pages). 

3.  Create the public and/or private pages that you want to use for the users' 
    *My Profile* and/or *My Dashboard* Sites. Public pages you create here 
    become pages on users' *My Profile* Site, while private pages become pages 
    on users' *My Dashboard* Site. 

When you return to User Groups in the Control Panel, you can access a user 
group's public and/or private pages via these links in the user group's 
*Actions* button 
(![Actions](../../../images/icon-actions.png)): 

-   **Go to Profile Pages:** Opens the user group's public *My Profile* page(s) 
    in a new browser window. 
-   **Go to Dashboard Pages:** Opens the user group's private *My Dashboard* 
    page(s) in a new browser window. 

In the new window, you can add more pages and portlets and configure Site 
settings. 

## Legacy User Group Sites Behavior [](id=legacy-user-group-sites-behavior)

Since the inheritance of user group Site pages is now dynamic, even if there are
hundreds of thousands of users, even millions, there isn't an impact in
performance. Versions of Liferay Portal and Liferay DXP prior to 7.0 required
user group pages be copied to each user's personal Site. If you long for the old
days, or if you're upgrading from an older version and need to keep that
behavior, enable it by adding the following line to your `portal-ext.properties`
file: 

    user.groups.copy.layouts.to.user.personal.site=true

When this property is set to `true`, the template pages are copied to a user's
personal Site once, and then may be modified by the user. This means that if 
changes are made to the template pages later, they only affect users that are
added to the user group after the change is made. Users with administrative
privileges over their personal Sites can modify the pages and their content if
the *Allow Site Administrators to Modify the Pages Associated with This Site
Template* box has been checked for the template. When a user is removed from
a user group, the associated pages are removed from the user's personal Site. If
a user is removed from a group and is subsequently added back, the group's
template pages are copied to the user's Site a second time. Note that if a user
group's Site is based on a Site Template and an administrator modifies the user
group's Site Template after users have already been added to the group, those
changes only take effect if the *Enable propagation of changes from the Site
Template* box for the user group was checked. 

Note that prior to Liferay Portal 6.1, pages from different user groups could be
combined on users' personal Sites by using a naming convention. Liferay Portal 
6.1 simplifies the way user groups' Sites work by disallowing page combination. 
Set the property `user.groups.copy.layouts.to.user.personal.site=true` to 
preserve the page combination functionality. 
