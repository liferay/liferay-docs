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

package com.liferay.docs.guestbook.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Guestbook}.
 * </p>
 *
 * @author liferay
 * @see Guestbook
 * @generated
 */
@ProviderType
public class GuestbookWrapper implements Guestbook, ModelWrapper<Guestbook> {
	public GuestbookWrapper(Guestbook guestbook) {
		_guestbook = guestbook;
	}

	@Override
	public Class<?> getModelClass() {
		return Guestbook.class;
	}

	@Override
	public String getModelClassName() {
		return Guestbook.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("guestbookId", getGuestbookId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long guestbookId = (Long)attributes.get("guestbookId");

		if (guestbookId != null) {
			setGuestbookId(guestbookId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Guestbook toEscapedModel() {
		return new GuestbookWrapper(_guestbook.toEscapedModel());
	}

	@Override
	public Guestbook toUnescapedModel() {
		return new GuestbookWrapper(_guestbook.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _guestbook.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _guestbook.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _guestbook.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _guestbook.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Guestbook> toCacheModel() {
		return _guestbook.toCacheModel();
	}

	@Override
	public int compareTo(Guestbook guestbook) {
		return _guestbook.compareTo(guestbook);
	}

	@Override
	public int hashCode() {
		return _guestbook.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _guestbook.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new GuestbookWrapper((Guestbook)_guestbook.clone());
	}

	/**
	* Returns the name of this guestbook.
	*
	* @return the name of this guestbook
	*/
	@Override
	public java.lang.String getName() {
		return _guestbook.getName();
	}

	/**
	* Returns the user name of this guestbook.
	*
	* @return the user name of this guestbook
	*/
	@Override
	public java.lang.String getUserName() {
		return _guestbook.getUserName();
	}

	/**
	* Returns the user uuid of this guestbook.
	*
	* @return the user uuid of this guestbook
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _guestbook.getUserUuid();
	}

	/**
	* Returns the uuid of this guestbook.
	*
	* @return the uuid of this guestbook
	*/
	@Override
	public java.lang.String getUuid() {
		return _guestbook.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _guestbook.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _guestbook.toXmlString();
	}

	/**
	* Returns the create date of this guestbook.
	*
	* @return the create date of this guestbook
	*/
	@Override
	public Date getCreateDate() {
		return _guestbook.getCreateDate();
	}

	/**
	* Returns the modified date of this guestbook.
	*
	* @return the modified date of this guestbook
	*/
	@Override
	public Date getModifiedDate() {
		return _guestbook.getModifiedDate();
	}

	/**
	* Returns the company ID of this guestbook.
	*
	* @return the company ID of this guestbook
	*/
	@Override
	public long getCompanyId() {
		return _guestbook.getCompanyId();
	}

	/**
	* Returns the group ID of this guestbook.
	*
	* @return the group ID of this guestbook
	*/
	@Override
	public long getGroupId() {
		return _guestbook.getGroupId();
	}

	/**
	* Returns the guestbook ID of this guestbook.
	*
	* @return the guestbook ID of this guestbook
	*/
	@Override
	public long getGuestbookId() {
		return _guestbook.getGuestbookId();
	}

	/**
	* Returns the primary key of this guestbook.
	*
	* @return the primary key of this guestbook
	*/
	@Override
	public long getPrimaryKey() {
		return _guestbook.getPrimaryKey();
	}

	/**
	* Returns the user ID of this guestbook.
	*
	* @return the user ID of this guestbook
	*/
	@Override
	public long getUserId() {
		return _guestbook.getUserId();
	}

	@Override
	public void persist() {
		_guestbook.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_guestbook.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this guestbook.
	*
	* @param companyId the company ID of this guestbook
	*/
	@Override
	public void setCompanyId(long companyId) {
		_guestbook.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this guestbook.
	*
	* @param createDate the create date of this guestbook
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_guestbook.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_guestbook.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_guestbook.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_guestbook.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this guestbook.
	*
	* @param groupId the group ID of this guestbook
	*/
	@Override
	public void setGroupId(long groupId) {
		_guestbook.setGroupId(groupId);
	}

	/**
	* Sets the guestbook ID of this guestbook.
	*
	* @param guestbookId the guestbook ID of this guestbook
	*/
	@Override
	public void setGuestbookId(long guestbookId) {
		_guestbook.setGuestbookId(guestbookId);
	}

	/**
	* Sets the modified date of this guestbook.
	*
	* @param modifiedDate the modified date of this guestbook
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_guestbook.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this guestbook.
	*
	* @param name the name of this guestbook
	*/
	@Override
	public void setName(java.lang.String name) {
		_guestbook.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_guestbook.setNew(n);
	}

	/**
	* Sets the primary key of this guestbook.
	*
	* @param primaryKey the primary key of this guestbook
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_guestbook.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_guestbook.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this guestbook.
	*
	* @param userId the user ID of this guestbook
	*/
	@Override
	public void setUserId(long userId) {
		_guestbook.setUserId(userId);
	}

	/**
	* Sets the user name of this guestbook.
	*
	* @param userName the user name of this guestbook
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_guestbook.setUserName(userName);
	}

	/**
	* Sets the user uuid of this guestbook.
	*
	* @param userUuid the user uuid of this guestbook
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_guestbook.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this guestbook.
	*
	* @param uuid the uuid of this guestbook
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_guestbook.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GuestbookWrapper)) {
			return false;
		}

		GuestbookWrapper guestbookWrapper = (GuestbookWrapper)obj;

		if (Objects.equals(_guestbook, guestbookWrapper._guestbook)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _guestbook.getStagedModelType();
	}

	@Override
	public Guestbook getWrappedModel() {
		return _guestbook;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _guestbook.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _guestbook.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_guestbook.resetOriginalValues();
	}

	private final Guestbook _guestbook;
}