# SyncManagerDelegate

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
