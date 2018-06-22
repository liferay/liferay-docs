# Service Template [](id=using-the-service-template)

In this article, you'll learn how to create a Liferay service as a Liferay
module. To create a Liferay service via the command line using Blade CLI or
Maven, use one of the commands with the following parameters:

    blade create -t service -v 7.1 [-p packageName] [-c className] [-s serviceName] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.service \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]
        -DserviceName=[serviceName]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `service`. Suppose you want to create a
service project called `my-service-project` with a package name of
`com.liferay.docs.service` and a class name of `Service`. Also, you'd like to
create a service of type `com.liferay.portal.kernel.events.LifecycleAction` that
also implements that same service. You could run the following command to
accomplish this:

    blade create -t service -v 7.1 -p com.liferay.docs.service -c Service -s com.liferay.portal.kernel.events.LifecycleAction my-service-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.service \
        -DgroupId=com.liferay \
        -DartifactId=my-service-project \
        -Dpackage=com.liferay.docs \
        -Dversion=1.0 \
        -DclassName=Service \
        -DclassName=com.liferay.portal.kernel.events.LifecycleAction \
        -Dauthor=Joe Bloggs

After running the Blade command above, your project's directory structure would
look like this

- `my-service-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/service`
                    - `Service.java`
            - `resources`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
