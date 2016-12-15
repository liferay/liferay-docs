# User Groups [](id=user-groups)

A User Group is a group of users. Were you hoping for a more
complicated, or enterpris-ey, definition? Try this: A user group is a box.
What's inside the box? Users. Is that definition still lacking? A user group
collects users that don't fit together outside of the specific function that the
user group was formed to fulfill. That's really all it is. Of course, this is
Liferay, so there's a ton of flexibility built in to user groups, and they're
integrated with Roles, Sites, Site Templates, Permissions, and, of course Users.
Because of that, there are a lot of different places where you can interact
with user groups, and there's more than one use case they can fulfill. In this
article you'll learn how to work with user groups to serve the most
common use cases. 

User groups allow portal administrators to create groups of users that traverse
hierarchical boundaries. For example, consider The Lunar Resort. It's
hierarchically divided into the Physical Plant Department, the Recreation
Department, and the Sales Department. The Lunar Resort's department-driven
structure is modeled using
[organizations](/discover/portal/-/knowledge_base/6-2/the-users-section-of-the-control-panel).
If there are functions needed in The Lunar Resort public or private sites that
can't be divided up according to the existing organizational hierarchy, you
might need user groups. For example, it might make sense to create a user groups
that includes the Human Resources representatives of each department. User
Groups are most often used to achieve one of the following goals:

<!-- Provide links above to 7-0 docs on organizations, when available-->

- Collect permissions. With User Groups, you can assign roles and permissions to
  a group of users that don't share an Organization. For example, in a
University portal, a user group could be created to group all teachers
independently of their organizations to make it easier to assign one or several
roles at once to all the teachers. In The Lunar Resort, we're going to use this
approach to give members of several organizations the *List Creator* role.

- Manage site membership. Using the previous example, all teachers could be
  members of the sites *University Employees* and *Students and Teachers
Collaboration Site* by adding the *Teachers* user group as a member. In the
Lunar Resort, you can add all the List Creators to the public facing site of The
Lunar Resort.

- Manage user personal pages. Provide predefined public or private pages to the
  users who belong to the user group. For example, the *Teachers* user group
could be created to ensure the home page on all teachers' personal sites has the
same layout and applications. In The Lunar Resort, you'll add a site template
that adds an Event Coordination page to each List Creator.

At the Lunar Resort, certain employees need the ability to create simple form
applications using Liferay's
[Dynamic Data Lists](/discover/portal/-/knowledge_base/7-0/creating-data-lists).
On the harsh lunar landscape, it's important to have a proper idea how many
guests are coming to the next Lunar Hike event. Many of the list creators will
be users from the Recreation Department, since many of the activities that
guests should know about are managed by that department. So why not just give
all the users of the Recreation Department the proper permissions for managing
the Dynamic Data Display portlets on the Activities Sign-Up page, and be done
with it? Sometimes other departments will need to add an event (for example, the
kitchen staff is preparing a Lunar Luau and needs a guest list). Also, you might
not want all the users of the Recreation Department to be a List Creator.
Remember that time Wilbert Donahue (he's a Lunar Rover Race Instructor, part of
the Recreation Department) was granted blogging permissions, and he used the
opportunity to write all about his favorite band, *ApeFLogger*? You don't want
Wilbert to be a List Creator, right?

<!-- Maybe add: Insidious Marmot, Urban Slug, The SlimeDoggz, The LumberJilz,
The WAR Boys, Metropolizm, Fanatical Mime to band list -->

![Figure 1: The List Creators group is needed to manage the sign-up lists on the Activities Sign-Up page of The Lunar Resort.](../../images/user-groups-activities-page.png)

## Creating a User Group [](id=creating-a-user-group)

User Groups are administered in the Control Panel, under *Users* &rarr; *User
Groups*. Start by creating a user group called *List Creators*. Later, you'll
also create a new site template for a user group site that user group members
can access to coordinate event scheduling, and you'll use the group to assign
all its users to The Lunar Resort public site. First create the user group:

1.  Navigate to the Control Panel by opening the Product Menu (click
![Menu](../../images/icon-menu.png)) and clicking *Control Panel*.

2.  Click on the *Users Groups* link and then click on the *Add* button.

    *Name* is the only required field, but you should enter a
    description as well.

    ![Figure 2: Provide a name and a description for your new User Group.](../../images/user-groups-add.png)

3.  Click *Save* and you will be redirected back to the *User Groups* page of
the Control Panel.

Right now, the user group is just an empty box. The user group has no sites or
roles to give it any functionality. No users are assigned to the user group, so
it isn't collecting users yet. That comes next.

## Assigning Members to a User Group [](id=assigning-members-to-a-user-group)

To add existing users to a user group:

1.  Navigate to the User Groups page of the Control Panel and click *Actions*
(![Actions](../../images/icon-actions.png)) &rarr; *Assign Members* next to the
List Creators group. 

    ![Figure 3: To add users to the User Group, select *Assign Members* from its Action menu.](../../images/user-groups-actions.png)

2.  Click the *Add Users* button to see a list of users that can
be assigned to the group.

    ![Figure 4: When assigning members to a user group, click on the *Available* tab to see a list of users able to be added to the user group.](../../images/user-groups-add-users.png)

3.  From this list, select one or more users and click *Add*.

Once you have a user group with users in it, what are you going to do with it?
The List Creators group is destined to get some specific permissions in the
Lunar Resort, but you can use user groups for site membership management or
personal page management. These scenarios are all covered in this article.

Next, add the users in the List Creators group to the Lunar Resort site. 

## User Groups and Site Membership [](id=user-groups-and-site-membership)

After a user group is created, you can add all its users at once as members of a
site in one step from the *Site Memberships* UI. In this example you'll see how
the newly created List Creators user group can be added to The Lunar Resort
site. This makes managing site membership easy. Just make sure you have the
correct site selected in the Product Menu!

![Figure 5: Select the site you want to manage using the site selector.](../../images/user-groups-site-selector.png)

Associate the user group with a site:

1.  From the Product Menu, select the site you want to work in (The Lunar
Resort) and open the site administration drop-down.

2.  Click *Users* &rarr; *Site Memberships*.

    ![Figure 6: Manage site memberships from the Product Menu.](../../images/user-groups-site-memberships.png)

3.  Click on the *User Groups* tab, click the Add
button (![Add](../../images/icon-add.png)) and select the List Creators user group.

    ![Figure 7: User groups let you assign many users to a site at once.](../../images/user-groups-add-to-site.png)

Now members of the List Creators user group are site members of The Lunar Resort
site.

Managing site membership with user groups is easy. User groups can also have
their own sites. 

## User Group Sites [](id=user-group-sites)

Each Liferay user has a personal site, consisting of public (Profile) and
private (Dashboard) pages. A user group site determines the base pages of the
personal sites of all the user group
members. If the user group site pages are added to a user's Profile pages, then
the user group site is a public site, accessible to anyone with the URL
(`http://www.[sitename].com/web/[username]`). If the site pages are added to the
user's Dashboard pages, then the site is a private site. A mixed approach can
also be used, where both private and public pages are added for the user group
site. If a user belongs to multiple user groups, all the pages from those user
group sites are made part of hiss personal site. 

<!--Link above when docs are created-->

When you created the List Creators user group, did you notice the section of the
New User Group form that allowed you to select a site template from two drop
down menus, *My Profile* and *My Dashboard*? That's where you add user group
sites from a site template. You can add a user group site while adding the user
group initially, or add one later if you decide you need to add one. You can
also add pages to the user group site manually, if you don't want to use a site
template. But that's done later, from the Actions menu of an already-created
user group.

![Figure 8: You can create a user group site from a site template while adding the User Group initially, or you can revisit and edit this form if you decide to add a site later.](../../images/user-groups-add.png)

In The Lunar Resort, the List Creators must have access to the private *Event
Coordination* page, with a Calendar application where events are scheduled that
the user group members will create lists for, and a Message Boards portlet to
discuss and resolve scheduling conflicts. Using site templates along with the
Calendar and Message Boards applications, you can easily do this. First you need
to create a site template for use in the user group site and deploy the
applications to the site pages.

To create a new site template, navigate to *Sites* &rarr; *Site Templates*. Add
a site template called Event Coordination and save it. Rename the default Home
page to Event Coordination. Now there's a site template, but the page is
currently empty. Go to the Event Coordination page, and add a Message Boards and
a Calendar application. Read the [documentation on site templates](discover/portal/-/knowledge_base/6-2/using-site-templates)
if you need further explanation.

<!--Get the 7-0 link when ready-->

Once you have a site template set up, use it to create the user group site.

1.  Navigate to the *Users* &rarr; *User Groups* section of the Control Panel. 

2.  To edit the List Creators user group, click the name of the group or the
Actions button (![Actions](../../images/icon-actions.png)), then *Edit*, for the user group.

3.  Since the pages should be private pages for the users of the site, open the
drop-down menu under *My Dashboard* and select the new site template you
created, Event Coordination.

    ![Figure 9: Selecting a site template under My Dashboard creates a private site for a user group.](../../images/user-groups-add-site-template.png)

4.  Click *Save*.

Now, when one of the List Creators signs in to The Lunar Resort and clicks on
their *My Dashboard* link, the Event Coordination page will be added to their
private page set.

User group site pages function similarly to regular site template pages, with an
important exception: user group site pages are not copied for each user. They're
shown dynamically along with any custom pages that the user may have on his/her
personal site. For this reason, users are not allowed to make any modifications
to the pages that are inherited from the user group. If needed, the user group
administrator can define certain areas of a page as customizable, like with
regular sites. This allows users to add and configure applications 
in the specified area of the page.

You can create a user group's site manually, instead of basing it on a site
template. To create a user group's site manually, use the *Actions* menu
mentioned above and choose *Manage Pages*. Add a new public or private page by
selecting My Profile or My Dashboard, then click the *Add Page* button. Once the
user group has at least one public or private page in place, you can go back to
the *Actions* menu and click on the *Go to the Profile Pages* or *Go to
the Dashboard Pages* link to open the user group's site in a new browser
window. In the new window, you can add more pages and portlets and configure
site settings.

![Figure 10: From a user group's Actions menu, select *Manage Pages* to create a user group site manually.](../../images/user-groups-manual-site.png)

In the example above, you based the List Creator user group's site on a
template. Leave the *Enable propagation of changes from the site template* box
checked to automatically update users' personal sites if the associated site
template changes. If you uncheck this box but recheck it later, the template
pages are copied to the users' sites, overwriting any changes they may have
made. You can allow users to make changes to the pages they receive from the
user group by enabling the customization options on each page.

This flexibility lets you achieve almost any desired configuration for a user's
personal site without having to modify it directly. When a user is assigned to a
user group, they'll immediately have access to the user group's site pages from
their personal site.

### Legacy User Group Sites Behavior [](id=legacy-user-group-sites-behavior)

Since the inheritance of user group site pages is now dynamic, even if there are
hundreds of thousands of users, even millions, there won't be an exponential
impact in performance. Previous versions of Liferay required user group pages be
copied to each user's personal site. If you really liked the old days, or if
you're upgrading from an older version of Liferay and need to keep the old
behavior, enable it by adding the following line to your portal-ext.properties
file:

	user.groups.copy.layouts.to.user.personal.site=true

When this property is set to true, the template pages are copied to a user's
personal site once, and then may be modified by the user. This means that If
changes are made to the template pages later, they will only affect users that
are added to the user group after the change is made. Users with administrative
privileges over their personal sites can modify the pages and their content if
the *Allow Site Administrators to Modify the Pages Associated with This Site
Template* box has been checked for the template. When a user is removed from a
user group, the associated pages are removed from the user's personal site.
If a user is removed from a group and is subsequently added back, the
group's template pages are copied to the user's site a second time. Note that if
a user group's site is based on a site template and an administrator modifies
the user group's site template after users have already been added to the group,
those changes only take effect if the *Enable propagation of changes from the
site template* box for the user group was checked.

Note that Prior to Liferay Portal 6.1, pages from different user groups could be
combined on users' personal sites by using a naming convention. Liferay Portal 6.1
simplifies the way user groups' sites work by disallowing page combination. Set
the property *user.groups.copy.layouts.to.user.personal.site=true* to preserve
the page combination functionality.

Just like user groups simplify site membership, they can also be used to assign
a role to all the users of a user group at once. Where you do it depends on the
scope of the role. 

## Configuring User Group Permissions [](id=configuring-user-group-permissions)

The List Creators user group needs the ability to manage Dynamic Data List
Display portlets on The Lunar Resort's Activities Sign-Up public page. This will
require the creation of a new Site Role called List Creator, and assignment of
any necessary permissions so the List Creators can do their job. You can refer
to the [article on Roles](/discover/portal/-/knowledge_base/6-2/roles-and-permissions) for a more detailed discussion. After creating
the new role, all that's left to do is assign the role to the user group.

<!--Link to 7-0 docs when ready-->

To create a new role, go to the Roles section of the Control Panel and click
*Add* &rarr; *Site Role*. Call it *List Creator*, give it a description, and
save it. From the list of roles, open the Actions menu for your new role, and
select *Define Permissions*. Select *Site Administration* &rarr; *Applications*
&rarr; *Dynamic Data Lists Display*. Select just the *Configuration* and *View*
permissions and click *Save.* Go back to the list of roles by clicking *Roles*
from the Product Menu or by clicking the back arrow
(![Back](../../images/icon-back.png)) at the
top left of the Define Permissions window.

Now you have a user group, and a site role that's ready to be used in The Lunar
Resort site. The user group members are also members of The Lunar Resort site,
so all that's left is to associate the user group with the role.

<!-- Some sort of schematic showing this relationship?-->

1.  Go to The Lunar Resort's Site Membership section.

2.  In the Site Memberships window, User Groups tab, open the
configuration menu (![Actions](../../images/icon-actions.png)) for the List
Creators user group and select *Assign Site Roles*.

    ![Figure 11: From a user group's Actions menu, select *Manage Pages* to create a user group site manually.](../../images/user-groups-site-role.png)

3.  Choose the *List Creator* role from the list and click *Add*.

Now members of the List Creators user group are site members of The Lunar Resort
site, and anyone in the user group has the List Creator role in the site. If you
want to continue configuring the List Creators user group, read the articles on
[Creating Simple Applications](/discover/portal/-/knowledge_base/7-0/creating-simple-applications).
In those articles you'll learn to create a data definition that the List
Creators can use to build new Activities Sign-Up lists and templates with.

<!-- Link above not published yet-->

If you want to know more about User Groups, and how to edit them, keep reading
here.

## Editing User Groups [](id=editing-user-groups)

To edit the basic properties a user group, just click on its name or description
(both are hyperlinks). Alternatively, click the *Edit* link from the Actions
menu of the user group. Also in the *Actions* menu, you'll see the full list
of actions that can be performed on a user group. When editing a user group, you
can view its site, if it exists, by clicking the *Open Pages* link under Public
Pages or Private Pages (read above for details on user group sites).

As with the other resources in the portal, you can click the Actions button
(![Actions](../../images/icon-actions.png)) next to a user group to perform
various operations on that group.

![Figure 12: The user group's Actions menu has various configuration options.](../../images/user-groups-actions.png)

**Edit** allows you to modify the name, description, or choose the
site template for a user group site.

**Permissions** lets you define which roles have permissions to view, edit,
delete, assign members to the user group, etc.

**User Group Pages Permissions** lets you define which roles have various
permissions related to managing the
user group's site pages.

**Manage Pages** takes you to the page administration section of the user
group's site. You can add pages to the user group site, import or
export pages, organize the page hierarchy, modify the look and feel of the
pages, add a logo or access other options from the Manage Site interface.

**Assign Members** lets you search for and select users in the portal to be
assigned to this user group as well as view the users currently belonging to the
user group.

**Delete** removes the user group.

+$$$

**Note:** If there are users in the User Group, you won't be able to delete it.
You'll first need to remove the users from the User Group.

$$$

If your user group has site pages, the options *Go to Profile Pages* and *Go to
Dashboard Pages* also appear in your user group's Actions menu. Clicking one of
these links opens the user group's site in a new browser window. Any changes you
make to the site are saved automatically. You can safely close the browser
window when you're done.

![Figure 13: If your user group has public and private site pages, you'll see *Go to Profile Pages* and *Go to Dashboard Pages* in the Actions menu.](../../images/user-groups-actions-site-pages.png) 

User groups are useful for managing site membership and roles, and for assigning
a specific task to a  specific group of users. Or, if you like tongue twisters,
this article covered *the usual uses that use useful user groups*. Let us know
if there's any functionality we missed, or if there's something that's not clear
from this article, by clicking the *Do you have any suggestions?* link, or the
*Feedback* link. Good jokes (okay, any jokes) can also be submitted. 

