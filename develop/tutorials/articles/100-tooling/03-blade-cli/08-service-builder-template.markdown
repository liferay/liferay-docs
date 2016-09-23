# Service Builder Template [](id=using-the-service-builder-template)

In this tutorial, you'll learn how to create a Liferay portlet application that
uses Service Builder as Liferay modules. To create a Liferay Service Builder
project, use a command with the following parameters: 

    blade create -t servicebuilder -p [package name] [project name]

The template for this kind of project is `servicebuilder`. Suppose you want to
create a Service Builder project called `tasks` with a package name of
`com.liferay.docs.tasks`. You could run the following command to accomplish
this:

    blade create -t servicebuilder -p com.liferay.docs.tasks tasks

This task creates the `tasks-api` and `tasks-service` folders. In many cases, a
Service Builder project also requires a `-web` folder to hold, for example,
portlet classes. This should be created manually. After running the command
above, your project's directory structure looks like this:

- `tasks`
    - `tasks-api`
        - `bnd.bnd`
        - `build.gradle`
    - `tasks-service`
        - `bnd.bnd`
        - `build.gradle`
        - `service.xml`
    - `build.gradle`
    - `settings.gradle`

To generate your service and API classes for the `*-api` and `*-service`
modules, replace the `service.xml` file in the `*-service` module. Then run
`blade gw buildService` from the `tasks` root directory to build your services.
Note that `blade gw` only works if the Gradle wrapper can be detected. To ensure
the availablity of the Gradle wrapper, be sure to work in a Liferay Workspace.
The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
