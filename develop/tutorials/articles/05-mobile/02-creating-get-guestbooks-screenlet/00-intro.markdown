# Creating Get Guestbooks Screenlet [](id=creating-get-guestbooks-screenlet)

In this Learning Path's previous section, you created an Android app that 
contains the Guestbook Mobile SDK and Liferay Screens. You also used Login 
Screenlet to implement authentication with your Liferay instance. That's all 
your app does though. It doesn't display any content from the Guestbook portlet. 
You'll change that in this section of the Learning Path by creating Get 
Guestbooks Screenlet. You'll create this Screenlet to retrieve and display the 
portlet's guestbooks in your app's navigation drawer. 

Creating your own Screenlets also brings additional benefits. Since you use a 
consistent, repeatable development model to create Screenlets, you can often 
reuse Screenlet code when creating other Screenlets. You can also package and 
reuse Screenlets in other apps. What's more, Screenlet UIs are fully pluggable. 
This lets you quickly change a Screenlet's appearance without affecting its 
functionality. In summary, Screenlets are pretty much the greatest thing since 
sliced bread. Now it's time to make a sandwich. 

Next, you'll learn how to create Get Guestbooks Screenlet by following these 
steps: 

1. Getting started: creating the Screenlet's package and model class. 

2. Creating the Screenlet's UI (its View). 

3. Understanding Mobile SDK calls. Recall that Screenlets use the Mobile SDK to 
   make server calls. Before writing Get Guestbooks Screenlet's server call, you 
   must understand how Mobile SDK calls work. 

4. Creating the Screenlet's server call. Screenlets use components called 
   *Interactors* to make server calls. 

5. Creating the Screenlet class. The Screenlet class governs the Screenlet's 
   behavior. 

Before beginning, you should read the tutorial 
[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android). 
It explains the components that constitute a Screenlet, and how they relate to 
one another. You should also read the tutorial 
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets). 
This section of the Learning Path follows the same basic steps as this tutorial, 
plus a few additional steps specific to Get Guestbooks Screenlet. If you get 
confused or stuck while creating the Screenlet, refer to the finished app that 
contains the Screenlet code 
[here in GitHub](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/code/04-mobile/screenlets-app/LiferayGuestbook). 
