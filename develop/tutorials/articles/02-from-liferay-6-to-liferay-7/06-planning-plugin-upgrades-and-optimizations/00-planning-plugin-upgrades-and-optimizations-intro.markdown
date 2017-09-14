# Planning Plugin Upgrades and Optimizations [](id=migrating-existing-code-to-liferay-7)

If you've explored @product-ver@'s features and possibly created new
portlet modules themes with Liferay's new  [tooling](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more)
and 
[techniques](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers),
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
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
automates much of the process. In addition, the 
[upgrade tutorials](/develop/tutorials/-/knowledge_base/7-0/upgrading-plugins-to-liferay-7)
demonstrate any remaining upgrade steps. 

You can deploy plugins to @product-ver@ as you have for previous releases (e.g.,
`ant clean deploy`). Since everything in @product-ver@ runs as OSGi modules,
however, you might wonder how traditional WAR-style plugins can run on it. The
answer: Liferay's Plugin Compatibility Layer. 

The Plugin Compatibility Layer converts standard WARs to Web Application Bundles
(WABs). WABs are full-fledged OSGi modules. The Plugin Compatibility Layer's
[WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator) 
supports deploying traditional plugins and web applications that contain
Servlets, JSPs, and other Java web technologies without making any OSGi specific
changes to them. <!--See [Plugin Compatibility Layer](TODO) for more details.-->

Note, you can still use an application server's mechanisms to deploy regular web
applications along with @product@, without using the Plugin Compatibility Layer.

After upgrading your plugins you can consider optimizations such as these:

-   Migrating plugins to
    [Gradle](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)
    or Maven to leverage their development commands and rich
    [Liferay plugin templates](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#developing-modules-with-liferay-workspace). 
-   [Migrating themes to the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7)
    to add [Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets) (new) 
    and to leverage
    [Node.js, Yeoman, and Gulp](/develop/tutorials/-/knowledge_base/7-0/themes-generator). 
-   [Converting plugins to modules](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)
    to leverage Declarative Services, extendability, and more
    [modularity benefits](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers).
-   Using the
    [Liferay Lexicon](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app)
    design language to apply a clean consistent application user experience. 

See the
[optimization tutorials](/develop/tutorials/-/knowledge_base/7-0/optimizing-plugins-for-liferay-7)
for more options and details. 

You *can* continue using the Plugins SDK to develop plugins. But the Plugins
SDK is deprecated  as of @product-ver@. In light of the deprecation, you should
consider migrating plugins from the Plugins SDK to one of the new environments:

-   [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#from-the-plugins-sdk-to-liferay-workspace)
    is a Gradle environment that supports developing modules and traditional
    plugins.
    [Blade's ](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications) 
    [`migrateWar  command`](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)
    moves Plugins SDK portlets to Liferay Workspace (Workspace) in a snap. 
-   [Liferay's Maven](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#whats-new-in-product-ver-for-maven-users) 
    plugins and archetypes support developing modules and traditional plugins.
    There's also a Liferay Workspace archetype for generating a Workspace that uses Maven. 

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide) 
supports developing in Workspaces using Gradle or [Maven](/develop/tutorials/-/knowledge_base/7-0/using-maven-in-liferay-ide).

In short, there's plenty of time to move plugins out of the Plugins SDK, but you
should at least plan for migrating to a new environment that works best for
you. 

Speaking of planning, properly planned upgrades and optimizations reduce the
time and effort they take. To help guide you through the upgrade and
optimization tutorials, you get these things:

-   Upgrade and optimization phase descriptions
-   Upgrade and optimization paths

## Upgrade and Optimization Phases [](id=upgrade-and-optimization-phases)

Follow these upgrade and optimization phases:

1.  Read the applicable
    [upgrade tutorials](/develop/tutorials/-/knowledge_base/7-0/upgrading-plugins-to-liferay-7)
    for your plugin. Examine the upgrade and optimization paths.

2.  Upgrade the plugin, making only the minimal changes necessary for it to 
    work on @product-ver@. 

3.  (Optional) Identify and apply only the most beneficial 
    [optimizations](/develop/tutorials/-/knowledge_base/7-0/optimizing-plugins-for-liferay-7)
    for your plugin. 

4.  (Optional) Apply additional optimizations as desired. 

## Upgrade and Optimization Paths [](id=upgrade-and-optimization-paths)

The following tables provide upgrade and optimization
paths for 6.2 plugins and features. 

### Plugin Upgrade and Optimization Paths [](id=plugin-upgrade-and-optimization-paths)

 Plugin | Upgrade path | Optimizations (optional) |
 :----------------- | :----------- | :----------------------- |
 Ext | [Customization with Ext Plugins](/develop/tutorials/-/knowledge_base/7-0/advanced-customization-with-ext-plugins)  | None |
 Hook - Language files | - [Upgrading Core Language Key Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-core-language-key-hooks)<br>- [Upgrading Portlet Language Key Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-portlet-language-key-hooks) | Same |
 Hook - Override a @product@ Core JSP | [Upgrading Core JSP Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-core-jsp-hooks) | Same |
 Hook - Override an app's JSP | [Upgrading App JSP Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-app-jsp-hook-plugins) | Same |
 Hook - Event Actions (Portal/Session/Servlet Service/Shutdown/Startup) | [Upgrading Portal Property and Event Action Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-portal-property-and-event-action-hooks) | Coming soon |
 Hook - Model Listeners | [Upgrading Model Listener Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-model-listener-hooks) | Same |
 Hook - Portal Properties | [Upgrading Portal Property and Event Action Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-portal-property-and-event-action-hooks) | Same |
 Hook - Properties | - If the property is now a [System Setting](/discover/portal/-/knowledge_base/7-0/system-settings), edit it there and/or use a `.config` file<br>- If the property is in the [liferay-hook.xml's DTD](@platform-ref@/7.0-latest/definitions/liferay-hook_7_0_0.dtd.html), then [adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool) and [resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | None |
 Hook - Service Wrappers | [Upgrading Service Wrappers](/develop/tutorials/-/knowledge_base/7-0/upgrading-service-wrappers) | Coming soon |
 Hook - Servlet Filter | [Upgrading Servlet Filter Hooks](/develop/tutorials/-/knowledge_base/7-0/upgrading-servlet-filter-hooks) | Coming soon |
 Hook - Struts actions | - [StrutsAction &rarr; StrutsActionWrapper](/develop/tutorials/-/knowledge_base/7-0/converting-strutsactionwrappers-to-mvccommands)<br> - [processAction &rarr; MVCActionCommand](/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcactioncommand)<br> - [render &rarr; MVCRenderCommand](/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcrendercommand)<br> - [serveResource &rarr; MVCResourceCommand](/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcresourcecommand) | Same |
 Layout Template | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)<br>3. [Update Layout Template](/develop/tutorials/-/knowledge_base/7-0/upgrading-layout-templates) | - [Migrate to Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7) (Node.js/Gulp/Yeoman) |
 Portlet - GenericPortlet | [Upgrading a GenericPortlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-a-genericportlet) | - [Migrate to Workspace/Gradle](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)<br>- [Apply Lexicon](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app)<br>- [Convert to OSGi modules](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet) |
 Portlet - Liferay MVC | [Upgrading a Liferay MVC Portlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-a-liferay-mvc-portlet) | - [Migrate to Workspace/Gradle](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)<br>- [Apply Lexicon](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app)<br>- [Convert to OSGi modules](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet) |
 Portlet - JSF | [Upgrading a Liferay JSF Portlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-a-liferay-jsf-portlet) | None |
 Portlet - Servlet/JSP | [Upgrading a Servlet-based Portlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-a-servlet-based-portlet) | Coming soon |
 Portlet - Spring MVC | [Upgrading a Spring MVC Portlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-a-spring-mvc-portlet) | None |
 Portlet - Struts 1 | [Upgrading a Struts Portlet](/develop/tutorials/-/knowledge_base/7-0/upgrading-a-struts-portlet) | None |
 Theme | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)<br>3. [Upgrade Theme](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes) | - [Migrate to Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7) (Node.js/Gulp/Yeoman)<br>- [Use Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets) |
 Web plugin | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | [Convert to OSGi module](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet), e.g., `portlet-x-web` | 

### Feature Upgrade and Optimization Paths [](id=feature-upgrade-and-optimization-paths)

 Feature | Upgrade path | Optimizations (optional) |
 :----------------- | :----------- | :----------------------- |
 Resources Importer | [Update the Resources Importer](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes#updating-the-resources-importer) | None |
 Services - Invoke a service from @product@ Core or another portlet or module | [Implement a Service Tracker](/develop/tutorials/-/knowledge_base/7-0/service-trackers) | [Invoke Liferay services from a module](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services) |
 Services - Module dependency | Copy `x-service.jar` to `WEB-INF/lib` | - [Migrate to Gradle/Maven](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications) and [add dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) on the OSGi service |
 Services - Service Builder | [Upgrading Portlets that use Service Builder](/develop/tutorials/-/knowledge_base/7-0/upgrading-portlets-that-use-service-builder) | [Convert to OSGi modules](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet), e.g., `x-api` and `x-service` |
 Services - Web services | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | [Use a Service Builder service with JAX-RS with a REST service in front](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs) |
 Template - FreeMarker | - [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>- [Adapt Theme templates](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes#updating-theme-templates) | None |
 Template - Velocity (deprecated) | [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool) | [Convert to FreeMarker](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes#updating-theme-templates) |

Now you have a game plan and a cheat sheet for upgrading and optimizing plugins
with confidence. 
