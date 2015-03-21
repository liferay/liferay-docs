# Extending Liferay Faces Bridge Using Factory Wrappers [](id=extending-liferay-faces-bridge-using-factory-wrappers)

In this tutorial, you'll explore how to extend the
[Liferay Faces Bridge](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-bridge)
using factory wrappers. Liferay Faces Bridge has several abstract classes that
serve as contracts for defining factories: 

- [`BridgeContextFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/context/BridgeContextFactory.java)
- [`BridgePhaseFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/BridgePhaseFactory.java)
- [`BridgeRequestScopeFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/scope/BridgeRequestScopeFactory.java)
- [`BridgeRequestScopeCacheFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/scope/BridgeRequestScopeCacheFactory.java)
- [`BridgeRequestScopeManagerFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/scope/BridgeRequestScopeManagerFactory.java)
- [`BridgeWriteBehindSupportFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/application/view/BridgeWriteBehindSupportFactory.java)
- [`BridgeURLFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/context/url/BridgeURLFactory.java)
- [`IncongruityContextFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/context/IncongruityContextFactory.java)
- [`PortletContainerFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/container/PortletContainerFactory.java)
- [`PortletContainerFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/container/PortletContainerFactory.java)
- [`UploadedFileFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/model/UploadedFileFactory.java)

These factories are defined using the standard JSF `<factory-extension>` element
in `faces-config.xml`. The *default implementations* of these factories are
defined in the bridge's [`META-INF/faces-config.xml`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/resources/META-INF/faces-config.xml)
file. 

The bridge features an *extension mechanism* that enables you to decorate any of
these factories using a `META-INF/faces-config.xml` descriptor (inside a `.jar`
file), or a `WEB-INF/faces-config.xml` descriptor (inside a portlet `.war`
file). This mechanism enables you to plug in your own factory implementations to
decorate (wrap) the default implementations, using a
[`FactoryExtension`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/util/src/main/java/com/liferay/faces/util/factory/FactoryExtension.java). 

## Wrapping the BridgeContextFactory with a Custom BridgeContext [](id=wrapping-the-bridgecontextfactory-with-a-custom-bridgecontext)

This tutorial for [Liferay Faces Bridge](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-bridge)
shows you how to wrap the
[`BridgeContextFactory`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/context/BridgeContextFactory.java)
class, so that it returns a custom
[`BridgeContext`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/bridge-impl/src/main/java/com/liferay/faces/bridge/context/BridgeContext.java)
instance by overriding one of the methods to provide custom functionality. 

1. Create a wrapper class for the `BridgeContext` that overrides the
   `getResponseNamespace()` method: 

        package com.mycompany.myproject;

        public class BridgeContextCustomImpl extends BridgeContextWrapper {

            private BridgeContext wrappedBridgeContext;

            public BridgeContextCustomImpl(BridgeContext bridgeContext) {

                this.wrappedBridgeContext = bridgeContext;
                BridgeContext.setCurrentInstance(this);
            }

            @Override
            public String getResponseNamespace() {
                // return value based on custom algorithm.
            }

            @Override
            public BridgeContext getWrapped() {
                return wrappedBridgeContext;
            }
        }

2. Create a wrapper class for the `BridgeContextFactory`: 

        package com.mycompany.myproject;

        public class BridgeContextFactoryCustomImpl extends BridgeContextFactory {

            private BridgeContextFactory wrappedBridgeContextFactory;

            public BridgeContextFactoryCustomImpl(
                BridgeContextFactory bridgeContextFactory) {

               this.wrappedBridgeContextFactory =  bridgeContextFactory;
            }

            public BridgeContextFactory getWrapped() {
                return wrappedBridgeContextFactory;
            }

            @Override
            public BridgeContext getBridgeContext(
                BridgeConfig bridgeConfig, BridgeRequestScope bridgeRequestScope,
                PortletConfig portletConfig, PortletContext portletContext,
                PortletRequest portletRequest, PortletResponse portletResponse,
                Bridge.PortletPhase portletPhase, PortletContainer portletContainer,
                IncongruityContext incongruityContext) {

                BridgeContext wrappedBridgeContext =
                wrappedBridgeContextFactory.getBridgeContext(
                    bridgeConfig, bridgeRequestScope, portletConfig, portletContext,
                    portletRequest, portletResponse, portletPhase, portletContainer,
                    incongruityContext);

                BridgeContext bridgeContext =
                    new BridgeContextCustomImpl(wrappedBridgeContext);

                return bridgeContext;
            }
        }

3. In the portlet's `WEB-INF/faces-config.xml`, specify the custom factory: 

        <faces-config xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:bridge="http://www.liferay.com/xml/ns/liferay-faces-bridge-2.0-extension"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
        http://java.sun.com/xml/ns/javaee/web-facesconfig_2_0.xsd">
            <factory>
                <factory-extension>
                    <bridge:bridge-context-factory>
                        com.mycompany.myproject.BridgeContextFactoryCustomImpl
                    </bridge:bridge-context-factory>
                </factory-extension>
            </factory>
        </faces-config>

4. Rebuild and re-deploy the portlet. 

That's all you need to do to implement and deploy a `BridgeContextFactory`
wrapper. 

## Related Topics [](id=related-topics)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Understanding Liferay Faces Portal](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-portal)
