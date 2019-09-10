---
header-id: user-group-sites
---

# User Group Sites

[TOC levels=1-4]

Each User has a personal Site that consists of public (Profile) and private 
(Dashboard) pages. A *User Group Site* determines the base pages of the User 
Group members' personal Sites. If the User Group Site pages are added to a 
User's Profile pages, then the User Group Site is a public Site, accessible to 
anyone with the URL (`http://www.[sitename].com/web/[username]`). If the User 
Group Site pages are added to the user's Dashboard pages, then the User Group 
Site is a private Site. A mixed approach can also be used, where both private 
and public pages are added for the User Group Site. If Users belong to multiple 
User Groups, all the pages from those User Group Sites are made part of their 
personal Sites. 

[When creating a user group](/docs/7-2/user/-/knowledge_base/u/creating-a-user-group), 
you can create the user group Site via the 
[Site Templates](/docs/7-2/user/-/knowledge_base/u/building-sites-from-templates) 
available for selection in the *My Profile* and *My Dashboard* selector menus. 
You can also create a User Group Site later, either manually or via a Site 
Template. 

## Creating User Group Sites From Site Templates

Follow these steps to create a User Group Site from a 
[Site Template](/docs/7-1/user/-/knowledge_base/u/building-sites-from-templates), 
for a User Group that already exists: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and select *Control Panel* &rarr; *Users* &rarr; *User Groups*. User groups
    appear in a table. 

2.  Click *Actions* 
    (![Actions](../../../images/icon-actions.png)) 
    &rarr; *Edit* for the User Group you want to create a Site for. This opens 
    a form that you can use to edit the User Group. Note that this is the same 
    form that appears when 
    [creating a user group](/docs/7-2/user/-/knowledge_base/u/creating-a-user-group). 

3.  To use a Site Template to create a public profile for the Users on their 
    *My Profile* Site, select that Site Template from the *My Profile* menu. To 
    use a Site Template to create private pages for the Users on their 
    *My Dashboard* Site, select that Site Template from the *My Dashboard* menu. 
    Note that you can also do both. 

4.  Click *Save*. 

Now, when one of the group's Users navigates to their *My Profile* or 
*My Dashboard* Sites, the content of those Sites reflect the Site Template(s)
you selected. 

User Group Site pages function similarly to regular Site Template pages, with an
important exception: User Group Site pages aren't copied for each user. They're
shown dynamically along with any custom pages that Users may have on their 
personal Site. For this reason, Users can't modify pages inherited from the User 
Group. If needed, the User Group administrator can define certain areas of a 
page as customizable, like with regular Sites. This lets Users add and configure 
widgets in the specified area of the page. 

This flexibility lets you achieve almost any desired configuration for a User's
personal Site without having to modify it directly. When Users are assigned to
a User Group, they'll immediately have access to the User Group's Site pages
from their personal Sites. 

| **Note:** Site Templates have an option that propagates changes made to the Site
| Template. If you use a Site Template with this option enabled, the User Group
| Sites update automatically when that template changes. If you disable this
| option but enable it again later, the template's pages are copied to the Users'
| Sites, overwriting any changes they may have made. For more information on the
| automatically propagating Site Template changes, see
| [Site Templates](/docs/7-2/user/-/knowledge_base/u/building-sites-from-templates).

## Creating User Group Sites Manually

You can create a User Group's Site manually, instead of basing it on a Site
Template. Follow these steps: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and select *Control Panel* &rarr; *Users* &rarr; *Users Groups*. User groups 
    appear here.

2.  Click *Actions* 
    (![Actions](../../../images/icon-actions.png)) 
    &rarr; *Manage Pages* for the user group you want to create a Site for. This 
    opens the *Pages* window. Note that this is the same window you use for 
    [creating pages](/docs/7-2/user/-/knowledge_base/u/creating-pages). 

3.  Create the public and/or private pages that you want to use for the Users' 
    *My Profile* and/or *My Dashboard* Sites. Public pages you create here 
    become pages on users' *My Profile* Site, while private pages become pages 
    on users' *My Dashboard* Site. 

When you return to User Groups in the Control Panel, you can access a User 
Group's public and/or private pages via these links in the User Group's 
*Actions* button 
(![Actions](../../../images/icon-actions.png)): 

-   **Go to Profile Pages:** Opens the User Group's public *My Profile* page(s) 
    in a new browser window. 
-   **Go to Dashboard Pages:** Opens the User Group's private *My Dashboard* 
    page(s) in a new browser window. 

In the new window, you can add more pages and portlets and configure Site 
settings. 

## Legacy User Group Sites Behavior

Since the inheritance of User Group Site pages is now dynamic, even if there are
hundreds of thousands of Users, even millions, there isn't an impact in
performance. Versions of Liferay Portal and Liferay DXP prior to 7.0 required
User Group pages be copied to each User's personal Site. If you long for the old
days, or if you're upgrading from an older version and must keep that
behavior, enable it by adding the following line to your `portal-ext.properties`
file: 

    user.groups.copy.layouts.to.user.personal.site=true

When this property is set to `true`, the template pages are copied to a User's
personal Site once, and then may be modified by the User. This means that if 
changes are made to the template pages later, they only affect Users added to
the User Group after the change is made. Users with administrative privileges
over their personal Sites can modify the pages and their content if the *Allow
Site Administrators to Modify the Pages Associated with This Site Template* box
has been checked for the template. When a User is removed from a User Group, the
associated pages are removed from the User's personal Site. If a User is removed
from a group and is subsequently added back, the group's template pages are
copied to the User's Site a second time. Note that if a User Group's Site is
based on a Site Template and an administrator modifies the User Group's Site
Template after users have already been added to the group, those changes only
take effect if the *Enable propagation of changes from the Site Template* box
for the User Group was checked. 

