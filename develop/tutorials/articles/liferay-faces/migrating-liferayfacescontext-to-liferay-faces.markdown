# Migrating LiferayFacesContext to Liferay Faces

In this tutorial, you'll learn how to migrate the `LiferayFacesContext` class
from PortletFaces to Liferay Faces. For further context as to why this is
needed, visit the [Migrating BridgeRequestAttributeListener to Liferay
Faces](/develop/tutorials/-/knowledge_base/6-2/migrating-bridgerequestattributelistener-to-liferay-faces)
tutorial. 

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

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->
