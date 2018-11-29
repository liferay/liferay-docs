# Creating iOS Screenlets [](id=creating-ios-screenlets)

[The built-in Screenlets](/develop/reference/-/knowledge_base/7-1/screenlets-in-liferay-screens-for-ios) 
cover common use cases for mobile apps that use Liferay. They authenticate 
users, interact with Dynamic Data Lists, display assets, and more. What if, 
however, there's no Screenlet for *your* use case? No problem! You can create 
your own. Extensibility is a key strength of Liferay Screens. 

This tutorial series explains how to create your own Screenlets. As an example, 
it references code from the sample 
[Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark), 
that saves bookmarks to Liferay's Bookmarks portlet. 

In general, you use the following steps to create Screenlets: 

1.  **Plan Your Screenlet:** Your Screenlet's features and use cases determine 
    where you'll create it and the portal services you'll call. 

2.  **Create Your Screenlet's UI (its Theme):** Although these tutorials present 
    all the information you need to create a Theme for your Screenlet, you may 
    first want to learn the steps for 
    [creating a Theme](/develop/tutorials/-/knowledge_base/7-1/creating-ios-themes). 
    For more information on Themes in general, see 
    [the tutorial on using Themes](/develop/tutorials/-/knowledge_base/7-1/using-themes-in-ios-screenlets). 

3.  **Create the Screenlet's Interactor:** Interactors are Screenlet components 
    that make server calls. 

4.  **Create the Screenlet class:** The Screenlet class is the Screenlet's 
    central component. It controls the Screenlet's behavior and is the component 
    the app developer interacts with when using a Screenlet. 

The tutorials that follow walk you through these steps. Before getting started, 
make sure that you're familiar with the 
[architecture of Liferay Screens](/develop/tutorials/-/knowledge_base/7-1/architecture-of-liferay-screens-for-ios). 
