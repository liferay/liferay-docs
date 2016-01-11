# Creating Get Guestbooks Screenlet [](id=creating-get-guestbooks-screenlet)

The app you created in this Learning Path's previous section calls the Guestbook 
portlet's remote services and displays the results, using the app's activity and 
fragment code. Although this works, it isn't very modular. In other words, the 
code that retrieves and displays data from the Guestbook portlet isn't separate 
from the rest of the app's code. This tight coupling could cause issues if you 
need to update any part of the app. Wouldn't it be great if there was a way to 
separate the Guestbook portlet's service calls from the rest of the app? 
Wouldn't it be great if you could also separate the code that handles and 
displays the results of those service calls? Today is your lucky day! You can do 
both by creating your own Screenlets for guestbooks and their entries. 

Creating your own Screenlets also brings additional benefits. Since you use a 
consistent, repeatable development model to create Screenlets, you can often 
reuse Screenlet code when creating other Screenlets. You can also package and 
reuse Screenlets in other apps. What's more, Screenlet UIs are fully pluggable. 
This lets you quickly change a Screenlet's appearance without affecting its 
functionality. In summary, Screenlets are pretty much the greatest thing since 
sliced bread. Now it's time to make a sandwich. 

In the parts that follow, you'll create Screenlets that retrieve and display 
guestbooks and entries: the Get Guestbooks Screenlet and the Get Entries 
Screenlet. You'll then change the app to use these Screenlets instead of using 
the Guestbook Mobile SDK directly in the app's activity and fragment code. 
Because you can insert Screenlets into existing UIs, you won't need to redesign 
the app. You can insert Get Guestbooks Screenlet into `GuestbookActivity`'s 
navigation drawer, and Get Entries Screenlet into `EntriesFragment`. When you 
finish, you'll have an app that uses modularity via Screenlets to present the 
Guestbook portlet's contents. The following screenshot illustrates this.

![Figure 1: Besides using Screenlets to retrieve and display guestbooks and entries, the app's design is unchanged.](../../images/android-app-design-screenlets.png)

Next, you'll learn how to create the Get Guestbooks Screenlet by following these
steps: 

1. Getting started: creating the Screenlet's package and model class. 

2. Creating the Screenlet's UI (its View). 

3. Creating the Screenlet's server call. Screenlets use *interactors* to make 
   server calls. 

4. Creating the Screenlet class. The Screenlet class governs the Screenlet's 
   behavior. 

Before beginning, you should read the tutorial 
[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android). 
It explains the components that constitute a Screenlet, and how they relate to 
one another. You should also read the tutorial 
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets). 
This section of the Learning Path follows the same basic steps as this tutorial, 
plus a few additional steps specific to the Get Guestbooks Screenlet. If you get 
confused or stuck while creating the Screenlet, refer to the finished app that 
contains the Screenlet code 
[here in GitHub](https://github.com/liferay/liferay-docs/develop/learning-paths/mobile/code/06-using-your-screenlets/LiferayGuestbook). 
