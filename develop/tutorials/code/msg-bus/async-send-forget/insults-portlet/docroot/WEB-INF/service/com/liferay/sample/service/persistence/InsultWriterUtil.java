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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.sample.model.InsultWriter;

import java.util.List;

/**
 * The persistence utility for the insult writer service. This utility wraps {@link InsultWriterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see InsultWriterPersistence
 * @see InsultWriterPersistenceImpl
 * @generated
 */
public class InsultWriterUtil {
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
	public static void clearCache(InsultWriter insultWriter) {
		getPersistence().clearCache(insultWriter);
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
	public static List<InsultWriter> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<InsultWriter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<InsultWriter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static InsultWriter update(InsultWriter insultWriter)
		throws SystemException {
		return getPersistence().update(insultWriter);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static InsultWriter update(InsultWriter insultWriter,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(insultWriter, serviceContext);
	}

	/**
	* Caches the insult writer in the entity cache if it is enabled.
	*
	* @param insultWriter the insult writer
	*/
	public static void cacheResult(
		com.liferay.sample.model.InsultWriter insultWriter) {
		getPersistence().cacheResult(insultWriter);
	}

	/**
	* Caches the insult writers in the entity cache if it is enabled.
	*
	* @param insultWriters the insult writers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.sample.model.InsultWriter> insultWriters) {
		getPersistence().cacheResult(insultWriters);
	}

	/**
	* Creates a new insult writer with the primary key. Does not add the insult writer to the database.
	*
	* @param insultWriterId the primary key for the new insult writer
	* @return the new insult writer
	*/
	public static com.liferay.sample.model.InsultWriter create(
		long insultWriterId) {
		return getPersistence().create(insultWriterId);
	}

	/**
	* Removes the insult writer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param insultWriterId the primary key of the insult writer
	* @return the insult writer that was removed
	* @throws com.liferay.sample.NoSuchInsultWriterException if a insult writer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sample.model.InsultWriter remove(
		long insultWriterId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sample.NoSuchInsultWriterException {
		return getPersistence().remove(insultWriterId);
	}

	public static com.liferay.sample.model.InsultWriter updateImpl(
		com.liferay.sample.model.InsultWriter insultWriter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(insultWriter);
	}

	/**
	* Returns the insult writer with the primary key or throws a {@link com.liferay.sample.NoSuchInsultWriterException} if it could not be found.
	*
	* @param insultWriterId the primary key of the insult writer
	* @return the insult writer
	* @throws com.liferay.sample.NoSuchInsultWriterException if a insult writer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sample.model.InsultWriter findByPrimaryKey(
		long insultWriterId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sample.NoSuchInsultWriterException {
		return getPersistence().findByPrimaryKey(insultWriterId);
	}

	/**
	* Returns the insult writer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param insultWriterId the primary key of the insult writer
	* @return the insult writer, or <code>null</code> if a insult writer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sample.model.InsultWriter fetchByPrimaryKey(
		long insultWriterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(insultWriterId);
	}

	/**
	* Returns all the insult writers.
	*
	* @return the insult writers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sample.model.InsultWriter> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the insult writers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultWriterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of insult writers
	* @param end the upper bound of the range of insult writers (not inclusive)
	* @return the range of insult writers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sample.model.InsultWriter> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the insult writers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultWriterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of insult writers
	* @param end the upper bound of the range of insult writers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of insult writers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sample.model.InsultWriter> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the insult writers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of insult writers.
	*
	* @return the number of insult writers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static InsultWriterPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (InsultWriterPersistence)PortletBeanLocatorUtil.locate(com.liferay.sample.service.ClpSerializer.getServletContextName(),
					InsultWriterPersistence.class.getName());

			ReferenceRegistry.registerReference(InsultWriterUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(InsultWriterPersistence persistence) {
	}

	private static InsultWriterPersistence _persistence;
}