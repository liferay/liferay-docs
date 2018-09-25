# Creating an iOS Extended Theme [](id=creating-an-ios-extended-theme)

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

    ![Figure 1: This example Extended Theme's XIB file extends the Login Portlet's UI and behavior with a switch that lets the user show or hide the password field value.](../../../../images/screens-ios-xcode-ext-theme.png)

2.  Create a new View class that extends the parent Theme's View class. You 
    should name this class after the XIB file you just created. You can add or
    override functionality of the parent Theme's View class. 

3.  Set your new View class as the custom class for your Theme's XIB file. 
    If you added `@IBOutlet` or `@IBAction` actions, bind them to your class. 

Well done! You can optionally 
[package](/develop/tutorials/-/knowledge_base/7-1/packaging-ios-themes) 
your Theme and/or start 
[using it](/develop/tutorials/-/knowledge_base/7-1/using-themes-in-ios-screenlets). 
