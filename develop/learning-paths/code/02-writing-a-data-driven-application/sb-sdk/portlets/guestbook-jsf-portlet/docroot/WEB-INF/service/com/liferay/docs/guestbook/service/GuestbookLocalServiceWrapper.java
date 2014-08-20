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

package com.liferay.docs.guestbook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GuestbookLocalService}.
 *
 * @author Rich Sezov
 * @see GuestbookLocalService
 * @generated
 */
public class GuestbookLocalServiceWrapper implements GuestbookLocalService,
	ServiceWrapper<GuestbookLocalService> {
	public GuestbookLocalServiceWrapper(
		GuestbookLocalService guestbookLocalService) {
		_guestbookLocalService = guestbookLocalService;
	}

	/**
	* Adds the guestbook to the database. Also notifies the appropriate model listeners.
	*
	* @param guestbook the guestbook
	* @return the guestbook that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook addGuestbook(
		com.liferay.docs.guestbook.model.Guestbook guestbook)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.addGuestbook(guestbook);
	}

	/**
	* Creates a new guestbook with the primary key. Does not add the guestbook to the database.
	*
	* @param guestbookId the primary key for the new guestbook
	* @return the new guestbook
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook createGuestbook(
		long guestbookId) {
		return _guestbookLocalService.createGuestbook(guestbookId);
	}

	/**
	* Deletes the guestbook with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook that was removed
	* @throws PortalException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
		long guestbookId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.deleteGuestbook(guestbookId);
	}

	/**
	* Deletes the guestbook from the database. Also notifies the appropriate model listeners.
	*
	* @param guestbook the guestbook
	* @return the guestbook that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
		com.liferay.docs.guestbook.model.Guestbook guestbook)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.deleteGuestbook(guestbook);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _guestbookLocalService.dynamicQuery();
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
		return _guestbookLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _guestbookLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _guestbookLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _guestbookLocalService.dynamicQueryCount(dynamicQuery);
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
		return _guestbookLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.docs.guestbook.model.Guestbook fetchGuestbook(
		long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.fetchGuestbook(guestbookId);
	}

	/**
	* Returns the guestbook with the matching UUID and company.
	*
	* @param uuid the guestbook's UUID
	* @param companyId the primary key of the company
	* @return the matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook fetchGuestbookByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.fetchGuestbookByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the guestbook matching the UUID and group.
	*
	* @param uuid the guestbook's UUID
	* @param groupId the primary key of the group
	* @return the matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook fetchGuestbookByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.fetchGuestbookByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the guestbook with the primary key.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook
	* @throws PortalException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook getGuestbook(
		long guestbookId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getGuestbook(guestbookId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the guestbook with the matching UUID and company.
	*
	* @param uuid the guestbook's UUID
	* @param companyId the primary key of the company
	* @return the matching guestbook
	* @throws PortalException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook getGuestbookByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getGuestbookByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the guestbook matching the UUID and group.
	*
	* @param uuid the guestbook's UUID
	* @param groupId the primary key of the group
	* @return the matching guestbook
	* @throws PortalException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook getGuestbookByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getGuestbookByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the guestbooks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of guestbooks
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getGuestbooks(start, end);
	}

	/**
	* Returns the number of guestbooks.
	*
	* @return the number of guestbooks
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getGuestbooksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getGuestbooksCount();
	}

	/**
	* Updates the guestbook in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param guestbook the guestbook
	* @return the guestbook that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.docs.guestbook.model.Guestbook updateGuestbook(
		com.liferay.docs.guestbook.model.Guestbook guestbook)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.updateGuestbook(guestbook);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _guestbookLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_guestbookLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _guestbookLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.docs.guestbook.model.Guestbook getFirstGuestbookByName(
		long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getFirstGuestbookByName(groupId, name);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getGuestbooks(groupId);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _guestbookLocalService.getGuestbooks(groupId, start, end);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public GuestbookLocalService getWrappedGuestbookLocalService() {
		return _guestbookLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedGuestbookLocalService(
		GuestbookLocalService guestbookLocalService) {
		_guestbookLocalService = guestbookLocalService;
	}

	@Override
	public GuestbookLocalService getWrappedService() {
		return _guestbookLocalService;
	}

	@Override
	public void setWrappedService(GuestbookLocalService guestbookLocalService) {
		_guestbookLocalService = guestbookLocalService;
	}

	private GuestbookLocalService _guestbookLocalService;
}