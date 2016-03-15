# Creating Android List Screenlets [](id=creating-android-list-screenlets)

One of the most common features in mobile apps (and in software in general) is to show lists of specific entities to the user.

Since Liferay Portal handles a lot of types of entities, you would need to create your own screenlet to show a list of one specific entity in your app. This element type may be an standard type (such User, or Bookmark), or maybe a custom type created by your own plugin.

This tutorial explains how to create your own list screenlets. As an example, it
references code from the sample
[Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/android/samples/addbookmarkscreenlet), 
that lists bookmarks from Liferay's Bookmarks portlet.

To understand the concepts explained here, you might want to examine the
[architecture](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)
of Liferay Screens for android. The tutorial
[Creating android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)
is also highly recommended since we're going to create a new screenlet from scratch.


## Pagination [](id=pagination)

Since your lists may be potentially quite large, the screenlet needs to support pagination. In some rare cases, you can skip this pagination feature, but it's quite unusual. If you want to list the number of days in a week, it clearly seems the paginations can be bypassed, but I bet you won't find this case usually.

Liferay Screens gives you some tools to implement [fluent pagination](http://www.androidnomad.com/blog/2014/4/21/fluent-pagination) 
with configurable page size. Both `AssetListScreenlet` and `DDLListScreenlet` use this approach.

## Creating the Screenlet [](id=creating-the-screenlet)

As you already know, one screenlet contains several sub-components which can be built in a separated manner. This allows us to focus in different responsibilities one by one

### Creating the Screenlet's UI [](id=creating-the-screenlets-ui)

Let's start by the view layer. In Android Studio, create a new class to represent your view that will inherit from `BaseListScreenletView` and receive three arguments, the model class you are going to represent, a standard Android View Holder and an adapter to that view holder.
    
For example, the Bookmark List Screenlet class will be `BookmarkListView` that represents `Bookmark` instances in a `BookmarkViewHolder` inside a `BookmarkAdapter`. Our `BookmarkListView` has only one responsibility right now, creating the adapter and passing it the row layout (and the progress layout but we'll use the default). The code of the View class will be similar to:

```java
	@Override
	protected BookmarkAdapter createListAdapter(int itemLayoutId, int itemProgressLayoutId) {
		return new BookmarkAdapter(itemLayoutId, itemProgressLayoutId, this);
	}

	@Override
	protected int getItemLayoutId() {
		return R.layout.bookmark_row;
	}
```

Let's create now our `BookmarkAdapter`. It's a standard android adapter but we can inherit from `BaseListAdapter` to simplify things. If we do, we only have to implement two methods, instantiate our view holder and the method that fills it with each row.

The code is pretty simple:

```java
	public BookmarkViewHolder createViewHolder(View view, BaseListAdapterListener listener) {
		return new BookmarkAdapter.BookmarkViewHolder(view, listener);
	}

	protected void fillHolder(Bookmark entry, BookmarkViewHolder holder) {
		holder.bind(entry);
	}
```

We've created our standard view holder as a inner class inside our view holder. The code is very similar as the other view holders you've seen before, no Liferay Screens code here:

```java
	public class BookmarkViewHolder extends BaseListAdapter.ViewHolder {
	
		public BookmarkViewHolder(View view, BaseListAdapterListener listener) {
			super(view, listener);
			_url = (TextView) view.findViewById(R.id.bookmark_url);
		}
			
		public void bind(Bookmark entry) {
			_url.setText(entry.getUrl());
		}
	
		private final TextView _url;
	}
``` 

In that view holder we are rendering the url attribute of our model POJO in the bookmark layout. The layout is a basic LinearLayout with a TextView.

The last step is implementing our layout with the recycler view. The code is the same for all the list screenlets (apart from decoration). The full code is as follows:

```xml
<com.liferay.mobile.screens.listbookmark.BookmarkListView
	android:id="@+id/liferay_list_screenlet"
	xmlns:android="http://schemas.android.com/apk/res/android"
	android:layout_width="match_parent"
	android:layout_height="match_parent">

	<ProgressBar
		android:id="@+id/liferay_progress"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_gravity="center"
		android:visibility="gone"/>

	<android.support.v7.widget.RecyclerView
		android:id="@+id/liferay_recycler_list"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:visibility="gone"/>
</com.liferay.mobile.screens.listbookmark.BookmarkListView>
```

The full code example is available [here](https://github.com/liferay/liferay-screens/tree/develop/android/samples/listbookmarkscreenlet).

### Creating the Screenlet Class [](id=creating-the-screenlet-class)

Once the view is prepared, we're going to create the screenlet itself. You just need to create a class extending from `BaseListScreenlet`.

Our screenlet is going to be very simple, we can forget the cache methods right now and implement directly our `createInteractor` and `loadRows`.

The `createInteractor` method is only instantiating the Interactor while the `loadRows` calls the search method of the interactor passing the groupId and folderId attributes.

The full code (apart from the constructors and empty cache methods) is as follows:

```java
public class BookmarkListScreenlet extends BaseListScreenlet<Bookmark, BookmarkListInteractorImpl> {

	@Override
	protected void loadRows(BookmarkListInteractorImpl interactor, int startRow, int endRow, Locale locale) throws Exception {
		interactor.loadRows(startRow, endRow, locale, groupId, folderId);
	}

	@Override
	protected BookmarkListInteractorImpl createInteractor(String actionName) {
		return new BookmarkListInteractorImpl(getScreenletId(), OfflinePolicy.REMOTE_ONLY);
	}

	private long groupId;
	private long folderId;
}

```

 In this class you will add the configuration parameters of the screenlet. In our case, we just need to configure the `groupId` and the `folderId`.
 		
This is enough for the moment. We'll add more code later.

### Creating the Interactor [](id=creating-the-interactor)

Now the screenlet needs to respond to user actions. In our case, the only action is "load the bookmarks". This action is triggered by the app's programmer calling method `loadPage`, or automatically when the screenlet appears in the screen (depending on the value of the property `autoLoad`). As you already know, to respond to actions the screenlet uses Interactor classes. So we'd need one interactor to load the list. In our case, given that we're extending from `BaseListScreenlet`, we need to extend from an specific interactor class, `BaseListInteractor`, which helps us to request list's pages. This interactor will have all the parameters it needs to perform its duty (`groupId` and `folderId` in our case) and override several methods:

```java
	protected BaseListCallback<Bookmark> getCallback(Pair<Integer, Integer> rowsRange, Locale locale) {
		return new BaseListCallback<Bookmark>(getTargetScreenletId(), rowsRange, locale) {
			@Override
			public Bookmark createEntity(Map<String, Object> stringObjectMap) {
				// transform the result map to your entity
			}
		};
	}

	@Override
	protected void getPageRowsRequest(Session session, int startRow, int endRow, Locale locale) throws Exception {
		// do the list request
	}

	@Override
	protected void getPageRowCountRequest(Session session) throws Exception {
		// do the count request
	}
```

We can ignore the other methods, we only need to implement them if we can to support cached requests.

Let's start implementing the first: `getCallback ` method will be called several times, one by each entity received by the server and its duty is to convert that entity (in server-side format) into a politer object. Our conversion is pretty simple, we just delegate the task to the entity class:

```java
	@Override
	public Bookmark createEntity(Map<String, Object> stringObjectMap) {
		return new Bookmark(stringObjectMap);
	}
```

The entity class just reads the value of one the fields, the *url*:

```java
	public Bookmark(Map<String, Object> stringObjectMap) {
		_url = (String) stringObjectMap.get("url");
	}
```

Now the implementation of the request methods. The code is using the Mobile SDK to do the appropriate calls:

```java
	@Override
	protected void getPageRowsRequest(Session session, int startRow, int endRow, Locale locale) throws Exception {
		new BookmarksEntryService(session).getEntries(_groupId, _folderId, startRow, endRow);
	}

	@Override
	protected void getPageRowCountRequest(Session session) throws Exception {
		new BookmarksEntryService(session).getEntriesCount(_groupId, _folderId);
	}
```
	
### Connecting the Dots [](id=connecting-the-dots)

So then, you have your screenlet ready to be used. We could hardcode the `groupId` and `folderId` and test it in our application. But let's wire everything to pass those attributes as xml parameters.

For that, we just need to create an xml with a `declare-styleable` block, similar to this:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
	<declare-styleable name="BookmarkListScreenlet">
		<attr name="groupId"/>
		<attr name="folderId"/>
	</declare-styleable>
</resources>
```

And read those attribute from our `BookmarkListScreenlet`:

```java
	@Override
	protected View createScreenletView(Context context, AttributeSet attributes) {
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
			attributes, R.styleable.BookmarkListScreenlet, 0, 0);
		_groupId = typedArray.getInt(R.styleable.BookmarkListScreenlet_groupId,
			(int) LiferayServerContext.getGroupId());
		_folderId = typedArray.getInt(R.styleable.BookmarkListScreenlet_folderId, 0);
		typedArray.recycle();

		return super.createScreenletView(context, attributes);
	}
```

Now you can pass the groupId and the folderId from your xml or add setters to set it up from Java code.

### Extra Ball: Use a Delegate  [](id=extra-add-delegate)

Even though your screenlet can be used now, we missed some useful features, so let's explain them now. The first one is the listener: you need to notify the app's developer when an relevant event occurs inside the screenlet. And as you already know, in android world this is done using the listener pattern.

Currently we are inheriting `BaseListScreenlet` which itself implements `BaseListInteractorListener<E>` parametrised with the model we provide in the Screenlet class and that provides us with the following methods:

```java
	@Override
	public void onListPageFailed(BaseListScreenlet source, int page, Exception e) {

	}

	@Override
	public void onListPageReceived(BaseListScreenlet source, int page, List<Bookmark> entries, int rowCount) {

	}

	@Override
	public void onListItemSelected(Bookmark element, View view) {

	}
```

If you want to add more methods and notify our activity of specific events, you first need to define a listener, which will be the contract (aka the set of methods) that any listener class will conform. You can do it in the `BookmarkListScreenlet.class` file by passing another listener that inherits from BaseListListener.
	
For example we could add a listener that notifies us when the interactor is called. For that, we implement a custom listener `BookmarkListListener`:

```java
public interface BookmarkListListener extends BaseListListener<Bookmark> {
	void interactorCalled();
}
```

We pass it to our screenlet from the activity:

```java
		BookmarkListScreenlet bookmarkListScreenlet = (BookmarkListScreenlet) findViewById(R.id.bookmarklist_screenlet);
		bookmarkListScreenlet.setListener(this);
```

And, lastly, we call our listener when we desire:

```java
	protected void loadRows(BookmarkListInteractorImpl interactor, int startRow, int endRow, Locale locale) throws Exception {

		((BookmarkListListener) getListener()).interactorCalled();

		...
	}
```

### Extra Ball: Add Offline Support [](id=extra-add-offline-support)

One of the most powerful capabilities of the screenlets is that they can work in offline. In the case of the list screenlets, this is quite straightforward, you just have to implement 4 methods in the interactor.

1. First we have to call a special method (that will call *loadRows*), we just have to replace our *loadRows* call with:
	
	```java
		processWithCache(startRow, endRow, locale);
	```

1. Implement *getElement* and *getContent*, the methods responsible of converting from cache to our element and back. The implementations are trivial if we take advantage of some JSON serialization:

	```java
		@Override
		protected String getContent(Bookmark object) {
			return new JSONObject(object.getValues()).toString();
		}
		
		@Override
		protected Bookmark getElement(TableCache tableCache) throws JSONException {
			return new Bookmark(JSONUtil.toMap(new JSONObject(tableCache.getContent())));
		}
	```

2. And we have to call to our store and recover methods passing the right attributes:

	```java
		@Override
		protected void storeToCache(BaseListEvent event, Object... args) {
			storeRows(String.valueOf(_folderId), BOOKMARK_LIST.BOOKMARK,
				BOOKMARK_LIST.BOOKMARK_COUNT, _groupId, null, event);
		}
		
		@Override
		protected boolean cached(Object... args) throws Exception {
			final int startRow = (int) args[0];
			final int endRow = (int) args[1];
			final Locale locale = (Locale) args[2];
	
			return recoverRows(String.valueOf(_folderId), BOOKMARK_LIST.BOOKMARK,
				BOOKMARK_LIST.BOOKMARK_COUNT, _groupId, null, locale, startRow, endRow);
		}
	```
	
	The only relevant code is that we are using our folderId as the id of the row and that the key to store the information is an enumeration, `BOOKMARK_LIST`, with 2 keys. The code for the enumeration is as follows:
	
	```java
		private enum BOOKMARK_LIST implements CachedType {
			BOOKMARK, BOOKMARK_COUNT
		}
	```

1. And that's it! just add your screenlet to your app, configure the cache policy, and everything will be handled under the hood.



You're done! Your Screenlet is a ready-to-use component that you can add to your
storyboard. You can even
[package](/develop/tutorials/-/knowledge_base/6-2/creating-android-themes#publish-your-themes-using-cocoapods)
it to contribute to the Screens project or distribute with CocoaPods.


## Related Topics [](id=related-topics)

[Creating android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)

[Architecture of Liferay Screens for android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Packaging android Themes](/develop/tutorials/-/knowledge_base/6-2/packaging-android-themes)

[Using Themes in android Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-android-screenlets)