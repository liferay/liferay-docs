# Activator Template [](id=activator-template)

In this article, you'll learn how to create a Liferay activator as a Liferay
module. To create a Liferay activator via the command line using Blade CLI or
Maven, use one of the commands with the following parameters:

    blade create -t activator -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.activator \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate
a Maven project using Blade CLI.

The template for this kind of project is `activator`. Suppose you want to create
an activator project called `my-activator-project` with a package name of
`com.liferay.docs.activator` and a class name of `Activator`. You could run the
following command to accomplish this:

    blade create -t activator -v 7.1 -p com.liferay.docs.activator -c Activator my-activator-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.activator \
        -DgroupId=com.liferay \
        -DartifactId=my-activator-project \
        -Dpackage=com.liferay.docs.activator \
        -Dversion=1.0 \
        -DclassName=Activator \
        -Dauthor=Joe Bloggs

Note that in your class, you're implementing the
`org.osgi.framework.BundleActivator` interface.

After running the Blade command above, your project's directory structure looks
like this:

- `my-activator-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/activator`
                    - `Activator.java`
            - `resources`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
