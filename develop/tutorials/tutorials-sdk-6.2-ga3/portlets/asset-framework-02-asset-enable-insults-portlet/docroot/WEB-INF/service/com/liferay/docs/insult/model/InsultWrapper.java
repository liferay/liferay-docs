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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Insult}.
 * </p>
 *
 * @author joebloggs
 * @see Insult
 * @generated
 */
public class InsultWrapper implements Insult, ModelWrapper<Insult> {
	public InsultWrapper(Insult insult) {
		_insult = insult;
	}

	@Override
	public Class<?> getModelClass() {
		return Insult.class;
	}

	@Override
	public String getModelClassName() {
		return Insult.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("insultId", getInsultId());
		attributes.put("insultString", getInsultString());
		attributes.put("userId", getUserId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long insultId = (Long)attributes.get("insultId");

		if (insultId != null) {
			setInsultId(insultId);
		}

		String insultString = (String)attributes.get("insultString");

		if (insultString != null) {
			setInsultString(insultString);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	/**
	* Returns the primary key of this insult.
	*
	* @return the primary key of this insult
	*/
	@Override
	public long getPrimaryKey() {
		return _insult.getPrimaryKey();
	}

	/**
	* Sets the primary key of this insult.
	*
	* @param primaryKey the primary key of this insult
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_insult.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this insult.
	*
	* @return the uuid of this insult
	*/
	@Override
	public java.lang.String getUuid() {
		return _insult.getUuid();
	}

	/**
	* Sets the uuid of this insult.
	*
	* @param uuid the uuid of this insult
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_insult.setUuid(uuid);
	}

	/**
	* Returns the insult ID of this insult.
	*
	* @return the insult ID of this insult
	*/
	@Override
	public long getInsultId() {
		return _insult.getInsultId();
	}

	/**
	* Sets the insult ID of this insult.
	*
	* @param insultId the insult ID of this insult
	*/
	@Override
	public void setInsultId(long insultId) {
		_insult.setInsultId(insultId);
	}

	/**
	* Returns the insult string of this insult.
	*
	* @return the insult string of this insult
	*/
	@Override
	public java.lang.String getInsultString() {
		return _insult.getInsultString();
	}

	/**
	* Sets the insult string of this insult.
	*
	* @param insultString the insult string of this insult
	*/
	@Override
	public void setInsultString(java.lang.String insultString) {
		_insult.setInsultString(insultString);
	}

	/**
	* Returns the user ID of this insult.
	*
	* @return the user ID of this insult
	*/
	@Override
	public long getUserId() {
		return _insult.getUserId();
	}

	/**
	* Sets the user ID of this insult.
	*
	* @param userId the user ID of this insult
	*/
	@Override
	public void setUserId(long userId) {
		_insult.setUserId(userId);
	}

	/**
	* Returns the user uuid of this insult.
	*
	* @return the user uuid of this insult
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insult.getUserUuid();
	}

	/**
	* Sets the user uuid of this insult.
	*
	* @param userUuid the user uuid of this insult
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_insult.setUserUuid(userUuid);
	}

	/**
	* Returns the group ID of this insult.
	*
	* @return the group ID of this insult
	*/
	@Override
	public long getGroupId() {
		return _insult.getGroupId();
	}

	/**
	* Sets the group ID of this insult.
	*
	* @param groupId the group ID of this insult
	*/
	@Override
	public void setGroupId(long groupId) {
		_insult.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this insult.
	*
	* @return the company ID of this insult
	*/
	@Override
	public long getCompanyId() {
		return _insult.getCompanyId();
	}

	/**
	* Sets the company ID of this insult.
	*
	* @param companyId the company ID of this insult
	*/
	@Override
	public void setCompanyId(long companyId) {
		_insult.setCompanyId(companyId);
	}

	/**
	* Returns the user name of this insult.
	*
	* @return the user name of this insult
	*/
	@Override
	public java.lang.String getUserName() {
		return _insult.getUserName();
	}

	/**
	* Sets the user name of this insult.
	*
	* @param userName the user name of this insult
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_insult.setUserName(userName);
	}

	/**
	* Returns the create date of this insult.
	*
	* @return the create date of this insult
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _insult.getCreateDate();
	}

	/**
	* Sets the create date of this insult.
	*
	* @param createDate the create date of this insult
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_insult.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this insult.
	*
	* @return the modified date of this insult
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _insult.getModifiedDate();
	}

	/**
	* Sets the modified date of this insult.
	*
	* @param modifiedDate the modified date of this insult
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_insult.setModifiedDate(modifiedDate);
	}

	@Override
	public boolean isNew() {
		return _insult.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_insult.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _insult.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_insult.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _insult.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _insult.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_insult.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _insult.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_insult.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_insult.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_insult.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new InsultWrapper((Insult)_insult.clone());
	}

	@Override
	public int compareTo(com.liferay.docs.insult.model.Insult insult) {
		return _insult.compareTo(insult);
	}

	@Override
	public int hashCode() {
		return _insult.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.docs.insult.model.Insult> toCacheModel() {
		return _insult.toCacheModel();
	}

	@Override
	public com.liferay.docs.insult.model.Insult toEscapedModel() {
		return new InsultWrapper(_insult.toEscapedModel());
	}

	@Override
	public com.liferay.docs.insult.model.Insult toUnescapedModel() {
		return new InsultWrapper(_insult.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _insult.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _insult.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_insult.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InsultWrapper)) {
			return false;
		}

		InsultWrapper insultWrapper = (InsultWrapper)obj;

		if (Validator.equals(_insult, insultWrapper._insult)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _insult.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Insult getWrappedInsult() {
		return _insult;
	}

	@Override
	public Insult getWrappedModel() {
		return _insult;
	}

	@Override
	public void resetOriginalValues() {
		_insult.resetOriginalValues();
	}

	private Insult _insult;
}