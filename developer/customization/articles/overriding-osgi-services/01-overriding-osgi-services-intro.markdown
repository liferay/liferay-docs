---
header-id: overriding-osgi-services
---

# Overriding OSGi Services

[TOC levels=1-4]

Components register as services with the OSGi service registry. A service 
component's availability, ranking, and attributes determine whether components 
referring to the service type bind to that particular service. @product@'s OSGI 
container is a dynamic environment in which services come and go and can be 
overridden, which means that if there's a service whose behavior you want to 
change, you can override it. Here are the steps for overriding a service:

1.  [Get the service and service reference details](/docs/7-2/customization/-/knowledge_base/c/examining-an-osgi-service-to-override)

2.  [Create a custom service](/docs/7-2/customization/-/knowledge_base/c/creating-a-custom-osgi-service)

3.  [Configure components to use your custom service](/docs/7-2/customization/-/knowledge_base/c/reconfiguring-components-to-use-your-service)

| **Note:** The 
| [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) 
| services in 
| [portal-impl](@platform-ref@/7.2-latest/javadocs/portal-impl/) 
| are Spring beans that Liferay makes available as OSGi services.

Start with examining the service you want to override. 
