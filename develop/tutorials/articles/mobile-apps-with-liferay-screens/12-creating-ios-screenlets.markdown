# Creating Screenlets in Liferay Screens for iOS [](id=creating-screenlets-in-liferay-screens-for-ios)

Liferay Screens' built-in screenlets cover many common use cases in mobile 
apps that connect to Liferay. For example, you can use them to authenticate 
users, interact with Dynamic Data Lists, and view assets. However, what if 
there's not a screenlet for *your* specific use case? No sweat! You can write 
your own screenlet. Screenlets can also be written by others and contributed to 
the Screens project. This extensibility is one of Screens' key strengths. 

In this tutorial, you'll create an example bookmarks screenlet for iOS that has 
the following features: 

- Allows entry of a URL in a text box. 
- Checks if the URL is valid and extracts its title value. 
- Lets the user modify the title. 
- Upon user confirmation, the URL and title is sent back to the Liferay 
  instance's Bookmark services to be saved. 
  
This screenlet is located [here on GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/AddBookmark-screenlet). 
You can use it as a reference as you follow along.
  
You can create the screenlet in your app's Xcode project. However, if you want 
to distribute your screenlet as a library so that other apps can use it, follow 
the steps described in the section *Publish Your Themes Using CocoaPods* in 
[Creating Themes in Liferay Screens for iOS](/develop/tutorials/creating-themes-in-liferay-screens-for-ios).

Before proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios) 
to understand in detail the concepts underlying screenlets. You may also want to 
read the tutorial [Creating Themes in Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios) 
to support your new screenlet from the theme that you want to use. Without any 
further ado, let the screenlet creation begin! 

## Creating the Screenlet [](id=creating-the-screenlet)

Use the following steps to create the screenlet: 

1. Create a new `xib` file for your screenlet. In this example the file is named 
   `AddBookmarkView_default.xib`. You'll build your UI here using Interface 
   Builder. Since the bookmarks screenlet needs text box fields for the 
   bookmark's URL and title, put in two text box fields (`UITextField`). Also, 
   add two buttons to let the user retrieve the title and save the bookmark. To 
   differentiate between these two user actions, assign a value for the 
   `restorationIdentifier` property in each button (for example, `get-title` and 
   `add-bookmark`). The `xib` with the text fields and buttons is shown here:

    ![Figure 1: The new `xib` file for the new screenlet.](../../images/screens-ios-xcode-add-bookmark.png)

2. Create a new interface protocol for the screenlet, containing any needed 
   attributes. Here, this is called `AddBookmarkViewModel` and the associated 
   attributes are `URL` and `title`:

        @objc protocol AddBookmarkViewModel {
        
            var URL: String? {get set}
        
            var title: String? {get set}
        
        }

3. Create a new class called `AddBookmarkView_default` that extends 
   `BaseScreenletView` and conforms `AddBookmarkViewModel`. It must wire all UI 
   components and events from the `xib` by using the standard `@IBOutlet` and 
   `@IBAction`. Getters and setters from `AddBookmarkViewModel` should, 
   respectively, get and set the data from UI components. Also, be sure to write 
   any animations or front end code here.
   
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

4. Set `AddBookmarkView_default` as your `AddBookmarkView_default.xib` file's 
   custom class. If you're using CocoaPods, be careful to set the appropiate 
   module (don't use the grayed out value *Current*). For example, the Custom 
   Class setting in this screenshot is incorrect. This is because the `xib` file 
   is bound to the custom class name without specifying the module:
    
    ![Figure 2: The `xib` file is bound to the custom class name without specifying the module.](../../images/screens-ios-theme-custom-module-wrong.png)
    
    In the following screenshot, the setting for the custom class is correct:
    
    ![Figure 3: The `xib` file is bound to the custom class name, with the module specified.](../../images/screens-ios-theme-custom-module-right.png)

5. Create a class called `GetSiteTitleInteractor` that extends from the 
   `Interactor` class. This new class is the place where you need to write the 
   code that gets the web site's title. It's important to understand how an 
   interactor works:

    - It works asynchronously. This means that the underlying operation (to 
      retrieve the HTML page) runs in the background. When it completes, a 
      closure is called: `onSuccess` or `onFailure` depending on the operation's 
      result. You must use the `callOnSuccess()` and `callOnFailure(error)` 
      methods, respectively, to invoke those closures. 
    - You must override the `start()` method and place your code there. 
    - Since the interactor receives the source screenlet in the init method, you 
      can use that reference to read the input data. 
    
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
        
6. Next, do the same with the second interactor, `LiferayAddBookmarkInteractor`. 
   This interactor is responsible for sending the URL and title to Liferay 
   portal to be stored there as a bookmark. Use `Liferay` as the class name 
   prefix to denote the interactor uses Liferay's services.
   
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

7. Now it's time to glue everything together! Ccreate a class called 
   `AddBookmarkScreenlet ` that extends `BaseScreenlet`. Optionally, you can add 
   any `@IBInspectable` properties to configure the behavior. For example, you 
   could use a boolean property to configure whether the user can save broken 
   URLs.
   
        import UIKit
        import LiferayScreens
        
        class AddBookmarkScreenlet: BaseScreenlet {
        
        
        }

8. Override the `createInteractor` method so it returns an instance of your 
   interactor depending on the `name` parameter. Here, this is 
   `GetSiteTitleInteractor` for the name `get-title` and 
   `LiferayAddBookmarkInteractor` for the name `add-bookmark`. Call separate 
   methods to create each interactor: 

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
        
9. Define the `createGetTitleInteractor()` method as follows: 

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

10. Similarly, define the `createAddBookmarkInteractor()` method as follows: 

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

11. You're done! Now you can add your new screenlet to your storyboard as usual 
    and use it as a ready-to-use component. When the user presses any button, 
    the BaseScreenlet's code creates and runs the interactor. 

Remember, if you want to see this screenlet's final code, it's project is 
located [here on GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/AddBookmark-screenlet). 
Great! By creating this example bookmarks screenlet, you now know how to create 
screenlets in Screens for iOS. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your iOS Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-ios-project)

[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Creating Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios)

[Liferay Screens Overview](/tutorials/-/knowledge_base/6-2/liferay-screens-overview)
