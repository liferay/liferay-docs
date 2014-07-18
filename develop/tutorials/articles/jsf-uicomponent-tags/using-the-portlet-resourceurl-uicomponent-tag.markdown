# Using the portlet:resourceURL UIComponent Tag

Finally, you'll look at the `portlet:resourceURL` tag. 

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

Great! You've been introduced to some of Liferay Faces Bridge's `UIComponent`
tags, and have can now implement them into your JSF portlet! 
