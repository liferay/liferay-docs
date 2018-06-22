# Invoking Local Services [](id=invoking-local-services)

Once you deploy your services module, those services are available in the
container. Service Builder generates local and remote service classes as OSGi
Declarative Services (DS) components. These components are accessible to other
DS components, so you can invoke them from other components, such as your web
application. Here's how:

1. [Add a reference to the local service component.](#step-1-reference-the-local-service-component) 

2. [Call the component's methods.](#step-2-call-the-service-component-methods)

There's a Blade sample called 
[Basic Service Builder](/develop/reference/-/knowledge_base/7-1/service-builder-samples).
Its `basic-web` module has a `Portlet` service component that demonstrates
referencing a local service component. This module also has JSPs that invoke the
component's methods. Your first step is to add a reference to the local service
component object.

## Step 1: Reference the Local Service Component [](id=step-1-reference-the-local-service-component)

Your application's Service Builder-generated local services are DS components
that you can inject into your application's other DS components
[using the `@Reference` annotation](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services).
The `basic-web` module's `JSPPortlet` class is a `Portlet` service component
that references the `FooLocalService` local service as a DS component.

    @Reference
	private volatile FooLocalService _fooLocalService;

The OSGi service registry wires the service implementation object to your class
that references it. The `JSPPortlet` sample class declares the
`_fooLocalService` field to be volatile, but making a field volatile is
completely optional. 

+$$$

**Note**: Service Builder generates `*LocalServiceImpl`, `*ServiceImpl`, 
`*PersistenceImpl`, and `[ENTITY_NAME]Impl` classes for your entities as Service
Builder Spring Beans---not OSGi Declarative Services. 
[Service Builder Spring Beans must use means other than the `@Reference` annotation to reference Liferay services and OSGi services](/develop/reference/-/knowledge_base/7-1/invoking-services-from-service-builder-code).

$$$

**Important:** You should never invoke `*LocalServiceImpl` objects directly. You
should only invoke them indirectly through their `*LocalService` service
interface. The OSGi service registry wires the service implementation object to
your class. 

You can make a service object available to JSPs by associating it with a
`RenderRequest` attribute. For example, the `JSPPortlet`'s `render` method
associates the `FooLocalService` object with an attribute called
`fooLocalService`. 

    @Override
    public void render(RenderRequest request, RenderResponse response)
        throws IOException, PortletException {

        //set service bean
        request.setAttribute("fooLocalService", getFooLocalService());

        super.render(request, response);
    }
    
    public FooLocalService getFooLocalService() {
        return _fooLocalService;
    }

If your JSP declares the `<portlet:defineObjects />` tag, it can retrieve the
service object from the `RenderRequest` attribute. For example, the
`JSPPortlet`'s `init.jsp` file retrieves the `FooLocalService` object from the
`"fooLocalService"` attribute. 

    ...
    <%@
    page import="com.liferay.blade.samples.servicebuilder.service.FooLocalService" %>
    ...

    <liferay-theme:defineObjects />

    <portlet:defineObjects />

    <%
    ...

    //get service bean
    FooLocalService fooLocalService = (FooLocalService)request.getAttribute("fooLocalService");
    %>

All JSPs that include the above `init.jsp` can use the `fooLocalService`
variable to invoke the local service component's methods. 

## Step 2: Call the Component's Methods [](id=step-2-call-the-service-component-methods)

Now that you have the service component object, you can invoke its methods as
you would any Java object's methods. 

The `basic-web` sample module's `view.jsp` and `edit_foo.jsp` files include the
`init.jsp` shown in the previous section. Therefore, they can access the
`fooLocalService` variable which references the service component object. The
`view.jsp` file uses the component's `getFoosCount` method and `getFoos` method
in a Liferay Search Container that lists `Foo` instances. 

    <liferay-ui:search-container
    	total="<%= fooLocalService.getFoosCount() %>"
    >
    	<liferay-ui:search-container-results
    		results="<%= fooLocalService.getFoos(searchContainer.getStart(), searchContainer.getEnd()) %>"
    	/>
        ...
    </liferay-ui:search-container>

The `edit_foo.jsp` file calls `getFoo(long id)` to retrieve a `Foo` entity based
on the entity instance's ID.

    long fooId = ParamUtil.getLong(request, "fooId");
    Foo foo = null;
    if (fooId > 0) {
    	foo = fooLocalService.getFoo(fooId);
    }

Using the `@Reference` annotation, you can inject your application's OSGi DS
components (such as a portlet DS component) with instances of your application's
Service Builder-generated local service components. Also you can provide your
JSPs access to the component instances via `RenderRequest` attributes. 

## Related Topics [](id=related-topics)

[Creating Local Services](/develop/tutorials/-/knowledge_base/7-1/creating-local-services)

[Creating Remote Services](/develop/tutorials/-/knowledge_base/7-1/creating-remote-services)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-1/invoking-remote-services)

[Service Security Layers](/develop/tutorials/-/knowledge_base/7-1/service-security-layers)

[Invoking Services from Service Builder Code](/develop/reference/-/knowledge_base/7-1/invoking-services-from-service-builder-code)

[OSGi Services and Dependency Injection with Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)
