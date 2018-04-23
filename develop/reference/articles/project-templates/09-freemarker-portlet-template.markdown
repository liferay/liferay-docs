# FreeMarker Portlet Template [](id=freemarker-portlet-template)

In this article, you'll learn how to create a Liferay FreeMarker portlet
application as a Liferay module. To create a Liferay FreeMarker portlet
application via the command line using Blade CLI or Maven, use one of the
commands with the following parameters:

    blade create -t freemarker-portlet -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.freemarker.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `freemarker-portlet`. Suppose you want
to create a FreeMarker portlet project called `my-freemarker-portlet-project`
with a package name of `com.liferay.docs.freemarkerportlet` and a class name of
`MyFreemarkerPortlet`. Also, you'd like to create a service of type
`javax.portlet.Portlet` that extends the
`com.liferay.util.bridges.freemarker.FreeMarkerPortlet` class. Here, *service*
means an OSGi service, not a Liferay API. Another way to say *service type* is
to say *component type*. You could run the following command to accomplish this:

    blade create -t freemarker-portlet -v 7.1 -p com.liferay.docs.freemarkerportlet -c MyFreemarkerPortlet my-freemarker-portlet-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.freemarker.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-freemarker-portlet-project \
        -Dpackage=com.liferay.docs.freemarkerportlet \
        -Dversion=1.0 \
        -DclassName=MyFreemarkerPortlet \
        -Dauthor=Joe Bloggs

After running the Blade command above, your project's directory structure looks
like this:

- `my-freemarker-portlet-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/freemarkerportlet`
                    - `constants`
                        - `MyFreemarkerPortletKeys.java`
                    - `portlet`
                        - `MyFreemarkerPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `css`
                            - `main.scss`
                - `templates`
                    - `init.ftl`
                    - `view.ftl`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
