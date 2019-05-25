---
header-id: modularizing-an-existing-portlet
---

# Modularizing an Existing Portlet

[TOC levels=1-4]

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

- [Converting portlet classes and the UI](/docs/7-1/tutorials/-/knowledge_base/t/converting-your-applications-portlet-classes-and-ui)
- [Converting Service Builder interfaces and implementations](/docs/7-1/tutorials/-/knowledge_base/t/converting-your-applications-service-builder-api-and-implementation)
- [Building and deploying modules](/docs/7-1/tutorials/-/knowledge_base/t/building-your-module-jars-for-deployment)

The instructions apply to portlets for both Liferay DXP Digital Enterprise and
Liferay Portal CE.

The first thing you'll do is create your application's web (client) module.
