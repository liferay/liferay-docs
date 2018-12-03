# Overriding OSGi Services [](id=overriding-service-references)

Liferay DXP consists of components that refer to and use other components,
inside and outside of Liferay. Components register as services with the OSGi
service registry. A service component's availability, ranking, and attributes
determine whether other components that refer to the service type, bind to that
particular service. OSGi services makes for a dynamic environment in which
services come and go and can be overridden. Here are the steps for overriding a
service:

1.  [Get the service and service reference details](/develop/tutorials/-/knowledge_base/7-1/examining-an-osgi-service-to-override)

2.  [Create a custom service](/develop/tutorials/-/knowledge_base/7-1/creating-a-custom-osgi-service)

3.  [Configure components to use your custom service](/develop/tutorials/-/knowledge_base/7-1/reconfiguring-components-to-use-your-service)

+$$$

**Note:** The
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)
services in
[portal-impl](@platform-ref@/7.1-latest/javadocs/portal-impl/)
are Spring beans that Liferay makes available as OSGi services. 

$$$

Start with examining the service you want to override. 
