# Migrating GenericFacesPortlet to Liferay Faces

In this tutorial, you'll learn how to migrate the `GenericFacesPortlet` class
from PortletFaces to Liferay Faces. For further context as to why this is
needed, visit the [Migrating BridgeRequestAttributeListener to Liferay
Faces](/develop/tutorials/-/knowledge_base/migrating-bridgerequestattributelistener-to-liferay-faces)
tutorial. 

<!-- Make sure above link is correct when Liferay Faces tutorials are uploaded
to dev.liferay.com. -Cody -->

PortletFaces Bridge provided its own
`org.portletfaces.bridge.GenericFacesPortlet` class, but the Liferay Faces
Bridge uses the JSR 329 standard
`javax.portlet.faces.bridge.GenericFacesPortlet` class. In order to ease
migration, the old class still exists in Liferay Faces Bridge although it has
been deprecated. It is recommended that the standard class name be used in all
the `WEB-INF/portlet.xml` file's `portlet-class` entries. 

As an example, the code snippet below conveys how the `GenericFacesPortlet`
`portlet-class` was specified in PortletFaces Bridge: 

    <portlet-app>
        <portlet>
            ...
            <portlet-class>
                org.portletfaces.bridge.GenericFacesPortlet
            </portlet-class>
            ...
        </portlet>
    </portlet-app>

That `portlet-class` declaration is replaced in the `WEB-INF/web.xml` file by
the Liferay Faces Bridge version of `GenericFacesPortlet`: 

    <portlet-app>
        <portlet>
            ...
            <portlet-class>
                javax.portlet.faces.GenericFacesPortlet
            </portlet-class>
            ...
        </portlet>
    </portlet-app>

After completing the above steps, you've officially migrated your
`GenericFacesPortlet` class to Liferay Faces! 

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->
