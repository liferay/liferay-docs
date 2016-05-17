# Using Liferay Push in Android Apps [](id=using-liferay-push-in-android-apps)

Liferay Screens supports push notifications in Android apps. To use
them, you must configure some APIs and modify your app to consume and/or
produce push notifications. This tutorial shows how to do all these things. 

<iframe width="560" height="315" src="https://www.youtube.com/embed/4LjutX0dcRw" frameborder="0" allowfullscreen></iframe>

## Configuring to Use Liferay Push Notifications [](id=configuring-to-use-liferay-push-notifications)

Your first step is to create and configure a Google project to use Google Cloud
Messaging ([GCM](https://developers.google.com/cloud-messaging/)). You also need
to configure the Liferay Push app to use the project's GCM API. 

Follow these steps to create and configure a Google project to support cloud
messaging: 

1.  In the [Google Cloud Messaging page](https://developers.google.com/cloud-messaging/android/start/),
    create a [configuration file](https://developers.google.com/mobile/add?platform=android&cntapi=gcm&cntapp=Default%20Demo%20App&cntpkg=gcm.play.android.samples.com.gcmquickstart&cnturl=https:%2F%2Fdevelopers.google.com%2Fcloud-messaging%2Fandroid%2Fstart%3Fconfigured%3Dtrue&cntlbl=Continue%20with%20Try%20Cloud%20Messaging). Set up your *App name* and *Android package name*.
    
2.	Enable Cloud Messaging for your new project by clicking on *Enable Google Cloud Messaging*. After that you will receive a server key and project id.
  
       ![Figure 1: The Google Developers Console displays the Project Number and the Server key.](../../images/screens-android-push-project-and-server-key.png)  
    

3.  Copy and save those values, the *Server API Key* and the *Sender ID* for specifying later as the push
    notifications API keys for Liferay Push.

Now that you've set up your Google project, you can configure the Liferay Push
app to use the project's GCM API. Install the Liferay Push app from the [Liferay Marketplace](http://www.liferay.com/marketplace). In the portal's control panel,
set the Push Notifications *API Key* to the value of the API key you generated
earlier in your Google project.

![Figure 3: In the portal, you can configure the API Key for the Liferay Push app to send notifications to your Android apps.](../../images/push-portlet.png)

Your portal is now ready to send push notifications to your Android apps!

## Receiving and Sending Push Notifications [](id=receiving-and-sending-push-notifications)

The 
[Liferay Push Client for Android](https://github.com/brunofarache/liferay-push-android) 
streamlines registering a device with the portal for receiving and sending push
notifications. 

The steps for using the client are explained in detail 
[here](https://github.com/brunofarache/liferay-push-android), 
but the main steps are demonstrated here.

In your Android application's Gradle build file, add a new dependency on the
Liferay Push Client for Android:

	dependencies {

		...

		compile 'com.liferay.mobile:liferay-push:1.0.7'
	}

Make sure your app's `liferay-plugin-package.properties` file specifies the Push
Notifications portlet as a required deployment context: 

    required-deployment-contexts=\
        push-notifications-portlet\
        ...

Next, you can learn how to register listeners for push notifications. 

### Receiving Push Notifications [](id=receiving-push-notifications)

First, register your device in GCM with the `SENDER_ID` you generated
previously:

	Session session = new SessionImpl(YOUR_SERVER, new BasicAuthentication(YOUR_USER, YOUR_PASSWORD));

	Push.with(session).register(this, YOUR_SENDER_ID);

If you're using Liferay Screens to maintain a session, you can retrieve it and 
use it instead of creating a new one:

	Push.with(SessionContext.createSessionFromCurrentSession()).register(this, YOUR_SENDER_ID);

If you use these example lines of code, make sure to replace `YOUR_SERVER`,
`YOUR_USER`, `YOUR_PASSWORD`, and `YOUR_SENDER_ID` with your own values. 

That's it! You can attach a listener to store the registration ID or to process
the notification sent to the activity (using `onPushNotification()`). You can
also register a receiver and service to process the notification. You can refer
to the
[`PushNotifications`](https://github.com/liferay/liferay-screens/tree/master/android/samples)
project (especially its `PushActivity` class) as an example push notifications
implementation. 

It's time to learn how to implement sending push notifications. 

### Sending Push Notifications [](id=sending-push-notifications)

Using the Liferay Push app, sending notifications to your app's users is
straightforward. You can specify the user IDs along with the message content: 

    PushNotificationsDeviceLocalServiceUtil.sendPushNotification(userIds, content);

This
[example hook plugin](https://github.com/nhpatt/push-with-ddl-hook) sends a push
notification each time a user creates a new DDL record or updates an existing
one. 

In your app's `portal.properties` file, you can add a listener for a class by
creating a
[*value.object.listener*](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Value%20Object)
property, set to a comma separated list of intended listener classes. Here's an
example listener setting for `DDLRecord` objects:

    value.object.listener.com.liferay.portlet.dynamicdatalists.model.DDLRecord=com.liferay.push.hooks.DDLRecordModelListener

Great! Now you know how to configure your Android apps to receive push 
notifications from Liferay.

In this tutorial, you've configured your portal to accommodate push notifications,
registered notification listeners, and implemented sending push notifications.
Way to go! 

## Related Topics [](id=related-topics)

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-android-projects-for-liferay-screens)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)
