# Writing an iOS App with Liferay Screens [](id=writing-an-ios-app-with-liferay-screens)

Users expect to access portal content from their mobile devices. As an intrepid
developer, you naturally want to turn these expectations into reality. 
Thankfully, Liferay provides a way for your mobile apps to access portal
content and applications with 
[Liferay Screens](https://www.liferay.com/supporting-products/liferay-screens)! 
Screens contains native components called *Screenlets* that can call @product@'s 
remote services and display the results in your app. Each Screenlet comes 
complete with its own fully pluggable UI that you can customize to your liking. 
Although the Screenlets included with Screens only work with @product@'s 
built-in remote services, you can write your own Screenlets that work with your 
custom portlets' remote services. 

If you're an experienced iOS developer but need a start-to-finish guide on how 
to integrate iOS apps with @product@, you're in the right place. This Learning 
Path walks you through the creation of an iOS app that interacts with the 
Guestbook portlet developed in the 
[Developing a Web Application](/develop/tutorials/-/knowledge_base/7-0/developing-a-web-application) 
Learning Path. Since this is a custom portlet, you'll write your own Screenlets 
that let your app retrieve and display guestbooks and their entries. 

You should note that although this Learning Path provides complete code snippets 
of the app, not every aspect of iOS development is explained in detail. Focus is 
instead placed on the code that leverages Liferay Screens. Therefore, you 
**must** have significant iOS development experience before attempting this 
Learning Path. Otherwise, you'll likely be confused. Apple provides extensive 
documentation of the iOS APIs as well as some basic tutorials on 
[developer.apple.com](https://developer.apple.com/). 

Experience in iOS development is all you need to start working. You needn't have 
completed the Developing a Web Application Learning Path to obtain a working 
Guestbook portlet. The complete Guestbook portlet's modules are provided for 
installation into your local @product@ instance. 

Now that you know what you'll be doing here, it's time to move on to the first
series of articles: Beginning iOS Development for Your Portal. These articles 
walk you through the steps required to get started developing an iOS app that 
interacts with @product@. 
