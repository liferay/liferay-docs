# Sync the Cache with the Server [](id=sync-the-cache-with-the-server)

When using a write Screenlet that supports offline mode, new data written to the 
cache must also be synced with the server. The write Screenlets included with 
Liferay Screens do this for you. However, you must do this manually when using a 
custom write Screenlet. You should do this in the activity or fragment that uses 
the Screenlet--exactly where in this activity or fragment is up to you though. 

+$$$

**Note:** You don't have to do this when adding offline mode support to read 
Screenlets. 

$$$

To sync a write Screenlet's data with the server manually, follow these steps:

1. Retrieve the event that needs to be synced with the server. To do this, you
   must first get the cache key associated with the event. Then use the key as 
   an argument to the `Cache.getObject` method. 
2. Call the Interactor with the event. This syncs the data with the server. 

For example, the following code uses the `Cache.findKeys` method to retrieve all 
`BookmarkEvent` keys in the cache. The loop that follows then retrieves the 
event that corresponds to each key, and syncs it to the server by calling the 
Interactor: 

    String[] keys = Cache.findKeys(BookmarkEvent.class, groupId, userId, locale, 0, 
        Integer.MAX_VALUE); 
    for (String key : keys) {

        BookmarkEvent event = Cache.getObject(BookmarkEvent.class, groupId, userId, key); 
        new AddBookmarkInteractor().execute(event); 
    }

Note that if you opted not to set a cache key in your Screenlet class, you can 
pass `null` in place of a key. 

Also note that you can use Android's `SharedPreferences` APIs as an alternative 
way to store and retrieve cache keys. For example, the following code stores 
cache keys in shared preferences: 

    SharedPreferences sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE); 
    HashSet<String> values = new HashSet<>();
    sharedPreferences.edit().putStringSet("keysToSync", values).apply();

You can then retrieve the keys as you would retrieve any other key-value set
from shared preferences:

    SharedPreferences sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE); 
    HashSet<String> keysToSync = sharedPreferences.getStringSet("keysToSync", new HashSet<>());
