# Using Liferay Sync on Your Desktop [](id=using-liferay-sync-on-your-desktop)

Liferay Sync synchronizes files between your Liferay sites and desktop devices.
It lets you work with your files without using a browser. The client also
ensures that the files are updated with the latest changes made by other users.
To use Liferay Sync in your desktop environment, you must install the Sync
desktop client. It's currently available for Windows and Mac OS. The Sync client
stores files locally so that they're always available, even when you're offline.
Files are automatically synchronized upon your client's reconnection with your 
Liferay instance. 

On your desktop devices, Liferay Sync creates a new folder structure in which to
synchronize files. You can treat the files the same as you do any ordinary file.
Credentials, Sync folder location, and other options are configured in the
client. Also, native desktop notification events inform you of what Sync is
doing. The native menu and task bar integration keep Sync controls within easy
reach. 

This guide walks you through setting up and using the Liferay Sync client on
your desktop. Before proceeding, check with your Liferay instance or site 
administrator to ensure that Sync is enabled for your sites. You then need to 
install the Sync client on your desktop device. The next section walks you 
through installing the client. 

## Installing the Desktop Liferay Sync Client [](id=installing-the-desktop-liferay-sync-client)

You can download the desktop client from the
[Liferay Sync downloads page](https://www.liferay.com/downloads/liferay-sync).
Installing Liferay Sync on Windows and Mac OS is straightforward. 

To install the Liferay Sync client on Windows, you must have administrator
privileges. Upon launching the Windows application installer, you're prompted
to choose an install location. Select an appropriate location and click
*Install*. Sync automatically starts after the installation finishes. The first
time Sync runs, you need to configure it to connect and sync with your Liferay 
instance. The configuration steps are shown below, after the Mac installation
instructions. 

+$$$

**Note:** You can upgrade previous versions of the desktop Liferay Sync client
to version 3.0. When doing so, however, you need to set up your account again in
the new version of the client. Prior to upgrading, it's typically best to shut 
down Liferay Sync, backup files from your local Sync folder, and delete that 
folder. 

$$$

The Liferay Sync client for Mac is packaged in a DMG file. Double-clicking on
the DMG file mounts it as a disk image and opens a window showing the image's
contents. To install Sync, drag the Liferay Sync icon to your Applications
folder. Once it's installed, run it from the Applications folder. If you're
running Mac OS X 10.9 or lower, you're prompted for your machine's administrator
credentials to install the Finder icon/context menu tool. This prompt only
appears when installing or upgrading the tool. 

![Figure 1: Drag the Liferay Sync icon to the Applications folder.](../../../images/sync-mac-install.png)

Now that you've installed sync, you're ready to configure it! The configuration 
steps for Sync on Windows and Mac are identical. First, enter your Liferay 
instance's address along with your account credentials. Click *Sign In* when 
you're finished. 

Sync offers you a warm greeting and lists the sites in your Liferay instance 
that you can sync with. You can search for a site in the *Search* bar above the 
site list. Check the checkbox for each site you want to sync with and then click 
*Proceed*. 

You must now specify a Sync folder with which your Liferay instance will sync. 
Sync creates this folder and it must not conflict with any existing local 
folder. The Sync folder's default name is the instance's host name and its 
default location is the user's documents folder. For example, since the instance 
in the screenshots below runs locally at the address `http://localhost:8080/`, 
Sync creates a Sync folder named *localhost* in the user's documents folder. You 
can, of course, specify any unique name and location you like for Sync to use 
for the Sync folder it creates. Click *Start Syncing* to begin syncing files. 

![Figure 2: The first time you run Liferay Sync, you need to tell it how to communicate with your Liferay server.](../../../images/sync-setup-01.png)

![Figure 3: Select the sites you want to sync with and then click *Proceed*.](../../../images/sync-setup-02.png)

![Figure 4: Specify your local Sync folder's name and location.](../../../images/sync-setup-03.png)

![Figure 5: Congratulations, you've successfully set up Liferay Sync!](../../../images/sync-setup-04.png)

Sync congratulates you on setting it up and begins to sync files from the sites
you selected to your local Sync folder. Note, completing the initial
synchronization may take a significant amount of time, depending on the amount
of data being transferred. You can safely close the window as syncing continues
in the background. To view the local Sync folder, click *Open Folder*. 

## Viewing Your Sync Status [](id=viewing-your-sync-status)

When Sync is running its icon appears in your task bar (Windows) or menu bar
(Mac). Clicking this icon opens a menu lists the Liferay instances you sync with 
and displays options for preferences, help, and quitting Sync. Mousing over an
instance's name extends the menu to show additional options for that instance. 
The following screenshots show this menu in Windows. 

![Figure 6: The Sync desktop menu options are always available from the task bar.](../../../images/sync-taskbar-01.png)

![Figure 7: Each Liferay instance also has its own set of options in the task bar menu.](../../../images/sync-taskbar-02.png)

The menu for each instance first shows the sync status. The sync status shows
*Synced* if you're completely synchronized with the instance. Mousing over *Open
Sync Folder* shows a list of sites that you sync with. Clicking on a site opens
your local Liferay Sync folder for that site in your native file manager.
Similarly, mousing over *View Website* provides a list of links to the site
pages containing the Documents and Media portlets that you have permission to
access. Lastly, *Recent Files* lists recently created and modified files in all
the repositories you can access. 

Next, you'll learn how to use Sync's preferences to control Sync's behavior.

## Using Sync Preferences [](id=using-sync-preferences)

You can use Sync's preferences to add/remove Liferay instances to sync with, 
edit instance connection settings, and control Sync's basic behavior. Open 
Sync's preferences by clicking the Sync icon in the task bar (Windows) or menu 
bar (Mac OS) and selecting *Preferences*. A preference screen for your instance 
accounts displays. 

![Figure 8: The Preferences menu's *Accounts* tab lets you manage syncing with sites per account.](../../../images/sync-preferences-accounts-01.png)

The accounts and instances that you sync with are listed on the left. The plus,
minus, and pencil icons at the bottom of the account list can be used to
respectively add, delete, or edit an account. You should use caution when
deleting an account from your Sync client, as doing so also deletes any local
files and folders for that account. When you select an account in the
Preferences screen, the sites you have permission to sync with are shown on the
right. These sites are further broken down into *Selected Sites* and *Unselected
Sites*. The sites you currently sync with are shown under *Selected Sites*.
Other sites available for syncing are shown under *Unselected Sites*. To select
a site to sync, click the *Select Sites* button under the site list. In the menu
that appears, check the checkbox for the sites you want and then click
*Confirm*. Use caution when de-selecting sites. De-selecting a site deletes its
folder on your machine. Below the *Select Sites* button, the local Sync folder
location for the selected account is shown. Click the *Change* button to change
this folder's location. 

The Preferences menu's *General* tab contains three settings for the Sync
client's general behavior. To start Sync automatically each time your machine
starts, check the checkbox next to *Launch Liferay Sync on startup*. To show a
small notification in the corner of your screen when a synced file changes,
check the checkbox next to *Show desktop notifications*. To automatically check
for new client versions, check the checkbox next to *Automatically check for
updates*. You can also check for updates manually by clicking the *Check Now*
button. 

![Figure 9: The Preferences menu's *General* tab contains settings for Sync's general behavior.](../../../images/sync-preferences-general-01.png)

Now that you have a handle on configuring Sync, it's time to see how it works 
with the files in your Sync folder.

## Using Your Local Sync Folder [](id=using-your-local-sync-folder)

Once you configure and run Sync, Sync automatically uploads to your Liferay 
instance any files you add or modify in your Sync folder. Sync also downloads to 
your Sync folder any file changes by other users. If you delete a file in your 
Sync folder, Sync also deletes it from the instance and other clients. You 
should therefore use **extreme caution** when deleting files in your Sync 
folder. If you accidentally delete a file though, not all is lost! The file can 
still be recovered from the instance's Recycle Bin, which is enabled by default. 
Note, if the instance or site administrator has disabled the Recycle Bin, 
recovering deleted files is impossible. 

+$$$

**Warning:** Deleting a file in your Sync folder also deletes it in the Liferay 
instance and in other clients. If you accidentally delete a file, it can be 
recovered from the instance's Recycle Bin. The Recycle Bin is enabled by 
default. File recovery is, however, impossible if the instance or site 
administrator has disabled the Recycle Bin. 

$$$

You can run through the following exercise to familiarize yourself with how to 
create, edit, download, and upload files with Sync. First, open your Sync folder 
in your file manager and create a new file called `README.txt`. Enter the word 
`test` in this file. Next, make sure you can access this file in your Liferay 
site. Go to the site you want to sync with and navigate to its Documents 
and Media portlet. It lists your `README.txt` file.

Download the `README.txt` file to a convenient location on your machine. Open 
the file and check that it still says `test`. Now open the `README.txt` file in 
your Sync folder and edit it so that it says `second test`. Once the changes are 
synced, go back to your browser and refresh the page with your Documents and 
Media portlet. Click on the *README.txt* file's name, look at the file 
information displayed, and check that the file's version number has been 
incremented. 

![Figure 10: Updating a file through Liferay Sync increments the file's version number. You can view a file's version number through the web interface.](../../../images/sync-file-edit-01.png)

If you download and open the `README.txt` file again, it now says `second test`.
Your edit was uploaded to the site! You can be confident that this edit was also
downloaded by all other Sync clients connected to your site. 

Now delete the `README.txt` file from your local Sync folder. When the changes
finish syncing, go back to your browser and refresh the page containing your
Documents and Media portlet. The file is gone! The file is also deleted from the
local Sync folders of all other Sync clients connected to the site. Remember
this very important rule: deleting files in your local Sync folder deletes them
*everywhere*! Next, you'll learn how to use the Sync client for your mobile
device. 
