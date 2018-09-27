# Creating a Metric [](id=creating-a-metric)

Adding a new metric to the Audience Targeting application is easy. First,
you must create a module and ensure it has the necessary Content Targeting API 
dependencies.

1.  [Create a module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module) 
    project for deploying a metric. A Blade CLI [content-targeting-tracking-action](/develop/reference/-/knowledge_base/7-1/content-targeting-tracking-action-template)
    template is available to help you get started quickly. It sets the default
    configuration for you, and it contains boilerplate code so you can skip the
    file creation steps and get started right away.

2.  Make sure your module specifies the dependencies necessary for an Audience
    Targeting metric. For example, you should specify the Content Targeting API
    and necessary Liferay packages. For example, this is the example
    `build.gradle` file used from a Gradle based metric:

        dependencies {
            compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.analytics.api", version: "3.0.0"
            compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.anonymous.users.api", version: "2.0.2"
            compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.api", version: "4.0.0"
            compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.3.0"
            compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "2.0.0"
            compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
            compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
            compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        }

    You can learn more about exposing the Content Targeting API in the
    [Accessing the Content Targeting API](/develop/tutorials/-/knowledge_base/7-1/accessing-the-content-targeting-api)
    tutorial. Once you've created your module and specified its dependencies,
    you'll need to define your metric's behavior. How your metric behaves is
    controlled by a Java class file that you create.

3.  Create a unique package name in the module's `src` directory, and create a
    new Java class in that package. To follow naming conventions, your class
    name should begin with the metric's name you're creating and end with
    *TrackingAction* (e.g., `NewsletterTrackingAction.java`). Your Java class
    should implement the
    [com.liferay.content.targeting.api.model.TrackingAction` interface](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html).

    You must implement the
    [TrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html}
    interface, but there are
    `TrackingAction` extension classes that provide helpful utilities that you
    can extend. For example, your metric can extend the
    [BaseJSPTrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPTrackingAction.html)
    class to support generating your metric's UI using JSPs. This tutorial
    demonstrates implementing the UI using a JSP and assumes the
    [TrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html)
    interface is implemented by extending the `BaseJSPTrackingAction` class. For
    more information on choosing a UI for your metric, see the
    [Selecting a UI Technology](/develop/tutorial/-/knowledge_base/7-1/best-practices-for-metrics#selecting-a-ui-technology)
    section.

4.  Directly above the class's declaration, insert the following annotation:

        @Component(immediate = true, service = TrackingAction.class)

    This declares the Component's implementation class and configures it to
    start immediately once deployed to @product@.

Now that your Java class is set up, you'll need to define how your metric works
by implementing the
[TrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html)
interface's methods. You'll begin implementing these methods next.

The first thing you'll define in your newsletter metric is the view/save
lifecycle.
