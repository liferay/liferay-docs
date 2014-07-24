# Migrating File Upload [](id=migrate-liferay-faces-file-upload-liferay-portal-6-2-dev-guide-04-en)

PortletFaces Bridge provided classes named
`org.portletfaces.bridge.component.HtmlInputFile` and
`org.portletfaces.bridge.component.UploadedFile`, but Liferay Faces Bridge uses
`com.liferay.faces.bridge.component.HtmlInputFile` and
`com.liferay.faces.bridge.component.UploadedFile`, respectively. In order to
migrate to the new classes, you will need to refactor to the new package
namespace, as deprecated classes have not been provided. 

    // PortletFaces Bridge package name:
    import org.portletfaces.bridge.component.HtmlInputFile;

    // Liferay Faces Bridge package name:
    import com.liferay.faces.bridge.component.HtmlInputFile;

    // PortletFaces Bridge package name:
    import org.portletfaces.bridge.component.UploadedFile;

    // Liferay Faces Bridge package name:
    import com.liferay.faces.bridge.component.UploadedFile;
 
Next, we'll look at migrating Facelet Tags to Liferay Faces.
