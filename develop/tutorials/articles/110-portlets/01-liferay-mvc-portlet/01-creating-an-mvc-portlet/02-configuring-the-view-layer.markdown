# Configuring the View Layer [](id=configuring-the-view-layer)

This section briefly covers how to get your view layer working, from organizing
your imports in one JSP file, to creating URLs that direct processing to
methods in your portlet class. 

+$$$

Note: As you create JSPs, you can 
[apply Clay styles to your app to match Liferay's apps](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app).

$$$

Liferay's best practice puts all Java imports, tag library declarations, and
variable initializations into a JSP called `init.jsp`. If you use 
[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli) or 
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)
to create a module based on the `mvc-portlet` project template, these taglib
declarations and initializations are added automatically to your `init.jsp`:

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <liferay-theme:defineObjects />

    <portlet:defineObjects />

Make sure to include the `init.jsp` in your other JSPs:

    <%@include file="/html/init.jsp"%>

JSPs can use action URLs to invoke controller methods. To create a link to
another page, use a render URL with the `mvcPath` parameter. The
[`<portlet:renderURL>` tag](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/renderURL.html) 
constructs the URL and assigns it to a variable. 

    <portlet:renderURL var="searchURL">
            <portlet:param name="mvcPath" value="/admin/view.jsp" />
    </portlet:renderURL>

The render URL is assigned to the `searchURL` variable specified by the `var`
attribute. The 
[`<portlet:param>` tag](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/param.html)
above assigns JSP path `/admin/view.jsp` to the render parameter `mvcPath`. The
controller's `render` method gets the JSP path from the `mvcPath` parameter to
render the following JSP: 

    docs.liferaymvc.web/src/main/resources/META-INF/resources/admin/view.jsp

To invoke the render URL, assign its variable (the one set to the `var`
attribute of the `<portlet:renderURL>`) to an action for a UI component, such as
a button or navigation bar item.

Action methods are different because they invoke an action (i.e., code), rather
than just linking to another page. In your JSP, use a 
[`<portlet:actionURL>` tag](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/actionURL.html) 
to create an action URL and then assign that URL as an action for a UI
component. Here's an action URL that calls a controller method named
`doSomething`. 

    <portlet:actionURL name="doSomething" var="doSomethingURL">
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:actionURL>

The portlet parameter named `redirect` is assigned to a JSP path for the portlet
to redirect to after invoking the portlet action. This action URL is assigned to
a variable named `doSomethingURL`. As with a render URL, you can assign an
action URL to a UI component action by the action URL's variable (the one set to
`var`). 

These simple examples demonstrate how the Liferay MVC framework facilitates
communication between a smaller application's view layer and controller. 
