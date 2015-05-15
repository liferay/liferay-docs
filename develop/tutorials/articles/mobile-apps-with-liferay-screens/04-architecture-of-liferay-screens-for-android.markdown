# Architecture of Liferay Screens for Android [](id=architecture-of-liferay-screens-for-android)

Liferay Screens separates its presentation and business-logic code, following 
the ideas of [Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter), 
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel), and 
[VIPER](http://www.objc.io/issue-13/viper.html). However, because they're 
designed for apps, Screens can't be considered a canonical implementation of 
these architectures. Screens isn't an app; it's a suite of visual components 
intended for use *in* apps. 

This tutorial explains Liferay Screens' internal architecture on Android. It 
begins with an overview of the high level components that make up the system, 
such as the core, screenlets, and views. These components are then described in 
detail. Now go ahead and get started examining Screens' building blocks! 

## High Level Architecture of Liferay Screens for Android [](id=high-level-architecture-of-liferay-screens-for-android)

Liferay Screens for Android is composed of the core, screenlet, and view layers. 
Interactors, while technically part of the core layer, are also an important 
component type that facilitate communication with the screenlet layer and the 
Liferay Mobile SDK. A diagram of Screens' high-level architecture is shown here, 
followed by a brief description of each item in the diagram.

![Figure 1: The high-level components of Liferay Screens for Android.](../../images/screens-android-architecture-01.png)

- *Core*: Includes all the base classes needed to develop other components. The 
  core is defined as a micro-framework that lets developers write their own 
  screenlets, views, and interactor classes.

- *Screenlets*: The library that contains all the available screenlets. Each 
  screenlet is a Java View class that can be inserted in any activity or 
  fragment view hierarchy. Screenlets render the selected layout in the runtime 
  and in Android Studio's visual editor. They also react to UI events, sending 
  server requests if necessary. Screenlets also define a set of configurable 
  properties that can be set from the layout XML and your Java code. 

- *Interactors*: A collection of classes that implement one specific user 
  interaction or use case. These classes can interact with local and remote 
  data sources. Most interactors need to send or receive data to or from a 
  Liferay instance. In this case, interactors use the [Mobile SDK](/tutorials/-/knowledge_base/6-2/mobile).

- *Views*: A set of layout and accompanying custom view classes that present 
  screenlets to the user.

Next, the core layer is described in detail.

## The Core Layer of Screens for Android [](id=the-core-layer-of-screens-for-android)

The core layer is the micro-framework that lets developers write their own 
screenlets in a structured and isolated way. This is possible because each 
screenlet has a clear communication API and purpose. Therefore, even components
written by different developers share a common structure.

![Figure 2: The core layer of Liferay Screens for Android.](../../images/screens-android-architecture-02.png)

- *Interactor*: The base class for all interactions and use cases supported by 
  the screenlet. These actions can range in complexity from simple algorithms to 
  asynchronous data requests from a server or database. A screenlet can have 
  more than one interactor. Typically, one interactor is required for each 
  supported operation. An interactor can call services through the Liferay 
  Mobile SDK and receive the responses asynchronously through the EventBus. This 
  eventually changes the state of the view classes. 
  
- *BaseScreenlet*: The base class for all screenlet classes. The main task of a
  screenlet class is to receive user events from the screenlet's view, 
  instantiate and call interactors, and then update the view data with the 
  result. This class contains a set of [template methods](http://www.oodesign.com/template-method-pattern.html) 
  that are intended to be overridden by children classes. These methods are 
  listed here:

	- *createScreenletView*: Typically inflates the screenlet's view and
	  gets the attribute values from the XML definition. 
	- *createInteractor*: Instantiates an interactor of a specific type, 
	  depending on the action name supplied. The action name specifies the 
	  interactor type. If a screenlet only supports one interactor type, 
	  then the action name may be ignored. 
	- *onUserAction*: Runs the interactor intended for use with an action 
	  name.

- *ScreenletView*: The class that implements the screenlet's UI. The object is 
  created in the screenlet's `createScreenletView` method. The view class' main 
  task is to render a specific UI using standard layout files and then update 
  the UI when the data changes.
  
- *InteractorAsyncTaskCallback*: Receives asynchronous responses from the 
  Liferay Mobile SDK's `AsyncTask`.

- *[EventBus](https://github.com/greenrobot/EventBus)*: The bus implementation 
  used to notify the interactor when asynchronous operations complete. This 
  decouples `AsyncTask` from the activity lifecycle, avoiding most problems 
  typically associated with `AsyncTask`.
  
- *[MobileSDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)*: 
  Used to call Liferay's remote services in a typesafe and transparent way.

- *SessionContext*: A singleton object that holds the logged in user's session.
  Apps can use an implicit login, which is invisible to the user, or a login 
  that relies on explicit user input to create the session. User logins can be
  implemented with `LoginScreenlet`.
  
- *LiferayServerContext*: A singleton object that holds server configuration
  parameters. It's loaded from `server_context.xml`, or from any other XML file 
  that overrides the keys defined in that file. 

- *LiferayScreensContext*: A singleton object that holds a reference to the
  application context. It's used internally where the context is necessary.

Now that you know what the core layer is comprised of, you're ready to learn the 
screenlet layer's details.

## The Screenlet Layer of Screens for Android [](id=the-screenlet-layer-of-screens-for-android)

The screenlet layer contains the screenlets available in Liferay Screens for
Android. The following diagram shows the screenlet layer in relation to the 
core, view, and interactor components. The screenlet classes detailed in the
diagram are explained in this section.

![Figure 3: The screenlet layer in relation to the other components of Liferay Screens for Android.](../../images/screens-android-architecture-03.png)

- *MyScreenletViewModel*: An interface that defines the attributes shown in the
  UI. It typically includes all the input and output values presented to the 
  user. For instance, `LoginScreenletViewModel` includes attributes like the 
  user name and password. The screenlet reads those values to start the 
  interactor's operations and may eventually change them based on the result.

- *MyScreenlet*: The class that represents the screenlet component the developer 
  interacts with. It includes:

    - A set of attributes that allows the screenlet's behavior to be configured. 
      The attribute values are read in the screenlet's `createScreenletView`
      method. The default values can optionally be set there. 
    - A reference to its view, depending on the `liferay:layoutId` attribute's 
      value. To meet the screenlet's requirements, all views must implement the 
      corresponding `ViewModel` interface. 
    - Zero or more methods that start the interactor's operations. These can be 
      public methods intended to be called by a developer, such as 
      `loadMyData()`. These methods can also be UI events received in the `View` 
      class through a regular listener (such as Android's `OnClickListener`) and 
      forwarded to the screenlet by using the `performUserAction()` method. 
    - A listener object provided by the app, intended to be called when any 
      event occurs. This is optional, but recommended.

- *MyScreenletInteractor*: The screenlet's interactor. A screenlet can have zero
  or more interactors, depending on the number of use cases it supports. For
  example, the `LoginScreenlet` only supports one use case (to log in the user),
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
  diagram, this class belongs to the Default view set. The view object and the
  layout file communicate using standard mechanisms like `findViewById` and
  listeners. When a user action occurs in the UI, it's received by the specified
  listener (for example, `OnClickListener`) and then passed to the screenlet
  object using the `performUserAction()` method.

- *myscreenlet_default.xml*: The layout file containing the component 
  definitions used to render the screenlet's view. The XML typically appears as 
  follows:

        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.MyScreenletView 
            xmlns:android="http://schemas.android.com/apk/res/android">
        
                <!-- 
                    put your regular components here: EditText, Button...
                -->
        
        </com.your.package.MyScreenletView>

For more details on how a screenlet is constructed, refer the tutorial 
[Creating Screenlets in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-android). 
Next, the view layer's details are described. 

## The View Layer of Screens for Android [](id=the-view-layer-of-screens-for-android)

The view layer lets developers change any screenlet's look and feel. Screenlets
include the `liferay:layoutId` attribute, which is used to determine the view
responsible for rendering the UI.

<!-- 
![Figure 4: The view layer of Liferay Screens for Android.](../../images/screens-android-architecture-04.png)
-->

There are several different view types:

- *Default views*: A mandatory view set that is supplied by Liferay. It's used 
  by default when the screenlet's `layoutId` is invalid or isn't specified. The
  Default view set uses a neutral, flat white and blue design with standard UI
  components. For example, `LoginScreenlet` uses standard text boxes for the 
  user name and password. However, the text boxes are styled with the Default 
  view's flat white and blue design. You can change the styles associated with 
  this view set to customize the colors, positions, and sizes. To do this, see 
  the `styles.xml` file. 

- *Child views*: Inherits another view's behavior, without including any code.
  Child views only contain a new layout file. This layout file can contain
  different colors, component positions, or any other visual changes. Because 
  the changes in Child views are only visual, the UI components and their 
  identifiers must be identical to those of the parent view. Child views present 
  a good alternative to implementing a completely different UI for one specific 
  scenario. As an example of implementing a Child view, you can create a view 
  inherited from Default for `LoginScreenlet` and then configure the new layout 
  file to change the position and size of the standard text boxes. 

- *Extended*: Inherits another view's behavior and code. This lets you implement 
  new behavior in the view, such as displaying new components in the UI or 
  otherwise introducing new functionality. An extended view provides a specific 
  view class for the screenlet (extending from the corresponding parent's view 
  class). Using the `LoginScreenlet` as an example, the extended view can be 
  used to present different components for the user name and password fields. 

- *Full views*: Can be used to customize the screenlet listeners and call custom 
  intereractors. Using the `LoginScreenlet` as an example, the Full view can be 
  used to add a new interactor that calls to another Liferay Portal instance. 
  The Default views are a kind of Full view. 

For more information, see the tutorial 
[Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android). 

Great! Now you know how Liferay Screens for Android is composed. However, 
there's one other detail that you should know before moving on: how screenlets 
interact with the Android lifecycle. 

## Android Lifecycle and Screenlets [](id=android-lifecycle-and-screenlets)

Liferay Screens automatically saves and restores the screenlets' state by using
the Android SDK methods `onSaveInstanceState` and `onRestoreInstanceState`. Each
screenlet uses a unique generated identifier (`screenletId`) that is also
restored. This ID is used, among other things, to identify the source of the
executed actions and assign results to them. 

The screenlets' state is restored after the `onCreate` and `onStart` methods,
as specified by the [standard Android lifecycle](http://developer.android.com/training/basics/activity-lifecycle/recreating.html).
It's recommended that you avoid any operation with an interactor before the
state is restored. This is because it can't be assured that the executed action
can find its intended destination. 

To avoid this behavior, screenlet method calls should be executed inside the
activity's `onResume` method. This ensures that the state is restored before
your code and any executing task is able to deliver the result to their source
interactor and screenlet. 

Awesome! Now you know the nitty gritty details of Liferay Screens for Android. 
This information is invaluable when using Screens to develop your apps. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your Android Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-android-project)

[Using Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/using-views-in-liferay-screens-for-android)

[Creating Screenlets in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-android)

[Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android)
