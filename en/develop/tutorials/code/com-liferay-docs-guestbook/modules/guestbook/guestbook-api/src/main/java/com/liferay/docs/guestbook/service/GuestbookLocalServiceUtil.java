/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Guestbook. This utility wraps
 * {@link com.liferay.docs.guestbook.service.impl.GuestbookLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author liferay
 * @see GuestbookLocalService
 * @see com.liferay.docs.guestbook.service.base.GuestbookLocalServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.impl.GuestbookLocalServiceImpl
 * @generated
 */
@ProviderType
public class GuestbookLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.docs.guestbook.service.impl.GuestbookLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the guestbook to the database. Also notifies the appropriate model listeners.
	*
	* @param guestbook the guestbook
	* @return the guestbook that was added
	*/
	public static com.liferay.docs.guestbook.model.Guestbook addGuestbook(
		com.liferay.docs.guestbook.model.Guestbook guestbook) {
		return getService().addGuestbook(guestbook);
	}

	public static com.liferay.docs.guestbook.model.Guestbook addGuestbook(
		long userId, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addGuestbook(userId, name, serviceContext);
	}

	/**
	* Creates a new guestbook with the primary key. Does not add the guestbook to the database.
	*
	* @param guestbookId the primary key for the new guestbook
	* @return the new guestbook
	*/
	public static com.liferay.docs.guestbook.model.Guestbook createGuestbook(
		long guestbookId) {
		return getService().createGuestbook(guestbookId);
	}

	/**
	* Deletes the guestbook from the database. Also notifies the appropriate model listeners.
	*
	* @param guestbook the guestbook
	* @return the guestbook that was removed
	*/
	public static com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
		com.liferay.docs.guestbook.model.Guestbook guestbook) {
		return getService().deleteGuestbook(guestbook);
	}

	/**
	* Deletes the guestbook with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook that was removed
	* @throws PortalException if a guestbook with the primary key could not be found
	*/
	public static com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
		long guestbookId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteGuestbook(guestbookId);
	}

	public static com.liferay.docs.guestbook.model.Guestbook deleteGuestbook(
		long guestbookId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteGuestbook(guestbookId, serviceContext);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.docs.guestbook.model.Guestbook fetchGuestbook(
		long guestbookId) {
		return getService().fetchGuestbook(guestbookId);
	}

	/**
	* Returns the guestbook matching the UUID and group.
	*
	* @param uuid the guestbook's UUID
	* @param groupId the primary key of the group
	* @return the matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchGuestbookByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchGuestbookByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* Returns the guestbook with the primary key.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook
	* @throws PortalException if a guestbook with the primary key could not be found
	*/
	public static com.liferay.docs.guestbook.model.Guestbook getGuestbook(
		long guestbookId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGuestbook(guestbookId);
	}

	/**
	* Returns the guestbook matching the UUID and group.
	*
	* @param uuid the guestbook's UUID
	* @param groupId the primary key of the group
	* @return the matching guestbook
	* @throws PortalException if a matching guestbook could not be found
	*/
	public static com.liferay.docs.guestbook.model.Guestbook getGuestbookByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGuestbookByUuidAndGroupId(uuid, groupId);
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
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		int start, int end) {
		return getService().getGuestbooks(start, end);
	}

	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		long groupId) {
		return getService().getGuestbooks(groupId);
	}

	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		long groupId, int start, int end) {
		return getService().getGuestbooks(groupId, start, end);
	}

	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooks(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.docs.guestbook.model.Guestbook> obc) {
		return getService().getGuestbooks(groupId, start, end, obc);
	}

	/**
	* Returns all the guestbooks matching the UUID and company.
	*
	* @param uuid the UUID of the guestbooks
	* @param companyId the primary key of the company
	* @return the matching guestbooks, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooksByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getGuestbooksByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of guestbooks matching the UUID and company.
	*
	* @param uuid the UUID of the guestbooks
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching guestbooks, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> getGuestbooksByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.docs.guestbook.model.Guestbook> orderByComparator) {
		return getService()
				   .getGuestbooksByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of guestbooks.
	*
	* @return the number of guestbooks
	*/
	public static int getGuestbooksCount() {
		return getService().getGuestbooksCount();
	}

	public static int getGuestbooksCount(long groupId) {
		return getService().getGuestbooksCount(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the guestbook in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param guestbook the guestbook
	* @return the guestbook that was updated
	*/
	public static com.liferay.docs.guestbook.model.Guestbook updateGuestbook(
		com.liferay.docs.guestbook.model.Guestbook guestbook) {
		return getService().updateGuestbook(guestbook);
	}

	public static com.liferay.docs.guestbook.model.Guestbook updateGuestbook(
		long userId, long guestbookId, String name,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateGuestbook(userId, guestbookId, name, serviceContext);
	}

	public static GuestbookLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GuestbookLocalService, GuestbookLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(GuestbookLocalService.class);

		ServiceTracker<GuestbookLocalService, GuestbookLocalService> serviceTracker =
			new ServiceTracker<GuestbookLocalService, GuestbookLocalService>(bundle.getBundleContext(),
				GuestbookLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}