# Creating iOS Screenlets [](id=creating-ios-screenlets)

The built-in
[screenlets](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios)
of Liferay Screens cover common use cases for mobile apps that use Liferay. They
authenticate users, interact with Dynamic Data Lists, view assets, and more.
What if, however, there's no screenlet for *your* use case? No problem! You can
write your own--anyone can create a screenlet. Extensibility is a key strength
of Liferay Screens. 

This tutorial explains how to create your own screenlets. As an example, it
references code from the sample
[Add Bookmark screenlet](https://github.com/liferay/liferay-screens/tree/1.0.0/ios/Samples/AddBookmark-screenlet), 
that saves bookmarks to Liferay's Bookmarks portlet.

To understand the components that comprise a screenlet, you might want to first
analyze the
[architecture](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
of Liferay Screens for iOS. Without further ado, let the screenlet creation
begin! 

## Determining Your Screenlet's Location [](id=determining-your-screenlets-location)

Where you should create your screenlet depends on how you plan to use it. If you
want to reuse or redistribute it, you should create it in an empty Cocoa Touch
Framework project in Xcode. You can then use CocoaPods to publish it. The
section
[Publish Your Themes Using CocoaPods](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes#publish-your-themes-using-cocoapods)
in the tutorial
[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)
explains how to publish an iOS screenlet. Even though that section refers to
themes, the steps for preparing screenlets for publishing are the same. If you
don't plan to reuse or redistribute your screenlet, create it in your app's
Xcode project.

## Creating the Screenlet [](id=creating-the-screenlet)

Follow these steps to create your screenlet: 

1.  In Xcode, create a new XIB file and construct your screenlet's UI with
    Interface Builder. Make sure to use a `restorationIdentifier` property to
    assign a unique restoration ID to each UI component that triggers an action.

    For example, the Add Bookmark screenlet's XIB file
    [`AddBookmarkView_default.xib`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Samples/AddBookmark-screenlet/LiferayScreensAddBookmarkScreenletSample/AddBookmarkScreenlet/AddBookmarkView_default.xib)
    specifies text box fields for a bookmark's URL and title, a button (with
    `restorationIdentifier="get-title"`) to retrieve the bookmark's title, and a
    button (with `restorationIdentifier="add-bookmark"`) to save the bookmark. 

    ![Figure 1: Here's the sample Add Bookmark screenlet's XIB file rendered in Interface Builder.](../../images/screens-ios-xcode-add-bookmark.png)

2.  Create a new interface protocol that specifies your screenlet's attributes.
    The Add Bookmark screenlet's interface protocol
    [`AddBookmarkViewModel`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Samples/AddBookmark-screenlet/LiferayScreensAddBookmarkScreenletSample/AddBookmarkScreenlet/AddBookmarkViewModel.swift)
    has associated attributes `URL` and `title`: 

        import UIKit

        @objc protocol AddBookmarkViewModel {

            var URL: String? {get set}

            var title: String? {get set}

        }

3.  Create a new view class with a name that matches the prefix of your XIB
    file's name. The class must extend
    [`BaseScreenletView`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Framework/Core/Base/BaseScreenletView.swift)
    and conform to the interface protocol you created. Use standard `@IBOutlet`s
    and `@IBAction`s to wire in all the UI components and events from the XIB.
    Implement getters and setters to respectively get and set values from the UI
    components. Also, in this class, make sure to implement any required
    animations or front-end logic. 

    For example, the sample screenlet's
    [`AddBookmarkView_default`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Samples/AddBookmark-screenlet/LiferayScreensAddBookmarkScreenletSample/AddBookmarkScreenlet/AddBookmarkView_default.swift)
    class conforms to the `AddBookmarkViewModel` interface protocol. It keeps
    references to the UI components and implements getters and setters for them.

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

4.  Specify the view class as your XIB file's custom class. In the Add Bookmark
    screenlet, for example, `AddBookmarkView_default` is set as the
    `AddBookmarkView_default.xib` file's custom class.

    If you're using CocoaPods, make sure to explicitly specify a valid module
    for the custom class--the grayed-out *Current* default value only suggests a
    module. 

    ![Figure 2: This XIB file's custom class's module is NOT specified.](../../images/screens-ios-theme-custom-module-wrong.png)

    ![Figure 3: The XIB file is bound to the custom class name, with the specified module.](../../images/screens-ios-theme-custom-module-right.png)

5.  Create an interactor class for each Liferay service your screenlet uses.
    Each interactor class must extend the Liferay Screens
    [`Interactor`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Framework/Core/Base/Interactor.swift)
    class. As a best practice, start your interactor class name with the
    service's name and end it with *Interactor*. If your interactor modifies
    Liferay, append *Liferay* to the service name. 

    Interactors work asynchronously, running their underlying operations in the
    background. Here's what you need to know to implement an interactor: 

    - You can access the source screenlet via the `self.screenlet` property--the
      the interactor's initializer sets it. You can use the screenlet's values
      in interactor operations. 
    - You must override `Interactor`'s `start` method to perform operations
      (e.g., invoke a Liferay portal operation via a Liferay Mobile SDK
      service). 
    - You can save a response to a property and make its value accessible. 
    - You must invoke methods `callOnSuccess` or `callOnFailure` to execute
      closures `onSuccess` or `onFailure`, respectively. 

    The sample Add Bookmark screenlet's interactor class
    `GetSiteTitleInteractor` extracts the site's title from its URL: 

        import UIKit
        import LiferayScreens

        public class GetSiteTitleInteractor: Interactor {

            public var resultTitle: String?

            private var session: NSURLSession?

            override public func start() -> Bool {
                let viewModel = self.screenlet.screenletView as! AddBookmarkViewModel

                if let URL = NSURL(string: viewModel.URL!) {

                    // Use the NSURLSession class to retrieve the HTML
                    NSURLSession.sharedSession().dataTaskWithURL(URL) {
                        (data, response, error) in

                        if let errorValue = error {
                            // Invoke callOnFailure(error) if an error occurs
                            self.callOnFailure(errorValue)
                        }
                        else {
                            if let html = NSString(data: data, encoding: NSUTF8StringEncoding) {

                                // When the response arrives, extract the title from the HTML
                                // Save the extracted title in the property resultTitle
                                self.resultTitle = self.parseTitle(html)
                            }

                            // Invoke callOnSuccess() when everything is done
                            self.callOnSuccess()
                        }
                    }.resume()

                    // Return true to notify the operation is in progress
                    return true
                }

                // Return false if you cannot start the operation
                return false
            }

            private func parseTitle(html: NSString) -> String {
                // Quick & dirty parse
                let range1 = html.rangeOfString("<title>")
                let range2 = html.rangeOfString("</title>")

                let start = range1.location + range1.length

                return html.substringWithRange(NSMakeRange(start, range2.location - start))
            }

        }

    The sample Add Bookmark screenlet's interactor class
    `LiferayAddBookmarkInteractor` adds a bookmark to the Liferay instance.
    Note, its name begins with `Liferay` because it communicates with a Liferay
    instance: 

        import UIKit
        import LiferayScreens
        import LRMobileSDK

        public class LiferayAddBookmarkInteractor: Interactor, LRCallback {

            public var resultBookmarkInfo: [String:AnyObject]?

            override public func start() -> Bool {
                let viewModel = self.screenlet.screenletView as! AddBookmarkViewModel

                if let URL = viewModel.URL {
                    let session = SessionContext.createSessionFromCurrentSession()
                    session?.callback = self

                    // Use MobileSDK's services to send the bookmark to the portal
                    let service = LRBookmarksEntryService_v62(session: session)

                    var error: NSError? = nil

                    service.addEntryWithGroupId(LiferayServerContext.groupId,
                            folderId: 0,
                            name: viewModel.title,
                            url: viewModel.URL,
                            description: "Added from Liferay Screens",
                            serviceContext: nil,
                            error: &error)

                    return (error == nil)
                }

                return false
            }

            public func onFailure(error: NSError!) {
                self.onFailure?(error)
            }

            public func onSuccess(result: AnyObject!) {
                // Save the response in the property resultBookmarkInfo
                resultBookmarkInfo = (result as! [String:AnyObject])

                self.onSuccess?()
            }

        }

6.  Create a screenlet class that extends
    [`BaseScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Framework/Core/Base/BaseScreenlet.swift)
    and includes, optionally, any `@IBInspectable` properties you need. The
    screenlet class leverages your interface protocol, custom view class, and
    interactor classes to control your screenlet's behavior.

    Override the `createInteractor` method to return an instance of the
    requested interactor. If you have multiple interactors, you can write
    additional private methods to retrieve and return the applicable type of
    interactor instance requested. You must handle each interactor's `onSuccess`
    and `onFailure` closures. 

    As an example, the Add Bookmark screenlet's screenlet class extends
    `BaseScreenlet` and overrides `createInteractor` to return an interactor
    instance that that matches the `name` parameter's value. The
    `createGetTitleInteractor` and `createAddBookmarkInteractor` methods create
    instances for interactors `GetSiteTitleInteractor` and
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

                // This shows the standard activity indicator in the screen...
                self.showHUDWithMessage("Getting site title...", details: nil)

                interactor.onSuccess = {
                    self.hideHUD()

                    // When the interactor is finished, set the resulting title in the title text field
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
    [GitHub](https://github.com/liferay/liferay-screens/tree/1.0.0/ios/Samples/AddBookmark-screenlet). 

You're done! Your screenlet is a ready-to-use component that you can add to your
storyboard. You can even
[package](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes#publish-your-themes-using-cocoapods)
it to contribute to the Screens project or distribute with CocoaPods. Now you
know how to create screenlets for iOS.

## Related Topics [](id=related-topics)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)
