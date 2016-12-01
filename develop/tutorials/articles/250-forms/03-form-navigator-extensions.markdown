# Form Navigator Extensions [](id=form-navigator)

Some data-centric applications require the creation of large data-entry forms.
Examples abound in healthcare, transportation, pharmaceutical, or any other
heavily regulated industry. For these applications, you need an easy way to
section off your forms into easily navigable groups. 

Since @product-ver@, the
[Form Navigator](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Form+Navigator) framework
enables you to add new sections and section categories dynamically to existing
form navigation. The framework includes a well-described API and a powerful
[`liferay-ui`](http://docs.liferay.com/portal/7.0/taglibs/liferay-ui)
tag called  [`form-navigator`](http://docs.liferay.com/portal/7.0/taglibs/liferay-ui/form-navigator.html).
It's easy to use and facilitates organizing large forms into sections of input
and categories. 

![Figure 1: The Form Navigator framework lets you add your app's configuration forms to existing form navigators, like the one used in Portal Settings.](../../images/form-navigator-portal-setting-extension.png)

Form Navigators can be used in two ways: customizing a Form Navigator that
already exists in the portal, and creating your own Form Navigator for your
application. This tutorial demonstrates customizing an existing form. It
references source code from an example portlet called the Form Nav Extension
portlet. On GitHub, you can find its complete project called
[form-nav-extension-portlet](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/form-nav-extension-portlet).
You can also download the Form Nav Extension portlet's bundle
`form-nav-extension-portlet-1.0.jar`. To download it, go to its GitHub
[page](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/osgi/modules/form-nav-extension-portlet-1.0.jar)
and  click the *View Raw* link. 

Before extending a Form Navigator, you should understand the parts of the Form
Navigator Framework and what they do. 

## Understanding the Parts of the Form Navigator Framework [](id=understanding-the-parts-of-the-form-navigator-framework)

Form Navigator implementations contain the following parts:

-   **A JSP that contains a form**: The form must specify a `form-navigator` tag. All
    the form's input sections tie into the Form Navigator. 
-   **Sections**: A section (or entry) is a JSP that specifies inputs
    and a Java class that models the section and ties it into the Form
    Navigator. 
-   **Categories**: A category aggregates one or more sections. A Java class
    models it and ties it into the Form Navigator. 
-   **IDs**: A Form Navigator and its categories should be publicly identified.
    That is, they should all have IDs that can be looked up in a public API
    (e.g., Javadoc). A developer extending a Form Navigator must otherwise
    have access to the Form Navigator's source code in order to find the IDs. 

Liferay's Form Navigator implementations meet all these requirements. They're
implemented similarly and their IDs are published in the Javadoc for the class
[`FormNavigatorConstants`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html).

+$$$

**Note**: Form Navigator extensions implemented using portal properties and form
navigation entry JSPs are deprecated but still supported in @product-ver@. All
new Form Navigator extensions should be implemented as this tutorial describes. 

$$$

Now that you know the parts of a Form Navigator, it'll be easier for you to
extend one. 

## Extending a Form Navigator [](id=extending-a-form-navigator)

Here's an overview of the steps to extend a Form Navigator:

-   **Step 1: Implement a component portlet project to accommodate form navigation.**
-   **Step 2: Create a JSP for each new section of inputs.**
-   **Step 3: Identify the Form Navigator and category (if any) you're extending.**
-   **Step 4: Create new categories.**
-   **Step 5: Create new sections.**

Let's set up a component portlet project to support form navigation. 

### Step 1: Implement a component portlet project to accommodate form navigation [](id=step-1-implement-a-component-portlet-project-to-accommodate-form-navigation)

First, your component portlet must be implemented as an OSGi bundle. You
can develop it in any environment that supports creating a bundle. Please refer
to the @product-ver@ tutorial section [Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)
to learn about development environments. The Form Nav Extension portlet
was created with BLADE based on the `blade.portlet.jsp` template. This is also
covered in the tutorial section linked above.

A Form Navigator extension bundle's metadata must do the following things:

-   Specify the bundle's symbolic name for your servlet context to target
-   Include your project's classes, JSPs, and resource bundles (for localization)
-   Include the `JspAnalyzerPlugin` to generate generate metadata for the JSPs'
    dependencies 
-   Specify a web context path for the Form Navigator classes to associate
    with the JSPs 

You should use a `bnd.bnd` file to specify this metadata. Your Bnd file should
include definitions and directives similar to those specified in the Form Nav
Extension portlet's `bnd.bnd` file: 

    Bundle-Name: Form Nav Extension Portlet
    Bundle-SymbolicName: com.liferay.docs.formnavextensionportlet
    Bundle-Version: 1.0.0
    Include-Resource:\
            classes,\
            META-INF/resources=src/main/resources/META-INF/resources
    -jsp: *.jsp,*.jspf
    -plugin.jsp: com.liferay.ant.bnd.jsp.JspAnalyzerPlugin
    Web-ContextPath: /formnavextensionportlet

The `Bundle-Name` value is arbitrary, but should be recognizable and unique. The
`Bundle-SymbolicName` must be unique--the project's package path makes for a
good symbolic name. For the `Include-Resource`, make sure to include your
project's classes and the root path of its JSPs. The directive below 
includes all the project's `.jsp` and `.jspf` files: 

    -jsp: *.jsp,*.jspf

And the following directive to specify a plugin to include all the JSP
dependencies:

    -plugin.jsp: com.liferay.ant.bnd.jsp.JspAnalyzerPlugin

Lastly, the `Web-ContextPath` specifies the root of the portlet's web context.

As you progress through this tutorial, you'll refer to the metadata in your
portlet's classes. Before diving into the Java classes, howerver, let's create
JSPs for your sections' inputs. 

### Step 2: Create a JSP for each new section of inputs [](id=step-2-create-a-jsp-for-each-new-section-of-inputs)

The existing Form Navigator has a form, and each of its sections extend the form
with sets of input. Your section will add its own inputs. You should create your
section's JSP under the `META-INF/resources` property folder you defined in
your `bnd.bnd` file's `Include-Resource` directive. The example Bnd file
specified the folder like this:

    META-INF/resources=src/main/resources/META-INF/resources 

Under the folder, you can add a JSP for each section of inputs you want to add
to the Form Navigator. Feel free to organize these with subfolders.  

The Form Nav Extension portlet's JSP file `/portal_settings/my_app.jsp` provides
a checkbox input to enable/disable My App's feature in the portal: 

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

        <%@ page import="com.liferay.docs.formnavextensionportlet.MyAppWebKeys" %>
        <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
        <%@ page import="com.liferay.portal.kernel.util.ResourceBundleUtil" %>

        <%@ page import="java.util.ResourceBundle" %>

        <%
        boolean companyMyAppFeatureEnabled = GetterUtil.getBoolean(request.getAttribute(MyAppWebKeys.COMPANY_MY_APP_FEATURE_ENABLED));

        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", request.getLocale(), getClass());
        %>

        <h3><liferay-ui:message key='<%= resourceBundle.getString("my-app-features") %>' /><h3>

        <aui:input checked="<%= companyMyAppFeatureEnabled %>" label='<%= resourceBundle.getString("enable-my-app-feature") %>' name="settings--myAppFeatureEnabled--" type="checkbox" value="<%= companyMyAppFeatureEnabled %>" />

The input's name is `settings--myAppFeatureEnabled--`. So that the Form Navigator
detects the inputs automatically, make sure to start each of your input's names
with `settings--` and end them with `--`. Add all the inputs you need in each of
your sets of inputs. 

After creating section JSPs, you must find out the IDs of the existing Form
Navigator and categories you're adding sections to. You refer to these IDs in
the category and section classes you'll create to represent your Form Navigator
extensions. 

### Step 3: Identify the form navigator and category you're extending [](id=step-3-identify-the-form-navigator-and-category-youre-extending)

Liferay's class
[`FormNavigatorConstants`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html)
specifies constant values for all the portlet Form Navigators and categories.
The identifiers follow the naming conventions below, where you substitue *FOO*
for the navigator's or category's name: 

- **Form navigator**: `FORM_NAVIGATOR_ID_FOO`
- **Category**: `CATEGORY_KEY_FOO`

Note the names of the constants that match the Form Navigator and categories
you're extending--you'll refer to them in your Java classes in the next steps. 

### Step 4: Create new categories [](id=step-4-create-new-categories)

To add a new category, create a class that implements the
[`FormNavigatorCategory`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorCategory.html)
interface. The class needs a `@Component` annotation to register it as a
service in Liferay's module framework. Here are the things your category class's
component annotation should do: 

-   Declare that the component is of service type
    `FormNavigatorCategory.class` 
-   Request immediate loading
-   Optionally, specify the category's
    [service ranking](http://www.osgi.org/javadoc/r4v42/org/osgi/framework/Constants.html#SERVICE%5FRANKING)
    relative to the Form Navigator's other categories. The higher the category's
    service ranking integer relative to the rankings of the other categories,
    the higher the category is listed in the form navigation. 

Here's an example component annotation for a category:

    @Component(
        immediate = true,
        property = {"service.ranking:Integer=20"},
        service = FormNavigatorCategory.class
    )

Next, you implement the
[`FormNavigatorCategory`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorCategory.html)
methods:

-   `getFormNavigatorId`: Return the Form Navigator's constant you noted
    previously from
    [`FormNavigatorConstants`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html)
-   `getKey`: Return an identifier for your category. You can optionally
    create a public class like [`FormNavigatorConstants`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html),
    to publish your project's identifiers. 
-   `getLabel(Locale)`: Return the localized category label. You can create a
    `Language.properties` file in your project's `src/main/resources/content` 
    folder and specify a key/value pair for the category label. You can then add
    the localized value of the property in language properties files for
    other locales.

For example, here's a Form Navigator category implementation for the *Social*
Portal Setting category:

    package com.liferay.portal.settings.web.servlet.taglib.ui;

    import com.liferay.portal.kernel.language.LanguageUtil;
    import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorCategory;
    import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;

    import java.util.Locale;

    import org.osgi.service.component.annotations.Component;

    /**
     * @author Sergio Gonz�lez
     * @author Philip Jones
     */
    @Component(
        immediate = true, property = {"service.ranking:Integer=10"},
        service = FormNavigatorCategory.class
    )
    public class CompanySettingsSocialFormNavigatorCategory
        implements FormNavigatorCategory {

        @Override
        public String getFormNavigatorId() {
            return FormNavigatorConstants.FORM_NAVIGATOR_ID_COMPANY_SETTINGS;
        }

        @Override
        public String getKey() {
            return FormNavigatorConstants.CATEGORY_KEY_COMPANY_SETTINGS_SOCIAL;
        }

        @Override
        public String getLabel(Locale locale) {
            return LanguageUtil.get(locale, "social");
        }

    }

After you've implemented new Form Navigator categories, you can add new
Form Navigator sections. 

### Step 5: Create new sections [](id=step-5-create-new-sections)

To add a new section (entry) that uses a JSP, create a class that extends
the abstract base class
[`BaseJSPFormNavigatorEntry`](https://docs.liferay.com/portal/7.0/javadocs/portal-service/com/liferay/portal/kernel/servlet/taglib/ui/BaseJSPFormNavigatorEntry.html)
and implements the
[`FormNavigatorEntry`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorEntry.html)
interface. The `BaseJSPFormNavigatorEntry` base class integrates the section's
JSP with the Form Navigator. In both these parts of your class declaration, you must
specify the Form Navigator's model bean class as the generic type on which they
operate. For example, if your Form Navigator's model bean class is `User`, your
decarlation would be like this:

    public class MyEntry extends BaseJSPFormNavigatorEntry<User>
            implements FormNavigatorEntry<User>

There are a couple different ways to determine the model bean class.
If you can access the Form Navigator's JSP source code, inspect the
[`form-navigator`](http://docs.liferay.com/portal/7.0/taglibs/liferay-ui/form-navigator.html)
element's `formModelBean` attribute value. The model bean class is the class
type of the object passed in as the `form-navigator`'s `formModelBean`
attribute. 

You can also deduce the model bean class from the name of the ID's constant in
[`FormNavigatorConstants`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html).
The word(s) right after `FORM_NAVIGATOR_ID_` in the constant's name hints at
the class type. For example, if the navigator's ID is
`FORM_NAVIGATOR_ID_USERS_SETTINGS`, then `User` is the model bean class; if the
ID is `FORM_NAVIGATOR_ID_ORGANIZATIONS`, then `Organization` is the class; etc.
Note: this is only a hint, and there are exceptions. For example, if the ID is
is `FORM_NAVIGATOR_ID_SITES`, then `Group` is the class. 

For example, here's a class declaration of a `FormNavigatorEntry`, from the Form
Nav Extension portlet: 

        @Component(
            immediate = true,
            property = {"service.ranking:Integer=20"},
            service = FormNavigatorEntry.class
        )
        public class MyAppCompanySettingsFormNavigatorEntry 
            extends BaseJSPFormNavigatorEntry<Company>
                implements FormNavigatorEntry<Company> {

                // ...

        }

It implements an entry on the `Company` model bean for the Portal Settings Form
Navigator: the navigator identifed by the constant
`FormNavigatorConstants.FORM_NAVIGATOR_ID_COMPANY_SETTINGS`. 

The class also includes the `@Component` annotation, which is the next thing an
entry must specify. An entry's `@Component` annotation registers the entry as a
service in Liferay's module framework. 

Here's what a Form Navigation entry's component annotation should do: 

-   Declare that the component is of service type
    `FormNavigatorEntry.class` 
-   Request immediate loading
-   Optionally, specify a property for the entry's
    [service ranking](http://www.osgi.org/javadoc/r4v42/org/osgi/framework/Constants.html#SERVICE%5FRANKING)
    relative to the other entries in the category. The higher the entry's
    service rankings integer relative to the rankings of the category's other
    entries, the higher the entry is listed in the category. 

Except for your entry's service ranking (optional), your entry's `@Component`
annotation should look similar to the previous example's annotation. Next,
you'll implement the entry class's methods.

The
[`FormNavigatorEntry`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorEntry.html)
implementation must implement the following methods: 

-   `getFormNavigatorId`: Return the Form Navigator's constant you noted
    previously from
    [`FormNavigatorConstants`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html)

-   `getCategoryKey`: Return the Form Navigator category constant you noted
    previously from
    [`FormNavigatorConstants`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html)

-   `getKey`: Return an identifier for your entry. You can optionally create a
    public class like [`FormNavigatorConstants`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/ui/FormNavigatorConstants.html),
    to publish your project's identifiers. 

-   `getLabel(Locale)`: Return the entry's localized label. You can create a
    `Language.properties` file in your project's `src/main/resources/content`
    folder and specify a key/value pair for the entry label. 

-   `getJspPath`: Return the path to the entry's JSP, starting from the path
    you specified previously for your `bnd.bnd` file's `META-INF/resources`
    property. 
    
-   `include(HttpServletRequest, HttpServletResponse)`: Sets the request
    and response attributes for displaying the entry's HTML. You can retrieve the
    form's current settings and pass them to the request. You can optionally use a
    template (e.g., FreeMarker or Velocity) to render the form page, as long as you
    completely override `BaseJSPFormNavigatorEntry`'s `include` method. The Form Nav
    Extension portlet's entry class's `include` method passes to the request the
    current settings that were saved as portlet preferences. It doesn't use a
    template language and instead calls `BaseJSPFormNavigatorEntry`'s `include`
    method.
        
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

-   `setServletContext(ServletContext)`: In this method, you set the parent
    entry class's servlet context. Then, using a `@Reference` annotation, you
    unbind the servlet context from its current target and target it to your
    app's OSGi bundle. First, add the `@Reference` annotation. Next, unbind the
    servlet context by specifying `unbind = "-"`.  Finally, to target the
    servlet context to your app's OSGi bundle, specify as the target value the
    bundle's symbolic name--it's the value you specified for
    `Bundle-SymbolicName` in your `bnd.bnd` file.

    For example, here's the `setServletContext(ServletContext)` method from the
    Form Nav Extension portlet's entry class: 


        @Override
        @Reference(
            target = "(osgi.web.symbolicname=com.liferay.docs.formnavextensionportlet)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            super.setServletContext(servletContext);
        }

The above method calls its parent's `setServletContext(ServletContext)` method.
But look at what its `@Reference` annotation does. It unbinds the servlet
context from its current binding and instead targets it to its own bundle--it
targets the bundle symbolically named
`com.liferay.docs.formnavextensionportlet`. That is the exact symbolic name
defined by `Bundle-SymbolicName:
com.liferay.docs.formnavextensionportlet` in the Form Nav Extension portlet's
`bnd.bnd` file.

You've learned what's required to create a section class. You declared your
class to extend the `BaseJSPFormNavigatorEntry` class and implement the
`FormNavigatorEntry` interface, both with respect to your Form Navigator's form
model bean class. Using annotations, you registered your entry class as an OSGi
service. Then you implemented all the entry methods to relate your entry to a
Form Navigator, category, and JSP, populate your entry's request object, and
target the servlet context to your bundle. 

If you're curious about what a working entry implementation looks like, check
out the example entry class next. 

## Example Form Navigator Entry Class [](id=example-form-navigator-entry-class)

Inspecting an example implementation can help you work out details in your
implementation. Here's the Form Nav Extension portlet's entry class
`MyAppCompanySettingsFormNavigatorEntry`:

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

The above class is declared an OSGi component that provides a
`FormNavigatorEntry.class` service. Since the entry adds a JSP to Portal
Settings, the class extends `BaseJSPFormNavigatorEntry` and implements
`FormNavigatorEntry` on the `Company` model bean class. The class specifies that
the entry belongs to the *Miscellaneous* portal settings category, by returning
navigator key `FormNavigatorConstants.FORM_NAVIGATOR_ID_COMPANY_SETTINGS` from
method `getFormNavigatorId` and category key
`FormNavigatorConstants.CATEGORY_KEY_COMPANY_SETTINGS_MISCELLANEOUS` from method
`getCategoryKey`. The entry's method `getKey` returns *my-app* as its own key
and method `getJspPath` maps the class to the entry's JSP by returning its JSP
file path `/portal_settings/my_app.jsp`.

The entry's `include` method retrieves a boolean portlet preference variable
`myAppFeatureEnabled` that specifies whether My App's feature is enabled for the
portal. It then sets the preference's value as an attribute on the request. The
Form Nav Portlet's language keys for the entry's name, input screen title, and
input label are defined in its `src/main/content/Language.properties` file. The
method `getLabel(Locale)` uses language key `my-app` to return the entry's
localized label. In summary, the `MyAppCompanySettingsFormNavigatorEntry` class
meets all of the Form Navigation framework's section entry requirements.

There you have it! You learned all the parts of the Form Navigator framework and
worked through all the steps to implement new categories and sections. To recap,
you prepared your project's `bnd.bnd` file to support form navigator extension,
created JSPs for your new form sections, identified the targeted form navigator
and categories, created new categories, and created new entry implementations.
You did it all! You now know what it takes to extend Liferay Form Navigators.

## Related Topics [](id=related-topics)

<!-- TODO Add a link to the tutorial on implementing form navigation in a
custom portlet -->

[Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

[Business Logic and Data Access](/develop/tutorials/-/knowledge_base/7-0/business-logic-and-data-access)
