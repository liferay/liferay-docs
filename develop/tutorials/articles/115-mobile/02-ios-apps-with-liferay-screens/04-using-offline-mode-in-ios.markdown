# Using Offline Mode in iOS [](id=using-offline-mode-in-ios)

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

If you want to enable the offline mode in any of your screenlets, you must
configure the attribute `offlinePolicy`. This attribute can take four possible
values. For a description of these values, see the section 
[Using Policies with Offline Mode](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens#using-policies-with-offline-mode)
in the offline mode architecture tutorial. Note that each Screenlet behaves a
bit differently with offline mode. For specific details, see the 
[Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios). 

## Handling Synchronization [](id=handling-synchronization)

Under some scenarios, values stored in the local cache need to be synchronized 
with the portal. For that purpose you must use the `SyncManager` class. 
This class is responsible for sending the information stored in the local cache 
that hasn't been sent to the portal yet.

Use the following steps to start a synchronization process: 

1.  Create an instance of the `SyncManager` class. You must pass a `CacheManager` 
    object in the constructor. You can get the current cache manager using 
    `SessionContext.currentCacheManager`. 

2.  Set the delegate property. This delegate object receives the events produced 
    in the synchronization process. For more details on the delegate's methods, see 
    [the API reference documentation for the `SyncManagerDelegate` class](/develop/reference/-/knowledge_base/7-0/syncmanagerdelegate).

3.  Make sure you keep a strong reference to the `SyncManager` object while the 
    process is running.

## Related Topics [](id=related-topics)

[Architecture of Offline Mode in Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)

[Using Offline Mode in Android](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-android)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)
