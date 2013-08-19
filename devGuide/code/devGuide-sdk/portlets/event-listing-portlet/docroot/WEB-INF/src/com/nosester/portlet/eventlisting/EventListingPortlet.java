package com.nosester.portlet.eventlisting;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import com.nosester.portlet.eventlisting.model.Event;
import com.nosester.portlet.eventlisting.model.impl.EventImpl;
import com.nosester.portlet.eventlisting.service.EventLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

public class EventListingPortlet extends MVCPortlet {

	public void addEvent(ActionRequest request, ActionResponse response)
			throws Exception {

		Event event = eventFromRequest(request);

		EventLocalServiceUtil.addEvent(event);

		sendRedirect(request, response);
	}

	public void deleteEvent(ActionRequest request, ActionResponse response)
		throws Exception {

		long eventId = ParamUtil.getLong(request, "eventId");

		EventLocalServiceUtil.deleteEvent(eventId);

		sendRedirect(request, response);
	}

	public void updateEvent(ActionRequest request, ActionResponse response)
		throws Exception {

		long eventId = ParamUtil.getLong(request, "eventId");

		Event event = eventFromRequest(request);

		EventLocalServiceUtil.updateEvent(event);

		sendRedirect(request, response);
	}

	private Event eventFromRequest(ActionRequest request) {

		EventImpl event = new EventImpl();

		event.setEventId(ParamUtil.getLong(request, "eventId"));
		event.setName(ParamUtil.getString(request, "name"));
		event.setDescription(ParamUtil.getString(request, "description"));
		event.setLocationId(ParamUtil.getLong(request, "locationId"));
		
		event.setDate(_extractDate(request));
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

		event.setUserId(themeDisplay.getUserId());
		event.setCompanyId(themeDisplay.getCompanyId());
		event.setGroupId(themeDisplay.getScopeGroupId());
		
		return event;
	}

	private Date _extractDate(ActionRequest request) {
		int year = ParamUtil.getInteger(request, "dateYear");
		int month = ParamUtil.getInteger(request, "dateMonth");
		int day = ParamUtil.getInteger(request, "dateDay");
		int hour = ParamUtil.getInteger(request, "dateHour");
		int minute = ParamUtil.getInteger(request, "dateMinute");
		int amPm = ParamUtil.getInteger(request, "dateAmPm");

		if (amPm == Calendar.PM) {
			hour += 12;
		}

		Calendar dateCal = CalendarFactoryUtil.getCalendar();
		dateCal.set(year, month, day, hour, minute);
		Date date = dateCal.getTime();
		return date;
	}

	private static Log _log = LogFactoryUtil.getLog(EventListingPortlet.class);

}