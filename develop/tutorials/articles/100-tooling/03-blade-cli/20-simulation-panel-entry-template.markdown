# Simulation Panel Entry Template

In this tutorial, you'll learn how to create a Liferay simulation panel entry as
a Liferay module. To create a simulation panel entry as a module, use a command
with the following parameters: 

    blade create -t simulationpanelentry -p [package name] -c [class name] [project name]

The template for this kind of project is `simulationpanelentry`. Suppose you
want to create a simulation panel entry project called
`my-simulation-panel-entry` with a package name of
`com.liferay.docs.application.list` and a class name of
`SampleSimulationPanelApp`. You could run the following command to accomplish
this:

    blade create -t simulationpanelentry -p com.liferay.docs -c Sample my-simulation-panel-entry

After running the command above, your project's directory structure would look
like this

- `my-simulation-panel-entry`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/application/list`
                    - `SampleSimulationPanelApp.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `simulation_panel.jsp`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[blade.simulation.panel.app](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-gradle/blade.simulation.panel.app)
sample project for a more expanded sample of a control menu entry. Likewise, see
the
[Extending the Simulation Menu](/develop/tutorials/-/knowledge_base/7-0/extending-the-simulation-menu)
tutorial for instructions on customizing a simulation panel entry project.
