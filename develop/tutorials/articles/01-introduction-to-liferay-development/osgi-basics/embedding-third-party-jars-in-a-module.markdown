# Embedding Third Party JARs in a Module

The OSGi framework is intended for modular development where you can deploy
modules to export information that can then be used by other modules. In a
perfect world, every module you create could make use of a required service
provided by another module. Unfortantely, not all services are packaged and
provided as an OSGi module registered in the OSGi registry, so how do you make
use of those? Liferay modules can still use services not registered in the OSGi
registry, but you'll have to embed those services as JARs in your module.

There are a couple different ways to do this:

- Declare the JAR dependency in a way that expands the third party JAR within
  the module JAR.
- Declare the JAR dependency as a resource.

Before learning how to declare your third party JARs, you should first
understand which cases these two methods apply.
