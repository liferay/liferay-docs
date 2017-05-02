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
        entryId = ((Integer) stringObjectMap.get("entryId")).longValue();
        groupId = ((Integer) stringObjectMap.get("groupId")).longValue();
        companyId = ((Integer) stringObjectMap.get("companyId")).longValue();
        userId = ((Integer) stringObjectMap.get("userId")).longValue();
        userName = (String) stringObjectMap.get("userName");
        createDate = (long) stringObjectMap.get("createDate");
        modifiedDate = (long) stringObjectMap.get("modifiedDate");
        name = (String) stringObjectMap.get("name");
        email = (String) stringObjectMap.get("email");
        message = (String) stringObjectMap.get("message");
        guestbookId = ((Integer) stringObjectMap.get("guestbookId")).longValue();
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