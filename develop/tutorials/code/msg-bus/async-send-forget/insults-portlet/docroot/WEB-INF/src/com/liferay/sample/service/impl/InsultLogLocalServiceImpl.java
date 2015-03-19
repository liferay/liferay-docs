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

package com.liferay.sample.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.sample.model.InsultLog;
import com.liferay.sample.service.InsultLogLocalServiceUtil;
import com.liferay.sample.service.base.InsultLogLocalServiceBaseImpl;

/**
 * The implementation of the insult log local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.sample.service.InsultLogLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nickgaskill
 * @see com.liferay.sample.service.base.InsultLogLocalServiceBaseImpl
 * @see com.liferay.sample.service.InsultLogLocalServiceUtil
 */
public class InsultLogLocalServiceImpl extends InsultLogLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.sample.service.InsultLogLocalServiceUtil} to access the insult log local service.
	 */
	
	public InsultLog addInsultLog(String insult, String rating, ServiceContext serviceContext)
			  throws PortalException, SystemException {
				
			    long insultLogId = counterLocalService.increment(InsultLog.class.getName());

			    InsultLog insultlog = insultLogPersistence.create(insultLogId);

			    insultlog.setInsult(insult);
			    insultlog.setRating(rating);
			    
			    super.addInsultLog(insultlog);
			    
			    return insultlog;
				
			}
			
	public InsultLog deleteInsultLog(InsultLog insultlog) throws SystemException {

	    return insultLogPersistence.remove(insultlog);
	}
	
	public InsultLog deleteInsultLog(long insultLogId)
		throws PortalException, SystemException {

		InsultLog insultlog = insultLogPersistence.findByPrimaryKey(insultLogId);

		return deleteInsultLog(insultlog);
	}
	
	public InsultLog getInsultLog(long insultLogId)
		throws SystemException, PortalException {

		return insultLogPersistence.findByPrimaryKey(insultLogId);
	}
	
	public InsultLog updateInsultLog(long insultLogId, String insult, String rating, ServiceContext serviceContext)
			  throws PortalException, SystemException {
		
		InsultLog insultlog = InsultLogLocalServiceUtil.fetchInsultLog(insultLogId);
		
		insultlog.setInsult(insult);
		insultlog.setRating(rating);
	    
	    super.updateInsultLog(insultlog);
	    
	    return insultlog;
		
	}
}