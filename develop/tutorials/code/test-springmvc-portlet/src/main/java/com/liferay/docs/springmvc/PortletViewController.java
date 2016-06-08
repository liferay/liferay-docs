/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.docs.springmvc;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ReleaseInfo;

@Controller
@RequestMapping("VIEW")
public class PortletViewController {

	public void destroy() {
		serviceTracker.close();
	}

	@RenderMapping
	public String question(Model model) {
		model.addAttribute("releaseInfo", ReleaseInfo.getReleaseInfo());

		// Test a service tracker
		serviceTracker.open();
		if (!serviceTracker.isEmpty()) {

			UserLocalService userLocalService = (UserLocalService) serviceTracker.getService();
			int count = userLocalService.getUsersCount();
			System.out.println(count);
		}
		return "test-springmvc-portlet/view";
	}
		//Set up a service tracker
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		BundleContext bundleContext = bundle.getBundleContext();
		ServiceTracker<?, ?> serviceTracker = new ServiceTracker<>(bundleContext, UserLocalService.class, null);
}