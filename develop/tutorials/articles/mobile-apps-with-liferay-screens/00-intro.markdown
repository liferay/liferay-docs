# Mobile Apps with Liferay Screens

Screens is Liferay's effort to speed up and simplify the development of native 
apps for Android and iOS devices. Liferay Screens provides a common 
infrastructure and a family of components called *screenlets* that are connected 
to the Liferay platform. This allows your mobile applications to leverage the 
wide variety of server-side features that Liferay already provides.

Before Screens, you could leverage those features by developing mobile apps 
using the [Liferay Mobile SDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview). 
However, Mobile SDK is a low-level layer on top of the Liferay JSON API. This 
means that you need to know what [Liferay's remote services](/tutorials/-/knowledge_base/6-2/invoking-remote-services) 
are and how to make specific calls to them. If you already know some of those 
Liferay concepts, then you may want to take a look at the 
[Mobile SDK documentation](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/mobile) 
to check whether it's convenient for your needs.

![An app using Liferay Screens for its sign up screen.](../../images/screens-phone-intro.png)

The goal of Screens is to speed up mobile app development by hiding the 
additional complexity of calling Liferay's remote services. Screens gives you a 
set of screenlets that make all the server calls under the hood, so you don't 
have to deal with remote services, JSON responses, or any other seemingly 
strange parameters. With Screens you're able to customize the UI, hide or extend 
screenlets' features, or even override the default behavior to implement 
specific features in your project. What's more, Screens can be seamlessly 
integrated into any of your existing Android or iOS projects. 
