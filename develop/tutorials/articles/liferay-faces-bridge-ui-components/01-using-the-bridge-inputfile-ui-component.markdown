# Using the bridge:inputFile UI Component [](id=using-the-bridgeinputfile-ui-component)

Liferay Faces Bridge provides bridge-specific `UIComponent` tags as part of its
component suite. In this tutorial, you'll explore the `bridge:inputFile` tag and
learn what it can do for your JSF portlet. 

The `bridge:inputFile` tag renders an HTML `<input type="file"/>` tag, providing
file upload capability. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:bridge="http://liferay.com/faces/bridge"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body>
            <h:form>
                <bridge:inputFile fileUploadListener="#{backingBean.handleFileUpload}" multiple="multiple" />
                <h:commandButton value="Submit" />
            </h:form>
        </h:body>
    </f:view>

Here's a code snippet from a class that imports the `FileUploadEvent` class and
implements handling the file upload: 

    import com.liferay.faces.bridge.event.FileUploadEvent;

    ...

    @ManagedBean(name = "backingBean")
    @ViewScoped
    public class ApplicantBackingBean implements Serializable {

            public void handleFileUpload(FileUploadEvent fileUploadEvent)
            throws Exception {
                UploadedFile uploadedFile = fileUploadEvent.getUploadedFile();
                System.err.println("Uploaded file:" + uploadedFile.getName());
            }
        }
    }

+$$$

 **Note:**The `bridge:inputFile` tag depends on Apache's `commons-fileupload`
 and `commons-io` modules. See the [Demo JSF2 Portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
 for more details.

$$$

Fantastic! You can add another UIComponent to your repertoire! 

**Related Topics**

[Liferay Faces Alloy UI Components](/develop/tutorials/-/knowledge_base/6-2/tutorials/liferay-faces-alloy-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)
