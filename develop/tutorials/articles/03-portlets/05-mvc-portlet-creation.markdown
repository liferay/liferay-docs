# Creating an MVC Portlet

You're convinced that Liferay's MVC Framework is right for you, and you want to learn how to configure it.

You'll need:

-  A module that publishes a portlet component with the necessary properties.
-  Controller code to handle the request and response.
-  JSPs to implement your view layer.

Along the way you'll want to know how to call services from your controller and
how to pass information from the view layer to the controller.

Keep in mind that you can take two paths with your Liferay MVC portlet
implementation. If you have a small application that won't be heavy on
controller logic (maybe just a couple of action methods), you can put all your
controller code in the `-Portlet` class. If you have more complex
needs (lots of actions, complex render logic to implement, or maybe even some
resource serving code), consider breaking the
controller up into [MVC Action Command classes](develop/tutorials/-/knowledge_base/7-0/mvc-action-command), [MVC Render Command classes](develop/tutorials/-/knowledge_base/7-0/mvc-render-command), and [MVC Resource Command classes](develop/tutorials/-/knowledge_base/7-0/mvc-resource-command). 

In this tutorial you'll learn to implement a Liferay MVC portlet with all of the
controller code in the `-Portlet` class.

## Configuring a WEB Module

As a naming convention, the module with your controller code and view layer is
referred to as the WEB module. A very basic WEB module might look
like this:

    docs-liferaymvc-web/
        src/main/java/
            com/liferay/docs/liferaymvc/web/portlet/LiferayMVCPortlet.java
        src/main/resources/
            content/
                Language.properties
            META/-INF/resources/
                init.jsp
                view.jsp
        build.gradle
        bnd.bnd

Of course you're not tied to the use of Gradle or BndTools to build your
project. However, you do need a JAR with the proper OSGi headers defined, which
is easily done if you provide a `bnd.bnd` file. To see Liferay MVC portlets
built with Maven, BndTools, and Gradle, you can check out the tutorial on
[Liferay Sample Modules](develop/tutorials/-/knowledge_base/7-0/liferay-sample-modules).

## Specifying OSGi Metadata

At a minimum, you should specify the bundle symbolic name and the bundle version
for the OSGi runtime. Providing a human readable bundle name is also
recommended.

    Bundle-Name: Example Liferay MVC Web
    Bundle-SymbolicName: com.liferay.mvcdocs.liferaymvc.web
    Bundle-Version: 1.0.0

It's not required, but it's a nice convention to use the root package name as
the bundle symbolic name.

## Creating a Portlet Component

Using the OSGi Declarative Services component model makes it easy to publish
service implementations to the OSGi runtime. In this case an implementation of
the `javax.portlet.Portlet` service must be published. Declare this using an
`@Component` annotation in the portlet class:

    @Component(
        immediate = true,
        service = Portlet.class
    )
    public class LiferayMVCPortlet extends MVCPortlet {
    }

That's good in itself, but the component needs to be fleshed out with some
properties:

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Liferay MVC Portlet",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/view.jsp",
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
    )
    public class LiferayMVCPortlet extends MVCPortlet {
    }

Some of those properties might look familiar to you if you've developed Liferay
MVC portlets before Liferay 7. That's because they correspond with the XML
attributes you used to specify in `liferay-portlet.xml`, `liferay-display.xml`,
and `portlet.xml`. To find a list of all the Liferay-specific
attributes you can specify as properties in your portlet components, check out
the
[liferay-portlet-app_7_0_0.dtd](https://docs.liferay.com/portal/7.0/definitions/liferay-portlet-app_7_0_0.dtd.html).

Consider the `<instanceable>` element from the above link as an example. To
specify that property in your component, use this syntax in your property list:

    "com.liferay.portlet.instanceable=true",

The properties namespaced with `javax.portlet....` are elements of the
[portlet.xml descriptor](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd).

Liferay's DTD files can be found
[here](https://docs.liferay.com/portal/7.0/definitions/).

You can publish this portlet component, but it doesn't do anything yet. Implement
the Controller code next.

## Writing Controller Code

As you're aware, your application will receive requests from the frontend, and
you'll need a way to process the request and respond to it.

### Action Methods

If you have a very simple application, you can implement all of your controller
code in the `-Portlet` class (the same one you annotated with `@Component`).
Here's what an action method might look like:

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

In this action method, the `ActionRequest` object is used to retrieve two pieces
of information that are needed to call the `addGuestbook` action, which is the
point of the method. If successful, the `SessionMessages` object is used to
store a success message. If an exception is thrown, it's caught, and the
appropriate `SessionErrors` object is used to store the exception message. Note
the call to the `setRenderParameter` method on the `ActionResponse`. This is
used to render the `edit_guestbook.jsp` if a guestbook could not be added, by
setting the `mvcPath` parameter.

### Render Logic

So what might a render method look like? First, note that implementing render
logic might not be necessary at all. Note the `init-param` properties you set in
your Component:

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=/view.jsp",

With these, you're directing the default rendering to your `view.jsp`. The
`template-path` property is used to tell the MVC framework where your JSP files
live. In the above example, `/` was used to mean that the JSP files are located
in the root `resources` directory of your project. That's why it's important to
follow Liferay's standard directory structure, outlined above. Here's the path
of a hypothetical Web module's resource directory:

    my-application-web/src/main/resources/META-INF/resources

In this case, the `view.jsp` file is found at

    my-application-web/src/main/resources/META-INF/resources/view.jsp

and that's the default view of the application. When the `init` method is
called, the initialization parameters you specify are read and used to direct
rendering to the default JSP. Throughout the controller, you can render a
different view (JSP file) by setting the render parameter `mvcPath`, like this:

    actionResponse.setRenderParameter("mvcPath", "/error.jsp");

In some cases, the uses of initialization parameters and render parameters
obviates the need for additional render logic. However, it's often necessary to
provide additional render logic. To do this, override the `render` method.
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

With render logic, you're providing the view layer with information to
display the data properly to the user. In this case, there's some information
needed at the outset, then some logic in the `if` statements that determine if
there are any guestbooks that can be displayed. If not, one should be created by
default. If there are guestbooks in the database, the ID of the first one in the
list retrieved via the `getGuestbooks` method should be displayed. This is
accomplished by passing the appropriate ID to the `RenderRequest` using the
`setAttirbute` method. Since this logic should be executed before the default
`render` method, the method concludes by calling `super.render`.

+$$$

**Note:** Are you wondering how to call Service Builder services in Liferay 7.0?
Refer to [the tutorial on Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)
for a more detailed explanation. In short, obtain a reference to the service by
annotating a setter method with the `@Reference` Declarative Services
annotation, and set the service object as a private variable.

    private GuestbookService _guestbookService;

    @Reference(unbind = "-")
    protected void setGuestbookService(GuestbookService guestbookService) {
        _guestbookService = guestbookService;
    }

Once done, you can call the service's methods.

    _guestbookService.addGuestbook(serviceContext.getUserId(), "Main",
            serviceContext);

$$$

### Setting and Retrieving Request Parameters and Attributes

In the render method and the action method of the portlet class, and even in
your JSPs, you can use a handy utility class called
[`ParamUtil`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)
to retrieve parameters from an `ActionRequest` or a `RenderRequest`.

    long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");

In the above example, the parameter was set into an action request in a JSP:

    <portlet:param name="guestbookId" 
            value="<%= String.valueOf(entry.getGuestbookId()) %>" />

You can also set attributes into the request using the method

    request.setAttribute();

in your portlet class. To read the attribute in a JSP, use the method

    request.getAttribute();

Since the `<portlet:defineObjects>`

To set parameters into the response in your controller code, you can use the
`setRenderParameter` method.

    actionResponse.setRenderParameter("mvcPath", "/error.jsp");

Passing information back and forth from your view and controller is important,
but there's more to the view layer than that.

## Configuring the View Layer

You now know how to extend Liferay's `MVCPortlet` to write controller code and
register a Component in the OSGi runtime. You also need a view layer, of course,
and for that, you'll use JSPs. Liferay's Lexicon Experience Language can be used
to guide your app's styling so it matches Liferay's. To learn about Lexicon, and
about some of Liferay's taglibs, refer to the tutorial [Applying Lexicon Styles to Your App](develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app).
This section will briefly cover how to get your view layer working, from
organizing your imports in one JSP file, to configuring URLs that direct
processing to your code in the portlet class. 

It's a good practice to put all your Java imports, tag library declarations, and
variable initializations into an `init.jsp` file. If you use Liferay IDE to
create your Web module, these taglib declarations and initializations will
automatically be added to your `init.jsp`:

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <liferay-theme:defineObjects />

    <portlet:defineObjects />

Make sure you include the `init.jsp` in your other JSP files:

    <%@include file="/html/init.jsp"%>

You can, if necessary, write Java code in your JSPs using scriptlets. Perhaps you set
an attribute into the request in your controller:

    renderRequest.setAttribute("guestbookId", guestbookId);

You can reference it in your JSP by calling the `renderRequest.getAttribute`
method:

    <%
        long guestbookId = Long.valueOf((Long) renderRequest
                .getAttribute("guestbookId"));
    %>

To construct a URL that calls the `render` method of your controller, you can
use the `liferay-portlet:renderURL` tag:

    <liferay-portlet:renderURL varImpl="searchURL">
        <portlet:param name="mvcPath" value="/admin/view.jsp" />
    </liferay-portlet:renderURL>

You create a variable to hold the generated URL with the `varImpl` attribute.
Then you can set any parameters you need using the `portlet:param` tag. The
`mvcPath` parameter is used to direct to another JSP. The example above points
to a JSP in

    my-application-web/src/main/resources/META-INF/resources/admin/view.jsp

You can then use the `varImpl` value to invoke the URL in your JSP code, perhaps
in a button or navigation bar item.

Action URLs can be similarly created with the `liferay-portlet` taglib:

    <liferay-portlet:actionURL name="doSomething" var="doSomethingURL">
        <portlet:param name="redirect" value="<%= redirect %>" />
    </liferay-portlet:actionURL>

The `name` of the action URL should match the action method name in your portlet
class; that's all Liferay's MVC framework needs in order to know that the action
method of the same name should run when this action URL is invoked. Use the
`var` attribute like you did the `varImpl` attribute in the render URL; to call
the action URL in your JSP code, whether it's in an icon, a button, or somewhere
else.

As you can see, with Liferay MVC it's pretty easy to make your controller talk
to your view layer.

## Beyond the Basics

This tutorial should get you up and running with a Liferay MVC Web module, but there's more
to know about creating an app in Liferay. Here are a few useful jumping off
points:

-  [Making URLs Friendlier](/develop/tutorials/-/knowledge_base/7-0/making-urls-friendlier)
-  [Applying Lexicon Styles to your App](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app)
-  [Localizing your Application](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application)
-  [Liferay's Workflow Framework](/develop/tutorials/-/knowledge_base/7-0/liferays-workflow-framework)
-  [Model Listeners](/develop/tutorials/-/knowledge_base/7-0/model-listeners)
-  [Application Security](/develop/tutorials/-/knowledge_base/7-0/application-security)
-  [Asset Framework](/develop/tutorials/-/knowledge_base/7-0/asset-framework)
-  [Business Logic and Data Access (Service Builder)](/develop/tutorials/-/knowledge_base/7-0/business-logic-and-data-access)

