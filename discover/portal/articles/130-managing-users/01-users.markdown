---
header-id: adding-and-managing-users
---

# Adding and Managing Users

[TOC levels=1-4]

Users are a fundamental entity in Liferay. If your portal requires people (even
just a set of site administrators) to have an account that they sign into to do
anything in the portal, you need to know about users. If your users are at all
divided hierarchically, like into departments, you might find Organizations
helpful. See the article on
[organizations](/docs/6-2/user/-/knowledge_base/u/the-users-section-of-the-control-panel)
for more information.

Whether or not you've ever used Liferay before, you're probably not surprised to
hear that Users are managed in the Control Panel's *User* section. If it were
any different, it'd be weird. 

For the Lunar Resort example portal that we're developing, consider what you'd
do if 

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

## What are Users?

In case there's any confusion over the term, a User in Liferay is an entity that
can sign into the portal and do something. Generally a User has more
privileges, called Permissions, than a Guest of your site, who does not sign in.
Users are assigned a Role (the Power User Role by default), and a Role is what
defines the user's privileges.

Understanding users is pretty straightforward. So how do you, the administrator,
add users to the portal?

<!-- Users and organizations tool tip form portal: Users and Organizations Users are individuals who perform tasks using the
portal. Administrators can create new users or deactivate existing users. Users
can join sites, be placed into organization hierarchies, be delegated
permissions in roles, or be collected into user groups. User groups can cross
organizational boundaries, and can be used to assign all members to other
collections, such as sites or roles. User groups may also be used to customize
personal site templates for members.

-->
## Adding Users

As the Lunar Resort Administrative user, you can add Users to the portal. 

1.  From the Product Menu, click *Control Panel* &rarr; *Users* &rarr; *Users
and Organizations*. 

1.  In the Users tab, click the Add button (![Add](../../images/icon-add.png).

    ![Figure 1: Add Users from the Users and Organizations section of the Control Panel.](../../images/usrmgmt-add-user.png)

1.  Fill out the Add User form and click *Save*. At a minimum, provide a Screen
Name, First Name, Last Name, and Email Address for the User.

    Below the form, you can continue configuring the user by clicking either 
    Organizations or Personal Site Template.

Once you save the form, the User is added to the portal.

![Figure 2: At a minimum, enter a screen name, email address, and first name to create a new user account.](../../images/your-request-completed-successfully.png)

After you submit the form, the page reloads with a success message. An expanded
form appears for editing the user's account (see all the new categories listed 
below the form?). This lets you fill out a lot more information about the user. 
You don't have to fill anything else out right now. Just note that when the user 
account was created, a password was automatically generated. If Liferay was 
correctly installed and a mail server was set up (see
[here](/docs/6-2/user/-/knowledge_base/u/server-administration#mail)), an
email message with the user's new password was sent to the user's email address.
This, of course, requires that Liferay can properly communicate with your SMTP
mail server.

If you haven't yet set up a mail server, use this page to change the default
password for your user account to something you can remember. Clicking on the
*Password* section heading below the form opens the Password section of the Add 
User form. Enter the new password twice and click *Save*. 

![Figure 3: Enter the password twice to manually set the password for a user. If the Password Policy you're using is configured to allow it, select whether to require the user to reset their password the first time they sign in to the portal.](../../images/usrmgmt-require-password-reset.png)

| **Note:** See the *Require Password Reset* checkbox at the bottom of the
| Password form? The default password policy is set so that even you, the
| administrator, cannot deselect it. As the administrator, however, you do have
| the ability to modify the default password policy so that this box becomes
| usable. Navigate to Password Policies in the Control Panel, open the Actions
| menu for the Default Password Policy, open the *Password Changes* section, and 
| toggle the *Change Required* checkbox to *NO* and save. Now you can decide 
| whether each user you add will need to reset their password.
| 
| See the article on Password Policies for more information on editing the default
| policy or creating your own.

### Adding an Administrative User

What if you are just setting up the Lunar Resort portal for the first time, and
you're using the default administrator account, the account of one of those
famous Liferay Administrators, *Test Test* or her cousin, *Joe Bloggs*? Since
you're the administrator of the Lunar Resort portal, you want to set up your own
administrator account for the portal. Use the steps above to add a user with
your information, then give your user account the same administrative rights as
the default administrator's account, click the *Roles* link below the form. This 
section shows the roles to which your account is currently assigned. At the 
moment, no roles are listed.

| **Note:** You can also customize the default roles a new user receives via 
| *Default User Associations*. This is covered in the article on Portal 
| Settings.

Assign the Administrator role to your user account. On the form for editing a 
user, after having clicked on the *Roles* section below the form, click the 
*Select* button above Inherited Regular Roles. A dialog box pops up with a list 
of all the regular (portal-scoped) roles in the portal. Select the Administrator 
role from the list. The dialog box disappears and the role is added to the list 
of roles associated with your account. Don't forget to click the *Save* button 
which is at the bottom of the page, below all the sections. You are now a portal 
administrator. Log out of the portal and then log back in with your own user 
account. 

| In production, you should always delete or disable the default administrator
| account to secure your portal.

Next, let's look at some other aspects of user management. 

## Editing Users

If you click on *Users and Organizations* in the Control Panel, you'll see your
own user's account in the list of users, along with any others. If you want to
change something about a particular user, you can click the *Actions* button
(the vertical ellipsis icon) next to that user.

![Figure 4: Open the Actions menu next to a listed user to update its configuration.](../../images/usrmgmt-user-actions.png)

- Choosing *Edit* takes you back to the Edit User page where you can modify any aspect
of the user account including the screen name, email address, first name, last
name, site and organization memberships, roles, etc.

- Choosing *Permissions* allows you to define which roles have permissions to edit the
user.

- Choosing *Manage Pages* allows you to configure the personal pages of a user.

![Figure 5: You can manage a user's personal pages through the user's Actions menu.](../../images/usrmgmt-user-actions-manage-pages.png)

- Choosing *Impersonate User* opens another browser window which allows you to browse
the site as if you were the user. This allows you to test your user management
on a user to make sure you're achieving the desired behavior, without having to
repeatedly log out of your administrator account and into the user's account.

- Choosing *Deactivate* deactivates the user's account. The user will still be in
your database, along with all the rest of your users, but their account will be
deactivated, so that they cannot sign in to the portal. If you have any
deactivated users, you'll have the option to view active portal users or
inactive portal users. If all the users are active, this filtering option will
not appear.

![Figure 6: You can choose whether to view active or inactive (deactivated) portal users in the users list found at *Product Menu* &rarr; *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*.](../../images/usrmgmt-active.png)

Note that most users can't perform most of the above actions. In fact, most
users won't have access to the Control Panel at all. You can perform all of the
above functions because you have administrative access.

## User Images

Users in @product@ have avatars. Administrative users can upload images in the
Edit User form. 

![Figure 7: Upload images for user avatars in the Edit User form.](../../images/users-ray-avatar.png)

If no image is explicitly uploaded for a user's avatar, a default image is
assigned, using the initials of the user (First Name then Last Name) over a
random color.

![Figure 8: If Johannes Bach was a user in your @product@ instance, his default avatar might look like this.](../../images/users-default-user-image.png)

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
[Liferay Home](/docs/7-0/deploy/-/knowledge_base/d/installing-product#liferay-home) 
folder. Once you restart @product@, the new default images will be used to
generate user avatars.

![Figure 7: Wolfgang Amadeus Mozart's default avatar, after disabling the use of
user initials.](../../images/users-alternate-default-image.png)

Learn how to take advantage of more localization strategies by leveraging
[language settings](/docs/7-0/tutorials/-/knowledge_base/t/using-liferays-language-settings).

Next, learn about collecting users in organizations.
