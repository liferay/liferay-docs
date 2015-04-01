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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Insulted}.
 * </p>
 *
 * @author nickgaskill
 * @see Insulted
 * @generated
 */
public class InsultedWrapper implements Insulted, ModelWrapper<Insulted> {
	public InsultedWrapper(Insulted insulted) {
		_insulted = insulted;
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

	/**
	* Returns the primary key of this insulted.
	*
	* @return the primary key of this insulted
	*/
	@Override
	public long getPrimaryKey() {
		return _insulted.getPrimaryKey();
	}

	/**
	* Sets the primary key of this insulted.
	*
	* @param primaryKey the primary key of this insulted
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_insulted.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the insulted ID of this insulted.
	*
	* @return the insulted ID of this insulted
	*/
	@Override
	public long getInsultedId() {
		return _insulted.getInsultedId();
	}

	/**
	* Sets the insulted ID of this insulted.
	*
	* @param insultedId the insulted ID of this insulted
	*/
	@Override
	public void setInsultedId(long insultedId) {
		_insulted.setInsultedId(insultedId);
	}

	/**
	* Returns the insult of this insulted.
	*
	* @return the insult of this insulted
	*/
	@Override
	public java.lang.String getInsult() {
		return _insulted.getInsult();
	}

	/**
	* Sets the insult of this insulted.
	*
	* @param insult the insult of this insulted
	*/
	@Override
	public void setInsult(java.lang.String insult) {
		_insulted.setInsult(insult);
	}

	/**
	* Returns the group ID of this insulted.
	*
	* @return the group ID of this insulted
	*/
	@Override
	public long getGroupId() {
		return _insulted.getGroupId();
	}

	/**
	* Sets the group ID of this insulted.
	*
	* @param groupId the group ID of this insulted
	*/
	@Override
	public void setGroupId(long groupId) {
		_insulted.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this insulted.
	*
	* @return the company ID of this insulted
	*/
	@Override
	public long getCompanyId() {
		return _insulted.getCompanyId();
	}

	/**
	* Sets the company ID of this insulted.
	*
	* @param companyId the company ID of this insulted
	*/
	@Override
	public void setCompanyId(long companyId) {
		_insulted.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this insulted.
	*
	* @return the user ID of this insulted
	*/
	@Override
	public long getUserId() {
		return _insulted.getUserId();
	}

	/**
	* Sets the user ID of this insulted.
	*
	* @param userId the user ID of this insulted
	*/
	@Override
	public void setUserId(long userId) {
		_insulted.setUserId(userId);
	}

	/**
	* Returns the user uuid of this insulted.
	*
	* @return the user uuid of this insulted
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insulted.getUserUuid();
	}

	/**
	* Sets the user uuid of this insulted.
	*
	* @param userUuid the user uuid of this insulted
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_insulted.setUserUuid(userUuid);
	}

	@Override
	public boolean isNew() {
		return _insulted.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_insulted.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _insulted.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_insulted.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _insulted.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _insulted.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_insulted.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _insulted.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_insulted.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_insulted.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_insulted.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new InsultedWrapper((Insulted)_insulted.clone());
	}

	@Override
	public int compareTo(com.liferay.sample.model.Insulted insulted) {
		return _insulted.compareTo(insulted);
	}

	@Override
	public int hashCode() {
		return _insulted.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.sample.model.Insulted> toCacheModel() {
		return _insulted.toCacheModel();
	}

	@Override
	public com.liferay.sample.model.Insulted toEscapedModel() {
		return new InsultedWrapper(_insulted.toEscapedModel());
	}

	@Override
	public com.liferay.sample.model.Insulted toUnescapedModel() {
		return new InsultedWrapper(_insulted.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _insulted.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _insulted.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_insulted.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InsultedWrapper)) {
			return false;
		}

		InsultedWrapper insultedWrapper = (InsultedWrapper)obj;

		if (Validator.equals(_insulted, insultedWrapper._insulted)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Insulted getWrappedInsulted() {
		return _insulted;
	}

	@Override
	public Insulted getWrappedModel() {
		return _insulted;
	}

	@Override
	public void resetOriginalValues() {
		_insulted.resetOriginalValues();
	}

	private Insulted _insulted;
}