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
Screenlet's XML in a layout. Guestbook List Screenlet only needs one attribute. 
You'll define it now. Create the file `guestbook_attrs.xml` in your app's 
`res/values` directory. Replace the file's contents with the following code: 

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

Now that you've defined these attributes and know what they need to do, you're 
ready to write the Screenlet class. 

## Extending BaseListScreenlet

Recall from 
[the list Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-screenlet-class) 
that you must extend `BaseListScreenlet` to create a Screenlet class for a list 
Screenlet. Use the following steps to create `GuestbookListScreenlet`; the 
Screenlet class for Guestbook List Screenlet. Note that these steps closely 
follow the steps in the list Screenlet creation tutorial. Departures from the 
tutorial's steps are noted. 

1. Create the `GuestbookListScreenlet` class in the package 
   `com.liferay.docs.guestbooklistscreenlet`. Recall that Screenlet classes for 
   list Screenlets must extend `BaseListScreenlet` with the model and Interactor 
   as type arguments. Declare `GuestbookListScreenlet` as such: 

        public class GuestbookListScreenlet extends 
            BaseListScreenlet<GuestbookModel, GuestbookListInteractor> {...

    This requires you to add the following imports: 

        import com.liferay.docs.guestbooklistscreenlet.interactor.GuestbookListInteractor;
        import com.liferay.docs.model.GuestbookModel;
        import com.liferay.mobile.screens.base.list.BaseListScreenlet;

    Note that the list Screenlet tutorial also instructs you to create instance 
    variables for your Screenlet's attributes. Guestbook List Screenlet doesn't 
    require instance variables for `groupId` and `layoutId` because Screens 
    handles them for you. 

2. Recall that for constructors, you can leverage the superclass constructors. 
   Do so now to create `GuestbookListScreenlet`'s constructors: 

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

3. Implement the `error` method. Recall that this method uses a listener in the 
   Screenlet framework to propagate any exception that occurs during the service 
   call: 

        @Override
        public void error(Exception e, String userAction) {
            if (getListener() != null) {
                getListener().error(e, userAction);
            }
        }

4. Override the `createScreenletView` method. Recall that this method reads the 
   Screenlet's attribute values and instantiates the View. In Guestbook List 
   Screenlet, you only need to read the value of the `groupId` attribute. 
   Also remember that calling the superclass's `createScreenletView` method 
   instantiates the View for you: 

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

5. Override the `loadRows` method. Recall that this method retrieves the list 
   rows from the server by starting the Interactor. The `loadRows` method in 
   `GuestbookListScreenlet` therefore starts a `GuestbookListInteractor` 
   instance. Because you don't need to pass any data to this Interactor, you can 
   call its `start` method with `0` as an argument: 

        @Override
        protected void loadRows(GuestbookListInteractor interactor) {
            interactor.start(0);
        }

6. Override the `createInteractor` method. Recall that this method instantiates 
   and returns the Interactor: 

        @Override
        protected GuestbookListInteractor createInteractor(String actionName) {
            return new GuestbookListInteractor();
        }

Awesome! Your Screenlet class is finished. Your Screenlet is finished, too! 
Before using Guestbook List Screenlet, however, you'll create Entry List 
Screenlet to show a list of each guestbook's entries. After all, viewing 
guestbooks without their entries doesn't make much sense; it isn't very exciting 
either. What's really exciting is that you can create Entry List Screenlet with 
the same set of steps you used to create Guestbook List Screenlet. The next 
series of articles in this Learning Path walks you through this. 
