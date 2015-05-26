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
import com.tour.portlet.tasks.model.Inventory;
import com.tour.portlet.tasks.service.InventoryLocalServiceUtil;
import com.tour.portlet.tasks.service.base.InventoryLocalServiceBaseImpl;

/**
 * The implementation of the inventory local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.tour.portlet.tasks.service.InventoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author nickgaskill
 * @see com.tour.portlet.tasks.service.base.InventoryLocalServiceBaseImpl
 * @see com.tour.portlet.tasks.service.InventoryLocalServiceUtil
 */
public class InventoryLocalServiceImpl extends InventoryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.tour.portlet.tasks.service.InventoryLocalServiceUtil} to access the inventory local service.
	 */
	
	public Inventory addInventory(String name, String description, ServiceContext serviceContext)
			  throws PortalException, SystemException {
				
			    long inventoryId = counterLocalService.increment(Inventory.class.getName());

			    Inventory inventory = inventoryPersistence.create(inventoryId);

			    inventory.setName(name);
			    inventory.setDescription(description);
			    
			    super.addInventory(inventory);
			    
			    return inventory;
				
			}
			
			public Inventory deleteInventory(Inventory inventory) throws SystemException {

			    return inventoryPersistence.remove(inventory);
			}
			
			public Inventory deleteInventory(long inventoryId)
				throws PortalException, SystemException {

				Inventory inventory = inventoryPersistence.findByPrimaryKey(inventoryId);

				return deleteInventory(inventory);
			}
			
			public Inventory getInventory(long inventoryId)
				throws SystemException, PortalException {

				return inventoryPersistence.findByPrimaryKey(inventoryId);
			}
			
			public Inventory updateInventory(long inventoryId, String name, String description, ServiceContext serviceContext)
					  throws PortalException, SystemException {
				
				Inventory inventory = InventoryLocalServiceUtil.fetchInventory(inventoryId);
				
				inventory.setName(name);
				inventory.setDescription(description);
			    
			    super.updateInventory(inventory);
			    
			    return inventory;
				
			}
	
}