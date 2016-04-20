# Packaging a JSF Application

Developers creating portlets for Liferay 7.0 typically have the choice of
packaging their portlets as Java EE style WAR artifacts or as a JAR OSGi bundle.
JSF portlet developers, however, must package their portlets as WAR artifacts
because the JSF framework expects a WAR layout and often requires the
`WEB-INF/faces-config.xml` descriptor and other Java EE resources such as the
`WEB-INF/web.xml` descriptor. In this tutorial, you'll learn two ways to package
a JSF portlet so it can be treated as an OSGi module at runtime, and the pros
and cons behind both.

Although the term *WAR* sounds like a non-OSGi style plugin, there are certain
steps that can be taken to ensure a WAR plugin is still treated as an OSGi
module during runtime. You can create your portlet as an OSGi Web Application
Bundle (WAB), which follows a WAR layout and ends with the usual `.war`
extension. A WAB is an OSGi bundle version of a WAR file. Moreover, it's a
portlet organized in a WAR-shaped layout that includes OSGi manifest entries.

Liferay 7.0 supports the WAB standard for deployment of Java EE style WARs.
In detail, Liferay implements the
[WAB Extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-extender),
which enables WABs to run as OSGi modules at runtime. You must format your WAB
so that it is recognized by the WAB Extender and properly deployed into the OSGi
container.

To get an idea of how a JSF WAB is organized, an example JSF WAB portlet anatomy
structure is outlined below:

- `META-INF`
    - `MANIFEST.MF` (OSGi-ready)
- `OSGI-INF`
    - Declarative Services descriptors (prepared by `bndlib`)
- `WEB-INF`
    - `classes`
        - Class files and related properties
    - `lib`
        - JAR dependencies
    - `resources`
        - CSS, XHTML, PNG or other frontend files
    - `views`
        - XHTML views
    - `faces-config.xml`
    - `web.xml`

To ensure your WAR is recognized by the Liferay's WAB extender, you'll need to
make sure you're including the `META-INF/MANIFEST.MF` file. You can either
include a `Bundle-SymbolicName` OSGi directive in this file or refrain from
including it. Both ways work, but there are differing benefits and drawbacks for
why to include/not include this directive.

## Including the Bundle-SymbolicName OSGi Directive in Your WAB

**Benefits:**

- Can supply a `bnd.bnd` file and utilize a build-time plugin (e.g.,
  [bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html))
  to affect the content of an OSGi-ready `META-INF/MANIFEST.MF`.
- Can take advantage of OSGi Declarative Services (DS)
- Provides a class that extends.
  [GenericPortlet](http://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/GenericPortlet.html)
  (or in the case of JSF,
  [GenericFacesPortlet](http://myfaces.apache.org/portlet-bridge/2.0/api/apidocs/javax/portlet/faces/GenericFacesPortlet.html))
  and specify portlet attributes via the `@Component` annotation, rather than
  using two separate descriptors: `WEB-INF/portlet.xml` and
  `WEB-INF/liferay-portlet.xml`.

**Drawbacks:**

- The `bnd.bnd` file must include embedded dependency information that is
  already present in the `pom.xml`.
- Bypasses the Liferay auto-deploy process, which means developers must have
  the `WEB-INF/web.xml` descriptor fully ready for deployment, such as
  `<servlet>` and `<servlet-mapping>` for Liferay's `PortletServlet` and a
  `<listener>` for Liferay's `PluginContextListener`.

Here's an example `web.xml` descriptor you could use that is ready for
deployment:

    <?xml version="1.0"?>

    <web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
        <display-name>/jsf.applicant-4.0.0-SNAPSHOT</display-name>
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
        <!-- Listens for PluginContext portal lifecycle changes. -->
        <listener>
            <listener-class>com.liferay.portal.kernel.servlet.PluginContextListener</listener-class>
        </listener>
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
        <!-- This servlet is required to serve the portlet. -->
        <servlet>
            <servlet-name>Portlet Servlet</servlet-name>
            <servlet-class>com.liferay.portal.kernel.servlet.PortletServlet</servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>Portlet Servlet</servlet-name>
            <url-pattern>/portlet-servlet/*</url-pattern>
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

## Excluding the Bundle-SymbolicName OSGi Directive in Your WAB

**Benefits:**

- Processed by the Liferay auto-deploy process, which adds the aforementioned
  `PortletServlet` and `PluginContextListener`.
- Processed by the Liferay
  [WAB Generator](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-generator),
  which automatically creates an OSGi-ready `META-INF/MANIFEST.MF`.
- Can affect the content of `META-INF/MANIFEST.MF` by putting BND directives and
  OSGi headers into the `WEB-INF/liferay-plugin/packages.properties` file.

**Drawbacks:**

- Can't supply the `bnd.bnd` and can't utilize a build-time plugin such as the
  [bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html).
- Can't take advantage of OSGi Declarative Services (DS)
- Developer must specify the `WEB-INF/portlet.xml` and
  `WEB-INF/liferay-portlet.xml` descriptors.

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

When including the `Bundle-SymbolicName` OSGi directive, the auto-deploy process
is bypassed, so you must manually add the appropriate `<servlet>`, `<listener>`,
etc. tags to the `src/main/webapp/WEB-INF/web.xml` descriptor. By having a
ready-to-deploy `web.xml` in the source of your project, there is no need to
expand the WAR yourself.

When excluding the `Bundle-SymbolicName` OSGi directive, you can override the
following properties to see the results of the WAB Generator:

    module.framework.web.generator.generated.wabs.store=false
    module.framework.web.generator.generated.wabs.store.dir=${module.framework.base.dir}/wabs

You can learn more about these properties in the
[Module Framework Web Application Bundles](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles)
properties section.

Excellent! Now you know how to package your JSF application as a WAB, are
informed about whether you'd like to include the `Bundle-SymbolicName` OSGi
directive in your project, and can successfully deploy your application.

## Related Topics [](id=related-topics)

[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)

[Creating A Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)

[Migrating A Legacy Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-legacy-application)
