package com.nosester.portlet.eventlisting;

import java.text.SimpleDateFormat;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.nosester.portlet.eventlisting.model.Event;
import com.nosester.portlet.eventlisting.model.impl.EventImpl;
import com.nosester.portlet.eventlisting.service.EventLocalServiceUtil;

public class EventListingPortlet extends MVCPortlet {

	public void addEvent(ActionRequest request, ActionResponse response)
			throws Exception {

		Event event = eventFromRequest(request);

		EventLocalServiceUtil.addEvent(event);

		sendRedirect(request, response);
	}
	
	public void updateEvent(ActionRequest request, ActionResponse response)
		throws Exception {

		Event event = eventFromRequest(request);

		EventLocalServiceUtil.updateEvent(event);

		sendRedirect(request, response);
	}

	public void deleteEvent(ActionRequest request, ActionResponse response)
		throws Exception {

		long eventId = ParamUtil.getLong(request, "eventId");

		EventLocalServiceUtil.deleteEvent(eventId);

		sendRedirect(request, response);
	}	

	private Event eventFromRequest(PortletRequest request) {

		EventImpl event = new EventImpl();

		event.setEventId(ParamUtil.getLong(request, "eventId"));
		event.setName(ParamUtil.getString(request, "name"));
		event.setDescription(ParamUtil.getString(request, "description"));
		event.setLocationId(ParamUtil.getLong(request, "locationId"));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		event.setDate(ParamUtil.getDate(request, "date", dateFormat));
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

		event.setUserId(themeDisplay.getUserId());
		event.setCompanyId(themeDisplay.getCompanyId());
		event.setGroupId(themeDisplay.getScopeGroupId());
		
		return event;
	}

	private static Log _log = LogFactoryUtil.getLog(EventListingPortlet.class);

}
