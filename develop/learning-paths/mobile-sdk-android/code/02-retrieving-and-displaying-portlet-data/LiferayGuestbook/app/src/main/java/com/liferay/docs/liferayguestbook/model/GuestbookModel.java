package com.liferay.docs.liferayguestbook.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class GuestbookModel implements Serializable {

    private long _guestbookId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userName;
    private long _createDate;
    private long _modifiedDate;
    private String _name;

    public GuestbookModel(JSONObject json) throws JSONException {
        _guestbookId = json.getLong("guestbookId");
        _groupId = json.getLong("groupId");
        _companyId = json.getLong("companyId");
        _userId = json.getLong("userId");
        _userName = json.getString("userName");
        _createDate = json.getLong("createDate");
        _modifiedDate = json.getLong("modifiedDate");
        _name = json.getString("name");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GuestbookModel)) {
            return false;
        }

        GuestbookModel guestbook = (GuestbookModel)obj;

        return (_guestbookId == guestbook.getGuestbookId());
    }

    @Override
    public String toString() {
        return _name;
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

    public String getGuestbookName() {
        return _name;
    }
}
