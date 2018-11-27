# Configuration Form Renderer

There are various approaches to customizing the auto-generated System Settings
UI for your configurable application. To replace an application's
configuration screen with a form that you build from scratch, this tutorial
provides a full demonstration using these steps:

1.  Use a `DisplayContext` class to transfer data between backend code and the
    JSP markup.
2.  Implement the `ConfigurationFormRenderer` interface.
3.  Render the configuration form. This tutorial demonstrates the use of a JSP
    and the previously created `DisplayContext` class that holds much of the
    Java needed for use in the JSP.
4.  Remove the auto-generated UI to avoid duplicate configuration screens in
    System Settings.

The generalized discussion of System Settings UI customization is found in a
[separate tutorial](/develop/tutorials/-/knowledge_base/7-1/customizing-the-system-settings-user-interface).

This tutorial demonstrates replacing the user interface for the Language
Template System Settings entry, found at  Control Panel &rarr; Configuration
&rarr; System Settings &rarr; Localization &rarr; Language Template. Apply the
same steps within your custom application. 

![Figure 1: The auto-generated UI for the Language Template configuration screen is sub-optimal. A select list with more sensible option labels is preferable.](../../../images/sys-settings-lang-template-default.png)

Specifically, the text input field labeled DDM Template Key in the
auto-generated UI will be replaced with a select list field type.

## Creating a `DisplayContext` [](id=creating-a-displaycontext)

A `DisplayContext` class is a POJO that simplifies and minimizes the use of
Java logic in JSPs. Display context usage isn't required, but it's a nice
convention to follow. It's a kind of data transfer object, where the
`DisplayContext`'s setters are called from the Java class providing the render
logic (in this case the `ConfigurationScreen`'s `render` method), and the
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

        public String getTitle() {
            return _title;
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

        public void setTitle(String title) {
            _title = title;
        }

        private String _currentTemplateName;
        private String _fieldLabel;
        private String _redirect;
        private final List<String[]> _templateValues = new ArrayList<>();
        private String _title;

    }

## Implementing a `ConfigurationScreen` [](id=implementing-a-configurationscreen)

First create the component and class declarations. Set the `configurationPid`
property to match the configuration whose UI you're writing, and the `service`
property to `ConfigurationScreen.class`:

    @Component(
        configurationPid = "com.liferay.site.navigation.language.web.configuration.SiteNavigationLanguageWebTemplateConfiguration",
        immediate = true, service = ConfigurationScreen.class
    )
    public class LanguageTemplateConfigurationScreen
        implements ConfigurationScreen {

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

Next override the `getCategoryKey` and `getKey` methods to provide the System
Settings section and entry keys (place the values for these keys in a language

properties file in `src/main/resource/META-INF/resource` inside your module):

    @Override
    public String getCategoryKey() {
        return "localization";
    }

    @Override
    public String getKey() {
        return "site-navigation-language-web-template-configuration-name";
    }

The `getName` method returns the localized value of the language key. It calls
`LanguageUtil.get` to retrieve the localized value of the entry title from the
resource bundle providing the module's localization keys and values. Pass in the
locale (provided to the method by the code that calls it), the configuration
screen's class name, and the String returned by `getKey` to retrieve the right
resource bundle.

    @Override
    public String getName(Locale locale) {
        return LanguageUtil.get(
            ResourceBundleUtil.getBundle(
                locale, LanguageTemplateConfigurationScreen.class),
            getKey());
    }

Set the scope of the entry with the `getScope` method:

    @Override
    public String getScope() {
        return "system";
    }

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

Once the configuration screen is implemented, create an MVC Action Command.

## Implementing an `MVCActionCommand` [](id=implementing-an-mvcactioncommand)

For more detailed information on MVC Action Commands, read
[here](/develop/tutorials/-/knowledge_base/7-1/mvc-action-command). If you're
using some other paradigm for writing your portlet's action methods, adapt
accordingly.

The action command needed for this example is actually an action command for
the _System Settings_ application, not your own custom application. Set up the
component and class declaration like this:

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
            "mvc.command.name=/site_navigation_language/update_language_template_configuration"
        },
        service = MVCActionCommand.class
    )
    public class UpdateLanguageTemplateConfigurationMVCActionCommand
        extends BaseMVCActionCommand {

Specify the System Settings portlet in the portlet name property and make sure
the MVC command name matches whatever you set in your JSPs `<portlet:
actionURL>` name property.

The logic in this class should answer the question 

_what should be done with the configuration value set in the custom
configuration screen?_

The code's answer, in plain English:

_set the value entered by the user (in the System Settings portlet)
into your application's configuration object._

To that end, code the `doProcessAction` and obtain a reference to the
`ConfigurationAdmin` service:

    @Override
    protected void doProcessAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        String ddmTemplateKey = ParamUtil.getString(
            actionRequest, "ddmTemplateKey");

        Configuration configuration = _configurationAdmin.getConfiguration(
            SiteNavigationLanguageWebTemplateConfiguration.class.getName(),
            StringPool.QUESTION);

        Dictionary<String, Object> properties = configuration.getProperties();

        if (properties == null) {
            properties = new HashMapDictionary<>();
        }

        properties.put("ddmTemplateKey", ddmTemplateKey);

        configuration.update(properties);
    }

    @Reference
    private ConfigurationAdmin _configurationAdmin;

    }

This example gets the DDM template key that the user selects from the request
and updates the configuration's properties with the key and value. Now any code
that 
[retrieves the configuration's values](/develop/tutorials/-/knowledge_base/7-1/reading-configuration-values-from-a-mvc-portlet)
doesn't have to know anything about the customized UI or the
`ConfigurationScreen` implementation.

## Writing the JSP Markup [](id=writing-the-jsp-markup)

Now write the JSP:

    <%@ include file="/init.jsp" %>

    <%
    LanguageTemplateConfigurationDisplayContext languageTemplateConfigurationDisplayContext = (LanguageTemplateConfigurationDisplayContext)request.getAttribute(LanguageTemplateConfigurationDisplayContext.class.getName());

    String currentTemplateName = languageTemplateConfigurationDisplayContext.getCurrentTemplateName();
    %>

    <portlet:actionURL name="/site_navigation_language/update_language_template_configuration" var="editURL" />

    <div class="sheet sheet-lg">
        <div class="sheet-header">
            <h2><%= languageTemplateConfigurationDisplayContext.getTitle() %></h2>
        </div>

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

            <div class="sheet-footer">
                <aui:button type="submit" />
            </div>
        </aui:form>
    </div>

The action URL has the same value as that entered in the action command
component's `mvc.command.name` property, and the opening scriptlet gets the
display context object from the request so that all its getters are invoked
whenever information from the backend is required. The `div` renders the HTML,
with the title serving as the second level heading. An `aui:form` provides the
rendering of the configuration form. A select field uses the field label and
current template name (as coded in the `ConfigurationScreen`) to define the
default selected value. All existing templates are retrieved from the display
context and displayed as options within the select list, via the `for...`
scriptlet and the `<aui:option>` tag.

So what does this example look like when all is said and done?

![Figure 2: A select list provides a more user friendly configuration experience than a text field.](../../../images/sys-settings-lang-template-custom.png)

If you read the overview article on 
[custom configuration screens](/develop/tutorials/-/knowledge_base/7-1/customizing-the-system-settings-user-interface), 
you recognize that you'll now have two configuration entries in System Settings:
the auto-generated UI and the one provided by your `ConfigurationScreen`! That's
no good. Get rid of the auto-generated UI.

## Remove the Default Configuration [](id=remove-the-default-configuration)

Adding one property to the `*Configuration` class's `@ExtendedObjectDefinition`
annotation will top the auto-generation of a UI.

The existing annotation for the language template example looks like this:

    @ExtendedObjectClassDefinition(category = "localization")

Add a comma and the `generateUI = false` property:

    @ExtendedObjectClassDefinition(category = "localization", generateUI=false)

That's it. Now the auto-generated UI is excluded and your custom-built UI is
the only one that appears.
