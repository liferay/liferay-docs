# Architecture of Liferay Screens for iOS [](id=architecture-of-liferay-screens-for-ios)

Liferay Screens separates its presentation and business-logic code using ideas 
from
[Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter),
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel),
and
[VIPER](http://www.objc.io/issue-13/viper.html). However, Screens isn't a
canonical implementation of these architectures because they're designed for
apps. Screens isn't an app; it's a suite of visual components intended for use
*in* apps. 

The tutorials in this section explain the architecture of Liferay Screens for 
iOS. First is an overview of the high level components that make up the system. 
This includes the Core, Screenlets, and Themes. These components are then 
described in detail in the tutorials that follow. After you get done examining 
these building blocks, you'll be ready to create some amazing Screenlets and 
Themes! 
