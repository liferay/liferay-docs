# Migrating From PortletFaces to Liferay Faces [](id=migrating-from-portletfaces-to-liferay-faces)

The Liferay Faces project originated from the <http://portletfaces.org>
community website. On April 3, 2012 Liferay announced that it would be assuming
leadership for the portletfaces.org community. Consequently, projects at
portletfaces.org were repackaged under the Liferay Faces umbrella project and
underwent the following name changes: 

- AlloyFaces &rarr; Liferay Faces Alloy 
- PorltetFaces Bridge &rarr; Liferay Faces Bridge 
- LiferayFaces &rarr; Liferay Faces Portal 

## Migrating BridgeRequestAttributeListener [](id=migrating-bridgerequestattributelistener)

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

That `listener-class` declaration is replaced in the `WEB-INF/web.xml` by the
Liferay Faces Bridge version of `BridgeRequestAttributeListener`: 

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

## Migrating Configuration Option Names [](id=migrating-configuration-option-names)

PortletFaces Bridge provided several configuration options for use within the
`WEB-INF/web.xml` and `WEB-INF/portlet.xml` descriptors. In order to ease
migration, the configuration option names have been reproduced in the Liferay
Faces project. It is recommended that the new configuration option names be
used, as shown in the following listing: 

- `org.portletfaces.bridge.containerAbleToSetHttpStatusCode` &rarr;
  `com.liferay.faces.bridge.containerAbleToSetHttpStatusCode` 

- `org.portletfaces.bridgeRequestScopePreserved` &rarr;
  `com.liferay.faces.bridge.bridgeRequestScopePreserved`

- `org.portletfaces.bridge.optimizePortletNamespace` &rarr;
  `com.liferay.faces.bridge.optimizePortletNamespace`

- `org.portletfaces.bridge.preferPreDestroy` &rarr;
  `com.liferay.faces.bridge.preferPreDestroy`

- `org.portletfaces.bridge.resolveXMLEntities` &rarr;
  `com.liferay.faces.bridge.resolveXMLEntities`

- `org.portletfaces.bridge.resourceBufferSize` &rarr;
  `com.liferay.faces.bridge.resourceBufferSize`

By making these changes, you're well on your way to migrating configuration
option names to the Liferay Faces project. 

## Migrating File Upload [](id=migrating-file-upload)

PortletFaces Bridge provided classes named
`org.portletfaces.bridge.component.HtmlInputFile` and
`org.portletfaces.bridge.component.UploadedFile`, but Liferay Faces Bridge uses
`com.liferay.faces.bridge.component.HtmlInputFile` and
`com.liferay.faces.bridge.component.UploadedFile`, respectively. In order to
migrate to the new classes, you need to refactor to the new package namespace,
as deprecated classes have not been provided. 

For example, the following import statements must be converted to the new
Liferay Faces namespace: 

- `import org.portletfaces.bridge.component.HtmlInputFile;` &rarr; `import
com.liferay.faces.bridge.component.HtmlInputFile;`

- `import org.portletfaces.bridge.component.UploadedFile;` &rarr; `import
com.liferay.faces.bridge.component.UploadedFile;`
 
Once you've refactored to the new package namespace, you've completed the
migrating process for file uploading to Liferay Faces. 

## Migrating Facelet Tag Library Namespaces [](id=migrating-facelet-tag-library-namespaces)

The projects at portletfaces.org provided several UIComponents and Composite
Components for use within Facelet views. The tag library documentation for these
components has been migrated to VDL documentation for each version of the
Liferay Faces Bridge: 

- The VDL documentation for the Liferay Faces 2.1 can be found at
  <http://docs.liferay.com/faces/2.1/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.0-legacy can be found at
  <http://docs.liferay.com/faces/3.0-legacy/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.0 can be found at
  <http://docs.liferay.com/faces/3.0/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.1 can be found at
  <http://docs.liferay.com/faces/3.1/vdldoc/>. 
- The VDL documentation for Liferay Faces 3.2 can be found at
  <http://docs.liferay.com/faces/3.2/vdldoc/>.

<!-- Re-add upon release. - Jim
- The VDL documentation for the Liferay Faces 4.1 can be found at <http://docs.liferay.com/faces/4.1/vdldoc/>.
- The VDL documentation for the Liferay Faces 4.2 can be found at <http://docs.liferay.com/faces/4.2/vdldoc/>.
-->

Each link for the VDL documentation contains information about all the `aui`,
`aui-cc`, `bridge`, `liferay-ui`, `liferay-util`, and `liferay-security` tags
for that version of the Liferay Faces Bridge, so each VDL documentation link
basically holds the entirety of the PortletFaces tag library documentation (with
the exception of certain tags which are excluded from certain versions of the
Lfieray Faces Bridge). 

By using the VDL documentation, your migration of the facelet tag library
namespaces from PortletFaces to Liferay Faces is a snap! 

## Migrating GenericFacesPortlet [](id=migrating-genericfacesportlet)

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

## Migrating LiferayFacesContext [](id=migrating-liferayfacescontext)

PortletFaces provided a class named
`org.portletfaces.liferay.faces.context.LiferayFacesContext` class, but Liferay
Faces Portal uses the `com.liferay.faces.portal.context.LiferayFacesContext`
class. In order to ease migration, the old class still exists in Liferay Faces
Portal although it has been deprecated. It is recommended that the standard
class name be used instead. 

For example, the following import statement must be converted to the new
Liferay Faces namespace: 

`import org.portletfaces.liferay.faces.context.LiferayFacesContext;` &rarr;
`import com.liferay.faces.portal.context.LiferayFacesContext;`
 
Once you've refactored to the new package namespace, you've completed the
migrating process for `LiferayFacesContext`. 

## Migrating Logging [](id=migrating-logging)

The PortletFaces-Logging project at portletletfaces.org has been moved into the
Liferay Faces Bridge codebase. In order to keep using this logging API in your
portlets, you'll need to refactor to the new package namespace, as deprecated
classes have not been provided. 

PortletFaces provided its own `org.portletfaces.logging.Logger` and
`org.portletfaces.logging.LoggerFactory` classes, but the Liferay Faces Bridge
uses the `com.liferay.faces.util.logging.Logger` and
`com.liferay.faces.util.logging.LoggerFactory`. 

For example, the following import statements must be converted to the new
Liferay Faces namespace: 

- `import org.portletfaces.logging.LoggerFactory;` &rarr; `import
com.liferay.faces.util.logging.LoggerFactory;` 

- `import org.portletfaces.logging.Logger;` &rarr; `import
com.liferay.faces.util.logging.Logger;` 

Great! You've officially migrated logging to Liferay Faces. 

## Migrating Portlet Preferences [](id=migrating-portlet-preferences)

PortletFaces Bridge provided its own
`org.portletfaces.bridge.preference.Preference` class but Liferay Faces Bridge
uses the JSR 329 standard `javax.portlet.faces.preference.Preference` class. In
order to migrate to the standard class, you'll need to refactor to the new
package namespace as deprecated classes have not been provided. 

For example, the following import statement must be converted to the new
Liferay Faces namespace: 

`import org.portletfaces.bridge.preference.Preference;` &rarr; `import
javax.portlet.faces.preference.Preference;`

With the new converted namespace, you've migrated portlet preferences to Liferay
Faces. That wraps up your migration from PortletFaces to Liferay Faces.
Congratulations! 

## Related Topics [](id=related-topics)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Specifying the portlet.xml file for Your JSF Portlet](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/specifying-the-portlet-xml-file-for-your-jsf-portlet)
