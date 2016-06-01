# Spring MVC

If you're developing applications for Liferay, it makes sense to use [Liferay's MVC framework](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet) and [Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder). If you're already a wizard with
Spring MVC, you can instead use that framework for your application's
controller. You can still use Service Builder for your Model layer.

So, what does it take to implement a Spring MVC application in Liferay? Assuming
you're familiar with Spring MVC, there are a few notable things to mention and
you have a decision to make, since Spring MVC projects need to be packaged as
WAR archives: Do you want to pre-configure your Spring MVC portlet project as a
WAB, or do you want to let the WAB generator in Liferay do the work for you? The
benefits and drawbacks of each approach are covered here.

<!-- Spring MVC can also be packaged as a JAR according to Miguel. We need to
find best practices for this. Maybe this should be re-factored to show 1. How to
make an existing Spring MVC portlet run in Liferay and consume OSGi services
using best practices, and how to start from scratch adn create a "best
practices" spring MVC portlet.-->

+$$$

**Note:** If you're wondering what in the world a WAB is, it's a Web Application Bundle.
If that doesn't clarify things, think of it as a traditional WAR-style
plugin, containing a `META-INF/MANIFEST.MF` file with the `Bundle-SymbolicName`
OSGi header specified. The WAB can run as an OSGi module because of the Liferay
[WAB Extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-extender). 

$$$

<!--
Here's the easiest way:

-  Create a project from the Liferay 6.2 Maven archetype:
http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liferay-portlet-spring-mvc-archetype%22

-  Update the descriptors `liferay-portlet.xml`,
   `liferay-display.xml`, and `liferay-plugin-package.properties` to Liferay 7.

-  Deploy the WAR. How does WAR deployment work in Liferay 7? Check out the
   tutorial on [packaging JSF applications](develop/tutorials/-/knowledge_base/7-0/packaging-a-jsf-application)
for an explanation.
-->

First, consider how you want to package your Spring MVC portlet project.

## Packaging a Spring MVC Portlet

Developers creating portlets for Liferay 7.0 can usually deploy their portlet as
Java EE style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR)
OSGi bundle artifacts. Spring MVC portlet developers don't have that
flexibility. Spring MVC portlets must be packaged as WAR artifacts because the
Spring MVC framework expects a WAR layout and requires Java EE resources such as
the `WEB-INF/web.xml` descriptor. There are two ways to package a Spring MVC
portlet so it can be treated as an OSGi module at runtime. The benefits and
drawbacks of each approach are listed.

Because Liferay supports the OSGi WAB standard for deployment, you can deploy
your WAR and it will run as expected in the OSGi runtime. There are a couple of
ways to make your source code into a WAB:

-  Pre-configure a WAB by providing the `MANIFEST.MF` file with
  `Bundle-SymbolicName` OSGi header. This is easily accomplished by using a
`bnd.bnd` file in the root of your project, which specifies OSGi headers that
will go in the manifest.

    This approach has some benefits:

    - The `bnd.bnd` file can be processed by a build-time plugin (e.g.,
      [bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html)) to affect the content of an OSGi-ready `META-INF/MANIFEST.MF`.
    - Can take advantage of OSGi Declarative Services (DS).

    There are also some drawbacks:

    - Bypasses the Liferay auto-deploy process, which means developers must
      have the `WEB-INF/web.xml` descriptor fully ready for deployment.

-  Alternatively, use Liferay's WAB Generator to convert your WAR into a WAB at
   deployment time.

    The benefits:

    - Processed by the Liferay auto-deploy process, which adds
          `PortletServlet` and `PluginContextListener` to the `WEB-INF/web.xml`
          descriptor.
    - Processed by the Liferay [WAB Generator](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-generator),
          which automatically creates an OSGi-ready `META-INF/MANIFEST.MF`.
    - Can affect the content of `META-INF/MANIFEST.MF` by putting BND directives and
          OSGi headers into the `WEB-INF/liferay-plugin-package.properties` file.

    The drawbacks:

    - Can't supply the `bnd.bnd` and can't
      utilize a build-time plugin such as the
[bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html)
for generating the manifest file.
    - Can't take advantage of OSGi Declarative Services (DS).
    - Developer must specify the `WEB-INF/portlet.xml` and
      `WEB-INF/liferay-portlet.xml` descriptors.

Of the two approaches above, it's recommended to use the pre-configured WAB,
providing a `bnd.bnd` and deployment-ready `web.xml`. If you're going to be
calling any services registered in the OSGi runtime (like Liferay services or
your own Service Builder services), this will allow you to use the Declarative
Services `@Reference` annotation to look up the services, so that your Spring
MVC portlet can take advantage of the OSGi lifecycle when satisfying its service
dependencies. That means your Spring MVC portlet will be taken out of service if
any of its service dependencies cannot be met.

Now get into the details of configuring a Spring MVC portlet for Liferay.

## Spring MVC Portlets in Liferay

What does a Liferay Spring MVC portlet look like? Almost identical to a Spring
MVC portlet that's not in Liferay. This won't be a comprehensive guide to
configuring a Spring MVC portlet. It will cover the high points, assuming you
already have familiarity with Spring MVC. Otherwise, consider using Liferay's
MVC framework.

<!--
Here's what will happen if you deploy a Spring MVC portlet WAR to Liferay without
any additional configuration:

![Figure x: You need to specify some information in Liferay's descriptor files
for your Spring MVC portlet to play nicely in
Liferay.](../../images/spring-mvc-menu-entry.png)

![Figure x: You need to specify some information in Liferay's descriptor files
for your Spring MVC portlet to play nicely in
Liferay.](../../images/spring-mvc-roles-error.png)
-->

Start with the `<portlet-class>` element in `portlet.xml`. It needs to specify a
`portlet-class` element that declares Spring's `DispatcherPortlet`:

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
`PluginContextListener` and a `servlet` and `servlet-mapping` for
`PortletServlet`:

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

If you're letting Liferay generate the WAB for you, the above is not necessary,
as it will be added automatically during auto-deployment.

Your application needs the ability to convert `PortletRequest`s to
`ServletRequest`s and back again. Add this to `web.xml`:

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

Now the front controller, `DispatcherPortlet`, can get a request from the view
layer, but there are no actual controller classes to delegate the request
handling to.

With Spring MVC your controller is conveniently separated into classes
that will handle a particular portlet mode.

You'll use Spring's annotations to configure the controller and tell
`DispatcherPortlet` which mode the controller supports. 

A simple controller supporting view mode might look like this:

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

### Liferay Descriptors

Liferay portlet plugins that are packaged as a WAR file should include some
Liferay specific descriptors. They're not required, but it's definitely a best
practice to include them.

The descriptor `liferay-display.xml` helps you control the display of your
portlet in Liferay. Find the complete DTD
[here](https://docs.liferay.com/portal/7.0/definitions/liferay-display_7_0_0.dtd.html).

Here's a simple example that just specifies the category the app will go under
in Liferay's menu for adding apps:

    <display>
        <category name="New Category">
            <portlet id="example-portlet" />
        </category>
    </display>

The descriptor `liferay-portlet.xml` is used for specifying additional
information about the portlet (like the location of CSS and JavaScript files, or
the icon for the portlet. A complete list of the attributes you can set can be
found [here](https://docs.liferay.com/portal/7.0/definitions/liferay-portlet-app_7_0_0.dtd.html)

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
defining the Liferay roles used in the portlet. They usually match the  so that, in your code, you can
check 

Then there's the `liferay-plugin-package.properties`. These properties describe
the Liferay plugin, declare its resources, and specify its security related
parameters. The DTD is found
[here](https://docs.liferay.com/portal/7.0/definitions/liferay-plugin-package_7_0_0.dtd.html)

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

You can find all of Liferay's DTDs [here](https://docs.liferay.com/portal/7.0/definitions/)

## Calling Services from Spring MVC

You can call OSGi-based Service Builder services from your Spring MVC portlet.

If you don't already have one, create a service builder project using [Blade
CLI]().

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
[here](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder). After
that, add your service's API JAR as a dependency in your Spring MVC project. If
you are using the pre-packaged WAB approach described above, call the services
as you would from an OSGi module, using Declarative Services. 

    import org.osgi.service.component.annotations.Reference;

First get a reference to the service you need, using the `@Reference` annotation
and a setter method that takes the local service as a parameter. Then set the
retrieved service to a private variable.

	@Reference
	protected void setFooLocalService(
		FooLocalService fooService) {

		_fooLocalService = fooService;
	}

	private FooLocalService _fooLocalService;

Now you can call the service in your controller classes.

    _fooLocalService.getFoosByGroupId(groupId);

The above approach lets you take full advantage of the OSGi lifecycle. If you're
using the WAB generator approach or if you're looking up old Service Builder
services (like services developed for Liferay 6), you won't have the ability to
look up a reference to the services published to the OSGi runtime using this
annotation. In either case, how do you call Service Builder services? While you
can't enjoy the full benefits of the OSGi lifecycle and you can't use the
`@Reference` annotation, you can still call the services. For example, you can
still call the static utility methods (for example,
`FooServiceUtil.getFoosByGroupId()`). In addition, you can look up the OSGi
service from its bundle context. Here's an example of looking up the
`UserImporter` service:

    Bundle bundle = FrameworkUtil.getBundle(getClass());
    BundleContext bundleContext = bundle.getBundleContext();
    ServiceReference<UserImporter> serviceReference =
        bundleContext.getServiceReference(UserImporter.class);
    
    UserImporter userImporter =
        bundleContext.getService(serviceReference);

First you look up the bundle using a call to `frameworkUtil.getBundle()`, and
then get its bundle context. `BundleContext` methods let your bundle interact
with the OSGi runtime. In this case your bundle needs a service reference, and then
a Service object, for the `UserImporter` service.

Again, these service calls are inferior to using the Declarative Services
`@Reference` annotation because you won't enjoy the benefits of the full OSGi
lifecycle. In other words, the WAB will be placed in service, and kept in
service, even if the services it depends on are not available. If at all
possible, use the pre-configured WAB approach described above when developing
your Spring MVC portlets. In addition, if you have legacy services, consider
upgrading them to fully baked OSGi services.
