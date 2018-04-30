# Service Wrapper Template [](id=using-the-service-wrapper-template)

In this article, you'll learn how to create a Liferay service wrapper as a
Liferay module. To create a Liferay service wrapper via the command line using
Blade CLI or Maven, use one of the commands with the following parameters:

    blade create -t service-wrapper -v 7.1 [-p packageName] [-c className] [-s serviceWrapperClass] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.service.wrapper \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className] \
        -DserviceWrapperClass=[serviceWrapperClass]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `service-wrapper`. Suppose you want to
create a service wrapper project called `service-override` with a package name of
`com.liferay.docs.serviceoverride` and a class name of
`UserLocalServiceOverride`. Also, you'd like to create a service of type
`com.liferay.portal.kernel.service.ServiceWrapper` that extends the
`com.liferay.portal.service.UserLocalServiceWrapper` class. You could run the
following command to accomplish this:

    blade create -t service-wrapper -v 7.1 -p com.liferay.docs.serviceoverride -c UserLocalServiceOverride -s com.liferay.portal.kernel.service.UserLocalServiceWrapper service-override

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.service.wrapper \
        -DgroupId=com.liferay \
        -DartifactId=service-override \
        -Dpackage=com.liferay.docs.serviceoverride \
        -Dversion=1.0 \
        -DclassName=UserLocalServiceOverride \
        -DserviceWrapperClass=com.liferay.portal.kernel.service.UserLocalServiceWrapper \
        -Dauthor=Joe Bloggs

Here, *service* means an OSGi service, not a Liferay API. Another way to say
*service type* is to say *component type*.

After running the Blade command above, your project's directory structure looks
like this:

- `service-override`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/serviceoverride`
                    - `UserLocalServiceOverride.java`
            - `resources`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
