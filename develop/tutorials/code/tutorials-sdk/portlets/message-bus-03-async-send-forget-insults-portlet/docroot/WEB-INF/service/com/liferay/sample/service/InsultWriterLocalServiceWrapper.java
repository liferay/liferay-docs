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

package com.liferay.sample.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InsultWriterLocalService}.
 *
 * @author nickgaskill
 * @see InsultWriterLocalService
 * @generated
 */
public class InsultWriterLocalServiceWrapper implements InsultWriterLocalService,
	ServiceWrapper<InsultWriterLocalService> {
	public InsultWriterLocalServiceWrapper(
		InsultWriterLocalService insultWriterLocalService) {
		_insultWriterLocalService = insultWriterLocalService;
	}

	/**
	* Adds the insult writer to the database. Also notifies the appropriate model listeners.
	*
	* @param insultWriter the insult writer
	* @return the insult writer that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultWriter addInsultWriter(
		com.liferay.sample.model.InsultWriter insultWriter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.addInsultWriter(insultWriter);
	}

	/**
	* Creates a new insult writer with the primary key. Does not add the insult writer to the database.
	*
	* @param insultWriterId the primary key for the new insult writer
	* @return the new insult writer
	*/
	@Override
	public com.liferay.sample.model.InsultWriter createInsultWriter(
		long insultWriterId) {
		return _insultWriterLocalService.createInsultWriter(insultWriterId);
	}

	/**
	* Deletes the insult writer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param insultWriterId the primary key of the insult writer
	* @return the insult writer that was removed
	* @throws PortalException if a insult writer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultWriter deleteInsultWriter(
		long insultWriterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.deleteInsultWriter(insultWriterId);
	}

	/**
	* Deletes the insult writer from the database. Also notifies the appropriate model listeners.
	*
	* @param insultWriter the insult writer
	* @return the insult writer that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultWriter deleteInsultWriter(
		com.liferay.sample.model.InsultWriter insultWriter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.deleteInsultWriter(insultWriter);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _insultWriterLocalService.dynamicQuery();
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
		return _insultWriterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultWriterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _insultWriterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultWriterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _insultWriterLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _insultWriterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _insultWriterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.sample.model.InsultWriter fetchInsultWriter(
		long insultWriterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.fetchInsultWriter(insultWriterId);
	}

	/**
	* Returns the insult writer with the primary key.
	*
	* @param insultWriterId the primary key of the insult writer
	* @return the insult writer
	* @throws PortalException if a insult writer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultWriter getInsultWriter(
		long insultWriterId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.getInsultWriter(insultWriterId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<com.liferay.sample.model.InsultWriter> getInsultWriters(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.getInsultWriters(start, end);
	}

	/**
	* Returns the number of insult writers.
	*
	* @return the number of insult writers
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getInsultWritersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.getInsultWritersCount();
	}

	/**
	* Updates the insult writer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param insultWriter the insult writer
	* @return the insult writer that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultWriter updateInsultWriter(
		com.liferay.sample.model.InsultWriter insultWriter)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.updateInsultWriter(insultWriter);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _insultWriterLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_insultWriterLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _insultWriterLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.sample.model.InsultWriter addInsultWriter(
		java.lang.String insult, java.lang.String rating,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.addInsultWriter(insult, rating,
			serviceContext);
	}

	@Override
	public com.liferay.sample.model.InsultWriter updateInsultWriter(
		long insultWriterId, java.lang.String insult, java.lang.String rating,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultWriterLocalService.updateInsultWriter(insultWriterId,
			insult, rating, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public InsultWriterLocalService getWrappedInsultWriterLocalService() {
		return _insultWriterLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedInsultWriterLocalService(
		InsultWriterLocalService insultWriterLocalService) {
		_insultWriterLocalService = insultWriterLocalService;
	}

	@Override
	public InsultWriterLocalService getWrappedService() {
		return _insultWriterLocalService;
	}

	@Override
	public void setWrappedService(
		InsultWriterLocalService insultWriterLocalService) {
		_insultWriterLocalService = insultWriterLocalService;
	}

	private InsultWriterLocalService _insultWriterLocalService;
}