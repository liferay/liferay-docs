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

package com.samples.portlet.eventlisting.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

/**
 * @author Joe Bloggs
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
		PortletConfig portletConfig, ActionRequest actionRequest,
		ActionResponse actionResponse) throws Exception {  

		super.processAction(portletConfig, actionRequest, actionResponse);

		PortletPreferences prefs = actionRequest.getPreferences();

		String showLocationAddress = prefs.getValue("showLocationAddress", "true");

		System.out.println("showLocationAddress=" + showLocationAddress +
			" in ConfigurationActionImpl.processAction().");
	}
}