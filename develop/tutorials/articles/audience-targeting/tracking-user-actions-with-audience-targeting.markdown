# Tracking User Actions with Audience Targeting [](id=tracking-user-actions-with-audience-targeting)

In the Audience Targeting application, a Campaign defines a set of content
targeted to specific user segments in a time period. Tracking Actions allow
campaign administrators to learn how users behave in the context of a campaign
by monitoring their interaction over different elements of the portal.

Developers will be able to extend the set of available tracking actions by
creating and deploying their own OSGi plugins which contain a class implementing
the [Tracking Action Interface](https://github.com/liferay/liferay-apps-content-targeting/blob/master/content-targeting-api/service/com/liferay/content/targeting/api/model/TrackingAction.java).

OSGi plugins can be quickly hot deployed and undeployed, managing their own
dependencies and providing new services that other OSGi plugins can consume.
In the case of a report OSGi plugin, it will be consumed by the Audience
Targeting application.

Adding a new tracking action to the Audience Targeting application is easy.
For example, let's see how to create a very simple tracking action called
`Forum Subscription`.

1. In the root of the Audience Targeting project, run the create_tracking_action
command (depending on your OS):

        ./create_tracking_action.sh forum-subscription "Forum Subscription"
        create_tracking_action.bat forum-subscription "Forum Subscription"

2. Move to the newly generated folder `tracking-action-forum-subscription`.
Notice that all the necessary files have been added by the
create_tracking_action command. If you now run the `ant deploy` command in the
root of the `tracking-action-forum-subscription` folder, you'll find this new
tracking action listed when creating or editing a Campaign in the Audience
Targeting application.
3. Of course you still need to make some changes to define how your tracking
action works. Open the `ForumSubscriptionTrackingAction.java` file. Some of the methods
that you can implement to modify your tracking action behavior are:
	* `processTrackingAction`: This method will handle the information provided
	by the administrator when configuring this tracking action through the
	tracking action GUI. For example, to store the selected time zone in the
	typeSettings field in the database from a select.
	* `getEventTypes`: This method returns a list of events that can be tracked
	by this tracking action.
	* `getFormHtml`: This method will return the HTML displayed to
	administrators when configuring a tracking action through the tracking
	action GUI. The BaseTrackingAction class already implements this method
	including a freemarker template placed in templates/ct_tracking_action.ftl.
	For example, for the Forum Subscription Tracking Action, you may add a
	selector with the available forums and categories.
	* `getIcon`: Configure the icon displayed in the Tracking Action GUI. You
	should use the name of a FontAwesome icon. For example: "icon-coffee" or
	"icon-globe"
	(See <a href="http://fortawesome.github.io/Font-Awesome/3.2.1/">Font Awesome documentation</a>)
	* `getName`: The name of your Tracking Action. (It can be localized)
	* `getSummary`: A description of the Tracking Action once it is configured.
	This will be used to help administrators. For example, if the Forum
	Subscription Tracking Action has been configured to track the Sports Forum,
	then the summary may be "Tracking visits to content in the Sports Forum"
4. Finally, deploy the tracking action plugin in the Liferay server. The new
tracking action will be available in the add/edit Campaign form. When the
Campaign admin selects it, the piece of GUI defined by the developer
(eg. the forums and categories selector) is added to the add/edit Campaign form
so that the admin can set a value for that specific campaign.
