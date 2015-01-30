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

import com.liferay.docs.guestbook.model.Guestbook;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the guestbook service. This utility wraps {@link GuestbookPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Rich Sezov
 * @see GuestbookPersistence
 * @see GuestbookPersistenceImpl
 * @generated
 */
public class GuestbookUtil {
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
	public static void clearCache(Guestbook guestbook) {
		getPersistence().clearCache(guestbook);
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
	public static List<Guestbook> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Guestbook> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Guestbook> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Guestbook update(Guestbook guestbook)
		throws SystemException {
		return getPersistence().update(guestbook);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Guestbook update(Guestbook guestbook,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(guestbook, serviceContext);
	}

	/**
	* Returns all the guestbooks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the guestbooks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the guestbooks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first guestbook in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first guestbook in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last guestbook in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last guestbook in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set where uuid = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook[] findByUuid_PrevAndNext(
		long guestbookId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(guestbookId, uuid, orderByComparator);
	}

	/**
	* Removes all the guestbooks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of guestbooks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the guestbook where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.docs.guestbook.NoSuchGuestbookException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the guestbook where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the guestbook where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the guestbook where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the guestbook that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of guestbooks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook[] findByUuid_C_PrevAndNext(
		long guestbookId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(guestbookId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the guestbooks where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the guestbooks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the guestbooks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the guestbooks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set where groupId = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook[] findByGroupId_PrevAndNext(
		long guestbookId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(guestbookId, groupId,
			orderByComparator);
	}

	/**
	* Returns all the guestbooks that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching guestbooks that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	* Returns a range of all the guestbooks that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the guestbooks that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set of guestbooks that the user has permission to view where groupId = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook[] filterFindByGroupId_PrevAndNext(
		long guestbookId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGroupId_PrevAndNext(guestbookId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the guestbooks where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of guestbooks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of guestbooks that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching guestbooks that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	* Returns all the guestbooks where name = &#63;.
	*
	* @param name the name
	* @return the matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGuestbookName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGuestbookName(name);
	}

	/**
	* Returns a range of all the guestbooks where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGuestbookName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGuestbookName(name, start, end);
	}

	/**
	* Returns an ordered range of all the guestbooks where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGuestbookName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGuestbookName(name, start, end, orderByComparator);
	}

	/**
	* Returns the first guestbook in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByGuestbookName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGuestbookName_First(name, orderByComparator);
	}

	/**
	* Returns the first guestbook in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByGuestbookName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGuestbookName_First(name, orderByComparator);
	}

	/**
	* Returns the last guestbook in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByGuestbookName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGuestbookName_Last(name, orderByComparator);
	}

	/**
	* Returns the last guestbook in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByGuestbookName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGuestbookName_Last(name, orderByComparator);
	}

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set where name = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook[] findByGuestbookName_PrevAndNext(
		long guestbookId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGuestbookName_PrevAndNext(guestbookId, name,
			orderByComparator);
	}

	/**
	* Removes all the guestbooks where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGuestbookName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGuestbookName(name);
	}

	/**
	* Returns the number of guestbooks where name = &#63;.
	*
	* @param name the name
	* @return the number of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGuestbookName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGuestbookName(name);
	}

	/**
	* Caches the guestbook in the entity cache if it is enabled.
	*
	* @param guestbook the guestbook
	*/
	public static void cacheResult(
		com.liferay.docs.guestbook.model.Guestbook guestbook) {
		getPersistence().cacheResult(guestbook);
	}

	/**
	* Caches the guestbooks in the entity cache if it is enabled.
	*
	* @param guestbooks the guestbooks
	*/
	public static void cacheResult(
		java.util.List<com.liferay.docs.guestbook.model.Guestbook> guestbooks) {
		getPersistence().cacheResult(guestbooks);
	}

	/**
	* Creates a new guestbook with the primary key. Does not add the guestbook to the database.
	*
	* @param guestbookId the primary key for the new guestbook
	* @return the new guestbook
	*/
	public static com.liferay.docs.guestbook.model.Guestbook create(
		long guestbookId) {
		return getPersistence().create(guestbookId);
	}

	/**
	* Removes the guestbook with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook that was removed
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook remove(
		long guestbookId)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(guestbookId);
	}

	public static com.liferay.docs.guestbook.model.Guestbook updateImpl(
		com.liferay.docs.guestbook.model.Guestbook guestbook)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(guestbook);
	}

	/**
	* Returns the guestbook with the primary key or throws a {@link com.liferay.docs.guestbook.NoSuchGuestbookException} if it could not be found.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook findByPrimaryKey(
		long guestbookId)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(guestbookId);
	}

	/**
	* Returns the guestbook with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook, or <code>null</code> if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.docs.guestbook.model.Guestbook fetchByPrimaryKey(
		long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(guestbookId);
	}

	/**
	* Returns all the guestbooks.
	*
	* @return the guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the guestbooks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.docs.guestbook.model.Guestbook> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the guestbooks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of guestbooks.
	*
	* @return the number of guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static GuestbookPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (GuestbookPersistence)PortletBeanLocatorUtil.locate(com.liferay.docs.guestbook.service.ClpSerializer.getServletContextName(),
					GuestbookPersistence.class.getName());

			ReferenceRegistry.registerReference(GuestbookUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(GuestbookPersistence persistence) {
	}

	private static GuestbookPersistence _persistence;
}