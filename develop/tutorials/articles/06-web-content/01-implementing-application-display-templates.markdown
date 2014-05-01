# Implementing Application Display Templates

A portlet's Display Settings (*Options* &rarr; *Configuration* &rarr; *Setup*
&rarr; *Display Settings*) let you customize its display. They come built in
with Liferay, so you don't have to do anything special to enable them for your
custom portlets. But what if you need settings in addition to Liferay's default
display settings? You could develop a theme or hook with the display options you
need, but it'd be nice if you could apply particular display options to specific
portlet instances without having to redeploy any plugins. Ideally, you should be
able to provide authorized portal users the ability to apply custom display
settings to portlets. This saves you from having to change portlet configuration
code every time you need new settings. 

Be of good cheer! That's precisely what Application Display Templates (ADTs)
provide-- the ability to add custom display settings to your portlets from the
portal. This isn't actually a new concept in Liferay. In some portlets (e.g.,
*Web Content*, *Documents and Media*, and *Dynamic Data Lists*), you can already
add as many display options (or templates) as you want. Now you can add them to
your custom portlets, too. The figure below shows what the Display Template
option looks like in a portlet Configuration menu.

![Figure 1: By using a custom display template, your portlet's display can be customized.](../../images/adt-dropdown.png)

In this tutorial, we'll demonstrate how to use the Application Display Templates
API to add this new feature to a sample portlet called the Location Listing
portlet. Let's get started learning how.

## Using the Application Display Templates API

To leverage the ADT API, there are several steps you need to follow. These
steps involve registering your portlet to use ADTs, defining permissions, and
exposing the ADT functionality to users. We'll demonstrate these steps by
enabling Application Display Templates for the Location Listing Portlet.

1. As a starting point, use the Location Listing portlet which is available in
   the <https://github.com/liferay/liferay-docs> Github repository
   [here](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/06-web-content/01-implementing-application-display-templates/begin/event-listing-portlet).
   You'll need to clone the repository if you haven't already done so. Then copy
   the `event-listing-portlet` folder into the `portlets` folder of your Liferay
   Plugins SDK. Although the project is named `event-listing-portlet`, the
   Location Listing portlet also resides inside this project as an additional
   portlet.

2. Create and register a custom `PortletDisplayTemplateHandler` class. To do
   this, create a new package called
   `com.nosester.portlet.eventlisting.template`. Then create a new class in that
   package called `LocationListingPortletDisplayTemplateHandler` and copy the
   following code into that class:

        package com.nosester.portlet.eventlisting.template;

        import java.util.List;
        import java.util.Locale;
        import java.util.Map;

        import com.liferay.portal.kernel.language.LanguageUtil;
        import com.liferay.portal.kernel.portletdisplaytemplate.BasePortletDisplayTemplateHandler;
        import com.liferay.portal.kernel.template.TemplateVariableGroup;
        import com.liferay.portal.kernel.util.StringPool;
        import com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateConstants;
        import com.nosester.portlet.eventlisting.model.Location;
        import com.nosester.portlet.eventlisting.util.PortletKeys;

        public class LocationListingPortletDisplayTemplateHandler extends
                BasePortletDisplayTemplateHandler {

            public String getClassName() {
                return Location.class.getName();
            }

            public String getName(Locale locale) {
                String locations = LanguageUtil.get(locale, "locations");

                return locations.concat(StringPool.SPACE).concat(
                        LanguageUtil.get(locale, "template"));
            }

            public String getResourceName() {
                return PortletKeys.LOCATION_LISTING_PORTLET_ID;
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
                    "locations", List.class, PortletDisplayTemplateConstants.ENTRIES,
                    "location", Location.class, "curLocation", "name");

                return templateVariableGroups;
            }
        }

    To join the exclusive ADT club, your portlet must sign a contract,
    committing itself to fulfill all the necessary Application Display Template
    requirements. In other words, you have to create your own
    `PortletDisplayTemplateHandler` implementation by extending the
    `BasePortletDisplayTemplateHandler` methods. You can check the
    [TemplateHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/template/TemplateHandler.html)
    interface Javadoc to learn about each method.

    Each of the methods in this class have a significant role in defining and
    implementing ADTs for your custom portlet. View the list below for a
    detailed explanation for each method defined specifically for ADTs:
    - **getClassName():** Defines the type of entry your portlet is rendering.
    - **getName():** Declares the name of your ADT type (typically, the name of
    the portlet).
    - **getResourceName():** Specifies which resource is using the ADT (e.g., a
    portlet) for permission checking.
    - **getTemplateVariableGroups():** Defines the variables exposed in the
    template editor.

3. Now that we've created the template handler, declare it with the
   `<template-handler>...</template-handler>` tags in the Location Listing
   Portlet's `<portlet>` element of the `docroot/WEB-INF/liferay-portlet.xml`
   file:

        <?xml version="1.0"?>
        <!DOCTYPE liferay-portlet-app PUBLIC "-//Liferay//DTD Portlet Application 6.2.0//EN" "http://www.liferay.com/dtd/liferay-portlet-app_6_2_0.dtd">
        
        <liferay-portlet-app>
            ...
            <portlet>
                <portlet-name>locationlisting</portlet-name>
                <icon>/icon.png</icon>
                <configuration-action-class>com.nosester.portlet.eventlisting.action.ConfigurationActionImpl</configuration-action-class>
                <template-handler>com.nosester.portlet.eventlisting.template.LocationListingPortletDisplayTemplateHandler</template-handler>
                <instanceable>false</instanceable>
                ...
            </portlet>
            ...
        </liferay-portlet-app>

4. Since the ability to add ADTs is new to your portlet, we need to configure
   permissions so that administrative users can grant permissions to the roles
   that will be allowed to create and manage display templates. Just add the
   action key `ADD_PORTLET_DISPLAY_TEMPLATE` to your portlet's
   `docroot/WEB-INF/src/resource-actions/default.xml` file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.2.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_2_0.dtd">
        <resource-action-mapping>
            ...
            <portlet-resource>
                <portlet-name>locationlisting</portlet-name>
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
	 
5. Now that your portlet officially supports ADTs, you'll want to expose the
   ADT option to your users. Just include the `liferay-ui:ddm-template-selector`
   taglib in the JSP file you're using to control your portlet's configuration
   mode. We'll add the display settings to the Location Listing Portlet's
   `docroot/html/locationlisting/configuration.jsp` file:
 
        <%@ include file="../init.jsp" %>
        ...
        <aui:form action="<%= configurationURL %>" method="post" name="fm">
            <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

            <aui:fieldset>
                <div class="display-template">

                    <%
                    TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(Location.class.getName());
                    %>

                    <liferay-ui:ddm-template-selector
                        classNameId="<%= PortalUtil.getClassNameId(templateHandler.getClassName()) %>"
                        displayStyle="<%= displayStyle %>"
                        displayStyleGroupId="<%= displayStyleGroupId %>"
                        refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
                        showEmptyOption="<%= true %>"
                    />
                </div>
            </aui:fieldset>
        ...
        </aui:form>

    In this JSP, the `TemplateHandler` object is initialized. Then, we specify
    the `liferay-ui:ddm-template-selector` taglib, which implements the Display
    Template drop-down menu in the Location Listing Portlet's Configuration
    menu.

6. You're almost finished, but you still have to extend your view code to
   render your portlet with the selected ADT. Here is where you decide exactly
   which part of your view will be rendered by the ADT and what will be
   available in the template context. To do this, add the following code
   outlined below to your Location Listing Portlet's
   `docroot/html/locationlisting/view.jsp` file:
 
        <%@ include file="/html/init.jsp" %>

        This is the <b>Location Listing Portlet</b> in View mode.
        ...
        <%
        String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
        long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);

        long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);

        boolean showLocationAddress_view = GetterUtil.getBoolean(portletPreferences.getValue("showLocationAddress", StringPool.TRUE));
        %>

        <c:choose>
            <c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
                <% List<Location> locations = LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId); %>

                <%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, locations) %>
            </c:when>
            <c:otherwise>
                <liferay-ui:search-container emptyResultsMessage="location-empty-results-message">
                    <liferay-ui:search-container-results
                        results="<%= LocationLocalServiceUtil.getLocationsByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
                        total="<%= LocationLocalServiceUtil.getLocationsCountByGroupId(scopeGroupId) %>"
                    />
                ...
                </liferay-ui:search-container>
            </c:otherwise>
        </c:choose>

    In this code snippet, we initialized variables dealing with the display
    settings (`displayStyle`, `displayStyleGroupId`, and
    `portletDisplayDDMTemplateId`), and then used a do-otherwise statement to
    choose between rendering the ADT, or displaying what was originally in the
    `view.jsp`. If the `portletDisplayDDMTemplateId` exists, the locations list
    is initialized and the ADT is rendered using the page context, template ID,
    and locations.

Now that our portlet supports ADTs, you can create your own scripts to change
the display of your portlet. We'll experiment by adding our own custom ADT.

1. Navigate to *Admin* &rarr; *Configuration* &rarr; *Application Display
   Templates*. Then select *Add* &rarr; *Locations Template*. Give your ADT a
   name and insert the following FreeMarker code into the template editor, and
   click *Save*:
   
        <#if entries?has_content>
            Quick List:
            <ul>
            <#list entries as curLocation>
                <li>${curLocation.name} - ${curLocation.streetAddress}, ${curLocation.city}, ${curLocation.stateOrProvince}</li>
            </#list>
            </ul>
        </#if>

2. Go back to your Location Listing Portlet and select *Options* &rarr;
   *Configuration* and click the *Display Template* drop-down. Select the ADT
   you created, and click *Save*.

![Figure 2: The Quick List template gives your locations in a bullet list format.](../../images/quick-list-template.png)
    
For the Location Listing portlet, we've created a basic FreeMarker script that
takes our locations from the default table format and displays them and their
selected fields in a bullet list format.
    
Once your script is uploaded into the portal and saved, users with the specified
roles can select the template when they're configuring the display settings of
your portlet on a page. You can visit the [Using Application Display
Templates](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-application-display-templates-liferay-portal-6-2-user-guide-07-en)
section in *Using Liferay Portal* for more details on using ADTs.

<!-- We can add similar text to what we have above from "learning-paths" instead
of "Using Liferay Portal", once we have a section on ADTs that we can point to.
-->

Next, we'll provide some recommendations for using ADTs in Liferay Portal.

## Recommendations for Using ADTs

You've harnessed a lot of power by learning to leverage the ADT API. Be
careful, for with great power, comes great responsibility! To that end, let's
talk about some practices you can use to to optimize your portlet's performance
and security. 
 
First let's talk about security. You may want to hide some classes or packages
from the template context, to limit the operations that ADTs can perform on
your portal. Liferay provides some portal properties to define the restricted
classes, packages, and variables. You can override the following portal
properties via the `portal-ext.properties` file.

    freemarker.engine.restricted.classes 
    freemarker.engine.restricted.packages
    freemarker.engine.restricted.variables
    velocity.engine.restricted.classes 
    velocity.engine.restricted.packages
    velocity.engine.restricted.variables

In particular, you may want to add `serviceLocator` to the list of default
values assigned to the `freemarker.engine.restricted.variables` and
`velocity.engine.restricted.variables` portal properties. Make sure to only add
to the classes, packages, and variables restricted by default by
`portal.properties`. Descriptions of Liferay Portal's FreeMarker engine and
Velocity engine properties are available on
[docs.liferay.com](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html). 

Application Display Templates introduce additional processing tasks when your
portlet is rendered. To minimize negative effects on performance, make your
templates as minimal as possible by focusing on the presentation, while using
the existing API for complex operations. The best way to make Application
Display Templates efficient is to know your template context well, and
understand what you can use from it. Fortunately, you don't need to memorize
the context information, thanks to Liferay's advanced template editor!

To navigate to the template editor for ADTs, go to *Admin* &rarr;
*Configuration* &rarr; *Application Display Templates* and click *Add* and
select the specific portlet on which you decide to create an ADT.

The template editor provides fields, general variables, and util variables
customized for the portlet you chose. These variable references can be found on
the left-side panel of the template editor. You can use them by simply placing
your cursor where you'd like the variable placed, and clicking the desired
variable to place it there. You can learn more about the template editor in the
[Using Application Display
Templates](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-application-display-templates-liferay-portal-6-2-user-guide-07-en)
section of *Using Liferay Portal*.

<!-- We can add similar text to what we have above from "learning-paths" instead
of "Using Liferay Portal", once we have a section on ADTs that we can point to.
-->

Finally, don't forget to run performance tests and tune the template cache
options by overriding the following portal properties in your
`portal-ext.properties` file: 

    freemarker.engine.resource.modification.check.interval 
    velocity.engine.resource.modification.check.interval

The cool thing about ADTs is the power they provide to your Liferay portlets,
providing infinite ways of editing your portlet to provide new interfaces for
your portal users. We stepped through how to configure ADTs for a custom portlet
like the Location Listing portlet, tried out a sample template, and ran through
important recommendations for using ADTs, which included security and
performance.

