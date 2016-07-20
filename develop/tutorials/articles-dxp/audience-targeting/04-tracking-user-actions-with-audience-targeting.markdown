# Tracking User Actions with Audience Targeting

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

To follow this tutorial, you must first have the Audience Targeting development
tools installed. Visit the
[Installing the Audience Targeting Development Tools](/develop/tutorials/-/knowledge_base/7-0/creating-new-audience-targeting-rule-types#installing-the-audience-targeting-development-tools)
section for information on how to do this.

Next, you'll begin learning how to create a custom report metric using the
Audience Targeting tracking action Blade CLI template.

## Creating a Metric

Adding a new custom report metric to the Audience Targeting application is easy.
In this tutorial, you'll learn how to create a metric and deploy it to your
Liferay server.

1. Run the `blade create -t contenttargetingtrackingaction` command from a
   command prompt. For example, the command below creates a tracking action
   project with `newsletter` for its project name and `NewsletterTrackingAction`
   as its class name within the `com.liferay.content.targeting.tracking.action`
   package:

        blade create -t contenttargetingtrackingaction -p com.liferay -c Newsletter newsletter

2. Navigate to the newly generated project folder that has your tracking
   action's name. Open the folder and study what's been generated.

    The `blade create -t contenttargetingtrackingaction` command created default
    files that make the plugin deployable.

3. Now is a convenient time to deploy the project to see how it currently looks
   in Portal.

    To deploy the plugin project, start a @product@ instance, open a terminal to
    your plugin project's directory, and run the `blade deploy` command. You'll
    find this new metric listed when creating or editing a campaign custom
    report in the Audience Targeting application.

4. To view your new metric, navigate to your portal's Site Administration &rarr;
   *Configuration* &rarr; *Audience Targeting* &rarr; *Campaigns* &rarr; *Edit
   Campaign*. Then click *Reports* &rarr; *Add Custom Report* and scroll down to
   the Metrics form.

    ![Figure 1: You can add your new metric to the form, but it doesn't do anything yet.](../../images-dxp/tracking-action-deploy.png)

You've successfully deployed your metric plugin. Next, you'll learn about the
components that were generated for you and how to edit them to create a
functional Audience Targeting custom report metric.

There are four components you can specify for your metric:

- *Metric Behavior*
- *Tracking Mechanism*
- *UI for Configuration (optional)*
- *Language Keys (optional)*

The behavior of your metric is controlled from a Java class located in your
metric's `src/main/java/com/liferay/content/targeting/tracking/action` folder.
You can provide a custom tracking mechanism (e.g., a servlet) or you can use the
ones provided by Audience Targeting. The metric's UI and language keys can be
configured in the `src/main/resources/META-INF/resources/view.jsp` and
`src/main/resources/content/Language.properties` files, respectively. You'll
learn more about the latter two components later on.

You can learn more about language keys and how to create, use, and generate them
by visiting the
[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)
tutorials. You'll learn about configuring the metric UI and using Audience
Targeting tracking mechanisms later in this tutorial. You'll begin with
customizing your metric's behavior.

You can begin creating your metric's functionality by specifying its behavior
in the `-TrackingAction` class (e.g., `NewsletterTrackingAction`) that Blade CLI
generated for you. This class implements the `TrackingAction` interface
(required), and extends the `BaseJSPTrackingAction` class. It's not mandatory to
extend `BaseJSPTrackingAction`, but it provides some helpful utilities, such as
support for generating your metric's UI using JSP. Note that there are multiple
methods in the generated `-TrackingAction` class; you must modify them to create
a working metric.

You'll begin defining the UI for your metric's configuration. As you read
earlier, your metric already has a JSP view, which is used to show the Metrics
form. Since the generated metric Java class extends `BaseJSPTrackingAction` by
default, your metric already supports using JSP pages.

JSP is the preferred technology for Audience Targeting extension views.
FreeMarker views, however, are still supported through their respective base
classes (e.g. `BaseFreemarkerTrackingAction`). If you're interested in using a
technology besides JSP or FreeMarker to implement your UI, you can add a method
`getFormHTML` to your `-TrackingAction` class and override the default.

The `getFormHTML` is used to retrieve the HTML created by the technology you
choose, and to return it as a string that is viewable from your metric's form.
If you plan, therefore, on using an alternative to JSP or FreeMarker, you
must override this method by creating and modifying it in your `-TrackingAction`
class. This tutorial demonstrates implementing the UI using JSP.

For example, if you wanted to create a metric that tracks the number of times a
user views a newsletter, you could create a menu with the following fields:

- *Alias:* used in reports that count the number of times a metric has been
  triggered.
- *Newsletter ID:* used to differentiate between newsletters.
- *Event Type:* used to differentiate several actions on the same newsletter,
  such as opening the newsletter or clicking on a link.

Here's a code snippet from a JSP template (e.g., `view.jsp`) that could be
applied to this example:

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

![Figure 2: This Newsletter metric requires the newsletter alias and ID.](../../images-dxp/metric-template.png)

Notice that the input field names in the JSP are prefixed with
`ContentTargetingUtil.GUID_REPLACEMENT`. This prefix is required for
multi-instantiable metrics, which are metrics that return `true` in the
`isInstantiable` method of their `-TrackingAction` class and can be added more
than once to the Metrics form.

Now that the your metric's UI is developed, you can resume the configuration of
your metric's behavior. The next method you'll edit in your `-TrackingAction`
class is the `populateContext` method. This method takes the values that were
entered by the user in the Metric form and injects them into the `context` map
parameter. For example, the following `populateContext` method populates the
`eventTypes` variable that was used in the newsletter JSP sample with the event
types available in the newsletter metric:

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

In many cases, a metric has multiple tracking event options. The more tracking
options your metric provides, the more opportunities you have to decipher your
audience's likes and dislikes within your site.

This sample newsletter metric tracks who views the configured newsletter by
placing a transparent image in the newsletter. Whenever the image is viewed,
the image makes a call to the Analytics System, which computes and stores the
information.

Notice that in the `populateContext` method above, a tracking image HTML is
created and injected into the `context` request attribute. This allows the
Analytics System to track the views on the newsletter.

You're probably curious as to how a transparent image can be added to a
newsletter to track its views. The transparent image can be generated as a code
snippet by your metric once you've entered the ID into the form and saved. Then
you can add the generated code into the content you'd like to track
(e.g., newsletter).

![Figure 3: You can insert the transparent image into your content by copying and pasting the generated code from the form.](../../images-dxp/tracking-action-paste-image.png)

To do this, you'll need to make a few modifications to your JSP file. Just
include these lines below the event type selector:

    <c:if test="<%= !Validator.isBlank(trackImageHTML) %>">
        <span class="h5">
            <liferay-ui:message key="paste-this-code-at-the-beginning-of-your-newsletter" />
        </span>
        <label for='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' key="paste-this-code-at-the-beginning-of-your-newsletter" /></label>

        <liferay-ui:input-resource id='<%= renderResponse.getNamespace() + ContentTargetingUtil.GUID_REPLACEMENT + "trackImageHTML" %>' url="<%= trackImageHTML %>" />
    </c:if>

You can test if your metric is working by copying the generated tracking image
HTML into an email HTML editor, send it, and open it as if it were an actual
newsletter. Then go to the custom report containing your newsletter metric and
select *Update Report* in the top-right menu. A chart and a table with the view
count on your newsletter will be shown.

For convenience, you can download the full source for the sample newsletter
metric as a
[ZIP file](https://customer.liferay.com/documents/10738/200086/newsletter.zip/589ea9a1-9473-4409-acc6-c41c6d20728a).

You now have all the knowledge necessary to create your own customized metric
and deploy it to your Audience Targeting application. With this, you can
generate custom reports to measure any user interaction.

## Related Topics

[Reporting User Behavior with Audience Targeting](/develop/tutorials/-/knowledge_base/7-0/reporting-user-behavior-with-audience-targeting)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

