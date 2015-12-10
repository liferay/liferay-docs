package com.liferay.docs.exampleconfigaction.portlet;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import com.liferay.docs.exampleconfigaction.configuration.ExampleConfiguration;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import aQute.bnd.annotation.metatype.Configurable;

@Component(
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
public class ExampleConfigurationActionPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		renderRequest.setAttribute(
			ExampleConfiguration.class.getName(),
			_exampleConfiguration);

		super.doView(renderRequest, renderResponse);
	}

	public String getFavoriteColor(Map labels) {
		return (String) labels.get(_exampleConfiguration.favoriteColor());
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_exampleConfiguration = Configurable.createConfigurable(
			ExampleConfiguration.class, properties);
	}

	private volatile ExampleConfiguration _exampleConfiguration;

}