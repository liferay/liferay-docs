# Creating iOS Themes [](id=creating-ios-themes)

By creating your own Themes, you can customize your mobile app's design and
functionality. You can create them from scratch or use an existing Theme as a
foundation. Themes include a View class for implementing Screenlet behavior and
an XIB file for specifying the UI. The three Liferay Screens Theme types support
different levels of customization and parent Theme inheritance. Here's what each
Theme type offers: 

**Child Theme:** presents the same UI components as its parent Theme, but lets 
you change their appearance and position.

**Extended Theme:** inherits its parent Theme's functionality and appearance,
but lets you add to and modify both.

**Full Theme:** provides a complete standalone View for a Screenlet. A full
Theme is ideal for implementing functionality and appearance completely
different from a Screenlet's current Theme.

This tutorial explains how to create all three types. To understand Theme
concepts and components, you might want to examine the
[architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
of Liferay Screens for iOS. The tutorial
[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)
can help you create any Screenlet classes your Theme requires. Now get ready to
create some Themes! 

## Determining Your Theme's Location [](id=determining-your-themes-location)

After determining the type of Theme to create, you need to decide where to
create it. If you want to reuse or redistribute it, you should create it in an
empty Cocoa Touch Framework project in Xcode. The 
[packaging](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes)
tutorial explains how to package and publish with CocoaPods. If you're
not planning to reuse or redistribute your Theme, you can create it directly
inside your app project. 

The rest of this tutorial explains how to create each type of Theme. First,
you'll learn how to create a Child Theme.

## Creating a Child Theme [](id=creating-a-child-theme)

In a Child Theme, you leverage a parent Theme's behavior and UI components, but
you can modify the appearance and position of the UI components. Note that you
can't add or remove any components and the parent Theme must be a Full Theme.
The Child Theme presents visual changes with its own XIB file and inherits the
parent's View class. 

For example, the Child Theme in Figure 1 presents the same UI components as the
[Login Screenlet's](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Auth/LoginScreenlet)
Default Theme, but enlarges them for viewing on devices with larger screens.

![Figure 1: The UI components are enlarged in the example Child Theme's XIB file.](../../../images/screens-ios-xcode-child-theme.png)

You can follow these steps to create a Child Theme: 

1.  In Xcode, create a new XIB file that's named after the Screenlet's View
    class and your Theme. By convention, an XIB file for a Screenlet with a View
    class named *FooScreenletView* and a Theme named *BarTheme* must be named
    `FooScreenletView_barTheme.xib`. You can use content from the parent Theme's
    XIB file as a foundation for your new XIB file. In your new XIB, you can
    change the UI components' visual properties (e.g., their position and size).
    You mustn't change, however, the XIB file's custom class, outlet connection,
    or `restorationIdentifier`--these must match those of the parent's XIB file.

	+$$$

	The XIB file name serves as the Theme's Xcode name. For example, the Theme
	in Figure 1 inherits from the Login Screenlet's Default Theme, which uses
	the View class `LoginView_default`. The new child Theme is named *Large*
	because it's purpose is to enlarge the Screenlet's UI components. In Xcode,
	it's assigned the Theme Name *large*. The XIB file is named
	`LoginView_large.xib`, after the Login Screenlet's View class and the
	Theme's Xcode name.

	$$$

You can optionally
[package](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes) your
Theme and/or start
[using](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)
it. Fantastic! Next, you'll learn how to create an Extended Theme.

## Extended Theme [](id=extended-theme)

An Extended Theme inherits another Theme's UI components and behavior, but lets
you add to or alter it by extending the parent Theme's View class and
creating a new XIB file. An Extended Theme's parent must be a Full Theme. The
[Flat7 Theme](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Flat7)
is an Extended Theme.

These steps explain how to create an Extended Theme:

1.  In Xcode, create a new XIB file named after the Screenlet's View class and
    your Theme. By convention, an XIB file for a Screenlet with a View class
    named *FooScreenletView* and a Theme named *BarTheme* must be named
    `FooScreenletView_barTheme.xib`. You can use the XIB file of your parent
    Theme as a template. Build your UI changes in your new XIB file with
    Interface Builder.

    ![Figure 2: This example Extended Theme's XIB file extends the Login Portlet's UI and behavior with a switch that lets the user show or hide the password field value.](../../../images/screens-ios-xcode-ext-theme.png)

2.  Create a new View class that extends the parent Theme's View class. You 
    should name this class after the XIB file you just created. You can add or
    override functionality of the parent Theme's View class.

3.  Set your new View class as the custom class for your Theme's XIB file.
    If you added `@IBOutlet` or `@IBAction` actions, bind them to your class. 

Well done! You can optionally
[package](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes) your
Theme and/or start
[using](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)
it. Now you know how to create and use an Extended Theme. Next, you'll learn how
to create a Full Theme.

## Full Theme [](id=full-theme)

A Full Theme implements unique behavior and appearance for a Screenlet, without
using a parent Theme. Its View class must inherit Screens's
[`BaseScreenletView`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift)
and conform to the Screenlet's View Model protocol. It must also specify a new
UI in an XIB file. As you create a Full Theme, you can refer to the tutorial
[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)
to learn how to create these classes. 

Follow these steps to create a Full Theme:

1.  Create a new XIB file and use Interface Builder to build your UI. By
    convention, an XIB file for a Screenlet with a View class named
    *FooScreenletView* and a Theme named *BarTheme* must be named
    `FooScreenletView_barTheme.xib`. You can use the XIB file from the
    Screenlet's default Theme as a template. 

    ![Figure 3: This Full Theme for the Login Screenlet, includes a text field for entering the user name, uses the UDID for the password, and adds a *Sign In* button with the same `restorationIdentifier` as the Default Theme.](../../../images/screens-ios-xcode-full-theme.png)

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

Super! You can optionally
[package](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes) your
Theme and/or start
[using](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)
it. Now you know how to create a Full Theme. Note that a Full Theme can serve as
a parent to a Child and Extended Theme. 

You've mastered Themes! 

## Related Topics [](id=related-topics)

[Packaging iOS Themes](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)
