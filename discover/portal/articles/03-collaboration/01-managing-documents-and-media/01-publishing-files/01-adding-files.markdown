# Adding Files to a Document Library [](id=adding-files)

There are several reasons why you might want to upload files to your site. You
might want to share photos and video files or embed them in web content. Or you
might simply want to back them up from your device to your site. You may have
important documents that you want to share with other users. And you might want
to collaborate on documents with other users. All of these examples include
first uploading a files to your site.

To demonstrate adding files to a Document Library, we'll implement a use case
for the Lunar Resort. Resort photographers take lots of photos and video footage
of resort guests having the time of their lives. As a courtesy to the guests the
staff stores each party's photos and videos on the resort site and shares them
with the parties. At first, you might think "How does a resort's sharing of
pictures and videos relate to me and my site?" Well, even if your company isn't
in the tourism business, it probably has similar requirements for managing and
publishing files. One common requirement is the ability to enable specific
groups of users to store, organize, and share files. And no matter what kinds of
files you work with, the fundamentals of granting file operation permissions is
the same. Let's explore how to do this for the Lunar Resort.

## Granting File Permissions and Roles [](id=granting-file-permissions-and-roles)

The Lunar Resort needs to manage carefully who can add, view, and update groups
of files. The resort stores in their Documents and Media libraries all kinds of
files for various purposes. The libraries not only contain guest media but also
departmental documents and media. Most of the documents are meant to be
disseminated within the departments only. Using @product@, we can
implement the resort's use cases. You see, @product@'s [Roles and
Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions) let
you precisely specify access to documents and media files. In addition, the
Document Library's folder permissions help you organize media. Let's get started
on the right foot with the Lunar Resorts guest media files by leveraging roles,
permissions, and folders in Documents and Media.

Since the Lunar Resort's *Souvenir and Memorabilia* staff members must be able
to upload and organize guest media, let's enable a role for these purposes. Sign
in as the @product@ admin and open the *Menu* (![Product 
Menu](../../../../images/icon-menu.png)). Then navigate to *Control Panel &rarr; 
Users &rarr; Roles*. If the staff group is a part of an Organization, and that 
organization has a site, create an Organization Role. Otherwise, create it as a 
Site Role. Name the role *Resort Guest Media Manager*. Once you've created it, 
click the role's Actions 
icon (![Actions](../../../../images/icon-actions.png)) and select *Define 
Permissions*. A screen appears for you to define the role's permissions.

![Figure 1: It's often helpful to define a role for specific users to access Documents and Media from Site Administration.](../../../../images/dm-define-role-permissions.png)

In the role's permission definition screen, navigate to *Site Administration
&rarr; Content &rarr; Documents and Media*. In the *General Permissions*
section, select *Access in Site Administration* and click *Save*. This role will
manage your special set of media files. Once you've created the role, assign it
to the users who manage the media. Snappy Fingers (username: *snappy*) is the
Lunar Resort's trigger-happy photographer. We assigned her to the *Resort Guest
Media Manager* role.

+$$$

**Note:** To learn how to create a role and define a role's permissions, refer
to
[Roles and Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions).

$$$

You've created your specialized role and assigned users to it. In the Documents
and Media library, you need a place for that role to manage Lunar Resort guest
media. A Documents and Media folder fits the bill. In the folder we'll create,
the role will add, organize, and share the guest media files. 

## Using the Add Menu [](id=using-the-add-menu)

Open the Menu, click on your site's name, and navigate to *Content &rarr;
Documents and Media* for your site. The Documents and Media screen appears and
displays the Documents and Media library's *Home* (its root folder). As you add
files and folders to the document library, they're listed here.

![Figure 2: The Documents and Media's *Home* folder starts empty. But the Add menu lets you upload and add all kinds of documents to the library.](../../../../images/dm-admin-add-menu.png)

Click the *Add* icon (![Add](../../../../images/icon-add.png)) to show what you 
can add to the Document Library. You can add documents, folders, and shortcuts 
the same as you do on your local file system. You can even configure access to 
an entirely different repository. Here are the Add menu's options:

**Folder**: lets you create a new location in the app's file system.

**Shortcut**: allows you to create a shortcut to any document that you can view.
You can set permissions on the shortcut to specify who can access the original
document through the shortcut.

**Repository**: allows you to add access to an external repository. Refer to
[Repostory Types](/discover/portal/-/knowledge_base/7-0/liferay-repository-types)
to learn how to add access to them. 

**Multiple Documents**: allows you to upload several documents at once.

**Basic Document**: allows you upload a single file that you would like the
default document type, "Basic Document," to apply to. By default, basic
documents are not described by any metadata sets.

**Google Docs**: available through an additional Liferay Plugin for Google 
Drive&trade; from the [Marketplace](https://web.liferay.com/marketplace). This 
option lets you create a file entry that links to a Google document. The section
[Accessing Google Docs&trade;](/discover/portal/-/knowledge_base/7-0/accessing-google-docs)
explains how to use this feature.

The remaining items in the Add menu are default [document 
types](/participate/liferaypedia/-/wiki/Main/document+types)
that are each described by a unique [metadata 
set](/participate/liferaypedia/-/wiki/Main/Metadata+Set). When you add a 
document belonging to a specific document type, a form appears for picking the 
file to upload and entering the data defined by the document type's metadata 
set. The *Contract* type appears below as an example. 

**Contract**: lets you upload a file that you would like the "Contract" document
type to apply to. This document type is intended to be used to describe legal
contracts. By default, contracts are described by effective date, expiration
date, contract type, status, legal reviewer, signing authority and deal name
fields. 

Any custom documents types that have been defined also appear in the Add menu.
If a document type has been created that matches the document you would like to
upload, you can select that document type from the Add menu. The Document
Library applies the metadata fields associated with the document type to your
document. On selecting a document type, you're prompted to fill in the fields
associated with it. 

## Creating Folders [](id=creating-folders)

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

## Setting Folder Permissions [](id=setting-folder-permissions)

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

## Adding Multiple Documents [](id=adding-multiple-documents)

There are a few different ways you can add image files, or any file for that
matter. You can add them one at a time or add multiple files simultaneously.
Let's add all the images at once. Click the Add icon and select *Multiple
Documents*. The *Add Multiple Documents* screen appears and displays an area for
dropping files in. You can drag and drop files into the area, or you can browse
for and select multiple files by clicking the *Select Files* button. Use the
method that's easiest for you. As you drop in files or select files, the Add
Multiple Documents screen lists them. On the side of the screen, the *All
Selected Files* section lists several options for the files. You can fill in a
common *Description* for the files. You can also specify a *Document Type* to
apply. Since we're uploading image files, we'll use the *Basic Document* type,
which is the default type. There are also options for
[categorizing and tagging](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories)
the selected files, and assigning them default permissions. We'll tighten up
permissions by clicking the *More Options* link and deselecting all the
permissions. When you're ready to upload the files, click *Publish*. The Add
Multiple Documents screen stays active, ready for you to add more files. When
you're done adding files, click the Back icon
(![Back](../../../../images/icon-back.png)) at the top of the screen. You're 
taken back to the folder you're adding files to. 

![Figure 4: Documents and Media conveniently lets you add multiple files at once.](../../../../images/dm-add-multiple-files.png)

## Using the Documents and Media Management Bar [](id=using-the-documents-and-media-management-bar)

Documents and Media lists your current folder's subfolders and file entries. A
*file entry* is the Document Library's representation of a file. It wraps the
file to better leverage it within @product@ and to associate additional
information and metadata with the file. File entries are displayed using icons,
by default. You can select either icon
(![Icon](../../../../images/icon-icon-style.png)), descriptive
(![Descriptive](../../../../images/icon-descriptive-style.png)), or list
(![List](../../../../images/icon-list-style.png)) display style. The icon 
display style shows a file as a cover image. If the file is an image type file, 
the image is used as the file entry's cover image. If the file isn't an image,
Documents and Media displays a generic image for that file type. Also displayed
with each file icon is the file's suffix (e.g., *JPG* for a file ending in
`.jpg`), last modification timestamp, name, and status (e.g., *Approved*,
*Draft*, etc.). And each file has an Actions menu. The actions are also
available from within the file entry's view, which we'll explore soon. The
descriptive and list display styles provide the same functionality, but display
the file entries in rows.

![Figure 5: Documents and Media's lists files using several display styles: icon (as shown here), descriptive, and list. And breadcrumbs show the current folder's path in the library.](../../../../images/dm-images-in-admin.png)

To see the current folder's details click the *Information* icon
(![**i**](../../../../images/icon-information.png)). An area with the folder's 
name appears. It shows the number of items in the folder. It also shows a 
*Subscribe* icon 
(![Subscribe](../../../../images/icon-star.png)) you can select to get
notifications about files added to or modified in the folder. Lastly, the
Actions icon lists actions you can perform on the current folder. Above the
Information icon and Display Styles is a *Search* that enables you to find files
by keywords.

On the other side of the screen also in the top area is a filtering selector.
It's default option is *All*. Click it to see the other filter options. The
*All* option (default) shows all of the current folder's immediate subfolders
and files. The *Recent* filter shows the most recently modified files, *Mine*
lists all the current user's files (no matter their folder), and *Document
Types* filters on files of the selected document type. If a Document Library
contains more documents than it can display at once, you can use the navigation
tool at the bottom of the app's window to either switch your view to another
page or configure the page to display more documents per page.

Next to the filtering selector, the *Order by* selector lets you select criteria
for ordering the files and folders. You can order them by creation date, title,
number of downloads, modification date (default), or size. The up and down
arrows next to the *Order by* selector, let you arrange them in ascending or
descending order.

From the list view, Documents and Media lets you act on one or more files at
once. When you select a checkbox for one or more files, the following action
icons appear: ![Action Icons](../../../../images/dm-action-icons.png). 
Describing them from left to right, the X is for canceling file checkout, the 
secured lock is for file check-out, the unsecured lock is for file check-in, the 
arrows are for moving the files, and the trash can is for moving files to the 
Recycle Bin. You can also move selected files to a subfolder via drag and drop. 
File check-out and check-in is explained in
[Collaborating on Files](/discover/portal/-/knowledge_base/7-0/collaborating-on-files).

![Figure 6: A file's entry view lets you act on the file, preview it, and inspect its details. If you've installed an appropriate preview plugin for a file, its preview image displays in the preview area. Liferay can, by default, preview many image types.](../../../../images/dm-file-entry-details.png)

## Viewing File Previews [](id=viewing-file-previews)

You've added several files to the *Spacey Party* folder. In the Document 
Library, each file has a file entry view. To open its file entry view, click the 
file's name. The screen's central viewing area displays a preview image of the 
file. If the file is an image file, its image is displayed. If a preview plugin 
for the file type is installed, the plugin displays an image (e.g., the opening 
scene of a video file or a presentation's first slide) for the file. If there 
are no preview plugins for the file, @product@ displays a generic image based on 
the file's type. Let's take a moment and consider file preview plugins and some 
of the powerful features they offer. 

Whenever possible, @product@ generates previews of documents added to the 
Document Library. Out of the box, @product@ only ships with Java-based APIs to
generate previews for documents. The only tool available that is 100% Java and
has a compatible license to be distributed with @product@ is PDFBox. If you 
upload a PDF file to Documents and Media, @product@ generates a preview for the 
PDF in a separate thread. This process may last only a few seconds for a small 
file. The larger the file, the longer it takes.

While a default implementation of image generation for document previews and
thumbnails is provided via PDFBox, you must install and configure some
additional tools to harness the full power of @product@'s Documents and Media
library. These tools include [*OpenOffice*](http://www.openoffice.org) or
[*LibreOffice*](http://www.libreoffice.org),
[*ImageMagick*](http://www.imagemagick.org), which requires
[*Ghostscript*](http://www.ghostscript.com), and
[*Xuggler*](http://www.xuggle.com/xuggler). With these tools installed and
configured, Documents and Media content is displayed using a customized viewer
depending on the type of content. Configuring @product@ to use OpenOffice or
LibreOffice in server mode allows you to generate thumbnails and previews for
supported file types (.pdf, .docx, .odt, .ppt, .odp, etc.), lets you view
documents in your browser, and lets you convert documents. ImageMagick allows
for faster and higher-quality previews and conversions. Xuggler allows for audio
and video previews, lets you play audio and video files in your browser, and
extracts thumbnails from video files. You can configure the tools via portal
properties you can set in the Control Panel's Server Administration screen or in
a `portal-ext.properties` file. To learn how to use these tools, refer to
[Configuring @product@](/discover/portal/-/knowledge_base/7-0/configuring-liferay).

You can view a document with a customized viewer that allows you to navigate
through the different pages of the document and read its content. In addition,
you can view a multimedia document (audio or video) and play it online. If the
browser supports HTML5, it uses the browser's native player. Otherwise it falls
back to a Flash player.

![Figure 7: By leveraging services like Xuggler and ImageMagick, you can watch a video clip or even view a slideshow inside @product@'s Documents and Media app.](../../../../images/dm-file-entry-video-preview.png)

Document previews are powerful and help users browse media more successfully to
find what they're looking for.

Above the file viewing area are the file's icon and the file entry's name,
author, upload timestamp, and rating. You can rate the file too. The comments
area below the file viewing area lets you comment on the file, subscribe to
comments, and reply to comments.

A file's options are accessible from the *Options* icon
(![Options](../../../../images/icon-options.png)) at the top of the screen. Here
are the file options:

**Download**: Downloads the file to your device.

**Edit**: Lets you modify the file's name, description, document type,
categorization, and [related 
assets](/discover/portal/-/knowledge_base/7-0/defining-content-relationships).
You can even upload a new file to replace it. Note, modifying the file
increments its version.

**Edit With Image Editor**: Opens the Image Editor to modify the image. The 
Image Editor is explained in [Editing 
Images](/discover/portal/-/knowledge_base/7-0/editing-images).

**Move**: Relocate the file to a different parent folder.

**Checkout/Checkin**: Checkout prevents others from modifying the document while
you are working. Other users can still view the current version of the document
if they have permission. You can check in the document when you're done working.

**Permissions**: Lets you specify which actions each role can perform on the
file. You can granted a role permission to perform the following actions.

- **Update**: Edit, checkout, and checkin the file.

- **Override Checkout**: Checkout the file, revoking the file's current
checkout if it is checked out.

- **Permissions**: View and configure this file's permissions.

- **Delete**: Move the file to the Recycle Bin.

- **View**: View the file.

- **Update Discussion**: Edit another user's comment on the file.

- **Delete Discussion**: Delete any comments on the file.

- **Add Discussion**: Comment on the file. 

**Move to the Recycle Bin**: Remove the file from the Documents and Media
library to the Recycle Bin. 

Click the *Information* icon 
(![**i**](../../../../images/icon-information.png)) to view the file entry 
details. The top portion of this area lists the file's version number, status, 
modification timestamp, and name of the user who last modified it. Next are 
links to download the file and links to show its URL and WebDAV URL. You can 
specify the WebDAV URL as the file name when opening the document from a desktop 
environment.

The section *Automatically Extracted Metadata* lists any and all metadata that's
been extracted automatically from the file. When adding new documents or viewing
existing documents, a process is triggered automatically that extracts the
file's metadata. The library used by this process is TIKA and it's already
included in @product@ out of the box. Depending on your file's type and the
metadata written with the file, you can find out all kinds of details. In the
case of audio or video files, their duration is displayed.

To view the *Version History* click the drown-down arrow next to *Details* and 
select *Versions*. This section lists the different versions of the file and 
lets you view, download, remove, and revert to specific file versions. File 
version history actions are explained in the [Collaborating on 
Files](/discover/portal/-/knowledge_base/7-0/collaborating-on-files) guide.

Let's review what you've done so far. First, you created a role to manage a
specific set of files for your site. You assigned users to the role and created
a Documents and Media folder named *Resort Guest Media* for them to add and
organize files. Then as a member of the role, you added a subfolder named
*Spacey Party* and added files to it. And just now, you viewed individual file
entry information and actions. 

Next, you'll learn how to edit images in @product@. 
