# Portlet Configuration Icon [](id=portlet-configuration-icon)

In this article, you'll learn how to create a Liferay portlet configuration
icon as a Liferay module. To create a portlet configuration icon via the command
line using Blade CLI or Maven, use one of the commands with the following
parameters:

    blade create -t portlet-configuration-icon [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.portlet.configuration.icon \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

The template for this kind of project is `portlet-configuration-icon`. Suppose
you want to create a portlet configuration icon project called
`my-portlet-config-icon` with a package name of
`com.liferay.docs.portlet.configuration.icon` and a class name of
`SamplePortletConfigurationIcon`. You could run the following command to
accomplish this:

    blade create -t portlet-configuration-icon -p com.liferay.docs -c Sample my-portlet-config-icon

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.portlet.configuration.icon \
        -DgroupId=com.liferay \
        -DartifactId=my-portlet-config-project \
        -Dpackage=com.liferay.docs \
        -Dversion=1.0 \
        -DclassName=Sample \
        -Dauthor=Joe Bloggs

After running the command above, your project's directory structure would look
like this

- `my-portlet-config-icon`
    - `gradle` (only in Blade CLI generated projects)
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/portlet/configuration/icon`
                    - `SamplePortletConfigurationIcon.java`
            - `resources`
                - `content`
                    - `Language.properties`
    - `bnd.bnd`
    - `build.gradle`
    - `[gradlew|pom.xml]`

The generated module is functional and is deployable to a @product@ instance. The
generated module, by default, creates a sample link in the Hello World portlet's
Options menu. To build upon the generated app, modify the project by adding
logic and additional files to the folders outlined above. You can visit the
[portlet-configuration-icon](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/portlet-configuration-icon)
sample project for a more expanded sample of a portlet configuration icon.
