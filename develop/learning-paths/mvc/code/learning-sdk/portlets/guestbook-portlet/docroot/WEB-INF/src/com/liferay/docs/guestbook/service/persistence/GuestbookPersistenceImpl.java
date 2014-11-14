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

import com.liferay.docs.guestbook.NoSuchGuestbookException;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.model.impl.GuestbookImpl;
import com.liferay.docs.guestbook.model.impl.GuestbookModelImpl;

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
 * The persistence implementation for the guestbook service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Rich Sezov
 * @see GuestbookPersistence
 * @see GuestbookUtil
 * @generated
 */
public class GuestbookPersistenceImpl extends BasePersistenceImpl<Guestbook>
	implements GuestbookPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GuestbookUtil} to access the guestbook persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GuestbookImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			GuestbookModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the guestbooks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching guestbooks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Guestbook> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Guestbook> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Guestbook> findByUuid(String uuid, int start, int end,
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

		List<Guestbook> list = (List<Guestbook>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Guestbook guestbook : list) {
				if (!Validator.equals(uuid, guestbook.getUuid())) {
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

			query.append(_SQL_SELECT_GUESTBOOK_WHERE);

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
				query.append(GuestbookModelImpl.ORDER_BY_JPQL);
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
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Guestbook>(list);
				}
				else {
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first guestbook in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByUuid_First(uuid, orderByComparator);

		if (guestbook != null) {
			return guestbook;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookException(msg.toString());
	}

	/**
	 * Returns the first guestbook in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Guestbook> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Guestbook findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByUuid_Last(uuid, orderByComparator);

		if (guestbook != null) {
			return guestbook;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookException(msg.toString());
	}

	/**
	 * Returns the last guestbook in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Guestbook> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Guestbook[] findByUuid_PrevAndNext(long guestbookId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = findByPrimaryKey(guestbookId);

		Session session = null;

		try {
			session = openSession();

			Guestbook[] array = new GuestbookImpl[3];

			array[0] = getByUuid_PrevAndNext(session, guestbook, uuid,
					orderByComparator, true);

			array[1] = guestbook;

			array[2] = getByUuid_PrevAndNext(session, guestbook, uuid,
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

	protected Guestbook getByUuid_PrevAndNext(Session session,
		Guestbook guestbook, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GUESTBOOK_WHERE);

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
			query.append(GuestbookModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(guestbook);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Guestbook> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guestbooks where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (Guestbook guestbook : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(guestbook);
		}
	}

	/**
	 * Returns the number of guestbooks where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching guestbooks
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

			query.append(_SQL_COUNT_GUESTBOOK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "guestbook.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "guestbook.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(guestbook.uuid IS NULL OR guestbook.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			GuestbookModelImpl.UUID_COLUMN_BITMASK |
			GuestbookModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the guestbook where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.docs.guestbook.NoSuchGuestbookException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guestbook
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook findByUUID_G(String uuid, long groupId)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByUUID_G(uuid, groupId);

		if (guestbook == null) {
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

			throw new NoSuchGuestbookException(msg.toString());
		}

		return guestbook;
	}

	/**
	 * Returns the guestbook where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guestbook, or <code>null</code> if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
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
	@Override
	public Guestbook fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Guestbook) {
			Guestbook guestbook = (Guestbook)result;

			if (!Validator.equals(uuid, guestbook.getUuid()) ||
					(groupId != guestbook.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_GUESTBOOK_WHERE);

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

				List<Guestbook> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Guestbook guestbook = list.get(0);

					result = guestbook;

					cacheResult(guestbook);

					if ((guestbook.getUuid() == null) ||
							!guestbook.getUuid().equals(uuid) ||
							(guestbook.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, guestbook);
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
			return (Guestbook)result;
		}
	}

	/**
	 * Removes the guestbook where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the guestbook that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook removeByUUID_G(String uuid, long groupId)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = findByUUID_G(uuid, groupId);

		return remove(guestbook);
	}

	/**
	 * Returns the number of guestbooks where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching guestbooks
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

			query.append(_SQL_COUNT_GUESTBOOK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "guestbook.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "guestbook.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(guestbook.uuid IS NULL OR guestbook.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "guestbook.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			GuestbookModelImpl.UUID_COLUMN_BITMASK |
			GuestbookModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the guestbooks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching guestbooks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Guestbook> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Guestbook> findByUuid_C(String uuid, long companyId, int start,
		int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<Guestbook> findByUuid_C(String uuid, long companyId, int start,
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

		List<Guestbook> list = (List<Guestbook>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Guestbook guestbook : list) {
				if (!Validator.equals(uuid, guestbook.getUuid()) ||
						(companyId != guestbook.getCompanyId())) {
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

			query.append(_SQL_SELECT_GUESTBOOK_WHERE);

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
				query.append(GuestbookModelImpl.ORDER_BY_JPQL);
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
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Guestbook>(list);
				}
				else {
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first guestbook in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (guestbook != null) {
			return guestbook;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookException(msg.toString());
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
	@Override
	public Guestbook fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Guestbook> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Guestbook findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (guestbook != null) {
			return guestbook;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookException(msg.toString());
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
	@Override
	public Guestbook fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Guestbook> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Guestbook[] findByUuid_C_PrevAndNext(long guestbookId, String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = findByPrimaryKey(guestbookId);

		Session session = null;

		try {
			session = openSession();

			Guestbook[] array = new GuestbookImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, guestbook, uuid,
					companyId, orderByComparator, true);

			array[1] = guestbook;

			array[2] = getByUuid_C_PrevAndNext(session, guestbook, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Guestbook getByUuid_C_PrevAndNext(Session session,
		Guestbook guestbook, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GUESTBOOK_WHERE);

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
			query.append(GuestbookModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(guestbook);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Guestbook> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guestbooks where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (Guestbook guestbook : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(guestbook);
		}
	}

	/**
	 * Returns the number of guestbooks where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching guestbooks
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

			query.append(_SQL_COUNT_GUESTBOOK_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "guestbook.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "guestbook.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(guestbook.uuid IS NULL OR guestbook.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "guestbook.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			GuestbookModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the guestbooks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching guestbooks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Guestbook> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Guestbook> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<Guestbook> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Guestbook> list = (List<Guestbook>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Guestbook guestbook : list) {
				if ((groupId != guestbook.getGroupId())) {
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

			query.append(_SQL_SELECT_GUESTBOOK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(GuestbookModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Guestbook>(list);
				}
				else {
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first guestbook in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByGroupId_First(groupId, orderByComparator);

		if (guestbook != null) {
			return guestbook;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookException(msg.toString());
	}

	/**
	 * Returns the first guestbook in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Guestbook> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Guestbook findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByGroupId_Last(groupId, orderByComparator);

		if (guestbook != null) {
			return guestbook;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookException(msg.toString());
	}

	/**
	 * Returns the last guestbook in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Guestbook> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Guestbook[] findByGroupId_PrevAndNext(long guestbookId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = findByPrimaryKey(guestbookId);

		Session session = null;

		try {
			session = openSession();

			Guestbook[] array = new GuestbookImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, guestbook, groupId,
					orderByComparator, true);

			array[1] = guestbook;

			array[2] = getByGroupId_PrevAndNext(session, guestbook, groupId,
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

	protected Guestbook getByGroupId_PrevAndNext(Session session,
		Guestbook guestbook, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GUESTBOOK_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(GuestbookModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(guestbook);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Guestbook> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the guestbooks that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching guestbooks that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Guestbook> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<Guestbook> filterFindByGroupId(long groupId, int start, int end)
		throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
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
	@Override
	public List<Guestbook> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GUESTBOOK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_GUESTBOOK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GUESTBOOK_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(GuestbookModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(GuestbookModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Guestbook.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, GuestbookImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, GuestbookImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<Guestbook>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public Guestbook[] filterFindByGroupId_PrevAndNext(long guestbookId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(guestbookId, groupId,
				orderByComparator);
		}

		Guestbook guestbook = findByPrimaryKey(guestbookId);

		Session session = null;

		try {
			session = openSession();

			Guestbook[] array = new GuestbookImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, guestbook,
					groupId, orderByComparator, true);

			array[1] = guestbook;

			array[2] = filterGetByGroupId_PrevAndNext(session, guestbook,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Guestbook filterGetByGroupId_PrevAndNext(Session session,
		Guestbook guestbook, long groupId, OrderByComparator orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_GUESTBOOK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_GUESTBOOK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GUESTBOOK_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(GuestbookModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(GuestbookModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Guestbook.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, GuestbookImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, GuestbookImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(guestbook);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Guestbook> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guestbooks where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (Guestbook guestbook : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(guestbook);
		}
	}

	/**
	 * Returns the number of guestbooks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching guestbooks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_GUESTBOOK_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	/**
	 * Returns the number of guestbooks that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching guestbooks that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_GUESTBOOK_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Guestbook.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "guestbook.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GUESTBOOKNAME =
		new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGuestbookName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GUESTBOOKNAME =
		new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, GuestbookImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGuestbookName",
			new String[] { String.class.getName() },
			GuestbookModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GUESTBOOKNAME = new FinderPath(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGuestbookName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the guestbooks where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching guestbooks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Guestbook> findByGuestbookName(String name)
		throws SystemException {
		return findByGuestbookName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<Guestbook> findByGuestbookName(String name, int start, int end)
		throws SystemException {
		return findByGuestbookName(name, start, end, null);
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
	@Override
	public List<Guestbook> findByGuestbookName(String name, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GUESTBOOKNAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GUESTBOOKNAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<Guestbook> list = (List<Guestbook>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Guestbook guestbook : list) {
				if (!Validator.equals(name, guestbook.getName())) {
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

			query.append(_SQL_SELECT_GUESTBOOK_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(GuestbookModelImpl.ORDER_BY_JPQL);
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
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Guestbook>(list);
				}
				else {
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first guestbook in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook findByGuestbookName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByGuestbookName_First(name, orderByComparator);

		if (guestbook != null) {
			return guestbook;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookException(msg.toString());
	}

	/**
	 * Returns the first guestbook in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook, or <code>null</code> if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByGuestbookName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<Guestbook> list = findByGuestbookName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Guestbook findByGuestbookName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByGuestbookName_Last(name, orderByComparator);

		if (guestbook != null) {
			return guestbook;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookException(msg.toString());
	}

	/**
	 * Returns the last guestbook in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook, or <code>null</code> if a matching guestbook could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByGuestbookName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGuestbookName(name);

		if (count == 0) {
			return null;
		}

		List<Guestbook> list = findByGuestbookName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Guestbook[] findByGuestbookName_PrevAndNext(long guestbookId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = findByPrimaryKey(guestbookId);

		Session session = null;

		try {
			session = openSession();

			Guestbook[] array = new GuestbookImpl[3];

			array[0] = getByGuestbookName_PrevAndNext(session, guestbook, name,
					orderByComparator, true);

			array[1] = guestbook;

			array[2] = getByGuestbookName_PrevAndNext(session, guestbook, name,
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

	protected Guestbook getByGuestbookName_PrevAndNext(Session session,
		Guestbook guestbook, String name, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GUESTBOOK_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_2);
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
			query.append(GuestbookModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(guestbook);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Guestbook> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guestbooks where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGuestbookName(String name) throws SystemException {
		for (Guestbook guestbook : findByGuestbookName(name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(guestbook);
		}
	}

	/**
	 * Returns the number of guestbooks where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching guestbooks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGuestbookName(String name) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GUESTBOOKNAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_GUESTBOOK_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_GUESTBOOKNAME_NAME_2);
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

	private static final String _FINDER_COLUMN_GUESTBOOKNAME_NAME_1 = "guestbook.name IS NULL";
	private static final String _FINDER_COLUMN_GUESTBOOKNAME_NAME_2 = "guestbook.name = ?";
	private static final String _FINDER_COLUMN_GUESTBOOKNAME_NAME_3 = "(guestbook.name IS NULL OR guestbook.name = '')";

	public GuestbookPersistenceImpl() {
		setModelClass(Guestbook.class);
	}

	/**
	 * Caches the guestbook in the entity cache if it is enabled.
	 *
	 * @param guestbook the guestbook
	 */
	@Override
	public void cacheResult(Guestbook guestbook) {
		EntityCacheUtil.putResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookImpl.class, guestbook.getPrimaryKey(), guestbook);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { guestbook.getUuid(), guestbook.getGroupId() },
			guestbook);

		guestbook.resetOriginalValues();
	}

	/**
	 * Caches the guestbooks in the entity cache if it is enabled.
	 *
	 * @param guestbooks the guestbooks
	 */
	@Override
	public void cacheResult(List<Guestbook> guestbooks) {
		for (Guestbook guestbook : guestbooks) {
			if (EntityCacheUtil.getResult(
						GuestbookModelImpl.ENTITY_CACHE_ENABLED,
						GuestbookImpl.class, guestbook.getPrimaryKey()) == null) {
				cacheResult(guestbook);
			}
			else {
				guestbook.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all guestbooks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(GuestbookImpl.class.getName());
		}

		EntityCacheUtil.clearCache(GuestbookImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the guestbook.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Guestbook guestbook) {
		EntityCacheUtil.removeResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookImpl.class, guestbook.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(guestbook);
	}

	@Override
	public void clearCache(List<Guestbook> guestbooks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Guestbook guestbook : guestbooks) {
			EntityCacheUtil.removeResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
				GuestbookImpl.class, guestbook.getPrimaryKey());

			clearUniqueFindersCache(guestbook);
		}
	}

	protected void cacheUniqueFindersCache(Guestbook guestbook) {
		if (guestbook.isNew()) {
			Object[] args = new Object[] {
					guestbook.getUuid(), guestbook.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				guestbook);
		}
		else {
			GuestbookModelImpl guestbookModelImpl = (GuestbookModelImpl)guestbook;

			if ((guestbookModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						guestbook.getUuid(), guestbook.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					guestbook);
			}
		}
	}

	protected void clearUniqueFindersCache(Guestbook guestbook) {
		GuestbookModelImpl guestbookModelImpl = (GuestbookModelImpl)guestbook;

		Object[] args = new Object[] { guestbook.getUuid(), guestbook.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((guestbookModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					guestbookModelImpl.getOriginalUuid(),
					guestbookModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new guestbook with the primary key. Does not add the guestbook to the database.
	 *
	 * @param guestbookId the primary key for the new guestbook
	 * @return the new guestbook
	 */
	@Override
	public Guestbook create(long guestbookId) {
		Guestbook guestbook = new GuestbookImpl();

		guestbook.setNew(true);
		guestbook.setPrimaryKey(guestbookId);

		String uuid = PortalUUIDUtil.generate();

		guestbook.setUuid(uuid);

		return guestbook;
	}

	/**
	 * Removes the guestbook with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param guestbookId the primary key of the guestbook
	 * @return the guestbook that was removed
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook remove(long guestbookId)
		throws NoSuchGuestbookException, SystemException {
		return remove((Serializable)guestbookId);
	}

	/**
	 * Removes the guestbook with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the guestbook
	 * @return the guestbook that was removed
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook remove(Serializable primaryKey)
		throws NoSuchGuestbookException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Guestbook guestbook = (Guestbook)session.get(GuestbookImpl.class,
					primaryKey);

			if (guestbook == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGuestbookException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(guestbook);
		}
		catch (NoSuchGuestbookException nsee) {
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
	protected Guestbook removeImpl(Guestbook guestbook)
		throws SystemException {
		guestbook = toUnwrappedModel(guestbook);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(guestbook)) {
				guestbook = (Guestbook)session.get(GuestbookImpl.class,
						guestbook.getPrimaryKeyObj());
			}

			if (guestbook != null) {
				session.delete(guestbook);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (guestbook != null) {
			clearCache(guestbook);
		}

		return guestbook;
	}

	@Override
	public Guestbook updateImpl(
		com.liferay.docs.guestbook.model.Guestbook guestbook)
		throws SystemException {
		guestbook = toUnwrappedModel(guestbook);

		boolean isNew = guestbook.isNew();

		GuestbookModelImpl guestbookModelImpl = (GuestbookModelImpl)guestbook;

		if (Validator.isNull(guestbook.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			guestbook.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (guestbook.isNew()) {
				session.save(guestbook);

				guestbook.setNew(false);
			}
			else {
				session.merge(guestbook);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !GuestbookModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((guestbookModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						guestbookModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { guestbookModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((guestbookModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						guestbookModelImpl.getOriginalUuid(),
						guestbookModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						guestbookModelImpl.getUuid(),
						guestbookModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((guestbookModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						guestbookModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { guestbookModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((guestbookModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GUESTBOOKNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						guestbookModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GUESTBOOKNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GUESTBOOKNAME,
					args);

				args = new Object[] { guestbookModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GUESTBOOKNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GUESTBOOKNAME,
					args);
			}
		}

		EntityCacheUtil.putResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookImpl.class, guestbook.getPrimaryKey(), guestbook);

		clearUniqueFindersCache(guestbook);
		cacheUniqueFindersCache(guestbook);

		return guestbook;
	}

	protected Guestbook toUnwrappedModel(Guestbook guestbook) {
		if (guestbook instanceof GuestbookImpl) {
			return guestbook;
		}

		GuestbookImpl guestbookImpl = new GuestbookImpl();

		guestbookImpl.setNew(guestbook.isNew());
		guestbookImpl.setPrimaryKey(guestbook.getPrimaryKey());

		guestbookImpl.setUuid(guestbook.getUuid());
		guestbookImpl.setGuestbookId(guestbook.getGuestbookId());
		guestbookImpl.setGroupId(guestbook.getGroupId());
		guestbookImpl.setCompanyId(guestbook.getCompanyId());
		guestbookImpl.setUserId(guestbook.getUserId());
		guestbookImpl.setUserName(guestbook.getUserName());
		guestbookImpl.setCreateDate(guestbook.getCreateDate());
		guestbookImpl.setModifiedDate(guestbook.getModifiedDate());
		guestbookImpl.setName(guestbook.getName());

		return guestbookImpl;
	}

	/**
	 * Returns the guestbook with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the guestbook
	 * @return the guestbook
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGuestbookException, SystemException {
		Guestbook guestbook = fetchByPrimaryKey(primaryKey);

		if (guestbook == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGuestbookException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return guestbook;
	}

	/**
	 * Returns the guestbook with the primary key or throws a {@link com.liferay.docs.guestbook.NoSuchGuestbookException} if it could not be found.
	 *
	 * @param guestbookId the primary key of the guestbook
	 * @return the guestbook
	 * @throws com.liferay.docs.guestbook.NoSuchGuestbookException if a guestbook with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook findByPrimaryKey(long guestbookId)
		throws NoSuchGuestbookException, SystemException {
		return findByPrimaryKey((Serializable)guestbookId);
	}

	/**
	 * Returns the guestbook with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the guestbook
	 * @return the guestbook, or <code>null</code> if a guestbook with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Guestbook guestbook = (Guestbook)EntityCacheUtil.getResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
				GuestbookImpl.class, primaryKey);

		if (guestbook == _nullGuestbook) {
			return null;
		}

		if (guestbook == null) {
			Session session = null;

			try {
				session = openSession();

				guestbook = (Guestbook)session.get(GuestbookImpl.class,
						primaryKey);

				if (guestbook != null) {
					cacheResult(guestbook);
				}
				else {
					EntityCacheUtil.putResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
						GuestbookImpl.class, primaryKey, _nullGuestbook);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
					GuestbookImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return guestbook;
	}

	/**
	 * Returns the guestbook with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param guestbookId the primary key of the guestbook
	 * @return the guestbook, or <code>null</code> if a guestbook with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Guestbook fetchByPrimaryKey(long guestbookId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)guestbookId);
	}

	/**
	 * Returns all the guestbooks.
	 *
	 * @return the guestbooks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Guestbook> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Guestbook> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<Guestbook> findAll(int start, int end,
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

		List<Guestbook> list = (List<Guestbook>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_GUESTBOOK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GUESTBOOK;

				if (pagination) {
					sql = sql.concat(GuestbookModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Guestbook>(list);
				}
				else {
					list = (List<Guestbook>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the guestbooks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Guestbook guestbook : findAll()) {
			remove(guestbook);
		}
	}

	/**
	 * Returns the number of guestbooks.
	 *
	 * @return the number of guestbooks
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

				Query q = session.createQuery(_SQL_COUNT_GUESTBOOK);

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
	 * Initializes the guestbook persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.docs.guestbook.model.Guestbook")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Guestbook>> listenersList = new ArrayList<ModelListener<Guestbook>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Guestbook>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(GuestbookImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_GUESTBOOK = "SELECT guestbook FROM Guestbook guestbook";
	private static final String _SQL_SELECT_GUESTBOOK_WHERE = "SELECT guestbook FROM Guestbook guestbook WHERE ";
	private static final String _SQL_COUNT_GUESTBOOK = "SELECT COUNT(guestbook) FROM Guestbook guestbook";
	private static final String _SQL_COUNT_GUESTBOOK_WHERE = "SELECT COUNT(guestbook) FROM Guestbook guestbook WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "guestbook.guestbookId";
	private static final String _FILTER_SQL_SELECT_GUESTBOOK_WHERE = "SELECT DISTINCT {guestbook.*} FROM GB_Guestbook guestbook WHERE ";
	private static final String _FILTER_SQL_SELECT_GUESTBOOK_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {GB_Guestbook.*} FROM (SELECT DISTINCT guestbook.guestbookId FROM GB_Guestbook guestbook WHERE ";
	private static final String _FILTER_SQL_SELECT_GUESTBOOK_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN GB_Guestbook ON TEMP_TABLE.guestbookId = GB_Guestbook.guestbookId";
	private static final String _FILTER_SQL_COUNT_GUESTBOOK_WHERE = "SELECT COUNT(DISTINCT guestbook.guestbookId) AS COUNT_VALUE FROM GB_Guestbook guestbook WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "guestbook";
	private static final String _FILTER_ENTITY_TABLE = "GB_Guestbook";
	private static final String _ORDER_BY_ENTITY_ALIAS = "guestbook.";
	private static final String _ORDER_BY_ENTITY_TABLE = "GB_Guestbook.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Guestbook exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Guestbook exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(GuestbookPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static Guestbook _nullGuestbook = new GuestbookImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Guestbook> toCacheModel() {
				return _nullGuestbookCacheModel;
			}
		};

	private static CacheModel<Guestbook> _nullGuestbookCacheModel = new CacheModel<Guestbook>() {
			@Override
			public Guestbook toEntityModel() {
				return _nullGuestbook;
			}
		};
}