# Implementing Application Display Templates [](id=implementing-application-display-templates)

[Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates)
(ADTs) provide--the ability to add custom display templates to your portlets
from the portal. The figure below shows what the Display Template option looks
like in a portlet Configuration menu.

![Figure 1: By using a custom display template, your portlet's display can be customized.](../../images/adt-dropdown.png)

In this tutorial, you'll learn how to use the Application Display Templates API
to add an ADT to a portlet.

## Using the Application Display Templates API [](id=using-the-application-display-templates-api)

To leverage the ADT API, there are several steps you need to follow. These
steps involve registering your portlet to use ADTs, defining permissions, and
exposing the ADT functionality to users. You'll walk through these steps now:

1. Create and register a custom `*PortletDisplayTemplateHandler` component.
   Liferay provides the [BasePortletDisplayTemplateHandler](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/portletdisplaytemplate/BasePortletDisplayTemplateHandler.html)
   as a base implementation for you to extend. You can check the
   [TemplateHandler](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateHandler.html)
   interface Javadoc to learn about each template handler method.
   
    The Component annotation ties your handler to a specific portlet setting
    the property `javax.portlet.name` as the portlet name of your portlet. The 
    same property should be found in your portlet class. For example:

        @Component(
            immediate = true,
            property = {
                "javax.portlet.name="+ AssetCategoriesNavigationPortletKeys.ASSET_CATEGORIES_NAVIGATION
            },
            service = TemplateHandler.class
        )

    Each of the methods in this class have a significant role in defining and
    implementing ADTs for your custom portlet. View the list below for a
    detailed explanation for each method defined specifically for ADTs:

   - **getClassName():** Defines the type of entry your portlet is rendering.
   - **getName():** Declares the name of your ADT type (typically, the name of
   the portlet).
   - **getResourceName():** Specifies which resource is using the ADT (e.g., a
   portlet) for permission checking. This method must return the portlet's
   [Fully Qualified Portlet ID](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Fully+Qualified+Portlet+ID)
   \(FQPI\).
   - **getTemplateVariableGroups():** Defines the variables exposed in the
   template editor.

   As an example `*PortletDisplayTemplateHandler` implementation, you can look
   at
   [WikiPortletDisplayTemplateHandler.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/portlet/template/WikiPortletDisplayTemplateHandler.java).

2. Since the ability to add ADTs is new to your portlet, you must configure
   permissions so that administrative users can grant permissions to the roles
   that will be allowed to create and manage display templates. Add the action
   key `ADD_PORTLET_DISPLAY_TEMPLATE` to your portlet's
   `/src/main/resources/resource-actions/default.xml` file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">
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

3. Now that your portlet officially supports ADTs, you'll want to expose the
   ADT option to your users. Just include the
   `<liferay-ui:ddm-template-selector>` tag in the JSP file you're using to
   control your portlet's configuration.

    For example, it may be helpful for you to insert an `<aui:fieldset>` in your
    configuration JSP file, like the following:

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

    In this JSP, the `<liferay-ddm:template-selector>` tag specifies the Display
    Template drop-down menu to be used in the portlet's Configuration menu. The
    variables `displayStyle` and `displayStyleGroupId` are preferences that your
    portlet stores when you use this taglib and your portlet uses the
    [BaseJSPSettingsConfigurationAction](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/portlet/BaseJSPSettingsConfigurationAction.java)
    or
    [DefaultConfigurationAction](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/portlet/DefaultConfigurationAction.java).
    Otherwise, you would need to obtain the value of those parameters and store
    them manually in your configuration class.

    As an example JSP, see the Wiki application's
    [configuration.jsp](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/wiki/wiki-web/src/main/resources/META-INF/resources/wiki/configuration.jsp). 

4. You must now extend your view code to render your portlet with the selected
   ADT. This allows you to decide which part of your view will be rendered by
   the ADT and what will be available in the template context.

    First, initialize the Java variables needed for the ADT: 

        <%
        String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
        long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);
        %>

    Next, you can test if the ADT is configured, grab the entities to be
    rendered, and render them using the ADT. The tag
    `<liferay-ddm:template-renderer>` aids with this process. It will
    automatically use the selected template or render its body if no template is
    selected.

    Here's some example code that demonstrates implementing this:

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

    In this step, you initialized variables dealing with the display settings 
    (`displayStyle` and `displayStyleGroupId`) and passed them to the tag along
    with other parameterers listed below:

    - `className`: your entity's class name.
    - `contextObjects`: accepts a `Map<String, Object>` with
    any object you want to the template context.
    - `entries`: accepts a list of your entities (e.g., `List<YourEntity>`).

    For an example that demonstrates implementing this, see
    [configuration.jsp](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/site-navigation/site-navigation-site-map-web/src/main/resources/META-INF/resources/configuration.jsp).

Now that your portlet supports ADTs, you can create your own scripts to change
the display of your portlet. You can experiment by adding your own custom ADT.

1. Navigate to Site Admin* &rarr; *Configuration* &rarr; *Application Display
   Templates*. Then select *Add* (![Add](../../images/icon-add.png)) &rarr;
   *Your Template*. Give your ADT a name and insert FreeMarker (like the
   following code) or Velocity code into the template editor, and click *Save*:
   
        <#if entries?has_content>
            Quick List:
            <ul>
            <#list entries as curEntry>
                <li>${curEntry.name} - ${curEntry.streetAddress}, ${curEntry.city}, ${curEntry.stateOrProvince}</li>
            </#list>
            </ul>
        </#if>

2. Go back to your portlet and select *Options*
   (![Options](../../images/icon-app-options.png)) &rarr; *Configuration* and
   click the *Display Template* drop-down. Select the ADT you created, and click
   *Save*.

![Figure 2: The example Social template for the Wiki application provides extended social functionalities.](../../images/wiki-social-adt.png)
    
Once your script is uploaded into the portal and saved, users with the specified
roles can select the template when they're configuring the display settings of
your portlet on a page. You can visit the
[Styling Apps with Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates)
section for more details on using ADTs.

<!-- We can add similar text to what we have above from "learning-paths" instead
of "Using Liferay Portal", once we have a section on ADTs that we can point to.
-->

Next, we'll provide some recommendations for using ADTs in Liferay Portal.

## Recommendations for Using ADTs [](id=recommendations-for-using-adts)

You've harnessed a lot of power by learning to leverage the ADT API. Be
careful, for with great power, comes great responsibility! To that end, you'll
learn about some practices you can use to optimize your portlet's performance
and security. 
 
First let's talk about security. You may want to hide some classes or packages
from the template context, to limit the operations that ADTs can perform on your
portal. Liferay provides some portal system settings, which can be accessed by
navigating to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*
&rarr; *Foundation* &rarr; *FreeMarker/Velocity Engine*, to define the
restricted classes, packages, and variables. In particular, you may want to add
`serviceLocator` to the list of default values assigned to the FreeMarker and
Velocity Engine Restricted variables.

Application Display Templates introduce additional processing tasks when your
portlet is rendered. To minimize negative effects on performance, make your
templates as minimal as possible by focusing on the presentation, while using
the existing API for complex operations. The best way to make Application
Display Templates efficient is to know your template context well, and
understand what you can use from it. Fortunately, you don't need to memorize
the context information, thanks to Liferay's advanced template editor!

To navigate to the template editor for ADTs, go to the Site Admin menu and
select *Configuration* &rarr; *Application Display Templates* and then click
*Add* and select the specific portlet on which you decide to create an ADT.

The template editor provides fields, general variables, and util variables
customized for the portlet you chose. These variable references can be found on
the left-side panel of the template editor. You can use them by simply placing
your cursor where you'd like the variable placed, and clicking the desired
variable to place it there. You can learn more about the template editor in the
[Styling Apps with Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates)
section.

<!-- We can add similar text to what we have above from "learning-paths" instead
of "Using Liferay Portal", once we have a section on ADTs that we can point to.
-->

Finally, don't forget to run performance tests and tune the template cache
options by modifying the *Resource modification check* field in *System
Settings* &rarr; *Foundation* &rarr; *FreeMarker/Velocity Engine*.

The cool thing about ADTs is the power they provide to your Liferay portlets,
providing infinite ways of editing your portlet to provide new interfaces for
your portal users. You stepped through how to configure ADTs for a custom
portlet, tried out a sample template, and ran through important recommendations
for using ADTs, which included security and performance.

## Related Topics [](id=related-topics)

[Styling Apps with Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates)

[Frontend Customizations](/develop/tutorials/-/knowledge_base/7-0/frontend-customizations)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
