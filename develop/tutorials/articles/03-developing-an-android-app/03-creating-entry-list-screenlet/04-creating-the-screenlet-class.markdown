# Creating the Screenlet Class

Recall that when using a Screenlet, the app developer primarily interacts with 
the Screenlet class. The Screenlet class contains attributes for configuring the 
Screenlet's behavior, a reference to the Screenlet's View, methods for invoking 
Interactor operations, and more. This article shows you how to create Entry List 
Screenlet's Screenlet class. 

As with most of Entry List Screenlet, its Screenlet class is almost identical to 
that of Guestbook List Screenlet. Besides working with entries instead of 
guestbooks, the only difference is that the Screenlet class in Entry List 
Screenlet must account for the guestbook ID of the guestbook the entries are 
retrieved from. Even the Screenlets' attributes are the same. 

Therefore, this article doesn't explain all the code in detail. Focus is instead 
placed on the parts that differ from Guestbook List Screenlet. For a full 
explanation of the code, see 
[the article on creating Guestbook List Screenlet's Screenlet class](https://www.liferay.com/). 

You'll create Entry List Screenlet's Screenlet class with the same 
steps you used to create Guestbook List Screenlet's Screenlet class:

1. Define the Screenlet's attributes. These are the XML attributes the app 
   developer can set when inserting the Screenlet's XML. These attributes 
   control aspects of the Screenlet's behavior. 

2. Create the Screenlet class. This class implements the Screenlet's 
   functionality defined in the View, listener, and Interactor. It also reads 
   the attribute values and configures the Screenlet accordingly. 

First, you'll define Entry List Screenlet's attributes. 

## Defining Screenlet Attributes

Recall that before creating the Screenlet class, you must define the attributes 
the app developer needs to control the Screenlet's behavior. Entry List 
Screenlet's attributes are identical to Guestbook List Screenlet's. In your 
app's `res/values` directory, create `entry_attrs.xml` and replace its contents 
with the following: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="EntryListScreenlet">
            <attr name="groupId"/>
            <attr name="offlinePolicy"/>
            <attr name="layoutId"/>
        </declare-styleable>
    </resources>

Next, you'll create the Screenlet class.

## Creating the Screenlet Class

Entry List Screenlet's Screenlet class must contain an instance variable for the 
ID of the guestbook the Screenlet retrieves entries from. This is required to 
call the Interactor method that results in the server calls. This is the only 
significant difference between the Screenlet classes of Entry List Screenlet and 
Guesbook List Screenlet. The remaining differences exist only because they 
handle different entities. 

In the `entrylistscreenlet` package, create a new class called 
`EntryListScreenlet`. Replace its content with the following:

    package com.liferay.docs.entrylistscreenlet;

    import android.content.Context;
    import android.content.res.TypedArray;
    import android.util.AttributeSet;
    import android.view.View;

    import com.liferay.docs.entrylistscreenlet.interactor.EntryListInteractorImpl;
    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.docs.model.EntryModel;
    import com.liferay.mobile.screens.base.list.BaseListScreenlet;
    import com.liferay.mobile.screens.cache.OfflinePolicy;
    import com.liferay.mobile.screens.context.LiferayServerContext;

    import java.util.Locale;


    public class EntryListScreenlet extends BaseListScreenlet<EntryModel, EntryListInteractorImpl> {

        private long _groupId;
        private long _guestbookId;
        private OfflinePolicy _offlinePolicy;

        public EntryListScreenlet(Context context) {
            super(context);
        }

        public EntryListScreenlet(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public EntryListScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

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

        public long getGuestbookId() {
            return _guestbookId;
        }

        public void setGuestbookId(long guestbookId) {
            _guestbookId = guestbookId;
        }

        @Override
        protected View createScreenletView(Context context, AttributeSet attributes) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes, R.styleable.EntryListScreenlet, 0, 0);
            Integer offlinePolicy = typedArray.getInteger(
                R.styleable.EntryListScreenlet_offlinePolicy,
                OfflinePolicy.REMOTE_ONLY.ordinal());
            _offlinePolicy = OfflinePolicy.values()[offlinePolicy];
            _groupId = typedArray.getInt(R.styleable.EntryListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());
            typedArray.recycle();

            return super.createScreenletView(context, attributes);
        }

        @Override
        protected void loadRows(EntryListInteractorImpl interactor, int startRow,
                            int endRow, Locale locale) throws Exception {

            interactor.loadRows(startRow, endRow, locale, _groupId, _guestbookId);
        }

        @Override
        protected EntryListInteractorImpl createInteractor(String actionName) {
            return new EntryListInteractorImpl(getScreenletId(), _offlinePolicy);
        }
    }

The instance variable for the guestbook ID is `_guestbookId`. The getter and 
setter methods `getGuestbookId` and `setGuestbookId` let the app developer get 
and set this variable, respectively. The `loadRows` method calls the 
Interactor class's `loadRows` method with `_guestbookId` as an argument. This 
results in the call that retrieves that guestbook's entries. For a full 
explanation of how the Screenlet class works, see 
[the article on creating Guestbook List Screenlet's Screenlet class](https://www.liferay.com/). 

That's it! Now you're ready to use Entry List Screenlet alongside Guestbook List 
Screenlet. The following section of this Learning Path concludes with both 
Screenlets working together in harmony. 
