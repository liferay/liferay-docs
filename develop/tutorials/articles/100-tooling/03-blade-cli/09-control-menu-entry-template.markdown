# Control Menu Entry Template [](id=control-menu-entry-template)

In this tutorial, you'll learn how to create a Liferay control menu entry as a
Liferay module. To create a Liferay control menu entry as a module, use a
command with the following parameters: 

    blade create -t control-menu-entry [-p packageName] [-c className] projectName

The template for this kind of project is `control-menu-entry`. Suppose you want to
create a control menu entry project called `my-control-menu-entry-project` with
a package name of `com.liferay.docs.entry.control.menu` and a class name of
`SampleProductNavigationControlMenuEntry`. You could run the following command
to accomplish this:

    blade create -t control-menu-entry -p com.liferay.docs.entry -c Sample my-control-menu-entry-project

After running the command above, your project's directory structure would look
like this

- `my-control-menu-entry-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/entry/control/menu`
                    - `SampleProductNavigationControlMenuEntry.java`
            - `resources`
                - `content`
                    - `Language.properties`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[blade.controlmenuentry](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-gradle/blade.controlmenuentry)
sample project for a more expanded sample of a control menu entry. Likewise, see
the
[Customizing the Control Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-control-menu)
tutorial for instructions on customizing a control menu entry project.
