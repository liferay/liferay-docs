# Using Offline Mode in iOS

Offline mode in Liferay Screens lets your apps function when connectivity is 
unavailable or intermittent. Even though the steady march of technology makes 
connections more stable and prevalent, there are still plenty of places the 
internet has trouble reaching. Areas with complex terrain, including cities with 
large buildings, often lack stable connections. Remote areas typically don't 
have a connection at all. Using Screens's offline mode in your apps gives your 
users flexibility in these situations. 

This tutorial shows you how to use offline mode in Screenlets. For an 
explanation of how offline mode works, see the tutorial 
[Architecture of Offline Mode in Liferay Screens](http://www.liferay.com/). 
Offline mode's architecture is the same on iOS and Android, although its use on 
these platforms differs.

## Configuring Screenlets for Offline Mode

If you want to enable the offline mode in any of your screenlets, you just need 
to configure the attribute offlinePolicy. This attribute can take four possible 
values. For a description of these values, see the section 
[Using Policies with Offline Mode](http://www.liferay.com/) 
in the offline mode architecture tutorial. Note that each Screenlet behaves a 
bit differently with offline mode. For specific details, see the 
[Screenlet reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios). 

## Handling Synchronization

Under some scenarios, values stored in the local cache need to be synchronized 
with the portal ones. For that purpose you need to use the `SyncManager` class. 
This class is responsible for sending the information stored in the local cache 
that hasn’t been sent to the portal yet.

Use the following steps to start a synchronization process: 

1. Create an instance of `SyncManager` class. You need to pass a `CacheManager` 
object in the constructor. You can get the current cache manager using 
`SessionContext.currentCacheManager`. 

2. Set the delegate property. This delegate object will receive the events 
produced in the synchronization process. For more details on the delegate 
methods, read the section below.

3. Make sure you keep a strong reference to the `SyncManager` object while the 
process is running.

For information on the `SyncManagerDelegate` class, see 
[its API reference documentation](http://www.liferay.com/).

## Related Topics

[Architecture of Offline Mode in Liferay Screens](http://www.liferay.com/)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)

[Using Offline Mode in Android](http://www.liferay.com/)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)
