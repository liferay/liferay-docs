# Portlet Toolbar Contributor Template [](id=portlet-toolbar-contributor-template)

In this article, you'll learn how to create a Liferay portlet toolbar
contributor as a Liferay module. To create a portlet toolbar contributor entry
via the command line using Blade CLI or Maven, use one of the commands with the
following parameters:

    blade create -t portlet-toolbar-contributor -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.portlet.toolbar.contributor \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `portlet-toolbar-contributor`. Suppose
you want to create a portlet toolbar contributor project called
`my-portlet-toolbar-contributor` with a package name of
`com.liferay.docs.portlet.toolbar.contributor` and a class name of
`SamplePortletToolbarContributor`. You could run the following command to
accomplish this:

    blade create -t portlet-toolbar-contributor -v 7.1 -p com.liferay.docs -c Sample my-portlet-toolbar-contributor

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.portlet.toolbar.contributor \
        -DgroupId=com.liferay \
        -DartifactId=my-portlet-toolbar-contributor \
        -Dpackage=com.liferay.docs \
        -Dversion=1.0 \
        -DclassName=Sample \
        -Dauthor=Joe Bloggs

After running the Blade command above, your project's directory structure would
look like this

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

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. This generated project, by default, creates
a new button on the Hello World portlet's toolbar. You can visit the
[portlet-toolbar-contributor](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/portlet-toolbar-contributor)
sample project for a more expanded sample of a portlet toolbar contributor.
