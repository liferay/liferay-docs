# Creating Guestbook List Screenlet's Screenlet Class [](id=creating-guestbook-list-screenlets-screenlet-class)

When using a Screenlet, app developers primarily interact with its Screenlet 
class. The Screenlet class contains attributes for configuring the Screenlet's 
behavior, a reference to the Screenlet's View, methods for invoking Interactor 
operations, and more. 

You'll use these steps to create the Screenlet class: 

1.  Define the Screenlet's attributes. These are the XML attributes the app 
    developer can set when inserting the Screenlet's XML. These attributes 
    control aspects of the Screenlet's behavior. 

2.  Create the Screenlet class. This class implements the Screenlet's 
    functionality defined in the View and Interactor. It also reads the 
    attribute values and configures the Screenlet accordingly. 

First, you'll define Guestbook List Screenlet's attributes. 

## Defining Screenlet Attributes [](id=defining-screenlet-attributes)

Before creating the Screenlet class, you should define its attributes. These are 
the attributes the app developer can set when inserting the Screenlet's XML in 
an activity or fragment layout. For example, to use 
[Login Screenlet](/develop/reference/-/knowledge_base/6-2/loginscreenlet-for-android), 
the app developer could insert the following Login Screenlet XML in an activity 
or fragment layout: 

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:basicAuthMethod="email"
        app:layoutId="@layout/login_default"
        />

The app developer can set the `app` attributes `basicAuthMethod` and `layoutId` 
to set Login Screenlet's authentication method and View, respectively. The 
Screenlet class reads these settings to enable the appropriate functionality. 

When creating a Screenlet, you can define the attributes you want to make 
available to app developers. You do this in an XML file inside your Android 
project's `res/values` directory. Guestbook List Screenlet only needs one 
attribute. You'll define it now. Create the file `guestbook_attrs.xml` in your 
app's `res/values` directory. Replace the file's contents with the following 
code: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="GuestbookListScreenlet">
            <attr name="groupId"/>
        </declare-styleable>
    </resources>

This defines the `groupId` attribute, which the app developer can use to set the 
portal site to communicate with if they don't want to use the default `groupId` 
setting in `server_context.xml`. You'll account for this attribute's 
functionality in the Screenlet class. 

Now that you've defined this attribute and know what it needs to do, you're 
ready to write the Screenlet class. 

## Extending BaseListScreenlet [](id=extending-baselistscreenlet)

Because 
[the `BaseListScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java) 
provides the basic functionality for all Screenlet classes in list Screenlets, 
including methods for pagination and other default behavior, your Screenlet 
class must extend `BaseListScreenlet` with your model class and Interactor as 
type arguments. 

Use the following steps to create the Screenlet class for Guestbook List 
Screenlet, `GuestbookListScreenlet`:

1.  Create the `GuestbookListScreenlet` class in the package 
    `com.liferay.docs.guestbooklistscreenlet`. This class must extend 
    `BaseListScreenlet` with the model and Interactor as type arguments: 

        public class GuestbookListScreenlet extends 
            BaseListScreenlet<GuestbookModel, GuestbookListInteractor> {...

    This requires you to add the following imports: 

        import com.liferay.docs.guestbooklistscreenlet.interactor.GuestbookListInteractor;
        import com.liferay.docs.model.GuestbookModel;
        import com.liferay.mobile.screens.base.list.BaseListScreenlet;

2.  For constructors, leverage the superclass constructors: 

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

3.  Implement the `error` method. This is a boilerplate method that uses a 
    listener in the Screenlet framework to propagate any exception, and the 
    user action that produced it, that occurs during the service call. This 
    method does this by checking for a listener and then calling its `error` 
    method with the `Exception` and `userAction`: 

        @Override
        public void error(Exception e, String userAction) {
            if (getListener() != null) {
                getListener().error(e, userAction);
            }
        }

4.  Override the `createScreenletView` method. This method reads the Screenlet's 
    attribute values via an 
    [Android `TypedArray`](https://developer.android.com/reference/android/content/res/TypedArray.html), 
    and instantiates the View. In Guestbook List Screenlet, you only need to 
    read the value of the `groupId` attribute and set it to the `groupId` 
    variable. Recall that the Screens framework propagates this variable to your 
    Interactor. Finish the `createScreenletView` method by calling the 
    superclass's `createScreenletView` method. This instantiates the View for 
    you: 

        @Override
        protected View createScreenletView(Context context, AttributeSet attributes) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, 
                R.styleable.GuestbookListScreenlet, 0, 0);
            groupId = typedArray.getInt(R.styleable.GuestbookListScreenlet_groupId, 
                (int) LiferayServerContext.getGroupId());
            typedArray.recycle();

            return super.createScreenletView(context, attributes);
        }

    Note that if the app developer doesn't set the `groupId` attribute, 
    `LiferayServerContext.getGroupId()` is called to retrieve the app's default 
    `liferay_group_id` setting from `res/values/server_context.xml`. 

    This `createScreenletView` method requires you to add the following imports: 

        import android.content.res.TypedArray;
        import android.view.View;
        import com.liferay.docs.liferayguestbook.R;
        import com.liferay.mobile.screens.context.LiferayServerContext;

5.  Override the `loadRows` method to start your Interactor and thereby retrieve 
    the list rows from the server. This method takes an instance of your 
    Interactor as an argument, which you use to call the Interactor's `start` 
    method. The `loadRows` method in `GuestbookListScreenlet` therefore starts a 
    `GuestbookListInteractor` instance. Note that the Interactor inherits 
    `start` from `BaseListInteractor`. Also, because you don't need to pass any 
    data to `GuestbookListInteractor`, you can call the `start` method with `0` 
    as an argument: 

        @Override
        protected void loadRows(GuestbookListInteractor interactor) {
            interactor.start(0);
        }

6.  Override the `createInteractor` method to instantiate your Interactor. Since 
    that's all this method needs to do, call your Interactor's constructor and 
    return the new instance: 

        @Override
        protected GuestbookListInteractor createInteractor(String actionName) {
            return new GuestbookListInteractor();
        }

Awesome! Your Screenlet class is finished. Note that this Screenlet class is 
very similar to the one in 
[the list Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/6-2/creating-android-list-screenlets#creating-the-screenlet-class). 

Your Screenlet is finished, too! Before using Guestbook List Screenlet, however, 
you'll create Entry List Screenlet to show a list of each guestbook's entries. 
After all, viewing guestbooks without their entries doesn't make much sense. It 
isn't very exciting either. What's really exciting is that you can create Entry 
List Screenlet with the same set of steps you used to create Guestbook List 
Screenlet. The next series of articles in this Learning Path walks you through 
this. 
