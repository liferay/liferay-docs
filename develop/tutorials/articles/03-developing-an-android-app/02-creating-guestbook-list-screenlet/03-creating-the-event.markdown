# Creating the Event

Recall that Screens uses the EventBus library to communicate the server call's 
results between Screenlet components. Screens does this via event objects that 
contain the server call's results. List Screenlets must therefore contain an 
event class that can create such objects. For more information and instructions 
on creating an event class, see 
[the section of the list Screenlet tutorial on event classes](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-screenlets-event). 
You'll follow those instructions here to create Guestbook List Screenlet's event 
class. 

First, create the `GuestbookEvent` class in the package 
`com.liferay.docs.guestbooklistscreenlet`. Replace its contents with the 
following code: 

    package com.liferay.docs.guestbooklistscreenlet;

    import com.liferay.docs.model.GuestbookModel;
    import com.liferay.mobile.screens.base.list.interactor.ListEvent;

    public class GuestbookEvent extends ListEvent<GuestbookModel> {

        private GuestbookModel guestbook;

        public GuestbookEvent() {
            super();
        }

        public GuestbookEvent(GuestbookModel guestbook) {
            this.guestbook = guestbook;
        }

        @Override
        public String getListKey() {
            return guestbook.getName();
        }

        @Override
        public GuestbookModel getModel() {
            return guestbook;
        }
    }

Note that this code is almost identical to that of the example `BookmarkEvent` 
in the list Screenlet tutorial. The only difference is that `GuestbookEvent` is 
adapted for guestbooks. 

Nice work! Your event class is done. You're almost ready to write the 
Screenlet's server call. Because Screenlets use the Mobile SDK to make server 
calls, you should first learn how Mobile SDK calls work. The next article shows 
you this. 
