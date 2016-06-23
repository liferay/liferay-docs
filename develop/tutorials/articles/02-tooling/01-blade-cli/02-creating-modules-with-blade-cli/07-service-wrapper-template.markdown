# Service Wrapper Template [](id=using-the-service-wrapper-template)

In this tutorial, you'll learn how to create a Liferay service wrapper as a
Liferay module. To create a Liferay service wrapper as a module, use a command
like the following:

    blade create -t servicewrapper -p com.liferay.docs.serviceoverride -c UserLocalServiceOverride -s com.liferay.portal.service.UserLocalServiceWrapper service-override

The command above creates a service wrapper project named `service-override` in
the current directory. You set the package name to
`com.liferay.docs.servicewrapper` and your class name to
`UserLocalServiceOverride`. Note that in that class, you're creating a service
of type `com.liferay.portal.kernel.service.ServiceWrapper` and extending the
`UserLocalServiceWrapper` class defined in the `blade` command. Here, *service*
means an OSGi service, not a Liferay API. Another way to say *service type* is
to say *component type*.

After running the command above, your project's directory structure looks like
this:

- `service-override`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/serviceoverride`
                    - `UserLocalServiceOverride.java`
    - `bnd.bnd`
    - `build.gradle`

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
