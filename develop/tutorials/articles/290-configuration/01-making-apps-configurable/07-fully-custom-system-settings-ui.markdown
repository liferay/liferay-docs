# Configuration Form Renderer

There are various approaches to customizing the auto-generated System Settings
UI for your configurable application. To replace an application's
configuration screen with a form that you build from scratch, this tutorial
provides a full demonstration using these steps:

1.  Use a `DisplayContext` class to transfer data between backend code and the
    desired JSP markup.
2.  Implement the `ConfigurationFormRenderer` interface.
3.  Render the configuration form. This tutorial demonstrates the use of a JSP
    and the previously created `DisplayContext` class.

The generalized discussion of System Settings UI customization is found in a
[separate tutorial](/develop/tutorials/-/knowledge_base/7-1/customizing-the-system-settings-user-interface).

This tutorial demonstrates replacing the configuration UI for the _Language
Template_ System Settings entry, found in  Control Panel &rarr; Configuration
&rarr; System Settings &rarr; Localization &rarr; Language Template. The same
steps apply for your custom application's auto-generated UI. 

![Figure 1: The auto-generated UI for the Language Template configuration screen is sub-optimal. A select list with more human readable options is preferable.](../../../images/sys-settings-lang-template-default.png)

Specifically, the text input field labeled DDM Template Key in the
auto-generated UI will be replaced with a select list field type, populated with
all possible DDM Template Keys.

## Creating a `DisplayContext` [](id=creating-a-displaycontext)

A `DisplayContext` class is a POJO that simplifies and minimizes the use of
Java logic in JSPs. Display context usage isn't required, but it's a nice
convention to follow. It's a kind of data transfer object, where the
`DisplayContext`'s setters are called from the Java class providing the render
logic (in this case the `ConfigurationFormRenderer`'s `render` method), and the
getters are called from the JSP, removing the need for the Java logic to be
written inside the JSP itself.

For this example, create a `LanguageTemplateConfigurationDisplayContext` class:

    public class LanguageTemplateConfigurationDisplayContext {

        public void addTemplateValue(
            String templateKey, String templateDisplayName) {

            _templateValues.add(new String[] {templateKey, templateDisplayName});
        }

        public String getCurrentTemplateName() {
            return _currentTemplateName;
        }

        public String getFieldLabel() {
            return _fieldLabel;
        }

        public String getRedirect() {
            return _redirect;
        }

        public List<String[]> getTemplateValues() {
            return _templateValues;
        }

        public void setCurrentTemplateName(String currentTemplateName) {
            _currentTemplateName = currentTemplateName;
        }

        public void setFieldLabel(String fieldLabel) {
            _fieldLabel = fieldLabel;
        }

        public void setRedirect(String redirect) {
            _redirect = redirect;
        }

        private String _currentTemplateName;
        private String _fieldLabel;
        private String _redirect;
        private final List<String[]> _templateValues = new ArrayList<>();

    }

## Implementing a `ConfigurationFormRenderer`

First create the component and class declarations. Set the `service` property
to `ConfigurationFormRenderer.class`:

    @Component(immediate = true, service = ConfigurationFormRenderer.class)
    public class LanguageTemplateConfigurationFormRenderer
        implements ConfigurationFormRenderer {

Next, write an `activate` method (decorated with `@Activate` and `@Modified`)
to to convert a map of the configuration's properties to a typed class. The
configuration is stored in a volatile field. Don't forget to make it volatile
to prevent thread safety problems. See the article on
[reading configuration values from a component class](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-component)
for more information.

    @Activate
    @Modified
    public void activate(Map<String, Object> properties) {
        _siteNavigationLanguageWebTemplateConfiguration =
            ConfigurableUtil.createConfigurable(
                SiteNavigationLanguageWebTemplateConfiguration.class,
                properties);
    }


    private volatile SiteNavigationLanguageWebTemplateConfiguration
        _siteNavigationLanguageWebTemplateConfiguration;

Next override the `getPid` and `getRequestParameters` methods:

	@Override
	public String getPid() {

		return "com.liferay.site.navigation.language.web.configuration.SiteNavigationLanguageWebTemplateConfiguration";
	}

Return the full configuration ID, as specirfied in the `*Configuration` class's
`@Meta.OCD` annotation.

	@Override
	public Map<String, Object> getRequestParameters
		(HttpServletRequest request) {

		Map<String, Object> params = new HashMap<>();

		String[] myddmtemplatekey = ParamUtil.getParameterValues(request, "myddmtemplatekey");

		params.put("ddm-template-key", myddmtemplatekey);

		return params;

	}

In the `getRequestParameters` method, map the parameters sent by the custom form
(obtained form the request) to the keys of the fields in the Configuration
interface.

Provide the render logic via the overridden `render` method. The rendering
approach demonstrated here uses a JSP. Recall that it's backed by a
`DisplayContext` class set into the request object. The values set
from this `render` method are available in the JSP via the `DisplayContext`
object's getters. 

Loop through the DDM Template Keys for the given `groupId` and set them into
the display context with the `addTemplateKey` method. Then set the other
necessary values that the JSP will need. In this case, set the title, the field
label, and the redirect URL.  Lastly, call `renderJSP` and pass in the
`servletContext`, request, response, and the path to the JSP: 

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response)
        throws IOException {

        LanguageTemplateConfigurationDisplayContext
            languageTemplateConfigurationDisplayContext =
                new LanguageTemplateConfigurationDisplayContext();

        languageTemplateConfigurationDisplayContext.setCurrentTemplateName(
            _siteNavigationLanguageWebTemplateConfiguration.ddmTemplateKey());

        long groupId = 0;

        Group group = _groupLocalService.fetchCompanyGroup(
            CompanyThreadLocal.getCompanyId());

        if (group != null) {
            groupId = group.getGroupId();
        }

        Locale locale = LocaleThreadLocal.getThemeDisplayLocale();

        List<DDMTemplate> ddmTemplates = _ddmTemplateLocalService.getTemplates(
            groupId, _portal.getClassNameId(LanguageEntry.class));

        for (DDMTemplate ddmTemplate : ddmTemplates) {
            languageTemplateConfigurationDisplayContext.addTemplateValue(
                ddmTemplate.getTemplateKey(), ddmTemplate.getName(locale));
        }

        languageTemplateConfigurationDisplayContext.setRedirect(
            _portal.getCurrentURL(request));

        languageTemplateConfigurationDisplayContext.setTitle(getName(locale));

        languageTemplateConfigurationDisplayContext.setFieldLabel(
            "language-selection-style");

        request.setAttribute(
            LanguageTemplateConfigurationDisplayContext.class.getName(),
            languageTemplateConfigurationDisplayContext);

        _jspRenderer.renderJSP(
            _servletContext, request, response,
            "/configuration/site_navigation_language_web_template.jsp");
    }

Specify the required service references at the bottom of the class. Be careful
to target the proper servlet context, passing the `bundle-SymbolicName` of the
module (found in its `bnd.bnd` file) into the `osgi.web.symbolicname` property
of the reference target:

    @Reference
    private DDMTemplateLocalService _ddmTemplateLocalService;

    @Reference
    private GroupLocalService _groupLocalService;

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference
    private Portal _portal;

    @Reference(
        target = "(osgi.web.symbolicname=com.liferay.site.navigation.language.web)",
        unbind = "-"
    )
    private ServletContext _servletContext;

Once the configuration form renderer is implemented, write the JSP markup for
the form.

## Writing the JSP Markup [](id=writing-the-jsp-markup)

Now write the JSP:

    <%@ include file="/init.jsp" %>

    <%
    LanguageTemplateConfigurationDisplayContext languageTemplateConfigurationDisplayContext = (LanguageTemplateConfigurationDisplayContext)request.getAttribute(LanguageTemplateConfigurationDisplayContext.class.getName());

    String currentTemplateName = languageTemplateConfigurationDisplayContext.getCurrentTemplateName();
    %>

    <portlet:actionURL name="/site_navigation_language/update_language_template_configuration" var="editURL" />

    <div class="sheet sheet-lg">

        <aui:form action="<%= editURL %>" name="fm">
            <aui:input name="redirect" type="hidden" value="<%= languageTemplateConfigurationDisplayContext.getRedirect() %>" />

            <aui:select label="<%= HtmlUtil.escape(languageTemplateConfigurationDisplayContext.getFieldLabel()) %>" name="ddmTemplateKey" value="<%= currentTemplateName %>">

                <%
                for (String[] templateValue : languageTemplateConfigurationDisplayContext.getTemplateValues()) {
                %>

                    <aui:option label="<%= templateValue[1] %>" selected="<%= currentTemplateName.equals(templateValue[0]) %>" value="<%= templateValue[0] %>" />

                <%
                }
                %>

            </aui:select>

        </aui:form>
    </div>

The action URL has the same value as that entered in the action command
component's `mvc.command.name` property, and the opening scriptlet gets the
display context object from the request so that all its getters are invoked
whenever information from the backend is required. The `div` renders the HTML,
with the title serving as the second level heading. An `aui:form` provides the
rendering of the configuration form. A select field uses the field label and
current template name (as coded in the `ConfigurationFormRenderer`) to define the
default selected value. All existing templates are retrieved from the display
context and displayed as options within the select list, via the `for...`
scriptlet and the `<aui:option>` tag.

So what does this example look like when all is said and done?

![Figure 2: A select list provides a more user friendly configuration experience than a text field.](../../../images/sys-settings-lang-template-custom.png)

Some configuration UIs require tweaking with a `ConfigurationFormRenderer`. This
tutorial shows a particularly good example. Administrators encountering the
Language Template entry in System Settings won't know the DDM Template Keys they
can use offhand. Providing the available values in a select field wildly
enhances the user experience.
