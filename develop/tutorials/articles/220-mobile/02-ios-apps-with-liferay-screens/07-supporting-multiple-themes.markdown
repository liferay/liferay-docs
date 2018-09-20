# Supporting Multiple Themes in Your Screenlet [](id=supporting-multiple-themes-in-your-screenlet)

Themes let you present the same Screenlet with a different look and feel. For 
example, if you have multiple apps that use the same Screenlet, you can use 
different Themes to match the Screenlet's appearance to each app's style. Each 
Screenlet that comes with Liferay Screens supports the use of multiple Themes. 
For your custom Screenlet to support different Themes, however, it must contain 
a *View Model* protocol. A View Model abstracts the Theme used to display the 
Screenlet, thus letting developers use other Themes. For example, note that the 
Screenlet class's `createInteractor` method in 
[the Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets) 
accesses the View class (`AddBookmarkView_default`) directly when getting a 
reference to the View class:

    let view = self.screenletView as! AddBookmarkView_default

This is all fine and well, except it hard codes the Theme defined by 
`AddBookmarkView_default`! To use a different Theme, you'd have to rewrite this 
line of code to use that Theme's View class. This isn't very flexible! Instead 
of making your Screenlet take expensive yoga classes, you can abstract the 
Theme's View class via a View Model protocol. 

This tutorial shows you how to add a View Model to your Screenlet. The Add 
Bookmark Screenlet created in 
[the Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets) 
is used as an example. Note that you can also follow these steps to add a View 
Model while creating your Screenlet. 

## Creating and Using a View Model [](id=creating-and-using-a-view-model)

Follow these steps to add and use a View Model in your Screenlet:

1. Create a View Model protocol that defines your Screenlet's attributes. These 
   attributes are the View class properties your Screenlet class uses. For 
   example, the 
   [Screenlet class in Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/blob/develop/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/AddBookmarkScreenlet.swift) 
   uses the View class properties `title` and `URL`. Add Bookmark Screenlet's 
   View Model protocol (`AddBookmarkViewModel`) must therefore define variables 
   for these properties: 

        import UIKit

        @objc protocol AddBookmarkViewModel {

            var URL: String? {get}

            var title: String? {get}

        }

2. Conform your View class to your Screenlet's View Model protocol. Make sure to 
   get/set all the protocol's properties. For example, here's Add Bookmark 
   Screenlet's View Class  (`AddBookmarkView_default`) conformed to 
   `AddBookmarkViewModel`: 

        import UIKit
        import LiferayScreens

        class AddBookmarkView_default: BaseScreenletView, AddBookmarkViewModel {
        
            @IBOutlet weak var URLTextField: UITextField?
            @IBOutlet weak var titleTextField: UITextField?
        
            var URL: String? {
                return URLTextField?.text
            }
        
            var title: String? {
                return titleTextField?.text
            }
        
        }

3. Create and use a View Model reference in your Screenlet class. By retrieving 
   data from this reference instead of a direct View class reference, you can 
   use your Screenlet with other Themes. For example, here's the 
   `AddBookmarkScreenlet` class with a `viewModel` property instead of a direct 
   reference to `AddBookmarkView_default`. This class's `createInteractor` 
   method then uses this property to get the `title` and `URL` properties in the 
   `AddBookmarkInteractor` constructor: 

        ...
        //View Model reference
        var viewModel: AddBookmarkViewModel {
            return self.screenletView as! AddBookmarkViewModel
        }

        override public func createInteractor(name name: String?, sender: AnyObject?) -> Interactor? {

            let interactor = AddBookmarkInteractor(screenlet: self,
                                                   folderId: folderId,
                                                   title: viewModel.title!,
                                                   url: viewModel.URL!)

            // Called when the Interactor finishes succesfully
            interactor.onSuccess = {
                let bookmarkName = interactor.resultBookmarkInfo!["name"] as! String
                print("Bookmark \"\(bookmarkName)\" saved!")
            }

            // Called when the Interactor finishes with an error
            interactor.onFailure = { _ in
                print("An error occurred saving the bookmark")
            }

            return interactor
        }
        ...

That's it! Now your Screenlet is ready to use other Themes that you create for 
it. See the tutorial 
[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes) 
for instructions on creating a Theme. 

## Related Topics [](id=related-topics)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)
