# Migrating Configuration Option Names [](id=liferay-faces-configuration-option-names-liferay-portal-6-2-dev-guide-en)

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

Next, we'll explain how file upload classes have changed between the PortletFaces
Bridge and the Liferay Faces Bridge. 
