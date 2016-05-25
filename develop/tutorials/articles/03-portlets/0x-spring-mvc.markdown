# Spring MVC

If you're developing applications for Liferay, it makes sense to use [Liferay's MCVC framework](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet) and [Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder). If you're already a wizard with
Spring MVC, you can instead use that framework for your application's
controller. You can still use Service Builder for your Model layer.

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

Now get into the details of configuring a Spring MVC portlet for Liferay.

## Spring MVC Portlets in Liferay

What does a Liferay Spring MVC portlet look like? Almost identical to a Spring
MVC portlet that's not in Liferay. This won't be a comprehensive guide to
configuring a Spring MVC portlet. It will cover the high points, assuming you
already have familiarity with Spring MVC. Otherwise, consider using Liferay's
MVC framework.

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
portlet. If you're going to call services published in a module to the OSGi
runtime (such as services generated by Service Builder), keep reading.

## Calling Services from Spring MVC

You can call OSGi-based Service Builder services from your Spring MVC portlet.

Create a service builder project using Blade CLI.

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
using the WAB generator approach, you won't have the ability to look up a
reference to the services published to the OSGi runtime. Instead, you can call
the traditional Liferay static utility methods (e.g., `FooServiceUtil.get*`) in
order to call Liferay services in your WABs. Calling Liferay's static utility
methods, however, means that you won't enjoy the full OSGi lifecycle. In other
words, the WAB will be placed in service, and kept in service, even if the
services it depends on are not available. 

<!-- ## Creating a Spring MVC Liferay project in Liferay IDE

The easiest route to developing a Spring MVC application is by using Liferay
IDE. This is accomplished very simply:

-  Configuring Eclipse Maven plugins and Liferay IDE
-  Creating a Liferay Plugin Project
-  Choosing Maven as the Build Type
-  Choosing Spring MVC as the portlet framework

Liferay IDE creates a project based on the [Spring MVC archetype project](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22liferay-portlet-spring-mvc-archetype%22)
in the Maven Central repository.

From there, you're free to develop your Liferay Maven Spring MVC portlet. You'll
have the necessary descriptor files, a default controller for *View* mode, a
default JSP, a `pom.xml`, and more.

+$$$

**Note:** The Liferay Maven Spring MVC archetype is for Liferay 6.2. To update
it for Liferay 7, update the descriptor files, and you'll be on your way.

$$$
-->


<!--
## Pre-Configured WAB [](id=pre-configured-wab)

The source of a pre-configured JSF Portlet WAB follows the standard directory
layout for WAR projects and includes a `bnd.bnd` file along with a Java class
that extends
[GenericFacesPortlet](http://myfaces.apache.org/portlet-bridge/2.0/api/apidocs/javax/portlet/faces/GenericFacesPortlet.html):

- `bnd.bnd`
- `src/`
    - `main/`
        - `java/`
            - `MyPortlet.java`
        - `webapp/`
            - `WEB-INF/`
                - `faces-config.xml`
                - `web.xml`

**Benefits:**

- The `bnd.bnd` file can be processed by a build-time plugin (e.g.,
  [bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html))
  to affect the content of an OSGi-ready `META-INF/MANIFEST.MF`.
- Can take advantage of OSGi Declarative Services (DS).
- Provides a class that extends
  [GenericFacesPortlet](http://myfaces.apache.org/portlet-bridge/2.0/api/apidocs/javax/portlet/faces/GenericFacesPortlet.html)
  and specifies portlet attributes via the `@Component` annotation, rather than
  using two separate descriptors: `WEB-INF/portlet.xml` and
  `WEB-INF/liferay-portlet.xml`.

**Drawbacks:**

- Bypasses the Liferay auto-deploy process, which means developers must have
  the `WEB-INF/web.xml` descriptor fully ready for deployment, such as
  `<servlet>` and `<servlet-mapping>` for Liferay's `PortletServlet` and a
  `<listener>` for Liferay's `PluginContextListener`.

Here's an example `web.xml` descriptor you could use that is ready for
deployment:

    <?xml version="1.0"?>

    <web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
        <display-name>/jsf.applicant-4.0.0-SNAPSHOT</display-name>
        <context-param>
            <param-name>javax.faces.WEBAPP_RESOURCES_DIRECTORY</param-name>
            <param-value>/WEB-INF/resources</param-value>
        </context-param>
        <context-param>
            <param-name>com.sun.faces.namespaceParameters</param-name>
            <param-value>true</param-value>
        </context-param>
        <listener>
            <listener-class>com.liferay.portal.kernel.servlet.PluginContextListener</listener-class>
        </listener>
        <servlet>
            <servlet-name>FacesServlet</servlet-name>
            <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>Faces Servlet</servlet-name>
            <url-pattern>*.xhtml</url-pattern>
        </servlet-mapping>
        <servlet>
            <servlet-name>Portlet Servlet</servlet-name>
            <servlet-class>com.liferay.portal.kernel.servlet.PortletServlet</servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>Portlet Servlet</servlet-name>
            <url-pattern>/portlet-servlet/*</url-pattern>
        </servlet-mapping>
        <security-constraint>
            <web-resource-collection>
                <web-resource-name>Facelet XHTML</web-resource-name>
                <url-pattern>*.xhtml</url-pattern>
            </web-resource-collection>
            <auth-constraint/>
        </security-constraint>
    </web-app>

## Auto-Generated WAB [](id=auto-generated-wab)

The source of an auto-generated JSF Portlet WAB also follows the standard
directory layout for WAR projects. However, it does *NOT* include a `bnd.bnd`
file and also does *NOT* include a Java class annotated with `@Component`:

- `src/`
    - `main/`
        - `java/`
        - `webapp/`
            - `WEB-INF/`
                - `faces-config.xml`
                - `liferay-display.xml`
                - `liferay-plugin-package.properties`
                - `liferay-portlet.xml`
                - `portlet.xml`
                - `web.xml`
	 			
**Benefits:**

- Processed by the Liferay auto-deploy process, which adds the aforementioned
  `PortletServlet` and `PluginContextListener` to the `WEB-INF/web.xml`
  descriptor.
- Processed by the Liferay.
  [WAB Generator](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-generator),
  which automatically creates an OSGi-ready `META-INF/MANIFEST.MF`.
- Can affect the content of `META-INF/MANIFEST.MF` by putting BND directives and
  OSGi headers into the `WEB-INF/liferay-plugin-package.properties` file.

**Drawbacks:**

- Can't supply the `bnd.bnd` and can't utilize a build-time plugin such as the
  [bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html).
- Can't take advantage of OSGi Declarative Services (DS).
- Developer must specify the `WEB-INF/portlet.xml` and
  `WEB-INF/liferay-portlet.xml` descriptors.

+$$$

**Note:** To see the output of the Liferay WAB Generator, you can override the
following properties:

    module.framework.web.generator.generated.wabs.store=false
    module.framework.web.generator.generated.wabs.store.dir=${module.framework.base.dir}/wabs

You can learn more about these properties in the
[Module Framework Web Application Bundles](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles)
properties section.

$$$



- `META-INF/`
    - `MANIFEST.MF` (OSGi-ready)
- `OSGI-INF/`
    - Declarative Services descriptors (prepared by `bndlib`)
- `WEB-INF/`
    - `classes/`
        - Class files and related properties
    - `lib/`
        - JAR dependencies
    - `resources/`
        - CSS, XHTML, PNG or other frontend files
    - `views/`
        - XHTML views
    - `faces-config.xml`
    - `web.xml`

-->
