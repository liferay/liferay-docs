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

import com.liferay.sample.NoSuchInsultWriterException;
import com.liferay.sample.model.InsultWriter;
import com.liferay.sample.model.impl.InsultWriterImpl;
import com.liferay.sample.model.impl.InsultWriterModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the insult writer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author nickgaskill
 * @see InsultWriterPersistence
 * @see InsultWriterUtil
 * @generated
 */
public class InsultWriterPersistenceImpl extends BasePersistenceImpl<InsultWriter>
	implements InsultWriterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link InsultWriterUtil} to access the insult writer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = InsultWriterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
			InsultWriterModelImpl.FINDER_CACHE_ENABLED, InsultWriterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
			InsultWriterModelImpl.FINDER_CACHE_ENABLED, InsultWriterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
			InsultWriterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public InsultWriterPersistenceImpl() {
		setModelClass(InsultWriter.class);
	}

	/**
	 * Caches the insult writer in the entity cache if it is enabled.
	 *
	 * @param insultWriter the insult writer
	 */
	@Override
	public void cacheResult(InsultWriter insultWriter) {
		EntityCacheUtil.putResult(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
			InsultWriterImpl.class, insultWriter.getPrimaryKey(), insultWriter);

		insultWriter.resetOriginalValues();
	}

	/**
	 * Caches the insult writers in the entity cache if it is enabled.
	 *
	 * @param insultWriters the insult writers
	 */
	@Override
	public void cacheResult(List<InsultWriter> insultWriters) {
		for (InsultWriter insultWriter : insultWriters) {
			if (EntityCacheUtil.getResult(
						InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
						InsultWriterImpl.class, insultWriter.getPrimaryKey()) == null) {
				cacheResult(insultWriter);
			}
			else {
				insultWriter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all insult writers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(InsultWriterImpl.class.getName());
		}

		EntityCacheUtil.clearCache(InsultWriterImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the insult writer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(InsultWriter insultWriter) {
		EntityCacheUtil.removeResult(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
			InsultWriterImpl.class, insultWriter.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<InsultWriter> insultWriters) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (InsultWriter insultWriter : insultWriters) {
			EntityCacheUtil.removeResult(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
				InsultWriterImpl.class, insultWriter.getPrimaryKey());
		}
	}

	/**
	 * Creates a new insult writer with the primary key. Does not add the insult writer to the database.
	 *
	 * @param insultWriterId the primary key for the new insult writer
	 * @return the new insult writer
	 */
	@Override
	public InsultWriter create(long insultWriterId) {
		InsultWriter insultWriter = new InsultWriterImpl();

		insultWriter.setNew(true);
		insultWriter.setPrimaryKey(insultWriterId);

		return insultWriter;
	}

	/**
	 * Removes the insult writer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param insultWriterId the primary key of the insult writer
	 * @return the insult writer that was removed
	 * @throws com.liferay.sample.NoSuchInsultWriterException if a insult writer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultWriter remove(long insultWriterId)
		throws NoSuchInsultWriterException, SystemException {
		return remove((Serializable)insultWriterId);
	}

	/**
	 * Removes the insult writer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the insult writer
	 * @return the insult writer that was removed
	 * @throws com.liferay.sample.NoSuchInsultWriterException if a insult writer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultWriter remove(Serializable primaryKey)
		throws NoSuchInsultWriterException, SystemException {
		Session session = null;

		try {
			session = openSession();

			InsultWriter insultWriter = (InsultWriter)session.get(InsultWriterImpl.class,
					primaryKey);

			if (insultWriter == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInsultWriterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(insultWriter);
		}
		catch (NoSuchInsultWriterException nsee) {
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
	protected InsultWriter removeImpl(InsultWriter insultWriter)
		throws SystemException {
		insultWriter = toUnwrappedModel(insultWriter);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(insultWriter)) {
				insultWriter = (InsultWriter)session.get(InsultWriterImpl.class,
						insultWriter.getPrimaryKeyObj());
			}

			if (insultWriter != null) {
				session.delete(insultWriter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (insultWriter != null) {
			clearCache(insultWriter);
		}

		return insultWriter;
	}

	@Override
	public InsultWriter updateImpl(
		com.liferay.sample.model.InsultWriter insultWriter)
		throws SystemException {
		insultWriter = toUnwrappedModel(insultWriter);

		boolean isNew = insultWriter.isNew();

		Session session = null;

		try {
			session = openSession();

			if (insultWriter.isNew()) {
				session.save(insultWriter);

				insultWriter.setNew(false);
			}
			else {
				session.merge(insultWriter);
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

		EntityCacheUtil.putResult(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
			InsultWriterImpl.class, insultWriter.getPrimaryKey(), insultWriter);

		return insultWriter;
	}

	protected InsultWriter toUnwrappedModel(InsultWriter insultWriter) {
		if (insultWriter instanceof InsultWriterImpl) {
			return insultWriter;
		}

		InsultWriterImpl insultWriterImpl = new InsultWriterImpl();

		insultWriterImpl.setNew(insultWriter.isNew());
		insultWriterImpl.setPrimaryKey(insultWriter.getPrimaryKey());

		insultWriterImpl.setInsultWriterId(insultWriter.getInsultWriterId());
		insultWriterImpl.setInsult(insultWriter.getInsult());
		insultWriterImpl.setRating(insultWriter.getRating());
		insultWriterImpl.setGroupId(insultWriter.getGroupId());
		insultWriterImpl.setCompanyId(insultWriter.getCompanyId());
		insultWriterImpl.setUserId(insultWriter.getUserId());

		return insultWriterImpl;
	}

	/**
	 * Returns the insult writer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the insult writer
	 * @return the insult writer
	 * @throws com.liferay.sample.NoSuchInsultWriterException if a insult writer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultWriter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInsultWriterException, SystemException {
		InsultWriter insultWriter = fetchByPrimaryKey(primaryKey);

		if (insultWriter == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInsultWriterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return insultWriter;
	}

	/**
	 * Returns the insult writer with the primary key or throws a {@link com.liferay.sample.NoSuchInsultWriterException} if it could not be found.
	 *
	 * @param insultWriterId the primary key of the insult writer
	 * @return the insult writer
	 * @throws com.liferay.sample.NoSuchInsultWriterException if a insult writer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultWriter findByPrimaryKey(long insultWriterId)
		throws NoSuchInsultWriterException, SystemException {
		return findByPrimaryKey((Serializable)insultWriterId);
	}

	/**
	 * Returns the insult writer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the insult writer
	 * @return the insult writer, or <code>null</code> if a insult writer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultWriter fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		InsultWriter insultWriter = (InsultWriter)EntityCacheUtil.getResult(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
				InsultWriterImpl.class, primaryKey);

		if (insultWriter == _nullInsultWriter) {
			return null;
		}

		if (insultWriter == null) {
			Session session = null;

			try {
				session = openSession();

				insultWriter = (InsultWriter)session.get(InsultWriterImpl.class,
						primaryKey);

				if (insultWriter != null) {
					cacheResult(insultWriter);
				}
				else {
					EntityCacheUtil.putResult(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
						InsultWriterImpl.class, primaryKey, _nullInsultWriter);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(InsultWriterModelImpl.ENTITY_CACHE_ENABLED,
					InsultWriterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return insultWriter;
	}

	/**
	 * Returns the insult writer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param insultWriterId the primary key of the insult writer
	 * @return the insult writer, or <code>null</code> if a insult writer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public InsultWriter fetchByPrimaryKey(long insultWriterId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)insultWriterId);
	}

	/**
	 * Returns all the insult writers.
	 *
	 * @return the insult writers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<InsultWriter> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the insult writers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultWriterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of insult writers
	 * @param end the upper bound of the range of insult writers (not inclusive)
	 * @return the range of insult writers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<InsultWriter> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the insult writers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sample.model.impl.InsultWriterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of insult writers
	 * @param end the upper bound of the range of insult writers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of insult writers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<InsultWriter> findAll(int start, int end,
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

		List<InsultWriter> list = (List<InsultWriter>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_INSULTWRITER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_INSULTWRITER;

				if (pagination) {
					sql = sql.concat(InsultWriterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<InsultWriter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<InsultWriter>(list);
				}
				else {
					list = (List<InsultWriter>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the insult writers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (InsultWriter insultWriter : findAll()) {
			remove(insultWriter);
		}
	}

	/**
	 * Returns the number of insult writers.
	 *
	 * @return the number of insult writers
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

				Query q = session.createQuery(_SQL_COUNT_INSULTWRITER);

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
	 * Initializes the insult writer persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.sample.model.InsultWriter")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<InsultWriter>> listenersList = new ArrayList<ModelListener<InsultWriter>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<InsultWriter>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(InsultWriterImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_INSULTWRITER = "SELECT insultWriter FROM InsultWriter insultWriter";
	private static final String _SQL_COUNT_INSULTWRITER = "SELECT COUNT(insultWriter) FROM InsultWriter insultWriter";
	private static final String _ORDER_BY_ENTITY_ALIAS = "insultWriter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InsultWriter exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(InsultWriterPersistenceImpl.class);
	private static InsultWriter _nullInsultWriter = new InsultWriterImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<InsultWriter> toCacheModel() {
				return _nullInsultWriterCacheModel;
			}
		};

	private static CacheModel<InsultWriter> _nullInsultWriterCacheModel = new CacheModel<InsultWriter>() {
			@Override
			public InsultWriter toEntityModel() {
				return _nullInsultWriter;
			}
		};
}