# Using Screenlets in Android Apps

You can start using screenlets once your Android app is configured to use 
Liferay Screens. Using them is very straightforward. As an example, this 
tutorial uses `LoginScreenlet` to show you how to insert and configure 
screenlets in your Android app. The general steps apply for other screenlets as 
well. For specific detail on each available screenlet, see the 
[screenlet reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android). 
You'll be a screenlet master in no time! 

## Inserting and Configuring Screenlets

First, insert a screenlet in your activity or fragment layout. You can do this 
in XML or with Android Studio's visual layout editor. For example, the following 
screenshot shows `LoginScreenlet` inside a `FrameLayout` in an activity. 

![Figure 1: The `LoginScreenlet` in a layout file.](../../images/screens-android-insert-screenlet.png)

Next, set the screenlet's attributes in its XML tag. Refer to the 
[screenlet documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android) 
to see the attributes required and supported by each screenlet. This screenshot 
shows the attributes of the `LoginScreenlet` being set:

![Figure 2: The screenlet's attributes are also set in its layout file.](../../images/screens-android-screenlet-attributes.png)

You now need to configure your app to listen for the events the screenlet 
triggers. To do this, implement the screenlet's listener interface in your 
activity or fragment class. For example, for `LoginScreenlet` you need to 
implement `LoginListener`. Then set your activity or fragment as the screenlet's 
listener. An example of this for `LoginScreenlet` is shown in the following 
screenshot: 

![Figure 3: Implement the screenlet's listener in your activity or fragment class.](../../images/screens-android-screenlet-listener.png)

Also, make sure that you implement any additional methods required by the 
screenlet's listener interface. These listener methods are listed in each 
screenlet's [reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android). 
For example, when implementing `LoginListener` you must also implement the 
`onLoginSuccess(User user)` and `onLoginFailure(Exception e)` methods. The 
example implementations here display simple `Toast` messages for each method. Of 
course, you're free to implement other functionality (or none at all) in 
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

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)

[Creating Android Views](/develop/tutorials/-/knowledge_base/6-2/creating-android-views)

[Preparing Your Android Project for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-your-android-project-for-liferay-screens)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)
