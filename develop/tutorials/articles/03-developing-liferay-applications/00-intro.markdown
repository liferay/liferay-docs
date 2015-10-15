# Introduction

Liferay 7 introduces a whole new application development paradigm based on
*modules* and *components*.

- A *module* is the one and only type of Liferay 7 plugin. Modules are called
  *[bundles](https://en.wikipedia.org/wiki/OSGi#Bundles)* in the OSGi world. A
  module can contain one or more components.
- A *component* is typically a [Declarative Services component](http://wiki.osgi.org/wiki/Declarative_Services).
  That is, a component is typically a Java class annotated with the
  `org.osgi.service.component.annotations.Component` annotation. A component
  lives within a module.

Liferay's new development model gives developers more flexibility than before.
All components are loosely coupled. The lifecycle of modules and components is
managed by Liferay's OSGi-based module framework. 

The flexibility doesn't end there, though. Previously, Liferay development had
to be done using Liferay's Plugins SDK. This is no longer the case. Liferay
developers can now create standalone module projects as long as the modules are
compatible with Liferay's new OSGi-based module framework. Other tools such as
Liferay's Eclipse-based IDEs (Liferay IDE and Liferay Developer Studio) can
still be used.

[Blade Tools](https://github.com/gamerson/blade.tools) is a new Liferay
tool that provides templates for creating a variety of types of modules. It's
not required but it does provide the easiest way to get started with Liferay 7
application development. Since it's a command-line tool,
[Blade Tools](https://github.com/gamerson/blade.tools) can be invoked directly
or from other applications like Liferay Developer Studio or Liferay IDE.

Although it's no longer required, the Liferay Plugins SDK can still be used to
create Liferay 7 modules. If you like the tooling infrastructure that Liferay
provides, it has been updated in this release to support both traditional
Liferay development and development using the new model. 

Read on to learn how to create modules for Liferay Portal. 
