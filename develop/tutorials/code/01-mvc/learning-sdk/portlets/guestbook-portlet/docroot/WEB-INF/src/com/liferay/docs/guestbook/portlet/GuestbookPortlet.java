package com.liferay.docs.guestbook.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.docs.guestbook.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class GuestbookPortlet
 */
public class GuestbookPortlet extends MVCPortlet {

	public void addEntry(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Entry.class.getName(), request);

		String userName = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		String message = ParamUtil.getString(request, "message");
		String guestbookName = ParamUtil.getString(request, "guestbookName");
		long guestbookId = ParamUtil.getLong(request, "guestbookId");
		long entryId = ParamUtil.getLong(request, "entryId");

		if (entryId > 0) {
			try {
				EntryServiceUtil.updateEntry(serviceContext.getUserId(),
						guestbookId, entryId, userName, email, message,
						serviceContext);

				SessionMessages.add(request, "entryAdded");

				response.setRenderParameter("guestbookName", guestbookName);
			} catch (Exception e) {
				SessionErrors.add(request, e.getClass().getName());

				PortalUtil.copyRequestParameters(request, response);

				response.setRenderParameter("mvcPath",
						"/html/guestbook/edit_entry.jsp");
			}
		} else {
			try {
				EntryServiceUtil.addEntry(serviceContext.getUserId(),
						guestbookId, userName, email, message, serviceContext);

				SessionMessages.add(request, "entryAdded");

				response.setRenderParameter("guestbookName", guestbookName);
			} catch (Exception e) {
				SessionErrors.add(request, e.getClass().getName());

				PortalUtil.copyRequestParameters(request, response);

				response.setRenderParameter("mvcPath",
						"/html/guestbook/edit_entry.jsp");
			}
		}
	}

	public void deleteEntry(ActionRequest request, ActionResponse response) {

		long entryId = ParamUtil.getLong(request, "entryId");
		long guestbookId = ParamUtil.getLong(request, "guestbookId");

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Entry.class.getName(), request);

			response.setRenderParameter("guestbookId",
					Long.toString(guestbookId));

			EntryServiceUtil.deleteEntry(entryId, serviceContext);
		} catch (Exception e) {

			SessionErrors.add(request, e.getClass().getName());
		}
	}

	public void addGuestbook(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Guestbook.class.getName(), request);

		String name = ParamUtil.getString(request, "guestbookName");

		try {
			GuestbookServiceUtil.addGuestbook(serviceContext.getUserId(), name,
					serviceContext);

			SessionMessages.add(request, "guestbookAdded");

		} catch (Exception e) {
			SessionErrors.add(request, e.getMessage());

			response.setRenderParameter("mvcPath",
					"/html/guestbook/edit_guestbook.jsp");
		}
	}

	@Override
	public void render(RenderRequest renderRequest,
			RenderResponse renderResponse) throws PortletException, IOException {

		try {

			Guestbook guestbook = null;

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Guestbook.class.getName(), renderRequest);

			String guestbookName = "";

			long groupId = serviceContext.getScopeGroupId();

			// First, get all the guestbooks to populate the tabs
			List<Guestbook> guestbooks = GuestbookServiceUtil
					.getGuestbooks(groupId);

			if (guestbooks.size() == 0) {
				guestbook = GuestbookServiceUtil.addGuestbook(
						serviceContext.getUserId(), "Main", serviceContext);

				// If we had to create the default guestbook, put it in the
				// request
				renderRequest.setAttribute(WebKeys.GUESTBOOK, guestbook);
			}

			// Now we check to see if the user selected a guestbook
			guestbook = (Guestbook) renderRequest
					.getAttribute(WebKeys.GUESTBOOK);

			if (guestbook == null) {

				// The user still could have selected a guestbook
				guestbookName = ParamUtil.getString(renderRequest,
						"guestbookName");
				if (guestbookName.equalsIgnoreCase("")) {

					guestbook = guestbooks.get(0);

				} else {

					OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil
							.getOrderByComparatorFactory();
					OrderByComparator orderByComparator = orderByComparatorFactory
							.create("guestbook", "name", true);

					guestbook = GuestbookServiceUtil.getGuestbookByG_N(
							serviceContext.getScopeGroupId(), guestbookName,
							orderByComparator);
				}

			}

			renderRequest.setAttribute(WebKeys.GUESTBOOK, guestbook);

		} catch (Exception e) {

			throw new PortletException(e);
		}

		super.render(renderRequest, renderResponse);
	}

}
