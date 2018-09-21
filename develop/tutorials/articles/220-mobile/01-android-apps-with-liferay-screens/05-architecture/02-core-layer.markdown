# Core Layer [](id=core-layer)

The core layer is the micro-framework that lets developers write Screenlets in a
structured and isolated way. All Screenlets share a common structure based on
the core classes, but each Screenlet can have a unique purpose and communication
API. 

![Figure 2: Here's the core layer of Liferay Screens for Android.](../../../../images/screens-android-architecture-02.png)

Here are the core's main components:

[**Interactor:**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/Interactor.java)
the base class for all Liferay Portal interactions and use cases that a
Screenlet supports. Interactors call services through the Liferay Mobile SDK and
receive responses asynchronously through the
[EventBus](https://github.com/greenrobot/EventBus), eventually changing a View's
state. Their actions can vary in complexity, from performing simple algorithms
to requesting data asynchronously from a server or database. A Screenlet can
have multiple Interactors, each dedicated to supporting a specific operation.

[**BaseScreenlet:**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java)
the base class for all Screenlet classes. It receives user events from a
Screenlet's View, instantiates and calls the Interactors, and then updates the
View with operation results. Classes that extend it can override its
[template methods](http://www.oodesign.com/template-method-pattern.html):

- *createScreenletView:* typically inflates the Screenlet's View and gets 
  the attribute values from the XML definition. 
- *createInteractor:* instantiates an Interactor for the specified action. 
  If a Screenlet only supports one Interactor type then that type of Interactor
  is always instantiated.
- *onUserAction:* runs the Interactor associated with the specified action.

**Screenlet View:** implements the Screenlet's UI. It's instantiated by the
Screenlet's `createScreenletView` method. It renders a specific UI using
standard layout files and updates the UI with data changes. When developing your 
own Views that extend a parent View, you can read the parent Screenlet's 
properties or call its methods from this class. 

[**EventBus:**](https://github.com/greenrobot/EventBus) notifies the Interactor
when asynchronous operations complete. It decouples the `AsyncTask` class
instance from the activity life cycle, to avoid problems typically associated
with `AsyncTask` instances.

[**Liferay Mobile SDK:**](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
calls a Liferay instance's remote services in a type-safe and transparent way.

[**SessionContext:**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/context/SessionContext.java)
a singleton class that holds the logged in user's session. Apps can use an
implicit login, invisible to the user, or a login that relies on explicit user
input to create the session. User logins can be implemented with the
[Login Screenlet](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android). 
This is explained in detail [here](/develop/tutorials/-/knowledge_base/7-0/accessing-the-liferay-session-in-android).

[**LiferayServerContext:**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/context/LiferayServerContext.java)
a singleton object that holds server configuration parameters. It's loaded from
the `server_context.xml` file, or from any other XML file that overrides the
keys defined in the `server_context.xml`.

[**server_context.xml:**](https://github.com/liferay/liferay-screens/blob/develop/android/library/core/src/main/res/values/server_context.xml) 
specifies the default server, `companyId` (Liferay instance ID) and `groupId` 
(site ID). You can also configure other Screens parameters in this file, such as 
the current Liferay version (with the attribute `liferay_portal_version`) or an 
alternative `ServiceVersionFactory` to access custom backends. 

[**LiferayScreensContext:**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/context/LiferayScreensContext.java)
a singleton object that holds a reference to the application context. It's used
internally where necessary. 

[**ServiceVersionFactory:**](https://github.com/liferay/liferay-screens/blob/develop/android/library/core/src/main/java/com/liferay/mobile/screens/util/ServiceVersionFactory.java) 
an interface that defines all the server operations supported in Liferay 
Screens. This is created and accessed through a 
[`ServiceProvider`](https://github.com/liferay/liferay-screens/blob/develop/android/library/core/src/main/java/com/liferay/mobile/screens/util/ServiceProvider.java) 
that creates the Server Connectors needed to interact with a specific Liferay 
version. The `ServiceVersionFactory` is an implementation of an 
[Abstract Factory pattern](https://en.wikipedia.org/wiki/Abstract_factory_pattern). 

Now that you know what makes up the core layer, you're ready to learn the 
Screenlet layer's details. 
