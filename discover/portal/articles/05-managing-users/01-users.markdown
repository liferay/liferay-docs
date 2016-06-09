# Adding and Managing Users [](id=adding-and-managing-users)

Users are a fundamental entity in Liferay. If your portal requires people (even
just a set of site administrators) to have an account that they sign into to do
anything in the portal, you need to know about users. If your users are at all
divided hierarchically, like into departments, you might find Organizations
helpful. See the article on
[organizations](discover/portal/-/knowledge_base/6-2/the-users-section-of-the-control-panel)
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
permissions within the portal.
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
## Adding Users [](id=adding-users)

As the Lunar Resort Administrative user, you can add Users to the portal. 

1.  From the Product Menu, click *Control Panel* &rarr; *Users* &rarr; *Users
and Organizations*. 

1.  Click *Add* &rarr; *User*.

    ![Figure 1: Add Users from the Users and Organizations section of the Control Panel.](../../images/usrmgmt-add-user.png)
    
    <!-- That screenshot is definitely going to be replaced. The add button here
    is not lexiconified -->

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
[here](discover/portal/-/knowledge_base/6-2/server-administration#mail)), an
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

Next, learn about collecting users in organizations.
