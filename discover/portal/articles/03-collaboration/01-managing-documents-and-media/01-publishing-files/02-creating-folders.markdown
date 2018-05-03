## Creating Folders

Let's continue with our example and create folders to organize the Lunar Resort
guest photos and videos that we'll upload. From the document library's Add menu,
select *Folder*. The new folder form appears. Since the folder is for storing
Lunar Resort guest media, name the folder *Resort Guest Media*. You can
optionally describe the folder. Initially, anyone can view the folder, but we'll
change that after we create it. Expand the *Permissions* section. By default,
site members are able to add files, subfolders, and shortcuts and subscribe to
changes to the folder's files. Click the *More Options* link and deselect all of
these checkboxes, as we only want resort media managers to modify the folder's
files. Click *Save* to finish creating the folder. 

Note that, document type restrictions and workflow definitions can be associated
with each folder. Child folders inherit their parent folder's document type
restrictions, by default. If workflow is enabled for the Document Library, you
can specify workflow definitions per folder and document type. You can specify a
folder's document type restrictions and workflow definitions from the folder's
edit screen, after the folder's been created.

![Figure 3: From a folder's Edit screen, you can restrict the document types it supports and select the folder's workflow.](../../../../images/dm-doc-type-restrictions-and-workflow.png)

Document types are a powerful way to enforce rules for documents. For our
example's folder, however, we'll keep the default supported document types and
workflow settings.

You've created a folder named *Resort Guest Media*. You can now specify its
permissions. 

## Setting Folder Permissions

Since we only want the *Resort Guest Media Manager* role to manage files in it, 
we must fine tune the folder's permissions. Click on the folder's *Actions* icon (![Actions](../../../../images/icon-actions.png)) and select the *Permissions* 
action. The Permissions window appears.

The folder permissions enable a role to perform the following actions:

**Access**: Access the folder's contents from a Windows desktop.

**Update**: Edit the folder's attributes and move the folder under a new parent
folder.

**Add Subfolder**: Create folders within the folder.

**Add Shortcut**: Create a shortcut (link) to any Documents and Media file the
role is authorized to view.

**Subscribe**: Receive email notification when files are added to or modified in
the folder.

**Add Document**: Add a new file to the folder.

**Permissions**: View and modify the folder's permissions.

**Delete**: Move the folder to the Recycle Bin.

**View**: View the folder.

Grant all the permissions to the *Resort Guest Media Manager* role, revoke all
permissions from all the other roles, and click *Save*. Users assigned to the
*Resort Guest Media Manager* role are now the only non-admin users who can
upload and manage media files in the *Resort Guest Media* folder.

You've created the *Resort Guest Media* folder and set appropriate permissions
for it. 

While you're signed in as the admin user, click on Documents and Media's
*Options* icon (![Options](../../../../images/icon-options.png)) and select
*Configuration*. From here you can specify the email sender and email templates
for email sent to Document Library folder subscribers. 

Now, sign in as a *Resort Guest Media Manager* role user. Since Snappy, the
Lunar Resort's photographer, belongs to this role, you can sign in as her. Then
go to *Documents and Media* in Site Administration, navigate to the *Resort
Guest Media* folder, and click the folder's name. The folder provides a
workspace for members of the *Resort Guest Media Manager* role to manage files
and subfolders.

Snappy has plenty of pictures of guests Mr. and Mrs. Spacey on her local file
system. To separate the Spacey's files from those of other guests, we'll create
a subfolder named *Spacey Party*. To create it, click the Add icon, and select
the *Subfolder* action. In the *New Folder* form that appears, name the folder
*Spacey Party* and describe the folder's purpose (e.g., "The Spacey's photos
from their visit to the Lunar Resort"). Click the *More Options* link and
deselect all the permissions within it, as we only want media managers to be
able to edit the folder. *Save* the new subfolder. You return to the folder
*Resort Guest Media*, which now lists your new subfolder *Spacey Party*.
Navigate into it. The Document and Media library's navigation breadcrumbs show
your folder's path: *Home &rarr; Resort Guest Media &rarr;Spacey Party*.

In your Document Library's *Resort Guest Media* folder, you've created a
subfolder named *Spacey Party*. Next, let's add the Spacey's photos to the
subfolder.
