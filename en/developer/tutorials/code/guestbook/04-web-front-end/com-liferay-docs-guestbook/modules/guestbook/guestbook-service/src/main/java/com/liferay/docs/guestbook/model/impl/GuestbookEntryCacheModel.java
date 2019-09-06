/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.guestbook.model.impl;

import com.liferay.docs.guestbook.model.GuestbookEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing GuestbookEntry in entity cache.
 *
 * @author Liferay
 * @generated
 */
@ProviderType
public class GuestbookEntryCacheModel
	implements CacheModel<GuestbookEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GuestbookEntryCacheModel)) {
			return false;
		}

		GuestbookEntryCacheModel guestbookEntryCacheModel =
			(GuestbookEntryCacheModel)obj;

		if (entryId == guestbookEntryCacheModel.entryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", entryId=");
		sb.append(entryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", email=");
		sb.append(email);
		sb.append(", message=");
		sb.append(message);
		sb.append(", guestbookId=");
		sb.append(guestbookId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GuestbookEntry toEntityModel() {
		GuestbookEntryImpl guestbookEntryImpl = new GuestbookEntryImpl();

		if (uuid == null) {
			guestbookEntryImpl.setUuid("");
		}
		else {
			guestbookEntryImpl.setUuid(uuid);
		}

		guestbookEntryImpl.setEntryId(entryId);

		if (name == null) {
			guestbookEntryImpl.setName("");
		}
		else {
			guestbookEntryImpl.setName(name);
		}

		if (email == null) {
			guestbookEntryImpl.setEmail("");
		}
		else {
			guestbookEntryImpl.setEmail(email);
		}

		if (message == null) {
			guestbookEntryImpl.setMessage("");
		}
		else {
			guestbookEntryImpl.setMessage(message);
		}

		guestbookEntryImpl.setGuestbookId(guestbookId);
		guestbookEntryImpl.setGroupId(groupId);
		guestbookEntryImpl.setCompanyId(companyId);
		guestbookEntryImpl.setUserId(userId);

		if (userName == null) {
			guestbookEntryImpl.setUserName("");
		}
		else {
			guestbookEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			guestbookEntryImpl.setCreateDate(null);
		}
		else {
			guestbookEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			guestbookEntryImpl.setModifiedDate(null);
		}
		else {
			guestbookEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		guestbookEntryImpl.setStatus(status);
		guestbookEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			guestbookEntryImpl.setStatusByUserName("");
		}
		else {
			guestbookEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			guestbookEntryImpl.setStatusDate(null);
		}
		else {
			guestbookEntryImpl.setStatusDate(new Date(statusDate));
		}

		guestbookEntryImpl.resetOriginalValues();

		return guestbookEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		entryId = objectInput.readLong();
		name = objectInput.readUTF();
		email = objectInput.readUTF();
		message = objectInput.readUTF();

		guestbookId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(entryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (message == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(message);
		}

		objectOutput.writeLong(guestbookId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long entryId;
	public String name;
	public String email;
	public String message;
	public long guestbookId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}