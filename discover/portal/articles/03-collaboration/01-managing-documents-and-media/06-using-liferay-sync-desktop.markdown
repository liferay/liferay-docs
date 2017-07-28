# Using Liferay Sync on Your Desktop [](id=using-liferay-sync-on-your-desktop)

Liferay Sync synchronizes files between your @product@ sites and desktop 
devices. It lets you work with your files without using a browser. The Sync 
clients also ensure that the files are updated with the latest changes made by 
other users. To use Liferay Sync in your desktop environment, you must install 
the Sync desktop client. It's currently available for Windows and Mac OS. The 
Sync client stores files locally so that they're always available, even when 
you're offline. Files are automatically synchronized upon your client's 
reconnection to your @product@ instance. 

On your desktop devices, Liferay Sync creates a new folder structure that it 
uses to synchronize files. You can treat the files the same as you do any 
ordinary file. Credentials, Sync folder location, and other options are 
configured in the client. Also, native desktop notification events inform you of 
what Sync is doing. The native menu and task bar integration keep Sync controls 
within easy reach. 

This guide walks you through setting up and using the Liferay Sync client on
your desktop. Before proceeding, check with your @product@ instance or site 
administrator to ensure that Sync is enabled for your sites. You then need to 
install the Sync client on your desktop device. The next section walks you 
through installing the client. 

## Installing the Desktop Liferay Sync Client [](id=installing-the-desktop-liferay-sync-client)

You can download the desktop client from the
[Liferay Sync downloads page](https://www.liferay.com/downloads/liferay-sync). 
Note that you'll need a Liferay account for this. Once you've downloaded the 
appropriate desktop client for your operating system, installing Liferay Sync on 
Windows or Mac OS is straightforward. 

To install the Liferay Sync client on Windows, you must have administrator
privileges. Upon launching the Windows application installer, you're prompted
to choose an install location. Select an appropriate location and click
*Install*. Sync automatically starts after the installation finishes. The first
time Sync runs, you need to configure it to connect and sync with your @product@ 
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

Next, you'll configure the Sync client. 

## Configuring the Liferay Sync Desktop Client [](id=configuring-the-liferay-sync-desktop-client)

Now that you've installed Sync, you're ready to configure it! The configuration 
steps for Sync on Windows and Mac are identical. 

1.  Open Sync and enter your @product@ instance's address along with your 
    account credentials. Click *Sign In* when you're finished. 

    ![Figure 2: The first time you run Liferay Sync, you need to tell it how to communicate with your @product@ server.](../../../images/sync-setup-01.png)

    When connecting to a server via HTTPS, an error appears if the certificate 
    can't be verified. Choosing *Proceed Anyway* bypasses verification and 
    leaves the connection open to compromise. Liferay Sync attempts to read the 
    certificates specified in the Java Control Panel 
    ([see section 20.4.5](https://docs.oracle.com/javase/8/docs/technotes/guides/deploy/jcp.html#A1152831)). 
    If Java isn't installed, you can also put your certificates in 
    `[user.home]/.liferay-sync-3/certificates`. Liferay Sync will trust all 
    certificates in this folder. 

    ![Figure 3: When connecting over HTTPS, Liferay Sync produces an error if it can't verify the security certificate. Choosing *Proceed Anyway* bypasses verification and leaves the connection open to compromise.](../../../images/sync-certificate-error.png)

2.  Select the sites you want to sync with. You can search for a site in the 
    *Search* bar above the site list. If you want to sync all the subfolders of 
    your selected sites, click *Proceed* and move on to the next step. 

    ![Figure 4: Select the sites you want to sync with. Clicking a site's gear icon opens another window where you can choose to sync with only specific subfolders in that site.](../../../images/sync-setup-02.png) 

    To sync only specific folders in a site, first click the site's gear icon. 
    In the window that appears, all folders are selected by default. Unselect 
    the folders you don't want to sync with. Unselecting a subfolder causes the 
    parent folder's checkbox to show a minus sign, indicating that you haven't 
    selected all of the parent folder's subfolders. To sync only the documents 
    at the top of a folder's hierarchy (no subfolders), unselect all of that 
    folder's subfolders. You can also do this by clicking the folder's checkbox 
    until the minus sign appears. Click *Select* when you're finished with your 
    selections, and then click *Proceed* to move on to the next step. 

    ![Figure 5: Choose the site's subfolders that you want to sync with. The checkbox with the minus sign indicates that not all of the *registration* folder's subfolders are selected.](../../../images/sync-select-folders.png)

3.  Specify the local folder your @product@ instance will sync with. This folder 
    will be used exclusively for Sync: Sync creates it and it must not conflict 
    with any existing local folder. The Sync folder's default name is the 
    instance's host name, and its default location is the user's documents 
    folder. For example, since the instance in the following screenshots runs 
    locally at the address `http://localhost:8080/`, Sync creates a Sync folder 
    named *localhost* in the user's documents folder. You can, of course, 
    specify any unique name and location for the Sync folder. Click *Start 
    Syncing* to begin syncing files. 

    ![Figure 6: Specify your local Sync folder's name and location.](../../../images/sync-setup-03.png)

4.  Celebrate! You've successfully set up Liferay Sync! Sync congratulates you 
    on setting it up and begins to sync files from the sites you selected to 
    your local Sync folder. Note, completing the initial synchronization may 
    take a significant amount of time, depending on the amount of data being 
    transferred. You can safely close the window as syncing continues in the 
    background. To view the local Sync folder, click *Open Folder*. To open 
    Sync's preferences, click the small gray text *advanced setup* near the 
    top-right. 

    ![Figure 7: Congratulations, you've successfully set up Liferay Sync!](../../../images/sync-setup-04.png)

Next, you'll learn how to use the Liferay Sync desktop client. 

## Using the Liferay Sync Desktop Client [](id=using-the-liferay-sync-desktop-client)

When Liferay Sync is running its icon appears in your task bar (Windows) or menu 
bar (Mac). Clicking this icon opens a menu that lets you work with and manage 
Liferay Sync. 

![Figure 8: The Liferay Sync menu in the Windows task bar and Mac menu bar gives you quick access to Sync.](../../../images/sync-toolbar-01.png)

The top of this menu shows your Sync status. If all your selected sites are 
synced, then your status is *Synced*. 

Below your Sync status, the menu lists three shortcuts for accessing your 
@product@ instance's files: 

- **Open Sync Folder:** Select a site to open its local Sync folder. 
- **View Website:** Select a site to view the page in @product@ that contains 
  its Documents and Media app. 
- **Recent Files:** Lists recently created and modified files in the 
  repositories you can access. 

Note that if you sync with two or more @product@ instances, Sync shows each at 
the top of the menu instead of your Sync status. Mouse over each instance to 
reveal a submenu with that instance's Sync status and file shortcuts. 

![Figure 9: When you sync with more than one @product@ instance, Sync shows submenus for each.](../../../images/sync-toolbar-02.png)

Lastly, regardless of how many @product@ instances you sync with, the menu lists 
the following three options: 

- **Preferences:** Open Sync's preferences. 
- **Help:** Open Sync's documentation. 
- **Quit:** Shut down Sync on your machine. 

Next, you'll learn how to use Sync's preferences to control how Sync functions 
on your machine. 

### Using Sync Preferences [](id=using-sync-preferences)

You can use Sync's preferences to add/remove @product@ instances to sync with, 
edit connection settings, and control Sync's basic behavior. Open Sync's 
preferences by clicking the Sync icon in the task bar (Windows) or menu bar 
(Mac OS) and selecting *Preferences*. A preference screen for your instance 
accounts displays. This is the *Accounts* tab in *Preferences*. 

![Figure 10: The Preferences menu's *Accounts* tab lets you manage syncing with sites per account.](../../../images/sync-preferences-accounts-01.png)

The *Accounts* tab contains the following:

- **Accounts:** the instance accounts you sync with. When you select an account, 
  the sites you have permission to sync with are shown on the right under 
  *Syncing Sites*. You can use the plus, minus, and pencil icons at the bottom 
  of the account list to add, delete, or edit an account, respectively. You 
  should use caution when deleting an account from your Sync client, as doing so 
  also deletes any local files and folders for that account. Adding an account 
  takes you through the same set of steps you used to set up the Sync client. 
  [Click here](/discover/portal/-/knowledge_base/7-0/using-liferay-sync-on-your-desktop#configuring-the-liferay-sync-desktop-client) 
  for instructions on this. 

- **Syncing Sites:** the sites you have permission to sync with for the selected 
  account. The sites you currently sync with are shown under *Selected Sites*. 
  Other sites available for syncing are shown under *Unselected Sites*. To 
  change the sites you sync with, click the *Manage Sites* button. The window 
  that appears lets you select and/or unselect sites to sync with. This window 
  is identical to the one that appeared when you first configured the client. 
  [Click here](/discover/portal/-/knowledge_base/7-0/using-liferay-sync-on-your-desktop#configuring-the-liferay-sync-desktop-client) 
  and see step two for instructions on using it. Use caution when de-selecting
  sites. De-selecting a site deletes its folder on your machine. 

- **Location:** the selected account's local Sync folder location. Click the 
  *Change* button to change this folder's location. 

The Preferences menu's *General* tab contains settings for the Sync client's 
general behavior. It lists the following options: 

- **Launch Liferay Sync on startup:** starts Sync automatically each time your 
  machine starts. 

- **Show desktop notifications:** shows a small notification in the corner of 
  your screen when a synced file changes. 

- **Automatically check for updates:** automatically check for new client 
  versions. You can click the *Check Now* button to check for updates manually. 

![Figure 11: The Preferences menu's *General* tab contains settings for Sync's general behavior.](../../../images/sync-preferences-general-01.png)

Lastly, the Preferences menu's *Network* tab lets you control how Sync transfers 
data with your @product@ instances. It contains the following options: 

- **Download Rate:** To limit the rate at which Sync downloads data, select 
  *Limit to* and then specify the rate. 

- **Upload Rate:** To limit the rate at which Sync uploads data, select 
  *Limit to* and then specify the rate. 

- **Enable LAN Syncing:** Whether to download updates from other desktop clients 
  on the same local network before downloading from the server. This can help 
  reduce server load and increase data transfer speeds. Note that LAN syncing 
  only works when enabled in the @product@ instance by the administrator, and in 
  other clients. 

![Figure 12: The Preferences menu's *Network* tab contains settings for Sync's data transfer behavior.](../../../images/sync-desktop-prefs-network.png)

Note that your @product@ instance's administrator can also limit the 
download/upload rate from the @product@ instance. In this case, the @product@ 
instance's settings take precedent. For example, if you set a 5.0 MB/s download 
rate in the client but the @product@ instance's download limit is 2.0 MB/s, the 
latter takes precedence. Also, the client's rate applies across all its 
accounts. For example, if the client connects to three accounts and its download 
rate is 5.0 MB/s, then the sum of the download rate for all three accounts never 
exceeds 5.0 MB/s. 

Now that you have a handle on configuring Sync, it's time to see how it works 
with the files in your Sync folder. 

### Using Your Local Sync Folder [](id=using-your-local-sync-folder)

Once you configure and run Sync, Sync automatically uploads to your @product@ 
instance any files you add or modify in your Sync folder. Sync also downloads to 
your Sync folder any file changes by other users. If you delete a file in your 
Sync folder, Sync also deletes it from the instance and other clients. You 
should therefore use **extreme caution** when deleting files in your Sync 
folder. If you accidentally delete a file though, not all is lost! The file can 
still be recovered from the instance's Recycle Bin, which is enabled by default. 
Note, if the instance or site administrator has disabled the Recycle Bin, 
recovering deleted files is impossible. 

+$$$

**Warning:** Deleting a file in your Sync folder also deletes it in the 
@product@ instance and in other clients. If you accidentally delete a file, it 
can be recovered from the instance's Recycle Bin. The Recycle Bin is enabled by 
default. File recovery is, however, impossible if the instance or site 
administrator has disabled the Recycle Bin. 

$$$

You can run through the following exercise to familiarize yourself with how to 
create, edit, download, and upload files with Sync. First, open your Sync folder 
in your file manager and create a new file called `README.txt`. Enter the word 
`test` in this file. Next, make sure you can access this file in your @product@ 
site. Go to the site you want to sync with and navigate to its Documents and 
Media app. It lists your `README.txt` file.

Download the `README.txt` file to a convenient location on your machine. Open 
the file and check that it still says `test`. Now open the `README.txt` file in 
your Sync folder and edit it so that it says `second test`. Once the changes are 
synced, go back to your browser and refresh the page with your Documents and 
Media app. Click on the *README.txt* file's name, look at the file information 
displayed, and check that the file's version number has been incremented. 

![Figure 13: Updating a file through Liferay Sync increments the file's version number. You can view a file's version number through the web interface.](../../../images/sync-file-edit-01.png)

If you download and open the `README.txt` file again, it now says `second test`.
Your edit was uploaded to the site! You can be confident that this edit was also
downloaded by all other Sync clients connected to your site. 

Now delete the `README.txt` file from your local Sync folder. When the changes
finish syncing, go back to your browser and refresh the page containing your
Documents and Media app. The file is gone! The file is also deleted from the
local Sync folders of all other Sync clients connected to the site. Remember
this very important rule: deleting files in your local Sync folder deletes them
*everywhere*! Next, you'll learn how to use the Sync client for your mobile
device. 
