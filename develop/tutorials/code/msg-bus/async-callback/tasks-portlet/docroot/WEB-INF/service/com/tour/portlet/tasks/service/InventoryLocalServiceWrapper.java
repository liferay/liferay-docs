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

package com.tour.portlet.tasks.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InventoryLocalService}.
 *
 * @author nickgaskill
 * @see InventoryLocalService
 * @generated
 */
public class InventoryLocalServiceWrapper implements InventoryLocalService,
	ServiceWrapper<InventoryLocalService> {
	public InventoryLocalServiceWrapper(
		InventoryLocalService inventoryLocalService) {
		_inventoryLocalService = inventoryLocalService;
	}

	/**
	* Adds the inventory to the database. Also notifies the appropriate model listeners.
	*
	* @param inventory the inventory
	* @return the inventory that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Inventory addInventory(
		com.tour.portlet.tasks.model.Inventory inventory)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.addInventory(inventory);
	}

	/**
	* Creates a new inventory with the primary key. Does not add the inventory to the database.
	*
	* @param inventoryId the primary key for the new inventory
	* @return the new inventory
	*/
	@Override
	public com.tour.portlet.tasks.model.Inventory createInventory(
		long inventoryId) {
		return _inventoryLocalService.createInventory(inventoryId);
	}

	/**
	* Deletes the inventory with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryId the primary key of the inventory
	* @return the inventory that was removed
	* @throws PortalException if a inventory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Inventory deleteInventory(
		long inventoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.deleteInventory(inventoryId);
	}

	/**
	* Deletes the inventory from the database. Also notifies the appropriate model listeners.
	*
	* @param inventory the inventory
	* @return the inventory that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Inventory deleteInventory(
		com.tour.portlet.tasks.model.Inventory inventory)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.deleteInventory(inventory);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _inventoryLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.InventoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.InventoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.tour.portlet.tasks.model.Inventory fetchInventory(
		long inventoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.fetchInventory(inventoryId);
	}

	/**
	* Returns the inventory with the primary key.
	*
	* @param inventoryId the primary key of the inventory
	* @return the inventory
	* @throws PortalException if a inventory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Inventory getInventory(long inventoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.getInventory(inventoryId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the inventories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.InventoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventories
	* @param end the upper bound of the range of inventories (not inclusive)
	* @return the range of inventories
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.tour.portlet.tasks.model.Inventory> getInventories(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.getInventories(start, end);
	}

	/**
	* Returns the number of inventories.
	*
	* @return the number of inventories
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getInventoriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.getInventoriesCount();
	}

	/**
	* Updates the inventory in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param inventory the inventory
	* @return the inventory that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Inventory updateInventory(
		com.tour.portlet.tasks.model.Inventory inventory)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.updateInventory(inventory);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _inventoryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_inventoryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _inventoryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.tour.portlet.tasks.model.Inventory addInventory(
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.addInventory(name, description,
			serviceContext);
	}

	@Override
	public com.tour.portlet.tasks.model.Inventory updateInventory(
		long inventoryId, java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _inventoryLocalService.updateInventory(inventoryId, name,
			description, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public InventoryLocalService getWrappedInventoryLocalService() {
		return _inventoryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedInventoryLocalService(
		InventoryLocalService inventoryLocalService) {
		_inventoryLocalService = inventoryLocalService;
	}

	@Override
	public InventoryLocalService getWrappedService() {
		return _inventoryLocalService;
	}

	@Override
	public void setWrappedService(InventoryLocalService inventoryLocalService) {
		_inventoryLocalService = inventoryLocalService;
	}

	private InventoryLocalService _inventoryLocalService;
}