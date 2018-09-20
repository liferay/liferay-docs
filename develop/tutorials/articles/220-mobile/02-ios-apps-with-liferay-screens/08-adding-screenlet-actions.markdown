# Adding Screenlet Actions [](id=adding-screenlet-actions)

With multiple Interactors, it's possible for a Screenlet to have multiple 
actions. You must create an Interactor class for each action. For example, if 
your Screenlet needs to make two server calls, then you need two Interactors: 
one for each call. Your Screenlet class's `createInteractor` method must return 
an instance of each Interactor. Also, recall that each action name is given by 
the `restorationIdentifier` of the UI components that trigger them. You should 
set this `restorationIdentifier` to a constant in your Screenlet. 

This tutorial walks you through the steps necessary to add an action to your 
Screenlet, and trigger an action programmatically. As an example, this tutorial 
uses the 
[advanced version of the sample Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced). 
This Screenlet is similar to the sample Add Bookmark Screenlet created in 
[the Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets). 
The advanced Add Bookmark Screenlet, however, contains two actions: 

1. Add Bookmark: Adds a bookmark to the Bookmarks portlet in a @product@
   installation. This is the Screenlet's main action, created in 
   [the Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets). 

2. Get Title: Retrieves the title from a bookmark URL entered by the user. This 
   tutorial shows you how to implement this action. 

Note that this tutorial doesn't explain Screenlet creation in general. Before 
proceeding, make sure you've read 
[the Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets). 
And without any further ado, it's time to implement your Screenlet's action! 

## Implementing Your Action [](id=implementing-your-action)

Use the following steps to add an action to your your Screenlet: 

1. Create a constant in your Screenlet class for each of your Screenlet's 
   actions. For example, here are the constants in Add Bookmark Screenlet's 
   Screenlet class (`AddBookmarkScreenlet`): 

        static let AddBookmarkAction = "add-bookmark"
        static let GetTitleAction = "get-title"

2. In your Theme's XIB file, add any new UI components that you need to perform 
   the action. For example, Add Boookmark Screenlet's XIB file needs a new 
   button for getting the URL's title: 

    ![Figure 1: The sample Add Bookmark Screenlet's XIB file contains a new button next to the *Title* field for retrieving the URL's title.](../../../images/screens-ios-xcode-add-bookmark-advanced.png)

3. Wire the UI components in your XIB file to your View class. In your View 
   class, you must also set each component's `restorationIdentifier` to its 
   related action. For example, the following code in the Add Bookmark 
   Screenlet's View class (`AddBookmarkView_default`) specifies an `@IBOutlet` 
   for each button. The `didSet` observer for each property sets the 
   `restorationIdentifier` to the appropriate constant you created in the first 
   step: 

        @IBOutlet weak var addBookmarkButton: UIButton? {
            didSet {
                addBookmarkButton?.restorationIdentifier = AddBookmarkScreenlet.AddBookmarkAction
            }
        }
        @IBOutlet weak var getTitleButton: UIButton? {
            didSet {
                getTitleButton?.restorationIdentifier = AddBookmarkScreenlet.GetTitleAction
            }
        }

4. Update your View class or View Model protocol to account for the new action. 
   For example, Add Bookmark Screenlet contains a View Model 
   (`AddBookmarkViewModel`) so it can 
   [support multiple Themes](/develop/tutorials/-/knowledge_base/7-0/supporting-multiple-themes-in-your-screenlet). 
   This View Model must allow the new action to set its `title` variable: 

        import UIKit

        @objc protocol AddBookmarkViewModel {
            var URL: String? {get}
            var title: String? {set get}
        }

5. If your Screenlet uses a View Model, conform your View class to the updated 
   View Model. For example, the `title` variable in Add Bookmark Screenlet's 
   View class (`AddBookmarkView_default`) must implement the setter from the 
   previous step: 

        var title: String? {
            get {
                return titleTextField?.text
            }
            set {
                self.titleTextField?.text = newValue
            }
        }

6. Create a new Interactor class for the new action. To do this, use the same 
   steps detailed in the 
   [Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets#creating-the-interactor). 
   For example, here's the Interactor class for Add Bookmark Screenlet's Get 
   Title action: 

        import UIKit
        import LiferayScreens


        public class GetWebTitleInteractor: Interactor {

            public var resultTitle: String?

            var url: String

            //MARK: Initializer

            public init(screenlet: BaseScreenlet, url: String) {
                self.url = url
                super.init(screenlet: screenlet)
            }

            override public func start() -> Bool {
                if let URL = NSURL(string: url) {

                    // Use the NSURLSession class to retrieve the HTML
                    NSURLSession.sharedSession().dataTaskWithURL(URL) {
                            (data, response, error) in

                        if let errorValue = error {
                            self.callOnFailure(errorValue)
                        }
                        else {
                            if let data = data, html = NSString(data: data, encoding: NSUTF8StringEncoding) {
                                self.resultTitle = self.parseTitle(html)
                            }

                            self.callOnSuccess()
                        }
                    }.resume()

                    return true
                }

                return false
            }

            // Parse the title from a webpage HTML
            private func parseTitle(html: NSString) -> String {
                let range1 = html.rangeOfString("<title>")
                let range2 = html.rangeOfString("</title>")

                let start = range1.location + range1.length

                return html.substringWithRange(NSMakeRange(start, range2.location - start))
            }

        }

7. Update your Screenlet class's `createInteractor` method so it returns the 
   correct Interactor for each action. For example, the `createInteractor` 
   method in Add Bookmark Screenlet's Screenlet class (`AddBookmarkScreenlet`) 
   contains a `switch` statement that returns an `AddBookmarkInteractor` or 
   `GetWebTitleInteractor` instance when the Add Bookmark or Get Title action is 
   called, respectively. Note that the `createAddBookmarkInteractor()` and 
   `createGetTitleInteractor()` methods create these instances. Although you 
   don't have to create your Interactor instances in separate methods, doing so 
   leads to cleaner code: 

        ... 
        override public func createInteractor(name name: String, sender: AnyObject?) 
            -> Interactor? {
                switch name {
                case AddBookmarkScreenlet.AddBookmarkAction:
                    return createAddBookmarkInteractor()
                case AddBookmarkScreenlet.GetTitleAction:
                    return createGetTitleInteractor()
                default:
                    return nil
                }
            }

        private func createAddBookmarkInteractor() -> Interactor {
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

        private func createGetTitleInteractor() -> Interactor {
            let interactor = GetWebTitleInteractor(screenlet: self, url: viewModel.URL!)

            // Called when the Interactor finishes succesfully
            interactor.onSuccess = {
                let title = interactor.resultTitle
                self.viewModel.title = title
            }

            // Called when the Interactor finishes with an error
            interactor.onFailure = { _ in
                print("An error occurred retrieving the title")
            }

            return interactor
        }
        ...

Great! Now you know how to support multiple actions in your Screenlets. The next 
section shows you how to trigger your actions programmatically. 

## Triggering Actions Programmatically [](id=triggering-actions-programmatically)

The user triggers Add Bookmark Screenlet's actions when they press buttons in 
the UI. What if you need to trigger the action programmatically? No problem! The 
[`BaseScreenletView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift) 
contains a set of `userAction` methods that you can call in your View class to 
perform actions programmatically. For example, it's possible to trigger Add
Bookmark Screenlet's Get Title action automatically whenever the user leaves the
`URLTextField`. Since `BaseScreenletView` is the delegate for all `UITextField` 
objects by default, This is done in the View class (`AddBookmarkView_default`) 
by implementing the 
[`textFieldDidEndEditing` method](https://developer.apple.com/reference/uikit/uitextfielddelegate/1619591-textfielddidendediting) 
to call the `userAction` method with the action name: 

    func textFieldDidEndEditing(textField: UITextField) {
        if textField == URLTextField {
            userAction(name: AddBookmarkScreenlet.GetTitleAction)
        }
    }

That's it! Now you know how to trigger your Screenlet actions programmatically. 

## Related Topics [](id=related-topics)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Create and Use a Connector with Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/create-and-use-a-connector-with-your-screenlet)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
