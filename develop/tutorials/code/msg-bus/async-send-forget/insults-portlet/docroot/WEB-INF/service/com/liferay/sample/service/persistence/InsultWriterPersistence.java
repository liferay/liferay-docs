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

import com.liferay.sample.model.InsultWriter;

/**
 * The persistence interface for the insult writer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see InsultWriterPersistenceImpl
 * @see InsultWriterUtil
 * @generated
 */
public interface InsultWriterPersistence extends BasePersistence<InsultWriter> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InsultWriterUtil} to access the insult writer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the insult writer in the entity cache if it is enabled.
	*
	* @param insultWriter the insult writer
	*/
	public void cacheResult(com.liferay.sample.model.InsultWriter insultWriter);

	/**
	* Caches the insult writers in the entity cache if it is enabled.
	*
	* @param insultWriters the insult writers
	*/
	public void cacheResult(
		java.util.List<com.liferay.sample.model.InsultWriter> insultWriters);

	/**
	* Creates a new insult writer with the primary key. Does not add the insult writer to the database.
	*
	* @param insultWriterId the primary key for the new insult writer
	* @return the new insult writer
	*/
	public com.liferay.sample.model.InsultWriter create(long insultWriterId);

	/**
	* Removes the insult writer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param insultWriterId the primary key of the insult writer
	* @return the insult writer that was removed
	* @throws com.liferay.sample.NoSuchInsultWriterException if a insult writer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.InsultWriter remove(long insultWriterId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sample.NoSuchInsultWriterException;

	public com.liferay.sample.model.InsultWriter updateImpl(
		com.liferay.sample.model.InsultWriter insultWriter)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insult writer with the primary key or throws a {@link com.liferay.sample.NoSuchInsultWriterException} if it could not be found.
	*
	* @param insultWriterId the primary key of the insult writer
	* @return the insult writer
	* @throws com.liferay.sample.NoSuchInsultWriterException if a insult writer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.InsultWriter findByPrimaryKey(
		long insultWriterId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sample.NoSuchInsultWriterException;

	/**
	* Returns the insult writer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param insultWriterId the primary key of the insult writer
	* @return the insult writer, or <code>null</code> if a insult writer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sample.model.InsultWriter fetchByPrimaryKey(
		long insultWriterId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the insult writers.
	*
	* @return the insult writers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sample.model.InsultWriter> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sample.model.InsultWriter> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sample.model.InsultWriter> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the insult writers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insult writers.
	*
	* @return the number of insult writers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}