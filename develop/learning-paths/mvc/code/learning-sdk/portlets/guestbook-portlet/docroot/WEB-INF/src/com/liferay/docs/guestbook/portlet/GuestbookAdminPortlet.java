package com.liferay.docs.guestbook.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class GuestbookAdminPortlet
 */
public class GuestbookAdminPortlet extends MVCPortlet {

	public void addGuestbook(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Guestbook.class.getName(), request);

		String name = ParamUtil.getString(request, "name");

		try {
			GuestbookServiceUtil.addGuestbook(serviceContext.getUserId(),
					name, serviceContext);
			
			SessionMessages.add(request, "guestbookAdded");
		} catch (PortalException e) {
			SessionErrors.add(request, e.getClass().getName());

			response.setRenderParameter("mvcPath",
					"/html/guestbookadmin/edit_guestbook.jsp");
		}
	}

	public void updateGuestbook(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Guestbook.class.getName(), request);

		String name = ParamUtil.getString(request, "name");
		long guestbookId = ParamUtil.getLong(request, "guestbookId");

		try {
			GuestbookServiceUtil.updateGuestbook(serviceContext.getUserId(), guestbookId,
					name, serviceContext);
			
			SessionMessages.add(request, "guestbookUpdated");
		} catch (PortalException pe) {
			SessionErrors.add(request, pe.getClass().getName());

			response.setRenderParameter("mvcPath",
					"/html/guestbookadmin/edit_guestbook.jsp");
		}
	}

	public void deleteGuestbook(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Guestbook.class.getName(), request);

		long guestbookId = ParamUtil.getLong(request, "guestbookId");
		
		try {
			GuestbookServiceUtil.deleteGuestbook(guestbookId, serviceContext);
			
			SessionMessages.add(request, "guestbookDeleted");
		} catch (PortalException pe) {
			SessionErrors.add(request, pe.getClass().getName());
		}
	}

}
