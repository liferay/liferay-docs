# Using the Liferay Sync Desktop Client [](id=using-the-liferay-sync-desktop-client)

When Liferay Sync is running, its icon appears in your task bar (Windows) or menu 
bar (Mac). Clicking this icon opens a menu that lets you work with and manage 
Liferay Sync. 

![Figure 1: The Liferay Sync menu in the Windows task bar and Mac menu bar gives you quick access to Sync.](../../../../images/sync-toolbar-01.png)

The top of this menu shows your Sync status. If all your selected Sites are 
synced, then your status is *Synced*. 

Below your Sync status, the menu lists three shortcuts for accessing your 
files: 

**Open Sync Folder:** Select a Site to open its local Sync folder. 

**View Website:** Select a Site to view the page in @product@ that contains 
its Documents and Media app. 

**Recent Files:** Lists recently created and modified files in the 
repositories you can access. 

Note that if you sync with two or more @product@ instances, Sync shows each at 
the top of the menu instead of your Sync status. Mouse over each instance to 
reveal a submenu with that instance's Sync status and file shortcuts. 

![Figure 2: When you sync with more than one @product@ instance, Sync shows submenus for each.](../../../../images/sync-toolbar-02.png)

Finally, regardless of how many @product@ instances you sync with, the menu lists 
the following three options: 

**Preferences:** Open Sync's preferences. 

**Help:** Open Sync's documentation. 

**Quit:** Shut down Sync on your machine. 

Next, you'll learn how to use Sync's preferences to control how Sync functions 
on your machine. 

## Using Sync Preferences [](id=using-sync-preferences)

You can use Sync's preferences to add/remove @product@ instances to sync with, 
edit connection settings, and control Sync's basic behavior. Open Sync's 
preferences by clicking the Sync icon in the task bar (Windows) or menu bar 
(Mac OS) and selecting *Preferences*. A preference screen for your instance 
accounts displays. This is the *Accounts* tab in *Preferences*. 

![Figure 3: The Preferences menu's *Accounts* tab lets you manage syncing with Sites per account.](../../../../images/sync-preferences-accounts-01.png)

The *Accounts* tab contains the following options:

**Accounts:** The accounts you sync with. When you select an account, the Sites
you have permission to sync with are shown on the right under *Syncing Sites*.
You can use the plus, minus, and pencil icons at the bottom of the account list
to add, delete, or edit an account, respectively. You should use caution when
deleting an account from your Sync client, as doing so also deletes any local
files and folders for that account. Adding an account takes you through the
same set of steps you used to set up the Sync client. [Click
here](/discover/portal/-/knowledge_base/7-1/installing-and-configuring-the-desktop-liferay-sync-client#configuring-the-liferay-sync-desktop-client) 
for instructions on this. 

**Syncing Sites:** The Sites you have permission to sync with for the selected 
account. The Sites you currently sync with are shown under *Selected Sites*. 
Other Sites available for syncing are shown under *Unselected Sites*. To 
change the Sites you sync with, click the *Manage Sites* button. The window 
that appears lets you select and/or unselect Sites to sync with. This window 
is identical to the one that appeared when you first configured the client. 
[Click here](/discover/portal/-/knowledge_base/7-1/installing-and-configuring-the-desktop-liferay-sync-client#configuring-the-liferay-sync-desktop-client) 
and see step two for instructions on using it. Use caution when de-selecting
Sites. De-selecting a Site deletes its folder on your machine. 

**Location:** The selected account's local Sync folder location. Click the 
*Change* button to change this folder's location. 

The Preferences menu's *General* tab contains settings for the Sync client's 
general behavior. It lists the following options: 

**Launch Liferay Sync on startup:** Starts Sync automatically each time your 
machine starts. 

**Show desktop notifications:** Shows a small notification in the corner of 
your screen when a synced file changes. 

**Automatically check for updates:** Automatically check for new client 
versions. You can click the *Check Now* button to check for updates manually. 

![Figure 4: The Preferences menu's *General* tab contains settings for Sync's general behavior.](../../../../images/sync-preferences-general-01.png)

Finally, the Preferences menu's *Network* tab controls how Sync transfers data
with your @product@ servers. It contains the following options: 

**Download Rate:** To limit the rate at which Sync downloads data, select 
*Limit to* and then specify the rate. 

**Upload Rate:** To limit the rate at which Sync uploads data, select 
*Limit to* and then specify the rate. 

**Enable LAN Syncing:** Whether to download updates from other desktop clients 
on the same local network before downloading from the server. This can help 
reduce server load and increase data transfer speeds. Note that LAN syncing 
only works when enabled in the @product@ instance by the administrator, and in 
other clients. 

![Figure 5: The Preferences menu's *Network* tab contains settings for Sync's data transfer behavior.](../../../../images/sync-desktop-prefs-network.png)

Note that your @product@ administrator can also limit the download/upload rate.
In this case, @product@'s settings take precedent. For example, if you set
a 5.0 MB/s download rate in the client but @product@'s download limit is 2.0
MB/s, the latter takes precedence. Also, the client's rate applies across all
its accounts. For example, if the client connects to three accounts and its
download rate is 5.0 MB/s, then the sum of the download rate for all three
accounts never exceeds 5.0 MB/s. 
