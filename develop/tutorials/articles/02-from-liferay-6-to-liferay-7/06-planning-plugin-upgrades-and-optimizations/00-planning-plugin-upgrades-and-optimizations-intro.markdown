---
header-id: migrating-existing-code-to-liferay-7
---

# Planning Plugin Upgrades and Optimizations

[TOC levels=1-4]

If you've explored @product-ver@'s features and possibly created new
portlet modules themes with Liferay's new  [tooling](/docs/7-1/tutorials/-/knowledge_base/t/improved-developer-tooling-liferay-workspace-maven-plugins-and-more)
and 
[techniques](/docs/7-1/tutorials/-/knowledge_base/t/osgi-and-modularity-for-liferay-6-developers),
you may be wondering how you'd upgrade existing plugins. The great thing is that
Liferay has automated much of the upgrade process. In addition, you can continue
developing plugins in traditional ways and adopt new development tooling and
techniques when you're ready. 

This tutorial guides you through phases of *upgrading* plugins and optionally
*optimizing* them. 

**Upgrade**: A process for deploying an existing plugin on @product-ver@ with
minimal changes. 

**Optimization**: An optional but recommended process for modifying a plugin or
migrating it to a new environment to improve the plugin or facilitate developing
it. 

Importantly, you should *upgrade* a plugin before applying any optimizations to 
it. 

The good news is that upgrading plugins to @product-ver@ is straightforward. For
Plugins SDK and Maven projects, Liferay @ide@'s
[Code Upgrade Tool](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
automates much of the process. In addition, the 
[upgrade tutorials](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7)
demonstrate any remaining upgrade steps. 

You can deploy plugins to @product-ver@ as you have for previous releases (e.g.,
`ant clean deploy`). Since everything in @product-ver@ runs as OSGi modules,
however, you might wonder how traditional WAR-style plugins can run on it. The
answer: Liferay's Plugin Compatibility Layer. 

The Plugin Compatibility Layer converts standard WARs to Web Application Bundles
(WABs). WABs are full-fledged OSGi modules. The Plugin Compatibility Layer's
[WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) 
supports deploying traditional plugins and web applications that contain
Servlets, JSPs, and other Java web technologies without making any OSGi specific
changes to them. 

Note, you can still use an application server's mechanisms to deploy regular web
applications along with @product@, without using the Plugin Compatibility Layer.

After upgrading your plugins you can consider optimizations such as these:

-   Migrating plugins to
    [Gradle](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)
    or Maven to leverage their development commands and rich
    [Liferay plugin templates](/docs/7-1/tutorials/-/knowledge_base/t/developing-modules-with-liferay-workspace). 
-   [Migrating themes to the Liferay Theme Generator](/docs/7-0/tutorials/-/knowledge_base/t/migrating-a-6-2-theme-to-liferay-7)
    to add [Themelets](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes) (new) 
    and to leverage
    [Node.js, Yeoman, and Gulp](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes). 
-   [Converting plugins to modules](/docs/7-1/tutorials/-/knowledge_base/t/modularizing-an-existing-portlet)
    to leverage Declarative Services, extendability, and more
    [modularity benefits](/docs/7-1/tutorials/-/knowledge_base/t/osgi-and-modularity-for-liferay-6-developers).
-   Using the
    [Clay](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-styles-to-your-app),
    to apply a clean consistent application user experience. 

See the
[optimization tutorials](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-plugins-for-liferay-7)
for more options and details. 

The Plugins SDK is no longer available to develop plugins for @product-ver@. 
Visit the
[Deprecated Apps in 7.1: What To Do](/docs/7-1/deploy/-/knowledge_base/d/deprecated-apps-in-7-1-what-to-do#foundation)
article for more information on the Plugins SDK removal.

In light of the removal, you should consider migrating plugins from the Plugins
SDK to one of the new environments:

-   [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/from-the-plugins-sdk-to-liferay-workspace)
    is a Gradle environment that supports developing modules and traditional
    plugins.
    [Blade's ](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications) 
    [`migrateWar  command`](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)
    moves Plugins SDK portlets to Liferay Workspace (Workspace) in a snap. 
-   [Liferay's Maven](/docs/7-1/tutorials/-/knowledge_base/t/whats-new-for-maven-users) 
    plugins and archetypes support developing modules and traditional plugins.
    There's also a Liferay Workspace archetype for generating a Workspace that uses Maven. 

[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide) supports
developing in Workspaces using Gradle or 
[Maven](/docs/7-1/tutorials/-/knowledge_base/t/using-maven-in-liferay-ide).

Properly planned upgrades and optimizations reduce the time and effort they
take. To help guide you through the upgrade and optimization tutorials, you get
these things:

-   Upgrade and optimization phase descriptions
-   Upgrade and optimization paths

## Upgrade and Optimization Phases

Follow these upgrade and optimization phases:

1.  Read the applicable
    [upgrade tutorials](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7)
    for your plugin. Examine the upgrade and optimization paths.

2.  Upgrade the plugin, making only the minimal changes necessary for it to 
    work on @product-ver@. 

3.  (Optional) Identify and apply only the most beneficial 
    [optimizations](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-plugins-for-liferay-7)
    for your plugin. 

4.  (Optional) Apply additional optimizations as desired. 

### Plugin Upgrade and Optimization Paths

 Plugin | Upgrade path | Optimizations (optional) |
 :----------------- | :----------- | :----------------------- |
 Ext | [Customization with Ext Plugins](/docs/7-0/tutorials/-/knowledge_base/t/advanced-customization-with-ext-plugins)  | None |
 Hook - Language files | - [Upgrading Core Language Key Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-language-key-hooks)<br>- [Upgrading Portlet Language Key Hooks](/develop/tutorials/-/knowledge_base/7-1/upgrading-portlet-language-key-hooks) | Same |
 Hook - Override a @product@ Core JSP | [Upgrading Core JSP Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-jsp-hooks) | Same |
 Hook - Override an app's JSP | [Upgrading App JSP Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-app-jsp-hook-plugins) | Same |
 Hook - Event Actions (Portal/Session/Servlet Service/Shutdown/Startup) | [Upgrading Portal Property and Event Action Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portal-property-and-event-action-hooks) | None |
 Hook - Model Listeners | [Upgrading Model Listener Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-model-listener-hooks) | Same |
 Hook - Portal Properties | [Upgrading Portal Property and Event Action Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portal-property-and-event-action-hooks) | Same |
 Hook - Properties | - If the property is now a [System Setting](/docs/7-1/user/-/knowledge_base/u/system-settings), edit it there and/or use a `.config` file<br>- If the property is in the [liferay-hook.xml's DTD](@platform-ref@/7.1-latest/definitions/liferay-hook_7_1_0.dtd.html), then [adapt code to API](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool) and [resolve dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies) | None |
 Hook - Service Wrappers | [Upgrading Service Wrappers](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-service-wrappers) | None |
 Hook - Servlet Filter | [Upgrading Servlet Filter Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-servlet-filter-hooks) | None |
 Hook - Struts actions | - [StrutsAction &rarr; StrutsActionWrapper](/docs/7-1/tutorials/-/knowledge_base/t/converting-strutsactionwrappers-to-mvccommands)<br> - [processAction &rarr; MVCActionCommand](/develop/tutorials/-/knowledge_base/7-1/overriding-mvcactioncommand)<br> - [render &rarr; MVCRenderCommand](/develop/tutorials/-/knowledge_base/7-1/overriding-mvcrendercommand)<br> - [serveResource &rarr; MVCResourceCommand](/develop/tutorials/-/knowledge_base/7-1/overriding-mvcresourcecommand) | Same |
 Layout Template | 1. [Adapt code to API](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)<br>3. [Update Layout Template](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually) | - [Migrate to Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7) (Node.js/Gulp/Yeoman) |
 Portlet - GenericPortlet | [Upgrading a GenericPortlet](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-genericportlet) | - [Migrate to Workspace/Gradle](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications)<br>- [Apply Clay](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app)<br>- [Convert to OSGi modules](/develop/tutorials/-/knowledge_base/7-1/modularizing-an-existing-portlet) |
 Portlet - JSF | [Upgrading a Liferay JSF Portlet](develop/tutorials/-/knowledge_base/7-1/upgrading-a-liferay-jsf-portlet) | None |
 Portlet - Liferay MVC | [Upgrading a Liferay MVC Portlet](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-liferay-mvc-portlet) | - [Migrate to Workspace/Gradle](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications)<br>- [Apply Clay](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app)<br>- [Convert to OSGi modules](/develop/tutorials/-/knowledge_base/7-1/modularizing-an-existing-portlet) |
 Portlet - Servlet/JSP | [Upgrading a Servlet-based Portlet](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-servlet-based-portlet) | None |
 Portlet - Spring MVC | [Upgrading a Spring MVC Portlet](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-spring-mvc-portlet) | None |
 Portlet - Struts 1 | [Upgrading a Struts Portlet](/docs/7-0/tutorials/-/knowledge_base/t/upgrading-a-struts-portlet) | [Converting StrustActionWrappers to MVCCommands](/develop/tutorials/-/knowledge_base/7-1/converting-strutsactionwrappers-to-mvccommands) |
 Theme | 1. [Adapt code to API](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)<br>3. [Upgrade Theme](/develop/tutorials/-/knowledge_base/7-1/upgrading-6-2-themes-intro) | - [Migrate to Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7) (Node.js/Gulp/Yeoman)<br>- [Use Themelets](/develop/tutorials/-/knowledge_base/7-1/creating-reusable-pieces-of-code-for-your-themes) |
 Web plugin | 1. [Adapt code to API](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies) | [Convert to OSGi module](/develop/tutorials/-/knowledge_base/7-1/modularizing-an-existing-portlet), e.g., `portlet-x-web` | 

### Feature Upgrade and Optimization Paths

 Feature | Upgrade path | Optimizations (optional) |
 :----------------- | :----------- | :----------------------- |
 JNDI data source | [Use @product@'s classloader to access the app server's JNDI API](/docs/7-1/tutorials/-/knowledge_base/t/connecting-to-data-sources-using-jndi) | None |
 Services - Invoke a service from @product@ Core or another portlet or module | [Implement a Service Tracker](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers) | [Invoke Liferay services from a module](/develop/tutorials/-/knowledge_base/7-1/invoking-local-services) |
 Services - Module dependency | Copy `x-service.jar` to `WEB-INF/lib` | - [Migrate to Gradle/Maven](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications) and [add dependency](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies) on the OSGi service |
 Services - Service Builder | [Upgrading Portlets that use Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portlets-that-use-service-builder) | [Convert to OSGi modules](/develop/tutorials/-/knowledge_base/7-1/modularizing-an-existing-portlet), e.g., `x-api` and `x-service` |
 Services - Web services | 1. [Adapt code to API](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies) | Use a Service Builder service with JAX-RS with a REST service in front |
 Template - FreeMarker | - [Adapt code to API](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>- [Adapt Theme templates](/develop/tutorials/-/knowledge_base/7-1/updating-theme-templates) | None |
 Template - Velocity (deprecated) | [Adapt code to API](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool) | [Convert to FreeMarker](/develop/tutorials/-/knowledge_base/7-1/updating-theme-templates) |

Now you have a game plan and a cheat sheet for upgrading and optimizing plugins
with confidence. 
