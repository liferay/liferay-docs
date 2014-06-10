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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Rich Sezov
 * @see EntryPersistenceImpl
 * @see EntryUtil
 * @generated
 */
public interface EntryPersistence extends BasePersistence<Entry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntryUtil} to access the entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the entries where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_G(
		long groupId, long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_G(
		long groupId, long guestbookId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.docs.guestbook.model.Entry> findByG_G(
		long groupId, long guestbookId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.docs.guestbook.model.Entry findByG_G_First(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Entry fetchByG_G_First(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.docs.guestbook.model.Entry findByG_G_Last(long groupId,
		long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Entry fetchByG_G_Last(
		long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.docs.guestbook.model.Entry[] findByG_G_PrevAndNext(
		long entryId, long groupId, long guestbookId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries where groupId = &#63; and guestbookId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_G(long groupId, long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries where groupId = &#63; and guestbookId = &#63;.
	*
	* @param groupId the group ID
	* @param guestbookId the guestbook ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_G(long groupId, long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the entry in the entity cache if it is enabled.
	*
	* @param entry the entry
	*/
	public void cacheResult(com.liferay.docs.guestbook.model.Entry entry);

	/**
	* Caches the entries in the entity cache if it is enabled.
	*
	* @param entries the entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.docs.guestbook.model.Entry> entries);

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public com.liferay.docs.guestbook.model.Entry create(long entryId);

	/**
	* Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Entry remove(long entryId)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.docs.guestbook.model.Entry updateImpl(
		com.liferay.docs.guestbook.model.Entry entry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entry with the primary key or throws a {@link com.liferay.docs.guestbook.NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Entry findByPrimaryKey(long entryId)
		throws com.liferay.docs.guestbook.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry, or <code>null</code> if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Entry fetchByPrimaryKey(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries.
	*
	* @return the entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.guestbook.model.Entry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.docs.guestbook.model.Entry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.docs.guestbook.model.Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}