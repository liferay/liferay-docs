# Improved Developer Tooling: Liferay Workspace, Maven Plugins and More [](id=improved-developer-tooling-liferay-workspace-maven-plugins-and-more)

Creating applications is fun when you have the right tools. Here are some key
ingredients:

- Rich templates for stubbing out projects
- Extensible build environments that offer state-of-the-art plugins
- Deployment and runtime management tools
- Application upgrade automation

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
(Workspace) boils over with all these things! It's a Gradle-based development
environment that integrates with
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide) and can be
used in conjunction with other IDEs, such as a "vanilla" Eclipse, IntelliJ, or
NetBeans. You can extend Workspace's Gradle environment with additional
[Gradle plugins](/develop/reference/-/knowledge_base/7-1/gradle) for testing,
code coverage analysis, and more.

If you prefer [Maven](/develop/tutorials/-/knowledge_base/7-1/maven) over
Gradle, you can leverage the
[Maven-based Workspace](/develop/tutorials/-/knowledge_base/7-1/maven-workspace)
instead. @product-ver@'s' lean artifacts and new project
[archetypes](/develop/tutorials/-/knowledge_base/7-1/generating-new-projects-using-archetypes)
and [Maven plugins](/develop/reference/-/knowledge_base/7-1/maven) make
@product@ development with Maven easier than ever.

Workspace comes with
[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli): a command line
tool for creating and deploying projects, managing the runtime environment, and
more. It provides all kinds of 
[project templates](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli#project-templates),
to create modules for developing in any Gradle or Maven environment. 

Liferay's tools also streamline the application upgrade process. Liferay @ide@'s
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
adapts traditional plugins to @product-ver@ APIs. The
[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator)
tool
[migrates](/develop/tutorials/-/knowledge_base/7-1/migrating-a-6-2-theme-to-liferay-7)
themes and layout templates to the new Node.js-based environment and
[adapts](/develop/tutorials/-/knowledge_base/7-1/upgrading-themes#running-the-upgrade-task-for-themes-generator-themes)
them to @product-ver@.

Here are the tooling improvement topics:

- [Moving from the Plugins SDK to Liferay Workspace](#from-the-plugins-sdk-to-liferay-workspace)
- [Developing projects with Liferay Workspace](#developing-projects-with-liferay-workspace)
- [What's new for Maven Users](#whats-new-for-maven-users)
- [Using other build systems and IDEs](#using-other-build-systems-and-ides)

## From the Plugins SDK to Liferay Workspace [](id=from-the-plugins-sdk-to-liferay-workspace)

The Liferay Plugins SDK is deprecated for @product-ver@. You can continue
developing on it, but should plan to eventually move to a new environment.
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
succeeds the Plugins SDK as Liferay's opinionated development environment. You
should use it if you're not using an alternative build system like Gradle or
Maven.

Here are Workspace's key features:

- [Module and component templates](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli#project-templates)
- [Sample projects](/develop/tutorials/-/knowledge_base/7-1/liferay-sample-projects)
- Portal server configurations
- [Project validation]()
- [Integration testing]()
- Folder structure flexibility
- [Commands](/develop/tutorials/-/knowledge_base/7-1/blade-cli) to migrate
  plugins, install @product@ bundles, and start/stop Portal instances

<!-- TODO: Add links above, when available. -Cody -->

The
[plugin upgrade](/develop/tutorials/-/knowledge_base/7-1/upgrading-plugins-to-liferay-7)
tutorials later in this series show how 
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)
automatically adapts existing plugins to @product-ver@. There's also a tutorial
that demonstrates how you can optionally
[migrate traditional plugins to Workspace](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications).

![Figure 1: Liferay @ide@'s Code Upgrade Tool automates many aspects of the plugin upgrade process.](../../../images/improved-tooling-code-upgrade-configure-proj.png)

Here's an example Workspace folder structure:

![Figure 2: Liferay Workspace aggregates projects to use the same server configurations and Gradle build environment.](../../../images/improved-tooling-workspace-structure.png)

Here's the Workspace anatomy:

- `bundles/` (generated) &rarr; default folder for @product@ bundles
- `configs/` &rarr; holds Portal server configurations
- `gradle/` &rarr; holds the Gradle wrapper files
- `modules/` &rarr; holds module projects
- `plugins-sdk/` (generated) &rarr; holds plugins from previous releases
- `themes/` &rarr; holds Node.js-based theme projects
- `wars/` &rarr; holds traditional web application projects
- `build.gradle` &rarr; common Gradle build file
- `gradle-local.properties` &rarr; sets user-specific properties for your
  workspace
- `gradle.properties` &rarr; specifies the Portal server configuration and
  project locations
- `gradlew / gradlew.bat` &rarr; executes the Gradle command wrapper
- `pom.xml` (only in Workspaces generated by Maven) &rarr; common Maven build
  file
- `settings.gradle` &rarr; applies plugins to the Workspace and configures its
  dependencies

Workspace module, theme, and WAR projects use the same Portal server
configurations. Developers can create configurations for module development,
user acceptance testing, production, and more.

Each subfolder under `configs` holds a Portal server configuration defined by
its `portal-ext.properties` file. Gradle property
`liferay.workspace.environment` in Workspace's `gradle.properties` file
specifies the configuration to use. See the
[Testing Projects](/develop/tutorials/-/knowledge_base/7-1/development-lifecycle-for-a-liferay-workspace#testing-projects)
section for more details.

Other Gradle properties let you set root locations for the @product@ bundle,
modules, themes, and a Plugins SDK. See the
[Gradle Workspace Properties](/develop/tutorials/-/knowledge_base/7-1/configuring-a-liferay-workspace#gradle-workspace-properties)
section for a list of all available Workspace properties.

### Workspace Folder Structure Properties [](id=workspace-folder-structure-properties)

 Property | Description |
:--------- | :------------- |
`liferay.workspace.environment` | Name of a `configs` subfolder holding the Portal server configuration to use |
`liferay.workspace.home.dir` | @product@ bundle root folder |
`liferay.workspace.modules.dir` | Module projects root folder |
`liferay.workspace.plugins.sdk.dir` | Plugins SDK root folder |
`liferay.workspace.themes.dir` | Theme projects root folder |
`liferay.workspace.wars.dir` | WAR-style projects root folder |

Workspace has Gradle tasks equivalent to the Plugins SDK Ant targets.

### Plugins SDK to Workspace Task Map [](id=plugins-sdk-to-workspace-task-map)

 Plugins SDK Ant Target | Workspace Gradle Task | Task Description |
:--------------------------- | :-------------------------- | :---------------- |
`build-css` | `buildCSS` | Builds CSS files |
`build-lang` | `buildLang` | Translates language keys using Language Builder |
`build-service` | `buildService` | Runs Service Builder |
`clean` | `clean` | Deletes all build outputs |
`compile` | `classes` | Compiles classes |
`deploy` | `deploy` (or `blade deploy`) | Installs the current project to @product@'s OSGi framework |
`jar` | `jar` | Compiles the project and packages it as a JAR file |
`war` | `assemble` | Assembles project output |

Other Workspace Gradle tasks provide additional functionality.

Workspace Gradle Task | Task Description |
:-------------------------- | :------------------ |
`buildDB` | Builds database SQL scripts from the generic SQL scripts |
`buildSoy` | Compiles Closure Templates in JavaScript functions |
`components` | Lists the project's components |
`configJSModules` | Generates the config file needed to load AMD files via combo loader in @product@
`dependencies` | Lists the project's declared dependencies |
`formatSource` | Runs Liferay Source Formatter to format project files
`initBundle` | Downloads and installs a @product@ bundle |
`model` | Lists the project's configuration model |
`projects` | Lists the project's sub-projects |
`properties` | Lists the project's
`replaceSoyTranslation` | Replaces `goog.getMsg` definitions
`transpileJS` | Transpiles the project's JavaScript files |

This is just a subset of available Gradle commands in a Liferay Workspace. Run
`gradlew tasks` from a project in workspace for a full list of Gradle commands.

<!-- TODO: Mention Workspace Test Integration section here for info on test
commands once article is available. -Cody -->

Next, learn how Workspace facilitates module development. 

## Developing Modules with Liferay Workspace [](id=developing-projects-with-liferay-workspace)

Workspace is a great Liferay module development environment because of these
features: 

- Templates that bootstrap module creation
- Gradle and Maven build systems for managing dependencies and assembling
  modules
- Module deployment and runtime management capabilities

[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli) (Blade), which is
a part of Workspace, has over thirty templates for Gradle and Maven-based module
projects--and more are being added. The templates stub out classes and resource
files for you to fill in with business logic and key information.

Here are some of the template's names:

- Activator
- API
- Content Targeting Report
- Content Targeting Rule
- Content Targeting Tracking Action
- Control Menu Entry
- MVC Portlet
- Panel App
- Portlet
- Portlet Configuration Icon
- Portlet Provider
- Portlet Toolbar Contributor
- Service
- Service Builder
- Service Wrapper
- Simulation Panel Entry
- Template Context Contributor
- etc..

[Blade creates modules](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli)
based on these templates. For a full list of these templates, visit the
[Project Templates](/develop/reference/-/knowledge_base/7-1/project-templates)
reference section.

For example, the following Blade command creates a Liferay MVC Portlet module
called `my-module`:

    blade create -t mvc-portlet -p com.liferay.docs.mymodule -c MyMvcPortlet my-module

Liferay @ide@'s 
[module project wizard](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-liferay-ide)
creates Workspace modules from the templates too.

![Figure 3: Liferay @ide@ lets developers select templates to stub out modules.](../../../images/improved-tooling-module-wizard.png)

Liferay @ide@'s component wizard facilitates 
[creating component classes](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-liferay-ide#creating-component-classes)
for portlets, service wrappers, Struts actions, and more.

![Figure 4: Liferay @ide@'s component wizard facilitates creating component classes.](../../../images/improved-tooling-component-wizard.png)

Building and deploying modules in a Workspace is a snap using
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/deploying-modules-with-liferay-ide)
and
[Blade](/develop/tutorials/-/knowledge_base/7-1/deploying-modules-with-blade-cli).
Workspace uses BndTools to generate each module's OSGi headers in a
`META-INF/MANIFEST.MF` file. Workspace deploys modules to the OSGi container
using Felix File Install commands.

Liferay @ide@ lets you deploy modules by dragging them onto your Portal server.

![Figure 5: Liferay @ide@ lets you deploy modules using drag-and-drop.](../../../images/improved-tooling-drag-n-drop-onto-server.png)

To learn more about Workspace and using it in Liferay @ide@, see
[this tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-a-liferay-workspace-with-liferay-ide). 

In a terminal, you can deploy modules using Blade's `deploy` command. For
example, the following command deploys the current module and 
["watches" for module changes to redeploy automatically](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#redeploying-module-changes-automatically).

    blade deploy -w

Make sure to check out the tutorial
[Starting Module Development](/develop/tutorials/-/knowledge_base/7-1/starting-module-development). 
for even more information on module development.

Next, you'll learn new features for developing on @product@ using Maven.

## What's New for Maven Users [](id=whats-new-for-maven-users)

Liferay Portal 7.0+ and Liferay DXP fully support Maven development and offers
several new and improved features: 

- [Liferay Workspace for Maven](/develop/tutorials/-/knowledge_base/7-1/maven-workspace)
- New archetypes
- New Maven plugins
- More granular dependency management

The new archetype `com.liferay.project.templates.workspace` generates a Liferay
Workspace that includes a POM file for developing in Workspace using Maven. You
can develop modules and themes in the Workspace subfolders. 

@product-ver@ provides many new Maven archetypes for various Liferay module
projects. There are over thirty
[Maven archetypes](/develop/reference/-/knowledge_base/7-1/project-templates)
for @product-ver@, and more are in development. Here are some popular ones:

- Configuration Icons
- Fragments
- Menu Buttons
- Portlets
    - MVC
    - npm
    - Soy
    - Spring MVC
- Service Builder
- Themes
- etc...

Liferay's Maven archetypes cover many different Liferay frameworks and
service types. These make Maven a first-class tool for creating Liferay modules
and themes. Visit the 
[Generating New Projects Using Archetypes](/develop/tutorials/-/knowledge_base/7-1/generating-new-projects-using-archetypes)
tutorial to learn more about Liferay's Maven archetypes and how to use them.

Liferay also provides several new and updated 
[Maven plugins](/develop/reference/-/knowledge_base/7-1/maven) that simplify the
build process. For example, the following plugins build style sheets, services,
and themes respectively:

- [CSS Builder](/develop/tutorials/-/knowledge_base/7-1/compiling-sass-files-in-a-maven-project)
- [Service Builder](/develop/tutorials/-/knowledge_base/7-1/using-service-builder-in-a-maven-project)
- [Theme Builder](/develop/tutorials/-/knowledge_base/7-1/building-themes-in-a-maven-project)

@product-ver@'s modularity provides a more granular dependency management
experience. You no longer need to depend on `portal-impl` or `portal-service`
(now `portal-kernel`) for everything. For example, to use @product@'s Wiki
framework, you need only depend on the Wiki module. You set dependencies on
concise modules that provide the functionality you want without inheriting extra
baggage.

Liferay's new Workspace environment, Maven archetypes, Maven plugins, and
streamlined modules make developing on @product@ easier than ever. To learn
more, see the 
[Maven tutorials](/develop/tutorials/-/knowledge_base/7-1/maven).

## Using Other Build Systems and IDEs [](id=using-other-build-systems-and-ides)

@product@ is tool agnostic--you can use whatever tools you like to develop on
it. You can use any IDE and even use Gradle, Bnd, or BndTools if you don't want
to use Workspace. The drawback is you lose the Liferay-specific project
templates that you get with Blade and Workspace.

Blade lets you create modules to develop anywhere, not only in Liferay
Workspace.

Here are some new Gradle features Liferay provides that are independent of
Workspace: 

- Liferay's [Gradle plugins](/develop/reference/-/knowledge_base/7-1/gradle)
- Buildship plugins in Liferay @ide@
- Liferay @ide@'s
  [new Gradle views](/develop/tutorials/-/knowledge_base/7-1/using-gradle-in-liferay-ide)
  for developing modules and working with Gradle tasks

Liferay has worked hard to make @product@ IDE-agnostic. There are Liferay module
developers who use IntelliJ and some enjoy using NetBeans.

Finally, you can copy and modify 
[Liferay sample projects](/develop/tutorials/-/knowledge_base/7-1/liferay-sample-projects) 
to serve as templates in place of the Blade templates. They're available for
these build systems:

- Gradle
- Liferay Workspace
- Maven

Liferay's approach to tooling has vastly improved for @product-ver@. Our tools
help you upgrade to @product-ver@, continue developing traditional plugins the
way you have been, and migrate to optimal development environments. Liferay
Workspace and the improved Maven support facilitate module development. And
developing on @product@ using other tools is easier than ever. Your tool options
are wide open.
