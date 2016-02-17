package com.liferay.docs.exampleconfig;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import aQute.bnd.annotation.metatype.Configurable;

@Component(
	configurationPid = "com.liferay.docs.exampleconfig.ExampleConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class ExampleConfigPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		renderRequest.setAttribute(
			ExampleConfiguration.class.getName(), _configuration);
		
		super.doView(renderRequest, renderResponse);
	}

	
	public String getFavoriteColor(Map colors) {
		return (String) colors.get(_configuration.favoriteColor());
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = Configurable.createConfigurable(
		ExampleConfiguration.class, properties);
	}	

	private volatile ExampleConfiguration _configuration;

}