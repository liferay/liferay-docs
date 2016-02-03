# Creating the Screenlet Class [](id=creating-the-screenlet-class-0)

Recall that app developers primarily interact with the Screenlet class when 
using a Screenlet in their app. You must therefore make sure the Screenlet class 
contains all the attributes and methods the app developer needs. The Screenlet 
class the Get Entries Screenlet requires, `GetEntriesScreenlet`, is almost 
identical to `GetGuestbooksScreenlet`. You can therefore create 
`GetEntriesScreenlet` with the same sequence of steps you used to create 
`GetGuestbooksScreenlet`: 

1. Define the Screenlet's attributes. These are the XML attributes the app 
   developer can set when inserting the Screenlet's XML in an activity or 
   fragment layout. These attributes control aspects of the Screenlet's 
   behavior. 

2. Create the Screenlet class. This class implements the Screenlet's 
   functionality as defined in the View, listener, and Interactor. It also reads 
   the attribute values and configures the Screenlet accordingly. 

Due to this similarity, this article doesn't explain `GetEntriesScreenlet` in 
detail. Focus is instead placed on the code unique to `GetEntriesScreenlet`. For 
a detailed explanation of the rest of the class, see 
[the article on creating `GetGuestbooksScreenlet`](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-the-screenlet-class). 

You'll get started here by defining the attributes `GetEntriesScreenlet` needs. 

## Defining Screenlet Attributes [](id=defining-screenlet-attributes)

In Get Guestbooks Screenlet, you defined the `layoutId`, `groupId`, and 
`autoLoad` attributes. Get Entries Screenlet also needs these. Create the file 
`entries_attrs.xml` in your app's `res/values` directory. Replace the file's 
contents with the following code:

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="GetEntriesScreenlet">
            <attr name="layoutId"/>
            <attr name="groupId"/>
            <attr name="autoLoad"/>
        </declare-styleable>
    </resources>

Next, you'll use these attributes in the Screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Create a new class called `GetEntriesScreenlet` inside the `getentriesscreenlet` 
package. Replace this class's contents with the following code:

    package com.liferay.docs.getentriesscreenlet;

    import android.content.Context;
    import android.content.res.TypedArray;
    import android.util.AttributeSet;
    import android.view.LayoutInflater;
    import android.view.View;

    import com.liferay.docs.getentriesscreenlet.interactor.GetEntriesInteractor;
    import com.liferay.docs.getentriesscreenlet.interactor.GetEntriesInteractorImpl;
    import com.liferay.docs.getentriesscreenlet.view.GetEntriesViewModel;
    import com.liferay.docs.model.EntryModel;
    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.mobile.screens.base.BaseScreenlet;
    import com.liferay.mobile.screens.context.LiferayServerContext;
    import com.liferay.mobile.screens.util.LiferayLogger;

    import java.util.List;

    public class GetEntriesScreenlet extends BaseScreenlet<GetEntriesViewModel, GetEntriesInteractor>
        implements GetEntriesListener {

        private GetEntriesListener _listener;
        private int _groupId;
        private long _guestbookId;
        private boolean _autoLoad;

        public GetEntriesScreenlet(Context context) {
            super(context);
        }

        public GetEntriesScreenlet(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public GetEntriesScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

        public void onGetEntriesSuccess(List<EntryModel> entries) {
            getViewModel().showFinishOperation(null, entries);

            if (_listener != null) {
                _listener.onGetEntriesSuccess(entries);
            }
        }

        public void onGetEntriesFailure(Exception e) {
            getViewModel().showFailedOperation(null, e);

            if (_listener != null) {
                _listener.onGetEntriesFailure(e);
            }
        }

        @Override
        public void onItemClicked(final EntryModel entry) {
            if (_listener != null) {
                _listener.onItemClicked(entry);
            }
        }

        public GetEntriesListener getListener() {
            return _listener;
        }

        public void setListener(GetEntriesListener listener) {
            _listener = listener;
        }

        public int getGroupId() {
            return _groupId;
        }

        public void setGroupId(int groupId) {
            _groupId = groupId;
        }

        public long getGuestbookId() {
            return _guestbookId;
        }

        public void setGuestbookId(long guestbookId) {
            _guestbookId = guestbookId;
        }

        @Override
        protected View createScreenletView(Context context, AttributeSet attributes) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, 
                R.styleable.GetEntriesScreenlet, 0, 0);

            int layoutId = typedArray.getResourceId(R.styleable.GetEntriesScreenlet_layoutId, 
                getDefaultLayoutId());
            _autoLoad = typedArray.getBoolean(R.styleable.GetEntriesScreenlet_autoLoad, true);

            _groupId = typedArray.getInt(R.styleable.GetEntriesScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());

            LiferayLogger.i("The Group ID is: " + _groupId);

            View view = LayoutInflater.from(context).inflate(layoutId, null);

            typedArray.recycle();

            return view;
        }

        @Override
        protected GetEntriesInteractor createInteractor(String actionName) {
            return new GetEntriesInteractorImpl(getScreenletId());
        }

        @Override
        protected void onUserAction(String userActionName, GetEntriesInteractor interactor, 
            Object... args) {

            try {
                interactor.getEntries(_groupId, _guestbookId);
            }
            catch (Exception e) {
                onGetEntriesFailure(e);
            }
        }

        @Override
        protected void onScreenletAttached() {
            if (_autoLoad) {
                performUserAction();
            }
        }
    }

This is the `GetGuestbooksScreenlet` code, adapted for entries. The only other 
difference is the `_guestbookId` variable and the code that uses it. This 
variable is required to define the guestbook the Screenlet retrieves entries 
from. The public methods `getGuestbookId` and `setGuestbookId`, respectively, 
get and set `_guestbookId`. An app developer can call these methods in the 
activity or fragment class they use the Screenlet in. The `GetEntriesScreenlet` 
class's `onUserAction` method calls the Interactor's `getEntries` method with 
`_groupId` and `_guestbookId`. 

Great! You finished the `GetEntriesScreenlet` class! There's one more thing to 
take care of, however, before you can use this Screenlet in your app. Return to 
the `GetEntriesView` class and uncomment the line containing 
`GetEntriesScreenlet` in the `onItemClick` method. This method should now appear 
as follows:

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, 
        final long id) {
        setItemChecked(position, true);

        ((GetEntriesScreenlet) getParent()).onItemClicked(_entries.get(position));
    }

This also requires that you import 
`com.liferay.docs.getentriesscreenlet.GetEntriesScreenlet` in `GetEntriesView`.

By retrieving the selected entry, this `onItemClick` method is analagous to the 
one that retrieves the selected guestbook in `GetGuestbooksView`. 

That's it! Now you're ready to use the Get Entries Screenlet alongside the Get 
Guestbooks Screenlet. The following section of this Learning Path concludes with 
both Screenlets working together in harmony. 
