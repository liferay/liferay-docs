# Portlet Template [](id=using-the-portlet-template)

In this tutorial, you'll learn how to create a Liferay portlet application
as a Liferay module. To create a Liferay portlet application as a module,
use a command with the following parameters: 

    blade create -t portlet [-p packageName] [-c className] projectName

The template for this kind of project is `portlet`. Suppose you want to create a
portlet project called `my-portlet-project` with a package name of
`com.liferay.docs.portlet` and a class name of `MyPortlet`. Also, you'd like to
create a service of type `javax.portlet.Portlet` that extends the
`javax.portlet.GenericPortlet` class. Here, *service* means an OSGi service, not
a Liferay API. Another way to say *service type* is to say *component type*. You
could run the following command to accomplish this:

    blade create -t portlet -p com.liferay.docs.portlet -c MyPortlet my-portlet-project

After running the command above, your project's directory structure looks like
this:

- `my-portlet-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/portlet`
                    - `MyPortlet.java`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
