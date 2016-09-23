# SyncManagerDelegate [](id=syncmanagerdelegate)

The `SyncManagerDelegate` class is required to 
[use Screenlets with offline mode](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-ios).
This class receives the events produced in the synchronization process. This 
document describes the class's methods. 

## Methods [](id=methods)

The following method is invoked when the synchronization process is started. The 
number of items to be synced are passed.

    syncManager(manager: SyncManager, itemsCount: UInt)

The following method is invoked when an item synchronization is about to start.

    syncManager(manager: SyncManager, onItemSyncScreenlet screenlet: String, 
        startKey: String, attributes: [String:AnyObject])

- `screenlet`: the screenlet name that stored this cache element
- `startKey`: the cache key where the item is stored
- `attributes`: some attributes stored together with the element. The specific 
  attributes depend on the type of the entry. For more details, read the 
  screenlet reference documentation.

The following method is invoked when an item synchronization is successfully 
completed.

    syncManager(manager: SyncManager, onItemSyncScreenlet screenlet: String, 
        completedKey: String, attributes: [String:AnyObject])

- `screenlet`: the screenlet name that stored this cache element
- `completedKey`: the cache key where the item is stored
- `attributes`: some attributes stored together with the element. The specific 
  attributes depend on the type of the entry. For more details, read the 
  screenlet reference documentation.

The following method is invoked when an item synchronization fails.

    syncManager(manager: SyncManager, onItemSyncScreenlet screenlet: String, 
        failedKey: String, attributes: [String:AnyObject], error: NSError)

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

- `screenlet`: the screenlet name that stored this cache element
- `conflictedKey`: the cache key where the item is stored
- `remoteValue`: the value stored in the server for the item being synchronized
- `localValue`: the value stored in the cache for the item being synchronized
- `resolve`: this is the continuation function to be called with the action 
  result. 

Supported values for `resolve` are:

- `UseRemote`: the remote version is overwritten with the local one. Both 
  the local cache and the portal have the same version.
- `UseLocal`: the local version is overwritten with the remote one. Both 
  the local cache and the portal have the same version
- `Discard`: the local version is removed and the remote one isn't 
  overwritten. 
- `Ignore`:  data is not changed, so the next synchronization will detect the 
  conflict again.
