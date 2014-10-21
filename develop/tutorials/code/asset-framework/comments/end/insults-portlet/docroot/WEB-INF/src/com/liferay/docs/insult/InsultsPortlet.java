package com.liferay.docs.insult;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.docs.insult.model.Insult;
import com.liferay.docs.insult.service.InsultLocalServiceUtil;

/**
 * Portlet implementation class InsultsPortlet
 */
public class InsultsPortlet extends MVCPortlet {

	public void addInsult(ActionRequest request, ActionResponse response)
            throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		
		if (themeDisplay.getPermissionChecker()
				.hasPermission(groupId, "com.liferay.docs.insult.model",
						groupId, "ADD_INSULT")) {

	        String insultString = ParamUtil.getString(request, "insultString");
	
	        ServiceContext serviceContext = ServiceContextFactory.getInstance(
	            Insult.class.getName(), request);
	        
	        InsultLocalServiceUtil.addInsult(serviceContext.getUserId(), serviceContext.getScopeGroupId(), 
            		insultString, serviceContext);
	        
	        sendRedirect(request, response);
	        
		} else {
			SessionErrors.add(request, "permission-error");
			sendRedirect(request, response);
		}
        
    }

    public void deleteInsult(ActionRequest request, ActionResponse response)
        throws Exception {
    	
    	ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();

        long insultId = ParamUtil.getLong(request, "insultId");
        
        if (themeDisplay.getPermissionChecker().hasPermission(groupId,
				"com.liferay.docs.insult.model.Insult",
				insultId, "DELETE")) {

        	InsultLocalServiceUtil.deleteInsult(insultId);
        	
        	sendRedirect(request, response);
        	
        } else {
        	SessionErrors.add(request, "permission-error");
			sendRedirect(request, response);
        }
        
    }

    public void updateInsult(ActionRequest request, ActionResponse response)
        throws Exception {

    	ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		
		long insultId = ParamUtil.getLong(request, "insultId");
		Insult insult = null;
		
		try {
			insult = InsultLocalServiceUtil.fetchInsult(insultId);
		} catch (SystemException se) {
			_log.error(se);
			return;
		}
		
		if (themeDisplay.getPermissionChecker()
				.hasPermission(groupId, "com.liferay.docs.insult.model.Insult",
						insult.getInsultId(), "UPDATE")) {

			String insultString = ParamUtil.getString(request, "insultString");
	
	        ServiceContext serviceContext = ServiceContextFactory.getInstance(
	            Insult.class.getName(), request);
	        
	        InsultLocalServiceUtil.updateInsult(
	                insultId, insultString, serviceContext);
	        
	        sendRedirect(request, response);
	        
		} else {
			SessionErrors.add(request, "permission-error");
			sendRedirect(request, response);
		}
		
    }

    private static Log _log = LogFactoryUtil.getLog(InsultsPortlet.class);

}
