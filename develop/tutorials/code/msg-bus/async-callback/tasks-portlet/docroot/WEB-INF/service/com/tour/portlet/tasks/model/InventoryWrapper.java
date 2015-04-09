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
 * This class is a wrapper for {@link Inventory}.
 * </p>
 *
 * @author nickgaskill
 * @see Inventory
 * @generated
 */
public class InventoryWrapper implements Inventory, ModelWrapper<Inventory> {
	public InventoryWrapper(Inventory inventory) {
		_inventory = inventory;
	}

	@Override
	public Class<?> getModelClass() {
		return Inventory.class;
	}

	@Override
	public String getModelClassName() {
		return Inventory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("inventoryId", getInventoryId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long inventoryId = (Long)attributes.get("inventoryId");

		if (inventoryId != null) {
			setInventoryId(inventoryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
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
	* Returns the primary key of this inventory.
	*
	* @return the primary key of this inventory
	*/
	@Override
	public long getPrimaryKey() {
		return _inventory.getPrimaryKey();
	}

	/**
	* Sets the primary key of this inventory.
	*
	* @param primaryKey the primary key of this inventory
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_inventory.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the inventory ID of this inventory.
	*
	* @return the inventory ID of this inventory
	*/
	@Override
	public long getInventoryId() {
		return _inventory.getInventoryId();
	}

	/**
	* Sets the inventory ID of this inventory.
	*
	* @param inventoryId the inventory ID of this inventory
	*/
	@Override
	public void setInventoryId(long inventoryId) {
		_inventory.setInventoryId(inventoryId);
	}

	/**
	* Returns the name of this inventory.
	*
	* @return the name of this inventory
	*/
	@Override
	public java.lang.String getName() {
		return _inventory.getName();
	}

	/**
	* Sets the name of this inventory.
	*
	* @param name the name of this inventory
	*/
	@Override
	public void setName(java.lang.String name) {
		_inventory.setName(name);
	}

	/**
	* Returns the description of this inventory.
	*
	* @return the description of this inventory
	*/
	@Override
	public java.lang.String getDescription() {
		return _inventory.getDescription();
	}

	/**
	* Sets the description of this inventory.
	*
	* @param description the description of this inventory
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_inventory.setDescription(description);
	}

	/**
	* Returns the group ID of this inventory.
	*
	* @return the group ID of this inventory
	*/
	@Override
	public long getGroupId() {
		return _inventory.getGroupId();
	}

	/**
	* Sets the group ID of this inventory.
	*
	* @param groupId the group ID of this inventory
	*/
	@Override
	public void setGroupId(long groupId) {
		_inventory.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this inventory.
	*
	* @return the company ID of this inventory
	*/
	@Override
	public long getCompanyId() {
		return _inventory.getCompanyId();
	}

	/**
	* Sets the company ID of this inventory.
	*
	* @param companyId the company ID of this inventory
	*/
	@Override
	public void setCompanyId(long companyId) {
		_inventory.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this inventory.
	*
	* @return the user ID of this inventory
	*/
	@Override
	public long getUserId() {
		return _inventory.getUserId();
	}

	/**
	* Sets the user ID of this inventory.
	*
	* @param userId the user ID of this inventory
	*/
	@Override
	public void setUserId(long userId) {
		_inventory.setUserId(userId);
	}

	/**
	* Returns the user uuid of this inventory.
	*
	* @return the user uuid of this inventory
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventory.getUserUuid();
	}

	/**
	* Sets the user uuid of this inventory.
	*
	* @param userUuid the user uuid of this inventory
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_inventory.setUserUuid(userUuid);
	}

	@Override
	public boolean isNew() {
		return _inventory.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_inventory.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _inventory.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_inventory.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _inventory.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _inventory.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_inventory.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _inventory.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_inventory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_inventory.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_inventory.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new InventoryWrapper((Inventory)_inventory.clone());
	}

	@Override
	public int compareTo(com.tour.portlet.tasks.model.Inventory inventory) {
		return _inventory.compareTo(inventory);
	}

	@Override
	public int hashCode() {
		return _inventory.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.tour.portlet.tasks.model.Inventory> toCacheModel() {
		return _inventory.toCacheModel();
	}

	@Override
	public com.tour.portlet.tasks.model.Inventory toEscapedModel() {
		return new InventoryWrapper(_inventory.toEscapedModel());
	}

	@Override
	public com.tour.portlet.tasks.model.Inventory toUnescapedModel() {
		return new InventoryWrapper(_inventory.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _inventory.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _inventory.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_inventory.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InventoryWrapper)) {
			return false;
		}

		InventoryWrapper inventoryWrapper = (InventoryWrapper)obj;

		if (Validator.equals(_inventory, inventoryWrapper._inventory)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Inventory getWrappedInventory() {
		return _inventory;
	}

	@Override
	public Inventory getWrappedModel() {
		return _inventory;
	}

	@Override
	public void resetOriginalValues() {
		_inventory.resetOriginalValues();
	}

	private Inventory _inventory;
}