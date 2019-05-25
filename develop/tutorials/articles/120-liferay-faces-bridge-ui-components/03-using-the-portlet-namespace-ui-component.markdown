---
header-id: using-the-portletnamespace-ui-component
---

# Using the portlet:namespace UI Component

[TOC levels=1-4]

Liferay Faces Bridge provides many useful Portlet 2.0 `UIComponent` tags as part
of its component suite. In this tutorial, you'll learn about the
`portlet:namespace` tag. 

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

Great! Another UIComponent tag to use for JSF development is in the books. 

## Related Topics

[Liferay Faces Alloy UI Components](/docs/6-2/tutorials/-/knowledge_base/t/liferay-faces-alloy-ui-components)

[Understanding Liferay Faces Bridge](/docs/6-2/tutorials/-/knowledge_base/t/understanding-liferay-faces-bridge)
