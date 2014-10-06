# Migrating Portlet Preferences to Liferay Faces

In this tutorial, you'll learn how to migrate portlet preferences from
PortletFaces to Liferay Faces. For further context as to why this is needed,
visit the [Migrating BridgeRequestAttributeListener to Liferay
Faces](/develop/tutorials/-/knowledge_base/6-2/migrating-bridgerequestattributelistener-to-liferay-faces)
tutorial. 

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
Faces. 

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->
