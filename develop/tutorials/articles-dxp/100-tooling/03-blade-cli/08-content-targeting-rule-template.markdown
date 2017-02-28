# Content Targeting Rule Template [](id=content-targeting-rule-template)

In this tutorial, you'll learn how to create a Liferay Content Targeting Rule
application as a Liferay module. Use this command to create a Liferay Content
Targeting Rule module: 

    blade create -t content-targeting-rule -p [package name] -c [class name] [project name]

The template for this kind of project is `content-targeting-rule`. To create a
rule project called `weather` with a package prefix of `com.liferay` and a class
name of `Weather`, use this command: 

    blade create -t content-targeting-rule -p com.liferay -c Weather weather

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

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
