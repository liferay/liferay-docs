# Migrating BridgeRequestAttributeListener to Liferay Faces

The Liferay Faces project originated from the <http://portletfaces.org>
community website. On April 3, 2012 Liferay announced that it would be assuming
leadership for the portletfaces.org community. Consequently, projects at
portletfaces.org were repackaged under the Liferay Faces umbrella project and
underwent the following name changes: 

- AlloyFaces &rarr; Liferay Faces Alloy 
- PorltetFaces Bridge &rarr; Liferay Faces Bridge 
- LiferayFaces &rarr; Liferay Faces Portal 

In this tutorial, you'll cover the replacement for the
`BridgeRequestAttributeListener`. 

PortletFaces Bridge provided a class named
`org.portletfaces.bridge.servlet.BridgeRequestAttributeListener` but Liferay
Faces Bridge uses
`com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener`. In order to
migrate to the new class, you'll need to refactor to the new package namespace,
as a deprecated class has not been provided. 

As an example, the code snippet below conveys how the
`BridgeRequestAttributeListener` was specified in PortletFaces Bridge:

    <web-app>
        <listener>
            ...
            <listener-class>
                org.portletfaces.bridge.servlet.BridgeRequestAttributeListener
            </listener-class>
            ...
        </listener>
    </web-app>

That `listener-class` declaration is replaced by the Liferay Faces Bridge
version, which is located in the `WEB-INF/web.xml` file:

    <web-app>
        <listener>
            ...
            <listener-class>
                com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener
            </listener-class>
            ...
        </listener>
    </web-app>

As you can see, migrating from PortletFaces Bridge to Liferay Faces Bridge is a
snap! 
