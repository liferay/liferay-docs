# Mobile Apps with Liferay Screens [](id=mobile-apps-with-liferay-screens)

Screens is Liferay's effort to speed up and simplify native app development for 
Android and iOS devices. Liferay Screens provides a common infrastructure and a 
family of components called *screenlets* that are connected to the Liferay 
platform. This lets your mobile apps leverage the wide variety of server-side 
features that Liferay already provides.

Before Screens, you could use the [Liferay Mobile SDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview) 
to leverage those features from your mobile apps. However, the Mobile SDK is a 
low-level layer on top of the Liferay JSON API. This means that you need to know 
what [Liferay's remote services](/tutorials/-/knowledge_base/6-2/invoking-remote-services) 
are and how to call them. If you already know Liferay's remote services, then 
you may want to review the [Mobile SDK documentation](/tutorials/-/knowledge_base/6-2/mobile) 
to see if the Mobile SDK fits your needs. 

![Figure 1: An app using Liferay Screens for its sign up screen.](../../images/screens-phone-intro.png)

Screens' goal is to speed up mobile app development by hiding the complexity of 
calling Liferay's remote services. Screens gives you a set of screenlets that 
make server calls under the hood. This abstraction layer means that you don't 
have to deal with remote services, JSON responses, or any other seemingly 
strange parameters. Screens also lets you customize the UI, hide or extend 
screenlets, and even override its default behavior. What's more, Screens can be 
seamlessly integrated into any of your existing Android or iOS projects. 
