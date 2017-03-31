# Content Targeting Report Template [](id=content-targeting-report-template)

In this article, you'll learn how to create a Liferay Content Targeting Report
application as a Liferay module. To create a Content Targeting Report via the
command line using Blade CLI or Maven, use one of the commands with the
following parameters:

    blade create -t content-targeting-report -p [package name] -c [class name] [project name]

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.content.targeting.report \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

The template for this kind of project is `content-targeting-report`. To create a
report project called `hits-by-country` with a package prefix of `com.liferay`
and a class name of `HitsByCountryReport`, use this command: 

    blade create -t content-targeting-report -p com.liferay -c HitsByCountry hits-by-country

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.content.targeting.report \
        -DgroupId=com.liferay \
        -DartifactId=hits-by-country \
        -Dpackage=com.liferay \
        -Dversion=1.0 \
        -DclassName=HitsByCountry \
        -Dauthor=Joe Bloggs

The command above creates a Content Targeting Rule project named
`hits-by-country` in the current folder. In the class, you're creating a service
of type `com.liferay.content.targeting.api.model.Report` and extending the
`com.liferay.content.targeting.api.model.BaseJSPReport` class. Here, *service*
means an OSGi service, not a Liferay API. Another way to say *service type* is
to say *component type*.

After running the command above, your project's folder structure looks like
this:

- `hits-by-country`
    - `gradle` (only in Blade CLI generated projects)
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/content/targeting/report`
                    - `HitsByCountryReport.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `edit.jsp`
                        - `view.jsp`
    - `bnd.bnd`
    - `build.gradle`
    - `[gradlew|pom.xml]`

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
