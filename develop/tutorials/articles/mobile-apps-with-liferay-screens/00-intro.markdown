# Mobile Apps with Liferay Screens [](id=mobile-apps-with-liferay-screens)

Liferay Screens speeds up and simplifies developing native apps that use
Liferay. Its power lies in its *screenlets*. A screenlet is a visual component
that you insert into your native app to leverage Liferay Portal's content and
[services](/develop/tutorials/-/knowledge_base/6-2/invoking-remote-services). On
[Android](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android)
and
[iOS](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios),
screenlets are available to log in to your portal, create accounts, submit
forms, display content, and more. Screenlets leverage pluggable *views* or
*themes* to provide different UIs and look and feel. Screens delivers powerful
functionality and UI flexibility. 

You're probably thinking, "These Screenlets sound like the greatest thing since
taco Tuesdays, but what if they don't fit in with my app's UI? What if they
don't behave exactly how I want them to? What if there's no screenlet for what I
want to do?" Fret not! You can customize screenlets to fit your needs by
changing or extending their UI and behavior. You can even write your own
screenlets! What's more, Screens seamlessly integrates with Android and iOS
projects. 

![Figure 1: Here's an app that uses a Liferay Screens sign up screenlet.](../../images/screens-phone-intro.png)

Screenlets leverage the
[Liferay Mobile SDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
to make server calls. The Mobile SDK is a low-level layer on top of the Liferay
JSON API. To write your own screenlets, you must familiarize yourself with
Liferay's
[remote services](/develop/tutorials/-/knowledge_base/6-2/invoking-remote-services).
If no existing screenlet meets your needs, consider customizing an existing
screenlet, creating a screenlet, or directly using the Mobile SDK. Creating a
screenlet involves writing Mobile SDK calls and constructing the screenlet; if
you don't plan to reuse or distribute the implementation then you may want to
forgo writing a screenlet and, instead, work with the
[Mobile SDK](/develop/tutorials/-/knowledge_base/6-2/mobile). A benefit of
integrating an existing screenlet into your app, however, is that the Mobile
SDK's details are abstracted from you. 

These tutorials show you how to use, customize, create, and distribute
screenlets for iOS and Android. They show you how to create views and themes,
too. There are even tutorials that explain the nitty-gritty details of the
Liferay Screens architecture. No matter how deep you want to go, you'll use
screenlets in no time. 
