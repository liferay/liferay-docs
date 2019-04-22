# Screen Navigation Framework 

The Screen Navigation Framework is for customizing and extending application
UIs. You can use it to make Liferay's applications your own and to make your
applications customizable by others. 

The framework uses a specific structure for screens and supports one or two 
levels of navigation. Each item in the top level navigation is a 
`ScreenNavigationCategory`. Each item in the second level is a 
`ScreenNavigationEntry`. Categories are usually represented by tabs, while
entries use a second level of navigation. You need not have any Entries in your
application, but you must have at least one Category.

![Figure 1: A typical application using screen navigation has three categories and numerous entries.](../../../images/screen-nav-sample-screen-1.png)

The Screen structure normally renders Navigation Categories as horizontal tabs
at the top of the page and Navigation Entries as a vertical list of items along
the left side of the page. The screen box containing the content uses the rest
of the screen. You can customize this default layout for your needs.

![Figure 2: Secondary navigation is optional; you may opt to have only tabs.](../../../images/screen-nav-one-level.png)

## Using the Framework for Your Application 

The Screen Navigation Framework comprises two parts: Java classes for your
screens and a tag library for your front-end. To use Screen Navigation for your
application, first you'll create the necessary Java classes and then add the 
front-end support through JSPs. 

## Adding Custom Screens to Liferay Applications 

You can extend certain Liferay Applications with custom screens. Custom screens 
can add configuration for features you've developed and added to a Liferay 
application, integrating them seamlessly with the original application.
