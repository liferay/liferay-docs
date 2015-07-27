package com.liferay.docs.exampleconfigurationportlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import com.liferay.docs.exampleconfigurationportlet.configuration.ExampleConfigurationPortletConfiguration;
import com.liferay.portal.kernel.settings.SettingsException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;

import aQute.bnd.annotation.metatype.Configurable;

@Component(
	configurationPid = "com.liferay.docs.exampleconfigurationportlet.configuration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Example Configuration Portlet",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ExampleConfigurationPortlet extends GenericPortlet {
	
	public String getDefaultLanguageLabel(Map labels) {
		return (String) labels.get(_configuration.defaultLanguage());
	}
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = Configurable.createConfigurable(
            ExampleConfigurationPortletConfiguration.class, properties);
	}
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {

		PrintWriter printWriter = response.getWriter();

		printWriter.print("Example Configuration Portlet is working!");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		try {
			ExampleConfigurationPortletConfiguration configuration =
                portletDisplay.getPortletInstanceConfiguration(
                    ExampleConfigurationPortletConfiguration.class);
		}
		catch (SettingsException se) {
			se.printStackTrace();
		}
	}
	
	private volatile ExampleConfigurationPortletConfiguration _configuration;

}
