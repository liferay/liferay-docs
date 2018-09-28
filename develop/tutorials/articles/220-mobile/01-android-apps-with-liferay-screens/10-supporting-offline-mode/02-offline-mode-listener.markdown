# Update the Listener [](id=update-the-listener)

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

## Related Topics [](id=related-topics)

[Create or Update the Event Class](/develop/tutorials/-/knowledge_base/7-1/create-or-update-the-event-class)

[Update the Interactor Class](/develop/tutorials/-/knowledge_base/7-1/update-the-interactor-class)

[Update the Screenlet Class](/develop/tutorials/-/knowledge_base/7-1/update-the-screenlet-class)

[Sync the Cache with the Server](/develop/tutorials/-/knowledge_base/7-1/sync-the-cache-with-the-server)
