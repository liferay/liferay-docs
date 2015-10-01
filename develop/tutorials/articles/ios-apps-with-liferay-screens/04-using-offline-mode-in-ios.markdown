# Using Offline Mode in iOS

Offline mode in Liferay Screens lets your apps function when connectivity is 
unavailable or intermittent. Even though the steady march of technology makes 
connections more stable and prevalent, there are still plenty of places the 
internet has trouble reaching. Areas with complex terrain, including cities with 
large buildings, often lack stable connections. Remote areas typically don't 
have a connection at all. Using Screens's offline mode in your apps gives your 
users flexibility in these situations. 

This tutorial shows you how to use offline mode in Screenlets. For an 
explanation of how offline mode works, see the tutorial 
[Architecture of Offline Mode in Liferay Screens](http://www.liferay.com/). 
Offline mode's architecture is the same on iOS and Android, although its use on 
these platforms differs.

## Configuring Screenlets for Offline Mode

If you want to enable the offline mode in any of your screenlets, you just need 
to configure the attribute offlinePolicy. This attribute can take four possible 
values. For a description of these values, see the section 
[Using Policies with Offline Mode](http://www.liferay.com/) 
in the offline mode architecture tutorial. Note that each Screenlet behaves a 
bit differently with offline mode. For specific details, see the 
[Screenlet reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios). 

## Handling Synchronization

Under some scenarios, values stored in the local cache need to be synchronized 
with the portal ones. For that purpose you need to use the `SyncManager` class. 
This class is responsible for sending the information stored in the local cache 
that hasn’t been sent to the portal yet.

Use the following steps to start a synchronization process: 

1. Create an instance of `SyncManager` class. You need to pass a `CacheManager` 
object in the constructor. You can get the current cache manager using 
`SessionContext.currentCacheManager`. 

2. Set the delegate property. This delegate object will receive the events 
produced in the synchronization process. For more details on the delegate 
methods, read the section below.

3. Make sure you keep a strong reference to the `SyncManager` object while the 
process is running.

## Understanding SyncManagerDelegate

The following method is invoked when the synchronization process is started. The 
number of items to be synch-ed will be passed.

    syncManager(manager: SyncManager, itemsCount: UInt)

The following method is invoked when an item synchronization is about to start.

    syncManager(manager: SyncManager, onItemSyncScreenlet screenlet: String, 
        startKey: String, attributes: [String:AnyObject])

This method's attributes are described as follows:

- `screenlet`: the screenlet name that stored this cache element
- `startKey`: the cache key where the item is stored
- `attributes`: some attributes stored together with the element. The specific 
attributes will depend on the type of the entry. For more details, read the 
screenlet reference documentation.

The following method is invoked when an item synchronization is successfully 
completed.

    syncManager(manager: SyncManager, onItemSyncScreenlet screenlet: String, 
        completedKey: String, attributes: [String:AnyObject])

This method's attributes are described as follows: 

- `screenlet`: the screenlet name that stored this cache element
- `completedKey`: the cache key where the item is stored
- `attributes`: some attributes stored together with the element. The specific 
attributes will depend on the type of the entry. For more details, read the 
screenlet reference documentation.

The following method is invoked when an item synchronization is failed.

    syncManager(manager: SyncManager, onItemSyncScreenlet screenlet: String, 
        failedKey: String, attributes: [String:AnyObject], error: NSError)

This method's attributes are described as follows:

- `screenlet`: the screenlet name that stored this cache element
- `failedKey`: the cache key where the item is stored
- `attributes`: some attributes stored together with the element. The specific 
attributes will depend on the type of the entry. For more details, read the 
screenlet reference documentation.
- `error`: the error occurred in the synchronization

The following method is invoked when an item synchronization detects a conflict. 
The method must invoke asynchronously a 
[continuation](https://en.wikipedia.org/wiki/Continuation-passing_style) 
argument with the conflict action result.

    syncManager(manager: SyncManager, onItemSyncScreenlet screenlet: String, 
        conflictedKey: String, remoteValue: AnyObject, localValue: AnyObject, 
        resolve: SyncConflictResolution -> ())

This method's attributes are described as follows:

- `screenlet`: the screenlet name that stored this cache element
- `conflictedKey`: the cache key where the item is stored
- `remoteValue`: the value stored in the server for the item being synchronized
- `localValue`: the value stored in the cache for the item being synchronized
- `resolve`: this is the continuation function to be called with the action 
result. 

Supported values for `resolve` are:

- `UseRemote`: the remote version will be overwritten with the local one. Both 
the local cache and the portal will have the same version.
- `UseLocal`: the local version will be overwritten with the remote one. Both 
the local cache and the portal will have the same version
- `Discard`: the local version will be removed and the remote one won’t be 
overwritten. 
- `Ignore`:  gmdata is not changed, so the next synchronization will detect the 
conflict again.

## Related Topics

[Architecture of Offline Mode in Liferay Screens](http://www.liferay.com/)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)

[Using Offline Mode in Android](http://www.liferay.com/)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)
