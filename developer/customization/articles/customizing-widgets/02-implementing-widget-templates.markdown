---
header-id: implementing-widget-templates
---

# Implementing Widget Templates

[TOC levels=1-4]

[Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
are ways to customize how a widget looks. You can create templates for a
widget's display and then choose which template is active. 

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

    The Site Map widget sets the `@Component` annotation like this:

    ```java
    @Component(
        immediate = true,
        property = "javax.portlet.name=" + SiteNavigationSiteMapPortletKeys.SITE_NAVIGATION_SITE_MAP,
        service = TemplateHandler.class
    )
    public class SiteNavigationSiteMapPortletDisplayTemplateHandler
        extends BasePortletDisplayTemplateHandler {
    }
    ```

    You'll continue stepping through the Site map widget's `TemplateHandler`
    implementation next.

2.  Override the base class' `getClassName()`, `getName(...)`, and
    `getResourceName()` methods:

    ```java
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
    ```

    These methods return the template handler's class name, the template
    handler's name (via
    [resource bundle](/docs/7-2/frameworks/-/knowledge_base/f/localization)),
    and the resource name associated with the Widget Template, respectively.

3.  Override the `getTemplateVariableGroups(...)` method to return your widget
    template's script variable groups. These are used to display hints in the
    template editor palette.

    ```java
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
    ```

    For this example, the *Pages* and *Site Map Display Context* fields are
    added to the default variables in the template editor palette.

    ![Figure 2: You can click a variable to add it to the template editor.](../../images/widget-template-fields.png)

4.  Set your display template configuration file path:

    ```java
    @Override
    protected String getTemplatesConfigPath() {
        return "com/liferay/site/navigation/site/map/web/portlet/template" +
            "/dependencies/portlet-display-templates.xml";
    }
    ```

    This method returns the XML file containing the display template definitions
    available for your portlet. You'll create this file next.

5.  Create your `portlet-display-templates.xml` file to define your display
    template definitions. For example,

    ```xml
    <?xml version="1.0"?>

    <root>
        <template>
            <template-key>site-map-multi-column-layout-ftl</template-key>
            <name>portlet-display-template-name-multi-column-layout</name>
            <description>portlet-display-template-description-multi-column-layout-sitemap</description>
            <language>ftl</language>
            <script-file>com/liferay/site/navigation/site/map/web/portlet/template/dependencies/portlet_display_template_multi_column_layout.ftl</script-file>
            <cacheable>false</cacheable>
        </template>
    </root>
    ```

    This defined template option is read and presented to the user through the
    widget's Configuration menu. Navigate to the Site Map widget's Configuration
    menu and you can confirm the *Multi Column Layout* option is available.

    ![Figure 3: You can choose the Widget Template you want to apply from the widget's Configuration menu.](../../images/widget-config-display.png)

    This template is created using FreeMarker. You'll create this template
    option next.

6.  Create your template script file that you specified in the previous step.
    For the Site Map widget, its Multi Column Layout option is configured in a
    FreeMarker template like this:

    ```markup
    <#if entries?has_content>
        <@liferay_aui.row>
            <#list entries as entry>
                <#if layoutPermission.containsWithoutViewableGroup(permissionChecker, entry, "VIEW")>
                    <@liferay_aui.col width=25>
                        <div class="results-header">
                            <h3>
                                <a

                                <#assign layoutType = entry.getLayoutType() />

                                <#if layoutType.isBrowsable()>
                                    href="${portalUtil.getLayoutURL(entry, themeDisplay)}"
                                </#if>

                                >${entry.getName(locale)}</a>
                            </h3>
                        </div>

                        <@displayPages
                            depth=1
                            pages=entry.getChildren(permissionChecker)
                        />
                    </@liferay_aui.col>
                </#if>
            </#list>
        </@liferay_aui.row>
    </#if>

    <#macro displayPages
        depth
        pages
    >
        <#if pages?has_content && ((depth < displayDepth?number) || (displayDepth?number == 0))>
            <ul class="child-pages">
                <#list pages as page>
                    <li>
                        <a

                        <#assign pageType = page.getLayoutType() />

                        <#if pageType.isBrowsable()>
                            href="${portalUtil.getLayoutURL(page, themeDisplay)}"
                        </#if>

                        >${page.getName(locale)}</a>

                        <@displayPages
                            depth=depth + 1
                            pages=page.getChildren(permissionChecker)
                        />
                    </li>
                </#list>
            </ul>
        </#if>
    </#macro>
    ```

    This template definition enforces page permissions, formats how the pages
    are displayed (multi column), and provides clickable links for each page.

7.  Your widget must define permissions for creating and managing display
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

8.  If your widget hasn't defined Liferay permissions before, create a file
    named `portlet.properties` in the `/resources` folder and add the following
    contents providing the path to your `default.xml`:

    ```properties
    include-and-override=portlet-ext.properties
    resource.actions.configs=resource-actions/default.xml
    ```

9.  Now expose the Widget Template selector to your users. Include the
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

10. You must now extend your view code to render your portlet using the selected
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
