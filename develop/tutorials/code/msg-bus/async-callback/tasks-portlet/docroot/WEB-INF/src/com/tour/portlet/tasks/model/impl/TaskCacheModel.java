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

import com.tour.portlet.tasks.model.Task;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Task in entity cache.
 *
 * @author nickgaskill
 * @see Task
 * @generated
 */
public class TaskCacheModel implements CacheModel<Task>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{taskId=");
		sb.append(taskId);
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
		sb.append(", roadieResponse=");
		sb.append(roadieResponse);
		sb.append(", inventoryResponse=");
		sb.append(inventoryResponse);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Task toEntityModel() {
		TaskImpl taskImpl = new TaskImpl();

		taskImpl.setTaskId(taskId);

		if (name == null) {
			taskImpl.setName(StringPool.BLANK);
		}
		else {
			taskImpl.setName(name);
		}

		if (description == null) {
			taskImpl.setDescription(StringPool.BLANK);
		}
		else {
			taskImpl.setDescription(description);
		}

		if (status == null) {
			taskImpl.setStatus(StringPool.BLANK);
		}
		else {
			taskImpl.setStatus(status);
		}

		taskImpl.setGroupId(groupId);
		taskImpl.setCompanyId(companyId);
		taskImpl.setUserId(userId);

		if (roadieResponse == null) {
			taskImpl.setRoadieResponse(StringPool.BLANK);
		}
		else {
			taskImpl.setRoadieResponse(roadieResponse);
		}

		if (inventoryResponse == null) {
			taskImpl.setInventoryResponse(StringPool.BLANK);
		}
		else {
			taskImpl.setInventoryResponse(inventoryResponse);
		}

		taskImpl.resetOriginalValues();

		return taskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		taskId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		status = objectInput.readUTF();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		roadieResponse = objectInput.readUTF();
		inventoryResponse = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(taskId);

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

		if (roadieResponse == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(roadieResponse);
		}

		if (inventoryResponse == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inventoryResponse);
		}
	}

	public long taskId;
	public String name;
	public String description;
	public String status;
	public long groupId;
	public long companyId;
	public long userId;
	public String roadieResponse;
	public String inventoryResponse;
}