# Using Offline Mode in Android [](id=using-offline-mode-in-android)

Offline mode in Liferay Screens lets your apps function when connectivity is 
unavailable or intermittent. Even though the steady march of technology makes 
connections more stable and prevalent, there are still plenty of places the 
Internet has trouble reaching. Areas with complex terrain, including cities with 
large buildings, often lack stable connections. Remote areas typically don't 
have connections at all. Using Screens's offline mode in your apps gives your 
users flexibility in these situations. 

This tutorial shows you how to use offline mode in Screenlets. For an 
explanation of how offline mode works, see the tutorial 
[Architecture of Offline Mode in Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Offline mode's architecture is the same on iOS and Android, although its use on 
these platforms differs.

## Configuring Screenlets for Offline Mode [](id=configuring-screenlets-for-offline-mode)

If you want to enable offline mode in any of your screenlets, you must configure
the `offlinePolicy` attribute when inserting the Screenlet's XML in a layout.
This attribute can take four possible values:

- `REMOTE_ONLY`
- `CACHE_ONLY`
- `REMOTE_FIRST`
- `CACHE_FIRST`

For a description of these values, see the section 
[Using Policies with Offline Mode](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens#using-policies-with-offline-mode) 
in the offline mode architecture tutorial. Note that each Screenlet behaves a 
bit differently with offline mode. For specific details, see the 
[Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android). 

## Handling Synchronization [](id=handling-synchronization)

Under some scenarios, values stored in the local cache need to be synchronized 
with the portal. To do this, you need to use the `CacheSyncService` class. This 
class sends information from the local cache to the portal. To register 
`CacheSyncService` with your app, you must add the following code to your 
`AndroidManifest.xml` file: 

    <receiver android:name=".CacheReceiver">
        <intent-filter>
            <action android:name="com.liferay.mobile.screens.auth.login.success"/>
            <action android:name="com.liferay.mobile.screens.cache.resync"/>
            <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
        </intent-filter>
    </receiver>

    <service
        android:name=".CacheSyncService"
        android:exported="false"/>

This code registers the `CacheReceiver` and `CacheSyncService` components. The 
`CacheReceiver` is invoked in the following scenarios:

- When a connectivity change occurs (for example, when the network connection is 
restored).
- When Login Screenlet successfully completes the login.
- When a specific `resync` intent is broadcasted. In this case, use 
`context.sendBroadcast(new Intent("com.liferay.mobile.screens.cache.resync"));`.

The `CacheSyncService` performs the synchronization process when invoked from 
the above receiver. This is currently an unassisted process. Future versions 
will include some kind of control mechanism.

## Related Topics [](id=related-topics)

[Architecture of Offline Mode in Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Using Offline Mode in iOS](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-ios)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)
