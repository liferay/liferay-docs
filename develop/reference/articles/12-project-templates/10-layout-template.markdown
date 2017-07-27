# Layout Template [](id=layout-template)

In this article, you'll learn how to create a Liferay layout template as a WAR
project. To create a Liferay layout template via the command line using Blade
CLI or Maven, use one of the commands with the following parameters:

    blade create -t layout-template projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.layout.template \
        -DartifactId=[projectName]

The template for this kind of project is `layout-template`. Suppose you want to
create a layout template project called `my-layout-template-project`. You
could run one of the following commands to accomplish this:

    blade create -t layout-template my-layout-template-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.layout.template \
        -DgroupId=com.liferay \
        -DartifactId=my-layout-template-project \
        -Dversion=1.0 \
        -Dauthor=Joe Bloggs

After running the command above, your project's directory structure looks like
this:

- `my-layout-template-project`
    - `gradle` (only in Blade CLI generated projects)
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `webapp`
                - `WEB-INF`
                    - `liferay-layout-templates.xml`
                    - `liferay-plugin-package.properties`
                - `my-layout-template-project.png`
                - `my-layout-template-project.tpl`
    - `build.gradle`
    - `[gradlew|pom.xml]`

The generated WAR is a working layout template and is deployable to a @product@
instance. To build upon the generated layout template, modify the project by
adding logic and additional files to the folders outlined above.
