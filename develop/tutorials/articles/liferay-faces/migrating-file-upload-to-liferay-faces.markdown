# Migrating File Upload to Liferay Faces

In this tutorial, you'll learn how to migrate file uploading classes from the
PortletFaces project to the Liferay Faces project. For further context as to why
this is needed, visit the [Migrating BridgeRequestAttributeListener to Liferay
Faces](/develop/tutorials/-/knowledge_base/migrating-bridgerequestattributelistener-to-liferay-faces)
tutorial.

<!-- Make sure above link is correct when Liferay Faces tutorials are uploaded
to dev.liferay.com. -Cody -->

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
