# Creating Get Guestbooks Screenlet

The app you created in this Learning Path's previous sections calls the 
Guestbook portlet's remote services via the Guestbook Mobile SDK. You made these 
calls, and handled their results, directly in the app's activity and fragment 
code. Although this works, it isn't very modular. In other words, the code that 
retrieves and displays data from the Guestbook portlet isn't separate from the 
rest of the app's code. This tight coupling could cause issues if you need to 
update any part of the app. Wouldn't it be great if there was a way to separate 
the Guestbook portlet's service calls from the rest of the app? Wouldn't it be 
great if you could separate the code that handles and displays the results of 
those service calls? Today is your lucky day! You can do both by 
creating your own Screenlets for guestbooks and their entries. Creating your own 
Screenlets also brings additional benefits. Since you use a consistent, 
repeatable development model to create Screenlets, you can often reuse Screenlet 
code when creating other Screenlets. You can also package and reuse Screenlets 
in other apps. What's more, Screenlet UIs are fully pluggable. This lets you 
quickly change a Screenlet's appearance without affecting its functionality. In 
summary, Screenlets are pretty much the greatest thing since sliced bread. Now 
it's time to make a sandwich. 

This section of the Learning Path walks you through creating the Get Guestbooks 
Screenlet. You'll follow the same basic steps as the tutorial 
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets), 
plus a few additional steps specific to the Get Guestbooks Screenlet. Before 
beginning, you should read the tutorial 
[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android). 
It explains the components that constitute a Screenlet, and how they relate to 
one another. 