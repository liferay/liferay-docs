package com.liferay.docs.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Map;

public class GuestbookModel implements Parcelable {

    private Map _values;
    private long _guestbookId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userName;
    private long _createDate;
    private long _modifiedDate;
    private String _name;

    public static final Creator<GuestbookModel> CREATOR = new Creator<GuestbookModel>() {
        @Override
        public GuestbookModel createFromParcel(Parcel in) {
            return new GuestbookModel(in);
        }

        @Override
        public GuestbookModel[] newArray(int size) {
            return new GuestbookModel[size];
        }
    };

    protected GuestbookModel(Parcel in) {
        _guestbookId = in.readLong();
        _groupId = in.readLong();
        _companyId = in.readLong();
        _userId = in.readLong();
        _userName = in.readString();
        _createDate = in.readLong();
        _modifiedDate = in.readLong();
        _name = in.readString();
    }

    public GuestbookModel(Map<String, Object> stringObjectMap) {
        _values = stringObjectMap;
        _guestbookId = Long.parseLong((String) stringObjectMap.get("guestbookId"));
        _groupId = Long.parseLong((String) stringObjectMap.get("groupId"));
        _companyId = Long.parseLong((String) stringObjectMap.get("companyId"));
        _userId = Long.parseLong((String) stringObjectMap.get("userId"));
        _userName = (String) stringObjectMap.get("userName");
        _createDate = (long) stringObjectMap.get("createDate");
        _modifiedDate = (long) stringObjectMap.get("modifiedDate");
        _name = (String) stringObjectMap.get("name");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_guestbookId);
        dest.writeLong(_groupId);
        dest.writeLong(_companyId);
        dest.writeLong(_userId);
        dest.writeString(_userName);
        dest.writeLong(_createDate);
        dest.writeLong(_modifiedDate);
        dest.writeString(_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getGuestbookId() {
        return _guestbookId;
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

    public Map getValues() {
        return _values;
    }

    public void setValues(Map values) {
        _values = values;
    }
}