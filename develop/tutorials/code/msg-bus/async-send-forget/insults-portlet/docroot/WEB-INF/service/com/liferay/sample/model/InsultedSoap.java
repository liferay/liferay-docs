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

package com.liferay.sample.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author nickgaskill
 * @generated
 */
public class InsultedSoap implements Serializable {
	public static InsultedSoap toSoapModel(Insulted model) {
		InsultedSoap soapModel = new InsultedSoap();

		soapModel.setInsultedId(model.getInsultedId());
		soapModel.setInsult(model.getInsult());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static InsultedSoap[] toSoapModels(Insulted[] models) {
		InsultedSoap[] soapModels = new InsultedSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InsultedSoap[][] toSoapModels(Insulted[][] models) {
		InsultedSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InsultedSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InsultedSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InsultedSoap[] toSoapModels(List<Insulted> models) {
		List<InsultedSoap> soapModels = new ArrayList<InsultedSoap>(models.size());

		for (Insulted model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InsultedSoap[soapModels.size()]);
	}

	public InsultedSoap() {
	}

	public long getPrimaryKey() {
		return _insultedId;
	}

	public void setPrimaryKey(long pk) {
		setInsultedId(pk);
	}

	public long getInsultedId() {
		return _insultedId;
	}

	public void setInsultedId(long insultedId) {
		_insultedId = insultedId;
	}

	public String getInsult() {
		return _insult;
	}

	public void setInsult(String insult) {
		_insult = insult;
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

	private long _insultedId;
	private String _insult;
	private long _groupId;
	private long _companyId;
	private long _userId;
}