# Configuring a Spring MVC Portlet [](id=configuring-a-spring-mvc-portlet)

This isn't a comprehensive guide to configuring a Spring MVC portlet. It covers
the high points, assuming you already have familiarity with Spring MVC. If you
don't, you should consider using
[Liferay's MVC framework](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet). 

What does a Liferay Spring MVC portlet look like? Almost identical to any other
Spring MVC portlet. 

## Portlet Configuration [](id=portlet-configuration)

In the `portlet.xml` file's `portlet-class` element you must declare Spring's
`DispatcherPortlet`:

    <portlet-class>org.springframework.web.portlet.DispatcherPortlet</portlet-class>

The Spring front controller needs to know where the application context file is,
so specify it as an initialization parameter in the `portlet.xml` (update the
path as needed):

    <init-param>
        <name>contextConfigLocation</name>
        <value>/WEB-INF/spring/portlet-context.xml</value>
    </init-param>

Provide an application context file (`portlet-context.xml` in the example
above), specified as you normally would for your Spring MVC portlet. Next
configure your web application. 

## Web Application Configuration [](id=web-application-configuration)

If you're
[letting Liferay generate the WAB for you](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
(this is the recommended approach), the elements are added automatically during
auto-deployment.

If you're configuring an
[OSGi Web Application Bundle (WAB)](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
yourself, the `web.xml` file in your Spring MVC
project must be fully ready for deployment. In addition to your Spring MVC
configuration, your `web.xml` must include these elements:

- `listener` for [`PluginContextListener`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/PluginContextListener.html)

- `servlet` and `servlet-mapping` for
[`PortletServlet`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/PortletServlet.html)

The elements look like this:

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

Your application must be able to convert `javax.portlet.PortletRequest`s to
`javax.servlet.ServletRequest`s and back again. Add this to the `web.xml`:

    <servlet>
        <servlet-name>ViewRendererServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.ViewRendererServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewRendererServlet</servlet-name>
        <url-pattern>/WEB-INF/servlet/view</url-pattern>
    </servlet-mapping>

That's all the configuration that's necessary for `web.xml`. Now you're ready to
configure the views. 

## Views [](id=views)

To configure the Spring view resolver, add a bean to your application context
file (`portlet-context.xml` in the previous example):

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>

Now the front controller, `org.springframework.web.portlet.DispatcherPortlet`,
can get a request from the view layer, so now it's time to configure controller
classes to handle the requests. 

## Controllers [](id=controllers)

With Spring MVC, your controller is conveniently separated into classes that
handle the portlet modes (View, Edit, Help).

You'll use Spring's annotations to configure the controller and tell
`DispatcherPortlet` which mode the controller supports. 

### View Mode Controller [](id=view-mode-controller)

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

### Edit Mode Controller [](id=edit-mode-controller)

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

Make sure to define any controller classes in your application context file by
adding a `bean` element for each one:

    <bean class="com.liferay.docs.springmvc.portlet.MyAppController" />
    <bean class="com.liferay.docs.springmvc.portlet.MyAppEditController" />

Develop your controllers and your views as you normally would in a Spring MVC
portlet. You must also provide some necessary descriptors for Liferay.

## Liferay Descriptors [](id=liferay-descriptors)

Liferay portlet plugins that are packaged as WAR files should include some
Liferay specific descriptors.

The descriptor `liferay-display.xml` controls the category in which your portlet
appears in @product@'s *Add* menu. Find the complete DTD
[here](@platform-ref@/7.1-latest/definitions/liferay-display_7_1_0.dtd.html).

Here's a simple example that specifies a new category for the application in
Liferay's menu for adding applications:

    <display>
        <category name="New Category">
            <portlet id="example-portlet" />
        </category>
    </display>

The descriptor `liferay-portlet.xml` specifies additional information about the
portlet (like the location of CSS and JavaScript files or the portlet's icon.
A complete list of the attributes you can set can be found
[here](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html)

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

You'll also notice the `role-mapper` elements included above. They define the
Liferay roles used in the portlet. 

The `liferay-plugin-package.properties` file describes the Liferay plugin,
declares its resources, and specifies its security related parameters. The DTD
is 
[here](@platform-ref@/7.1-latest/definitions/liferay-plugin-package_7_1_0.dtd.html).

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

In the `liferay-plugin-package.properties` file, you can also add OSGi metadata
which the
[Liferay WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
adds to the `MANIFEST.MF` file when you deploy your WAR file. 

All of Liferay's DTDs are [here](@platform-ref@/7.1-latest/definitions/).

## Calling Services from Spring MVC [](id=calling-services-from-spring-mvc)

To call OSGi-based Service Builder services from your Spring MVC portlet, you
need a mechanism that gives you access to the OSGi service registry.

Since you're in the context of a Spring MVC portlet, you can't look up a
reference to the services (including Service Builder services) published to the
OSGi runtime using Declarative Services. You have to use
[Service Trackers](/develop/tutorials/-/knowledge_base/7-1/service-trackers).
There's some boilerplate code involved, but the ability to look up services in
the OSGi runtime is worth it. 

Next consider how to package and deploy your Spring MVC portlet.
