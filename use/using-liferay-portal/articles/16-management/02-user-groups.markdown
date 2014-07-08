# User Groups [](id=user-groups-lp-6-2-use-useportal)

User Groups are designed to allow portal administrators to create groups of
users that traverse the organizations hierarchy. They can be used to create
arbitrary groupings of users who don't necessarily share an obvious hierarchical
attribute. Users can be assigned to multiple user groups. For example, consider
a software company with many offices and departments within each office. The
company's office/department structure could be modeled through organizations. In
this situation, it might make sense to create user groups for developers, office
managers, accountant, etc. User Groups are most often used to achieve one of the
following goals:

- To simplify the assignment of several roles to a group of users. For example,
  in a University portal, a user group could be created to group all teachers
  independently of their organizations to make it easier to assign one or
  several roles at once to all the teachers.

- To simplify membership to one or more sites by specifying a group of users.
  Using the previous example, all teachers could be members of the sites
  *University Employees* and *Students and Teachers Collaboration Site* by
  adding the *Teachers* user group as a member.

- To provide predefined public or private pages to the users who belong to the
  user group. For example, the *Teachers* user group could be created to ensure
  the home page on all teachers' personal sites has the same layout and
  applications.

Creating a user group is easy. Navigate to the Control Panel, click on the
*Users Groups* link and then click on the *Add* button. There's only one
required field: Name. It's usually best to enter a description as well. Click
*Save* and you will be redirected back to the *User Groups* page of the Control
Panel.

![Figure 16.5: When creating a new user group, you can select a site template for the public or private pages of the user group site. If you don't select a site template at creation time, you can edit the user group later to add one.](../../images/server-configuration-new-user-group.png)

Note in the figure above that a user group can have a site, with public and
private pages. A user group site is a special type of site that determines the
base pages of the personal sites of all the user group members. User group sites
work similarly to site templates, except that user group site pages are not
copied for each user. Instead, they are shown dynamically along with any custom
pages that the user may have on his/her personal site. For this reason, users
are not allowed to make any modifications to the pages that are *inherited* from
the user group. Optionally, the administrators of the user group can define
certain areas as customizable, just like they can for regular sites. This allows
users to decide which applications they want to place in certain areas of each
page, as well as customize the configurations of the applications.

![Figure 16.6: To edit the pages of a user group site, click *Actions* &rarr; *Manage Site Pages* next to the user group you'd like to edit.](../../images/01-editing-a-user-group.png)

As with the other resources in the portal, you can click the *Actions* button
next to a user group to perform various operations on that group.

**Edit:** allows you to modify the name or description of the user group.

**Permissions:** lets you define which roles have permissions to view, edit,
delete, assign members to the user group, etc.

**Site Permissions:** lets you define which roles have permissions to manage the
user group site, to specify who can administer its pages, export and import
pages and portlet content, manage archived setups and configure its
applications. 

**Manage Site Pages:** allows you to add pages to the user group site, import or
export pages, organize the page hierarchy, modify the look and feel of the
pages, add a logo or access other options from the Manage Site interface.

**Assign Members:** lets you search for and select users in the portal to be
assigned to this user group as well as view the users currently belonging to the
user group .

**Delete:** removes the user group.

If your user group has a site, two options named *Go to the Site's Public Pages*
and *Go to the Site's Private Pages* also appear as links in your user group's
Actions menu. Clicking one of these links opens the user group's site in a new
browser window. Any changes you make to the site are saved automatically. You
can safely close the browser window when you're done.

## Creating and Editing a User Group

A user group's site can be administered from the Control Panel. Click on *User
Groups* from the Control Panel to see a list of existing user groups. To edit a
user group, click on its name or description. You can also click on the
*Actions* button to see the full list of actions that can be performed on a user
group. When editing a user group, you can view its site, if it exists, by
clicking the *Open Pages* link under Public Pages or Private Pages (read below
for details on user group sites).

As an example of how user group sites can be used, let's create a user group
called *Bloggers* along with a simple site template. We'll call the site
template *Bloggers* too. It should contain a single *Blog* page with the Blogs
and Recents Bloggers portlets on it. First, navigate to the User Groups page of
the Control Panel. Then click *Add* and enter the name *Bloggers* for your user
group, and optionally, a description. Click *Save* to create your user group.

Our next step is to assign an existing user to the *Bloggers* group.

## Assigning Members to a User Group

Navigate to *Users and Organizations* and create a new user called *Joe Bloggs*.
Then navigate to the User Groups page of the Control Panel and click *Actions*
&rarr; *Assign Members* next to the Bloggers group. Click the *Available* tab to
see a list of users that can be assigned to the group.

![Figure 16.7: When assigning members to a user group, click on the *Available* tab to see a list of users able to be added to the user group.](../../images/01-adding-members-to-user-group.png)

From this list, one or more users can be assigned as members of the user group.
After the user group has been created and several users have been added to it,
you can add all those users at once as members of a site in one step from the
*Site Memberships* UI of the site. You can also use the user group when
assigning a role to users from the roles management UI. The next section
explains how to use user group sites.

## User Group Sites

Liferay allows users to each have a personal site consisting of public and
private pages. Permissions can be granted to allow users to customize their
personal sites at will. Originally, the default configuration of those pages
could only be determined by the portal administrator through the
`portal-ext.properties` file and, optionally, by providing the configuration in
a LAR file. You can still configure it like this but it isn't very flexible or
easy to use.

By using User Group Sites, portal administrators can add pages to the personal
sites of all the users who belong to the site in an easy and centralized way.
All the user group site's public pages are shown as part of the user's public
personal site. All the user group site's private pages are shown as part of the
user's private site. If a user belongs to several user groups, all of its pages
are made part of his public and private site. In an educational institution's
portal, for example, teachers, staff and students could get different default
pages and applications on their personal sites.

The pages a user's personal site *inherits* from a User Group still belong to
the User Group and thus cannot be changed in any way by the user. What the user
group administrators can do is define certain areas of the pages as customizable
to allow the users to choose which applications and what configuration should be
shown in those areas. If a user has permission to add custom pages to his/her
personal site, besides those *inherited* from a user group, the custom pages are
always shown last.

Since the *inheritance* of pages is done dynamically, this system, which was
introduced in Liferay 6.1, can scale to hundreds of thousands of users or even
millions of them without an exponential impact in performance. Previous versions
of Liferay used a different technique that required user group pages be copied
to each user's personal site. For portals upgrading from previous versions of
Liferay, you can keep the old behavior but it has been left disabled by default.
You can enable it by adding the following line to your portal-ext.properties
file:

	user.groups.copy.layouts.to.user.personal.site=true

When this property is set to true, once the template pages have been copied to a
user's personal site, the copies may be modified by the user. Changes done to
the originals in the User Group will only affect new users added to the user
group. Users with administrative privileges over their personal sites can modify
the pages and their content if the *Allow Site Administrators to Modify the
Pages Associated with This Site Template* box has been checked for the template.
When a user is removed from a user group, the associated pages are removed from
the user's personal site. Moreover, if a user is removed from a group and is
subsequently added back, the group's template pages are copied to the user's
site a second time. Note that if a user group's site is based on a site template
and an administrator modifies the user group's site template after users have
already been added to the group, those changes only take effect if the *Enable
propagation of changes from the site template* box for the user group was
checked.

---

 ![Tip](../../images/01-tip.png) **Tip:** Prior to Liferay 6.1, pages from
 different user groups could be combined on users' personal sites by using a
 naming convention. Liferay 6.1 simplifies the way user groups' sites work by
 disallowing page combination. Set the property
 *user.groups.copy.layouts.to.user.personal.site* to true if you want to
 preserve the page combination functionality.

---

You can create a user group's site manually or base it on a site template. To
create a user group's site manually, use the *Actions* menu mentioned above and
choose *Manage Site Pages*. You can add a new public or private page by
selecting the appropriate tab and then clicking the *Add Page* button. Once the
user group has at least one public or private page in place, you can go back to
the *Actions* menu and click on the *Go to the Site's Public Pages* or *Go to
the Site's Private Pages* link to open the user group's site in a new browser
window. In the new window, you can add more pages and portlets and configure
site settings.

You can also base a user group's site on a template. When editing a user group,
use the Public Pages and Private Pages drop down lists to select a site
template. Leave the *Enable propagation of changes from the site template* box
checked to automatically update users' personal sites if the associated site
template changes. If you uncheck this box but recheck it later, the template
pages are copied to the users' sites, overwriting any changes they may have
made. You can allow users to make changes to the pages they receive from the
user group by enabling the customization options on each page.

This flexibility lets you achieve almost any desired configuration for a user's
personal site without having to modify it directly. When a user is assigned to a
user group, the configured site pages are copied directly to the user's personal
site.

Continuing with the example above, we will create a site for our sample user
group. Edit the *Bloggers* user group. Choose an existing Site Template from the
drop down menu for the user group's public pages and click *Save*. After the
page reloads you can click to see the pages and make any changes desired, add
additional pages, etc.

![Figure 16.8: You can select a site template to apply to a user group's public or private pages if the public or private page set is empty. If pages have already been added to the page set, you'll have to remove them before you can apply a site template the page.](../../images/server-configuration-new-user-group.png)

Also, try visiting the public site of one of the users who belongs to the user
group. You'll see that all of the pages in the user group appear as part of the
user site, including ones copied from the site template and ones added
afterwards.
