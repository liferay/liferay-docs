# Screenlet Layer of Liferay Screens for iOS [](id=screenlet-layer-of-liferay-screens-for-ios)

The Screenlet layer contains the available Screenlets in Liferay Screens for
iOS. The following diagram shows the Screenlet layer in relation to the Core,
Interactor, Theme, and Connector layers. The Screenlet classes in the diagram 
are explained in this section. 

![Figure 1: This diagram illustrates the iOS Screenlet Layer's relationship to other Screens components.](../../../../images/screens-ios-architecture-03.png)

Screenlets are comprised of several Swift classes and an XIB file:

-   **MyScreenletViewModel:** A protocol that defines the attributes shown in 
    the UI. It typically accounts for all the input and output values presented 
    to the user. For example, 
    [`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Auth/LoginScreenlet/LoginViewModel.swift)
    includes attributes like the user name and password. A Connector can be
    configured by reading and validating these values. Also, the Screenlet can 
    change these values based on any default values and operation results. 

-   **MyScreenlet:**  a class that represents the Screenlet component the app
    developer interacts with. It includes the following things:

    -   Inspectable parameters for configuring the Screenlet's behavior. The
        initial state can be set in the Screenlet's data. 
    -   A reference to the Screenlet's View, based on the selected Theme. To 
        meet the Screenlet's requirements, all Themes must implement the 
        `ViewModel` protocol. 
    -   Any number of methods for invoking Connectors. You can optionally make 
        them public for app developers to call.  
    -   An optional (but recommended)
        [delegate object](https://developer.apple.com/library/ios/documentation/general/conceptual/DevPedia-CocoaCore/Delegation.html)
        the Screenlet can call on for particular events. 

-   **MyUserCaseInteractor:**  Each Interactor runs the operations that 
    implement the use case. These can be local operations, remote operations, or 
    a combination thereof. Operations can be executed sequentially or in 
    parallel. The final results are stored in a `result` object that can be read 
    by the Screenlet when notified. The number of Interactor classes a Screenlet 
    requires depends on the number of use cases it supports. 

-   **MyOperationConnector:** This is related to the Interactor, but has one or 
    more Connectors. If the Server Connector is a back-end call, then there's 
    typically only a single request. Each Server Connector is responsible for 
    retrieving a set of related values. The results are stored in a `result` 
    object that can be read by the Interactor when notified. The number of 
    Server Connector classes an Interactor requires depends on the number of 
    endpoints you need to query, or even the number of different servers you 
    need to support. Connectors are always created using a 
    [factory class](https://en.wikipedia.org/wiki/Abstract_factory_pattern). 
    You can therefore take advantage of 
    [Inversion of Control](https://en.wikipedia.org/wiki/Inversion_of_control). 
    This way, you can implement your own factory class to use to create your own 
    Connector objects. To tell Screens to use your factory class, specify it in 
    the `liferay-server-context.plist` file as described 
    [in the tutorial on preparing your iOS project for Screens](/develop/tutorials/-/knowledge_base/7-1/preparing-ios-projects-for-liferay-screens#configuring-communication-with-liferay). 

-   **MyScreenletView_themeX:** A class that belongs to one specific Theme. In 
    the diagram, this Theme is *ThemeX*. The class renders the Screenlet's UI by 
    using its related XIB file. The View object and XIB file communicate using 
    standard mechanisms like `@IBOutlet` and `@IBAction`. When a user action 
    occurs in the XIB file, it's received by `BaseScreenletView` and then passed 
    to the Screenlet class via the `performAction` method. To identify different 
    events, the component's `restorationIdentifier` property is passed to the 
    `performAction` method. 

-   **MyScreenletView_themeX.xib:** An XIB file that specifies how to render the
    Screenlet's View. Its name is very important. By convention, a Screenlet 
    with a view class named *FooScreenletView* and a Theme named *BarTheme* must 
    have an XIB file named `FooScreenletView_barTheme.xib`. 

For more details, refer to the tutorials on 
[creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-1/creating-ios-screenlets). 
