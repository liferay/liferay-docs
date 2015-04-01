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

package com.liferay.sample.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.sample.NoSuchInsultLogException;
import com.liferay.sample.model.InsultLog;
import com.liferay.sample.model.impl.InsultLogImpl;
import com.liferay.sample.model.impl.InsultLogModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the insult log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see InsultLogPersistence
 * @see InsultLogUtil
 * @generated
 */
public class InsultLogPersistenceImpl extends BasePersistenceImpl<InsultLog>
	implements InsultLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link InsultLogUtil} to access the insult log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = InsultLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
			InsultLogModelImpl.FINDER_CACHE_ENABLED, InsultLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
			InsultLogModelImpl.FINDER_CACHE_ENABLED, InsultLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
			InsultLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public InsultLogPersistenceImpl() {
		setModelClass(InsultLog.class);
	}

	/**
	 * Caches the insult log in the entity cache if it is enabled.
	 *
	 * @param insultLog the insult log
	 */
	@Override
	public void cacheResult(InsultLog insultLog) {
		EntityCacheUtil.putResult(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
			InsultLogImpl.class, insultLog.getPrimaryKey(), insultLog);

		insultLog.resetOriginalValues();
	}

	/**
	 * Caches the insult logs in the entity cache if it is enabled.
	 *
	 * @param insultLogs the insult logs
	 */
	@Override
	public void cacheResult(List<InsultLog> insultLogs) {
		for (InsultLog insultLog : insultLogs) {
			if (EntityCacheUtil.getResult(
						InsultLogModelImpl.ENTITY_CACHE_ENABLED,
						InsultLogImpl.class, insultLog.getPrimaryKey()) == null) {
				cacheResult(insultLog);
			}
			else {
				insultLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all insult logs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(InsultLogImpl.class.getName());
		}

		EntityCacheUtil.clearCache(InsultLogImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the insult log.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(InsultLog insultLog) {
		EntityCacheUtil.removeResult(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
			InsultLogImpl.class, insultLog.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<InsultLog> insultLogs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (InsultLog insultLog : insultLogs) {
			EntityCacheUtil.removeResult(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
				InsultLogImpl.class, insultLog.getPrimaryKey());
		}
	}

	/**
	 * Creates a new insult log with the primary key. Does not add the insult log to the database.
	 *
	 * @param insultLogId the primary key for the new insult log
	 * @return the new insult log
	 */
	@Override
	public InsultLog create(long insultLogId) {
		InsultLog insultLog = new InsultLogImpl();

		insultLog.setNew(true);
		insultLog.setPrimaryKey(insultLogId);

		return insultLog;
	}

	/**
	 * Removes the insult log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param insultLogId the primary key of the insult log
	 * @return the insult log that was removed
	 * @throws com.liferay.sample.NoSuchInsultLogException if a insult log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultLog remove(long insultLogId)
		throws NoSuchInsultLogException, SystemException {
		return remove((Serializable)insultLogId);
	}

	/**
	 * Removes the insult log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the insult log
	 * @return the insult log that was removed
	 * @throws com.liferay.sample.NoSuchInsultLogException if a insult log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultLog remove(Serializable primaryKey)
		throws NoSuchInsultLogException, SystemException {
		Session session = null;

		try {
			session = openSession();

			InsultLog insultLog = (InsultLog)session.get(InsultLogImpl.class,
					primaryKey);

			if (insultLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInsultLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(insultLog);
		}
		catch (NoSuchInsultLogException nsee) {
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
	protected InsultLog removeImpl(InsultLog insultLog)
		throws SystemException {
		insultLog = toUnwrappedModel(insultLog);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(insultLog)) {
				insultLog = (InsultLog)session.get(InsultLogImpl.class,
						insultLog.getPrimaryKeyObj());
			}

			if (insultLog != null) {
				session.delete(insultLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (insultLog != null) {
			clearCache(insultLog);
		}

		return insultLog;
	}

	@Override
	public InsultLog updateImpl(com.liferay.sample.model.InsultLog insultLog)
		throws SystemException {
		insultLog = toUnwrappedModel(insultLog);

		boolean isNew = insultLog.isNew();

		Session session = null;

		try {
			session = openSession();

			if (insultLog.isNew()) {
				session.save(insultLog);

				insultLog.setNew(false);
			}
			else {
				session.merge(insultLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
			InsultLogImpl.class, insultLog.getPrimaryKey(), insultLog);

		return insultLog;
	}

	protected InsultLog toUnwrappedModel(InsultLog insultLog) {
		if (insultLog instanceof InsultLogImpl) {
			return insultLog;
		}

		InsultLogImpl insultLogImpl = new InsultLogImpl();

		insultLogImpl.setNew(insultLog.isNew());
		insultLogImpl.setPrimaryKey(insultLog.getPrimaryKey());

		insultLogImpl.setInsultLogId(insultLog.getInsultLogId());
		insultLogImpl.setInsult(insultLog.getInsult());
		insultLogImpl.setRating(insultLog.getRating());
		insultLogImpl.setGroupId(insultLog.getGroupId());
		insultLogImpl.setCompanyId(insultLog.getCompanyId());
		insultLogImpl.setUserId(insultLog.getUserId());

		return insultLogImpl;
	}

	/**
	 * Returns the insult log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the insult log
	 * @return the insult log
	 * @throws com.liferay.sample.NoSuchInsultLogException if a insult log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInsultLogException, SystemException {
		InsultLog insultLog = fetchByPrimaryKey(primaryKey);

		if (insultLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInsultLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return insultLog;
	}

	/**
	 * Returns the insult log with the primary key or throws a {@link com.liferay.sample.NoSuchInsultLogException} if it could not be found.
	 *
	 * @param insultLogId the primary key of the insult log
	 * @return the insult log
	 * @throws com.liferay.sample.NoSuchInsultLogException if a insult log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultLog findByPrimaryKey(long insultLogId)
		throws NoSuchInsultLogException, SystemException {
		return findByPrimaryKey((Serializable)insultLogId);
	}

	/**
	 * Returns the insult log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the insult log
	 * @return the insult log, or <code>null</code> if a insult log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultLog fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		InsultLog insultLog = (InsultLog)EntityCacheUtil.getResult(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
				InsultLogImpl.class, primaryKey);

		if (insultLog == _nullInsultLog) {
			return null;
		}

		if (insultLog == null) {
			Session session = null;

			try {
				session = openSession();

				insultLog = (InsultLog)session.get(InsultLogImpl.class,
						primaryKey);

				if (insultLog != null) {
					cacheResult(insultLog);
				}
				else {
					EntityCacheUtil.putResult(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
						InsultLogImpl.class, primaryKey, _nullInsultLog);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(InsultLogModelImpl.ENTITY_CACHE_ENABLED,
					InsultLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return insultLog;
	}

	/**
	 * Returns the insult log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param insultLogId the primary key of the insult log
	 * @return the insult log, or <code>null</code> if a insult log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultLog fetchByPrimaryKey(long insultLogId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)insultLogId);
	}

	/**
	 * Returns all the insult logs.
	 *
	 * @return the insult logs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<InsultLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the insult logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of insult logs
	 * @param end the upper bound of the range of insult logs (not inclusive)
	 * @return the range of insult logs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<InsultLog> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the insult logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of insult logs
	 * @param end the upper bound of the range of insult logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of insult logs
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<InsultLog> findAll(int start, int end,
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

		List<InsultLog> list = (List<InsultLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_INSULTLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_INSULTLOG;

				if (pagination) {
					sql = sql.concat(InsultLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<InsultLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<InsultLog>(list);
				}
				else {
					list = (List<InsultLog>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the insult logs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (InsultLog insultLog : findAll()) {
			remove(insultLog);
		}
	}

	/**
	 * Returns the number of insult logs.
	 *
	 * @return the number of insult logs
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

				Query q = session.createQuery(_SQL_COUNT_INSULTLOG);

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
	 * Initializes the insult log persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.sample.model.InsultLog")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<InsultLog>> listenersList = new ArrayList<ModelListener<InsultLog>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<InsultLog>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(InsultLogImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_INSULTLOG = "SELECT insultLog FROM InsultLog insultLog";
	private static final String _SQL_COUNT_INSULTLOG = "SELECT COUNT(insultLog) FROM InsultLog insultLog";
	private static final String _ORDER_BY_ENTITY_ALIAS = "insultLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InsultLog exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(InsultLogPersistenceImpl.class);
	private static InsultLog _nullInsultLog = new InsultLogImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<InsultLog> toCacheModel() {
				return _nullInsultLogCacheModel;
			}
		};

	private static CacheModel<InsultLog> _nullInsultLogCacheModel = new CacheModel<InsultLog>() {
			@Override
			public InsultLog toEntityModel() {
				return _nullInsultLog;
			}
		};
}