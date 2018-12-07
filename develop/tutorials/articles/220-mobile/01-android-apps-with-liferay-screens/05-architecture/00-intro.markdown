# Architecture of Liferay Screens for Android [](id=architecture-of-liferay-screens-for-android)

Liferay Screens applies architectural ideas from
[Model View Presenter](http://en.wikipedia.org/wiki/Model-view-presenter), 
[Model View ViewModel](http://en.wikipedia.org/wiki/Model_View_ViewModel), and 
[VIPER](http://www.objc.io/issue-13/viper.html). Screens isn't considered a
canonical implementation of these architectures, because it isn't an app, but it
borrows from them to separate presentation layers from business-logic. This
tutorial explains Screen's high-level architecture, its components' low-level
architecture, and the Android Screenlet lifecycle. Now go ahead and get started
examining Screens's building blocks! 
