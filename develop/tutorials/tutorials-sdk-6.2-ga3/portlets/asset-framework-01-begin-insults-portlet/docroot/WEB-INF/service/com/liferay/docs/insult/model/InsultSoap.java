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

package com.liferay.docs.insult.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author joebloggs
 * @generated
 */
public class InsultSoap implements Serializable {
	public static InsultSoap toSoapModel(Insult model) {
		InsultSoap soapModel = new InsultSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setInsultId(model.getInsultId());
		soapModel.setInsultString(model.getInsultString());
		soapModel.setUserId(model.getUserId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static InsultSoap[] toSoapModels(Insult[] models) {
		InsultSoap[] soapModels = new InsultSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InsultSoap[][] toSoapModels(Insult[][] models) {
		InsultSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InsultSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InsultSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InsultSoap[] toSoapModels(List<Insult> models) {
		List<InsultSoap> soapModels = new ArrayList<InsultSoap>(models.size());

		for (Insult model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InsultSoap[soapModels.size()]);
	}

	public InsultSoap() {
	}

	public long getPrimaryKey() {
		return _insultId;
	}

	public void setPrimaryKey(long pk) {
		setInsultId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getInsultId() {
		return _insultId;
	}

	public void setInsultId(long insultId) {
		_insultId = insultId;
	}

	public String getInsultString() {
		return _insultString;
	}

	public void setInsultString(String insultString) {
		_insultString = insultString;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private String _uuid;
	private long _insultId;
	private String _insultString;
	private long _userId;
	private long _groupId;
	private long _companyId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
}