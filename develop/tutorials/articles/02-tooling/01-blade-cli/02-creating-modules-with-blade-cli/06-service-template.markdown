# Service Template [](id=using-the-service-template)

In this tutorial, you'll learn how to create a Liferay service as a Liferay
module. To create a Liferay service as a module, use a command like the
following:

    blade create -t service -p com.liferay.docs.service -c Service -s com.liferay.portal.kernel.events.LifecycleAction  my-service-project

The command above creates an activator project named `my-service-project` in the
current directory. You set the package name to `com.liferay.docs.service` and
the class name to `Service`. Note that in that class, you're creating a service
of type `com.liferay.portal.kernel.events.LifecycleAction` and implementing that
same service.

After running the command above, your project's directory structure looks like
this:

- `my-service-project`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/service`
                    - `Service.java`
    - `bnd.bnd`
    - `build.gradle`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
