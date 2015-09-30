# Using Offline Mode

Offline mode in Liferay Screens lets your apps function when connectivity is 
unavailable or intermittent. Even though the steady march of technology makes 
connections more stable and prevalent, there are still plenty of places the 
internet has trouble reaching. Areas with complex terrain, including cities with 
large buildings, often lack stable connections. Remote areas typically don't 
have a connection at all. Using Screens's offline mode in your apps gives your 
users flexibility in these situations. 

This tutorial shows you how to use offline mode in Screenlets. You'll get 
started by learning about how Screens handles offline mode.

## Architecture of Offline Mode

Screenlets in Liferay Screens support the following phases: 

1. Get information from the portal.
2. Show this information to the user.
3. Collect the user's input (if necessary).
4. Send this input back to the portal (if necessary).

This is summarized by the following diagram:

<!-- Insert diagram -->

Note that not all Screenlets need to execute each phase. For example, Web 
Content Display Screenlet only needs to retrieve and display portal content. 
Conversely, Login Screenlet and Sign Up Screenlet only need to handle user 
input. Only the most complex Screenlets, like DDL Form Screenlet and User 
Portrait Screenlet, need to do both. 

So what does all this have to do with offline mode? This is a fantastic 
question! Liferay Screens's offline infrastructure is a small layer of code that 
intercepts (in a good way) information going to and coming from the portal. It 
stores this information in a local data store for use when there's no internet 
connection. This is shown in the following diagram. Note this is the same 
diagram as before, with the addition of *Local cache* representing the local 
data store:

<!-- Insert diagram -->

With offline mode enabled, any Screenlet can persist information exchanged with 
the portal. You can also configure exactly how offline mode works with the 
Screenlet you're using. You do this through *policies*.

### Using Policies with Offline Mode

Policies configure how a Screenlet behaves when it sends or receives data. The 
Screenlet adheres to the policy even if the data operation fails. Screenlets 
support the following policies:

- **remote-only:** The Screenlet only uses network connections (the local cache 
is disabled). Screenlets functioned this way prior to the introduction of 
offline mode. Use this policy when you want your app to always use remote 
content. Your app won't work, however, if a network connection is unavailable. 
Also, apps using this policy are slower due to network lag. Note that if the 
request succeeds, the result is stored in the local cache for later use.
<!-- This makes no sense: It starts off by saying the local cache is disabled, but then says that it's not. -->

- **cache-only:** The Screenlet only uses local storage to load information (the 
network connection isn't used). Use this policy when you want your app to always 
use offline content. Note that some portal data may not exist or may be outaded 
in the app's local cache.

- **remote-first:** The Screenlet first tries to use the network connection. If 
this fails, it then tries to use the local storage. This is the typical behavior 
in apps where you want the latest information when there's a connection, but 
also want to support a fallback mechanism when the connection is gone. Note that 
your app may use outdated information when there's no connection. In many cases, 
however, this is better than showing your users no information at all.

- **cache-first:** The Screenlet first tries to use the local cache. If this 
fails, it then tries to use the network connection. This is the typical behavior 
in apps that optimize performance and network efficiency. You can update the 
data in a background process, or let the user update the data on-demand (via an 
option, for example). Note that while the information retrieved by the Screenlet 
may be outdated, the loading times and bandwidth consumption may be 
significantly lower.

These policies behave a bit differently depending on the direction the 
information is flowing. In other words, when a Screenlet set to a specific 
policy retrieves information from the portal, it may behave differently than 
when submitting information to the portal. As an example, consider the possible 
scenarios for User Portrait Screenlet:

- When loading the portrait
    - **remote-only:** The Screenlet always attempts to load the portrait from 
    the portal. If the request fails, the operation also fails.
    - **cache-only**: The Screenlet always attempts to load the portrait from 
    the local cache. If the portrait doesn't exist there, the operation also 
    fails.
    - **remote-first:** The Screenlet first attempts to load the portrait from 
    the portal. If the request succeeds, the portrait is stored in the local 
    cache for later usage. If the request fails, the Screenlet tries to load the 
    portrait from the local cache. If the local cache doesn't contain the 
    portrait, then the image isn't loaded and the standard error handling code 
    is called (call delegate, use default placeholder, etc...). 
    - **cache-first:** The Screenlet first attempts to load the portrait from 
    the local cache. If the portrait doesn't exist there, it's then requested 
    from the server.

- When submitting the portrait
    - **remote-only:** The Screenlet first sends the new portrait to the portal. 
    If the submission is successful, the portrait is also stored in the local 
    cache. If the submission fails, the operation also fails.
    - **cache-only**: The Screenlet only stores the portrait locally. The 
    portrait may be loaded from the cache later, or synchronized with the 
    portal.
    - **remote-first:** The Screenlet first tries to send the new portrait to 
    the portal. If this fails due to lack of network connectivity, the Screenlet 
    stores the portrait in the local cache for later synchronization with the 
    portal.
    - **cache-first:** The screenlet first stores the new portrait locally, then 
    sends it to the portal. If the submission fails, the portriat is still 
    stored locally, but the send operation fails.

### Understanding Synchronization

When a Screenlet sends information to the portal, the data may also be stored in 
the app's local cache. If the portal request fails, then the new content doesn't 
reach the portal, resulting in the local and portal data being out-of-sync. In 
this scenario, the app has the most recent content, while the portal has the old 
content. The app's data is referred to as the *dirty version*, meaning it should 
be synchronized with the portal as soon as possible. When the dirty version gets 
synchronized with the portal, the dirty flag is removed from the local content.

<!-- Insert diagram -->

Note that things can get more complex. For example, when the remote version 
changes while the local version is out-of-sync, the synchronization process 
produces a conflict. The local version can't overwrite the remote one because 
this causes data loss, and the remote version can't overwrite the local version 
for the same reason. In this case, the developer needs to resolve the conflict 
by choosing between the local or remote versions. 

<!-- Insert diagram -->

Synchronization conflicts have four possible resolutions:

1. **Keep local version:** The remote version is overwritten with the local 
version. This results in the local cache and the portal having the same version 
(Version 2a in the above diagram).

2. **Keep remote version:** The local version is overwritten with the remote 
version. This results in the local cache and the portal having the same version 
(Version 2b in the above diagram).

3. **Discard:** The local version is removed and the remote version isn't 
overwritten.

4. **Ignore:** No data is changed, so the next synchronization event reproduces 
the conflict.
