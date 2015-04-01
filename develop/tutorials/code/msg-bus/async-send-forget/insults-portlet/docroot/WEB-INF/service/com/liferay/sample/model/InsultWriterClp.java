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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.sample.service.ClpSerializer;
import com.liferay.sample.service.InsultWriterLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nickgaskill
 */
public class InsultWriterClp extends BaseModelImpl<InsultWriter>
	implements InsultWriter {
	public InsultWriterClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return InsultWriter.class;
	}

	@Override
	public String getModelClassName() {
		return InsultWriter.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _insultWriterId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setInsultWriterId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _insultWriterId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("insultWriterId", getInsultWriterId());
		attributes.put("insult", getInsult());
		attributes.put("rating", getRating());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long insultWriterId = (Long)attributes.get("insultWriterId");

		if (insultWriterId != null) {
			setInsultWriterId(insultWriterId);
		}

		String insult = (String)attributes.get("insult");

		if (insult != null) {
			setInsult(insult);
		}

		String rating = (String)attributes.get("rating");

		if (rating != null) {
			setRating(rating);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public long getInsultWriterId() {
		return _insultWriterId;
	}

	@Override
	public void setInsultWriterId(long insultWriterId) {
		_insultWriterId = insultWriterId;

		if (_insultWriterRemoteModel != null) {
			try {
				Class<?> clazz = _insultWriterRemoteModel.getClass();

				Method method = clazz.getMethod("setInsultWriterId", long.class);

				method.invoke(_insultWriterRemoteModel, insultWriterId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getInsult() {
		return _insult;
	}

	@Override
	public void setInsult(String insult) {
		_insult = insult;

		if (_insultWriterRemoteModel != null) {
			try {
				Class<?> clazz = _insultWriterRemoteModel.getClass();

				Method method = clazz.getMethod("setInsult", String.class);

				method.invoke(_insultWriterRemoteModel, insult);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getRating() {
		return _rating;
	}

	@Override
	public void setRating(String rating) {
		_rating = rating;

		if (_insultWriterRemoteModel != null) {
			try {
				Class<?> clazz = _insultWriterRemoteModel.getClass();

				Method method = clazz.getMethod("setRating", String.class);

				method.invoke(_insultWriterRemoteModel, rating);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_insultWriterRemoteModel != null) {
			try {
				Class<?> clazz = _insultWriterRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_insultWriterRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_insultWriterRemoteModel != null) {
			try {
				Class<?> clazz = _insultWriterRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_insultWriterRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_insultWriterRemoteModel != null) {
			try {
				Class<?> clazz = _insultWriterRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_insultWriterRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public BaseModel<?> getInsultWriterRemoteModel() {
		return _insultWriterRemoteModel;
	}

	public void setInsultWriterRemoteModel(BaseModel<?> insultWriterRemoteModel) {
		_insultWriterRemoteModel = insultWriterRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _insultWriterRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_insultWriterRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			InsultWriterLocalServiceUtil.addInsultWriter(this);
		}
		else {
			InsultWriterLocalServiceUtil.updateInsultWriter(this);
		}
	}

	@Override
	public InsultWriter toEscapedModel() {
		return (InsultWriter)ProxyUtil.newProxyInstance(InsultWriter.class.getClassLoader(),
			new Class[] { InsultWriter.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		InsultWriterClp clone = new InsultWriterClp();

		clone.setInsultWriterId(getInsultWriterId());
		clone.setInsult(getInsult());
		clone.setRating(getRating());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());

		return clone;
	}

	@Override
	public int compareTo(InsultWriter insultWriter) {
		long primaryKey = insultWriter.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InsultWriterClp)) {
			return false;
		}

		InsultWriterClp insultWriter = (InsultWriterClp)obj;

		long primaryKey = insultWriter.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{insultWriterId=");
		sb.append(getInsultWriterId());
		sb.append(", insult=");
		sb.append(getInsult());
		sb.append(", rating=");
		sb.append(getRating());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.sample.model.InsultWriter");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>insultWriterId</column-name><column-value><![CDATA[");
		sb.append(getInsultWriterId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>insult</column-name><column-value><![CDATA[");
		sb.append(getInsult());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rating</column-name><column-value><![CDATA[");
		sb.append(getRating());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _insultWriterId;
	private String _insult;
	private String _rating;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private BaseModel<?> _insultWriterRemoteModel;
}