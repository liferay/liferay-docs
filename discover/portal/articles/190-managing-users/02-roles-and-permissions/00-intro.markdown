# Roles and Permissions [](id=roles-and-permissions)

If a *Role* were to win a Grammy or an Oscar or some other ego-feeding
popularity contest, it better remember to thank all its *permissions* groupies
during the acceptance speech, because they're the ones doing the real work. The
role is just the pretty face, so to speak.

Roles collect permissions that define a particular function, according to
a particular scope. Roles collect permissions, and Users are assigned to Roles.

+$$$

**Note:** Roles are assigned to Users, but it's tedious to assign each User to
role intended for lots of Users. Recall that Users are grouped in Sites,
Organizations, and User Groups. Implicitly assign regular scoped permissions to
Users by assigning a role directly to one of these user groupings.

![Figure x: Assign Users to a role, directly or by their association with a Site, Organization, or User Group.](../../../images/roles-assignees.png)

$$$

Take a Message Board Administrator Role, for example. A Role with that name
should have permissions relevant to the specific Message Board portlets
delegated to it. Users with this Role inherit the permissions collected underneath
the umbrella of the Role.

In addition to regular Roles, Site Roles, and Organization Roles, there are also
Teams. Teams can be created by site administrators within a specific Site. The
permissions granted to a Team are defined and applied only within the Team's
site. The permissions defined by regular, Site, and Organization Roles, by
contrast, are defined at the global level, although they are applied to
different scopes. 

Regular role
: Permissions are defined at the global level and are applied at the global
scope.

Site role
: Permissions are defined at the global level and are applied to one specific
Site.

Organization role
: Permissions are defined at the global level and are applied to one specific
Organization.

Team
: Permissions are defined within a specific Site and are assigned within that
specific Site.

Read 
[here](/discover/portal/-/knowledge_base/7-1/creating-teams-for-advanced-site-membership-management) 
for more information about Teams.

## Deleting Asset Containers [](id=deleting-asset-containers)

A Web Content Folder contains Web Content articles. The Web Content Folder is
an asset container, and the Web Content Article is an asset. It's possible to
give a Role permission to delete an asset container without giving the Role
permission to delete individual assets. In that case, beware: if a Role assignee
deletes an asset container with individual assets in it, the individual assets
themselves are deleted as well.

Besides Web Content Folders, examples of asset containers include Bookmarks
Folders, Message Boards Categories, Wiki Nodes, and Documents and Media Folders.

You might not need to create a Role for a certain functionality. Liferay
provides many pre-configured Roles for your convenience.

## Default Liferay Roles [](id=default-liferay-roles)

In the Roles Application appears a list of all the Roles in Liferay, by
scope. 

These are some of the pre-configured regular Roles:
- Guest: The Guest role is assigned to unauthenticated users and grants the
  lowest-level permissions. 
- User: The User role is assigned to authenticated Users and grants basic
  permissions (mostly *Add to Page* permissions for their own Sites).
- Power User: The Power User Role grants more permissions than the User Role.
  It's an extension point for distinguishing regular Users from
  more privileged Users. For example, you can set things up so that only
  Power Users have personal sites.
- Administrator: The administrator Role grants permission manage the entire
  portal, including global portal settings and individual Sites, Organizations,
  and Users.

These are some of the pre-configured site roles:
- Site Member: The Site Member Role grants basic privileges within a Site, such
  as permission to visit the Site's private pages.
- Site Administrator: The Site Administrator Role grants permission to manage
  *almost* all aspects of a Site including site content, site memberships, and
  site settings. Site Administrators cannot delete the membership of or remove
  roles from other Site Administrators or Site Owners. They also *cannot* assign
  other Users as Site Administrators or Site Owners.
- Site Owner: The Site Owner Role is the same as the Site Administrator Role
  except that it grants permission to manage *all* aspects of a Site, including
  permission to delete the membership of or remove Roles from Site
  Administrators or other Site Owners. They *can* assign other users as Site
  Administrators or Site Owners.

These are some of the pre-configured organization roles:
- Organization User: The Organization User role grants basic privileges within
  an Organization. If the Organization has an attached Site, the Organization
  User Role implicitly grants the Site member Role within the attached Site.
- Organization Administrator: The Organization Administrator Role grants
  permission to manage *almost* all aspects of an Organization including the
  Organization's Users and the Organization's Site (if it exists). Organization
  Administrators cannot delete the membership of or remove Roles from other
  Organization Administrators or Organization Owners. They also *cannot* assign
  other Users as Organization Administrators or Organization Owners.
- Organization Owner: The Organization Owner Role is the same as the
  Organization Administrator Role except that it grants permission to manage
  *all* aspects of an Organization, including permission to delete the
  membership of or remove Roles from Organization Administrators or other
  Organization Owners. They *can* assign other Users as Organization
  Administrators or Organization Owners.

+$$$

**Tip:** It's easy to overlook the differences between owner type roles and
administrator type roles for Sites and Organizations. Site and Organization
administrators *cannot* remove the administrator or owner Role from any other
administrator or owner, and they *cannot* appoint other Users as site or
organization administrators or owners. 

In contrast, site and organization owners *can* do those things.

$$$

Roles, and the permissions granted with their assignment, are foundational
components in Liferay. Understanding their uses and configuration enhances
your ability to configure @product@ to suit your organizational needs.
