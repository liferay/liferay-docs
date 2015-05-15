# Using Screenlets in Your Android Project [](id=using-screenlets-in-your-android-project)

You can start using screenlets once your Android project is configured to use 
Liferay Screens. Using screenlets is very straightforward. This tutorial shows 
you how to insert and configure screenlets in your Android project. You'll be a 
screenlet master in no time! 

## Inserting and Configuring Screenlets in Your Android Project [](id=inserting-and-configuring-screenlets-in-your-android-project)

To start using sceenlets in your Android project, first insert a screenlet in 
your activity or fragment layout. You can do this in XML or with Android 
Studio's visual layout editor. The following screenshot shows the 
`LoginScreenlet` inside a `FrameLayout`. 

![Figure 1: The `LoginScreenlet` in a layout file.](../../images/screens-android-insert-screenlet.png)

Next, set the screenlet's attributes in its XML tag. Refer to the screenlet
documentation to see the attributes supported by each screenlet. This screenshot
shows the attributes of the `LoginScreenlet` being set:

![Figure 2: The screenlet's attributes are also set in its layout file.](../../images/screens-android-screenlet-attributes.png)

You now need to configure your app to listen for the events triggered by the
screenlet. To do this, implement the listener interface associated with the
screenlet in your activity or fragment class. For example, for the
`LoginScreenlet` you need to implement the `LoginListener` interface. Then set
your activity or fragment as the screenlet's listener. This is shown in the
following screenshot:

![Figure 3: Implement the screenlet's listener in your activity or fragment class.](../../images/screens-android-screenlet-listener.png)

Also make sure that you implement any additional methods required by the 
screenlet's listener interface. These listener methods are listed in the 
screenlet's [reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android). 
For example, when implementing `LoginListener` you must also implement the 
`onLoginSuccess(User user)` and `onLoginFailure(Exception e)` methods. Here, 
example implementations that display `Toast` messages for each method are shown. 
Of course, you're free to implement other functionality (or none at all) in 
these methods as needed. 

    @Override
    public void onLoginSuccess(User user) {
        Toast.makeText(getApplicationContext(), "Sign in successful!", 
            Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onLoginFailure(Exception e) {
        String message = "Couldn't log in: " + e.getMessage();
        Toast.makeText(getApplicationContext(), message, 
            Toast.LENGTH_SHORT).show();
    }

That's all there is to it! Awesome! Now you know how to use screenlets in your 
Android apps. 

## Related Topics [](id=related-topics)

[Creating Screenlets in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-android)

[Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android)

[Preparing Your Android Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-android-project-for-liferay-screens)

[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)
