# Using PreDestroy and BridgePreDestroy Annotations [](id=predestroy-bridgepredestroy-annotations-liferay-portal-6-2-dev-guide-en)

When JSF developers want to perform cleanup on managed-beans before they are
destroyed, they typically annotate a method inside the bean with the
`@PreDestroy` annotation. However, section 6.8.1 of the JSR 329 standard
discusses the need for the `@BridgePreDestroy` and
`@BridgeRequestScopeAttributeAdded`  annotations in the bridge API. 

---

 ![Note](../../images/tip.png) **Note:** For an in-depth discussion of this
 issue, please refer to <http://issues.liferay.com/browse/FACES-146>. 

---

In order to explain this requirement, it is necessary to make a distinction
between *local* portals and *remote* portals. Local portals invoke portlets that
are deployed in the same (local) portlet container. Remote portals invoke
portlets that are deployed elsewhere via WSRP (Web Services for Remote
Portlets). The `@BridgePreDestroy` and `@BridgeRequestScopeAttributeAdded`
annotations were introduced into the JSR 329 standard primarily to support WSRP
in remote portals. That being the case, the standard indicates that developers
should always use `@BridgePreDestroy` instead of `@PreDestroy`. Liferay Faces
Bridge however takes a different approach: rather than assuming the remote
portal use-case, Liferay Faces Bridge assumes the local portal use-case. When
developing with a local portal like Liferay, Liferay Faces Bridge ensures that
the standard `@PreDestroy` annotation works as expected. This means there is no
reason to use the `@BridgeRequestScope` annotation with a local portal when
using Liferay Faces Bridge. Developers must manually configure Liferay Faces
Bridge via the `WEB-INF/web.xml` descriptor in order to leverage the
`@BridgePreDestroy` and `@BridgeRequestScopeAttributeAdded` annotations for
WSRP. 

    <!--
    The default value of the following context-param is false, meaning that
    Liferay Faces Bridge will invoke methods annotated with @PreDestroy over
    those annoated with @BridgePreDestroy. Setting the value of the following
    context-param instructs Liferay Faces Bridge to prefer the @BridgePreDestroy
    annotation over the standard @PreDestroy annotation in order to support a
    WSRP remote portal environment.
    -->
    <context-param>
        <param-name>com.liferay.faces.bridge.preferPreDestroy</param-name>
        <param-value>false</param-value>
    </context-param>

    <!--
    The following listener is required to support the
    @BridgeRequestScopeAttributeAdded annotation in a WSRP remote portal
    environment. 
    -->
    <listener>
        <listener-class>com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener</listener-class>
    </listener>

Alternatively, the `com.liferay.faces.bridge.preferPreDestroy` value can be
specified on a portlet-by-portlet basis in the `WEB-INF/portlet.xml` descriptor.
