# Creating Screenlets in Liferay Screens for iOS [](id=creating-screenlets-in-liferay-screens-for-ios)

The built-in screenlets in Liferay Screens cover many common use cases in mobile 
apps that connect to Liferay. For example, you can use them to authenticate 
users, interact with Dynamic Data Lists, view assets, and more. What if, 
however, there's not a screenlet for *your* use case? No problem! You can write 
your own screenlet. Screenlets can also be written by others and contributed to 
the Screens project or distributed with CocoaPods. This extensibility is one of 
Screens's key strengths. 

This tutorial shows you how to create your own screenlets. As an example, code 
is provided for a [sample Add Bookmark screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/AddBookmark-screenlet) 
that saves bookmarks in Liferay's Bookmarks portlet.

Before creating your first screenlet, you may want to read the tutorial 
[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios) 
to understand the components that comprise a screenlet. You may also want to 
read the tutorial [Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes) 
to support your new screenlet from the theme that you want to use. Without any 
further ado, let the screenlet creation begin! 

## Deciding Your Screenlet's Location

Where you create your screenlet depends on how you plan to use it. If you don't 
plan to reuse or redistribute it, create it in your app's Xcode project. 
Otherwise, follow the steps described in the section *Publish Your Themes Using 
CocoaPods* in the tutorial [Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes). 
Even though that section refers to themes, the steps for publishing screenlets 
are the same. 

## Creating the Screenlet [](id=creating-the-screenlet)

Use the following steps to create your screenlet: 

1. Create a new XIB file and construct your screenlet's UI with Interface 
   Builder. For example, the Add Bookmark screenlet's XIB file is named 
   `AddBookmarkView_default.xib` and contains text box fields for the 
   bookmark's URL and title. It also contains two buttons that let the user 
   retrieve the title and save the bookmark. To differentiate between these two 
   user actions, a value is assigned for the `restorationIdentifier` property in 
   each button (for example, `get-title` and `add-bookmark`). The example XIB 
   with the text fields and buttons is shown here:

    ![Figure 1: The example Add Bookmark screenlet's XIB file.](../../images/screens-ios-xcode-add-bookmark.png)

2. Create a new interface protocol for your screenlet, containing any needed 
   attributes. In the example Add Bookmark screenlet, this is called 
   `AddBookmarkViewModel` and the associated attributes are `URL` and `title`: 

        @objc protocol AddBookmarkViewModel {
        
            var URL: String? {get set}
        
            var title: String? {get set}
        
        }

3. Create a new class and give it a name that matches the XIB file's name. This 
   new class should extend `BaseScreenletView` and conform the interface 
   protocol you created in the previous step. It must also wire all UI 
   components and events from the XIB by using the standard `@IBOutlet` and 
   `@IBAction`. Getters and setters from the interface protocol should, 
   respectively, get and set the data from UI components. Also, be sure to write 
   any animations or front end code here. As an example, this class in the Add 
   Bookmark screenlet is called `AddBookmarkView_default` and conforms the 
   `AddBookmarkViewModel` interface protocol. The URL and title text fields from 
   its XIB are connected via `@IBOutlet`. It also implements the `URL` and 
   `title` properties defined in its interface protocol: 
   
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

4. Set the class you just created as the XIB file's custom class. For example, 
   in the Add Bookmark screenlet, `AddBookmarkView_default` is set as the 
   `AddBookmarkView_default.xib` file's custom class. If you're using CocoaPods, 
   be careful to set the appropiate module (don't use the grayed out value 
   *Current*). For example, the Custom Class setting in this screenshot is 
   incorrect. This is because the XIB file is bound to the custom class name 
   without specifying the module:
    
    ![Figure 2: The XIB file is bound to the custom class name without specifying the module.](../../images/screens-ios-theme-custom-module-wrong.png)
    
    In the following screenshot, the setting for the custom class is correct:
    
    ![Figure 3: The XIB file is bound to the custom class name, with the module specified.](../../images/screens-ios-theme-custom-module-right.png)
    
    Remember to wire all the `@IBOutlet` and `@IBAction`, in this example you will have to wire the two text fields and the action button.

5. Create the interactor classes you need to make any network calls. Each 
   interactor class should extend Screens's `Interactor` class. The number of 
   interactor classes you need depends on your screenlet's functionality. 
   Likewise, you should name your interactor classes after the network calls 
   they make. It's important to understand how an interactor works:

    - It works asynchronously, running the underlying operation in the 
      background. When it completes, a closure is called: `onSuccess` or 
      `onFailure` depending on the operation's result. You must use the 
      `callOnSuccess()` and `callOnFailure(error)` methods, respectively, to 
      invoke those closures. 
    - You must override the `start()` method and place your code there. 
    - Since the interactor receives the source screenlet in the `init` method, 
      you can use that reference to read the input data. 
    
    For example, the Add Bookmark screenlet needs two interactor classes: one 
    for getting the site's title from its URL (`GetSiteTitleInteractor`), and 
    one for adding the bookmark in the Liferay instance 
    (`LiferayAddBookmarkInteractor`). Note that the latter's name begins with 
    `Liferay`, because it communicates with a Liferay instance. These example 
    interactor classes are shown here:
    
        import UIKit
        import LiferayScreens
        
        public class GetSiteTitleInteractor: Interactor {
        
            public var resultTitle: String?
        
            override public func start() -> Bool {
                let viewModel = self.screenlet as! AddBookmarkViewModel
        
                if let URL = viewModel.URL {
                    // 1. use NSURLSession to retrieve the HTML
                    // 2. When the response arrives, extract the title from the HTML
                    // 3. Save the extracted title in the property 'resultTitle'
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
                    // 3. Save the response in the property 'resultBookmarkInfo'
                    // 4. invoke callOnSuccess() or callOnFailure(error) when everything is done
        
                    // return true to notify the operation is in progress
                    return true
                }
        
                // return false if you cannot start the operation
                return false
            }
        
        }

6. Create the screenlet class. This class leverages the code from the preceding 
   steps to control your screenlet's behavior. It should extend `BaseScreenlet` 
   and (optionally) include any `@IBInspectable` properties that you need. You 
   should also override the `createInteractor` method to return an instance of 
   the interactor needed when the method is called. If you have multiple 
   interactors, you can write additional methods that return an instance of 
   each, and then call them when needed in `createInteractor`. 
   
    The Add Bookmark screenlet's screenlet class is included here as an example. 
    It extends `BaseScreenlet` and overrides `createInteractor` to return an 
    interactor instance that depends on the `name` parameter's value. The 
    `createGetTitleInteractor` and `createAddBookmarkInteractor` methods create 
    the interactor instances for `GetSiteTitleInteractor` and 
    `LiferayAddBookmarkInteractor`, respectively. 
   
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

7. You're done! You can now add your new screenlet to your storyboard as usual 
    and use it as a ready-to-use component. 

Remember, if you want to see the example Add Bookmark screenlet's final code, 
its project is located [here on GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/AddBookmark-screenlet). 
Great! Now you know how to create screenlets in Screens for iOS. 

## Related Topics [](id=related-topics)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)
