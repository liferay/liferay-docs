# Architecture of Liferay Screens for iOS [](id=architecture-of-liferay-screens-for-ios)

Liferay Screens separates its presentation and business-logic code using ideas 
from [Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter), 
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel), and 
[VIPER](http://www.objc.io/issue-13/viper.html). However, Screens isn't a 
canonical implementation of these architectures because they're designed for 
apps. Screens isn't an app; it's a suite of visual components intended for use 
*in* apps. 

This tutorial explains the architecture of Liferay Screens for iOS. It begins 
with an overview of the high level components that make up the system. This 
includes the core, screenlets, and themes. These components are then described 
in detail in the sections that follow. After you get done examining Screens's 
building blocks, you'll be ready to create some amazing screenlets and themes!

## High Level Architecture of Liferay Screens for iOS [](id=high-level-architecture-of-liferay-screens-for-ios)

Liferay Screens for iOS is composed of the core, screenlet, and theme layers. 
Although technically part of the core layer, server operations are crucial 
because they allow interaction with local and remote data sources. They also 
leverage Liferay operations to call the Liferay Mobile SDK. Screens's high-level 
architecture is shown here, followed by a description of each item on the 
diagram.

![Figure 1: The high level components of Liferay Screens for iOS.](../../images/screens-ios-architecture-01.png)

- *Core*: The base classes needed to develop other components. The core is a 
  micro-framework that lets developers to write their own screenlets, themes, 
  and server operation classes. 

- *Screenlets*: The screenlet library. Each screenlet is a Swift class that can 
  be inserted in a `UIView`. Screenlets render the selected theme in the runtime 
  and in Interface Builder. They also react to user interface events, starting 
  server operations if necessary. Screenlets also define a set of 
  `@IBInspectable` properties that can be configured from Interface Builder.

- *Server Operations*: A collection of `NSOperation` classes that interact with 
  local and remote data sources. Liferay's own set of operations, Liferay 
  Operations, use the [Liferay Mobile SDK](/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-ios-app).
  All server operations can be run concurrently since they use the 
  [`NSOperation` framework](https://developer.apple.com/library/mac/documentation/General/Conceptual/ConcurrencyProgrammingGuide/OperationObjects/OperationObjects.html#//apple_ref/doc/uid/TP40008091-CH101-SW1). 
  It's very easy to define priorities and dependencies between operations, so 
  you can build your own graph of operations that can be resolved by the 
  framework.

- *Themes*: A set of `xib` files and accompanying `UIView` classes that present 
  screenlets to the user.

The next section describes the core layer in detail.

## The Core Layer

The core layer is the micro-framework that lets developers write their own 
components in a structured and isolated way. This is possible because each 
component has a clear communication API and purpose. Therefore, even components 
written by different developers share a common structure. 

![Figure 2: The core layer of Liferay Screens for iOS.](../../images/screens-ios-architecture-02.png)

- *ServerOperation*: The base class for all operations started by screenlets. 
  These operations retrieve data asynchronously from local or remote data 
  sources. The screenlet classes instantiate and begin operations. They also 
  receive the responses, which change the state of the view classes.

- *BaseScreenlet*: The base class for all screenlet classes. Screenlet classes 
  receive user events from the UI, begin server operations, and update any view 
  data from the result. `BaseScreenlet` contains a set of [template methods](http://www.oodesign.com/template-method-pattern.html) 
  that children classes should overwrite.

- *BaseScreenletView*: The base class of all screenlet view classes. Its 
  children classes belong to the theme layer. View classes use standard `xib` 
  files to render a UI and then update it when the data changes. 
  `BaseScreenletView` contains template methods that children classes should 
  overwrite. 

- *SessionContext*: A singleton class that holds the user's session when they 
  are logged in to the app. Apps can use an implicit login, which is invisible 
  to the user, or an explicit login that lets the user manually create the 
  session. Manual logins can be implemented with `LoginScreenlet`.

- *LiferayServerContext*: A singleton class that holds server configuration 
  parameters. It's loaded from the `liferay-server-context.plist` file. Most 
  screenlets use these parameters as default values.

Now that you know the core layer's composition, you're ready to learn the 
screenlet layer's details. 

## The Screenlet Layer

The screenlet layer contains the screenlets available in Liferay Screens for
iOS. The following diagram shows the screenlet layer in relation to the core,
theme, and server operation layers. The screenlet classes in the diagram are 
explained in this section. 

![Figure 3: The screenlet layer in relation to the other components of Liferay Screens for iOS.](../../images/screens-ios-architecture-03.png)

- *MyScreenletData*: An interface that defines the screenlet's attributes. It 
  typically includes input values for `LoginScreenlet`, such as the user name 
  and password. An operation can be configured by reading and validating these 
  values. Also, the screenlet can change these values based on any default 
  values and the operation's result. 

- *MyScreenlet*: The class that represents the screenlet component. It includes: 

    - A set of inspectable parameters that allow configuration of its behavior. 
      Optionally, the initial state can be set in the screenlet's data. 
    - A reference to its view, depending on the selected theme. To meet the 
      screenlet's requirements, all themes must implement the `Data` interface. 
    - Zero or more methods that create and start server operations. These can be 
      public methods like `loadMyData()`, which a developer should call, or UI 
      events received in `onUserAction()`. 
    - A [delegate object](https://developer.apple.com/library/ios/documentation/general/conceptual/DevPedia-CocoaCore/Delegation.html)
      called when events occur. This is optional, but recommended. 

- *MyScreenletOperation*: This is related to the screenlet, but has one or more 
  server operations and is located in the server operations layer. If the server 
  operation is a back-end call, then there's typically only a single request. 
  Each server operation is responsible for retrieving a set of related values. 
  The results are stored in a `result` object that can be read by the screenlet 
  when it's notified. 

- *MyScreenletView_themeX*: A class that belongs to one specific theme. In the
  diagram, this theme is `ThemeX`. The class renders the screenlet's UI by using 
  its related `xib` file. The view object and `xib` file communicate using
  standard mechanisms like `@IBOutlet` and `@IBAction`. When a user action 
  occurs in the `xib` file, it's received by `BaseScreenletView` and then passed 
  to the screenlet class by the `onUserAction()` method. To identify different 
  events, the component's `restorationIdentifier` property is passed to the `onUserAction()` method. 

- *MyScreenletView_themeX.xib*: The `xib` file containing the components used to 
  render the view. This class's name is very important. By convention, a 
  screenlet `FooScreenlet` and a theme `BarTheme` must have an `xib` file named 
  `FooScreenlet_barTheme.xib`. 

For more details, refer to the tutorial [Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets). 
Next, the theme layer of Screens for iOS is described. 

## The Theme Layer

The theme layer lets developers use more than one theme for any screenlet. 
The screenlet property `themeName` determines the theme to load. A single theme 
can be used to implement a look and feel for a limited set of screenlets. 

![Figure 4: The theme layer of Liferay Screens for iOS.](../../images/screens-ios-architecture-04.png)

- *Default theme*: A mandatory theme supplied by Liferay. It's used by default 
  when the screenlet's `themeName` isn't specified or is invalid. The Default 
  theme uses a neutral, flat white and blue design with standard UI components. 
  For example, `LoginScreenlet` uses standard text boxes for the user name and 
  password fields, but uses the Default theme's flat white and blue design. 

- *Full*: A complete theme that can show different attributes and components. 
  Using `LoginScreenlet` as an example, a Full theme can be used to present 
  different components for the user name and password fields. For example, you 
  can show the password field and then infer the user name from elsewhere. The 
  Default theme is a Full theme. 

- *Child*: Inherits only the look and feel from another theme's UI components. 
  This doesn't include any code; it only contains a new `xib` file with visual 
  changes. Therefore, the parent and Child themes must share the same UI 
  components. In the diagram, the Child theme inherits from the Default theme. 
  As an example of implementing a Child theme for `LoginScreenlet`, you can 
  create a theme inherited from Default and then configure the new `xib` file to 
  change the position and size of the standard text boxes. 

- *Extended*: Provides an extended implementation of a theme inherited from a 
  parent theme. In the diagram, the Extended theme inherits the Full theme and 
  extends its screenlet view class. The [Flat7](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Flat7)
  theme is a good sample of an Extended theme. In this case, it's based on the
  Default theme.

For more details on theme creation, see the tutorial [Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes). 

Awesome! Now you know the nitty gritty details of Liferay Screens for iOS. This 
information is invaluable when using Screens to develop your apps. 

## Related Topics [](id=related-topics)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)
