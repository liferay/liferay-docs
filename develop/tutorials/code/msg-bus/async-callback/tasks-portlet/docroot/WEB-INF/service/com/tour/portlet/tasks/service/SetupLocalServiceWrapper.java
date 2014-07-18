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

package com.tour.portlet.tasks.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SetupLocalService}.
 *
 * @author nickgaskill
 * @see SetupLocalService
 * @generated
 */
public class SetupLocalServiceWrapper implements SetupLocalService,
	ServiceWrapper<SetupLocalService> {
	public SetupLocalServiceWrapper(SetupLocalService setupLocalService) {
		_setupLocalService = setupLocalService;
	}

	/**
	* Adds the setup to the database. Also notifies the appropriate model listeners.
	*
	* @param setup the setup
	* @return the setup that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Setup addSetup(
		com.tour.portlet.tasks.model.Setup setup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.addSetup(setup);
	}

	/**
	* Creates a new setup with the primary key. Does not add the setup to the database.
	*
	* @param setupId the primary key for the new setup
	* @return the new setup
	*/
	@Override
	public com.tour.portlet.tasks.model.Setup createSetup(long setupId) {
		return _setupLocalService.createSetup(setupId);
	}

	/**
	* Deletes the setup with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param setupId the primary key of the setup
	* @return the setup that was removed
	* @throws PortalException if a setup with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Setup deleteSetup(long setupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.deleteSetup(setupId);
	}

	/**
	* Deletes the setup from the database. Also notifies the appropriate model listeners.
	*
	* @param setup the setup
	* @return the setup that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Setup deleteSetup(
		com.tour.portlet.tasks.model.Setup setup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.deleteSetup(setup);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _setupLocalService.dynamicQuery();
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
		return _setupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.SetupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _setupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.SetupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _setupLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _setupLocalService.dynamicQueryCount(dynamicQuery);
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
		return _setupLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.tour.portlet.tasks.model.Setup fetchSetup(long setupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.fetchSetup(setupId);
	}

	/**
	* Returns the setup with the primary key.
	*
	* @param setupId the primary key of the setup
	* @return the setup
	* @throws PortalException if a setup with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Setup getSetup(long setupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.getSetup(setupId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<com.tour.portlet.tasks.model.Setup> getSetups(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.getSetups(start, end);
	}

	/**
	* Returns the number of setups.
	*
	* @return the number of setups
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSetupsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.getSetupsCount();
	}

	/**
	* Updates the setup in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param setup the setup
	* @return the setup that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.tour.portlet.tasks.model.Setup updateSetup(
		com.tour.portlet.tasks.model.Setup setup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.updateSetup(setup);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _setupLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_setupLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _setupLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.tour.portlet.tasks.model.Setup addSetup(java.lang.String name,
		java.lang.String description, java.lang.String status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.addSetup(name, description, status,
			serviceContext);
	}

	@Override
	public com.tour.portlet.tasks.model.Setup updateSetup(long setupId,
		java.lang.String name, java.lang.String description,
		java.lang.String status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _setupLocalService.updateSetup(setupId, name, description,
			status, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SetupLocalService getWrappedSetupLocalService() {
		return _setupLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSetupLocalService(SetupLocalService setupLocalService) {
		_setupLocalService = setupLocalService;
	}

	@Override
	public SetupLocalService getWrappedService() {
		return _setupLocalService;
	}

	@Override
	public void setWrappedService(SetupLocalService setupLocalService) {
		_setupLocalService = setupLocalService;
	}

	private SetupLocalService _setupLocalService;
}