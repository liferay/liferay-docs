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

import com.tour.portlet.tasks.model.Inventory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Inventory in entity cache.
 *
 * @author nickgaskill
 * @see Inventory
 * @generated
 */
public class InventoryCacheModel implements CacheModel<Inventory>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{inventoryId=");
		sb.append(inventoryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
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
	public Inventory toEntityModel() {
		InventoryImpl inventoryImpl = new InventoryImpl();

		inventoryImpl.setInventoryId(inventoryId);

		if (name == null) {
			inventoryImpl.setName(StringPool.BLANK);
		}
		else {
			inventoryImpl.setName(name);
		}

		if (description == null) {
			inventoryImpl.setDescription(StringPool.BLANK);
		}
		else {
			inventoryImpl.setDescription(description);
		}

		inventoryImpl.setGroupId(groupId);
		inventoryImpl.setCompanyId(companyId);
		inventoryImpl.setUserId(userId);

		inventoryImpl.resetOriginalValues();

		return inventoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		inventoryId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(inventoryId);

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

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
	}

	public long inventoryId;
	public String name;
	public String description;
	public long groupId;
	public long companyId;
	public long userId;
}