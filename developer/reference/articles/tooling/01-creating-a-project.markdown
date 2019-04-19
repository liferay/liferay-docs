---
header-id: creating-a-project
---

# Creating a Project

[TOC levels=1-4]

Liferay provides many project templates you can use to generate starter projects
formatted in an opinionated way. Visit the [Project
Templates](/docs/7-2/reference/-/knowledge_base/reference/project-templates)
reference section for more information on the available project templates. Each
project template has different configurable options, so be sure to research
a project template before generating it.

You can use your desired tool to generate a project. The following tools are
preconfigured for Liferay project generation:

- [Blade CLI](/docs/7-2/reference/-/knowledge_base/reference/blade-cli)
- [Liferay Dev Studio](/docs/7-2/reference/-/knowledge_base/reference/liferay-dev-studio)
- [Liferay IntelliJ Plugin](/docs/7-2/reference/-/knowledge_base/reference/liferay-intellij-plugin)
- [Maven](/docs/7-2/reference/-/knowledge_base/reference/maven)

It's recommended to create Liferay projects within a
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/reference/liferay-workspace).
Most tools, however, support creating projects in a standalone environment
(except for IntelliJ). Visit the appropriate section to learn how to create a
project with the highlighted tool.

## Blade CLI

1.  Print the available project templates by executing this:

        blade create -l

    Note the project template you want to generate; you'll use it in the next
    step.

2.  Run the following command to create a Gradle project with Blade CLI:

        blade create -t [projectTemplate] [option1] [option2] ... [optionN] [projectName]

| **Note:** If you want to generate a project for a previous version (e.g.,
| Liferay Portal 7.0), you can specify this using the `-v` flag. For example, to
| create a project for Liferay Portal 7.0, you would include `-v 7.0` in your
| create command sequence.

The available configuration options are documented for each
[project template](/docs/7-2/reference/-/knowledge_base/reference/project-templates).
Run `blade create --help` for the entire list of available options.

## Liferay Dev Studio

1.  Navigate to *File* &rarr; *New* &rarr; *Liferay Module Project*.

2.  Specify the project name, location, build type, @product@ version, and
    template type.

    ![Figure 1: The New Liferay Module Project wizard offers project templates for JAR and WAR-based projects.](../../images/liferay-project-wizard.png)

3.  Click *Next* and you're given additional configuration options based on the
    project template you selected. For example, if you selected a template that
    requires a component class, you must configure it in the wizard.

    ![Figure 2: Specify your component class's details in the Portlet Component Class Wizard.](../../images/component-class-wizard.png)

    You can specify your component class's name, package name, and its
    properties. The properties you assign are the ones found in the `@Component`
    annotation's `property = {...}` assignment.

    | **Note:** You can also create a new component class for a pre-existing
    | module project. Navigate to *File* &rarr; *New* &rarr; *Liferay Component
    | Class*. This is a similar wizard to the previous component class wizard,
    | except you can select a component class template. 

4.  Click *Finish* to create your project.

## Liferay IntelliJ Plugin

1.  Navigate to *File* &rarr; *New* &rarr; *Liferay Module*.

    ![Figure 3: Selecting *Liferay Module* opens the New Liferay Modules wizard.](../../images/intellij-new-liferay-module.png)

2.  Select the project you want to create.

    ![Figure 4: Choose the project template to create your module.](../../images/intellij-modules.png)

3.  Configure your project's SDK (i.e., JDK), package name, class name, and
    service name, if necessary. Then click *Next*.

4.  Give your project a name. Then click *Finish*.

## Maven

1.  Execute the following Maven command:

        mvn archetype:generate -Dfilter=liferay

2.  Select the archetype you want to leverage and proceed through the
    configuration prompts.

| **Note:** Maven projects can also be generated using Blade CLI. Follow
| [Blade CLI's](#blade-cli-gradle) project creation instructions and insert the
| `-b maven` parameter in the Blade command.

Archetypes prefixed with `com.liferay.project.templates.[TYPE]` or
`com.liferay.faces.archetype:[TYPE]` are compatible with @product-ver@. All
other Liferay archetypes are legacy archetypes targeted for previous versions of
@product@.

See Maven's
[Archetype Generation](http://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html)
documentation for further details on how to modify the Maven archetype
generation process.
