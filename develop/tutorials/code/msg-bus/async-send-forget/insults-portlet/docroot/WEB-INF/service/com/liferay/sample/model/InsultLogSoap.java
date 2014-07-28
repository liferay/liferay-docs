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
public class InsultLogSoap implements Serializable {
	public static InsultLogSoap toSoapModel(InsultLog model) {
		InsultLogSoap soapModel = new InsultLogSoap();

		soapModel.setInsultLogId(model.getInsultLogId());
		soapModel.setInsult(model.getInsult());
		soapModel.setRating(model.getRating());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static InsultLogSoap[] toSoapModels(InsultLog[] models) {
		InsultLogSoap[] soapModels = new InsultLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InsultLogSoap[][] toSoapModels(InsultLog[][] models) {
		InsultLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InsultLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InsultLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InsultLogSoap[] toSoapModels(List<InsultLog> models) {
		List<InsultLogSoap> soapModels = new ArrayList<InsultLogSoap>(models.size());

		for (InsultLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InsultLogSoap[soapModels.size()]);
	}

	public InsultLogSoap() {
	}

	public long getPrimaryKey() {
		return _insultLogId;
	}

	public void setPrimaryKey(long pk) {
		setInsultLogId(pk);
	}

	public long getInsultLogId() {
		return _insultLogId;
	}

	public void setInsultLogId(long insultLogId) {
		_insultLogId = insultLogId;
	}

	public String getInsult() {
		return _insult;
	}

	public void setInsult(String insult) {
		_insult = insult;
	}

	public String getRating() {
		return _rating;
	}

	public void setRating(String rating) {
		_rating = rating;
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

	private long _insultLogId;
	private String _insult;
	private String _rating;
	private long _groupId;
	private long _companyId;
	private long _userId;
}