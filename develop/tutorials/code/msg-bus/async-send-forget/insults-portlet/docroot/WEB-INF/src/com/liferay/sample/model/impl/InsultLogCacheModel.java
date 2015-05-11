/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sample.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.sample.model.InsultLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing InsultLog in entity cache.
 *
 * @author nickgaskill
 * @see InsultLog
 * @generated
 */
public class InsultLogCacheModel implements CacheModel<InsultLog>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{insultLogId=");
		sb.append(insultLogId);
		sb.append(", insult=");
		sb.append(insult);
		sb.append(", rating=");
		sb.append(rating);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public InsultLog toEntityModel() {
		InsultLogImpl insultLogImpl = new InsultLogImpl();

		insultLogImpl.setInsultLogId(insultLogId);

		if (insult == null) {
			insultLogImpl.setInsult(StringPool.BLANK);
		}
		else {
			insultLogImpl.setInsult(insult);
		}

		if (rating == null) {
			insultLogImpl.setRating(StringPool.BLANK);
		}
		else {
			insultLogImpl.setRating(rating);
		}

		insultLogImpl.setGroupId(groupId);
		insultLogImpl.setCompanyId(companyId);
		insultLogImpl.setUserId(userId);

		insultLogImpl.resetOriginalValues();

		return insultLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		insultLogId = objectInput.readLong();
		insult = objectInput.readUTF();
		rating = objectInput.readUTF();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(insultLogId);

		if (insult == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insult);
		}

		if (rating == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rating);
		}

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
	}

	public long insultLogId;
	public String insult;
	public String rating;
	public long groupId;
	public long companyId;
	public long userId;
}