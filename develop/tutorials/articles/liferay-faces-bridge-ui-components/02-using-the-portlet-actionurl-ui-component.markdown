# Using the portlet:actionURL UI Component [](id=using-the-portletactionurl-ui-component)

Liferay Faces Bridge provides several Portlet 2.0 `UIComponent` tags as part of
its component suite. In this tutorial, you'll learn about the
`portlet:actionURL` tag. 

+$$$

 **Note:** Although JSP tags are provided by the portlet container
 implementation, Liferay Faces Bridge provides these tags in order to support
 their usage within Facelets. 

$$$

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

This knowledge adds just another component tag for your JSF portlet development.

**Related Topics**

[Liferay Faces Alloy UI Components](/develop/tutorials/-/knowledge_base/6-2/tutorials/liferay-faces-alloy-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)
