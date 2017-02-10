# Content Targeting Tracking Action Template [](id=content-targeting-tracking-action-template)

In this tutorial, you'll learn how to create a Liferay Content Targeting
Tracking Action application as a Liferay module. Use this command to create a
Liferay Content Targeting Tracking Action: 

    blade create -t content-targeting-tracking-action -p [package name] -c [class name] [project name]

The template for this kind of project is `content-targeting-tracking-action`. To
create a tracking action project called `newsletter` with a package prefix of
`com.liferay` and a class name of `Newsletter`, use this command: 

    blade create -t content-targeting-tracking-action -p com.liferay -c Newsletter newsletter

The command above creates a Content Targeting Tracking Action project named
`newsletter` in the current folder. In the class, you're creating a service of
type `com.liferay.content.targeting.api.model.TrackingAction` and extending the
`com.liferay.content.targeting.api.model.BaseJSPTrackingAction` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*.

After running the command above, your project's folder structure looks like
this:

- `newsletter`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/content/targeting/tracking/action`
                    - `NewsletterTrackingAction.java`
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
