# Understanding Liferay's Module Framework [](id=understanding-liferays-module-framework)

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
module framework and not only supports, but also encourages the development of
plugins as modules. Moreover, much of Liferay's core functionality has been
extracted into modules and installed using Liferay's module framework. Read on
to learn how to create Liferay 7 applications and how to manage Liferay's module
framework.

## Creating Liferay Plugins [](id=creating-liferay-plugins)

Prior to Liferay 7, Liferay supported several different types of plugins:

- Portlets
- Layout Templates
- Themes
- Hooks
- Ext plugins
- Web plugins

Liferay 7 supports only one type of plugin: a bundle. Don't worry, Liferay 7 is
backwards compatible with the older plugin types. You can deploy one of the
older plugin types to Liferay, and it's automatically converted to a bundle
and installed into the module framework. But where does the term *bundle* come
from? Liferay's module framework is an OSGi framework. In OSGi, *bundles* are
deployable JAR files. They are often referred to as WAB (web application bundle)
files. An OSGi bundle is simply a group of Java classes, additional resources
(optionally), and a `MANIFEST.MF` file, packaged together as a JAR file. You
need not be familiar with OSGi to create plugins for Liferay 7. We'll explain
all the steps you need to take.

Liferay supports three mechanisms for developing and installing plugins into
its module framework. There are pros and cons to each approach:

- WAB from traditional Liferay plugin
    - This form is exactly the output of the traditional Liferay SDK: a WAR file
      which is converted to a WAB file and then deployed into the module
      framework.
    - Pros:
        - Path of least resistance to deliver existing plugins on Liferay
        - Uses traditional descriptors like `portlet.xml`,
          `liferay-portlet.xml`, `web.xml`, etc.
        - Liferay exports Service Builder beans as OSGi services transparently
        - Portlets can be enhanced by other modules that target portlet
          integration points (using the FQN of the portlet(s) in the WAB)
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

- Pre-crafted WAB
    - This form comes from having a project structure identical to the
        traditional Liferay plugin, but asking the build system to create an
        OSGi bundle (jar) directly using BND’s wab statements.
    - Pros:
        - Avoids the runtime analysis required to produce OSGi metadata and
          artifact conversion of WAR files
        - Uses traditional descriptors like `portlet.xml`,
          `liferay-portlet.xml`, `web.xml`, etc.
        - Can use Service Builder's OSGi mode which will produce SB services
          friendlier to OSGi.
        - Possible to access OSGi services through a `BundleContext` object,
          which can be obtained through either of these methods:

        BundleContext bundleContext = (BundleContext)servletContext.getAttribute(
        "osgi-bundlecontext");
    (see OSGi R5 Enterprise 128.6.1)

        Bundle bundle = org.osgi.framework.FrameworkUtil.getBundle(SomeClassInMyWar.class);
        BundleContext bundleContext = bundle.getBundleContext();

    - Cons:
        - More complex to produce because more of the metadata analysis must be
          done manually

- OSGi Service (a.k.a. Component Portlet)
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

## Using the Felix Gogo Shell [](id=using-the-felix-gogo-shell)

To interact with Liferay's module framework, you can use Felix Gogo shell. To
access it, use a telnet client to connect to port `11311` of your Liferay
server's machine. Since the shell is accessible through telnet, it is only
available on the local network interface. Use the following command if you're
running Liferay locally:

    telnet localhost 11311

Once you connect, you should see a message that says *Welcome to Apache Felix
Gogo*. The prompt looks like this:

    g!

Here are some useful Gogo shell commands:

`help`: lists all the available Gogo shell commands. Notice that each command
has two parts to its name, separated by a colon. For example, the full name of
the `help` command is `felix:help`. The first part is the command scope while
the second part is the command function. The scope allows commands with the same
name to be disambiguated. E.g., scope allows the `felix:refresh` command to be
distinguished from the `equinox:refresh` command.

`help [command name]`: lists information about a specific command including a
description of the command, the scope of the command, and information about any
flags or parameters that can be supplied when invoking the command.

`lb`: lists all of the bundles installed in Liferay's module framework. Use
the `-s` flag to list the bundles using the bundles' symbolic names.

`b [bundle ID]`: lists information about a specific bundle including the
bundle's symbolic name, bundle ID, data root, registered (provided) and used
services, imported and exported packages, and more

`headers [bundle ID]`: lists metadata about the bundle from the bundle's
`MANIFEST.MF` file

`diag [bundle ID]`: lists information about why the specified bundle is not
working (e.g., unresolved dependencies, etc.)

`packages [package name]`: lists all of the named package's dependencies 

`scr:list`: lists all of the components registered in the module framework.
(*scr* stands for service component runtime.)

`services`: lists all of the services that have been registered in Liferay's
module framework.

`install [path to JAR file]`: installs the specified bundle into Liferay's
module framework

`start [bundle ID]`: starts the specified bundle

`stop [bundle ID]`: stops the specified bundle

`uninstall [bundle ID]`: uninstalls the specified bundle from Liferay's module
framework

For more information about the Gogo shell, please visit [http://felix.apache.org/documentation/subprojects/apache-felix-gogo.html](http://felix.apache.org/documentation/subprojects/apache-felix-gogo.html).

## Using the Felix Web Console [](id=using-the-felix-web-console)

Another way to interact with Liferay's module framework is via the Felix Web
Console. To access Liferay's Felix Web Console if you're running Liferay
locally, visit
[http://localhost:8080/o/system/console/bundles](http://localhost:8080/o/system/console/bundles).

By default, you'll see a list of all the bundles installed in Liferay's module
framework. You can expand each bundle entry by clicking on the arrow next to the
bundle name to view details about each bundle.

These details include the following information about each bundle (this is not
an exhaustive list):

- its symbolic name
- its version 
- its location on the machine running Liferay
- the date/time it was last modified
- imported and exported packages and their versions
- used and provided services and their IDs

This information can be very useful when developing Liferay bundles. For
example, suppose you get an error like this during development:

    org.osgi.framework.BundleException: Could not resolve module: your.bundle.symbolic.name [230] Unresolved requirement: Import-Package: com.liferay.bookmarks.service; version="[1.0.0,2.0.0)" [Sanitized]

This error means that your bundle could not import a required package. Your
bundle requires a version of the `com.liferay.bookmarks.service` package with a
version greater than or equal to `1.0.0` but less than `2.0.0`. But no bundle
registered in Liferay's module framework exports such a package.

If you know of a bundle that exports the package you require, you can use the
Felix Web Console to check the version of the package that's exported. For
example, the `com.liferay.bookmarks.api` bundle exports the
`com.liferay.bookmarks.service` package. So you could expand the bundle entry
for the Liferay Bookmarks API bundle and check the list of exported packages for
the `com.liferay.bookmarks.service` package and find the version of the package
that's exported. Then you could update your bundle's dependency on the package
to use the correct version of the exported package.

With the Felix Web Console, you can not only view all the installed bundles
in Liferay's module framework but also all the registered services. To view
the services registered on your locally running Liferay instance, visit
[http://localhost:8080/o/system/console](http://localhost:8080/o/system/console).
For each service, you can see the service ID, the service type, and the bundle
providing the service. The service type is the name of the interface the service
implements and under which it's registered. (Services can implement and register
themselves under multiple interfaces.)

You can expand each registered service by clicking on the arrow next to the
service name to view details about each service. These details include the
following information about each service (this is not an exhaustive list):

- The name of the service bean
- The symbolic name of the bundle providing the service
- The version of the bundle providing the service
- The bundles using the service (if any)
- The JSON web service context name (if applicable)
- The JSON web service context path (if applicable)

This information can be very useful when developing Liferay bundles that consume
or provide services.

Liferay's Felix Web Console allows you to manage more than just bundles and
services (although these management tools are probably its most important
features). Explore the Web Console's navigation menu to find additional tools
such as Configuration, Log Service, Licenses, and System Information.

<!-- This was great, Jesse! I think, however, that some screenshots are in
order for the web console, to illustrate what you mention in the text. -Rich -->

## Related Topics [](id=related-topics)

[Creating a Simple Bundle](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle)
