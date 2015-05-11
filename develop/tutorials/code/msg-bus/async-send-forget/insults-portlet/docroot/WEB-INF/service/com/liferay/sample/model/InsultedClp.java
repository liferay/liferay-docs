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
import com.liferay.sample.service.InsultedLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nickgaskill
 */
public class InsultedClp extends BaseModelImpl<Insulted> implements Insulted {
	public InsultedClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Insulted.class;
	}

	@Override
	public String getModelClassName() {
		return Insulted.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _insultedId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setInsultedId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _insultedId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("insultedId", getInsultedId());
		attributes.put("insult", getInsult());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long insultedId = (Long)attributes.get("insultedId");

		if (insultedId != null) {
			setInsultedId(insultedId);
		}

		String insult = (String)attributes.get("insult");

		if (insult != null) {
			setInsult(insult);
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
	public long getInsultedId() {
		return _insultedId;
	}

	@Override
	public void setInsultedId(long insultedId) {
		_insultedId = insultedId;

		if (_insultedRemoteModel != null) {
			try {
				Class<?> clazz = _insultedRemoteModel.getClass();

				Method method = clazz.getMethod("setInsultedId", long.class);

				method.invoke(_insultedRemoteModel, insultedId);
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

		if (_insultedRemoteModel != null) {
			try {
				Class<?> clazz = _insultedRemoteModel.getClass();

				Method method = clazz.getMethod("setInsult", String.class);

				method.invoke(_insultedRemoteModel, insult);
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

		if (_insultedRemoteModel != null) {
			try {
				Class<?> clazz = _insultedRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_insultedRemoteModel, groupId);
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

		if (_insultedRemoteModel != null) {
			try {
				Class<?> clazz = _insultedRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_insultedRemoteModel, companyId);
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

		if (_insultedRemoteModel != null) {
			try {
				Class<?> clazz = _insultedRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_insultedRemoteModel, userId);
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

	public BaseModel<?> getInsultedRemoteModel() {
		return _insultedRemoteModel;
	}

	public void setInsultedRemoteModel(BaseModel<?> insultedRemoteModel) {
		_insultedRemoteModel = insultedRemoteModel;
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

		Class<?> remoteModelClass = _insultedRemoteModel.getClass();

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

		Object returnValue = method.invoke(_insultedRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			InsultedLocalServiceUtil.addInsulted(this);
		}
		else {
			InsultedLocalServiceUtil.updateInsulted(this);
		}
	}

	@Override
	public Insulted toEscapedModel() {
		return (Insulted)ProxyUtil.newProxyInstance(Insulted.class.getClassLoader(),
			new Class[] { Insulted.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		InsultedClp clone = new InsultedClp();

		clone.setInsultedId(getInsultedId());
		clone.setInsult(getInsult());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());

		return clone;
	}

	@Override
	public int compareTo(Insulted insulted) {
		long primaryKey = insulted.getPrimaryKey();

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

		if (!(obj instanceof InsultedClp)) {
			return false;
		}

		InsultedClp insulted = (InsultedClp)obj;

		long primaryKey = insulted.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{insultedId=");
		sb.append(getInsultedId());
		sb.append(", insult=");
		sb.append(getInsult());
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
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.sample.model.Insulted");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>insultedId</column-name><column-value><![CDATA[");
		sb.append(getInsultedId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>insult</column-name><column-value><![CDATA[");
		sb.append(getInsult());
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

	private long _insultedId;
	private String _insult;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private BaseModel<?> _insultedRemoteModel;
}