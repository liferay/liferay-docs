# Configuring Liferay Sync [](id=configuring-liferay-sync)

Sync Connector lets you manage how, or if, clients connect to your @product@
server. You can also configure default file permissions on a per-Site basis, and
manage the devices that connect to your @product@ instance. To access Sync
Connector, select *Control Panel* &rarr; *Configuration* &rarr; *Sync Connector
Admin*. 

Sync Connector Admin has three tabs: 

1.  **Settings:** Control Sync's general behavior. These settings apply globally 
    to Sync. 

    ![Figure 1: The Control Panel's Configuration section contains Sync Connector Admin.](../../../../images/sync-admin-01.png)

    **Allow the use of Sync?:** Whether Sync is enabled.

    **Allow users to sync their personal Sites?:** Whether users can sync data 
    with their personal Sites. 

    **Allow LAN Syncing?:** Whether desktop clients attempt to download 
    updates from other desktop clients on the same local network before 
    downloading from the server. This can help reduce server load and increase 
    data transfer speeds. Note that LAN syncing only works with clients that 
    also enable it. 

    **Max Connections:** The maximum number of simultaneous connections each 
    client is allowed per account. For example, if Max Connections is three, a 
    client can simultaneously upload or download up to three files for each 
    account. Note, this setting operates on a per client basis. If Max 
    Connections is set to three and a user has two clients connected to an 
    account (which is possible if Sync is installed on two different 
    machines), then the user is effectively allowed six simultaneous 
    connections. While increasing Max Connections can speed up file transfers 
    it also places a heavier load on the server. *Max Connections* is set to 
    one by default. 

    **Poll Interval:** The frequency in seconds that clients automatically 
    check the @product@ instance for updates. For example, if set to ten, 
    connected clients check the instance for updates every ten seconds. The 
    default Poll Interval is five. 

    **Max Download Rate:** The maximum transfer rate, in bytes, at which 
    clients can download. A value of 0 specifies no limit. This setting takes 
    precedence over clients' download rate setting. 

    **Max Upload Rate:** The maximum transfer rate, in bytes, at which clients 
    can upload. A value of 0 specifies no limit. This setting takes precedence 
    over clients' upload rate setting. 

    **Force Security Mode:** Whether to force security mode on mobile clients. 
    Security mode encrypts Sync files on the device and requires a passcode 
    when accessing the Sync mobile app. 

2.  **Sites:** Control Sync on a per-Site basis. 

    ![Figure 2: Sync Connector Admin's Sites tab lets you manage Sync on a per-Site basis.](../../../../images/sync-admin-02.png)
 
    For each Site in the @product@ instance, the Sites tab lists each Site's 
    default file permissions (more on this in a moment) and whether Sync is 
    enabled for that Site. Sync is enabled by default for all Sites. To disable 
    Sync for a Site, click the Site's *Actions* button 
    (![Actions](../../../../images/icon-actions.png)) and select 
    *Disable Sync Site*. **Please use caution** when disabling Sync for a Site, 
    as doing so **deletes** files for that Site from the Sync clients. Disabling
    Sync for a Site, however, doesn't affect the Site's files on the server. 

    +$$$

    **Warning:** Disabling Sync for specific Sites from Sync Connector Admin can 
    result in data loss across clients. If Sync is disabled for a Site users are 
    currently syncing, any files in the clients' sync folders for that Site are 
    automatically deleted from their clients. If a user is offline when Sync is 
    disabled for a Site, any offline changes or additions they make are deleted 
    upon client reconnection. 

    $$$

    You can enable Sync for a Site by selecting *Enable Sync Site* from its 
    Actions button. Make sure that each Site for which Sync is enabled has a 
    Documents and Media app on at least one of its pages. If a Site doesn't have 
    the app on any of its pages and users click the *Open Website* link from 
    their Sync menus, the error message *The requested resource was not found* 
    appears. 

    The Sites tab also sets default file permissions for files uploaded from
    Sync clients. The process for setting permissions is nearly the same as for
    enabling or disabling Sync for Sites. To set the default file permissions
    for a single Site, click its Actions button and select *Default File
    Permissions*. This lets you select the default file permissions for that
    Site. Click *Choose* for the permissions you want to use. 

    ![Figure 3: Click *Choose* to select the default file permissions for a Site in Sync.](../../../../images/sync-admin-03.png)

    To set the default file permissions for several Sites, select the checkboxes 
    for the Sites, click the *Default File Permissions* link that appears above 
    the table, and select the permissions you want to use. Default file 
    permissions might behave differently than you'd expect. They control *only* 
    the permissions for new files uploaded through the Sync clients; they don't 
    affect permissions for uploading or restrict document owners (the user who 
    originally uploaded a document) in any way. For example, even if you set a 
    Site's default file permissions to View Only, that Site's users can still 
    upload new documents to the Site. The file's owner has edit permission; the 
    rest of the Site's users have the View Only permission. 

3.  **Devices:** View and manage the devices registered with Sync. 

    ![Figure 4: Sync Connector Admin's Devices tab lists all the devices Sync has registered.](../../../../images/sync-admin-devices.png)

    Each row in the Devices tab's table represents a device. The *Name* column 
    lists the user that registered the device. The remaining columns list each 
    device's location, client type, client build number, last connection date, 
    and status. Each device's Actions button 
    (![Actions](../../../../images/icon-actions.png)) manages that 
    device. You can change a device's status from Active to Inactive by 
    selecting *Actions* &rarr; *Disable Sync Device*. Inactive devices can't 
    sync. Inactive mobile devices also can't access local Sync files. Once
    a device is Inactive, you can erase Sync files from it by selecting
    *Actions* &rarr; *Wipe Sync Device*. This also signs the device out and
    removes the account from the client. If the device is offline, this happens
    when it tries to reconnect. The Actions menu also enables or deletes an
    Inactive device. Deleting a device only removes it from the list of
    registered devices; it can still reconnect and reregister. 
