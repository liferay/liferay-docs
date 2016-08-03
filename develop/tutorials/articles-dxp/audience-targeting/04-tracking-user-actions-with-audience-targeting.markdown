# Tracking User Actions with Audience Targeting [](id=tracking-user-actions-with-audience-targeting)

In the Audience Targeting application, a campaign defines a set of content
targeted to specific user segments in a time period. Campaign custom reports
allow campaign administrators to learn how users behave in the context of a
campaign by monitoring their interaction over different elements of the portal.

Developers are able to extend the set of available metrics (also known as
tracking actions) in campaign custom reports by creating and deploying their
own OSGi plugin, which contains a class implementing the `TrackingAction`
Interface.

OSGi plugins can be hot deployed and undeployed, manage their own dependencies,
and provide new services that other OSGi plugins can consume. In the case of a
metric OSGi plugin, it is consumed by the Audience Targeting application.

To learn more about how metrics are used in the Audience Targeting application,
visit the
[Defining Metrics](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/managing-campaigns#defining-metrics)
section.

Next, you'll begin learning how to create a custom report metric.

## Creating a Metric [](id=creating-a-metric)

Adding a new custom report metric to the Audience Targeting application is easy.
There are four components you can specify for your metric:

- *Metric Behavior*
- *Tracking Mechanism*
- *UI for Configuration (optional)*
- *Language Keys (optional)*

Before you can define your metric's components, you must create a module and
ensure it has the necessary Content Targeting dependencies.

1. Create a module for deploying a metric using your favorite third party tool.
   A Blade CLI
   [contenttargetingtrackingaction](/develop/tutorials/-/knowledge_base/7-0/content-targeting-tracking-action-template)
   template is available to help you get started quickly by setting all the
   default configuration for you, and it contains boilerplate code so you can
   skip the file creation steps and get started right away.

2. Make sure your module specifies the dependencies necessary for an Audience
   Targeting metric. For example, you should specify the Content Targeting API
   and necessary Liferay packages. For example, examine the example
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

Once you've created your module and specified its dependencies, you'll need to
define your metric's behavior. The behavior of your metric is controlled from a
Java class file that you create.

1. Create a unique package name in the module's `src` directory, and create a
   new Java class in that package. To follow naming conventions, your class name
   should begin with the metric name you're creating, and end with
   *TrackingAction* (e.g., `NewsletterTrackingAction.java`). Your Java class
   should implement the `TrackingAction` interface.

2. Directly above the class's declaration, insert the following code:

        @Component(immediate = true, service = TrackingAction.class)

    This annotation declares the implementation class of the Component, and
    specifies to immediately start the module once deployed to @product@.

Before diving deeper into your `-TrackingAction` class, it's important to
understand what is available for you to extend from this class. It is required
to implement the `TrackingAction` interface, but there are `TrackingAction`
extension classes you can extend from in your custom metric, which provide
helpful utilities. For example, you can extend the `BaseJSPTrackingAction` class
for support when generating your metric's UI using JSPs.

Since Liferay 7.0, JSP is the preferred technology for Audience Targeting
extension views. FreeMarker views, however, are still supported through their
respective base classes (e.g., `BaseFreemarkerTrackingAction`). If you're
interested in using a technology besides JSP or FreeMarker to implement your UI,
you can add a method `getFormHTML` to your `-TrackingAction` class.

The `getFormHTML` is used to retrieve the HTML created by the technology you
choose, and to return it as a string that is viewable from your metric's form.
If you plan, therefore, on using an alternative to JSP or FreeMarker, you must
override this method by creating and modifying it in your `-TrackingAction`
class. This tutorial demonstrates implementing the UI using JSP, and assumes the
`TrackingAction` interface is implemented by extending the
`BaseJSPTrackingAction` class.

Of course, you still need to make some additional changes to define how your
metric works. Here are some of the methods that you can implement to modify your
metric's behavior:

<!-- The below method descriptions are the Javadoc copied from the
`TrackingAction` interface. Since the source code is not accessible and the
Javadoc for Audience Targeting is not currently published, I've provided some
methods and descriptions until the Javadoc is available publicly. -Cody -->

- `activate`: Does processing when the tracking action is installed.
- `deActivate`: Does processing when the tracking action is uninstalled.
- `deleteData`: Removes any additional data added by this tracking action when
  the tracking action instance is removed.
- `exportData`: Exports any additional data added by this tracking action when
  the tracking action instance is exported.
- `getDescription`: Returns the tracking action localized description.
- `getEventTypes`: Returns the list with the event types that can be monitored
  by this tracking action.
- `getFormHTML`: Returns the HTML code containing the form fields required to
  edit the tracking action instance configuration, based on the context.
- `getIcon`: Returns the Font Awesome CSS class for the tracking action icon.
- `getName`: Returns the tracking action localized name.
- `getShortDescription`: Returns the tracking action localized short
  description.
- `getSummary`: Returns the tracking action instance localized summary.
- `getTrackingActionKey`: Returns the key that identifies the tracking action.
  The tracking action instances of this tracking action are identified by their
  tracking action key.
- `importData`: Imports any additional data added by this tracking action when
  the tracking action instance is imported.
- `isInstantiable`: Returns `true` if the tracking action can be used more than
  once with different values for a campaign.
- `isVisible()`: Returns `true` if the tracking action is visible.
- `isVisible(String, long)`: Returns `true` if the tracking action is visible.
- `processTrackingAction`: Returns the result of evaluating the tracking action
  form fields in the context of the request and response.

Once you've modified your metric's behavior, you'll need to develop the UI for
your metric's configuration. As you read earlier, a component of your metric is
its UI configuration, which is used to show the metric's form. If your
`-TrackingAction` class is already extending `BaseJSPTrackingAction`, your
metric already supports using JSP pages. If you used the
`contenttargetingtrackingaction` Blade CLI template, your project is already
extending `BaseJSPTrackingAction` and has a default `view.jsp` file already
configured.

To view a sample metric and its UI configuration, download the sample
[newsletter metric](https://customer.liferay.com/documents/10738/200086/newsletter.zip/589ea9a1-9473-4409-acc6-c41c6d20728a).

For example, if you wanted to create a metric that tracks the number of times a
user views a newsletter, you could create a menu with the following fields:

- *Alias:* used in reports that count the number of times a metric has been
  triggered.
- *Newsletter ID:* used to differentiate between newsletters.
- *Event Type:* used to differentiate several actions on the same newsletter,
  such as opening the newsletter or clicking on a link.

Here's a code snippet from the newsletter metric's JSP template (`view.jsp`)
that could be applied to this example:

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

This JSP code creates an AUI input field for an *Alias* and a *Newsletter ID*,
which are both required, assuming that their values have been obtained from the
context attribute in the request. The *Alias* and *Newsletter ID* fields are
necessary because a name and ID are required for the metric to know which
newsletter to track.

Another field that is created from the above sample code is the *Event Type*
field. The event type specifies the type of event to monitor related to the
tracked entity (e.g., view, click, submit, etc.). For this example JSP, you'd
need to declare the possible `eventTypes` in your `-TrackingAction` class.
You'll learn how to do this later.

![Figure 1: This Newsletter metric requires the newsletter alias and ID.](../../images-dxp/metric-template.png)

Notice that the input field names in the JSP are prefixed with
`ContentTargetingUtil.GUID_REPLACEMENT`. This prefix is required for
multi-instantiable metrics, which are metrics that return `true` in the
`isInstantiable` method of their `-TrackingAction` class and can be added more
than once to the Metrics form.

Now you'll jump back into modifying your metrics's behavior via the
`-TrackingAction` class. You'll dive further into the sample newsletter metric
and find what is necessary to make the JSP code work with the TrackingAction
Java class.

An important method in the newsletter's `NewsletterTrackingAction` class is the
`populateContext` method. This method takes the values that were entered by the
user in the Metric form and injects them into the `context` map parameter. For
example, the following `populateContext` method populates the `eventTypes`
variable that was used in the newsletter JSP sample with the event types
available in the newsletter metric:

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

    @Reference
    protected void setAnalyticsProcessor(AnalyticsProcessor analyticsProcessor) {
        _analyticsProcessor = analyticsProcessor;
    }

    private AnalyticsProcessor _analyticsProcessor;
    private static final String[] _EVENT_TYPES = {"view"};

You can provide a custom tracking mechanism (e.g., a servlet) or you can use the
ones provided by Audience Targeting. In many cases, a metric has multiple
tracking event options. The more tracking options your metric provides, the more
opportunities you have to decipher your audience's likes and dislikes within
your site.

This sample newsletter metric tracks who views the configured newsletter by
placing a transparent image in the newsletter. Whenever the image is viewed,
the image makes a call to the Analytics System, which computes and stores the
information.

Notice that in the `populateContext` method above, a tracking image HTML is
created and injected into the `context` request attribute. This allows the
Analytics System to track the views on the newsletter.

You're probably curious as to how a transparent image can be added to a
newsletter to track its views. The transparent image can be generated as a code
snippet by the metric once you've entered the ID into the form and saved. Then
you can add the generated code into the content you'd like to track
(e.g., newsletter).

![Figure 2: You can insert the transparent image into your content by copying and pasting the generated code from the form.](../../images-dxp/metric-paste-image.png)

To do this, you could add the following lines to the `JSP` file below the event
type selector:

    <c:if test="<%= !Validator.isBlank(trackImageHTML) %>">
        <span class="h5">
            <liferay-ui:message key="paste-this-code-at-the-beginning-of-your-newsletter" />
        </span>
        <label for='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' key="paste-this-code-at-the-beginning-of-your-newsletter" /></label>

        <liferay-ui:input-resource id='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' url="<%= trackImageHTML %>" />
    </c:if>

You could test if the metric was working by copying the generated tracking image
HTML into an email HTML editor, sending it, and opening it as if it were an
actual newsletter. Then open the custom report containing the newsletter metric
and select *Update Report*, a chart and table with the newsletter's view count
is displayed.

For convenience, you can download the full source for the sample newsletter
metric as a
[ZIP file](https://customer.liferay.com/documents/10738/200086/newsletter.zip/589ea9a1-9473-4409-acc6-c41c6d20728a).
You can deploy it to your @product@ instance and try it out yourself!

If you've been paying close attention, you'll notice that there is still one
final component to cover for tracking actions: language keys. You can learn more
about language keys and how to create, use, and generate them by visiting the
[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
tutorials.

You now have all the knowledge necessary to create your own customized metric
and deploy it to your Audience Targeting application. With this, you can
generate custom reports to measure any user interaction.

## Related Topics [](id=related-topics)

[Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/7-0/reporting-user-behavior-with-audience-targeting)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

