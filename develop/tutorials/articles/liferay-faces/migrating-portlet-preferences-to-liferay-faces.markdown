# Migrating Portlet Preferences [](id=migrate-liferay-faces-portlet-preferences-liferay-portal-6-2-dev-guide-en)

PortletFaces Bridge provided its own
`org.portletfaces.bridge.preference.Preference` class but Liferay Faces Bridge
uses the JSR 329 standard `javax.portlet.faces.preference.Preference` class. In
order to migrate to the standard class, you will need to refactor to the new
package namespace as deprecated classes have not been provided. 

    // PortletFaces Bridge package name:
    import org.portletfaces.bridge.preference.Preference;

    // Liferay Faces Bridge package name:
    import javax.portlet.faces.preference.Preference;

And those are all the changes necessary to migrate projects from the
PortletFaces Bridge to the Liferay Faces Bridge. 
