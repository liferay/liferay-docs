# Creating Liferay Modules

At a basic level, Liferay Portal is a web application that runs on a Java EE
application server or servlet container. In Liferay 6.2 and prior versions,
Liferay plugins were deployed as separate web applications. Thus, both Liferay
and its plugins were installed alongside each other in the running
application server or servlet container. However, Liferay has steadily been
integrating a module framework into its core. Liferay does not itself run inside
a module framework; rather, it hosts its own module framework.

Liferay 6.2 included a module framework that provided limited functionality. In
Liferay 6.2, although plugins *could* be developed and installed as modules,
this method was not recommended or supported. Liferay 7 includes a more mature
module framework and encourages the development of plugins as modules. Moreover,
much of Liferay's core functionality has been extracted into modules and
installed using Liferay's module framework. Read on to learn how to create
Liferay 7 applications and how to manage Liferay's module framework.

Prior to Liferay 7, Liferay supported several different types of plugins:

- Portlets
- Layout Templates
- Themes
- Hooks
- Ext plugins
- Web plugins

Liferay 7 supports only one type of plugin: a module (a.k.a. bundle). Don't
worry, Liferay 7 is backwards compatible with the older plugin types. You can
deploy one of the older plugin types to Liferay, and it's automatically
converted to one or more modules and installed into the module framework. A
module is simply a group of Java classes, additional resources (optionally), and
a `MANIFEST.MF` file, packaged together as a JAR file. You need not be familiar
with OSGi to create plugins for Liferay 7. We'll explain all the steps you need
to take.

Liferay supports three mechanisms for developing and installing plugins into
its module framework. There are pros and cons to each approach:

- Create a Module or Modules from a Traditional Liferay Plugin
    - This method converts the output of the traditional Liferay SDK, a WAR
      file, into one or more modules which are deployed into the module
      framework.
    - Pros:
        - Represents the path of least resistance for delivering existing
          plugins to Liferay
        - Uses traditional descriptors like `portlet.xml`,
          `liferay-portlet.xml`, `web.xml`, etc.
        - Liferay exports Service Builder beans as components transparently
        - Portlets can be enhanced by other modules that target portlet
          integration points (using the FQN of the portlet(s) in the module)
        - Corrects some of the issues with sharing of Service Builder services
          between plugins
        - Possible to access OSGi services through a `BundleContext` object,
          which can be obtained through either of these methods:

        BundleContext bundleContext = (BundleContext)servletContext.getAttribute(
        "osgi-bundlecontext");
    (see OSGi R5 Enterprise 128.6.1)

        Bundle bundle = org.osgi.framework.FrameworkUtil.getBundle(SomeClassInMyWar.class);
        BundleContext bundleContext = bundle.getBundleContext();

    - Cons:
        - OSGi service accesses render the plugin incompatible with non-OSGi app
          servers or servlet containers

- Pre-crafted Module
    - This form comes from having a project structure identical to the
        traditional Liferay plugin, but asking the build system to create an
        OSGi bundle (jar) directly using BND's wab statements.
    - Pros:
        - Avoids the runtime analysis required to produce OSGi metadata and
          artifact conversion of WAR files
        - Uses traditional descriptors like `portlet.xml`,
          `liferay-portlet.xml`, `web.xml`, etc.
        - Can use Service Builder's OSGi mode which will produce SB services
          friendlier to OSGi.
        - Possible to access OSGi services through a `BundleContext` object,
          which can be obtained through either of the two methods mentioned
          in the previous section.

    - Cons:
        - More complex to produce because more of the metadata analysis must be
          done manually

- Component Portlet Module (a.k.a., OSGi Service Module)
    - This form virtually eliminates all JavaEE-isms from portlet development
      and results in almost pure OSGi bundles (JSP support is provided
      seamlessly).
    - Pros:
        - Lowest amount of boilerplate code
        - Complete OSGi dynamics
        - Promotes ultimate composability
        - No XML in most cases
    - Cons:
        - Only supported by Liferay's Module Framework

## Related Topics [](id=related-topics)

[Creating a Simple Bundle](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle)
