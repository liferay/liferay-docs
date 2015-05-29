# Creating iOS Screenlets [](id=creating-ios-screenlets)

The built-in
[screenlets](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios)
of Liferay Screens cover common use cases for mobile apps that use Liferay. They
authenticate users, interact with Dynamic Data Lists, view assets, and more.
What if, however, there's no screenlet for *your* use case? No problem! You can
write your own--anyone can create a screenlet. Extensibility is a key strength
of Screens. 

This tutorial shows you how to create your own screenlet. As an example, it
references code from the sample
[Add Bookmark screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/AddBookmark-screenlet), 
that saves bookmarks to Liferay's Bookmarks portlet.

Before creating your first screenlet, you might want to learn the
[architecture](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
of Liferay Screens for iOS, to understand the components that comprise a
screenlet. Without further ado, let the screenlet creation begin! 

## Deciding Your Screenlet's Location [](id=deciding-your-screenlets-location)

Where you should create your screenlet depends on how you plan to use it. If you
don't plan to reuse or redistribute it, create it in your app's Xcode project.
Otherwise, create it following the steps described in the section
[Publish Your Themes Using CocoaPods](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes#publish-your-themes-using-cocoapods)
in the tutorial
[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes). 
Even though that section refers to themes, the steps for preparing screenlets
for publishing are the same.

## Creating the Screenlet [](id=creating-the-screenlet)

Follow these steps to create your screenlet: 

1.  With Interface Builder, create a new XIB file for constructing your
    screenlet's UI. The Add Bookmark screenlet's XIB file
    `AddBookmarkView_default.xib`, for example, specifies text box fields for a
    bookmark's URL and title, a button to retrieve the bookmark's title, and a
    button to save the bookmark. You differentiate between user actions by
    specifying a unique `restorationIdentifier` property value for each action.
    The sample screenlet uses the value `get-title` to trigger the action that
    retrieves the title and `add-bookmark` to trigger saving the bookmark.

    ![Figure 1: Here is the sample Add Bookmark screenlet's XIB file.](../../images/screens-ios-xcode-add-bookmark.png)

2.  Create a new interface protocol that specifies your screenlet's attributes.
    The sample Add Bookmark screenlet's interface protocol is called
    `AddBookmarkViewModel` and has associated attributes `URL` and `title`: 

        @objc protocol AddBookmarkViewModel {

            var URL: String? {get set}

            var title: String? {get set}

        }

3.  Create a new view class and give it a name that matches the XIB file's name.
    The class must extend
    [`BaseScreenletView`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Framework/Core/Base/BaseScreenletView.swift)
    and conform to the interface protocol you created. Use standard `@IBOutlet`s
    and `@IBAction`s to wire all the UI components and events from the XIB.
    Implement getters and setters to respectively get and set data from the UI
    components. Also, in this class, make sure to write any required animation
    or front-end code. 

    As an example, the sample screenlet's `AddBookmarkView_default` class
    conforms to the `AddBookmarkViewModel` interface protocol. It connects
    `@IBOutlet`s to the `URL` and `title` text fields specified in the XIB file.
    Lastly, it implements getters and setters for the interface protocol's `URL`
    and `title` properties:

        import UIKit
        import LiferayScreens

        class AddBookmarkView_default: BaseScreenletView, AddBookmarkViewModel {

            @IBOutlet weak var URLTextField: UITextField!
            @IBOutlet weak var titleTextField: UITextField!

            var URL: String? {
                get {
                    return URLTextField.text
                }
                set {
                    URLTextField.text = newValue
                }
            }

            var title: String? {
                get {
                    return titleTextField.text
                }
                set {
                    titleTextField.text = newValue
                }
            }

        }

4.  Specify the view class you created as your XIB file's custom class. In the
    Add Bookmark screenlet, for example, `AddBookmarkView_default` is set as 
    the `AddBookmarkView_default.xib` file's custom class.

    If you're using CocoaPods, make sure to explicitly specify a valid module
    for the custom class--the grayed-out *Current* default text only suggests a
    module. 

    ![Figure 2: This XIB file's custom class's module is NOT specified.](../../images/screens-ios-theme-custom-module-wrong.png)

    ![Figure 3: The XIB file is bound to the custom class name, with the specified module.](../../images/screens-ios-theme-custom-module-right.png)

5.  Create an interactor class for each Liferay service your screenlet uses.
    Each interactor class must extend the Liferay Screens
    [`Interactor`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Framework/Core/Base/Interactor.swift)
    class. A best practice is to start your interactor class name with the
    service's name and end it with *Interactor*. If your interactor modifies
    Liferay, add *Liferay* in front of the service name. 

    An interactor's `start` method is responsible for invoking the portal
    operation. So you must override `Interactor`'s `start` method to do the
    following things:

    - Since the interactor receives the source screenlet in the init method, you
      can read data from that reference (`self.screenlet`) and use it as input
      to the portal operation. 
    - Invoke the Mobile SDK's service to call the portal operation. The service
      works asynchronously, running the operation in the background. 
    - Save the service response to a property variable. Make this property's
      value accessible to classes that need it. 
    - Use methods `callOnSuccess()` or `callOnFailure(error)` to invoke closures
      `onSuccess` or `onFailure`, respectively. 

    The sample Add Bookmark screenlet uses two interactor classes: one 
    for getting the site's title from its URL (`GetSiteTitleInteractor`), and 
    one for adding the bookmark to the Liferay instance 
    (`LiferayAddBookmarkInteractor`). Note that the latter class name begins with 
    `Liferay`, because it communicates with a Liferay instance. Here are
    paraphrased versions of the sample screenlet's interactors classes: 

        import UIKit
        import LiferayScreens

        public class GetSiteTitleInteractor: Interactor {

            public var resultTitle: String?

            override public func start() -> Bool {
                let viewModel = self.screenlet as! AddBookmarkViewModel

                if let URL = viewModel.URL {
                    // 1. use NSURLSession to retrieve the HTML
                    // 2. When the response arrives, extract the title from the HTML
                    // 3. Save the extracted title in the property resultTitle
                    // 4. invoke callOnSuccess() or callOnFailure(error) when everything is done
        
                    // return true to notify the operation is in progress
                    return true
                }

                // return false if you cannot start the operation
                return false
            }

        }


        import UIKit
        import LiferayScreens

        public class LiferayAddBookmarkInteractor: Interactor {

            public var resultBookmarkInfo: [String:AnyObject]?

            override public func start() -> Bool {
                let viewModel = self.screenlet as! AddBookmarkViewModel

                if let URL = viewModel.URL {
                    // 1. use MobileSDK's services to send the bookmark to the portal
                    // 3. Save the response in the property resultBookmarkInfo
                    // 4. invoke callOnSuccess() or callOnFailure(error) when everything is done

                    // return true to notify the operation is in progress
                    return true
                }

                // return false if you cannot start the operation
                return false
            }

        }

6.  Create a screenlet class that extends
    [`BaseScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Framework/Core/Base/BaseScreenlet.swift)
    and includes, optionally, any `@IBInspectable` properties you need. This
    screenlet class leverages your interface protocol, custom view class, and
    interactor classes to control your screenlet's behavior.

    Override the `createInteractor` method to return an instance of the
    requested interactor. If you have multiple interactors, you can write
    additional private methods to retrieve and return the applicable type of
    instance requested. You must handle each interactor's `onSuccess` and
    `onFailure` closures. 

    As an example, the Add Bookmark screenlet's screenlet class extends
    `BaseScreenlet` and overrides `createInteractor` to return an interactor
    instance that that matches the `name` parameter's value. The
    `createGetTitleInteractor` and `createAddBookmarkInteractor` methods create
    the interactor instances for `GetSiteTitleInteractor` and
    `LiferayAddBookmarkInteractor`, respectively. They show messages that
    indicate the operation being invoked and they handle the closures. Here's
    the sample Add Bookmark screenlet class: 

        import UIKit
        import LiferayScreens

        class AddBookmarkScreenlet: BaseScreenlet {

            override public func createInteractor(#name: String?, sender: AnyObject?) -> Interactor? {
                switch name! {
                case "get-title":
                    return createGetTitleInteractor()

                case "add-bookmark":
                    return createAddBookmarkInteractor()

                default:
                    return nil
                }
            }

            private func createGetTitleInteractor() -> GetSiteTitleInteractor {
                let interactor = GetSiteTitleInteractor(screenlet: self)

                // this shows the standard activity indicator in the screen...
                self.showHUDWithMessage("Getting site title...", details: nil)

                interactor.onSuccess = {
                    self.hideHUD()

                    // when the interactor is finished, set the resulting title in the title text field
                    (self.screenletView as? AddBookmarkViewModel)?.title = interactor.resultTitle
                }

                interactor.onFailure = { err in
                    self.showHUDWithMessage("An error occurred retrieving the title",
                        details: nil,
                        closeMode: .ManualClose(true),
                        spinnerMode: .NoSpinner)
                }

                return interactor
            }

            private func createAddBookmarkInteractor() -> LiferayAddBookmarkInteractor {
                let interactor = LiferayAddBookmarkInteractor(screenlet: self)

                self.showHUDWithMessage("Saving bookmark...", details: nil)

                interactor.onSuccess = {
                    self.hideHUDWithMessage("Bookmark saved!")
                }

                interactor.onFailure = { e in
                    self.showHUDWithMessage("An error occurred saving the bookmark",
                        details: nil,
                        closeMode: .ManualClose(true),
                        spinnerMode: .NoSpinner)
                }

                return interactor
            }

        }

    For reference, the sample Add Bookmark screenlet's final code is on
    [GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/AddBookmark-screenlet). 

You're done! Your screenlet is a ready-to-use component that you can add to your
storyboard. You can even
[package it](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes#publish-your-themes-using-cocoapods)
to contribute to the Screens project or distribute with CocoaPods. Now you know
how to create screenlets for iOS.

## Related Topics [](id=related-topics)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)
