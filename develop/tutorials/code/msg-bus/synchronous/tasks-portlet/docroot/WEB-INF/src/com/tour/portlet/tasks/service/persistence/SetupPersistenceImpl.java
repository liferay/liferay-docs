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

package com.tour.portlet.tasks.service.persistence;

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

import com.tour.portlet.tasks.NoSuchSetupException;
import com.tour.portlet.tasks.model.Setup;
import com.tour.portlet.tasks.model.impl.SetupImpl;
import com.tour.portlet.tasks.model.impl.SetupModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the setup service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see SetupPersistence
 * @see SetupUtil
 * @generated
 */
public class SetupPersistenceImpl extends BasePersistenceImpl<Setup>
	implements SetupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SetupUtil} to access the setup persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SetupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SetupModelImpl.ENTITY_CACHE_ENABLED,
			SetupModelImpl.FINDER_CACHE_ENABLED, SetupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SetupModelImpl.ENTITY_CACHE_ENABLED,
			SetupModelImpl.FINDER_CACHE_ENABLED, SetupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SetupModelImpl.ENTITY_CACHE_ENABLED,
			SetupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SetupPersistenceImpl() {
		setModelClass(Setup.class);
	}

	/**
	 * Caches the setup in the entity cache if it is enabled.
	 *
	 * @param setup the setup
	 */
	@Override
	public void cacheResult(Setup setup) {
		EntityCacheUtil.putResult(SetupModelImpl.ENTITY_CACHE_ENABLED,
			SetupImpl.class, setup.getPrimaryKey(), setup);

		setup.resetOriginalValues();
	}

	/**
	 * Caches the setups in the entity cache if it is enabled.
	 *
	 * @param setups the setups
	 */
	@Override
	public void cacheResult(List<Setup> setups) {
		for (Setup setup : setups) {
			if (EntityCacheUtil.getResult(SetupModelImpl.ENTITY_CACHE_ENABLED,
						SetupImpl.class, setup.getPrimaryKey()) == null) {
				cacheResult(setup);
			}
			else {
				setup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all setups.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SetupImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SetupImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the setup.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Setup setup) {
		EntityCacheUtil.removeResult(SetupModelImpl.ENTITY_CACHE_ENABLED,
			SetupImpl.class, setup.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Setup> setups) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Setup setup : setups) {
			EntityCacheUtil.removeResult(SetupModelImpl.ENTITY_CACHE_ENABLED,
				SetupImpl.class, setup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new setup with the primary key. Does not add the setup to the database.
	 *
	 * @param setupId the primary key for the new setup
	 * @return the new setup
	 */
	@Override
	public Setup create(long setupId) {
		Setup setup = new SetupImpl();

		setup.setNew(true);
		setup.setPrimaryKey(setupId);

		return setup;
	}

	/**
	 * Removes the setup with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param setupId the primary key of the setup
	 * @return the setup that was removed
	 * @throws com.tour.portlet.tasks.NoSuchSetupException if a setup with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Setup remove(long setupId)
		throws NoSuchSetupException, SystemException {
		return remove((Serializable)setupId);
	}

	/**
	 * Removes the setup with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the setup
	 * @return the setup that was removed
	 * @throws com.tour.portlet.tasks.NoSuchSetupException if a setup with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Setup remove(Serializable primaryKey)
		throws NoSuchSetupException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Setup setup = (Setup)session.get(SetupImpl.class, primaryKey);

			if (setup == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSetupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(setup);
		}
		catch (NoSuchSetupException nsee) {
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
	protected Setup removeImpl(Setup setup) throws SystemException {
		setup = toUnwrappedModel(setup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(setup)) {
				setup = (Setup)session.get(SetupImpl.class,
						setup.getPrimaryKeyObj());
			}

			if (setup != null) {
				session.delete(setup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (setup != null) {
			clearCache(setup);
		}

		return setup;
	}

	@Override
	public Setup updateImpl(com.tour.portlet.tasks.model.Setup setup)
		throws SystemException {
		setup = toUnwrappedModel(setup);

		boolean isNew = setup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (setup.isNew()) {
				session.save(setup);

				setup.setNew(false);
			}
			else {
				session.merge(setup);
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

		EntityCacheUtil.putResult(SetupModelImpl.ENTITY_CACHE_ENABLED,
			SetupImpl.class, setup.getPrimaryKey(), setup);

		return setup;
	}

	protected Setup toUnwrappedModel(Setup setup) {
		if (setup instanceof SetupImpl) {
			return setup;
		}

		SetupImpl setupImpl = new SetupImpl();

		setupImpl.setNew(setup.isNew());
		setupImpl.setPrimaryKey(setup.getPrimaryKey());

		setupImpl.setSetupId(setup.getSetupId());
		setupImpl.setName(setup.getName());
		setupImpl.setDescription(setup.getDescription());
		setupImpl.setStatus(setup.getStatus());
		setupImpl.setGroupId(setup.getGroupId());
		setupImpl.setCompanyId(setup.getCompanyId());
		setupImpl.setUserId(setup.getUserId());

		return setupImpl;
	}

	/**
	 * Returns the setup with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the setup
	 * @return the setup
	 * @throws com.tour.portlet.tasks.NoSuchSetupException if a setup with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Setup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSetupException, SystemException {
		Setup setup = fetchByPrimaryKey(primaryKey);

		if (setup == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSetupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return setup;
	}

	/**
	 * Returns the setup with the primary key or throws a {@link com.tour.portlet.tasks.NoSuchSetupException} if it could not be found.
	 *
	 * @param setupId the primary key of the setup
	 * @return the setup
	 * @throws com.tour.portlet.tasks.NoSuchSetupException if a setup with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Setup findByPrimaryKey(long setupId)
		throws NoSuchSetupException, SystemException {
		return findByPrimaryKey((Serializable)setupId);
	}

	/**
	 * Returns the setup with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the setup
	 * @return the setup, or <code>null</code> if a setup with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Setup fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Setup setup = (Setup)EntityCacheUtil.getResult(SetupModelImpl.ENTITY_CACHE_ENABLED,
				SetupImpl.class, primaryKey);

		if (setup == _nullSetup) {
			return null;
		}

		if (setup == null) {
			Session session = null;

			try {
				session = openSession();

				setup = (Setup)session.get(SetupImpl.class, primaryKey);

				if (setup != null) {
					cacheResult(setup);
				}
				else {
					EntityCacheUtil.putResult(SetupModelImpl.ENTITY_CACHE_ENABLED,
						SetupImpl.class, primaryKey, _nullSetup);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SetupModelImpl.ENTITY_CACHE_ENABLED,
					SetupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return setup;
	}

	/**
	 * Returns the setup with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param setupId the primary key of the setup
	 * @return the setup, or <code>null</code> if a setup with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Setup fetchByPrimaryKey(long setupId) throws SystemException {
		return fetchByPrimaryKey((Serializable)setupId);
	}

	/**
	 * Returns all the setups.
	 *
	 * @return the setups
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Setup> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the setups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.SetupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of setups
	 * @param end the upper bound of the range of setups (not inclusive)
	 * @return the range of setups
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Setup> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the setups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.tour.portlet.tasks.model.impl.SetupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of setups
	 * @param end the upper bound of the range of setups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of setups
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Setup> findAll(int start, int end,
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

		List<Setup> list = (List<Setup>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SETUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SETUP;

				if (pagination) {
					sql = sql.concat(SetupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Setup>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Setup>(list);
				}
				else {
					list = (List<Setup>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the setups from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Setup setup : findAll()) {
			remove(setup);
		}
	}

	/**
	 * Returns the number of setups.
	 *
	 * @return the number of setups
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

				Query q = session.createQuery(_SQL_COUNT_SETUP);

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
	 * Initializes the setup persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.tour.portlet.tasks.model.Setup")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Setup>> listenersList = new ArrayList<ModelListener<Setup>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Setup>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SetupImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SETUP = "SELECT setup FROM Setup setup";
	private static final String _SQL_COUNT_SETUP = "SELECT COUNT(setup) FROM Setup setup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "setup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Setup exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SetupPersistenceImpl.class);
	private static Setup _nullSetup = new SetupImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Setup> toCacheModel() {
				return _nullSetupCacheModel;
			}
		};

	private static CacheModel<Setup> _nullSetupCacheModel = new CacheModel<Setup>() {
			@Override
			public Setup toEntityModel() {
				return _nullSetup;
			}
		};
}