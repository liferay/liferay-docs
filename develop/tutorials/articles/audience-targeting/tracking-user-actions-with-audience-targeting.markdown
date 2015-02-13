# Tracking User Actions with Audience Targeting [](id=tracking-user-actions-with-audience-targeting)

In the Audience Targeting application, a campaign defines a set of content
targeted to specific user segments in a time period. Tracking actions allow
campaign administrators to learn how users behave in the context of a campaign
by monitoring their interaction over different elements of the portal.

Developers are able to extend the set of available tracking actions by creating
and deploying their own OSGi plugins, which contain a class implementing the
[Tracking Action Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/TrackingAction.java).

OSGi plugins can be hot deployed and undeployed, manage their own dependencies,
and provide new services that other OSGi plugins can consume. In the case of a
tracking action OSGi plugin, it is consumed by the Audience Targeting
application.

To learn more about how tracking actions are used in the Audience Targeting
application, visit the
[Defining Tracking Actions](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/managing-campaigns#defining-tracking-actions)
section.

To follow this tutorial, you must first have the Audience Targeting SDK
installed. Visit the
[Installing the Audience Targeting Project](/develop/tutorials/-/knowledge_base/6-2/creating-new-audience-targeting-rule-types#installing-the-audience-targeting-project)
section for information on how to do this.

Next, you'll begin learning how to create a custom tracking action using the
Audience Targeting SDK.

## Creating a Tracking Action

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
   Portal. To deploy the plugin project, open a command prompt to your plugin's
   directory and run the `ant deploy` command. You'll find your new tracking
   action listed when creating or editing a campaign in the Audience Targeting
   application.

    +$$$

    **Tip:** When a plugin has been successfully deployed but its changes aren't
    visible, check that the generated `.jar` in the Plugins SDK's `/dist` folder
    contains the latest modifications. If it doesn't contain them, delete it and
    redeploy the plugin.

    $$$

4. To view your new tracking action, navigate to your portal's *Admin* &rarr;
*Site Administration* &rarr; *Configuration* &rarr; *Audience Targeting* menu.
To see the tracking action you just deployed, click *Campaigns* &rarr; *Add
Campaign*. Then scroll down to the Tracking Actions form.

    ![Figure 1: You can add your new tracking action to the form, but it doesn't do anything yet.](../../images/tracking-action-deploy.png)

You've succesfully deployed your tracking action plugin. Next, you'll learn
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
[TrackingAction](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/TrackingAction.java)
interface (required), and extends the
[BaseTrackingAction](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/BaseTrackingAction.java)
class. It's not mandatory to extend `BaseTrackingAction`, but it provides some
helpful utilities, such as support for generating your rule's UI using
FreeMarker. Note that there are multiple methods in the generated
`-TrackingAction` class; you must modify them to create a working tracking
action. You'll begin defining the tracking action's behavior by modifying the
way it looks to administrators in the Tracking Actions form.

When studying your tracking action in Portal, notice that it uses a puzzle piece
icon. You can change the tracking action's icon by modifying the `getIcon` 
default method used in the `-TrackingAction` class.

1. Open your tracking action's Java class file and find the `getIcon` method.
This method configures the icon displayed in the Tracking Actions UI. You can
replace the value *"icon-puzzle"* with the name of a Font Awesome icon (e.g.,
*"icon-envelope-alt"* or *"icon-trophy"*) that appropriately fits your tracking
action. For a complete listing of icons that you can specify, you can visit
[Font Awesome](http://fortawesome.github.io/Font-Awesome/3.2.1/).

2. Redeploy your tracking action plugin by running `ant deploy` from the command
   prompt. Now your rule uses its new icon.

        ![Figure 2: Your tracking action now uses the Font Awesome icon you specified.](../../images/tracking-action-icon.png)

Now that you've modified a basic feature in your `-TrackingAction` class, you'll
need to develop the UI for your tracking rule's configuration.



3. Of course, you still need to make some changes to define how your tracking
   action works. Open the Java class file that was created (e.g.,
   `ForumSubscriptionTrackingAction.java`). Here are some of the methods that
   you can implement to modify your tracking action behavior:

    * `processTrackingAction`: handles the information provided by the
    administrator when configuring this tracking action through the tracking
    action GUI (for example, to store the selected time zone in the
    `typeSettings` field in the database from a *select*).

    * `getEventTypes`: returns a list of events that can be tracked by this
    tracking action.

    * `getFormHtml`: returns the HTML displayed to administrators when
    configuring a tracking action through the tracking action GUI. The
    `BaseTrackingAction` class already implements this method, including a
    FreeMarker template placed in `templates/ct_tracking_action.ftl`. For
    example, for a Forum Subscription Tracking Action, you may add a selector
    with the available forums and categories.

    * `getIcon`: configures the icon displayed in the Tracking Action GUI. You
    should use the name of a FontAwesome icon. For example: *"icon-coffee"* or
    *"icon-globe"*
    (See [Font Awesome documentation](http://fortawesome.github.io/Font-Awesome/3.2.1/)).

    * `getName`: the name of your tracking action (it can be localized).

    * `getSummary`: the description of the tracking action once it is
    configured. This is used to help administrators. For example, if the Forum
    Subscription Tracking Action has been configured to track the Sports Forum,
    then the summary may be *Tracking visits to content in the Sports Forum*.

4. Finally, deploy the tracking action plugin to the Liferay server. The new
   tracking action is available in the Add/Edit Campaign form. When the Campaign
   admin selects it, the GUI defined by the developer (e.g., the forums and
   categories selector) is added to the Add/Edit Campaign form so the admin can
   set a value for that specific campaign.

Awesome! You now have all the knowledge necessary to create your own customized
tracking actions and deploy them to your Audience Targeting app. For working
examples of the default tracking actions included in the Audience Targeting app,
visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting) page and
study the folders with the `tracking-action-` prefix.
