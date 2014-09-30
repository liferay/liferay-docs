package com.samples.portlet.eventlisting.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {  

		super.processAction(portletConfig, actionRequest, actionResponse);

		PortletPreferences prefs = actionRequest.getPreferences();

		String showLocationAddress = prefs.getValue(
				"showLocationAddress", "true");

		System.out.println("showLocationAddress=" + showLocationAddress +
				" in ConfigurationActionImpl.processAction().");
	}
}