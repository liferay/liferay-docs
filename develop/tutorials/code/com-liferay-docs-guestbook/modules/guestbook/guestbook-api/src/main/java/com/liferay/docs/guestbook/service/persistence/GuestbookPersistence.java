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

import com.liferay.docs.guestbook.exception.NoSuchGuestbookException;
import com.liferay.docs.guestbook.model.Guestbook;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the guestbook service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author liferay
 * @see com.liferay.docs.guestbook.service.persistence.impl.GuestbookPersistenceImpl
 * @see GuestbookUtil
 * @generated
 */
@ProviderType
public interface GuestbookPersistence extends BasePersistence<Guestbook> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GuestbookUtil} to access the guestbook persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the guestbooks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching guestbooks
	*/
	public java.util.List<Guestbook> findByUuid(String uuid);

	/**
	* Returns a range of all the guestbooks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the guestbooks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns an ordered range of all the guestbooks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first guestbook in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook
	* @throws NoSuchGuestbookException if a matching guestbook could not be found
	*/
	public Guestbook findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Returns the first guestbook in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public Guestbook fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns the last guestbook in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook
	* @throws NoSuchGuestbookException if a matching guestbook could not be found
	*/
	public Guestbook findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Returns the last guestbook in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public Guestbook fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set where uuid = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws NoSuchGuestbookException if a guestbook with the primary key could not be found
	*/
	public Guestbook[] findByUuid_PrevAndNext(long guestbookId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Removes all the guestbooks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of guestbooks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching guestbooks
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the guestbook where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchGuestbookException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching guestbook
	* @throws NoSuchGuestbookException if a matching guestbook could not be found
	*/
	public Guestbook findByUUID_G(String uuid, long groupId)
		throws NoSuchGuestbookException;

	/**
	* Returns the guestbook where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public Guestbook fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the guestbook where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public Guestbook fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the guestbook where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the guestbook that was removed
	*/
	public Guestbook removeByUUID_G(String uuid, long groupId)
		throws NoSuchGuestbookException;

	/**
	* Returns the number of guestbooks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching guestbooks
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching guestbooks
	*/
	public java.util.List<Guestbook> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns an ordered range of all the guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook
	* @throws NoSuchGuestbookException if a matching guestbook could not be found
	*/
	public Guestbook findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Returns the first guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public Guestbook fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns the last guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook
	* @throws NoSuchGuestbookException if a matching guestbook could not be found
	*/
	public Guestbook findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Returns the last guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public Guestbook fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws NoSuchGuestbookException if a guestbook with the primary key could not be found
	*/
	public Guestbook[] findByUuid_C_PrevAndNext(long guestbookId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Removes all the guestbooks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of guestbooks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching guestbooks
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the guestbooks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching guestbooks
	*/
	public java.util.List<Guestbook> findByGroupId(long groupId);

	/**
	* Returns a range of all the guestbooks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the guestbooks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns an ordered range of all the guestbooks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching guestbooks
	*/
	public java.util.List<Guestbook> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook
	* @throws NoSuchGuestbookException if a matching guestbook could not be found
	*/
	public Guestbook findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Returns the first guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public Guestbook fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns the last guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook
	* @throws NoSuchGuestbookException if a matching guestbook could not be found
	*/
	public Guestbook findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Returns the last guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	*/
	public Guestbook fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set where groupId = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws NoSuchGuestbookException if a guestbook with the primary key could not be found
	*/
	public Guestbook[] findByGroupId_PrevAndNext(long guestbookId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Returns all the guestbooks that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching guestbooks that the user has permission to view
	*/
	public java.util.List<Guestbook> filterFindByGroupId(long groupId);

	/**
	* Returns a range of all the guestbooks that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of matching guestbooks that the user has permission to view
	*/
	public java.util.List<Guestbook> filterFindByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the guestbooks that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching guestbooks that the user has permission to view
	*/
	public java.util.List<Guestbook> filterFindByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns the guestbooks before and after the current guestbook in the ordered set of guestbooks that the user has permission to view where groupId = &#63;.
	*
	* @param guestbookId the primary key of the current guestbook
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook
	* @throws NoSuchGuestbookException if a guestbook with the primary key could not be found
	*/
	public Guestbook[] filterFindByGroupId_PrevAndNext(long guestbookId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator)
		throws NoSuchGuestbookException;

	/**
	* Removes all the guestbooks where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of guestbooks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching guestbooks
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of guestbooks that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching guestbooks that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Caches the guestbook in the entity cache if it is enabled.
	*
	* @param guestbook the guestbook
	*/
	public void cacheResult(Guestbook guestbook);

	/**
	* Caches the guestbooks in the entity cache if it is enabled.
	*
	* @param guestbooks the guestbooks
	*/
	public void cacheResult(java.util.List<Guestbook> guestbooks);

	/**
	* Creates a new guestbook with the primary key. Does not add the guestbook to the database.
	*
	* @param guestbookId the primary key for the new guestbook
	* @return the new guestbook
	*/
	public Guestbook create(long guestbookId);

	/**
	* Removes the guestbook with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook that was removed
	* @throws NoSuchGuestbookException if a guestbook with the primary key could not be found
	*/
	public Guestbook remove(long guestbookId) throws NoSuchGuestbookException;

	public Guestbook updateImpl(Guestbook guestbook);

	/**
	* Returns the guestbook with the primary key or throws a {@link NoSuchGuestbookException} if it could not be found.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook
	* @throws NoSuchGuestbookException if a guestbook with the primary key could not be found
	*/
	public Guestbook findByPrimaryKey(long guestbookId)
		throws NoSuchGuestbookException;

	/**
	* Returns the guestbook with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook, or <code>null</code> if a guestbook with the primary key could not be found
	*/
	public Guestbook fetchByPrimaryKey(long guestbookId);

	@Override
	public java.util.Map<java.io.Serializable, Guestbook> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the guestbooks.
	*
	* @return the guestbooks
	*/
	public java.util.List<Guestbook> findAll();

	/**
	* Returns a range of all the guestbooks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @return the range of guestbooks
	*/
	public java.util.List<Guestbook> findAll(int start, int end);

	/**
	* Returns an ordered range of all the guestbooks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of guestbooks
	*/
	public java.util.List<Guestbook> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator);

	/**
	* Returns an ordered range of all the guestbooks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of guestbooks
	* @param end the upper bound of the range of guestbooks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of guestbooks
	*/
	public java.util.List<Guestbook> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Guestbook> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the guestbooks from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of guestbooks.
	*
	* @return the number of guestbooks
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}