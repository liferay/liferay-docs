# Theme Template [](id=theme-template)

In this article, you'll learn how to create a Liferay theme as a WAR project. To
create a Liferay theme via the command line using Blade CLI or Maven, use one of
the commands with the following parameters:

    blade create -t theme projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.theme \
        -DartifactId=[projectName]

The template for this kind of project is `theme`. Suppose you want to create a
theme project called `my-theme-project` as a WAR file. You could run the
following command to accomplish this:

    blade create -t theme my-theme-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.theme \
        -DgroupId=com.liferay \
        -DartifactId=my-theme-project \
        -Dversion=1.0

After running the command above, your project's folder structure looks like
this: 

- `my-theme-project`
    - `gradle` (only in Blade CLI generated projects)
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
    - `[gradlew|pom.xml]`

The generated theme is functional and is deployable to a @product@ instance. To
build upon the generated theme, modify the project by adding logic and
additional files to the folders outlined above. To build upon the generated app,
modify the project by adding logic and additional files to the folders outlined
above. You can visit the [Theme sample project](sample project) for a more
expanded sample of a theme. Likewise, see the
[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/themes-and-layout-templates)
tutorial section for more information on creating themes.
