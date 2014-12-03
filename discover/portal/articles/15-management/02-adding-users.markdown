# Adding Users [](id=adding-users)

Let's add a user account for yourself and configure this account so it has the
same administrative access as the default administrator account. Go up to the
Dockbar, mouse over *Go to* and click *Control Panel*. Then open the *Users and
Organizations* page under the *Portal* category. Click the *Add* button and
select *User*. Fill out the Add User form using your name and email address.
When you are finished, click *Save*.

![Figure 15.2: The Add User Screen](../../images/01-add-user-screen.png)

After you submit the form, the page reloads with a message saying the save was
successful. An expanded form appears that allows you to fill out a lot more
information about the user. You don't have to fill anything else out right now.
Just note that when the user ID was created, a password was automatically
generated and, if Liferay was correctly installed (see chapter 14), an email
message with the password in it was sent to the user. This, of course, requires
that Liferay can properly communicate with your SMTP mail server.

![Figure 15.3: Liferay's User Account Editor](../../images/01-user-account-editor.png)

If you haven't yet set up your mail server, you'll need to use this page to
change the default password for the user ID to something you can remember. You
can do this by clicking on the *Password* link in the box on the right, entering
the new password in the two fields and clicking *Save*. Next, you should give
your user account the same administrative rights as the default administrator's
account. This allows you to perform administrative tasks with your own ID
instead of having to use the default ID. It also helps to make your portal more
secure by deleting or disabling the default ID.

Click the *Roles* link. The control panel's Roles page shows the roles to which
your ID is currently assigned. You should have one role: Power User. By default,
all users are assigned the Power User role. You can give this role certain
permissions if you wish or disable it altogether. You can also define the
default roles a new user receives. We'll see how to do this later.

To make yourself an Administrator, click the *Select* link. A dialog box pops up
with a list of all the roles in the system. Select the Administrator role from
the list. The dialog box disappears and the role is added to the list of roles
associated with your account. Next, click the *Save* button, which is at the
bottom of the blue bar of links on the right. You are now an administrator of
the portal. Log out of the portal and then log back in with your own user ID.

We'll next look at some aspects of user management. 
