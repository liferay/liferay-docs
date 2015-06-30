# Administering Liferay Sync

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
[installing and using the Sync desktop and mobile clients](http://www.liferay.com/). 

The first thing you need to do to prepare your portal for Sync is install the 
*Sync Connector* plugin. This is discussed next. 

## Installing the Sync Connector Plugin

Prior to Liferay 6.2, Liferay Portal's Documents and Media services contained
all the logic used by Liferay Sync. As of Liferay 6.2, Sync processing is 
instead in the Sync Connector plugin. This plugin is therefore required for 
using Sync in your Liferay 6.2 installations. While an older version of the 
plugin (*Sync Connector Web*) is installed by default in Liferay Portal 6.2 
bundles, you need to install the new Sync Connector plugin for Sync to work 
properly. The new Sync clients work only with the latest Sync Connector plugin. 
The install process for Sync Connector is the same as that of any other Liferay 
plugin. Once you've installed it, you're ready to use Sync. 

The plugin contains two applications: *Sync Web* (sync-web) and *Sync Admin* 
(sync-admin-portlet). Sync Web contains the logic required for Sync to function. 
It has no UI and works in the background to enable Sync without requiring any 
attention from you or your end users. Sync Admin lets you control how Sync 
clients function with your portal. For example, you can use Sync Admin to 
disable Sync across the portal, or on a site-by-site basis. However, you don't 
have to interact with Sync Admin for Sync to function properly. By default, Sync 
Admin is set to enable Sync for all sites in the portal.

If you've installed the Sync Connector plugin and want to use Sync Admin's 
default settings, you can skip the following section on configuring Sync Admin 
and proceed to the sections on preventing accidental file deletion and ensuring 
Sync security. **Please be sure** to read these sections and **warn your users** 
of the potential for accidental data loss. Then direct your users to install and 
configure the Sync desktop and/or mobile clients. 

If you need to disable Sync for specific sites, however, you need to configure 
Sync Admin. This is shown next. 

## Using Sync Admin to Configure Liferay Sync [](id=using-sync-admin-to-configure-liferay-sync)

Configuring Sync Admin is very straightforward. It has a simple UI that lets you
enable or disable Sync across the portal or for specific sites. You can access 
Sync Admin in the Control Panel's Configuration section. 

![Figure 5.23: The Sync Admin portlet is available in the Control Panel's Configuration Section.](../../images/sync-admin-01.png)

By default, Sync is enabled for all sites in the portal. You can uncheck the 
checkbox next to *Enabled* to disable Sync for all sites. To enable or disable 
Sync for a particular site, first make sure that the checkbox next to 
*Enabled* is checked. Sync is enabled for sites in the *Enabled* column and 
disabled for sites in the *Disabled* column. You can move sites back and forth
by selecting them and then clicking the arrow button in the direction of the
column where they should go. **Please use caution** when disabling Sync for a
specific site, as doing so causes any files for that site to be **deleted** from 
the clients as well. Disabling Sync for a site, however, doesn't affect the 
site's files in the portal.

+$$$

**Warning:** Disabling Sync for specific sites from Sync Admin can result in 
data loss across clients. If Sync is disabled for a site users are currently
syncing, any files in the clients' sync folders for that site are automatically
deleted from their clients. If a user is offline when Sync is disabled for a
site, any offline changes or additions they make are deleted upon reconnection. 

$$$

The remaining two settings in Sync Admin control how your portal exchanges data 
with Sync clients. The *Max Connections* field sets the maximum number of 
simultaneous connections each client is allowed per account. For example, if 
this is set to three, a client can upload or download up to three files 
simultaneously for each account. It's important to remember that this setting 
operates on a per client basis. If this is set to three and a user has two 
clients connected to an account (which is possible if Sync is installed on two 
different machines), then this effectively becomes six for that user. While 
increasing Max Connections can speed up file transfers, it also places a heavier 
load on the server. *Max Connections* is set to one by default.

The next setting in Sync Admin is *Poll Interval*. Poll Interval sets the length 
of time in seconds for how often clients check the portal for updates. For 
example, if this is set to ten, connected clients check the portal for updates
every ten seconds. The default value is five.

Also, each site enabled for Sync must have a Documents and Media portlet. If 
there's no Documents and Media portlet on a site selected for syncing, users 
get a *The requested resource was not found* error when they try to use the 
*Open Website* link from their Sync menus. 

Now that you know how to configure Sync in your portal, it's time to look closer 
at a use case that warrants special attention for administrators: accidental 
file deletion. 

## Protecting Against Accidental File Deletion [](id=protecting-against-accidental-file-deletion)

The power of Liferay Sync rests in its ability to propagate any changes that 
users make across the portal and connected clients. These changes can also
include file deletion. When a file is deleted from a connected client, that
deletion is also carried out in the portal and in any other connected clients.
Likewise, if a file is deleted in the portal, it's also deleted in any
connected clients. In other words, if a file is deleted anywhere, it's deleted
*everywhere*. You're probably thinking, "This is a disaster waiting to happen!" 
Don't fret! Liferay's Recycle Bin is enabled by default and allows recovery of 
any deleted files. You can access the Recycle Bin from each site's 
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
connections to and from your portal. It's important to note that to support 
Security Mode in the Sync mobile client, your Liferay server must use SSL. 
Otherwise, files are transmitted in the open. Also, as long as your server is
configured to use HTTPS, the Sync clients communicate securely with your portal
using user-supplied credentials. This ensures that each user can only access the
documents and sites they have permission for. The next section gives a brief
demonstration of how Sync's permissions work with those of your portal.

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
confirm that the `secret.txt` file you uploaded to the Secret Site is downloaded
to your new Sync folder. Open it and check that it contains the text `classified
information`. Next, use Sync to connect to your portal with the user that
doesn't belong to the Secret Site. Since this user isn't a site member, the file
is doesn't sync. 

Congratulations! You've successfully set up a Liferay Sync folder that can only 
be accessed by the secretagent user and administrators. By using Liferay's 
permissions, Sync allows users to access only the files and folders they can 
access in the portal. 

Great! Now you know how to enable and configure Liferay Sync in your portal. The 
next article explains how end users can install and configure the Sync desktop 
and mobile clients. 
