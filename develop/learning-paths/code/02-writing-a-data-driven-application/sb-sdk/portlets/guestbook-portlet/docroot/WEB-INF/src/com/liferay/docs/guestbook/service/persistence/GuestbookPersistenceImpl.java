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
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "guestbook.groupId = ?";

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
	}

	@Override
	public void clearCache(List<Guestbook> guestbooks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Guestbook guestbook : guestbooks) {
			EntityCacheUtil.removeResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
				GuestbookImpl.class, guestbook.getPrimaryKey());
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
		}

		EntityCacheUtil.putResult(GuestbookModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookImpl.class, guestbook.getPrimaryKey(), guestbook);

		return guestbook;
	}

	protected Guestbook toUnwrappedModel(Guestbook guestbook) {
		if (guestbook instanceof GuestbookImpl) {
			return guestbook;
		}

		GuestbookImpl guestbookImpl = new GuestbookImpl();

		guestbookImpl.setNew(guestbook.isNew());
		guestbookImpl.setPrimaryKey(guestbook.getPrimaryKey());

		guestbookImpl.setGuestbookId(guestbook.getGuestbookId());
		guestbookImpl.setGroupId(guestbook.getGroupId());
		guestbookImpl.setCompanyId(guestbook.getCompanyId());
		guestbookImpl.setUserId(guestbook.getUserId());
		guestbookImpl.setUserName(guestbook.getUserName());
		guestbookImpl.setCreateDate(guestbook.getCreateDate());
		guestbookImpl.setModifiedDate(guestbook.getModifiedDate());
		guestbookImpl.setName(guestbook.getName());
		guestbookImpl.setEntryId(guestbook.getEntryId());

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
	private static final String _ORDER_BY_ENTITY_ALIAS = "guestbook.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Guestbook exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Guestbook exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(GuestbookPersistenceImpl.class);
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