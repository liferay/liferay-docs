package com.tour.portlet.tasks;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tour.portlet.tasks.model.Setup;
import com.tour.portlet.tasks.service.SetupLocalServiceUtil;

/**
 * Portlet implementation class SetupPortlet
 */
public class SetupPortlet extends MVCPortlet {
	
	public void addSetup(ActionRequest request, ActionResponse response)
            throws Exception {

        _updateSetup(request);

        sendRedirect(request, response);
    }
	
	public void deleteSetup(ActionRequest request, ActionResponse response)
	        throws Exception {

	        long setupId = ParamUtil.getLong(request, "setupId");

	        SetupLocalServiceUtil.deleteSetup(setupId);

	        sendRedirect(request, response);
	    }
	
	public void updateSetup(ActionRequest request, ActionResponse response)
	        throws Exception {

	        _updateSetup(request);

	        sendRedirect(request, response);
	    }
	
	private Setup _updateSetup(ActionRequest request)
	        throws PortalException, SystemException {
		
		long setupId = ParamUtil.getLong(request, "setupId");
        String name = ParamUtil.getString(request, "name");
        String description = ParamUtil.getString(request, "description");
        String status = ParamUtil.getString(request, "status");
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Setup.class.getName(), request);

        Setup setup = null;
        
        if (setupId <= 0) {
        	setup = SetupLocalServiceUtil.addSetup(
                name, description, status, serviceContext);
        }
        else {
        	setup = SetupLocalServiceUtil.getSetup(setupId);

        	setup = SetupLocalServiceUtil.updateSetup(
        			setupId, name, description, status, serviceContext);
        }

        return setup;
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(SetupPortlet.class); 

}
