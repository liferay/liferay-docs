---
header-id: disabling-spa
---

# Disabling SPA

[TOC levels=1-4]

Certain elements of your page may require a regular navigation to work properly. 
For example, you may have downloadable content that you want to share with the 
user. In these cases, SPA must be disabled for those specific elements. You can 
disable SPA at these scopes:

- disable SPA across an entire @product@ instance
- disable SPA in a portlet
- Disable SPA in individual elements

Follow the steps in the corresponding section to disable SPA for that scope. 

## Disabling SPA across an Instance

To disable SPA across an entire @product@ instance, add the following line to 
your `portal-ext.properties`:

```properties
javascript.single.page.application.enabled = false
```

## Disabling SPA for a Portlet

To disable SPA for a portlet, you must blacklist it. To blacklist a portlet from 
SPA, follow these steps:

1.  Open your portlet class.

2.  Set the `com.liferay.portlet.single-page-application` property to false:

    ```java
    com.liferay.portlet.single-page-application=false
    ```

    If you prefer, you can set this property to false in your `portlet.xml` 
    instead by adding the following property to the `<portlet>` section:

    ```xml
    <single-page-application>false</single-page-application>
    ```

3.  Alternatively, you can override the 
    [`isSinglePageApplication` method](@platform-ref@/7.2-latest/javadocs/portal-impl/com/liferay/portal/model/impl/PortletImpl.html#isSinglePageApplication--)
    of the portlet to return `false`.

## Disabling SPA for an Individual Element

To disable SPA for a form or link follow these steps:

1.  Add the `data-senna-off` attribute to the element.

2.  Set the value to `true`. See the example below: 

    ```html
    <a data-senna-off="true" href="/pages/page2.html">Page 2</a>
    ```

Nice! Now you know how to disable SPA in your app. 

## Related Topics

- [Configuring SPA System Settings](/docs/7-2/frameworks/-/knowledge_base/f/configuring-spa-system-settings)
- [Specifying How Resources Are Loaded During SPA Navigation](/docs/7-2/frameworks/-/knowledge_base/f/specifying-how-resources-are-loaded-during-navigation)
- [Detaching Global Listeners](/docs/7-2/frameworks/-/knowledge_base/f/detaching-global-listeners)
