# Creating the Screenlet Class [](id=creating-the-screenlet-class)

The Screenlet class is the central hub of a Screenlet. It contains the 
Screenlet's properties, a reference to the Screenlet's View class, methods for 
invoking Interactor operations, and more. When using a Screenlet, app developers 
primarily interact with its Screenlet class. In other words, if a Screenlet were 
to become self-aware, it would happen in its Screenlet class (though we're 
reasonably confident this won't happen). 

[Screens's `BaseScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenlet.swift) 
is a base Screenlet class implementation. Since `BaseScreenlet` provides most of 
a Screenlet class's required functionality, your Screenlet class should extend 
`BaseScreenlet`. This lets you focus on your Screenlet's unique functionality. 
Your Screenlet class must also include any `@IBInspectable` properties your 
Screenlet requires and a reference to your Screenlet's View class. To perform 
your Screenlet's action, your Screenlet class must override `BaseScreenlet`'s 
`createInteractor` method. This method should create an instance of your 
Interactor and then set the Interactor's `onSuccess` and `onFailure` closures to 
define what happens when the server call succeeds or fails, respectively. 

For example, the 
[`AddBookmarkScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/AddBookmarkScreenlet.swift) 
is the Screenlet class in Add Bookmark Screenlet. This class extends 
`BaseScreenlet` and contains an `@IBInspectable` variable for the bookmark 
folder's ID (`folderId`). The `AddBookmarkScreenlet` class's `createInteractor` 
method first gets a reference to the View class (`AddBookmarkView_default`). It 
then creates an `AddBookmarkInteractor` instance with this Screenlet class 
(`self`), the `folderId`, the bookmark's title, and the bookmark's URL. Note 
that the View class reference contains the bookmark title and URL that the user 
entered into the UI. The `createInteractor` method then sets the Interactor's 
`onSuccess` closure to print a success message when the server call succeeds. 
Likewise, the Interactor's `onFailure` closure is set to print an error message 
when the server call fails. Note that you're not restricted to only printing 
messages here: you should set these closures to do whatever is best for your 
Screenlet. The `createInteractor` method finishes by returning the Interactor 
instance. Here's the complete `AddBookmarkScreenlet` class: 

    import UIKit
    import LiferayScreens


    public class AddBookmarkScreenlet: BaseScreenlet {

        //MARK: Inspectables

        @IBInspectable var folderId: Int64 = 0

        //MARK: BaseScreenlet

        override public func createInteractor(name name: String?, sender: AnyObject?) -> Interactor? {

            let view = self.screenletView as! AddBookmarkView_default

            let interactor = AddBookmarkInteractor(screenlet: self,
                                               folderId: folderId,
                                               title: view.title!,
                                               url: view.URL!)

            //Called when the Interactor's server call finishes succesfully
            interactor.onSuccess = {
                let bookmarkName = interactor.resultBookmarkInfo!["name"] as! String
                print("Bookmark \"\(bookmarkName)\" saved!")
            }

            //Called when the Interactor's server call fails
            interactor.onFailure = { _ in
                print("An error occurred saving the bookmark")
            }

            return interactor
        }

    }

For reference, the sample Add Bookmark Screenlet's final code is 
[here on GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/AddBookmarkScreenlet.swift).
