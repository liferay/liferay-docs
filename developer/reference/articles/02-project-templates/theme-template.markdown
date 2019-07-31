---
header-id: theme-template
---

# Theme Template

[TOC levels=1-4]

In this article, you'll learn how to create a Liferay theme as a WAR project. To
create a Liferay theme via the command line using Blade CLI or Maven, use one of
the commands with the following parameters:

```bash
blade create -t theme projectName
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.theme \
    -DartifactId=[projectName] \
    -DliferayVersion=7.2
```

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `theme`. Suppose you want to create a
theme project called `my-theme-project` as a WAR file. You could run the
following command to accomplish this:

```bash
blade create -t theme my-theme-project
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.theme \
    -DgroupId=com.liferay \
    -DartifactId=my-theme-project \
    -Dversion=1.0 \
    -DliferayVersion=7.2
```

After running the Blade command above, your project's folder structure looks
like this: 

- `my-theme-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `resources`
                - `resources-importer`
                    - `sitemap.json`
            - `webapp`
                - `css`
                    - `_custom.scss`
                - `WEB-INF`
                    - `liferay-plugin-package.properties`
                    - `web.xml`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated theme is functional and is deployable to a @product@ instance. To
build upon the generated project, modify the project by adding logic and
additional files to the folders outlined above. You can visit the
[simple-theme](/docs/7-1/reference/-/knowledge_base/r/theme) project for a more
expanded sample of a theme. Likewise, see the
[Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) section
for more information on creating themes.
