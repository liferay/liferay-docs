# Tracking User Actions with Audience Targeting [](id=tracking-user-actions-with-audience-targeting)

In the Audience Targeting application, a campaign defines a set of content
targeted to specific user segments in a time period. Tracking actions allow
campaign administrators to learn how users behave in the context of a campaign
by monitoring their interaction over different elements of the portal.

Developers are able to extend the set of available tracking actions by creating
and deploying their own OSGi plugins, which contain a class implementing the
[Tracking Action Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/TrackingAction.java).

OSGi plugins can be quickly hot deployed and undeployed, managing their own
dependencies and providing new services that other OSGi plugins can consume. In
the case of a tracking action OSGi plugin, it is consumed by the Audience
Targeting application.

To follow this tutorial, you must first have the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting) and
Plugins SDK configured properly. Visit the
[Installing the Audience Targeting Project](/develop/tutorials/-/knowledge_base/6-2/creating-new-audience-targeting-rule-types#installing-the-audience-targeting-project)
section for information on how to do this.

Adding a new tracking action to the Audience Targeting application is easy.
In this tutorial, you'll learn how to create a tracking action and deploy it to
your Liferay server.

1. In the root of the Audience Targeting project, run the
   `create_tracking_action` command (depending on your OS). For example, the
   command below shows creating a `forum-subscription` tracking action named
   *Forum Subscription*:

        create_tracking_action.[bat|sh] forum-subscription "Forum Subscription"

2. Move to the newly generated folder that has your tracking action's name
   prefixed with `tracking-action-` (e.g.,
   `tracking-action-forum-subscription`). Notice that all the necessary files
   have been added by the `create_tracking_action` command. If you now run the
   `ant deploy` command in the root of the newly generated folder, you'll find
   this new tracking action listed when creating or editing a campaign in the
   Audience Targeting application.

3. Of course, you still need to make some changes to define how your tracking
   action works. Open the Java class file that was created (e.g.,
   `ForumSubscriptionTrackingAction.java`). Some of the methods that you can
   implement to modify your tracking action behavior are:

    * `processTrackingAction`: handles the information provided by the
    administrator when configuring this tracking action through the tracking
    action GUI. For example, to store the selected time zone in the
    `typeSettings` field in the database from a *select*.

    * `getEventTypes`: returns a list of events that can be tracked by this
    tracking action.

    * `getFormHtml`: returns the HTML displayed to administrators when
    configuring a tracking action through the tracking action GUI. The
    `BaseTrackingAction` class already implements this method including a
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

4. Finally, deploy the tracking action plugin in the Liferay server. The new
   tracking action is available in the Add/Edit Campaign form. When the Campaign
   admin selects it, the GUI defined by the developer (e.g., the forums and
   categories selector) is added to the Add/Edit Campaign form so the admin can
   set a value for that specific campaign.

Awesome! You now have all the knowledge necessary to create your own customized
tracking actions and deploy them to your Audience Targeting app. For working
examples of the default tracking actions included in the Audience Targeting app,
visit the Audience Targeting
[project](https://github.com/liferay/liferay-apps-content-targeting) page, and
study the folders with the `tracking-action-` prefix.
