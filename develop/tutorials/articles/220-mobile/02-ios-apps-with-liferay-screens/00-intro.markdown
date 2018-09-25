# iOS Apps with Liferay Screens [](id=ios-apps-with-liferay-screens)

Liferay Screens speeds up and simplifies developing native mobile apps that use
Liferay. Its power lies in its *Screenlets*. A Screenlet is a visual component
that you insert into your native app to leverage @product@'s content and
[services](/develop/tutorials/-/knowledge_base/7-1/web-services). 
On iOS, Screenlets are available to log in to your portal, create accounts, 
submit forms, display content, and more. You can use any number of Screenlets in 
your app; they're independent, so you can use them in modular fashion. 
Screenlets on iOS also deliver UI flexibility with pluggable *Themes* that 
implement elegant user interfaces. The 
[reference documentation](/develop/reference/-/knowledge_base/7-1/screenlets-in-liferay-screens-for-ios) 
for iOS Screenlets describes each Screenlet's features and Themes. 

You might be thinking, "These Screenlets sound like the greatest thing since
taco Tuesdays, but what if they don't fit in with my app's UI? What if they
don't behave exactly how I want them to? What if there's no Screenlet for what I
want to do?" Fret not! You can customize Screenlets to fit your needs by
changing or extending their UI and behavior. You can even write your own
Screenlets! What's more, Screens seamlessly integrates with your existing iOS 
projects. 

![Figure 1: The Liferay Screens Sign Up Screenlet lets users create an account in the portal.](../../../images/screens-ios-intro.png)

Screenlets leverage the
[Liferay Mobile SDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
to make server calls. The Mobile SDK is a low-level layer on top of the Liferay
JSON API. To write your own Screenlets, you must familiarize yourself with
@product@'s 
[web services](/develop/tutorials/-/knowledge_base/7-1/web-services). 
If no existing Screenlet meets your needs, consider customizing an existing
Screenlet, creating a Screenlet, or directly using the Mobile SDK. Creating a
Screenlet involves writing Mobile SDK calls and constructing the Screenlet; if
you don't plan to reuse or distribute the implementation then you may want to
forgo writing a Screenlet and instead work with the
[Mobile SDK](/develop/tutorials/-/knowledge_base/7-1/mobile-sdk). 
A benefit of integrating an existing Screenlet into your app, however, is that 
the Mobile SDK's details are abstracted from you. 

These tutorials show you how to use, customize, create, and distribute iOS 
Screenlets and their Themes. There's even a tutorial that explains the Liferay 
Screens architecture. 

To get started, 
[prepare your iOS project](/develop/tutorials/-/knowledge_base/7-1/preparing-ios-projects-for-liferay-screens)
to use Liferay Screens. 
