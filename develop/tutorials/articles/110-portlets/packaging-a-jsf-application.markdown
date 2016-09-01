# Packaging a JSF Application [](id=packaging-a-jsf-application)

Liferay supports developing and deploying JSF portlets on Liferay Portal by
using
[Liferay Faces](https://www.liferay.com/community/liferay-projects/liferay-faces/overview).
Liferay Faces provides a bridge that supports deploying JSF web applications as
portlets on Liferay 7.0. Liferay Faces Bridge makes developing JSF portlets as
similar as possible to JSF web app development. In this section of tutorials,
you'll learn the portlet development process and how to use JSF in Liferay 7.
For a comprehensive demo for the JSF component suite, visit the
[Liferay Faces Showcase](http://www.liferayfaces.org/web/guest/showcase).

Developers creating portlets for Liferay 7.0 typically have the choice of
packaging their portlets as Java EE style Web Application ARchive (WAR)
artifacts or as Java ARchive (JAR) OSGi bundle artifacts. JSF portlet
developers, however, must package their portlets as WAR artifacts because the
JSF framework expects a WAR layout and often requires the
`WEB-INF/faces-config.xml` descriptor and other Java EE resources such as the
`WEB-INF/web.xml` descriptor. In this tutorial, you'll learn two ways to package
a JSF portlet so it can be treated as an OSGi module at runtime, and the pros
and cons behind both.

Liferay 7.0 supports the OSGi Web Application Bundle (WAB) standard for
deployment of Java EE style WARs. Simply put, a WAB is an archive that has a WAR
layout and contains a `META-INF/MANIFEST.MF` file with the `Bundle-SymbolicName`
OSGi directive.

Enabling WABs to run as OSGi modules at runtime is made possible by the Liferay
[WAB Extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-extender).
You can either let the Liferay WAB Generator convert your WAR artifact to a WAB
at deployment time, or set up the build system of your WAR project to create a
pre-configured WAB with an OSGi-ready `META-INF/MANIFEST.MF`. In both cases, the
source of the project has a WAR layout and the artifact filename may end with
the either the `.jar` or `.war` extension.

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

First, you'll learn about the auto-generated WAB.

## Auto-Generated WAB [](id=auto-generated-wab)

The source of an auto-generated JSF Portlet WAB follows the standard
directory layout for WAR projects.

- `src/`
    - `main/`
        - `java/`
        - `webapp/`
            - `WEB-INF/`
                - `faces-config.xml`
                - `liferay-display.xml` (no longer needed if using `@Component`)
                - `liferay-plugin-package.properties`
                - `liferay-portlet.xml` (no longer needed if using `@Component`)
                - `portlet.xml` (no longer needed if using `@Component`)
                - `web.xml`

BND directives for the manifest can be placed in the
`liferay-plugin-package.properties` file. For use of Declarative Services in
your JSF portlet, a Java class that extends
[GenericFacesPortlet](http://myfaces.apache.org/portlet-bridge/2.0/api/apidocs/javax/portlet/faces/GenericFacesPortlet.html)
must be included and annotated with `@Component` on the class and `@Reference`
on a method to inject a service. You'll notice from the directory layout above
that the `liferay-display.xml`, `liferay-portlet.xml`, `portlet.xml` are no
longer needed, as they were in previous versions of Liferay, if annotating a
class that extends `GenericFacesPortlet` with `@Component`.

**Benefits:**

- Processed by the Liferay
  [WAB Generator](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-generator),
  which automatically creates an OSGi-ready `META-INF/MANIFEST.MF`.
- Processed by the Liferay auto-deploy process, which adds the
  `PluginContextListener` to the `WEB-INF/web.xml` descriptor when the WAB has
  not been pre-configured.
- Can affect the content of `META-INF/MANIFEST.MF` by putting BND directives and
  OSGi headers into the `WEB-INF/liferay-plugin-package.properties` file.
- Can use the `@Component` annotation's property attribute and remove the need
  for the `liferay-display.xml`, `liferay-portlet.xml`, and the `portlet.xml`
  files.
- Can use the `@Reference` annotation to use Declarative Services.

**Drawbacks:**

- Cannot supply a `bnd.bnd` file and cannot utilize a build-time plugin such as
  the
  [bnd-maven-plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html).

+$$$

**Note:** To see the output of the Liferay WAB Generator, you can override the
following properties:

    module.framework.web.generator.generated.wabs.store=true
    module.framework.web.generator.generated.wabs.store.dir=${module.framework.base.dir}/wabs

You can learn more about these properties in the
[Module Framework Web Application Bundles](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles)
properties section.

$$$

Next, you'll discover how to manually configure a WAB.

## Pre-Configured WAB [](id=pre-configured-wab)

Pre-configuring a WAB is not supported; however, if you prefer bypassing the
Liferay auto-deploy process and WAB Generator, you should be aware of the
differences in the project's configuration.

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

**Drawbacks:**

- This is an approach that is not supported by your Liferay DXP subscription.
- Bypasses the Liferay auto-deploy process and the WAB Generator.
- Developers must have the `WEB-INF/web.xml` descriptor fully ready for
  deployment with a `<listener>` for Liferay's `PluginContextListener`.
- The `Import-Package` and `Bundle-ClassPath` sections of the
  `META-INF/MANIFEST.MF` will likely be more difficult to configure since the
  `Import-Package` header must be specified by the developer.

Next, you'll learn how to utilize OSGi services from your WAB.

## Utilizing OSGi Services [](id=utilizing-osgi-services)

Both approaches can use custom-built OSGi services, since both result in the
deployment of a WAB (a true OSGi module). Also, both approaches can be used to
take advantage of OSGi Declarative Services via the `@Reference` annotation:

	private UserLocalService userLocalService;

	@Reference(service = UserLocalService.class)
	protected void setUserLocalService(UserLocalService userLocalService) {

		logger.debug("setUserLocalService: " + userLocalService.getOSGiServiceIdentifier());
		this.userLocalService = userLocalService;

	}

A JSF-friendly lookup of an OSGi service can be done by using the JSF Expression
Language (EL):

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

Or you could more directly use the `facesContext` with the Liferay Faces Bridge
as follows:

    UserLocalService userLocalService = (UserLocalService) facesContext.getExternalContext().getApplicationMap().get("userLocalService");

This approach will only work if the class that extends `GenericFacesPortlet`
(annotated with `@Component`) saves the reference to the injected service as a
`PortletContext` attribute. See the
[PrimeFacesUsersPortlet](https://github.com/liferay/liferay-faces-portal/blob/master/demo/primefaces-users-portlet/src/main/java/com/liferay/faces/demos/portlet/PrimeFacesUsersPortlet.java)
class for an example. Using the `@Reference` annotation, however, allows the
portlet to enjoy the full OSGi lifecycle, so that the portlet will not be
available for use if the required service is not available.

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

Excellent! You've learned how to let the Liferay WAB Generator configure your
JSF application as a WAB.

## Related Topics [](id=related-topics)

[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)

[From Liferay 6 to Liferay 7](/develop/tutorials/-/knowledge_base/7-0/from-liferay-6-to-liferay-7)
