# Spring MVC [](id=spring-mvc)

Liferay is an open platform in an ecosystem of open platforms. Just because
Liferay has its own [MVC framework](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet),
therefore, doesn't mean you have to use it. It is perfectly valid to bring the
tools and experience you have from other development projects over to Liferay.
In fact, we expect you to. Liferay's development platform is standards-based,
making it an ideal choice for applications of almost any type. 

If you're already a wizard with Spring MVC, you can use it instead of Liferay's
[`MVCPortlet` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
with no limitations whatsoever. Since Spring MVC replaces only your
application's web application layer, you can still use
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder) 
for your service layer. 

So what does it take to implement a Spring MVC application in Liferay? Start by
considering how to package a Spring MVC application for @product-ver@.

## Packaging a Spring MVC Portlet [](id=packaging-a-spring-mvc-portlet)

Developers creating portlets for @product-ver@ can usually deploy their portlet as
Java EE-style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR)
OSGi bundle artifacts. Spring MVC portlet developers don't have that
flexibility. Spring MVC portlets must be packaged as WAR artifacts because the
Spring MVC framework is designed for Java EE. Therefore, it expects a WAR layout
and requires Java EE resources such as the `WEB-INF/web.xml` descriptor. 

Because Liferay supports the OSGi WAB standard for deployment, you can deploy
your WAR and it will run as expected in the OSGi runtime. Here are the high
points on why that works in @product-ver@:

-   The Liferay auto-deploy process runs, adding the `PortletServlet` and
    `PlugincontextListener` configurations to the `WEB-INF/web.xml` file.

-   The
    [Liferay WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)
    automatically creates an OSGi-ready `META-INF/MANIFEST.MF` file. If you want
    to affect the content of the manifest file, you can place BND directives and
    OSGi headers directly into the `WEB-INF/liferay-plugin-package.properties`
    file.

You'll still need to provide the Liferay descriptor files `liferay-display.xml`
and `liferay-portlet.xml`, and you'll need a `portlet.xml` descriptor.

Develop a Spring MVC portlet WAR file with the appropriate descriptor files, and
let the auto-deploy process and Liferay's WAB generator take care of converting
your project to a Liferay-ready WAB.

Now get into the details of configuring a Spring MVC portlet for Liferay.

## Spring MVC Portlets in Liferay [](id=spring-mvc-portlets-in-liferay)

This isn't a comprehensive guide to configuring a Spring MVC portlet. It covers
the high points, assuming you already have familiarity with Spring MVC. If you
don't, you should consider using Liferay's MVC framework. 

What does a Liferay Spring MVC portlet look like? Almost identical to any other
Spring MVC portlet. To configure a Spring MVC portlet, start with the
`<portlet-class>` element in `portlet.xml`. In it you must declare Spring's
`DispatcherPortlet`:

    <portlet-class>org.springframework.web.portlet.DispatcherPortlet</portlet-class>

The Spring front controller needs to know where the application context file is,
so specify it as an initialization parameter in `portlet.xml` (update the path
as needed):

    <init-param>
        <name>contextConfigLocation</name>
        <value>/WEB-INF/spring/portlet-context.xml</value>
    </init-param>

Provide an application context file (`portlet-context.xml` in the example
above), specified as you normally would for your Spring MVC portlet.

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

If you're configuring a WAB yourself, the `web.xml` file in your Spring MVC
project needs to be fully ready for deployment. In addition to any `web.xml`
configuration for Spring MVC, you need to include a listener for
[`PluginContextListener`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/PluginContextListener.html)
and a `servlet` and `servlet-mapping` for
[`PortletServlet`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/PortletServlet.html):

    <listener>
        <listener-class>com.liferay.portal.kernel.servlet.PluginContextListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>Portlet Servlet</servlet-name>
        <servlet-class>com.liferay.portal.kernel.servlet.PortletServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Portlet Servlet</servlet-name>
        <url-pattern>/portlet-servlet/*</url-pattern>
    </servlet-mapping>

If you're letting Liferay generate the WAB for you (this is the recommended
approach), the above is not necessary, as it is added automatically during
auto-deployment.

Your application must be able to convert `javax.portlet.PortletRequest`s to
`javax.servlet.ServletRequest`s and back again. Add this to `web.xml`:

    <servlet>
        <servlet-name>ViewRendererServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.ViewRendererServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewRendererServlet</servlet-name>
        <url-pattern>/WEB-INF/servlet/view</url-pattern>
    </servlet-mapping>

To configure the Spring view resolver, add a bean in your application context
file:

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

Now the front controller, `org.springframework.web.portlet.DispatcherPortlet`,
can get a request from the view layer, but there are no actual controller
classes to delegate the request handling to.

With Spring MVC, your controller is conveniently separated into classes
that handle the portlet modes (View, Edit, Help).

You'll use Spring's annotations to configure the controller and tell
`DispatcherPortlet` which mode the controller supports. 

A simple controller class supporting View mode might look like this:

    @Controller("myAppController")
    @RequestMapping("VIEW")
    public class MyAppController {

        @RenderMapping
        public String processRenderRequest(RenderRequest request,
                RenderResponse response) {

            return "defaultView";
        }
    }

The `return defaultView` statement should be understood in terms of the view
resolver bean in the application context file, which gives the String
`defaultView` a prefix of `WEB-INF/views/`, and a suffix of `.jsp`. That maps to
the path `WEB-INF/views/defaultView.jsp`, where you would place your default
view for the application. 

With Spring MVC, you can only support one portlet phase in each controller. 

An edit mode controller might contain render methods and action methods.

    @Controller("myAppEditController")
    @RequestMapping("EDIT")
    public class MyAppEditController {

        @RenderMapping
        public String processRenderRequest(RenderRequest request,
                RenderResponse response) {

            return "thisView";
        }

        @ActionMapping(params="action=doSomething")
        public void doSomething(Actionrequest request, ActionResponse response){
        
            // Do something here

        }
    }

You need to define any controller classes in your application context file by
adding a `<bean>` tag for each one:

    <bean class="com.liferay.docs.springmvc.portlet.MyAppController" />
    <bean class="com.liferay.docs.springmvc.portlet.MyAppEditController" />

Develop your controllers and your views as you normally would in a Spring MVC
portlet. You'll also need to provide some necessary descriptors for Liferay.

### Liferay Descriptors [](id=liferay-descriptors)

Liferay portlet plugins that are packaged as WAR files should include some
Liferay specific descriptors.

The descriptor `liferay-display.xml` controls the category in which your portlet
appears in @product@'s *Add* menu. Find the complete DTD
[here](@platform-ref@/7.0-latest/definitions/liferay-display_7_0_0.dtd.html).

Here's a simple example that just specifies the category the application will go under
in Liferay's menu for adding applications:

    <display>
        <category name="New Category">
            <portlet id="example-portlet" />
        </category>
    </display>

The descriptor `liferay-portlet.xml` is used for specifying additional
information about the portlet (like the location of CSS and JavaScript files or
the portlet's icon. A complete list of the attributes you can set can be
found [here](@platform-ref@/7.0-latest/definitions/liferay-portlet-app_7_0_0.dtd.html)

    <liferay-portlet-app>
        <portlet>
            <portlet-name>example-portlet</portlet-name>
            <instanceable>true</instanceable>
            <render-weight>0</render-weight>
            <ajaxable>true</ajaxable>
            <header-portlet-css>/css/main.css</header-portlet-css>
            <footer-portlet-javascript>/js/main.js</footer-portlet-javascript>
            <footer-portlet-javascript>/js/jquery.foundation.orbit.js</footer-portlet-javascript>
        </portlet>
        <role-mapper>
            <role-name>administrator</role-name>
            <role-link>Administrator</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>guest</role-name>
            <role-link>Guest</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>power-user</role-name>
            <role-link>Power User</role-link>
        </role-mapper>
        <role-mapper>
            <role-name>user</role-name>
            <role-link>User</role-link>
        </role-mapper>
    </liferay-portlet-app>

You'll also notice the `role-mapper` elements included above. They're for
defining the Liferay roles used in the portlet. 

Then there's the `liferay-plugin-package.properties`. These properties describe
the Liferay plugin, declare its resources, and specify its security related
parameters. The DTD is found
[here](@platform-ref@/7.0-latest/definitions/liferay-plugin-package_7_0_0.dtd.html)

    name=example-portlet
    module-group-id=liferay
    module-incremental-version=1
    tags=
    short-description=
    change-log=
    page-url=http://www.liferay.com
    author=Liferay, Inc.
    licenses=LGPL
    version=1

In the `liferay-plugin-package.properties` file you can also add OSGi metadata,
which is properly placed in the `MANIFEST.MF` file when you deploy your
WAR file. 

Find all of Liferay's DTDs [here](@platform-ref@/7.0-latest/definitions/)

## Calling Services from Spring MVC [](id=calling-services-from-spring-mvc)

To call OSGi-based Service Builder services from your Spring MVC portlet, you
need a mechanism that gives you access to the OSGi service registry.

+$$$

**Note:** If you don't already have one, create a service builder project using [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

    springmvc-service-builder/
        build.gradle
        springmvc-service-builder-api/
            bnd.bnd
            build.gradle
        springmvc-service-builder-service/
            bnd.bnd
            build.gradle
            service.xml

Design your model entity and write your service layer as normal (see the
tutorials on Service Builder
[here](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)). After
that, add your service's API JAR as a dependency in your Spring MVC project. 

$$$

Since you're in the context of a Spring MVC portlet, you can't look up a
reference to the services published to the OSGi runtime using Declarative
Services. So how do you call Service Builder services, or other services
published in the OSGi service registry? One way is by calling the static
utility methods.

    FooServiceUtil.getFoosByGroupId()

While very simple, that's not a good way to call OSGi services because of the
dynamic nature of the OSGi runtime. Service implementations could be removed and
added at any time, and your plugin needs to be able to account for that.
Additionally, you need a mechanism that lets your portlet plugin react
gracefully to the possibility of the service implementation becoming unavailable
entirely. That's why you should open a Service Tracker when you want to call a
service that's in the OSGi service registry.

### Service Trackers [](id=service-trackers)

Since you don't have the luxury of using Declarative Services to manage your
service dependencies, you have a little bit of work to do if you want to gain
some of the benefits OSGi gives you:

-  Accounting for multiple service implementations, using the best service
   implementation available (taking into account the service ranking property)

-  Accounting for no service implementations

The static utility classes don't let you do that, and that's sad. But be happy,
because with a little code, you can regain those benefits. For the details on
implementing a service tracker, read the [Service Trackers tutorial](/develop/tutorials/-/knowledge_base/7-0/service-trackers).

To summarize, you'll need to do these things:

-  Instantiate a new `org.osgi.util.tracker.ServiceTracker` to track the service
    of the type you need.

-  Open the service tracker in an `@PostConstruct` method.

-  Make sure the service tracker has
    something in it.

-  If there is indeed something in the service tracker,
    get the service.

-  Now you're ready to call the service. Here's what the `if` block might look
    like:

        if (!someServiceTracker.isEmpty()) {
            SomeService someService = someServiceTracker.getService();
            someService.doSomethingCool();
        }

-  Close the service tracker in an `@PreDestroy` method.

That's probably not enough detail, so refer to the tutorial on [Service
Trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers) for the
details. As you'll see in the tutorial, there's some boilerplate code involved,
but leveraging service trackers lets you look up services in the OSGi
runtime.

If you are not required to use a Spring MVC portlet, consider using Liferay's
MVC framework to design your portlets instead. Then you can take advantage of
the Declarative Services `@Component` and `@Reference` annotations, which let
you avoid the boilerplate code associated with service trackers.
