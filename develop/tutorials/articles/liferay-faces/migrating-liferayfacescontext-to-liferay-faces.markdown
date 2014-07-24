# Migrating LiferayFacesContext [](id=migrate-liferayfacescontext-liferay-portal-6-2-dev-guide-en)

PortletFaces provided a class named
`org.portletfaces.liferay.faces.context.LiferayFacesContext` class but Liferay
Faces Portal uses the `com.liferay.faces.portal.context.LiferayFacesContext`
class. In order to ease migration, the old class still exists in Liferay Faces
Portal although it has been deprecated. It is recommended that the standard
class name be used instead. 

    // LiferayFaces package name:
    import org.portletfaces.liferay.faces.context.LiferayFacesContext;

    // Liferay Faces Portal package name:
    import com.liferay.faces.portal.context.LiferayFacesContext;
 
The next section explains some of the changes in Logging between PortletFaces
and Liferay Faces. 
