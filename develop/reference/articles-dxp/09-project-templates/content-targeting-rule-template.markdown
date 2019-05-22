# Content Targeting Rule Template [](id=content-targeting-rule-template)

In this article, you'll learn how to create a Liferay Content Targeting Rule
application as a Liferay module. To create a Content Targeting Rule via the
command line using Blade CLI or Maven, use one of the commands with the
following parameters:

    blade create -t content-targeting-rule -p [package name] -c [class name] [project name]

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.content.targeting.rule \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className] \
        -DliferayVersion=7.1

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `content-targeting-rule`. To create a
rule project called `weather` with a package prefix of `com.liferay` and a class
name of `Weather`, use this command: 

    blade create -t content-targeting-rule -p com.liferay -c Weather weather

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.content.targeting.rule \
        -DgroupId=com.liferay \
        -DartifactId=weather \
        -Dpackage=com.liferay \
        -Dversion=1.0 \
        -DclassName=Weather \
        -Dauthor=Joe Bloggs \
        -DliferayVersion=7.1

The command above creates a Content Targeting Rule project named `weather` in
the current folder.  In the class, you're creating a service of
type `com.liferay.content.targeting.api.model.Rule` and extending the
`com.liferay.content.targeting.api.model.BaseJSPRule` class. Here, *service*
means an OSGi service, not a Liferay API. Another way to say *service type* is
to say *component type*.

After running the command above, your project's folder structure looks like
this:

- `weather`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/content/targeting/rule`
                    - `WeatherRule.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `view.jsp`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.