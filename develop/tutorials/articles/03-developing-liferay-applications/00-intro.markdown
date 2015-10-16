# Introduction

At a basic level, Liferay Portal is a web application that runs on a Java EE
application server or servlet container. In Liferay 6.2 and prior versions,
Liferay plugins were deployed as separate web applications. Thus, both Liferay
and its plugins were installed alongside each other in the running application
server or servlet container. However, Liferay 7 includes an OSGi-based module
framework to which plugins are deployed.

+$$$

**Note:** Liferay 6.2 included a module framework that provided limited
functionality. In Liferay 6.2, although plugins *could* be developed and
installed as modules, this method was not recommended or supported. Liferay 7
includes a more mature module framework and encourages the development of
plugins as modules. Moreover, much of Liferay's core functionality has been
extracted into modules and installed into Liferay's module framework.

$$$

Liferay 7 supports only one type of plugin: a module (a.k.a. bundle). Don't
worry, Liferay 7 is backwards compatible with all of the older plugin types
(except Ext plugins): portlets, layout templates, themes, hooks, and web
plugins. You can deploy one of the older plugin types to Liferay, and it's
automatically converted into to one or more modules that are installed into the
module framework. Thus, there are two basic mechanisms that Liferay 7 supports
for developing and deploying modules into its module framework:

1. Create a Liferay 7 style plugin. A Liferay 7 style plugin is called a
   *module*. Architecturally, a module is a JAR file. When a module is deployed,
   Liferay installs the module into its module framework. Liferay modules are
   *only* supported by Liferay's module framework. This is the recommended
   approach for new application development.

2. Create a traditional Liferay 6.2 style plugin. Architecturally, a 6.2 style
   plugin is a WAR file. This is the output of the traditional Liferay Plugins
   SDK. This approach is still fully supported and allows for backwards
   compatibility. When a 6.2 style plugin is deployed, Liferay converts the WAR
   file into one or more modules which are deployed into Liferay's module
   framework. This approach represents the path of least resistance for
   delivering existing plugins to Liferay 7.

If you want to develop a Liferay 6.2 style plugin, please see the
[Liferay 6.2 tutorials](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/tutorials).
If you want to develop a Liferay 7 style plugin, read on! You're in the right
place. You need not be familiar with OSGi to create applications for Liferay 7.
You really only need to understand two concepts: *modules* and *components*.

- A *module* is the one and only type of Liferay 7 plugin. Architecturally, a
  module is simply a group of Java classes, additional resources (optionally),
  and a `MANIFEST.MF` file, packaged together as a JAR file. Liferay
  applications usually consist of at least two or three modules. Note that
  modules are called *[bundles](https://en.wikipedia.org/wiki/OSGi#Bundles)* in
  the OSGi world. A module can contain one or more components. 
- Conceptually, a *component* is just an implementation of an interface. In
  Liferay, a component is typically a [Declarative Services
  component](http://wiki.osgi.org/wiki/Declarative_Services). This means that
  it's a Java class that's decorated with the
  `org.osgi.service.component.annotations.Component` annotation. The interface
  implemented by the component class is specified by the value of the `service`
  attribute of the annotation. A component lives within a module.

+$$$

**Important:** Don't confuse the terms *service* and *component*. A Liferay
*service* typically means a Liferay API. A *component* is an OSGi service, not a
Liferay service. That is, a component is registered in the service registry of
Liferay's module framework. A component does not necessarily have anything do
with Liferay's API.

$$$

Liferay's new development model gives developers more flexibility than before.
All components are loosely coupled. The lifecycle of modules and components is
managed by Liferay's OSGi-based module framework. Liferay 7 applications should
be designed as a compilation of lightweight components.

The flexibility doesn't end there, though. Previously, Liferay development had
to be done using Liferay's Plugins SDK. This is no longer the case. Liferay
developers can now create standalone module projects as long as the modules are
compatible with Liferay's new OSGi-based module framework. Other tools such as
Liferay's Eclipse-based IDEs (Liferay IDE and Liferay Developer Studio) can
still be used.

[Blade Tools](https://github.com/gamerson/blade.tools) is a new Liferay tool
that provides templates for creating a variety of types of modules. It's the
recommended (but not required) way to get started with Liferay 7 application
development. Since it's a command-line tool,
[Blade Tools](https://github.com/gamerson/blade.tools) can be invoked directly
or from other applications like Liferay IDE or Liferay Developer Studio.

Although it's no longer required, the Liferay Plugins SDK can still be used to
create Liferay 7 modules. It has been updated for the Liferay 7 release to
support both traditional Liferay development and development using the new
model. 

Read on to learn how to create modules for Liferay Portal. 

