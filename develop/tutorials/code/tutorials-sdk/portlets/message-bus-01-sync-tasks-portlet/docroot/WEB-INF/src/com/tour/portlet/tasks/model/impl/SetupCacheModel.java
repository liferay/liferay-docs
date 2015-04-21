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

package com.tour.portlet.tasks.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.tour.portlet.tasks.model.Setup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Setup in entity cache.
 *
 * @author nickgaskill
 * @see Setup
 * @generated
 */
public class SetupCacheModel implements CacheModel<Setup>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{setupId=");
		sb.append(setupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", status=");
		sb.append(status);
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
	public Setup toEntityModel() {
		SetupImpl setupImpl = new SetupImpl();

		setupImpl.setSetupId(setupId);

		if (name == null) {
			setupImpl.setName(StringPool.BLANK);
		}
		else {
			setupImpl.setName(name);
		}

		if (description == null) {
			setupImpl.setDescription(StringPool.BLANK);
		}
		else {
			setupImpl.setDescription(description);
		}

		if (status == null) {
			setupImpl.setStatus(StringPool.BLANK);
		}
		else {
			setupImpl.setStatus(status);
		}

		setupImpl.setGroupId(groupId);
		setupImpl.setCompanyId(companyId);
		setupImpl.setUserId(userId);

		setupImpl.resetOriginalValues();

		return setupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		setupId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		status = objectInput.readUTF();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(setupId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
	}

	public long setupId;
	public String name;
	public String description;
	public String status;
	public long groupId;
	public long companyId;
	public long userId;
}