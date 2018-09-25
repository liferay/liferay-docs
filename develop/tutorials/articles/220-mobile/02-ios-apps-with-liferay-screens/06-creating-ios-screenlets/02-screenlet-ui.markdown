# Creating the iOS Screenlet's UI [](id=creating-the-ios-screenlets-ui)

In Liferay Screens for iOS, a Screenlet's UI is called a Theme. Every Screenlet 
must have at least one Theme. A Theme has the following components: 

1. An XIB file: defines the UI components that the Theme presents to the end 
   user. 

2. A View class: renders the UI, handles user interactions, and communicates 
   with the Screenlet class. 

First, create a new XIB file and use Interface Builder to construct your 
Screenlet's UI. In many cases, the Screenlet's actions must be triggered from
the Theme. To achieve this, make sure to use a `restorationIdentifier` property
to assign a unique ID to each UI component that triggers an action. The user
triggers the action by interacting with the UI component. If the action only
changes the UI's state (that is, changes the UI component's properties), then
you can associate that component's event to an `IBAction` method as usual.
Actions using `restorationIdentifier` are intended for use by actions that need
an Interactor, such as actions that make server requests or retrieve data from a
database. 

For example, Add Bookmark Screenlet's UI needs text boxes for entering a 
bookmark's URL and title. This UI also needs a button to support the Screenlet's 
action: sending the bookmark to a Liferay instance. The 
[XIB file `AddBookmarkView_default.xib`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/Themes/AddBookmarkView_default.xib) 
defines this UI. Because the button triggers the Screenlet's action, it contains 
`restorationIdentifier="add-bookmark"`. 

![Figure 1: Here's the sample Add Bookmark Screenlet's XIB file rendered in Interface Builder.](../../../../images/screens-ios-xcode-add-bookmark.png)

+$$$

**Note:** The Screenlet in this tutorial doesn't support multiple Themes. If you 
want your Screenlet to support multiple Themes, your View class must also 
conform a *View Model* protocol that you create. For instructions on this, see 
the tutorial 
[Supporting Multiple Themes in Your Screenlet](/develop/tutorials/-/knowledge_base/7-1/supporting-multiple-themes-in-your-ios-screenlet). 

$$$

Now you must create your Screenlet's View class. This class controls the UI you 
just defined. In the 
[`BaseScreenletView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift), 
Screens provides the default functionality required by all View classes. Your 
View class must therefore extend `BaseScreenletView` to provide the 
functionality unique to your Screenlet. To support your UI, use standard 
`@IBOutlet`s and `@IBAction`s to connect all your XIB's UI components and events 
to your View class. You should also implement getters and setters to get values 
from and set values to the UI components. Your View class should also implement 
any required animations or front-end logic. 

For example, 
[`AddBookmarkView_default`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/Themes/AddBookmarkView_default.swift) 
is Add Bookmark Screenlet's View class. This class extends `BaseScreenletView` 
and contains `@IBOutlet` references to the XIB's text fields. The getters for 
these references let the Theme retrieve the data the user enters into the 
corresponding text field: 

    import UIKit
    import LiferayScreens

    class AddBookmarkView_default: BaseScreenletView {

       @IBOutlet weak var URLTextField: UITextField?
       @IBOutlet weak var titleTextField: UITextField?

       var URL: String? {
           return URLTextField?.text
       }

       var title: String? {
           return titleTextField?.text
       }
    }

In Interface Builder, you must now specify your View class as your XIB file's 
custom class. In Add Bookmark Screenlet, for example, `AddBookmarkView_default` 
is set as the `AddBookmarkView_default.xib` file's custom class in Interface 
Builder. 

If you're using CocoaPods, make sure to explicitly set a valid module for the 
custom class--the grayed-out *Current* default value only suggests a module. 

![Figure 2: In this XIB file, the custom class's module is NOT specified.](../../../../images/screens-ios-theme-custom-module-wrong.png)

![Figure 3: The XIB file is bound to the custom class name, with the specified module.](../../../../images/screens-ios-theme-custom-module-right.png)
