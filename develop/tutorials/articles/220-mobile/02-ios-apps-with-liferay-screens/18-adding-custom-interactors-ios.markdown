# Adding Custom Interactors to iOS Screenlets [](id=adding-custom-interactors-to-ios-screenlets)

Interactors are Screenlet components that implement server communication for a 
specific use case. For example, the Login Screenlet's interactor calls the 
Liferay Mobile SDK service that authenticates a user to the portal. Similarly, 
the interactor for 
[the Add Bookmark Screenlet](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets) 
calls the Liferay Mobile SDK service that adds a bookmark to the Bookmarks 
portlet. 

That's all fine and well, but what if you want to customize a Screenlet's server 
call? What if you want to use a different back-end with a Screenlet? No problem! 
You can implement a custom interactor for the Screenlet. You can plug in a 
different interactor that makes its server call by using custom logic or network 
code. To do this, you must implement the current interactor's interface and then 
pass it to the Screenlet you want to override. You should do this inside your 
app's code. 

In this tutorial, you'll see an example interactor that overrides the Login 
Screenlet to always log in the same user, without a password.

## Implementing a Custom Interactor [](id=implementing-a-custom-interactor)

1. Implement your custom interactor. You must inherit 
   `ServerConnectorInteractor`, as shown here:

        class LoginCustomInteractor: ServerConnectorInteractor {

            override func createConnector() -> ServerConnector? {

                ...

                return connector
            }

        }

2. Implement the optional protocol that receives a `customInteractorForAction`, 
   and return your own interactor:

        func screenlet(screenlet: BaseScreenlet, 
                customInteractorForAction: String, 
                withSender: AnyObject?) -> Interactor? {

            return LoginCustomInteractor()
        }

Great! Now you know how to implement custom interactors for iOS Screenlets.

## Related Topics [](id=related-topics)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)
