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

package com.liferay.docs.insult.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.docs.insult.service.base.InsultLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.docs.insult.model.Insult;
import com.liferay.docs.insult.service.InsultLocalServiceUtil;

/**
 * The implementation of the insult local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.docs.insult.service.InsultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author joebloggs
 * @see com.liferay.docs.insult.service.base.InsultLocalServiceBaseImpl
 * @see com.liferay.docs.insult.service.InsultLocalServiceUtil
 */
public class InsultLocalServiceImpl extends InsultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.docs.insult.service.InsultLocalServiceUtil} to access the insult local service.
	 */
	
	public Insult addInsult(long userId, long groupId,
	        String insultString, ServiceContext serviceContext)
	    throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		
		Date now = new Date();
		
		long insultId = counterLocalService.increment(Insult.class.getName());

	    Insult insult = insultPersistence.create(insultId);

	    insult.setUserId(user.getUserId());
	    insult.setUserName(user.getFullName());
	    insult.setGroupId(groupId);
	    insult.setCompanyId(user.getCompanyId());
	    insult.setInsultString(insultString);
	    insult.setCreateDate(serviceContext.getCreateDate(now));
	    insult.setModifiedDate(serviceContext.getModifiedDate(now));

	    super.addInsult(insult);
	    
	    resourceLocalService.addResources(
                insult.getCompanyId(), insult.getGroupId(), insult.getUserId(),
                Insult.class.getName(), insult.getInsultId(), false,
                true, true);

		long classTypeId = 0;
		boolean visible = true;
		Date startDate = null;
		Date endDate = null;
		Date expirationDate = null;
		String mimeType = ContentTypes.TEXT_HTML;
		String title = insult.getInsultString();
		String description = insult.getInsultString();
		String summary = insult.getInsultString();
		String url = null;
		String layoutUuid = null;
		int height = 0;
		int width = 0;
		Integer priority = null;
		boolean sync = false;

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, groupId, insult.getCreateDate(),
			insult.getModifiedDate(), Insult.class.getName(),
			insult.getInsultId(), insult.getUuid(), classTypeId,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(), visible, startDate, endDate,
			expirationDate, mimeType, title, description, summary, url,
			layoutUuid, height, width, priority, sync);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Insult.class);
		indexer.reindex(insult);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
			AssetLinkConstants.TYPE_RELATED);

	    return insult;
	}
	
	public Insult deleteInsult(Insult insult) throws PortalException, SystemException {
		
		resourceLocalService.deleteResource(insult.getCompanyId(),
				Insult.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				insult.getPrimaryKey());
	    	    
	    return insultPersistence.remove(insult);
	}
	
	public Insult deleteInsult(long insultId)
		    throws PortalException, SystemException {

		    Insult insult = insultPersistence.findByPrimaryKey(insultId);

			AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                    Insult.class.getName(), insultId);

			assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

			assetEntryLocalService.deleteEntry(
				Insult.class.getName(), insult.getInsultId());

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Insult.class);
			indexer.delete(insult);

		    return deleteInsult(insult);
		}
	
	public Insult updateInsult(
	        long insultId, String insultString, ServiceContext serviceContext)
	    throws PortalException, SystemException {
		long groupId = serviceContext.getScopeGroupId();

	    Insult insult = InsultLocalServiceUtil.fetchInsult(insultId);
	    long userId = serviceContext.getUserId();
	    
	    Date now = new Date();

	    insult.setInsultString(insultString);
	    insult.setModifiedDate(serviceContext.getModifiedDate(now));

	    super.updateInsult(insult);

		long classTypeId = 0;
		boolean visible = true;
		Date startDate = null;
		Date endDate = null;
		Date expirationDate = null;
		String mimeType = ContentTypes.TEXT_HTML;
		String title = insult.getInsultString();
		String description = insult.getInsultString();
		String summary = insult.getInsultString();
		String url = null;
		String layoutUuid = null;
		int height = 0;
		int width = 0;
		Integer priority = null;
		boolean sync = false;

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, groupId, insult.getCreateDate(),
			insult.getModifiedDate(), Insult.class.getName(),
			insult.getInsultId(), insult.getUuid(), classTypeId,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(), visible, startDate, endDate,
			expirationDate, mimeType, title, description, summary, url,
			layoutUuid, height, width, priority, sync);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Insult.class);
		indexer.reindex(insult);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
			AssetLinkConstants.TYPE_RELATED);

	    return insult;
	}
	
	public List<Insult> getInsultsByGroupId(long groupId) throws SystemException {

		return insultPersistence.findByGroupId(groupId);
	}
	
	public List<Insult> getInsultsByGroupId(long groupId, int start, int end) throws SystemException {

		return insultPersistence.findByGroupId(groupId, start, end);
	}
	
	public int getInsultsCountByGroupId(long groupId) throws SystemException {

		return insultPersistence.countByGroupId(groupId);
	}
	
}