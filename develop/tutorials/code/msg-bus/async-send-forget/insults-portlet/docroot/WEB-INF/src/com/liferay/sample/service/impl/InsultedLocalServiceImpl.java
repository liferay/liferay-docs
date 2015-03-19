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
import com.liferay.sample.model.Insulted;
import com.liferay.sample.service.InsultedLocalServiceUtil;
import com.liferay.sample.service.base.InsultedLocalServiceBaseImpl;

/**
 * The implementation of the insulted local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.sample.service.InsultedLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nickgaskill
 * @see com.liferay.sample.service.base.InsultedLocalServiceBaseImpl
 * @see com.liferay.sample.service.InsultedLocalServiceUtil
 */
public class InsultedLocalServiceImpl extends InsultedLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.sample.service.InsultedLocalServiceUtil} to access the insulted local service.
	 */
	
	public Insulted addInsulted(String insult, ServiceContext serviceContext)
			  throws PortalException, SystemException {
				
			    long insultedId = counterLocalService.increment(Insulted.class.getName());

			    Insulted insulted = insultedPersistence.create(insultedId);

			    insulted.setInsult(insult);
			    
			    super.addInsulted(insulted);
			    
			    return insulted;
				
			}
			
	public Insulted deleteInsulted(Insulted insulted) throws SystemException {

	    return insultedPersistence.remove(insulted);
	}
	
	public Insulted deleteInsulted(long insultedId)
		throws PortalException, SystemException {

		Insulted insulted = insultedPersistence.findByPrimaryKey(insultedId);

		return deleteInsulted(insulted);
	}
	
	public Insulted getInsulted(long insultedId)
		throws SystemException, PortalException {

		return insultedPersistence.findByPrimaryKey(insultedId);
	}
	
	public Insulted updateInsulted(long insultedId, String insult, ServiceContext serviceContext)
			  throws PortalException, SystemException {
		
		Insulted insulted = InsultedLocalServiceUtil.fetchInsulted(insultedId);
		
		insulted.setInsult(insult);
	    
	    super.updateInsulted(insulted);
	    
	    return insulted;
		
	}
	
}