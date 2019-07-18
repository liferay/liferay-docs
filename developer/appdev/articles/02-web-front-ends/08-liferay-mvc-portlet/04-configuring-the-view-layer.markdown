---
header-id: configuring-the-view-layer
---

# Configuring the View Layer

[TOC levels=1-4]

This section briefly covers how to get your view layer working, from organizing
your imports in one JSP file, to creating URLs that direct processing to
methods in your portlet class. 

| Note: As you create JSPs, you can
| [apply Clay styles to your app to match Liferay's apps](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-styles-to-your-app).

## Using the init.jsp 

Liferay's  practice puts all Java imports, tag library declarations, and
variable initializations into a JSP called `init.jsp`. If you use 
[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli) or 
[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)
to create a module based on the `mvc-portlet` project template, these taglib
declarations and initializations are added automatically to your `init.jsp`:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />
```

Here are the tag libraries it gives you:

-   [`c`](https://docs.liferay.com/ce/portal/7.2-latest/taglibs/util-taglib/c/tld-frame.html): JSTL core tags. 
-   [`portlet`](https://docs.liferay.com/ce/portal/7.2-latest/taglibs/util-taglib/portlet/tld-frame.html): Standard portlet component tags. 
-   [`aui`](https://docs.liferay.com/ce/portal/7.2-latest/taglibs/util-taglib/aui/tld-summary.html): [AlloyUI](https://alloyui.com/) component tags.
-   [`liferay-portlet`](https://docs.liferay.com/ce/portal/7.2-latest/taglibs/util-taglib/liferay-portlet/tld-frame.html): Liferay portlet component tags. 
-   [`liferay-theme`](https://docs.liferay.com/ce/portal/7.2-latest/taglibs/util-taglib/liferay-theme/tld-frame.html): Liferay theme component tags. 
-   [`liferay-ui`](https://docs.liferay.com/ce/portal/7.2-latest/taglibs/util-taglib/liferay-ui/tld-frame.html): Liferay UI component tags. 

These tags make portlet and Liferay objects available:

-   `<portlet:defineObjects />`: Implicit Java variables that reference 
    Portlet API objects. The objects available are limited to those available in
    the current portlet request. For details, see the `defineObjects` tag in
    [JSR-286](https://jcp.org/en/jsr/detail?id=286). 

-   `<liferay-theme:defineObjects />`: Implicit Java variables that 
    reference Liferay objects. 

To use all that the `init.jsp` has, include it in your other JSPs:

```javascript
<%@include file="/html/init.jsp"%>
```

A JSP uses render URLs to display other pages and action URLs to invoke
controller methods. 

## Using Render URLs

A render URL attached to a UI component action displays another page. For
example, this render URL displays the JSP `/path/to/foo.jsp`. 

```javascript
<portlet:renderURL var="adminURL">
    <portlet:param name="mvcPath" value="/path/to/foo.jsp" />
</portlet:renderURL>
```

Here's how to use a render URL:

1.  Add a
    [`<portlet:renderURL>`](@platform-ref@/7.2-latest/taglibs/util-taglib/portlet/renderURL.html)
    to your JSP. 

2.  Name the render URL via a `var` attribute in the `<portlet:renderURL>` tag.
    The `<portlet:renderURL>` tag constructs the URL and assigns it to the
    variable. For example, this render URL is assigned to the variable named
    `adminURL`: 

    ```javascript
    <portlet:renderURL var="adminURL">
       ...
    </portlet:renderURL>
    ```

3.  As sub-element to the `<portlet:renderURL>` tag, add a 
    [`<portlet:param>`](@platform-ref@/7.2-latest/taglibs/util-taglib/portlet/param.html)
    tag with the following attributes:

    `name="mvcPath"`: Your controller's `render` method forwards processing to
    the JSP at the path specified in the `value`.

    `value="/path/to/foo.jsp"`: The path to the JSP to render. Replace the value
    `/path/to/foo.jsp` with your JSP path. 

```javascript
<portlet:renderURL var="adminURL">
    <portlet:param name="mvcPath" value="/path/to/foo.jsp" />
</portlet:renderURL>
```

4.  To invoke the render URL, assign its variable (`var`) to a UI component 
    action, such as a button or navigation bar item action. 

Invoking the UI component causes the controller's render method to display the
`mvcPath` parameter's JSP. 

## Using Action URLs

Action methods are different because they invoke an action (i.e., code), rather
than link to another page. For example, this action URL invokes a
controller method called `doSomething` and passes a parameter called `redirect`.
The `redirect` parameter contains the path of the JSP to render after invoking
the action: 

```javascript
<portlet:actionURL name="doSomething" var="doSomethingURL">
    <portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>
```

Here's how to use an action URL:

1.  Add a
    [`<portlet:actionURL>`](@platform-ref@/7.2-latest/taglibs/util-taglib/portlet/actionURL.html)
    to your JSP. 

2.  Add a `name` and `var` attribute to the `<portlet:actionURL>`. The
    `<portlet:actionURL>` tag constructs the URL and assigns it to the `var`
    variable.

    `name`: Controller action to invoke. 

    `var`: Variable to assign the action URL to. 

```jsp
<portlet:actionURL name="doSomething" var="doSomethingURL">
   ...
</portlet:actionURL>
```

3.  As sub-element to the `<portlet:actionURL>` tag, add a 
    [`<portlet:param>`](@platform-ref@/7.2-latest/taglibs/util-taglib/portlet/param.html)
    tag that has the following attributes:

    `name="redirect"`: Tells the portlet to redirect to the JSP associated with 
    this parameter. 

    `value="/path/to/foo.jsp"`: Redirects the user to this JSP path after
    invoking the action. Replace the value `/path/to/bar.jsp` with your JSP
    path. 

```jsp
<portlet:actionURL name="doSomething" var="doSomethingURL">
    <portlet:param name="redirect" value="/path/to/bar.jsp" />
</portlet:actionURL>
```

4.  To invoke the action URL, assign its variable (`var`) to a UI component 
    action, such as a button or navigation bar item action. 

Congratulations! Your portlet is ready for action. 

These simple examples demonstrate how Liferay MVC Portlet facilitates
communication between a smaller application's view layer and controller. 

## Related Topics 

[Writing MVC Portlet Controller Code](/docs/7-2/appdev/-/knowledge_base/a/writing-mvc-portlet-controller-code)

[MVC Action Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-action-command)

[MVC Render Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-render-command)

[MVC Resource Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-resource-command)

[Front-end Taglibs](/docs/7-2/reference/-/knowledge_base/r/front-end-taglibs)

[Liferay JavaScript APIs](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/liferay-javascript-apis)
