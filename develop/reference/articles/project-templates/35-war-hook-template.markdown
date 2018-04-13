# WAR Hook Template [](id=war-hook-template)

In this article, you'll learn how to create a Liferay WAR hook project. To
create a Liferay WAR hook via the command line using Blade CLI or Maven, use one
of the commands with the following parameters:

    blade create -t war-hook -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.war.hook \
        -DartifactId=[projectName]
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `war-hook`. Suppose you want to create
a WAR hook project called `my-war-hook-project` with a package name of
`com.liferay.docs` and a class name of `MyWarHook`. You could run the following
command to accomplish this:

    blade create -t war-hook -v 7.1 -p com.liferay.docs -c MyWarHook my-war-hook-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.war.hook \
        -DgroupId=com.liferay \
        -DartifactId=my-war-hook-project \
        -Dpackage=com.liferay.docs \
        -DclassName=MyWarHook \
        -Dversion=1.0

After running the Blade command above, your project's folder structure looks
like this: 

- `my-war-hook-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs`
                    - `MyWarHookLoginPostAction`
                    - `MyWarHookStartupAction`
            - `resources`
                - `portal.properties`
            - `webapp`
                - `WEB-INF`
                    - `liferay-hook.xml`
                    - `liferay-plugin-package.properties`
                    - `web.xml`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated WAR hook is functional and is deployable to a @product@ instance.
To build upon the generated project, modify the project by adding logic and
additional files to the folders outlined above. Deploying WAR hooks is
supported for @product-ver@, however, it is recommended to optimize your WAR
hooks to fragments or other applicable module projects. You can visit the
[Customizing](/develop/tutorials/-/knowledge_base/7-0/customizing) section for
info on how to do this for many project types. See the
[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
section for more information on WAR hooks.
