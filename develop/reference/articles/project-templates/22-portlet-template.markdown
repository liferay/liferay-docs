# Portlet Template [](id=using-the-portlet-template)

In this article, you'll learn how to create a Liferay portlet application
as a Liferay module. To create a Liferay portlet application via the command
line using Blade CLI or Maven, use one of the commands with the following
parameters:

    blade create -t portlet -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `portlet`. Suppose you want to create a
portlet project called `my-portlet-project` with a package name of
`com.liferay.docs.portlet` and a class name of `MyPortlet`. Also, you'd like to
create a service of type `javax.portlet.Portlet` that extends the
`javax.portlet.GenericPortlet` class. Here, *service* means an OSGi service, not
a Liferay API. Another way to say *service type* is to say *component type*. You
could run the following command to accomplish this:

    blade create -t portlet -v 7.1 -p com.liferay.docs.portlet -c MyPortlet my-portlet-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-portlet-project \
        -Dpackage=com.liferay.docs.portlet \
        -Dversion=1.0 \
        -DclassName=MyPortlet \
        -Dauthor=Joe Bloggs

After running the Blade command above, your project's directory structure looks
like this:

- `my-portlet-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/portlet`
                    - `constants`
                        - `MyPortletKeys.java`
                    - `portlet`
                        - `MyPortlet.java`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
