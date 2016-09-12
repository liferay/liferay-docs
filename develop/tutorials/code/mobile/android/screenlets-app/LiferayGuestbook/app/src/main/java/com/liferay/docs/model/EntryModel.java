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