# Creating the Screenlet Class

Recall that app developers primarily interact with the Screenlet class when 
using a Screenlet in their app. You therefore need to make sure the Screenlet 
class contains all the attributes and methods the app developer needs. You'll 
create the `GetEntriesScreenlet` class to be very similar to the 
`GetGuestbooksScreenlet` class. The `GetEntriesScreenlet` class, however, needs 
a `guestbookId` for retrieiving guestbook entries. You'll get started by 
defining the attributes needed in the Screenlet class.

## Defining Screenlet Attributes

In the Get Guestbooks Screenlet, you defined the `layoutId`, `groupId`, and 
`autoLoad` attributes. You'll define the same attributes for the Get Entries 
Screenlet. Create the file `entries_attrs.xml` in your app's `res/values` 
directory. Replace the file's contents with the following code:

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="GetEntriesScreenlet">
            <attr name="layoutId"/>
            <attr name="groupId"/>
            <attr name="autoLoad"/>
        </declare-styleable>
    </resources>

Next, you'll use these attributes in the Screenlet class.

## Creating the Screenlet Class

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

With a few small but important differences, this is the `GetGuestbooksScreenlet` 
code, adapted for entries. Note that `GetEntriesScreenlet` contains a 
`_guestbookId` variable for defining the guestbook the Screenlet retrieves 
entries from. The public methods `getGuestbookId` and `setGuestbookId`, 
respectively, get and set `_guestbookId`. An app developer can call these 
methods in the activity or fragment class containing the Screenlet. The 
`GetEntriesScreenlet` class's `onUserAction` method then calls the interactor's 
`getEntries` method with `_guestbookId`. 

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

That's it! Now you're ready to use the Get Entries Screenlet alongside the Get 
Guestbooks Screenlet. The follwing articles conclude with both Screenlets 
working together in harmony. 
