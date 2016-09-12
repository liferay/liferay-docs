# Getting Started with Entry List Screenlet [](id=getting-started-with-entry-list-screenlet)

Like Guestbook List Screenlet, you'll create Entry List Screenlet in a new 
package inside your app's project. Get started by creating the package 
`com.liferay.docs.entrylistscreenlet`. Once you have this package, you're ready 
to start writing the Screenlet. 

## Creating the Model Class for Entries [](id=creating-the-model-class-for-entries)

Entry List Screenlet must call the Guestbook portlet with the same Guestbook 
Mobile SDK you built earlier. As it does for guestbooks, this Mobile SDK returns 
entries from the portlet in a `JSONArray` that contains each entry in a 
`JSONObject`. Recall that Screens's 
[`BaseListCallback` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListCallback.java) 
automatically transforms these JSON entities into `Map` objects for you, and 
that you must create a model class that converts these `Map` objects into entry 
objects. This gives you an efficient way to work with entries throughout 
the Screenlet and in any app that uses it. The model class you'll create 
here--`EntryModel`--creates `EntryModel` objects that serve as entry objects. 

Create the following `EntryModel` class alongside the `GuestbookModel` class in 
the `com.liferay.docs.model` package: 

    package com.liferay.docs.model;

    import android.os.Parcel;
    import android.os.Parcelable;

    import java.util.Date;
    import java.util.Map;


    public class EntryModel implements Parcelable {

        private Map _values;
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

        public static final Creator<EntryModel> CREATOR = new Creator<EntryModel>() {
            @Override
            public EntryModel createFromParcel(Parcel in) {
                return new EntryModel(in);
            }

            @Override
            public EntryModel[] newArray(int size) {
                return new EntryModel[size];
            }
        };

        protected EntryModel(Parcel in) {
            _entryId = in.readLong();
            _groupId = in.readLong();
            _companyId = in.readLong();
            _userId = in.readLong();
            _userName = in.readString();
            _createDate = in.readLong();
            _modifiedDate = in.readLong();
            _name = in.readString();
            _email = in.readString();
            _message = in.readString();
            _guestbookId = in.readLong();
        }

        public EntryModel(Map<String, Object> stringObjectMap) {
            _values = stringObjectMap;
            _entryId = Long.parseLong((String) stringObjectMap.get("entryId"));
            _groupId = Long.parseLong((String) stringObjectMap.get("groupId"));
            _companyId = Long.parseLong((String) stringObjectMap.get("companyId"));
            _userId = Long.parseLong((String) stringObjectMap.get("userId"));
            _userName = (String) stringObjectMap.get("userName");
            _createDate = (long) stringObjectMap.get("createDate");
            _modifiedDate = (long) stringObjectMap.get("modifiedDate");
            _name = (String) stringObjectMap.get("name");
            _email = (String) stringObjectMap.get("email");
            _message = (String) stringObjectMap.get("message");
            _guestbookId = Long.parseLong((String) stringObjectMap.get("guestbookId"));
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(_entryId);
            dest.writeLong(_groupId);
            dest.writeLong(_companyId);
            dest.writeLong(_userId);
            dest.writeString(_userName);
            dest.writeLong(_createDate);
            dest.writeLong(_modifiedDate);
            dest.writeString(_name);
            dest.writeString(_email);
            dest.writeString(_message);
            dest.writeLong(_guestbookId);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public Map getValues() {
            return _values;
        }

        public void setValues(Map values) {
            _values = values;
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
`GuestbookModel`. For an explanation of the code, see 
[the article on getting started with Guestbook List Screenlet](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-guestbook-list-screenlet). 

Next, you'll create the Screenlet's UI. 
