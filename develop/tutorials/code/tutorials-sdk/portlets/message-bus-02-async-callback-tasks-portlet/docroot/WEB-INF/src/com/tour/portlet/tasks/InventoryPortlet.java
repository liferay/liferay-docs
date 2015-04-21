package com.tour.portlet.tasks;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tour.portlet.tasks.model.Inventory;
import com.tour.portlet.tasks.service.InventoryLocalServiceUtil;

/**
 * Portlet implementation class InventoryPortlet
 */
public class InventoryPortlet extends MVCPortlet {
	
	public void addInventory(ActionRequest request, ActionResponse response)
            throws Exception {

        _updateInventory(request);

        sendRedirect(request, response);
    }
	
	public void deleteInventory(ActionRequest request, ActionResponse response)
	        throws Exception {

	        long inventoryId = ParamUtil.getLong(request, "inventoryId");

	        InventoryLocalServiceUtil.deleteInventory(inventoryId);

	        sendRedirect(request, response);
	    }
	
	public void updateInventory(ActionRequest request, ActionResponse response)
	        throws Exception {

	        _updateInventory(request);

	        sendRedirect(request, response);
	    }
	
	private Inventory _updateInventory(ActionRequest request)
	        throws PortalException, SystemException {
		
		long inventoryId = ParamUtil.getLong(request, "inventoryId");
        String name = ParamUtil.getString(request, "name");
        String description = ParamUtil.getString(request, "description");
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
        		Inventory.class.getName(), request);

        Inventory inventory = null;
        
        if (inventoryId <= 0) {
        	inventory = InventoryLocalServiceUtil.addInventory(
                name, description, serviceContext);
        }
        else {
        	inventory = InventoryLocalServiceUtil.getInventory(inventoryId);

        	inventory = InventoryLocalServiceUtil.updateInventory(
        			inventoryId, name, description, serviceContext);
        }

        return inventory;
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(InventoryPortlet.class);  

}
