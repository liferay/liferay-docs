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

package com.liferay.docs.guestbook.service.persistence;

import com.liferay.docs.guestbook.model.Entry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the entry service. This utility wraps {@link EntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Rich Sezov
 * @see EntryPersistence
 * @see EntryPersistenceImpl
 * @generated
 */
public class EntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Entry entry) {
		getPersistence().clearCache(entry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Entry update(Entry entry) throws SystemException {
		return getPersistence().update(entry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Entry update(Entry entry, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(entry, serviceContext);
	}

	/**
	* Returns all the entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where uuid = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry[] findByUuid_PrevAndNext(
		long entryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(entryId, uuid, orderByComparator);
	}

	/**
	* Removes all the entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the entry where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.docs.guestbook.NoSuchEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry[] findByUuid_C_PrevAndNext(
		long entryId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(entryId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the entries where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_G(
		long groupId, long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_G(groupId, guestbookId);
	}

	/**
	* Returns a range of all the entries where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_G(
		long groupId, long guestbookId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_G(groupId, guestbookId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_G(
		long groupId, long guestbookId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_G(groupId, guestbookId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByG_G_First(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByG_G_First(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByG_G_Last(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByG_G_Last(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry[] findByG_G_PrevAndNext(
		long entryId, long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> filterFindByG_G(
		long groupId, long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_G(groupId, guestbookId);
	}

	/**
	* Returns a range of all the entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> filterFindByG_G(
		long groupId, long guestbookId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_G(groupId, guestbookId, start, end);
	}

	/**
	* Returns an ordered range of all the entries that the user has permissions to view where groupId = &#63; and guestbookId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> filterFindByG_G(
		long groupId, long guestbookId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry[] filterFindByG_G_PrevAndNext(
		long entryId, long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_G_PrevAndNext(entryId, groupId, guestbookId,
			orderByComparator);
	}

	/**
	* Removes all the entries where groupId = &#63; and guestbookId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_G(long groupId, long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_G(groupId, guestbookId);
	}

	/**
	* Returns the number of entries where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_G(long groupId, long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_G(groupId, guestbookId);
	}

	/**
	* Returns the number of entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the number of matching entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_G(long groupId, long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_G(groupId, guestbookId);
	}

	/**
	* Returns all the entries where name = &#63;.
	*
	* @param name the name
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByEntryName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEntryName(name);
	}

	/**
	* Returns a range of all the entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByEntryName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEntryName(name, start, end);
	}

	/**
	* Returns an ordered range of all the entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByEntryName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEntryName(name, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByEntryName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEntryName_First(name, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByEntryName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEntryName_First(name, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByEntryName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEntryName_Last(name, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByEntryName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEntryName_Last(name, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where name = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry[] findByEntryName_PrevAndNext(
		long entryId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEntryName_PrevAndNext(entryId, name, orderByComparator);
	}

	/**
	* Removes all the entries where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEntryName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEntryName(name);
	}

	/**
	* Returns the number of entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEntryName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEntryName(name);
	}

	/**
	* Returns all the entries where guestbookId = &#63; and name = &#63;.
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_N(
		long guestbookId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_N(guestbookId, name);
	}

	/**
	* Returns a range of all the entries where guestbookId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_N(
		long guestbookId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_N(guestbookId, name, start, end);
	}

	/**
	* Returns an ordered range of all the entries where guestbookId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_N(
		long guestbookId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_N(guestbookId, name, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where guestbookId = &#63; and name = &#63;.
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByG_N_First(
		long guestbookId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_N_First(guestbookId, name, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where guestbookId = &#63; and name = &#63;.
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByG_N_First(
		long guestbookId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_N_First(guestbookId, name, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where guestbookId = &#63; and name = &#63;.
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByG_N_Last(
		long guestbookId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_N_Last(guestbookId, name, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where guestbookId = &#63; and name = &#63;.
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByG_N_Last(
		long guestbookId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_N_Last(guestbookId, name, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where guestbookId = &#63; and name = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param guestbookId the guestbook ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry[] findByG_N_PrevAndNext(
		long entryId, long guestbookId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_N_PrevAndNext(entryId, guestbookId, name,
			orderByComparator);
	}

	/**
	* Removes all the entries where guestbookId = &#63; and name = &#63; from the database.
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_N(long guestbookId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_N(guestbookId, name);
	}

	/**
	* Returns the number of entries where guestbookId = &#63; and name = &#63;.
	*
	* @param guestbookId the guestbook ID
	* @param name the name
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_N(long guestbookId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_N(guestbookId, name);
	}

	/**
	* Caches the entry in the entity cache if it is enabled.
	*
	* @param entry the entry
	*/
	public static void cacheResult(com.liferay.docs.guestbook.model.Entry entry) {
		getPersistence().cacheResult(entry);
	}

	/**
	* Caches the entries in the entity cache if it is enabled.
	*
	* @param entries the entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.docs.guestbook.model.Entry> entries) {
		getPersistence().cacheResult(entries);
	}

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public static com.liferay.docs.guestbook.model.Entry create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry remove(long entryId)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(entryId);
	}

	public static com.liferay.docs.guestbook.model.Entry updateImpl(
		com.liferay.docs.guestbook.model.Entry entry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(entry);
	}

	/**
	* Returns the entry with the primary key or throws a {@link com.liferay.docs.guestbook.NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry findByPrimaryKey(
		long entryId)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry, or <code>null</code> if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Entry fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	* Returns all the entries.
	*
	* @return the entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static EntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (EntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.docs.guestbook.service.ClpSerializer.getServletContextName(),
					EntryPersistence.class.getName());

			ReferenceRegistry.registerReference(EntryUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(EntryPersistence persistence) {
	}

	private static EntryPersistence _persistence;
}