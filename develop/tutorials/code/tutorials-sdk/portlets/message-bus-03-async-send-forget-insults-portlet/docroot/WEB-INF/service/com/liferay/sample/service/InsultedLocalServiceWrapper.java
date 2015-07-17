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
 * Provides a wrapper for {@link InsultedLocalService}.
 *
 * @author nickgaskill
 * @see InsultedLocalService
 * @generated
 */
public class InsultedLocalServiceWrapper implements InsultedLocalService,
	ServiceWrapper<InsultedLocalService> {
	public InsultedLocalServiceWrapper(
		InsultedLocalService insultedLocalService) {
		_insultedLocalService = insultedLocalService;
	}

	/**
	* Adds the insulted to the database. Also notifies the appropriate model listeners.
	*
	* @param insulted the insulted
	* @return the insulted that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.Insulted addInsulted(
		com.liferay.sample.model.Insulted insulted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.addInsulted(insulted);
	}

	/**
	* Creates a new insulted with the primary key. Does not add the insulted to the database.
	*
	* @param insultedId the primary key for the new insulted
	* @return the new insulted
	*/
	@Override
	public com.liferay.sample.model.Insulted createInsulted(long insultedId) {
		return _insultedLocalService.createInsulted(insultedId);
	}

	/**
	* Deletes the insulted with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param insultedId the primary key of the insulted
	* @return the insulted that was removed
	* @throws PortalException if a insulted with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.Insulted deleteInsulted(long insultedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.deleteInsulted(insultedId);
	}

	/**
	* Deletes the insulted from the database. Also notifies the appropriate model listeners.
	*
	* @param insulted the insulted
	* @return the insulted that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.Insulted deleteInsulted(
		com.liferay.sample.model.Insulted insulted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.deleteInsulted(insulted);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _insultedLocalService.dynamicQuery();
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
		return _insultedLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _insultedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _insultedLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _insultedLocalService.dynamicQueryCount(dynamicQuery);
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
		return _insultedLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.sample.model.Insulted fetchInsulted(long insultedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.fetchInsulted(insultedId);
	}

	/**
	* Returns the insulted with the primary key.
	*
	* @param insultedId the primary key of the insulted
	* @return the insulted
	* @throws PortalException if a insulted with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.Insulted getInsulted(long insultedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.getInsulted(insultedId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.getPersistedModel(primaryKeyObj);
	}

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
	@Override
	public java.util.List<com.liferay.sample.model.Insulted> getInsulteds(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.getInsulteds(start, end);
	}

	/**
	* Returns the number of insulteds.
	*
	* @return the number of insulteds
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getInsultedsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.getInsultedsCount();
	}

	/**
	* Updates the insulted in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param insulted the insulted
	* @return the insulted that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.Insulted updateInsulted(
		com.liferay.sample.model.Insulted insulted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.updateInsulted(insulted);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _insultedLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_insultedLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _insultedLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.sample.model.Insulted addInsulted(
		java.lang.String insult,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.addInsulted(insult, serviceContext);
	}

	@Override
	public com.liferay.sample.model.Insulted updateInsulted(long insultedId,
		java.lang.String insult,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultedLocalService.updateInsulted(insultedId, insult,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public InsultedLocalService getWrappedInsultedLocalService() {
		return _insultedLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedInsultedLocalService(
		InsultedLocalService insultedLocalService) {
		_insultedLocalService = insultedLocalService;
	}

	@Override
	public InsultedLocalService getWrappedService() {
		return _insultedLocalService;
	}

	@Override
	public void setWrappedService(InsultedLocalService insultedLocalService) {
		_insultedLocalService = insultedLocalService;
	}

	private InsultedLocalService _insultedLocalService;
}