# Administering Liferay Sync [](id=administering-liferay-sync)

Liferay Sync is an add-on product for Liferay 6.2 CE and EE that synchronizes 
files between your Liferay server and users' desktop and mobile environments. 
With Liferay Sync, your users can publish and access shared documents and files 
from their native environments without using a browser. Clients for Windows and 
Mac OS desktops and Android and iOS mobile platforms are currently supported. As 
users add and collaborate on documents and files, Liferay Sync automatically 
synchronizes them across all configured Sync clients and in your portal. Liferay 
Sync is fully integrated into the Liferay Platform so that features such as 
authentication and versioning function in the supported environments. The 
Liferay Sync desktop client stores files locally so that they're always 
available, even when users are offline. It automatically synchronizes files upon 
reconnection. The Liferay Sync mobile client saves storage space on users' 
devices by downloading only the files they choose. 

This guide walks you through setting up your portal and its sites for Sync. In 
addition to basic portal configuration, important topics such as portal security 
and accidental data loss are covered. Note that you must be an administrator to 
set up your portal for Sync. If you're not a portal or site administrator, you 
can skip this guide and move on to 
[installing and using the Sync desktop client](/discover/portal/-/knowledge_base/6-2/using-liferay-sync-on-your-desktop). 

To prepare your portal for Sync, you first need to install the *Sync Connector* 
plugin. The next section discusses this. 

## Installing the Sync Connector Plugin [](id=installing-the-sync-connector-plugin)

Prior to Liferay 6.2, Liferay Portal's Documents and Media services contained
all the logic used by Liferay Sync. As of Liferay 6.2, the Sync Connector plugin 
instead contains the required Sync logic. You must therefore use this plugin 
when using Sync with your Liferay 6.2 installations. Liferay Portal 6.2 includes 
an older version of the plugin (*Sync Connector Web*). You need to upgrade to 
the new Sync Connector plugin for Sync to work properly. The new Sync clients 
require the latest Sync Connector plugin. Older Sync 1.x clients can't connect 
to the new Sync Connector plugin. Sync Connector installs the same as any other 
Liferay plugin. Once you've installed it, you're ready to use Sync. 

The plugin contains two applications: *Sync Web* (sync-web) and *Sync Admin* 
(sync-admin-portlet). Sync Web contains the logic required for Sync to function. 
It has no UI and works in the background to enable Sync without requiring any 
attention from you or your users. Sync Admin lets you control how Sync clients 
function with your portal. For example, you can use Sync Admin to disable Sync 
across the portal, or on a site-by-site basis. However, you don't have to 
interact with Sync Admin for Sync to function properly. By default, Sync Admin 
enables Sync for all the portal's sites.

If you've installed the Sync Connector plugin and want to use Sync Admin's 
default settings, you can skip the following section on configuring Sync Admin 
and proceed to the sections on preventing accidental file deletion and ensuring 
Sync security. **Please be sure** to read these sections and **warn your users** 
of the potential for accidental data loss. Then direct your users to install and 
configure the Sync desktop and/or mobile clients. 

If you need to disable Sync for specific sites, however, you need to configure 
Sync Admin. The following section discusses this. 

## Using Sync Admin to Configure Liferay Sync [](id=using-sync-admin-to-configure-liferay-sync)

Sync Admin has a simple UI that lets you enable or disable Sync across the 
portal or for specific sites. You can also configure the default file 
permissions on a per-site basis. You can access Sync Admin in the Control 
Panel's Configuration section. 

![Figure 5.12: The Control Panel's Configuration section contains the Sync Admin portlet.](../../images/sync-admin-01.png)

Sync Admin has two tabs: *Settings* and *Sites*. The Settings tab has *General* 
and *Advanced* settings that apply to Sync as a whole. In the General settings, 
you can disable Sync for all sites by unchecking the checkbox next to *Allow the 
use of Sync?*. You can also disable user personal sites from syncing by 
unchecking the checkbox next to *Allow users to sync their personal sites?*. 
These settings are enabled by default.

The Advanced settings in the Settings tab control how your portal exchanges data 
with Sync clients. The *Max Connections* field sets the maximum number of 
simultaneous connections each client is allowed per account. For example, if 
this is set to three, a client can simultaneously upload or download up to three 
files for each account. Note that this setting operates on a per client basis. 
If this is set to three and a user has two clients connected to an account 
(which is possible if Sync is installed on two different machines), then this 
effectively becomes six for that user. While increasing Max Connections can 
speed up file transfers, it also places a heavier load on the server. 
*Max Connections* is set to one by default. 

The next Advanced setting in the Settings tab is *Poll Interval*. Poll Interval 
sets the length of time in seconds for how often clients check the portal for 
updates. For example, if this is set to ten, connected clients check the portal 
for updates every ten seconds. The default value is five. 

Next up is Sync Admin's Sites tab. This lets you can control Sync on a per-site 
basis. 

![Figure 5.13: Sync Admin's Sites tab lets you manage Sync on a per-site basis.](../../images/sync-admin-02.png)

For each site in the portal, the Sites tab lists the default file permissions 
(more on this in a moment) and whether Sync is enabled. All sites are enabled 
for Sync by default. To disable a site for Sync, click its drop-down arrow to 
the right and then click *Disable Sync Site*. To disable multiple sites at once, 
check their checkbox in the left column, and then click *Disable Sync Sites* 
when it appears above the table. **Please use caution** when disabling Sync for 
a site, as doing so causes any files for that site to be **deleted** from the 
Sync clients as well. Disabling Sync for a site, however, doesn't affect the 
site's files in the portal. 

+$$$

**Warning:** Disabling Sync for specific sites from Sync Admin can result in 
data loss across clients. If Sync is disabled for a site users are currently
syncing, any files in the clients' sync folders for that site are automatically
deleted from their clients. If a user is offline when Sync is disabled for a
site, any offline changes or additions they make are deleted upon reconnection. 

$$$

You can use the same procedures to enable one or more sites for Sync that have 
been disabled. The only difference is that you must click *Enable Sync Site* or 
*Enable Sync Sites* instead.

Also, each site enabled for Sync should have a Documents and Media portlet on 
one of its pages. If a site selected for syncing doesn't have a Documents and 
Media portlet on a page, users get a *The requested resource was not found* 
error when they try to use the *Open Website* link from their Sync menus. 

Sync Admin's Sites tab also lets you set the default file permissions for files 
uploaded via Sync clients. Setting the default file permissions works similarly 
to enabling or disabling sites for Sync. To set the default file permissions for 
a single site, click its drop-down arrow to the right, and then click 
*Default File Permissions*. This opens a window that lets you select the default 
file permissions for that site. Click *Choose* next to the permissions you want 
to use.

![Figure 5.14: Click *Choose* to select the default file permissions for a site in Sync.](../../images/sync-admin-03.png)

To set the default file permissions for several sites, check the checkbox to the 
left of each. Then click *Default File Permissions* when it appears above the 
table, and select the permissions you want to use. 

Now that you know how to set default file permissions, you should learn more 
about what exactly they do.

### Understanding Default File Permissions

Default file permissions don't behave how you might expect them to. They control 
*only* the permissions for new files uploaded through the Sync client; they 
don't control upload permission or restrict document owners (the user who 
originally uploaded a document) in any way. For example, even if you set a 
site's default file permissions to View Only, that site's users can still upload 
new documents to the site. However, only the file's owner has edit permission; 
the rest of the site's users have View Only permission.

Awesome! Now that you know how to configure Sync in your portal, it's time to 
look closer at a use case that warrants special attention for administrators: 
accidental file deletion. 

## Protecting Against Accidental File Deletion [](id=protecting-against-accidental-file-deletion)

The power of Liferay Sync rests in its ability to propagate any changes that 
users make across the portal and connected clients. These changes can also
include file deletion. When a user deletes a file from a connected client, Sync 
also deletes that file in the portal and in any other connected clients. 
Likewise, if a user deletes a file in the portal, Sync also deletes the file in 
any connected clients. In other words, if a user deletes a file anywhere, Sync 
deletes it *everywhere*. You're probably thinking, "This is a disaster waiting 
to happen!" Don't fret! Liferay's Recycle Bin is enabled by default and lets you 
recover deleted files. You can access the Recycle Bin from each site's 
*Site Administration* menu. 

+$$$

**Warning:** Liferay Sync automatically propagates file and folder deletion 
through the portal and in any connected clients. If the portal or site 
administrator has disabled the Recycle Bin, accidentally deleted files can't be 
recovered.

$$$

Portal and site administrators can, of course, disable the Recycle Bin. If
you've disabled the Recycle Bin, then you don't have any protection from 
accidental file deletion propagated through Sync. 

## Ensuring Sync Security [](id=ensuring-sync-security)

As an administrator, you're undoubtedly concerned about the security of any 
connections to and from your portal. As long as your server is configured to use 
HTTPS, the Sync clients communicate securely with your portal using 
user-supplied credentials. This ensures that each user can only access the
documents and sites they have permission for. Also, your Liferay server must use 
SSL to support Security Mode in the Sync mobile client. Otherwise, files are 
transmitted in the open. The next section gives a brief demonstration of how 
Sync's permissions work with your portal's permissions. 

### Demonstrating Liferay Sync Permissions [](id=demonstrating-liferay-sync-permissions)

Sync uses Liferay's default permissions to determine the files and folders to 
sync with the user's machine. This means that Sync can only sync files a user
can access in the portal. After installing the desktop Sync client, you can use
the following steps to test this functionality. 

First, enter the text `classified information` into a new text file and save it 
on your desktop as `secret.txt`. Then use your browser to log into your portal 
and create a new user with the user name *secretagent* and the email address 
*secretagent@liferay.com*. Give this user a password and then create a new 
private site called *Secret Site*. Create a page on the site and add the 
Documents and Media portlet to it. Then assign the secretagent user to the 
Secret Site and grant the *Site Administrator* role to this user. Log in as 
secretagent and select *Secret Site* from the *My Sites* menu on the Dockbar. 
Then upload the `secret.txt` document to the Documents and Media portlet. Make
sure you also have a user that isn't a member of the Secret Site and therefore 
doesn't have access to any of its documents through Sync. If you don't have such 
a user, create one now.

Next, configure your Liferay Sync client to log in with the secretagent 
user's credentials and sync with the Secret Site. Open the Liferay Sync menu 
from the system tray and select *Preferences*. In the *Accounts* tab, click the
plus icon at the window's bottom left to add an account. Provide the secretagent
user's credentials and uncheck all Liferay sites except the Secret Site. Now
confirm that Sync downloaded the `secret.txt` file to your new Sync folder. Open 
it and check that it contains the text `classified information`. Next, use Sync 
to connect to your portal with the user that doesn't belong to the Secret Site. 
The file doesn't sync because this user isn't a site member. 

Now go to Sync Admin and set the Secret Site's default file permissions to View 
Only. Create a new user, add them to the Secret Site, and add their account in 
your Liferay Sync client. As with the secretagent user, Sync downloads the 
`secret.txt` file to this user's local Sync folder because they're a member 
of the Secret Site. Now edit and save this file. Even though you can edit and 
save it locally, those edits aren't synced because the site's default file 
permissions are set to View Only. After attempting the sync, a red *x* appears 
next to the file in the local Sync folder. Right click the file to see the 
error. It confirms the user doesn't have the required permissions. 

![Figure 5.15: The upload error occurs because the user only has permission to view files.](../../images/sync-file-permissions-error.png)

To confirm that the error didn't propagate through Sync, open the file in the 
secretagent user's local Sync folder. It still contains the original text. 
Likewise, the original file remains in the site's Documents and Media portlet. 
To get rid of the error in the other user's local Sync folder, return there and 
then right click the file and select *Download From Server*. This replaces the 
file with the latest file in the portal. 

Now edit `secret.txt` in the secretagent user's local Sync folder. When you 
check the file in the other user's local Sync folder and in the portal, you can 
see that Sync propagated the edits. This occurs because the secretagent user 
owns the file in the portal. Owners can do whatever they want with their files, 
even when the site's default file permissions are set to View Only.

Congratulations! You've successfully set up a Liferay Sync folder that can only 
be accessed by site members and administrators. You've also seen how Sync's 
default file permissions work. By using Liferay's permissions alongside Sync 
Admin's default file permissions, Sync gives administrators two levels of 
control over files in their sites. 

Great! Now you know how to enable and configure Liferay Sync in your portal. The 
next article explains how end users can install and configure the Sync desktop 
client. 
