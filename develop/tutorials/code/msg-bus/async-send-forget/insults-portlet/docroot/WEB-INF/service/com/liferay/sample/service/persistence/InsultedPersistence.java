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

package com.liferay.sample.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.sample.model.Insulted;

/**
 * The persistence interface for the insulted service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see InsultedPersistenceImpl
 * @see InsultedUtil
 * @generated
 */
public interface InsultedPersistence extends BasePersistence<Insulted> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InsultedUtil} to access the insulted persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the insulted in the entity cache if it is enabled.
	*
	* @param insulted the insulted
	*/
	public void cacheResult(com.liferay.sample.model.Insulted insulted);

	/**
	* Caches the insulteds in the entity cache if it is enabled.
	*
	* @param insulteds the insulteds
	*/
	public void cacheResult(
		java.util.List<com.liferay.sample.model.Insulted> insulteds);

	/**
	* Creates a new insulted with the primary key. Does not add the insulted to the database.
	*
	* @param insultedId the primary key for the new insulted
	* @return the new insulted
	*/
	public com.liferay.sample.model.Insulted create(long insultedId);

	/**
	* Removes the insulted with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param insultedId the primary key of the insulted
	* @return the insulted that was removed
	* @throws com.liferay.sample.NoSuchInsultedException if a insulted with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.Insulted remove(long insultedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sample.NoSuchInsultedException;

	public com.liferay.sample.model.Insulted updateImpl(
		com.liferay.sample.model.Insulted insulted)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insulted with the primary key or throws a {@link com.liferay.sample.NoSuchInsultedException} if it could not be found.
	*
	* @param insultedId the primary key of the insulted
	* @return the insulted
	* @throws com.liferay.sample.NoSuchInsultedException if a insulted with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.Insulted findByPrimaryKey(long insultedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sample.NoSuchInsultedException;

	/**
	* Returns the insulted with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param insultedId the primary key of the insulted
	* @return the insulted, or <code>null</code> if a insulted with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.Insulted fetchByPrimaryKey(long insultedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the insulteds.
	*
	* @return the insulteds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sample.model.Insulted> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the insulteds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of insulteds
	* @param end the upper bound of the range of insulteds (not inclusive)
	* @return the range of insulteds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sample.model.Insulted> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the insulteds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of insulteds
	* @param end the upper bound of the range of insulteds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of insulteds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sample.model.Insulted> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the insulteds from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insulteds.
	*
	* @return the number of insulteds
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}