# Implementing Application Display Templates [](id=implementing-application-display-templates-lp-6-2-develop-tutorial)

<!-- You can test this tutorial using the following portlet project 
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/wc/impl-adts/begin/event-listing-portlet)
-->

[Application Display Templates](/use/-/knowledge_base/using-application-display-templates-lp-6-2-use-useportal)
(ADTs) provide--the ability to add custom display settings to your portlets from
the portal. This isn't actually a new concept in Liferay. In some portlets
(e.g., *Web Content*, *Documents and Media*, and *Dynamic Data Lists*), you can
already add as many display options (or templates) as you want. Now you can add
them to your custom portlets, too. The figure below shows what the Display
Template option looks like in a portlet Configuration menu.

![Figure 1: By using a custom display template, your portlet's display can be customized.](../../images/adt-dropdown.png)

In this tutorial, we'll demonstrate how to use the Application Display Templates
API to add an ADT to a portlet. Let's get started learning how.

## Using the Application Display Templates API [](id=using-the-application-display-templates-api-lp-6-2-develop-tutorial)

To leverage the ADT API, there are several steps you need to follow. These
steps involve registering your portlet to use ADTs, defining permissions, and
exposing the ADT functionality to users. Let's walk through these steps:

1. Create and register a custom `*PortletDisplayTemplateHandler` class. Liferay
   provides the [`BasePortletDisplayTemplateHandler`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/portletdisplaytemplate/BasePortletDisplayTemplateHandler.html)
   as a base implementation for you to extend. You can check the
   [TemplateHandler](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/template/TemplateHandler.html)
   interface Javadoc to learn about each template handler method.

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

    As an example `*PortletDisplayTemplateHandler` implementation, you can look
    at
    [`LocationListingPortletDisplayTemplateHandler.java`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/wc/impl-adts/end/event-listing-portlet/docroot/WEB-INF/src/com/samples/portlet/eventlisting/template/LocationListingPortletDisplayTemplateHandler.java).

2. Now that you've created the template handler, declare it with the
   `<template-handler>...</template-handler>` tags in the `<portlet>` element of
   your portlet's `docroot/WEB-INF/liferay-portlet.xml` file. Here's an
   example snippet for some context:

        <?xml version="1.0"?>
        <!DOCTYPE liferay-portlet-app PUBLIC "-//Liferay//DTD Portlet Application 6.2.0//EN" "http://www.liferay.com/dtd/liferay-portlet-app_6_2_0.dtd">
        
        <liferay-portlet-app>
        
            ...
            
            <portlet>
                <portlet-name>yourportlet</portlet-name>
                <icon>/icon.png</icon>
                <configuration-action-class>com.samples.portlet.eventlisting.action.ConfigurationActionImpl</configuration-action-class>
                <template-handler>com.samples.portlet.yourportlet.template.YourEntityPortletDisplayTemplateHandler</template-handler>
                <instanceable>false</instanceable>
                
                ...
                
            </portlet>
            
            ...
            
        </liferay-portlet-app>

3. Since the ability to add ADTs is new to your portlet, you must configure
   permissions so that administrative users can grant permissions to the roles
   that will be allowed to create and manage display templates. Just add the
   action key `ADD_PORTLET_DISPLAY_TEMPLATE` to your portlet's
   `docroot/WEB-INF/src/resource-actions/default.xml` file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.2.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_6_2_0.dtd">
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
	 
4.  Now that your portlet officially supports ADTs, you'll want to expose the
    ADT option to your users. Just include the
    `<liferay-ui:ddm-template-selector>` tag in the JSP file you're using to
    control your portlet's configuration.
   
    For example, it may be helpful for you to insert an `<aui:fieldset>` like
    the following one in your configuration JSP file:
 
        <aui:fieldset>
            <div class="display-template">

                <%
                TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(YourEntity.class.getName());
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

    In this JSP, the `TemplateHandler` object is initialized for the
    `YourEntity` class. Then, the `<liferay-ui:ddm-template-selector>` tag
    specifies the Display Template drop-down menu to be used in the portlet's
    Configuration menu.
    
    As an example JSP, see
    [`configuration.jsp`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/wc/impl-adts/end/event-listing-portlet/docroot/html/locationlisting/configuration.jsp). 

5.  You're almost finished, but you still have to extend your view code to
    render your portlet with the selected ADT. Here is where you decide exactly
    which part of your view will be rendered by the ADT and what will be
    available in the template context.

    First, initialize the Java variables needed for the ADT: 

        <%
        String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
        long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);

        long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);
        %>

    Next, you can test if the ADT is configured, grab the entities to be
    rendered, and render them using the ADT. 

    Here's some example code that demonstrates implementing this:
		
		<c:choose>
			<c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
				<% List<YourEntity> entities = YourEntity.LocalServiceUtil.getLocationsByGroupId(scopeGroupId); %>
		
				<%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, entities) %>
			</c:when>
			<c:otherwise>

			...

            </c:otherwise>
        </c:choose>

    In this step, we initialized variables dealing with the display settings 
    (`displayStyle`, `displayStyleGroupId`, and `portletDisplayDDMTemplateId`), 
    and then used conditional tags to choose between rendering the ADT, or
    displaying the entities some other way. If the
    `portletDisplayDDMTemplateId` exists, the entity list is initialized and
    the ADT is rendered using the page context, template ID, and entities.
    Otherwise, the entities are displayed some other way that you implement.

    For an example that demonstrates implementing this, see
    [`view.jsp`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/wc/impl-adts/end/event-listing-portlet/docroot/html/locationlisting/view.jsp).

Now that your portlet supports ADTs, you can create your own scripts to change
the display of your portlet. You can experiment by adding your own custom ADT.

1. Navigate to *Admin* &rarr; *Configuration* &rarr; *Application Display
   Templates*. Then select *Add* &rarr; *Your Template*. Give your ADT a name
   and insert FreeMarker (like the following code) or Velocity code into the
   template editor, and click *Save*:
   
        <#if entries?has_content>
            Quick List:
            <ul>
            <#list entries as curEntry>
                <li>${curEntry.name} - ${curEntry.streetAddress}, ${curEntry.city}, ${curEntry.stateOrProvince}</li>
            </#list>
            </ul>
        </#if>

2. Go back to your portlet and select *Options* &rarr; *Configuration* and click
   the *Display Template* drop-down. Select the ADT you created, and click
   *Save*.

![Figure 2: The example Quick List template displays entities in a bullet list format.](../../images/quick-list-template.png)
    
Once your script is uploaded into the portal and saved, users with the specified
roles can select the template when they're configuring the display settings of
your portlet on a page. You can visit the
[Using Application Display Templates](/use/-/knowledge_base/using-application-display-templates-lp-6-2-use-useportal)
section in *Using Liferay Portal* for more details on using ADTs.

<!-- We can add similar text to what we have above from "learning-paths" instead
of "Using Liferay Portal", once we have a section on ADTs that we can point to.
-->

Next, we'll provide some recommendations for using ADTs in Liferay Portal.

## Recommendations for Using ADTs [](id=recommendations-for-using-adts-lp-6-2-develop-tutorial)

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
[Using Application Display Templates](/use/-/knowledge_base/using-application-display-templates-lp-6-2-use-useportal)
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

## Next Steps 

[Customizing Liferay Portal](/tutorials/-/knowledge_base/customizing-liferay-portal-lp-6-2-develop-tutorial)

[Localization](/tutorials/-/knowledge_base/localization-lp-6-2-develop-tutorial)

[OpenSocial Gadgets](/tutorials/-/knowledge_base/creating-and-integrating-with-opensocial-liferay-portal-6-2-dev-guide-08-en)

[AlloyUI](/tutorials/-/knowledge_base/alloyui-lp-6-2-develop-tutorial)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/liferay-ui-taglibs-lp-6-2-develop-tutorial)
