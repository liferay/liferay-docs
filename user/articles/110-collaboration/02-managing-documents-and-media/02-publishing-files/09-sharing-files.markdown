---
header-id: sharing-files
---

# Sharing Files

[TOC levels=1-4]

@product@'s 
[role-based permissions system](/docs/7-2/user/-/knowledge_base/u/roles-and-permissions) 
defines which actions users can take on assets, including 
[files](/docs/7-2/user/-/knowledge_base/u/adding-files-to-a-document-library#granting-file-permissions-and-roles). 
Administrators can let users collaborate on files by assigning the appropriate 
file permissions to a Role, and then assigning users to that Role. Similarly, 
non-administrative users can grant permissions to Roles for files they own. 

This Role-based permissions system sometimes falls short. For example, if a Role 
appropriate for file collaboration doesn't exist, an administrator must create 
it and manage its users and permissions. Non-administrative users can't create 
or manage Roles. Also, if a user wants to share a file with one other user, it's 
not practical for an administrator to create and manage a Role for only two 
users. 

@product@'s sharing feature solves these problems by letting users share 
files directly with each other, without involving an administrator. This saves 
time and effort for everyone. After all, sharing is caring. 

| **Note:** Administrators can disable sharing. For instructions on this, see 
| [Configuring Sharing](/docs/7-2/user/-/knowledge_base/u/configuring-sharing). 

When you share, you grant some of your own permissions for that file to the
receiving user. However, there are some important caveats: 

-   You can only grant View, Comment, or Update permissions. For example, you 
    can't grant Delete or Override Checkout permissions even if you have those 
    permissions on the file. 
-   You can only grant permissions you have on the file. For example, you can't 
    grant Update permission if you only have View and Comment permissions on the 
    file. 
-   You must grant at least View permission. 
-   Traditional Role-based permissions always take precedence over sharing 
    permissions. So although sharing can extend permissions, it can't remove 
    those granted via Roles in the portal. 
-   By default, the Guest Role has Add Discussion permission. This overlaps with
    the Comment permission in sharing. Therefore, all users can comment on
    a file regardless of whether the Comment permission was granted via sharing.
    Administrators can change this by removing the Add Discussion permission
    from the Guest Role. 

Also note that the receiving user must be part of the same instance, but doesn't 
have to be a member of the same Site. 

## Sharing Files in Documents and Media

To share a file, you must own that file or be an administrator. You must share
files via the Documents and Media app in Site Administration or the Documents
and Media widget on a page. 

Follow these steps to share a file: 

1.  Using the Documents and Media widget on a page or in Site Administration,
    navigate to the file you want to share.

    To navigate to the Documents and Media app in Site Administration, open the 
    *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)), 
    click your Site's name, and go to *Content & Data* &rarr; 
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

    Alternatively, click the file in Documents and Media and then click the 
    *Share* button at the top-right. This opens the same Share dialog. 

    ![Figure 1: To share a file, you must fill out the Share dialog as these steps describe.](../../../../images/sharing-file.png)

3.  Enter the name or email address of the user you want to share the file with. 
    To share the file with multiple users, enter each user's email address in a 
    comma delimited list. 

4.  To let receiving users also share the file, select 
    *Allow the document to be shared with other users*. Note, however, that 
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

## Working with Shared Files

You can access files shared with you in three places: 

1.  **The Documents and Media Library:** Files shared with you are visible in 
    their existing Documents and Media locations. For example, if someone shares 
    a file with you that resides in the Documents and Media Library's Home 
    folder, then you can access the file in that folder. 

2.  **The Notifications app:** When a file is shared with you, you get a 
    notification in the Notifications app. Clicking the notification takes you 
    to the file in Documents and Media. For information on notifications, see 
    [Managing Notifications and Requests](/docs/7-2/user/-/knowledge_base/u/managing-notifications-and-requests). 

    ![Figure 2: The Notifications app contains the notifications that are sent when a user shares a file with you.](../../../../images/sharing-notifications.png)

3.  **The Shared Content app:** This app lists all the content shared with you, 
    and the content you shared. You can access this app from your user menu. 
    Each file has an Actions button 
    (![Actions](../../../../images/icon-actions.png)) 
    for performing permitted actions on the file (e.g., view, comment, update). 

    ![Figure 3: The Shared Content app lists the files shared with you, and the files you shared.](../../../../images/shared-content-app.png)

## Managing Shared Files

After sharing a file, you can unshare it or modify its permissions on a per-user 
basis. This can only be done by Administrators, the file's owner, or any user 
with Update permission and permission to share the file. You can take these 
actions from the file's Info panel in Documents and Media. Follow these steps: 

1.  Click the file in Documents and Media, then click the *Info* button 
    (![Info](../../../../images/icon-information-dm.png)) 
    at the top-right. The file's Info panel slides out from the right. 

2.  Click the *Manage Collaborators* link. This shows a list of the users you
    shared the file with and their file permissions. 

    ![Figure 4: Click *Manage Collaborators* to open up the list of users you shared the file with.](../../../../images/sharing-info.png)

3.  Make any changes you want to the list of collaborators. To unshare the file 
    with a user, click the `x` icon next to that user. You can also change the 
    file permissions via the selector menu for each user. 

4.  Click *Save* and close the dialog. 

![Figure 5: The Collaborators dialog lets you unshare a file or change the file permissions for each user.](../../../../images/sharing-collaborators.png)
