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

package com.liferay.docs.insult.model.impl;

import com.liferay.docs.insult.model.Insult;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Insult in entity cache.
 *
 * @author joebloggs
 * @see Insult
 * @generated
 */
public class InsultCacheModel implements CacheModel<Insult>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", insultId=");
		sb.append(insultId);
		sb.append(", insultString=");
		sb.append(insultString);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Insult toEntityModel() {
		InsultImpl insultImpl = new InsultImpl();

		if (uuid == null) {
			insultImpl.setUuid(StringPool.BLANK);
		}
		else {
			insultImpl.setUuid(uuid);
		}

		insultImpl.setInsultId(insultId);

		if (insultString == null) {
			insultImpl.setInsultString(StringPool.BLANK);
		}
		else {
			insultImpl.setInsultString(insultString);
		}

		insultImpl.setUserId(userId);
		insultImpl.setGroupId(groupId);
		insultImpl.setCompanyId(companyId);

		if (userName == null) {
			insultImpl.setUserName(StringPool.BLANK);
		}
		else {
			insultImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			insultImpl.setCreateDate(null);
		}
		else {
			insultImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			insultImpl.setModifiedDate(null);
		}
		else {
			insultImpl.setModifiedDate(new Date(modifiedDate));
		}

		insultImpl.resetOriginalValues();

		return insultImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		insultId = objectInput.readLong();
		insultString = objectInput.readUTF();
		userId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(insultId);

		if (insultString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insultString);
		}

		objectOutput.writeLong(userId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
	}

	public String uuid;
	public long insultId;
	public String insultString;
	public long userId;
	public long groupId;
	public long companyId;
	public String userName;
	public long createDate;
	public long modifiedDate;
}