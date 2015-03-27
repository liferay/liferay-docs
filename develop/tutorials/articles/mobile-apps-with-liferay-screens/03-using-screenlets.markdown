# Using Screenlets

You can start using screenlets once your Android or iOS project is configured to 
use Liferay Screens. Using screenlets is very straightforward. This tutorial 
shows you how to insert and configure screenlets in your Android and iOS 
projects. The Android steps are shown first. 

## Using Screenlets in Your Android Project

First, insert a screenlet in your activity or fragment layout. You can do this 
in XML or with the visual layout editor in Android Studio.

![The `LoginScreenlet` in a layout file.](../../images/screens-android-insert-screenlet.png)

Next, set the screenlet's attributes in its XML tag. Refer to the screenlet 
documentation to see the attributes each screenlet supports:

![The screenlet's attributes are also set in its layout file.](../../images/screens-android-screenlet-attributes.png)

You now need to configure your app to listen for the events the screenlet 
produces. To do this, implement the listener interface associated with the 
screenlet in your activity or fragment class. For example, for the 
`LoginScreenlet` you need to implement the `LoginListener` interface. Then set 
your activity or fragment as the screenlet's listener:

![Implement the screenlet's listener in your activity or fragment class.](../../images/screens-android-screenlet-listener.png)

Awesome! Now you know how to use screenlets in your Android apps.

## Using Screenlets in Your iOS Project

Now you're ready to start using screenlets in your project. First, use Interface 
Builder to insert a new UIView in your Storyboard or XIB file. This is shown in 
the following screenshot:

![Add a new UI View to your project.](../../images/screens-ios-add-uiwindow.png)

Next, change the Custom Class to screenlet's class name. For example, if you're 
using `LoginScreenlet`, then enter that as the Custom Class name. This is shown 
here:

![Change the Custom Class to match the screenlet.](../../images/screens-ios-custom-class.png)

Now you need to conform the screenlet's delegate protocol in your 
`ViewController` class. For example, for the `LoginScreenlet` this is 
`LoginScreenletDelegate`.

![Conform the screenlet's delegate protocol.](../../images/screens-ios-conform-delegate.png)

Now that the screenlet's delegate protocol is conformed in your `ViewController` 
class, go back to Interface Builder and connect the screenlet's delegate to your 
view controller. If the screenlet you're using has more outlets, you can assign 
them as well.

![Connect the screenlet's delegate in Interface Builder.](../../images/screens-ios-xcode-delegate.png)

Note that there are [some issues](http://stackoverflow.com/questions/26180268/interface-builder-iboutlet-and-protocols-for-delegate-and-datasource-in-swift/26180481#26180481) 
connecting an outlet to Swift source code. You can change the delegate data type 
or just assign the outlets by code.

Awesome! Now you know how to use screenlets in your projects. However, if you 
want to use screenlets from Objective-C code, there are a few more things that 
you need to take care of. These are presented in the next section. If you don't 
need to use screenlets from Objective-C, you can skip this.

### Using Screenlets from Objective-C

If you want to invoke screenlet classes from your Objective-C code, then there 
are a couple of additional header files that you need to import. Their import 
statements are shown here:

    #import "liferay-screens-bridge.h"
    #import "[name_of_your_project]-Swift.h"
    
Simply replace `name_of_your_project` with your project's name. If your 
project's name uses non-alphanumeric characters, replace them with `_`. If you 
get tired of adding the same imports over and over again, you can add a 
precompiler header file using the following steps:

1. Create the file `Prefix.pch` and add it to your project.

2. Add the previous imports to that file.

3. Edit the following build settings of your target, using the indicated 
   settings. Remember to replace `path/to/your/file/` with the path to your 
   `Prefix.pch` file:

    - Precompile Prefix Header: `Yes`
    - Prefix Header: `path/to/your/file/Prefix.pch`

    ![The `Prefix.pch` configuration in Xcode settings.](../../images/screens-ios-xcode-prefix.png)

Super! Now you know how to call screenlets from the Objective-C code in your 
project. 

## Related Topics

[Architecture of Liferay Screens](https://www.liferay.com/)

[Preparing Your Project for Liferay Screens](https://www.liferay.com/)
