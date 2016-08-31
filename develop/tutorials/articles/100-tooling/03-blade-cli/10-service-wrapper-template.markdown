# Service Wrapper Template [](id=using-the-service-wrapper-template)

In this tutorial, you'll learn how to create a Liferay service wrapper as a
Liferay module. To create a Liferay service wrapper as a module, use a command
with the following parameters:

    blade create -t servicewrapper -p [package name] -c [class name] -s [service wrapper to extend] [project name]

The template for this kind of project is `servicewrapper`. Suppose you want to
create a service wrapper project called `service-override` with a package name of
`com.liferay.docs.serviceoverride` and a class name of
`UserLocalServiceOverride`. Also, you'd like to create a service of type
`com.liferay.portal.kernel.service.ServiceWrapper` that extends the
`com.liferay.portal.service.UserLocalServiceWrapper` class. You could run the
following command to accomplish this:

    blade create -t servicewrapper -p com.liferay.docs.serviceoverride -c UserLocalServiceOverride -s com.liferay.portal.kernel.service.UserLocalServiceWrapper service-override

Here, *service* means an OSGi service, not a Liferay API. Another way to say
*service type* is to say *component type*.

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
