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

package com.liferay.docs.guestbook.service.persistence;

import com.liferay.docs.guestbook.model.GuestbookEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the guestbook entry service. This utility wraps <code>com.liferay.docs.guestbook.service.persistence.impl.GuestbookEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Liferay
 * @see GuestbookEntryPersistence
 * @generated
 */
@ProviderType
public class GuestbookEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(GuestbookEntry guestbookEntry) {
		getPersistence().clearCache(guestbookEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, GuestbookEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<GuestbookEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GuestbookEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GuestbookEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GuestbookEntry update(GuestbookEntry guestbookEntry) {
		return getPersistence().update(guestbookEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GuestbookEntry update(
		GuestbookEntry guestbookEntry, ServiceContext serviceContext) {

		return getPersistence().update(guestbookEntry, serviceContext);
	}

	/**
	 * Returns all the guestbook entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching guestbook entries
	 */
	public static List<GuestbookEntry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the guestbook entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @return the range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry findByUuid_First(
			String uuid, OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry fetchByUuid_First(
		String uuid, OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry findByUuid_Last(
			String uuid, OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry fetchByUuid_Last(
		String uuid, OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the guestbook entries before and after the current guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param entryId the primary key of the current guestbook entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	public static GuestbookEntry[] findByUuid_PrevAndNext(
			long entryId, String uuid,
			OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByUuid_PrevAndNext(
			entryId, uuid, orderByComparator);
	}

	/**
	 * Removes all the guestbook entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of guestbook entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching guestbook entries
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the guestbook entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchGuestbookEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry findByUUID_G(String uuid, long groupId)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the guestbook entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the guestbook entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	 * Removes the guestbook entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the guestbook entry that was removed
	 */
	public static GuestbookEntry removeByUUID_G(String uuid, long groupId)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of guestbook entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching guestbook entries
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching guestbook entries
	 */
	public static List<GuestbookEntry> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @return the range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the guestbook entries before and after the current guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param entryId the primary key of the current guestbook entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	public static GuestbookEntry[] findByUuid_C_PrevAndNext(
			long entryId, String uuid, long companyId,
			OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByUuid_C_PrevAndNext(
			entryId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the guestbook entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching guestbook entries
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the guestbook entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the matching guestbook entries
	 */
	public static List<GuestbookEntry> findByG_G(
		long groupId, long guestbookId) {

		return getPersistence().findByG_G(groupId, guestbookId);
	}

	/**
	 * Returns a range of all the guestbook entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @return the range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end) {

		return getPersistence().findByG_G(groupId, guestbookId, start, end);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().findByG_G(
			groupId, guestbookId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching guestbook entries
	 */
	public static List<GuestbookEntry> findByG_G(
		long groupId, long guestbookId, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByG_G(
			groupId, guestbookId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first guestbook entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry findByG_G_First(
			long groupId, long guestbookId,
			OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByG_G_First(
			groupId, guestbookId, orderByComparator);
	}

	/**
	 * Returns the first guestbook entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry fetchByG_G_First(
		long groupId, long guestbookId,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().fetchByG_G_First(
			groupId, guestbookId, orderByComparator);
	}

	/**
	 * Returns the last guestbook entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry findByG_G_Last(
			long groupId, long guestbookId,
			OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByG_G_Last(
			groupId, guestbookId, orderByComparator);
	}

	/**
	 * Returns the last guestbook entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	public static GuestbookEntry fetchByG_G_Last(
		long groupId, long guestbookId,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().fetchByG_G_Last(
			groupId, guestbookId, orderByComparator);
	}

	/**
	 * Returns the guestbook entries before and after the current guestbook entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param entryId the primary key of the current guestbook entry
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	public static GuestbookEntry[] findByG_G_PrevAndNext(
			long entryId, long groupId, long guestbookId,
			OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByG_G_PrevAndNext(
			entryId, groupId, guestbookId, orderByComparator);
	}

	/**
	 * Returns all the guestbook entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the matching guestbook entries that the user has permission to view
	 */
	public static List<GuestbookEntry> filterFindByG_G(
		long groupId, long guestbookId) {

		return getPersistence().filterFindByG_G(groupId, guestbookId);
	}

	/**
	 * Returns a range of all the guestbook entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @return the range of matching guestbook entries that the user has permission to view
	 */
	public static List<GuestbookEntry> filterFindByG_G(
		long groupId, long guestbookId, int start, int end) {

		return getPersistence().filterFindByG_G(
			groupId, guestbookId, start, end);
	}

	/**
	 * Returns an ordered range of all the guestbook entries that the user has permissions to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guestbook entries that the user has permission to view
	 */
	public static List<GuestbookEntry> filterFindByG_G(
		long groupId, long guestbookId, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().filterFindByG_G(
			groupId, guestbookId, start, end, orderByComparator);
	}

	/**
	 * Returns the guestbook entries before and after the current guestbook entry in the ordered set of guestbook entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param entryId the primary key of the current guestbook entry
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	public static GuestbookEntry[] filterFindByG_G_PrevAndNext(
			long entryId, long groupId, long guestbookId,
			OrderByComparator<GuestbookEntry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().filterFindByG_G_PrevAndNext(
			entryId, groupId, guestbookId, orderByComparator);
	}

	/**
	 * Removes all the guestbook entries where groupId = &#63; and guestbookId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 */
	public static void removeByG_G(long groupId, long guestbookId) {
		getPersistence().removeByG_G(groupId, guestbookId);
	}

	/**
	 * Returns the number of guestbook entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the number of matching guestbook entries
	 */
	public static int countByG_G(long groupId, long guestbookId) {
		return getPersistence().countByG_G(groupId, guestbookId);
	}

	/**
	 * Returns the number of guestbook entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the number of matching guestbook entries that the user has permission to view
	 */
	public static int filterCountByG_G(long groupId, long guestbookId) {
		return getPersistence().filterCountByG_G(groupId, guestbookId);
	}

	/**
	 * Caches the guestbook entry in the entity cache if it is enabled.
	 *
	 * @param guestbookEntry the guestbook entry
	 */
	public static void cacheResult(GuestbookEntry guestbookEntry) {
		getPersistence().cacheResult(guestbookEntry);
	}

	/**
	 * Caches the guestbook entries in the entity cache if it is enabled.
	 *
	 * @param guestbookEntries the guestbook entries
	 */
	public static void cacheResult(List<GuestbookEntry> guestbookEntries) {
		getPersistence().cacheResult(guestbookEntries);
	}

	/**
	 * Creates a new guestbook entry with the primary key. Does not add the guestbook entry to the database.
	 *
	 * @param entryId the primary key for the new guestbook entry
	 * @return the new guestbook entry
	 */
	public static GuestbookEntry create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	 * Removes the guestbook entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the guestbook entry
	 * @return the guestbook entry that was removed
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	public static GuestbookEntry remove(long entryId)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().remove(entryId);
	}

	public static GuestbookEntry updateImpl(GuestbookEntry guestbookEntry) {
		return getPersistence().updateImpl(guestbookEntry);
	}

	/**
	 * Returns the guestbook entry with the primary key or throws a <code>NoSuchGuestbookEntryException</code> if it could not be found.
	 *
	 * @param entryId the primary key of the guestbook entry
	 * @return the guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	public static GuestbookEntry findByPrimaryKey(long entryId)
		throws com.liferay.docs.guestbook.exception.
			NoSuchGuestbookEntryException {

		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	 * Returns the guestbook entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the guestbook entry
	 * @return the guestbook entry, or <code>null</code> if a guestbook entry with the primary key could not be found
	 */
	public static GuestbookEntry fetchByPrimaryKey(long entryId) {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	 * Returns all the guestbook entries.
	 *
	 * @return the guestbook entries
	 */
	public static List<GuestbookEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the guestbook entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @return the range of guestbook entries
	 */
	public static List<GuestbookEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the guestbook entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of guestbook entries
	 */
	public static List<GuestbookEntry> findAll(
		int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the guestbook entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>GuestbookEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of guestbook entries
	 */
	public static List<GuestbookEntry> findAll(
		int start, int end, OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the guestbook entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of guestbook entries.
	 *
	 * @return the number of guestbook entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static GuestbookEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<GuestbookEntryPersistence, GuestbookEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			GuestbookEntryPersistence.class);

		ServiceTracker<GuestbookEntryPersistence, GuestbookEntryPersistence>
			serviceTracker =
				new ServiceTracker
					<GuestbookEntryPersistence, GuestbookEntryPersistence>(
						bundle.getBundleContext(),
						GuestbookEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}