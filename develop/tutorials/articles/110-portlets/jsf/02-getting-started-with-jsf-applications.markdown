# Getting Started with JSF Applications

This tutorial is not a comprehensive guide for configuring JSF applications in
Liferay. You'll examine the high points, assuming you already have a workable
knowledge with JSF.

How and why is JSF so easy to deploy to Liferay? Isn't JSF a separate
technology? Liferay supports JSF portlets with the use of
[*Liferay Faces*](https://web.liferay.com/community/liferay-projects/liferay-faces/overview),
which is an umbrella project that provides support for the JSF standard.

JSF portlets use 
[*Liferay Faces Bridge*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-bridge),
which supports deploying JSF web applications as portlets on any JSR 286
(Portlet 2.0) compliant portlet container, like Liferay Portal. Liferay
Faces Bridge is applied to a portlet as a dependency in a build file, such as a
`pom.xml`:

    <dependencies>
        ...
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>${liferay.faces.bridge.ext.version}</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.impl</artifactId>
            <version>${liferay.faces.bridge.version}</version>
        </dependency>
        ...
    </dependencies>

There are several UI component suites that a JSF application can use, which include
[*Liferay Faces Alloy*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-alloy],
[*PrimeFaces*](http://primefaces.org/),
[*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf), and
[*RichFaces*](http://richfaces.jboss.org/). Furthermore, you can take advantage
of
[*Liferay Faces Portal*](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-portal)
in order to use Liferay-specific utilities and UI components. These components
can be used by specifying them as dependencies in your build file, as well.

Make sure to configure the `GenericFacesPortlet` class in the `portlet.xml`.
This class handles invocations to your JSF portlet and makes the portlet relying
on Liferay Faces Bridge easier to develop by acting as a turnkey implementation.

    <portlet>
        ...
        <portlet-class>javax.portlet.faces.GenericFacesPortlet</portlet-class>
        ...
    </portlet>

Also, make sure to define a default view file. Specifically, the view file is
configured as an `init-param` in the `portlet.xml` file.

    <init-param>
        <name>javax.portlet.faces.defaultViewId.view</name>
        <value>/WEB-INF/views/view.xhtml</value>
    </init-param>

There are other basic descriptors that you'll need to have reside in your JSF
portlet: `faces-config.xml` and `web.xml`. You can visit the
[Packaging a JSF Application](/develop/tutorials/-/knowledge_base/7-0/packaging-a-jsf-application)
tutorial for more information on where these descriptors should reside in your
JSF portlet structure.

The `faces-config.xml` descriptor serves as a JSF portlet's application
configuration file, which is used to register and configure objects and
navigation rules.

+$$$

**Note:** Many auto-generated `faces-config.xml` files have the following
configuration:

    <lifecycle>
        <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
    </lifecycle>

This configures your JSF portlet to log the before/after phases of the JSF
lifecycle to your console in debug mode. You might want to remove this
declaration before deploying to production.

$$$

The `web.xml` file serves as a deployment descriptor that provides necessary
configurations for your JSF portlet to successfully deploy and function in
@product@. A sample `web.xml` for a JSF portlet is given below, with
explanations for each element documented in-line:

    <?xml version="1.0"?>

    <web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
        <display-name>/jsf.test.portlet</display-name>
        <!-- Sets the resources directory inside the WEB-INF folder, which means your resources are secure from non-JSF calls.  -->
        <context-param>
            <param-name>javax.faces.WEBAPP_RESOURCES_DIRECTORY</param-name>
            <param-value>/WEB-INF/resources</param-value>
        </context-param>
        <!-- Instruct Mojarra to namespace parameters according to NamingContainer rules. -->
        <context-param>
            <param-name>com.sun.faces.namespaceParameters</param-name>
            <param-value>true</param-value>
        </context-param>
        <!-- Although the FacesServlet will not be invoked by any portlet requests, it is required to initialize JSF. -->
        <servlet>
            <servlet-name>FacesServlet</servlet-name>
            <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <!-- MyFaces will not initialize unless a servlet-mapping to the Faces Servlet is present. -->
        <servlet-mapping>
            <servlet-name>Faces Servlet</servlet-name>
            <url-pattern>*.xhtml</url-pattern>
        </servlet-mapping>
        <!-- Prevent direct access to Facelet XHTML -->
        <security-constraint>
            <web-resource-collection>
                <web-resource-name>Facelet XHTML</web-resource-name>
                <url-pattern>*.xhtml</url-pattern>
            </web-resource-collection>
            <auth-constraint/>
        </security-constraint>
    </web-app>

JSF portlets also use Liferay descriptors, which you can further learn about in
the
[Liferay Descriptors](/develop/tutorials/-/knowledge_base/7-0/spring-mvc#liferay-descriptors)
sub-section.

Service Builder works the same in a JSF portlet as it would in any other
standard WAR-style MVC portlet. To learn more about how Service Builder works in
Liferay, visit the
[Business Logic and Data Access](/develop/tutorials/-/knowledge_base/7-0/business-logic-and-data-access)
tutorials. You can create a `service.xml` file and run Service Builder in your
JSF project to build services.

To call OSGi-based Service Builder services from your JSF portlet, you need a
mechanism that gives you access to the OSGi service registry. You can't look up
services published to the OSGi runtime using Declarative Services. Instead, you
should open a
[ServiceTracker](https://osgi.org/javadoc/r6/core/org/osgi/util/tracker/ServiceTracker.html)
when you want to call a service that's in the OSGi service registry.

To implement a service tracker in your JSF portlet, you can add a type-safe
wrapper class that extends `org.osgi.util.tracker.ServiceTracker`. For example,
this is done in a demo JSF portlet as follows:

    public class UserLocalServiceTracker extends ServiceTracker<UserLocalService, UserLocalService> {

        public UserLocalServiceTracker(BundleContext bundleContext) {
            super(bundleContext, UserLocalService.class, null);
        }
    }

After extending the `ServiceTracker`, just call the constructor and the service
tracker is ready to use in your managed bean.

In a managed bean, whenever you need to call a service, open the service
tracker. For example, this is done in the same demo JSF portlet to open the
service tracker, using the
[@PostContruct](http://docs.oracle.com/javaee/7/api/javax/annotation/PostConstruct.html)
annotation:

    @PostConstruct
    public void postConstruct() {
        Bundle bundle = FrameworkUtil.getBundle(this.getClass());
        BundleContext bundleContext = bundle.getBundleContext();
        userLocalServiceTracker = new UserLocalServiceTracker(bundleContext);
        userLocalServiceTracker.open();
    }

Then the service can be called:

    UserLocalService userLocalService = userLocalServiceTracker.getService();
    ...

    userLocalService.updateUser(user);

When it's time for the managed bean to go out of scope, you must close the
service tracker using the
[@PreDestroy](http://docs.oracle.com/javaee/7/api/javax/annotation/PreDestroy.html)
annotation:

    @PreDestroy
    public void preDestroy() {
        userLocalServiceTracker.close();
    }

For more information on service trackers and how to use them in WAR-style
portlets, see the
[Service Trackers](/develop/tutorials/-/knowledge_base/7-0/spring-mvc#service-trackers)
sub-section.

Hopefully this introductory tutorial for JSF portlets developed for Liferay 7 is
helpful for a big-picture look on the JSF technology developed for @product@. To
learn more in-depth concepts and configurations for JSF portlets, visit the
other tutorials contained in the
[JSF Portlets with Liferay Faces](/develop/tutorials/-/knowledge_base/7-0/jsf-portlets-with-liferay-faces)
tutorials set.

## Related Topics

[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)
