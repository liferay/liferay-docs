---
header-id: defining-a-metrics-view-save-lifecycle
---

# Defining a Metric's View/Save Lifecycle

[TOC levels=1-4]

In this section, you will define the metric's view/save lifecycle: what happens 
when a user applies a metric to a report using the Report Editor.

| **Note:** To view the Javadoc for the Content Targeting classes mentioned in
| this article, download the
| [Javadoc JAR](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc).

You'll begin defining the newsletter metric's Java class. This assumes that you
followed the instructions in the previous article to create the
`NewsletterTrackingAction` class and extend
`com.liferay.content.targeting.api.model.BaseJSPTrackingAction`. If you used the
`content-targeting-tracking-action` Blade CLI template, your project is already
extending `BaseJSPTrackingAction` and a default `view.jsp` file is already
created.

1.  Add the activation and deactivation methods to your class.

    ```java
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
    ```

    These methods call the super class
    `com.liferay.content.targeting.api.model.BaseTrackingAction` to implement
    necessary logging and processing for when your metric starts and stops. Make
    sure to include the
    [@Activate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Activate.html)
    and
    [@Deactivate](https://osgi.org/javadoc/r6/cmpn/org/osgi/service/component/annotations/Deactivate.html)
    annotations, which are required.

2. Add the following method:

    ```java
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
    ```

    To understand what this method accomplishes, you should look at the
    metric's configuration lifecycle.

    ![Figure 1: An Audience Targeting metric must be configured by the user and processed before it can become part of a Report.](../../../images-dxp/metric-lifecycle.png)

    When the user opens the Report Editor, the render phase begins for the
    metric. The `getFormHTML(...)` method retrieves the HTML to display. You
    don't have to worry about implementing this method because it's already
    implemented in the `BaseJSPTrackingAction` class you're extending. The
    `getFormHTML` method calls the `populateContext(...)` method.

    You'll notice the `populateContext` method is not available in the
    `TrackingAction` interface. This is because it's not needed in all cases.
    It's available by extending the `BaseJSPTrackingAction` class, and you'll
    need to add more logic to it for the newsletter metric.
    
    The `populateContext` method generates a map with all the parameters your
    JSP view needs to render the metric's HTML. This map is stored in the
    `context` variable, which is pre-populated with basic values in the Portlet
    logic, and then each metric contributes its specific parameters to it. The
    `populateContext` method above populates the `alias`, `elementId`,
    `eventType`, and `eventTypes` context variables with the adjacent values
    from the `values` map parameter, which is then passed to the JSP.

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
    [Defining the Metric's UI](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-metrics-ui)
    section. Once the HTML is successfully retrieved and the user has set the
    newsletter's values and clicked *Save*, the action phase begins. 

3.  Once the action phase begins, Audience Targeting processes the tracking
    action (metric). The `processTrackingAction(...)` method takes the values
    from the
    [metric's UI form](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-metrics-ui)
    and stores them in the corresponding fields of the `trackingActionInstance`.
    Since the `BaseTrackingAction` class provides a default implementation of
    this method that returns `null`, the `NewsletterTrackingAction` class does
    not need to implement it.

    If you need to process any custom fields in your metric, you should override
    this method. If you want your custom values to be stored in the
    `typeSettings` field of the `trackingActionInstance`, return their value
    instead of `null`.

    | **Note:** For more complex cases, you can create your own services to store
    | your metric's information to a database. You should invoke your services'
    | update logic within the `processTrackingAction` method. For more information
    | on creating services, see the
    | [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)
    | tutorials.

    Once the metric processing ends, the form is reloaded and the lifecycle
    restarts again. The value(s) specified in the metric are stored and are
    ready to be accessed once the report generation begins. Next, you must set
    the event types that the newsletter metric should evaluate.

4.  Add the following method and private field:

    ```java
    @Override
    public List<String> getEventTypes() {
        return ListUtil.fromArray(_EVENT_TYPES);
    }

    private static final String[] _EVENT_TYPES = {"view"};
    ```

    This specifies that your newsletter metric only tracks who views the
    newsletter.

5.  Define a way to retrieve the metric's localized summary. In many instances,
    you can do this by combining keys in the metric's resource bundle with
    the information stored for the metric. For the newsletter metric, you can
    provide information about the ID of the newsletter being tracked, which is
    stored in the `alias` field of the `trackingActionInstance` object.

    ```java
    @Override
    public String getSummary(
        TrackingActionInstance trackingActionInstance, Locale locale) {

        return LanguageUtil.get(
            locale, trackingActionInstance.getTypeSettings());
    }
    ```

6.  Set the servlet context for your metric.

    ```java
    @Override
    @Reference(
        target = "(osgi.web.symbolicname=newsletter)",
        unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
    }
    ```

    This is only required for metrics extending the `BaseJSPTrackingAction`
    class. The servlet context must be set for the metric to render its own JSP
    files. The `setServletContext` method is invoked automatically when the
    metric module is installed and resolved in Liferay. Make sure the
    `osgi.web.symbolicname` in the `target` property of the `@Reference`
    annotation is set to the same value as the `Bundle-SymbolicName` defined in
    the `bnd.bnd` file of the module.

Next, you'll define a tracking mechanism for your metric to use.
