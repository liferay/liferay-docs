# Administering Liferay Sync [](id=administering-liferay-sync)

Liferay Sync is an add-on product for Liferay 6.2 CE and EE, and Liferay 7.0, 
that synchronizes files between your Liferay server and users' desktop and 
mobile environments. With Liferay Sync, users can publish and access shared 
documents and files from their native environments without using a browser. 
Clients for Windows and Mac OS desktops and Android and iOS mobile platforms are 
supported. As users add and collaborate on documents and files, Liferay Sync 
automatically synchronizes them across all configured Sync clients and in your 
portal. Liferay Sync integrates completely with the Liferay Platform so that 
features such as authentication and versioning function in the supported 
environments. The Liferay Sync desktop client stores files locally so they're 
always available, even when users are offline. It automatically synchronizes 
files upon client reconnection. The Liferay Sync mobile client saves storage 
space on users' devices by downloading only the files they choose. 

This guide walks you through setting up Sync for your portal and its sites. In
addition to basic portal configuration, important topics such as portal security
and accidental data loss are covered. Note, you must be an administrator to set
up your portal for Sync. If you're not a portal or site administrator, you can
skip this guide and move on to
[installing and using the Sync desktop client](/discover/portal/-/knowledge_base/6-2/using-liferay-sync-on-your-desktop). 

To prepare your portal for Sync, you must first install the *Sync Connector* 
plugin. 

## Installing the Sync Connector Plugin [](id=installing-the-sync-connector-plugin)

Prior to Liferay 6.2, Liferay Portal's Documents and Media services contained
all the logic Liferay Sync used. As of Liferay 6.2, the Sync Connector plugin
instead contains the logic Sync requires. On Liferay 7.0, you must therefore
install the Sync Connector plugin. The new Sync 3.x clients also require the 
latest Sync Connector plugin. Note, older Sync 1.x clients can't connect to the 
latest Sync Connector plugin. 

+$$$

**Note:** The Liferay Sync Security module that Sync requires is included and 
enabled by default in Liferay 7.0. You can verify this by ensuring that the 
`SYNC_DEFAULT` and `SYNC_TOKEN` entries are enabled in *Control Panel* &rarr; 
*Configuration* &rarr; *Service Access Policy*. 

$$$

Sync Connector contains two applications: *Sync Web* (sync-web) and *Sync Admin*
(sync-admin-portlet). Sync Web transparently provides Sync's functional logic.
Sync Admin provides a UI that lets you control how Sync clients function with
your portal. For example, you can disable Sync across the portal or on a
site-by-site basis. Sync Connector installs the same as any other Liferay
plugin. Once you've installed Sync, it's enabled by default for all the portal's
sites, so you can start using it. 

If you want to use Sync Admin's default settings and are fine with Sync being
enabled for all the portal's sites, you can skip the next section. Before
directing your users to install and configure the Sync desktop and mobile
clients, however, **Make sure to read** this guide's sections on preventing
accidental file deletion, and ensuring Sync security and **warn your users**
about the potential for accidental data loss.

## Using Sync Admin to Configure Liferay Sync [](id=using-sync-admin-to-configure-liferay-sync)

Sync Admin's UI lets you enable or disable Sync across the portal or for
specific sites. You can also configure default file permissions on a per-site
basis. Sync Admin is accessible in the Control Panel's Configuration section. 

![Figure 1: The Control Panel's Configuration section contains the Sync Admin portlet.](../../images/sync-admin-01.png)

Sync Admin has two tabs: *Settings* and *Sites*. The Settings tab has *General*
and *Advanced* settings that apply globally to Sync. In General settings, you
can disable Sync for all sites by unchecking the checkbox *Allow the use of
Sync?*. You can also disable user personal sites from syncing by unchecking the
checkbox *Allow users to sync their personal sites?*. Sync is enabled by default
on all these sites. 

The Advanced section in Settings lets you tune the portal's data exchange with
Sync clients. The *Max Connections* value specifies the maximum number of
simultaneous connections each client is allowed per account. For example, if Max
Connections is three, a client can simultaneously upload or download up to three
files for each account. Note, this setting operates on a per client basis. If
the Max Connections is set to three and a user has two clients connected to an
account (which is possible if Sync is installed on two different machines), then
the user is effectively allowed six simultaneous connections. While increasing
Max Connections can speed up file transfers it also places a heavier load on the
server. *Max Connections* is set to one by default. 

*Poll Interval* is the next Advanced setting. It's the frequency in seconds that
clients automatically check the portal for updates. For example, if set to ten,
connected clients check the portal for updates every ten seconds. The default
Poll Interval is five. 

Next up is the Sites tab, which lets you can control Sync on a per-site basis. 

![Figure 2: Sync Admin's Sites tab lets you manage Sync on a per-site basis.](../../images/sync-admin-02.png)

For each site in the portal, the Sites tab lists each site's default file
permissions (more on this in a moment) and whether Sync is enabled for that
site. Sync is enabled by default for all sites. To disable Sync for a site,
click the drop-down arrow in the last column for the site and select *Disable
Sync Site*. To disable multiple sites at once, select their checkboxes and click
the *Disable Sync Sites* link that appears above the table. **Please use
caution** when disabling Sync for a site, as doing so **deletes** files for that
site from the Sync clients. Disabling Sync for a site, however, doesn't affect
the site's files in the portal. 

+$$$

**Warning:** Disabling Sync for specific sites from Sync Admin can result in
data loss across clients. If Sync is disabled for a site users are currently
syncing, any files in the clients' sync folders for that site are automatically
deleted from their clients. If a user is offline when Sync is disabled for a
site, any offline changes or additions they make are deleted upon client
reconnection. 

$$$

You can enable Sync for a site by selecting *Enable Sync Site* from its dropdown
arrow. To enable Sync for multiple sites, select their checkboxes and click the
*Enable Sync Sites* link that appears above the table. 

Make sure that each site for which Sync is enabled has a Documents and Media
portlet on at least one of its pages. If a site doesn't have the portlet on any
of its pages and users click the *Open Website* link from their Sync menus, the
error message *The requested resource was not found* appears. 

The Sites tab also lets you set default file permissions for files uploaded from
Sync clients. The process for setting permissions is nearly the same as for
enabling or disabling Sync for sites. To set the default file permissions for a
single site, click its drop-down arrow and select *Default File Permissions*.
This opens a window that lets you select the default file permissions for that
site. Click *Choose* for the permissions you want to use. 

![Figure 3: Click *Choose* to select the default file permissions for a site in Sync.](../../images/sync-admin-03.png)

To set the default file permissions for several sites, select the checkboxes for
the sites, click the *Default File Permissions* link that appears above the
table, and select the permissions you want to use. 

Now that you know how to set default file permissions, you should learn more 
about what exactly they do. 

## Understanding Default File Permissions [](id=understanding-default-file-permissions)

The default file permissions might behave differently than you'd expect. They
control *only* the permissions for new files uploaded through the Sync clients;
they don't affect permissions for uploading or restrict document owners (the
user who originally uploaded a document) in any way. For example, even if you
set a site's default file permissions to View Only, that site's users can still
upload new documents to the site. The file's owner has edit permission; the rest
of the site's users have the View Only permission. 

Awesome! Now that you know how to configure Sync in your portal, it's time to 
look closer at a use case that warrants special attention for administrators: 
accidental file deletion. 

## Protecting Against Accidental File Deletion [](id=protecting-against-accidental-file-deletion)

Liferay Sync's power rests in its ability to propagate between the portal and
connected Sync clients. When a user deletes a file from a connected client, Sync
also deletes the file in the portal and in any other connected clients.
Likewise, if a user deletes a file in the portal, Sync also deletes the file in
all connected clients. In other words, anywhere a user deletes a file, Sync
deletes it *everywhere*. You're probably thinking, "This is a disaster waiting
to happen!" Don't fret! Liferay's Recycle Bin is enabled by default and lets you
recover deleted files. You can access the Recycle Bin from each site's *Site
Administration* menu. 

+$$$

**Warning:** Liferay Sync automatically propagates file and folder deletion
through the portal and in all connected clients. If a portal or site
administrator disables the Recycle Bin, deleted files can't be recovered.

$$$

Portal and site administrators can, of course, disable the Recycle Bin.
Disabling the Recycle Bin in a site, however, leaves the site vulnerable to
accidental file deletions that propagate through Sync. 

## Ensuring Sync Security [](id=ensuring-sync-security)

As an administrator, you're undoubtedly concerned about the security of all
connections to and from your portal. As long as your server is configured to use
HTTPS, Sync clients communicate securely with your portal with user-supplied
credentials. Users can only access the documents and sites they're permitted to
access. To support Security Mode in the Sync mobile client and securely transmit
files, your Liferay server must also use SSL. The next section demonstrates how
Sync's permissions work with portal's permissions. 

## Liferay Sync Permissions Demonstration [](id=liferay-sync-permissions-demonstration)

Sync uses Liferay's default permissions to determine files and folders to sync
with the user's devices. It can only sync files a user can access in the portal.
After installing the desktop Sync client, you can follow the steps below to test
this functionality. 

First, enter the text `classified information` into a new text file and save it
on your desktop as `secret.txt`. Then use your browser to sign into your portal
and create a new user with the user name *secretagent* and the email address
*secretagent\@liferay.com*. Give this user a password and then create a new
private site called *Secret Site*. Create a page on the site and add the
Documents and Media portlet to it. Then add the secretagent user to the Secret
Site and grant the *Site Administrator* role to the user. Log in as secretagent
and navigate to the Secret Site. Then upload the `secret.txt` document to the 
Documents and Media portlet. Make sure you also have a user that isn't a member 
of the Secret Site and therefore doesn't have access to any of its documents 
through Sync. If you don't have such a user, create one now. 

Next, configure your Liferay Sync client to sign in with the secretagent user's
credentials and sync with the Secret Site. Open the Liferay Sync menu from the
system tray and select *Preferences*. In the *Accounts* tab, click the plus icon
at the window's bottom left to add an account. Provide the secretagent user's
credentials and uncheck all Liferay sites except the Secret Site. Now confirm
that Sync downloaded the `secret.txt` file to your new Sync folder. Open it and
check that it contains the text `classified information`. Next, use Sync to
connect to your portal with the user that doesn't belong to the Secret Site. The
file doesn't sync because this user isn't a site member. 

Now go to Sync Admin and set the Secret Site's default file permissions to View
Only. Create a new user, add them to the Secret Site, and add their account in
your Liferay Sync client. As with the secretagent user, Sync downloads the
`secret.txt` file to this user's local Sync folder because the user is a member
of the Secret Site. Now edit and save this file. Even though you can edit and
save it locally, the edits aren't synced because the site's default file
permissions are View Only. After attempting the sync, a red *x* appears next to
the file in the local Sync folder. Right click the file to see the error. It
confirms the user doesn't have the required permissions. 

![Figure 4: The upload error occurs because the user only has permission to view files.](../../images/sync-file-permissions-error.png)

To confirm that the error didn't propagate through Sync, open the file in the 
secretagent user's local Sync folder. It still contains the original text. 
Likewise, the original file remains in the site's Documents and Media portlet. 
To get rid of the error in the other user's local Sync folder, return there and 
then right click the file and select *Download From Server*. This replaces the 
file with the latest file in the portal. 

Now edit `secret.txt` in the secretagent user's local Sync folder. When you
check the file in the other user's local Sync folder and in the portal, notice
that Sync propagated the edits. The changes were propagated because the
secretagent user owns the file in the portal. Owners can do anything with their
files, even when the site's default file permissions are set to View Only.

Congratulations! You've successfully set up a Liferay Sync folder that can only 
be accessed by site members and administrators. You've also seen how Sync's 
default file permissions work. By using Liferay's permissions alongside Sync 
Admin's default file permissions, Sync gives administrators two levels of 
control over files in their sites. 

Great! Now you know how to enable and configure Liferay Sync in your portal. The 
next article explains how end users can install and configure the Sync desktop 
client. 
