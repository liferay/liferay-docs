# Liferay Sync [](id=liferay-sync)

Liferay Sync is an add-on product for Liferay 6.1 GA2 CE and EE, and later
versions, that synchronizes files between your Liferay server and users' desktop
and mobile environments. With Liferay Sync, your users can publish and access
shared documents and files from their native environments without using a
browser. Windows and Mac OS desktops and Android and iOS-based mobile platforms
are currently supported, while Linux users have an unsupported beta they can
use. As users add and collaborate on documents and files, Liferay Sync
automatically synchronizes them across all configured Sync clients. Liferay Sync
is fully integrated into the Liferay Platform so that features such as
authentication, versioning, and social collaboration function in the supported
environments. Liferay Sync stores files locally so that they're always
available, even when you're offline. It automatically synchronizes your files
upon reconnection.

## How does it work? [](id=how-does-it-work)

Liferay Sync manages documents and site information through Liferay's built-in
web services. Clients securely communicate to Liferay using user-supplied
credentials such that each users can only access those documents and sites for
which they have permission. Changes made through Liferay Sync are immediately
available to the rest of the Liferay Platform, including users accessing Liferay
through traditional web-based interfaces.

For desktop environments, a new folder structure is created and used for
synchronizing files. Files found therein can be treated as any ordinary file.
Credentials, sync frequency, and other options can be configured in-client.
Native desktop notification events keep you abreast of what Sync is doing, and
native menu and taskbar integration keep Sync controls within easy reach.

Mobile environments are naturally dependent on the way in which documents are
handled. For Android and iOS, documents are maintained in a file list, and can
be viewed by clicking on the files themselves. External files accessible from
other apps can be "opened" using Liferay Sync, thereby dropping them into your
Sync folder and synchronizing them across other Sync clients. In iOS devices,
"pulling down" on the Sync file list forces a refresh. In Android, click on the
*Refresh* icon within the menu.

Liferay Sync is designed to work with both Liferay 6.1 GA2 CE and EE, and later
versions. Using Sync with Liferay CE limits users to syncing one site.  Using
Sync with Liferay EE enables users to synchronize documents and files across all
the sites they can access.

Liferay Sync is also designed to work with Liferay Social Office. You can sync
one site from Social Office CE as well as one site from Liferay Portal CE. If
you've installed Social Office CE on Liferay Portal EE, then you can sync any
site from Portal, but only one from Social Office. If you've installed Social
Office EE on Liferay Portal EE, then you can sync any and all sites.

## Installing Liferay Sync [](id=installing-liferay-sync)

For Windows or Mac OS, visit the Liferay Sync product page [Liferay Sync Product
Page](http://www.liferay.com/products/liferay-sync/features), and click *Get it
Now* (on the right-side navigation menu) to download the client application for
your desktop environment. For Windows, the client application installer should
be named `liferay-sync-<version>-<date>.exe`. For Mac OS, it should be
`liferay-sync-<version>-<date>.dmg`. There is also a Linux beta version
available. Follow the on-screen instructions of the installer wizard to
configure your client to connect to an existing Liferay 6.2 deployment using
your Liferay credentials.

Prior to Liferay 6.2, Liferay Portal's Documents and Media services contained
all the logic used by Liferay Sync. As of Liferay 6.2, Sync processing has been
removed from the portal and been placed in a plugin. Make sure that the Sync
plugin has been installed on your Liferay server before trying to connect to
your portal via a Liferay Sync client. The Sync plugin's name is *sync-web* and
is installed by default in Liferay Portal bundles. The Sync plugin is also
available on Liferay Marketplace and can be downloaded and installed on your
Liferay server just like any other Marketplace app. To find the app on
Marketplace, search for *Sync CE* or *Sync EE*, depending on your portal
version. To receive new features, optimizations, and bug fixes, make sure to the
update your Sync app whenever a new version is available.

### Windows [](id=windows)

Upon launching the Windows application installer, you'll be prompted to choose
an installation location for Liferay Sync. Browse to an appropriate location on
your machine and click *Install*. After the installation is complete, you'll
need to complete the Liferay Sync setup. Leave the *Run Liferay Sync* button
checked to automatically start Liferay Sync after you click *Finish*.

The first time you run Liferay Sync, you'll have to enter some account
information. Sync needs to know where you'd like to locally store the files it's
supposed to sync with your Liferay server. And, of course, it needs to know your
server's URL and the account credentials with which it should authenticate.

![Figure 5.14: The first time you run Liferay Sync, you'll have to tell it how to communicate with your Liferay server.](../../images/liferay-sync-new-account-info.png)

The options for the Mac OS application installer are similar.

### Mac OS [](id=mac-os)

Liferay Sync for Mac is packaged in a DMG file. Double-clicking on a DMG mounts
it as a disk image, and opens a window showing the contents of the image. To
install Sync, drag the Liferay Sync icon to the Applications folder. Once it's
installed, go to your Applications folder to run it.

![Figure 5.15: Drag the Liferay Sync icon to the Applications folder.](../../images/liferay-sync-mac-install.png)

When you launch Liferay Sync, the first thing you need to do is provide it with
the URL for the Liferay server that you'll be using Sync with, along with your
Liferay credentials. After that, you'll need to run through the brief setup
process that was described above for Windows.

Once you've finished your configuration and have clicked *OK*, Liferay Sync
starts running in the background, and an icon appears in your top menu bar. If
you wish to change any of your settings, click the icon to open the Liferay Sync
menu and click on *Preferences*. Note that on Windows, the Sync menu says
*Properties*, not *Preferences*.

### Mobile [](id=mobile)

For iOS, visit the App Store, search for Liferay, and install the Liferay Sync
App.

For Android, go to Google Play, search for Liferay, and install the Liferay Sync
App.

Once the mobile apps are installed, follow the on-screen instructions as below.

## Using Liferay Sync on the Desktop [](id=using-liferay-sync-on-the-desktop)

Once installed, you'll see a Liferay Sync icon in your taskbar whenever it's
running. A green checkmark means Liferay Sync has a working connection to your
Liferay server and is updating the files in your Sync folder according to the
interval you specified in the wizard. Click the Liferay Sync icon in your
taskbar to bring up the menu. The options availabe from the menu are listed
below:

<!-- Check the list below: Some menu items may have changed for Sync Desktop
1.1.1. -->

*Open Sync Folder* opens your Liferay Sync folder in your native file manager.

*Open Website* provides links to the pages containing the Documents and Media
portlets which you have permission to access. By default, you can find links to
your personal Documents and Media repository as well as links to the Documents
and Media repositories of all the other sites you belong to. 

+$$$

Note for administrators: If you don't have a
Documents and Media portlet anywhere on a site that's been selected for syncing,
you'll have to add the portlet. Otherwise, users will get a *The requested
resource was not found* error when they try to use the *Open Website* link from
their Sync menus.

$$$

*Recent Files* shows a list of recently created or modified files from all the
repositories you can access.

*Properties* (*Preferences*, on Mac OS) lets you change properties like starting
on login, desktop notifications, and sync frequency. It also allows you to edit
the account information you provided when you started Sync for the first time.
For example, you can enter a new URL for your Liferay server and enter a
different set of Liferay credentials.

![Figure 5.16: Open the Liferay Sync menu and select *Properties* (*Preferences*, on Mac OS) to edit the settings you configured during setup.](../../images/liferay-sync-properties.png)

There are three items listed in the *General Settings* section. *Start Liferay
Sync on Login* is checked by default. If you don't want Sync to start
automatically, uncheck this. *Show Desktop Notifications* is also checked by
default. Unless you uncheck this, when a file that you have synced is changed, a
small notification will appear in the corner of your screen. The *Check Server
For Updates Every:* field enables you to set how frequently it will check to see
if anything has changed. This can be set anywhere between 5 seconds and 30
minutes.

Click the *Edit Settings* button in the *Account Settings* section to specify
your server's URL and enter your Liferay credentials. Use the *Test Connection*
button to make sure Liferay Sync can communicate with the server. Editing your
settings also allows you to specify your Sync folder, the folder where Sync will
store files on your machine. By default, files are stored in the *liferay-sync*
subfolder of your personal Documents folder.

Finally, the *Site Settings* section allows you to choose which sites you wish
to sync media from. By default, it will list all of the sites that you are a
member of, but you can uncheck any of those sites if you don't want to sync
those files.

*About* displays Liferay Sync version information, copyright information, and a
link to Liferay's home page.

*Check for Updates* checks to see if a new version of Liferay Sync is available
from [liferay.com](liferay.com) and allows you to set whether or not Liferay
Sync should automatically check for updates.

### Using your Sync folder [](id=using-your-sync-folder)

Once Liferay Sync has been configured and is running, any files you add to or
modify in your Sync folder are automatically detected and uploaded to your
Liferay server. Also, changes from other users are downloaded to your Sync
folder.

If you delete a file your sync folder, it will only be deleted locally. It will
not be deleted on the server. This mechanism prevents users from accidentally
deleting shared files. When you delete a file from you Sync folder, Sync will no
longer download changes to this file the next time it syncs. If you want to go
back to syncing a file, simply restore it from you recycle bin or trash can.
Once the file is restored back to the Sync folder, Sync keeps that file in sync
by uploading any of your changes and downloading any changes from the server.

You can run through the following exercise to familiarize yourself with how to
create, edit, download, and upload files with Liferay Sync. First, open your
Liferay Sync folder in your file manager (use the *Open Sync Folder* option of
the Liferay Sync menu from the taskbar), and create a new file called
`README.txt`. Edit this file and enter the word *test*. Next, check that you can
access this file from your Liferay site. Open your browser, navigate to your
Liferay site, and sign in with your Liferay account credentials. First, make
sure that that you're on the site you want to sync with. Then navigate to *Site
Administration* &rarr; *Documents and Media*. You should see your `README.txt`
file listed there.

Download the file (click the small triangle icon at the top right corner of the
*README.txt* icon and select *Download*) to a convenient location on your
machine and check that it still says *test*. Now open the `README.txt` file in
your Sync folder and edit it so that it says *second test*. Once the changes are 
synced go back to your browser and refresh your Documents and Media page. Click 
on the *README.txt* icon, look at the information displayed to the right, and 
you'll see that its version number has incremented.

![Figure 5.17: Updating a file through Liferay Sync increments the file's version number. You can view a file's version number through the web interface.](../../images/liferay-sync-README.png)

You'll see that it now says *second test*--your edit was uploaded to the server.
You can be confident that this edit was also downloaded by all other Liferay
Sync clients connected to your site. 

### Demonstrating Liferay Sync Permissions [](id=demonstrating-liferay-sync-permissions)

Liferay Sync uses the default Liferay permissions to determine which files and
folders are synced to the user's machine. This means that whatever files a user
can access from a certain site are the ones that will be pulled down by Liferay
Sync if that site is selected in the Sync client. You can test the functionality
of Liferay Sync permissions by performing the following steps. First, create a
new file on your desktop called *secret.txt*. Enter the text *classified
information* into this file. Then use your browser to log into Liferay and
create a new user called *secretagent* with the email address
*secretagent@liferay.com* and the password *test*. Also, create a new private
site called *Secret Site*. Then assign the *secretagent* user to the *Secret
Site* and grant the *Site Administrator* role to this user. There will be no
other members of this site unless they are assigned by an administrator. Log in
as the *secretagent* and select *Secret Site* from the *My Sites* tab on the
Dockbar. Then click on *Documents and Media* and upload the *secret.txt*
document.

Next, we'll configure our Liferay Sync client to log in with the *secretagent*
user's credentials and access the *Secret Site*. Open the Liferay Sync menu from
the taskbar and select *Properties*. Click on the *Edit Settings* button, choose
a new Sync folder, enter your server's URL, and enter the secret agent's
credentials: *secretagent@liferay.com* and *test*. Lastly, uncheck all Liferay
sites except the *Secret Site*, and click *OK*. Confirm that the *secret.txt*
file that you uploaded to the *Secret Site*, is downloaded to your new Sync
folder. Open it and check that it says *classified information*. If you
reconfigure your Sync client connect to your Liferay instance using the
credentials of another user who doesn't belong to the *Secret Site*, the
*secret.txt* will not be downloaded. Congratulations! You've successfully set up
a Liferay Sync folder that can only be accessed by the *secretagent* user and
your administrators.

## Using Liferay Sync Mobile [](id=using-liferay-sync-mobile)

Once you've installed Liferay Sync on your Android or iOS mobile environment,
you'll be able to access the same functionality that's available when using Sync
on a desktop environment. However, the interface differs from that of the Sync
desktop clients. Let's discuss the usage of Liferay Sync Mobile for Android
first.

### Android [](id=android)

After installing Liferay Sync for Android, an empty screen appears asking you to
set up the app. This screen appears whenever preferences are missing. Touch the
screen and it displays the *Settings* view. You can always go back to *Settings*
by clicking on the wrench icon at the top right corner of the screen.

![Figure 5.18: Click the wrench icon to access your Liferay server credentials.](../../images/liferay-sync-android-settings.png)

Enter your Liferay server credentials by filling in your *Login*, *Password*,
and *Server* information. Your *Login* is either your user account's email
address or screen name. Use the same credentials you use to log in to the portal
in a browser. In the *Server* field, enter your portal's URL. In this example,
the server URL is *http://www.liferay.com*. Click the key icon on the top right
to test your connection and check if everything is correct.

Note for Gingerbread users: If you can't see some of the features described
here, click on the menu button to view a list of all possible actions. This
includes options to refresh, open the settings menu, upload files, take photos,
test your connection, etc. After you have successfully tested your connection,
hit the *back* button and you'll see a list of Liferay sites you have access to.

![Figure 5.19: Sites](../../images/liferay-sync-android-sites.png)

You can browse the files of a site by tapping on any of them. This opens a list
of the folders and files belonging to the site that you have permission to view.
From here, you can click on a folder and browse deeper into the folder hierarchy
or click the *Back* button to navigate back to parent folders up to the initial
*Sites* list.

Single-tap on a file to open it. If the file has never been downloaded before,
Sync will download it and open after it has finished downloading. You can only
view the file's contents if your device has an app installed that can open the
file type. For example, in order to open a PDF, you must have at least one PDF
viewer app installed. Otherwise, you will see a message informing you that no
viewer is available and you need to install an app that can open the file.

Long-press on any folder or file to find a list of actions you can take on it:
*Add to Favorites*, *View Details*, *Download*, *Rename* or *Delete*. This
actions menu varies depending on which entry type is selected: file or folder. 

On Gingerbread, the actions menu appears in the middle of the screen. On Ice
Cream Sandwich and above, you can find the action icons and menu at the top
right.

Clicking on *Add to Favorites* (Gingerbread) or the gray star (Ice Cream
Sandwich) adds the selected file to the *Favorites* list. *Favorites* are
special files that can be accessed and viewed even when you are offline (more
details below). If a file is already marked as a favorite, you'll see a
*Remove from Favorites* or blue star instead. Clicking on it removes the
selected file from the *Favorites* list.

Clicking on *View Details* (Gingerbread) or the round icon with the letter "i"
(Ice Cream Sandwich) opens the details view, which displays the entry's metadata
such as creation date, author, version, description, etc.:

![Figure 5.20: Liferay Sync's details view offers several options.](../../images/liferay-sync-android-details.png)

If you click on *Download* (floppy disk icon on Ice Cream Sandwich), it
downloads and overwrites the local file copy.

You can rename a folder or file by clicking on the *Rename* option. This renames
the entry in the portal.

Clicking on *Delete* deletes the file/folder from the remote portal, and 
other users won't be able to view or download it. On Ice Cream Sandwich and
above, you can select multiple entries for deletion.

Some actions are not related to a specific folder or file. You can find these
actions in the menu on the top action bar when no entry is selected (Gingerbread
users need to click on the device menu button). Depending on the device screen
width, some icons may overflow to the three dots button on the right. Click on
this button to see all of the available actions.

![Figure 5.21: More options are available by selecting the three dots button.](../../images/liferay-sync-android-more.png)

The *Refresh* button fetches and updates the list of folders and files that have
been changed in the portal.

The *Camera* button allows you to quickly take a picture and upload the image to
the current folder. The image file name is automatically generated with a time
stamp.

The *New Folder* button asks you for the name of the folder you want to create
in the portal.

The *Upload* button displays the types of local files you can upload to the
portal. Choosing *Image*, for example, shows all images that are stored locally
on your device. Once you choose the files and confirm, these files are uploaded
to the portal and are placed in the current folder. By default, you can upload
images, videos, and audio files. If you have installed an app on your device
that can open and browse any type of file, you will also see an option called
*Other files*.

The *Favorites* menu option opens the favorites list. All files that have been
marked as favorites show up in this list. You should mark your most important
files as favorites because, as mentioned earlier, the *Favorites* feature gives
you quick offline access to them. You can view the contents of items in the
*Favorites* list, view their metadata and, of course, remove them from the list.

Next, let's look at the iOS Sync app.

### iOS [](id=ios)

After installing Liferay Sync for iOS, an empty screen appears asking you
to set up the app. This screen appears whenever preferences are missing.

Click on *Settings* in the toolbar and enter your Liferay server credentials by
filling in your *Login*, *Password*, and *Server* information. Your *Login*
is either your user account's email address or screen name, whichever you use
to log in to the portal in a browser. In the *Server* field, enter your
portal's URL. In this example, the server URL is *http://www.liferay.com*. Click
on *Test Connection* to check if your configuration is correct.

After you have successfully tested your connection, tap on the *Documents*
toolbar section and you'll see a list of Liferay sites you have access to. You
can browse the files of a site by tapping on its name or icon. This opens a list
of the folders and files belonging to the site that you have permission to view.

![Figure 5.22: Liferay Sync offers a folder and files menu.](../../images/liferay-sync-ios-files-list.png)

From here, you can click on a folder to browse deeper into the folder hierarchy.
You can also click on the *Back* button to navigate back to parent folders up to
the initial *Sites* list.

You can refresh the list by pushing it down. This updates all the files and
folders that have been changed in the portal.

When you click on a file, this file is downloaded from the remote portal
and, if a previewer for this file type is available, you can view the contents
of the file. The next time you open a file, it won't download it again; instead,
it opens the local copy.

![Figure 5.23: Local files are stored after they are downloaded.](../../images/liferay-sync-ios-open.png)

There are 3 icons at the bottom of the screen when you open a file:

- Round icon with the letter "i" opens the details view, which displays the
entry's metadata such as creation date, author, version, description, etc.

- Star icon at the center adds the selected file to the *Favorites* list.
*Favorites* are special files that can be accessed and viewed even when you are
offline (more details below). If a file has already been marked as a favorite,
clicking on the star icon removes the file from the *Favorites* list.

- Share icon displays sharing options. You can, for example, send the file as an
email attachment, print the file, or copy it to your clipboard. Some external
apps may also appear in this list. For example, you can share your file with
social apps and messengers if they are available.f

In the file list, there's an Edit button. Clicking on it switches the app to
the edit mode as shown below:

![Figure 5.24: Edit mode is a key feature in Liferay Sync mobile.](../../images/liferay-sync-ios-edit.png)

Selecting one or more files or folders and clicking on the *Delete* button
deletes the selected files or folders from the remote portal. Once you delete
files or folders from the remote portal, other users won't be able to view or
download them.

Selecting only one file or folder enables the *Rename* button. Click on it to
change the entry's name locally and remotely.

To quickly delete a file or folder from the portal, you can also swipe right and
click on the *Delete* button in the file list view.

If you want to upload an image or video to the portal, click the *Plus* button
at the top right corner. You should see three options: 

- *Take a photo or video* opens your camera app and lets you take a photo or
record a video and upload it.

- *Choose Existing* allows you to upload multiple photos or videos stored on
your device.

- *Create New Folder* lets you type the name of the folder and creates it in the
portal.

The *Favorites* toolbar section opens the favorites list. All files that have
been marked as favorites show up in this list. You should mark your most
important files as favorites because, as mentioned earlier, the *Favorites*
feature gives you quick offline access to them. You can view the contents of
items in the *Favorites* list, view their metadata and, of course, remove them
from the list.

All downloaded files are stored on your device indefinitely. If you want to
delete downloaded files locally but don't want to remove them from the portal,
go to *Settings* and click on the *Clear Cache* button.
