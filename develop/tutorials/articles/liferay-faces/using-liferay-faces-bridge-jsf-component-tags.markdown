# Using Liferay Faces Bridge JSF Component Tags

Although the JSR 329 standard does not define any JSF components that bridge
implementations are required to provide, Liferay Faces Bridge comes with a
handful of components that are helpful to use in JSF portlets. 

Because Liferay Faces has several [active versions](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en)
(targeting different versions of JSF, Liferay Portal, etc.), there are several
versions of the project View Declaration Language (VDL) documentation for these
tags. The VDL documentation can be found at the following addresses: 

- The VDL documentation for Liferay Faces 2.1 can be found at
  <http://docs.liferay.com/faces/2.1/vdldoc/>. 
- The VDL documentation for Liferay Faces 3.0-legacy can be found at
  <http://docs.liferay.com/faces/3.0-legacy/vdldoc/>.
- The VDL documentation for Liferay Faces 3.0 can be found at
  <http://docs.liferay.com/faces/3.0/vdldoc/>.
- The VDL documentation for Liferay Faces 3.1 can be found at
  <http://docs.liferay.com/faces/3.1/vdldoc/>.
- The VDL documentation for Liferay Faces 3.2 can be found at
  <http://docs.liferay.com/faces/3.2/vdldoc/>.

<!-- Re-add VDLs for 4.1 and 4.2 when released. - Jim
- The VDL documentation for Liferay Faces 4.1 can be found at <http://docs.liferay.com/faces/4.1/vdldoc/>.
- The VDL documentation for Liferay Faces 4.2 can be found at <http://docs.liferay.com/faces/4.2/vdldoc/>.
-->

Liferay Faces Bridge provides the following UI component tags under the `bridge`
and `portlet` namespaces for the Bridge and Portlet 2.0 tags respectively. Let's
look at the Bridge tags first. 

#### Bridge UIComponent Tags [](id=liferay-faces-bridge-uicomponent-tags-liferay-portal-6-2-dev-guide-en)

Liferay Faces Bridge provides the following bridge-specific `UIComponent` tags as
part of its component suite. 

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

---

 ![Note](../../images/tip.png) **Note:** Usage of this tag requires the Apache
 `commons-fileupload` and `commons-io` dependencies. See the [Demo JSF2
 Portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
 for more details.  

---

Next, let's learn about the Portlet `UIComponent` tags available in Liferay Faces
Bridge. 

#### Portlet 2.0 UIComponent Tags [](id=faces-bridge-portlet-2-0-uicomponent-tags-liferay-portal-6-2-dev-guide-en)

Liferay Faces Bridge provides the following Portlet 2.0 `UIComponent` tags as
part of its component suite. 

---

 ![Note](../../images/tip.png) **Note:** Although JSP tags are provided by the
 portlet container  implementation, Liferay Faces Bridge provides these tags in
 order to support their usage within Facelets. 

---

The first tag we'll look at is `portlet:actionURL`.

If the `var` attribute is present, the `portlet:actionURL` tag introduces an EL
variable that contains a `javax.portlet.ActionURL`, adequate for postbacks.
Otherwise, the URL is written to the response. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:actionURL var="myActionURL" >
                    <portlet:param name="foo" value="1234" />
                </portlet:actionURL>
                <h:outputText var="actionURL=#{myActionURL}" />
            </h:form>
        </h:body>
    </f:view>

Next, we'll look at an example of the `portlet:namespace` tag.

If the `var` attribute is present, the `portlet:namespace` tag introduces
an EL variable that contains the portlet namespace. Otherwise, the
namespace is written to the response.

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:namespace var="mynamespace" />
                <h:outputText var="namespace=#{mynamespace}" />
            </h:form>
        </h:body>
    </f:view>

The `portlet:param` tag is up next.

The `portlet:param` tag lets you add a request parameter name=value pair when
nested inside `portlet:actionURL`, `portlet:renderURL`, or `portlet:resourceURL`
tags. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:actionURL>
                    <portlet:param name="foo" value="1234" />
                </portlet:actionURL>
            </h:form>
        </h:body>
    </f:view>

The next tag we'll look at is the `portlet:renderURL` tag.

If the `var` attribute is present, the `portlet:renderURL` tag introduces an EL
variable that contains a `javax.portlet.PortletURL`, adequate for rendering.
Otherwise, the URL is written to the response. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:renderURL var="myRenderURL">
                    <portlet:param name="foo" value="1234" />
                </portlet:renderURL>
                <h:outputText var="actionURL=#{myRenderURL}" />
            </h:form>
        </h:body>
    </f:view>

Finally, we'll look at the `portlet:resourceURL` tag.

If the `var` attribute is present, the `portlet:resourceURL` tag introduces an
EL variable that contains a `javax.portlet.ActionURL`, adequate for obtaining
resources. Otherwise, the URL is written to the response. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:resourceURL var="myResourceURL">
                    <portlet:param name="foo" value="1234" />
                </portlet:resourceURL>
                <h:outputText var="actionURL=#{myResourceURL}" />
            </h:form>
        </h:body>
    </f:view>

Now that we've introduced you to some of Liferay Faces Bridge's `UIComponent`
tags, let's explore how to dynamically add JSF portlets to portal pages. 
