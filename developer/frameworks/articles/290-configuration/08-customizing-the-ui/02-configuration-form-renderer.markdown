# Configuration Form Renderer [](id=configuration-form-renderer)

To replace an application's auto-generated configuration screen with a form
built from scratch, you follow these steps:

1.  Use a `DisplayContext` class to transfer data between back-end code and the
    desired JSP markup.
2.  Implement the `ConfigurationFormRenderer` interface.
3.  Render the configuration form. This tutorial demonstrates the use of a JSP
    and the previously created `DisplayContext` class.

A generalized discussion on System Settings UI customization is found in a
[separate section](/docs/7-2/frameworks/-/knowledge_base/f/customizing-the-system-settings-user-interface).

This article demonstrates replacing the configuration UI for the _Language
Template_ System Settings entry, found in  Control Panel &rarr; Configuration
&rarr; System Settings &rarr; Localization &rarr; Language Template. The same
steps apply when replacing your custom application's auto-generated UI. 

![Figure 1: The auto-generated UI for the Language Template configuration screen is sub-optimal. A select list with more human readable options is preferable.](../../../images/sys-settings-lang-template-default.png)

Specifically, the text input field labeled *DDM Template Key* in the
auto-generated UI is replaced with a select list field type called *Language
Selection Style*, populated with all possible DDM Template Keys.

## Creating a `DisplayContext` [](id=creating-a-displaycontext)

A `DisplayContext` class is a POJO that simplifies and minimizes the use of Java
logic in JSPs. Display context usage isn't required, but it's a nice convention
to follow. It's a kind of data transfer object, where the `DisplayContext`'s
setters are called from the Java class providing the render logic (in this case
the `ConfigurationFormRenderer`'s `render` method), and the getters are called
from the JSP, removing the need for Java logic to be written inside the JSP
itself.

For this example, create a `LanguageTemplateConfigurationDisplayContext` class
with these contents:

```java

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

    public List<String[]> getTemplateValues() {
        return _templateValues;
    }

    public void setCurrentTemplateName(String currentTemplateName) {
        _currentTemplateName = currentTemplateName;
    }

    public void setFieldLabel(String fieldLabel) {
        _fieldLabel = fieldLabel;
    }

    private String _currentTemplateName;
    private String _fieldLabel;
    private final List<String[]> _templateValues = new ArrayList<>();

}

```

Next implement the `ConfigurationFormRenderer`.

## Implementing a `ConfigurationFormRenderer` [](id=implementing-a-configurationformrenderer)

First create the component and class declarations. Set the `service` property
to `ConfigurationFormRenderer.class`:

```java

@Component(
    configurationPid = "com.liferay.site.navigation.language.web.configuration.SiteNavigationLanguageWebTemplateConfiguration",
    immediate = true, service = ConfigurationFormRenderer.class
)
public class LanguageTemplateConfigurationFormRenderer
    implements ConfigurationFormRenderer {

```

Next, write an `activate` method (decorated with `@Activate` and `@Modified`)
to to convert a map of the configuration's properties to a typed class. The
configuration is stored in a volatile field. Don't forget to make it volatile
to prevent thread safety problems. See the article on
[reading configuration values from a component class](/docs/7-2/frameworks/-/knowledge_base/f/reading-configuration-values-from-a-component)
for more information.

```java

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

```

Next override the `getPid` and `getRequestParameters` methods:

```java

@Override
public String getPid() {
    return "com.liferay.site.navigation.language.web.configuration." +
        "SiteNavigationLanguageWebTemplateConfiguration";
}

```

Return the full configuration ID, as specified in the `*Configuration` class's
`@Meta.OCD` annotation.

```java

@Override
public Map<String, Object> getRequestParameters(
    HttpServletRequest request) {

    Map<String, Object> params = new HashMap<>();

    String ddmTemplateKey = ParamUtil.getString(request, "ddmTemplateKey");

    params.put("ddmTemplateKey", ddmTemplateKey);

    return params;
}

```

In the `getRequestParameters` method, map the parameters sent by the custom form
(obtained from the request) to the keys of the fields in the Configuration
interface.

Provide the render logic via the overridden `render` method. The rendering
approach demonstrated here uses a JSP. Recall that it's backed by a
`DisplayContext` class set into the request object. The values set from this
`render` method are available in the JSP via the `DisplayContext` object's
getters. 

Loop through the DDM Template Keys for the given `groupId` and set them into
the display context with the `addTemplateKey` method. Then set the other
necessary values that the JSP needs. In this case, set the title, the field
label, and the redirect URL. Finally, call `renderJSP` and pass in the
`servletContext`, request, response, and the path to the JSP: 

```java

@Override
public void render(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

    Locale locale = request.getLocale();

    LanguageTemplateConfigurationDisplayContext
        languageTemplateConfigurationDisplayContext =
            new LanguageTemplateConfigurationDisplayContext();

    languageTemplateConfigurationDisplayContext.setCurrentTemplateName(
        _siteNavigationLanguageWebTemplateConfiguration.ddmTemplateKey());

    long groupId = 0;

    long companyId = _portal.getCompanyId(actionRequest);

    Group group = _groupLocalService.fetchCompanyGroup(companyId);

    if (group != null) {
        groupId = group.getGroupId();
    }

    List<DDMTemplate> ddmTemplates = _ddmTemplateLocalService.getTemplates(
        groupId, _portal.getClassNameId(LanguageEntry.class));

    for (DDMTemplate ddmTemplate : ddmTemplates) {
        languageTemplateConfigurationDisplayContext.addTemplateValue(
            ddmTemplate.getTemplateKey(), ddmTemplate.getName(locale));
    }

    languageTemplateConfigurationDisplayContext.setFieldLabel(
        LanguageUtil.get(
            ResourceBundleUtil.getBundle(
                locale, LanguageTemplateConfigurationFormRenderer.class),
            "language-selection-style"));

    request.setAttribute(
        LanguageTemplateConfigurationDisplayContext.class.getName(),
        languageTemplateConfigurationDisplayContext);

    _jspRenderer.renderJSP(
        _servletContext, request, response,
        "/configuration/site_navigation_language_web_template.jsp");
}

```

Specify the required service references at the bottom of the class. Be careful
to target the proper servlet context, passing the `bundle-SymbolicName` of the
module (found in its `bnd.bnd` file) into the `osgi.web.symbolicname` property
of the reference target:

```java

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

```

Once the configuration form renderer is implemented, you can write the JSP
markup for the form.

## Writing the JSP Markup [](id=writing-the-jsp-markup)

Now write the JSP:

```jsp

    <%@ include file="/init.jsp" %>

    <%
    LanguageTemplateConfigurationDisplayContext
        languageTemplateConfigurationDisplayContext = (LanguageTemplateConfigurationDisplayContext)request.getAttribute(LanguageTemplateConfigurationDisplayContext.class.getName());

Admin: Instance Settings    String currentTemplateName = languageTemplateConfigurationDisplayContext.getCurrentTemplateName();
    %>

    <aui:select label="<%= HtmlUtil.escape(languageTemplateConfigurationDisplayContext.getFieldLabel()) %>" name="ddmTemplateKey" value="<%= currentTemplateName %>">

        <%
        for (String[] templateValue : languageTemplateConfigurationDisplayContext.getTemplateValues()) {
        %>

            <aui:option label="<%= templateValue[1] %>" selected="<%= currentTemplateName.equals(templateValue[0]) %>" value="<%= templateValue[0] %>" />

        <%
        }
        %>

    </aui:select>

```

The opening scriptlet gets the display context object from the request so that
all its getters are invoked whenever information from the back-end is required.
Right away, the `getCurrentTemplateName` method is called, since the current
template name is needed for the first option's `ddmTemplateKey` display value as
soon as the form is rendered. This happens in the `<aui:select>` tag. There's
just a bit of logic used to create an option for each of the available DDM
templates that can be chosen.

So what does this example look like when all is said and done?

![Figure 2: A select list provides a more user friendly configuration experience than a text field.](../../../images/sys-settings-lang-template-custom.png)

Now, administrators encountering the Language Template entry in System Settings
won't be handicappd by not knowing the available DDM Template Keys. Providing
the available values in a select field wildly enhances the user experience.
