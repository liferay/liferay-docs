# Tracking User Actions with Audience Targeting [](id=tracking-user-actions-with-audience-targeting)

In the Audience Targeting (AT) application, a campaign defines a set of content
targeted to specific user segments during a time period. Campaign custom reports
allow campaign administrators to learn how users behave in the context of a
campaign by monitoring their interaction over different elements of the site.
Out of the box, Liferay provides several metrics that are based on entity types
that you can track, such as content, forms, links, pages, etc. You can use these
metrics to create custom reports. For example, if you want track how many users
watch a YouTube video that is published on your site, you might create a custom
report with the YouTube Videos metric.

The AT app ships with many metrics you can apply to custom reports, but it's
also extensible. This means that if the default metrics available do not fulfill
your needs, you can create one yourself!

A metric's development strategy comes down to four choices:

- Entity to Track
- Tracking Mechanism
- Tracking Events
- Differentiation Method

Creating a metric involves targeting what you want to track in a custom report.
Suppose you're the owner of a hardware store and you'd like to send emails to
your customers notifying them of the store's weekly newsletter. You send the
email every week, but you're in the dark about how many customers actually open
and read the newsletter. For this example, your entity to track is a newsletter.

To track how many customers view the newsletter, you'll need to create a
tracking mechanism. You can provide a custom tracking mechanism (e.g., a
servlet) or you can use the ones provided by Audience Targeting. For a 
newsletter, you could use a transparent image as the tracking mechanism, which
would have the *View* tracking event capability. Whenever the image is viewed,
the Audience Targeting app computes and stores the information.

In many cases, a metric can have multiple tracking event options. For example,
the YouTube Videos metric provides tracking event options like Buffering,
Playing, Paused, Ended, etc. This lets you track different kinds of actions on
an entity, providing a more accurate report on user interactions.

Finally, you must assign the metric to an entity. For a newsletter, you could
provide a Newsletter ID field that the user could fill in to differentiate
newsletters, if there's more than one.

To learn more about how metrics are used in the Audience Targeting application,
visit the
[Defining Metrics](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/managing-campaigns#defining-metrics)
section.

For this tutorial, you'll create a newsletter that can track who views it. This
process involves defining the view/save lifecycle, which is when a user applies
a metric to a report using the Report Editor. Then you'll define its tracking
mechanism, tracking event(s), and differentiation method, similar to what was
described above.

![Figure 1: The sample Newsletter metric requires the newsletter name, ID, and event type.](../../images-dxp/metric-template.png)

Now that you have an idea of how to plan your new metric, you'll begin
creating one next!

## Creating a Metric [](id=creating-a-metric)

Adding a new metric to the Audience Targeting application is easy. First,
you must create a module and ensure it has the necessary Content Targeting API
dependencies.

1.  Create a module project for deploying a metric. A Blade CLI
    [contenttargetingtrackingaction](/develop/tutorials/-/knowledge_base/7-0/content-targeting-tracking-action-template)
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
    [com.liferay.content.targeting.api.model.TrackingAction` interface](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html).

    You must implement the
    [TrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html}
    interface, but there are
    `TrackingAction` extension classes that provide helpful utilities that you
    can extend. For example, your metric can extend the
    [BaseJSPTrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPTrackingAction.html)
    class to support generating your metric's UI using JSPs. This tutorial
    demonstrates implementing the UI using a JSP and assumes the
    [TrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html)
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
[TrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html)
interface's methods. You'll begin implementing these methods next.

The first thing you'll define in your newsletter metric is the view/save
lifecycle.

## Defining a Metric's View/Save Lifecycle [](id=defining-a-metrics-view-save-lifecycle)

This section covers how to define a metric's view/save lifecycle. This is when
a user applies a metric to a report using the Report Editor.

In this section, you'll begin defining the newsletter metric's Java class. This
assumes that you followed the instructions above, creating the
`NewsletterTrackingAction` class and extending
[BaseJSPTrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPTrackingAction.html).
If you used the `contenttargetingtrackingaction` Blade CLI template, your
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
    [BaseTrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseTrackingAction.html)
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
    [BaseJSPTrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseJSPTrackingAction.html)
    class you're extending. The `getFormHTML` method calls the
    `populateContext(...)` method.

    You'll notice the `populateContext` method is not available in the
    [TrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/TrackingAction.html)
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
    [BaseTrackingAction](https://docs.liferay.com/dxp/apps/content-targeting/2.0.0/javadocs/com/liferay/content/targeting/api/model/BaseTrackingAction.html)
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

## Using a Tracking Mechanism [](id=using-a-tracking-mechanism)

Imagine an administrator has successfully configured and saved your custom
metric to his or her report. Now what? Your metric needs to fulfill its
purpose, which is to track the `view` event type for the defined newsletter. To
do this, you must define a tracking mechanism. For your newsletter, you'll use a
transparent image as the tracking mechanism, which would have the *View*
tracking event capability. Whenever the image is viewed, the newsletter metric
computes and stores the information.

For the newsletter metric, you'll use a tracking mechanism provided by the
Audience Targeting app.

1.  You must set the analytics processor that the Content Targeting API provides
    for tracking events. Add the following method and private field:

        @Reference
        protected void setAnalyticsProcessor(AnalyticsProcessor analyticsProcessor) {
            _analyticsProcessor = analyticsProcessor;
        }

        private AnalyticsProcessor _analyticsProcessor;

    The analytics processor is a module of the Audience Targeting Analytics
    system. It contains a servlet to track analytics from Liferay pages (views,
    clicks, etc.) and an API to leverage this tracking mechanism. In the
    `setAnalyticsProcesoor(...)` method, you're obtaining a reference of the
    current analytics processor to build the URL used to generate a transparent
    image. All you have to do is insert the generated URL into your newsletter's
    HTML, and the transparent image tracks who reads it. Everything is processed
    by the default Audience Targeting Analytics system automatically.

    Now that you've obtained a reference of the analytics processor, you need to
    add logic for generating the appropriate tracking URL.

2.  Replace the `populateContext` method with the updated method:

        @Override
        protected void populateContext(
            TrackingActionInstance trackingActionInstance,
            Map<String, Object> context, Map<String, String> values) {

            String alias = StringPool.BLANK;
            String elementId = StringPool.BLANK;
            String eventType = StringPool.BLANK;
            String trackImageHTML = StringPool.BLANK;

            if (!values.isEmpty()) {
                alias = values.get("alias");
                elementId = values.get("elementId");
                eventType = values.get("eventType");
            }
            else if (trackingActionInstance != null) {
                alias = trackingActionInstance.getAlias();
                elementId = trackingActionInstance.getElementId();
                eventType = trackingActionInstance.getEventType();

                String trackImageURL = _analyticsProcessor.getTrackingURL(
                    trackingActionInstance.getCompanyId(), 0, 0, "", 0,
                    Campaign.class.getName(),
                    new long[] {trackingActionInstance.getCampaignId()},
                    trackingActionInstance.getElementId(), "view", "");

                trackImageHTML = "<img alt=\"\" src=\"" + trackImageURL + "\" />";
            }

            context.put("alias", alias);
            context.put("elementId", elementId);
            context.put("eventType", eventType);
            context.put("eventTypes", getEventTypes());
            context.put("trackImageHTML", trackImageHTML);
        }

    This updated method creates a new variable named `trackImageHTML`,
    retrieves a tracking URL using the analytics processor, and then populates
    the `trackImageHTML` context variable. When creating a new metric, the
    transparent image's URL field is not present in the metric's form. When the
    metric is initially saved, however, the URL is generated using the analytics
    processor and is available for copying.

Excellent! You've obtained the analytics processor and can create the
transparent image tracking mechanism. The newsletter metric's behavior is
defined and complete. The last thing you need to do is create a JSP template.

## Defining the Metric's UI [](id=defining-the-metrics-ui)

The Java code you've added to this point has assumed that there are three
configurable fields for your newsletter metric: 

- *Name:* used in reports that count the number of times a metric has been
  triggered. This is also known as the newsletter's alias.
- *Newsletter ID:* used to differentiate between newsletters.
- *Event Type:* used to differentiate several actions on the same newsletter,
  such as opening the newsletter or clicking on a link.

To let administrators set these values, you must define a UI so your metric can
be configured during the view/save lifecycle. Remember that you must also define
a field to display the generated transparent image's URL. Create a `view.jsp`
file in your metric's module (e.g.,
`/src/main/resources/META-INF/resources/view.jsp`) and add the following logic:

    <%
    Map<String, Object> context = (Map<String, Object>)request.getAttribute("context");

    String alias = (String)context.get("alias");
    String elementId = (String)context.get("elementId");
    String eventType = (String)context.get("eventType");
    List<String> eventTypes = (List<String>)context.get("eventTypes");
    String trackImageHTML = (String)context.get("trackImageHTML");
    %>

    <aui:input helpMessage="name-help" label="name" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "alias" %>' type="text" value="<%= alias %>">
        <aui:validator name="required" />
    </aui:input>

    <aui:input helpMessage="enter-the-id-of-the-newsletter-to-be-tracked" label="newsletter-id" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "elementId" %>' type="text" value="<%= elementId %>">
        <aui:validator name="required" />
    </aui:input>

    <c:if test="<%= ListUtil.isNotEmpty(eventTypes) %>">
        <aui:select label="event-type" name='<%= ContentTargetingUtil.GUID_REPLACEMENT + "eventType" %>'>

            <%
            for (String curEventType : eventTypes) {
            %>

                <aui:option label="<%= curEventType %>" selected="<%= curEventType.equals(eventType) %>" value="<%= curEventType %>" />

            <%
            }
            %>

        </aui:select>
    </c:if>

    <c:if test="<%= !Validator.isBlank(trackImageHTML) %>">
        <span class="h5">
            <liferay-ui:message key="paste-this-code-at-the-beginning-of-your-newsletter" />
        </span>
        <label for='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' key="paste-this-code-at-the-beginning-of-your-newsletter" /></label>

        <liferay-ui:input-resource id='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' url="<%= trackImageHTML %>" />
    </c:if>

First you instantiate the `context` variable and its attributes you configured
in your Java class's `populateContext` method. Then you specify the appropriate
fields Name, Newsletter ID, and Event Type. Finally, you present the generated
transparent image URL.

Notice that the input field names in the JSP are prefixed with
`ContentTargetingUtil.GUID_REPLACEMENT`. This prefix is required for
multi-instantiable metrics, which are metrics that return `true` in the
`isInstantiable` method of their `-TrackingAction` class and can be added more
than once to the Metrics form.

![Figure 3: Once you've saved the metric, you can copy the generated transparent image URL into your newsletter's HTML to track who views it.](../../images-dxp/metric-generated-url.png)

Congratulations! You've created the newsletter metric and can now track whether
users viewed a newsletter. You can test if the metric is working by copying the
generated tracking image HTML into an email HTML editor, sending it, and opening
it as if it were an actual newsletter. Then open the custom report containing
the newsletter metric and select *Update Report*. A chart and table with the
newsletter's view count is shown.

You can view the finished version of the newsletter metric by downloading its
[ZIP file](https://customer.liferay.com/documents/10738/200086/newsletter.zip/589ea9a1-9473-4409-acc6-c41c6d20728a).

Now you've created and examined a fully functional metric and have the knowledge
to create your own.

## Related Topics [](id=related-topics)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

[Defining Metrics](/discover/portal/-/knowledge_base/7-0/managing-campaigns#defining-metrics)

[Audience Targeting Metrics](/discover/portal/-/knowledge_base/7-0/audience-targeting-metrics)
