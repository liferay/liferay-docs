---
header-id: configuring-portlet-properties-for-your-widget
---

# Configuring Portlet Properties for Your Widget

[TOC levels=1-4]

Follow these steps to configure your widget's properties:

1.  Open your generated JavaScript widget's `package.json` file.

2.  Set the properties under the `portlet` entry. Note that these are the same 
    properties you would define in the Java `@Component` annotation of a 
    portlet, as defined in the 
    [liferay-portlet-app_7_2_0.dtd](@platform-ref@/7.2-latest/definitions/liferay-portlet-app_7_2_0.dtd.html). 
    An example configuration is shown below:

    ```json
    "portlet": {
    	"com.liferay.portlet.display-category": "category.sample",
    	"com.liferay.portlet.header-portlet-css": "/css/styles.css",
    	"com.liferay.portlet.instanceable": true,
    	"javax.portlet.name": "my_js_portlet_project",
    	"javax.portlet.security-role-ref": "power-user,user",
    	"javax.portlet.resource-bundle": "content.Language"
    },
    ```
    
3.  Deploy your bundle to apply the changes.

Great! Now you know how to configure your JavaScript widget's properties.

## Related Topics

- [Configuring System Settings and Instance Settings for Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/configuring-system-settings-and-instance-settings-for-your-javascript-widget)
- [Localizing Your Widget](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-widget)
- [Using Translation Features in Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/using-translation-features-in-your-javascript-widget)
