# Administering Liferay Sync [](id=administering-liferay-sync)

Liferay Sync is an add-on product for @product@ that synchronizes files between
your @product@ server and users' desktop and mobile environments. With Liferay
Sync, users can publish and access shared documents and files from their native
environments without using a browser. Clients for Windows and Mac OS desktops
and Android and iOS mobile platforms are supported. As users add and collaborate
on documents and files, Liferay Sync automatically synchronizes them across all
configured Sync clients and in your @product@ instance. Liferay Sync integrates
completely with @product@ so that features such as authentication and versioning
function in the supported environments. The Liferay Sync desktop client stores
files locally so they're always available, even when users are offline. It
automatically synchronizes files upon client reconnection. The Liferay Sync
mobile client saves storage space on users' devices by downloading only the
files they choose. 

This guide walks you through setting up Sync for your @product@ instance and its 
sites. In addition to basic configuration, important topics such as security and 
accidental data loss are covered. Note, you must be an administrator to set up 
your @product@ instance for Sync. If you're not an instance or site 
administrator, you can skip this guide and move on to 
[installing and using the Sync desktop client](/discover/portal/-/knowledge_base/7-0/using-liferay-sync-on-your-desktop). 

To prepare your @product@ instance for Sync, you must first install Sync's 
prerequisite apps. 

## Installing Sync's Prerequisites [](id=installing-the-sync-connector-plugin)

Liferay Sync requires that your @product@ instance contains the most recent 
versions of the following Liferay Marketplace apps: 

- [Liferay CE IP Geocoder](https://web.liferay.com/marketplace/-/mp/application/15197657): 
  Uses geographic IP lookup to get the approximate location of devices that 
  register with Sync. This is a security measure that lets you see where the 
  devices are that connect to your @product@ instance via Sync. 

- [Liferay CE Sync Connector](https://web.liferay.com/marketplace/-/mp/application/31071510): 
  Lets you enable and configure Sync in your @product@ instance. For example, 
  you can disable Sync across the instance or on a site-by-site basis. Note that 
  Sync is enabled by default for all your @product@ instance's sites. 

These apps are preinstalled in the latest @product@ releases. To verify that 
these apps are installed in your @product@ instance, navigate to *Control Panel* 
&rarr; *Apps* &rarr; *App Manager*. The App Manager lists the installed apps. 
You can also search for them in the *Search* field at the upper right. If your 
@product@ instance doesn't contain these apps, install them from Liferay 
Marketplace. 
[Click here for instructions on installing apps from Liferay Marketplace](/discover/portal/-/knowledge_base/7-0/using-the-liferay-marketplace). 

Note that older Sync 1.x clients can't connect to the latest Sync Connector app. 

+$$$

**Note:** The Liferay Sync Security module that Sync requires is included and 
enabled by default in @product@. You can verify this by ensuring that the 
`SYNC_DEFAULT` and `SYNC_TOKEN` entries are enabled in *Control Panel* &rarr; 
*Configuration* &rarr; *Service Access Policy*. 

$$$

If you want to use Sync Connector's default settings and are fine with Sync 
being enabled for all your @product@ instance's sites, you can skip the next 
section. Before directing your users to install and configure the Sync desktop 
and mobile clients, however, **Make sure to read** this guide's sections on 
preventing accidental file deletion and ensuring Sync security. You should also 
**warn your users** about the potential for accidental data loss. 

## Configuring Liferay Sync [](id=using-sync-admin-to-configure-liferay-sync)

Sync Connector lets you manage how, or if, clients connect to your @product@ 
instance. You can also configure default file permissions on a per-site basis, 
and manage the devices that connect to your @product@ instance. To access Sync 
Connector, select *Control Panel* &rarr; *Configuration* &rarr; *Sync Connector 
Admin*. 

Sync Connector Admin has three tabs: 

1. **Settings:** Control Sync's general behavior. These settings apply globally 
   to Sync. 

    ![Figure 1: The Control Panel's Configuration section contains Sync Connector Admin.](../../../images/sync-admin-01.png)

    - **Allow the use of Sync?:** Whether Sync is enabled.

    - **Allow users to sync their personal sites?:** Whether users can sync data 
      with their personal sites. 

    - **Allow LAN Syncing?:** Whether desktop clients attempt to download 
      updates from other desktop clients on the same local network before 
      downloading from the server. This can help reduce server load and increase 
      data transfer speeds. Note that LAN syncing only works with clients that 
      also enable it. 

    - **Max Connections:** The maximum number of simultaneous connections each 
      client is allowed per account. For example, if Max Connections is three, a 
      client can simultaneously upload or download up to three files for each 
      account. Note, this setting operates on a per client basis. If Max 
      Connections is set to three and a user has two clients connected to an 
      account (which is possible if Sync is installed on two different 
      machines), then the user is effectively allowed six simultaneous 
      connections. While increasing Max Connections can speed up file transfers 
      it also places a heavier load on the server. *Max Connections* is set to 
      one by default. 

    - **Poll Interval:** The frequency in seconds that clients automatically 
      check the @product@ instance for updates. For example, if set to ten, 
      connected clients check the instance for updates every ten seconds. The 
      default Poll Interval is five. 

    - **Max Download Rate:** The maximum transfer rate, in bytes, at which 
      clients can download. A value of 0 specifies no limit. This setting takes 
      precedence over clients' download rate setting. 

    - **Max Upload Rate:** The maximum transfer rate, in bytes, at which clients 
      can upload. A value of 0 specifies no limit. This setting takes precedence 
      over clients' upload rate setting. 

    - **Force Security Mode:** Whether to force security mode on mobile clients. 
      Security mode encrypts Sync files on the device and requires a passcode 
      when accessing the Sync mobile app. 

2. **Sites:** Control Sync on a per-site basis. 

    ![Figure 2: Sync Connector Admin's Sites tab lets you manage Sync on a per-site basis.](../../../images/sync-admin-02.png)
    
    For each site in the @product@ instance, the Sites tab lists each site's 
    default file permissions (more on this in a moment) and whether Sync is 
    enabled for that site. Sync is enabled by default for all sites. To disable 
    Sync for a site, click the site's *Actions* button (![Actions](../../../images/icon-actions.png)) 
    and select *Disable Sync Site*. To disable multiple sites at once, select 
    their checkboxes and click the *Disable Sync Sites* link that appears above 
    the table. **Please use caution** when disabling Sync for a site, as doing 
    so **deletes** files for that site from the Sync clients. Disabling Sync for 
    a site, however, doesn't affect the site's files in the @product@ instance. 

    +$$$

    **Warning:** Disabling Sync for specific sites from Sync Connector Admin can 
    result in data loss across clients. If Sync is disabled for a site users are 
    currently syncing, any files in the clients' sync folders for that site are 
    automatically deleted from their clients. If a user is offline when Sync is 
    disabled for a site, any offline changes or additions they make are deleted 
    upon client reconnection. 

    $$$

    You can enable Sync for a site by selecting *Enable Sync Site* from its 
    Actions button. To enable Sync for multiple sites, select their checkboxes 
    and click the *Enable Sync Sites* link that appears above the table. 

    Make sure that each site for which Sync is enabled has a Documents and Media 
    app on at least one of its pages. If a site doesn't have the app on any of 
    its pages and users click the *Open Website* link from their Sync menus, the 
    error message *The requested resource was not found* appears. 

    The Sites tab also lets you set default file permissions for files uploaded 
    from Sync clients. The process for setting permissions is nearly the same as 
    for enabling or disabling Sync for sites. To set the default file 
    permissions for a single site, click its Actions button and select *Default 
    File Permissions*. This opens a window that lets you select the default file 
    permissions for that site. Click *Choose* for the permissions you want to 
    use. 

    ![Figure 3: Click *Choose* to select the default file permissions for a site in Sync.](../../../images/sync-admin-03.png)

    To set the default file permissions for several sites, select the checkboxes 
    for the sites, click the *Default File Permissions* link that appears above 
    the table, and select the permissions you want to use. Default file 
    permissions might behave differently than you'd expect. They control *only* 
    the permissions for new files uploaded through the Sync clients; they don't 
    affect permissions for uploading or restrict document owners (the user who 
    originally uploaded a document) in any way. For example, even if you set a 
    site's default file permissions to View Only, that site's users can still 
    upload new documents to the site. The file's owner has edit permission; the 
    rest of the site's users have the View Only permission. 

3. **Devices:** View and manage the devices registered with Sync. 

    ![Figure 4: Sync Connector Admin's Devices tab lists all the devices Sync has registered for the @product@ instance.](../../../images/sync-admin-devices.png)

    Each row in the Devices tab's table represents a device. The *Name* column 
    lists the user that registered the device. The remaining columns list each 
    device's location, client type, client build number, last connection date, 
    and status. Each device's Actions button (![Actions](../../../images/icon-actions.png)) 
    lets you manage that device. You can change a device's status from Active to 
    Inactive by selecting *Actions* &rarr; *Disable Sync Device*. Inactive 
    devices can't sync with the @product@ instance. Inactive mobile devices also 
    can't access local Sync files. Once a device is Inactive, you can erase Sync 
    files from it by selecting *Actions* &rarr; *Wipe Sync Device*. This also 
    signs the device out and removes the account from the client. If the device 
    is offline, this happens when it tries to reconnect. The Actions menu also 
    lets you enable or delete an Inactive device. Deleting a device only removes 
    it from the list of registered devices; it can still reconnect and 
    reregister. 

Awesome! Now that you know how to configure Sync in your @product@ instance, 
it's time to look closer at a use case that warrants special attention for 
administrators: accidental file deletion. 

## Protecting Against Accidental File Deletion [](id=protecting-against-accidental-file-deletion)

Liferay Sync's power rests in its ability to propagate between the @product@ 
instance and connected Sync clients. When a user deletes a file from a connected 
client, Sync also deletes the file in the instance and in any other connected 
clients. Likewise, if a user deletes a file in the instance, Sync also deletes 
the file in all connected clients. In other words, anywhere a user deletes a 
file, Sync deletes it *everywhere*. You're probably thinking, "This is a 
disaster waiting to happen!" Don't fret! @product@'s Recycle Bin is enabled by 
default and lets you recover deleted files. You can access the Recycle Bin from 
each site's *Site Administration* menu. 

+$$$

**Warning:** Liferay Sync automatically propagates file and folder deletion
through the @product@ instance and in all connected clients. If an instance or 
site administrator disables the Recycle Bin, deleted files can't be recovered.

$$$

@product@ instance and site administrators can, of course, disable the Recycle 
Bin. Disabling the Recycle Bin in a site, however, leaves the site vulnerable to
accidental file deletions that propagate through Sync. 

## Ensuring Sync Security [](id=ensuring-sync-security)

As an administrator, you're undoubtedly concerned about the security of all
connections to and from your @product@ instance. As long as your server is 
configured to use HTTPS, Sync clients communicate securely with your instance 
with user-supplied credentials. Users can only access the documents and sites 
they're permitted to access. To support Security Mode in the Sync mobile client 
and securely transmit files, your @product@ server must also use SSL. The next 
section demonstrates how Sync's permissions work with your @product@ instance's 
permissions. 

## Liferay Sync Permissions Demonstration [](id=liferay-sync-permissions-demonstration)

Sync uses @product@'s default permissions to determine files and folders to sync
with the user's devices. It can only sync files a user can access in the 
@product@ instance. After installing the desktop Sync client, you can follow the 
steps below to test this functionality. 

First, enter the text `classified information` into a new text file and save it
on your desktop as `secret.txt`. Then use your browser to sign into your
@product@ instance and create a new user with the user name *secretagent* and
the email address *secretagent\@liferay.com*. Give this user a password and then
create a new private site called *Secret Site*. Create a page on the site and 
add the Documents and Media app to it. Then add the secretagent user to the
Secret Site and grant the *Site Administrator* role to the user. Log in as
secretagent and navigate to the Secret Site. Then upload the `secret.txt`
document to the Documents and Media app. Make sure you also have a user that
isn't a member of the Secret Site and therefore doesn't have access to any of
its documents through Sync. If you don't have such a user, create one now. 

Next, configure your Liferay Sync client to sign in with the secretagent user's
credentials and sync with the Secret Site. Open the Liferay Sync menu from the
system tray and select *Preferences*. In the *Accounts* tab, click the plus icon
at the window's bottom left to add an account. Provide the secretagent user's
credentials and uncheck all @product@ sites except the Secret Site. Now confirm
that Sync downloaded the `secret.txt` file to your new Sync folder. Open it and
check that it contains the text `classified information`. Next, use Sync to
connect to your @product@ instance with the user that doesn't belong to the 
Secret Site. The file doesn't sync because this user isn't a site member. 

Now go to Sync Connector Admin and set the Secret Site's default file 
permissions to View Only. Create a new user, add them to the Secret Site, and 
add their account in your Liferay Sync client. As with the secretagent user, 
Sync downloads the `secret.txt` file to this user's local Sync folder because 
the user is a member of the Secret Site. Now edit and save this file. Even 
though you can edit and save it locally, the edits aren't synced because the 
site's default file permissions are View Only. After attempting the sync, a red 
*x* appears next to the file in the local Sync folder. Right click the file to 
see the error. It confirms the user doesn't have the required permissions. 

![Figure 5: The upload error occurs because the user only has permission to view files.](../../../images/sync-file-permissions-error.png)

To confirm that the error didn't propagate through Sync, open the file in the 
secretagent user's local Sync folder. It still contains the original text. 
Likewise, the original file remains in the site's Documents and Media portlet. 
To get rid of the error in the other user's local Sync folder, return there and 
then right click the file and select *Download From Server*. This replaces the 
file with the latest file in the @product@ instance. 

Now edit `secret.txt` in the secretagent user's local Sync folder. When you
check the file in the other user's local Sync folder and in the @product@ 
instance, notice that Sync propagated the edits. The changes were propagated 
because the secretagent user owns the file in the instance. Owners can do 
anything with their files, even when the site's default file permissions are set 
to View Only.

Congratulations! You've successfully set up a Liferay Sync folder that can only 
be accessed by site members and administrators. You've also seen how Sync's 
default file permissions work. By using @product@'s permissions alongside Sync 
Admin's default file permissions, Sync gives administrators two levels of 
control over files in their sites. 

Great! Now you know how to enable and configure Liferay Sync in your @product@ 
instance. The next article explains how end users can install and configure the 
Sync desktop client. 
