# Creating Guestbook List Screenlet [](id=creating-guestbook-list-screenlet)

In the previous section, you created an Android app that contains the Guestbook
Mobile SDK and Liferay Screens. You also used 
[Login Screenlet](/develop/reference/-/knowledge_base/6-2/loginscreenlet-for-android)
to implement authentication to Liferay Portal. That's all your app does though.
It doesn't display any Guestbook portlet content. In this section of the
Learning Path, you'll create Guestbook List Screenlet to retrieve and display
the portlet's guestbooks in your app's navigation drawer. 

Creating your own Screenlets brings additional benefits. Since you use a 
consistent, repeatable development model to create them, you can often reuse 
code when creating other Screenlets. You can also package and reuse Screenlets 
in other apps. What's more, Screenlet UIs are fully pluggable. This lets you 
change a Screenlet's appearance quickly without affecting its functionality. In 
summary, Screenlets are pretty much the greatest thing since sliced bread. Now 
it's time to make a sandwich. 

You'll use these steps to create Guestbook List Screenlet: 

1. Getting started: creating the Screenlet's package, and model class. 
2. Creating the Screenlet's UI (its View). 
3. Creating the Screenlet's Interactor. Interactors are Screenlet components 
   that make server calls. 
4. Creating the Screenlet class. The Screenlet class governs the Screenlet's 
   behavior. 

As background material, the following materials are helpful: 

- [Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android): 
  Explains the components that constitute a Screenlet, and how they relate to 
  one another. 
- [Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets): 
  Explains the general steps for creating a Screenlet. 
- [Creating Android List Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-list-screenlets): 
  Explains the general steps for creating a list Screenlet. This section of the 
  Learning Path follows this tutorial. 

Note that these tutorials explain Screenlet and list Screenlet concepts that 
this Learning Path doesn't cover in depth. Although it's possible to complete 
this Learning Path without reading these tutorials, they explain how Screenlets 
work in more detail. By reading them you'll be better able to apply the Learning 
Path material to your own Screenlets. 

If you get confused or stuck while creating Guestbook List Screenlet, refer to 
the finished app that contains the Screenlet code 
[here in GitHub](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/code/04-mobile/screenlets-app/LiferayGuestbook). 
