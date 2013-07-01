/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Location}.
 * </p>
 *
 * @author    jbloggs
 * @see       Location
 * @generated
 */
public class LocationWrapper implements Location, ModelWrapper<Location> {
	public LocationWrapper(Location location) {
		_location = location;
	}

	public Class<?> getModelClass() {
		return Location.class;
	}

	public String getModelClassName() {
		return Location.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("locationId", getLocationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("streetAddress", getStreetAddress());
		attributes.put("city", getCity());
		attributes.put("stateOrProvince", getStateOrProvince());
		attributes.put("country", getCountry());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long locationId = (Long)attributes.get("locationId");

		if (locationId != null) {
			setLocationId(locationId);
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

		Long createDate = (Long)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long modifiedDate = (Long)attributes.get("modifiedDate");

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

		String streetAddress = (String)attributes.get("streetAddress");

		if (streetAddress != null) {
			setStreetAddress(streetAddress);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		String stateOrProvince = (String)attributes.get("stateOrProvince");

		if (stateOrProvince != null) {
			setStateOrProvince(stateOrProvince);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}
	}

	/**
	* Returns the primary key of this location.
	*
	* @return the primary key of this location
	*/
	public long getPrimaryKey() {
		return _location.getPrimaryKey();
	}

	/**
	* Sets the primary key of this location.
	*
	* @param primaryKey the primary key of this location
	*/
	public void setPrimaryKey(long primaryKey) {
		_location.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the location ID of this location.
	*
	* @return the location ID of this location
	*/
	public long getLocationId() {
		return _location.getLocationId();
	}

	/**
	* Sets the location ID of this location.
	*
	* @param locationId the location ID of this location
	*/
	public void setLocationId(long locationId) {
		_location.setLocationId(locationId);
	}

	/**
	* Returns the company ID of this location.
	*
	* @return the company ID of this location
	*/
	public long getCompanyId() {
		return _location.getCompanyId();
	}

	/**
	* Sets the company ID of this location.
	*
	* @param companyId the company ID of this location
	*/
	public void setCompanyId(long companyId) {
		_location.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this location.
	*
	* @return the group ID of this location
	*/
	public long getGroupId() {
		return _location.getGroupId();
	}

	/**
	* Sets the group ID of this location.
	*
	* @param groupId the group ID of this location
	*/
	public void setGroupId(long groupId) {
		_location.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this location.
	*
	* @return the user ID of this location
	*/
	public long getUserId() {
		return _location.getUserId();
	}

	/**
	* Sets the user ID of this location.
	*
	* @param userId the user ID of this location
	*/
	public void setUserId(long userId) {
		_location.setUserId(userId);
	}

	/**
	* Returns the user uuid of this location.
	*
	* @return the user uuid of this location
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _location.getUserUuid();
	}

	/**
	* Sets the user uuid of this location.
	*
	* @param userUuid the user uuid of this location
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_location.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this location.
	*
	* @return the create date of this location
	*/
	public long getCreateDate() {
		return _location.getCreateDate();
	}

	/**
	* Sets the create date of this location.
	*
	* @param createDate the create date of this location
	*/
	public void setCreateDate(long createDate) {
		_location.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this location.
	*
	* @return the modified date of this location
	*/
	public long getModifiedDate() {
		return _location.getModifiedDate();
	}

	/**
	* Sets the modified date of this location.
	*
	* @param modifiedDate the modified date of this location
	*/
	public void setModifiedDate(long modifiedDate) {
		_location.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this location.
	*
	* @return the name of this location
	*/
	public java.lang.String getName() {
		return _location.getName();
	}

	/**
	* Sets the name of this location.
	*
	* @param name the name of this location
	*/
	public void setName(java.lang.String name) {
		_location.setName(name);
	}

	/**
	* Returns the description of this location.
	*
	* @return the description of this location
	*/
	public java.lang.String getDescription() {
		return _location.getDescription();
	}

	/**
	* Sets the description of this location.
	*
	* @param description the description of this location
	*/
	public void setDescription(java.lang.String description) {
		_location.setDescription(description);
	}

	/**
	* Returns the street address of this location.
	*
	* @return the street address of this location
	*/
	public java.lang.String getStreetAddress() {
		return _location.getStreetAddress();
	}

	/**
	* Sets the street address of this location.
	*
	* @param streetAddress the street address of this location
	*/
	public void setStreetAddress(java.lang.String streetAddress) {
		_location.setStreetAddress(streetAddress);
	}

	/**
	* Returns the city of this location.
	*
	* @return the city of this location
	*/
	public java.lang.String getCity() {
		return _location.getCity();
	}

	/**
	* Sets the city of this location.
	*
	* @param city the city of this location
	*/
	public void setCity(java.lang.String city) {
		_location.setCity(city);
	}

	/**
	* Returns the state or province of this location.
	*
	* @return the state or province of this location
	*/
	public java.lang.String getStateOrProvince() {
		return _location.getStateOrProvince();
	}

	/**
	* Sets the state or province of this location.
	*
	* @param stateOrProvince the state or province of this location
	*/
	public void setStateOrProvince(java.lang.String stateOrProvince) {
		_location.setStateOrProvince(stateOrProvince);
	}

	/**
	* Returns the country of this location.
	*
	* @return the country of this location
	*/
	public java.lang.String getCountry() {
		return _location.getCountry();
	}

	/**
	* Sets the country of this location.
	*
	* @param country the country of this location
	*/
	public void setCountry(java.lang.String country) {
		_location.setCountry(country);
	}

	public boolean isNew() {
		return _location.isNew();
	}

	public void setNew(boolean n) {
		_location.setNew(n);
	}

	public boolean isCachedModel() {
		return _location.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_location.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _location.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _location.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_location.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _location.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_location.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new LocationWrapper((Location)_location.clone());
	}

	public int compareTo(
		com.nosester.portlet.eventlisting.model.Location location) {
		return _location.compareTo(location);
	}

	@Override
	public int hashCode() {
		return _location.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.nosester.portlet.eventlisting.model.Location> toCacheModel() {
		return _location.toCacheModel();
	}

	public com.nosester.portlet.eventlisting.model.Location toEscapedModel() {
		return new LocationWrapper(_location.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _location.toString();
	}

	public java.lang.String toXmlString() {
		return _location.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_location.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Location getWrappedLocation() {
		return _location;
	}

	public Location getWrappedModel() {
		return _location;
	}

	public void resetOriginalValues() {
		_location.resetOriginalValues();
	}

	private Location _location;
}