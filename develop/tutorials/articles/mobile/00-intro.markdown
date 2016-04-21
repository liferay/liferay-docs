# Mobile [](id=mobile)

Liferay provides two ways to create native Android and iOS apps that work with 
your Liferay instances: Liferay Screens and the Liferay Mobile SDK. Liferay 
Screens does this via ready-to-use components called *Screenlets*. Since 
Screenlets already contain the code required to call your Liferay instance--and 
a complete UI--all you need to do is insert and configure them in your Android 
or iOS app. Screens provides Screenlets for common tasks such as logging in, 
viewing web content, adding DDL records, and more. You can also customize each 
Screenlet to fit your specific needs, or write your own Screenlet. Behind the 
scenes, Screenlets use the Liferay Mobile SDK to call Liferay's remote services. 

The Liferay Mobile SDK is a lower-level tool that lets you manually invoke 
Liferay's remote services. You'll need to use the Mobile SDK to write your own 
Screenlets, or call Liferay's remote services independent of Screens. In most 
cases, you'll find that using Screens saves you time and effort. For example, 
although you can use the Mobile SDK to implement login in your app, Screens 
already provides this via Login Screenlet. There are certain cases, however, 
where using the Mobile SDK makes sense. For example, if you need to call one or 
more Liferay remote services but your app's UI doesn't need to reflect this, 
then it doesn't make sense to use Screenlets for this purpose. Each Screenlet 
must contain a UI. 

Regardless of your specific needs, Liferay has you covered with Liferay Screens 
and the Liferay Mobile SDK. This section of tutorials contains the following 
sections that show you how to use both:

- [Android Apps with Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/android-apps-with-liferay-screens)

- [iOS Apps with Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/ios-apps-with-liferay-screens)

- [Liferay Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/mobile-sdk)

Venture forth to become a mobile guru!
