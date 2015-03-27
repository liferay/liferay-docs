# Architecture of Liferay Screens for Android

This document explains the internal architecture of Liferay Screens for Android. 
It begins with an overview of the high level components that make up the system, 
such as the core, screenlets, and views. A description of these components is 
then presented in the sections that follow.

## High Level Architecture

Liferay Screens separates presentation and business-logic code, following the 
ideas of the [Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter), 
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel), and 
[VIPER](http://www.objc.io/issue-13/viper.html) architectures. However, Screens 
can't be considered a canonical implementation of these architectures. This is 
because they're designed for apps. Screens isn't an app, but is instead a suite 
of visual components intended for use in apps. Liferay Screens for Android 
contains the following high level components:

![The high level components of Liferay Screens for Android.](http://liferay.github.io/liferay-screens/android/library/svg/architecture-components.svg)

- *Core*: Includes all the base classes needed to develop other components. The 
  core is defined as a micro-framework that lets developers write their own 
  screenlets, views, and interactor classes.

- *Screenlets*: The library that contains all the available screenlets. Each 
  screenlet is a Java View class that can be inserted in any activity or 
  fragment view hierarchy. Screenlets render the selected layout both in the 
  runtime and Android Studio's visual editor. They also react to user interface 
  events, sending server requests if necessary. Screenlets also define a set of 
  configurable properties that can be set from the layout XML and your Java 
  code.

- *Interactors*: A collection of classes that implement one specific user 
  interaction or use case. These classses can interact with both remote and 
  local data sources. Most of the interactors need to send/receive data to/from 
  Liferay Portal. In this case, interactors use the [Mobile SDK](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-ios-app).

- *Views*: A set of layout and accompanying custom view classes that present 
  screenlets to the user.

## The Core Layer

The core layer is the micro-framework that lets developers write their own
screenlets in a structured and isolated way. This is possible because each
screenlet has a clear purpose and communication API. Therefore, even components
developed by different people share a common structure.

![The core layer of Liferay Screens for Android.](http://liferay.github.io/liferay-screens/android/library/svg/architecture-core.svg)

- *Interactor*: The base class for all interactions and use cases supported by 
  the screenlet. These actions can range in complexity from simple algorithms to 
  asynchronous data requests from a server or database. A screenlet can have 
  more than one interactor, typically one for each kind of supported operation. 
  An interactor can call Liferay Mobile SDK services and receive the responses
  asynchronously through the EventBus, eventually changing the state of the view
  classes.
  
- *BaseScreenlet*: The base class for all screenlet classes. The main task of a
  screenlet class is to receive user action events from the screenlet's view,
  instantiate and call interactors, and then update any view data with the 
  result. This class contains a set of [templated methods](http://www.oodesign.com/template-method-pattern.html) 
  that are intended to be overriden by children classes. These methods are 
  listed here:

	- *createScreenletView*: Typically inflates the screenlet's view and
	  gets the attribute values from the XML definition. 
	- *createInteractor*: Instantiates an interactor of a specific type, 
	  depending on the action name supplied. The action name specifies the 
	  interactor type. If a screenlet only supports one interactor type, 
	  then the action name may be ignored. 
	- *onUserAction*: Runs the interactor intended for use with an action 
	  name.

- *ScreenletView*: The class that implements the screenlet UI. The object is 
  created in the screenlet's `createScreenletView` method. The main task of the 
  view class is to render a specific UI using standard layout files and then 
  update the UI when the data changes.
  
- *InteractorAsyncTaskCallback*: Receives asynchronous responses from the 
  Liferay Mobile SDK's `AsyncTask`.

- *[EventBus](https://github.com/greenrobot/EventBus)*: The bus implementation 
  used to notify the interactor when asynchronous operations complete. This 
  decouples `AsyncTask` from the activity lifecycle, avoiding most of the 
  typical problems associated with `AsyncTask`.
  
- *[MobileSDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)*: 
  Used to call Liferay's remote services in a typesafe and transparent way.

- *SessionContext*: A singleton object that holds the logged in user's session.
  Apps can use an implicit login, which is invisible to the user, or a login 
  that relies on explicit user input to create the session. User logins can be
  implemented with `LoginScreenlet`.
  
- *LiferayServerContext*: A singleton object that holds server configuration
  parameters. It's loaded from the `server_context.xml` file, or from any other
  XML file that overrides the keys defined in that file.
  
- *LiferayScreensContext*: A singleton object that holds a reference to the
  application context. It's used internally where the context is necessary.

## The Screenlet Layer

The screenlet layer contains the screenlets available in Liferay Screens for
Android. The following diagram shows the screenlet layer in relation to the 
core, view, and interactor components. The screenlet classes detailed in the
diagram are explained in this section.

![The screenlet layer in relation to the other components of Liferay Screens.](http://liferay.github.io/liferay-screens/android/library/svg/architecture-screenlets.svg)

- *MyScreenletViewModel*: An interface that defines the attributes shown in the
  UI. It typically includes all the input and output values presented to the 
  user. For instance, `LoginScreenletViewModel` includes attributes like the 
  user name and password. The screenlet reads those values to start the 
  interactor's operations and may eventually change them based on the 
  interactor's result.

- *MyScreenlet*: The class that represents the screenlet component that the
  developer interacts with. It includes:

	- A set of attributes that allows the screenlet's behavior to be
	  configured. The attribute values are read with the screenlet's
	  `createScreenletView` method. Optionally, the default values can be 
	  also set there. 
	- A reference to its view, depending on the `liferay:layoutId` 
	  attribute's value. To meet the screenlet's requirements, all views 
	  must implement the corresponding `ViewModel` interface. 
	- Zero or more methods that start the interactor's operations. These can 
	  be public methods intended to be called by a developer, such as 
	  `loadMyData()`. These methods can also be UI events received in the 
	  `View` class through a regular listener (such as Android's 
	  `OnClickListener`) and forwarded to the screenlet by using the
	  `performUserAction()` method. 
	- A listener object, provided by the app, intented to be called when any 
	  event occurs. This is optional, but recommended.

- *MyScreenletInteractor*: The screenlet's interactor. A screenlet can have zero
  or more interactors, depending on the number of use cases it supports. For
  example, the `LoginScreenlet` only supports one use case (to log the user in),
  so it has only one interactor. However, the `DDLFormScreenlet` supports 
  several use cases (load the form, load a record, submit the form, etc...), so 
  it includes one interactor for each. It's important to note that an interactor
  implements an end-to-end operation to achieve a result, so it may perform
  several steps in turn. For example, an interactor can first send a request to 
  a server and then compute a local value based on the response. It can then 
  send this value to a different server. When the interaction is finished, the
  interactor must notify to its listener, which is typically its screenlet.
  
- *MyScreenletDefaultView*: The class that renders the UI of the screenlet by
  using the screenlet's associated layout `myscreenlet_default.xml`. In the
  diagram this class belongs to the Default view set. The view object and the
  layout file communicate using standard mechanisms like `findViewById` and
  listeners. When a user action occurs in the UI, it's received by the specified
  listener (for example, `OnClickListener`) and then passed to the screenlet
  object using the `performUserAction()` method.

- *myscreenlet_default.xml*: The layout file with the definitions of the 
  components used to render the screenlet's view. Typically, the XML appears as 
  follows:

	<?xml version="1.0" encoding="utf-8"?>
	<com.your.package.MyScreenletView 
		xmlns:android="http://schemas.android.com/apk/res/android">
	
		<!-- 
			put your regular components here: EditText, Button...
		-->
	
	</com.your.package.MyScreenletView>

For more details, refer the guide on [How to Create Your Own Screenlet](http://www.liferay.com/).

## The View Layer

The view layer lets developers use more than one look and feel for any 
screenlet. Screenlets have the `liferay:layoutId` attribute, which is used to
determine the view responsible for rendering the UI.

![The view layer of Liferay Screens for Android.](http://liferay.github.io/liferay-screens/android/library/svg/architecture-views.svg)

There are several different view types:

- *Default views*: A mandatory view set that is supplied by Liferay. It's used 
  by default when the screenlet's `layoutId` is invalid or isn't specified. The
  Default view set uses a neutral, flat white and blue design with standard UI
  components. For example, in `LoginScreenlet` it uses standard but styled text
  boxes for the user name and password. At any point you can change the styles
  associated with this view set to customize the colors, positions, and sizes. 
  To do this, see the `styles.xml` file.

- *Full views*: Can be used to show a different set of components and 
  attributes. Using the `LoginScreenlet` as an example, the Full view can be 
  used to present different components for the user name and password fields. 
  For example, you it's possible to show only the password field and infer the 
  user name from elsewhere. The Default views are a kind of Full view.

- *Child views*: Inherits another view's behavior, without including any code.
  Child views only contain a new layout file. This layout file can contain
  different colors, component positions, or any other visual changes. Because 
  the changes in Child views are only visual, the UI components and their 
  identifiers must be identical to those of the parent view. In the diagram, a 
  Child view inherits from the Default view. As an example of implementing a 
  Child view, you can create a view inherited from Default for `LoginScreenlet` 
  and then configure the new layout file to change the position and size of the 
  standard text boxes. Child views present a good alternative to implementing a 
  completely different UI for one specific scenario.

- *Extended*: Inherits another view's behavior and code. This lets you implement
  new behavior in the view, such as displaying new components in the UI or
  otherwise introducing new functionality. In the diagram, the Extended view
  extends the Full one, but provides a specific view class for the screenlet
  (extending from the corresponding parent's view class). For more information,
  see the guide [How to Create Your Own Theme](http://www.liferay.com/).

## Android Lifecycle and Screenlets

Liferay Screens automatically saves and restores the screenlets' state by using
the Android SDK methods `onSaveInstanceState` and `onRestoreInstanceState`. Each
screenlet uses a unique generated identifier (`screenletId`) that is also
restored. This id is used, among other things, to identify the source of the
executed actions and assign the results to them.

The screenlets' state is restored after the `onCreated` and `onStart` methods,
as specified by the [standard Android
lifecycle](http://developer.android.com/training/basics/activity-lifecycle/recreating.html).
Before the state is restored, we recommend avoiding any operation with an
interactor. This is because it can't be assured that the executed action would
find its intended destination.

To avoid this behavior, screenlet method calls should be executed inside the
activity's `onResume` method. This ensures that the state is restored before
your code and any executing task is able to deliver the result to their source
interactor and screenlet.
