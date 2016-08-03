# Service Builder Template [](id=using-the-service-builder-template)

In this tutorial, you'll learn how to create a Liferay portlet application that
uses Service Builder as Liferay modules. To create a Liferay Service Builder
project, use a command like the following:

    blade create -t servicebuilder -p com.liferay.docs.tasks tasks

The command above creates an Service Builder project named `tasks` in the
current directory. You also set the package name to `com.liferay.docs.tasks`. By
default, the template adds the `portlet` folder to the end of your generated
package name to identify the package as portlet class package.

After running the command above, your project's directory structure looks like
this:

- `tasks`
    - `tasks-api`
        - `bnd.bnd`
        - `build.gradle`
    - `tasks-service`
        - `bnd.bnd`
        - `build.gradle`
        - `service.xml`
    - `tasks-web`
        - `src`
            - `main`
                - `java`
                    - `com/liferay/docs/tasks/portlet`
                        - `TasksPortlet.java`
                - `resources`
                    - `content`
                        - `Language.properties`
                    - `META-INF`
                        - `resources`
                            - `init.jsp`
                            - `view.jsp`
        - `bnd.bnd`
        - `build.gradle`
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
