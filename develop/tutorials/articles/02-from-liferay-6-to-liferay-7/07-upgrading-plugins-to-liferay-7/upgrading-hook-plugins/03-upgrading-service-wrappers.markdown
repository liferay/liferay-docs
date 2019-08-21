---
header-id: upgrading-service-wrappers
---

# Upgrading Service Wrappers

[TOC levels=1-4]

Upgrading traditional 
[service wrapper hook plugins](/docs/6-2/tutorials/-/knowledge_base/t/overriding-a-portal-service-using-a-hook) 
to @product-ver@ is quick and easy. 

1.  Use [Liferay @ide@'s Code Upgrade Tool](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
    to adapt the plugin's code to @product-ver@'s API. 

2.  Deploy the plugin. 

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

## Related Articles

[Overriding Liferay Services \(Service Wrappers\)](/docs/7-1/tutorials/-/knowledge_base/t/customizing-liferay-services-service-wrappers)

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
