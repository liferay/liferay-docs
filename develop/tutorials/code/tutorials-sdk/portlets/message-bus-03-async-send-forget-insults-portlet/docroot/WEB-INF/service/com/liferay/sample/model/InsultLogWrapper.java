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
 * This class is a wrapper for {@link InsultLog}.
 * </p>
 *
 * @author nickgaskill
 * @see InsultLog
 * @generated
 */
public class InsultLogWrapper implements InsultLog, ModelWrapper<InsultLog> {
	public InsultLogWrapper(InsultLog insultLog) {
		_insultLog = insultLog;
	}

	@Override
	public Class<?> getModelClass() {
		return InsultLog.class;
	}

	@Override
	public String getModelClassName() {
		return InsultLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("insultLogId", getInsultLogId());
		attributes.put("insult", getInsult());
		attributes.put("rating", getRating());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long insultLogId = (Long)attributes.get("insultLogId");

		if (insultLogId != null) {
			setInsultLogId(insultLogId);
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

	/**
	* Returns the primary key of this insult log.
	*
	* @return the primary key of this insult log
	*/
	@Override
	public long getPrimaryKey() {
		return _insultLog.getPrimaryKey();
	}

	/**
	* Sets the primary key of this insult log.
	*
	* @param primaryKey the primary key of this insult log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_insultLog.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the insult log ID of this insult log.
	*
	* @return the insult log ID of this insult log
	*/
	@Override
	public long getInsultLogId() {
		return _insultLog.getInsultLogId();
	}

	/**
	* Sets the insult log ID of this insult log.
	*
	* @param insultLogId the insult log ID of this insult log
	*/
	@Override
	public void setInsultLogId(long insultLogId) {
		_insultLog.setInsultLogId(insultLogId);
	}

	/**
	* Returns the insult of this insult log.
	*
	* @return the insult of this insult log
	*/
	@Override
	public java.lang.String getInsult() {
		return _insultLog.getInsult();
	}

	/**
	* Sets the insult of this insult log.
	*
	* @param insult the insult of this insult log
	*/
	@Override
	public void setInsult(java.lang.String insult) {
		_insultLog.setInsult(insult);
	}

	/**
	* Returns the rating of this insult log.
	*
	* @return the rating of this insult log
	*/
	@Override
	public java.lang.String getRating() {
		return _insultLog.getRating();
	}

	/**
	* Sets the rating of this insult log.
	*
	* @param rating the rating of this insult log
	*/
	@Override
	public void setRating(java.lang.String rating) {
		_insultLog.setRating(rating);
	}

	/**
	* Returns the group ID of this insult log.
	*
	* @return the group ID of this insult log
	*/
	@Override
	public long getGroupId() {
		return _insultLog.getGroupId();
	}

	/**
	* Sets the group ID of this insult log.
	*
	* @param groupId the group ID of this insult log
	*/
	@Override
	public void setGroupId(long groupId) {
		_insultLog.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this insult log.
	*
	* @return the company ID of this insult log
	*/
	@Override
	public long getCompanyId() {
		return _insultLog.getCompanyId();
	}

	/**
	* Sets the company ID of this insult log.
	*
	* @param companyId the company ID of this insult log
	*/
	@Override
	public void setCompanyId(long companyId) {
		_insultLog.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this insult log.
	*
	* @return the user ID of this insult log
	*/
	@Override
	public long getUserId() {
		return _insultLog.getUserId();
	}

	/**
	* Sets the user ID of this insult log.
	*
	* @param userId the user ID of this insult log
	*/
	@Override
	public void setUserId(long userId) {
		_insultLog.setUserId(userId);
	}

	/**
	* Returns the user uuid of this insult log.
	*
	* @return the user uuid of this insult log
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultLog.getUserUuid();
	}

	/**
	* Sets the user uuid of this insult log.
	*
	* @param userUuid the user uuid of this insult log
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_insultLog.setUserUuid(userUuid);
	}

	@Override
	public boolean isNew() {
		return _insultLog.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_insultLog.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _insultLog.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_insultLog.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _insultLog.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _insultLog.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_insultLog.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _insultLog.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_insultLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_insultLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_insultLog.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new InsultLogWrapper((InsultLog)_insultLog.clone());
	}

	@Override
	public int compareTo(com.liferay.sample.model.InsultLog insultLog) {
		return _insultLog.compareTo(insultLog);
	}

	@Override
	public int hashCode() {
		return _insultLog.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.sample.model.InsultLog> toCacheModel() {
		return _insultLog.toCacheModel();
	}

	@Override
	public com.liferay.sample.model.InsultLog toEscapedModel() {
		return new InsultLogWrapper(_insultLog.toEscapedModel());
	}

	@Override
	public com.liferay.sample.model.InsultLog toUnescapedModel() {
		return new InsultLogWrapper(_insultLog.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _insultLog.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _insultLog.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_insultLog.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InsultLogWrapper)) {
			return false;
		}

		InsultLogWrapper insultLogWrapper = (InsultLogWrapper)obj;

		if (Validator.equals(_insultLog, insultLogWrapper._insultLog)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public InsultLog getWrappedInsultLog() {
		return _insultLog;
	}

	@Override
	public InsultLog getWrappedModel() {
		return _insultLog;
	}

	@Override
	public void resetOriginalValues() {
		_insultLog.resetOriginalValues();
	}

	private InsultLog _insultLog;
}