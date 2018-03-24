# Modularizing an Existing Portlet [](id=modularizing-an-existing-portlet)

An application with properly modularized plugins offers several benefits. You
can release individually its plugins without releasing the entire application.
External clients can consume services from particular plugins, without having to
depend on an entire application. And by splitting up large amounts of code into
concise modules, teams can more easily focus on particular areas of the
application. These are just a few reasons to modularize application plugins. 

In this tutorial, you'll learn how to convert your traditional application into
modules. Before getting started, it's important to reiterate that the module
structure shown in this tutorial is just one of many ways for structuring your
application's modules. It's also important to remember that applications come in
all different shapes and sizes. There may be special actions that some
applications require. This tutorial provides the general process for converting
to modules using Liferay's module structure.

Here's what's involved:

- Converting portlet classes and the UI
- Converting Service Builder interfaces and implementations
- Building and deploying modules

The instructions covered in this tutorial apply to both the commercial and open 
source versions of Liferay.

The first thing you'll do is create your application's web (client) module.
