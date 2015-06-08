/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferay.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=Sample portlet",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)

/**
 *
 * @author rsezov
 */
public class SamplePortlet {

@Override
public void doView( renderRequest request, RenderResponse response) 
	throws IOException {

	PrintWriter writer = response.getWriter();
	writer.print("Sample Portlet is working!");
}
	
}
