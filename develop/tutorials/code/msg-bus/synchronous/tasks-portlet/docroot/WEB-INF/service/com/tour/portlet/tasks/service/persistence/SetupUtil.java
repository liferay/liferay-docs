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

import com.tour.portlet.tasks.model.Setup;

import java.util.List;

/**
 * The persistence utility for the setup service. This utility wraps {@link SetupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see SetupPersistence
 * @see SetupPersistenceImpl
 * @generated
 */
public class SetupUtil {
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
	public static void clearCache(Setup setup) {
		getPersistence().clearCache(setup);
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
	public static List<Setup> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Setup> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Setup> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Setup update(Setup setup) throws SystemException {
		return getPersistence().update(setup);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Setup update(Setup setup, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(setup, serviceContext);
	}

	/**
	* Caches the setup in the entity cache if it is enabled.
	*
	* @param setup the setup
	*/
	public static void cacheResult(com.tour.portlet.tasks.model.Setup setup) {
		getPersistence().cacheResult(setup);
	}

	/**
	* Caches the setups in the entity cache if it is enabled.
	*
	* @param setups the setups
	*/
	public static void cacheResult(
		java.util.List<com.tour.portlet.tasks.model.Setup> setups) {
		getPersistence().cacheResult(setups);
	}

	/**
	* Creates a new setup with the primary key. Does not add the setup to the database.
	*
	* @param setupId the primary key for the new setup
	* @return the new setup
	*/
	public static com.tour.portlet.tasks.model.Setup create(long setupId) {
		return getPersistence().create(setupId);
	}

	/**
	* Removes the setup with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param setupId the primary key of the setup
	* @return the setup that was removed
	* @throws com.tour.portlet.tasks.NoSuchSetupException if a setup with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tour.portlet.tasks.model.Setup remove(long setupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tour.portlet.tasks.NoSuchSetupException {
		return getPersistence().remove(setupId);
	}

	public static com.tour.portlet.tasks.model.Setup updateImpl(
		com.tour.portlet.tasks.model.Setup setup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(setup);
	}

	/**
	* Returns the setup with the primary key or throws a {@link com.tour.portlet.tasks.NoSuchSetupException} if it could not be found.
	*
	* @param setupId the primary key of the setup
	* @return the setup
	* @throws com.tour.portlet.tasks.NoSuchSetupException if a setup with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tour.portlet.tasks.model.Setup findByPrimaryKey(
		long setupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tour.portlet.tasks.NoSuchSetupException {
		return getPersistence().findByPrimaryKey(setupId);
	}

	/**
	* Returns the setup with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param setupId the primary key of the setup
	* @return the setup, or <code>null</code> if a setup with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.tour.portlet.tasks.model.Setup fetchByPrimaryKey(
		long setupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(setupId);
	}

	/**
	* Returns all the setups.
	*
	* @return the setups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tour.portlet.tasks.model.Setup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the setups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.SetupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of setups
	* @param end the upper bound of the range of setups (not inclusive)
	* @return the range of setups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tour.portlet.tasks.model.Setup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the setups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.SetupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of setups
	* @param end the upper bound of the range of setups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of setups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.tour.portlet.tasks.model.Setup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the setups from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of setups.
	*
	* @return the number of setups
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SetupPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SetupPersistence)PortletBeanLocatorUtil.locate(com.tour.portlet.tasks.service.ClpSerializer.getServletContextName(),
					SetupPersistence.class.getName());

			ReferenceRegistry.registerReference(SetupUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(SetupPersistence persistence) {
	}

	private static SetupPersistence _persistence;
}