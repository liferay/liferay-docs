# Planning Plugin Upgrades and Optimizations [](id=migrating-existing-code-to-liferay-7)

By now you've explored @product-ver@'s features and you might have created new
portlet modules or a theme using Liferay's new  [tooling](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more)
and 
[techniques](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity-for-liferay-6-developers).
But you also have existing plugins ready to upgrade as soon as possible. The
great thing is that Liferay's automated much of the upgrade process. In
addition, you can continue developing the plugins in traditional ways and adopt
new development tooling and techniques when you're ready. 

This tutorial guides you through phases of *upgrading* plugins and optionally
*optimizing* them. 

**upgrade**: A process for deploying an existing plugin on @product-ver@ with
minimal changes. 

**optimization**: An optional process for modifying a plugin or migrating it to 
a new environment to improve the plugin or facilitate developing it. 

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
-   [Migrating themes to the Themes Generator](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7)
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

Note, you can continue using the Plugins SDK to develop plugins. But the Plugins
SDK is deprecated  as of @product-ver@. In light of the deprecation, you should
consider migrating plugins from the Plugins SDK and to new environments, such as
ones that use Gradle or Maven. Liferay offers  both. 

-   [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#from-the-plugins-sdk-to-liferay-workspace)
    is a Gradle environment that supports developing modules and traditional
    plugins.
    [Blade's ](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications) 
    [`migrateWar  command`](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)
    moves Plugins SDK portlets to Liferay Workspace (Workspace) in a snap. 
-   [Liferay's Maven](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#whats-new-in-product-ver-for-maven-users) 
    plugins and archetypes support developing modules and traditional plugins.
    There's also a Liferay Workspace archetype for generating a Workspace that uses Maven. 

And [Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide) completely
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
 Ext | Coming soon | Coming soon |
 Hook - Language files | - [Override Portal language keys using a module with a resource bundle](/develop/tutorials/-/knowledge_base/7-0/modifying-liferays-language-keys)<br>- [Override module language keys using a module with a resource bundle and loader](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-language-keys) | Same |
 Hook - Override a @product@ Core JSP | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Convert to module that uses custom JSP bag](/develop/tutorials/-/knowledge_base/7-0/overriding-core-jsps) ([automated in Workspace](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool#step-7-convert-custom-jsp-hooks)) | Same |
 Hook - Override an app's JSP | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Convert to fragment module](/develop/tutorials/-/knowledge_base/7-0/overriding-core-jsps) ([automated for Workspace](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool#step-7-convert-custom-jsp-hooks)) | Same |
 Hook - Event Actions (Portal/Session/Shutdown/Startup) | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | Coming soon |
 Hook - Properties | - If the property is now a [System Setting](/discover/portal/-/knowledge_base/7-0/system-settings), edit it there and/or use a `.config` file<br>- If the property is in the [liferay-hook.xml's DTD](@platform-ref@/7.0-latest/definitions/liferay-hook_7_0_0.dtd.html), then [adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool) and [resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | None |
 Hook - Service Wrappers | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | Coming soon |
 Hook - Service pre-action | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | Coming soon |
 Hook - Servlet Filter | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | Coming soon |
 Hook - Struts actions | - [StrutsAction &rarr; StrutsActionWrapper](/develop/tutorials/-/knowledge_base/7-0/converting-strutsactionwrappers-to-mvccommands)<br> - [processAction &rarr; MVCActionCommand](/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcactioncommand)<br> - [render &rarr; MVCRenderCommand](/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcrendercommand)<br> - [serveResource &rarr; MVCResourceCommand](/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcresourcecommand) | Same |
 Layout Template | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)<br>3. [Update Layout Template](/develop/tutorials/-/knowledge_base/7-0/upgrading-layout-templates) | - [Migrate to Themes Generator](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7) (Node.js/Gulp/Yeoman) |
 Portlet - Liferay MVC | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | - [Migrate to Workspace/Gradle](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)<br>- [Apply Lexicon](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app)<br>- [Convert to OSGi modules](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet) |
 Portlet - JSF | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Configure dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) | None |
 Portlet - Servlet/JSP | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | Coming soon |
 Portlet - Spring MVC | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)<br>3. [Update Spring MVC portlet](/develop/tutorials/-/knowledge_base/7-0/spring-mvc) | None |
 Theme | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)<br>3. [Upgrade Theme](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes) | - [Migrate to Themes Generator](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7) (Node.js/Gulp/Yeoman)<br>- [Use Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets) |
 Web plugin | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | [Convert to OSGi module](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet), e.g., `portlet-x-web` | 

### Feature Upgrade and Optimization Paths [](id=feature-upgrade-and-optimization-paths)

 Feature | Upgrade path | Optimizations (optional) |
 :----------------- | :----------- | :----------------------- |
 Resources Importer | [Update the Resources Importer](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes#updating-the-resources-importer) | None |
 Services - Invoke a service from @product@ Core or another portlet or module | [Implement a Service Tracker](/develop/tutorials/-/knowledge_base/7-0/service-trackers) | [Invoke Liferay services from a module](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services) |
 Services - Module dependency | Copy `x-service.jar` to `WEB-INF/lib` | - [Migrate to Gradle/Maven](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications) and [add dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) on the OSGi service |
 Services - Service Builder | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | [Convert to OSGi modules](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet), e.g., `x-api` and `x-service` |
 Services - Web services | 1. [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>2. [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies) | [Use a Service Builder service with JAX-RS with a REST service in front](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs) |
 Template - FreeMarker | - [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)<br>- [Adapt Theme templates](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes#updating-theme-templates) | None |
 Template - Velocity (deprecated) | [Adapt code to API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool) | [Convert to FreeMarker](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes#updating-theme-templates) |

Now you have a game plan and a cheat sheet for upgrading and optimizing plugins
with confidence. 
