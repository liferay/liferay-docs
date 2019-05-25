---
header-id: using-the-portletrenderurl-ui-component
---

# Using the portlet:renderURL UI Component

[TOC levels=1-4]

The Liferay Faces Bridge component suite provides many Portlet 2.0 `UIComponent`
tags that are useful for JSF development. In this tutorial, you'll learn about
the `portlet:renderURL` tag. 

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

Fantastic! You can add another UIComponent to your repertoire! 

## Related Topics

[Liferay Faces Alloy UI Components](/docs/6-2/tutorials/-/knowledge_base/t/liferay-faces-alloy-ui-components)

[Understanding Liferay Faces Bridge](/docs/6-2/tutorials/-/knowledge_base/t/understanding-liferay-faces-bridge)
