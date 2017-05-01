# Creating Entry List Screenlet's Interactor [](id=creating-entry-list-screenlets-interactor)

Recall that Interactors are Screenlet components that make server calls and 
process the results. Also recall that Interactors themselves are made up of 
several components: 

1. The event class
2. The listener interface
3. The Interactor class

Since the list Screenlet framework already contains two listeners, you only need 
to create the event and Interactor classes. This article walks you through the 
steps required do this. Because Entry List Screenlet's Interactor is so similar 
to that of Guestbook List Screenlet, these steps aren't explained in detail. 
Focus is instead placed on the few places in the code where the Interactors 
diverge. For a full explanation of the code, 
[click here](/develop/tutorials/-/knowledge_base/6-2/creating-guestbook-list-screenlets-interactor) 
to see the article on creating Guestbook List Screenlet's Interactor. 

You'll create the event class first. 

## Creating the Event Class [](id=creating-the-event-class)

Recall that you must create an event class to communicate the server call's 
results via 
[EventBus](http://greenrobot.org/eventbus/). 
First, create a new package called `interactor` in the 
`com.liferay.docs.entrylistscreenlet` package. Then create the `EntryEvent` 
class in the `interactor` package. Replace this class's contents with this code: 

    package com.liferay.docs.entrylistscreenlet.interactor;

    import com.liferay.docs.model.EntryModel;
    import com.liferay.mobile.screens.base.list.interactor.ListEvent;

    public class EntryEvent extends ListEvent<EntryModel> {

        private EntryModel entry;

        public EntryEvent() {
            super();
        }

        public EntryEvent(EntryModel entry) {
            this.entry = entry;
        }

        @Override
        public String getListKey() {
            return entry.getMessage();
        }

        @Override
        public EntryModel getModel() {
            return entry;
        }
    }

This code is almost identical to `GuestbookEvent`. The only difference is that 
it works with entries instead of guestbooks. 

Next, you'll create the Interactor class. 

## Creating the Interactor Class [](id=creating-the-interactor-class)

Recall that an Interactor class issues the server call and processes the results 
via the event. In the `interactor` package, create a new class called 
`EntryListInteractor`. Replace this class's content with this code: 

    package com.liferay.docs.entrylistscreenlet.interactor;

    import com.liferay.docs.model.EntryModel;
    import com.liferay.mobile.android.v62.entry.EntryService;
    import com.liferay.mobile.screens.base.list.interactor.BaseListInteractor;
    import com.liferay.mobile.screens.base.list.interactor.BaseListInteractorListener;
    import com.liferay.mobile.screens.base.list.interactor.Query;

    import org.json.JSONArray;
    import java.util.Map;

    public class EntryListInteractor extends
        BaseListInteractor<BaseListInteractorListener<EntryModel>, EntryEvent> {

        @Override
        protected JSONArray getPageRowsRequest(Query query, Object... args) throws Exception {

            long guestbookId = (long) args[0];
            return new EntryService(getSession()).getEntries(groupId, guestbookId, 
                query.getStartRow(), query.getEndRow());
        }

        @Override
        protected Integer getPageRowCountRequest(Object... args) throws Exception {

            long guestbookId = (long) args[0];
            return new EntryService(getSession()).getEntriesCount(groupId, guestbookId);
        }

        @Override
        protected EntryEvent createEntity(Map<String, Object> stringObjectMap) {
            EntryModel entry = new EntryModel(stringObjectMap);
            return new EntryEvent(entry);
        }

        @Override
        protected String getIdFromArgs(Object... args) {
            return String.valueOf(args[0]);
        }
    }

Besides getting entries instead of guestbooks, this class is almost identical to 
`GuestbookListInteractor`. The only other differences are due to the service 
calls that retrieve the entries and number of entries from a guestbook in the 
Guestbook portlet. These service calls, made in `getPageRowsRequest` and 
`getPageRowCountRequest`, require an `EntryService` instance. The `getEntries` 
method retrieves a guestbook's entries, and the `getEntriesCount` method 
retrieves the number of entries in a guestbook. Note that these calls require a 
guestbook ID (`guestbookId`) in addition to the group ID (`groupId`). The 
`getPageRowsRequest` and `getPageRowCountRequest` methods get the `guestbookId` 
from the `args` argument, and then use it along with `groupId` make their 
service calls. You'll see how the `guestbookId` gets into the `args` argument 
when you create the Screenlet class. 

Nicely done! Now that Entry List Screenlet has an Interactor, you must create 
the Screenlet class. The next article shows you how to do this. 
