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

import com.liferay.docs.guestbook.NoSuchEntryException;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.impl.EntryImpl;
import com.liferay.docs.guestbook.model.impl.EntryModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Rich Sezov
 * @see EntryPersistence
 * @see EntryUtil
 * @generated
 */
public class EntryPersistenceImpl extends BasePersistenceImpl<Entry>
	implements EntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EntryUtil} to access the entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			EntryModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Entry> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Entry> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if (!Validator.equals(uuid, entry.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Entry findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByUuid_First(uuid, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByUuid_Last(uuid, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry[] findByUuid_PrevAndNext(long entryId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, entry, uuid,
					orderByComparator, true);

			array[1] = entry;

			array[2] = getByUuid_PrevAndNext(session, entry, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByUuid_PrevAndNext(Session session, Entry entry,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (Entry entry : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "entry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "entry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(entry.uuid IS NULL OR entry.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			EntryModelImpl.UUID_COLUMN_BITMASK |
			EntryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the entry where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.docs.guestbook.NoSuchEntryException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByUUID_G(String uuid, long groupId)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByUUID_G(uuid, groupId);

		if (entry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchEntryException(msg.toString());
		}

		return entry;
	}

	/**
	 * Returns the entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
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
	@Override
	public Entry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Entry) {
			Entry entry = (Entry)result;

			if (!Validator.equals(uuid, entry.getUuid()) ||
					(groupId != entry.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Entry> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Entry entry = list.get(0);

					result = entry;

					cacheResult(entry);

					if ((entry.getUuid() == null) ||
							!entry.getUuid().equals(uuid) ||
							(entry.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, entry);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Entry)result;
		}
	}

	/**
	 * Removes the entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry removeByUUID_G(String uuid, long groupId)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByUUID_G(uuid, groupId);

		return remove(entry);
	}

	/**
	 * Returns the number of entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "entry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "entry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(entry.uuid IS NULL OR entry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "entry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			EntryModelImpl.UUID_COLUMN_BITMASK |
			EntryModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Entry> findByUuid_C(String uuid, long companyId, int start,
		int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<Entry> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if (!Validator.equals(uuid, entry.getUuid()) ||
						(companyId != entry.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Entry findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
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
	@Override
	public Entry fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
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
	@Override
	public Entry fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry[] findByUuid_C_PrevAndNext(long entryId, String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, entry, uuid, companyId,
					orderByComparator, true);

			array[1] = entry;

			array[2] = getByUuid_C_PrevAndNext(session, entry, uuid, companyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByUuid_C_PrevAndNext(Session session, Entry entry,
		String uuid, long companyId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (Entry entry : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "entry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "entry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(entry.uuid IS NULL OR entry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "entry.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_G = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_G",
			new String[] { Long.class.getName(), Long.class.getName() },
			EntryModelImpl.GROUPID_COLUMN_BITMASK |
			EntryModelImpl.GUESTBOOKID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_G = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_G",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByG_G(long groupId, long guestbookId)
		throws SystemException {
		return findByG_G(groupId, guestbookId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Entry> findByG_G(long groupId, long guestbookId, int start,
		int end) throws SystemException {
		return findByG_G(groupId, guestbookId, start, end, null);
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
	@Override
	public List<Entry> findByG_G(long groupId, long guestbookId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G;
			finderArgs = new Object[] { groupId, guestbookId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_G;
			finderArgs = new Object[] {
					groupId, guestbookId,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((groupId != entry.getGroupId()) ||
						(guestbookId != entry.getGuestbookId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_G_GROUPID_2);

			query.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(guestbookId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Entry findByG_G_First(long groupId, long guestbookId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByG_G_First(groupId, guestbookId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", guestbookId=");
		msg.append(guestbookId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
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
	@Override
	public Entry fetchByG_G_First(long groupId, long guestbookId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByG_G(groupId, guestbookId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry findByG_G_Last(long groupId, long guestbookId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByG_G_Last(groupId, guestbookId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", guestbookId=");
		msg.append(guestbookId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
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
	@Override
	public Entry fetchByG_G_Last(long groupId, long guestbookId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_G(groupId, guestbookId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByG_G(groupId, guestbookId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry[] findByG_G_PrevAndNext(long entryId, long groupId,
		long guestbookId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByG_G_PrevAndNext(session, entry, groupId,
					guestbookId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByG_G_PrevAndNext(session, entry, groupId,
					guestbookId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByG_G_PrevAndNext(Session session, Entry entry,
		long groupId, long guestbookId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_G_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(guestbookId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> filterFindByG_G(long groupId, long guestbookId)
		throws SystemException {
		return filterFindByG_G(groupId, guestbookId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Entry> filterFindByG_G(long groupId, long guestbookId,
		int start, int end) throws SystemException {
		return filterFindByG_G(groupId, guestbookId, start, end, null);
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
	@Override
	public List<Entry> filterFindByG_G(long groupId, long guestbookId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_G(groupId, guestbookId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_G_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(EntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, EntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, EntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(guestbookId);

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Entry[] filterFindByG_G_PrevAndNext(long entryId, long groupId,
		long guestbookId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_G_PrevAndNext(entryId, groupId, guestbookId,
				orderByComparator);
		}

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = filterGetByG_G_PrevAndNext(session, entry, groupId,
					guestbookId, orderByComparator, true);

			array[1] = entry;

			array[2] = filterGetByG_G_PrevAndNext(session, entry, groupId,
					guestbookId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry filterGetByG_G_PrevAndNext(Session session, Entry entry,
		long groupId, long guestbookId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_G_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(EntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, EntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, EntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(guestbookId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where groupId = &#63; and guestbookId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_G(long groupId, long guestbookId)
		throws SystemException {
		for (Entry entry : findByG_G(groupId, guestbookId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_G(long groupId, long guestbookId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_G;

		Object[] finderArgs = new Object[] { groupId, guestbookId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_G_GROUPID_2);

			query.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(guestbookId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of entries that the user has permission to view where groupId = &#63; and guestbookId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @return the number of matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByG_G(long groupId, long guestbookId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_G(groupId, guestbookId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_G_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_GUESTBOOKID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(guestbookId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_G_GROUPID_2 = "entry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_G_GUESTBOOKID_2 = "entry.guestbookId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ENTRYNAME =
		new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEntryName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTRYNAME =
		new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEntryName",
			new String[] { String.class.getName() },
			EntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ENTRYNAME = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEntryName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByEntryName(String name) throws SystemException {
		return findByEntryName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Entry> findByEntryName(String name, int start, int end)
		throws SystemException {
		return findByEntryName(name, start, end, null);
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
	@Override
	public List<Entry> findByEntryName(String name, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTRYNAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ENTRYNAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if (!Validator.equals(name, entry.getName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_ENTRYNAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ENTRYNAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_ENTRYNAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Entry findByEntryName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByEntryName_First(name, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByEntryName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByEntryName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry findByEntryName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByEntryName_Last(name, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByEntryName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEntryName(name);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByEntryName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry[] findByEntryName_PrevAndNext(long entryId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByEntryName_PrevAndNext(session, entry, name,
					orderByComparator, true);

			array[1] = entry;

			array[2] = getByEntryName_PrevAndNext(session, entry, name,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByEntryName_PrevAndNext(Session session, Entry entry,
		String name, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_ENTRYNAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ENTRYNAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_ENTRYNAME_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByEntryName(String name) throws SystemException {
		for (Entry entry : findByEntryName(name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByEntryName(String name) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ENTRYNAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_ENTRYNAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ENTRYNAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_ENTRYNAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ENTRYNAME_NAME_1 = "entry.name IS NULL";
	private static final String _FINDER_COLUMN_ENTRYNAME_NAME_2 = "entry.name = ?";
	private static final String _FINDER_COLUMN_ENTRYNAME_NAME_3 = "(entry.name IS NULL OR entry.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_N = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_N",
			new String[] { Long.class.getName(), String.class.getName() },
			EntryModelImpl.GUESTBOOKID_COLUMN_BITMASK |
			EntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_N = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the entries where guestbookId = &#63; and name = &#63;.
	 *
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByG_N(long guestbookId, String name)
		throws SystemException {
		return findByG_N(guestbookId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Entry> findByG_N(long guestbookId, String name, int start,
		int end) throws SystemException {
		return findByG_N(guestbookId, name, start, end, null);
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
	@Override
	public List<Entry> findByG_N(long guestbookId, String name, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N;
			finderArgs = new Object[] { guestbookId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_N;
			finderArgs = new Object[] {
					guestbookId, name,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((guestbookId != entry.getGuestbookId()) ||
						!Validator.equals(name, entry.getName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_N_GUESTBOOKID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(guestbookId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Entry findByG_N_First(long guestbookId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByG_N_First(guestbookId, name, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("guestbookId=");
		msg.append(guestbookId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
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
	@Override
	public Entry fetchByG_N_First(long guestbookId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByG_N(guestbookId, name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry findByG_N_Last(long guestbookId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByG_N_Last(guestbookId, name, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("guestbookId=");
		msg.append(guestbookId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
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
	@Override
	public Entry fetchByG_N_Last(long guestbookId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_N(guestbookId, name);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByG_N(guestbookId, name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Entry[] findByG_N_PrevAndNext(long entryId, long guestbookId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByG_N_PrevAndNext(session, entry, guestbookId, name,
					orderByComparator, true);

			array[1] = entry;

			array[2] = getByG_N_PrevAndNext(session, entry, guestbookId, name,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByG_N_PrevAndNext(Session session, Entry entry,
		long guestbookId, String name, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_N_GUESTBOOKID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_N_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(guestbookId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where guestbookId = &#63; and name = &#63; from the database.
	 *
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_N(long guestbookId, String name)
		throws SystemException {
		for (Entry entry : findByG_N(guestbookId, name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where guestbookId = &#63; and name = &#63;.
	 *
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_N(long guestbookId, String name)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_N;

		Object[] finderArgs = new Object[] { guestbookId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_N_GUESTBOOKID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(guestbookId);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_N_GUESTBOOKID_2 = "entry.guestbookId = ? AND ";
	private static final String _FINDER_COLUMN_G_N_NAME_1 = "entry.name IS NULL";
	private static final String _FINDER_COLUMN_G_N_NAME_2 = "entry.name = ?";
	private static final String _FINDER_COLUMN_G_N_NAME_3 = "(entry.name IS NULL OR entry.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_G_N = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_G_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_N = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_G_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			EntryModelImpl.GROUPID_COLUMN_BITMASK |
			EntryModelImpl.GUESTBOOKID_COLUMN_BITMASK |
			EntryModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_G_N = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_G_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the entries where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByG_G_N(long groupId, long guestbookId, String name)
		throws SystemException {
		return findByG_G_N(groupId, guestbookId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByG_G_N(long groupId, long guestbookId, String name,
		int start, int end) throws SystemException {
		return findByG_G_N(groupId, guestbookId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByG_G_N(long groupId, long guestbookId, String name,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_N;
			finderArgs = new Object[] { groupId, guestbookId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_G_N;
			finderArgs = new Object[] {
					groupId, guestbookId, name,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((groupId != entry.getGroupId()) ||
						(guestbookId != entry.getGuestbookId()) ||
						!Validator.equals(name, entry.getName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_G_N_GROUPID_2);

			query.append(_FINDER_COLUMN_G_G_N_GUESTBOOKID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_G_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_G_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_G_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(guestbookId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByG_G_N_First(long groupId, long guestbookId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByG_G_N_First(groupId, guestbookId, name,
				orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", guestbookId=");
		msg.append(guestbookId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByG_G_N_First(long groupId, long guestbookId,
		String name, OrderByComparator orderByComparator)
		throws SystemException {
		List<Entry> list = findByG_G_N(groupId, guestbookId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByG_G_N_Last(long groupId, long guestbookId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByG_G_N_Last(groupId, guestbookId, name,
				orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", guestbookId=");
		msg.append(guestbookId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByG_G_N_Last(long groupId, long guestbookId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_G_N(groupId, guestbookId, name);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByG_G_N(groupId, guestbookId, name, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByG_G_N_PrevAndNext(long entryId, long groupId,
		long guestbookId, String name, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByG_G_N_PrevAndNext(session, entry, groupId,
					guestbookId, name, orderByComparator, true);

			array[1] = entry;

			array[2] = getByG_G_N_PrevAndNext(session, entry, groupId,
					guestbookId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByG_G_N_PrevAndNext(Session session, Entry entry,
		long groupId, long guestbookId, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_G_N_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_N_GUESTBOOKID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_G_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_G_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_G_N_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(guestbookId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the entries that the user has permission to view where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @return the matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> filterFindByG_G_N(long groupId, long guestbookId,
		String name) throws SystemException {
		return filterFindByG_G_N(groupId, guestbookId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries that the user has permission to view where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> filterFindByG_G_N(long groupId, long guestbookId,
		String name, int start, int end) throws SystemException {
		return filterFindByG_G_N(groupId, guestbookId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries that the user has permissions to view where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> filterFindByG_G_N(long groupId, long guestbookId,
		String name, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_G_N(groupId, guestbookId, name, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_G_N_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_N_GUESTBOOKID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_G_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_G_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_G_N_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(EntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, EntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, EntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(guestbookId);

			if (bindName) {
				qPos.add(name);
			}

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set of entries that the user has permission to view where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] filterFindByG_G_N_PrevAndNext(long entryId, long groupId,
		long guestbookId, String name, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_G_N_PrevAndNext(entryId, groupId, guestbookId, name,
				orderByComparator);
		}

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = filterGetByG_G_N_PrevAndNext(session, entry, groupId,
					guestbookId, name, orderByComparator, true);

			array[1] = entry;

			array[2] = filterGetByG_G_N_PrevAndNext(session, entry, groupId,
					guestbookId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry filterGetByG_G_N_PrevAndNext(Session session, Entry entry,
		long groupId, long guestbookId, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_G_N_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_N_GUESTBOOKID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_G_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_G_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_G_N_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(EntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, EntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, EntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(guestbookId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where groupId = &#63; and guestbookId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_G_N(long groupId, long guestbookId, String name)
		throws SystemException {
		for (Entry entry : findByG_G_N(groupId, guestbookId, name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_G_N(long groupId, long guestbookId, String name)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_G_N;

		Object[] finderArgs = new Object[] { groupId, guestbookId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_G_N_GROUPID_2);

			query.append(_FINDER_COLUMN_G_G_N_GUESTBOOKID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_G_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_G_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_G_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(guestbookId);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of entries that the user has permission to view where groupId = &#63; and guestbookId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param name the name
	 * @return the number of matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByG_G_N(long groupId, long guestbookId, String name)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_G_N(groupId, guestbookId, name);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_G_N_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_N_GUESTBOOKID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_G_G_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_G_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_G_G_N_NAME_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(guestbookId);

			if (bindName) {
				qPos.add(name);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_G_N_GROUPID_2 = "entry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_G_N_GUESTBOOKID_2 = "entry.guestbookId = ? AND ";
	private static final String _FINDER_COLUMN_G_G_N_NAME_1 = "entry.name IS NULL";
	private static final String _FINDER_COLUMN_G_G_N_NAME_2 = "entry.name = ?";
	private static final String _FINDER_COLUMN_G_G_N_NAME_3 = "(entry.name IS NULL OR entry.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_G_S = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_S = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			EntryModelImpl.GROUPID_COLUMN_BITMASK |
			EntryModelImpl.GUESTBOOKID_COLUMN_BITMASK |
			EntryModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_G_S = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the entries where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByG_G_S(long groupId, long guestbookId, int status)
		throws SystemException {
		return findByG_G_S(groupId, guestbookId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByG_G_S(long groupId, long guestbookId, int status,
		int start, int end) throws SystemException {
		return findByG_G_S(groupId, guestbookId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByG_G_S(long groupId, long guestbookId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_S;
			finderArgs = new Object[] { groupId, guestbookId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_G_S;
			finderArgs = new Object[] {
					groupId, guestbookId, status,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((groupId != entry.getGroupId()) ||
						(guestbookId != entry.getGuestbookId()) ||
						(status != entry.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_G_S_GUESTBOOKID_2);

			query.append(_FINDER_COLUMN_G_G_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(guestbookId);

				qPos.add(status);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first entry in the ordered set where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByG_G_S_First(long groupId, long guestbookId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByG_G_S_First(groupId, guestbookId, status,
				orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", guestbookId=");
		msg.append(guestbookId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByG_G_S_First(long groupId, long guestbookId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByG_G_S(groupId, guestbookId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByG_G_S_Last(long groupId, long guestbookId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByG_G_S_Last(groupId, guestbookId, status,
				orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", guestbookId=");
		msg.append(guestbookId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByG_G_S_Last(long groupId, long guestbookId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_G_S(groupId, guestbookId, status);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByG_G_S(groupId, guestbookId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByG_G_S_PrevAndNext(long entryId, long groupId,
		long guestbookId, int status, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByG_G_S_PrevAndNext(session, entry, groupId,
					guestbookId, status, orderByComparator, true);

			array[1] = entry;

			array[2] = getByG_G_S_PrevAndNext(session, entry, groupId,
					guestbookId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByG_G_S_PrevAndNext(Session session, Entry entry,
		long groupId, long guestbookId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_S_GUESTBOOKID_2);

		query.append(_FINDER_COLUMN_G_G_S_STATUS_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(guestbookId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the entries that the user has permission to view where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @return the matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> filterFindByG_G_S(long groupId, long guestbookId,
		int status) throws SystemException {
		return filterFindByG_G_S(groupId, guestbookId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries that the user has permission to view where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> filterFindByG_G_S(long groupId, long guestbookId,
		int status, int start, int end) throws SystemException {
		return filterFindByG_G_S(groupId, guestbookId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries that the user has permissions to view where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.docs.guestbook.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> filterFindByG_G_S(long groupId, long guestbookId,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_G_S(groupId, guestbookId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_S_GUESTBOOKID_2);

		query.append(_FINDER_COLUMN_G_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(EntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, EntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, EntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(guestbookId);

			qPos.add(status);

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set of entries that the user has permission to view where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] filterFindByG_G_S_PrevAndNext(long entryId, long groupId,
		long guestbookId, int status, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_G_S_PrevAndNext(entryId, groupId, guestbookId,
				status, orderByComparator);
		}

		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = filterGetByG_G_S_PrevAndNext(session, entry, groupId,
					guestbookId, status, orderByComparator, true);

			array[1] = entry;

			array[2] = filterGetByG_G_S_PrevAndNext(session, entry, groupId,
					guestbookId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry filterGetByG_G_S_PrevAndNext(Session session, Entry entry,
		long groupId, long guestbookId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_S_GUESTBOOKID_2);

		query.append(_FINDER_COLUMN_G_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(EntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, EntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, EntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(guestbookId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where groupId = &#63; and guestbookId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_G_S(long groupId, long guestbookId, int status)
		throws SystemException {
		for (Entry entry : findByG_G_S(groupId, guestbookId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_G_S(long groupId, long guestbookId, int status)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_G_S;

		Object[] finderArgs = new Object[] { groupId, guestbookId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_G_S_GUESTBOOKID_2);

			query.append(_FINDER_COLUMN_G_G_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(guestbookId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of entries that the user has permission to view where groupId = &#63; and guestbookId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param guestbookId the guestbook ID
	 * @param status the status
	 * @return the number of matching entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByG_G_S(long groupId, long guestbookId, int status)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_G_S(groupId, guestbookId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_G_S_GUESTBOOKID_2);

		query.append(_FINDER_COLUMN_G_G_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Entry.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(guestbookId);

			qPos.add(status);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_G_S_GROUPID_2 = "entry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_G_S_GUESTBOOKID_2 = "entry.guestbookId = ? AND ";
	private static final String _FINDER_COLUMN_G_G_S_STATUS_2 = "entry.status = ?";

	public EntryPersistenceImpl() {
		setModelClass(Entry.class);
	}

	/**
	 * Caches the entry in the entity cache if it is enabled.
	 *
	 * @param entry the entry
	 */
	@Override
	public void cacheResult(Entry entry) {
		EntityCacheUtil.putResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey(), entry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { entry.getUuid(), entry.getGroupId() }, entry);

		entry.resetOriginalValues();
	}

	/**
	 * Caches the entries in the entity cache if it is enabled.
	 *
	 * @param entries the entries
	 */
	@Override
	public void cacheResult(List<Entry> entries) {
		for (Entry entry : entries) {
			if (EntityCacheUtil.getResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
						EntryImpl.class, entry.getPrimaryKey()) == null) {
				cacheResult(entry);
			}
			else {
				entry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(EntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(EntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Entry entry) {
		EntityCacheUtil.removeResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(entry);
	}

	@Override
	public void clearCache(List<Entry> entries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Entry entry : entries) {
			EntityCacheUtil.removeResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
				EntryImpl.class, entry.getPrimaryKey());

			clearUniqueFindersCache(entry);
		}
	}

	protected void cacheUniqueFindersCache(Entry entry) {
		if (entry.isNew()) {
			Object[] args = new Object[] { entry.getUuid(), entry.getGroupId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args, entry);
		}
		else {
			EntryModelImpl entryModelImpl = (EntryModelImpl)entry;

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { entry.getUuid(), entry.getGroupId() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					entry);
			}
		}
	}

	protected void clearUniqueFindersCache(Entry entry) {
		EntryModelImpl entryModelImpl = (EntryModelImpl)entry;

		Object[] args = new Object[] { entry.getUuid(), entry.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((entryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					entryModelImpl.getOriginalUuid(),
					entryModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new entry with the primary key. Does not add the entry to the database.
	 *
	 * @param entryId the primary key for the new entry
	 * @return the new entry
	 */
	@Override
	public Entry create(long entryId) {
		Entry entry = new EntryImpl();

		entry.setNew(true);
		entry.setPrimaryKey(entryId);

		String uuid = PortalUUIDUtil.generate();

		entry.setUuid(uuid);

		return entry;
	}

	/**
	 * Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry that was removed
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry remove(long entryId)
		throws NoSuchEntryException, SystemException {
		return remove((Serializable)entryId);
	}

	/**
	 * Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the entry
	 * @return the entry that was removed
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry remove(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Entry entry = (Entry)session.get(EntryImpl.class, primaryKey);

			if (entry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(entry);
		}
		catch (NoSuchEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Entry removeImpl(Entry entry) throws SystemException {
		entry = toUnwrappedModel(entry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entry)) {
				entry = (Entry)session.get(EntryImpl.class,
						entry.getPrimaryKeyObj());
			}

			if (entry != null) {
				session.delete(entry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entry != null) {
			clearCache(entry);
		}

		return entry;
	}

	@Override
	public Entry updateImpl(com.liferay.docs.guestbook.model.Entry entry)
		throws SystemException {
		entry = toUnwrappedModel(entry);

		boolean isNew = entry.isNew();

		EntryModelImpl entryModelImpl = (EntryModelImpl)entry;

		if (Validator.isNull(entry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			entry.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (entry.isNew()) {
				session.save(entry);

				entry.setNew(false);
			}
			else {
				session.merge(entry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { entryModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { entryModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalUuid(),
						entryModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						entryModelImpl.getUuid(), entryModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalGroupId(),
						entryModelImpl.getOriginalGuestbookId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G,
					args);

				args = new Object[] {
						entryModelImpl.getGroupId(),
						entryModelImpl.getGuestbookId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTRYNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { entryModelImpl.getOriginalName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ENTRYNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTRYNAME,
					args);

				args = new Object[] { entryModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ENTRYNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENTRYNAME,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalGuestbookId(),
						entryModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N,
					args);

				args = new Object[] {
						entryModelImpl.getGuestbookId(),
						entryModelImpl.getName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_N,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalGroupId(),
						entryModelImpl.getOriginalGuestbookId(),
						entryModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_G_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_N,
					args);

				args = new Object[] {
						entryModelImpl.getGroupId(),
						entryModelImpl.getGuestbookId(),
						entryModelImpl.getName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_G_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_N,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalGroupId(),
						entryModelImpl.getOriginalGuestbookId(),
						entryModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_S,
					args);

				args = new Object[] {
						entryModelImpl.getGroupId(),
						entryModelImpl.getGuestbookId(),
						entryModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_G_S,
					args);
			}
		}

		EntityCacheUtil.putResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey(), entry);

		clearUniqueFindersCache(entry);
		cacheUniqueFindersCache(entry);

		return entry;
	}

	protected Entry toUnwrappedModel(Entry entry) {
		if (entry instanceof EntryImpl) {
			return entry;
		}

		EntryImpl entryImpl = new EntryImpl();

		entryImpl.setNew(entry.isNew());
		entryImpl.setPrimaryKey(entry.getPrimaryKey());

		entryImpl.setUuid(entry.getUuid());
		entryImpl.setEntryId(entry.getEntryId());
		entryImpl.setGroupId(entry.getGroupId());
		entryImpl.setCompanyId(entry.getCompanyId());
		entryImpl.setUserId(entry.getUserId());
		entryImpl.setUserName(entry.getUserName());
		entryImpl.setCreateDate(entry.getCreateDate());
		entryImpl.setModifiedDate(entry.getModifiedDate());
		entryImpl.setName(entry.getName());
		entryImpl.setEmail(entry.getEmail());
		entryImpl.setMessage(entry.getMessage());
		entryImpl.setGuestbookId(entry.getGuestbookId());
		entryImpl.setStatus(entry.getStatus());
		entryImpl.setStatusByUserId(entry.getStatusByUserId());
		entryImpl.setStatusByUserName(entry.getStatusByUserName());
		entryImpl.setStatusDate(entry.getStatusDate());

		return entryImpl;
	}

	/**
	 * Returns the entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the entry
	 * @return the entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByPrimaryKey(primaryKey);

		if (entry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return entry;
	}

	/**
	 * Returns the entry with the primary key or throws a {@link com.liferay.docs.guestbook.NoSuchEntryException} if it could not be found.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry
	 * @throws com.liferay.docs.guestbook.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByPrimaryKey(long entryId)
		throws NoSuchEntryException, SystemException {
		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the entry
	 * @return the entry, or <code>null</code> if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Entry entry = (Entry)EntityCacheUtil.getResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
				EntryImpl.class, primaryKey);

		if (entry == _nullEntry) {
			return null;
		}

		if (entry == null) {
			Session session = null;

			try {
				session = openSession();

				entry = (Entry)session.get(EntryImpl.class, primaryKey);

				if (entry != null) {
					cacheResult(entry);
				}
				else {
					EntityCacheUtil.putResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
						EntryImpl.class, primaryKey, _nullEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
					EntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return entry;
	}

	/**
	 * Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry, or <code>null</code> if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByPrimaryKey(long entryId) throws SystemException {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns all the entries.
	 *
	 * @return the entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Entry> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<Entry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTRY;

				if (pagination) {
					sql = sql.concat(EntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Entry entry : findAll()) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries.
	 *
	 * @return the number of entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENTRY);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.docs.guestbook.model.Entry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Entry>> listenersList = new ArrayList<ModelListener<Entry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Entry>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(EntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ENTRY = "SELECT entry FROM Entry entry";
	private static final String _SQL_SELECT_ENTRY_WHERE = "SELECT entry FROM Entry entry WHERE ";
	private static final String _SQL_COUNT_ENTRY = "SELECT COUNT(entry) FROM Entry entry";
	private static final String _SQL_COUNT_ENTRY_WHERE = "SELECT COUNT(entry) FROM Entry entry WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "entry.entryId";
	private static final String _FILTER_SQL_SELECT_ENTRY_WHERE = "SELECT DISTINCT {entry.*} FROM GB_Entry entry WHERE ";
	private static final String _FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {GB_Entry.*} FROM (SELECT DISTINCT entry.entryId FROM GB_Entry entry WHERE ";
	private static final String _FILTER_SQL_SELECT_ENTRY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN GB_Entry ON TEMP_TABLE.entryId = GB_Entry.entryId";
	private static final String _FILTER_SQL_COUNT_ENTRY_WHERE = "SELECT COUNT(DISTINCT entry.entryId) AS COUNT_VALUE FROM GB_Entry entry WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "entry";
	private static final String _FILTER_ENTITY_TABLE = "GB_Entry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "entry.";
	private static final String _ORDER_BY_ENTITY_TABLE = "GB_Entry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Entry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Entry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(EntryPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static Entry _nullEntry = new EntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Entry> toCacheModel() {
				return _nullEntryCacheModel;
			}
		};

	private static CacheModel<Entry> _nullEntryCacheModel = new CacheModel<Entry>() {
			@Override
			public Entry toEntityModel() {
				return _nullEntry;
			}
		};
}