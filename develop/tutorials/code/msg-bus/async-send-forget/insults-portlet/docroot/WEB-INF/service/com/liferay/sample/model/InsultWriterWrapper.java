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
 * This class is a wrapper for {@link InsultWriter}.
 * </p>
 *
 * @author nickgaskill
 * @see InsultWriter
 * @generated
 */
public class InsultWriterWrapper implements InsultWriter,
	ModelWrapper<InsultWriter> {
	public InsultWriterWrapper(InsultWriter insultWriter) {
		_insultWriter = insultWriter;
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

	/**
	* Returns the primary key of this insult writer.
	*
	* @return the primary key of this insult writer
	*/
	@Override
	public long getPrimaryKey() {
		return _insultWriter.getPrimaryKey();
	}

	/**
	* Sets the primary key of this insult writer.
	*
	* @param primaryKey the primary key of this insult writer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_insultWriter.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the insult writer ID of this insult writer.
	*
	* @return the insult writer ID of this insult writer
	*/
	@Override
	public long getInsultWriterId() {
		return _insultWriter.getInsultWriterId();
	}

	/**
	* Sets the insult writer ID of this insult writer.
	*
	* @param insultWriterId the insult writer ID of this insult writer
	*/
	@Override
	public void setInsultWriterId(long insultWriterId) {
		_insultWriter.setInsultWriterId(insultWriterId);
	}

	/**
	* Returns the insult of this insult writer.
	*
	* @return the insult of this insult writer
	*/
	@Override
	public java.lang.String getInsult() {
		return _insultWriter.getInsult();
	}

	/**
	* Sets the insult of this insult writer.
	*
	* @param insult the insult of this insult writer
	*/
	@Override
	public void setInsult(java.lang.String insult) {
		_insultWriter.setInsult(insult);
	}

	/**
	* Returns the rating of this insult writer.
	*
	* @return the rating of this insult writer
	*/
	@Override
	public java.lang.String getRating() {
		return _insultWriter.getRating();
	}

	/**
	* Sets the rating of this insult writer.
	*
	* @param rating the rating of this insult writer
	*/
	@Override
	public void setRating(java.lang.String rating) {
		_insultWriter.setRating(rating);
	}

	/**
	* Returns the group ID of this insult writer.
	*
	* @return the group ID of this insult writer
	*/
	@Override
	public long getGroupId() {
		return _insultWriter.getGroupId();
	}

	/**
	* Sets the group ID of this insult writer.
	*
	* @param groupId the group ID of this insult writer
	*/
	@Override
	public void setGroupId(long groupId) {
		_insultWriter.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this insult writer.
	*
	* @return the company ID of this insult writer
	*/
	@Override
	public long getCompanyId() {
		return _insultWriter.getCompanyId();
	}

	/**
	* Sets the company ID of this insult writer.
	*
	* @param companyId the company ID of this insult writer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_insultWriter.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this insult writer.
	*
	* @return the user ID of this insult writer
	*/
	@Override
	public long getUserId() {
		return _insultWriter.getUserId();
	}

	/**
	* Sets the user ID of this insult writer.
	*
	* @param userId the user ID of this insult writer
	*/
	@Override
	public void setUserId(long userId) {
		_insultWriter.setUserId(userId);
	}

	/**
	* Returns the user uuid of this insult writer.
	*
	* @return the user uuid of this insult writer
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultWriter.getUserUuid();
	}

	/**
	* Sets the user uuid of this insult writer.
	*
	* @param userUuid the user uuid of this insult writer
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_insultWriter.setUserUuid(userUuid);
	}

	@Override
	public boolean isNew() {
		return _insultWriter.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_insultWriter.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _insultWriter.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_insultWriter.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _insultWriter.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _insultWriter.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_insultWriter.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _insultWriter.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_insultWriter.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_insultWriter.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_insultWriter.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new InsultWriterWrapper((InsultWriter)_insultWriter.clone());
	}

	@Override
	public int compareTo(com.liferay.sample.model.InsultWriter insultWriter) {
		return _insultWriter.compareTo(insultWriter);
	}

	@Override
	public int hashCode() {
		return _insultWriter.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.sample.model.InsultWriter> toCacheModel() {
		return _insultWriter.toCacheModel();
	}

	@Override
	public com.liferay.sample.model.InsultWriter toEscapedModel() {
		return new InsultWriterWrapper(_insultWriter.toEscapedModel());
	}

	@Override
	public com.liferay.sample.model.InsultWriter toUnescapedModel() {
		return new InsultWriterWrapper(_insultWriter.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _insultWriter.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _insultWriter.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_insultWriter.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InsultWriterWrapper)) {
			return false;
		}

		InsultWriterWrapper insultWriterWrapper = (InsultWriterWrapper)obj;

		if (Validator.equals(_insultWriter, insultWriterWrapper._insultWriter)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public InsultWriter getWrappedInsultWriter() {
		return _insultWriter;
	}

	@Override
	public InsultWriter getWrappedModel() {
		return _insultWriter;
	}

	@Override
	public void resetOriginalValues() {
		_insultWriter.resetOriginalValues();
	}

	private InsultWriter _insultWriter;
}