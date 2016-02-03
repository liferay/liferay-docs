# Getting Started with Get Entries Screenlet [](id=getting-started-with-get-entries-screenlet)

Like Get Guestbooks Screenlet, you'll create Get Entries Screenlet in a new 
package inside your app's project. You'll do this because you don't need to 
redistribute the Screenlet. Get started by creating the package 
`com.liferay.docs.getentriesscreenlet`. Once you have this package, you're ready 
to start writing the Screenlet. 

## Creating the Model Class for Entries [](id=creating-the-model-class-for-entries)

As with Get Guestbooks Screenlet, Get Entries Screenlet must use the same 
Guestbook Mobile SDK you built earlier to call the Guestbook portlet. As it does 
for guestbooks, this Mobile SDK returns entries from the portlet in a 
`JSONArray` that contains each entry in a `JSONObject`. You must therefore 
create a model class that can transform each `JSONObject` into a proper entry 
model object that is easier to work with in your app. To do this, create the 
following `EntryModel` class in the `com.liferay.docs.model` package:

    package com.liferay.docs.model;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.Serializable;
    import java.util.Date;


    public class EntryModel implements Serializable {

        private long _entryId;
        private long _groupId;
        private long _companyId;
        private long _userId;
        private String _userName;
        private long _createDate;
        private long _modifiedDate;
        private String _name;
        private String _email;
        private String _message;
        private long _guestbookId;

        public EntryModel(JSONObject json) throws JSONException {

            _entryId = json.getLong("entryId");
            _groupId = json.getLong("groupId");
            _companyId = json.getLong("companyId");
            _userId = json.getLong("userId");
            _userName = json.getString("userName");
            _createDate = json.getLong("createDate");
            _modifiedDate = json.getLong("modifiedDate");
            _name = json.getString("name");
            _email = json.getString("email");
            _message = json.getString("message");
            _guestbookId = json.getLong("guestbookId");
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof EntryModel)) {
                return false;
            }

            EntryModel entry = (EntryModel) obj;

            return (_entryId == entry.getEntryId());
        }

        @Override
        public String toString() {
            return _message;
        }

        public long getEntryId() {
            return _entryId;
        }

        public long getGroupId() {
            return _groupId;
        }

        public long getCompanyId() {
            return _companyId;
        }

        public long getUserId() {
            return _userId;
        }

        public String getUserName() {
            return _userName;
        }

        public Date getCreateDate() {
            Date createDate = new Date(_createDate);
            return createDate;
        }

        public Date getModifiedDate() {
            Date modifiedDate = new Date(_modifiedDate);
            return modifiedDate;
        }

        public String getName() {
            return _name;
        }

        public String getEmail() {
            return _email;
        }

        public String getMessage() {
            return _message;
        }

        public long getGuestbookId() {
            return _guestbookId;
        }
    }

Besides working with entries instead of guestbooks, this class works the same as 
`GuestbookModel`. 

Next, you need to create the Screenlet's UI. 
