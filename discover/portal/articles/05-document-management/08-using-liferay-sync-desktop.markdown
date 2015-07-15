# Using Liferay Sync on Your Desktop [](id=using-liferay-sync-on-your-desktop)

Liferay Sync keeps files from your Liferay sites synchronized with your desktop 
devices. This lets you work with your files without using a browser. It also 
ensures that you're always up to date with the latest changes made by other 
users. Using Liferay Sync requires that you install the Sync client on your 
desktop. Sync is currently available for Windows and Mac OS desktops. The Sync 
desktop client stores files locally so that they're always available, even when 
you're offline. Files are automatically synchronized upon reconnection. 

On your desktop devices, Liferay Sync creates a new folder structure used for
synchronizing files. You can treat the files there the same as any ordinary 
file. Credentials, sync folder location, and other options are configured in the 
client. Also, native desktop notification events keep you informed of what Sync 
is doing. Native menu and task bar integration keep Sync controls within easy 
reach. 

This guide walks you through setting up and using the Liferay Sync client 
on your desktop. Before proceeding, check with your portal or site administrator 
to ensure that Sync is enabled for your sites. Once this is done, you'll then 
need to install the Sync client on your desktop device. 

## Installing the Desktop Liferay Sync Client [](id=installing-the-desktop-liferay-sync-client)

The installation steps for Liferay Sync on Windows and Mac OS differ only 
slightly. Regardless of platform, you need administrator privileges on your 
machine. You can download the desktop client from the 
[Liferay Sync downloads page](https://www.liferay.com/downloads/liferay-sync). 
Upon launching the Windows application installer, you're prompted to choose an 
install location. Select an appropriate location and click *Install*. Sync 
automatically starts when the installation finishes. The first time you run 
Sync, you need to configure it to connect and sync with your portal. The 
configuration steps are shown below, after the Mac installation instructions. 

Liferay Sync for Mac is packaged in a DMG file. Double-clicking on a DMG mounts
it as a disk image and opens a window showing the image's contents. To install 
Sync, drag the Liferay Sync icon to the Applications folder. Once it's 
installed, run it from your Applications folder. When you first run Sync for 
Mac, you're prompted for administrator credentials for your machine so that the 
Finder icon/context menu tool can be installed. This prompt only appears when 
the tool is installed or upgraded.

![Figure 5.15: Drag the Liferay Sync icon to the Applications folder.](../../images/sync-mac-install.png)

Now that you've installed sync, you're ready to configure it! The configuration 
steps for Sync on Windows and Mac are identical. First, enter your portal's
address along with your account credentials. Click *Sign In* when you're
finished. 

Next, Sync offers you a warm greeting and shows you the list of sites in your 
portal that you can sync with. If there's a long list of sites that you don't 
want to scroll through, or you don't see the site you want to sync with, you can 
search for sites in the *Search* bar above the site list. Check the checkbox for 
each site you want to sync with and then click *Proceed*. 

You now need to specify the local folder that you want to sync with your portal.
This folder is created for you if it doesn't exist. By default, this folder is
in your user documents folder and is named after your portal. For example, since
the portal in the screenshots below is running locally, the Sync folder is named
*localhost*. You can also see that this folder is located in the machine's user
documents directory. Of course, you're free to place this folder anywhere and
call it anything you want. Click *Start Syncing* when you're ready to begin
syncing files. 

![Figure 5.16: The first time you run Liferay Sync, you need to tell it how to communicate with your Liferay server.](../../images/sync-setup-01.png)

![Figure 5.17: Select the sites you want to sync with and then click *Proceed*.](../../images/sync-setup-02.png)

![Figure 5.18: Specify your local Sync folder's name and location.](../../images/sync-setup-03.png)

![Figure 5.19: Congratulations, you've successfully set up Liferay Sync!](../../images/sync-setup-04.png)

Sync congratulates you on setting it up and begins to sync files from the sites 
you selected, in the local folder you specified. Note that completing the 
initial sync may take a significant amount of time depending on the amount of 
data being transferred. You can safely close the window, and syncing will
continue in the background. To view the local sync folder, click *Open Folder*.

## Viewing Your Sync Status [](id=viewing-your-sync-status)

Once the Liferay Sync desktop client is installed, an icon appears in your 
task bar (Windows) or menu bar (Mac) whenever it's running. Clicking this icon 
opens a menu that shows the portals you're synced with, links to preferences and 
help, and the option to quit Sync. Mousing over a portal extends the menu to 
show additional options for that portal. The following screenshots show this 
menu in Windows.

![Figure 5.20: The Sync desktop menu options are always available from the task bar.](../../images/sync-taskbar-01.png)

![Figure 5.21: Each portal also has its own set of options in the task bar menu.](../../images/sync-taskbar-02.png)

The menu for each portal first shows the sync status. The sync status shows 
*Synced* when synchronization is complete. Mousing over *Open Sync Folder* 
shows a list of sites that you're synced with. Clicking on a site opens your 
local Liferay Sync folder for that site in your native file manager. Similarly, 
mousing over *View Website* provides a list of links to the site pages 
containing the Documents and Media portlets that you have permission to access. 
Lastly, *Recent Files* shows a list of recently created or modified files in all 
the repositories you can access.

Next, you'll learn how to use Sync's preferences to control how Sync behaves.

## Using Sync Preferences [](id=using-sync-preferences)

You can use Sync's preferences to add or remove portals to sync with, edit the 
settings used to connect to your portals, and control the basic behavior of 
Sync. Open Sync's preferences by clicking the Sync icon in the task bar 
(Windows) or menu bar (Mac OS) and selecting *Preferences*. You're first shown 
the Preferences menu's *Accounts* tab.

![Figure 5.22: The Preferences menu's *Accounts* tab.](../../images/sync-preferences-accounts-01.png)

The accounts and portals that you sync with are listed on the left. The plus, 
minus, and pencil icons at the bottom of the account list can be used to 
respectively add, delete, or edit an account. You should use caution when 
deleting an account from your Sync client, as doing so also deletes any local 
files and folders for that account. When an account is selected in the 
Preferences UI, the sites you have permission to sync with are shown on the 
right. These sites are further broken down into *Selected Sites* and 
*Unselected Sites*. The sites you currently sync with are shown under 
*Selected Sites*. Other sites available for syncing are shown under 
*Unselected Sites*. To select a site to sync, click the *Select Sites* 
button under the site list. In the menu that appears, check the checkbox for the 
sites you want and then click *Confirm*. Use caution when de-selecting sites.
De-selecting a site deletes its folder on your machine. Below the *Select Sites*
button, the local sync folder location for the selected account is shown. Click
the *Change* button to change this folder's location.

The Preferences menu's *General* tab contains three settings for the Sync 
client's general behavior. To start Sync automatically each time your machine 
starts, check the checkbox next to *Launch Liferay Sync on startup*. To show a 
small notification in the corner of your screen when a synced file changes, 
check the checkbox next to *Show Desktop Notifications*. To automatically check 
for new client versions, check the checkbox next to 
*Automatically check for updates*. You can also check for updates manually by 
clicking the *Check Now* button. 

![Figure 5.23: The Preferences menu's *General* tab contains settings for Sync's general behavior.](../../images/sync-preferences-general-01.png)

Now that you have a handle on configuring Sync, it's time to see how it works 
with the files in your Sync folder.

## Using Your Local Sync Folder [](id=using-your-local-sync-folder)

Once Sync is configured and running, any files you add or modify in your Sync 
folder are automatically uploaded to your portal. File changes by other users 
are also downloaded to your Sync folder. You should note that if you delete a 
file in your Sync folder, it's also deleted from the portal and other clients. 
You should therefore use **extreme caution** when deleting files in your Sync 
folder. If you accidentally delete a file, not all is lost! The file can still 
be recovered from the portal's Recycle Bin, which is enabled by default. Note 
that if the portal or site administrator has disabled the Recycle Bin, 
recovering the file isn't possible.

+$$$

**Warning:** Deleting a file in your Sync folder also deletes it in the 
portal and in other clients. If you accidentally delete a file, it can be 
recovered from the portal's Recycle Bin. The Recycle Bin is enabled by default. 
However, file recovery isn't possible if the portal or site administrator has 
disabled the Recycle Bin.

$$$

You can run through the following exercise to familiarize yourself with how to 
create, edit, download, and upload files with Sync. First, open your Sync folder 
in your file manager and create a new file called `README.txt`. Enter the word 
`test` in this file. Next, check that you can access this file in your Liferay 
site. Make sure that that you're on the site you want to sync with and then 
navigate to its Documents and Media portlet. You should see your `README.txt` 
file listed there.

Next, download the file to a convenient location on your machine. The simplest 
way to do this is to hover your mouse over the file's icon, click the small
triangle icon on the file's top right corner, and click *Download*. Open the
file and check that it still says `test`. Now open the `README.txt` file in your
Sync folder and edit it so that it says `second test`. Once the changes are
synced, go back to your browser and refresh the page with your Documents and
Media portlet. Click on the *README.txt* icon, look at the information displayed
to the right, and you should see that the file's version number has been
incremented.

![Figure 5.24: Updating a file through Liferay Sync increments the file's version number. You can view a file's version number through the web interface.](../../images/sync-file-edit-01.png)

If you download and open the file again, it now says `second test`. Your edit 
was uploaded to the server! You can be confident that this edit was also 
downloaded by all other Sync clients connected to your site. 

Now delete the file from your local Sync folder. When the changes finish 
syncing, go back to your browser and refresh the page with your Documents and 
Media portlet. The file is now gone! The file is also deleted from the local 
Sync folders of all other Sync clients connected to the site. It's very, very 
important to remember this rule of thumb when you're working with files in your 
local Sync folder: deleting files there deletes them *everywhere*! Next, you'll
learn how to use the Sync client for your mobile device. 
