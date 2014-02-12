package com.nosester.portlet.eventlisting.template;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateConstants;
import com.liferay.util.portlet.PortletProps;

import com.nosester.portlet.eventlisting.model.Location;
import com.nosester.portlet.eventlisting.util.PortletKeys;

import java.util.List;
import java.util.Locale;
import java.util.Map;
public class LocationListingPortletDisplayTemplateHandler
		extends BasePortletDisplayTemplateHandler {

	public String getClassName() {
		return Location.class.getName();
	}

	public String getName(Locale locale) {
		String locations = LanguageUtil.get(locale, PortletKeys.LOCATIONS);

		return locations.concat(StringPool.SPACE).concat(
				LanguageUtil.get(locale, "template"));
	}

	public String getResourceName() {
		return PortletKeys.LOCATION_LISTING_PORTLET_ID;
	}

	public String getTemplatesHelpPath(String language) {
		return PortletProps.get(
			getTemplatesHelpPropertyKey(), new Filter(language));
	}

	@Override
	public String getTemplatesHelpPropertyKey() {
		return "locations.portlet.display.templates.help";
	}

	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		TemplateVariableGroup templateVariableGroup =
			templateVariableGroups.get("fields");

		templateVariableGroup.empty();

		templateVariableGroup.addCollectionVariable(
			"locations", List.class, PortletDisplayTemplateConstants.ENTRIES,
			"location", Location.class, "curLocation", "name");

		return templateVariableGroups;
	}

}