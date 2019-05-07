# WAR Core Ext

In this article, you'll learn how to create a Liferay WAR core Ext project. To
create a WAR core Ext project via the command line using Blade CLI or Maven, use
one of the commands with the following parameters:

    blade create -t war-core-ext projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.war.core.ext \
        -DartifactId=[projectName] \
        -DliferayVersion=7.1

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `war-core-ext`. Suppose you want to
create a WAR core Ext project called `my-war-core-ext-project`. You could run
the following command to accomplish this:

    blade create -t war-core-ext my-war-core-ext-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.war.core-ext \
        -DgroupId=com.liferay \
        -DartifactId=my-war-core-ext-project \
        -DliferayVersion=7.1

After running the Blade command above, your project's folder structure looks
like this: 

- `my-war-core-ext-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `extImpl`
            - `java`
            - `resources`
                - `META-INF`
                    - `ext-model-hints.xml`
                    - `ext-spring.xml`
                    - `portal-log4j-ext.xml`
        - `extKernel`
            - `java`
            - `resources`
                - `META-INF`
        - `extUtilBridges`
            - `java`
            - `resources`
                - `META-INF`
        - `extUtilJava`
            - `java`
            - `resources`
                - `META-INF`
        - `extUtilTaglib`
            - `java`
            - `resources`
                - `META-INF`
        - `main`
            - `webapp`
                - `WEB-INF`
                    - `ext-web`
                        - `docroot`
                            - `WEB-INF`
                                - `liferay-portlet-ext.xml`
                                - `portlet-ext.xml`
                                - `struts-config-ext.xml`
                                - `tiles-defs-ext.xml`
                                - `web.xml`
                    - `liferay-plugin-package.properties`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

+$$$

**Note:** If you generate a WAR Ext project using Gradle outside of Liferay
Workspace, you must set the `app.server.parent.dir` property in the project's
`gradle.properties`. The app server location is required for this project to
compile.

$$$

The generated WAR Ext project is functional and is deployable to a @product@ instance.
To build upon the generated project, modify the project by adding logic and
additional files to the folders outlined above. Deploying WAR Ext projects is
only supported for limited use cases; it is recommended to leverage provided
extension points offered in @product@. You can visit the
[Customizing Core Functionality with Ext](/develop/reference/-/knowledge_base/7-1/customizing-core-functionality-with-ext)
section for info on how to do this.
