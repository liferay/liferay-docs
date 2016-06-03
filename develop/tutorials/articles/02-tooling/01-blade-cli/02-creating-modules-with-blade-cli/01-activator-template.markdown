# Activator Template [](id=using-the-activator-template)

In this tutorial, you'll learn how to create a Liferay activator as a Liferay
module. To create a Liferay activator as a module, use a command like the
following:

    blade create -t activator -p com.liferay.docs.activator -c Activator my-activator-project

The command above creates an activator project named `my-activator-project` in
the current directory. You set the package name to `com.liferay.docs.activator`
and your portlet component class name to `Activator`. Note that in that class,
you're implementing the `org.osgi.framework.BundleActivator` interface.

After running the command above, your project's directory structure looks like
this:

- `my-activator-project`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/activator`
                    - `Activator.java`
    - `bnd.bnd`
    - `build.gradle`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
