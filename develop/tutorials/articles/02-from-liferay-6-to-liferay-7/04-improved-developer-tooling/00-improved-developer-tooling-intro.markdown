---
header-id: improved-developer-tooling-liferay-workspace-maven-plugins-and-more
---

# Improved Developer Tooling: Liferay Workspace, Maven Plugins and More

[TOC levels=1-4]

Creating applications is fun when you have the right tools. Here are some key
ingredients:

-   Rich templates for stubbing out projects
-   Extensible build environments that offer state-of-the-art plugins
-   Deployment and runtime management tools
-   Application upgrade automation

[Liferay Workspace](/docs/7-0/tutorials/-/knowledge_base/t/liferay-workspace)
(Workspace) boils over with all these things! It's a Gradle-based development
environment that integrates with [Liferay
@ide@](/docs/7-0/tutorials/-/knowledge_base/t/liferay-ide) and can be used in
conjunction with other IDEs, such as a "vanilla" Eclipse, IntelliJ, and
NetBeans. You can extend Workspace's Gradle environment with community-developed (or
home-grown) [plugins](/docs/7-0/reference/-/knowledge_base/r/gradle) for
testing, code coverage analysis, and more. 

Workspace comes with [Blade
CLI](/docs/7-0/tutorials/-/knowledge_base/t/blade-cli): a command line tool for
creating and deploying modules, managing the runtime environment, and more. It
provides all kinds of module
[templates](/docs/7-0/tutorials/-/knowledge_base/t/creating-modules-with-blade-cli#module-templates),
to create modules for developing in any Gradle environment. 

Liferay's tools also streamline the application upgrade process. Liferay @ide@'s
[Code Upgrade Tool](/docs/7-0/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
adapts traditional plugins to @product-ver@ APIs. 
[Liferay's Liferay Theme Generator](/docs/7-0/tutorials/-/knowledge_base/t/themes-generator)
[migrates](/docs/7-0/tutorials/-/knowledge_base/t/migrating-a-6-2-theme-to-liferay-7)
themes and layout templates to the new NodeJS-based environment and
[adapts](/docs/7-0/tutorials/-/knowledge_base/t/upgrading-themes#running-the-upgrade-task-for-themes-generator-themes)
them to @product-ver@.

@product@ offers you more with
[Maven](/docs/7-0/tutorials/-/knowledge_base/t/maven) too. The  archetype
Liferay Project Templates Workspace lets you develop in Liferay Workspace using
Maven. @product-ver@'s' lean artifacts and new project
[archetypes](/docs/7-0/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes)
and [Maven plugins](/docs/7-0/reference/-/knowledge_base/r/maven) make
@product@ development with Maven easier than ever.

Here are the tooling improvement topics:

-   [Moving from the Plugins SDK to Liferay Workspace](#from-the-plugins-sdk-to-liferay-workspace)
-   [Developing modules with Liferay Workspace](#developing-modules-with-liferay-workspace)
-   [What's new in @product@ for Maven Users](#whats-new-in-product-ver-for-maven-users)
-   [Using other build systems and IDEs](#using-other-build-systems-and-ides)

## From the Plugins SDK to Liferay Workspace

The Liferay Plugins SDK is deprecated as of @product-ver@. You can continue
developing on it, but should plan to eventually move to a new environment.
[Liferay Workspace](/docs/7-0/tutorials/-/knowledge_base/t/liferay-workspace)
succeeds the Plugins SDK as Liferay's opinionated development environment. You
should use it if you're not using an alternative build system like Gradle or
Maven.

Here are Workspace's key features:

-   [Module and component templates](/docs/7-0/tutorials/-/knowledge_base/t/creating-modules-with-blade-cli#module-templates)
-   [Sample projects](/docs/7-0/tutorials/-/knowledge_base/t/liferay-sample-modules)
-   Portal server configurations
-   Folder structure flexibility
-   [Commands](/docs/7-0/tutorials/-/knowledge_base/t/blade-cli) to migrate
    plugins, install @product@ bundles, and start/stop Portal instances

The [plugin upgrade](/docs/7-0/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7)
tutorials later in this series show how 
[Liferay @ide@](/docs/7-0/tutorials/-/knowledge_base/t/liferay-ide) automatically adapts existing plugins to @product-ver@. There's also a tutorial
that demonstrates how you can optionally
[migrate traditional plugins to Workspace](/docs/7-0/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications).

![Figure 1: Liferay @ide@'s Code Upgrade Tool automates many aspects of the plugin upgrade process.](../../../images/improved-tooling-code-upgrade-configure-proj.png)

Here's an example Workspace folder structure:

![Figure 2: Liferay Workspace aggregates projects to use the same server configurations and Gradle build environment.](../../../images/improved-tooling-workspace-structure.png)

Here's the Workspace anatomy:

-   `bundles/` (generated) &rarr; default folder for @product@ bundles
-   `configs/` &rarr; holds Portal server configurations
-   `gradle/` &rarr; holds the Gradle wrapper files
-   `modules/` &rarr; holds module projects
-   `plugins-sdk/` (generated) &rarr; holds plugins from previous releases
-   `themes/` &rarr; holds NodeJS-based theme projects
-   `wars/` (generated) &rarr; holds traditional web application projects
-   `build.gradle` &rarr; common Gradle build file
-   `gradle.properties` &rarr; specifies the Portal server configuration and
    project locations
-   `gradlew / gradlew.bat` &rarr; executes the Gradle command wrapper
-   `pom.xml` (only in Workspaces generated by Maven) &rarr; common Maven build
    file
-   `settings.gradle` &rarr; applies plugins to the Workspace and configures its
    dependencies

Workspace module, theme, and war projects use the same Portal server
configurations. Developers can create configurations for module development,
user acceptance testing, production, and more.

Each subfolder under `configs` holds a Portal server configuration defined by
its `portal-ext.properties` file. Gradle property
`liferay.workspace.environment` in Workspace's `gradle.properties` file
specifies the configuration to use.

Other Gradle properties let you set root locations for the @product@ bundle,
modules, themes, and a Plugins SDK.

### Workspace Folder Structure Properties

 Property | Description |
:--------- | :------------- |
`liferay.workspace.environment` | Name of a `configs` subfolder holding the Portal server configuration to use |
`liferay.workspace.home.dir` | @product@ bundle root folder |
`liferay.workspace.modules.dir` | Module projects root folder |
`liferay.workspace.plugins.sdk.dir` | Plugins SDK root folder |
`liferay.workspace.themes.dir` | Theme projects root folder |

Workspace has Gradle tasks equivalent to the Plugins SDK Ant targets.

### Plugins SDK to Workspace Task Map

 Plugins SDK Ant Target | Workspace Gradle Task | Task Description |
:--------------------------- | :-------------------------- | :---------------- |
`build-css` | `buildCSS` | Builds CSS files |
`build-lang` | `buildLang` | Translates language keys using Language Builder |
`build-service` | `buildService` | Runs Service Builder |
`clean` | `clean` | Deletes all build outputs |
`compile` | `classes` | Compiles classes |
`deploy` | `deploy` (or `blade deploy`) | Installs the current module to @product@'s module framework |
`jar` | `jar` | Compiles the project and packages it as a JAR file |
`war` | `assemble` | Assembles project output |

Other Workspace Gradle tasks provide additional functionality.

Workspace Gradle Task | Task Description |
:-------------------------- | :------------------ |
`buildSoy` | Compiles Closure Templates in JavaScript functions |
`components` | Lists the project's components |
`dependencies` | Lists the project's declared dependencies |
`initBundle` | Downloads and installs a @product@ bundle |
`model` | Lists the project's configuration model |
`transpileJS` | Transpiles the project's JavaScript files |

Next, learn how Workspace facilitates module development. 

## Developing Modules with Liferay Workspace

Workspace is a great Liferay module development environment because of these
features: 

-   Templates that bootstrap module creation
-   Gradle and Maven build systems for managing dependencies and assembling
    modules
-   Module deployment and runtime management capabilities

[Blade CLI](/docs/7-0/tutorials/-/knowledge_base/t/blade-cli) (Blade), which is
a part of Workspace, has over twenty templates for Gradle-based module
projects--and more are being added. The templates stub out classes and resource
files for you to fill in with business logic and key information.

Here are some of the template's names:

-   Activator
-   API
-   Content Targeting Report
-   Content Targeting Rule
-   Content Targeting Tracking Action
-   Control Menu Entry
-   MVC Portlet
-   Panel App
-   Portlet
-   Portlet Configuration Icon
-   Portlet Provider
-   Portlet Toolbar Contributor
-   Service
-   Service Builder
-   Service Wrapper
-   Simulation Panel Entry
-   Template Context Contributor

[Blade creates modules](/docs/7-0/tutorials/-/knowledge_base/t/creating-modules-with-blade-cli)
based on these templates.

For example, the following Blade command creates a Liferay MVC Portlet module
called `my-module`:

    blade create -t mvc-portlet -p com.liferay.docs.mymodule -c MyMvcPortlet my-module

Liferay @ide@'s 
[module project wizard](/docs/7-0/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide)
creates Workspace modules from the templates too.

![Figure 3: Liferay @ide@ lets developers select templates to stub out modules.](../../../images/improved-tooling-module-wizard.png)

Liferay @ide@'s component wizard facilitates 
[creating component classes](/docs/7-0/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide#creating-component-classes)
for portlets, service wrappers, Struts actions, and more.

![Figure 4: Liferay @ide@'s component wizard facilitates creating component classes.](../../../images/improved-tooling-component-wizard.png)

Building and deploying modules in a Workspace is a snap using [Liferay
@ide@](/docs/7-0/tutorials/-/knowledge_base/t/deploying-modules-with-liferay-ide)
and
[Blade](/docs/7-0/tutorials/-/knowledge_base/t/deploying-modules-with-blade-cli).
Workspace uses BndTools to generate each module's OSGi headers in a
`META-INF/MANIFEST.MF` file. Workspace deploy modules to the OSGi container
using Felix File Install commands.

Liferay @ide@ lets you deploy modules by dragging them onto your Portal server.

![Figure 5: Liferay @ide@ lets you deploy modules using drag-and-drop.](../../../images/improved-tooling-drag-n-drop-onto-server.png)

In a terminal, you can deploy modules using Blade's `deploy` command. For
example, the following command deploys the current module and 
["watches" for module changes to redeploy automatically](/docs/7-0/tutorials/-/knowledge_base/t/starting-module-development#redeploying-module-changes-automatically).

    blade deploy -w

To learn more about Workspace and using it in Liferay @ide@, see these
tutorials: 

-   [Workspace](/docs/7-0/tutorials/-/knowledge_base/t/liferay-workspace)
-   [Blade CLI](/docs/7-0/tutorials/-/knowledge_base/t/blade-cli)
-   [Liferay @ide@](/docs/7-0/tutorials/-/knowledge_base/t/liferay-ide)

And make sure to check out the tutorial [Starting Module
Development](/docs/7-0/tutorials/-/knowledge_base/t/starting-module-development). 

Next, you'll learn new features for developing on @product@ using Maven.

## What's New in @product-ver@ for Maven Users

@product-ver@ fully supports Maven development and offers several new and
improved features: 

-   Liferay Workspace for Maven
-   New archetypes
-   New Maven plugins
-   More granular dependency management

The new archetype Liferay Project Templates Workspace generates a Liferay
Workspace that includes a POM file for developing in Workspace using Maven. You
can develop modules and themes in the Workspace subfolders. 

@product-ver@ provides many new Maven archetypes for various Liferay module
projects. There are over twenty-five Maven archetypes for @product-ver@, and
more are in development. Here are some popular ones:

-   Portlets
-   Themes
-   Configuration Icons
-   Menu Buttons
-   Service Builder

Liferay's Maven archetypes cover many different Liferay frameworks and
service types. These make Maven a first-class tool for creating Liferay modules
and themes. Visit the 
[Generating New Projects Using Archetypes](/docs/7-0/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes)
tutorial to learn more about Liferay's Maven archetypes and how to use them.

Liferay also provides several new and updated 
[Maven plugins](/docs/7-0/reference/-/knowledge_base/r/maven) that simplify the
build process. The following plugins build style sheets, services, and themes
respectively:

-   [CSS Builder](/docs/7-0/tutorials/-/knowledge_base/t/compiling-sass-files-in-a-maven-project)
-   [Service Builder](/docs/7-0/tutorials/-/knowledge_base/t/using-service-builder-in-a-maven-project)
-   [Theme Builder](/docs/7-0/tutorials/-/knowledge_base/t/building-themes-in-a-maven-project)

@product-ver@'s modularity provides a more granular dependency management
experience. You no longer need to depend on `portal-impl` or `portal-service`
(now `portal-kernel`) for everything. For example, to use Liferay's Wiki
framework, you need only depend on the Wiki module. You set dependencies on
concise modules that provide the functionality you want without inheriting extra
baggage.

Liferay's new Workspace environment, Maven archetypes, Maven plugins, and
streamlined modules make developing on @product@ easier than ever. To learn
more, see the 
[Maven tutorials](/docs/7-0/tutorials/-/knowledge_base/t/maven).

## Using Other Build Systems and IDEs

@product@ is tool agnostic--you can use whatever tools you like to develop on
it. You can use any IDE and even use Gradle, Bnd, or BndTools if you don't want
to use Workspace. The drawback is you lose the Liferay-specific project
templates that you get with Blade and Workspace.

Blade lets you create modules to develop anywhere, not only in Liferay
Workspace.

Here are some new Gradle features Liferay provides that are independent of
Workspace: 

-   Liferay's [Gradle plugins](/docs/7-0/reference/-/knowledge_base/r/gradle)
-   Buildship plugins in Liferay @ide@
-   Liferay @ide@'s [new Gradle views](/docs/7-0/tutorials/-/knowledge_base/t/using-gradle-in-liferay-ide)
    for developing modules and working with Gradle tasks

Liferay has worked hard to make @product@ IDE-agnostic. There are Liferay module
developers who use IntelliJ and some enjoy using NetBeans.

Finally, you can copy and modify 
[Liferay sample projects](/docs/7-0/tutorials/-/knowledge_base/t/liferay-sample-modules) 
to serve as templates in place of the Blade templates. They're available for
these build systems:

-   Maven
-   Gradle
-   Liferay's Gradle environment based on the `com.liferay.plugin` plugin

Liferay's approach to tooling has vastly improved for @product-ver@. Our tools
help you upgrade to @product-ver@, continue developing traditional plugins the
way you have been, and migrate to optimal development environments. Liferay
Workspace and the improved Maven support facilitate module development.  And
developing on @product@ using other tools is easier than ever. Your tool options
are wide open.
