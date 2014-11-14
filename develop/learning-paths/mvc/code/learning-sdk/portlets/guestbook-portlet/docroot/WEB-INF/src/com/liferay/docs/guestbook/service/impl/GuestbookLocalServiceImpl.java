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

package com.liferay.docs.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.docs.guestbook.GuestbookNameException;
import com.liferay.docs.guestbook.NoSuchGuestbookException;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.base.GuestbookLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;

/**
 * The implementation of the guestbook local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.docs.guestbook.service.GuestbookLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Rich Sezov
 * @see com.liferay.docs.guestbook.service.base.GuestbookLocalServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil
 */
public class GuestbookLocalServiceImpl extends GuestbookLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil} to access
	 * the guestbook local service.
	 */

	public Guestbook getGuestbookByName(String name, OrderByComparator orderByComparator) 
			throws SystemException, NoSuchGuestbookException {
		return guestbookPersistence.findByGuestbookName_First(name, orderByComparator);
	}
	
	public List<Guestbook> getGuestbooks(long groupId) throws SystemException {
		return guestbookPersistence.findByGroupId(groupId);
	}

	public List<Guestbook> getGuestbooks(long groupId, int start, int end)
			throws SystemException {
		return guestbookPersistence.findByGroupId(groupId, start, end);
	}
	
	public int getGuestbooksCount(long groupId) throws SystemException {
		return guestbookPersistence.countByGroupId(groupId);
	}

	public Guestbook addGuestbook(long userId, String name,
			ServiceContext serviceContext) throws SystemException,
			PortalException {

		long groupId = serviceContext.getScopeGroupId();

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(name);

		long guestbookId = counterLocalService.increment();

		Guestbook guestbook = guestbookPersistence.create(guestbookId);

		guestbook.setUuid(serviceContext.getUuid());
		guestbook.setUserId(userId);
		guestbook.setGroupId(groupId);
		guestbook.setCompanyId(user.getCompanyId());
		guestbook.setUserName(user.getFullName());
		guestbook.setCreateDate(serviceContext.getCreateDate(now));
		guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
		guestbook.setName(name);
		guestbook.setExpandoBridgeAttributes(serviceContext);

		guestbookPersistence.update(guestbook);

		resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
				Guestbook.class.getName(), guestbookId, false, true, true);
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
				groupId, guestbook.getCreateDate(),
				guestbook.getModifiedDate(), Guestbook.class.getName(),
				guestbookId, guestbook.getUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, guestbook.getName(), null, null, null,
				null, 0, 0, null, false);
		
		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				Guestbook.class);

		indexer.reindex(guestbook);

		return guestbook;

	}

	public Guestbook updateGuestbook(long userId, long guestbookId,
			String name, ServiceContext serviceContext) throws PortalException,
			SystemException {

		Date now = new Date();

		validate(name);

		Guestbook guestbook = getGuestbook(guestbookId);
		
		User user = UserLocalServiceUtil.getUser(userId);

		guestbook.setUserId(userId);
		guestbook.setUserName(user.getFullName());
		guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
		guestbook.setName(name);
		guestbook.setExpandoBridgeAttributes(serviceContext);

		guestbookPersistence.update(guestbook);

		resourceLocalService.updateResources(serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), name, guestbookId,
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(guestbook.getUserId(),
				guestbook.getGroupId(), guestbook.getCreateDate(),
				guestbook.getModifiedDate(), Guestbook.class.getName(),
				guestbookId, guestbook.getUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, guestbook.getName(), null, null, null,
				null, 0, 0, null, false);
		
		assetLinkLocalService.updateLinks(serviceContext.getUserId(),
				assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				Guestbook.class);

		indexer.reindex(guestbook);

		return guestbook;
	}

	public Guestbook deleteGuestbook(long guestbookId,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		Guestbook guestbook = getGuestbook(guestbookId);

		List<Entry> entries = EntryLocalServiceUtil.getEntries(
				serviceContext.getScopeGroupId(), guestbookId);

		for (Entry entry : entries) {
			EntryLocalServiceUtil.deleteEntry(entry.getEntryId(),
					serviceContext);
		}

		resourceLocalService.deleteResource(serviceContext.getCompanyId(),
				Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
				guestbookId);
		
		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
				Guestbook.class.getName(), guestbookId);
		
		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

		assetEntryLocalService.deleteEntry(assetEntry);
		
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				Guestbook.class);

		indexer.delete(guestbook);
		
		guestbook = deleteGuestbook(guestbookId);

		return guestbook;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new GuestbookNameException();
		}
	}

}