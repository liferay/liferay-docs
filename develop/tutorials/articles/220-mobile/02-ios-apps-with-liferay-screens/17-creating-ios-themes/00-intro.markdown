# Creating iOS Themes [](id=creating-ios-themes)

By creating your own Themes, you can customize your mobile app's design and
functionality. You can create them from scratch or use an existing Theme as a
foundation. Themes include a View class for implementing Screenlet behavior and
an XIB file for specifying the UI. The three Liferay Screens Theme types support
different levels of customization and parent Theme inheritance. Here's what each
Theme type offers: 

-   **Child Theme:** presents the same UI components as its parent Theme, but 
    lets you change their appearance and position. 

-   **Extended Theme:** inherits its parent Theme's functionality and 
    appearance, but lets you add to and modify both. 

-   **Full Theme:** provides a complete standalone View for a Screenlet. A full
    Theme is ideal for implementing functionality and appearance completely
    different from a Screenlet's current Theme. 

This tutorial explains how to create all three types. To understand Theme
concepts and components, you might want to examine the
[architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
of Liferay Screens for iOS. The tutorial
[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)
can help you create any Screenlet classes your Theme requires. 

## Determining Your Theme's Location [](id=determining-your-themes-location)

After determining the type of Theme to create, you need to decide where to
create it. If you want to reuse or redistribute it, you should create it in an
empty Cocoa Touch Framework project in Xcode. The 
[packaging](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes)
tutorial explains how to package and publish with CocoaPods. If you're
not planning to reuse or redistribute your Theme, you can create it directly
inside your app project. 
