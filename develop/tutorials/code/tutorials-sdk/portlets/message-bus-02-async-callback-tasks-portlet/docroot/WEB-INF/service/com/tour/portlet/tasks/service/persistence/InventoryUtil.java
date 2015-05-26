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

package com.tour.portlet.tasks.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.tour.portlet.tasks.model.Inventory;

import java.util.List;

/**
 * The persistence utility for the inventory service. This utility wraps {@link InventoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see InventoryPersistence
 * @see InventoryPersistenceImpl
 * @generated
 */
public class InventoryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Inventory inventory) {
		getPersistence().clearCache(inventory);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Inventory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Inventory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Inventory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Inventory update(Inventory inventory)
		throws SystemException {
		return getPersistence().update(inventory);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Inventory update(Inventory inventory,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(inventory, serviceContext);
	}

	/**
	* Caches the inventory in the entity cache if it is enabled.
	*
	* @param inventory the inventory
	*/
	public static void cacheResult(
		com.tour.portlet.tasks.model.Inventory inventory) {
		getPersistence().cacheResult(inventory);
	}

	/**
	* Caches the inventories in the entity cache if it is enabled.
	*
	* @param inventories the inventories
	*/
	public static void cacheResult(
		java.util.List<com.tour.portlet.tasks.model.Inventory> inventories) {
		getPersistence().cacheResult(inventories);
	}

	/**
	* Creates a new inventory with the primary key. Does not add the inventory to the database.
	*
	* @param inventoryId the primary key for the new inventory
	* @return the new inventory
	*/
	public static com.tour.portlet.tasks.model.Inventory create(
		long inventoryId) {
		return getPersistence().create(inventoryId);
	}

	/**
	* Removes the inventory with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryId the primary key of the inventory
	* @return the inventory that was removed
	* @throws com.tour.portlet.tasks.NoSuchInventoryException if a inventory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tour.portlet.tasks.model.Inventory remove(
		long inventoryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tour.portlet.tasks.NoSuchInventoryException {
		return getPersistence().remove(inventoryId);
	}

	public static com.tour.portlet.tasks.model.Inventory updateImpl(
		com.tour.portlet.tasks.model.Inventory inventory)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(inventory);
	}

	/**
	* Returns the inventory with the primary key or throws a {@link com.tour.portlet.tasks.NoSuchInventoryException} if it could not be found.
	*
	* @param inventoryId the primary key of the inventory
	* @return the inventory
	* @throws com.tour.portlet.tasks.NoSuchInventoryException if a inventory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tour.portlet.tasks.model.Inventory findByPrimaryKey(
		long inventoryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tour.portlet.tasks.NoSuchInventoryException {
		return getPersistence().findByPrimaryKey(inventoryId);
	}

	/**
	* Returns the inventory with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param inventoryId the primary key of the inventory
	* @return the inventory, or <code>null</code> if a inventory with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tour.portlet.tasks.model.Inventory fetchByPrimaryKey(
		long inventoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(inventoryId);
	}

	/**
	* Returns all the inventories.
	*
	* @return the inventories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tour.portlet.tasks.model.Inventory> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.tour.portlet.tasks.model.Inventory> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the inventories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.InventoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventories
	* @param end the upper bound of the range of inventories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of inventories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tour.portlet.tasks.model.Inventory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the inventories from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of inventories.
	*
	* @return the number of inventories
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static InventoryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (InventoryPersistence)PortletBeanLocatorUtil.locate(com.tour.portlet.tasks.service.ClpSerializer.getServletContextName(),
					InventoryPersistence.class.getName());

			ReferenceRegistry.registerReference(InventoryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(InventoryPersistence persistence) {
	}

	private static InventoryPersistence _persistence;
}