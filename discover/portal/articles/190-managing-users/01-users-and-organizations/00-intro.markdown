# Users and Organizations

*Users* and *organizations* are fundamental entities in Liferay. If your portal
requires people (even just a set of site administrators) to have an account
that they sign into to do anything in the portal, you need to know about users.
If your users are at all divided hierarchically, like into departments,
you'll find that organizations are helpful. 

Whether or not you've ever used Liferay before, you're probably not surprised to
hear that users and organizations are managed in the Control Panel's *Users and
Organizations* section. If it were any different, it'd be weird. 

Consider the Lunar Resort portal. Consider what you'd do if 

- an employee leaves the company to join that pesky competitor, Martian Resort
  and Luxury Spa.
- an employee joins the resort as a new Mechanical Crew member.
- an employee is promoted from Crew Supervisor to Department Head, and needs the requisite
permissions within @product@.
- you need to organize the users by department.
- a new department is added to the Lunar Resort and the employees need their own
internal website.
- an employee gets married, and their name changes.

The user tasks listed above are all resolved in the Users and Organizations
section of the Control Panel.

## What are Users? [](id=what-are-users)

In case there's any confusion over the term, a User in Liferay is an entity that
can sign into the portal and do something. Generally a User has more
privileges, called Permissions, than a Guest of your site, who does not sign in.
Users are assigned a Role (the Power User Role by default), and a Role is what
defines the user's privileges.

Understanding users is pretty straightforward. Organizations are a bit trickier,
but a smart person like you is undoubtedly up to the challenge. 

## What are Organizations? [](id=what-are-organizations)

An Organization is a way to group Liferay Users hierarchically. For example,
each of your company's departments (Human Resources and Customer Support, for
example) could be an organization in Liferay. Often times, organizations have
their own site.

Many simple portal designs don't use organizations at all; they only use sites
(see the sections on 
[Web content Management](/discover/portal/-/knowledge_base/7-1/web-content-management) 
and
[Advanced Web Content Management](/discover/portal/-/knowledge_base/7-1/advanced-web-content-management)
for more information on sites). The main purpose of organizations is to enable
distributed user management. Using organizations, portal administrators can
delegate some user management responsibilities to organization administrators.
If you don't anticipate needing to delegate user management responsibilities,
your portal design probably doesn't need to include organizations. 

### When to Use Organizations

To decide whether your portal design should include organizations, think about
your portal's function. A simple photo-sharing web site could be powered by
sites only. On the other hand, organizations are useful for corporations or
educational institutions since their users can easily be placed into a
hierarchical structure. Don't think that organizations are only for large
enterprises, though. Any group hierarchy, from large government agencies all the
way down to small clubs, can be modeled with organizations. Also, don't think
that you must decide between an organization based structure or a site based
structure for assembling your portal's users. Users can belong both to
organizations and to independent sites. For example, a corporation or
educational institution could create a social networking site open to all portal
users, even ones from separate organizations. 

To illustrate what an Organization is, consider a potential organization of the
Lunar Resort's intranet. The company hierarchy has three tiers: The Lunar
Resort, its Departments, and divisions within each department.

- Lunar Resort--The top-level Organization.
	- Physical Plant Department--Department of users that keep the place running.
		- Grounds Crew--Users that maintain the grounds.
		- Janitorial Crew--Users who keep the resort clean.
		- Mechanical Crew--Users who fix stuff, like lunar rovers.
	- Recreation Department--A department that makes sure much fun
	  is had by guests of the Lunar Resort.
        - Golf Instructors--Teach guests how to golf on the moon.
        - Rover Race Instructors--Teach guests how to drive the lunar rovers.
        - Lunar Sherpas--Lead guests on moon hikes.
    - Sales Department--A department of users who sell things to Lunar
      Resort guests.
        - Up-sale Group--Make sure guests know how easy it is to improve
          their stay by spending more money.
        - Souvenir and Memorabilia Group--Peddle souvenirs to Lunar Resort guests.
        - Retail Group--Maintain the Lunar Resort store, which
          contains basic necessities, since guests are coming all the way from Earth.

Each department is a sub-organization of the resort, and each division
is a sub-organization of the department.

+$$$

**User Groups and Organizations:** It's easy to confuse User Groups (covered in
a separate article) with Organizations since they both group users. User Groups
are usually an ad hoc collection of users, organized for a specific function in
the portal. In the Lunar Resort, if you wanted a group of bloggers, for example,
it wouldn't make sense to assign the Sales Department the role of blogging (see
the article on roles if you're not sure what they are). The Sales Department
users would be able to blog whenever a new t-shirt design became available in
the Lunar Resort store, but they probably wouldn't be as diligent about
announcing the new Rover Racing schedule. Instead, creating a user group
containing one individual from each department who is responsible for blogging
would make more sense. Read the article on User Groups to learn more about how
to use them in your portal.

$$$

### What can Organization Administrators Do?

Whenever you have a collection of users that fit into a hierarchical structure,
you can use organizations to model those users. Organization administrators can
manage all the users in their organization *and* in any sub-organization.
Referring to the hierarchy above, for example, an organization administrator of
the Lunar Resort could manage any users belonging to the resort itself, to any
of the departments, or to any of a department's subdivisions. An Organization
Administrator of the Physical Plant Department can manage any users belonging to
the Physical Plant Department itself, or to the Grounds Crew, the Janitorial
Crew, or the Mechanical Crew. However, an administrator of the Physical Plant
Department can't manage users belonging to the Recreation Department or users
in the Retail Group organization.

Organizations and sub-organization hierarchies can be created to unlimited
levels. Users can be members of one or many organizations. The rights of an
Organization Administrator apply both to his/her organization and to any child
organizations. Members of child organizations are implicit members of their
parent organizations. This means, for example, that members of child
organizations can access the private pages of their parent organizations. This
behavior can be customized in your portal's `portal-ext.properties`
configuration file. There's an `Organizations` [section of the
portal.properties](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Organizations)
file where the properties specific to organizations are listed. 

Since organizations are designed for distributed user administration,
Organization Administrators have an entirely different set of privileges than
Site Administrators. Site Administrators are responsible for the pages,
portlets, and content of their site. They are also responsible for managing the
membership of their site. To this end, they can set the membership type to Open,
Restricted, or Private. They can also add users to or remove users from their
site but cannot manage the users themselves. If an organization has a site
attached to it, the Organization Administrator has the same administrative
ability as a Site Administrator when it comes to managing the site's content,
but the site membership management is different. An organization site's members
will simply be the members of the organization. Organization Administrators have
more user management permissions than Site Administrators; they can edit users
belonging to their organization or any sub-organization. They cannot add
existing portal users to their organization, but they can create new users
within their organization. Only portal administrators can add existing users to
an organization.

Organization Administrators can't access the Control Panel by default, but they
don't need to. In their personal site, organization administrators can click on
the *My Organizations* link to gain access to any organizations they manage.

<!-- REPLACE [Figure 1: The My Organizations application lets Organization Administrators manage their organizations in their personal site.](../../images/organizations-my-organizations.png)-->

### Organization Roles and Permissions

A huge time-saving benefit of including organizations into your portal design is
that organization administrators can assign organization-scoped roles to members
of the entire organization. For example, consider an IT Security group in a
corporate setting. You could have a sub-organization of your IT organization
that handles security for all of the applications company-wide. If you grant the
IT Security organization the portal administrator role, all the members of the
organization would have administrative access to the entire portal. Suppose
further that a user in this organization was later hired by the Human Resources
department. The simple act of removing the user from the IT Security
organization also removes the user's administrative privileges, since the
privilege came from the IT Security organization's role. By adding the user to
the HR organization, any roles the HR organization has (such as access to a
benefits system in the portal) are transferred to the user. In this manner, you
can design your portal to correspond with your existing organization chart and
users' permissions are granted according to their positions in the chart.

Of course, this is only one way to set up your portal. If you have more complex
requirements for permissions within an organization, you can create custom
organization-scoped roles to assemble the permissions you wish to grant to
particular users. Alternatively, you could consider attaching a site to your
organization and using site teams to assemble the sets of permissions (see
below). See the [Roles and Permissions article](https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/roles-and-permissions) for more detail.

### Organization Sites

Does your organization need to have its own site? Many organizations don't, but
since some do, Liferay allows sites to be attached to organizations. If an
organization has an attached site, the organization's administrators are treated
as the site administrators of the attached site. This means that they can manage
the pages, portlets, and content of the site as well as the users of the
organization. Members of an organization with an attached site are treated as
members of the organization's site. This means that they can access the private
pages of the organization's site, along with any portlets or content there. The
capability of attaching sites to organizations allows portal administrators to
use organizations to facilitate distributed portal administration, not just
distributed user administration. 

That's a lot of information on organizations. Next, learn how to create and
manage users and organizations. 






MOVE TO SEPARATE ARTICLE
-------------------------------------------------------
## Adding Users [](id=adding-users)

As the Lunar Resort Administrative user, you can add Users to the portal. 

1.  From the Product Menu, click *Control Panel* &rarr; *Users* &rarr; *Users
and Organizations*. 

1.  In the Users tab, click the Add button (![Add](../../images/icon-add.png).

    ![Figure 1: Add Users from the Users and Organizations section of the Control Panel.](../../images/usrmgmt-add-user.png)

1.  Fill out the Add User form and click *Save*. At a minimum, provide a Screen
Name, First Name, Last Name, and Email Address for the User.

    You're on the Details page of the Add User form at this point. To the right,
there's a navigation pane where you can continue configuring the user you're
adding by clicking either Organizations or Personal Site.

Once you save the form, the User is added to the portal.

![Figure 2: At a minimum, enter a screen name, email address, and first name to create a new user account.](../../images/your-request-completed-successfully.png)

After you submit the form, the page reloads with a success message. An expanded
form appears for editing the user's account (see all the new categories in the
navigation pane to the right of the form?). This allows you to fill out a lot
more information about the user. You don't have to fill anything else out right
now. Just note that when the user account was created, a password was
automatically generated. If Liferay was correctly installed and a mail server
was set up (see
[here](/discover/portal/-/knowledge_base/6-2/server-administration#mail)), an
email message with the user's new password was sent to the user's email address.
This, of course, requires that Liferay can properly communicate with your SMTP
mail server.

If you have not set up a mail server, you can click the Password link to access
the Password section of the Add User form to manually set a password for your
new user. Enter the new password twice.

![Figure 3: Enter the password twice to manually set the password for a user. If the Password Policy you're using is configured to allow it, select whether to require the user to reset their password the first time they sign in to the portal.](../../images/usrmgmt-require-password-reset.png)

+$$$

**Note:** See the *Require Password Reset* checkbox at the bottom of the
Password form? The default password policy is set so that even you, the
administrator, cannot deselect it. As the administrator, however, you do have
the ability to modify the default password policy so that this box becomes
usable. Navigate to Password Policies in the Control Panel, click on the Default
Password Policy, and deselect the *Change Required* checkbox. Now you can decide
whether each user you add will need to reset their password. 

See the article on Password Policies for more information on editing the default
policy or creating your own.

$$$

If you haven't yet set up a mail server, use this page to change the default
password for your user account to something you can remember. Clicking on the
*Password* link in the navigation pane on the right opens the Password section
of the Add User form. Enter the new password twice and click *Save*. 

### Adding an Administrative User [](id=adding-an-administrative-user)

What if you are just setting up the Lunar Resort portal for the first time, and
you're using the default administrator account, the account of one of those
famous Liferay Administrators, *Test Test* or her cousin, *Joe Bloggs*? Since
you're the administrator of the Lunar Resort portal, you want to set up your own
administrator account for the portal. Use the steps above to add a user with
your information, then give your user account the same administrative rights as
the default administrator's account, click the *Roles* link in the right
navigation pane. This page of the form shows the roles to which your account is
currently assigned. You should have one role: Power User. 

+$$$

**Note:** By default, all users are assigned the Power User role. The Power User
role doesn't grant any special permissions. It can safely be ignored.
Alternatively, it can be used to extend the User role. If there are certain
custom permissions that you'd like all of your portal users to have, you can
grant these custom permissions to the Power User role. You can also customize
the default roles a new user receives via *Default User Associations*. This is
covered in the article on Portal Settings.

$$$

Assign the Administrator role to our user account. On the form for
editing a user, after having clicked on *Roles* at the right side of the screen,
click the *Select* link under Regular Roles. A dialog box pops up with a list of
all the regular (portal-scoped) roles in the portal. Select the Administrator
role from the list. The dialog box disappears and the role is added to the list
of roles associated with your account. Don't forget to click the *Save* button
which is at the bottom of the blue bar of links on the right side of the screen.
You are now a portal administrator. Log out of the portal and then log back in
with your own user account. 

+$$$

In production, you should always delete or disable the default administrator
account to secure your portal.

$$$

Next, let's look at some other aspects of user management. 

## Editing Users [](id=editing-users)

If you click on *Users and Organizations* in the Control Panel, you'll see your
own user's account in the list of users, along with any others. If you want to
change something about a particular user, you can click the *Actions* button
(the vertical ellipsis icon) next to that user.

<!-- Add screenshot here, this is definitely a lexicon element (the ellipsis actions
button) -->

- Choosing *Edit* takes you back to the Edit User page where you can modify any aspect
of the user account including the screen name, email address, first name, last
name, site and organization memberships, roles, etc.

- Choosing *Permissions* allows you to define which roles have permissions to edit the
user.

- Choosing *Manage Pages* allows you to configure the personal pages of a user.

    <!--Screenshot here and in between all these bullets, maybe? -->

- Choosing *Impersonate User* opens another browser window which allows you to browse
the site as if you were the user. This allows you to test your user management
on a user to make sure you're achieving the desired behavior, without having to
repeatedly log out of your administrator account and into the user's account.

- Choosing *Deactivate* deactivates the user's account. The user will still be in
your databse,along with all the rest of your users, but their account will be
deactivated, so that they cannot sign in to the portal. If you have any
deactivated users, you'll have the option to view active portal users or
inactive portal users. If all the users are active, this filtering option will
not appear.

    ![Figure 4: You can choose whether to view active or inactive (deactivated) portal users in the users list found at *Product Menu* &rarr; *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*.](../../images/usrmgmt-active.png)

Note that most users can't perform most of the above actions. In fact, most
users won't have access to the Control Panel at all. You can perform all of the
above functions because you have administrative access.

## User Images [](id=user-images)

Users in @product@ have avatars. Administrative users can upload images in the
Edit User form. 

![Figure 5: Upload images for user avatars in the Edit User form.](../../images/users-ray-avatar.png)

If no image is explicitly uploaded for a user's avatar, a default image is
assigned, using the initials of the user (First Name then Last Name) over a
random color.

![Figure 6: If Johannes Bach was a user in your @product@ instance, his default avatar might look like this.](../../images/users-default-user-image.png)

For many locales, the default approach for generating user images is perfectly
suitable. For some locales, though, the default user image is misleading or
confusing. For example, there are locales where the order of the initials is
reversed (Last Name then First Name), and there are locales where the use of one
character of the first name and one character of the last name produces
meaningless results to the reader (especially languages not based on the Latin
alphabet). If that's true for your locale, disable the inclusion of users'
initials in the default avatars. Just enter

    users.image.default.use.initials=false

in a `portal-ext.properties` file placed in your 
[Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-product#liferay-home) 
folder. Once you restart @product@, the new default images will be used to
generate user avatars.

![Figure 7: Wolfgang Amadeus Mozart's default avatar, after disabling the use of
user initials.](../../images/users-alternate-default-image.png)

Learn how to take advantage of more localization strategies by leveraging
[language settings](/developer/tutorials/-/knowledge_base/7-0/using-liferays-language-settings).

Next, learn about collecting users in organizations.
