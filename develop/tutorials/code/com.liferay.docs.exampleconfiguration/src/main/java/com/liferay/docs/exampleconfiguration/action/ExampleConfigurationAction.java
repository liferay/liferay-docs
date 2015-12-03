/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.docs.exampleconfiguration.action;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.liferay.docs.exampleconfiguration.configuration.ExampleConfiguration;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import aQute.bnd.annotation.metatype.Configurable;

@Component(
	configurationPid = "com.liferay.docs.exampleconfig.configuration.ExampleConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=com_liferay_docs_exampleconfig_portlet_ExampleConfigPortlet"
	},
	service = ConfigurationAction.class
)
public class ExampleConfigurationAction extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String favoriteColor = ParamUtil.getString(actionRequest, "favoriteColor");
		setPreference(actionRequest, "favoriteColor", favoriteColor);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(
		PortletConfig portletConfig, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) throws Exception {

		httpServletRequest.setAttribute(
			ExampleConfiguration.class.getName(),
			_exampleConfiguration);

		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_exampleConfiguration = Configurable.createConfigurable(
			ExampleConfiguration.class, properties);
	}

	private volatile ExampleConfiguration _exampleConfiguration;

}
