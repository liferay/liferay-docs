# API Template [](id=api-template)

In this tutorial, you'll learn how to create a Liferay API as a Liferay module.
To create a Liferay API as a module, use a command with the following
parameters: 

    blade create -t api [-p packageName] [-c className] projectName

The template for this kind of project is `api`. The `api` template creates a
simple `api` module with an empty public interface. For example, suppose you
want to create an API project called `my-api-project` with a package name of
`com.liferay.docs.api` and a class name of `MyApi`. You could run the following
command to accomplish this:

    blade create -t api -p com.liferay.docs -c MyApi my-api-project

After running the command above, your project's directory structure looks like
this:

- `my-api-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/api`
                    - `MyApi.java`
            - resources
                - `com/liferay/docs/api`
                    - `packageinfo`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
