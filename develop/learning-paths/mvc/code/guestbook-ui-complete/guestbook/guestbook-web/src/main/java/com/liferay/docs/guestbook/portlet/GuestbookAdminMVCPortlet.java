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

package com.liferay.docs.guestbook.portlet;

import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Guestbooks",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.portlet-title-based-navigation=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/guestbookadminmvcportlet/view.jsp",
		"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class GuestbookAdminMVCPortlet extends MVCPortlet {

	public void addGuestbook(ActionRequest request, ActionResponse response)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Guestbook.class.getName(), request);

		String name = ParamUtil.getString(request, "name");

		try {
			_guestbookService.addGuestbook(
				serviceContext.getUserId(), name, serviceContext);

			SessionMessages.add(request, "guestbookAdded");
		}
		catch (PortalException pe) {
			Class<?> clazz = pe.getClass();

			SessionErrors.add(request, clazz.getName());

			response.setRenderParameter(
				"mvcPath", "/html/guestbookadminmvcportlet/edit_guestbook.jsp");
		}
	}

	public void deleteGuestbook(ActionRequest request, ActionResponse response)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Guestbook.class.getName(), request);

		long guestbookId = ParamUtil.getLong(request, "guestbookId");

		try {
			_guestbookService.deleteGuestbook(guestbookId, serviceContext);

			SessionMessages.add(request, "guestbookDeleted");
		}
		catch (PortalException pe) {
			Class<?> clazz = pe.getClass();

			SessionErrors.add(request, clazz.getName());
		}
	}

	public void updateGuestbook(ActionRequest request, ActionResponse response)
		throws PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Guestbook.class.getName(), request);

		String name = ParamUtil.getString(request, "name");
		long guestbookId = ParamUtil.getLong(request, "guestbookId");

		try {
			_guestbookService.updateGuestbook(
				serviceContext.getUserId(), guestbookId, name, serviceContext);

			SessionMessages.add(request, "guestbookUpdated");
		}
		catch (PortalException pe) {
			Class<?> clazz = pe.getClass();

			SessionErrors.add(request, clazz.getName());

			response.setRenderParameter(
				"mvcPath", "/html/guestbookadminmvcportlet/edit_guestbook.jsp");
		}
	}

	@Reference(unbind = "-")
	protected void setGuestbookService(GuestbookService guestbookService) {
		_guestbookService = guestbookService;
	}

	private GuestbookService _guestbookService;

}