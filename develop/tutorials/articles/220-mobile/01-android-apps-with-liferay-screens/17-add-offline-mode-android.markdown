# Adding Offline Mode Support to Your Android Screenlet [](id=adding-offline-mode-support-to-your-android-screenlet)

Offline mode lets Screenlets function without a network connection. For offline 
mode to work with your Screenlet, you must manually add support for it. 
Fortunately, Liferay Screens 2.0 introduced a simpler way of implementing 
offline mode support in Android Screenlets: 

- Update your Screenlet's classes to leverage the offline mode cache
- Create an event class (if your Screenlet doesn't already have one)

Implementing these steps, however, differs somewhat depending on how your 
Screenlet communicates with the server: 

- **A write Screenlet:** writes data to a server. The Add Bookmark Screenlet
  created in the 
  [basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets) 
  is a good example of a simple write Screenlet. It asks the user to enter a URL 
  and a title, which it then sends to the Bookmarks portlet in @product@ 
  to create a bookmark. 
- **A read Screenlet:** reads data from a server. The Web Content Display 
  Screenlet included with Liferay Screens is a good example of a read Screenlet. 
  It retrieves web content from @product@ for display in an Android 
  app. 
  [Click here](/develop/reference/-/knowledge_base/7-0/webcontentdisplayscreenlet-for-android) 
  to see Web Content Display Screenlet's documentation. 

This tutorial shows you how to add offline mode support to both kinds of 
Screenlets. You'll start with write Screenlets, using Add Bookmark Screenlet as 
an example. Before getting started, be sure to read 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets) 
to familiarize yourself with Add Bookmark Screenlet's code. You'll conclude by
learning how offline mode implementation in read Screenlets differs from that of
write Screenlets. 

## Adding Offline Mode Support to Write Screenlets [](id=adding-offline-mode-support-to-write-screenlets)

To add offline mode support to write Screenlets, you'll follow these steps: 

1. [Create or update the event class](/develop/tutorials/-/knowledge_base/7-0/adding-offline-mode-support-to-your-android-screenlet#create-or-update-the-event-class). 
2. [Update the listener interface](/develop/tutorials/-/knowledge_base/7-0/adding-offline-mode-support-to-your-android-screenlet#update-the-listener). 
3. [Update the Interactor class](/develop/tutorials/-/knowledge_base/7-0/adding-offline-mode-support-to-your-android-screenlet#update-the-interactor-class). 
4. [Update the Screenlet class](/develop/tutorials/-/knowledge_base/7-0/adding-offline-mode-support-to-your-android-screenlet#update-the-screenlet-class). 
5. [Sync the cache with the server](/develop/tutorials/-/knowledge_base/7-0/adding-offline-mode-support-to-your-android-screenlet#sync-the-cache-with-the-server). 

Each of the sections that follow detail one of these steps. You'll begin by 
creating or updating the event class. 

### Create or Update the Event Class [](id=create-or-update-the-event-class)

Recall from the basic Screenlet creation tutorial that an event class is 
required to handle communication between Screenlet components. Also recall that 
many Screenlets can use the event class included with Screens, `BasicEvent`, as 
their event class. For offline mode to work, however, you must create an event 
class that extends `CacheEvent` 
([click here](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/event/CacheEvent.java) 
to see `CacheEvent`). Your event class has one primary responsibility: store and 
provide access to the arguments passed to the Interactor. To accomplish this, 
your event class should do these things: 

- Extend `CacheEvent`. For the arguments, define variables and public getter 
  methods. 
- Define a no-argument constructor that only calls the corresponding superclass 
  constructor. 
- Define a constructor that sets the Interactor's arguments. 

In the case of Add Bookmark Screenlet, the arguments are the bookmark's URL, 
folder ID, and title. For example, here's the full code for this Screenlet's 
event class, `BookmarkEvent`: 

    public class BookmarkEvent extends CacheEvent {

        private String url; 
        private String title; 
        private long folderId; 

        public BookmarkEvent() { 
            super(); 
        }

        public BookmarkEvent(String url, String title, long folderId) {

            this.url = url; 
            this.title = title; 
            this.folderId = folderId; 
        }

        public String getURL() { 
            return url; 
        }

        public String getTitle() { 
            return title; 
        }

        public long getFolderId() { 
            return folderId; 
        } 
    }

Next, you'll update the listener. 

### Update the Listener [](id=update-the-listener)

Recall from the basic Screenlet creation tutorial that the listener interface 
defines a success method and a failure method. This lets implementing classes 
respond to the server call's success or failure. Listeners that support offline 
mode offer the same functionality, although differently. Offline mode listeners 
must extend `BaseCacheListener`, which defines only this `error` method: 

    void error(Exception e, String userAction);

By extending `BaseCacheListener`, your listener no longer needs an explicit 
failure method because it inherits the `error` method instead. This `error` 
method also includes an argument for the user action that triggered the 
exception. 

You can therefore update your listener to support offline mode by extending 
`BaseCacheListener` and deleting the failure method. For example, here's Add 
Bookmark Screenlet's listener, `AddBookmarkListener`, after being updated to 
support offline mode: 

    public interface AddBookmarkListener extends BaseCacheListener {

        onAddBookmarkSuccess(); 
    }

Note that you must also remove any failure method implementations (such as in an 
activity or fragment that implements the listener), and replace any failure 
method calls with `error` method calls. You'll do the latter next when updating 
the Interactor class. 

### Update the Interactor Class [](id=update-the-interactor-class)

Recall from the basic Screenlet creation tutorial that Interactor classes extend 
`BaseRemoteInteractor` with the listener and event as type arguments. To support 
offline mode, your Interactor class must instead extend one of the following 
classes. Which one depends on whether your Interactor writes data to or reads 
data from a server: 

- `BaseCacheWriteInteractor`: writes data to a server. Extend this class if your 
  Screenlet is a write Screenlet. 
  [Click here](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/BaseCacheWriteInteractor.java) 
  to see this class. 
- `BaseCacheReadInteractor`: reads data from a server. Extend this class if your 
  Screenlet is a read Screenlet. 
  [Click here](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/BaseCacheReadInteractor.java) 
  to see this class. 

In either case, the type arguments are the same: the listener and the event. 
Note, however, that the event must extend `CacheEvent` as described above. For 
example, since Add Bookmark Screenlet is a write Screenlet, to support offline 
mode its Interactor class must extend `BaseCacheWriteInteractor` with 
`AddBookmarkListener` and `AddBookmarkEvent` as type arguments: 

    public class AddBookmarkInteractor extends
        BaseCacheWriteInteractor<AddBookmarkListener, BookmarkEvent> {...

You must also make a few changes to the Interactor class's code. The main change 
is that the `execute` method now takes the event instead of var args. You can 
then retrieve the data you need from the event. For example, to support offline 
mode, the `execute` method in `AddBookmarkInteractor` takes `BookmarkEvent` as 
an argument. The bookmark's URL, title, and folder ID are then retrieved from 
the event for use in the `getJSONObject` method that makes the server call. The 
`execute` method finishes by setting the resulting `JSONObject` to the event, 
and then returning the event: 

    @Override 
    public BookmarkEvent execute(BookmarkEvent bookmarkEvent) throws Exception {

        validate(bookmarkEvent.getUrl(), bookmarkEvent.getFolderId());

        JSONObject jsonObject = getJSONObject(bookmarkEvent.getUrl(), bookmarkEvent.getTitle(), 
            bookmarkEvent.getFolderId());
        bookmarkEvent.setJSONObject(jsonObject); 
        return bookmarkEvent; 
    }

You should also change the `onSuccess` method to take an instance of your event 
class instead of `BasicEvent`. This is the only change you need to make to this 
method. For example, the `onSuccess` method in `AddBookmarkInteractor` supports 
offline mode by taking a `BookmarkEvent` instead of a `BasicEvent`: 

    @Override 
    public void onSuccess(BookmarkEvent event) {
        getListener().onAddBookmarkSuccess(); 
    }

Now make the same change to the `onFailure` method, but replace the listener's 
failure method call with a call to the `error` method inherited from 
`BaseCacheListener` (see the listener section above for an explanation of this 
method). For the `error` method's arguments, you can retrieve the exception from 
the event and define a string to use as the user action. For example, to 
support offline mode the `onFailure` method in `AddBookmarkInteractor` takes a 
`BookmarkEvent` instead of a `BasicEvent`. Also, the method's `error` call 
defines the "ADD_BOOKMARK" string to indicate that the error occurred while 
trying to add a bookmark to the server: 

    @Override public void onFailure(BookmarkEvent event) {
        getListener().error(event.getException(), "ADD_BOOKMARK"); 
    }

### Update the Screenlet Class [](id=update-the-screenlet-class)

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

### Sync the Cache with the Server [](id=sync-the-cache-with-the-server)

When using a write Screenlet that supports offline mode, new data written to the 
cache must also be synced with the server. The write Screenlets included with 
Liferay Screens do this for you. However, you must do this manually when using a 
custom write Screenlet. You should do this in the activity or fragment that uses 
the Screenlet--exactly where in this activity or fragment is up to you though. 

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

Next, you'll learn how to add offline mode support to read Screenlets. 

## Adding Offline Mode Support to Read Screenlets [](id=adding-offline-mode-support-to-read-screenlets)

Implementing offline mode support in a read Screenlet is almost identical to 
doing so in a write Screenlet. There are two small differences, though: 

1. You can still pass arguments to the Interactor with var args instead of an 
   event. 

2. The Interactor class must extend `BaseCacheReadInteractor`, which forces you 
   to implement the `getIdFromArgs` method. This method takes the var args passed 
   to the Interactor so you can return the argument that identifies your entity. 
   Note that because this method requires you to return a `String`, you'll often 
   use `String.valueOf` to return non-string arguments as a string. For example, 
   the `getIdFromArgs` implementation in Comment Display Screenlet's 
   `CommentLoadInteractor` retrieves the comment ID (a `long`) from the first
   argument and then returns it as a `String`: 

        @Override 
        protected String getIdFromArgs(Object... args) { 
            long commentId = (long) args[0]; 
            return String.valueOf(commentId); 
        }

That's it! Next, you'll learn about list Screenlets and offline mode support. 

### Adding Offline Mode Support to List Screenlets [](id=adding-offline-mode-support-to-list-screenlets)

A list Screenlet is a special type of read Screenlet that displays entities in a 
list. Recall from the 
[list Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets) 
that list Screenlets have a model class that encapsulates entities retrieved
from the server. To support offline mode, a list Screenlet's event class must
extend `ListEvent` with the model class as a type argument. This event class
also needs three things: 

1. A default constructor 
2. A `getListKey` method that returns a unique ID to store the entity with 
3. A `getModel` method that returns the model instance 

The list Screenlet creation tutorial contains example model and event classes 
that support offline mode for Bookmark List Screenlet. Click the following links 
to see the sections in the tutorial that show you how to create these classes: 

- [Creating the Model Class](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-model-class) 
- [Creating the Event](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-screenlets-event)

And that's all! Now you know how to support offline mode in your Screenlets. 

## Related Topics [](id=related-topics)

[Using Offline Mode in Android](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-android)

[Architecture of Offline Mode in Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)

[Creating Android List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets)
