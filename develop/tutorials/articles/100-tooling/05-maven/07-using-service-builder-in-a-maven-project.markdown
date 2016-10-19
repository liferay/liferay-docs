# Using Service Builder in a Maven Project

Liferay's
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
is a model-driven code generation tool that is frequently used by many Liferay
module projects. If you have a Liferay Maven project, you may be wondering if
Service Builder works with your Maven modules; the answer is a resounding yes!

The easiest way to configure a Service Builder project is to use Liferay's
provided Service Builder Maven archetype. You can learn how to generate a
Service Builder Maven project by visiting the
[Generating New Projects Using Archetypes](/develop/tutorials/-/knowledge_base/7-0/generating-new-projects-using-archetypes)
tutorial. In some cases, this may not be possible due to a number of reasons:

- Updating Maven project to follow OSGi modular architecture.
- Have pre-existing modular Maven project and don't want to start over.

In this tutorial, you'll learn how to configure Service Builder in a Liferay
Maven modular project. Time to get started!

1. 
