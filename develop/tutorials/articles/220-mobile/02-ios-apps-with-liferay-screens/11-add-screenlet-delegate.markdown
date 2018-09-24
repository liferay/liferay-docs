# Add a Screenlet Delegate [](id=add-a-screenlet-delegate)

Screenlet delegates let other classes respond to your Screenlet's actions. For 
example, 
[Login Screenlet's delegate](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios#delegate) 
lets the app developer implement methods that respond to login success or 
failure. Note that the 
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
for each Screenlet that comes with Liferay Screens lists the Screenlet's 
delegate methods. 

You can also create a delegate for your own Screenlet. This tutorial walks you 
through the steps required to do this, using code from 
[the advanced version of the sample Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced) 
as an example. 
All the example code in this tutorial resides in 
[this Screenlet's Screenlet class](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/AddBookmarkScreenlet.swift). 
Also note that this sample Screenlet has two actions: adding a bookmark to a 
Liferay instance's Bookmarks portlet, and retrieving a bookmark's title from its 
URL. This tutorial only details creating a delegate for adding a bookmark. 

Follow these steps to add a delegate to your Screenlet: 

1. Define a delegate protocol that extends the 
   [`BaseScreenletDelegate` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenlet.swift). 
   In this protocol, define success and failure methods so the conforming 
   class can respond to the server call's success and failure, respectively. 
   As parameters, these methods should take a Screenlet instance and the success 
   or failure object. For example, Add Bookmark Screenlet's delegate protocol 
   (`AddBookmarkScreenletDelegate`) defines the following success and failure 
   methods:  

        @objc public protocol AddBookmarkScreenletDelegate: BaseScreenletDelegate {

            optional func screenlet(screenlet: AddBookmarkScreenlet,
                                onBookmarkAdded bookmark: [String: AnyObject])

            optional func screenlet(screenlet: AddBookmarkScreenlet,
                                onAddBookmarkError error: NSError)

        }

   Both take an `AddBookmarkScreenlet` instance as their first 
   argument. For their second argument, the success method contains the bookmark 
   added to the server, and the failure method contains the `NSError` object. 
   Note that in this example, the methods are optional. This means that the 
   delegate class doesn't have to implement them.

2. In your Screenlet class, add a property for your delegate. This property 
   should return `BaseScreenlet`'s `delegate` property as an instance of your 
   delegate. For example, the `addBookmarkDelegate` property in 
   `AddBookmarkScreenlet` returns the `self.delegate` property as 
   `AddBookmarkScreenletDelegate`: 

        var addBookmarkDelegate: AddBookmarkScreenletDelegate? {
            return self.delegate as? AddBookmarkScreenletDelegate
        }

3. Also in your Screenlet class, invoke the appropriate delegate methods in your 
   Interactor's closures. For example, the `interactor.onSuccess` closure in 
   `AddBookmarkScreenlet` calls the delegate method that responds when the 
   Screenlet successfully adds a bookmark. The `interactor.onFailure` closure 
   calls the delegate method that responds when the Screenlet fails to add a 
   bookmark. Note that in this example, these closures are in the Screenlet 
   class's Interactor method that adds a bookmark 
   (`createAddBookmarkInteractor`). Be sure to call your delegate methods 
   wherever the appropriate Interactor's closures are in your Screenlet class: 

        private func createAddBookmarkInteractor() -> Interactor {
            let interactor = AddBookmarkInteractor(screenlet: self,
                                               folderId: folderId,
                                               title: viewModel.title!,
                                               url: viewModel.URL!)

            // Called when the Interactor finishes successfully
            interactor.onSuccess = {
                if let bookmark = interactor.resultBookmark {
                    self.addBookmarkDelegate?.screenlet?(self, onBookmarkAdded: bookmark)
                }
            }

            // Called when the Interactor finishes with an error
            interactor.onFailure = { error in
                self.addBookmarkDelegate?.screenlet?(self, onAddBookmarkError: error)
            }

            return interactor
        }

Great! Now you know how to add a delegate to your Screenlets. 

**Related Topics**

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Adding Screenlet Actions](/develop/tutorials/-/knowledge_base/7-0/adding-screenlet-actions)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
