# Understanding Liferay's Module Framework [](id=understanding-liferays-module-framework)

At a basic level, Liferay Portal is a web application that runs on a Java EE
application server or servlet container. In Liferay Portal 6.2 and prior versions,
Liferay plugins were deployed as separate web applications. Thus, both Liferay
and its plugins were installed alongside each other in the running application
server or servlet container. @product-ver@ introduces a major improvement: an
OSGi-based module framework to which plugins are deployed. Liferay's module
framework makes it easier than ever for developers to create Liferay
applications and to customize Liferay!

+$$$

**Note:** Liferay Portal 6.2 included a module framework that provided limited
functionality. Although plugins *could* be developed and installed as modules,
this method was not recommended or supported. @product-ver@ includes a more mature
module framework and developers are encouraged to create modules. Moreover, much
of Liferay's core functionality has been extracted into modules and installed
into Liferay's module framework.

$$$

As a developer, you benefit from Liferay's module framework in many ways:

- Liferay's module framework makes it easier than ever to manage dependencies.
  Individual modules export code at the package level for use by other modules.

- Liferay modules hide their internals from other modules. Since the internals
  are hidden from other modules, developers are free to change the internals
  whenever needed.

- Modules publish services to and consume services from a service registry.
  Service contracts are loosely coupled from service providers and service
  consumers.

- Liferay's module framework dynamically manages module lifecycles. Modules can
  be installed, started, updated, stopped, and uninstalled while Liferay is
  running. This makes it very easy for Liferay developers to update their
  plugins and check their functionality.

- Deployment is easy and straightforward, and the process doesn't vary according
  to application server or servlet container.

- Liferay modules are carefully versioned and explicitly declare dependencies on
  specific versions of other modules. This solves the problem of having two
  applications that depend on different versions of the same library. In
  Liferay's module framework, the two applications can each depend on their own
  versions of the library.

- Liferay's module framework is small, fast, and secure.

- Liferay's module framework is OSGi-based. If you're already using OSGi in the
  context of another project such as Eclipse, NetBeans, IntelliJ, GlassFish,
  JBoss, JOnAS, or JIRA, you can apply your knowledge to Liferay.

@product-ver@ still supports the deployment of WAR-style plugins, but its underlying
implementation converts them into one or more modules that are installed into
the module framework. Thus, there are two basic mechanisms that @product-ver@
supports for developing and deploying modules into its module framework:

1. Create a @product-ver@ style plugin. A @product-ver@ style plugin is called a
   *module*. Architecturally, a module is a JAR file. When a module is deployed,
   Liferay installs it into its module framework. Liferay modules are *only*
   supported by Liferay's module framework. This is the recommended approach for
   new application development.

2. Create a traditional Liferay Portal 6.2 style plugin. Architecturally, a 6.2 style
   plugin is a WAR file. This is the output of the traditional Liferay Plugins
   SDK. This approach is still fully supported and allows for backwards
   compatibility both with Liferay and the JSR-286 portlet standard. When a 6.2
   style plugin is deployed, Liferay converts the WAR file into one or more
   modules which are deployed into Liferay's module framework. This approach
   represents the path of least resistance for delivering existing plugins to
   @product-ver@.

If you want to develop a Liferay Portal 6.2 style plugin, please see the
[Liferay Portal 6.2 tutorials](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/tutorials).
If you want to develop a @product-ver@ style plugin, you're in the right
place. You need not be familiar with OSGi to create applications for @product-ver@.
You only need to understand two concepts: *modules* and *components*.

- A *module* is the one and only type of @product-ver@ plugin. Architecturally, a
  module is simply a group of Java classes, additional optional resources,
  and a `MANIFEST.MF` file, packaged together as a JAR file. Liferay
  applications usually consist of at least two or three modules. Note that
  modules are sometimes called
  *[bundles](https://en.wikipedia.org/wiki/OSGi#Bundles)* in the OSGi world. A
  module can contain one or more components. 
- A *component* is just an implementation of an interface. In
  Liferay, a component is typically a [Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services) component. This
  means that it's a Java class that's decorated with the
  `org.osgi.service.component.annotations.Component` annotation. You specify the interface
  to implement by adding a `service` attribute to the annotation. A component
  lives within a module.

+$$$

**Important:** Don't confuse the terms *service* and *component*. A Liferay
*service* typically means a Liferay API. A *component* is an OSGi service, not a
Liferay service. That is, a component is registered in the service registry of
Liferay's OSGI-based module framework. A component does not necessarily have
anything do with Liferay's API.

$$$

Liferay's new development model gives developers more flexibility than before.
All components are loosely coupled. The lifecycle of modules and components is
managed by Liferay's OSGi-based module framework. @product-ver@ applications should
be designed as a compilation of lightweight components.

The flexibility doesn't end there, though. Previously, Liferay development had
to be done using Liferay's Plugins SDK. This is no longer the case. Liferay
developers can now create standalone module projects as long as the modules are
compatible with Liferay's module framework. Liferay hasn't abandoned its own
tooling, though: Liferay's Eclipse-based IDEs (Liferay IDE and Liferay Developer
Studio) can still be used. But now you have options. 

[Blade Tools](https://github.com/gamerson/liferay-blade-tools) is a new Liferay
tool that provides templates for creating a variety of types of modules. It's
the recommended (but not required) way to get started with @product-ver@ application
development. Since it's a command-line tool,
[Blade Tools](https://github.com/gamerson/liferay-blade-tools) can be invoked
directly or from other applications like Liferay IDE or Liferay Developer
Studio.

Although it's no longer required, the Liferay Plugins SDK can still be used to
create @product-ver@ modules. It has been updated for the @product-ver@ release to
support both traditional Liferay development and development using the new
model. 

Read on to learn how to create modules for Liferay Portal!
