# Extending Form Navigators with New Sections and Categories [](id=extending-form-navigators-with-new-sections-and-categories)

Do you want to provide pages for administrators to configure your app's global
and site-specific settings? Would you like to extend Liferay's user or
organization settings pages to add custom forms? Since Liferay 7.0, the Form
Navigator framework enables you to dynamically add new sections, and categories
of sections, to existing form navigation. The framework includes an API that
leverages the `form-navigator` tag. It's easy to use and facilitates organizing
big forms into categories and sections, so administrators can easily find your
custom forms, fill them in, and save the inputs. 

![Figure 1: The Form Navigator framework lets you add your app's configuration forms to existing form navigators, like the one used in Portal Settings.](../../images/form-navigator-portal-setting-extension.png)

This tutorial demonstrates adding a new navigation section (entry) to an
existing form navigator. It references source code from an example portlet
called the Form Nav Extension portlet. You can find its complete project called
[form-nav-extension-portlet](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/form-nav-extension-portlet),
on GitHub. 

You can also download the Form Nav Extension portlet's bundle
`form-nav-extension-portlet-1.0.jar`. To download it, go to its GitHub
[page](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/osgi/modules/form-nav-extension-portlet-1.0.jar)
and  click the *View Raw* link.

To add a new section entry to existing form navigation, follow these steps: 

1.  Add to your project's `bnd.bnd` file a unique web context path for your
    application. It allows your Java classes to reference your form input
    JSPs. Here's the web context path entry specified in the Form Nav Extension
    example portlet's `bnd.bnd` file:

        Web-ContextPath: /formnavextensionportlet

2.  Create a JSP that presents inputs for your app. The Form Nav Extension
    portlet's JSP provides a checkbox input to enable or disable My App's
    feature in the portal: 

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
        taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

        <%@ page import="com.liferay.docs.formnavextensionportlet.MyAppWebKeys" %><%@
        page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
        page import="com.liferay.portal.kernel.util.ResourceBundleUtil" %>

        <%@ page import="java.util.ResourceBundle" %>

        <%
        boolean companyMyAppFeatureEnabled = GetterUtil.getBoolean(request.getAttribute(MyAppWebKeys.COMPANY_MY_APP_FEATURE_ENABLED));

        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", request.getLocale(), getClass());
        %>

        <h3><liferay-ui:message key='<%= resourceBundle.getString("my-app-features") %>' /><h3>

        <aui:input checked="<%= companyMyAppFeatureEnabled %>" label='<%= resourceBundle.getString("enable-my-app-feature") %>' name="settings--myAppFeatureEnabled--" type="checkbox" value="<%= companyMyAppFeatureEnabled %>" />

3.  At the heart of your form navigation extension is your Java implementation.
    To add a new section entry within an existing Liferay form navigator, 
    create a Java class that  implements the
    [`FormNavigatorEntry`](http://docs.liferay.com/portal/7.0-a1/javadocs/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorEntry.html) 
    interface, for the class type on which the form navigator operates. The
    example portlet's Java class `MyAppCompanySettingsFormNavigatorEntry`, shown
    below, implements the `FormNavigatorEntry` interface. Since the example
    implementation extends the Portal Settings form navigator which operates on
    model class `Company`, it both extends the `BaseJSPFormNavigatorEntry` and
    implements the `FormNavigatorEntry` interface, with respect to the `Company`
    class. Hint: to find the name of the model class a form navigator operates
    on, see what type of object is passed in as the `formModelBean` attribute
    value for the
    [`form-navigator`](http://docs.liferay.com/portal/7.0-a1/taglibs/liferay-ui/form-navigator.html)
    tag in the respective Liferay JSP file. 

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

        import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
        import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
        import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
        import com.liferay.portal.kernel.util.PrefsParamUtil;
        import com.liferay.portal.kernel.util.PrefsPropsUtil;
        import com.liferay.portal.kernel.util.ResourceBundleUtil;
        import com.liferay.portal.kernel.util.WebKeys;
        import com.liferay.portal.model.Company;
        import com.liferay.portal.theme.ThemeDisplay;

        @Component(
            immediate = true,
            property = {"service.ranking:Integer=20"},
            service = FormNavigatorEntry.class
        )
        public class MyAppCompanySettingsFormNavigatorEntry 
            extends BaseJSPFormNavigatorEntry<Company>
                implements FormNavigatorEntry<Company> {

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
                ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                    "content.Language", locale, getClass());

                return resourceBundle.getString("my-app");
            }

            @Override
            public void include(HttpServletRequest request, HttpServletResponse response)
                    throws IOException {

                ThemeDisplay themeDisplay =
                    (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

                PortletPreferences companyPortletPreferences =
                    PrefsPropsUtil.getPreferences(themeDisplay.getCompanyId(), true);

                boolean companyMyAppFeatureEnabled =
                    PrefsParamUtil.getBoolean(
                        companyPortletPreferences, request, "myAppFeatureEnabled",
                        true);

                request.setAttribute(
                    MyAppWebKeys.COMPANY_MY_APP_FEATURE_ENABLED,
                    companyMyAppFeatureEnabled);

                super.include(request, response);
            }

            @Override
            @Reference(
                target = "(osgi.web.symbolicname=com.liferay.docs.formnavextensionportlet)",
                unbind = "-"
            )
            public void setServletContext(ServletContext servletContext) {
                super.setServletContext(servletContext);
            }

        }

This class extends abstract base class
[`BaseJSPFormNavigatorEntry`](http://docs.liferay.com/portal/7.0-a1/javadocs/com/liferay/portal/kernel/servlet/taglib/ui/BaseJSPFormNavigatorEntry.html).
Make sure your class also extends it if you use a JSP to render the form page.
The `BaseJSPFormNavigatorEntry` base class integrates the entry's JSP with the
form navigator you target. You can optionally use a template language to
render the form page, as long as you render it your implementation of
`BaseJSPFormNavigatorEntry`'s `include` method. 

Above the class declaration is a `@Component` annotation that registers the
class in the OSGi registry, so the targeted form navigator can retrieve it. Make
sure to specify the component annotation, set it for immediate activation, and
declare it as publishing service type `FormNavigatorEntry.class`. You can
optionally specify where to display your section entry among the category's
existing entries. You do this by setting a `service.ranking` OSGi property to an
integer. The higher the entry's service ranking integer, relative to the
rankings of the category's other entries, the higher the entry is listed in
category in the form navigation. 

Next, you can implement the getter methods that identify the form navigator,
category, and your entry's JSP, key, and label. In the `getCategoryKey` method
you return the ID of the form navigator category in which to add your section
entry. The class
[`FormNavigatorConstants`](http://docs.liferay.com/portal/7.0-a1/javadocs/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html)
specifies Liferay Portal's category IDs. To identify the form navigator you're
extending, implement method `getFormNavigatorId` to return that form navigator's
ID. The ID is specified as `id` attribute value in the Liferay JSP's
`form-navigator` tag. The form navigator IDs used in Liferay Portal are
specified in the class `FormNavigatorConstants`. To map your entry class to your
section entry's form input, implement method `getJspPath` to return the path to
its JSP. Then implement a `getKey` method to return a key that uniquely
identifies your entry within the form navigator. Lastly, implement the
`getLabel` method to return a label for the navigation entry. 

To apply the section entry to your app, target its OSGi bundle for the servlet
context. Add a `setServlectContext` method to invoke the parent's
`setServlectContext` method. Then add a `@Reference` annotation to it to target
the servlet context to your app's OSGi bundle and unbind the servlet context.
Lastly, you can override the `include` method to implement logic to retrieve
existing values for your JSP's form input. The Form Nav Extension portlet's
`include` method retrieves its form's values that are saved in portlet
preferences.

That's all there is to adding section entries to Liferay's form navigators.
Adding categories to existing navigators is even easier. 

To add a new category to an existing form navigator, you implement the
[`FormNavigatorCategory`](http://docs.liferay.com/portal/7.0-a1/javadocs/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorCategory.html)
interface. The class needs a `@Component` annotation similar to the one you
specify for a section entry, except it must declare the category class by
specifying the publishing service type `FormNavigatorCategory.class`. In the
annotation, also specify your category class for immediate activation and
optionally set a service ranking for it. The service ranking determines where
the category is placed relative to the form navigator's other categories. Here's
an annotation that specifies all these things:

    @Component(
        immediate = true,
        property = {"service.ranking:Integer=20"},
        service = FormNavigatorCategory.class
    )

In your category implementation, you must also identify the targeted form
navigator by returning its ID in method `getFormNavigatorId`. Implement a
`getKey` method to return a key that uniquely identifies your category within
the form navigator. And implement the `getLabel` method to return a label for
the navigation category. 

There you have it! You now know what it takes to extend Liferay form navigators
with new categories and section entries.

**Related Topics**

<!-- TODO Add a link to the tutorial on implementing form navigation in a
custom portlet -->

[Creating a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/creating-a-portlet-application)

[Using JSPs in a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/using-jsps-in-a-portlet-application)
