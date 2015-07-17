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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Setup}.
 * </p>
 *
 * @author nickgaskill
 * @see Setup
 * @generated
 */
public class SetupWrapper implements Setup, ModelWrapper<Setup> {
	public SetupWrapper(Setup setup) {
		_setup = setup;
	}

	@Override
	public Class<?> getModelClass() {
		return Setup.class;
	}

	@Override
	public String getModelClassName() {
		return Setup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("setupId", getSetupId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("status", getStatus());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long setupId = (Long)attributes.get("setupId");

		if (setupId != null) {
			setSetupId(setupId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
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

	/**
	* Returns the primary key of this setup.
	*
	* @return the primary key of this setup
	*/
	@Override
	public long getPrimaryKey() {
		return _setup.getPrimaryKey();
	}

	/**
	* Sets the primary key of this setup.
	*
	* @param primaryKey the primary key of this setup
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_setup.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the setup ID of this setup.
	*
	* @return the setup ID of this setup
	*/
	@Override
	public long getSetupId() {
		return _setup.getSetupId();
	}

	/**
	* Sets the setup ID of this setup.
	*
	* @param setupId the setup ID of this setup
	*/
	@Override
	public void setSetupId(long setupId) {
		_setup.setSetupId(setupId);
	}

	/**
	* Returns the name of this setup.
	*
	* @return the name of this setup
	*/
	@Override
	public java.lang.String getName() {
		return _setup.getName();
	}

	/**
	* Sets the name of this setup.
	*
	* @param name the name of this setup
	*/
	@Override
	public void setName(java.lang.String name) {
		_setup.setName(name);
	}

	/**
	* Returns the description of this setup.
	*
	* @return the description of this setup
	*/
	@Override
	public java.lang.String getDescription() {
		return _setup.getDescription();
	}

	/**
	* Sets the description of this setup.
	*
	* @param description the description of this setup
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_setup.setDescription(description);
	}

	/**
	* Returns the status of this setup.
	*
	* @return the status of this setup
	*/
	@Override
	public java.lang.String getStatus() {
		return _setup.getStatus();
	}

	/**
	* Sets the status of this setup.
	*
	* @param status the status of this setup
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_setup.setStatus(status);
	}

	/**
	* Returns the group ID of this setup.
	*
	* @return the group ID of this setup
	*/
	@Override
	public long getGroupId() {
		return _setup.getGroupId();
	}

	/**
	* Sets the group ID of this setup.
	*
	* @param groupId the group ID of this setup
	*/
	@Override
	public void setGroupId(long groupId) {
		_setup.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this setup.
	*
	* @return the company ID of this setup
	*/
	@Override
	public long getCompanyId() {
		return _setup.getCompanyId();
	}

	/**
	* Sets the company ID of this setup.
	*
	* @param companyId the company ID of this setup
	*/
	@Override
	public void setCompanyId(long companyId) {
		_setup.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this setup.
	*
	* @return the user ID of this setup
	*/
	@Override
	public long getUserId() {
		return _setup.getUserId();
	}

	/**
	* Sets the user ID of this setup.
	*
	* @param userId the user ID of this setup
	*/
	@Override
	public void setUserId(long userId) {
		_setup.setUserId(userId);
	}

	/**
	* Returns the user uuid of this setup.
	*
	* @return the user uuid of this setup
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _setup.getUserUuid();
	}

	/**
	* Sets the user uuid of this setup.
	*
	* @param userUuid the user uuid of this setup
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_setup.setUserUuid(userUuid);
	}

	@Override
	public boolean isNew() {
		return _setup.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_setup.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _setup.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_setup.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _setup.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _setup.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_setup.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _setup.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_setup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_setup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_setup.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SetupWrapper((Setup)_setup.clone());
	}

	@Override
	public int compareTo(com.tour.portlet.tasks.model.Setup setup) {
		return _setup.compareTo(setup);
	}

	@Override
	public int hashCode() {
		return _setup.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.tour.portlet.tasks.model.Setup> toCacheModel() {
		return _setup.toCacheModel();
	}

	@Override
	public com.tour.portlet.tasks.model.Setup toEscapedModel() {
		return new SetupWrapper(_setup.toEscapedModel());
	}

	@Override
	public com.tour.portlet.tasks.model.Setup toUnescapedModel() {
		return new SetupWrapper(_setup.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _setup.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _setup.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_setup.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SetupWrapper)) {
			return false;
		}

		SetupWrapper setupWrapper = (SetupWrapper)obj;

		if (Validator.equals(_setup, setupWrapper._setup)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Setup getWrappedSetup() {
		return _setup;
	}

	@Override
	public Setup getWrappedModel() {
		return _setup;
	}

	@Override
	public void resetOriginalValues() {
		_setup.resetOriginalValues();
	}

	private Setup _setup;
}