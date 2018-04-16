# Adding, Editing, and Deleting Users

At the root of managing users is adding, editing, and deleting them. As long as
you're the Administrative user, you can do all these things and more. 

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
correctly installed and a 
[mail server was set up](/discover/portal/-/knowledge_base/7-1/server-administration#mail), 
an email message with the user's new password was sent to the user's email
address.  This, of course, requires that Liferay can properly communicate with
your SMTP mail server.

If you haven't set up a mail server, click the Password item from the General
menu to access the Password section of the Add User functionality. Manually set
a password for your new user. Enter the new password twice.

<!-- [Figure 3: Enter the password twice to manually set the password for a user. If the Password Policy you're using is configured to allow it, select whether to require the user to reset their password the first time they sign in to the portal.](../../images/usrmgmt-require-password-reset.png) -->

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

Most users can't perform any of the above actions. In fact, most users won't
have access to the Control Panel at all. You can perform all of the above
functions because you have administrative access.

## Deleting Users

Employees move on and user accounts become stale. You don't want to keep them
around forever, but you must be careful when deleting users. To guard against
accidental deletion of users, a two-step process must be followed to delete a
user: deactivate first, then delete.

1.  Find the user to delete in the Users tab of Control Panel &rarr; Users
    &rarr; Users and Organizations. If you have a lot of users, save time by
    searching for the user.

2.  Click the Actions menu for the user and select *Deactivate*. You're asked to
    confirm that you want to deactivate the user. Click *OK*. 

    You'll see a success message and the user disappears. They're not gone yet.

3.  By default the Users table displays only Active users. Click on *Active* in
    the top of the table and a dropdown menu appears. Click *Inactive*, and you
    can see the user you just deactivated. 

4.  Click the Actions menu again, and click *Delete* if you really mean to
    delete the user. Confirm that you want to delete the user, and they
    disappear again. This time, it's for real. They're gone from the User table,
    and from the database.

+$$$

**Deactivated Users:** Deactivating a user means the user can't log in to
@product@. They have no more permissions in the sites and pages of the portal
than a guest, although their account still exists in the system. 

They're reactivted by a portal administrator finding them in the Users table (be
sure you're filtering the table results by Deactivated users), clicking the
Actions menu, and selecting Activate. There's no confirmation window for
activation: they're automatically restyored to their former status once Activate
is clicked.

$$$

Now you understand the basic principles of user administration. There's some
really important additional topics in the next article that you should consider
mandatory information for all portal administrators, so do continue reading.
