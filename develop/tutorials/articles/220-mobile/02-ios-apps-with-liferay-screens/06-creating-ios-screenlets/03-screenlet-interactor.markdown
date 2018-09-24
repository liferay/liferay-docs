# Creating the iOS Screenlet's Interactor

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
