# Portlet Toolbar Contributor Template [](id=portlet-toolbar-contributor-template)

In this tutorial, you'll learn how to create a Liferay portlet toolbar
contributor as a Liferay module. To create a portlet toolbar contributor entry
as a module, use a command with the following parameters: 

    blade create -t portlet-toolbar-contributor [-p packageName] [-c className] projectName

The template for this kind of project is `portlet-toolbar-contributor`. Suppose
you want to create a portlet toolbar contributor project called
`my-portlet-toolbar-contributor` with a package name of
`com.liferay.docs.portlet.toolbar.contributor` and a class name of
`SamplePortletToolbarContributor`. You could run the following command to
accomplish this:

    blade create -t portlet-toolbar-contributor -p com.liferay.docs -c Sample my-portlet-toolbar-contributor

After running the command above, your project's directory structure would look
like this

- `my-portlet-toolbar-contributor`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/portlet/toolbar/contributor`
                    - `SamplePortletToolbarContributor.java`
            - `resources`
                - `content`
                    - `Language.properties`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. This generated project, by default, creates
a new button on the Hello World portlet's toolbar. You can visit the
[blade.portlet.toolbar.contributor](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-gradle/blade.portlet.toolbar.contributor)
sample project for a more expanded sample of a portlet toolbar contributor.
