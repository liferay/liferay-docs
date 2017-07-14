package com.liferay.docs.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Map;

public class GuestbookModel implements Parcelable {

    private Map values;
    private long guestbookId;
    private long groupId;
    private long companyId;
    private long userId;
    private String userName;
    private long createDate;
    private long modifiedDate;
    private String name;

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

    public GuestbookModel() {
        super();
    }

    protected GuestbookModel(Parcel in) {
        guestbookId = in.readLong();
        groupId = in.readLong();
        companyId = in.readLong();
        userId = in.readLong();
        userName = in.readString();
        createDate = in.readLong();
        modifiedDate = in.readLong();
        name = in.readString();
    }

    public GuestbookModel(Map<String, Object> stringObjectMap) {
        values = stringObjectMap;
        guestbookId =  ((Integer) stringObjectMap.get("guestbookId")).longValue();
        groupId = ((Integer) stringObjectMap.get("groupId")).longValue();
        companyId = ((Integer) stringObjectMap.get("companyId")).longValue();
        userId = ((Integer) stringObjectMap.get("userId")).longValue();
        userName = (String) stringObjectMap.get("userName");
        createDate = (long) stringObjectMap.get("createDate");
        modifiedDate = (long) stringObjectMap.get("modifiedDate");
        name = (String) stringObjectMap.get("name");
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(guestbookId);
        dest.writeLong(groupId);
        dest.writeLong(companyId);
        dest.writeLong(userId);
        dest.writeString(userName);
        dest.writeLong(createDate);
        dest.writeLong(modifiedDate);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getGuestbookId() {
        return guestbookId;
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

    public Map getValues() {
        return values;
    }

    public void setValues(Map values) {
        this.values = values;
    }
}