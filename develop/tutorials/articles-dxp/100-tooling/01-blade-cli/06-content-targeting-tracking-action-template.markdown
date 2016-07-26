# Content Targeting Tracking Action Template

In this tutorial, you'll learn how to create a Liferay Content Targeting
Tracking Action application as a Liferay module. To create a Liferay Content
Targeting Tracking Action application as a module, use a command like the
following:

    blade create -t contenttargetingtrackingaction -p com.liferay -c Newsletter newsletter

The command above creates a Content Targeting Tracking Action project named
`newsletter` in the current directory. You specified the prefix for the package
name, which is set to `com.liferay.content.targeting.tracking.action` and your
portlet component class name to `NewsletterTrackingAction`. Note that in that
class, you're creating a service of type
`com.liferay.content.targeting.api.model.TrackingAction` and extending the
`com.liferay.content.targeting.api.model.BaseJSPTrackingAction` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*.

After running the command above, your project's directory structure looks like
this:

- `newsletter`
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

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
