---
header-id: setting-default-preferences-for-an-embedded-portlet
---

# Setting Default Preferences for an Embedded Portlet

[TOC levels=1-4]

Follow these steps to set default portlet preferences for an embedded portlet:

1.  Retrieve portlet preferences using the `freeMarkerPortletPreferences` 
    object. The example below retrieves the `barebone`:

    ```markup
    <#assign preferences = freeMarkerPortletPreferences.getPreferences(
      "portletSetupPortletDecoratorId", "barebone"
    ) />
     ```
 
2.  Set the `defaultPreferences` attribute of the embedded portlet to the 
    `freeMarkerPortletPreferences` object you just configured:

    ```markup
    <@liferay_portlet["runtime"]
        defaultPreferences="${preferences}"
        portletName="com_liferay_login_web_portlet_LoginPortlet"
    />
    ```

Below are some additional attributes you can define for embedded portlets:

| Preference | Description |
| --- | --- |
| **defaultPreferences** | A string of Portlet Preferences for the application. This includes look and feel configurations. |
| **instanceId** | The instance ID for the app, if the application is instanceable. |
| **persistSettings** | Whether to have an application use its default settings, which will persist across layouts. The default value is *true*. |
| **settingsScope** | Specifies which settings to use for the application. The default value is `portletInstance`, but it can be set to `group` or `company`. |

Now you know how to set default preferences for embedded portlets! 

## Related Topics

- [Embedding Portlets by Name](/docs/7-2/frameworks/-/knowledge_base/f/embedding-a-portlet-by-portlet-name)
- [Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)
- [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
