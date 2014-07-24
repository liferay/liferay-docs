# Migrating GenericFacesPortlet [](id=migrate-genericfacesportlet-liferay-portal-6-2-dev-guide-en)

PortletFaces Bridge provided its own
`org.portletfaces.bridge.GenericFacesPortlet` class but the Liferay Faces Bridge
uses the JSR 329 standard `javax.portlet.faces.bridge.GenericFacesPortlet`
class. In order to ease migration, the old class still exists in Liferay Faces
Bridge although it has been deprecated. It is recommended that the standard
class name be used in all `WEB-INF/portlet.xml` `portlet-class` entries. 

    <!-- PortletFaces Bridge GenericFacesPortlet -->
    <portlet-app>
        <portlet>
            ...
            <portlet-class>
                org.portletfaces.bridge.GenericFacesPortlet
            </portlet-class>
            ...
        </portlet>
    </portlet-app>

    <!-- Liferay Faces Bridge GenericFacesPortlet -->
    <portlet-app>
        <portlet>
            ...
            <portlet-class>
                javax.portlet.faces.GenericFacesPortlet
            </portlet-class>
            ...
        </portlet>
    </portlet-app>

Next we'll look at the new version of the `LiferayFacesContext`.
