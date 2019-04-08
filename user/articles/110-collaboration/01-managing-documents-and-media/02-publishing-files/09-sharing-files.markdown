# Sharing Files [](id=sharing-files)

@product@'s 
[role-based permissions system](/discover/portal/-/knowledge_base/7-2/roles-and-permissions) 
lets administrators choose which actions users can take on assets, including 
[files](/discover/portal/-/knowledge_base/7-2/adding-files-to-a-document-library#granting-file-permissions-and-roles). 
Administrators can let users collaborate on files by assigning the appropriate 
file permissions to a role, and then assigning users to that role. Similarly, 
non-administrative users can grant permissions to roles for files they own. 

This role-based permissions system sometimes falls short. For example, if a role 
appropriate for file collaboration doesn't exist, an administrator must create 
it and manage its users and permissions. Non-administrative users can't create 
or manage roles. Also, if a user wants to share a file with one other user, it's 
not practical for an administrator to create and manage a role for only two 
users. @product@'s sharing feature solves these problems by letting users share 
files directly with each other, without involving an administrator. This saves 
time and effort for everyone. After all, sharing is caring. 

| **Note:** Administrators can disable sharing. For instructions on this, see 
| [Configuring Sharing](/discover/portal/-/knowledge_base/7-2/configuring-sharing). 

When you share a file, you grant some of your own permissions for that file to 
the receiving user. There are, however, some important restrictions on this: 

-   You can only grant View, Comment, or Update permissions. For example, you 
    can't grant Delete or Override Checkout permissions even if you have those 
    permissions on the file. 
-   You can only grant permissions you have on the file. For example, you can't 
    grant Update permission if you only have View and Comment permissions on the 
    file. 
-   You must grant at least View permission. 

Also note that the receiving user must be part of the same instance, but doesn't 
have to be a member of the same site. 

## Sharing Files in Documents and Media [](id=sharing-files-in-documents-and-media)

To share a file, you must be the owner of that file or an administrator. You 
must share files via the Documents and Media app in Site Administration, or the 
Documents and Media widget on a page. 

Follow these steps to share a file: 

1.  Navigate to the file you want to share in the Documents and Media app in 
    Site Administration, or the Documents and Media widget on a page. 

    To navigate to the Documents and Media app in Site Administration, open the 
    *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)), 
    click your site's name, and go to *Content & Data* &rarr; 
    *Documents and Media*. 

    To share a file via the Documents and Media widget on a page, actions must 
    be enabled for the widget. Follow these steps to enable actions: 

    -   Select *Configuration* from the widget's *Options* menu 
        (![Options](../../../../images/icon-app-options.png)). 
    -   In the *Setup* tab's *Display Settings*, select *Show Actions*. 
    -   Click *Save* and close the Configuration window. 

2.  Click the file's *Actions* button 
    (![Actions](../../../../images/icon-actions.png)) 
    and select *Share*. This opens the Share dialog. 

    ![Figure 1: To share a file, you must fill out the Share dialog as these steps describe.](../../../../images/sharing-file.png)

3.  Enter the name or email address of the user you want to share the file with. 
    To share the file with multiple users, enter each user's email address in a 
    comma delimited list. 

4.  To let receiving users also share the file, select 
    *Allow the document to be shared with other users*. Unselect this option to 
    prevent receiving users from sharing the file. Note, however, that 
    administrators can share the file regardless of your selection here. 

5.  Select the file permissions to grant to receiving users. Because you can 
    only grant your own permissions for the file, some of these options may be 
    unavailable: 

    -   **Update:** View, comment, and update.
    -   **Comments:** View and comment.
    -   **View:** View only.

    If you enabled further sharing in the previous step, note that receiving 
    users can only share the file with the permissions you grant here. 

6.  Click *Share*. 

## Working with Shared Files [](id=working-with-shared-files)

You can access files shared with you in three places: 

1.  **The Documents and Media Library:** Files shared with you are visible in 
    their existing Documents and Media locations. For example, if someone shares 
    a file with you that resides in the Documents and Media Library's Home 
    folder, then you can access the file in that folder. 

2.  **The Notifications app:** When a file is shared with you, you get a 
    notification in the Notifications app. Clicking the notification takes you 
    to the file in Documents and Media. For information on notifications, see 
    [Managing Notifications and Requests](/discover/portal/-/knowledge_base/7-2/managing-notifications-and-requests). 

    ![Figure 2: The Notifications app contains the notifications that are sent when a user shares a file with you.](../../../../images/sharing-notifications.png)

3.  **The Shared Assets app:** This app lists all the files shared with you, and 
    the files you shared. Access this app by navigating to *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)) 
    &rarr; *My Account* &rarr; *Shared Assets*. Each file has an Actions button 
    (![Actions](../../../../images/icon-actions.png)) 
    that you can use to perform any permitted actions on the file (e.g., view, 
    comment, update). 

    ![Figure 3: The Shared Assets app lists the files shared with you, and the files you shared.](../../../../images/sharing-app.png)

## Managing Shared Files [](id=managing-shared-files)

After sharing a file, you can unshare it or modify its permissions on a per-user 
basis. This can only be done by Administrators, the file's owner, or any user 
with Update permission and permission to share the file. You can take these 
actions from the file's Info panel in Documents and Media. Follow these steps to 
do so: 

1.  Click the file in Documents and Media, then click the *Info* button 
    (![Info](../../../../images/icon-information-dm.png)) 
    at the top-right. The file's Info panel then slides out from the right. 

2.  Click the *Manage Collaborators* link. This opens a dialog with a list of 
    the users you shared the file with and their file permissions. 

    ![Figure 4: Click *Manage Collaborators* to open up the list of users you shared the file with.](../../../../images/sharing-info.png)

3.  Make any changes you want to the list of collaborators. To unshare the file 
    with a user, click the `x` icon next to that user. You can also change the 
    file permissions via the selector menu for each user. 

4.  Click *Save* and close the dialog. 

![Figure 5: The Collaborators dialog lets you unshare a file or change the file permissions for each user.](../../../../images/sharing-collaborators.png)
