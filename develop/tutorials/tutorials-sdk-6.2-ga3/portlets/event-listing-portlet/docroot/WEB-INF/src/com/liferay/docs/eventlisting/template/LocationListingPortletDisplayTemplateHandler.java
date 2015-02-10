package com.liferay.docs.eventlisting.template;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.docs.eventlisting.util.PortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateConstants;
import com.nosester.portlet.eventlisting.model.Location;

public class LocationListingPortletDisplayTemplateHandler extends
        BasePortletDisplayTemplateHandler {

    public String getClassName() {
        return Location.class.getName();
    }

    public String getName(Locale locale) {
        String locations = LanguageUtil.get(locale, "locations");

        return locations.concat(StringPool.SPACE).concat(
                LanguageUtil.get(locale, "template"));
    }

    public String getResourceName() {
        return PortletKeys.LOCATION_LISTING_PORTLET_ID;
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