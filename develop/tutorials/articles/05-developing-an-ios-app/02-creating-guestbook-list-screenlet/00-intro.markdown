# Creating Guestbook List Screenlet [](id=creating-guestbook-list-screenlet-ios)

In the previous section, you created an iOS app that contains the Guestbook
Mobile SDK and Liferay Screens. You also used 
[Login Screenlet](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios) 
to implement authentication to @product@. That's all your app does though. It 
doesn't display any Guestbook portlet content. In this section of the Learning 
Path, you'll create Guestbook List Screenlet to retrieve and display the 
portlet's guestbooks in your app's guestbooks scene. 

Creating your own Screenlets brings additional benefits. Since you use a 
consistent, repeatable development model to create them, you can often reuse 
code when creating other Screenlets. You can also package and reuse Screenlets 
in other apps. What's more, Screenlet UIs are fully pluggable. This lets you 
change a Screenlet's appearance quickly without affecting its functionality. In 
summary, Screenlets are pretty much the greatest thing since sliced bread. Now 
it's time to make a sandwich. 

You'll use these steps to create Guestbook List Screenlet: 

1.  Getting started: Create the Screenlet's folder, and the model class. The 
    model class creates objects that represent guestbooks retrieved from the 
    portlet, making it easier to work with guestbooks in your app. 
2.  Create the Screenlet's UI (its Theme). 
3.  Create the Connector. Connectors are Screenlet components that make server 
    calls. 
4.  Create the Interactor. In list Screenlets, Interactors are Screenlet 
    components that instantiate Connectors and receive their results. 
5.  Create the delegate. Delegates let other classes respond to the Screenlet's 
    events. 
6.  Create the Screenlet class. The Screenlet class governs the Screenlet's 
    behavior. 

As background material, the following materials are helpful: 

- [Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios): 
  Explains the components that constitute a Screenlet, and how they relate to 
  one another. 
- [Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets): 
  Explains the general steps for creating a Screenlet. 
- [Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets): 
  Explains the general steps for creating a list Screenlet. This section of the 
  Learning Path follows this tutorial. 

Note that these tutorials explain Screenlet and list Screenlet concepts that 
this Learning Path doesn't cover in depth. Although it's possible to complete 
this Learning Path without reading these tutorials, they explain how Screenlets 
work in more detail. By reading them you'll be better able to apply the Learning 
Path material to your own Screenlets. 

If you get confused or stuck while creating Guestbook List Screenlet, refer to 
the finished app that contains the Screenlet code 
[here in GitHub](https://github.com/liferay/liferay-docs/tree/7.0.x/develop/tutorials/code/mobile/ios/screenlets-app/Liferay%20Guestbook). 
