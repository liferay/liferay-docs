package com.liferay.docs.formnavextensionportlet;

import java.io.IOException;
import java.util.Locale;

import javax.portlet.PortletPreferences;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Component(immediate = true, property = {"service.ranking:Integer=20"},
	service = FormNavigatorEntry.class)
public class MyAppCompanySettingsFormNavigatorEntry 
	extends BaseJSPFormNavigatorEntry<Object>
		implements FormNavigatorEntry<Object> {

	@Override
	public String getCategoryKey() {
		return FormNavigatorConstants.CATEGORY_KEY_COMPANY_SETTINGS_MISCELLANEOUS;
	}

	@Override
	public String getFormNavigatorId() {
		return FormNavigatorConstants.FORM_NAVIGATOR_ID_COMPANY_SETTINGS;
	}

	@Override
	protected String getJspPath() {
		return "/my_app.jsp";
	}

	@Override
	public String getKey() {
		return "my-app";
	}

	@Override
	public String getLabel(Locale locale) {
		return "My App";
	}

	@Override
	public void include(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

		PortletPreferences companyPortletPreferences = PrefsPropsUtil
				.getPreferences(themeDisplay.getCompanyId(), true);

		boolean companyMyAppFeatureEnabled = PrefsParamUtil
				.getBoolean(companyPortletPreferences, request,
						"myAppFeatureEnabled", true);

		request.setAttribute(MyAppWebKeys.COMPANY_MY_APP_FEATURE_ENABLED,
				companyMyAppFeatureEnabled);

		super.include(request, response);
	}

	@Override
	@Reference(target = "(osgi.web.symbolicname=com.liferay.docs.formnavextensionportlet)", unbind = "-")
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

}