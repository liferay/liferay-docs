# Migrating BridgeRequestAttributeListener [](id=migrate-to-bridgerequestattributelistener-liferay-portal-6-2-dev-guide-en)

PortletFaces Bridge provided a class named
`org.portletfaces.bridge.servlet.BridgeRequestAttributeListener` but Liferay
Faces Bridge uses
`com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener`. In order to
migrate to the new class, you will need to refactor to the new package
namespace, as a deprecated class has not been provided. 

<!--
Mention this is from `WEB-INF/web.xml`.
Modify code to possibly leave out the <web-app> tag.
Clarify that we're replacing the PortletFaces one with Liferay Faces Bridge one.
- Jim
-->

    <!-- PortletFaces Bridge BridgeRequestAttributeListener -->
    <web-app>
        <listener>
            ...
            <listener-class>
                org.portletfaces.bridge.servlet.BridgeRequestAttributeListener
            </listener-class>
            ...
        </listener>
    </web-app>

    <!-- Liferay Faces Bridge GenericFacesPortlet -->
    <web-app>
        <listener>
            ...
            <listener-class>
                com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener
            </listener-class>
            ...
        </listener>
    </web-app>

Next, we'll look at the migration of configuration option names. 
