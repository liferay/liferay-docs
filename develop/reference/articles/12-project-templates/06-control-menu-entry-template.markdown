# Control Menu Entry Template [](id=control-menu-entry-template)

In this article, you'll learn how to create a Liferay Control Menu entry as a
Liferay module. To create a Liferay Control Menu entry via the command line
using Blade CLI or Maven, use one of the commands with the following parameters:

    blade create -t control-menu-entry [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.control.menu.entry \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

The template for this kind of project is `control-menu-entry`. Suppose you want to
create a control menu entry project called `my-control-menu-entry-project` with
a package name of `com.liferay.docs.entry.control.menu` and a class name of
`SampleProductNavigationControlMenuEntry`. You could run the following command
to accomplish this:

    blade create -t control-menu-entry -p com.liferay.docs.entry -c Sample my-control-menu-entry-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.control.menu.entry \
        -DgroupId=com.liferay \
        -DartifactId=my-control-menu-entry-project \
        -Dpackage=com.liferay.docs.entry \
        -Dversion=1.0 \
        -DclassName=Sample \
        -Dauthor=Joe Bloggs

After running the command above, your project's directory structure would look
like this:

- `my-control-menu-entry-project`
    - `gradle` (only in Blade CLI generated projects)
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
    - `[gradlew|pom.xml]`

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[blade.controlmenuentry](/develop/reference/-/knowledge_base/7-0/control-menu-entry)
sample project for a more expanded sample of a Control Menu entry. Likewise, see
the
[Customizing the Control Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-control-menu)
tutorial for instructions on customizing a Control Menu entry project.
