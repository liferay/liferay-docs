# Architecture of Liferay Screens for iOS [](id=architecture-of-liferay-screens-for-ios)

Liferay Screens separates its presentation and business-logic code, following 
the ideas of [Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter), 
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel), and 
[VIPER](http://www.objc.io/issue-13/viper.html). However, because they're 
designed for apps, Screens can't be considered a canonical implementation of 
these architectures. Screens isn't an app; it's a suite of visual components 
intended for use *in* apps. 

This tutorial explains Liferay Screens' internal architecture on iOS. It begins 
with an overview of the high level components that make up the system, 
such as the core, screenlets, and themes. These components are then described in 
detail. Now go ahead and get started examining Screens' building blocks! 

## High Level Architecture of Liferay Screens for iOS [](id=high-level-architecture-of-liferay-screens-for-ios)

Liferay Screens for iOS is composed of the core, screenlet, and theme layers. 
Server operations, while technically part of the core layer, allow interaction 
with local and remote data sources. They also leverage Liferay operations to 
call the Liferay Mobile SDK. A diagram of Screens' high-level architecture is 
shown here, followed by a description of each item on the diagram.

![Figure 1: The high level components of Liferay Screens for iOS.](../../images/screens-ios-architecture-01.png)

- *Core*: This is the component that includes all the base classes needed to
  develop other components. It can be defined as a micro-framework that allows
  developers to write their own screenlets, themes, and server operation 
  classes. 

- *Screenlets*: This is the library that contains all the available screenlets.
  Each screenlet is a Swift class that can be inserted in a `UIView`. Screenlets
  render the selected theme in the runtime and in Interface Builder. They also 
  react to user interface events, starting server operations if necessary.
  Screenlets also define a set of `@IBInspectable` properties, which can be
  configured from Interface Builder.

- *Server Operations*: This is a collection of `NSOperation` classes that
  interact with both remote and local data sources. Liferay has its own set of
  operations, Liferay Operations, that use the [Liferay Mobile SDK](/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-ios-app).
  Given that all server operations use the [`NSOperation` framework](https://developer.apple.com/library/mac/documentation/General/Conceptual/ConcurrencyProgrammingGuide/OperationObjects/OperationObjects.html#//apple_ref/doc/uid/TP40008091-CH101-SW1),
  they can be run concurrently. It's very easy to define priorities and
  dependencies between operations, so you can build your own graph of operations
  that can be resolved by the framework.

- *Themes*: This is a set of `xib` files and accompanying `UIView` classes that
  present screenlets to the user.

The next section describes the core layer in detail.

## The Core Layer of Screens for iOS [](id=the-core-layer-of-screens-for-ios)

The core layer is the micro-framework that lets developers write their own 
components in a structured and isolated way. This is possible because each 
component has a clear communication API and purpose. Therefore, even components 
written by different developers share a common structure. 

![Figure 2: The core layer of Liferay Screens for iOS.](../../images/screens-ios-architecture-02.png)

- *ServerOperation*: This is the base class for all operations started by 
  screenlets. These operations retrieve data asynchronously. Despite the name
  `ServerOperation`, the data source can be local or remote. The screenlet 
  classes instantiate and begin operations. They also receive the responses, 
  which change the state of the view classes.

- *BaseScreenlet*: This is the base class for all screenlet classes. The main
  task of a screenlet class is to receive user events from the UI, begin server 
  operations, and update any view data from the result. This class contains a 
  set of [templated methods](http://www.oodesign.com/template-method-pattern.html) 
  that are intended to be overwritten by children classes.

- *BaseScreenletView*: This is the base class for the view classes of all 
  screenlets. These children classes belong to the theme layer. The main task of
  the view classes is to render a specific UI using standard `xib` files and 
  then update the UI when the data changes. This class contains templated 
  methods that are intended to be overwritten by children classes.

- *SessionContext*: This is a singleton class that holds the session of the user
  logged in to the app. Apps can use an implicit login, which is invisible to 
  the user, or an explicit login that allows the user to manually create the 
  session. Manual logins can be implemented with `LoginScreenlet`.

- *LiferayServerContext*: This is a singleton class that holds some server
  configuration parameters. It's loaded from the `liferay-server-context.plist`
  file. Most screenlets use these parameters as default values.

Now that you know the core layer's composition, you're ready to learn the 
screenlet layer's details. 

## The Screenlet Layer of Screens for iOS [](id=the-screenlet-layer-of-screens-for-ios)

The screenlet layer contains the screenlets available in Liferay Screens for
iOS. The following diagram shows the screenlet layer in relation to the core,
theme, and server operation layers. The screenlet classes detailed in the
diagram are explained in this section. 

![Figure 3: The screenlet layer in relation to the other components of Liferay Screens for iOS.](../../images/screens-ios-architecture-03.png)

- *MyScreenletData*: This is an interface that defines the screenlet's 
  attributes. It typically includes the input values for the `LoginScreenlet`, 
  such as the user name and password. The operation can be configured by reading 
  and validating these values. Also, the screenlet can change these values based 
  on the operation's result and any default values. 

- *MyScreenlet*: This is the class that represents the screenlet component. It
  includes: 

    - A set of inspectable parameters that allow configuration of its behavior. 
      Optionally, the initial state can be set in the screenlet's data. 
    - A reference to its view, depending on the selected theme. To meet the 
      screenlet's requirements, all themes must implement the `Data` interface. 
    - Zero or more methods that create and start server operations. These can be 
      public methods like `loadMyData()`, which is intended to be called by a 
      developer, or UI events received in `onUserAction()`. 
    - A [delegate object](https://developer.apple.com/library/ios/documentation/general/conceptual/DevPedia-CocoaCore/Delegation.html)
      called when events occur. This is optional, but recommended. 

- *MyScreenletOperation*: This is related to the screenlet, but is located in 
  the server operations layer and has one or more server operations. If the 
  server operation is a back-end call, then there's typically only a single 
  request. Each server operation is responsible for retrieving a set of related 
  values. The results are stored in a `result` object that can be read by the 
  screenlet when it's notified. 

- *MyScreenletView_themeX*: This class belongs to one specific theme. In the
  diagram, this theme is `ThemeX`. The class renders the screenlet's UI by using 
  its related `xib` file. The view object and `xib` file communicate using
  standard mechanisms like `@IBOutlet` and `@IBAction`. When a user action 
  occurs in the `xib` file, it's received by `BaseScreenletView` and then passed 
  to the screenlet class by using the `onUserAction()` method. To identify 
  different events, the component's `restorationIdentifier` property is passed 
  to the `onUserAction()` method. 

- *MyScreenletView_themeX.xib*: This is the `xib` file containing the components 
  used to render the view. Note that this class' name is very important. By 
  convention, the `xib` file for a screenlet `FooScreenlet` and a theme 
  `BarTheme` must be called `FooScreenlet_barTheme.xib`. 

For more details, refer to the tutorial 
[Creating Screenlets in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-ios). 
Next, the theme layer of Screens for iOS is described. 

## The Theme Layer of Screens for iOS [](id=the-theme-layer-of-screens-for-ios)

The theme layer lets developers use more than one theme for any screenlet. 
Screenlets have a property called `themeName` that is used to determine the 
theme to load. Depending on your requirements, a single theme can be used to
implement a look and feel for a limited set of screenlets. 

![Figure 4: The theme layer of Liferay Screens for iOS.](../../images/screens-ios-architecture-04.png)

- *Default theme*: This is a mandatory theme supplied by Liferay. It's used by 
  default when the screenlet's `themeName` isn't specified or is invalid. The 
  Default theme uses a neutral, flat white and blue design with standard UI 
  components. For example, `LoginScreenlet` uses standard text boxes for the 
  user name and password fields, but uses the Default theme's flat white and 
  blue design. 

- *Full*: This is a complete theme that can show a different set of attributes
  and components. Using the `LoginScreenlet` as an example, a Full theme can be
  used to present different components for the user name and password fields. 
  For example, you can show the password field and then infer the user name from
  somewhere else. The Default theme is itself a Full theme.

- *Child*: This theme inherits only the UI components' look and feel from 
  another theme. It doesn't include any code. It only contains a new `xib` file
  with visual changes. Therefore, the parent and Child themes must share the 
  same UI components. In the diagram, the Child theme inherits from the Default 
  theme. As an example of implementing a Child theme for `LoginScreenlet`, you 
  can create a theme inherited from Default and then configure the new `xib` 
  file to change the position and size of the standard text boxes. Given that 
  Default doesn't support iPad resolutions yet, this is the recommended way to 
  create a theme that supports these resolutions.

- *Extended*: This theme inherits from a parent theme, but provides an extended 
  implementation. In the diagram, the Extended theme extends the Full theme, but 
  provides a view class for the screenlet (extending from the corresponding 
  parent's screenlet class). The [Flat7](https://github.com/liferay/liferay-screens/tree/master/ios/Library/Themes/Flat7)
  theme is a good sample of an Extended theme. In this case, it's based on the
  Default theme.

For more details, see the tutorial [Creating Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios). 
Awesome! Now you know the nitty gritty details of Liferay Screens for iOS. This 
information is invaluable when using Screens to develop your apps. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your iOS Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-ios-project)

[Using Themes in iOS Screenlets](/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)

[Creating Screenlets in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-ios)

[Creating Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios)
