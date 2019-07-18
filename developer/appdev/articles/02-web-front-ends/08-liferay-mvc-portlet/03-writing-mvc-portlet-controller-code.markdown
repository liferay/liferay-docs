---
header-id: writing-mvc-portlet-controller-code
---

# Writing MVC Portlet Controller Code

[TOC levels=1-4]

In MVC, your controller is a traffic director: it provides data to the right
front-end view for display to the user, and it takes data the user entered in
the front-end and passes it to the right back-end service. For this reason, the
controller must process requests from the front-end, and it must determine the
right front-end view to pass data back to the user. 

If you have a small application that's not heavy on controller logic, you can
put all your controller code in the `-Portlet` class. If you have more complex
needs (lots of actions, complex render logic to implement, or maybe even some
resource serving code), consider breaking the controller into [MVC Render
Command classes](/docs/7-2/appdev/-/knowledge_base/a/mvc-render-command), [MVC
Action Command classes](/docs/7-2/appdev/-/knowledge_base/a/mvc-action-command),
and
[MVC Resource Command classes](/docs/7-2/appdev/-/knowledge_base/a/mvc-resource-command). 
Here you'll implement controller logic for small applications, where all the
controller code is in the `-Portlet` class. It involves these things: 

- Action methods
- Render logic
- Setting and retrieving request parameters and attributes

Start with creating action methods. 

## Action Methods

Your portlet class can act as your controller by itself and process requests
using action methods. Here's a sample action method: 

```java
public void addGuestbook(ActionRequest request, ActionResponse response)
        throws PortalException, SystemException {

    ServiceContext serviceContext = ServiceContextFactory.getInstance(
        Guestbook.class.getName(), request);

    String name = ParamUtil.getString(request, "name");

    try {
        _guestbookService.addGuestbook(serviceContext.getUserId(),
                name, serviceContext);

        SessionMessages.add(request, "guestbookAdded");

    } catch (Exception e) {
        SessionErrors.add(request, e.getClass().getName());

        response.setRenderParameter("mvcPath",
            "/html/guestbook/edit_guestbook.jsp");
    }

}
```

This action method has one job: call a service to add a guestbook. If the call
succeeds, the message `"guestbookAdded"` is associated with the request and
added to the 
[`SessionMessages` object](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/SessionMessages.html).
If an exception is thrown, it's caught, and the class name is associated with
the request and added to the 
[`SessionErrors` object](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/SessionErrors.html),
and the response is set to render `edit_guestbook.jsp`. Setting the `mvcPath`
render parameter is a Liferay `MVCPortlet` framework convention that denotes the
next view to render to the user. 

While action methods respond to user actions, render logic determines the view
to display to the user. Render logic is next. 

## Render Logic

Here's how MVC Portlet determines which view to render. Note the `init-param`
properties you set in your component:

```java
"javax.portlet.init-param.template-path=/",
"javax.portlet.init-param.view-template=/view.jsp",
```

The `template-path` property tells the MVC framework where your JSP files live.
In the above example, `/` means that the JSP files are in your project's root
`resources` folder. That's why it's important to follow Liferay's standard
folder structure. The `view-template` property directs the default rendering to
`view.jsp`.

Here's the path of a hypothetical Web module's resource folder:

```
docs.liferaymvc.web/src/main/resources/META-INF/resources
```

Based on that resource folder, the `view.jsp` file is found at

```
docs.liferaymvc.web/src/main/resources/META-INF/resources/view.jsp
```

and that's the application's default view. When the portlet's `init` method
(e.g., your portlet's override of `MVCPortlet.init()`) is called, Liferay reads the
initialization parameters you specify and directs rendering to the default JSP.
Throughout the controller, you can render different views (JSP files) by setting
the render parameter `mvcPath` like this:

```java
actionResponse.setRenderParameter("mvcPath", "/error.jsp");
```

You can avoid render logic by using initialization parameters and render
parameters, but most of the time you'll override the portlet's `render` method.
Here's an example:

```java
@Override
public void render(RenderRequest renderRequest,
        RenderResponse renderResponse) throws PortletException, IOException {

    try {
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Guestbook.class.getName(), renderRequest);

        long groupId = serviceContext.getScopeGroupId();

        long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

        List<Guestbook> guestbooks = _guestbookService
                .getGuestbooks(groupId);

        if (guestbooks.size() == 0) {
            Guestbook guestbook = _guestbookService.addGuestbook(
                    serviceContext.getUserId(), "Main", serviceContext);

            guestbookId = guestbook.getGuestbookId();

        }

        if (!(guestbookId > 0)) {
            guestbookId = guestbooks.get(0).getGuestbookId();
        }

        renderRequest.setAttribute("guestbookId", guestbookId);

    } catch (Exception e) {

        throw new PortletException(e);
    }

    super.render(renderRequest, renderResponse);

}
```

This render logic provides the view layer with data to display to the user. The
`render` method above sets the render request attribute `guestbookId` with the
ID of a guestbook to display. If guestbooks exist, it chooses the first.
Otherwise, it creates a guestbook and sets it to display. Lastly the method
passes the render request and render response objects to the base class via its
`render` method. 

| **Note:** Are you wondering how to call
| [Service Builder services](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
| in @product-ver@? In short, obtain a reference to the service by annotating
| one of  your fields of that service type with the `@Reference`
| [Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
| annotation.
| 
| `
| @Reference
| private GuestbookService _guestbookService;`
| 
| Once done, you can call the service's methods.
| 
| `_guestbookService.addGuestbook(serviceContext.getUserId(), "Main",
|         serviceContext);`

Before venturing into the view layer, the next section demonstrates ways to pass
information between the controller and view layers. 

## Setting and Retrieving Request and Response Parameters and Attributes

A handy utility class called
[`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)
facilitates retrieving parameters from an [`ActionRequest`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/ActionRequest.html)
or a
[`RenderRequest`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/RenderRequest.html).

For example, this JSP passes a parameter named `guestbookId` in an action
URL.

```jsp
<portlet:actionURL name="doSomething" var="doSomethingURL">
    <portlet:param name="guestbookId" 
            value="<%= String.valueOf(entry.getGuestbookId()) %>" />
</portlet:actionURL>
```

The `<portlet:actionURL>` tag's `name` attribute maps the action URL to a
controller action method named `doSomething`. Triggering an action URL invokes
the corresponding method in the controller. 

The controller's `doSomething` method referenced in this example gets the
`guestbookId` parameter value from the `ActionRequest`.

```java
long guestbookId = ParamUtil.getLong(actionRequest, "guestbookId");
```

To pass information back to the view layer, the controller code can set render
parameters on response objects. 

```java
actionResponse.setRenderParameter("mvcPath", "/error.jsp");
```

The code above sets a parameter called `mvcPath` to JSP path `/error.jsp`.
This causes the controller's render method to redirect the user to that JSP. 

Your controller class can also set attributes into response objects using the
`setAttribute` method. 

```java
renderResponse.setAttribute("guestbookId", guestbookId);
```

JSPs can use Java code in scriptlets to interact with the request object. 

```javascript
<%
    long guestbookId = Long.valueOf((Long) renderRequest
            .getAttribute("guestbookId"));
%>
```

Passing information back and forth from your view and controller is important,
but there's more to the view layer than that. The view layer is up next. 

## Related Topics 

[Creating an MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/creating-an-mvc-portlet)

[Configuring the View Layer](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer)

[MVC Action Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-action-command)

[MVC Render Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-render-command)

[MVC Resource Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-resource-command)
