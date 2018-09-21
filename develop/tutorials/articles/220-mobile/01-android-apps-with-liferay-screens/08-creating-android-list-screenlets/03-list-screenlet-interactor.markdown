# Creating the Interactor [](id=creating-the-interactor-0)

Recall from 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#creating-the-screenlets-Interactor-class) 
that Interactors retrieve and process a server call's results. Also recall that 
the following components make up an Interactor: 

1. Event
2. Listener
3. Interactor Class

These components perform the same basic functions in list Screenlets as they do 
in non-list Screenlets. Creating them, however, is a bit different. Each of the 
following sections show you how to create one of these components. First, you'll 
create the event.

### Creating the Event [](id=creating-the-screenlets-event)

Screens uses the 
[EventBus](http://greenrobot.org/eventbus/) 
library to handle communication within Screenlets. Screenlet components 
therefore communicate with each other by using event classes that contain the 
server call's results. Your list Screenlet's event class must extend 
[the `ListEvent` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/ListEvent.java) 
parameterized with your model class. Your event class should also contain a 
private instance variable for the model class, a constructor that sets this 
variable, and a no-argument constructor that calls the superclass constructor. 
For example, Bookmark List Screenlet's event class (`BookmarkEvent`) 
communicates `Bookmark` objects. It therefore extends `ListEvent` with 
`Bookmark` as a type argument, and defines a private `Bookmark` variable that 
its `BookmarkEvent(Bookmark bookmark)` constructor sets: 

    public class BookmarkEvent extends ListEvent<Bookmark> {

	      private Bookmark bookmark;

	      public BookmarkEvent() {
		        super();
	      }

	      public BookmarkEvent(Bookmark bookmark) {
      		  this.bookmark = bookmark;
	      }
        ...

You must also implement `ListEvent`'s abstract methods in your event class. Note 
that these methods support 
[offline mode](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-android). 
Although these methods are briefly described here, supporting offline mode in 
your Screenlets is addressed in detail in a separate tutorial. 

- `getListKey`: returns the ID for the cache. This ID is typically the data 
  each list row displays. For example, the `getListKey` method in 
  `BookmarkEvent` returns the bookmark's URL: 

        @Override
        public String getListKey() {
            return bookmark.getUrl();
        }

- `getModel`: unwraps the model entity to the cache by returning the model class 
  instance. For example, the `getModel` method in `BookmarkEvent` method returns 
  the bookmark: 

        @Override
        public Bookmark getModel() {
            return bookmark;
        }

Next, you'll create your Screenlet's listener. 

### Creating the Listener [](id=creating-the-screenlets-listener)

Recall that listeners let the app developer respond to events that occur in 
Screenlets. For example, an app developer using Login Screenlet in an activity 
must implement `LoginListener` in that activity to respond to login success or 
failure. When creating a list Screenlet, however, you don't have to create a 
separate listener. Developers can use your list Screenlet in an activity or 
fragment by implementing 
[the `BaseListListener` interface](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java) 
parameterized with your model class. For example, to use Bookmark List 
Screenlet in an activity, an app developer's activity declaration could look 
like this: 

    public class BookmarkListActivity extends AppCompatActivity 
        implements BaseListListener<Bookmark> {...

The `BaseListListener` interface defines the following methods that the app 
developer can implement in their activity or fragment: 

- `void onListPageFailed(int startRow, Exception e)`: Responds to the 
  Screenlet's failure to retrieve entities from the server. 

- `void onListPageReceived(int startRow, int endRow, List<E> entries, int rowCount)`: 
  Responds to the Screenlet's success in retrieving entities from the server. 

- `void onListItemSelected(E element, View view)`: Responds to a user selection 
  in the list. 

If these methods meet your list Screenlet's needs, then you can move on to the 
next section in this tutorial. If you want to let app developers respond to more 
actions, however, you must create your own listener that extends 
`BaseListListener` parameterized with your model class. For example, Bookmark 
List Screenlet contains such a listener: `BookmarkListListener`. This listener 
defines a single method that notifies the app developer when the Interactor is 
called: 

    public interface BookmarkListListener extends BaseListListener<Bookmark> {
        void interactorCalled();
    }

Next, you'll create the Interactor class. 

### Creating the Interactor Class [](id=creating-the-interactor-class)

Recall that as an Interactor's central component, the Interactor class makes the 
service call to retrieve entities from @product@, and processes the results of 
that call. The list Screenlet framework's 
[`BaseListInteractor` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) 
provides most of the functionality that Interactor classes in list Screenlets 
require. You must, however, extend `BaseListInteractor` to make your service 
calls and handle their results via your model and event classes. Your Interactor 
class must therefore extend `BaseListInteractor`, parameterized with 
`BaseListInteractorListener<YourModelClass>` and your event class. For example, 
Bookmark List Screenlet's Interactor class, `BookmarkListInteractor`, extends 
`BaseListInteractor` parameterized with `BaseListInteractorListener<Bookmark>` 
and `BookmarkEvent`: 

    public class BookmarkListInteractor extends 
        BaseListInteractor<BaseListInteractorListener<Bookmark>, BookmarkEvent> {...

Your Interactor must also override the methods needed to make the server call 
and process the results: 

- `getPageRowsRequest`: Retrieves the specified page of entities. In the example 
  `BookmarkListInteractor`, this method first uses the `args` parameter to 
  retrieve the ID of the folder to retrieve bookmarks from. It then sets the 
  comparator (more on this shortly) if the app developer sets one when inserting 
  the Screenlet XML in a fragment or activity. The `getPageRowsRequest` method 
  finishes by calling `BookmarksEntryService`'s `getEntries` method to retrieve 
  a page of bookmarks. Note that the service call, like the service call in the 
  [basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#creating-the-screenlets-Interactor-class), 
  uses `LiferayServerContext.isLiferay7()` to check the portal version to make 
  sure the correct service instance is used. This isn't required if you only 
  plan to use your Screenlet with one portal version. Also note that the 
  `groupId` variable used to make the service calls isn't set anywhere in 
  `getPageRowsRequest` or `BookmarkListInteractor`. Interactors that extend 
  `BaseListInteractor`, like `BookmarkListInteractor`, inherit this variable via 
  the Screens framework. You'll set it when you create the Screenlet class. 
  Here's `BookmarkListInteractor`'s complete `getPageRowsRequest` method: 

        @Override
        protected JSONArray getPageRowsRequest(Query query, Object... args) throws Exception {
            long folderId = (long) args[0];

            if (args[1] != null) {
                query.setComparator((String) args[1]);
            }

            if (LiferayServerContext.isLiferay7()) {
                return new BookmarksEntryService(getSession()).getEntries(groupId, folderId, 
                    query.getStartRow(), query.getEndRow(), query.getComparatorJSONWrapper());
            } else {
                return new com.liferay.mobile.android.v62.bookmarksentry.BookmarksEntryService(
                    getSession()).getEntries(groupId, folderId, query.getStartRow(), 
                    query.getEndRow(), query.getComparatorJSONWrapper());
            }
        }

    You might now be asking yourself what a comparator is. A comparator is a 
    class in the @product@ instance that sorts a portlet's entities. For 
    example, the Bookmarks portlet contains several comparators that can sort 
    entities by different criteria. 
    [Click here](https://github.com/liferay/liferay-portal/tree/master/modules/apps/collaboration/bookmarks/bookmarks-api/src/main/java/com/liferay/bookmarks/util/comparator) 
    to see these comparators. Although it's not required, you can develop your 
    list Screenlet to use a comparator to sort its entities. Since Bookmark List 
    Screenlet supports comparators, you'll see more of this as you progress 
    through this tutorial. 

- `getPageRowCountRequest`: Retrieves the number of entities, to enable 
  pagination. In the example `BookmarkListInteractor`, this method first uses 
  the `args` parameter to get the ID of the folder in which to count bookmarks. 
  It then calls `BookmarksEntryService`'s `getEntriesCount` method to retrieve 
  the number of bookmarks:

        @Override
        protected Integer getPageRowCountRequest(Object... args) throws Exception {
            long folderId = (long) args[0];

            if (LiferayServerContext.isLiferay7()) {
                return new BookmarksEntryService(getSession()).getEntriesCount(groupId, folderId);
            } else {
                return new com.liferay.mobile.android.v62.bookmarksentry.BookmarksEntryService(
                    getSession()).getEntriesCount(groupId, folderId);
            }
        }

- `createEntity`: Returns an instance of your event that contains the server 
  call's results. This method receives the results as `Map<String, Object>`, 
  which it uses to instantiate your model class. It then uses this model 
  instance to create the event object. In the example `BookmarkListInteractor`, 
  this method passes the `Map<String, Object>` to the 
  `Bookmark` constructor. It then uses the resulting `Bookmark` to create and 
  return a `BookmarkEvent`: 

        @Override
        protected BookmarkEvent createEntity(Map<String, Object> stringObjectMap) {
            Bookmark bookmark = new Bookmark(stringObjectMap);
            return new BookmarkEvent(bookmark);
        }

- `getIdFromArgs`: a boilerplate method that returns the value of the first 
  object argument as a string. This serves as a cache key for 
  [offline mode](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-android): 

        @Override
        protected String getIdFromArgs(Object... args) {
            return String.valueOf(args[0]);
        }

    You must implement this method even if you don't intend to support offline 
    mode in your Screenlet. Having this method in your Interactor class makes it 
    simpler to add offline mode functionality later. Supporting offline mode in 
    your Screenlets is addressed in detail in a separate tutorial. 

To see the complete `BookmarkListInteractor` class, 
[click here](https://github.com/liferay/liferay-screens/blob/master/android/samples/listbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/listbookmark/BookmarkListInteractor.java). 

Next, you'll create the Screenlet class. 
