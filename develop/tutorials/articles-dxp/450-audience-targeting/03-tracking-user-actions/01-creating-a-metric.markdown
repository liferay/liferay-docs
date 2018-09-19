# Creating a Metric [](id=creating-a-metric)

Adding a new metric to the Audience Targeting application is easy. First,
you must create a module and ensure it has the necessary Content Targeting API 
dependencies.

1.  [Create a module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#creating-a-module) 
    project for deploying a metric. A Blade CLI [content-targeting-tracking-action](/develop/reference/-/knowledge_base/7-0/content-targeting-tracking-action-template)
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
    [Accessing the Content Targeting API](/develop/tutorials/-/knowledge_base/7-0/accessing-the-content-targeting-api)
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
    [Selecting a UI Technology](/develop/tutorial/-/knowledge_base/7-0/best-practices-for-metrics#selecting-a-ui-technology)
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

## Defining a Metric's View/Save Lifecycle [](id=defining-a-metrics-view-save-lifecycle)

This section covers how to define a metric's view/save lifecycle. This is when
a user applies a metric to a report using the Report Editor.

In this section, you'll begin defining the newsletter metric's Java class. This
assumes that you followed the instructions above, creating the
`NewsletterTrackingAction` class and extending
[BaseJSPTrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPTrackingAction.html).
If you used the `content-targeting-tracking-action` Blade CLI template, your
project is already extending `BaseJSPTrackingAction` and a default `view.jsp`
file is already created.

1.  Add the activation and deactivation methods to your class.

        @Activate
        @Override
        public void activate() {
            super.activate();
        }

        @Deactivate
        @Override
        public void deActivate() {
            super.deActivate();
        }

    These methods call the super class
    [BaseTrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseTrackingAction.html)
    to implement necessary logging and processing for when your metric starts
    and stops. Make sure to include the
    [@Activate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html)
    and
    [@Deactivate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html)
    annotations, which are required.

2. Add the following method:

        @Override
        protected void populateContext(
            TrackingActionInstance trackingActionInstance,
            Map<String, Object> context, Map<String, String> values) {

            String alias = StringPool.BLANK;
            String elementId = StringPool.BLANK;
            String eventType = StringPool.BLANK;

            if (!values.isEmpty()) {
                alias = values.get("alias");
                elementId = values.get("elementId");
                eventType = values.get("eventType");
            }
            else if (trackingActionInstance != null) {
                alias = trackingActionInstance.getAlias();
                elementId = trackingActionInstance.getElementId();
                eventType = trackingActionInstance.getEventType();
            }

            context.put("alias", alias);
            context.put("elementId", elementId);
            context.put("eventType", eventType);
            context.put("eventTypes", getEventTypes());
        }

    To understand what this method accomplishes, you should understand the
    metric's configuration lifecycle.

    ![Figure 2: An Audience Targeting metric must be configured by the user and processed before it can become part of a Report.](../../images-dxp/metric-lifecycle.png)

    When the user opens the Report Editor, the render phase begins for the
    metric. The `getFormHTML(...)` method retrieves the HTML to display. You
    don't have to worry about implementing this method because it's already
    implemented in the
    [BaseJSPTrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPTrackingAction.html)
    class you're extending. The `getFormHTML` method calls the
    `populateContext(...)` method.

    You'll notice the `populateContext` method is not available in the
    [TrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html)
    interface. This is because it's not needed in all cases.
    It's available by extending the `BaseJSPTrackingAction` class, and you'll
    need to add more logic to it for the newsletter metric.
    
    The goal of the `populateContext` method is to generate a map with all the
    parameters your JSP view needs to render the metric's HTML. This map is
    stored in the `context` variable, which is pre-populated with basic values
    in the Portlet logic, and then each metric contributes its specific
    parameters to it. The `populateContext` method above populates the `alias`,
    `elementId`, `eventType`, and `eventTypes` context variables with the
    adjacent values from the `values` map parameter, which is then passed to the
    JSP.

    For the newsletter metric, the `populateContext` method accounts for three
    use cases:

    a. The metric was added but has no set values yet. In this case, the default
       values defined by the developer are injected (e.g., `alias=""`).

    b. The metric was added and a value is set, but the request failed to
       complete (e.g., due to an error). In this case, the `values` parameter
       of the `populateContext` method contains the values that were intended
       to be saved, and they are injected so that they are displayed in the
       metric's view together with the error message.

    c. The metric was added and a value was successfully set. In this case, the
       `values` parameter is empty, and you have to obtain the values from
       storage that the form should display and inject them in the context so
       they're displayed in the metric's HTML. The newsletter metric stores
       values in the metric's instance, but complex metrics could use services
       to store values.

    You can think of the `populateContext` method as the intermediary between
    your JSP and your backend code. You can see how to create the newsletter
    metric's UI using a JSP by skipping to the
    [Defining the Metric's UI](/develop/tutorials/-/knowledge_base/7-0/tracking-user-actions-with-audience-targeting#defining-the-metrics-ui)
    section. Once the HTML is successfully retrieved and the user has set the
    newsletter's values and clicked *Save*, the action phase begins. 

3.  Once the action phase begins, AT processes the tracking action (metric). The
    `processTrackingAction(...)` method takes the values from the
    [metric's UI form](/develop/tutorials/-/knowledge_base/7-0/tracking-user-actions-with-audience-targeting#defining-the-metrics-ui)
    and stores them in the corresponding fields of the `trackingActionInstance`.
    Since the
    [BaseTrackingAction](@app-ref@/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseTrackingAction.html)
    class provides a default implementation of this method that returns `null`,
    the `NewsletterTrackingAction` class does not need to implement it.

    If you need to process any custom fields in your metric, you should override
    this method. If you want your custom values to be stored in the
    `typeSettings` field of the `trackingActionInstance`, return their value
    instead of `null`.

    +$$$

    **Note:** For more complex cases, you can create your own services to store
    your metric's information to a database. You should invoke your services'
    update logic within the `processTrackingAction` method. For more information
    on creating services, see the
    [Service Builder](/develop/tutorials/-/knowledge_base/7-0/business-logic-and-data-access)
    tutorials.

    $$$

    Once the metric processing ends, the form is reloaded and the lifecycle
    restarts again. The value(s) specified in the metric are stored and are
    ready to be accessed once the report generation begins. Next, you must set
    the event types that the newsletter metric should evaluate.

4.  Add the following method and private field:

        @Override
        public List<String> getEventTypes() {
            return ListUtil.fromArray(_EVENT_TYPES);
        }

        private static final String[] _EVENT_TYPES = {"view"};

    This specifies that your newsletter metric only tracks who views the
    newsletter.

5.  Define a way to retrieve the metric's localized summary. In many instances,
    you can do this by combining keys in the metric's resource bundle with
    the information stored for the metric. For the newsletter metric, you can
    provide information about the ID of the newsletter being tracked, which is
    stored in the `alias` field of the `trackingActionInstance` object.

        @Override
        public String getSummary(
            String summary = LanguageUtil.format(
                locale, "tracking-newsletter-x",
                new Object[] {trackingActionInstance.getAlias()});

            return summary;
        }

6.  Set the servlet context for your metric.

        @Override
        @Reference(
            target = "(osgi.web.symbolicname=newsletter)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            super.setServletContext(servletContext);
        }

    This is only required for metrics extending the `BaseJSPTrackingAction`
    class. The servlet context must be set for the metric to render its own JSP
    files. The `setServletContext` method is invoked automatically when the
    metric module is installed and resolved in Liferay. Make sure the
    `osgi.web.symbolicname` in the `target` property of the `@Reference`
    annotation is set to the same value as the `Bundle-SymbolicName` defined in
    the `bnd.bnd` file of the module.

Next, you'll define a tracking mechanism for your metric to use.
