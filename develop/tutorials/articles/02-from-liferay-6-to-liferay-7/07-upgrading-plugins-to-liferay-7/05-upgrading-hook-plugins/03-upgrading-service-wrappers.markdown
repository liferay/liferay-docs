# Upgrading Service Wrappers [](id=upgrading-service-wrappers)

Upgrading traditional 
[service wrapper hook plugins](/develop/tutorials/-/knowledge_base/6-2/overriding-a-portal-service-using-a-hook) 
to @product-ver@ is quick and easy. 

1.  Use [Liferay @ide@'s Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
    to adapt the plugin's code to @product-ver@'s API. 

2.  Deploy the plugin. 

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

## Related Articles [](id=related-articles)

[Overriding Liferay Services \(Service Wrappers\)](/develop/tutorials/-/knowledge_base/7-1/customizing-liferay-services-service-wrappers)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-1/upgrading-the-liferay-maven-build)
