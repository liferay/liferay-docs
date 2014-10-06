# Migrating Logging to Liferay Faces

In this tutorial, you'll learn how to migrate logging from PortletFaces to
Liferay Faces. For further context as to why this is needed, visit the
[Migrating BridgeRequestAttributeListener to Liferay
Faces](/develop/tutorials/-/knowledge_base/6-2/migrating-bridgerequestattributelistener-to-liferay-faces)
tutorial. 

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

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->
