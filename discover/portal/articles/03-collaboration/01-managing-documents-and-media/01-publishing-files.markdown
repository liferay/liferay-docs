# Publishing Files [](id=publishing-files)

As you create sites, you'll probably want to share files on them.
@product@'s Documents and Media library (Document Library) let's you upload
and publish all kinds of files on your sites. Pictures, videos, spreadsheets,
slide presentations and more can be stored in and shared from the Document
Library. Document Library instances can be scoped to a @product@ instance,
site, or page, so you can work with files where their relevant.

In this chapter, you'll learn how to add files, display them, and collaborate on
them. You can upload all kinds of files, including custom file types, to the
Document Library. Users can then browse and view files from the Media Gallery or
Documents and Media apps. You'll learn how to use both apps. And lastly, you'll
learn how to collaborate on files from within several environments, including
your browser, local desktop, and mobile device. We've pulled out all the stops
to simplify online file management. Let's get the ball rolling by adding files
to a Document Library.

## Adding Files [](id=adding-files)

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
in as the Liferay admin and open the *Menu* (![Product
Menu](../../../images/icon-menu.png)). Then navigate to *Control Panel &rarr; Users
&rarr; Roles*. If the staff group is a part of an Organization, and that
organization has a site, create an Organization Role. Otherwise, create it as a
Site Role. Name the role *Resort Guest Media Manager*. Once you've created it,
click the role's Actions icon
(![Actions](../../../images/icon-actions.png)) and select *Define
Permissions*. A screen appears for you to define the role's permissions.

![Figure 1: It's often helpful to define a role for specific users to access Documents and Media from Site Administration.](../../../images/dm-define-role-permissions.png)

In the role's permission definition screen, navigate to *Site Administration
&rarr; Content &rarr; Documents and Media*. In the *General Permissions*
section, select *Access in Site Administration* and click *Save*. This role will
manage your special set of media files. Once you've created the role, assign it
to the users who manage the media. Snappy Fingers (username: *snappy*) is the
Lunar Resort's trigger-happy photographer. We assigned her to the *Resort Guest
Media Manager* role.

+$$$

To learn how to create a role and define a role's permissions, refer to [Roles
and Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions).

$$$

You've created your specialized role and assigned users to it. In the Documents
and Media library, you need a place for that role to manage Lunar Resort guest
media. A Documents and Media folder fits the bill. In the folder we'll create,
the role will add, organize, and share the guest media files. 

Open the Menu, click on your site's name, and navigate to *Content &rarr;
Documents and Media* for your site. The Documents and Media screen appears and
displays the Documents and Media library's *Home* (its root folder). As you add
files and folders to the document library, they're listed here.

![Figure 2: The Documents and Media's *Home* folder starts empty. But the Add menu lets you upload and add all kinds of documents to the library.](../../../images/dm-admin-add-menu.png)

Click the *Add* icon (![Add](../../../images/icon-add.png)) to show what you can add
to the Document Library. You can add documents, folders, and shortcuts the same as you do
on your local file system. You can even configure access to an entirely different
repository. Here are the Add menu's options:

**Folder**: lets you create a new location in the app's file system.

**Shortcut**: allows you to create a shortcut to any document that you can view.
You can set permissions on the shortcut to specify who can access the original
document through the shortcut.

**Repository**: allows you to add access to an external repository. Refer to
[Repostory Types](/discover/portal/-/knowledge_base/7-0/liferay-repository-types) to
learn how to add access to them. 

**Multiple Documents**: allows you to upload several documents at once.

**Basic Document**: allows you upload a single file that you would like the
default document type, "Basic Document," to apply to. By default, basic
documents are not described by any metadata sets.

**Google Docs**: lets you create a file entry that links to a Google document.
The section [Accessing Google Docs](/discover/portal/-/knowledge_base/7-0/publishing-files#accessing-google-docs)
explains how to use this feature.

The remaining items in the Add menu are default [document types](/participate/liferaypedia/-/wiki/Main/document+types)
that are each described by a unique [metadata set](/participate/liferaypedia/-/wiki/Main/Metadata+Set).
When you add a document belonging to a specific document type, you're presented
with a form to not only specify the file to upload but also to fill out the
fields defined by the document type's metadata set. We describe the "Contract"
document type by way of example.

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

![Figure 3: From a folder's Edit screen, you can restrict the document types it supports and select the folder's workflow.](../../../images/dm-doc-type-restrictions-and-workflow.png)

Document types are a powerful way to enforce rules for documents. For our
example's folder, however, we'll keep the default supported document types and
workflow settings.

You've created a folder named *Resort Guest Media*. You can now specify its
permissions. Since we only want the *Resort Guest Media Manager* role to manage
files in it, we must fine tune the folder's permissions. Click on the folder's
*Actions* icon (![Actions](../../../images/icon-actions.png)) and select the
*Permissions* action. The Permissions window appears.

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
for it. While you're signed in as the admin user, click on Documents and Media's
*Options* icon (![Options](../../../images/icon-options.png)) and select
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
which is the default type. There are also options for [categorizing and
tagging](/discover/portal/-/knowledge_base/7-0/organizing-content-with-tags-and-categories)
the selected files, and assigning them default permissions. We'll tighten up
permissions by clicking the *More Options* link and deselecting all the
permissions. When you're ready to upload the files, click *Publish*. The Add
Multiple Documents screen stays active, ready for you to add more files. When
you're done adding files, click the Back icon
(![Back](../../../images/icon-back.png)) at the top of the screen. You're taken
back to the folder you're adding files to. 

![Figure 4: Documents and Media conveniently lets you add multiple files at once.](../../../images/dm-add-multiple-files.png)

Documents and Media lists your current folder's subfolders and file entries. A
*file entry* is the Document Library's representation of a file. It wraps the
file to better leverage it within Liferay and to associate additional
information and metadata with the file. File entries are displayed using icons,
by default. You can select either icon
(![Icon](../../../images/icon-icon-style.png)), descriptive
(![Descriptive](../../../images/icon-descriptive-style.png)), or list
(![List](../../../images/icon-list-style.png)) display style. The icon display
style shows a file as a cover image. If the file is an image type file, the
image is used as the file entry's cover image. If the file isn't an image,
Documents and Media displays a generic image for that file type. Also displayed
with each file icon is the file's suffix (e.g., *JPG* for a file ending in
`.jpg`), last modification timestamp, name, and status (e.g., *Approved*,
*Draft*, etc.). And each file has an Actions menu. The actions are also
available from within the file entry's view, which we'll explore soon. The
descriptive and list display styles provide the same functionality, but display
the file entries in rows.

![Figure 5: Documents and Media's lists files using several display styles: icon (as shown here), descriptive, and list. And breadcrumbs show the current folder's path in the library.](../../../images/dm-images-in-admin.png)

To see the current folder's details click the *Information* icon
(![**i**](../../../images/icon-information.png)). An area with the folder's name
appears. It shows the number of items in the folder. It also shows a *Subscribe*
icon ![Subscribe](../../../images/icon-star.png) you can select to get
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
icons appear: ![Action Icons](../../../images/dm-action-icons.png). Describing
them from left to right, the X is for canceling file checkout, the secured lock
is for file check-in, the unsecured lock is for file check-out, the arrows are
for moving the files, and the trash can is for moving files to the Recycle Bin.
You can also move selected files to a subfolder via drag and drop. File
check-out and check-in is explained later in [Collaborating on
Files](/discover/portal/-/knowledge_base/7-0/publishing-files#collaborating-on-files).

![Figure 6: A file's entry view lets you act on the file, preview it, and inspect its details. If you've installed an appropriate preview plugin for a file, its preview image displays in the preview area. Liferay can, by default, preview many image types.](../../../images/dm-file-entry-details.png)

You've added several files to the *Spacey Party* folder. In the Document Library,
each file has a file entry view. To open its file entry view, click the file's
name. The screen's central viewing area displays a preview image of the file. If
the file is an image file, its image is displayed. If a preview plugin for the
file type is installed, the plugin displays an image (e.g., the opening scene of
a video file or a presentation's first slide) for the file. If there are no
preview plugins for the file, Liferay displays a generic image based on the
file's type. Let's take a moment and consider file preview plugins and some of
the powerful features they offer. 

Whenever possible, Liferay generates previews of documents added to the Document
Library. Out of the box, @product@ only ships with Java-based APIs to
generate previews for documents. The only tool available that is 100% Java and
has a compatible license to be distributed with Liferay is PDFBox. If you upload
a PDF file to Documents and Media, Liferay generates a preview for the PDF in a
separate thread. This process may last only a few seconds for a small file. The
larger the file, the longer it takes.

While a default implementation of image generation for document previews and
thumbnails is provided via PDFBox, you must install and configure some
additional tools to harness the full power of Liferay's Documents and Media
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
[Configuring
Liferay](/discover/portal/-/knowledge_base/7-0/configuring-liferay).

You can view a document with a customized viewer that allows you to navigate
through the different pages of the document and read its content. In addition,
you can view a multimedia document (audio or video) and play it online. If the
browser supports HTML5, it uses the browser's native player. Otherwise it falls
back to a Flash player.

![Figure 7: By leveraging services like Xuggler and ImageMagick, you can watch a video clip or even view a slideshow inside Liferay's Documents and Media app.](../../../images/dm-file-entry-video-preview.png)

Document previews are powerful and help users browse media more successfully to
find what they're looking for.

Above the file viewing area are the file's icon and the file entry's name,
author, upload timestamp, and rating. You can rate the file too. The comments
area below the file viewing area lets you comment on the file, subscribe to
comments, and reply to comments.

A file's options are accessible from the *Options* icon
(![Options](../../../images/icon-options.png)) at the top of the screen. Here
are the file options:

**Download**: Downloads the file to your device.

**Edit**: Lets you modify the file's name, description, document type,
categorization, and [related assets](/discover/portal/-/knowledge_base/7-0/defining-content-relationships).
You can even upload a new file to replace it. Note, modifying the file
increments its version.

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

- **Move to the Recycle Bin**: Remove the file from the Documents and Media
library to the Recycle Bin. 

File entry details are listed beside the file entry's main viewing area. The top
portion of this area lists the file's version number, status, modification
timestamp, and name of the user who last modified it. Next are links to download
the file and links to show its URL and WebDAV URL. You can specify the WebDAV
URL as the file name when opening the document from a desktop environment.

The section *Automatically Extracted Metadata* lists any and all metadata that's
been extracted automatically from the file. When adding new documents or viewing
existing documents, a process is triggered automatically that extracts the
file's metadata. The library used by this process is TIKA and it's already
included in @product@ out of the box. Depending on your file's type and the
metadata written with the file, you can find out all kinds of details. In the
case of audio or video files, their duration is displayed.

The *Version History* section lists the different versions of the file and lets
you view, download, remove, and revert to specific file versions. File version
history actions are explained later in the section *Collaborating on Files*.

Let's review what you've done so far. First, you created a role to manage a
specific set of files for your site. You assigned users to the role and created
a Documents and Media folder named *Resort Guest Media* for them to add and
organize files. Then as a member of the role, you added a subfolder named *Spacey
Party* and added files to it. And just now, you viewed individual file entry
information and actions. Next, let's see how you can make adjustments to images 
in the document library.

## Editing Images [](id=editing-images)

You've uploaded an image to the document library. It's almost perfect, but it's 
missing a little something. It could use a little more focus to really highlight 
the message you're trying to communicate. Just a little editing and the image 
would draw your readers in. You could edit the image in your favorite image 
editing program and re-upload it, but that's a bit tedious for the minor changes 
this image requires.

What else can you do? 

Lucky for you, Liferay now comes with its own built-in image editor. 

A crop here and there, a little bit more contrast, and some adjustments in 
saturation, and boom! The image is ready to go.   

Since Liferay Portal CE 7.0 GA2 and Liferay DXP 7.0, users have been able to
edit images within Liferay. To access the image editor, go to the Documents and
Media repository and locate the image you want to edit. Click the Actions icon
(![Actions](../../../images/icon-actions.png)) and select *Edit With Image
Editor*.

![Figure 8: You can access the image editor through the Documents and Media repository.](../../../images/image-editor-docs-and-media.png)

The image editor can also be accessed through the item selector. Essentially, 
anywhere you have an image, you can edit images. For example, in blog entries, 
web content articles, and wikis, you can view a preview of the image.

Within the item selector preview window, you can access the image editor by 
clicking the pencil icon (![Pencil](../../../images/icon-edit-pencil.png)) in 
the bottom right corner of the preview window.

![Figure 9: You can access the image editor through the item selector preview window.](../../../images/image-editor-preview-window.png)

The image editor was designed with quick editing in mind, and offers a minimal, 
user-friendly UI:

![Figure 10: The image editor's UI is clear and to the point, offering only what you need.](../../../images/image-editor-tools.png)

The main toolbar consists of three buttons, each of which contain a subset of
options. The menu options are described below:

**Transform Tools** (![Transform](../../../images/icon-transform.png))

**Crop**: Lets you reframe the image, by removing the outer portions.

**Resize**: Lets you resize the image in pixels. If the lock is closed, the
aspect ratio remains locked, and changing either width or height will
automatically adjust the other dimension to maintain the aspect ratio. When the
lock is opened, the width and height can be changed individually, allowing the
aspect ratio to be altered. This is not recommended though, as the image can
become distorted.

**Rotate**: Lets you rotate the image to the left or right, in 90 degree
increments.

**Adjustment Tools** (![Adjustment](../../../images/icon-adjustment.png))

**Saturation**: Lets you adjust the amount of color saturation in the image. The
default value of 50 is the current saturation. The slider values range
from 0(completely desaturated) to 100(completely saturated).

**Contrast**: Lets you adjust the difference between highlights and shadows. A
value of 50 is the current level of contrast. The slider values range from
0(no contrast) to 100(full contrast).

**Brightness**: Lets you adjust the amount of light in the image. A value of
50 is the current brightness. The slider ranges from 0(completely black) to
100(completely white).

The third and final toolset is a preset of filters, which you can access by 
clicking the wand icon (![Wand](../../../images/icon-wand.png)):

![Figure 11: The image editor offers several filter presets for you to choose from.](../../../images/image-editor-filters.png)

Once you have made changes to the image in the editor, you can click the 
*Cancel* button to cancel the changes, or the *Apply* button to apply the 
changes to the image. Once you have applied changes to your image, the history 
bar appears:

![Figure 12: The history bar allows you to undo, redo, and reset changes.](../../../images/image-editor-history-bar.png)

The history bar allows you to undo, redo, and reset changes that have been made
to the image in the editor. Note that the reset button should be used with 
caution; once it has been pressed the image is reset to its original state, 
causing all changes made within the editor to be lost.

As mentioned earlier, you can access the image editor in the Documents and Media
repository, or through the item selector in blogs, web content articles, and
wikis. When the image is edited within the Documents and Media repository, and
saved, the version is incremented a minor version i.e. version 1.0 to version 
1.1. You can view the previous versions of the image through the 
*version history* table, under the information section for the image. When the 
image is edited within the item selector, and saved, a copy is created of the 
image and saved to the document library.

<!-- Add back in after developer tutorial has been written 

Note that these tools are modularized, and are each their own OSGi module. This 
modular design allows for developers to create their own custom tools and 
filters.

You can learn more about that in the [Expanding the image editor tutorial](URL)

-->

Next, let's explore how you can access Google documents from your document 
libraries.

## Accessing Google Docs [](id=accessing-google-docs)

The Document Library not only lets you upload files but also allows you to
access files stored in Google applications, such as Google Drive and Google
Photos. This integration with Google and other external repositories enables you
to access all of your documents from your Liferay sites. 

As a Liferay administrator, you must configure your Liferay instance's access to
Google's API Picker. @product@ calls on it to access files stored in
Google's apps. Your @product@ instance must use a unique API keys and
client ID to invoke the Google API Picker. The API key is used for accessing
public data and the client ID is for authenticating and authorizing user access
to non-public data. You must use a Google developer project to access the API
picker using an API key and client ID. The three basic steps are as follows:

- **Step 1: Enable the Google API Picker**

- **Step 2: Create Credentials**

- **Step 3: Configure @product@'s Google Apps Settings**

Let's start by enabling the Google API Picker in a Google developer project. 

### Step 1: Enable the Google API Picker [](id=step-1-enable-the-google-api-picker)

@product@ uses the *Google API Picker* to access files in Google. 

1. Create a Google account (or use an existing one). 

2. Open the Google Developer Console at <https://console.developers.google.com>.

3. Create a new project (or use an existing one). 

4. In the console's product and services menu, click on the API Manager. An
overview screen lists the Google APIs. 

5. In the list of Google APIs, find the *Google API Picker* and enable it.
Liferay uses the API to access Google documents. 

Next we'll create credentials to use with the Google API. 

### Step 2: Create Credentials [](id=step-2-create-credentials)

1. In the API Manager navigation, click on *Credentials*. 

2. Create a new OAuth client ID for a *Web application*. Specify the following
attributes: 
    -   **Name**: `Google Docs Hook`
    -   **Authorized JavaScript origins**: `[liferay-instance-URL]` (for
        example, `http://localhost:8080` for development machines) 
    -   **Authorized redirect URIs**: `[liferay-instance-URL]/oath2callback`

3. Create a new API key of type *Browser key*. Specify your @product@
instance's URL in the field listing sites from which your Google project should
accept requests.

Your new OAuth client ID and public API access key are listed in your Google
project's credentials. Keep the credentials screen open so you can easily
reference these values as you specify them in @product@. All that's left is
to enter the API key and client ID into your Liferay instance's Google Apps
configuration. 

### Step 3: Configure @product@'s Google Apps Settings [](id=step-3-configure-liferays-google-apps-settings)

To call Google's APIs, you must configure your @product@ instance with your
Google project's OAuth client ID and public API access key.

1. From @product@'s *Control Panel*, navigate to *Configuration &rarr;
Instance Settings*.

2. Click on the *Miscellaneous* tab and expand the *Google Apps*
section.

3. For *Google Apps API Key*, enter your Google project's API key.

4. For *Google Client ID*, enter your Google project's OAuth client ID. 

5. *Save* your changes. 

Your Liferay instance is ready to access Google documents from the Document
Library. 

### Linking to Google Docs [](id=linking-to-google-docs)

In your Document Library, you can create file entries that link to Google
documents, such as files stored in Google Drive or photos saved to Google. To
link to a Google doc in Documents and Media, click the Add icon
(![Add](../../../images/icon-add.png)) and select *Google Docs*. The *New Google
Docs* screen appears. 

The Google docs edit screen is similar to the edit screen for basic documents,
except for file selection. Clicking on the *Select File* button for Google
Docs brings up Google's file picker. It lets you select files from your Google
Drive or your photos. Select the Google file you want to link to and click
*Publish*. 

![Figure 13: You can select files from Google Drive or your photos.](../../../images/dm-google-select-a-file.png)

A new file entry appears for the Google document you linked to. You can view the
file entry as you would any file entry. Contents of the Google doc show in the
file entry preview pane. As with any file entry, Documents and Media's *Options*
icon (![Options](../../../images/icon-options.png)) gives you access to the
Download, Edit, Move, Permissions, Move to Recycle Bin, and
Checkin/Checkout/Cancel Checkout options. 

![Figure 14: The Google doc file entry view displays the file's information and provides a preview of it.](../../../images/dm-google-doc-file-entry.png)

You now know how to access Google docs from your document libraries. Next, let's
pick up with our Lunar Resort guest photos example as we consider how to display
files on site pages. 

## Displaying Files [](id=displaying-files)

Once you've uploaded image files and videos to your Document Library, you'll
probably want to display them in your site. Here are some ways of you can
display your media files:

- Show them in a Documents and Media app
- Display them in a Media Gallery
- Use the Asset Publisher
- Insert them in an asset, like a Web Content article or Blogs Entry

Continuing with the Lunar Resort example, the media team sits down with each
guest party to show them a page filled with photos and videos of their vacation
experience. The team likes to show guests a slideshow to get some oohs and ahh's
from them and determine their favorites. Then they offer to print and frame the
photos and plaster the photos on all kinds of fun items such as keychains,
coffee mugs, and tee-shirts. They even offer to burn media files onto DVDs.
Let's explore how the media team shows off the guest's pics and videos on a
site.

The media team creates a page for each guest party. Team members can customize
each page to focus on each party's media. Each party's media page will have a
Media Gallery app. The Media Gallery shows a large thumbnail of each media file,
lets the user download individual files, and comes with slideshow capabilities.
Let's create a media page for the Spacey guest party and add the Media Gallery.

[Add a page](/discover/portal/-/knowledge_base/7-0/creating-and-managing-pages)
named *Spacey Party*. Next, click the *Add* icon
(![Add](../../../images/icon-add-app.png)), then navigate to *Applications
&rarr; Content Management*, and select *Add* next to *Media Gallery*. The app
appears on the page and shows your Home folder's files by default. Since we want
to focus on the Spacey party's files, let's configure the app to show files from
the *Spacey Party* folder. Click the app's Options icon
(![Options](../../../images/icon-app-options.png)) and select *Configuration*.
The Configuration window appears and shows the *Setup* tab.

The Setup tab's Display Settings section provides checkboxes to enable showing each file's
actions, gallery navigation links (for paging through media files), and a search
field. File search is enabled by default. The Media Type selector lets you
filter on media file types; all supported types are filtered on by default. And
the Media Gallery uses a [Display Template](/https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Application+Display+Templates).
You can create your own Display Templates for the Media Gallery or select an
existing display template, like the *Carousel* display template. **Important**:
To access the Carousel display template in Media Gallery, your role must have
*View* access for that template. Since the Carousel template is in the Global
scope, a Global scope administrator must grant the role permission to view the
template.

The Folders Listing section lets you select a Document Library folder to serve
as the root folder from which to display files. The root folder is the
highest-level folder that's accessible from the Documents and Media app. For
example, suppose you created a folder called *My Documents* in Documents and
Media's default Home folder. If you set the My Documents folder to be your app's
new root folder, the app accesses the My Documents folder and no longer accesses
the Home folder.

![Figure 15: The Documents and Media app can be configured to use any folder as a root folder to display.](../../../images/dm-select-root-folder.png)

Lastly, the Ratings section of the Display Settings lets you enable users to
rate files and comment on them. 

Here are descriptions for the Media Gallery app's other configuration tabs:

**Communication**: lists public render parameters the application publishes to
other applications on the page. Other applications can read these and take
actions based on them. For each shared parameter, you can specify whether to
allow communication using the parameter and select which incoming parameter can
populate it. 

**Sharing**: lets you embed the application instance as a widget on on any
website, Facebook, or Netvibes, or as an OpenSocial Gadget.

**Scope**: lets you specify the Document Library instance the application uses:
the current site's instance (default), global instance, or the page's instance.
If the page doesn't already have an instance of the app, you can select scope
option *+ \[Page Name\] \(Create New\)* to create a page-scoped instance for the
application to display. 

While we're in the Configuration window there are a couple things we should do.
To enable the media team role member to act on the files, select the *Show
Actions* checkbox. Since we only want to show the Spacey's files, expand the
*Folders Listing* section, then click the *Select* button to browse and select
the *Spacey Party* folder. Then click *Save*.

The Media
Gallery now shows all of the Spacey party's files. This app enables the Lunar
Resort media team to act on individual files. If Mrs. Spacey likes particular
files, the staff member can download them locally and add notes in each file
entry's comments.

![Figure 16: The Media Gallery renders large thumbnail images of media files. It gives users quick access to download files that interest them.](../../../images/dm-media-gallery.png)

The Spacey Party's large image thumbnails display in the Media Gallery. When you
click on a file's thumbnail, a slideshow appears showing the selected image. The
window lists thumbnails of the folder's other images below the image that's
currently shown. And the slideshow continues until you either click pause or
view the folder's last image. The slideshow feature provides a nice way to show
off images. Click the X to close the slideshow window and return to the page. 

![Figure 17: The Media Gallery's slideshow provides a nice way to view images.](../../../images/dm-media-gallery-slideshow.png) 

The Media Gallery and Documents and Media apps are at your service to show
off your Document Library files. The Lunar Resort's Souvenir and Memorabilia
group enjoys patron reactions to the great pics and videos that Snappy takes of
them. And the salespeople are more than happy to plaster the pictures onto
clothing and accessories to make a handsome commission. 

Next, we'll step out of the Lunar Resort's Souvenir and Memorabilia group and
Document Library file collaboration.

## Collaborating on Files [](id=collaborating-on-files)

Not only does @product@'s Documents and Media enable you to publish files
easily, but it also provides a terrific environment for collaborating on
documents. There are many common collaboration scenarios. For example, you might
have a spreadsheet that you and your peers need to populate, or a proposal that
you and other stakeholders must produce. You might be asked to review and edit a
document. All these cases involve multiple users modifying documents and require
an environment that helps manage document changes. Liferay's Documents and Media
provides these capabilities. 

@product@'s document management apps support accessing apps on different
kinds of devices and environments. Not only can you access documents from your
site via your browser, but you can also access them from your native file
browser and use Liferay Sync to access them from your tablet or mobile device.
And Liferay Sync makes sure you're you can access the latest document versions. 

To demonstrate using these features, we'll join the Lunar Resort
Recreation Department members collaboration on an important document: the Lunar
Resort's upcoming activities newsletter. Resort representatives from each activity
group must contribute content to entice resort guests to sign up for activities.
Staff members involved include Chip Shot (golf instructor), Wilma Carwin (lunar
rover race instructor), and Tenzing Nepal (lunar sherpa). Each of them use
different devices and bring their own style and attitude to the collaboration.
So perhaps you'll be able to relate to their effort.

Before the group leads can start working on the newsletter, the department
director (who's associated with the organization's content administrator Role)
creates a folder called *Newsletters* in the organization's document library. To
follow along with the example, create the *Newsletters* folder in an
organization's document library on your site. This folder will hold all of the department's
newsletters.

Next, open your favorite document editor, enter the title "Upcoming Lunar
Activities", and save the document as `upcoming-activities.docx`. Upload the
file to the document library and enable the leads to edit the file by
associating them with Role that you grant permission to edit the file. To enable
the activity leads to access the newsletter from the department's private
Organization Site, add a Documents and Media app to its home page. Then turn on
the *Show actions* display option in the app's configuration window. Make sure
to assign the leads a role with permissions to access the folder. From the
Documents and Media app on Recreation Department's private home page, the leads
can now access the *Newsletters* folder and the initial document. The
newsletter's next collaborator will be Tenzing Nepal, the resort's veteran lunar
sherpa.

Tenzing is as agile online as he is on a mountainside. He often edits his
documents across several versions so they read just as he likes them. He prefers
to check documents out from within the web interface and download them to his
computer to work on them. Let's follow Tenzing as he produces multiple versions
of the newsletter. 

### Creating New File Versions [](id=creating-new-file-versions)

To prevent other users from accidentally overwriting changes you're making to a
document, the Document Library allows you to *checkout* the document. The user
who checks out a file is the only user who can checkin changes to the file.
Checking in a file produces a new file version. 

Tenzing Nepal is the first resort instructor to edit the activities newsletter.
You can imitate Tenzing's actions or follow along as you create new versions of
your own files. Inside the Documents and Media app, select the
`upcoming-activities.docx` file's *Checkout* action. Upon checkout, the file's
status is *Draft*. While the file is checked out, you're the sole editor; all
other users must wait until after you've checked in the file to edit it. While
you have the file checked out, you can download it, replace the file entry's
file, move the file entry to another Document Library folder, check it in, or
cancel checkout. Tenzing proceeds with the next step towards contributing to the
file.

Unless you're using [Liferay Sync](/discover/portal/-/knowledge_base/7-0/using-liferay-sync-on-your-desktop)
or a local drive mapped to the file's WebDAV (explained in the next section)
URL, you'll typically follow the basic workflow below to edit a Document Library
file from your machine:

1. Checkout the file.
2. Download the file and edit it locally.
3. Edit the file locally.
4. From the file's Edit screen in Documents and Media, browse to and select the
local file. Save and Checkin
5. Check in the file.

Let's continue as Tenzing, following the above worklow. (1) You've already
checked out the file. (2) Download the file to your machine by clicking the
*Download* action. (3) Then open the file in your favorite document editor, add
information on the upcoming Lunar hikes, and saves your changes. You're now
ready to apply the changes to file in Documents and Media.

In the file entry's screen in Documents and Media, click on the *Edit* action.
Then click *Browse*, navigate to the copy of the `upcoming-activities.docx` file
you edited on your desktop, and select it. Then click the *Save and Check In*
button and the *Describe Your Changes* screen appears. This screen allows you to
describe your changes and specify whether to apply a major or minor version
increment (default). For example, if a file's current version is 1.0, the file's
next minor version is 1.1 and next major version would be 2.0. To increment the
file to a new *Major Version*, you must select the *Changes Create a Major
Version* checkbox. Your changes will otherwise result in a new Minor Version.

Select the *Changes Create a Major Version* checkbox, describe your changes in
the *Change Log* field, and click *Save*. The file's major version number is
incremented and its status is now *Approved*.

+$$$

**Note**: If you *Edit* a file without checking it out, the Edit screen displays
a checkbox *Customize the Version Number Increment and Describe My Changes*. For
your file modification, if you want to select between major/minor version types
and/or want to enter a change log then select the checkbox. On clicking
*Publish*, the *Describe Your Changes* screen appears so you can specify your
change's version increment type and description.

$$$

Since the file is checked in, any user can now check it out. Tenzing realizes
there are some minor details he wants to add. As Tenzing, checkout the file once
again. Download the file locally, make the changes (any changes you like), and
save the local file. Then edit the file entry in Documents in Media, browse to
and select your local file, and click *Save and Check In*. This time, leave the
major version selection unchecked and click *Save*. The file is checked in and
it's minor version number is incremented. 

![Figure 18: No file is "written in stone". Version history actions let you inspect, delete, and reinstate file versions.](../../../images/dm-file-version-actions.png)

In the file entry's screen, the *Version History* table lists the versions of
the file. The department director created version 1.0, Tenzing created major
version 2.0, and Tenzing also created minor version 2.1. Each file version has
an Actions icon (![Actions](../../../images/icon-actions.png)). You
can perform the following actions on each file version that's been checked in
and approved (if no workflow is associated with a folder, the file's approved on
checkin):

**Download**: Download the version of the file to your machine.

**View**: View the file entry screen for the version of the file.

**Revert**: Check in a new major version of the file based on that file version.

**Delete Version**: Remove the file version from the Document Library. All other
file versions remain intact.

You've used the basic Document Library workflow to edit files locally and upload
them to create new file versions of the newsletter. Next up is accessing
documents from your desktop file manager. To demonstrate this, you'll step into
the shoes of lunar rover race instructor Wilma Carwin.

Wilma updates the newsletter in a different manner. She doesn't care much for
high tech. The only online device she uses is the workstation in the garage
office. Wilma only uses it when she has to; otherwise she'd rather be behind the
wheel of a rover doing donuts in the dirt and banking turns off the canyon
walls. Fortunately for Wilma, the resort IT team mapped the rec department's
document library to a network drive on her workstation. This lets her edit
documents without having to meddle with the Lunar Resort site. It's worth
explaining how they integrated her file manager with the document library. 

### Desktop Access to Documents and Media [](id=desktop-access-to-documents-and-media)

You can access the Document Library from your computer's file manager via
WebDAV. WebDAV stands for Web-based Distributed Authoring and Versioning. It's a
set of methods based on HTTP that allows users to create, edit, move, or delete
files stored on web servers. WebDAV is supported by most major operating systems
and desktop environments, including Linux (both KDE and GNOME), Mac OS, and
Windows.

At the Lunar Resort, Activity Department members use WebDAV to access their
Document Library. WebDAV provides an alternative way to do this using a file
manager instead of a web browser. To access the Document Library folder from
your file browser, you need to use log in credentials for the @product@
instance and the WebDAV URL of the folder you'd like to access.

Navigate to the Documents and Media app hosting the folder you'd like to access.
Click on the folder's Actions icon ![Actions](../../../images/icon-actions.png) and
select *Access from Desktop*.

![Figure 19: Select *Access from Desktop* to get the folder's WebDAV URL.](../../../images/dm-access-from-desktop-action.png)

Copy the WebDAV URL. On Windows, map a network drive drive to the WebDAV URL.
Enter your @product@ credentials when you're prompted for them. The document
library folder appears in the network drive. From your file browser, you can now
add, edit, move, or delete files in this folder.

On Mac OS X, select *Go* &rarr; *Connect to Server* in Finder. Then enter the
WebDAV URL of the folder you'd like to access in the Server Address field, click
*Connect* and you should be prompted for your @product@ credentials.

On Linux, you must slightly modify the WebDAV URL of your folder in your file
manager. For KDE's Dolphin, change the URL's protocol so that it says
`webdav://` instead of `http://`. For GNOME's Nautilus, change the URL's
protocol so that it says `dav://` instead of `http://`. Then press *Enter* and
you're prompted for your @product@ credentials.

Note that @product@ increments the version numbers of files edited and
uploaded via WebDAV so you don't have to worry that using your file manager will
bypass the functionality of @product@'s web interface. 

Lunar rover race instructor Wilma Carwin doesn't mind editing her Documents and
Media files from her computer's file manager, because it's convenient and
easiest for her. She opens the document `upcoming-activites.docx` and adds news
about various types of rover racing lessons offered and the upcoming Lunar Grand
Prix. She saves the file and closes it. The file in Documents and Media is now
"Approved" and its minor version is incremented. Voila! She's done her part
collaborating on the newsletter.

Following Wilma Carwin's demonstration, you've learned how to map your local file
manager to a Document Library folder and collaborate on files from your desktop
environment. 

## Summary [](id=summary)

You've learned the fundamentals of uploading, managing, and collaborating on
files in your sites. The Document Library integrates each file into @product@,
allowing you to apply permissions, workflows, and rich metadata. And it provides
a version control system for managing document progress. You displayed image
files on pages using the Media Gallery and Documents and Media apps. And you've
mastered checking out and checking in new file versions using the basic
download/upload technique and by mapping your local file manager to the Document
Library, for seamless file editing. Congratulations on your document management
accomplishments!

Now that we know the basic functionality of the Documents and Media portlet,
let's explore using document types and metadata.
