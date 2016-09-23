# Content Targeting Report Template [](id=content-targeting-report-template)

In this tutorial, you'll learn how to create a Liferay Content Targeting Report
application as a Liferay module. Use this command to create a Liferay Content
Targeting Report Module: 

    blade create -t contenttargetingreport -p [package name] -c [class name] [project name]

The template for this kind of project is `contenttargetingreport`. To create a
report project called `hits-by-country` with a package prefix of `com.liferay`
and a class name of `HitsByCountryReport`, use this command: 

    blade create -t contenttargetingreport -p com.liferay -c HitsByCountry hits-by-country

The command above creates a Content Targeting Rule project named
`hits-by-country` in the current folder. In the class, you're creating a service
of type `com.liferay.content.targeting.api.model.Report` and extending the
`com.liferay.content.targeting.api.model.BaseJSPReport` class. Here, *service*
means an OSGi service, not a Liferay API. Another way to say *service type* is
to say *component type*.

After running the command above, your project's folder structure looks like
this:

- `hits-by-country`
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

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
