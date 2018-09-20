# Creating and Using Your Screenlet's Model Class [](id=creating-and-using-your-screenlets-model-class)

Liferay Screens typically receives entities from a Liferay instance as 
`[String:AnyObject]`, where `String` is the entity's attribute and `AnyObject` 
is the attribute's value. Although you can use these dictionary objects 
throughout your Screenlet, it's often easier to create a *model class* that 
converts each into an object that represents the corresponding Liferay entity. 
This is especially convenient for complex entities composed of many 
attribute-value pairs. Note that Liferay Screens already provides several model 
classes for you. 
[Click here](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Models) 
to see them. 

At this point, you might be saying, "Ugh! I have complex entities and Screens 
doesn't provide a model class for them! I'm just going to give up and watch 
football." Fret not! Although we'd never come between you and football, creating 
and using your own model class is straightforward. 

Using the 
[advanced version of the sample Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced) 
as an example, this tutorial shows you how to create and use a model class in 
your Screenlet. First, you'll create your model class. 

## Creating Your Model Class [](id=creating-your-model-class)

Your model class must contain all the code necessary to transform each 
`[String:AnyObject]` that comes back from the server into a model object that 
represents the corresponding Liferay entity. This includes a constant for 
holding each `[String:AnyObject]`, and initializer that sets this constant, and 
a public property for each attribute value. 

For example, the sample Add Bookmark Screenlet adds a bookmark to a Liferay 
instance's Bookmarks portlet. Since the Mobile SDK service method that adds the 
bookmark also returns it as `[String:AnyObject]`, the Screenlet can convert it 
into an object that represents bookmarks. It does so with its 
[`Bookmark` model class](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Model/Bookmark.swift). 
This class extends `NSObject` and sets the `[String:AnyObject]` to the 
`attributes` constant via the initializer. This class also defines computed 
properties that return the attribute values for each bookmark's name and URL: 

    @objc public class Bookmark : NSObject {

        public let attributes: [String:AnyObject]

        public var name: String {
            return attributes["name"] as! String
        }

        override public var description: String {
            return attributes["description"] as! String
        }

        public var url: String {
            return attributes["url"] as! String
        }

        public init(attributes: [String:AnyObject]) {
            self.attributes = attributes
        }

    }

Next, you'll put your model class to work. 

## Using Your Model Class [](id=using-your-model-class)

Now that your model class exists, you can use model objects anywhere your 
Screenlet handles results. Exactly where depends on what Screenlet components 
your Screenlet uses. For example, Add Bookmark Screenlet's Connector, 
Interactor, delegate, and Screenlet class all handle the Screenlet's results. 
The steps here therefore show you how to use model objects in each of these 
components. Note, however, that your Screenlet may lack a Connector or delegate: 
these components are optional. Variations on these steps are therefore noted 
where applicable. 

1. Create model objects where the `[String: AnyObject]` results originate. For 
   example, the `[String: AnyObject]` results in Add Bookmark Screenlet 
   originate in the Connector. Therefore, this is where the Screenlet creates 
   `Bookmark` objects. The following code in the Screenlet's Connector 
   (`AddBookmarkLiferayConnector`) does this. The `if` statement following the 
   service call casts the results to `[String: AnyObject]`, calls the `Bookmark` 
   initializer with those results, and stores the resulting `Bookmark` object to 
   the public `resultBookmarkInfo` variable. Note that this is only the code 
   that makes the service call and creates the `Bookmark` object. 
   [Click here to see the complete `AddBookmarkLiferayConnector` class](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Connector/AddBookmarkLiferayConnector.swift): 

        ...
        // Public property for the results
        public var resultBookmarkInfo: Bookmark?

        ...

        override public func doRun(session session: LRSession) {
            let service = LRBookmarksEntryService_v7(session: session)

            do {
                let result = try service.addEntryWithGroupId(LiferayServerContext.groupId,
                                                             folderId: folderId,
                                                             name: title,
                                                             url: url,
                                                             description: "Added from Liferay Screens",
                                                             serviceContext: nil)

                // Creates Bookmark objects from the service call's results
                if let result = result as? [String: AnyObject] {
                    resultBookmarkInfo = Bookmark(attributes: result)
                    lastError = nil
                }
                ...
            }
            ...
        }

    If your Screenlet doesn't have Connector, then your Interactor's `start` 
    method makes your server call and handles its results. Otherwise, the 
    process for creating a `Bookmark` object from `[String: AnyObject]` is the 
    same. 

2. Handle your model objects in your Screenlet's Interactor. The Interactor 
   processes your Screenlet's results, so it must also handle your model 
   objects. If your Screenlet doesn't use a Connector, then you already did this 
   in your Interactor's `start` method as mentioned at the end of the previous 
   step. If your Screenlet uses a Connector, however, then this happens in your 
   Interactor's `completedConnector` method. For example, the 
   `completedConnector` method in Add Bookmark Screenlet's Interactor 
   (`AddBookmarkInteractor`) retrieves the `Bookmark` via the Connector's 
   `resultBookmarkInfo` variable. This method then assigns the `Bookmark` to the 
   Interactor's public `resultBookmark` variable. Note that this is only the 
   code that handles `Bookmark` objects. 
   [Click here to see the complete `AddBookmarkInteractor` class](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Interactor/AddBookmarkInteractor.swift): 

        ...
        // Public property for the results
        public var resultBookmark: Bookmark?

        ...

        // The completedConnector method gets the results from the Connector
        override public func completedConnector(c: ServerConnector) { 
            if let addCon = (c as? AddBookmarkLiferayConnector), 
                bookmark = addCon.resultBookmarkInfo { 
                    self.resultBookmark = bookmark 
                }
        }

3. If your Screenlet uses a delegate, your delegate protocol must account for 
   your model objects. Skip this step if you don't have a delegate. For example, 
   Add Bookmark Screenlet's delegate (`AddBookmarkScreenletDelegate`) must 
   communicate `Bookmark` objects. The delegate's first method does this via its 
   second argument: 

        @objc public protocol AddBookmarkScreenletDelegate: BaseScreenletDelegate {

            optional func screenlet(screenlet: AddBookmarkScreenlet,
                            onBookmarkAdded bookmark: Bookmark)

            optional func screenlet(screenlet: AddBookmarkScreenlet,
                            onAddBookmarkError error: NSError)

        }

4. Get the model object from the Interactor in your Screenlet class's 
   `interactor.onSuccess` closure. You can then use the model object however you 
   wish. For example, the `interactor.onSuccess` closure in Add Bookmark 
   Screenlet's Screenlet class (`AddBookmarkScreenlet`) retrieves the `Bookmark` 
   from the Interactor's `resultBookmark` property. It then handles the 
   `Bookmark` via the delegate. Note that in this example, the closure is in the 
   Screenlet class's Interactor method that adds a bookmark 
   (`createAddBookmarkInteractor`). Be sure to get your model object wherever 
   the `interactor.onSuccess` closure is in your Screenlet class. 
   [Click here to see the complete `AddBookmarkScreenlet`](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/AddBookmarkScreenlet.swift): 

        ...
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
        ...

Awesome! Now you know how to create and use a model class in your Screenlet. 

## Related Topics [](id=related-topics)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Adding Screenlet Actions](/develop/tutorials/-/knowledge_base/7-0/adding-screenlet-actions)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
