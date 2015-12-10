# Architecture of Liferay Screens for Android [](id=architecture-of-liferay-screens-for-android)

Liferay Screens applies architectural ideas from
[Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter), 
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel), and 
[VIPER](http://www.objc.io/issue-13/viper.html). Screens isn't considered a
canonical implementation of these architectures, because it isn't an app, but it
borrows from them to separate presentation layers from business-logic. This
tutorial explains Screen's high-level architecture, its components' low-level
architecture, and the Android Screenlet lifecyle. Now go ahead and get started
examining Screens's building blocks! 

## High-Level Architecture [](id=high-level-architecture)

Liferay Screens for Android is composed of a Core, a Screenlet layer, a View
layer, and Interactors. Interactors are technically part of the core, but are 
worth covering separately. They facilitate interaction with both local and
remote data sources, as well as communication between the Screenlet layer and
the [Liferay Mobile SDK](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/mobile). 

![Figure 1: Here are the high-level components of Liferay Screens for Android. The dashed arrow connectors represent a "uses" relationship, in which a component uses the component its pointing to.](../../images/screens-android-architecture-01.png)

Each component is described below.

**Core:** includes all the base classes for developing other Screens components.
It's a micro-framework that lets developers write their own Screenlets, Views,
and Interactors.

**Screenlets:** Java view classes for inserting into any activity or fragment view
hierarchy. They render a selected layout in the runtime and in Android Studio's
visual editor and react to UI events, sending any necessary server requests.
You can set a screentlet's properties from its layout XML file and Java classes.
The Screenlets bundled with Liferay Screens are known collectively as the
[Screenlet Library](https://dev.liferay.com/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android). 

**Interactors:** implement specific use cases for communicating with servers.
They can use local and remote data sources. Most Interactors use the
[Liferay Mobile SDK](/develop/tutorials/-/knowledge_base/6-2/mobile) 
to exchange data with a Liferay instance. If a user action or use case needs to 
execute more than one query on a local or remote store, the sequence is done 
in the corresponding Interactor. If a screenlet supports more than one user 
action or use case, an Interactor needs to be created for each. 

**Views:** a set of layouts and accompanying custom view classes that present 
Screenlets to the user.

Next, the core layer is described in detail. 

## Core Layer [](id=core-layer)

The core layer is the micro-framework that lets developers write Screenlets in a
structured and isolated way. All Screenlets share a common structure based on
the core classes, but each Screenlet can have a unique purpose and communication
API. 

![Figure 2: Here's the core layer of Liferay Screens for Android.](../../images/screens-android-architecture-02.png)

Here are the core's main components:

[**Interactor:**](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/Interactor.java)
the base class for all Liferay Portal interactions and use cases that a
Screenlet supports. Interactors call services through the Liferay Mobile SDK and
receive responses asynchronously through the
[EventBus](https://github.com/greenrobot/EventBus), eventually changing a View's
state. Their actions can vary in complexity, from performing simple algorithms
to requesting data asynchronously from a server or database. A Screenlet can
have multiple Interactors, each dedicated to supporting a specific operation.

[**BaseScreenlet:**](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java)
the base class for all Screenlet classes. It receives user events from a
screenlet's View, instantiates and calls the Interactors, and then updates the
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
standard layout files and updates the UI with data changes.

[**InteractorAsyncTaskCallback:**](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/InteractorAsyncTaskCallback.java)
receives asynchronous responses from the Liferay Mobile SDK's `AsyncTask` class.

[**EventBus:**](https://github.com/greenrobot/EventBus) notifies the Interactor
when asynchronous operations complete. It decouples the `AsyncTask` class
instance from the activity life cycle, to avoid problems typically associated
with `AsyncTask` instances.

[**Liferay Mobile SDK:**](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
calls a Liferay instance's remote services in a type-safe and transparent way.

[**SessionContext:**](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/context/SessionContext.java)
a singleton class that holds the logged in user's session. Apps can use an
implicit login, invisible to the user, or a login that relies on explicit user
input to create the session. User logins can be implemented with the
[Login Screenlet](https://dev.liferay.com/develop/reference/-/knowledge_base/6-2/loginscreenlet-for-android). It is explained in detail [here](/develop/tutorials/-/knowledge_base/6-2/accessing-liferay-session-and-logged-user).

[**LiferayServerContext:**](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/context/LiferayServerContext.java)
a singleton object that holds server configuration parameters. It's loaded from
the `server_context.xml` file, or from any other XML file that overrides the
keys defined in the `server_context.xml`.

[**LiferayScreensContext:**](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/context/LiferayScreensContext.java)
a singleton object that holds a reference to the application context. It's used
internally where necessary. 

Now that you know what the core layer comprises, you're ready to learn the 
Screenlet layer's details.

## Screenlet Layer [](id=screenlet-layer)

The Screenlet layer contains the Screenlets available in Liferay Screens for
Android. The following diagram uses Screenlet classes prefixed with
*MyScreenlet* to show the Screenlet layer's relationship with the core, View,
and Interactor components. 

![Figure 3: This diagram illustrates the Android Screenlet layer's relationship to other Screens components.](../../images/screens-android-architecture-03.png)

Screenlets are comprised of several Java classes and an XML descriptor file:

**MyScreenletViewModel:** an interface that defines the attributes shown in the
UI. It typically accounts for all the input and output values presented to the
user. For instance,
[`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/view/LoginViewModel.java)
includes attributes like the user name and password. The Screenlet can read the
attribute values, invoke Interactor operations, and change these values based
on operation results.

**MyScreenlet:** a class that represents the Screenlet component the app
developer interacts with. It includes the following things:

- Attribute fields for configuring the Screenlet's behavior. They are read in
  the Screenlet's `createScreenletView` method and their default values can 
  optionally be set there too.
- A reference to the Screenlet's View, specified by the `liferay:layoutId` 
  attribute's value. Note: a View must implement the Screenlet's `ViewModel`
  interface. 
- Any number of methods for invoking Interactor operations. You can optionally
  make them public for app developers to call. They can also handle UI events
  received in the view class through a regular listener (such as Android's
  `OnClickListener`) or events forwarded to the Screenlet via the
  `performUserAction` method.
- An optional (but recommended) listener object for the Screenlet to call on a 
  particular event. 

**MyScreenletInteractor:** implements an end-to-end use case that communicates
with a server or consumes a Liferay service. It might perform several
intermediate steps. For example, it might send a request to a server, compute a
local value based on the response, and then send this value to a different
server. On completing an interaction, the Interactor must notify its listeners,
one of which is typically the Screenlet class instance. The number of
Interactors a Screenlet requires depends on the number of server use cases it
supports. For example, the
[Login Screenlet](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/LoginScreenlet.java)
class only supports one use case (log in the user), so it has only one
Interactor. The
[DDL Forms Screenlet](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/ddl/form/DDLFormScreenlet.java)
class, however, supports several use cases (load the form, load a record, submit
the form, etc.), so it uses a different Interactor class for each use case.

**MyScreenletDefaultView:** a class that renders the Screenlet's UI with the
default layout. The class in Figure 3, for example, belongs to the Default View
set. The View object and the layout file communicate using standard mechanisms,
like a `findViewById` method or a listener object. User actions are received by
a specified listener (for example, `OnClickListener`) and then passed to the
Screenlet object via the `performUserAction` method. 

**myscreenlet_default.xml:** an XML file that specifies how to render the
Screenlet's View. Here's a skeleton of a Screenlet's layout XML file: 

    <?xml version="1.0" encoding="utf-8"?>
    <com.your.package.MyScreenletView 
        xmlns:android="http://schemas.android.com/apk/res/android">

        <!-- Put your regular components here: EditText, Button, etc. -->

    </com.your.package.MyScreenletView>

Refer to the tutorial
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)
for more Screenlet details. Next, the View layer's details are described. 

## View Layer [](id=view-layer)

The View layer lets developers set a Screenlet's look and feel. Each Screenlet's
`liferay:layoutId` attribute specifies its View. A View consists of a Screenlet
class, view class, and layout XML file. The layout XML file specifies the UI
components, while the Screenlet class and view class control the View's
behavior. By inheriting one or more of these View layer components from another
View, the different View *types* allow varying levels of control over a
Screenlet's UI design and behavior.

There are several different View types:

**Child:** presents the same behavior and UI components as its parent, but can
change the UI components' appearance and position. A Child View specifies visual
changes in its own layout XML file; it inherits the parent's view class and
Screenlet class. It can't add or remove any UI components. The parent must be a
Full View. Creating a Child View is ideal when you only need to make visual
changes to an existing View. For example, you might create a Child View for
Login Screenlet's Default View to set new positions and sizes for the standard
text boxes.

**Extended:** inherits the parent View's behavior and appearance, but lets you
change and add to both. You can do so by creating a custom view class and a new
layout XML file. An Extended View inherits all the parent View's other
classes, including its Screenlet, listeners, and Interactors; if you need to
customize any of them, you must create a Full View to do so. An Extended View's
parent must be a Full View. Creating an Extended View is ideal for adding,
removing, or changing an existing View's UI components. For example, you can
extend the Login Screenlet's Default View to present different UI components for
the user name and password fields.

**Full:** provides a complete standalone View. It doesn't inherit another View's
UI components or behavior. When creating a Full View, you must therefore create
its Screenlet class, view class, and layout XML file. You should create a Full
View when you don't need to inherit another View or when you need to alter the
core behavior of a Screenlet by customizing its listeners or calling custom
Interactors. For example, you could implement a Full View with a new Interactor
for calling a different Liferay Portal instance. Default Views are Full Views.

Liferay Screens Views are organized into *View sets* that contain Views for
several Screenlets. Liferay's available View sets are listed here: 

- [*Default:*](https://github.com/liferay/liferay-screens/tree/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews)
  a mandatory View Set supplied by Liferay. It's used by a Screenlet if no
  layout ID is specified or if no View is found with the layout ID. The Default
  View Set uses a neutral, flat white and blue design with standard UI
  components. In the
  [Login Screenlet](https://github.com/liferay/liferay-screens/tree/1.1.0/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login),
  for example, the Default View uses standard text boxes for the user name and
  password, but the text boxes are styled with the Default View's flat white and
  blue design. You can customize this View Set's properties, such as its
  components' colors, positions, and sizes. See the Default View Set's
  [`styles.xml`](https://github.com/liferay/liferay-screens/blob/1.1.0/android/library/core/src/main/res/values/styles.xml)
  file for specific values. Since the Default View Set contains Full Views, you
  can use them to create your own custom Child and Extended Views.

- [*Material:*](https://github.com/liferay/liferay-screens/tree/1.1.0/android/viewsets/material)
  the View Set containing Views that conform to Android's [Material design guidelines](https://developer.android.com/design/material/index.html).

- [*Westeros:*](https://github.com/liferay/liferay-screens/tree/1.1.0/android/viewsets/westeros) 
  the View Set containing Views for the 
  [Bank of Westeros](https://github.com/liferay/liferay-screens/tree/1.1.0/android/samples/bankofwesteros) 
  sample app.

For information on creating Views, see the tutorial 
[Creating Android Views](/develop/tutorials/-/knowledge_base/6-2/creating-android-views). 

Great! Now you know how Liferay Screens for Android is composed. However,
there's something you should know before moving on: how Screenlets interact with
the Android life cycle. 

## Screenlet Lifecycle [](id=screenlet-lifecycle)

Liferay Screens automatically saves and restores Screenlets' states using the
Android SDK methods `onSaveInstanceState` and `onRestoreInstanceState`. Each
Screenlet uses a uniquely generated identifier (`screenletId`) to assign action
results to action sources. 

The Screenlets' states are restored after the `onCreate` and `onStart` methods, as
specified by the
[standard Android lifecycle](http://developer.android.com/training/basics/activity-lifecycle/recreating.html).
It's a best practice to execute Screenlet methods inside the activity's
`onResume` method; this helps assure that actions and tasks find their
destinations. 

Awesome! Now you know the nitty gritty architectural details of Liferay Screens
for Android. Let this tutorial be a resource for you as you work with Liferay
Screens. 

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-views-in-android-screenlets)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)

[Creating Android Views](/develop/tutorials/-/knowledge_base/6-2/creating-android-views)
