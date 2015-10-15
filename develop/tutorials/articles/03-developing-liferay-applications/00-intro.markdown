# Introduction

Liferay 7 introduces a new application development paradigm. Liferay plugins are
no longer deployed to Liferay's application server or servlet container.
Instead, they are deployed to Liferay's OSGi-based module framework. However,
you need not be an OSGi expert to succeed as a Liferay 7 developer. You really
only need to understand two concepts: *modules* and *components*.

- A *module* is the one and only type of Liferay 7 plugin. Architecturally, a
  module is a `.jar` file that contains Liferay-specific resources and metadata.
  Liferay applications usually consist of at least two or three modules. Note
  that modules are called
  *[bundles](https://en.wikipedia.org/wiki/OSGi#Bundles)* in the OSGi world. A
  module can contain one or more components. 
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
managed by Liferay's OSGi-based module framework. 

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
