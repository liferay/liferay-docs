# Creating iOS Screenlets [](id=creating-ios-screenlets)

The built-in
[Screenlets](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios)
cover common use cases for mobile apps that use Liferay. They
authenticate users, interact with Dynamic Data Lists, display assets, and more.
What if, however, there's no Screenlet for *your* use case? No problem! You can
create your own. Extensibility is a key strength of Liferay Screens. 

This tutorial explains how to create your own Screenlets. As an example, it
references code from the sample 
[Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark), 
that saves bookmarks to Liferay's Bookmarks portlet. 

In general, you use the following steps to create Screenlets: 

1. Plan Your Screenlet: Your Screenlet's features and use cases determine where 
   you'll create it and the Liferay remote services you'll call. 

2. Create Your Screenlet's UI (its Theme): Although this tutorial presents all 
   the information you need to create a Theme for your Screenlet, you may first 
   want to learn the steps for 
   [creating a Theme](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes). 
   For more information on Themes in general, see 
   [the tutorial on using Themes with Screenlets](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets). 

3. Create the Screenlet's Interactor. Interactors are Screenlet components that 
   make server calls. 

4. Create the Screenlet class. The Screenlet class is the Screenlet's central 
   component. It controls the Screenlet's behavior and is the component the app 
   developer interacts with when inserting a Screenlet. 

Before getting started, make sure that you're familiar with the architecture of 
Liferay Screens. 
[Click here](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios) 
to read the Screens architecture tutorial. 

Without further ado, let the Screenlet creation begin! 

## Planning Your Screenlet [](id=planning-your-screenlet)

Before creating your Screenlet, you must determine what it needs to do and how 
you want developers to use it. This determines where you'll create your 
Screenlet and its functionality. 

Where you should create your Screenlet depends on how you plan to use it. If you
want to reuse or redistribute it, you should create it in an empty Cocoa Touch
Framework project in Xcode. You can then use CocoaPods to publish it. The
section
[Publish Your Themes Using CocoaPods](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes#publish-your-themes-using-cocoapods)
in the tutorial 
[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)
explains how to publish an iOS Screenlet. Even though that section refers to
Themes, the steps for preparing Screenlets for publication are the same. If you
don't plan to reuse or redistribute your Screenlet, create it in your app's
Xcode project. 

You must also determine your Screenlet's functionality and what data your 
Screenlet requires. This determines the actions your Screenlet must support and 
the Liferay remote services it must call. For example, Add Bookmark Screenlet 
only needs to respond to one action: adding a bookmark to Liferay's Bookmarks 
portlet. To add a bookmark, this Screenlet must call the Liferay instance's 
`add-entry` service for `BookmarksEntry`. If you're running a Liferay instance 
locally on port 8080, 
[click here](http://localhost:8080/api/jsonws?contextName=bookmarks&signature=%2Fbookmarks.bookmarksentry%2Fadd-entry-6-groupId-folderId-name-url-description-serviceContext) 
to see this service. To add a bookmark, this service requires the following 
parameters: 

- `groupId`: The site ID in the Liferay instance that contains the 
  Bookmarks portlet. 

- `folderId`: The folder ID in the Bookmarks portlet that receives the new 
  bookmark. 

- `name`: The new bookmark's title. 

- `url`: The new bookmark's URL. 

- `description`: The new bookmark's description. 

- `serviceContext`: A 
  [Liferay `ServiceContext`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/ServiceContext.html) 
  object. 

Add Bookmark Screenlet must therefore account for each of these parameters. When 
saving a bookmark, the Screenlet asks the user to enter the bookmark's URL and 
name. The user isn't required, however, to enter any other parameters. This is 
because the app developer sets the `groupId` and `folderId` via the app's code. 
Also, the Screenlet's code automatically populates the `description` and 
`serviceContext`. 

Great! Now you're ready to create your Screenlet's Theme! 

## Creating the Screenlet's UI [](id=creating-the-screenlets-ui)

In Liferay Screens for iOS, a Screenlet's UI is called a Theme. Every Screenlet 
must have at least one Theme. A Theme has the following components: 

1. An XIB file: defines the UI components that the Theme presents to the end 
   user. 

2. A View class: renders the UI, handles user interactions, and communicates 
   with the Screenlet class. 

First, create a new XIB file and use Interface Builder to construct your 
Screenlet's UI. In many cases, the Screenlet's actions must be triggered from
the Theme. To achieve this, make sure to use a `restorationIdentifier` property
to assign a unique ID to each UI component that triggers an action. The user
triggers the action by interacting with the UI component. If the action only
changes the UI's state (that is, changes the UI component's properties), then
you can associate that component's event to an `IBAction` method as usual.
Actions using `restorationIdentifier` are intended for use by actions that need
an Interactor, such as actions that make server requests or retrieve data from a
database. 

For example, Add Bookmark Screenlet's UI needs text boxes for entering a 
bookmark's URL and title. This UI also needs a button to support the Screenlet's 
action: sending the bookmark to a Liferay instance. The 
[XIB file `AddBookmarkView_default.xib`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/Themes/AddBookmarkView_default.xib) 
defines this UI. Because the button triggers the Screenlet's action, it contains 
`restorationIdentifier="add-bookmark"`. 

![Figure 1: Here's the sample Add Bookmark Screenlet's XIB file rendered in Interface Builder.](../../../images/screens-ios-xcode-add-bookmark.png)

+$$$

**Note:** The Screenlet in this tutorial doesn't support multiple Themes. If you 
want your Screenlet to support multiple Themes, your View class must also 
conform a *View Model* protocol that you create. For instructions on this, see 
the tutorial 
[Supporting Multiple Themes in Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/supporting-multiple-themes-in-your-screenlet). 

$$$

Now you must create your Screenlet's View class. This class controls the UI you 
just defined. In the 
[`BaseScreenletView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift), 
Screens provides the default functionality required by all View classes. Your 
View class must therefore extend `BaseScreenletView` to provide the 
functionality unique to your Screenlet. To support your UI, use standard 
`@IBOutlet`s and `@IBAction`s to connect all your XIB's UI components and events 
to your View class. You should also implement getters and setters to get values 
from and set values to the UI components. Your View class should also implement 
any required animations or front-end logic. 

For example, 
[`AddBookmarkView_default`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/Themes/AddBookmarkView_default.swift) 
is Add Bookmark Screenlet's View class. This class extends `BaseScreenletView` 
and contains `@IBOutlet` references to the XIB's text fields. The getters for 
these references let the Theme retrieve the data the user enters into the 
corresponding text field: 

    import UIKit
    import LiferayScreens

    class AddBookmarkView_default: BaseScreenletView {

       @IBOutlet weak var URLTextField: UITextField?
       @IBOutlet weak var titleTextField: UITextField?

       var URL: String? {
           return URLTextField?.text
       }

       var title: String? {
           return titleTextField?.text
       }
    }

In Interface Builder, you must now specify your View class as your XIB file's 
custom class. In Add Bookmark Screenlet, for example, `AddBookmarkView_default` 
is set as the `AddBookmarkView_default.xib` file's custom class in Interface 
Builder. 

If you're using CocoaPods, make sure to explicitly set a valid module for the 
custom class--the grayed-out *Current* default value only suggests a module. 

![Figure 2: In this XIB file, the custom class's module is NOT specified.](../../../images/screens-ios-theme-custom-module-wrong.png)

![Figure 3: The XIB file is bound to the custom class name, with the specified module.](../../../images/screens-ios-theme-custom-module-right.png)

Next, you'll create your Screenlet's Interactor. 

## Creating the Interactor [](id=creating-the-interactor)

Create an Interactor class for each of your Screenlet's actions. In the 
[`Interactor` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/Interactor.swift), 
Screens provides the default functionality required by all Interactor classes. 
Your Interactor class must therefore extend `Interactor` to provide the 
functionality unique to your Screenlet. 

+$$$

**Note:** You may wish to make your server call in a Connector instead of an 
Interactor. Doing so provides an additional abstraction layer for your server 
call, leaving your Interactor to instantiate your Connector and receive its 
results. For instructions on this, see the tutorial 
[Create and Use a Connector with Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/create-and-use-a-connector-with-your-screenlet). 

$$$

Interactors work synchronously, but you can use callbacks (delegates) or 
Connectors to run their operations in the background. For example, the Liferay 
Mobile SDK provides the 
[`LRCallback` protocol](https://github.com/liferay/liferay-mobile-sdk/blob/master/ios/Source/Core/LRCallback.h) 
for this purpose. This is described in 
[the Mobile SDK tutorial on invoking Liferay services asynchronously](/develop/tutorials/-/knowledge_base/7-0/invoking-services-asynchronously-from-your-ios-app). 
Screens bridges this protocol to make it available in Swift. Your Interactor 
class can conform this protocol to make its server calls asynchronously. To 
implement an Interactor class:

- Your initializer must receive all required properties and a reference to the 
  Screenlet. 
- Override `Interactor`'s `start` method to perform the server operations your 
  Screenlet requires (e.g., invoke a Liferay operation via a Liferay Mobile SDK 
  service). 
- Save the server response to an accessible property, if necessary. For example, 
  if the server call returns objects from a Liferay instance, you should 
  store these objects in an accessible property. This way your Screenlet can 
  display those results to the user. 
- You must invoke the methods `callOnSuccess` and `callOnFailure` to execute the 
  closures `onSuccess` and `onFailure`, respectively. 

For example, the sample Add Bookmark Screenlet's Interactor class 
[`AddBookmarkInteractor`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Basic/Interactor/AddBookmarkInteractor.swift) 
makes the server call that adds a bookmark to a Liferay instance. This class 
extends the `Interactor` class and conforms the `LRCallback` protocol. The 
latter ensures that the Interactor's server call runs asynchronously: 

    public class AddBookmarkInteractor: Interactor, LRCallback {...

To save the server call's results, `AddBookmarkInteractor` defines the public 
variable `resultBookmarkInfo`. This class also defines public constants for the 
bookmark's folder ID, title, and URL. The initializer sets these variables and 
calls `Interactor`'s constructor with a reference to the base Screenlet class 
(`BaseScreenlet`): 

    public var resultBookmarkInfo: [String:AnyObject]?
    public let folderId: Int64
    public let title: String
    public let url: String

    public init(screenlet: BaseScreenlet, folderId: Int64, title: String, url: String) {
        self.folderId = folderId
        self.title = title
        self.url = url
        super.init(screenlet: screenlet)
    }

The `AddBookmarkInteractor` class's `start` method makes the server call. To do 
so, it must first get a `Session`. Since Login Screenlet creates a session 
automatically upon successful login, the `start` method retrieves this session 
with `SessionContext.createSessionFromCurrentSession()`. To make the server call 
asynchronously, the `start` method must set a callback to this session. Because 
`AddBookmarkInteractor` conforms the `LRCallback` protocol, setting `self` as 
the session's callback accomplishes this. The `start` method must then create a 
`LRBookmarksEntryService_v7` instance and call this instance's 
`addEntryWithGroupId` method. The latter method calls a Liferay instance's 
`add-entry` service for `BookmarksEntry`. The `start` method therefore provides 
the `groupId`, `folderId`, `name`, `url`, `description`, and `serviceContext` 
arguments to `addEntryWithGroupId`. Note that this example provides a hard-coded 
string for the `description`. Also, the `serviceContext` is `nil` because the 
Mobile SDK handles the `ServiceContext` object for you: 

    override public func start() -> Bool {
        let session = SessionContext.createSessionFromCurrentSession()
        session?.callback = self
    
        let service = LRBookmarksEntryService_v7(session: session)

        do {
            try service.addEntryWithGroupId(LiferayServerContext.groupId,
                            folderId: folderId,
                            name: title,
                            url: url,
                            description: "Added from Liferay Screens",
                            serviceContext: nil)

            return true
        }
        catch {
            return false
        }
    }

Finally, the `AddBookmarkInteractor` class must conform the `LRCallback` 
protocol by implementing the `onFailure` and `onSuccess` methods. The 
`onFailure` method communicates the `NSError` object that results from a failed 
server call. It does this by calling the base `Interactor` class's 
`callOnFailure` method with the error. When the server call succeeds, the 
`onSuccess` method sets the server call's results (the `result` argument) to the 
`resultBookmarkInfo` variable. The `onSuccess` method finishes by calling the 
base `Interactor` class's `callOnSuccess` method to communicate the success 
status throughout the Screenlet: 

    public func onFailure(error: NSError!) {
        self.callOnFailure(error)
    }

    public func onSuccess(result: AnyObject!) {
        //Save result bookmark info
        resultBookmarkInfo = (result as! [String:AnyObject])

        self.callOnSuccess()
    }

Next, you'll create the Screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

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

You're done! Your Screenlet is a ready-to-use component that you can add to your 
storyboard. You can even 
[package it](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes#publish-your-themes-using-cocoapods) 
to contribute to the Screens project or distribute it with CocoaPods. Now you 
know how to create iOS Screenlets! 

## Related Topics [](id=related-topics)

[Supporting Multiple Themes in Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/supporting-multiple-themes-in-your-screenlet)

[Adding Screenlet Actions](/develop/tutorials/-/knowledge_base/7-0/adding-screenlet-actions)

[Create and Use a Connector with Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/create-and-use-a-connector-with-your-screenlet)

[Add a Screenlet Delegate](/develop/tutorials/-/knowledge_base/7-0/add-a-screenlet-delegate)

[Using and Creating Progress Presenters](/develop/tutorials/-/knowledge_base/7-0/using-and-creating-progress-presenters)

[Creating and Using Your Screenlet's Model Class](/develop/tutorials/-/knowledge_base/7-0/creating-and-using-your-screenlets-model-class)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)
