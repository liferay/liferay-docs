# Architecture of Liferay Screens for Android [](id=architecture-of-liferay-screens-for-android)

Liferay Screens applies architectural ideas from
[Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter), 
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel), and 
[VIPER](http://www.objc.io/issue-13/viper.html). Screens isn't considered a
canonical implementation of these architectures, because it isn't an app, but it
borrows from them to separate presentation layers from business-logic. This
tutorial explains Screen's high-level architecture, its component's low-level
architecture, and the Android screenlet lifecyle. Now, go ahead and get started
examining Screens's building blocks! 

## High-Level Architecture [](id=high-level-architecture)

Liferay Screens for Android is composed of a core, a screenlet layer, a view
layer, and interactors. Interactors, though technically part of the core, are
an important component type that facilitate communication between the screenlet
layer and the Liferay Mobile SDK. Figure 1 illustrates the high-level
architecture. 

![Figure 1: Here are the high-level components of Liferay Screens for Android. The arrows represent a "uses" relationship.](../../images/screens-android-architecture-01.png)

Each component is described below.

*Core*: includes all the base classes for developing other Screens components.
It's a micro-framework that lets developers write their own screenlets, views,
and interactors. 

*Screenlets*: Java View classes for inserting in any activity or fragment view
hierarchy. They render a selected layout in the runtime and in Android Studio's
visual editor, and react to UI events, sending any necessary server requests.
You can set a screentlet's properties from its layout XML file and Java code. 

*Interactors*: implement specific user interactions or use cases. They can interact
with local and remote data sources. Most interactors use the
[Liferay Mobile SDK](/tutorials/-/knowledge_base/6-2/mobile) to exchange data
with a Liferay instance. 

*Views*: a set of layouts and accompanying custom view classes that present 
screenlets to the user.

Next, the core layer is described in detail. 

## Core Layer [](id=core-layer)

The core layer is the micro-framework that lets developers write screenlets in a
structured and isolated way. All screenlets share a common structure based on
the core classes, but each screenlet can have a unique purpose and communication
API. 

![Figure 2: Here's the core layer of Liferay Screens for Android.](../../images/screens-android-architecture-02.png)

Here are the core classes:

*Interactor*: the base class for all Liferay portal interactions and use cases
that a screenlet supports. Interactors call services through the Liferay Mobile
SDK and receive responses asynchronously through the EventBus, eventually
changing a view's state. Their actions can vary in complexity, from performing
simple algorithms to requesting data asynchronously from a server or database. A
screenlet can have multiple interactors; each operation a screenlet supports
typically requires a dedicated interactor. 

*BaseScreenlet*: the base class for all screenlet classes. It primarily receives
user events from a screentlet's view, instantiates and calls the interactors,
and then updates the view with operation results. Classes that extend it can
override its
[template methods](http://www.oodesign.com/template-method-pattern.html):

- *createScreenletView*: typically inflates the screenlet's view and gets the
  attribute values from the XML definition. 
- *createInteractor*: instantiates an interactor for the specified action. If a
  screenlet only supports one interactor type then that type of interactor is
  always instantiated. 
- *onUserAction*: runs the interactor associated with the specified action.

*ScreenletView*: implements the screenlet's UI. It's instantiated by the
screenlet's `createScreenletView` method. It renders a specific UI using
standard layout files and then update the UI with data changes.

*InteractorAsyncTaskCallback*: receives asynchronous responses from the Liferay
Mobile SDK's `AsyncTask` class.

*[EventBus](https://github.com/greenrobot/EventBus)*: notifies the interactor
when asynchronous operations complete. It decouples the `AsyncTask` class
instance from the activity lifecycle, to avoid problems typically associated
with `AsyncTask` instances. 

*[MobileSDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)*: 
calls Liferay's remote services in a type-safe and transparent way.

*SessionContext*: a singleton object that holds the logged in user's session.
Apps can use an implicit login, invisible to the user, or a login that relies on
explicit user input to create the session. User logins can be implemented with
`LoginScreenlet`.

*LiferayServerContext*: a singleton object that holds server configuration
parameters. It's loaded from the `server_context.xml` file, or from any other
XML file that overrides the keys defined in the `server_context.xml`. 

*LiferayScreensContext*: a singleton object that holds a reference to the
application context. It's used internally where necessary.

Now that you know what the core layer is comprised of, you're ready to learn the 
screenlet layer's details.

## Screenlet Layer [](id=screenlet-layer)

The screenlet layer contains the screenlets available in Liferay Screens for
Android. The following diagram uses screenlet classes prefixed with
*MyScreenlet* to show the screenlet layer's relationship with the core, view,
and interactor components.  

![Figure 3: This diagrame illustrates the Android screenlet layer's relationship to other Screens components.](../../images/screens-android-architecture-03.png)

Screenlets are comprised of several Java classes and an XML descriptor file:

*ViewModel*: an interface that defines the attributes shown in the
  UI. It typically accounts for all the input and output values presented to the 
  user. For instance, `LoginScreenletViewModel` includes attributes like the 
  user name and password. The screenlet can read the attribute values, invoke  
  interactor operations, and change the values based on operation results. 

*Screenlet*: a class that represents the screenlet component the developer
interacts with. It's comprised of the following things:

- Attribute fields for configuring the screenlet's behavior. They are
  read in the screenlet's `createScreenletView` method and their default values
  can optionally be set there too. 
- A reference to the screenlet's view, specified by the `liferay:layoutId`
  attribute's value. Note: a view must implement the screenlet's `ViewModel`
  interface. 
- Any number of methods for invoking interactor operations. You can optionally
  make them public, for app developers to call. They can also handle UI events
  received in the `View` class through a regular listener (such as Android's
  `OnClickListener`) or events forwarded to the screenlet via the
  `performUserAction()` method.
- An optional (but recommended) listener object for the screenlet to call on a
  particular event. 

*Interactor*: implements an end-to-end use case that consumes a Liferay service.
It may perform several intermediate steps. For example, it may send a request to
a server, compute a local value based on the response, and then send this value
to a different server. On completing an interaction, the interactor must notify
its listener, which is typically its screenlet. 

The number of interators a screenlet requires depends on the number of Liferay
service use cases it supports. For example, the `LoginScreenlet` only supports
one use case (log in the user), so it has only one interactor. Whereas, the
`DDLFormScreenlet` supports several use cases (load the form, load a record,
submit the form, etc...); so it uses a different interactor class for each use
case. 

*DefaultView*: a class that renders the screenlet's UI with the default layout.
The class in Figure 3, for example, belongs to the Default view set. The view
object and the layout file communicate using standard mechanisms, like a
`findViewById` method or a listener object. User actions are received by a
specified listener (for example, `OnClickListener`) and then passed to the
screenlet object using the `performUserAction` method.

*Default Layout*: an XML file that specifies how to render the screenlet's view.
Here's a skeleton of a screenlet's layout XML file: 

    <?xml version="1.0" encoding="utf-8"?>
    <com.your.package.MyScreenletView 
        xmlns:android="http://schemas.android.com/apk/res/android">
    
        <!-- Put your regular components here: EditText, Button, etc. -->

    </com.your.package.MyScreenletView>

Refer to
[Creating Android Screenlets](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-android)
for more screenlet details. Next, the view layer's details are described. 

## The View Layer [](id=the-view-layer)

The view layer lets developers set a screenlet's look and feel. Each screenlet's
`liferay:layoutId` attribute specifies its view. 

<!-- 
![Figure 4: The view layer of Liferay Screens for Android.](../../images/screens-android-architecture-04.png)
-->

There are several different view types:

*Default*: a mandatory view set that Liferay supplies; screenlets use it if no
layout ID is specified or if the ID is invalid. The Default view set uses a
neutral flat white and blue design with standard UI components. In the
`LoginScreenlet`, for example, the view uses standard text boxes for the user
name and password, but the text boxes are styled with the Default view's flat
white and blue design. You can customize aspects of this view set, such as its
colors, positions, and sizes. You can refer to the `styles.xml` file. 

*Child*: inherits the behavior of its parent view, without including any code.
Child views only contain a new layout file, which can specify different colors,
component positions, or any other visual changes. Because the changes in Child
views are only visual, its UI components and their identifiers must match those
of the parent view. For a single scenario, creating a Child view can be a good
alternative to implementing a completely new UI. You can, for example, create a
Child view from the `LoginScreenlet`'s Default view and configure the layout
file with new positions and sizes for the standard text boxes. 

*Extended*: inherits another view's behavior and code. They let you introduce
new UI components or functionality. An extended view provides a specific view
class, that extends the parent's view class. You can, for example, extend the
`LoginScreenlet`'s view to present different components for the user name and
password fields. 

*Full*: can customize the screenlet listeners and call custom intereractors. You
could implement a Full view, for example, with an additional new interactor for
calling a different Liferay Portal instance. Default views are Full views. 

For more information, see the tutorial 
[Creating Android Views](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android). 

Great! Now you know how Liferay Screens for Android is composed. However,
there's something you should know before moving on: how screenlets interact with
the Android lifecycle. 

## Screenlet Lifecycle [](id=screenlet-lifecycle)

Liferay Screens automatically saves and restores screenlets state using the
Android SDK methods `onSaveInstanceState` and `onRestoreInstanceState`. Each
screenlet uses a uniquely generated identifier (`screenletId`) assign action
results to action sources. 

The screenlets' state is restored after the `onCreate` and `onStart` methods, as
specified by the
[standard Android lifecycle](http://developer.android.com/training/basics/activity-lifecycle/recreating.html).
It's best practice to execute screenlet methods inside of the activity's
`onResume` method; this helps assure that actions and tasks find their
destinations. 

Awesome! Now you know the nitty gritty architectural details of Liferay Screens
for Android. Let this tutorial be a resource for you as you work with Liferay
Screens. 

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-android-project)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-views-in-android-screenlets)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-android)

[Creating Android Views](/develop/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android)
