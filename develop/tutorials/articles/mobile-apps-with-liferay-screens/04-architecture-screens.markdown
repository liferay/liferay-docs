# Architecture of Liferay Screens [](id=architecture-of-liferay-screens)

Liferay Screens separates presentation and business-logic code, following the 
ideas of the [Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter), 
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel), and 
[VIPER](http://www.objc.io/issue-13/viper.html) architectures. However, Screens 
can't be considered a canonical implementation of these architectures. This is 
because they're designed for apps. Screens isn't an app, but is instead a suite 
of visual components intended for use in apps.

This tutorial explains Liferay Screens' internal architecture. Since Screens has 
separate implementations for Android and iOS, the architectures for these 
platforms are presented separately. The first section details Screens' 
architecture for Android. The second section details Screens' architecture for 
iOS. Each begins with an overview of the high level components that make up the 
system, such as the core, screenlets, views (Android), and themes (iOS). These 
components are then described in detail.

## Architecture of Liferay Screens for Android [](id=architecture-of-liferay-screens-for-android)

Liferay Screens for Android is composed of the core, screenlet, and view layers. 
Interactors, while technically part of the core layer, are also an important 
component type that facilitate communication with the screenlet layer and the 
Liferay Mobile SDK. A diagram of Screens' high-level architecture is shown here, 
followed by a brief description of each item on the diagram.

![Figure 1: The high level components of Liferay Screens for Android.](../../images/screens-android-architecture-01.png)

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
  Liferay Portal. In this case, interactors use the [Mobile SDK](/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-ios-app).

- *Views*: A set of layout and accompanying custom view classes that present 
  screenlets to the user.

The next section describes the core layer in detail.

### The Core Layer of Screens for Android [](id=the-core-layer-of-screens-for-android)

The core layer is the micro-framework that lets developers write their own 
screenlets in a structured and isolated way. This is possible because each 
screenlet has a clear communication API and purpose. Therefore, even components
developed by different people share a common structure.

![Figure 2: The core layer of Liferay Screens for Android.](../../images/screens-android-architecture-02.png)

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

Now that you know the details of the core layer, you're ready to learn the 
screenlet layer's details.

### The Screenlet Layer of Screens for Android [](id=the-screenlet-layer-of-screens-for-android)

The screenlet layer contains the screenlets available in Liferay Screens for
Android. The following diagram shows the screenlet layer in relation to the 
core, view, and interactor components. The screenlet classes detailed in the
diagram are explained in this section.

![Figure 3: The screenlet layer in relation to the other components of Liferay Screens for Android.](../../images/screens-android-architecture-03.png)

- *MyScreenletViewModel*: An interface that defines the attributes shown in the
  UI. It typically includes all the input and output values presented to the 
  user. For instance, `LoginScreenletViewModel` includes attributes like the 
  user name and password. The screenlet reads those values to start the 
  interactor's operations and may eventually change them based on the 
  interactor's result.

- *MyScreenlet*: The class that represents the screenlet component that the
  developer interacts with. It includes:

    - A set of attributes that allows the screenlet's behavior to be configured. 
      The attribute values are read with the screenlet's `createScreenletView` 
      method. Optionally, the default values can be also set there. 
    - A reference to its view, depending on the `liferay:layoutId` attribute's 
      value. To meet the screenlet's requirements, all views must implement the 
      corresponding `ViewModel` interface. 
    - Zero or more methods that start the interactor's operations. These can be 
      public methods intended to be called by a developer, such as 
      `loadMyData()`. These methods can also be UI events received in the `View` 
      class through a regular listener (such as Android's `OnClickListener`) and 
      forwarded to the screenlet by using the `performUserAction()` method. 
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

For more details, refer the tutorial [Creating Screenlets](/tutorials/-/knowledge_base/6-2/creating-screenlets). 
Next, the view layer's details are described. 

### The View Layer of Screens for Android [](id=the-view-layer-of-screens-for-android)

The view layer lets developers use more than one look and feel for any 
screenlet. Screenlets have the `liferay:layoutId` attribute, which is used to
determine the view responsible for rendering the UI.

![Figure 4: The view layer of Liferay Screens for Android.](../../images/screens-android-architecture-04.png)

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
  (extending from the corresponding parent's view class).

For more information, see the tutorial [Creating Views and Themes](/tutorials/-/knowledge_base/6-2/creating-views-and-themes). 

Great! Now you know how Liferay Screens for Android is composed. However, 
there's one other detail that you should know before moving on: how screenlets 
interact with the Android lifecycle. 

### Android Lifecycle and Screenlets [](id=android-lifecycle-and-screenlets)

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

## Architecture of Liferay Screens for iOS [](id=architecture-of-liferay-screens-for-ios)

Liferay Screens for iOS is composed of the core, screenlet, and theme layers. 
Server operations, while technically part of the core layer, allow interaction 
with local and remote data sources. They also leverage Liferay operations to 
call the Liferay Mobile SDK. A diagram of Screens' high-level architecture is 
shown here, followed by a description of each item on the diagram.

![Figure 5: The high level components of Liferay Screens for iOS.](../../images/screens-ios-architecture-01.png)

- *Core*: This is the component that includes all the base classes needed to
  develop other components. It can be defined as a micro-framework that allows
  developers to write their own screenlets, themes, and server operation 
  classes.

- *Screenlets*: This is the library that contains all the available screenlets.
  Each screenlet is a Swift class that can be inserted in a `UIView`. Screenlets
  render the selected theme both in the runtime and Interface Builder. They also
  react to user interface events, starting server operations if necessary.
  Screenlets also define a set of `@IBInspectable` properties, which can be
  configured from Interface Builder.

- *Server Operations*: This is a collection of `NSOperation` classes that
  interact with both remote and local data sources. Liferay has its own set of
  operations, Liferay Operations, that use the [Mobile SDK](/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-ios-app).
  Given that all server operations use the [`NSOperation` framework](https://developer.apple.com/library/mac/documentation/General/Conceptual/ConcurrencyProgrammingGuide/OperationObjects/OperationObjects.html#//apple_ref/doc/uid/TP40008091-CH101-SW1),
  they can be run concurrently. It's very easy to define priorities and
  dependencies between operations, so you can build your own graph of operations
  that can be resolved by the framework.

- *Themes*: This is a set of `xib` files and accompanying `UIView` classes that
  present screenlets to the user.

The next section describes the core layer in detail.

### The Core Layer of Screens for iOS [](id=the-core-layer-of-screens-for-ios)

The core layer is the micro-framework that lets developers write their own 
components in a structured and isolated way. This is due to the fact that each 
component has a clear purpose and communication API. Therefore, even components 
developed by different people share a common structure. 

![Figure 6: The core layer of Liferay Screens for iOS.](../../images/screens-ios-architecture-02.png)

- *ServerOperation*: This is the base class for all operations started by
  screenlets. These operations retrieve data asynchronously. Despite the name
  `ServerOperation`, the data source can be local or remote. The screenlet 
  classes instantiate and begin operations. They also receive the responses, 
  which change the state of the view classes.

- *BaseScreenlet*: This is the base class for all screenlet classes. The main
  task of a screenlet class is to receive user action events from the UI, begin
  server operations, and update any view data from the result. This class 
  contains a set of [templated methods](http://www.oodesign.com/template-method-pattern.html) 
  that are intended to be overwritten by children classes.

- *BaseScreenletView*: This is the base class for the view classes of all
  screenlets. These children classes belong to the theme layer. The main task of
  the view classes is to render a specific UI using standard `xib` files and 
  then update the UI when the data changes. This class contains a set of 
  templated methods that are intended to be overwritten by children classes.

- *SessionContext*: This is a singleton class that holds the session of the user
  that is logged in to the app. Apps can use an implicit login, which is 
  invisible to the user, or a login that allows the user to manually create the 
  session. Manual logins can be implemented with `LoginScreenlet`.

- *LiferayServerContext*: This is a singleton class that holds some server
  configuration parameters. It's loaded from the `liferay-server-context.plist`
  file. Most screenlets use these parameters as default values.

Now that you know the details of the core layer, you're ready to learn the 
screenlet layer's details.

### The Screenlet Layer of Screens for iOS [](id=the-screenlet-layer-of-screens-for-ios)

The screenlet layer contains the screenlets available in Liferay Screens for
iOS. The following diagram shows the screenlet layer in relation to the core,
theme, and server operation components. The screenlet classes detailed in the
diagram are explained in this section.

![Figure 7: The screenlet layer in relation to the other components of Liferay Screens for iOS.](../../images/screens-ios-architecture-03.png)

- *MyScreenletData*: This is an interface that defines the attributes of the
  screenlet. It typically includes the input values for the `LoginScreenlet`, 
  such as the user name and password. The operation can read and validate these 
  values to configure the operation. The screenlet can change these values based 
  on the operation's result and any default values. 

- *MyScreenlet*: This is the class that represents the screenlet component. It
  includes: 

    - A set of inspectable parameters that allow configuration of its behavior. 
      Optionally, the initial state can be set in the screenlet's data. 
    - A reference to its view, depending on the selected theme. To meet the 
      screenlet's requirements, all themes must implement the `Data` interface. 
    - Zero or more methods that create and start server operations. These can be 
      public methods like `loadMyData()`, which is intended to be called by a 
      developer, or UI events received on `onUserAction()`. 
    - A [delegate object](https://developer.apple.com/library/ios/documentation/general/conceptual/DevPedia-CocoaCore/Delegation.html)
      to be called when events occur. this is optional, but recommended. 

- *MyScreenletOperation*: This is related to the screenlet, but is located in
  the server operations layer and has one or more server operations. If the 
  server operation is a back-end call, then there's typically just a single 
  request. Each server operation is responsible for retrieving a set of related 
  values. The results are stored in a `result` object that can be read by the 
  screenlet when it's notified. 

- *MyScreenletView_themeX*: This class belongs to one specific theme. In the
  diagram this theme is `ThemeX`. The class renders the UI of the screenlet 
  using its related `xib` file. The view object and `xib` file communicate using
  standard mechanisms like `@IBOutlet` and `@IBAction`. When a user action 
  occurs in the `xib` file, it's received by `BaseScreenletView` and then passed 
  to the screenlet class using the `onUserAction()` method. To identify 
  different events, the `restorationIdentifier` property of the component is 
  passed to the `onUserAction()` method. 

- *MyScreenletView_themeX.xib*: This is the `xib` file with the components used
  to render the view. Note that the name of this class is very important. By
  convention, the `xib` file for a screenlet `FooScreenlet` and a theme 
  `BarTheme` must be called `FooScreenlet_barTheme.xib`. 

For more details, refer to the tutorial [Creating Screenlets](/tutorials/-/knowledge_base/6-2/creating-screenlets). 
Next, the theme layer of Screens for iOS is described. 

### The Theme Layer of Screens for iOS [](id=the-theme-layer-of-screens-for-ios)

The theme layer lets developers use more than one theme for any screenlet.
Screenlets have a property called `themeName`, which is used to determine the
theme to load. Depending on your requirements, a single theme can be used to
implement a look and feel for a limited set of screenlets. 

![Figure 8: The theme layer of Liferay Screens for iOS](../../images/screens-ios-architecture-04.png)

- *Default theme*: This is a mandatory theme that is supplied by Liferay. It's
  used by default when the screenlet's `themeName` isn't specified or is 
  invalid. The Default theme uses a neutral, flat white and blue design with 
  standard UI components. For example, it uses standard text boxes for the user 
  name and password in the `LoginScreenlet`. Right now, the Default theme can 
  only be used in the resolution of the iPhone 5, in portrait orientation. In 
  the near future, this theme will use Autolayout features to seamlessly support 
  different resolutions and orientations.

- *Full*: This is a complete theme that can show a different set of attributes
  and components. Using the `LoginScreenlet` as an example, a Full theme can be
  used to present different components for the user name and password fields. 
  For example, you can show the password field and then infer the user name from
  somewhere else. The Default theme is a Full theme itself.

- *Child*: This theme inherits only the look and feel of the UI components from
  another theme. It doesn't include any code. It just contains a new `xib` file
  with a different layout, colors, positions, and any other visual changes.
  Therefore, the UI components of the two themes must be the same. In the 
  diagram, Child inherits from the Default theme. As an example of implementing 
  the Child theme, you can change the position and size of the standard text 
  boxes in `LoginScreenlet` by creating a theme inherited from Default, and then 
  configure the new `xib` file. Given that Default doesn't support iPad 
  resolutions yet, this is the recommended way to create a theme that supports 
  these resolutions.

- *Extended*: This theme inherits from other parent theme, but provides an
  extended implementation. In the diagram, the Extended theme extends the Full
  theme, but provides a view class for the screenlet (extending from the
  corresponding parent screenlet class). The [Flat7](https://github.com/liferay/liferay-screens/tree/master/ios/Library/Themes/Flat7)
  theme is a good sample of an Extended theme. In this case, it's based on the
  Default theme.

For more details, see the tutorial [Creating Views and Themes](/tutorials/-/knowledge_base/6-2/creating-views-and-themes).

## Related Topics [](id=related-topics)

[Using Screenlets](/tutorials/-/knowledge_base/6-2/using-screenlets)

[Using Views and Themes](/tutorials/-/knowledge_base/6-2/using-views-and-themes)

[Creating Screenlets](/tutorials/-/knowledge_base/6-2/creating-screenlets)

[Creating Views and Themes](/tutorials/-/knowledge_base/6-2/creating-views-and-themes)
