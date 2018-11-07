# Managing LCS Users in Your Project [](id=managing-lcs-users-in-your-project)

The Users section of LCS is where you manage the LCS users that are part of your 
project. It's here that you can grant or revoke LCS Roles. To manage users, 
first click the *Users* tab just below the Dashboard tab on the upper-left of 
your screen. 

+$$$

**Note:** You can't add users to your project via the LCS UI or the LCS client 
app. To add users to your project, you must contact Liferay support. 

$$$

![Figure 1: The Users tab lets you manage the LCS users in your project.](../../../images-dxp/lcs-users.png)

The *Users* tab displays a list of the users in your project. This list includes 
each user's name, email, image, LCS Roles, and a *Manage Roles* button. Each LCS 
user must have an assigned Role. The following Roles are available: 

**LCS Administrator:** All LCS functionality is available to administrators.
This is the only Role that can manage other users' Roles. 

**LCS Environment Manager:** All LCS functionality is available in the scope 
of an environment, with the exception of managing other users. 

**LCS Environment Viewer:** Has read-only access in the scope of an 
environment.

You should note that each of these LCS Roles assume users already have the LCS
User Role in their Liferay.com accounts. The LCS User Role is granted
automatically the first time a user logs into LCS. The actions that can be
performed by each of the LCS Roles are detailed in the below permissions matrix. 

**LCS Permissions Matrix**

Action | &nbsp;LCS Administrator | &nbsp;LCS Environment Manager | &nbsp;LCS Environment Viewer |
------ | ----------------------- | ----------------------------- | ---------------------------- |
Access LCS | true | true | true |
Access Any Environment | true | false | false |
Access a Particular Environment | true | true | true |
Manage Users | true | false | false |
Create and Delete Environments | true | false | false |
Edit Any Environment | true | false | false |
Edit a Particular Environment | true | true | false |
Server Registration in Any Environment | true | false | false |
Server Registration in a Particular Environment | true | true | false |
Install Fix Packs in Any Environment | true | false | false |
Install Fix Packs in a Particular Environment | true | true | false |

Now that you know what Roles are available in an LCS project and what they do, 
you're ready to learn how to manage them.

## Managing LCS Roles [](id=managing-lcs-roles)

Follow these steps to manage a user's LCS Roles:

1.  Click the user's *Manage Roles* button. 

2.  To revoke a Role, click *Revoke Role* for that Role. 

3.  To assign a Role, choose the Role (and environment, if applicable) and click 
    *Assign*. 

+$$$

**Note:** A user can't have an environment Role (e.g., LCS Environment Manager, 
LCS Environment Viewer) and the LCS Administrator Role at the same time. 

$$$

![Figure 2: You can assign or revoke a user's LCS Roles.](../../../images-dxp/lcs-user-roles.png)
