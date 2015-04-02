# Using Screenlets [](id=using-screenlets)

You can start using screenlets once your Android or iOS project is configured to 
use Liferay Screens. Using screenlets is very straightforward. This tutorial 
shows you how to insert and configure screenlets in your Android and iOS 
projects. The Android steps are shown first. You'll be a screenlet master in no 
time! 

## Using Screenlets in Your Android Project [](id=using-screenlets-in-your-android-project)

To start using sceenlets in your Android project, first insert a screenlet in 
your activity or fragment layout. You can do this in XML or with Android 
Studio's visual layout editor. The following screenshot shows the 
`LoginScreenlet` inside a `FrameLayout`.

![Figure 1: The `LoginScreenlet` in a layout file.](../../images/screens-android-insert-screenlet.png)

Next, set the screenlet's attributes in its XML tag. Refer to the screenlet 
documentation to see the attributes each screenlet supports. This screenshot 
shows the attributes of the `LoginScreenlet` being set:

![Figure 2: The screenlet's attributes are also set in its layout file.](../../images/screens-android-screenlet-attributes.png)

You now need to configure your app to listen for the events the screenlet 
produces. To do this, implement the listener interface associated with the 
screenlet in your activity or fragment class. For example, for the 
`LoginScreenlet` you need to implement the `LoginListener` interface. Then set 
your activity or fragment as the screenlet's listener. This is shown in the 
following screenshot:

![Figure 3: Implement the screenlet's listener in your activity or fragment class.](../../images/screens-android-screenlet-listener.png)

That's all there is to it! Awesome! Now you know how to use screenlets in your 
Android apps. The next section shows you how to use screenlets in your iOS apps. 

## Using Screenlets in Your iOS Project [](id=using-screenlets-in-your-ios-project)

To start using screenlets in your iOS project, first use Interface Builder to 
insert a new UIView in your Storyboard or XIB file. This is shown here: 

![Figure 4: Add a new UI View to your project.](../../images/screens-ios-add-uiwindow.png)

Next, change the Custom Class to the screenlet's class name. For example, if 
you're using `LoginScreenlet`, then enter `LoginScreenlet` as the Custom Class 
name. This is shown in the following screenshot:

![Figure 5: Change the Custom Class name to match the screenlet.](../../images/screens-ios-custom-class.png)

Now you need to conform the screenlet's delegate protocol in your 
`ViewController` class. For example, for the `LoginScreenlet` this is 
`LoginScreenletDelegate`. This is shown in the screenshot that follows. Note 
that you need to implement the functionality of `onLoginResponse` and 
`onLoginError`. This is indicated by the comments in the screenshot. 

![Figure 6: Conform the screenlet's delegate protocol.](../../images/screens-ios-conform-delegate.png)

Now that the screenlet's delegate protocol is conformed in your `ViewController` 
class, go back to Interface Builder and connect the screenlet's delegate to your 
view controller. If the screenlet you're using has more outlets, you can assign 
them as well. 

![Figure 7: Connect the screenlet's delegate in Interface Builder.](../../images/screens-ios-xcode-delegate.png)

Note that there are [some issues in Xcode](http://stackoverflow.com/questions/26180268/interface-builder-iboutlet-and-protocols-for-delegate-and-datasource-in-swift/26180481#26180481) 
with connecting an outlet to Swift source code. To get around this, you can 
change the delegate data type or assign the outlets in your code.

Awesome! Now you know how to use screenlets in your iOS projects. However, if 
you want to use screenlets from Objective-C code, then there are a few more 
things you need to take care of. These are presented in the next section. You 
can skip this if you don't need to use screenlets from Objective-C. 

### Using Screenlets from Objective-C [](id=using-screenlets-from-objective-c)

If you want to invoke screenlet classes from your Objective-C code, then you 
need to import some additional header files. These import statements are shown 
here: 

    #import "liferay-screens-bridge.h"
    #import "[name_of_your_project]-Swift.h"

Simply replace `name_of_your_project` with your project's name. If your 
project's name uses non-alphanumeric characters, replace them with `_`. If you 
get tired of adding the same imports over and over again, you can use the 
following steps to add a precompiler header file:

1. Create the file `Prefix.pch` and add it to your project.

2. Add the above imports to that file.

3. Edit the following build settings of your target, using the indicated 
   settings. Remember to replace `path/to/your/file/` with the path to your 
   `Prefix.pch` file:

    - Precompile Prefix Header: `Yes`
    - Prefix Header: `path/to/your/file/Prefix.pch`

    ![Figure 8: The `Prefix.pch` configuration in Xcode settings.](../../images/screens-ios-xcode-prefix.png)

Super! Now you know how to call screenlets from the Objective-C code in your iOS 
project. 

## Related Topics [](id=related-topics)

[Creating Screenlets](/tutorials/-/knowledge_base/6-2/creating-screenlets)

[Creating Views and Themes](/tutorials/-/knowledge_base/6-2/creating-views-and-themes)

[Preparing Your Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-project-for-liferay-screens)

[Architecture of Liferay Screens](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens)
