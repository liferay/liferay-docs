# Accessing the Portlet API with ExternalContext for JSF Portlets [](id=accessing-the-portlet-api-with-externalcontext)

Just as JSF *web app* developers rely on `ExternalContext` to access the Servlet
API, JSF *portlet* developers rely on it to access the Portlet API. 

As you develop JSF portlets, you'll often need to access instances of the
`javax.portlet.PortletRequest` and `javax.portlet.PortletResponse` classes. You
access these instances similarly to how you'd access the
`javax.servlet.http.HttpServletRequest` and
`javax.servlet.http.HttpServletResponse` classes in a servlet environment,
except that you cast them to the portlet versions of the classes. 

In the example code snippet below, the request object from
`externalContext.getRequest()` is cast to the `PortletRequest` class and the
response object from `externalContext.getResponse()` is cast to the
`PortletResponse` class: 

    import javax.portlet.PortletRequest;
    import javax.portlet.PortletResponse;
    ...

    public class PortletBackingBean {

        public void submit() {
            FacesContext facesContext =
                FacesContext.getCurrentInstance();

            ExternalContext externalContext =
                facesContext.getExternalContext();

            PortletRequest portletRequest =
                (PortletRequest) externalContext.getRequest();

            PortletResponse portletResponse =
                (PortletResponse) externalContext.getResponse();
        }
    }
 
The code listing above uses the singleton class `LiferayFacesContext`, which has
methods `getPortletRequest()` and `getPortletResponse()`. You can leverage the
`LiferayFacesContext` class in your JSF portlets on Liferay to get easy access
to the portlet requests and responses. <!-- This class comes with Liferay Faces
Portal, which can be covered in the [Liferay Faces Portal](www.liferay.com)
tutorial. 

Replace above link once Liferay Faces Portal tutorials are available. -Cody
-->

<!-- I think more explanation here is necessary. There's no indication in the
code that LiferayFacesContext is the implementation that's returned, and there's
no explanation as to why you didn't use the getPortletRequest() and
getPortletResponse() methods. I can assume from reading it that you didn't use
them because you were going for a more generic, portlet container-friendly
implementation in this first example, but I (and the reader) am not sure of
that. -Rich -->

In this tutorial, you've explored requesting objects from the portlet API using
`ExternalContext`. 

