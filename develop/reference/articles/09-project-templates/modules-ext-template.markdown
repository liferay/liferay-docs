# Modules Ext Template [](id=modules-ext-template)

In this article, you'll learn how to create an Ext module. To create an Ext
module via the command line using Blade CLI or Maven, use one of the commands
with the following parameters:

    blade create -t modules-ext [-p packageName] [-m originalModuleName] [-M originalModuleVersion] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.modules.ext \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DoriginalModuleName=[originalModuleName] \
        -DoriginalModuleVersion=[originalModuleVersion] \
        -DliferayVersion=7.1

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `modules-ext`. Suppose you want to
create an Ext module called `my-ext-module-project` that overrides the
`com.liferay.test.web` module (BSN) with version `1.0.0`. If you have
[Target Platform](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace)
enabled, you're not required to specify the intended module version to override.
Also, the override module has a package path of `com.liferay.docs.test`. You
must use the exact path of the original module when creating an Ext module. You
could run the following command to accomplish this: 

    blade create -t modules-ext -p com.liferay.docs.test -m com.liferay.test.web -M 1.0.0 my-ext-module-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.modules.ext \
        -DartifactId=my-ext-module-project \
        -Dpackage=com.liferay.docs.test \
        -DoriginalModuleName=com.liferay.test.web \
        -DoriginalModuleVersion=1.0.0 \
        -DliferayVersion=7.1

After running the Blade command above, your project's directory structure looks
like this:

- `my-ext-module-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/test`
            - `resources`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

To build upon the generated project, modify the project by adding logic and
additional files to the folders outlined above.
