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

import com.liferay.sample.NoSuchInsultedException;
import com.liferay.sample.model.Insulted;
import com.liferay.sample.model.impl.InsultedImpl;
import com.liferay.sample.model.impl.InsultedModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the insulted service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see InsultedPersistence
 * @see InsultedUtil
 * @generated
 */
public class InsultedPersistenceImpl extends BasePersistenceImpl<Insulted>
	implements InsultedPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link InsultedUtil} to access the insulted persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = InsultedImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InsultedModelImpl.ENTITY_CACHE_ENABLED,
			InsultedModelImpl.FINDER_CACHE_ENABLED, InsultedImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InsultedModelImpl.ENTITY_CACHE_ENABLED,
			InsultedModelImpl.FINDER_CACHE_ENABLED, InsultedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InsultedModelImpl.ENTITY_CACHE_ENABLED,
			InsultedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public InsultedPersistenceImpl() {
		setModelClass(Insulted.class);
	}

	/**
	 * Caches the insulted in the entity cache if it is enabled.
	 *
	 * @param insulted the insulted
	 */
	@Override
	public void cacheResult(Insulted insulted) {
		EntityCacheUtil.putResult(InsultedModelImpl.ENTITY_CACHE_ENABLED,
			InsultedImpl.class, insulted.getPrimaryKey(), insulted);

		insulted.resetOriginalValues();
	}

	/**
	 * Caches the insulteds in the entity cache if it is enabled.
	 *
	 * @param insulteds the insulteds
	 */
	@Override
	public void cacheResult(List<Insulted> insulteds) {
		for (Insulted insulted : insulteds) {
			if (EntityCacheUtil.getResult(
						InsultedModelImpl.ENTITY_CACHE_ENABLED,
						InsultedImpl.class, insulted.getPrimaryKey()) == null) {
				cacheResult(insulted);
			}
			else {
				insulted.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all insulteds.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(InsultedImpl.class.getName());
		}

		EntityCacheUtil.clearCache(InsultedImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the insulted.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Insulted insulted) {
		EntityCacheUtil.removeResult(InsultedModelImpl.ENTITY_CACHE_ENABLED,
			InsultedImpl.class, insulted.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Insulted> insulteds) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Insulted insulted : insulteds) {
			EntityCacheUtil.removeResult(InsultedModelImpl.ENTITY_CACHE_ENABLED,
				InsultedImpl.class, insulted.getPrimaryKey());
		}
	}

	/**
	 * Creates a new insulted with the primary key. Does not add the insulted to the database.
	 *
	 * @param insultedId the primary key for the new insulted
	 * @return the new insulted
	 */
	@Override
	public Insulted create(long insultedId) {
		Insulted insulted = new InsultedImpl();

		insulted.setNew(true);
		insulted.setPrimaryKey(insultedId);

		return insulted;
	}

	/**
	 * Removes the insulted with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param insultedId the primary key of the insulted
	 * @return the insulted that was removed
	 * @throws com.liferay.sample.NoSuchInsultedException if a insulted with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Insulted remove(long insultedId)
		throws NoSuchInsultedException, SystemException {
		return remove((Serializable)insultedId);
	}

	/**
	 * Removes the insulted with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the insulted
	 * @return the insulted that was removed
	 * @throws com.liferay.sample.NoSuchInsultedException if a insulted with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Insulted remove(Serializable primaryKey)
		throws NoSuchInsultedException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Insulted insulted = (Insulted)session.get(InsultedImpl.class,
					primaryKey);

			if (insulted == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInsultedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(insulted);
		}
		catch (NoSuchInsultedException nsee) {
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
	protected Insulted removeImpl(Insulted insulted) throws SystemException {
		insulted = toUnwrappedModel(insulted);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(insulted)) {
				insulted = (Insulted)session.get(InsultedImpl.class,
						insulted.getPrimaryKeyObj());
			}

			if (insulted != null) {
				session.delete(insulted);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (insulted != null) {
			clearCache(insulted);
		}

		return insulted;
	}

	@Override
	public Insulted updateImpl(com.liferay.sample.model.Insulted insulted)
		throws SystemException {
		insulted = toUnwrappedModel(insulted);

		boolean isNew = insulted.isNew();

		Session session = null;

		try {
			session = openSession();

			if (insulted.isNew()) {
				session.save(insulted);

				insulted.setNew(false);
			}
			else {
				session.merge(insulted);
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

		EntityCacheUtil.putResult(InsultedModelImpl.ENTITY_CACHE_ENABLED,
			InsultedImpl.class, insulted.getPrimaryKey(), insulted);

		return insulted;
	}

	protected Insulted toUnwrappedModel(Insulted insulted) {
		if (insulted instanceof InsultedImpl) {
			return insulted;
		}

		InsultedImpl insultedImpl = new InsultedImpl();

		insultedImpl.setNew(insulted.isNew());
		insultedImpl.setPrimaryKey(insulted.getPrimaryKey());

		insultedImpl.setInsultedId(insulted.getInsultedId());
		insultedImpl.setInsult(insulted.getInsult());
		insultedImpl.setGroupId(insulted.getGroupId());
		insultedImpl.setCompanyId(insulted.getCompanyId());
		insultedImpl.setUserId(insulted.getUserId());

		return insultedImpl;
	}

	/**
	 * Returns the insulted with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the insulted
	 * @return the insulted
	 * @throws com.liferay.sample.NoSuchInsultedException if a insulted with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Insulted findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInsultedException, SystemException {
		Insulted insulted = fetchByPrimaryKey(primaryKey);

		if (insulted == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInsultedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return insulted;
	}

	/**
	 * Returns the insulted with the primary key or throws a {@link com.liferay.sample.NoSuchInsultedException} if it could not be found.
	 *
	 * @param insultedId the primary key of the insulted
	 * @return the insulted
	 * @throws com.liferay.sample.NoSuchInsultedException if a insulted with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Insulted findByPrimaryKey(long insultedId)
		throws NoSuchInsultedException, SystemException {
		return findByPrimaryKey((Serializable)insultedId);
	}

	/**
	 * Returns the insulted with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the insulted
	 * @return the insulted, or <code>null</code> if a insulted with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Insulted fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Insulted insulted = (Insulted)EntityCacheUtil.getResult(InsultedModelImpl.ENTITY_CACHE_ENABLED,
				InsultedImpl.class, primaryKey);

		if (insulted == _nullInsulted) {
			return null;
		}

		if (insulted == null) {
			Session session = null;

			try {
				session = openSession();

				insulted = (Insulted)session.get(InsultedImpl.class, primaryKey);

				if (insulted != null) {
					cacheResult(insulted);
				}
				else {
					EntityCacheUtil.putResult(InsultedModelImpl.ENTITY_CACHE_ENABLED,
						InsultedImpl.class, primaryKey, _nullInsulted);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(InsultedModelImpl.ENTITY_CACHE_ENABLED,
					InsultedImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return insulted;
	}

	/**
	 * Returns the insulted with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param insultedId the primary key of the insulted
	 * @return the insulted, or <code>null</code> if a insulted with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Insulted fetchByPrimaryKey(long insultedId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)insultedId);
	}

	/**
	 * Returns all the insulteds.
	 *
	 * @return the insulteds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Insulted> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the insulteds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of insulteds
	 * @param end the upper bound of the range of insulteds (not inclusive)
	 * @return the range of insulteds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Insulted> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the insulteds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of insulteds
	 * @param end the upper bound of the range of insulteds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of insulteds
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Insulted> findAll(int start, int end,
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

		List<Insulted> list = (List<Insulted>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_INSULTED);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_INSULTED;

				if (pagination) {
					sql = sql.concat(InsultedModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Insulted>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Insulted>(list);
				}
				else {
					list = (List<Insulted>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the insulteds from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Insulted insulted : findAll()) {
			remove(insulted);
		}
	}

	/**
	 * Returns the number of insulteds.
	 *
	 * @return the number of insulteds
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

				Query q = session.createQuery(_SQL_COUNT_INSULTED);

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
	 * Initializes the insulted persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.sample.model.Insulted")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Insulted>> listenersList = new ArrayList<ModelListener<Insulted>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Insulted>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(InsultedImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_INSULTED = "SELECT insulted FROM Insulted insulted";
	private static final String _SQL_COUNT_INSULTED = "SELECT COUNT(insulted) FROM Insulted insulted";
	private static final String _ORDER_BY_ENTITY_ALIAS = "insulted.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Insulted exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(InsultedPersistenceImpl.class);
	private static Insulted _nullInsulted = new InsultedImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Insulted> toCacheModel() {
				return _nullInsultedCacheModel;
			}
		};

	private static CacheModel<Insulted> _nullInsultedCacheModel = new CacheModel<Insulted>() {
			@Override
			public Insulted toEntityModel() {
				return _nullInsulted;
			}
		};
}