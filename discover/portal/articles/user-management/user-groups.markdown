<!-- Correct Typo in user-groups-admin-web Language.properties "autoatically"-->
<!-- Figures and captions -->

# User Groups [](id=user-groups)

User Groups are designed to allow portal administrators to create groups of
users that traverse the organizations hierarchy. They can be used to create
arbitrary groupings of users who don't necessarily share an obvious hierarchical
attribute. Users can be assigned to multiple user groups. For example, consider
The Lunar Resort. It's hierarchically divided into the Physical Plant
Department, the Recreation Department, and the Sales Department). The Lunar
Resort's department structure is modeled through organizations. Because of that,
it might make sense to create a user groups that includes the Human Resources
representatives of each department. User Groups are
most often used to achieve one of the following goals:

- Collect permissions. With User Groups, you can assign roles to a group of
  users that don't share an Organization. For example, in a University portal, a
user group could be created to group all teachers independently of their
organizations to make it easier to assign one or several roles at once to all
the teachers. In The Lunar Resort, we're going to use this approach to give
members fo several organizations the *List Creator* role.

- Manage site membership. Using the previous example, all teachers could be
  members of the sites *University Employees* and *Students and Teachers
Collaboration Site* by adding the *Teachers* user group as a member.

- Delegate page management. Provide predefined public or private pages to the
  users who belong to the user group. For example, the *Teachers* user group
could be created to ensure the home page on all teachers' personal sites has the
same layout and applications.

At the Lunar Resort, certain employees need the ability to create simple form
applications using Liferay's [Dynamic Data
Lists](/discover/portal/-/knowledge_base/7-0/using-dynamic-data-lists). On the
harsh lunar landscape, it's important to have a proper idea how many guests are
coming to the next Lunar Hike event.  Primarily, users from the Recreation
Department will be our list creators, since most of the activities that guests
should know about are managed by that department. So why not just give all the
users of the Recreation Department the proper permissions for managing the
Dynamic Data Display portlets on the Activities Sign-Up page, and be done with
it? Sometimes other departments need to add an event (for example, the kitchen
staff is preparing a Lunar Luau and needs a guest list), and, you might not want
all the users of the Recreation Department to be a List Creator. Remember that
time Wilbert Donahue (he's a Lunar Rover Race Instructor, part of the Recreation
Department) was granted blogging permissions, and he used the opportunity to
write all about his favorite band? You don't want Wilbert to be a List Creator,
right?

<!-- IMAGE OF ACTIVITIES SIGN-UP PAGE, WITH DDL DISPLAY PORTLETS ADDED? -->

## Creating a User Group

User Groups are administered in the Control Panel, under *Users* &rarr; *User
Groups*. Create a user group called *List Creators* along with a simple site
template. Call the site template *List Creators* too. It should contain a single
*List Testing* page with a few Dynamic Data List Display portlets on it. First
create the user group:

1.  First, navigate to the Control Panel by opening the Product Menu and
clicking *Control Panel*.

2.  Click on the *Users Groups* link and then click on the *Add* button.

    *Name* is the only required field, but you should enter a
    description as well.

3.  Click *Save* and you will be redirected back to the *User Groups* page of
the Control Panel.

Right now, the user group is just an empty box with nothing in it. The user
group has no sites or roles to give it any functionality. No users are assigned
to the user group, so it isn't collecting users yet. That comes next.

## Assigning Members to a User Group [](id=assigning-members-to-a-user-group)

If you don't already have users to work with, navigate to *Users and
Organizations* and create a new user called *Wilbert Donahue*. 

To add existing users to a user group:

1.  Navigate to
the User Groups page of the Control Panel and click *Actions*
(![Actions](../../images/actions-icon.png)) &rarr; *Assign Members* next to the
List Creators group. 

2.  Click the *Add Users* button to see a list of users that can
be assigned to the group.

    ![Figure X: When assigning members to a user group, click on the *Available* tab
to see a list of users able to be added to the user group.](../../../images/01-adding-members-to-user-group.png)

3.  From this list, assign one or more users to the user group.

Once you have a user group with users in it, what are you going to do with it?
The next step for the List Creators user group is to give it some functionality.
The List Creators group is destined to get some specific permissions in the
Lunar Resort, but you can use user groups for site membership management or
delegating page management responsibilities. These scenarios are all covered in
this article.

## Configuring User Group Permissions

The List Creators user group needs the ability to manage Dynamic Data List
Display portlets on The Lunar Resort's public page, Activities Sign-Up. This
will require the creation of a new Role called List Creator, and assignment of
any necessary permissions so the List Creators can do their job.

To create a new role, go to the Roles section of the Control Panel and click
*Add* &rarr; *Site Role*. Call it *List Creator*, give it a description, and
save it. From the list of roles, open the Actions menu for your new role, and
select *Define Permissions*. Select *Site Administration* &rarr; *Applications*
&rarr; *Dynamic Data Lists Display*. Select just the *Configuration* and *View*
permissions and click *Save.* Go back to the list of roles by clicking *Roles*
from the Product Menu or by clicking the back arrow <!--Image of Icon?-->at the
top left of the Define Permissions window.

Now you have a user group with a user in it, and a site role that's ready to be
used in The Lunar Resort site. You still need to associate the user group with a
site, and associate the user group with the role.

1.  From the Product Menu, select the site you want to work in (The Lunar
Resort).

2.  Click *Users* &rarr; *Site Memberships*.

3.  Click on the *User Groups* tab, click the Add button,<!--ADD ICON--> and select the List Creators user group.

4.  Still in the Site Memberships window, User Groups tab, open the
configuration menu for the List Creators user group and select *Assign Site
Roles*.

5.  Choose the *List Creator* role from the list and click *Add*.

<!-- Above needs screen shots -->

Now members of the List Creators user group are site members of The Lunar Resort
site, and anyone in the user group has the List Creator role in the site.


<!--MAKE SURE YOU DO COVER THESE -->

<!-- Move later -->
## Managing Site Membership with User Group

After the user group has been created and several users have been added to it,
you can add all those users at once as members of a site in one step from the
*Site Memberships* UI of the site. 




You can also use the user group when
assigning a role to users from the roles management UI. The next section
explains how to use user group sites.


![Figure X: When creating a new user group, you can select a site template for
the public or private pages of the user group site. If you don't select a site
template at creation time, you can edit the user group later to add
one.](../../../images/server-configuration-new-user-group.png)

<!--Add a User Group Sites section? -->

A user group can have a site, with public and private pages. A user group site
is a special type of site that determines the base pages of the personal sites
of all the user group members. User group sites work similarly to site
templates, except that user group site pages are not copied for each user.
Instead, they are shown dynamically along with any custom pages that the user
may have on his/her personal site. For this reason, users are not allowed to
make any modifications to the pages that are *inherited* from the user group.
Optionally, the administrators of the user group can define certain areas as
customizable, just like they can for regular sites. This allows users to decide
which applications they want to place in certain areas of each page, as well as
customize the configurations of the applications.

![Figure X: To edit the pages of a user group site, click *Actions* &rarr;
*Manage Site Pages* next to the user group you'd like to edit.](../../../images/01-editing-a-user-group.png)

## Editing User Groups
To edit a user group, click on its name or description. You can also
click on the *Actions* button to see the full list of actions that can be
performed on a user group. When editing a user group, you can view its site, if
it exists, by clicking the *Open Pages* link under Public Pages or Private Pages
(read below for details on user group sites).

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

## User Group Sites [](id=user-group-sites)

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

+$$$

**Tip:** Prior to Liferay 6.1, pages from
 different user groups could be combined on users' personal sites by using a
 naming convention. Liferay 6.1 simplifies the way user groups' sites work by
 disallowing page combination. Set the property
 *user.groups.copy.layouts.to.user.personal.site* to true if you want to
 preserve the page combination functionality.

$$$

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

![Figure X: You can select a site template to apply to a user group's public
or private pages if the public or private page set is empty. If pages have
already been added to the page set, you'll have to remove them before you can
apply a site template the page.](../../../images/server-configuration-new-user-group.png)

Also, try visiting the public site of one of the users who belongs to the user
group. You'll see that all of the pages in the user group appear as part of the
user site, including ones copied from the site template and ones added
afterwards.
