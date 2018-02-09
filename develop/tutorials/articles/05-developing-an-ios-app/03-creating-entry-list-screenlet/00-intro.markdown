# Creating Entry List Screenlet [](id=creating-entry-list-screenlet-ios)

In the previous section, you created Guestbook List Screenlet to retrieve and
display guestbooks from the Guestbook portlet. You still need a way to retrieve
and display each guestbook's entries, though. You'll do this by creating another
list Screenlet: Entry List Screenlet. This section walks you through the steps
required to create it. 

Because you use a consistent development model to create Screenlets, similar 
Screenlets have similar code. As with guestbooks, it makes sense to display 
entries in a list using a list Screenlet. This means you can reuse most of
Guestbook List Screenlet's code in Entry List Screenlet. You'll therefore create 
Entry List Screenlet using the same sequence of steps you used to create 
Guestbook List Screenlet: 

1.  Getting started: create the Screenlet's folder, and the model class. This 
    model class creates objects that represent entries retrieved from the 
    portlet, making it easier to work with entries in the app. 
2.  Create the Screenlet's UI (its Theme). 
3.  Create the Connector. Connectors are Screenlet components that make server 
    calls. 
4.  Create the Interactor. In list Screenlets, Interactors are Screenlet 
    components that instantiate Connectors and receive their results. 
5.  Create the delegate. Delegates let other classes respond to the Screenlet's 
    events. 
6.  Create the Screenlet class. The Screenlet class governs the Screenlet's 
    behavior. 

Although this Learning Path section presents complete code snippets, it only 
discusses the code unique to Entry List Screenlet. Refer back to the previous 
section for detailed explanations of the code shared with Guestbook List 
Screenlet. 

If you get confused or stuck while creating Guestbook List Screenlet, refer to 
the finished app that contains the Screenlet code 
[here in GitHub](https://github.com/liferay/liferay-docs/tree/7.0.x/develop/tutorials/code/mobile/ios/screenlets-app/Liferay%20Guestbook).
