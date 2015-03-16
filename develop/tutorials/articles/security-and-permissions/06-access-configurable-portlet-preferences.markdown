# Controlling Access with Configurable Portlet Preferences

In addition to setting explicit permissions, you can also control access to your 
portlet's features through configurable portlet preferences. Portlet preferences 
are properties for storing basic portlet configuration data. You can make them 
configurable by adding them to a Setup tab in your portlet's Configuration menu. 
Administrators can then use the portlet's permissions to control access to the 
Configuration menu. In the screenshot here, configurable portlet preferences are 
shown for several features of the asset framework.

![Figure 1: Configurable portlet prefrences give you fine grained control over specific features in your portlet.](../../images/portlet-preferences-01.png)

This tutorial first discusses the benefits of using configurable portlet 
prefrences to control access to your portlet's features. Creating and using 
configurable portlet preferences is then presented. You'll be a master of 
configurations in no time flat!

## Deciding to Use Configurable Portlet Preferences

So why bother with configurable portlet preferences for controlling access to 
portlet features? Why not just use permissions? Great question! One answer is 
that configurable portlet preferences give administrators more precise control 
over which users can access individual features in a portlet. For example, 
administrators can give certain users access to the portlet's Configuration menu 
without also giving them access to the portlet's permissions. Also, defining 
explicit permissions for each feature of your portlet can quickly crowd the 
permissions window if you have many features. Consider Liferay's Blogs portlet. 
It has many configurable portlet preferences, as is shown in the following 
screenshot. Implementing each of these settings as a separate permission isn't a 
feasible solution.

![Figure 2: The configurable portlet preferences for Liferay's Blogs portlet.](../../images/portlet-preferences-02.png)

You should now have some idea of the usefulness of configurable portlet 
preferences. Next, their implementation and use is shown.

## Using Configurable Portlet Preferences

Implementing configurable portlet preferences is a straightforward task. For 
instructions, see the tutorial [Using Configurable Portlet Preferences](/tutorials/-/knowledge_base/6-2/using-configurable-portlet-preferences). 
Keep in mind that unless you need to use the portlet preferences with some 
additional logic in your controller, then you can skip the third step of that 
tutorial. Once your configuration JSP and any additional control logic that you 
need is defined, you can use your portlet preferences to control access to the 
features implemented in any other JSP. To do so, simply wrap each feature in a 
`<c:if>` tag that checks the value of the appropriate preference. For example, 
the following code gets the hypothetical portlet preference `"showYourFeature"` 
and then uses it with a `<c:if>` tag to determine if the given feature should be 
shown:

    ```
    <%
    ```
    boolean showYourFeature_view = GetterUtil.getBoolean(portletPreferences.
        getValue("showYourFeature", StringPool.TRUE));
    ```
    %>
    ```
    <c:if test='<%= showYourFeature_view %>'>
        tags for your feature go here
    </c:if>
    ```
    
Administrators can then use the portlet's Permissions tab in the Configuration 
menu to set the roles that can access the Configuration menu. Those with access 
to the Configuration menu are then able to set any preferences defined in the 
Setup tab.

![Figure 3: The Permissions tab of the portlet's Configuration menu.](../../images/portlet-preferences-03.png)

Stupendous! Now you know when and how to use configurable portlet preferences to 
control access to your portlet's features. 

## Related Topics

[MVC Portlets](/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Plugin Security and PACL](/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)
