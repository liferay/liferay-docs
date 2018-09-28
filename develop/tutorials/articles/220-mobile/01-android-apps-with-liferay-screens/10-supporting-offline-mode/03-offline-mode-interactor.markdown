# Update the Interactor Class [](id=update-the-interactor-class)

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

If your Screenlet is a write Screenlet, you must change the Interactor's 
`execute` method to take the event instead of var args (in read Screenlets, this 
method can still take var args). You can then retrieve the data you need from 
the event. For example, to support offline mode, the `execute` method in 
`AddBookmarkInteractor` takes `BookmarkEvent` as an argument. The bookmark's 
URL, title, and folder ID are then retrieved from the event for use in the 
`getJSONObject` method that makes the server call. The `execute` method finishes 
by setting the resulting `JSONObject` to the event, and then returning the 
event: 

    @Override 
    public BookmarkEvent execute(BookmarkEvent bookmarkEvent) throws Exception {

        validate(bookmarkEvent.getUrl(), bookmarkEvent.getFolderId());

        JSONObject jsonObject = getJSONObject(bookmarkEvent.getUrl(), bookmarkEvent.getTitle(), 
            bookmarkEvent.getFolderId());
        bookmarkEvent.setJSONObject(jsonObject); 
        return bookmarkEvent; 
    }

If your Screenlet is a read Screenlet, then you must also implement the 
`getIdFromArgs` method of `BaseCacheReadInteractor`. This method takes the var 
args passed to the Interactor so you can return the argument that identifies 
your entity. Note that because this method requires you to return a `String`, 
you'll often use `String.valueOf` to return non-string arguments as a string. 
For example, the `getIdFromArgs` implementation in Comment Display Screenlet's 
`CommentLoadInteractor` retrieves the comment ID (a `long`) from the first
argument and then returns it as a `String`: 

     @Override 
     protected String getIdFromArgs(Object... args) { 
         long commentId = (long) args[0]; 
         return String.valueOf(commentId); 
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

## Related Topics [](id=related-topics)

[Create or Update the Event Class](/develop/tutorials/-/knowledge_base/7-1/create-or-update-the-event-class)

[Update the Listener](/develop/tutorials/-/knowledge_base/7-1/update-the-listener)

[Update the Screenlet Class](/develop/tutorials/-/knowledge_base/7-1/update-the-screenlet-class)

[Sync the Cache with the Server](/develop/tutorials/-/knowledge_base/7-1/sync-the-cache-with-the-server)
