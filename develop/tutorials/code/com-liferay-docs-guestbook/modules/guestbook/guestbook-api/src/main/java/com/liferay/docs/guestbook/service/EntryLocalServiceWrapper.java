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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntryLocalService}.
 *
 * @author liferay
 * @see EntryLocalService
 * @generated
 */
@ProviderType
public class EntryLocalServiceWrapper implements EntryLocalService,
	ServiceWrapper<EntryLocalService> {
	public EntryLocalServiceWrapper(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	/**
	* Adds the entry to the database. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was added
	*/
	@Override
	public com.liferay.docs.guestbook.model.Entry addEntry(
		com.liferay.docs.guestbook.model.Entry entry) {
		return _entryLocalService.addEntry(entry);
	}

	@Override
	public com.liferay.docs.guestbook.model.Entry addEntry(long userId,
		long guestbookId, java.lang.String name, java.lang.String email,
		java.lang.String message,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.addEntry(userId, guestbookId, name, email,
			message, serviceContext);
	}

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	@Override
	public com.liferay.docs.guestbook.model.Entry createEntry(long entryId) {
		return _entryLocalService.createEntry(entryId);
	}

	/**
	* Deletes the entry from the database. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was removed
	*/
	@Override
	public com.liferay.docs.guestbook.model.Entry deleteEntry(
		com.liferay.docs.guestbook.model.Entry entry) {
		return _entryLocalService.deleteEntry(entry);
	}

	/**
	* Deletes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws PortalException if a entry with the primary key could not be found
	*/
	@Override
	public com.liferay.docs.guestbook.model.Entry deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.deleteEntry(entryId);
	}

	@Override
	public com.liferay.docs.guestbook.model.Entry deleteEntry(long entryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.deleteEntry(entryId, serviceContext);
	}

	@Override
	public com.liferay.docs.guestbook.model.Entry fetchEntry(long entryId) {
		return _entryLocalService.fetchEntry(entryId);
	}

	/**
	* Returns the entry matching the UUID and group.
	*
	* @param uuid the entry's UUID
	* @param groupId the primary key of the group
	* @return the matching entry, or <code>null</code> if a matching entry could not be found
	*/
	@Override
	public com.liferay.docs.guestbook.model.Entry fetchEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _entryLocalService.fetchEntryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the entry with the primary key.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws PortalException if a entry with the primary key could not be found
	*/
	@Override
	public com.liferay.docs.guestbook.model.Entry getEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.getEntry(entryId);
	}

	/**
	* Returns the entry matching the UUID and group.
	*
	* @param uuid the entry's UUID
	* @param groupId the primary key of the group
	* @return the matching entry
	* @throws PortalException if a matching entry could not be found
	*/
	@Override
	public com.liferay.docs.guestbook.model.Entry getEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.getEntryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entry the entry
	* @return the entry that was updated
	*/
	@Override
	public com.liferay.docs.guestbook.model.Entry updateEntry(
		com.liferay.docs.guestbook.model.Entry entry) {
		return _entryLocalService.updateEntry(entry);
	}

	@Override
	public com.liferay.docs.guestbook.model.Entry updateEntry(long userId,
		long guestbookId, long entryId, java.lang.String name,
		java.lang.String email, java.lang.String message,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.updateEntry(userId, guestbookId, entryId,
			name, email, message, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _entryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _entryLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _entryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	*/
	@Override
	public int getEntriesCount() {
		return _entryLocalService.getEntriesCount();
	}

	@Override
	public int getEntriesCount(long groupId, long guestbookId) {
		return _entryLocalService.getEntriesCount(groupId, guestbookId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _entryLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _entryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _entryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _entryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	*/
	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Entry> getEntries(
		int start, int end) {
		return _entryLocalService.getEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Entry> getEntries(
		long groupId, long guestbookId) {
		return _entryLocalService.getEntries(groupId, guestbookId);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Entry> getEntries(
		long groupId, long guestbookId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.getEntries(groupId, guestbookId, start, end);
	}

	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Entry> getEntries(
		long groupId, long guestbookId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.docs.guestbook.model.Entry> obc) {
		return _entryLocalService.getEntries(groupId, guestbookId, start, end,
			obc);
	}

	/**
	* Returns all the entries matching the UUID and company.
	*
	* @param uuid the UUID of the entries
	* @param companyId the primary key of the company
	* @return the matching entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Entry> getEntriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _entryLocalService.getEntriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of entries matching the UUID and company.
	*
	* @param uuid the UUID of the entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching entries, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.docs.guestbook.model.Entry> getEntriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.docs.guestbook.model.Entry> orderByComparator) {
		return _entryLocalService.getEntriesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _entryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _entryLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public EntryLocalService getWrappedService() {
		return _entryLocalService;
	}

	@Override
	public void setWrappedService(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	private EntryLocalService _entryLocalService;
}