# Adding and Editing Users

At the root of managing users is adding, editing, and deleting them. As long as
you're the Administrative user, you can add users to the portal. 

## Adding Users

Adding users is straightforward:

1.  From the Product Menu, click *Control Panel* &rarr; *Users* &rarr; *Users
and Organizations*. 

2.  In the Users tab, click the Add button <!-- ([Add](../../images/icon-add.png)-->.

    <!-- REPLACE [Figure 1: Add Users from the Users and Organizations section of the Control Panel.](../../images/usrmgmt-add-user.png)-->

3.  Fill out the Add User form and click *Save*. At a minimum, provide a Screen
Name, First Name, Last Name, and Email Address for the User.

+$$$

**New for 7.1:** The Add User functionality has been split over several
independent forms. Saving the first form creates the user, and then you'll see a
success message saying

    The user was created successfully. You can now add additional information about the user.

$$$

After submission of the first form, you're directed to the Information form of
the Add User functionality. To the left, there's a navigation pane where you can
continue configuring the user you're adding by clicking through the available
forms. The options in the left menu change as you click through the tabs at the
top. Peruse the forms for the three tabs (General, Contact, Preferences) and
fill in all the applicable information.

![Figure x: At a minimum, enter a screen name, email address, and first name to create a new user account. Then you'll be taken to the Information form and can continue configuring the user.](../../images/add-user-forms-menu.png)

You don't have to fill anything else out right now. Just note that when the user
account was created, a password was automatically generated. If Liferay was
correctly installed and a mail server was set up (see
[here](/discover/portal/-/knowledge_base/7-1/server-administration#mail)), an
email message with the user's new password was sent to the user's email address.
This, of course, requires that Liferay can properly communicate with your SMTP
mail server.

If you haven't set up a mail server, click the Password item from the General
menu to access the Password section of the Add User functionality. Manually set
a password for your new user. Enter the new password twice.

<!-- [Figure 3: Enter the password twice to manually set the password for a user. If the Password Policy you're using is configured to allow it, select whether to require the user to reset their password the first time they sign in to the portal.](../../images/usrmgmt-require-password-reset.png) -->

+$$$

**Note:** See the *Require Password Reset* checkbox at the bottom of the
Password form? The default password policy is set so that even you, the
administrator, cannot deselect it. As the administrator, however, you do have
the ability to modify the default password policy so that this box becomes
usable. Navigate to Password Policies in the Control Panel, click on the Default
Password Policy, and deselect the *Change Required* switcher in the Password
Changes section. Now you can decide whether each user you add will need to reset
their password. 

See the article on Password Policies for more information on editing the default
policy or creating your own.

$$$

### Adding an Administrative User [](id=adding-an-administrative-user)

What if you are just setting up the Lunar Resort portal for the first time, and
you're using the default administrator account, the account of one of those
famous Liferay Administrators, *Test Test* or her cousin, *Joe Bloggs*? Since
you're the administrator of the Lunar Resort portal, you want to set up your own
administrator account for the portal. Use the steps above to add a user with
your information, then give your user account the same administrative rights as
the default administrator's account, click the *Roles* link in the left
navigation pane (in the General tab). This page of the form shows the roles to
which your account is currently assigned. You should have one role: Power User. 

+$$$

**Power Users:** Users are not assigned the Power User role by default. The
Power User role grants mroe permissions than the User role. If the User role is
sufficient for you, ignore the Power User role. Alternatively, use it to extend
provide a second level of user permissions and assign it to those users. If
there are certain custom permissions that you'd like all of your portal users to
have, you can grant these custom permissions to the User role. You can also
customize the default roles a new user receives via *Default User Associations*.
This is covered in the article on Instance Settings.

$$$

Assign the Administrator role to your user account. On the form for editing a
user, after having clicked on *Roles* at the left side of the screen, click the
*Select* link under Regular Roles. A dialog box pops up with a list of all the
regular (portal-scoped) roles in the portal. Select the Administrator role from
the list (click *Choose*). The dialog box disappears and the role is added to
the list of roles associated with your account. You are now a portal
administrator. Log out of the portal and then log back in with your own user
account. 

+$$$

In production, you should always delete or disable the default administrator
account to secure your portal.

$$$

Next, let's look at some other aspects of user management. 

## Editing Users [](id=editing-users)

If you click on *Users and Organizations* in the Control Panel, you'll see your
own user's account in the list of users, along with any others. To change
something about a particular user, click the *Actions* button (the vertical
ellipsis icon) next to that user.

Choosing *Edit* takes you back to the Edit User page where you can modify any
aspect of the user account including the screen name, email address, first name,
last name, site and organization memberships, roles, etc.

Choosing *Permissions* allows you to define which roles have permissions to edit
the user.

Choosing *Manage Pages* allows you to configure the personal pages of a user.

Choosing *Impersonate User* opens another browser window which allows you to
browse the site as if you were the user. This allows you to test your user
management on a user to make sure you're achieving the desired behavior, without
having to repeatedly log out of your administrator account and into the user's
account.

Choosing *Deactivate* deactivates the user's account. The user will still be in
your databse,along with all the rest of your users, but their account will be
deactivated, so that they cannot sign in to the portal. If you have any
deactivated users, you'll have the option to view active portal users or
inactive portal users. If all the users are active, this filtering option will
not appear.

Choosing *Erase Personal Data* deletes the users personal data. Read
[here](LINK) for more information.

Choosing *Export Personal Data* lets you download the user's personal data.
Read [here](LINK) for more information.

<!--REPLACE [Figure x: You can choose whether to view active or inactive (deactivated) portal users in the users list found at *Product Menu* &rarr; *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*.](../../images/usrmgmt-active.png) -->

Most users can't perform most of the above actions. In fact, most users won't
have access to the Control Panel at all. You can perform all of the above
functions because you have administrative access.

## User Images [](id=user-images)

Users in @product@ have avatars. Administrative users can upload images in the
Edit User form, and any user can update thier account information, including
their image, from their personal site (*My account &rarr; Account Settings*). 

<!-- REPLACE [Figure 5: Upload images for user avatars in the Edit User form.](../../images/users-ray-avatar.png) -->

If no image is explicitly uploaded for a user's avatar, a default image is
assigned. By default the initials of the user are displayed (First Name then
Last Name) over a random color.

<!-- REPLACE [Figure 6: If Johannes Bach was a user in your @product@ instance, his default avatar might look like this.](../../images/users-default-user-image.png) -->

If the default approach for generating user images isn't suitable for your
portal, disable the inclusion of users' initials in the default avatars: 

1.  Navigate to Control Panel &rarr; Configuration &rarr; System Settings

2.  Find the Users category. Click *User Images*.

3.  Deselect *Use Initials for Default User Portrait*.

<!--
lang.user.default.portrait=initials
lang.user.initials.field.names=first-name,last-name
-->

![Figure 7: Wolfgang Amadeus Mozart's default avatar, after disabling the use of
user initials.](../../images/users-alternate-default-image.png)

Learn how to take advantage of more localization strategies by leveraging
[language settings](/developer/tutorials/-/knowledge_base/7-1/using-liferays-language-settings).

## Numeric Screen Names

In prior versions, the ability to use numeric user screen names was disabled via
the default portal property

    users.screen.name.allow.numeric=false

Other user management systems (LDAP, for example) did not have the same
restriction, which made importing users into @product@ more difficult.
Administrators first had to set the  above property to `true` before importing
(or possibly after an initial failed import and lots of cursing). In
@product-ver@, this property defaults to `true`:

    users.screen.name.allow.numeric=true

This means you're free to set a user screen name to *97854*, or whatever other
number you can think of, and imports from systems that allow numeric screen
names will go more smoothly. That's everything you need to know to take
advantage of this feature. Keep reading to understand what enabled the change.

Because users have personal sites, the URL to user *97854*'s personal site is

    http://localhost:8080/web/97854

Meanwhile, a default URL to a site cleverly named *test site* is

    http://localhost:8080/web/test-site

There's no conflict here, but two conditions could easily lead to one:

1.  The site's `groupId` matches the number chosen for the user's screen
    name. Each site has a unique numeric identifier in the database and backend
    code, called `groupId`. There's nothing stopping it from matching the user's
    numeric screen name, so it could easily be `97854` just like the
    hypothetical user above.

2.  A site administrator comes along and changes the site's friendly URL to
    match its `groupId`. Hello, URL conflict! Now the site's URL matches the
    user's URL:

        http://localhost:8080/web/97854

This conflict is no longer possible. In @product-ver@, a site's friendly URL
is not allowed to be numeric. See for yourself:

1.  Navigate to the site's Configuration &rarr; Site Settings
&rarr; Site URL section.

2.  In the Friendly URL section, enter *97854* and save the form. You'll see
    this failure message if you don't have a user with the matching screen
    name:

        The friendly URL may conflict with another page.

    You'll see this failure message if there's an existing conflict with a user
    screen name:

        Please enter a unique friendly URL. Site [user-first-name user-last-name] has the same friendly URL.

Next, learn about collecting users in organizations.
