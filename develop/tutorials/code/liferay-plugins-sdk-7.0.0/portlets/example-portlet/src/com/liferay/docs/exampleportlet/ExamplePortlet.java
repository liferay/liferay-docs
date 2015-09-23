package com.liferay.docs.exampleportlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Example Portlet",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ExamplePortlet extends GenericPortlet {
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {

		PrintWriter printWriter = response.getWriter();

		printWriter.print("Example Portlet is working!");
	}

}
