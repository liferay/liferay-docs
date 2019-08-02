---
header-id: implementing-widget-templates
---

# Implementing Widget Templates

[TOC levels=1-4]

[Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
are ways to customize how an application looks. You can create templates for an
application's display and then choose which template is active. 

![Figure 1: By using a custom display template, your portlet's display can be customized.](../../images/widget-template-dropdown.png)

To add Widget Template support to your portlet, follow the steps below.

1.  Create and register a custom `*PortletDisplayTemplateHandler` component.
    Liferay provides the
    [`BasePortletDisplayTemplateHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portletdisplaytemplate/BasePortletDisplayTemplateHandler.html)
    as a base implementation for you to extend. You can check the
    [`TemplateHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateHandler.html)
    interface Javadoc to learn about each template handler method.

    The `@Component` annotation ties your handler to a specific portlet by
    setting the property `javax.portlet.name` to your portlet's name. The same
    property should be found in your portlet class. For example,

    ```java
    @Component(
        immediate = true,
        property = {
            "javax.portlet.name="+ AssetCategoriesNavigationPortletKeys.ASSET_CATEGORIES_NAVIGATION
        },
        service = TemplateHandler.class
    )
    ```

    The Site Map widget provides a good example implementation:

    ```java
    @Component(
        immediate = true,
        property = "javax.portlet.name=" + SiteNavigationSiteMapPortletKeys.SITE_NAVIGATION_SITE_MAP,
        service = TemplateHandler.class
    )
    public class SiteNavigationSiteMapPortletDisplayTemplateHandler
        extends BasePortletDisplayTemplateHandler {

        @Override
        public String getClassName() {
            return LayoutSet.class.getName();
        }

        @Override
        public String getName(Locale locale) {
            String portletTitle = _portal.getPortletTitle(
                SiteNavigationSiteMapPortletKeys.SITE_NAVIGATION_SITE_MAP,
                ResourceBundleUtil.getBundle(locale, getClass()));

            return LanguageUtil.format(locale, "x-template", portletTitle, false);
        }

        @Override
        public String getResourceName() {
            return SiteNavigationSiteMapPortletKeys.SITE_NAVIGATION_SITE_MAP;
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
                "pages", List.class, PortletDisplayTemplateConstants.ENTRIES,
                "page", Layout.class, "curPage", "getName(locale)");
            templateVariableGroup.addVariable(
                "site-map-display-context",
                SiteNavigationSiteMapDisplayContext.class, "siteMapDisplayContext");

            return templateVariableGroups;
        }

        @Override
        protected String getTemplatesConfigPath() {
            return "com/liferay/site/navigation/site/map/web/portlet/template" +
                "/dependencies/portlet-display-templates.xml";
        }

        @Reference
        private Portal _portal;

    }
    ```

2.  Your application must define permissions for creating and managing display
    templates. Add the action key `ADD_PORTLET_DISPLAY_TEMPLATE` to your
    portlet's `/src/main/resources/resource-actions/default.xml` file:

    ```xml
    <?xml version="1.0"?>
    <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.2.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_2_0.dtd">
    <resource-action-mapping>
        ...
        <portlet-resource>
            <portlet-name>yourportlet</portlet-name>
            <permissions>
                <supports>
                    <action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
                    <action-key>ADD_TO_PAGE</action-key>
                    <action-key>CONFIGURATION</action-key>
                    <action-key>VIEW</action-key>
                </supports>
                ...
            </permissions>
        </portlet-resource>
        ...
    </resource-action-mapping>
    ```

3.  If your application hasn't defined Liferay permissions before, create a file
    named `portlet.properties` in the `/resources` folder and add the following
    contents providing the path to your `default.xml`:

    ```properties
    include-and-override=portlet-ext.properties
    resource.actions.configs=resource-actions/default.xml
    ```

4.  Now expose the Widget Template selector to your users. Include the
    `<liferay-ddm:template-selector>` tag in the JSP file you're using to
    control your portlet's configuration.

    For example, it may be helpful for you to insert a
    `<liferay-frontend:fieldset>` in your configuration JSP file like this:

    ```markup
    <liferay-frontend:fieldset
        collapsible="<%= true %>"
        label="templates"
    >
        <div class="display-template">
            <liferay-ddm:template-selector
                classNameId="<%= YourEntity.class.getName() %>"
                displayStyle="<%= displayStyle %>"
                displayStyleGroupId="<%= displayStyleGroupId %>"
                refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
                showEmptyOption="<%= true %>"
            />
        </div>
    </liferay-frontend:fieldset>
    ```

    In this JSP, the `<liferay-ddm:template-selector>` tag specifies the Display
    Template drop-down menu to be used in the widget's Configuration menu.

5.  You must now extend your view code to render your portlet using the selected
    Widget Template. 

    First, initialize the Java variables needed for the Widget Template: 

    ```markup
    <%
    String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
    long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);
    %>
    ```

    Next, you can test if the Widget Template is configured, grab the entities
    to be rendered, and render them using the Widget Template. The tag
    `<liferay-ddm:template-renderer>` aids with this process. It automatically
    uses the selected template or renders its body if no template is selected.

    Here's some example code that demonstrates implementing this:

    ```markup
    <liferay-ddm:template-renderer
        className="<%= YourEntity.class.getName() %>"
        contextObjects="<%= contextObjects %>"
        displayStyle="<%= displayStyle %>"
        displayStyleGroupId="<%= displayStyleGroupId %>"
        entries="<%= yourEntities %>"
    >

        <%-- The code that renders the default view should be inserted here. --%>

    </liferay-ddm:template-renderer>
    ```

    In this step, you initialized variables dealing with the display settings 
    (`displayStyle` and `displayStyleGroupId`) and passed them to the tag along
    with other parameters.

    As an example, the Site Map widget implements the
    `<liferay-ddm:template-renderer>` tag in its `view.jsp` like this:

    ```markup
    <liferay-ddm:template-renderer
        className="<%= LayoutSet.class.getName() %>"
        contextObjects="<%= contextObjects %>"
        displayStyle="<%= siteNavigationSiteMapPortletInstanceConfiguration.displayStyle() %>"
        displayStyleGroupId="<%= siteNavigationSiteMapDisplayContext.getDisplayStyleGroupId() %>"
        entries="<%= siteNavigationSiteMapDisplayContext.getRootLayouts() %>"
    >
        <%= siteNavigationSiteMapDisplayContext.buildSiteMap() %>
    </liferay-ddm:template-renderer>
    ```

    This logic builds the site's navigation map when the widget is added to a
    page.

Awesome! Your portlet now supports Widget Templates! Once your script is
uploaded and saved, Users with the specified Roles can select the template when
they're configuring the display settings of your portlet on a
page. You can visit the
[Styling Widgets with Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
section for more details on using Widget Templates.
