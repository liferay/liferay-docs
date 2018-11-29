# Update the Screenlet Class [](id=update-the-screenlet-class)

Updating the Screenlet class for offline mode is straightforward. In the 
Screenlet class's `onUserAction` method, you'll change the call to the 
Interactor's `start` method so that it takes only an event as an argument. 
Before doing this, however, you should create an event instance and set its 
cache key. A cache key is a value that identifies an entity in the local cache. 
This lets you retrieve the entity from the cache for later synchronization with 
the server. 

In Add Bookmark Screenlet, for example, a bookmark's URL makes a good cache key. 
To support offline mode, the `onUserAction` method in `AddBookmarkScreenlet` 
creates a new `BookmarkEvent` instance with a bookmark's data and then uses the 
`setCacheKey` method to set the bookmark's URL as the event's cache key. The 
Interactor's start method takes this event as its argument: 

    BookmarkEvent event = new BookmarkEvent(url, title, folderId);
    event.setCacheKey(url); 
    interactor.start(event);

Note that you don't have to set a cache key to use offline mode. Instead, you 
can pass the event to the `start` method without calling `setCacheKey`. However, 
this means that you'll only be able to access the most recent entity in the 
cache. 

That's it! Your write Screenlet now supports offline mode. There's one more 
detail to keep in mind, however, when using the Screenlet: syncing the cache 
with the server. You'll learn about this next. 

## Related Topics [](id=related-topics)

[Create or Update the Event Class](/develop/tutorials/-/knowledge_base/7-1/create-or-update-the-event-class)

[Update the Listener](/develop/tutorials/-/knowledge_base/7-1/update-the-listener)

[Update the Interactor Class](/develop/tutorials/-/knowledge_base/7-1/update-the-interactor-class)

[Sync the Cache with the Server](/develop/tutorials/-/knowledge_base/7-1/sync-the-cache-with-the-server)
