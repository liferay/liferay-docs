# Spring MVC

If you're developing applications for Liferay, it makes sense to use [Liferay's MVC framework](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet) and [Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder). If you're already a wizard with
Spring MVC, you can instead use that framework for your application's
controller. You can still use Service Builder for your service layer.

So, what does it take to implement a Spring MVC application in Liferay? Assuming
you're familiar with Spring MVC, there are a few notable things to mention and
you have a decision to make, since Spring MVC projects need to be packaged as
WAR archives: Do you want to pre-configure your Spring MVC portlet project as a
WAB, or do you want to let the WAB generator in Liferay do the work for you? The
benefits and drawbacks of each approach are covered here.

+$$$

**Note:** If you're wondering what in the world a WAB is, it's a Web Application Bundle.
If that doesn't clarify things, think of it as a traditional WAR-style
plugin, containing a `META-INF/MANIFEST.MF` file with the `Bundle-SymbolicName`
OSGi header specified. The WAB can run as an OSGi module because of the Liferay
[WAB Extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-extender). 

$$$

First, consider how you want to package your Spring MVC portlet project.

## Packaging a Spring MVC Portlet

Developers creating portlets for Liferay 7.0 can usually deploy their portlet as
Java EE style Web Application ARchive (WAR) artifacts or as Java ARchive (JAR)
OSGi bundle artifacts. Spring MVC portlet developers don't have that
flexibility. Spring MVC portlets must be packaged as WAR artifacts because the
Spring MVC framework expects a WAR layout and requires Java EE resources such as
the `WEB-INF/web.xml` descriptor. 

Because Liferay supports the OSGi WAB standard for deployment, you can deploy
your WAR and it will run as expected in the OSGi runtime. There are a couple of
ways to make your source code into a WAB:

-  Use Liferay's WAB Generator to convert your WAR into a WAB at
   deployment time.

    The benefits of this approach:

    - Processed by the Liferay auto-deploy process, which adds
          `PortletServlet` and `PluginContextListener` to the `WEB-INF/web.xml`
          descriptor.
    - Processed by the Liferay [WAB Generator](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-generator),
          which automatically creates an OSGi-ready `META-INF/MANIFEST.MF`.
    - Can affect the content of `META-INF/MANIFEST.MF` by putting BND directives and
          OSGi headers into the `WEB-INF/liferay-plugin-package.properties` file.

    The drawback:

    - Can't supply the `bnd.bnd` and can't
      utilize a build-time plugin such as the
[bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html)
for generating the manifest file.

-  Pre-configure a WAB by providing the `MANIFEST.MF` file with
  `Bundle-SymbolicName` OSGi header. This is easily accomplished by using a
`bnd.bnd` file in the root of your project, which specifies OSGi headers that
will go in the manifest.

    This approach has a benefit over the WAB Generator approach:

    - The `bnd.bnd` file can be processed by a build-time plugin (e.g.,
      [bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html)) to affect the content of an OSGi-ready `META-INF/MANIFEST.MF`.

    There's also a drawback:

    - Bypasses the Liferay auto-deploy process, which means developers must
      have the `WEB-INF/web.xml` descriptor fully ready for deployment.

In either case, you'll need to provide the Liferay descriptor files
`liferay-display.xml` and `liferay-portlet.xml`, and you'll need a `portlet.xml`
descriptor.

Of the two approaches above, it's recommended to develop a Spring MVC portlet
WAR file with the appropriate descriptor files, and let the auto-deploy process
and Liferay's WAB generator take care of converting your project to a
Liferay-ready WAB. This is what each tool gets you:

-  The auto-deploy feature in Liferay will automatically configure the required
   Portlet Servlet and `PluginContextListener` in your project's `web.xml`:

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

-  The WAB generator adds the necessary OSGi headers in the required
   `MANIFEST.MF` file. You can directly configure OSGi headers in your project's
`plugin-package.properties` file.

Now get into the details of configuring a Spring MVC portlet for Liferay.

## Spring MVC Portlets in Liferay

What does a Liferay Spring MVC portlet look like? Almost identical to a Spring
MVC portlet that's not in Liferay. This won't be a comprehensive guide to
configuring a Spring MVC portlet. It will cover the high points, assuming you
already have familiarity with Spring MVC. Otherwise, consider using Liferay's
MVC framework.

Start with the `<portlet-class>` element in `portlet.xml`. In it you must
declare Spring's `DispatcherPortlet`:

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

A simple controller class supporting view mode might look like this:

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
Liferay specific descriptors.

The descriptor `liferay-display.xml` provides control over the display of your
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
defining the Liferay roles used in the portlet. 

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

In the `liferay-plugin-package.properties` file you can also add OSGi metadata,
and it will be properly placed in the `MANIFEST.MF` file when you deploy your
WAR file. 

Find all of Liferay's DTDs [here](https://docs.liferay.com/portal/7.0/definitions/)

## Calling Services from Spring MVC

You can call OSGi-based Service Builder services from your Spring MVC portlet.
Unfortunately, without an OSGi Component, you can't leverage the OSGi lifecycle
in your Spring MVC portlet. Your portlet will remain in service even if a
service it depends on becomes unavailable.

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
[here](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder). After
that, add your service's API JAR as a dependency in your Spring MVC project. 

$$$

Since you don't have the ability to look up a reference to the services
published to the OSGi runtime using Declarative Services, how do you call
Service Builder services? One way is by calling the static utility methods.

    FooServiceUtil.getFoosByGroupId()

Preferably, you can look up the OSGi service using the Service Tracker API.
First you look up the bundle using a call to `frameworkUtil.getBundle()`, and
then get its bundle context. `BundleContext` methods let your bundle interact
with the OSGi runtime. In this case you're going to pass the bundle context to a
service tracker with the name of the service's class, then open the service
tracker. An `init` method is a good place to put this initialization code :

	@PostConstruct
	public void init() {

		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		BundleContext bundleContext = bundle.getBundleContext();
		serviceTracker = new ServiceTracker<>(bundleContext, SomeService.class, null);
		serviceTracker.open();
	}

When you want to call the service in your controller's method, first make sure
it's been fetched by the Service Tracker, then get the service using the Service
Tracker API's `getService` method. After that, use the service to do something
cool, and close the service tracker when you're done with it:

    if (!serviceTracker.isEmpty()) {
        SomeService someService = (SomeService) serviceTracker.getService();

        someService.doSomethingCool();
    }

When it's time for the controller bean to be removed, you can close the service
tracker. Using a `destroy` method is an appropriate place to do this:

	@PreDestroy
	public void destroy() {
		
		serviceTracker.close();
	}

Note that using a service tracker, as shown above, is inferior to using the
Declarative Services `@Reference` annotation because you won't enjoy the
benefits of the full OSGi lifecycle. In other words, the WAB will be placed in
service, and kept in service, even if the services it depends on are not
available. If you are not required to use a Spring MVC portlet, consider using
Liferay's MVC framework to design your portlets instead, since you can take
advantage of the Declarative Services `@Component` and `@Reference`, which let's
you leverage the OSGi lifecycle.
