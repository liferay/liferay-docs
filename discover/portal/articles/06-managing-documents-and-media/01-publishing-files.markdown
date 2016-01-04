# Publishing Files

As you create sites, you'll probably want store files on the site and share
them. You can manage them in the Documents and Media library (Document Library).
To use documents where they're relavent, Liferay lets you use global, site
scoped, and page scoped Document Library instances. In this chapter, you'll
learn how to add files to a Document Library, display them in a site, and
collaborate on a file with other users. Let's get the ball rolling by adding
files to a Document Libary. 

## Adding Files

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

You can force users to add only certain document types to a folder. By default,
child folders inherit the restrictions of their parent folder. You can change
this behavior by editing the folder and selecting the allowed document types.

If workflow is enabled, you can specify different workflow definitions per
folder. Furthermore, you can specify different workflow definitions per document
type and per folder. You can set this by editing the folder. 

![Figure x: From a folder's Edit screen, you can restrict the document types it supports and select the folder's workflow.](../../images/dm-doc-type-restrictions-and-workflow.png)

Document types are a powerful way to enforce rules for documents uploaded by
users. For our example, we'll keep the default supported document types and
workflow settings. 

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

**Edit**: Lets you modify the file's name, description, document type,
categorization, and [related assets](/discover/portal/-/knowledge_base/7-0/publishing-content-dyanmically).
You can even upload a new file to replace it. Note, modifying the file
increments its version. 

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

Once you've uploaded image files and videos to your Document Library, you'll
probably want to display them in your site. Here are some ways of you can
display your image files:

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
style. The Ratings section lets you enable users to rate files and comment on
them.

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

Not only does Liferay's Documents and Media enable you to publish files easily,
but it also provides a terrific environment for collaborating on documents.
There are many common collaboration scenarios. For example, you might have a
spreadsheet that you and your peers need to populate, or perhaps a proposal that
you and other stakeholders must produce. Or you may be asked to review and edit
a document. All these cases involve multi-author documents and they require an
environment that helps manage document changes. Liferay's Documents and Media
provides these capabilities. 

Liferay's document management apps support accessing apps on different kinds of
devices and and environments. Not only can you access documents from your site
via your browser, but you can also access them from your native file browser and
use Liferay Sync to access them from your tablet or mobile device. And Liferay
Sync makes sure you're you can access the latest document versions. 

To demonstrate using these features, we'll walk through how Lunar Resort
Recreation Department members collaborate on an important document: the Lunar
Resort's upcoming activiters newsletter. Representatives from each activity
group must contribute content to entice resort guests to sign up for activities.
Staff members involved include Chip Shot (golf insructor), Wilma Carwin (lunar
rover race instructor), and Tenzing Nepal (lunar sherpa). Each of them use
different devices and bring their own style and attitude to the collaboration.
So perhaps you'll be able to relate to their effort.

Before the leads can start working on the newsletter, the department director
(who's associated with the organization's content administrator Role) creates a
folder called *Newsletters* in the organization's document library. Open your
favorite document editor, enter the title "Upcoming Lunar Activities", and save
the document giving it the file name `upcoming-activities.docx`. Upload the file
to the document library and enable the leads to edit the file by associating
them with Role that you grant permission to edit the file. To enable the
activity leads to access the newsletter from the department's private
Organization Site, add a Documents and Media app to its home page. Then turn on
the *Show actions* display option in the app's configuration window. Make sure
to assign the leads a role with permissions to access the folder. The leads can
now access the *Newsletters* folder and the initial document from Documents and
Media on Recreation Department's private home page. 

Tenzing Nepal, the resort's veteran lunar sherpa, is as agile online as he is
on a mountainside. He often edits his documents accross several versions so they
read just as he likes them. He prefers to check documents out from within the
web interface and download them to his computer to work on them. Let's follow
Tenzing as he produces multiple newsletter versions. 

### Creating New File Versions

To prevent other users from accicentally overwriting changes you're making to a
document, the Document Library allows you to *checkout* the document. The user
who checks out a file is the only user who can checkin changes to the file.
Checking in a file produces a new file version. 

Tenzing Nepal is the first resort instructor to edit the activities newsletter.
Inside the Documents and Media app, he selects the `upcoming-activities.docx` file's
*Checkout* action. Upon checkout, the file's status is *Draft*. 
While Tenzing has the file checked out, he's the sole editor; all
other users must wait until after he's checked in the file to edit it. While he
has the file checked out, he can download it, edit which file it's refering to,
move it to another Document Library folder, check it in, or cancel checkout.
Tenzing proceeds with the next step towards contributing to the file.

Unless [Liferay Sync](/discover/portal/-/knowledge_base/7-0/using-liferay-sync-on-your-desktop)
makes or a local drive mapped to the file's WebDAV (explained in the next
section) URL, you typically follow the basic workflow below to edit a Document
Library file from your machine:

1. Checkout the file.
2. Download it.
3. Edit the file locally.
4. From the file's Edit screen in Documents and Media, browse to and select the
local file.
5. Check in the file.

Let's continue as Tenzing, following the above worklow. You've already checked
out the file (1). Now download the file to your machine by clicking the
*Download* action (2). Then open the file in your favorite document editor, add
information on the upcoming Lunar hikes, and saves your changes (3). You're now
ready to apply the changes to file in Documents and Media.

In the file entry's screen in Documents and Media, click on the *Edit* action.
Then click *Browse*, navigate to the copy of the `upcoming-activities.docx` file
you edited on your desktop, and select it. Then click the *Save and Check In*
button and the *Describe Your Changes* screen appears. This screen allows you to
specify whether to apply a major or minor (default) version increment and
describe your changes. For example, if a file's current version is 1.0, the
file's next minor version is 1.1 and next major version would be 2.0. To
increment the file to a new *Major Version*, select the *Changes Create a Major
Version* checkbox. Your changes will otherwise result in a new Minor Version. In
the *Change Log* field, you can describe your changes. To check in the file and
apply the version increment, click *Save*.

Select the *Changes Create a Major Version* checkbox, describe your changes, and
click *Save*. The file's major version number is incremented and its status is
now *Approved*.

+$$$

**Note**: If you *Edit* a file without checking it out, the Edit screen displays
a checkbox *Customize the Version Number Increment and Describe My Changes*. For
your file edit, if you want to select between major/minor version types and/or
want to enter a change log then select the checkbox. On clicking *Publish*, the
*Describe Your Changes* screen appears so you can specify your change's version
increment type and description.

$$$

Since the file is checked in, any user can check it out. Tenzing realizes there
are some minor details he wants to add. As Tenzing, checkout the file once
again. Download the file locally, make the changes (any changes you like), and
save the local file. Then edit the file in Documents in Media, browse to your
local file, and click *Save and Check In*. This time, leave the major version
selection unchecked, describe your changes, and click *Save*. The file entry's
minor version number is incremented.

<!-- Explain the Version History table 
-->


Race instructor Wilma Carwin updates the newsletter in a different manner. Wilma
doesn't care much for high tech. The only online device she uses is the
workstation in the garage office. She only uses it when she has to; otherwise
she'd rather be behind the wheel of a rover doing donuts in the dirt and banking
turns off the canyon walls. Fortunately for Wilma, the resort IT team mapped the
rec department's document library to a network drive on her workstation. This
lets her edit documents without having to meddle with the Lunar Resort site.
It's worth explaining how they integrated her file manager with the document
library. 

### Desktop Access to Documents and Media

You can access the Document Library from your computer's file manager via
WebDAV. WebDAV stands for Web-based Distributed Authoring and Versioning. It's a
set of methods based on HTTP that allows users to create, edit, move, or delete
files stored on web servers. WebDAV is supported by most major operating systems
and desktop environments, including Linux (both KDE and GNOME), Mac OS, and
Windows.

At the Lunar Resort, Activity Department members use WebDAV to access their
Document Library. WebDAV provides an alternative way to do this using a file
manager instead of a web browser. To access the Document Library folder from
your file browser, you need to use log in credentials for the portal and the
WebDAV URL of the folder you'd like to access.

Navigate to the Documents and Media app hosting the folder you'd like to access.
Click on the folder's Actions icon ![Actions](../../images/icon-actions.png) and
select *Access from Desktop*.

![Figure x: Select *Access from Desktop* to get the the folder's WebDAV URL.](../../images/dm-access-from-desktop-action.png)

Copy the WebDAV URL. On Windows, map a network drive drive to the WebDAV URL.
Enter your Liferay credentials when you're prompted for them. The document
library folder appears in the network drive. From your file browser, you can now
add, edit, move, or delete files in this folder.

On Mac OS X, select *Go* &rarr; *Connect to Server* in Finder. Then enter the
WebDAV URL of the folder you'd like to access in the Server Address field, click
*Connect* and you should be prompted for your Liferay credentials.

On Linux, you must slightly modify the WebDAV URL of your folder in your file
manager. For KDE's Dolphin, change the URL's protocol so that it says
`webdav://` instead of `http://`. For GNOME's Nautilus, change the URL's
protocol so that it says `dav://` instead of `http://`. Then press *Enter* and
you're prompted for your Liferay credentials.

Note that Liferay increments the version numbers of files edited and uploaded
via WebDAV so you don't have to worry that using your file manager will bypass
the functionality of Liferay's web interface. 

Lunar rover race instructor Wilma Carwin doesn't mind editing her Documents and
Media files from her computer's file manager, because it's convenient and
easiest for her. She opens the document `upcoming-activites.docx` and adds news
about various types of rover racing lessons offered and the upcoming Lunar Grand
Prix. She saves the file and closes it. The file in Documents and Media is now
"Approved" and its minor version is incremented. Voila! She's done her part collaborating
on the newsletter. 

<!--
Make sure to include:

- Email configuration

-->
