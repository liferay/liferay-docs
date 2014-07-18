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
public class SetupSoap implements Serializable {
	public static SetupSoap toSoapModel(Setup model) {
		SetupSoap soapModel = new SetupSoap();

		soapModel.setSetupId(model.getSetupId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setStatus(model.getStatus());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static SetupSoap[] toSoapModels(Setup[] models) {
		SetupSoap[] soapModels = new SetupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SetupSoap[][] toSoapModels(Setup[][] models) {
		SetupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SetupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SetupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SetupSoap[] toSoapModels(List<Setup> models) {
		List<SetupSoap> soapModels = new ArrayList<SetupSoap>(models.size());

		for (Setup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SetupSoap[soapModels.size()]);
	}

	public SetupSoap() {
	}

	public long getPrimaryKey() {
		return _setupId;
	}

	public void setPrimaryKey(long pk) {
		setSetupId(pk);
	}

	public long getSetupId() {
		return _setupId;
	}

	public void setSetupId(long setupId) {
		_setupId = setupId;
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

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
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

	private long _setupId;
	private String _name;
	private String _description;
	private String _status;
	private long _groupId;
	private long _companyId;
	private long _userId;
}