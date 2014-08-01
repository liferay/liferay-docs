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

import com.liferay.portal.service.persistence.BasePersistence;

import com.tour.portlet.tasks.model.Setup;

/**
 * The persistence interface for the setup service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see SetupPersistenceImpl
 * @see SetupUtil
 * @generated
 */
public interface SetupPersistence extends BasePersistence<Setup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SetupUtil} to access the setup persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the setup in the entity cache if it is enabled.
	*
	* @param setup the setup
	*/
	public void cacheResult(com.tour.portlet.tasks.model.Setup setup);

	/**
	* Caches the setups in the entity cache if it is enabled.
	*
	* @param setups the setups
	*/
	public void cacheResult(
		java.util.List<com.tour.portlet.tasks.model.Setup> setups);

	/**
	* Creates a new setup with the primary key. Does not add the setup to the database.
	*
	* @param setupId the primary key for the new setup
	* @return the new setup
	*/
	public com.tour.portlet.tasks.model.Setup create(long setupId);

	/**
	* Removes the setup with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param setupId the primary key of the setup
	* @return the setup that was removed
	* @throws com.tour.portlet.tasks.NoSuchSetupException if a setup with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tour.portlet.tasks.model.Setup remove(long setupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tour.portlet.tasks.NoSuchSetupException;

	public com.tour.portlet.tasks.model.Setup updateImpl(
		com.tour.portlet.tasks.model.Setup setup)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the setup with the primary key or throws a {@link com.tour.portlet.tasks.NoSuchSetupException} if it could not be found.
	*
	* @param setupId the primary key of the setup
	* @return the setup
	* @throws com.tour.portlet.tasks.NoSuchSetupException if a setup with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tour.portlet.tasks.model.Setup findByPrimaryKey(long setupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.tour.portlet.tasks.NoSuchSetupException;

	/**
	* Returns the setup with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param setupId the primary key of the setup
	* @return the setup, or <code>null</code> if a setup with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tour.portlet.tasks.model.Setup fetchByPrimaryKey(long setupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the setups.
	*
	* @return the setups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tour.portlet.tasks.model.Setup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tour.portlet.tasks.model.Setup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.tour.portlet.tasks.model.Setup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the setups from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of setups.
	*
	* @return the number of setups
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}