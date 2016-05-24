# Architecture of Offline Mode in Liferay Screens [](id=architecture-of-offline-mode-in-liferay-screens)

Mobile users may encounter difficulty getting or maintaining a network 
connection at certain locations or times of day. Using offline mode with 
Screenlets ensures that your app still functions in these situations. You should 
note, however, that some difficulties may arise when using an app offline. For
example, allowing users to edit data in an app when they're offline may cause
synchronization conflicts with the portal when they reconnect. By detailing how
offline mode is implemented in Liferay Screens, this tutorial helps you be
aware of such difficulties and know how to handle them.

## Understanding Offline Mode's Basics [](id=understanding-offline-modes-basics)

Screenlets in Liferay Screens support the following phases: 

1.  Get information from the portal.
2.  Show information to the user.
3.  Collect the user's input (if necessary).
4.  Send input to the portal (if necessary).

The following diagram summarizes these phases:

![Figure 1: A Screenlet's basic phases when requesting and submitting data to the portal.](../../../images/screens-offline-01.png)

Note that not all Screenlets need to execute each phase. For example, the Web
Content Display Screenlet only needs to retrieve and display portal content.
Conversely, Login Screenlet and Sign Up Screenlet only need to handle user
input. Only the most complex Screenlets, like the DDL Form Screenlet and the
User Portrait Screenlet, need to do both. 

So what does all this have to do with offline mode? Liferay Screens's offline 
infrastructure is a small layer of code that intercepts information going to and 
coming from the portal. It stores this information in a local data store for use 
when there's no Internet connection. The following diagram illustrates this, 
with *Local cache* representing the local data store:

![Figure 2: This is the same diagram as before, with the addition of the local cache for offline mode.](../../../images/screens-offline-02.png)

With offline mode enabled, any Screenlet can persist information exchanged with 
the portal. You can also configure exactly how offline mode works with the 
Screenlet you're using. You do this through *policies*.

## Using Policies with Offline Mode [](id=using-policies-with-offline-mode)

Policies configure how a Screenlet behaves with offline mode when it sends or 
receives data. The Screenlet adheres to the policy even if the data operation 
fails. Screenlets support the following policies:

**remote-only:** The Screenlet only uses network connections to load data. 
Screenlets functioned this way prior to the introduction of offline mode. Use 
this policy when you want the Screenlet always to use remote content. Your app 
won't work, however, if a network connection is unavailable. Also, apps using 
this policy tend to be slower due to network lag. Note that if the request 
succeeds, the Screenlet stores the data in the local cache for later use.

**cache-only:** The Screenlet only uses local storage to load data (it doesn't 
use the network connection). Use this policy when you want the Screenlet to 
always use offline content. Note that in the app's local cache, some portal data 
may not exist or may be outdated.

**remote-first:** The Screenlet first tries to use the network connection to 
load data. If this fails, it then tries to load data from local storage. Use 
this policy when you want the Screenlet to use the latest portal data when 
there's a connection, but also want to support a fallback mechanism when the 
connection is gone. Note that the Screenlet may use outdated information when 
there's no connection. In many cases, however, this is better than showing your 
users no information at all.

**cache-first:** The Screenlet first tries to load data from local storage. If 
this fails, it then tries to use the network connection. Use this policy when 
you want the Screenlet to optimize performance and network efficiency. You can 
update data in a background process, or let the user update on-demand (via an 
option, for example). Note that while the information retrieved from local 
storage may be outdated, loading times and bandwidth consumption are typically 
lower.

These policies behave a bit differently depending on the data's direction. In 
other words, when a Screenlet set to a specific policy retrieves information 
from the portal, it may behave differently than when it submits information to 
the portal. As an example, consider the possible scenarios for User Portrait 
Screenlet: 

- When loading the portrait:
    - **remote-only:** The Screenlet always attempts to load the portrait from 
      the portal. If the request fails, the operation also fails.
    
    - **cache-only**: The Screenlet always attempts to load the portrait from 
      the local cache. The operation fails if the portrait doesn't exist there.
    
    - **remote-first:** The Screenlet first attempts to load the portrait from 
      the portal. If the request succeeds, the Screenlet stores the portrait 
      locally for later use. If the request fails, the Screenlet tries to load the 
      portrait from the local cache. If the local cache doesn't contain the 
      portrait, the Screenlet can't load it, and calls the standard error handling 
      code (call the delegate, use the default placeholder, etc...). 
    
    - **cache-first:** The Screenlet first attempts to load the portrait from 
      the local cache. If the portrait doesn't exist there, it's then requested 
      from the server.

- When submitting the portrait:
    - **remote-only:** The Screenlet first sends the new portrait to the portal. 
      If the submission succeeds, the Screenlet also stores the portrait in the 
      local cache. If the submission fails, the operation also fails.
    
    - **cache-only**: The Screenlet only stores the portrait locally. The 
      portrait may be loaded from the cache later, or synchronized with the 
      portal.
    
    - **remote-first:** The Screenlet first tries to send the new portrait to 
      the portal. If this fails due to lack of network connectivity, the Screenlet 
      stores the portrait in the local cache for later synchronization with the 
      portal.
    
    - **cache-first:** The screenlet first stores the new portrait locally, then 
      sends it to the portal. If the submission fails, the Screenlet still stores 
      the portrait locally, but the send operation fails.

## Understanding Synchronization [](id=understanding-synchronization)

Synchronization can be a tricky problem to solve. What initially seems 
straightforward quickly evolves into scenarios where you're not sure which 
version of the data to use. Having offline users complicates things further. The 
following diagram illustrates how the Screenlet retrieves and stores portal 
data. 

![Figure 3: The Screenlet requests the resource from the portal and stores it in the app's local cache.](../../../images/screens-offline-03.png)

When a user edits the data in the app, the Screenlet needs to send the new data 
to the portal. But what happens if the user is offline? In this case, the new 
data can't reach the portal and the local and portal data are out-of-sync. In 
this scenario, the app has the new data while the portal has the old data. The 
app's data in this synchronization state is called the *dirty version*. Put away 
your soap and washcloth. We don't recommend giving your mobile device a bath. In 
this context, dirty means that the data should be synchronized with the portal 
as soon as possible. When the Screenlet synchronizes the dirty version, it 
removes the dirty flag from the local data. 

![Figure 4: The updated data is said to be dirty when the Screenlet can't send it to the portal.](../../../images/screens-offline-04.png)

![Figure 5: The dirty flag is removed once synchronization completes.](../../../images/screens-offline-05.png)

There are other complicated synchronization states. For example, portal data may
change while out-of-sync with a Screenlet's local data. To avoid data loss, the
local data can't overwrite the portal data, and vice versa. In this situation,
the synchronization process produces a conflict when it runs.  The following
diagram illustrates this.

![Figure 6: Users have changed the data independently in the app and in the portal, causing a synchronization conflict.](../../../images/screens-offline-06.png)

The developer needs to resolve the conflict by choosing the local data or portal 
data. Synchronization conflicts have four possible resolutions:

1.  **Keep the local version:** The Screenlet overwrites the portal data with the 
    local data. This results in the local cache and the portal having the same 
    version of the data (Version 2 in the above diagram).

2.  **Keep remote version:** The Screenlet overwrites the local data with the 
    portal data. This results in the local cache and the portal having the same 
    version of the data (Version 3 in the above diagram).

3.  **Discard:** The Screenlet removes the local data, and the portal data isn't 
    overwritten.

4.  **Ignore:** The Screenlet doesn't change any data. The next synchronization 
    event reproduces the conflict.

Great! Now that you know how offline mode works, you're ready to put it to use.

## Related Topics [](id=related-topics)

[Using Offline Mode in Android](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-android)

[Using Offline Mode in iOS](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-ios)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)
