# Starting Module Development [](id=starting-module-development)

Developing modules for @product@ requires:

-   **Creating a folder structure**: A good folder structure facilitates
    evolving and maintaining code, especially in collaboration. Popular tools
    use pre-defined folder structures familiar to developers. 

-   **Writing code and configuration files**: A manifest, Java classes, and
    resources. Modules stubbed out with them let developers focus on
    implementing logic.

-   **Compilation**: Acquiring dependencies and building the module. Common
    build tools that manage dependencies include Gradle, Maven, and Ant/Ivy.

-   **Deployment**: Interacting with the runtime environment to install,
    monitor, and modify modules.

There are several good build tools for developing modules on @product@. This
tutorial demonstrates starting a new module using Liferay Workspace. It's
Liferay's opinionated build environment based on Gradle and BndTools that
simplifies module development and automates much of it.

+$$$

**Note**: Liferay supports developers using their build tool of choice. In
addition to providing Liferay Workspace for those who don't already have a
preferred build environment, Liferay provides good support for Maven, Gradle,
and BndTools. The following tutorials and samples demonstrate developing in
these environments.

-   [Maven tutorials](/develop/tutorials/-/knowledge_base/7-0/maven)
    and [samples](/develop/tutorials/-/knowledge_base/7-0/liferay-sample-modules)

-   [Gradle in Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/using-gradle-in-liferay-ide)
    and [samples](/develop/tutorials/-/knowledge_base/7-0/liferay-sample-modules)

-   [BndTools samples](/develop/tutorials/-/knowledge_base/7-0/liferay-sample-modules)

$$$

+$$$

**Note**: Themes and Layout Templates are not built as modules. To learn how to
create them, see the [Themes and Layout Templates tutorials](/develop/tutorials/-/knowledge_base/7-0/themes-and-layout-templates).

$$$

Here are the steps for starting module development:

1. [Set up a Liferay Workspace](#setting-up-a-liferay-workspace)

2. [Create a module](#creating-a-module)

3. [Build and deploy the module](#building-and-deploying-a-module)

On completing this tutorial you'll have created a module and deployed it to a
local @product@ bundle. 

## Setting up a Liferay Workspace [](id=setting-up-a-liferay-workspace)

Creating and configuring a Liferay Workspace (Workspace) is straightforward
using a tool called Blade CLI (Blade). Blade is a command line tool that creates
Workspaces and performs common tasks. 

Follow these steps to install Blade if you don't already have it:

1.  Install the [Java Package Manager](http://jpm4j.org/#!/md/install) (JPM).

2.  From a terminal, install [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)
    using JPM:

        (sudo) jpm install com.liferay.blade.cli

The `blade` executable is now in the system path.

You can create a Workspace in the current directory by executing this command: 

	blade init <workspaceName>

You've created a Workspace! Its directory structure looks like the one shown in
the figure below. 

![Figure 1: Liferay Workspace aggregates projects so they can leverage the Gradle build environment.](../../../images/starting-module-dev-workspace-structure.png)

Workspace can be configured to use a @product@ installation bundle anywhere on
the local file system. The `liferay.workspace.home.dir` property in
`gradle.properties` sets the default bundle location to a folder
`<workspace>/bundles` (not yet created). For convenience it's suggested to 
[install a @product@ bundle](/discover/deployment/-/knowledge_base/7-0/preparing-for-install)
there. If you install it to a different location, uncomment the
`liferay.workspace.home.dir` property and set it to that location.

+$$$

**Note**: User interfaces in Liferay @ide@ lets developers create and import
Liferay Workspace projects. 

To create a project, follow the tutorial [Creating a Liferay Workspace Project with Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-liferay-ide).

To import a project, use the wizard from *File &rarr; Import &rarr; Liferay &rarr; Liferay Workspace Project*.

$$$

The Workspace is ready for creating modules.

## Creating a Module [](id=creating-a-module)

Blade provides module *templates* and module *samples*. The templates stub out
files for different types of modules. The samples can be generated in a
Workspace and demonstrate many module types. Developers can use templates and
samples to develop modules.

### Using Module Templates [](id=using-module-templates)

The Blade command `blade create -l` lists the module templates.

![Figure 2: Blade's `create` command generates a module based on a template. Executing `create -l` lists the template names.](../../../images/starting-module-dev-blade-templates.png)

+$$$

**Note**: Liferay @ide@'s module wizard lets developers select a template for
their module project. For details, see the tutorial 
[Creating a Module with Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-liferay-ide). 

$$$

Here's the command syntax for creating a module:

    blade create [options] [moduleName]

The `blade help [commandName]` command describes the specified command. To
describe the `create` command, for example, execute this: 

    blade help create

Here's an example of creating a Liferay MVC Portlet module:

    blade create -t mvc-portlet -p com.liferay.docs.mymodule -c MyMvcPortlet my-module

Module projects are created in the `modules` folder by default.

Here's the module project anatomy:

-   `src/main/java/` &rarr; Java package root

-   `src/main/resources/content/` (optional) &rarr; Language resource bundle
    root 

-   `src/main/resources/META-INF/resources/` (optional) &rarr; Root for UI
    templates, such as JSPs 

-   `bnd.bnd` &rarr; Specifies essential OSGi module manifest headers

-   `build.gradle` &rarr; Configures dependencies and more using Gradle

The figure below shows an MVC portlet module project.

![Figure 3: Liferay modules use the standard Maven directory structure.](../../../images/starting-module-dev-module-structure.png)

Sample modules are another helpful development resource.

### Using Module Samples [](id=using-module-samples)

An alternative to creating a module from a template is to generate a *sample*
module. Developers can examine or modify sample modules as desired. 

This command lists the sample names:

    blade samples 

The figure below shows the listing.

![Figure 4: The `blade samples` command lists the names of sample modules developers can create, examine, and modify to meet their needs.](../../../images/starting-module-dev-blade-samples.png)

Here's the Blade samples command syntax:

    blade samples <sampleName>

Samples are created to the `samples` folder.

Building a module and deploying it to @product@ is easy.

## Building and Deploying a Module [](id=building-and-deploying-a-module)

Liferay Workspace provides Gradle tasks for building and deploying modules.
Blade's `blade gw` command solves a common need in Gradle projects: invoking the
Gradle wrapper from any project directory. You can use `blade gw` just as you
would invoke `gradlew`, without having to specify the wrapper path.

+$$$

**Note**: For an even simpler Gradle wrapper command, install *gw*.

`(sudo) jpm install gw@1.0.1`

Usage: `gw <task>`

$$$

In a module folder, execute this command to list the Gradle tasks available:

    blade gw tasks

Workspace uses BndTools to generate the module's OSGi `MANIFEST.MF` file and
package it in the module JAR. To compile the module and generate the module JAR,
execute the `jar` Gradle task: 

    blade gw jar

The generated JAR is in the module project's `build/libs` folder and ready for
deployment to @product@. 

Start your @product@ server, if you haven't already started it. 

+$$$

**Tip**: To open a new terminal window and the Workspace's @product@ server
(bundled with Tomcat or JBoss/Wildfly), execute this command: 

`blade server start -b`

$$$

Blade can deploy modules to any local @product@ server. It communicates with
@product@'s OSGi framework using Felix Gogo shell and deploys modules directly
to the OSGi container using Felix File Install commands. Unless the developer
specifies a Gogo shell port explicitly (e.g., `blade deploy -p <port>`), the
command uses the default port `11311`. 

To deploy the module, execute this command:

	blade deploy

Also Blade lets developers deploy all modules in the current folder tree. To
deploy all modules in a Workspace's modules folder, for example, execute
`blade deploy` in the `<workspace>/modules` folder. 

+$$$

**Note**: Liferay @ide@ lets developers deploy modules by dragging them from the
Package Explorer onto the @product@ server. @ide@ provides access to 
[Liferay Workspace Gradle tasks](/develop/tutorials/-/knowledge_base/7-0/managing-module-projects-with-liferay-ide)
too. 

![Figure 5: Liferay @ide@ lets developers deploy modules using drag-and-drop.](../../../images/starting-module-dev-drag-module.png)

$$$

If you deployed a portlet module, it's available in the @product@ UI under the
application category and name you specified via the portlet component's
`com.liferay.portlet.display-category` and `javax.portlet.display-name`
properties in the `@Component` annotation. 

![Figure 6: Here's a bare-bones portlet based on one of Liferay's module templates.](../../../images/starting-module-dev-portlet.png)

## Redeploying Module Changes Automatically [](id=redeploying-module-changes-automatically)

Blade lets developers set a *watch* on changes to a module project's output
files. If they're modified, Blade redeploys the module automatically. To set a
watch on a module at deployment, execute this command in the module project:

	blade deploy -w

Here's output from deploying (and watching) a module named *com.liferay.docs.mymodule*: 

	E:\workspaces\my-liferay-workspace\modules\my-module-project>blade deploy -w

	:modules:my-module-project:compileJava UP-TO-DATE
	:modules:my-module-project:buildCSS UP-TO-DATE
	:modules:my-module-project:processResources UP-TO-DATE
	:modules:my-module-project:transpileJS SKIPPED
	:modules:my-module-project:configJSModules SKIPPED
	:modules:my-module-project:classes UP-TO-DATE
	:modules:my-module-project:jar UP-TO-DATE
	:modules:my-module-project:assemble UP-TO-DATE
	:modules:my-module-project:build

	BUILD SUCCESSFUL

	Total time: 2.962 secs
	install file:/E:/workspaces/my-liferay-workspace/modules/my-module-project/build/libs/com.liferay.docs.mymodule-1.0.0.jar
	Bundle ID: 505
	start 505

	Scanning E:\workspaces\my-liferay-workspace\modules\my-module-project

	...

	Waiting for changes to input files of tasks... (ctrl-d then enter to exit)

Output from the `blade deploy -w` command indicates that the module is installed
and started, reports the module's OSGi bundle ID, and stands ready to redeploy
the module if its output files change. 

Congratulations on a great start to developing your module!

## Related Articles [](id=related-articles)

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)

[Tooling](/develop/tutorials/-/knowledge_base/7-0/tooling)

[OSGi Basics for Liferay Development](/develop/tutorials/-/knowledge_base/7-0/osgi-basics-for-liferay-development)

[Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

