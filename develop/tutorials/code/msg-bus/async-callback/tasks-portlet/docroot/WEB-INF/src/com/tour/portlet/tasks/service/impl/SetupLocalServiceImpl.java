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

package com.tour.portlet.tasks.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.tour.portlet.tasks.model.Setup;
import com.tour.portlet.tasks.service.SetupLocalServiceUtil;
import com.tour.portlet.tasks.service.base.SetupLocalServiceBaseImpl;

/**
 * The implementation of the setup local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.tour.portlet.tasks.service.SetupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nickgaskill
 * @see com.tour.portlet.tasks.service.base.SetupLocalServiceBaseImpl
 * @see com.tour.portlet.tasks.service.SetupLocalServiceUtil
 */
public class SetupLocalServiceImpl extends SetupLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.tour.portlet.tasks.service.SetupLocalServiceUtil} to access the setup local service.
	 */
	
	public Setup addSetup(String name, String description, String status, ServiceContext serviceContext)
			  throws PortalException, SystemException {
				
			    long setupId = counterLocalService.increment(Setup.class.getName());

			    Setup setup = setupPersistence.create(setupId);

			    setup.setName(name);
			    setup.setDescription(description);
			    setup.setStatus(status);
			    
			    super.addSetup(setup);
			    
			    return setup;
				
			}
			
			public Setup deleteSetup(Setup setup) throws SystemException {

			    return setupPersistence.remove(setup);
			}
			
			public Setup deleteSetup(long setupId)
				throws PortalException, SystemException {

				Setup setup = setupPersistence.findByPrimaryKey(setupId);

				return deleteSetup(setup);
			}
			
			public Setup getSetup(long setupId)
				throws SystemException, PortalException {

				return setupPersistence.findByPrimaryKey(setupId);
			}
			
			public Setup updateSetup(long setupId, String name, String description, String status, ServiceContext serviceContext)
					  throws PortalException, SystemException {
				
				Setup setup = SetupLocalServiceUtil.fetchSetup(setupId);
				
				setup.setName(name);
				setup.setDescription(description);
				setup.setStatus(status);
			    
			    super.updateSetup(setup);
			    
			    return setup;
				
			}
	
}