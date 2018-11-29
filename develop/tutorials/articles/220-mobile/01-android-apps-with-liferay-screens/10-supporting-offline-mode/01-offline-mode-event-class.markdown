# Create or Update the Event Class [](id=create-or-update-the-event-class)

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

## Related Topics [](id=related-topics)

[Update the Listener](/develop/tutorials/-/knowledge_base/7-1/update-the-listener)

[Update the Interactor Class](/develop/tutorials/-/knowledge_base/7-1/update-the-interactor-class)

[Update the Screenlet Class](/develop/tutorials/-/knowledge_base/7-1/update-the-screenlet-class)

[Sync the Cache with the Server](/develop/tutorials/-/knowledge_base/7-1/sync-the-cache-with-the-server)
