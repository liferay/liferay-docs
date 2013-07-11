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

package com.nosester.portlet.eventlisting.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Event}.
 * </p>
 *
 * @author    Joe Bloggs
 * @see       Event
 * @generated
 */
public class EventWrapper implements Event, ModelWrapper<Event> {
	public EventWrapper(Event event) {
		_event = event;
	}

	public Class<?> getModelClass() {
		return Event.class;
	}

	public String getModelClassName() {
		return Event.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventId", getEventId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("date", getDate());
		attributes.put("locationId", getLocationId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		Long locationId = (Long)attributes.get("locationId");

		if (locationId != null) {
			setLocationId(locationId);
		}
	}

	/**
	* Returns the primary key of this event.
	*
	* @return the primary key of this event
	*/
	public long getPrimaryKey() {
		return _event.getPrimaryKey();
	}

	/**
	* Sets the primary key of this event.
	*
	* @param primaryKey the primary key of this event
	*/
	public void setPrimaryKey(long primaryKey) {
		_event.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the event ID of this event.
	*
	* @return the event ID of this event
	*/
	public long getEventId() {
		return _event.getEventId();
	}

	/**
	* Sets the event ID of this event.
	*
	* @param eventId the event ID of this event
	*/
	public void setEventId(long eventId) {
		_event.setEventId(eventId);
	}

	/**
	* Returns the company ID of this event.
	*
	* @return the company ID of this event
	*/
	public long getCompanyId() {
		return _event.getCompanyId();
	}

	/**
	* Sets the company ID of this event.
	*
	* @param companyId the company ID of this event
	*/
	public void setCompanyId(long companyId) {
		_event.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this event.
	*
	* @return the group ID of this event
	*/
	public long getGroupId() {
		return _event.getGroupId();
	}

	/**
	* Sets the group ID of this event.
	*
	* @param groupId the group ID of this event
	*/
	public void setGroupId(long groupId) {
		_event.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this event.
	*
	* @return the user ID of this event
	*/
	public long getUserId() {
		return _event.getUserId();
	}

	/**
	* Sets the user ID of this event.
	*
	* @param userId the user ID of this event
	*/
	public void setUserId(long userId) {
		_event.setUserId(userId);
	}

	/**
	* Returns the user uuid of this event.
	*
	* @return the user uuid of this event
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _event.getUserUuid();
	}

	/**
	* Sets the user uuid of this event.
	*
	* @param userUuid the user uuid of this event
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_event.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this event.
	*
	* @return the create date of this event
	*/
	public java.util.Date getCreateDate() {
		return _event.getCreateDate();
	}

	/**
	* Sets the create date of this event.
	*
	* @param createDate the create date of this event
	*/
	public void setCreateDate(java.util.Date createDate) {
		_event.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this event.
	*
	* @return the modified date of this event
	*/
	public java.util.Date getModifiedDate() {
		return _event.getModifiedDate();
	}

	/**
	* Sets the modified date of this event.
	*
	* @param modifiedDate the modified date of this event
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_event.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this event.
	*
	* @return the name of this event
	*/
	public java.lang.String getName() {
		return _event.getName();
	}

	/**
	* Sets the name of this event.
	*
	* @param name the name of this event
	*/
	public void setName(java.lang.String name) {
		_event.setName(name);
	}

	/**
	* Returns the description of this event.
	*
	* @return the description of this event
	*/
	public java.lang.String getDescription() {
		return _event.getDescription();
	}

	/**
	* Sets the description of this event.
	*
	* @param description the description of this event
	*/
	public void setDescription(java.lang.String description) {
		_event.setDescription(description);
	}

	/**
	* Returns the date of this event.
	*
	* @return the date of this event
	*/
	public java.util.Date getDate() {
		return _event.getDate();
	}

	/**
	* Sets the date of this event.
	*
	* @param date the date of this event
	*/
	public void setDate(java.util.Date date) {
		_event.setDate(date);
	}

	/**
	* Returns the location ID of this event.
	*
	* @return the location ID of this event
	*/
	public long getLocationId() {
		return _event.getLocationId();
	}

	/**
	* Sets the location ID of this event.
	*
	* @param locationId the location ID of this event
	*/
	public void setLocationId(long locationId) {
		_event.setLocationId(locationId);
	}

	public boolean isNew() {
		return _event.isNew();
	}

	public void setNew(boolean n) {
		_event.setNew(n);
	}

	public boolean isCachedModel() {
		return _event.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_event.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _event.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _event.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_event.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _event.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_event.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new EventWrapper((Event)_event.clone());
	}

	public int compareTo(com.nosester.portlet.eventlisting.model.Event event) {
		return _event.compareTo(event);
	}

	@Override
	public int hashCode() {
		return _event.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.nosester.portlet.eventlisting.model.Event> toCacheModel() {
		return _event.toCacheModel();
	}

	public com.nosester.portlet.eventlisting.model.Event toEscapedModel() {
		return new EventWrapper(_event.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _event.toString();
	}

	public java.lang.String toXmlString() {
		return _event.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_event.persist();
	}

	public com.nosester.portlet.eventlisting.model.Location getLocation() {
		return _event.getLocation();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Event getWrappedEvent() {
		return _event;
	}

	public Event getWrappedModel() {
		return _event;
	}

	public void resetOriginalValues() {
		_event.resetOriginalValues();
	}

	private Event _event;
}