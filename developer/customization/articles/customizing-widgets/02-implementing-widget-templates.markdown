---
header-id: implementing-widget-templates
---

# Implementing Widget Templates

[TOC levels=1-4]

[Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
let you add custom display templates to your widgets from the portal. The figure
below shows what the Display Template option looks like in a widget's
Configuration menu.

![Figure 1: By using a custom display template, your portlet's display can be customized.](../../images/widget-template-dropdown.png)

To add Widget Template support in your custom portlet, follow the steps below.

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

    As an example `*PortletDisplayTemplateHandler` implementation, you can look
    at the
    [`WikiPortletDisplayTemplateHandler`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/portlet/template/WikiPortletDisplayTemplateHandler.java)
    class.

2.  Since the ability to add Widget Templates is new to your portlet, you must
    configure permissions so that administrative users can grant permissions to
    the roles that will be allowed to create and manage display templates. Add
    the action key `ADD_PORTLET_DISPLAY_TEMPLATE` to your portlet's
    `/src/main/resources/resource-actions/default.xml` file:

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

3.  Next, you must ensure that @product@ can find the updated `default.xml` 
    with the new resource action when you deploy the module. Create a file 
    named `portlet.properties` in the `/resources` folder and add the following
    contents providing the path to your `default.xml`:

    ```properties
    include-and-override=portlet-ext.properties
    resource.actions.configs=resource-actions/default.xml
    ```

4.  Now that your portlet officially supports Widget Templates, you should
    expose the Widget Template option to your users. Include the
    `<liferay-ui:ddm-template-selector>` tag in the JSP file you're using to
    control your portlet's configuration.

    For example, it may be helpful for you to insert an `<aui:fieldset>` in your
    configuration JSP file like this:

    ```
    <aui:fieldset>
        <div class="display-template">
            <liferay-ddm:template-selector
                classNameId="<%= YourEntity.class.getName() %>"
                displayStyle="<%= displayStyle %>"
                displayStyleGroupId="<%= displayStyleGroupId %>"
                refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
                showEmptyOption="<%= true %>"
            />
        </div>
    </aui:fieldset>
    ```

    In this JSP, the `<liferay-ddm:template-selector>` tag specifies the Display
    Template drop-down menu to be used in the widget's Configuration menu.

    As an example JSP, see the Wiki widget's
    [`configuration.jsp`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/wiki/wiki-web/src/main/resources/META-INF/resources/wiki/configuration.jsp). 

5.  You must now extend your view code to render your portlet with the selected
    Widget Template. This lets you decide which part of your view is rendered by
    the Widget Template and what is available in the template context.

    First, initialize the Java variables needed for the Widget Template: 

    ```
    <%
    String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
    long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);
    %>
    ```

    Next, you can test if the Widget Template is configured, grabs the entities
    to be rendered, and renders them using the Widget Template. The tag
    `<liferay-ddm:template-renderer>` aids with this process. It automatically
    uses the selected template, or renders its body if no template is selected.

    Here's some example code that demonstrates implementing this:

    ```
    <liferay-ddm:template-renderer
        className="<%= YourEntity.class.getName() %>"
        contextObjects="<%= contextObjects %>"
        displayStyle="<%= displayStyle %>"
        displayStyleGroupId="<%= displayStyleGroupId %>"
        entries="<%= yourEntities %>"
    >

        <%-- The code that will be rendered by default when there is no
        template available should be inserted here. --%>

    </liferay-ddm:template-renderer>
    ```

    In this step, you initialized variables dealing with the display settings 
    (`displayStyle` and `displayStyleGroupId`) and passed them to the tag along
    with other parameters.

    For an example that demonstrates implementing this, see
    [`configuration.jsp`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/site-navigation/site-navigation-site-map-web/src/main/resources/META-INF/resources/configuration.jsp).

Awesome! Your portlet now supports Widget Templates! Once your script is
uploaded into the portal and saved, users with the specified roles can select
the template when they're configuring the display settings of your portlet on a
page. You can visit the
[Styling Widgets with Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
section for more details on using Widget Templates.
