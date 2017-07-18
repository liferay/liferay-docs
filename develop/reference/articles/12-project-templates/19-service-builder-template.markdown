# Service Builder Template [](id=using-the-service-builder-template)

In this article, you'll learn how to create a Liferay portlet application that
uses Service Builder as Liferay modules. To create a Liferay Service Builder
project via the command line using Blade CLI or Maven, use one of the commands
with the following parameters:

    blade create -t service-builder [-p packageName] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.service.builder \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DapiPath=[apiPath]

The template for this kind of project is `service-builder`. Suppose you want to
create a Service Builder project called `tasks` with a package name of
`com.liferay.docs.tasks`. You could run the following command to accomplish
this:

    blade create -t service-builder -p com.liferay.docs.tasks tasks

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.service.builder \
        -DgroupId=com.liferay \
        -DartifactId=tasks \
        -Dpackage=com.liferay.docs.tasks \
        -Dversion=1.0 \
        -DapiPath=com.liferay.api.path

<!-- How do we set apiPath param in blade command? -Cody -->

This task creates the `tasks-api` and `tasks-service` folders. In many cases, a
Service Builder project also requires a `-web` folder to hold, for example,
portlet classes. This should be created manually. After running the command
above, your project's directory structure looks like this:

- `tasks`
    - `gradle` (only in Blade CLI generated projects)
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `tasks-api`
        - `bnd.bnd`
        - `build.gradle`
    - `tasks-service`
        - `bnd.bnd`
        - `build.gradle`
        - `service.xml`
    - `build.gradle`
    - `[gradlew|pom.xml]`
    - `settings.gradle`

To generate your service and API classes for the `*-api` and `*-service`
modules, replace the `service.xml` file in the `*-service` module. Depending on
your build tool, you can build your services by executing

    blade gw buildService

or

    mvn service-builder:build

from the `tasks` root directory. Note that `blade gw` only works if the Gradle
wrapper can be detected. To ensure the availability of the Gradle wrapper, be
sure to work in a Liferay Workspace.

The `mvn service-builder:build` command only works if you're using the
`com.liferay.portal.tools.service.builder` plugin version 1.0.145+. Maven
projects using an earlier version of the Service Builder plugin should update
their POM accordingly.

The generated module is functional and is
deployable to a @product@ instance. To build upon the generated app, modify the
project by adding logic and additional files to the folders outlined above.

## Related Topics  [](id=related-topics)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)

[Using Service Builder in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/using-service-builder-in-a-maven-project)

[Service Builder with Maven](/develop/tutorials/-/knowledge_base/7-0//develop/reference/-/knowledge_base/7-0/service-builder-with-maven)
