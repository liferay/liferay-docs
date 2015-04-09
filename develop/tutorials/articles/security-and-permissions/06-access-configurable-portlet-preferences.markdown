# Controlling Access with Configurable Portlet Preferences [](id=controlling-access-with-configurable-portlet-preferences)

As a Liferay application developer, using Liferay's permissions system isn't
your only option for controlling access to the features of your application. You
can allow access to your portlet's features to be configured through
configurable portlet preferences. Portlet preferences are properties for storing
basic portlet configuration data. You can make portlet preferences configurable
by adding them to a Setup tab in your portlet's Configuration menu. Users with
permission to access your portlet's Configuration menu can adjust the
preferences. In the screenshot below, configurable portlet preferences are shown
for several features of the asset framework.

![Figure 1: Configurable portlet preferences give you fine-grained control over specific features in your portlet.](../../images/portlet-preferences-01.png)

Read on to learn about the benefits of using configurable portlet preferences to
control access to your portlet's features. With Liferay, it's easy to create
portlet preferences and allow users to configure them. You'll be a master of
configurations in no time flat!

## Deciding to Use Configurable Portlet Preferences [](id=deciding-to-use-configurable-portlet-preferences)

So why bother with configurable portlet preferences for controlling access to
portlet features? Why not just use permissions? First of all, portlet
preferences can be a simpler approach. You only have to worry about one
permission: the permission to access your portlet's Configuration window. Any
user with permission to access your portlet's Configuration window can adjust
any of your portlet's preferences. For example, administrators can allow certain
users access to the portlet's Configuration window without also allowing them to
edit to the portlet's permissions.

By default, Liferay stores a set of portlet preferences for each user, for each
portlet instance. However, this can be adjusted by changing the following
`liferay-portlet.xml` settings of your portlet from these defaults:

- `<preferences-company-wide>false</preferences-company-wide>`
- `<preferences-unique-per-layout>true</preferences-unique-per-layout>`
- `<preferences-owned-by-group>true</preferences-owned-by-group>`

You shouldn't use portlet preferences as a *substitute* for permissions.
However, if you want to control access to certain portlet features via portlet
preferences, make sure you understand how these settings affect your portlet's
preferences.

A second reason to use portlet preferences is that if you have lots of features,
defining explicit permissions for each feature of your portlet can quickly crowd
the permissions window. Consider Liferay's Blogs portlet. It has many
configurable portlet preferences, as is shown in the following screenshot.
Implementing each of these settings as a separate permission isn't a feasible
solution.

![Figure 2: The configurable portlet preferences for Liferay's Blogs portlet.](../../images/portlet-preferences-02.png)

Now that you understand how portlet preferences can be useful, it's time to
learn how to implement them.

## Implementing Configurable Portlet Preferences [](id=implementing-configurable-portlet-preferences)

Implementing configurable portlet preferences is a straightforward task. For 
instructions, see the tutorial [Using Configurable Portlet Preferences](/develop/tutorials/-/knowledge_base/6-2/using-configurable-portlet-preferences). 
Keep in mind that unless you need to use the portlet preferences with some
additional logic in your controller, you can skip the third step of that
tutorial. Once you've created your configuration JSP and any additional control
logic that you need, you can use your portlet preferences to control access to
the features implemented in any other JSP. To do so, simply wrap each feature in
a `<c:if>` tag that checks the value of the appropriate preference. For example,
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
        <!--tags for your feature go here-->
    </c:if>
    ```

Administrators can then use the portlet's Permissions tab in the Configuration
menu to set the roles that can access the Configuration menu. Those with access
to the Configuration menu are then able to set any preferences defined in the
Setup tab.

![Figure 3: The Permissions tab of the portlet's Configuration menu.](../../images/portlet-preferences-03.png)

Stupendous! Now you know when and how to use configurable portlet preferences to 
control access to your portlet's features. 

## Related Topics [](id=related-topics)

[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Plugin Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)
