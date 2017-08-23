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

import aQute.bnd.annotation.ProviderType;

import com.liferay.docs.guestbook.model.Entry;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the entry service. This utility wraps {@link com.liferay.docs.guestbook.service.persistence.impl.EntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author liferay
 * @see EntryPersistence
 * @see com.liferay.docs.guestbook.service.persistence.impl.EntryPersistenceImpl
 * @generated
 */
@ProviderType
public class EntryUtil {
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
	public static void clearCache(Entry entry) {
		getPersistence().clearCache(entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Entry update(Entry entry) {
		return getPersistence().update(entry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Entry update(Entry entry, ServiceContext serviceContext) {
		return getPersistence().update(entry, serviceContext);
	}

	/**
	* Returns all the entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching entries
	*/
	public static List<Entry> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Entry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByUuid_First(java.lang.String uuid,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where uuid = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByUuid_PrevAndNext(long entryId,
		java.lang.String uuid, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(entryId, uuid, orderByComparator);
	}

	/**
	* Removes all the entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching entries
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the entry that was removed
	*/
	public static Entry removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching entries
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching entries
	*/
	public static List<Entry> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByUuid_C_PrevAndNext(long entryId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(entryId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching entries
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the entries where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the matching entries
	*/
	public static List<Entry> findByG_G(long groupId, long guestbookId) {
		return getPersistence().findByG_G(groupId, guestbookId);
	}

	/**
	* Returns a range of all the entries where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByG_G(long groupId, long guestbookId,
		int start, int end) {
		return getPersistence().findByG_G(groupId, guestbookId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByG_G(long groupId, long guestbookId,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findByG_G(groupId, guestbookId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the entries where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByG_G(long groupId, long guestbookId,
		int start, int end, OrderByComparator<Entry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_G(groupId, guestbookId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByG_G_First(long groupId, long guestbookId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByG_G_First(groupId, guestbookId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByG_G_First(long groupId, long guestbookId,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByG_G_First(groupId, guestbookId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByG_G_Last(long groupId, long guestbookId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByG_G_Last(groupId, guestbookId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByG_G_Last(long groupId, long guestbookId,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByG_G_Last(groupId, guestbookId, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByG_G_PrevAndNext(long entryId, long groupId,
		long guestbookId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByG_G_PrevAndNext(entryId, groupId, guestbookId,
			orderByComparator);
	}

	/**
	* Returns all the entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the matching entries that the user has permission to view
	*/
	public static List<Entry> filterFindByG_G(long groupId, long guestbookId) {
		return getPersistence().filterFindByG_G(groupId, guestbookId);
	}

	/**
	* Returns a range of all the entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries that the user has permission to view
	*/
	public static List<Entry> filterFindByG_G(long groupId, long guestbookId,
		int start, int end) {
		return getPersistence().filterFindByG_G(groupId, guestbookId, start, end);
	}

	/**
	* Returns an ordered range of all the entries that the user has permissions to view where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries that the user has permission to view
	*/
	public static List<Entry> filterFindByG_G(long groupId, long guestbookId,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .filterFindByG_G(groupId, guestbookId, start, end,
			orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set of entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] filterFindByG_G_PrevAndNext(long entryId,
		long groupId, long guestbookId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByG_G_PrevAndNext(entryId, groupId, guestbookId,
			orderByComparator);
	}

	/**
	* Removes all the entries where groupId = &#63; and guestbookId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	*/
	public static void removeByG_G(long groupId, long guestbookId) {
		getPersistence().removeByG_G(groupId, guestbookId);
	}

	/**
	* Returns the number of entries where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the number of matching entries
	*/
	public static int countByG_G(long groupId, long guestbookId) {
		return getPersistence().countByG_G(groupId, guestbookId);
	}

	/**
	* Returns the number of entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the number of matching entries that the user has permission to view
	*/
	public static int filterCountByG_G(long groupId, long guestbookId) {
		return getPersistence().filterCountByG_G(groupId, guestbookId);
	}

	/**
	* Returns all the entries where status = &#63;.
	*
	* @param status the status
	* @return the matching entries
	*/
	public static List<Entry> findByStatus(int status) {
		return getPersistence().findByStatus(status);
	}

	/**
	* Returns a range of all the entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByStatus(int status, int start, int end) {
		return getPersistence().findByStatus(status, start, end);
	}

	/**
	* Returns an ordered range of all the entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByStatus(int status, int start, int end,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByStatus(int status, int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByStatus_First(int status,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByStatus_First(int status,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByStatus_Last(int status,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByStatus_Last(int status,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where status = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByStatus_PrevAndNext(long entryId, int status,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByStatus_PrevAndNext(entryId, status, orderByComparator);
	}

	/**
	* Removes all the entries where status = &#63; from the database.
	*
	* @param status the status
	*/
	public static void removeByStatus(int status) {
		getPersistence().removeByStatus(status);
	}

	/**
	* Returns the number of entries where status = &#63;.
	*
	* @param status the status
	* @return the number of matching entries
	*/
	public static int countByStatus(int status) {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns all the entries where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching entries
	*/
	public static List<Entry> findByG_S(long groupId, int status) {
		return getPersistence().findByG_S(groupId, status);
	}

	/**
	* Returns a range of all the entries where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByG_S(long groupId, int status, int start,
		int end) {
		return getPersistence().findByG_S(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the entries where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entries where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator<Entry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByG_S_First(long groupId, int status,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByG_S_First(long groupId, int status,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByG_S_Last(long groupId, int status,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByG_S_Last(long groupId, int status,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByG_S_PrevAndNext(long entryId, long groupId,
		int status, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .findByG_S_PrevAndNext(entryId, groupId, status,
			orderByComparator);
	}

	/**
	* Returns all the entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching entries that the user has permission to view
	*/
	public static List<Entry> filterFindByG_S(long groupId, int status) {
		return getPersistence().filterFindByG_S(groupId, status);
	}

	/**
	* Returns a range of all the entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries that the user has permission to view
	*/
	public static List<Entry> filterFindByG_S(long groupId, int status,
		int start, int end) {
		return getPersistence().filterFindByG_S(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the entries that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries that the user has permission to view
	*/
	public static List<Entry> filterFindByG_S(long groupId, int status,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .filterFindByG_S(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set of entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] filterFindByG_S_PrevAndNext(long entryId,
		long groupId, int status, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByG_S_PrevAndNext(entryId, groupId, status,
			orderByComparator);
	}

	/**
	* Removes all the entries where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public static void removeByG_S(long groupId, int status) {
		getPersistence().removeByG_S(groupId, status);
	}

	/**
	* Returns the number of entries where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching entries
	*/
	public static int countByG_S(long groupId, int status) {
		return getPersistence().countByG_S(groupId, status);
	}

	/**
	* Returns the number of entries that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching entries that the user has permission to view
	*/
	public static int filterCountByG_S(long groupId, int status) {
		return getPersistence().filterCountByG_S(groupId, status);
	}

	/**
	* Caches the entry in the entity cache if it is enabled.
	*
	* @param entry the entry
	*/
	public static void cacheResult(Entry entry) {
		getPersistence().cacheResult(entry);
	}

	/**
	* Caches the entries in the entity cache if it is enabled.
	*
	* @param entries the entries
	*/
	public static void cacheResult(List<Entry> entries) {
		getPersistence().cacheResult(entries);
	}

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public static Entry create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry remove(long entryId)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence().remove(entryId);
	}

	public static Entry updateImpl(Entry entry) {
		return getPersistence().updateImpl(entry);
	}

	/**
	* Returns the entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry findByPrimaryKey(long entryId)
		throws com.liferay.docs.guestbook.exception.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry, or <code>null</code> if a entry with the primary key could not be found
	*/
	public static Entry fetchByPrimaryKey(long entryId) {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	public static java.util.Map<java.io.Serializable, Entry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the entries.
	*
	* @return the entries
	*/
	public static List<Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	*/
	public static List<Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entries
	*/
	public static List<Entry> findAll(int start, int end,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of entries
	*/
	public static List<Entry> findAll(int start, int end,
		OrderByComparator<Entry> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntryPersistence, EntryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EntryPersistence.class);
}