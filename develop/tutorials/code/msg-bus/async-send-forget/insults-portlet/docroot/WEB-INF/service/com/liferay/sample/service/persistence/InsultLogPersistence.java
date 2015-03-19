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

import com.liferay.sample.model.InsultLog;

/**
 * The persistence interface for the insult log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see InsultLogPersistenceImpl
 * @see InsultLogUtil
 * @generated
 */
public interface InsultLogPersistence extends BasePersistence<InsultLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InsultLogUtil} to access the insult log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the insult log in the entity cache if it is enabled.
	*
	* @param insultLog the insult log
	*/
	public void cacheResult(com.liferay.sample.model.InsultLog insultLog);

	/**
	* Caches the insult logs in the entity cache if it is enabled.
	*
	* @param insultLogs the insult logs
	*/
	public void cacheResult(
		java.util.List<com.liferay.sample.model.InsultLog> insultLogs);

	/**
	* Creates a new insult log with the primary key. Does not add the insult log to the database.
	*
	* @param insultLogId the primary key for the new insult log
	* @return the new insult log
	*/
	public com.liferay.sample.model.InsultLog create(long insultLogId);

	/**
	* Removes the insult log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param insultLogId the primary key of the insult log
	* @return the insult log that was removed
	* @throws com.liferay.sample.NoSuchInsultLogException if a insult log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.InsultLog remove(long insultLogId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sample.NoSuchInsultLogException;

	public com.liferay.sample.model.InsultLog updateImpl(
		com.liferay.sample.model.InsultLog insultLog)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insult log with the primary key or throws a {@link com.liferay.sample.NoSuchInsultLogException} if it could not be found.
	*
	* @param insultLogId the primary key of the insult log
	* @return the insult log
	* @throws com.liferay.sample.NoSuchInsultLogException if a insult log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.InsultLog findByPrimaryKey(long insultLogId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sample.NoSuchInsultLogException;

	/**
	* Returns the insult log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param insultLogId the primary key of the insult log
	* @return the insult log, or <code>null</code> if a insult log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.InsultLog fetchByPrimaryKey(
		long insultLogId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the insult logs.
	*
	* @return the insult logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sample.model.InsultLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the insult logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of insult logs
	* @param end the upper bound of the range of insult logs (not inclusive)
	* @return the range of insult logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sample.model.InsultLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the insult logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of insult logs
	* @param end the upper bound of the range of insult logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of insult logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sample.model.InsultLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the insult logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insult logs.
	*
	* @return the number of insult logs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}