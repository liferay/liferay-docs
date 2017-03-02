# Activator Template [](id=using-the-activator-template)

In this tutorial, you'll learn how to create a Liferay activator as a Liferay
module. To create a Liferay activator as a module, use a command with the
following parameters:

    blade create -t activator [-p packageName] [-c className] projectName

The template for this kind of project is `activator`. Suppose you want to create
an activator project called `my-activator-project` with a package name of
`com.liferay.docs.activator` and a class name of `Activator`. You could run the
following command to accomplish this:

    blade create -t activator -p com.liferay.docs.activator -c Activator my-activator-project

Note that in your class, you're implementing the
`org.osgi.framework.BundleActivator` interface.

After running the command above, your project's directory structure looks like
this:

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
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
