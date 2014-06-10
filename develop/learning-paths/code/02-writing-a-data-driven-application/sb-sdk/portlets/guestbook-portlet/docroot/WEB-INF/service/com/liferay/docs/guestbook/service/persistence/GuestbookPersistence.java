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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the guestbook service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Rich Sezov
 * @see GuestbookPersistenceImpl
 * @see GuestbookUtil
 * @generated
 */
public interface GuestbookPersistence extends BasePersistence<Guestbook> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GuestbookUtil} to access the guestbook persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the guestbooks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Guestbook findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Guestbook fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Guestbook findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last guestbook in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Guestbook fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.docs.guestbook.model.Guestbook[] findByGroupId_PrevAndNext(
		long guestbookId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the guestbooks where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of guestbooks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the guestbook in the entity cache if it is enabled.
	*
	* @param guestbook the guestbook
	*/
	public void cacheResult(
		com.liferay.docs.guestbook.model.Guestbook guestbook);

	/**
	* Caches the guestbooks in the entity cache if it is enabled.
	*
	* @param guestbooks the guestbooks
	*/
	public void cacheResult(
		java.util.List<com.liferay.docs.guestbook.model.Guestbook> guestbooks);

	/**
	* Creates a new guestbook with the primary key. Does not add the guestbook to the database.
	*
	* @param guestbookId the primary key for the new guestbook
	* @return the new guestbook
	*/
	public com.liferay.docs.guestbook.model.Guestbook create(long guestbookId);

	/**
	* Removes the guestbook with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook that was removed
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Guestbook remove(long guestbookId)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.docs.guestbook.model.Guestbook updateImpl(
		com.liferay.docs.guestbook.model.Guestbook guestbook)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the guestbook with the primary key or throws a {@link com.liferay.docs.guestbook.NoSuchGuestbookException} if it could not be found.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook
	* @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Guestbook findByPrimaryKey(
		long guestbookId)
		throws com.liferay.docs.guestbook.NoSuchGuestbookException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the guestbook with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param guestbookId the primary key of the guestbook
	* @return the guestbook, or <code>null</code> if a guestbook with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.docs.guestbook.model.Guestbook fetchByPrimaryKey(
		long guestbookId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the guestbooks.
	*
	* @return the guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.docs.guestbook.model.Guestbook> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the guestbooks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of guestbooks.
	*
	* @return the number of guestbooks
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}