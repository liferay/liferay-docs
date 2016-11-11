# iOS Best Practices [](id=ios-best-practices)

When developing iOS projects with Liferay Screens, there are a few best 
practices that you should follow to ensure your code is as clean and bug-free as 
possible. This tutorial lists these. Note that this tutorial doesn't cover Swift 
coding conventions for contributing to the Liferay Screens project on GitHub. 
[Click here](https://github.com/liferay/liferay-screens/blob/master/ios/swift-style-guide.md) 
to see these.

## Naming Conventions [](id=naming-conventions)

Using the naming conventions described here leads to consistency and a better 
understanding of the Screens library. This makes working with your Screenlets 
much simpler. 

### Screenlet Folder [](id=screenlet-folder)

Your Screenlet folder's name should indicate your Screenlet's functionality. For 
example, 
[Login Screenlet's folder is named `LoginScreenlet`](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Auth/LoginScreenlet). 

If you have multiple Screenlets that operate on the same entity, you can place 
them inside a folder named for that entity. For example, 
[Asset Display Screenlet](/develop/reference/-/knowledge_base/7-0/asset-display-screenlet-for-ios) 
and 
[Asset List Screenlet](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-ios) 
both work with Liferay assets. They're therefore in the Screens library's 
[`Asset` folder](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Asset). 

### Screenlets [](id=screenlets)

Naming Screenlets properly is very important; they're the main focus of Liferay 
Screens. Your Screenlet should be named with its principal action first, 
followed by *Screenlet*. Its Screenlet class should also follow this pattern. 
For example, 
[Login Screenlet's](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios) 
principal action is to log users into a Liferay instance. Its Screenlet class is 
`LoginScreenlet`. 

### View Models [](id=view-models)

You should place View Models in your Screenlet's root folder and name them 
after your Screenlet. For example, 
[Forgot Password Screenlet's](/develop/reference/-/knowledge_base/7-0/forgotpasswordscreenlet-for-ios) 
View Model is in the 
[`ForgotPasswordScreenlet` folder](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Auth/ForgotPasswordScreenlet) 
and is named `ForgotPasswordViewModel`. 

### Interactors [](id=interactors)

You should place your Screenlet's Interactors in a folder named `Interactors` in 
your Screenlet's root folder. You should name each Interactor with its action 
first, followed by *Interactor*. For example, 
[Rating Screenlet](/develop/reference/-/knowledge_base/7-0/rating-screenlet-for-ios) 
has three Interactors in 
[its `Interactors` folder](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Rating/Interactors): 

- `DeleteRatingInteractor`: Deletes an asset's ratings
- `LoadRatingsInteractor`: Loads an asset's ratings
- `UpdateRatingInteractor`: Updates an asset's ratings

### Connectors [](id=connectors)

Name your Connectors with the same naming conventions as Interactors, replacing 
*Interactor* with *Connector*. If your Connector calls a Liferay service, 
precede *Connector* with *Liferay*. For example, 
[the Connector `CommentAddLiferayConnector`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Comment/Add/Connectors/CommentAddLiferayConnector.swift) 
adds comments to an asset in a Liferay instance. A Connector that retrieves a 
webpage's title from any URL would be called `GetWebsiteTitleConnector`. 

### Themes [](id=themes)

Place your Screenlet's Themes in a folder named *Themes* in your Screenlet's 
root folder. If you're creating a group of similarly styled Themes for multiple 
Screenlets, however, then you can place them in a separate *Themes* folder 
outside of your Screenlets' root folders. This is what the Screens Library does 
for 
[its Default and Flat7 Themes](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes). 
The `Default` and `Flat7` folders each contain similarly styled Themes for 
several Screenlets. Also note that each Screenlet's Theme is in its own folder. 
For example, 
[Forgot Password Screenlet's](/develop/reference/-/knowledge_base/7-0/forgotpasswordscreenlet-for-ios) 
Default Theme is in 
[the folder `Themes/Default/Auth/ForgotPasswordScreenlet`](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Default/Auth/ForgotPasswordScreenlet). 
Note that the `Auth` folder is the Screenlet's module. Creating your Screenlets 
and Themes in modules isn't required. 

Recall that a Theme consists of an XIB file and a View class. Name these after 
your Screenlet, but with *View* instead of *Screenlet*. The filenames should 
also be suffixed with `_yourThemeName`. For example, the XIB file and View class 
for Forgot Password Screenlet's Default theme are 
`ForgotPasswordView_default.xib` and `ForgotPasswordView_default.swift`, 
respectively. 

## Avoid Hardcoded Elements [](id=avoid-hardcoded-elements)

Using constants instead of hard coded elements is a simple way to avoid bugs. 
Constants reduce the likelihood that you'll make a typo when referring to common 
elements. They also gather these elements in a single location. For example, 
when you 
[add an action to your Screenlet](/develop/tutorials/-/knowledge_base/7-0/adding-screenlet-actions), 
each Screenlet action used as a `restorationIdentifier` in the View class is 
defined as a constant in the Screenlet class. The Screenlet class's 
`createInteractor` method then uses the constants to distinguish between the 
actions. If you instead typed each action manually in both places, a typo could 
break your Screenlet and would be difficult to track down. Defining the actions 
in one place via constants avoids this potentially maddening complication. 

Screenlet attributes, like those listed in each Screenlet's 
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios), 
are another good example of this. Although you can set these directly in 
Interface Builder, it's better to set them via constants in a `plist` file. This 
puts all your Screenlets' attributes in a single location that is also subject 
to version control. For instructions on setting attributes in a `plist` file, 
[see the Configuring Communication with Liferay section](/develop/tutorials/-/knowledge_base/7-0/preparing-ios-projects-for-liferay-screens#configuring-communication-with-liferay) 
of the tutorial on preparing iOS projects for Liferay Screens. 

To retrieve these values in your code, you can use the following `LiferayServerContext` methods:

- `propertyForKey`: Get a property as an `AnyObject`
- `numberPropertyForKey`: Get a property as an `NSNumber`.
- `longPropertyForKey`: Get a property as an `Int64`.
- `intPropertyForKey`: Get a property as an `Int`.
- `booleanPropertyForKey`: Get a property as a `Bool`.
- `datePropertyForKey`: Get a property as an `NSDate`.
- `stringPropertyForKey`: Get a property as a `String`.

For example, the following code retrieves the `galleryFolderId` value and sets 
it to Image Gallery Screenlet's `folderId` attribute:

    @IBOutlet weak var imageGalleryScreenlet: ImageGalleryScreenlet? {
        didSet {
            imageGalleryScreenlet?.delegate = self
            imageGalleryScreenlet?.presentingViewController = self

            imageGalleryScreenlet?.repositoryId = LiferayServerContext.groupId
            imageGalleryScreenlet?.folderId = LiferayServerContext.longPropertyForKey("galleryFolderId")
        }
    }

## Stay in Your Layer [](id=stay-in-your-layer)

When accessing variables that belong to other Screenlet components, you should 
avoid those outside your current Screenlet layer. This achieves better 
decoupling between the layers, which tends to reduce bugs and simplify 
maintenance. For an explanation of the layers in Liferay Screens, see 
[the architecture tutorial](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios).
For example, you shouldn't directly access View variables from an Interactor. 
This Interactor's `start` method gets a View instance and accesses its `title` 
variable: 

    public class MyInteractor: Interactor {
        override func start() -> Bool {
            if let view = self.screenlet.screenletView as? MyView {
                let title = view.title
                ...
            }
        }
    }

Instead, you should pass the variable to the Interactor's initializer. The 
Interactor now contains its own `title` variable, set in its initializer:

    public class MyInteractor: Interactor {

        public let title: String

        //MARK: Initializer

        public init(screenlet: BaseScreenlet, title: String) {
            self.title = title
            super.init(screenlet: screenlet)
        }
    }

The Screenlet class's `createInteractor` method calls this initializer when 
creating an instance of the Interactor. Also note that the Screenlet's View 
Model is used to retrieve the View's `title`. As explained in the tutorial 
[Supporting Multiple Themes in Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/supporting-multiple-themes-in-your-screenlet), 
a View Model serves as an abstraction layer for your View, which lets you use 
different Themes with a Screenlet: 

    public class MyScreenlet: BaseScreenlet {
        ...
        override public func createInteractor(name name: String, sender: AnyObject?) -> Interactor? {
            let interactor = MyInteractor(self, title: viewModel.title)
            ...
        }
        ...
    }

There are, however, a few places where you can break this rule (otherwise it 
wouldn't be possible for layers to interact): 

- The Screenlet class's `createInteractor` method. To get the user's input, this 
  method must access the View's computed properties. 

- The Interactor's `onSuccess` closure in the Screenlet class. Here, you must 
  retrieve the Interactor's result object. 

- When using a Connector, the Interactor's `completedConnector` method. In this 
  method, you must retrieve the Connector's result object. 

- The Screenlet class's View Model references. This is required for the 
  Screenlet to communicate with the View. 

## Related Topics [](id=related-topics)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Supporting Multiple Themes in Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/supporting-multiple-themes-in-your-screenlet)

[Adding Screenlet Actions](/develop/tutorials/-/knowledge_base/7-0/adding-screenlet-actions)

[Create and Use a Connector with Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/create-and-use-a-connector-with-your-screenlet)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
