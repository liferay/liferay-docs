# Writing an Android App for Your Portal [](id=writing-an-android-app-for-your-portal)

Users expect to access portal content from their mobile devices. As an intrepid
developer, you naturally want to turn these expectations into reality.
Thankfully, Liferay provides a way for your mobile apps to access portal
content and applications with 
[Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/android-apps-with-liferay-screens)! 
Screens contains native components called *Screenlets* that can call Liferay's 
remote services and display the results in your app. Each Screenlet comes 
complete with its own fully pluggable UI that you can customize to your liking. 
Although the Screenlets included with Screens only work with Liferay's built-in 
remote services, you can write your own Screenlets that work with your custom 
portlets' remote services. 

If you're an experienced Android developer but need a start-to-finish guide on 
how to integrate Android apps with Liferay, you're in the right place. This 
Learning Path walks you through the creation of an Android app that interacts 
with the Guestbook portlet developed in the 
[Liferay MVC learning path](/develop/learning-paths/mvc/-/knowledge_base/6-2/beginning-liferay-development). 
Since this is a custom portlet, you'll write your own Screenlets that let your 
app retrieve and display guestbooks and their entries. 

You should note that while this Learning Path provides complete code snippets of 
the app as it's developed, not every aspect of Android development is explained 
in detail. Focus is instead placed on the code that leverages Liferay Screens. 
Therefore, you **must** have significant experience in Android app development 
before attempting this Learning Path. Otherwise, you're likely to become 
confused. Google provides extensive documentation of the Android APIs as well as 
some basic tutorials on
[developer.android.com](http://developer.android.com/index.html). 

Experience in Android development is all you need to start working. You needn't 
have completed the Liferay MVC Learning Path to obtain a working Guestbook 
portlet. The complete Guestbook portlet's `.war` package is provided for 
installation into your local Liferay instance. 

Now that you know what you'll be doing here, it's time to move on to the first
series of articles: Beginning Android Development for Your Portal. These 
articles walk you through the steps required to get started developing an 
Android app that interacts with Liferay.
