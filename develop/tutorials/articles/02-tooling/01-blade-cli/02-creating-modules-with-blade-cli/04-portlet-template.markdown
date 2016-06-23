# Portlet Template [](id=using-the-portlet-template)

In this tutorial, you'll learn how to create a Liferay portlet application
as a Liferay module. To create a Liferay portlet application as a module,
use a command like the following:

    blade create -t portlet -p com.liferay.docs.portlet -c MyPortlet my-portlet-project

The command above creates a portlet project named `my-portlet-project` in the
current directory. You set the package name to `com.liferay.docs.portlet` and
your portlet component class name to `MyPortlet`. Note that in that class,
you're creating a service of type `javax.portlet.Portlet` and extending the
`javax.portlet.GenericPortlet` class. Here, *service* means an OSGi service, not
a Liferay API. Another way to say *service type* is to say *component type*.

After running the command above, your project's directory structure looks like
this:

- `my-portlet-project`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/portlet`
                    - `MyPortlet.java`
    - `bnd.bnd`
    - `build.gradle`

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
