# Using the portlet:param UI Component [](id=using-the-portletparam-ui-component)

The Liferay Faces Bridge component suite provides many Portlet 2.0 `UIComponent`
tags that are useful for JSF development. In this tutorial, you'll learn about
the `portlet:param` tag. 

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

Now you know a little bit about the `portlet:param` tag and have viewed a
code snippet for context. 

**Related Topics**

[Liferay Faces Alloy UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-alloy-ui-components)
