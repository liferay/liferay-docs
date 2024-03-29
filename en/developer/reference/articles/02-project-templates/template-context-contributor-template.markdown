---
header-id: template-context-contributor-template
---

# Template Context Contributor Template

[TOC levels=1-4]

In this article, you'll learn how to create a Liferay template context
contributor as a Liferay module. To create a template context contributor via
the command line using Blade CLI or Maven, use one of the commands with the
following parameters:

```bash
blade create -t template-context-contributor [-p packageName] [-c className] projectName
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.template.context.contributor \
    -DartifactId=[projectName] \
    -Dpackage=[packageName] \
    -DclassName=[className] \
    -DliferayVersion=7.2
```

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `template-context-contributor`. Suppose
you want to create a template context contributor project called
`my-template-context-contributor` with a package name of
`com.liferay.docs.theme.contributor` and a class name of
`SampleTemplateContextContributor`. You could run the following command to
accomplish this:

```bash
blade create -t template-context-contributor -p com.liferay.docs -c Sample my-template-context-contributor
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.template.context.contributor \
    -DgroupId=com.liferay \
    -DartifactId=my-template-context-contributor \
    -Dpackage=com.liferay.docs \
    -Dversion=1.0 \
    -DclassName=Sample \
    -Dauthor=Joe Bloggs \
    -DliferayVersion=7.2
```

After running the Blade command above, your project's directory structure would
look like this

- `my-template-context-contributor`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/context/contributor`
                    - `SampleTemplateContextContributor.java`
            - `resources`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[template-context-contributor](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/themes/template-context-contributor)
sample project for a more expanded sample of a template context contributor.
Likewise, see the
[Context Contributors](/docs/7-2/frameworks/-/knowledge_base/f/injecting-additional-context-variables-and-functionality-into-your-theme-templates)
tutorial for instructions on customizing a template context contributor project.
