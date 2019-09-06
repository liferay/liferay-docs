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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link GuestbookEntry}.
 * </p>
 *
 * @author Liferay
 * @see GuestbookEntry
 * @generated
 */
@ProviderType
public class GuestbookEntryWrapper
	extends BaseModelWrapper<GuestbookEntry>
	implements GuestbookEntry, ModelWrapper<GuestbookEntry> {

	public GuestbookEntryWrapper(GuestbookEntry guestbookEntry) {
		super(guestbookEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("entryId", getEntryId());
		attributes.put("name", getName());
		attributes.put("email", getEmail());
		attributes.put("message", getMessage());
		attributes.put("guestbookId", getGuestbookId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
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

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	/**
	 * Returns the company ID of this guestbook entry.
	 *
	 * @return the company ID of this guestbook entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this guestbook entry.
	 *
	 * @return the create date of this guestbook entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the email of this guestbook entry.
	 *
	 * @return the email of this guestbook entry
	 */
	@Override
	public String getEmail() {
		return model.getEmail();
	}

	/**
	 * Returns the entry ID of this guestbook entry.
	 *
	 * @return the entry ID of this guestbook entry
	 */
	@Override
	public long getEntryId() {
		return model.getEntryId();
	}

	/**
	 * Returns the group ID of this guestbook entry.
	 *
	 * @return the group ID of this guestbook entry
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the guestbook ID of this guestbook entry.
	 *
	 * @return the guestbook ID of this guestbook entry
	 */
	@Override
	public long getGuestbookId() {
		return model.getGuestbookId();
	}

	/**
	 * Returns the message of this guestbook entry.
	 *
	 * @return the message of this guestbook entry
	 */
	@Override
	public String getMessage() {
		return model.getMessage();
	}

	/**
	 * Returns the modified date of this guestbook entry.
	 *
	 * @return the modified date of this guestbook entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this guestbook entry.
	 *
	 * @return the name of this guestbook entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this guestbook entry.
	 *
	 * @return the primary key of this guestbook entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this guestbook entry.
	 *
	 * @return the status of this guestbook entry
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this guestbook entry.
	 *
	 * @return the status by user ID of this guestbook entry
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this guestbook entry.
	 *
	 * @return the status by user name of this guestbook entry
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this guestbook entry.
	 *
	 * @return the status by user uuid of this guestbook entry
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this guestbook entry.
	 *
	 * @return the status date of this guestbook entry
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Returns the user ID of this guestbook entry.
	 *
	 * @return the user ID of this guestbook entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this guestbook entry.
	 *
	 * @return the user name of this guestbook entry
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this guestbook entry.
	 *
	 * @return the user uuid of this guestbook entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this guestbook entry.
	 *
	 * @return the uuid of this guestbook entry
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this guestbook entry is approved.
	 *
	 * @return <code>true</code> if this guestbook entry is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this guestbook entry is denied.
	 *
	 * @return <code>true</code> if this guestbook entry is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this guestbook entry is a draft.
	 *
	 * @return <code>true</code> if this guestbook entry is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this guestbook entry is expired.
	 *
	 * @return <code>true</code> if this guestbook entry is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this guestbook entry is inactive.
	 *
	 * @return <code>true</code> if this guestbook entry is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this guestbook entry is incomplete.
	 *
	 * @return <code>true</code> if this guestbook entry is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this guestbook entry is pending.
	 *
	 * @return <code>true</code> if this guestbook entry is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this guestbook entry is scheduled.
	 *
	 * @return <code>true</code> if this guestbook entry is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this guestbook entry.
	 *
	 * @param companyId the company ID of this guestbook entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this guestbook entry.
	 *
	 * @param createDate the create date of this guestbook entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the email of this guestbook entry.
	 *
	 * @param email the email of this guestbook entry
	 */
	@Override
	public void setEmail(String email) {
		model.setEmail(email);
	}

	/**
	 * Sets the entry ID of this guestbook entry.
	 *
	 * @param entryId the entry ID of this guestbook entry
	 */
	@Override
	public void setEntryId(long entryId) {
		model.setEntryId(entryId);
	}

	/**
	 * Sets the group ID of this guestbook entry.
	 *
	 * @param groupId the group ID of this guestbook entry
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the guestbook ID of this guestbook entry.
	 *
	 * @param guestbookId the guestbook ID of this guestbook entry
	 */
	@Override
	public void setGuestbookId(long guestbookId) {
		model.setGuestbookId(guestbookId);
	}

	/**
	 * Sets the message of this guestbook entry.
	 *
	 * @param message the message of this guestbook entry
	 */
	@Override
	public void setMessage(String message) {
		model.setMessage(message);
	}

	/**
	 * Sets the modified date of this guestbook entry.
	 *
	 * @param modifiedDate the modified date of this guestbook entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this guestbook entry.
	 *
	 * @param name the name of this guestbook entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this guestbook entry.
	 *
	 * @param primaryKey the primary key of this guestbook entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this guestbook entry.
	 *
	 * @param status the status of this guestbook entry
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this guestbook entry.
	 *
	 * @param statusByUserId the status by user ID of this guestbook entry
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this guestbook entry.
	 *
	 * @param statusByUserName the status by user name of this guestbook entry
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this guestbook entry.
	 *
	 * @param statusByUserUuid the status by user uuid of this guestbook entry
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this guestbook entry.
	 *
	 * @param statusDate the status date of this guestbook entry
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the user ID of this guestbook entry.
	 *
	 * @param userId the user ID of this guestbook entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this guestbook entry.
	 *
	 * @param userName the user name of this guestbook entry
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this guestbook entry.
	 *
	 * @param userUuid the user uuid of this guestbook entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this guestbook entry.
	 *
	 * @param uuid the uuid of this guestbook entry
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected GuestbookEntryWrapper wrap(GuestbookEntry guestbookEntry) {
		return new GuestbookEntryWrapper(guestbookEntry);
	}

}