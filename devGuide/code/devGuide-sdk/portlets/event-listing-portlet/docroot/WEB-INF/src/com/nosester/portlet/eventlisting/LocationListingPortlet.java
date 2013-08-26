package com.nosester.portlet.eventlisting;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import com.nosester.portlet.eventlisting.model.Location;
import com.nosester.portlet.eventlisting.model.impl.LocationImpl;
import com.nosester.portlet.eventlisting.service.LocationLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
public class LocationListingPortlet extends MVCPortlet {

	public void addLocation(ActionRequest request, ActionResponse response)
			throws Exception {

		LocationImpl location = new LocationImpl();

		location.setName(ParamUtil.getString(request, "name"));
		location.setDescription(ParamUtil.getString(request, "description"));
		location.setStreetAddress(ParamUtil.getString(request, "streetAddress"));
		location.setCity(ParamUtil.getString(request, "city"));
		location.setStateOrProvince(ParamUtil.getString(request, "stateOrProvince"));
		location.setCountry(ParamUtil.getString(request, "country"));

		ThemeDisplay themeDisplay = (ThemeDisplay)request
				.getAttribute(WebKeys.THEME_DISPLAY);

		location.setUserId(themeDisplay.getUserId());
		location.setCompanyId(themeDisplay.getCompanyId());
		location.setGroupId(themeDisplay.getScopeGroupId());

		LocationLocalServiceUtil.addLocation(location);

		sendRedirect(request, response);
	}

	public void deleteLocation(ActionRequest request, ActionResponse response)
		throws Exception {

		long locationId = ParamUtil.getLong(request, "locationId");

		LocationLocalServiceUtil.deleteLocation(locationId);

		sendRedirect(request, response);
	}

	public void updateLocation(ActionRequest request, ActionResponse response)
		throws Exception {

		long locationId = ParamUtil.getLong(request, "locationId");

		Location location = LocationLocalServiceUtil.fetchLocation(locationId);

		location.setLocationId(ParamUtil.getLong(request, "locationId"));
		location.setName(ParamUtil.getString(request, "name"));
		location.setDescription(ParamUtil.getString(request, "description"));
		location.setStreetAddress(ParamUtil.getString(request, "streetAddress"));
		location.setCity(ParamUtil.getString(request, "city"));
		location.setStateOrProvince(ParamUtil.getString(request, "stateOrProvince"));
		location.setCountry(ParamUtil.getString(request, "country"));

		ThemeDisplay themeDisplay = (ThemeDisplay)request
				.getAttribute(WebKeys.THEME_DISPLAY);

		location.setUserId(themeDisplay.getUserId());
		location.setCompanyId(themeDisplay.getCompanyId());
		location.setGroupId(themeDisplay.getScopeGroupId());

		LocationLocalServiceUtil.updateLocation(location);

		sendRedirect(request, response);
	}

}