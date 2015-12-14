# Publishing Files

TODO - Chapter intro

## Adding and Organizing Files

TODO - Update this section's images. They're currently just filler.

The Lunar Resort photographers take lots of photos and video footage of resort
guests having the time of their lives. As a courtesy to the guests the staff
stores each party's photos and videos on the site and shares them with the
parties. At first, you might think "How does a resort's sharing of pics and
videos relate to me and my site?" Well, even if your company isn't involved with
in tourism, it probably needs to enable specific users to store, organize, and
share files. No matter what kind of files you work with, the fundamentals of
authorizing users to add and interact with files is the same. As an example,
let's explore how to implement this for the Lunar Resort's staff.

At the Lunar Resort, we need to exercise care in managing who can add, view, and
update groups of files. The resort stores all kinds of files, for various
purposes in their Documents and Media libraries. The libraries not only contain
guest media but also departmental documents and media files. Most of the
documents are meant to be only disseminated within the departments. The Liferay
platform provides tools to implement the resort's use cases. You see, Liferay's
[Roles and
Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions) let
you carefully manage access to documents and media files. In addition, Documents
and Media's folder permissions help you organize resort party media. You might
want to permission and organize your site's files in a similar manner. Let's get
started on the right foot with the Lunar Resorts guest media files by leveraging
roles, permissions, and folders in Documents and Media.

Since the Lunar Resort's *Souvenir and Memorabilia* staff members must be able
to upload and organize guest photos and videos, let's enable a role for those
purposes. Sign in as the Liferay admin and open the the *Product Menu*
(![Product Menu](../../images/icon-menu.png)). Then navigate to *Control Panel
&rarr; Users &rarr; Roles*. If the staff group is a part of an Organization, and
that organization has a site, create an Organization Role. Otherwise, create it
as a Site Role. Name the role *Resort Guest Media Manager*. Once you've created
it, click the role's Actions icon
(![Actions](../../images/icon-action-triangle.png)) and select *Define
Permissions*. The *Roles* screen appears, showing the *Define Permissions* tab.
In that tab, navigate to *Site Administration &rarr; Content &rarr; Documents
and Media*, select . *Access in Site Administration* from the *General
Permissions* section, and click *Save*. This role will manage your special set
of media files. Once you've created the role, assign it to the users who manage
the guest media. For the Lunar Resort, we created a user named Snappy (username:
*snappy*) that we assigned to the role. Snappy is our trigger-happy
photographer.

+$$$

To learn how to create a role and define permissions for it, refer to [Roles and
Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions).

$$$

You've created your specialized role and assigned users to it. Next, you need a
space in the Documents and Media library for that role to manage Lunar Resort
guest media files. A Documents and Media folder fits the bill. In that folder
the role will add, organize, and share the files. First, open the Product Menu,
click on your site's name, and navigate to *Content &rarr; Documents and Media*
in for your site. The Documents and Media screen appears and displays the
Documents and Media library's *Home* folder (the library's root folder). As you
add files and folders to the library, they're listed here.

![Figure x: The Documents and Media's *Home* folder starts empty. But the Add menu lets you upload all kinds of documents to the library.](../../images/dm-admin-with-prod-menu-and-add-menu.png)

Click the Add icon (![Add](../../images/icon-add.png)) to show what you can add
to the Document Library. You can add documents, folders, and shortcuts just like
on your local file system. You can even add access to an entirely different
repository. Here are the Add menu's options:

**Folder:** lets you create a new location in your portlet's file system.

**Shortcut:** allows you to create a shortcut to any document that you have read
access for. You can set permissions on the shortcut to specify who can access
the original document through the shortcut.

**Repository:** Allows you to add an entirely new repository to your Document
Library. Refer to the [Repostory
Types](/discover/portal/-/knowledge_base/7-0/repository-types) section to learn
how to add a repository. 

**Multiple Documents:** allows you to upload several documents at once.

**Basic Document:** allows you upload a single file that you would like the
default document type, "Basic Document," to apply to. By default, basic
documents are not described by any metadata sets.

The remaining items in the Add menu are default document types that are each
described by a unique metadata set. When you add a document belonging to a
specific document type, you're presented with a form to not only specify the
file to upload but also to fill out the fields defined by the document type's
metadata set. We describe the "Contract" document type by way of example.

**Contract:** lets you upload a file that you would like the "Contract" document
type to apply to. This document type is intended to be used to describe legal
contracts. By default, contracts are described by effective date, expiration
date, contract type, status, legal reviewer, signing authority and deal name
fields. Document types are discussed below. 

Any custom documents types that have been defined also appear in the Add menu.
If a document type has been created that matches the document you would like to
upload, you can select that document type from the Add menu. This will associate
the metadata fields associated with the document type to your document and you
will be asked to fill out the fields.

Let's continue a folders to organize the Lunar Resort guest photos and videos
that we'll upload. From the Add menu, select *Folder*. The new folder form
appears. Since the folder is for storing Lunar Resort guest media, name the
folder *Resort Guest Media*. You can optionally describe the folder too. The
folder is initially viewable by site members, but we'll change that after we
create it. Click the *More Options* link to show checkboxes for more
permissions. By default, site members are able to add files to the folder and
subscribe to changes in the folder's files. Deselect all of these checkboxes, as
we only want resort media managers to modify this folder's files. Click *Save*
to finish creating the folder.

Since we only want the *Resort Guest Media Manager* role to manage files in the
*Resort Guest Media* folder, we must fine tune the folder's permissions. Click
on the folder's *Actions* icon (![Actions](../../images/icon-actions.png)) and
select the *Permissions* action. The Permissions window appears.

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
role are now the only non-admin users who can upload and manage media files in
the folder. 

Now, sign in as a *Resort Guest Media Manager* role user. Since the Lunar
Resort's user named Snappy belongs to this role, we'll sign in as her. To open
the folder, go to *Documents and Media* in Site Administration, navigate to the
*Resort Guest Media* folder, and click the folder's name. The folder provides a
workspace for role members to manage files and folders.

Snappy has plenty of pictures of guests Mr. and Mrs. Smith on her local file
system. To separate the Smith's files from those of other guests, we'll create a
subfolder named *Smith Party*. To create it, click the Add icon. When clicked,
the icon lists all the document types and several other types of things you can
add. Select the *Subfolder* option to add a subfolder. In the *New Folder* form
that appears, name the folder *Smith Party* and describe the folder's purpose
(e.g., "The Smith's photos from their visit to the Lunar Resort on some-date").
Click the *More Options* link and deselect all the permissions within it, as we
only want media managers to be able to edit the folder. *Save* the new
subfolder. You're returned to the folder *Resort Guest Media*, which now lists
your new subfolder *Smith Party*. Navigate into the subfolder. The Document and
Media library's navigation breadcrumbs shows your folder's path: *Home &rarr;
Resort Guest Media &rarr;Smith Party*. Next, let's add the Smith's photos to the
folder.

There are a few different ways that you can add image files, or any file for
that matter. You can add image files one at a time or add multiple files
simultaneously. Let's add all the images at once. Click the Add icon and select
*Multiple Documents*. The *Add Multiple Documents* screen appears and displays
an area for dropping files in. You can drag and drop files into the area, or you
can browse for and select multiple files by clicking the *Select Files* button.
Use the method that's easiest for you. As you drop in files or select files, the
Add Multiple Documents screen lists them. On the side of the screen, the *All
Selected Files* section lists several options for the files. You can fill in a
common *Description* for the files. You can also select the the *Document Type*
of the files. Since we're uploading image files, we'll use the *Basic Document*
type, which is the default type. There are also options for categorizing the
selected files and assigning them default permissions. We'll tighten up
permissions by clicking the *More Options* link and deselecting all the
permissions. When you're ready to upload the files, click *Publish*. The Add
Multiple Documents screen stays active, ready for you to add more files. When
you're done adding files, click the Back icon
(![Back](../../images/icon-back.png)) at the top of the screen. You're taken
back to the folder you're adding files to. 
<!-- For categorization and tags, link to  
Reference Publishing Content Dynamically > Organization Content with Tags and
Categories -->

![Figure x: Documents and Media's lists files using several display styles: icon (as shown here), descriptive, and list. And breadcrumbs show the current folder's path in the library.](../../images/dm-images-in-admin.png)

Documents and Media lists your folder's files. They're shown using the icon
display style, by default. You can select either icon, descriptive, or list
display style; here they're shown from left to right: ![Display
Styles](../../images/list-view-display-styles.png). The icon display style shows
a file as a cover image. If the file is an image type file, the image is used as
the file entry's cover image. Documents and Media, otherwise, displays a
familiar image that represents the file's type. Also displayed with each file
icon is the file's suffix (e.g., *JPG* for a file ending with `.jpg`), last
modification timestamp, name, and status (e.g., *Approved*, *Draft*, etc.). And
each file has an Actions menu. The actions are also available from within the
file entry's view, which we'll explore soon. The descriptive and list display
styles provide the same functionality, but provide a different look and are
displayed in rows. 

To see the current folder's details click the *Information* icon
(![**i**](../../images/icon-information.png)). An area with the folder's name
appears. It shows the number of items in the folder. It also shows a *Subscribe*
icon that you can click to get notifications about files added to or modified in
the folder. Lastly, the Actions icon lists actions you can perform on the
current folder.

The tab bar at the top of Documents and Media's view provides filters. *Folders*
lists the folders in Home, *Recent* shows the most recently modified files,
*Mine* lists all the current user's files (no matter their folder), and
*Document Types* filters on files of the selected document type. The *Search*
field next to the filters enables you to find files by keywords. If your portlet
contains more documents than it can display at once, you can use the navigation
tool at the bottom of the portlet window to either switch your view to another
page or configure the page to display more documents per page.

Just above the file list, the *Order by* selector lets you select criteria for
ordering the files and folders. You can order them by creation date, title,
number of downloads, modification date (default), or size. The up and down
arrows let you arrange them in ascending or descending order.

From the list view, Documents and Media lets you act on one or more files at
once. When you select the *All* checkbox, next to the *Order by* selector, the
following action icons appear: ![Action
Icons](../../images/dm-action-icons.png). Describing them from left to right,
the X is for cancelling file checkout, the secured lock is for file check-in,
the unsecured lock is for file check-out, the crossed arrows are for moving the
files, and the trash can is for moving files to the Recycle Bin. You move
selected files to a subfolder via drag and drop. File check-out and check-in is
explained later in
[Collaborating on Files](/discover/portal/-/knowledge_base/7-0/publishing-files#collaborating-on-files).

![Figure x: A file's entry view lets you act on the file, preview it, and inspect its details. If you've installed an appropriate preview plugin for a file, its preview image displays in the preview area. Liferay can, by default, preview many image types.](../../images/dm-file-entry-details.png)

To open its file entry view, click the file's name. The screen's central viewing
area displays a preview image of the file. If the file is an image file, its
image is displayed. If a preview plugin for the file type is installed, the
plugin displays an image (e.g., the opening scene of a video file or a
presentation's first slide) for the file. If there are no preview plugins for
the file, Liferay displays a generic image based on the file's type. Let's take
a moment and consider file preview plugins and some of the powerful features
they offer. 

Whenever possible, Liferay generates previews of documents added to the Document
Library. Out of the box, Liferay only ships with Java-based APIs to generate
previews for documents. The only tool available that is 100% Java and has a
compatible license to be distributed with Liferay is PDFBox. If you upload a PDF
file to the Documents and Media portlet, Liferay generates a preview for the PDF
in a separate thread. This process may last only a few seconds for a small file.
The larger the file, the longer it takes.

While a default implementation of image generation for document previews and
thumbnails is provided via PDFBox, you'll need to install and configure some
additional tools to harness the full power of Liferay's Documents and Media
library. These tools include [*OpenOffice*](http://www.openoffice.org) or
[*LibreOffice*](http://www.libreoffice.org),
[*ImageMagick*](http://www.imagemagick.org), which requires
[*Ghostscript*](http://www.ghostscript.com), and
[*Xuggler*](http://www.xuggle.com/xuggler). With these tools installed and
configured, Documents and Media content is displayed using a customized viewer
depending on the type of content. Configuring Liferay to use OpenOffice or
LibreOffice in server mode allows you to generate thumbnails and previews for
supported file types (.pdf, .docx, .odt, .ppt, .odp, etc.), lets you view
documents in your browser, and lets you convert documents. ImageMagick allows
for faster and higher-quality previews and conversions. Xuggler allows for audio
and video previews, lets you play audio and video files in your browser, and
extracts thumbnails from video files. Please see [Configuring
Liferay](/discover/portal/-/knowledge_base/7-0/configuring-liferay) for how to
use these tools.

You can view a document with a customized viewer that allows you to navigate
through the different pages of the document and read its content. In addition,
you can view a multimedia document (audio or video) and play it online. If the
browser supports HTML5, it uses the native player of the browser. Otherwise it
falls back to a Flash player.

<!--Figure : You can watch a video clip or even view a slideshow inside Liferay's
Documents and Media portlet.-->

Document previews are powerful and help users browse media more successfully to
find what they're looking for. 

Above the file viewing area are the file's icon and the file entry's name,
author, upload timestamp, and rating. You can rate the file too. The comments
area below the file viewing area lets you comment on the file, subscribe to
comments, and reply to comments.

A file's actions are listed at the top of the file entry screen. Here are the
file actions:

**Download**: Downloads the file to your device.

**Edit**: Lets you modify the file's name, description, document type, categorization,
and related assets. You can even upload a new file to replace it. Note, modifying the file
increments its version. <!-- Link to  link to  
Reference Publishing Content Dynamically > Defining Content Relationships -->

**Move**: Relocate the file to a different parent folder. 

**Checkout/Checkin**: Prevents others from modifying the document while you
are working. Other users can still view the current version of the document if
they have permission. You can check the document back in when you're done working. 

**Permissions**: Lets you specify which actions each role can perform on the
file. You can granted a role permission to perform the following actions.

- **Update**: Allows the role to edit, checkout, and checkin the file.

- **Override Checkout**: Checkout the file, revoking the file's currrent
checkout if it is checked out.

- **Permissions**: View and configure this file's permissions.

- **Delete**: Move the file to the Recycle Bin.

- **View**: View the file.

- **Update Discussion**: Edit another user's comment on the file.

- **Delete Discussion**: Delete any comments on the file.

- **Add Discussion**: Comment on the file. 

- **Move to the Recycle Bin**: Removes the file from the Documents and Media
library to the Recycle Bin. 

File entry details are listed beside the file entry's main viewing area. The top portion of
this area lists the file's version number, status, modification timestamp, and
name of the user who last modified it. Next are links to download the file and
links to show its URL and WebDAV URL. You can specify the WebDAV URL as the file
name when opening the document from Microsoft Office.

The section *Automatically Extracted Metadata* lists any and all metadata that's
been extracted automatically from the file. When adding new documents or viewing
existing documents, a process is triggered automatically that extracts the
file's metadata. The library used by this process is TIKA and it's already
included in Liferay out of the box. Depending on your file's type and the
metadata written with the file, you can find out all kinds of details. In the
case of audio or video files, their duration is displayed. The *Version History*
section lists the different versions of the file and lets you view and download
specific versions.

Let's review what you've done so far. First, you created a role to manage a
specific set of files for your site. You assigned users to the role and created
a Documents and Media folder for them to add and organize files. Then as a
member of the role, you added folders and files to the Documents and Media
library. And just now, you viewed individual file entry information and actions.
Next, let's consider how to display files on site pages. 

## Displaying Files

TODO - Intro

Here are some ways of you can display your image files:

- Insert them in an asset, like a Web Content article or Blogs Entry
- Use the Asset Publisher
- Display them in a Media Gallery
- Show them in a Documents and Media app

The Lunar Resort media team sits down with each guest party to show them a page
filled with photos and videos of their vacation experience. The resort likes to
show them a slideshow to get some oohs and ahh's from the guests and determine
their favorites. Then they offer to print and frame photos, and plaster the
photos on all kinds of fun items like keychains, coffee mugs, and tee-shirts.
They'll even burn the media onto a DVD for the party. Let's explore how the
media team shows off the guest's pics and videos.

The media team creates a page for each guest party. Team members can customize
each page to focus on and show off the party's media. Each guest's media page
starts with a Documents and Media app and has a Media Gallery app below it. The
Documents and Media app shows thumbnail images of all the files and makes them
individually accessible for download. The Media Gallery comes with slideshow
capabilities and enables the staff member to download an archive of the folder's
files. Let's create a media page for the Smiths and add these apps to it.

Add a named *Smith Party* that has a one column layout. Then, add the *Documents
and Media* application to the page. It's in the *Content Management* section of
the *Applications* menu. The Documents and Media app shows your Home folder's
files by default. The app lets you browse, add, and act on files just as you do
in Site Administration. Since we want to focus on the Smith's files, let's
configure the app to show only files from the *Smith Party* Document Library
folder. Click the Documents and Media app's Options icon
(![Options](../../images/icon-app-options.png)) and select *Configuration*. The
Configuration window appears and shows the *Setup* tab's sub-tab *Display
Settings*.

The Display Settings tab has these sections: Display Settings, Folders Listing,
Entries Listing for List Display Style, and Ratings. The Display Settings
section provides checkboxes to enable showing each file's actions, file search,
and the file filters. File search is enabled by default. The settings also let
you set the maximum number of file entries to display per page. And lastly, you
can select display styles (e.g., Icon, Descriptive, and List) to make available
to the app's users. The Icon display style is selected by default.

The Folders Listing section lets you select a Document Library folder to serve as
the root folder from which to display files. The root folder is the
highest-level folder that's accessible from the Documents and Media portlet. For
example, suppose you created a folder called *My Documents* in the Documents and
Media portlet's default Home folder. If you set the My Documents folder to be
your portlet's new root folder, the original Home folder would no longer be
accessible.

The last two Display Settings tab's sections are the Entries Listing for List
Display Style section and Ratings section. The former section allows you to
select fields to show for each file, if the app's user selects the List display
style. The Ratings section lets you enable ratings and comments.
<!-- Explain more about and at least link to sections on ratings and comments? -->

Here are descriptions for the Documents and Media app's other configuration tabs:

**Permissions:** shows Liferay's permissions dialog for the application.

**Communication:** lists public render parameters the application publishes to
other applications on the page. Other applications can read these and take
actions based on them. For each shared parameter, you can specify whether to
allow communication using the parameter and select which incoming parameter can
populate it. 

**Sharing:** lets you embed the application instance as a widget on on any
website, Facebook, or Netvibes, or as an OpenSocial Gadget.

**Scope:** lets you specify the Document Library instance the application uses:
the current site's instance (default), global instance, or the page's instance.
If the page doesn't already have an instance of the app, you can select scope
option *+ \[Page Name\] \(Create New\)* to create a page-scoped instance for the
application to display. 

While we're in the Configuration window there are a couple things we should do.
First select the *Display Settings* tab. To enable the media team member to act
on the files, select the *Show Actions* checkbox. Since we only want to show the
Smith's files, click *Select* to browse and choose a the *Smith Party* folder.
To save your settings, click *Save*. The Documents and Media app now shows all
of the Smith's files. This app enables the Lunar Resort media team to act on
individual files. If Mrs. Smith likes particular files, the staff member can
download them locally and add notes about the file in the file entry's comments.

<!--Consider showing figure of the DM app with images and videos. Install
Xuggler to show off the video cover image too.-->

The Documents and Media app is great for working with files individually, but
the Media Gallery has slideshow features built in. Plus, it lets you download a
folder's files as an archive. The Media Gallery app is also in the *Add &rarr;
Applications* menu's *Content Management* section. Add the Media Gallery below
the Documents and Media app on your page. By default, it displays the Documents
and Media library's home folder's files. Bring up the Media Gallery's
Configuration window. The Media Gallery's settings are very similar to the
Documents and Media app's settings, but there are a couple differences. The
Media Gallery focuses only on image and video file types, and it uses a [Display
Template](/participate/liferaypedia/-/wiki/Main/Application+Display+Templates).
You can create your own Display Templates for the Media Gallery or
select an existing display template, like the *Carousel* display template.
**Important**: To access the Carousel display template in Media Gallery, your
role must have *View* access for that template. Since the Carousel template is
in the Global scope, a Global scope administrator must grant the role permission
to view the template. The Media Gallery also has configuration tabs for
communication, permissions, preferences, and scope. In the app's Display
Settings, select checkboxes *Show Actions* and *Show Folder Menu*. In the
*Folder Settings* section, select the *Smith Party* folder as the app's root
folder and click *Save*.

The Smith Party image file thumbnails display in the Media Gallery. When you
click on a file's thumbnail, a slideshow appears showing the selected image. The
window lists thumbnails of the folder's other images below the image that's
currently shown. And the slideshow continues until you either click pause or the
folder's last image is shown. The slideshow feature provides a nice way to show
off images. Click the X to close the slideshow window and return to the page. 

![Figure x: The Media Gallery's slideshow provides a nice way to view your images.](../../images/dm-media-gallery-slideshow.png)

Clicking the folder's Actions icon (![Actions](../../images/icon-actions.png))
lists actions you can perform with respect to the folder. For the Lunar Resort
media team, the *Download* option is very useful. It creates an archive of the
folder's files and dowloads them to your machine. The media staff burns a copy
of the downloaded files onto a disk and sells them to the guests for a fair
price. 

Here's a complete listing of the folder actions available in the Media Gallery:

**Download**: Downloads an archive of the files to your device.

**Edit**: Lets you modify the folder's name, description, document type, and
workflow restrictions

**Move**: Relocates the folder to a different parent folder. 

**Permissions**: Lets you specify which actions each role can perform on the
folder. 

**Add Subfolder**: Creates a folder within this folder.

**Add File Entry**: Lets you upload a file to the folder as a new file entry.

**Multiple Media**: Allows you to upload several files at once.

**View Slide Show**: Opens a window that shows the files in actual size in a
slide show on your device. 

**Add Shortcut**: Enables you to create a shortcut (link) to any Documents and
Media file the user is authorized to view. You can set permissions on the
shortcut to specify who can access the original document through the shortcut.

**Access from Desktop**: Makes the folder available to work with from a
Windows desktop.

**Move to the Recycle Bin**: Removes the file from the Documents and Media
library to the Recycle Bin. 

The Documents and Media app and Media Gallery are now at your disposal to show
off your site's Document Library files. The Lunar Resort's Souvenir and
Memorabilia group enjoys their patrons reactions to the great pics and videos
that Snappy takes of them. And the salespeople are more than happy to plaster
the pictures onto clothing and accessories to make a handsome commision. 

Next, we'll step out of the Souvenir and Memorabilia group and step into a
different realm of the Lunar Resort, to demonstrate collaborating on files in
Documents and Media.

## Collaborating on Files

TODO - Things to do or include:

- Use the DM app

- Collaborate on a newsletter (.docx) of activities for the upcoming season. 

- Mention the Related Assets field for the file entry.

- Explain file update checkbox *Customize the Version Number Increment and
Describe My Changes*

- Explain Document type restrictions and workflow per folder (see 
https://github.com/liferay/liferay-docs/blob/6.2.x/discover/portal/articles/05-document-management/06-automatic-previews-and-metadata.markdown#document-type-restrictions-and-workflow-per-folder-

- Show Subscription email configuration (collab)

- Demonstrate aspects of the File menu - Download, Edit, Checkout, Checkin, major/minor versions (collab)

- Access DM files from Desktop. WebDAV. Explain this compares with checkout
checkin. See
https://github.com/liferay/liferay-docs/blob/6.2.x/discover/portal/articles/05-document-management/06-automatic-previews-and-metadata.markdown#webdav-access- 


