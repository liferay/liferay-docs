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
 * Provides a wrapper for {@link InsultLogLocalService}.
 *
 * @author nickgaskill
 * @see InsultLogLocalService
 * @generated
 */
public class InsultLogLocalServiceWrapper implements InsultLogLocalService,
	ServiceWrapper<InsultLogLocalService> {
	public InsultLogLocalServiceWrapper(
		InsultLogLocalService insultLogLocalService) {
		_insultLogLocalService = insultLogLocalService;
	}

	/**
	* Adds the insult log to the database. Also notifies the appropriate model listeners.
	*
	* @param insultLog the insult log
	* @return the insult log that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultLog addInsultLog(
		com.liferay.sample.model.InsultLog insultLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.addInsultLog(insultLog);
	}

	/**
	* Creates a new insult log with the primary key. Does not add the insult log to the database.
	*
	* @param insultLogId the primary key for the new insult log
	* @return the new insult log
	*/
	@Override
	public com.liferay.sample.model.InsultLog createInsultLog(long insultLogId) {
		return _insultLogLocalService.createInsultLog(insultLogId);
	}

	/**
	* Deletes the insult log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param insultLogId the primary key of the insult log
	* @return the insult log that was removed
	* @throws PortalException if a insult log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultLog deleteInsultLog(long insultLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.deleteInsultLog(insultLogId);
	}

	/**
	* Deletes the insult log from the database. Also notifies the appropriate model listeners.
	*
	* @param insultLog the insult log
	* @return the insult log that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultLog deleteInsultLog(
		com.liferay.sample.model.InsultLog insultLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.deleteInsultLog(insultLog);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _insultLogLocalService.dynamicQuery();
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
		return _insultLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _insultLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _insultLogLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _insultLogLocalService.dynamicQueryCount(dynamicQuery);
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
		return _insultLogLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.sample.model.InsultLog fetchInsultLog(long insultLogId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.fetchInsultLog(insultLogId);
	}

	/**
	* Returns the insult log with the primary key.
	*
	* @param insultLogId the primary key of the insult log
	* @return the insult log
	* @throws PortalException if a insult log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultLog getInsultLog(long insultLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.getInsultLog(insultLogId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.getPersistedModel(primaryKeyObj);
	}

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
	@Override
	public java.util.List<com.liferay.sample.model.InsultLog> getInsultLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.getInsultLogs(start, end);
	}

	/**
	* Returns the number of insult logs.
	*
	* @return the number of insult logs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getInsultLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.getInsultLogsCount();
	}

	/**
	* Updates the insult log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param insultLog the insult log
	* @return the insult log that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sample.model.InsultLog updateInsultLog(
		com.liferay.sample.model.InsultLog insultLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.updateInsultLog(insultLog);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _insultLogLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_insultLogLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _insultLogLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.sample.model.InsultLog addInsultLog(
		java.lang.String insult, java.lang.String rating,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.addInsultLog(insult, rating,
			serviceContext);
	}

	@Override
	public com.liferay.sample.model.InsultLog updateInsultLog(
		long insultLogId, java.lang.String insult, java.lang.String rating,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _insultLogLocalService.updateInsultLog(insultLogId, insult,
			rating, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public InsultLogLocalService getWrappedInsultLogLocalService() {
		return _insultLogLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedInsultLogLocalService(
		InsultLogLocalService insultLogLocalService) {
		_insultLogLocalService = insultLogLocalService;
	}

	@Override
	public InsultLogLocalService getWrappedService() {
		return _insultLogLocalService;
	}

	@Override
	public void setWrappedService(InsultLogLocalService insultLogLocalService) {
		_insultLogLocalService = insultLogLocalService;
	}

	private InsultLogLocalService _insultLogLocalService;
}