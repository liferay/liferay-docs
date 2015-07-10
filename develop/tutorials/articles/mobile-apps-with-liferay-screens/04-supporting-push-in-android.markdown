# Supporting Push Notifications in Android

Liferay Screens is capable of supporting push notifications. To support push 
notifications 
([GCM](https://developers.google.com/cloud-messaging/)) in your Android app, 
there are a few steps you need to follow. This tutorial describes these steps. 

## Create a Google Project and Enable Push

The first things you need to do are in the 
[Google Developers Console](https://console.developers.google.com/). In the 
Google Developers Console, you need to create a Google project, generate a 
`SENDER_ID` and *API Key*, and enable push notifications for your Google 
project. The following steps describe this process:

1. Create a Google project in the Google Developers Console. After creation, 
   the *Project Number* (next to your Project ID) is your `SENDER_ID`. Copy this 
   to a text file so you can access it quickly.

    ![Figure 1: An example Google project with the Project Number showing in the upper right hand corner of the Project Dashboard.](../../images/screens-android-push-project-number.png)

2. Enable push notifications for your new Google project by going to 
   *APIs & auth* &rarr; *APIs* &rarr; *Cloud Messaging for Android*, and 
   clicking *Enable API*.

3. Now you need to create a server key. First navigate to *APIs & auth* &rarr; 
   *Credentials* &rarr; *Create new Key (Server Key)*. Select *Create* and copy 
   the API Key string. This string acts as your API Key in Liferay Portal. 
   Copy this to a text file so you can access it quickly.

    ![Figure 2: Create a server key to act as the API Key in your portal.](../../images/screens-android-push-server-key.png)

Now that you've set up your Google project, you're ready to install and 
configure Liferay Push in your portal.

## Install and configure Liferay Push in Liferay Portal

For your Screens app to receive push notifications, your portal must be able to 
send them. Fortunately, the 
[Liferay Push](http://www.liferay.com/marketplace/-/mp/application/48439053) 
plugin on Marketplace streamlines your portal's push configuration. Install the 
Liferay Push plugin from Marketplace and then configure the API Key using the 
server key you generated earlier. 

![Figure 3: Configure the API Key in the Liferay Push portlet.](../../images/push-portlet.png)

Your portal is now ready to send push notifications to Android apps!

## Using Liferay Push in your Android Application

The 
[Liferay Push Client for Android](https://github.com/brunofarache/liferay-push-android) 
streamlines the complex flow of registering a device with the portal for 
sending and receiving push notifications.

The steps for using this client are explained in detail 
[here](https://github.com/brunofarache/liferay-push-android), 
but the main steps are shown next:

1. Add a new Gradle dependency to the Liferay Push Client for Android:

        dependencies {

            ...

            compile 'com.liferay.mobile:liferay-push:1.0.2'
        }

2. Register your device in GCM with the `SENDER_ID` you previously generated:

        Session session = new SessionImpl(YOUR_SERVER, new BasicAuthentication(YOUR_USER, YOUR_PASSWORD));

        Push.with(session).register(this, YOUR_SENDER_ID);
	
    If you're using Liferay Screens to maintain a session, you can retrieve and 
    use it instead of creating a new one:

        Push.with(SessionContext.createSessionFromCurrentSession()).register(this, YOUR_SENDER_ID);

3. That's it! You can attach a listener to store the registration id or to 
process the notification sent to the activity (setting `onPushNotification()`). 
You can also register a receiver and service to process the notification (as 
shown in the example project).

To see an example of push notifications in action, see the 
[`PushNotifications`](https://github.com/liferay/liferay-screens/tree/master/android/samples) 
example project (especially the `PushActivity` class). 

## Using Liferay Push in Your Server

By using the Liferay Push plugin, sending push notifications to your Android 
users is very straightforward. Just send the user ids and the message content as 
follows: 

    PushNotificationsDeviceLocalServiceUtil.sendPushNotification(userIds, content);

An example 
[hook](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal) 
that sends a push notification each time a user creates a new DDL record or 
updates an existing one is available 
[here](https://github.com/nhpatt/push-with-ddl-hook). 

Note that your project should depend on the `push-notifications-portlet` 
(in `liferay-plugin-package.properties`):

    required-deployment-contexts=\
        push-notifications-portlet

Also, link your Java class with the model you want to listen for. For example:

    value.object.listener.com.liferay.portlet.dynamicdatalists.model.DDLRecord=com.liferay.push.hooks.DDLRecordModelListener

Great! Now you know how to configure your Android apps to receive push 
notifications sent from Liferay.

## Related Topics

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-android-projects-for-liferay-screens)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)
