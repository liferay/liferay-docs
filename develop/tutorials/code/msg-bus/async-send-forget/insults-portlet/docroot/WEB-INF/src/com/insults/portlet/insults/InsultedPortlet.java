package com.insults.portlet.insults;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.sample.model.Insulted;
import com.liferay.sample.service.InsultedLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class InsultedPortlet
 */
public class InsultedPortlet extends MVCPortlet {
	
	public void addInsulted(ActionRequest request, ActionResponse response)
            throws Exception {

        _updateInsulted(request);

        sendRedirect(request, response);
    }
	
	public void deleteInsulted(ActionRequest request, ActionResponse response)
	        throws Exception {

	        long insultedId = ParamUtil.getLong(request, "insultedId");

	        InsultedLocalServiceUtil.deleteInsulted(insultedId);

	        sendRedirect(request, response);
	    }
	
	public void updateInsulted(ActionRequest request, ActionResponse response)
	        throws Exception {

	        _updateInsulted(request);

	        sendRedirect(request, response);
	    }
	
	private Insulted _updateInsulted(ActionRequest request)
	        throws PortalException, SystemException {
		
		long insultedId = ParamUtil.getLong(request, "insultedId");
        String insult = ParamUtil.getString(request, "insult");
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Insulted.class.getName(), request);

        Insulted insulted = null;
        
        if (insultedId <= 0) {
        	insulted = InsultedLocalServiceUtil.addInsulted(
                insult, serviceContext);
        }
        else {
        	insulted = InsultedLocalServiceUtil.getInsulted(insultedId);

        	insulted = InsultedLocalServiceUtil.updateInsulted(
        			insultedId, insult, serviceContext);
        }

        return insulted;
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(InsultedPortlet.class);   

}
