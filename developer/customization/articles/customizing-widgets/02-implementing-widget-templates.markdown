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

    The
    [`WikiPortletDisplayTemplateHandler`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/portlet/template/WikiPortletDisplayTemplateHandler.java)
    is a good example `*PortletDisplayTemplateHandler` implementation.

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

3.  If your application hasn't defined Liferay permissions before, Create a file
    named `portlet.properties` in the `/resources` folder and add the following
    contents providing the path to your `default.xml`:

    ```properties
    include-and-override=portlet-ext.properties
    resource.actions.configs=resource-actions/default.xml
    ```

4.  Now expose the Widget Template selector to your users. Include the
    `<liferay-ui:ddm-template-selector>` tag in the JSP file you're using to
    control your portlet's configuration.

    <!-- The tag above doesn't match the tag in the code snippet below. -Rich
    -->

    For example, it may be helpful for you to insert an `<aui:fieldset>` in your
    configuration JSP file like this:

    ```markup
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

    For an example that demonstrates implementing this, see
    [`configuration.jsp`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/site-navigation/site-navigation-site-map-web/src/main/resources/META-INF/resources/configuration.jsp).

Awesome! Your portlet now supports Widget Templates! Once your script is
uploaded and saved, Users with the specified Roles can select the template when
they're configuring the display settings of your portlet on a
page. You can visit the
[Styling Widgets with Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
section for more details on using Widget Templates.
