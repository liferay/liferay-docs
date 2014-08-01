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
import com.liferay.sample.model.InsultLog;
import com.liferay.sample.service.InsultLogLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class InsultLogPortlet
 */
public class InsultLogPortlet extends MVCPortlet {
	
	public void addInsultLog(ActionRequest request, ActionResponse response)
            throws Exception {

        _updateInsultLog(request);

        sendRedirect(request, response);
    }
	
	public void deleteInsultLog(ActionRequest request, ActionResponse response)
	        throws Exception {

	        long insultLogId = ParamUtil.getLong(request, "insultLogId");

	        InsultLogLocalServiceUtil.deleteInsultLog(insultLogId);

	        sendRedirect(request, response);
	    }
	
	public void updateInsultLog(ActionRequest request, ActionResponse response)
	        throws Exception {

	        _updateInsultLog(request);

	        sendRedirect(request, response);
	    }
	
	private InsultLog _updateInsultLog(ActionRequest request)
	        throws PortalException, SystemException {
		
		long insultLogId = ParamUtil.getLong(request, "insultLogId");
        String insult = ParamUtil.getString(request, "insult");
        String rating = ParamUtil.getString(request, "rating");
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                InsultLog.class.getName(), request);

        InsultLog insultlog = null;
        
        if (insultLogId <= 0) {
        	insultlog = InsultLogLocalServiceUtil.addInsultLog(
                insult, rating, serviceContext);
        }
        else {
        	insultlog = InsultLogLocalServiceUtil.getInsultLog(insultLogId);

        	insultlog = InsultLogLocalServiceUtil.updateInsultLog(
        			insultLogId, insult, rating, serviceContext);
        }

        return insultlog;
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(InsultLogPortlet.class);  

}
