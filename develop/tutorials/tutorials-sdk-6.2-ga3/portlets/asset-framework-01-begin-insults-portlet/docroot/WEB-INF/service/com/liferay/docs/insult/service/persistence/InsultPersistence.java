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

package com.liferay.docs.insult.service.persistence;

import com.liferay.docs.insult.model.Insult;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the insult service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author joebloggs
 * @see InsultPersistenceImpl
 * @see InsultUtil
 * @generated
 */
public interface InsultPersistence extends BasePersistence<Insult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InsultUtil} to access the insult persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the insults where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the insults where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @return the range of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the insults where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first insult in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first insult in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching insult, or <code>null</code> if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last insult in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last insult in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching insult, or <code>null</code> if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insults before and after the current insult in the ordered set where uuid = &#63;.
	*
	* @param insultId the primary key of the current insult
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a insult with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult[] findByUuid_PrevAndNext(
		long insultId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the insults where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insults where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insult where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.docs.insult.NoSuchInsultException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insult where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching insult, or <code>null</code> if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insult where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching insult, or <code>null</code> if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the insult where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the insult that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insults where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the insults where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the insults where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @return the range of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the insults where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first insult in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first insult in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching insult, or <code>null</code> if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last insult in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last insult in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching insult, or <code>null</code> if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insults before and after the current insult in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param insultId the primary key of the current insult
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a insult with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult[] findByUuid_C_PrevAndNext(
		long insultId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the insults where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insults where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the insults where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the insults where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @return the range of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the insults where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first insult in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first insult in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching insult, or <code>null</code> if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last insult in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last insult in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching insult, or <code>null</code> if a matching insult could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insults before and after the current insult in the ordered set where groupId = &#63;.
	*
	* @param insultId the primary key of the current insult
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a insult with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult[] findByGroupId_PrevAndNext(
		long insultId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the insults that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching insults that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the insults that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @return the range of matching insults that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the insults that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching insults that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insults before and after the current insult in the ordered set of insults that the user has permission to view where groupId = &#63;.
	*
	* @param insultId the primary key of the current insult
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a insult with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult[] filterFindByGroupId_PrevAndNext(
		long insultId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the insults where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insults where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching insults
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insults that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching insults that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the insult in the entity cache if it is enabled.
	*
	* @param insult the insult
	*/
	public void cacheResult(com.liferay.docs.insult.model.Insult insult);

	/**
	* Caches the insults in the entity cache if it is enabled.
	*
	* @param insults the insults
	*/
	public void cacheResult(
		java.util.List<com.liferay.docs.insult.model.Insult> insults);

	/**
	* Creates a new insult with the primary key. Does not add the insult to the database.
	*
	* @param insultId the primary key for the new insult
	* @return the new insult
	*/
	public com.liferay.docs.insult.model.Insult create(long insultId);

	/**
	* Removes the insult with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param insultId the primary key of the insult
	* @return the insult that was removed
	* @throws com.liferay.docs.insult.NoSuchInsultException if a insult with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult remove(long insultId)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.docs.insult.model.Insult updateImpl(
		com.liferay.docs.insult.model.Insult insult)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insult with the primary key or throws a {@link com.liferay.docs.insult.NoSuchInsultException} if it could not be found.
	*
	* @param insultId the primary key of the insult
	* @return the insult
	* @throws com.liferay.docs.insult.NoSuchInsultException if a insult with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult findByPrimaryKey(long insultId)
		throws com.liferay.docs.insult.NoSuchInsultException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the insult with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param insultId the primary key of the insult
	* @return the insult, or <code>null</code> if a insult with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.insult.model.Insult fetchByPrimaryKey(long insultId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the insults.
	*
	* @return the insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the insults.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @return the range of insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the insults.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.insult.model.impl.InsultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of insults
	* @param end the upper bound of the range of insults (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of insults
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.insult.model.Insult> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the insults from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of insults.
	*
	* @return the number of insults
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}