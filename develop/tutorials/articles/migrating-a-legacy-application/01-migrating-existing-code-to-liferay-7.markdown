# Migrating Existing Code to Liferay 7

Liferay 7's one and only plugin type is a [*module*](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Module). 
It's an OSGi-based module that Liferay's module framework hands off to OSGi for
deployment. It has some similarities to Liferay 6.x WAR-style applications,
but has some differences that you must address in them. 

As an overview, a module contains Java classes, frontend source files such as
JSPs, and module resources. In addition, a module must specify modules it
depends on, classes it uses from those modules, and classes it exposes for other
modules to use. You specify them in a module's manifest--we show you how to
generate and manage it. There are more conventions we've adopted to leverage
benefits of modularity with OSGi, but the above mentioned items are the primary
ones.

What does all this mean to you with regards to your existing portlets, hooks,
EXT plugins, and themes? That's what you'll learn in these tutorials. Here's an
overview of each legacy plugin's migration path:

- *Portlets* - Convert to a module, by first adapting a WAR-style plugin to
Liferay 7's API and then formally modularizing it

- *Hooks* - Convert to a module, or in some cases, convert to an OSGi Fragment

- *Themes* - Adapt to Liferay 7's frontend API, as a WAR-style plugin

- *Ext Plugins* - Convert to a module

The tutorials that follow demonstrate how to make these conversions and
adaptations to your legacy plugins.

We'll start by learning how to adapt a WAR-style legacy application to Liferay
7's API. 
