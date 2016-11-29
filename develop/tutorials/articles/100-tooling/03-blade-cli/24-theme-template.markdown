# Theme Template [](id=theme-template)

In this tutorial, you'll learn how to create a Liferay theme as a WAR project.
To create a Liferay theme, use a command with the following parameters: 

    blade create -t theme projectName

The template for this kind of project is `theme`. Suppose you want to create a
theme project called `my-theme-project` as a WAR file. You could run the
following command to accomplish this:

    blade create -t theme my-theme-project

After running the command above, your project's folder structure looks like
this: 

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

The generated theme is functional and is deployable to a Liferay instance. To
build upon the generated theme, modify the project by adding logic and
additional files to the folders outlined above. To build upon the generated app,
modify the project by adding logic and additional files to the folders outlined
above. You can visit the
[blade.theme](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-gradle/blade.theme)
sample project for a more expanded sample of a theme. Likewise, see the
[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/themes-and-layout-templates)
tutorial section for more information on creating themes.
