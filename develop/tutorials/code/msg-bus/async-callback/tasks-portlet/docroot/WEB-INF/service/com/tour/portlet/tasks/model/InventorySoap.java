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

package com.tour.portlet.tasks.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author nickgaskill
 * @generated
 */
public class InventorySoap implements Serializable {
	public static InventorySoap toSoapModel(Inventory model) {
		InventorySoap soapModel = new InventorySoap();

		soapModel.setInventoryId(model.getInventoryId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static InventorySoap[] toSoapModels(Inventory[] models) {
		InventorySoap[] soapModels = new InventorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InventorySoap[][] toSoapModels(Inventory[][] models) {
		InventorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InventorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new InventorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InventorySoap[] toSoapModels(List<Inventory> models) {
		List<InventorySoap> soapModels = new ArrayList<InventorySoap>(models.size());

		for (Inventory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InventorySoap[soapModels.size()]);
	}

	public InventorySoap() {
	}

	public long getPrimaryKey() {
		return _inventoryId;
	}

	public void setPrimaryKey(long pk) {
		setInventoryId(pk);
	}

	public long getInventoryId() {
		return _inventoryId;
	}

	public void setInventoryId(long inventoryId) {
		_inventoryId = inventoryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _inventoryId;
	private String _name;
	private String _description;
	private long _groupId;
	private long _companyId;
	private long _userId;
}