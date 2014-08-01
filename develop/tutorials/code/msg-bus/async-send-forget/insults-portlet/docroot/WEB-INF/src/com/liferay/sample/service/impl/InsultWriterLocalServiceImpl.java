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
import com.liferay.sample.model.InsultWriter;
import com.liferay.sample.service.InsultWriterLocalServiceUtil;
import com.liferay.sample.service.base.InsultWriterLocalServiceBaseImpl;


/**
 * The implementation of the insult writer local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.sample.service.InsultWriterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nickgaskill
 * @see com.liferay.sample.service.base.InsultWriterLocalServiceBaseImpl
 * @see com.liferay.sample.service.InsultWriterLocalServiceUtil
 */
public class InsultWriterLocalServiceImpl
	extends InsultWriterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.sample.service.InsultWriterLocalServiceUtil} to access the insult writer local service.
	 */
	
	public InsultWriter addInsultWriter(String insult, String rating, ServiceContext serviceContext)
			  throws PortalException, SystemException {
				
			    long insultWriterId = counterLocalService.increment(InsultWriter.class.getName());

			    InsultWriter insultwriter = insultWriterPersistence.create(insultWriterId);

			    insultwriter.setInsult(insult);
			    insultwriter.setRating(rating);
			    
			    super.addInsultWriter(insultwriter);
			    
			    return insultwriter;
				
			}
			
	public InsultWriter deleteInsultWriter(InsultWriter insultwriter) throws SystemException {

	    return insultWriterPersistence.remove(insultwriter);
	}
	
	public InsultWriter deleteInsultWriter(long insultWriterId)
		throws PortalException, SystemException {

		InsultWriter insultwriter = insultWriterPersistence.findByPrimaryKey(insultWriterId);

		return deleteInsultWriter(insultwriter);
	}
	
	public InsultWriter getInsultWriter(long insultWriterId)
		throws SystemException, PortalException {

		return insultWriterPersistence.findByPrimaryKey(insultWriterId);
	}
	
	public InsultWriter updateInsultWriter(long insultWriterId, String insult, String rating, ServiceContext serviceContext)
			  throws PortalException, SystemException {
		
		InsultWriter insultwriter = InsultWriterLocalServiceUtil.fetchInsultWriter(insultWriterId);
		
		insultwriter.setInsult(insult);
		insultwriter.setRating(rating);
	    
	    super.updateInsultWriter(insultwriter);
	    
	    return insultwriter;
		
	}
}