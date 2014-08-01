package com.insults.portlet.insults;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.sample.model.InsultWriter;
import com.liferay.sample.service.InsultWriterLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;


/**
 * Portlet implementation class InsultWriterPortlet
 */
public class InsultWriterPortlet extends MVCPortlet {
	
	public void addInsultWriter(ActionRequest request, ActionResponse response)
            throws Exception {

        _updateInsultWriter(request);

        sendRedirect(request, response);
    }
	
	public void deleteInsultWriter(ActionRequest request, ActionResponse response)
	        throws Exception {

	        long insultWriterId = ParamUtil.getLong(request, "insultWriterId");

	        InsultWriterLocalServiceUtil.deleteInsultWriter(insultWriterId);

	        sendRedirect(request, response);
	    }
	
	public void updateInsultWriter(ActionRequest request, ActionResponse response)
	        throws Exception {

	        _updateInsultWriter(request);

	        sendRedirect(request, response);
	    }
	
	private InsultWriter _updateInsultWriter(ActionRequest request)
	        throws PortalException, SystemException {
		
		long insultWriterId = ParamUtil.getLong(request, "insultWriterId");
        String insult = ParamUtil.getString(request, "insult");
        String rating = ParamUtil.getString(request, "rating");
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                InsultWriter.class.getName(), request);

        InsultWriter insultwriter = null;
        
        if (insultWriterId <= 0) {
        	insultwriter = InsultWriterLocalServiceUtil.addInsultWriter(
                insult, rating, serviceContext);
        	
        	// Begin Message Bus...
        	
        	JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        	
        	jsonObject.put("insult", insult);
        	jsonObject.put("rating", rating);
        	
        	MessageBusUtil.sendMessage("insults/users", jsonObject.toString());
        	
        	// End Message Bus...
        	
        }
        else {
        	insultwriter = InsultWriterLocalServiceUtil.getInsultWriter(insultWriterId);

        	insultwriter = InsultWriterLocalServiceUtil.updateInsultWriter(
        			insultWriterId, insult, rating, serviceContext);
        }

        return insultwriter;
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(InsultWriterPortlet.class); 

}
