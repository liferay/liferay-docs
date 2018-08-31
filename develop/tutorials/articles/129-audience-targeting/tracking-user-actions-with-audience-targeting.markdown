# Tracking User Actions with Audience Targeting [](id=tracking-user-actions-with-audience-targeting)

In the Audience Targeting application, a campaign defines a set of content
targeted to specific user segments in a time period. Tracking actions allow
campaign administrators to learn how users behave in the context of a campaign
by monitoring their interaction over different elements of the portal.

Developers are able to extend the set of available tracking actions by creating
and deploying their own OSGi plugin, which contains a class implementing the
[Tracking Action Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/6.2.x/content-targeting-api/service/com/liferay/content/targeting/api/model/TrackingAction.java).

OSGi plugins can be hot deployed and undeployed, manage their own dependencies,
and provide new services that other OSGi plugins can consume. In the case of a
tracking action OSGi plugin, it is consumed by the Audience Targeting
application.

To learn more about how tracking actions are used in the Audience Targeting
application, visit the
[Defining Tracking Actions](/discover/portal/-/knowledge_base/6-2/managing-campaigns#defining-tracking-actions)
section.

To follow this tutorial, you must first have the Audience Targeting SDK
installed. Visit the
[Installing the Audience Targeting Project](/develop/tutorials/-/knowledge_base/6-2/creating-new-audience-targeting-rule-types#installing-the-audience-targeting-project)
section for information on how to do this.

Next, you'll begin learning how to create a custom tracking action using the
Audience Targeting SDK.

## Creating a Tracking Action [](id=creating-a-tracking-action)

Adding a new tracking action to the Audience Targeting application is easy.
In this tutorial, you'll learn how to create a tracking action and deploy it to
your Liferay server.

1. In the root Audience Targeting project folder `apps/content-targeting`, run
   the `create_tracking_action` command (appropriate for your OS) from a command
   prompt. For example, the command below shows creating a `newsletter` tracking
   action project named *Newsletter*:

        create_tracking_action.bat newsletter "Newsletter"

    or

        ./create_tracking_action.sh newsletter "Newsletter"

2. Navigate to the newly generated project folder that has your tracking
   action's name prefixed with `tracking-action-` (e.g.,
   `tracking-action-newsletter`). Open the folder and study what's been
   generated.

    The `create_tracking_action` command created default files that make the
    plugin deployable.

3. Now is a good time to deploy the project to see how it currently looks in
   the Portal. To deploy the plugin project, open a command prompt to your
   plugin's directory and run the `ant deploy` command. You'll find your new
   tracking action listed when creating or editing a campaign in the Audience
   Targeting application.

    +$$$

    **Tip:** When a plugin has been successfully deployed and you don't see its
    changes, check that the generated `.jar` in the Plugins SDK's `/dist` folder
    contains the latest modifications. If it doesn't contain them, delete it and
    redeploy the plugin. Alternatively, you can run the `ant clean deploy`
    command.

    $$$

4. To view your new tracking action, navigate to your portal's *Admin* &rarr;
*Site Administration* &rarr; *Configuration* &rarr; *Audience Targeting* menu.
To see the tracking action you just deployed, click *Campaigns* &rarr; *Add
Campaign*. Then scroll down to the Tracking Actions form.

    ![Figure 1: You can add your new tracking action to the form, but it doesn't do anything yet.](../../images/tracking-action-deploy.png)

You've successfully deployed your tracking action plugin. Next, you'll learn
about the components that were generated for you and how to edit them to create
a functional Audience Targeting tracking action.

There are four components you can specify for your tracking action:

- *Tracking Action Behavior*
- *Tracking Mechanism*
- *UI for Configuration (optional)*
- *Language Keys (optional)*

The behavior of your tracking action is controlled from a Java class located in
your tracking action's
`src/com/liferay/content/targeting/tracking/action/[TRACKING_ACTION_NAME]`
folder. The tracking mechanism (e.g., hook or servlet) is not included by
default; you'll need to create this manually. The tracking action's UI and
language keys can be configured in the `src/templates/ct_tracking_action.ftl`
and `src/content/Language.properties` files, respectively.

You can learn more about language keys and how to create, use, and generate them
by visiting the
[Localization](/develop/tutorials/-/knowledge_base/6-2/localization) tutorials.
You'll learn about configuring a tracking mechanism and UI later in this
tutorial. You'll begin with customizing your tracking action's behavior.

You can begin creating your tracking action's functionality by specifying its
behavior in the `-TrackingAction` class (e.g., `NewsletterTrackingAction`) that
the SDK generated for you. This class implements the
[TrackingAction](https://github.com/liferay/liferay-apps-content-targeting/blob/6.2.x/content-targeting-api/service/com/liferay/content/targeting/api/model/TrackingAction.java)
interface (required), and extends the
[BaseTrackingAction](https://github.com/liferay/liferay-apps-content-targeting/blob/6.2.x/content-targeting-api/service/com/liferay/content/targeting/api/model/BaseTrackingAction.java)
class. It's not mandatory to extend `BaseTrackingAction`, but it provides some
helpful utilities, such as support for generating your tracking action's UI
using FreeMarker. Note that there are multiple methods in the generated
`-TrackingAction` class; you must modify them to create a working tracking
action. You'll begin defining the tracking action's behavior by modifying the
way it looks to administrators in the Tracking Actions form.

When studying your tracking action in Portal, notice that it uses a puzzle piece
icon. You can change the tracking action's icon by modifying the `getIcon` 
default method used in the `-TrackingAction` class.

1. Open your tracking action's Java class file and find the `getIcon` method.
   This method configures the icon displayed in the Tracking Actions UI. You can
   replace the value *"icon-puzzle"* with the name of a Font Awesome icon (e.g.,
   *"icon-envelope-alt"* or *"icon-trophy"*) that appropriately fits your
   tracking action. For a complete listing of icons that you can specify, you
   can visit [Font Awesome](http://fortawesome.github.io/Font-Awesome/3.2.1/).

2. Redeploy your tracking action plugin by running `ant deploy` from the command
   prompt. Now your tracking action uses its new icon.

    ![Figure 2: Your tracking action now uses the Font Awesome icon you specified.](../../images/tracking-action-icon.png)

Now that you've modified a basic feature in your `-TrackingAction` class, you'll
need to develop the UI for your tracking action's configuration. As you read
earlier, your tracking action already has an FTL template, which is used to show
the Tracking Actions form. Since a generated tracking action Java class extends
`BaseTrackingAction` by default, your tracking action already supports using the
FreeMarker language.

If you're interested in using a technology besides FreeMarker to implement your
UI, you can add a method `getFormHTML` to your `-TrackingAction` class. For
further details on this method, see the
[BaseTrackingAction](https://github.com/liferay/liferay-apps-content-targeting/blob/6.2.x/content-targeting-api/service/com/liferay/content/targeting/api/model/BaseTrackingAction.java)
class.

The `getFormHTML` method is configured for FreeMarker templates in the
`BaseTrackingAction` class. This method is used to retrieve the HTML created by
the technology you choose and to return it as a string that is viewable from
your tracking action's form. Therefore, if you plan on using an alternative to
FreeMarker, you must override this method by creating and modifying it in your
`-TrackingAction` class. This tutorial demonstrates implementing the UI using
FreeMarker.

For example, if you wanted to create a tracking action that tracks the number of
times a user views a newsletter, you could create a menu with the following
fields:

- *Alias:* used in reports that count the number of times a tracking action has
been triggered.
- *Newsletter ID:* used to differentiate between newsletters.
- *Tracking Action:* used to differentiate several actions on the same
newsletter, such as opening the newsletter or clicking on a link.

Here's a code snippet from a FreeMarker template (e.g.,
`ct_tracking_action.ftl`) that could be applied to this example:

    <@aui["input"] helpMessage="alias-help" label="alias" name="{ct_field_guid}alias" type="text" value=alias>
        <@aui["validator"] name="required" />
    </@>

    <@aui["input"] helpMessage="enter-the-id-of-the-newsletter-to-be-tracked" label="newsletter-id" name="{ct_field_guid}elementId" type="text" value=elementId>
        <@aui["validator"] name="required" />
    </@>

    <#if eventTypes?has_content && (eventTypes?size > 1)>
        <@aui["select"] label="tracking-action" name="{ct_field_guid}eventType">
            <#list eventTypes as curEventType>
                 <@aui["option"] label="${curEventType}" selected=(eventType == curEventType) value=curEventType />
             </#list>
         </@>
    <#else>
        <#list eventTypes as curEventType>
            <@aui["input"] disabled=true label="tracking-action" name="{ct_field_guid}eventType" type="text" value=curEventType />
        </#list>
    </#if>

This FreeMarker code creates an AUI input field for an Alias and Newsletter ID,
which are both required. The Alias and Newsletter ID fields are necessary
because a name and ID are required for the tracking action to know which
newsletter to track.

Another field that is created from the above sample code is the Tracking Action
field. The tracking action specifies the type of event to monitor related to the
tracked entity (e.g., view, click, submit, etc.). If there is more than one
event type available, the Tracking Action field is a *select* drop-down
box; otherwise, the event type field is disabled or view only. For this example
template, you'd need to declare the possible `eventTypes` in your
`-TrackingAction` class. You'll learn how to do this later.

![Figure 3: This Newsletter tracking action requires the newsletter alias and ID.](../../images/tracking-action-template.png)

For other working examples of FreeMarker templates used for tracking actions,
visit the Audience Targeting [project](https://github.com/liferay/liferay-apps-content-targeting/tree/samples2015)
on Github.

Now that the your tracking action's UI is developed, you can resume the
configuration of your tracking action's behavior. The next method you'll edit in
your `-TrackingAction` class is the `populateContext` method. This method takes
the values that were entered by the user in the Tracking Action form and injects
them into the `context` map parameter. For example, the following
`populateContext` method populates the `eventTypes` variable that was used in
the newsletter FTL sample with the event types available in the newsletter
tracking action:

    @Override
    protected void populateContext(
            TrackingActionInstance trackingActionInstance,
            Map<String, Object> context, Map<String, String> values) {

        context.put("eventTypes", getEventTypes());

        ThemeDisplay themeDisplay = (ThemeDisplay)context.get("themeDisplay");

        String trackURL = themeDisplay.getPortalURL() + "/o/tracking-action-newsletter/track";

        String trackImageURL = HttpUtil.addParameter(
                trackURL, "elementId", "elementIdToken");
        trackImageURL = HttpUtil.addParameter(trackImageURL, "imageId", "1");
        trackImageURL = HttpUtil.addParameter(trackImageURL, "email", "");

        context.put("trackImageURL", trackImageURL);
    }

By populating the `eventTypes` variable, the Tracking Action field can use it to
distinguish whether or not a drop-down menu of tracking actions is displayed.
In many cases, a tracking action has multiple tracking event options. The more
tracking options your tracking action provides, the more opportunities you have
to decipher your audience's likes and dislikes within your site.

This sample newsletter tracking action tracks who views the configured
newsletter by placing a transparent image in the newsletter. Whenever the image
is viewed, the image makes a call to the tracking mechanism, which computes and
stores the information. You'll learn more about the tracking mechanism and how
to create one later. You'll also learn more about the transparent image and how
it communicates with the tracking mechanism later in this tutorial.

Notice in the `populateContext` method above, a tracking URL `.../track` is
created and a tracking image URL is injected into the `context` parameter. These
are used in the tracking mechanism that you'll create next.

Now that your tracking action's behavior is configured, you'll create the
tracking mechanism. This can be done using a hook or servlet. For this tutorial,
you'll learn about creating a tracking servlet.

The first thing you'll need to do is create a separate Java class where all 
your tracking logic is stored. In the
`src/com/liferay/content/targeting/tracking/action/[TRACKING_ACTION_NAME]`
directory, create a `[TRACKING_ACTION_NAME]ProcessorServlet` class. This class
should extend the
[HttpServlet](https://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServlet.html)
class.

There are two important aspects of the tracking mechanism that you'll need to
configure:

- Tracking the requests that match the tracking action event configured from the
UI
- Storing the tracked information

To illustrate how to accomplish these two goals, you can study the
[NewsletterProcessorServlet](https://github.com/liferay/liferay-apps-content-targeting/blob/samples-v1.1/tracking-action-newsletter/src/com/liferay/content/targeting/tracking/action/newsletter/NewsletterProcessorServlet.java)
class, which tracks when a user views a newsletter.

Whenever the transparent image is viewed in the newsletter, the image makes a
call to the servlet. The servlet intercepts all requests matching the pattern
`/track` in order to track the request's information. Recall from the
`populateContext` method that you injected the tracking URL `.../track` into the
`context` variable. This is what is used to distinguish calls to the servlet
that are relevant to the tracking action.

Once the servlet intercepts the calls related to the transparent image, it must
have a way to store it. An analytics service which can be used to
store your tracking information is available for that purpose.

In the newsletter servlet tracking mechanism, the tracking information is
injected into a
[Message](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/messaging/Message.html)
object and sent to the analytics service using Liferay's message bus:

    Message message = new Message();

    ...
    message.put("userId", userId);
    message.put("event", event);
    message.put("elementId", elementId);
    message.put("layoutURL", url);

    MessageBusUtil.sendMessage("liferay/analytics", message);

Using the message bus is a common way to send tracking information for storage
in Liferay's analytics service. To learn more about Liferay's message bus, visit
the [Using Message Bus](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/using-message-bus-liferay-portal-6-2-dev-guide-06-en)
section.

<!-- Replace above link once Message Bus section is available in LDN. -Cody -->

Now that you know about the tracking mechanism and how it should function, it's
time to finish off your custom tracking action. 

Throughout this tutorial, using a transparent image that sends requests to the
tracking mechanism when the newsletter is viewed has been mentioned frequently.
You're probably curious as to how a transparent image can be added to content to
track its views.

The transparent image can be generated as a code snippet by your tracking action
once you've entered the ID into the form. Once saving, you can add the generated
code into the content you'd like to track (e.g., newsletter).

![Figure 4: You can insert the transparent image into your content by copying and pasting the generated code from the form.](../../images/tracking-action-paste-image.png)

To do this, you'll need to make a few modifications to your FTL file. As an
example to reference, view the sample newsletter's
[ct_tracking_action.ftl](https://github.com/liferay/liferay-apps-content-targeting/commit/82ac9bd2d63e285decb85bd69cf7ed9445016e61)
diffs, which highlight the logic necessary for your tracking action to generate
transparent image code.

In summary, the `-TrackingAction` class generates a generic tracking URL which
is missing the newsletter ID. In the updated FTL, the newsletter ID you
specified in the form is added, supplying the full tracking URL that should be
used by the transparent image.

You now have all the knowledge necessary to create your own customized tracking
action and deploy it to your Audience Targeting application. For working
examples of the default tracking actions included in the Audience Targeting app,
visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting) page and
study the folders with the `tracking-action-` prefix. To view the final solution
of a deployable sample newsletter tracking action, you can download its
[ZIP file](/documents/10184/598548/tracking-action-newsletter.zip).

## Related Topics [](id=related-topics)

[Creating Model Listeners](/develop/tutorials/-/knowledge_base/6-2/creating-model-listeners)

[Web Content Management](/discover/portal/-/knowledge_base/6-2/web-content-management)

