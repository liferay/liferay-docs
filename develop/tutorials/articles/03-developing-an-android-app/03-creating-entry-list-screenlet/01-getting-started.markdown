# Getting Started with Entry List Screenlet [](id=getting-started-with-entry-list-screenlet)

Like Guestbook List Screenlet, you'll create Entry List Screenlet in a new 
package inside your app's project. Get started by creating the package 
`com.liferay.docs.entrylistscreenlet`. Once you have this package, you're ready 
to start writing the Screenlet. 

## Creating the Model Class for Entries [](id=creating-the-model-class-for-entries)

Recall that you need a model class to represent entities retrieved from 
@product@. The model class you'll create for guestbook entries, `EntryModel`, 
creates `EntryModel` objects that serve as guestbook entries retrieved from 
@product@. 

Create the following `EntryModel` class alongside the `GuestbookModel` class in 
the `com.liferay.docs.model` package: 

    package com.liferay.docs.model;

    import android.os.Parcel;
    import android.os.Parcelable;

    import java.util.Date;
    import java.util.Map;

    public class EntryModel implements Parcelable {

        private Map values;
        private long entryId;
        private long groupId;
        private long companyId;
        private long userId;
        private String userName;
        private long createDate;
        private long modifiedDate;
        private String name;
        private String email;
        private String message;
        private long guestbookId;

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

        public EntryModel() {
            super();
        }

        protected EntryModel(Parcel in) {
            entryId = in.readLong();
            groupId = in.readLong();
            companyId = in.readLong();
            userId = in.readLong();
            userName = in.readString();
            createDate = in.readLong();
            modifiedDate = in.readLong();
            name = in.readString();
            email = in.readString();
            message = in.readString();
            guestbookId = in.readLong();
        }

        public EntryModel(Map<String, Object> stringObjectMap) {
            values = stringObjectMap;
            entryId = Long.parseLong((String) stringObjectMap.get("entryId"));
            groupId = Long.parseLong((String) stringObjectMap.get("groupId"));
            companyId = Long.parseLong((String) stringObjectMap.get("companyId"));
            userId = Long.parseLong((String) stringObjectMap.get("userId"));
            userName = (String) stringObjectMap.get("userName");
            createDate = (long) stringObjectMap.get("createDate");
            modifiedDate = (long) stringObjectMap.get("modifiedDate");
            name = (String) stringObjectMap.get("name");
            email = (String) stringObjectMap.get("email");
            message = (String) stringObjectMap.get("message");
            guestbookId = Long.parseLong((String) stringObjectMap.get("guestbookId"));
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(entryId);
            dest.writeLong(groupId);
            dest.writeLong(companyId);
            dest.writeLong(userId);
            dest.writeString(userName);
            dest.writeLong(createDate);
            dest.writeLong(modifiedDate);
            dest.writeString(name);
            dest.writeString(email);
            dest.writeString(message);
            dest.writeLong(guestbookId);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public Map getValues() {
            return values;
        }

        public void setValues(Map values) {
            this.values = values;
        }

        public long getEntryId() {
            return entryId;
        }

        public long getGroupId() {
            return groupId;
        }

        public long getCompanyId() {
            return companyId;
        }

        public long getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public Date getCreateDate() {
            return new Date(createDate);
        }

        public Date getModifiedDate() {
            return new Date(modifiedDate);
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getMessage() {
            return message;
        }

        public long getGuestbookId() {
            return guestbookId;
        }

    }

Besides working with entries instead of guestbooks, this class works the same as 
`GuestbookModel`. For an explanation of the code, see 
[the article on getting started with Guestbook List Screenlet](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-guestbook-list-screenlet). 

Next, you'll create the Screenlet's UI. 
