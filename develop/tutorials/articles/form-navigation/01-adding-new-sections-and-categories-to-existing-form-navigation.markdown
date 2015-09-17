# Adding New Sections and Categories to Existing Form Navigation

Liferay Portal provides the taglib `form-navigator` that is useful to organize a
big form in categories and sections that can be easily browsed and filled by the
user.

Since Liferay 7.0, Liferay offers a mechanism to dynamically add new sections to
form navigator taglibs via OSGi modules. Developers can take advantage of this
mechanism to create their own OSGi plugins and add new categories or sections in
those form navigators.

![Figure 1: The Form Navigation framework lets you add your app's configuration forms to existing form navigators, like the one used in Portal Settings.](../../images/form-navigator-portal-setting-extension.png)

The `form-navigator` tag has an attribute `id` that indentifies that particular
form-navigator taglib instance. This attributed is used by the sections and
categories to identify in which form navigator they will be included. 

This tutorial demonstrates adding a new navigation section (entry) to an
existing list of navigation sections. Source code from an example portlet called
the Form Nav Extension portlet demonstrates extending form navigation. You can
find the code referenced by this tutorial in a complete project on Github here:
<https://github.com/jhinkey/liferay-docs/tree/pr256-form-navigator/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/form-nav-extension-portlet>.
You can also download the portlet's bundle from the following link (click *View
Raw* to download it):
<https://github.com/jhinkey/liferay-docs/blob/pr256-form-navigator/develop/tutorials/code/liferay-plugins-sdk-7.0.0/osgi/modules/com.liferay.docs.formnavportlet.jar>

To add a new section entry to existing form navigation, follow these steps: 

1.  Add to your project's `bnd.bnd` file a unique web context path for your
    application. The context path allows your Java classes to reference your
    form JSPs. Here's the web context path entry specified in the Form Nav
    Extension example portlet's `bnd.bnd` file:

        Web-ContextPath: /formnavextensionportlet

2.  Create a JSP with inputs for your app. The Form Nav Extension portlet's JSP
    provides a checkbox input to enable or disable *My App*'s feature in the
    portal:  

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
	taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

	<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
	page import="com.liferay.docs.formnavextensionportlet.MyAppWebKeys" %>

        <h3>My App Feature<h3>

        <%
        boolean companyMyAppFeatureEnabled = GetterUtil.getBoolean(request.getAttribute(MyAppWebKeys.COMPANY_MY_APP_FEATURE_ENABLED));
        %>

        <aui:input checked="<%= companyMyAppFeatureEnabled %>" label="My App Feature Enabled" name="settings--myAppFeatureEnabled--" type="checkbox" value="<%= companyMyAppFeatureEnabled %>" />

3.  To add a new section entry within an existing Liferay form navigator, you
    must create a Java class that  implements the
    [`FormNavigatorEntry`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorEntry.java) 
    interface for the class type you're form operates on. The example portlet's
    Java class `MyAppCompanySettingsFormNavigatorEntry`, shown below, implements
    the `FormNavigatorEntry` interface. Since the example form is only
    operating on a portlet preference, the class simply implements the interface
    on `<Object>`. 

       package com.liferay.docs.formnavextensionportlet;

	import java.io.IOException;
	import java.util.Locale;
	import java.util.ResourceBundle;

	import javax.portlet.PortletPreferences;
	import javax.servlet.ServletContext;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;

	import com.liferay.portal.kernel.language.LanguageUtil;
	import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
	import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
	import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
	import com.liferay.portal.kernel.util.PrefsParamUtil;
	import com.liferay.portal.kernel.util.PrefsPropsUtil;
	import com.liferay.portal.kernel.util.ResourceBundleUtil;
	import com.liferay.portal.kernel.util.WebKeys;
	import com.liferay.portal.theme.ThemeDisplay;

	@Component(immediate = true, property = {"service.ranking:Integer=25"},
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
			return "/portal_settings/my_app.jsp";
		}

		@Override
		public String getKey() {
			return "my-app";
		}

		@Override
		public String getLabel(Locale locale) {
			return LanguageUtil.get(locale, getKey());
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

    The above class extends the `BaseJSPFormNavigatorEntry` class. Make sure to
    extend this base class too. It integrates your JSP with the existing form
    navigator. 
 
    Above the class declaration, is an `@Component` annotation that registers
    the in the OSGi registry so the form navigator tag can retrieve it.  Make
    sure to specify the component annotation, set it for immediate activation,
    and declare it as publishing a service of type `FormNavigatorEntry.class`. 

    In the `@Component` annotation, you can optionally specify where to display
    your section entry among the category's existing entries.  You do this by
    specifing a `service.ranking` OSGi property set to an integer. The higher the
    entry's relative service ranking among the category's existing entries, the
    higher it's listed within the category.  

    In the `getCategoryKey` method you return the ID of the form navigator
    category in which the section entry is to be added. The class
    [`FormNavigatorConstants`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.java) 
    specifies Liferay Portal's category IDs. 

    To identify the form navigator you're extending, implement method
    `getFormNavigatorId` to return that navigation's form navigator ID. 
    Liferay Portal's form navigator IDs are specified also in the class
    [`FormNavigatorConstants`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.java).

    To map your entry class to your section entry's form input, implement method
    `getJspPath` to return the path to its JSP. 

    Implement a `getKey` method to return a key that uniquely identifies your
    entry within the form navigator. 

    The `getLabel` method translates an entry's key, by locale, as a label for
    the navigation entry. It's convienent, but optional, to use the key value as
    the entry's label. 

    You can override the method `include` to implement logic to retrieve
    existing values for your form. 

    Lastly, for the section entry to apply to it's app, its servlet context must
    target it's OSGi bundle. The `target` and `unbind` properties in the
    `setServlectContext` method's `@Reference` annotation accomplish this.

That's all there is to adding section entries to Liferay's form navigators.
Adding categories to existing navigators is even easier. 

To include a new category in a existing form navigator, you must implement the
[`FormNavigatorCategory`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorCategory.java)
interface. You must identify the form navigator in which the category is to be
included and implement method `getFormNavigatorId` to return that form
navigator's ID. Values of existing form navigator IDs used in the portal can be
found in the class `FormNavigatorConstants.java`. 

Category implementations also need to be registered in the OSGi registry so
the form navigator taglib can retrieve them. The order in which the categories
will be displayed is based on the `service.ranking` OSGi property. The higher
the service ranking is, the higher the category appears in the form navigation. 

There you have it! You now know what it takes to extend Liferay form navigators
with new categories and section entries.
