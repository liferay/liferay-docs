# Using PreDestroy and BridgePreDestroy Annotations with Liferay Faces Bridge [](id=using-predestroy-and-bridgepredestroy-annotations-with-liferay-faces-bridge)

For this tutorial, you'll learn about the`PreDestroy` and `BridgePreDestroy`
annotations and how to use them. 

When JSF developers want to perform cleanup on managed-beans before theyre
destroyed, they typically annotate a method inside the bean using the
`@PreDestroy` annotation. Section 6.8.1 of the JSR 329 standard, however,
discusses the need for the `@BridgePreDestroy` and
`@BridgeRequestScopeAttributeAdded` annotations in the bridge API. 

+$$$

**Note:** For an in-depth discussion of this issue, please refer to
<http://issues.liferay.com/browse/FACES-146>. 

$$$

In order to understand this requirement, it's necessary to differentiate between
*local* portals and *remote* portals. Local portals invoke portlets that are
deployed in the same (local) portlet container. Remote portals invoke portlets
that are deployed elsewhere via WSRP (Web Services for Remote Portlets). The
`@BridgePreDestroy` and `@BridgeRequestScopeAttributeAdded` annotations were
introduced into the JSR 329 standard primarily to support WSRP in remote
portals. That being the case, the standard indicates that developers should
always use `@BridgePreDestroy` instead of `@PreDestroy`. Liferay Faces Bridge,
however, takes a different approach: rather than assuming the remote portal
use-case, Liferay Faces Bridge assumes the local portal use-case. When
developing with a local portal like Liferay Portal, Liferay Faces Bridge ensures
that the standard `@PreDestroy` annotation works as expected. This means there
is no reason to use the `@BridgeRequestScope` annotation with a local portal
when using Liferay Faces Bridge. To leverage the `@BridgePreDestroy` and
`@BridgeRequestScopeAttributeAdded` annotations for WSRP, you must manually
configure Liferay Faces Bridge via the portlet project's `WEB-INF/web.xml`
descriptor. 

In the below declaration taken from a `WEB-INF/web.xml`, the default value of
the `context-param` is false, meaning that Liferay Faces Bridge invokes methods
annotated with `@PreDestroy` over those annoated with `@BridgePreDestroy`: 

    <context-param>
        <param-name>com.liferay.faces.bridge.preferPreDestroy</param-name>
        <param-value>false</param-value>
    </context-param>

Setting the value of the `context-param` instructs Liferay Faces Bridge to
prefer the `@BridgePreDestroy` annotation over the standard `@PreDestroy`
annotation, in order to support a WSRP remote portal environment. 

The following listener from a `WEB-INF/web.xml` demonstrates supporting the
`@BridgeRequestScopeAttributeAdded` annotation in a WSRP remote portal
environment: 

    <listener>
        <listener-class>com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener</listener-class>
    </listener>

The `com.liferay.faces.bridge.preferPreDestroy` value can, alternatively, be
specified on a portlet-by-portlet basis in the portlet project's
`WEB-INF/portlet.xml` descriptor. 

Be learning these requirements, you're able to perform cleanup on managed-beans
before they're destroyed. You can do all of this while still abiding by section
6.8.1 of the JSR 329 standard. 

**Related Topics**

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Understanding Liferay Faces Portal](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-portal)
