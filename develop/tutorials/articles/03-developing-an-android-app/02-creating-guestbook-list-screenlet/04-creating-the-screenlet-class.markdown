# Creating the Screenlet Class [](id=creating-the-screenlet-class)

When using a Screenlet, app developers primarily interact with its Screenlet 
class. The Screenlet class contains attributes for configuring the Screenlet's 
behavior, a reference to the Screenlet's View, methods for invoking Interactor 
operations, and more. To app developers, the Screenlet class is like the 
driver's seat of a fine luxury automobile. All the controls are in just the 
right place, do exactly what they should, and make you feel like royalty on the 
road. Now it's your turn to craft this for Guestbook List Screenlet. Don't 
worry, we promise you won't get a speeding ticket. 

You'll use these steps to create the Screenlet class: 

1. Define the Screenlet's attributes. These are the XML attributes the app 
   developer can set when inserting the Screenlet's XML. These attributes 
   control aspects of the Screenlet's behavior. 

2. Create the Screenlet class. This class implements the Screenlet's 
   functionality defined in the View, listener, and Interactor. It also reads 
   the attribute values and configures the Screenlet accordingly. 

First, you'll define Guestbook List Screenlet's attributes. 

## Defining Screenlet Attributes [](id=defining-screenlet-attributes)

Recall from 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#defining-screenlet-attributes-in-your-app) 
that you should create a Screenlet's attributes before creating the Screenlet 
class. These are the attributes the app developer can set when inserting the 
Screenlet's XML in a layout. You'll define Guestbook List Screenlet's attributes 
now. Create the file `guestbook_attrs.xml` in your app's `res/values` directory. 
Replace the file's contents with the following code: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="GuestbookListScreenlet">
            <attr name="groupId"/>
            <attr name="layoutId"/>
        </declare-styleable>
    </resources>

This defines the attributes `groupId` and `layoutId`. You'll add these 
attributes' functionality in the Screenlet class. Here's a brief description of 
the functionality you'll add to each: 

- `groupId`: Sets the portal site to communicate with, if the app developer 
  doesn't want to use the default `groupId` setting in `server_context.xml`. 

- `layoutId`: Sets the Screenlet's View. This functions the same as the 
  `layoutId` attribute in Liferay's existing Screenlets. 

Now that you've defined these attributes and know what they need to do, you're 
ready to write the Screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Recall from 
[the list Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-screenlet-class) 
that you must extend BaseListScreenlet to create a Screenlet class for a list 
Screenlet. This is because 
[`BaseListScreenlet` class](https://github.com/liferay/liferay-screens/blob/2.1.0/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java) 
provides the basic functionality for all Screenlet classes in list Screenlets. 
This includes methods for pagination and other default behavior. Use the 
following steps to create `GuestbookListScreenlet`; the Screenlet class for 
Guestbook List Screenlet. Note that these steps closely follow the steps in the 
list Screenlet creation tutorial. 

1. Create the `GuestbookListScreenlet` class in the package 
   `com.liferay.docs.guestbooklistscreenlet`. This class must extend 
   `BaseListScreenlet` with `GuestbookModel` and `GuestbookListInteractor` as 
   type arguments: 

        public class GuestbookListScreenlet extends 
            BaseListScreenlet<GuestbookModel, GuestbookListInteractor> {...

    This requires you to add the following imports: 

        import com.liferay.docs.guestbooklistscreenlet.interactor.GuestbookListInteractor;
        import com.liferay.docs.model.GuestbookModel;
        import com.liferay.mobile.screens.base.list.BaseListScreenlet;

2. Leverage the superclass constructors to create `GuestbookListScreenlet`'s 
   constructors:

        public GuestbookListScreenlet(Context context) {
            super(context);
        }

        public GuestbookListScreenlet(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public GuestbookListScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public GuestbookListScreenlet(Context context, AttributeSet attrs, int defStyleAttr, 
            int defStyleRes) {
                super(context, attrs, defStyleAttr, defStyleRes);
        }

    This requires you to add the following imports: 

        import android.content.Context;
        import android.util.AttributeSet;

3. Implement the `error` method. This method uses a listener to propagate any 
   exception that occurs during the service call: 

        @Override
        public void error(Exception e, String userAction) {
            if (getListener() != null) {
                getListener().error(e, userAction);
            }
        }

4. Override the `createScreenletView` method to read the value of the `groupId` 
   attribute you defined and create the Screenlet's View. Note that you don't 
   have to read the `layoutId` attribute's value here because calling the 
   superclass's `createScreenletView` method does so for you: 

        @Override
        protected View createScreenletView(Context context, AttributeSet attributes) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, 
                R.styleable.GuestbookListScreenlet, 0, 0);
            groupId = typedArray.getInt(R.styleable.GuestbookListScreenlet_groupId, 
                (int) LiferayServerContext.getGroupId());
            typedArray.recycle();

            return super.createScreenletView(context, attributes);
        }

    This requires you to add the following imports: 

        import android.content.res.TypedArray;
        import android.view.View;
        import com.liferay.docs.liferayguestbook.R;
        import com.liferay.mobile.screens.context.LiferayServerContext;

5. Override the `loadRows` method to retrieve the list rows from the server. Do 
   this by starting `GuestbookListInteractor`. Because you don't need to pass 
   any data to the Interactor, you can call its `start` method with no 
   arguments: 

        @Override
        protected void loadRows(GuestbookListInteractor interactor) {
            interactor.start();
        }

6. Override the `createInteractor` method to instantiate and return 
   `GuestbookListInteractor`: 

        @Override
        protected GuestbookListInteractor createInteractor(String actionName) {
            return new GuestbookListInteractor();
        }

### Extending BaseListScreenlet [](id=extending-baselistscreenlet)

Your Screenlet class must extend `BaseListScreenlet` with your model and 
Interactor classes as type arguments, and implement any functionality specific 
to your Screenlet. 

In the `guestbooklistscreenlet` package, create a new class called 
`GuestbookListScreenlet`. This class must extend `BaseListScreenlet` with 
`GuestbookModel` and `GuestbookListInteractorImpl` as type arguments. Your class 
declaration should look like this: 

    public class GuestbookListScreenlet extends 
        BaseListScreenlet<GuestbookModel, GuestbookListInteractorImpl> {...

Next, you must add an instance variable for each Screenlet attribute you created 
in the preceding section, besides `layoutId` (you'll learn why in a moment). Add 
these variables now: 

    private long _groupId;
    private OfflinePolicy _offlinePolicy;

Now add your Screenlet class's constructors. You don't need anything special 
here; just leverage the superclass constructors: 

    public GuestbookListScreenlet(Context context) {
        super(context);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);
    }

Next, add the methods that support 
[offline mode](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens) 
in your Screenlet. As with those in your Interactor class, these are boilerplate 
implementations. See the 
[list Screenlet tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets) 
for details on how they work: 

    @Override
    public void loadingFromCache(boolean success) {
        if (getListener() != null) {
            getListener().loadingFromCache(success);
        }
    }

    @Override
    public void retrievingOnline(boolean triedInCache, Exception e) {
        if (getListener() != null) {
            getListener().retrievingOnline(triedInCache, e);
        }
    }

    @Override
    public void storingToCache(Object object) {
        if (getListener() != null) {
            getListener().storingToCache(object);
        }
    }

Now you must override the method that reads the attribute settings and inflates 
the View: the aptly named `createScreenletView`. This method 
[is an abstract method of Screens's `BaseScreenlet` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java#L232), 
and takes a 
[`Context` object](https://developer.android.com/reference/android/content/Context.html) 
and an 
[`AttributeSet` object](https://developer.android.com/reference/android/util/AttributeSet.html) 
as arguments. 
Although 
[`BaseListScreenlet` implements `createScreenletView`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java#L152-L174), 
you must provide an implementation suitable for your Screenlet. For example, 
your `createScreenletView` implementation must account for Guestbook List 
Screenlet's `groupId` and `offlinePolicy` attributes. Add this method to your 
`GuestbookListScreenlet` class now: 

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {

    }

In this method, you'll use an 
[Android `TypedArray`](http://developer.android.com/reference/android/content/res/TypedArray.html) 
to retrieve any settings the app developer makes for the attributes you defined 
in `guestbook_attrs.xml`. Add the following to your `createScreenletView` 
method: 

    TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes, R.styleable.GuesbookListScreenlet, 0, 0);

First, you'll retrieve the app developer's `offlinePolicy` setting from the 
`TypedArray`, and use it to set the Screenlet class's `_offlinePolicy` variable. 
You'll retrieve the app developer's setting with the `TypedArray` method 
`getInteger`. Add the following to your `createScreenletView` method: 

    Integer offlinePolicy = typedArray.getInteger(
                R.styleable.GuesbookListScreenlet_offlinePolicy,
                OfflinePolicy.REMOTE_ONLY.ordinal());

With its first argument--`R.styleable.GuesbookListScreenlet_offlinePolicy`--the 
`getInteger` method retrieves the app developer's `offlinePolicy` setting. If 
the app developer hasn't set this attribute, `getInteger` defaults to the remote 
only policy setting via its second argument: 
`OfflinePolicy.REMOTE_ONLY.ordinal()`. Regardless of where the setting 
originates, the results are set to the local variable `offlinePolicy`. You must 
then use this variable to set the Screenlet class's `_offlinePolicy` variable. 
Do this now by adding the following code to your `createScreenletView` method: 

    _offlinePolicy = OfflinePolicy.values()[offlinePolicy];

[Screens's `OfflinePolicy`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/cache/OfflinePolicy.java) 
is an enumeration that defines the four possible offline mode settings: 
`REMOTE_ONLY`, `CACHE_ONLY`, `CACHE_FIRST`, and `REMOTE_FIRST`. The `Integer` 
set in the local `offlinePolicy` variable is used to select the corresponding 
value from the enumeration, which is then set to the Screenlet class's 
`_offlinePolicy` variable. 

Now you must retrieve the app developer's setting for the `groupId` attribute. 
You'll do this with the `TypedArray` method `getInt`, much as you used the 
`TypedArray` method `getInteger` to retrieve the app developer's `offlinePolicy` 
setting. Add the following code to your `createScreenletView` method: 

    _groupId = typedArray.getInt(R.styleable.GuesbookListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());

The first argument to `getInt` specifies the app developer's `groupId` setting, 
while the second argument specifies the `groupId` setting from the app's 
`server_context.xml`. The latter is provided as a default in case the app 
developer doesn't set `groupId`. The `getInt` method's results are set to the 
Screenlet class's `_groupId` variable.

Since you're now done with the `TypedArray` you created, you must recycle it. 
This is a best practice when working with `TypedArray` in Android. See 
[Android's `TypedArray` documentation](https://developer.android.com/reference/android/content/res/TypedArray.html) 
for more information. To recycle the `TypedArray`, add the following to your 
`createScreenletView` method: 

    typedArray.recycle();

Lastly, your `createScreenletView` method must inflate your Screenlet's layout 
and return its View. You'll do this by calling 
[`BaseListScreenlet`'s `createScreenletView` method](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java#L152-L174). 
Note this method also uses a `TypedArray` to retrieve and set the Screenlet's 
attributes, including the `layoutId`. This is why your `createScreenletView` 
implementation doesn't need support for `layoutId`; calling 
`BaseListScreenlet`'s implementation supports it for you. Calling the 
`BaseListScreenlet` implementation also gets you other attributes for 
controlling pagination (`firstPageSize` and `pageSize`), loading the Screenlet 
automatically (`autoLoad`), and more. The method finishes by inflating and 
returning the View that matches the `layoutId`. Call this method now by adding 
the following code to your `createScreenletView` method: 

    return super.createScreenletView(context, attributes);

Nice job! You finished your `createScreenletView` method. Check to make sure it 
looks like this: 

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes, R.styleable.GuesbookListScreenlet, 0, 0);
        Integer offlinePolicy = typedArray.getInteger(
                R.styleable.GuesbookListScreenlet_offlinePolicy,
                OfflinePolicy.REMOTE_ONLY.ordinal());
        _offlinePolicy = OfflinePolicy.values()[offlinePolicy];
        _groupId = typedArray.getInt(R.styleable.GuesbookListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());
        typedArray.recycle();

        return super.createScreenletView(context, attributes);
    }

Next, to start the Interactor operation that retrieves the list rows for display 
in the Screenlet, you must implement 
[`BaseListScreenlet`'s abstract `loadRows` method](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java#L149-L150). 
This method must call your Interactor class's `loadRows` method. Add the 
following `loadRows` method to your Screenlet class: 

    @Override
    protected void loadRows(GuestbookListInteractorImpl interactor, int startRow,
                            int endRow, Locale locale) throws Exception {

        interactor.loadRows(startRow, endRow, locale, _groupId);
    }

Note that the `interactor.loadRows` call includes the `_groupId`. This ensures 
that the Interactor retrieves guestbooks from the correct site in your Liferay 
instance. 

Lastly, your Screenlet class needs a method to create an instance of your 
Interactor class. Add this method now: 

    @Override
    protected GuestbookListInteractorImpl createInteractor(String actionName) {
        return new GuestbookListInteractorImpl(getScreenletId(), _offlinePolicy);
    }

Awesome! Your Screenlet class is finished. Your Screenlet is finished, too! 
Before using Guestbook List Screenlet, however, you'll create Entry List 
Screenlet to show a list of each guestbook's entries. After all, viewing 
guestbooks without their entries doesn't make much sense. It isn't very exciting 
either. What's really exciting is that you can create Entry List Screenlet with 
the same set of steps you used to create Guestbook List Screenlet. The next 
series of articles in this Learning Path walks you through this. 
