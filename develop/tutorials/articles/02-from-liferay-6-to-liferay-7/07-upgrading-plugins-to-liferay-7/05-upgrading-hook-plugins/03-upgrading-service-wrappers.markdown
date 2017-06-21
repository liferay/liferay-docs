# Upgrading Service Wrappers [](id=upgrading-service-wrappers)

Upgrading traditional [service wrapper hook plugins](/develop/tutorials/-/knowledge_base/6-2/overriding-a-portal-service-using-a-hook) to @product-ver@ is quick and easy. 

1.  Use @liferay-ide@'s Code Upgrade Tool to adapt the plugin's code to
    @product-ver@'s API.  

2.  Deploy the plugin. 

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

**Related Articles**

[Overriding Liferay Services \(Service Wrappers\)](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services-service-wrappers)
