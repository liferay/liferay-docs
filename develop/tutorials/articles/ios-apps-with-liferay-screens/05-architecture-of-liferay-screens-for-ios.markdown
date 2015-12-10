# Architecture of Liferay Screens for iOS [](id=architecture-of-liferay-screens-for-ios)

Liferay Screens separates its presentation and business-logic code using ideas
from
[Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter),
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel),
and
[VIPER](http://www.objc.io/issue-13/viper.html). However, Screens isn't a
canonical implementation of these architectures because they're designed for
apps. Screens isn't an app; it's a suite of visual components intended for use
*in* apps. 

This tutorial explains the architecture of Liferay Screens for iOS. It begins 
with an overview of the high level components that make up the system. This 
includes the Core, Screenlets, and Themes. These components are then described 
in detail in the sections that follow. After you get done examining Screens's 
building blocks, you'll be ready to create some amazing Screenlets and Themes!

## High Level Architecture of Liferay Screens for iOS [](id=high-level-architecture-of-liferay-screens-for-ios)

Liferay Screens for iOS is composed of a Core, a Screenlet layer, a View layer,
and Server operations. Server operations are technically part of the Core, but
are worth describing separately. They facilitate interaction with local and
remote data sources and communication between the Screenlet layer and the
[Liferay Mobile SDK](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/mobile). 

![Figure 1: The high level components of Liferay Screens for iOS.](../../images/screens-ios-architecture-01.png)

Each component is described below.

**Core:** includes all the base classes for developing other Screens components.
It's a micro-framework that lets developers write their own Screenlets, views,
and Server operation classes.

**Screenlets:** Swift classes for inserting into any `UIView`. They render a
selected Theme in the runtime and in Interface Builder. They also react to user
interface events to start Server operations. Screenlets also define a set of
`@IBInspectable` properties that can be configured from Interface Builder. The
Screenlets bundled with Liferay Screens are known as the [Screenlet library](https://dev.liferay.com/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios).

**Interactors**: implement specific use cases that involve communicating with
servers. They can use local and remote data sources by using *Server Operations*
or custom classes. If a user action or use case needs to execute more than one
query on a local or remote store, the sequence is done in the corresponding
Interactor. If a screenlet supports more than one user action or use case, an
Interactor needs to be created for each. 

**Server Operations:** a collection of `NSOperation` classes that can interact
with local and remote data sources and Liferay instances. Liferay's own set of
operations, Liferay Operations, use the
[Liferay Mobile SDK](/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-ios-app).
All Server operations can be run concurrently since they use the 
[`NSOperation` framework](https://developer.apple.com/library/mac/documentation/General/Conceptual/ConcurrencyProgrammingGuide/OperationObjects/OperationObjects.html#//apple_ref/doc/uid/TP40008091-CH101-SW1). 
It's very easy to define priorities and dependencies between operations, so you
can build your own graph of operations that can be resolved by the framework.

**Themes:** a set of XIB files and accompanying `UIView` classes that present 
Screenlets to the user.

The next section describes the Core in detail.

## Core [](id=core)

The Core is the micro-framework that lets developers write Screenlets in a
structured and isolated way. All Screenlets share a common structure based on
the Core classes, but each Screenlet can have a unique purpose and communication
API. 

![Figure 2: Here's the core layer of Liferay Screens for iOS.](../../images/screens-ios-architecture-02.png)

[**ServerOperation**](https://github.com/liferay/liferay-screens/blob/1.1.0/ios/Framework/Core/Base/ServerOperation.swift):
the base class for all Liferay Portal interactions and use cases that a
Screenlet supports. Operations retrieve data asynchronously from local or remote
data sources. The Screenlet classes instantiate operation classes, invoke their
operations, and receive operation responses that change the state of the
Screenlet's View classes. 

[**BaseScreenlet**](https://github.com/liferay/liferay-screens/blob/1.1.0/ios/Framework/Core/Base/BaseScreenlet.swift):
the base class for all Screenlet classes. Screenlet classes receive user events
from the UI, begin Server operations, and update any view data from the result.
`BaseScreenlet` contains a set of
[template methods](http://www.oodesign.com/template-method-pattern.html) 
that child classes should overwrite.

[**BaseScreenletView**](https://github.com/liferay/liferay-screens/blob/1.1.0/ios/Framework/Core/Base/BaseScreenletView.swift):
the base class for all Screenlet View classes. Its child classes belong to the
Theme layer. View classes use standard XIB files to render a UI and then update
it when the data changes. `BaseScreenletView` contains template methods that
child classes should overwrite.

[**SessionContext**](https://github.com/liferay/liferay-screens/blob/1.1.0/ios/Framework/Core/Context/SessionContext.swift):
a singleton object that holds the logged in user's session. Apps can use an
implicit login, invisible to the user, or a login that relies on explicit user
input to create the session. User logins can be implemented with the
[Login Screenlet](https://dev.liferay.com/develop/reference/-/knowledge_base/6-2/loginscreenlet-for-ios). It is explained in detail [here](/develop/tutorials/-/knowledge_base/6-2/accessing-liferay-session-and-logged-user).

[**LiferayServerContext**](https://github.com/liferay/liferay-screens/blob/1.1.0/ios/Framework/Core/Context/LiferayServerContext.swift):
a singleton object that holds server configuration parameters. It's loaded from
the `liferay-server-context.plist` file. Most Screenlets use these parameters as
default values.

Now that you know what the Core contains, you're ready to learn the Screenlet
layer's details. 

## Screenlet Layer [](id=screenlet-layer)

The Screenlet layer contains the available Screenlets in Liferay Screens for
iOS. The following diagram shows the Screenlet layer in relation to the Core,
Theme, and Server Operation layers. The Screenlet classes in the diagram are 
explained in this section. 

![Figure 3: This diagram illustrates the iOS Screenlet Layer's relationship to other Screens components.](../../images/screens-ios-architecture-03.png)

Screenlets are comprised of several Swift classes and an XIB file:

**MyScreenletData:** an interface that defines the attributes shown in the UI.
It typically accounts for all the input and output values presented to the user.
For instance,
[`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/1.1.0/ios/Framework/Core/Auth/LoginScreenlet/LoginViewModel.swift)
includes attributes like the user name and password. An operation can be
configured by reading and validating these values. Also, the Screenlet can
change these values based on any default values and operation results. 

**MyScreenlet:**  a class that represents the Screenlet component the app
developer interacts with. It includes the following things:

- Inspectable parameters for configuring the Screenlet's behavior. The
  initial state can be set in the Screenlet's data. 
- A reference to the Screenlet's View, based on the selected Theme. To meet
  the Screenlet's requirements, all Themes must implement the `Data`
  interface. 
- Any number of methods for invoking Server operations. You can optionally
  make them public for app developers to call. They can also handle UI
  events received in the `onUserAction` method. 
- An optional (but recommended)
  [delegate object](https://developer.apple.com/library/ios/documentation/general/conceptual/DevPedia-CocoaCore/Delegation.html)
  the Screenlet can call on for particular events. 

**MyScreenletOperation:** This is related to the Screenlet, but has one or
more Server Operations and is in the Server Operations Layer. If the Server
Operation is a back-end call, then there's typically only a single request. Each
Server Operation is responsible for retrieving a set of related values. The
results are stored in a `result` object that can be read by the Screenlet when
it's notified. The number of Server Operation classes a Screenlet requires
depends on the number of server use cases it supports. 

**MyScreenletView_themeX:** A class that belongs to one specific Theme. In the
diagram, this Theme is *ThemeX*. The class renders the Screenlet's UI by using
its related XIB file. The View object and XIB file communicate using standard
mechanisms like `@IBOutlet` and `@IBAction`. When a user action occurs in the
XIB file, it's received by `BaseScreenletView` and then passed to the Screenlet
class via the `onUserAction` method. To identify different events, the
component's `restorationIdentifier` property is passed to the `onUserAction`
method.

**MyScreenletView_themeX.xib:** an XIB file that specifies how to render the
Screenlet's View. Its name is very important. By convention, a Screenlet with a
view class named *FooScreenletView* and a Theme named *BarTheme* must have an
XIB file named `FooScreenletView_barTheme.xib`.

For more details, refer to the tutorial
[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets). 
Next, the Theme Layer of Screens for iOS is described. 

## Theme Layer [](id=theme-layer)

The Theme Layer lets developers set a Screenlet's look and feel. The Screenlet 
property `themeName` determines the Theme to load. This can be set by the 
Screenlet's *Theme Name* field in Interface Builder. A Theme consists of a view 
class for Screenlet behavior and an XIB file for the UI. By inheriting one or 
more of these components from another Theme, the different Theme *types* allow 
varying levels of control over a Screenlet's  UI design and behavior. 

![Figure 4: The Theme Layer of Liferay Screens for iOS.](../../images/screens-ios-architecture-04.png)

There are several different Theme types:

**Child:** Presents the same UI components and behavior as the parent Theme, but
can change the UI components' appearance and position. A Child Theme specifies
visual changes in its own XIB file; it inherits the parent's view class and
Screenlet class. It can't add or remove any UI components. The Child Theme's
parent must be a Full Theme. In the diagram, the Child Theme inherits from the
Default Theme. Creating a Child Theme is ideal when you only need to make visual
changes to an existing Theme. For example, you can create a Child Theme that
sets new positions and sizes for the standard text boxes in Login Screenlet's
Default Theme.

**Extended:** Inherits the parent Theme's behavior and appearance, but lets you
change and add to both. You can do so by creating a custom view class that
extends the parent Theme's view class and a new XIB file. An Extended Theme's
parent must be a Full Theme. In the diagram, the Extended Theme inherits the
Full Theme and extends its Screenlet's view class. Refer to the
[Flat7 Theme](https://github.com/liferay/liferay-screens/tree/1.1.0/ios/Framework/Themes/Flat7)
for an example of an Extended Theme.

**Full:** Provides a complete standalone theme. It has no parent Theme and
implements unique behavior and appearance for a Screenlet. Its view class must
extend Screens's `BaseScreenletView` class and conform to the Screenlet's view
model protocol. It must also specify a new UI in an XIB file. Full Themes can
also serve as parents for Child and Extended Themes. Refer to the
[Default Theme](https://github.com/liferay/liferay-screens/tree/1.1.0/ios/Framework/Themes/Default)
for an example of a Full Theme.

Themes in Liferay Screens are organized into sets that contain Themes for 
several Screenlets. Liferay's available Theme sets are listed here: 

- [*Default:*](https://github.com/liferay/liferay-screens/tree/1.1.0/ios/Framework/Themes/Default)
  A mandatory Theme set supplied by Liferay. It's used if the Screenlet's
  `themeName` isn't specified or is invalid. The Default Theme uses a neutral,
  flat white and blue design with standard UI components. For example, the
  [Login Screenlet](https://github.com/liferay/liferay-screens/tree/1.1.0/ios/Framework/Core/Auth/LoginScreenlet)
  uses standard text boxes for the user name and password fields, but uses the
  Default Theme's flat white and blue design. 

- [*Flat7:*](https://github.com/liferay/liferay-screens/tree/1.1.0/ios/Framework/Themes/Flat7)
  A collection of Themes that use a flat black and green design, and UI 
  components with rounded edges. They're Extended Themes. 

- [*Westeros:*](https://github.com/liferay/liferay-screens/tree/1.1.0/ios/Samples/WesterosBank/Theme)
  The Theme for the 
  [Bank of Westeros](https://github.com/liferay/liferay-screens/tree/1.1.0/ios/Samples/WesterosBank/App) 
  sample app.

For more details on Theme creation, see the tutorial
[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes). 

Awesome! Now you know the nitty gritty details of Liferay Screens for iOS. This 
information is invaluable when using Screens to develop your apps. 

## Related Topics [](id=related-topics)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)
