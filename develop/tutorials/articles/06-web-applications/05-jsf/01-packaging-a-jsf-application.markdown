# Packaging a JSF Application [](id=packaging-a-jsf-application)

Developers creating portlets for Liferay 7.0 typically have the choice of
packaging their portlets as Java EE style Web Application ARchive (WAR)
artifacts or as Java ARchive (JAR) OSGi bundle artifacts. JSF portlet
developers, however, must package their portlets as WAR artifacts because the
JSF framework expects a WAR layout and often requires the
`WEB-INF/faces-config.xml` descriptor and other Java EE resources such as the
`WEB-INF/web.xml` descriptor. In this tutorial, you'll learn two ways to package
a JSF portlet so it can be treated as an OSGi module at runtime, and the pros
and cons behind both.

Liferay 7.0 supports the OSGi WAB standard for deployment of Java EE style WARs.
Simply put, a WAB is an archive that that has a WAR layout and contains a
`META-INF/MANIFEST.MF` file with the `Bundle-SymbolicName` OSGi directive.

Enabling WABs to run as OSGi modules at runtime is made possible by the Liferay
[WAB Extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-extender).
You can either setup the build system of your WAR project to create a
pre-configured WAB, or let the Liferay WAB Generator convert your WAR artifact
to a WAB at deployment time. In both cases, the source of the project has a WAR
layout and the artifact filename ends with the usual `.war` extension.

To get an idea of how a JSF WAR artifact is packaged for use as a WAB, an
example anatomy structure is outlined below:

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

## Utilizing OSGi Services [](id=utilizing-osgi-services)

Both approaches can use custom-built OSGi services, since both result in the
deployment of a WAB (true OSGi modules). Also, both approaches can be used to
lookup OSGi services, although including the `Bundle-SymbolicName` OSGi
directive allows you to take advantage of OSGi Declarative Services via the
`@Reference` annotation. A JSF-friendly lookup of an OSGi service for both
approaches can be done by using the JSF Expression Language (EL):

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

Then you can write an
[ELResolver](http://docs.oracle.com/javaee/7/api/javax/el/ELResolver.html) that
does the following:

    BundleContext bundleContext = FrameworkUtil.getBundle(getClass()).getBundleContext();
    ServiceReference<?>[] refs = ctx.getServiceReferences(UserService.class.getName(), null);
    userService = (UserService) bundleContext.getService(refs[0]);

The drawback of this technique is that you don't enjoy the full OSGi lifecycle.
In other words, the WAB will be placed in-service whether or not its dependent
services are available. Additionally, it will not go out-of-service when any
dependent services become unavailable.

Lastly, you can still call the traditional Liferay static utility methods (e.g.,
`UserServiceUtil.get*`) in order to call Liferay services in your WABs. Calling
Liferay's static utility methods, however, also means that you won't enjoy the
full OSGi lifecycle.

Once you've packaged your JSF WAB and are ready to deploy, simply copy and paste
your `.war` file to your Liferay instance's `/deploy` folder.

+$$$

**Note:** You can alternatively copy your `.war` file to your Liferay instance's
`/osgi/war` folder. When a `.war` file is copied to the `/deploy` folder,
Liferay copies the WAR artifacts directly to the `/osgi/war`, so you can skip
this system step by copying the `.war` directly to its final destination.

$$$


Excellent! You've learned how to pre-configure your JSF application as a WAB.
But if you would rather let the Liferay WAB Generator do it for you, that's fine
too.

## Related Topics [](id=related-topics)

[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)

[Creating A Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)

[Migrating A Liferay 6 Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-liferay-6-application)
