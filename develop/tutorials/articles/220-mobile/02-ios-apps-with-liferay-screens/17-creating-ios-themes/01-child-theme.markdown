# Creating a Child Theme [](id=creating-a-child-theme)

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
it.
