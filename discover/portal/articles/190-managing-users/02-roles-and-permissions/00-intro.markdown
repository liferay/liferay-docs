# Roles and Permissions

If a *role* were to win a Grammy or an Oscar or some other ego-feeding
popularity contest, it better remember to thank all its *permissions* groupies
during the acceptance speech, because they're the ones doing the real work. The
role is just the pretty face, so to speak.

Roles collect permissions that define a particular function within
@product@, according to a particular scope. Roles collect permissions, and
users are assigned to roles, either directly or through their association with a
User Group, an Organization, or a Site. 

Take a Message Board Administrator role, for example. A role with that name
is likely to have permissions relevant to the specific Message Board portlets
delegated to it. Users with this role inherit the permissions collected underneath
the umbrella of the role.

In addition to regular roles, site roles, and organization roles, there are also
teams. Teams can be created by site administrators within a specific site. The
permissions granted to a team are defined and applied only within the team's
site. The permissions defined by regular, site, and organization roles, by
contrast, are defined at the portal level, although they are applied to
different scopes. 

Regular role
: Permissions are defined at the portal level and are applied at the portal-wide
scope.

Site role
: Permissions are defined at the portal level and are applied to one specific
site.

Organization role
: Permissions are defined at the portal level and are applied to one specific
organization.

Team
: Permissions are defined within a specific site and are assigned within that
specific site.

Read 
[here](/discover/portal/-/knowledge_base/7-1/creating-teams-for-advanced-site-membership-management) 
for more information about teams.

## Deleting Asset Containers [](id=deleting-asset-containers)

A Web Content Folder contains Web Content articles. The Web Content Folder is
an asset container, and the Web Content Article is an asset. It's possible to
give a role permission to delete an asset container without giving the role
permission to delete individual assets. In that case, beware: if a role assignee
deletes an asset container with individual assets in it, the individual assets
themselves *will* be deleted as well.

Besides Web Content Folders, examples of asset containers include Bookmarks
Folders, Message Boards Categories, Wiki Nodes, and Documents and Media Folders.

You might not need to create a role for a certain functionality. Liferay
provides many pre-configured roles for your convenience.

## Default Liferay Roles [](id=default-liferay-roles)

In the Roles Application, you'll see a list of all the roles in Liferay, by
scope. 

These are some of the pre-configured regular roles:
- Guest: The Guest role is assigned to unauthenticated users and grants the
  lowest-level permissions within the portal.
- User: The User role is assigned to authenticated users and grants basic
  permissions within the portal (mostly *Add to Page* permissions for
  applications).
- Power User: The Power User role grants more permissions than the User role.
  It's designed to be an extension point for distinguishing regular users from
  more privileged users. For example, you can set up your portal so that only
  Power Users have personal sites.
- Administrator: The administrator role grants the ability to manage the entire
  portal, including global portal settings and individual sites, organizations,
  and users.

These are some of the pre-configured site roles:
- Site Member: The Site Member role grants basic privileges within a site, such
  as the ability to visit the site's private pages.
- Site Administrator: The Site Administrator role grants the ability to manage
  *almost* all aspects of a site including site content, site memberships, and
  site settings. Site Administrators cannot delete the membership of or remove
  roles from other Site Administrators or Site Owners. They also *cannot* assign
  other users as Site Administrators or Site Owners.
- Site Owner: The Site Owner role is the same as the Site Administrator role
  except that it grants the ability to manage *all* aspects of a site, including
  the ability to delete the membership of or remove roles from Site
  Administrators or other Site Owners. They *can* assign other users as Site
  Administrators or Site Owners.

These are some of the pre-configured organization roles:
- Organization User: The Organization User role grants basic privileges within
  an organization. If the organization has an attached site, the Organization
  User role implicitly grants the Site member role within the attached site.
- Organization Administrator: The Organization Administrator role grants the
  ability to manage *almost* all aspects of an organization including the
  organization's users and the organization's site (if it exists). Organization
  Administrators cannot delete the membership of or remove roles from other
  Organization Administrators or Organization Owners. They also *cannot* assign
  other users as Organization Administrators or Organization Owners.
- Organization Owner: The Organization Owner role is the same as the
  Organization Administrator role except that it grants the ability to manage
  *all* aspects of an organization, including the ability to delete the
  membership of or remove roles from Organization Administrators or other
  Organization Owners. They *can* assign other users as Organization
  Administrators or Organization Owners.

+$$$

**Tip:** It's easy to overlook the differences between owner type roles and
administrator type roles for sites and organizations. Site and organization
administrators *cannot* remove the administrator or owner role from any other
administrator or owner, and they *cannot* appoint other users as site or
organization administrators or owners. 

In contrast, site and organization owners *can* do those things.

$$$

Roles, and the permissions granted with their assignment, are foundational
components in Liferay. Understanding their uses and configuration enhances
your ability to configure @product@ to suit your organizational needs.
