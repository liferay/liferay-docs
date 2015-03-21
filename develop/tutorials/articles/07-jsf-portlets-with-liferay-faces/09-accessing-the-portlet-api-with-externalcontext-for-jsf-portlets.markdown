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

Notice that you're able to retrieve the `ExternalContext` from the current
`FacesContext` instance. 

In this tutorial, you've explored requesting objects from the portlet API using
`ExternalContext`. 

**Related Topics**

[Communicating Between JSF Portlets Using IPC](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/communicating-between-jsf-portlets-using-ipc)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Portal](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)
