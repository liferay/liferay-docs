# Publishing Files

TODO - Chapter intro

## Adding and Organizing Files

TODO - Update this section's images. They're currently just filler.

The Lunar Resort photographers take lots of photos and video footage of resort
guests having the time of their lives. As a courtesy to the guests the staff
stores each party's photos and videos on the Lunar Resort site and shares them
with the parties. At first, you might think "How does a resort's sharing of pics
and videos relate to me and my site?" Well, even if your company isn't involved
with in tourism, it probably needs to enable specific users to store, organize,
and share files. No matter what kind of files you work with, the fundamentals of
authorizing users to add and interact with files is the same. As an example,
let's explore how to implement this for the Lunar Resort's media staff.

At the Lunar Resort, we need to exercise care in managing who can add, view, and
update groups of files. The resort stores all kinds of files, for various
purposes in their Documents and Media library. The library not only contains
media files of guests but also documents from various departments. Most
departmental documents are meant to be only be disseminated within that
department. And even within the Lunar Resort's media department, their guest
media use case requires guest parties only be able to interact with their own
party's photos and videos. The Liferay platform provides tools to implement the
resor's use case. You see, Liferay's [Roles and
Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions) let
you carefully manage access to document and media files. In addition, Documents
and Media's folder permissions help partition resort party media. You might want
to do something similar in your site. Let's get started on the right foot with
the Lunar Resorts guest media files by leveraging roles, folders, and
permissions in Documents and Media.

Since the Lunar Resort media staff members must be able to upload and organize
guest photos and videos, let's enable a Site Role for those purposes. Sign in as
the Liferay admin and open the the *Product Menu* (![Product
Menu](../../images/menu-icon.png)). Then navigate to *Control Panel &rarr; Users
&rarr; Roles*. If you're building the Lunar Resort, create a Site Role called
*Resort Guest Media Manager*. Once you've created it, click the role's Actions
icon (!(Actions](../../images/action-triangle-icon.png)) and select *Define
Permissions*. The *Roles* screen appears, showing the *Define Permissions* tab.
In that tab, navigate to *Site Administration &rarr; Content &rarr; Documents
and Media*, select . *Access in Site Administration* from the *General
Permissions* section, and click *Save*. This role will manage your special set
of media files.

+$$$

**Note**: To learn how to create a Site Role and Define Permissions for it,
refer to [Roles and
Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions).

$$$

Once you've created your specialized Site Role, assign that role to the users
who manage the media. Open the *Product Menu* and click on your site's name, to
show the Site Administration menu. In the menu, navigate to *Users &rarr; Site
Memberships*. Find the user to assign the role, click on that user's Actions
icon (![Actions](../../images/actions-icon.png)) and select the *Assign Site
Roles* action. The Assign Site Roles window appears and shows the role you
created. Select the role you created and click the *Add* button. Your user is
now authorized to do everything we permit the role to do. Assign to the role all
users you want to associate with it. For the Lunar Resort, we've created a user
named *photographer* that we assigned to the role.

You've created your specialized Site Role and assigned users to it. Next, you
need a space in the Documents and Media library for that role to manage Lunar
Resort guest media files. A Documents and Media folder fits the bill. In that
folder the role will add, organize, and share the files. First, navigate to
*Content &rarr; Documents and Media* in Site Administration. The Documents and
Media screen appears and displays the Documents and Media library's *Home*
folder (the library's root folder). As you add files and folders to the library,
they're listed here.

![Figure x: The Documents and Media's *Home* folder starts empty. But the Add menu lets you upload all kinds of documents to the library.](../../images/dm-admin-with-prod-menu-and-add-menu.png)

Click the Add icon (![Add](../../images/add-icon.png)) and select the *Folder*
option. The new folder form appears. Since the folder is for storing Lunar
Resort guest media, name the folder *Resort Guest Media*. You can optionally
describe the folder too. The folder is initially be viewable by anyone, but
we'll change that after we create it. Click the *More Options* link to show
checkboxes for more permissions. By default, site members are able to add files
to the folder and subscribe to changes in the folder's files. Deselect all of
these checkboxes, as we only want resort media managers to modify this folder's
files. Click *Save* to finish creating the folder. 

Since we only want the *Resort Guest Media Manager* role to manage files in the
*Resort Guest Media* folder, we must fine tune the folder's permissions. Click
on the folder's *Actions* icon (![Actions](../../images/actions-icon.png)) and
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
permissions from all the other roles, and click *Save*. Users assigned to the role 
are now the only non-admin users who can upload and manage photos and
videos in the folder. 

Now, sign in as a *Resort Guest Media Manager* role user. Since the Lunar
Resort's user named *photographer* belongs to this role, we'll sign in as her.
To open the folder, go to *Documents and Media* in Site Administration, navigate
to the *Resort Guest Media* folder, and click the folder's name. The folder
provides a workspace for members of the role to manage files and folders.

The Lunar Resort's photographer has plenty of pictures of guests Mr. and Mrs.
John Smith on her local file system. To separate the Smith's files from those of
other guests, we'll create a subfolder named *Smith, John and Jane*. To create
it, click the Add icon. When clicked, the icon lists all the document types and
several other types of things you can add. Select the *Subfolder* option to add
a subfolder. In the *New Folder* form that appears, name the folder *Smith, John
and Jane* and describe the folder's purpose (e.g., "The Smith's photos from
their visit to the Lunar Resort on some-date"). Click the *More Options* link
and deselect all the permissions within it, as we only want media managers to be
able to edit the folder. *Save* the new subfolder. You're returned to the folder
*Resort Guest Media*, which now lists your new subfolder *Smith, John and Jane*.
Navigate into the subfolder. The Document and Media library's navigation
breadcrumbs shows your folder's path: *Home &rarr; Resort Guest Media
&rarr;Smith, John and Jane*. Next, let's add the Smith's photos to their folder.

There are a few different ways that you can add image files, or any file for
that matter. You can add image files one at a time or add multiple files
together. Let's add all our images at once. Click the Add icon and select
*Multiple Documents*. The *Add Multiple Documents* screen appears and displays
an area for dropping files in. You can drag and drop files into the area or you
can browse for and select multiple files by clicking the *Select Files* button.
Use the method that's easiest for adding your files. As you drop in files or
select files, the Add Multiple Documents screen lists them. On the side of the
screen, the *All Selected Files* section lists several options for the files.
You can fill in a common *Description* for the files. You can also select the
the *Document Type* of the files. Since we're uploading image files, we'll use
the *Basic Document* type, which is the default type. There are also options for
categorizing the selected files and assigning them default permissions. We'll
tighten up permissions by clicking the *More Options* link and deselecting all
the permissions. When you're ready to upload the files, click *Publish*. The Add
Multiple Documents screen stays active, ready for you to add more files. When
you're done adding files, click the Back icon
(![Back](../../images/back-icon.png)) at the top of the screen. You're taken go
back to the folder you're working in. 

![Figure x: Documents and Media's lists files using several display styles: icon (as shown here), descriptive, and list. And breadcrumbs shows the current folder's path in the library.](../../images/dm-images-in-admin.png)

Documents and Media lists your folder's files. They're shown using the icon
display style, by default. You can select either icon, descriptive, or list
display style; here they're shown from left to right: ![Display
Styles](../../images/list-view-display-styles.png). The icon display style shows
file as a cover image. If the file is an image file, that image is used as the cover image.
Documents and Media, otherwise, displays a familiar image that
represents the file's type. Also displayed with each file icon is the file's
suffix (e.g., *JPG* for a file ending with `.jpg`), last modification timestamp, name, and status (e.g., *Approved*, *Draft*,
etc.). And each file has an Actions menu. The actions are also available
from within the file entry's view, which we'll explore soon. The
descriptive and list display styles provide the same functionality, but provide
a different look and are displayed in rows. 

To see the current folder's details click the *Information* icon
(![i](../../images/information-icon.png)). An area with the folder's name
appears. It shows the number of items in the folder. It also shows a *Subscribe*
icon that you can click to get notifications about files added to or modified in
the folder. Lastly, the folder's Actions icon lists actions you can perform on
the current folder.

The tab bar at the top of Documents and Media's view provides file filters.
*Folders* lists the folders in Home, *Recent* shows the most recently modified
files first, *Mine* lists all of the current user's files (no matter their
folder ), and *Document Types* filters on files of the selected
document type. Next to the filters is *Search*, which enables you to find files
by keyword.

Just above the file list, the *Order by* selector lets you select criteria for
ordering the files and folders. You can order them by creation date, title,
number of downloads, modification date (default), or size. The up and down
arrows let you arrange them in ascending or descending order.

Documents and Media lets you act on one or more files at once. When you select
the *All* checkbox, next to the *Order by* selector, the following action icons
appear: ![Action Icons](../../images/dm-action-icons.png). Describing them from
left to right, the X is for cancelling file checkout, the secured lock is for
file check-in, the unsecured lock is for file check-out, the crossed arrows are
for moving the files, and the trash can is for moving files to the Recycle Bin.
File check-out and check-in is explained later in *Collaborating on Files*.

![Figure x: A file's entry view lets you act on the file, preview it, and inspect its details. If you've installed an appropriate preview plugin for a file, its perview image displays in the preview area. Liferay can preview many image types, by default.](../../images/dm-file-entry-details.png)

To open its file entry view, click the file's name. In the center of this view
are the file's icon, name, author, creation date/time, and rating. You can rate
the file too. If you're Liferay installation has a plugin that lets you preview
the file type, then a preview image of the file shows below the file's core
details. The comments area is at the bottom of the screen. Here you can comment
on the file, subscribe to comments, and reply to them.

Beside the main viewing area more details are listed for the file. The top
portion of this area lists the file's version number, status, modification date
and name of user who last modifying it. Next are links to download the file, get
its URL, and WebDAV URL. Clicking on the URL or WebDAV URL links opens a field
that lets you copy the URL so you can give out when you're referencing the
document. You can specify the WebDAV URL as the file name when opening the
document from Microsoft Office. The next section lists any and all medatadata
that's been automatically extracted from the file. Depending on your file's type
and the metadata written with the file, you can find out all kinds of gritty
details. The *Version History* section lists all the different versions of the
file. It even lets you view and download specific versions.

A file's actions are listed at the top of its file entry screen. Here are the
file actions:

- **Download**: Downloads the file to your device.

- **Edit**: Lets you modify the file's name, description, document type, categorization,
and related assets. You can even upload a new file to replace it. Note, modifying the file
increments its version. 

- **Move**: Relocate the file to a different parent folder. 

- **Checkout/Checkin**: Prevents others from modifying the document while you
are working. Other users can still view the current version of the document if
they have permission. You can check the document back in when you're done working. 

- **Permissions**: Lets you specify which actions each role can perform on the
file. You can granted a role permission to perform the following actions:

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

As you can see, Documents and Media lets you manage media
how you like.

Let's review what you've done so far. First, you created a role to manage a
specific set of files for your site. You assigned users to the role and created
a Documents and Media folder for them to add and organize files. Then as a
member of the role, you added folders and files to the Documents and Media
library. And just now, you viewed individual file entry information and actions.
Next, let's consider how to display image files on our site's pages. 

Here are some ways of you can display your image files:

- Insert them in an asset, like a Web Content article or Blogs Entry
- Use the Asset Publisher
- Display them in a Media Gallery
- Show them in a Documents and Media app

The Lunar Resort media team sits down with each guest party to show them a page
filled with photos and videos of their vacation experience. The resort likes to
show them a slideshow to get some oohs and ahh's from the guests and determine
their favorites. Then they offer to print the photos, frame them, and print them
on all kinds of fun items like keychains, coffee mugs, and tee-shirts. They'll
even burn the media onto a DVD for the party. Let's explore how the media team
displays guest media. 

The media team creates a page for each guest party. They can customize each page
to focus on and show off the party's media. Each guest's media page starts with a
Documents and Media app and has a Media Gallery app below it. The Documents and
Media app shows thumbnail images of all the files and makes them individually
accessible for download. The Media Gallery comes with slideshow capabilities and
lets you download a folder's files as an archive. Let's create a media page for
the Smith's and add these apps to it. 

Add a one-column page named *Smith Party*. Then, add the *Documents and Media*
application to the page. It's in the *Content Management* section of the
*Applications* menu. The Documents and Media app shows your *Home* folder's
files by default. The app lets you browse, add, and act on files just as you do
in Site Administration. Since we want to focus on the Smith's files, we should
configure the app to only show files from the Smith's folder. Click the
Documents and Media app's Options icon
(![Options](../../images/app-options-icon.png)) and select *Configuration*. The
Configuration window appears and shows the *Setup* tab's sub-tab *Display
Settings*. 

The Display Settings tab has these sections: Display Settings, Folders Listing,
Entries Listing for List Display Style, and Ratings. The Display Settings
section provides checkboxes to enable showing each file's actions, showing
search, and displaying the file filters. File search is enabled by default. The
settings also let you set the maximum number of file entries to display. And
lastly, you can select display styles (e.g., Icon, Descriptive, and List) to
make available to the app's users. The Icon display style is selected by
default. The Folders Listing section lets you select a Documents and Media
library folder to serve as the root folder rom which to display the library's
files

The last two Display Settings tab's sections are the Entries Listing for List
Display Style section and Ratings section. The former section allows you to
select fields to show for each file if the app's user selects the List display
style. The Ratings section lets you control whether ratings and comments are
enabled for use in the app.

Here are descriptions for the other Documents and Media Configuration tabs:

**Permissions:** shows Liferay's permissions dialog for the application.

**Communication:** lists public render parameters the application publishes to
other applications on the page. Other applications can read these and take
actions based on them. For each shared parameter, you can specify whether to
allow communication using the parameter and select which incoming parameter can
populate it. 

**Sharing:** lets you embed the application instance as a widget on on any
website, Facebook, or Netvibes, or as an OpenSocial Gadget.

**Scope:** lets you specify the instance the application displays: the current
site's instance (default), global instance, or page instance. If the page
doesn't already have an instance of the app, you can select scope option *+
\[Page Name\] \(Create New\)* to create a page-scoped instance for the
application to display. 

While we're in the Configuration window there are a couple things we should do.
First select the *Display Settings* tab. 
To enable the user to act on the files, select the *Show Actions* checkbox.
Since we only want to show the Smith's files, click *Select* to browse and
choose a the *Smith, John and Jane* folder. To save your settings, click *Save*.
The Documents and Media app now shows all of the Smith's files. This app enables
the Lunar Resort media team to act on individual files. If Mrs. Smith likes 
particular files, the staff member can download them locally and add notes about
the file in the file entry's comments. 

Next, add the Media Gallery app below the Documents and Media app. By default,
it displays the Documents and Media library's home folder's files. Bring up the
Media Gallery's Configuration window. The Media Gallery's settings are very
similar to the Documents and Media app's settings. There are a couple
differences. The Media Gallery focuses only on image and video file types. And
it also is able to use Display Templates. You can create your own Display
Templates for the Media Gallery. The Media Gallery also has configuration tabs
for communication, permissions, preferences, and scope. In the app's Display
Settings, select checkboxes *Show Actions* and *Show Folder Menu*. In the
*Folder Settings* section, select the *Smith, John and Jane* folder as the app's
root folder and click *Save*. 

The Media Gallery also shows the Smith's pictures. Select any picture to bring
up a thumbnail slideshow of the folder's pictures. 

TODO - More coming


## Collaborating on Files

TODO - Things to do or include:

- Use the DM app

- Show Subscription email configuration (collab)

- Demonstrate aspects of the File menu - Download, Edit, Checkout, Checkin, major/minor versions (collab)

- Access DM files from Desktop. Explain this compares with checkout checkin.


