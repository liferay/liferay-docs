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
public class InsultWriterSoap implements Serializable {
	public static InsultWriterSoap toSoapModel(InsultWriter model) {
		InsultWriterSoap soapModel = new InsultWriterSoap();

		soapModel.setInsultWriterId(model.getInsultWriterId());
		soapModel.setInsult(model.getInsult());
		soapModel.setRating(model.getRating());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static InsultWriterSoap[] toSoapModels(InsultWriter[] models) {
		InsultWriterSoap[] soapModels = new InsultWriterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InsultWriterSoap[][] toSoapModels(InsultWriter[][] models) {
		InsultWriterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InsultWriterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InsultWriterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InsultWriterSoap[] toSoapModels(List<InsultWriter> models) {
		List<InsultWriterSoap> soapModels = new ArrayList<InsultWriterSoap>(models.size());

		for (InsultWriter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InsultWriterSoap[soapModels.size()]);
	}

	public InsultWriterSoap() {
	}

	public long getPrimaryKey() {
		return _insultWriterId;
	}

	public void setPrimaryKey(long pk) {
		setInsultWriterId(pk);
	}

	public long getInsultWriterId() {
		return _insultWriterId;
	}

	public void setInsultWriterId(long insultWriterId) {
		_insultWriterId = insultWriterId;
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

	private long _insultWriterId;
	private String _insult;
	private String _rating;
	private long _groupId;
	private long _companyId;
	private long _userId;
}