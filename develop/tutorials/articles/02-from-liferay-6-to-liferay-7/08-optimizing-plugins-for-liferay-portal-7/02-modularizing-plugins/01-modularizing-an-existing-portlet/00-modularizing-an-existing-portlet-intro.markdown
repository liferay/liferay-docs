# Modularizing an Existing Portlet [](id=modularizing-an-existing-portlet)

An application with properly modularized plugins offers several benefits. You
can release individually its plugins without releasing the entire application.
External clients can consume services from particular plugins without having to
depend on an entire application. And by splitting up large amounts of code into
concise modules, teams can more easily focus on particular areas of the
application. These are just a few reasons to modularize application plugins. 

In these tutorials, you'll learn how to convert your traditional application
into modules. Before getting started, it's important to reiterate that the
module structure shown in these tutorials is just one of many ways for
structuring your application's modules. Also applications come in all different
shapes and sizes. There may be special actions that some applications require.
These tutorials provide the general process for converting to modules using
Liferay's module structure.

Here's what's involved:

- [Converting portlet classes and the UI](/develop/tutorials/-/knowledge_base/7-1/converting-your-applications-portlet-classes-and-ui)
- [Converting Service Builder interfaces and implementations](/develop/tutorials/-/knowledge_base/7-1/converting-your-applications-service-builder-api-and-implementation)
- [Building and deploying modules](/develop/tutorials/-/knowledge_base/7-1/building-your-module-jars-for-deployment)

The instructions apply to portlets for both Liferay DXP Digital Enterprise and
Liferay Portal CE.

The first thing you'll do is create your application's web (client) module.
