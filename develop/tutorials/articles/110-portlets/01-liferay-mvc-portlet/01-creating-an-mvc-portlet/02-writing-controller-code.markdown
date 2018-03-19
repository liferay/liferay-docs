# Writing Controller Code [](id=writing-controller-code)

In MVC, your controller receives requests from the front end, and it receives
data from the back end. It's responsible for sending that data to the right
front end view for displaying to the user, and it's responsible for taking data
the user entered in the front end and passing it to the right back end service.
For this reason, the controller needs a way to process requests from the front
end and respond to them appropriately, and it needs a way to determine the
appropriate front end view to pass data back to the user. 

If you have a small application that's not heavy on controller logic (maybe just
a couple of action methods), you can put all your controller code in the
`-Portlet` class. If you have more complex needs (lots of actions, complex
render logic to implement, or maybe even some resource serving code), consider
breaking the controller into [MVC Render Command classes](/develop/tutorials/-/knowledge_base/7-1/mvc-render-command),
[MVC Action Command classes](/develop/tutorials/-/knowledge_base/7-1/mvc-action-command),
and [MVC Resource Command classes](/develop/tutorials/-/knowledge_base/7-1/mvc-resource-command).
This tutorial demonstrates implementing controller logic for small applications.

In this tutorial you'll learn to implement a Liferay MVC portlet with all the
controller code in the `-Portlet` class. It involves these things:

- [Action methods](#action-methods)
- [Render logic](#render-logic)
- [Setting and retrieving request parameters and attributes](#setting-and-retrieving-request-parameters-and-attributes) 

Start with creating action methods. 

## Action Methods [](id=action-methods)

If you have a small application, you can implement all your controller logic in
your portlet class (the same one you annotated with `@Component`), which acts as
your controller by itself. Use action methods to process requests. Here's what
an action method might look like:

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

This action method's `javax.portlet.ActionRequest` object has two pieces of
information to use in calling the `addGuestbook` service, which is the point of
the method. If the guestbook is added successfully, the message
`"guestbookAdded"` is associated with the request and added to the
[`SessionMessages` object](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/SessionMessages.html).
If an exception is thrown, it's caught, and the class name is associated with
the request and added to the [`SessionErrors`
object](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/SessionErrors.html)
and the response is set to render `edit_guestbook.jsp`. Setting the `mvcPath`
render parameter is a Liferay `MVCPortlet` framework convention that denotes the
next view to render to the user. 

While action methods respond to user actions, render logic determines the view
to display to the user. Render logic is next. 

## Render Logic [](id=render-logic)

So what might a render method look like? First, implementing render logic might
not be necessary at all. Note the `init-param` properties you set in your
Component:

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=/view.jsp",

The `template-path` property tells the MVC framework where your JSP files live.
In the above example, `/` means that the JSP files are located in your project's
root `resources` folder. That's why it's important to follow Liferay's standard
folder structure, outlined in the previous tutorial. The `view-template`
property directs the default rendering to `view.jsp`.

Here's the path of a hypothetical Web module's resource folder:

    docs.liferaymvc.web/src/main/resources/META-INF/resources

Based on that resource folder, the `view.jsp` file is found at

    docs.liferaymvc.web/src/main/resources/META-INF/resources/view.jsp

and that's the default view of the application. When the portlet's `init` method
(e.g., your portlet's override of `MVCPortlet.init()`) is called, the
initialization parameters you specify are read and used to direct rendering to
the default JSP. Throughout the controller, you can render different views (JSP
files) by setting the render parameter `mvcPath`, like this:

    actionResponse.setRenderParameter("mvcPath", "/error.jsp");

In some cases, using initialization parameters and render parameters obviates
the need for additional render logic. However, it's often necessary to provide
additional render logic. To do this, override the portlet's `render` method.
Here's an example:

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

With render logic, you're providing the view layer with information to display
the data properly to the user. The `render` method above sets the render request
attribute `guestbookId` with the ID of a guestbook to display. If there are any
guestbooks, it sets the current guestbook to display. Otherwise, it creates a
guestbook and sets it to display. Lastly the method passes the render request
and render response objects to the base class via its `render` method. 

+$$$

**Note:** Are you wondering how to call Service Builder services in 
@product-ver@?
[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-1/finding-and-invoking-liferay-services)
is straightforward. In short, obtain a reference to the service by annotating
one of your fields of that service type with the `@Reference` Declarative
Services annotation.

    @Reference
    private GuestbookService _guestbookService;

Once done, you can call the service's methods.

    _guestbookService.addGuestbook(serviceContext.getUserId(), "Main",
            serviceContext);

$$$

Before venturing into the view layer, the next section demonstrates ways to pass
information between the controller and view layers.  

## Setting and Retrieving Request and Response Parameters and Attributes [](id=setting-and-retrieving-request-parameters-and-attributes)

In your portlet class's render method, action methods, and even in your JSPs,
you can use a handy utility class called
[`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)
to retrieve parameters from an `ActionRequest` or a `RenderRequest`.

For example, a JSP could pass a parameter named `guestbookId` in an action
URL.

    <portlet:actionURL name="doSomething" var="doSomethingURL">
        <portlet:param name="guestbookId" 
                value="<%= String.valueOf(entry.getGuestbookId()) %>" />
    </portlet:actionURL>

The `<portlet:actionURL>` tag's `name` attribute maps the action URL to a
controller action method named `doSomething`. Triggering an action URL (or
render URL or resource URL) invokes the corresponding method in the controller. 

The controller's `doSomething` method referenced in this example can then get
the `guestbookId` parameter value from the `ActionRequest`.

    long guestbookId = ParamUtil.getLong(actionRequest, "guestbookId");

To pass information back to the view layer, the controller code can set render
parameters on response objects. 

    actionResponse.setRenderParameter("mvcPath", "/error.jsp");

The code above sets the a parameter called `mvcPath` to JSP path `/error.jsp`.
This causes the controller's render method to redirect the user to the JSP
`/error.jsp`. 

Similarly to setting and getting parameters, your controller class can also set
attributes into response objects using the `setAttribute` method. 

    renderRequest.setAttribute("guestbookId", guestbookId);

JSPs can use Java code in scriptlets to interact with the request object. 

    <%
        long guestbookId = Long.valueOf((Long) renderRequest
                .getAttribute("guestbookId"));
    %>

Passing information back and forth from your view and controller is important,
but there's more to the view layer than that. The view layer is up next. 
