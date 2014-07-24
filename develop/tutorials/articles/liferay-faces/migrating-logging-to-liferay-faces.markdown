# Migrating Logging [](id=migrate-to-liferay-faces-logging-liferay-portal-6-2-dev-guide-en)

The PortletFaces-Logging project at portletletfaces.org has been moved into the
Liferay Faces Bridge codebase. In order to keep using this logging API in your
portlets, you will need to refactor to the new package namespace, as deprecated
classes have not been provided. 

    // PortletFaces-Logging package names:
    import org.portletfaces.logging.LoggerFactory;
    import org.portletfaces.logging.Logger;

    // Liferay Faces Bridge package names:
    import com.liferay.faces.util.logging.LoggerFactory;
    import com.liferay.faces.util.logging.Logger;

The last migration we'll look at is Portlet Preferences.
