# Creating an iOS Full Theme [](id=creating-an-ios-full-theme)

A Full Theme implements unique behavior and appearance for a Screenlet, without
using a parent Theme. Its View class must inherit Screens's
[`BaseScreenletView`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift)
and conform to the Screenlet's View Model protocol. It must also specify a new
UI in an XIB file. As you create a Full Theme, you can refer to the tutorial
[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-1/creating-ios-screenlets)
to learn how to create these classes. 

Follow these steps to create a Full Theme:

1.  Create a new XIB file and use Interface Builder to build your UI. By
    convention, an XIB file for a Screenlet with a View class named
    *FooScreenletView* and a Theme named *BarTheme* must be named
    `FooScreenletView_barTheme.xib`. You can use the XIB file from the
    Screenlet's default Theme as a template. 

    ![Figure 1: This Full Theme for the Login Screenlet, includes a text field for entering the user name, uses the UDID for the password, and adds a *Sign In* button with the same `restorationIdentifier` as the Default Theme.](../../../../images/screens-ios-xcode-full-theme.png)

2.  Create a new View class for your Theme named after the XIB file you just 
    created. As a template, you can use the View class of your Screenlet's
    Default Theme. Your new View class must inherit
    [`BaseScreenletView`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift)
	and conform to the Screenlet's `*ScreenletViewModel` protocol, implementing
	the corresponding getters and setters. It should also add all the
	`@IBOutlet` properties or `@IBAction` methods you need to bind your UI
	components. 

3.  Set your Theme's new View class as your XIB file's custom class and bind 
    any `@IBOutlet` and `@IBAction` actions to your class. 

Now that your theme is finished, you can optionally 
[package](/develop/tutorials/-/knowledge_base/7-1/packaging-ios-themes) 
and/or start 
[using it](/develop/tutorials/-/knowledge_base/7-1/using-themes-in-ios-screenlets). 
Note that a Full Theme can serve as a parent to a Child and Extended Theme. 
