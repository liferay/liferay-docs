# Creating Android Views [](id=creating-android-views)

By creating your own Views, you can customize your mobile app's layout, style,
and functionality. You can create them from scratch or use an existing View as a
foundation. Views include a View class for implementing Screenlet behavior, a
Screenlet class for notifying listeners and invoking Interactors, and an XML 
file for specifying the UI. The four Liferay Screens View types support
different levels of customization and parent View inheritance. Here's what each
View type offers: 

**Themed View:** presents the same structure as the current View, but alters the 
theme colors and tints of the View's resources. All existing Views can be themed 
with different styles. The View's colors reflect the current value of the 
Android 
[color palette](https://www.google.com/design/spec/style/color.html#color-color-palette). 
If you want to use one View Set with another View Set's colors, you can use 
those colors in your app's theme (e.g. `colorPrimary_default`, 
`colorPrimary_material`, `colorPrimary_westeros`). 

**Child View:** presents the same UI components as its parent View, but lets 
you change their appearance and position.

**Extended View:** inherits its parent View's functionality and appearance,
but lets you add to and modify both.

**Full View:** provides a complete standalone View for a Screenlet. A full
View is ideal for implementing completely different functionality and appearance
from a Screenlet's current theme.

This tutorial explains how to create all four types of Views. To understand
View concepts and components, you might want to examine the
[architecture](/develop/tutorials/-/knowledge_base/7-1/architecture-of-liferay-screens-for-android)
of Liferay Screens for Android. And the tutorial
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-1/creating-android-screenlets)
can help you create or extend any Screenlet classes your View requires. Now get
ready to create some great Views! 

## Determining Your View's Location [](id=determining-your-views-location)

First, decide whether you'll reuse your view or if it's just for your current
app. If you don't plan to reuse it in another app or don't want to redistribute
it, create it in your app project. 

If you want to reuse your View in another app, create it in a new Android
application module; the tutorial
[Packaging Android Screenlets](/develop/tutorials/-/knowledge_base/7-1/packaging-your-screenlets) 
explains how. When your View's project is in place, you can start creating it. 

First, you'll learn how to create a Themed View. 
