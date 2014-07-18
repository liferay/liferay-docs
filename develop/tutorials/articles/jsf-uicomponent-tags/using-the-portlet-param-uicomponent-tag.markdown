# Using the portlet:param UIComponent Tag

The next tag you'll explore is the `portlet:param` tag. 

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
