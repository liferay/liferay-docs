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

package com.sample.portlet.insults.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.model.ResourceConstants;
import com.sample.portlet.insults.model.Insult;
import com.sample.portlet.insults.service.InsultLocalServiceUtil;
import com.sample.portlet.insults.service.base.InsultLocalServiceBaseImpl;

/**
 * The implementation of the insult local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.sample.portlet.insults.service.InsultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nickgaskill
 * @see com.sample.portlet.insults.service.base.InsultLocalServiceBaseImpl
 * @see com.sample.portlet.insults.service.InsultLocalServiceUtil
 */
public class InsultLocalServiceImpl extends InsultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.sample.portlet.insults.service.InsultLocalServiceUtil} to access the insult local service.
	 */
	
	public Insult addInsult(long userId, long groupId,
	        String insultString, ServiceContext serviceContext)
	    throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		
		long insultId = counterLocalService.increment(Insult.class.getName());

	    Insult insult = insultPersistence.create(insultId);

	    insult.setUserId(user.getUserId());
	    insult.setGroupId(groupId);
	    insult.setCompanyId(user.getCompanyId());
	    insult.setInsultString(insultString);

	    super.addInsult(insult);
	    
	    try {
	    	resourceLocalService.addResources(
	                insult.getCompanyId(), insult.getGroupId(), insult.getUserId(),
	                Insult.class.getName(), insult.getInsultId(), false,
	                true, true);
	    } catch (Exception e) {
	    	System.out.println("Adding resources...");
	    	e.printStackTrace();
	    }
	    
	    assetEntryLocalService.updateEntry(
				userId, insult.getGroupId(), Insult.class.getName(),
				insult.getInsultId(), serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames());
	    
	    Indexer indexer = IndexerRegistryUtil.getIndexer(Insult.class);
		indexer.reindex(insult);

	    return insult;
	}
	
	public Insult deleteInsult(Insult insult) throws SystemException {
		
		try {
			resourceLocalService.deleteResource(insult.getCompanyId(),
					Insult.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					insult.getPrimaryKey());
		} catch (Exception e) {
			System.out.println("Deleting resources...");
			e.printStackTrace();
		}
		
		try {
			assetEntryLocalService.deleteEntry(
					Insult.class.getName(), insult.getInsultId());
		} catch (Exception e) {
			System.out.println("Deleting assets...");
			e.printStackTrace();
		}
		
		Indexer indexer = IndexerRegistryUtil.getIndexer(Insult.class);
		try {
			indexer.delete(insult);
		} catch (Exception e) {
			System.out.println("Removing from index...");
			e.printStackTrace();
		}
	    	    
	    return insultPersistence.remove(insult);
	}
	
	public Insult deleteInsult(long insultId)
		    throws PortalException, SystemException {

		    Insult insult = insultPersistence.findByPrimaryKey(insultId);

		    return deleteInsult(insult);
		}
	
	public Insult getInsult(long insultId)
		    throws SystemException, PortalException {

		    return insultPersistence.findByPrimaryKey(insultId);
		}
	
	public Insult updateInsult(
	        long insultId, String insultString, ServiceContext serviceContext)
	    throws PortalException, SystemException {

	    Insult insult = InsultLocalServiceUtil.fetchInsult(insultId);
	    long userId = serviceContext.getUserId();

	    insult.setInsultString(insultString);

	    super.updateInsult(insult);
	    
	    assetEntryLocalService.updateEntry(
				userId, insult.getGroupId(), Insult.class.getName(),
				insult.getInsultId(), serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames());
	    
	    Indexer indexer = IndexerRegistryUtil.getIndexer(Insult.class);
		indexer.reindex(insult);

	    return insult;
	}
	
}