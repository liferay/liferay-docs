# Packaging a JSF Application [](id=packaging-a-jsf-application)

Developers creating portlets for Liferay 7.0 typically have the choice of
packaging their portlets as Java EE style Web Application ARchive (WAR)
artifacts or as Java ARchive (JAR) OSGi bundle artifacts. JSF portlet
developers, however, must package their portlets as WAR artifacts because the
JSF framework expects a WAR layout and often requires the
`WEB-INF/faces-config.xml` descriptor and other Java EE resources such as the
`WEB-INF/web.xml` descriptor. In this tutorial, you'll learn how to package
a JSF portlet so it can be treated as an OSGi module at runtime, and the pros
and cons behind JSF-specific OSGi packaging practices when deploying to Liferay.

Liferay 7.0 supports the OSGi Web Application Bundle (WAB) standard for
deployment of Java EE style WARs. Simply put, a WAB is an archive that has a WAR
layout and contains a `META-INF/MANIFEST.MF` file with the `Bundle-SymbolicName`
OSGi directive.

Enabling WABs to run as OSGi modules at runtime is made possible by the Liferay
[WAB Extender](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-extender).
The Liferay WAB Generator converts your WAR artifact to a WAB at deployment
time. The source of the project has a WAR layout and the artifact filename may
end with either the `.jar` or `.war` extension.

To get an idea of how a JSF WAR artifact is packaged for use as a WAB, an
example anatomy structure is outlined below:

- `META-INF/`
    - `MANIFEST.MF` (Made OSGi-ready at deploy time via the WAB Generator)
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
    - `liferay-plugin-package.properties`
    - `liferay-portlet.xml`
    - `portlet.xml`
    - `web.xml`

Next, you'll learn how the WAB Generator auto-generates your JSF portlet.

## Auto-Generated WAB [](id=auto-generated-wab)

The source of an auto-generated JSF Portlet WAB follows the standard folder
layout for WAR projects.

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

The Liferay WAB Generator automatically creates an OSGi-ready
`META-INF/MANIFEST.MF` file. If you want to modify the contents of the manifest
file, add additional BND directives for the manifest in the
`liferay-plugin-package.properties` file.

**Benefits:**

- Processed by the Liferay
  [WAB Generator](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/portal-osgi-web/portal-osgi-web-wab-generator),
  which automatically creates an OSGi-ready `META-INF/MANIFEST.MF`.
- Processed by the Liferay auto-deploy process, which adds the
  `PluginContextListener` to the `WEB-INF/web.xml` descriptor when the WAB has
  not been pre-configured.
- Can affect the content of `META-INF/MANIFEST.MF` by putting BND directives and
  OSGi headers into the `WEB-INF/liferay-plugin-package.properties` file.

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

Using the WAB Generator is the only Liferay supported way to develop JSF
portlets deployed to @product@.

Excellent! Now you can make an informed packaging decision for your JSF
application.

## Related Topics [](id=related-topics)

[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)

[Creating A Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)

[Migrating A Liferay 6 Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-liferay-6-application)
