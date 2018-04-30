# Adding, Editing, and Deleting Users [](id=adding-editing-and-deleting-users)

At the root of managing Users is adding, editing, and deleting them. As long as
you're the Administrative user, you can do all these things and more. 

## Adding Users [](id=adding-users)

Adding Users is straightforward:

1.  From the Product Menu, click *Control Panel* &rarr; *Users* &rarr; *Users
and Organizations*. 

2.  In the Users tab, click the *Add* button <!-- ([Add](../../images/icon-add.png)-->.

    ![Figure 1: Add Users from the Users and Organizations section of the Control Panel.](../../../images/usrmgmt-add-user.png)

3.  Fill out the Add User form and click *Save*. At a minimum, provide a Screen
    Name, First Name, Last Name, and Email Address for the User.

    The Add User functionality is split over several independent forms. Saving
    the first form creates the user, and then you'll see a success message
    saying

        The user was created successfully. You can now add additional information about the user.

After submission of the first form, you see a larger form with many sections.
The one you're on is the Information section. To the left is a navigation
pane where you can continue configuring the user you're adding by clicking
through the available sections. The options in the left menu change as you
click through the tabs at the top. Peruse the sections for the three tabs
(General, Contact, Preferences) and fill in all the applicable information.

![Figure 2: At a minimum, enter a screen name, email address, and first name to create a new user account. Then you'll be taken to the Information form and can continue configuring the user.](../../../images/add-user-forms-menu.png)

You don't have to fill anything else out right now. Just note that when the user
account was created, a password was automatically generated. If Liferay was
correctly installed and a 
[mail server was set up](/discover/portal/-/knowledge_base/7-1/server-administration#mail), 
an email message with the User's new password was sent to the User's email
address.

If you haven't set up a mail server, click the *Password* item from the General
menu and manually set a password for your new user. Enter the new password
twice.

![Figure 3: Enter the password twice to manually set the password for a user. If the Password Policy you're using is configured to allow it, select whether to require the user to reset their password the first time they sign in to the portal.](../../../images/usrmgmt-require-password-reset.png)

## Editing Users [](id=editing-users)

If you click on *Users and Organizations* in the Control Panel, you'll see your
own user's account in the list of Users, along with any others. To change
something about a particular user, click the *Actions* button
(![Actions](../../../images/icon-actions.png)) next to that user.

Choosing *Edit* takes you back to the Edit User page where you can modify any
aspect of the User account including the screen name, email address, first name,
last name, Site and Organization memberships, Roles, etc.

Choosing *Permissions* allows you to define which Roles have permissions to edit
the User.

Choosing *Manage Pages* allows you to configure the personal pages of a User.

Choosing *Impersonate User* opens another browser window that loads the site as
if you were the User so you can test your User management on a User to make
sure you're achieving the desired behavior, without having to repeatedly log
out of your administrator account and into the User's account.

Choosing *Deactivate* deactivates the user's account. The User is still in
your database along with all the rest of your Users, but the account is
deactivated, so the User cannot sign in to the portal. You can toggle between
active and inactive Users in the Users view. If all the Users are active, this
filtering option doesn't appear.

Choosing *Erase Personal Data* deletes the User's personal data. <!--Read
[here](LINK) for more information.-->

Choosing *Export Personal Data* lets you download the User's personal data.
<!--Read [here](LINK) for more information.-->

![Figure 4: You can choose whether to view active or inactive (deactivated) portal users in the users list found at *Product Menu* &rarr; *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*.](../../../images/usrmgmt-active.png)

Most Users can't perform any of the above actions. In fact, most Users won't
have access to the Control Panel at all. You can perform all of the above
functions because you have administrative access.

## Deleting Users [](id=deleting-users)

You must be careful when deleting Users. To guard against accidental deletion
of Users, a two-step process must be followed: deactivate first, then delete.

1.  Find the User to delete in the Users tab of *Control Panel* &rarr; *Users*
    &rarr; *Users and Organizations*. If you have a lot of Users, save time by
    searching for the User.

2.  Click the *Actions* menu for the User and select *Deactivate*. You're asked to
    confirm that you want to deactivate the User. Click *OK*. 

    You'll see a success message and the User disappears, but isn't gone yet.

3.  By default the Users table displays only Active users. Click on *Active* in
    the top of the table and a dropdown menu appears. Click *Inactive*, and you
    can see the User you just deactivated. 

4.  Click the Actions menu again, and click *Delete* if you really mean to
    delete the User. Confirm that you want to delete the User, and now the User
    is gone. This time, it's for real.

+$$$

**Deactivated Users:** Deactivating a user means the user can't log in to the
portal. He/she has no more permissions in the sites and pages of the portal than
a guest, although the account still exists in the system. 

Users are reactivated by a portal administrator finding them in the Users table
(be sure you're filtering the table results by Deactivated users), clicking the
Actions menu, and selecting Activate. There's no confirmation window for
activation: they're automatically restored to their former status once Activate
is clicked.

$$$

Now you understand the basic principles of user administration. There are
important additional topics in the next article that you should consider
mandatory information for all portal administrators, so do continue reading.
