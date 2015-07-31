package com.liferay.docs.liferayguestbook.model;

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
        if (!(obj instanceof GuestbookModel)) {
            return false;
        }

        EntryModel entry = (EntryModel)obj;

        return (_entryId == entry.getEntryId());
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
