# Creating an MVC Portlet [](id=creating-an-mvc-portlet)

You're convinced that Liferay's MVC Framework is right for you, and you want to learn how to configure it.

You'll need:

-  A module that publishes a portlet Component with the necessary properties.
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
controller into [MVC Action Command classes](/develop/tutorials/-/knowledge_base/7-1/mvc-action-command), [MVC Render Command classes](/develop/tutorials/-/knowledge_base/7-1/mvc-render-command), and [MVC Resource Command classes](/develop/tutorials/-/knowledge_base/7-1/mvc-resource-command). 

In this tutorial you'll learn to implement a Liferay MVC portlet with all the
controller code in the `-Portlet` class. Here are the general steps: 

1. [Configuring a Web module](#configuring-a-web-module)
2. [Specifying OSGi metadata](#specifying-osgi-metadata)
3. [Creating a portlet Component](#creating-a-portlet-component)
4. [Writing Controller code](#writing-controller-code)
5. [Configuring the view layer](#configuring-the-view-layer)

Start with configuring a Web module for your portlet. 

## Step 1: Configuring a Web Module [](id=configuring-a-web-module)

[Creating a Web module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module)
is straightforward. As a naming convention, the module with your controller
code and view layer is referred to as the Web module. A very basic Web module
might look like this:

-   docs.liferaymvc.web/
    -   src/main/java/
        -   com/liferay/docs/liferaymvc/web/portlet/LiferayMVCPortlet.java
    -   src/main/resources/
        -   content/
            -   Language.properties
        -   META/-INF/resources/
            -   init.jsp
            -   view.jsp
    -   build.gradle
    -   bnd.bnd

Of course you're not tied to using Gradle or bnd to build your project. However,
you must build your module as a JAR and define your module with proper OSGi
headers--bnd helps with that. [Maven](/develop/tutorials/-/knowledge_base/7-1/maven)
is another great option and there are plenty of [Maven and Gradle sample projects](/develop/tutorials/-/knowledge_base/7-1/liferay-sample-modules)
to reference. 

## Step 2: Specifying OSGi Metadata [](id=specifying-osgi-metadata)

OSGi metadata describes your module to the OSGi runtime environment. At a
minimum, you should specify the bundle symbolic name and the bundle version. A
human readable bundle name is also recommended.

    Bundle-Name: Example Liferay MVC Web
    Bundle-SymbolicName: com.liferay.docs.liferaymvc.web
    Bundle-Version: 1.0.0

If you don't specify a `Bundle-SymbolicName`, one will be generated from the
project's directory path, which is suitable for many cases. It's a nice
convention to specify the root package name as your bundle symbolic name.

## Step 3: Creating a Portlet Component [](id=creating-a-portlet-component)

The OSGi Declarative Services component model makes it easy to publish service
implementations to the OSGi runtime. For example, publishing your portlet class
as a `javax.portlet.Portlet` requires an `@Component` annotation like this one:

    @Component(
        immediate = true,
        service = Portlet.class
    )
    public class LiferayMVCPortlet extends MVCPortlet {
    }

The `immediate = true` attribute tells the runtime to publish the portlet as
soon as its dependencies resolve. Attribute `service = Portlet.class` specifies
that the portlet provides the `javax.portlet.Portlet` service. 

Since Liferay's
[`MVCPortlet` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
is itself an extension of `javax.portlet.Portlet`,
you've provided the right implementation. That's good in itself, but the
Component needs to be fleshed out with some properties:

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
MVC portlets for Liferay Portal 6.2. That's because they correspond with the XML
attributes you used to specify in `liferay-portlet.xml`, `liferay-display.xml`,
and `portlet.xml`. The [mapping of portlet descriptors to OSGi properties](/develop/reference/-/knowledge_base/7-1/portlet-descriptor-to-osgi-service-property-map)
can help find OSGi properties for descriptors you're familiar with. 

To keep compatibility with the JSR-168 and JSR-286 portlet specs, these DTDs
define the Liferay-specific portlet attributes:

- [liferay-portlet-app_7_1_0.dtd](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html). 
- [liferay-display_7_0_0.dtd](@platform-ref@/7.1-latest/definitions/liferay-display_7_0_0.dtd.html)

For example, consider the `<instanceable>` element from
`liferay-portlet-app_7_1_0.dtd`. To specify that property in your Component, use
this syntax in your `@Component` property list:

    "com.liferay.portlet.instanceable=true",

The properties namespaced with `javax.portlet....` are elements of the
[portlet.xml descriptor](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd).

Also note that you can use the `com.liferay.portlet.display-category` property
to create nested categories. Use `//` to separate the category root and all
categories and sub-categories that comprise your portlet's category path. Here's
an example:

    com.liferay.portlet.display-category=root//category.category1//category.category2

You can publish this portlet Component, but it doesn't do anything yet. You'll
implement the controller next.

## Step 4: Writing Controller Code [](id=writing-controller-code)

In MVC, your controller receives requests from the front end, and it receives
data from the back end. It's responsible for sending that data to the right
front end view for displaying to the user, and it's responsible for taking data
the user entered in the front end and passing it to the right back end service.
For this reason, the controller needs a way to process requests from the front
end and respond to them appropriately, and it needs a way to determine the
appropriate front end view to pass data back to the user. 

Liferay's MVC portlet framework offers two ways to handle data coming from the
user to the back end. One of these is designed for smaller applications, and the
other is designed for larger applications. First, you'll learn about processing
requests in smaller applications. After that, you'll learn how data is rendered
from the back end to the user. The [MVC Action Command](/develop/tutorials/-/knowledge_base/7-1/mvc-action-command), 
[MVC Render Command](/develop/tutorials/-/knowledge_base/7-1/mvc-render-command), 
and [MVC Resource Command](/develop/tutorials/-/knowledge_base/7-1/mvc-resource-command) 
tutorials demonstrate processing requests for larger applications. But read
them after you finish this one, so you can understand how the whole framework
works. 

Handling data from the user involves these things:

- [Action methods](#action-methods)
- [Render logic](#render-logic)
- [Setting and retrieving request parameters and attributes](#setting-and-retrieving-request-parameters-and-attributes) 

Start with creating action methods. 

### Action Methods [](id=action-methods)

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

In this action method's `javax.portlet.ActionRequest` object has two pieces of
information to use in calling the `addGuestbook` service, which is the point of
the method. If the guestbook is added successfully, the message
`"guestbookAdded"` is associated with the request and added to the
[`SessionMessages`
object](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/SessionMessages.html).
If an exception is thrown, it's caught, and the class name is associated with
the request and added to the [`SessionErrors`
object](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/SessionErrors.html)
and the response is set to render `edit_guestbook.jsp`. Setting the `mvcPath`
render parameter is a Liferay `MVCPortlet` framework convention that denotes the
next view to render to the user. 

While action methods respond to user actions, render logic determines the view
to display to the user. Render logic is next. 

### Render Logic [](id=render-logic)

So what might a render method look like? First, implementing render logic might
not be necessary at all. Note the `init-param` properties you set in your
Component:

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=/view.jsp",

The `template-path` property tells the MVC framework where your JSP files live.
In the above example, `/` means that the JSP files are located in your project's
root `resources` folder. That's why it's important to follow Liferay's standard
folder structure, outlined above. The `view-template` property directs the
default rendering to `view.jsp`.

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

In some cases, the uses of initialization parameters and render parameters
obviates the need for additional render logic. However, it's often necessary to
provide additional render logic. To do this, override the portlet's `render`
method. Here's an example:

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
attribute `guestbookId` with the ID of a guestbook to display. If there are
any guestbooks, it sets the current guestbook to display. Otherwise, it creates
a guestbook and sets it to display. Lastly the method calls `super.render`,
passing it the render request.

+$$$

**Note:** Are you wondering how to call Service Builder services in @product-ver@?
[The tutorial on finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-1/finding-and-invoking-liferay-services)
shows you how. In short, obtain a reference to the service by annotating one of
your fields of that service type with the `@Reference` Declarative Services
annotation.

    @Reference
    private GuestbookService _guestbookService;

Once done, you can call the service's methods.

    _guestbookService.addGuestbook(serviceContext.getUserId(), "Main",
            serviceContext);

$$$

### Setting and Retrieving Request and Response Parameters and Attributes [](id=setting-and-retrieving-request-parameters-and-attributes)

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

The controller's action method `doSomething` could get the `guestbookId`
parameter's value.

    long guestbookId = ParamUtil.getLong(actionRequest, "guestbookId");

Controller code can also set render parameters on response objects. 

    actionResponse.setRenderParameter("mvcPath", "/error.jsp");

Similarly, your controller class can also set attributes into response objects
using the `setAttribute` method. JSPs can then access those attributes from the
response object's `getAttribute` method. 

Passing information back and forth from your view and controller is important,
but there's more to the view layer than that.

## Step 5: Configuring the View Layer [](id=configuring-the-view-layer)

You now know how to extend Liferay's `MVCPortlet` to write controller code and
register a Component in the OSGi runtime. You also need a view layer, of course,
and for that, you'll use JSPs. This section briefly covers how to get your view
layer working, from organizing your imports in one JSP file, to configuring URLs
that direct processing to methods in your portlet class. 

+$$$

Note: As you create JSPs, you can [apply Clay styles to your app to match Liferay's apps](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app).

$$$

It's a good practice to put all your Java imports, tag library declarations, and
variable initializations into a JSP called `init.jsp`. If you use [Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)
to create your Web module, these taglib declarations and initializations are
automatically added to your `init.jsp`:

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

Your JSPs and controller can interact with each other via request objects. For
example, your controller sets this request attribute:

    renderRequest.setAttribute("guestbookId", guestbookId);

In your JSP, you can write Java code in scriptlets to interact with the request
object. This scriptlet invokes `getAttribute` on the request object to get the
value of the request attribute `guestbookId`: 

    <%
        long guestbookId = Long.valueOf((Long) renderRequest
                .getAttribute("guestbookId"));
    %>

JSPs can use render URLs and action URLs to invoke controller methods.  To call
a controller's `render` method, create a render URL and set it for a UI
component to invoke. The [`<portlet:renderURL>` tag](@platform-ref@/7.0-latest/taglibs/util-taglib/portlet/renderURL.html)
lets you construct the URL and assign it to a variable to pass to a UI
component. 

    <portlet:renderURL var="searchURL">
            <portlet:param name="mvcPath" value="/admin/view.jsp" />
    </portlet:renderURL>

The entire render URL is assigned to a variable specified by the `var`
attribute. The render URL above is assigned to a variable called `searchURL`.
The [`<portlet:param>` tag](@platform-ref@/7.0-latest/taglibs/util-taglib/portlet/param.html)
assigns JSP path `/admin/view.jsp` to the render
parameter `mvcPath`. The controller's render method gets the JSP path from the
`mvcPath` parameter to render the following JSP: 

    docs.liferaymvc.web/src/main/resources/META-INF/resources/admin/view.jsp

To invoke the render URL, assign its variable (the one assign to the `var`
attribute of the `portlet:renderURL`) to an action for a UI component, such as a
button or navigation bar item.

Invoking controller action methods from your JSP works similarly to invoking the
controller's render method. In your JSP, use a [`<portlet:actionURL>` tag](@platform-ref@/7.0-latest/taglibs/util-taglib/portlet/actionURL.html)
to create an action URL and then assign that URL as an action for a UI
component. Here's an action URL for calling controller method `doSomething`. 

    <portlet:actionURL name="doSomething" var="doSomethingURL">
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:actionURL>

Its portlet parameter named `redirect` is assigned to a JSP path for the portlet
to redirect to after invoking the portlet action. This action URL is assigned to
a variable named `doSomethingURL`. As with a `renderURL` variable, you can
assign an `actionURL` variable to a UI component's action. 

This simple examples demonstrate how the Liferay MVC framework facilitates
communication between a smaller application's controller and view layer. 

## Beyond the Basics [](id=beyond-the-basics)

This tutorial should get you up and running with a Liferay MVC Web module, but
there's more to know about creating an app in Liferay. To support more actions, complex render logic, and or serving resources, continue
reading the MVC command tutorials that follow. 

Regardless of your application's size or complexity, there are more conveniences
and features to leverage in your portlets. Here are a few useful jumping off
points:

-  [Making URLs Friendlier](/develop/tutorials/-/knowledge_base/7-1/making-urls-friendlier)
-  [Applying Clay Styles to your App](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app)
-  [Localizing your Application](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application)
-  [Liferay's Workflow Framework](/develop/tutorials/-/knowledge_base/7-1/liferays-workflow-framework)
-  [Model Listeners](/develop/tutorials/-/knowledge_base/7-1/model-listeners)
-  [Application Security](/develop/tutorials/-/knowledge_base/7-1/application-security)
-  [Asset Framework](/develop/tutorials/-/knowledge_base/7-1/asset-framework)
-  [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)

Enjoy creating your own portlets!

## Related Topics

[Developing a Web Application](/develop/tutorials/-/knowledge_base/7-1/developing-a-web-application)

[Starting Module Development](/develop/tutorials/-/knowledge_base/7-1/starting-module-development)

[OSGi Basics for Liferay Development](/develop/tutorials/-/knowledge_base/7-1/osgi-basics-for-liferay-development)

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
