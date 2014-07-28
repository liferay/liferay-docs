/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.samples.portlet.eventlisting.service.persistence;

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

import com.liferay.samples.portlet.eventlisting.NoSuchLocationException;
import com.liferay.samples.portlet.eventlisting.model.Location;
import com.liferay.samples.portlet.eventlisting.model.impl.LocationImpl;
import com.liferay.samples.portlet.eventlisting.model.impl.LocationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the location service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Joe Bloggs
 * @see LocationPersistence
 * @see LocationUtil
 * @generated
 */
public class LocationPersistenceImpl extends BasePersistenceImpl<Location>
	implements LocationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LocationUtil} to access the location persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LocationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationModelImpl.FINDER_CACHE_ENABLED, LocationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationModelImpl.FINDER_CACHE_ENABLED, LocationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationModelImpl.FINDER_CACHE_ENABLED, LocationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationModelImpl.FINDER_CACHE_ENABLED, LocationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			LocationModelImpl.GROUPID_COLUMN_BITMASK |
			LocationModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the locations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching locations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Location> findByGroupId(long groupId) throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the locations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of locations
	 * @param end the upper bound of the range of locations (not inclusive)
	 * @return the range of matching locations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Location> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the locations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of locations
	 * @param end the upper bound of the range of locations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching locations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Location> findByGroupId(long groupId, int start, int end,
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

		List<Location> list = (List<Location>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Location location : list) {
				if ((groupId != location.getGroupId())) {
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

			query.append(_SQL_SELECT_LOCATION_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LocationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<Location>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Location>(list);
				}
				else {
					list = (List<Location>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first location in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching location
	 * @throws com.liferay.samples.portlet.eventlisting.NoSuchLocationException if a matching location could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchLocationException, SystemException {
		Location location = fetchByGroupId_First(groupId, orderByComparator);

		if (location != null) {
			return location;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLocationException(msg.toString());
	}

	/**
	 * Returns the first location in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching location, or <code>null</code> if a matching location could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Location> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last location in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching location
	 * @throws com.liferay.samples.portlet.eventlisting.NoSuchLocationException if a matching location could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchLocationException, SystemException {
		Location location = fetchByGroupId_Last(groupId, orderByComparator);

		if (location != null) {
			return location;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLocationException(msg.toString());
	}

	/**
	 * Returns the last location in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching location, or <code>null</code> if a matching location could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<Location> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the locations before and after the current location in the ordered set where groupId = &#63;.
	 *
	 * @param locationId the primary key of the current location
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next location
	 * @throws com.liferay.samples.portlet.eventlisting.NoSuchLocationException if a location with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location[] findByGroupId_PrevAndNext(long locationId, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchLocationException, SystemException {
		Location location = findByPrimaryKey(locationId);

		Session session = null;

		try {
			session = openSession();

			Location[] array = new LocationImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, location, groupId,
					orderByComparator, true);

			array[1] = location;

			array[2] = getByGroupId_PrevAndNext(session, location, groupId,
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

	protected Location getByGroupId_PrevAndNext(Session session,
		Location location, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LOCATION_WHERE);

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
			query.append(LocationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(location);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Location> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the locations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (Location location : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(location);
		}
	}

	/**
	 * Returns the number of locations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching locations
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

			query.append(_SQL_COUNT_LOCATION_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "location.groupId = ?";

	public LocationPersistenceImpl() {
		setModelClass(Location.class);
	}

	/**
	 * Caches the location in the entity cache if it is enabled.
	 *
	 * @param location the location
	 */
	@Override
	public void cacheResult(Location location) {
		EntityCacheUtil.putResult(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationImpl.class, location.getPrimaryKey(), location);

		location.resetOriginalValues();
	}

	/**
	 * Caches the locations in the entity cache if it is enabled.
	 *
	 * @param locations the locations
	 */
	@Override
	public void cacheResult(List<Location> locations) {
		for (Location location : locations) {
			if (EntityCacheUtil.getResult(
						LocationModelImpl.ENTITY_CACHE_ENABLED,
						LocationImpl.class, location.getPrimaryKey()) == null) {
				cacheResult(location);
			}
			else {
				location.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all locations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LocationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LocationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the location.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Location location) {
		EntityCacheUtil.removeResult(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationImpl.class, location.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Location> locations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Location location : locations) {
			EntityCacheUtil.removeResult(LocationModelImpl.ENTITY_CACHE_ENABLED,
				LocationImpl.class, location.getPrimaryKey());
		}
	}

	/**
	 * Creates a new location with the primary key. Does not add the location to the database.
	 *
	 * @param locationId the primary key for the new location
	 * @return the new location
	 */
	@Override
	public Location create(long locationId) {
		Location location = new LocationImpl();

		location.setNew(true);
		location.setPrimaryKey(locationId);

		return location;
	}

	/**
	 * Removes the location with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param locationId the primary key of the location
	 * @return the location that was removed
	 * @throws com.liferay.samples.portlet.eventlisting.NoSuchLocationException if a location with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location remove(long locationId)
		throws NoSuchLocationException, SystemException {
		return remove((Serializable)locationId);
	}

	/**
	 * Removes the location with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the location
	 * @return the location that was removed
	 * @throws com.liferay.samples.portlet.eventlisting.NoSuchLocationException if a location with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location remove(Serializable primaryKey)
		throws NoSuchLocationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Location location = (Location)session.get(LocationImpl.class,
					primaryKey);

			if (location == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLocationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(location);
		}
		catch (NoSuchLocationException nsee) {
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
	protected Location removeImpl(Location location) throws SystemException {
		location = toUnwrappedModel(location);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(location)) {
				location = (Location)session.get(LocationImpl.class,
						location.getPrimaryKeyObj());
			}

			if (location != null) {
				session.delete(location);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (location != null) {
			clearCache(location);
		}

		return location;
	}

	@Override
	public Location updateImpl(
		com.liferay.samples.portlet.eventlisting.model.Location location)
		throws SystemException {
		location = toUnwrappedModel(location);

		boolean isNew = location.isNew();

		LocationModelImpl locationModelImpl = (LocationModelImpl)location;

		Session session = null;

		try {
			session = openSession();

			if (location.isNew()) {
				session.save(location);

				location.setNew(false);
			}
			else {
				session.merge(location);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LocationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((locationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						locationModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { locationModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(LocationModelImpl.ENTITY_CACHE_ENABLED,
			LocationImpl.class, location.getPrimaryKey(), location);

		return location;
	}

	protected Location toUnwrappedModel(Location location) {
		if (location instanceof LocationImpl) {
			return location;
		}

		LocationImpl locationImpl = new LocationImpl();

		locationImpl.setNew(location.isNew());
		locationImpl.setPrimaryKey(location.getPrimaryKey());

		locationImpl.setLocationId(location.getLocationId());
		locationImpl.setName(location.getName());
		locationImpl.setDescription(location.getDescription());
		locationImpl.setStreetAddress(location.getStreetAddress());
		locationImpl.setCity(location.getCity());
		locationImpl.setStateOrProvince(location.getStateOrProvince());
		locationImpl.setCountry(location.getCountry());
		locationImpl.setCompanyId(location.getCompanyId());
		locationImpl.setGroupId(location.getGroupId());
		locationImpl.setUserId(location.getUserId());
		locationImpl.setCreateDate(location.getCreateDate());
		locationImpl.setModifiedDate(location.getModifiedDate());

		return locationImpl;
	}

	/**
	 * Returns the location with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the location
	 * @return the location
	 * @throws com.liferay.samples.portlet.eventlisting.NoSuchLocationException if a location with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLocationException, SystemException {
		Location location = fetchByPrimaryKey(primaryKey);

		if (location == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLocationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return location;
	}

	/**
	 * Returns the location with the primary key or throws a {@link com.liferay.samples.portlet.eventlisting.NoSuchLocationException} if it could not be found.
	 *
	 * @param locationId the primary key of the location
	 * @return the location
	 * @throws com.liferay.samples.portlet.eventlisting.NoSuchLocationException if a location with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location findByPrimaryKey(long locationId)
		throws NoSuchLocationException, SystemException {
		return findByPrimaryKey((Serializable)locationId);
	}

	/**
	 * Returns the location with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the location
	 * @return the location, or <code>null</code> if a location with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Location location = (Location)EntityCacheUtil.getResult(LocationModelImpl.ENTITY_CACHE_ENABLED,
				LocationImpl.class, primaryKey);

		if (location == _nullLocation) {
			return null;
		}

		if (location == null) {
			Session session = null;

			try {
				session = openSession();

				location = (Location)session.get(LocationImpl.class, primaryKey);

				if (location != null) {
					cacheResult(location);
				}
				else {
					EntityCacheUtil.putResult(LocationModelImpl.ENTITY_CACHE_ENABLED,
						LocationImpl.class, primaryKey, _nullLocation);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(LocationModelImpl.ENTITY_CACHE_ENABLED,
					LocationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return location;
	}

	/**
	 * Returns the location with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param locationId the primary key of the location
	 * @return the location, or <code>null</code> if a location with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Location fetchByPrimaryKey(long locationId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)locationId);
	}

	/**
	 * Returns all the locations.
	 *
	 * @return the locations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Location> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the locations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of locations
	 * @param end the upper bound of the range of locations (not inclusive)
	 * @return the range of locations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Location> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the locations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samples.portlet.eventlisting.model.impl.LocationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of locations
	 * @param end the upper bound of the range of locations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of locations
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Location> findAll(int start, int end,
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

		List<Location> list = (List<Location>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LOCATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOCATION;

				if (pagination) {
					sql = sql.concat(LocationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Location>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Location>(list);
				}
				else {
					list = (List<Location>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the locations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Location location : findAll()) {
			remove(location);
		}
	}

	/**
	 * Returns the number of locations.
	 *
	 * @return the number of locations
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

				Query q = session.createQuery(_SQL_COUNT_LOCATION);

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
	 * Initializes the location persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.samples.portlet.eventlisting.model.Location")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Location>> listenersList = new ArrayList<ModelListener<Location>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Location>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(LocationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_LOCATION = "SELECT location FROM Location location";
	private static final String _SQL_SELECT_LOCATION_WHERE = "SELECT location FROM Location location WHERE ";
	private static final String _SQL_COUNT_LOCATION = "SELECT COUNT(location) FROM Location location";
	private static final String _SQL_COUNT_LOCATION_WHERE = "SELECT COUNT(location) FROM Location location WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "location.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Location exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Location exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(LocationPersistenceImpl.class);
	private static Location _nullLocation = new LocationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Location> toCacheModel() {
				return _nullLocationCacheModel;
			}
		};

	private static CacheModel<Location> _nullLocationCacheModel = new CacheModel<Location>() {
			@Override
			public Location toEntityModel() {
				return _nullLocation;
			}
		};
}