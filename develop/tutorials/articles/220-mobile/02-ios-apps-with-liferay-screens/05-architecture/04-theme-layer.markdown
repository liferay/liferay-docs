# Theme Layer of Liferay Screens for iOS

The Theme Layer lets developers set a Screenlet's look and feel. The Screenlet 
property `themeName` determines the Theme to load. This can be set by the 
Screenlet's *Theme Name* field in Interface Builder. A Theme consists of a view 
class for Screenlet behavior and an XIB file for the UI. By inheriting one or 
more of these components from another Theme, the different Theme *types* allow 
varying levels of control over a Screenlet's  UI design and behavior. 

![Figure 4: The Theme Layer of Liferay Screens for iOS.](../../../../images/screens-ios-architecture-04.png)

There are several Theme types:

-   **Default Theme:** The standard Theme provided by Liferay. It can be used as 
    a template to create other Themes, or as the parent Theme of other Themes. 
    Each Theme for each Screenlet requires a View class. A Default Theme's View 
    class is named `MyScreenletView_default`, where `MyScreenlet` is the 
    Screenlet's name. This class is similar to the standard `ViewController` in 
    iOS; it receives and handles UI events by using the standard `@IBAction` and 
    `@IBOutlet`. The View class usually uses an XIB file to build the UI 
    components. This XIB file is bound to the class. 

-   **Child Theme:** Presents the same UI components as the parent Theme, but
    can change the UI components' appearance and position. A Child Theme 
    specifies visual changes in its own XIB file; it can't add or remove any UI 
    components. In the diagram, the Child Theme inherits from the Default Theme. 
    Creating a Child Theme is ideal when you only need to make visual changes to 
    an existing Theme. For example, you can create a Child Theme that sets new 
    positions and sizes for the standard text boxes in Login Screenlet's Default 
    Theme, but without adding or overwriting existing code. 

-   **Full:** Provides a complete standalone theme. It has no parent Theme and
    implements unique behavior and appearance for a Screenlet. Its View class 
    must extend Screens's `BaseScreenletView` class and conform to the 
    Screenlet's view model protocol. It must also specify a new UI in an XIB 
    file. Refer to the
    [Default Theme](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Default) 
    for an example of a Full Theme. 

-   **Extended:** Inherits the parent Theme's behavior and appearance, but lets 
    you change and add code to both. You can do so by creating a new XIB file 
    and a custom View class that extends the parent Theme's View class. In the 
    diagram, the Extended Theme inherits the Full Theme and extends its 
    Screenlet's View class. Refer to the
    [Flat7 Theme](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Flat7)
    for an example of an Extended Theme.

Themes in Liferay Screens are organized into sets that contain Themes for 
several Screenlets. Liferay's available Theme sets are listed here: 

-   [**Default:**](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Default)
    A mandatory Theme set supplied by Liferay. It's used if the Screenlet's
    `themeName` isn't specified or is invalid. The Default Theme uses a neutral,
    flat white and blue design with standard UI components. For example, the
    [Login Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Auth/LoginScreenlet)
    uses standard text boxes for the user name and password fields, but uses the
    Default Theme's flat white and blue design. 

-   [**Flat7:**](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Flat7)
    A collection of Themes that use a flat black and green design, and UI 
    components with rounded edges. They're Extended Themes. 

-   [**Westeros:**](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/WesterosBank/Theme)
    The Theme for the 
    [Bank of Westeros](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/WesterosBank/App) 
    sample app. 

For more details on Theme creation, see the tutorial series 
[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes). 
