# Configuring Portlet Properties for Your JS Portlet [](id=configuring-portlet-properties-for-your-js-portlet)

Follow these steps to configure your portlet's properties:

1.  Open your generated JavaScript portlet's `package.json` file.

2.  Set the properties under the `portlet` entry. Note that these are the same 
    properties you would define in the Java `@Component` annotation of a 
    portlet, as defined in the 
    [liferay-portlet-app_7_2_0.dtd](@platform-ref@/7.2-latest/definitions/liferay-portlet-app_7_2_0.dtd.html). 
    An example configuration is shown below:

        "portlet": {
        	"com.liferay.portlet.display-category": "category.sample",
        	"com.liferay.portlet.header-portlet-css": "/css/styles.css",
        	"com.liferay.portlet.instanceable": true,
        	"javax.portlet.name": "my_js_portlet_project",
        	"javax.portlet.security-role-ref": "power-user,user",
        	"javax.portlet.resource-bundle": "content.Language"
        },
    
3.  Deploy your bundle to apply the changes.

Great! Now you know how to configure your JavaScript portlet's properties. 

## Related Topics [](id=related-topics)

- [Configuring System Settings and Instance Settings for Your JavaScript Portlet](/docs/7-2/frameworks/-/knowledge_base/f/configuring-system-settings-and-instance-settings-for-your-js-portlet)
- [Localizing Your Portlet](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-portlet)
- [Using Translation Features in Your JavaScript Portlet](/docs/7-2/frameworks/-/knowledge_base/f/using-translation-features-in-your-javascript-portlet)
