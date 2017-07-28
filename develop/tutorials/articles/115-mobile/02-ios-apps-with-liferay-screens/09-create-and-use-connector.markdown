# Create and Use a Connector with Your Screenlet [](id=create-and-use-a-connector-with-your-screenlet)

In Liferay Screens, a Connector is a class that interacts asynchronously with 
local and remote data sources and Liferay instances. Recall that callbacks also 
make asynchronous service calls. So why bother with a Connector? Connectors 
provide a layer of abstraction by making your service call outside your 
Interactor. For example, 
[the Interactor in the Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets#creating-the-interactor) 
makes the server call and and processes its results via `LRCallback`. This 
Screenlet could instead make its server call in a separate Connector class, 
leaving the Interactor to instantiate the Connector and receive its results. 
Connectors also let you validate your Screenlet's data. For more information on 
Connectors, see the 
[tutorial on the architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios). 

This tutorial walks you through the steps required to create and use a Connector 
with your Screenlets, using the 
[advanced version of the sample Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced)
as an example. This Screenlet contains two actions: 

1. Add Bookmark: Adds a bookmark to the Bookmarks portlet in a @product@
   installation. This tutorial shows you how to create and use a Connector for
   this action. 

2. Get Title: Retrieves the title from a bookmark URL entered by the user. This 
   tutorial shows you how to use a pre-existing Connector with this action. 

Before proceeding, make sure you've read 
[the Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets). 
First, you'll learn how to create your Connector. 

## Creating Connectors [](id=creating-connectors)

When you create your Connector class, be sure to follow the 
[naming convention](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices#naming-conventions) 
specified in the best practices tutorial. 

Use the following steps to implement your Connector class: 

1.  Create your Connector class by extending the 
    [`ServerConnector` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerConnector.swift). 
    For example, here's the class declaration for Add Bookmark Screenlet's 
    Connector class, `AddBookmarkLiferayConnector`:

        public class AddBookmarkLiferayConnector: ServerConnector {
            ...
        }

2.  Add the properties needed to call the Mobile SDK service, then create an 
    initializer that sets those properties. For example, 
    `AddBookmarkLiferayConnector` needs properties for the bookmark's folder ID, 
    title, and URL. It also needs an initializer to set those properties: 

        public let folderId: Int64
        public let title: String
        public let url: String

        public init(folderId: Int64, title: String, url: String) {
            self.folderId = folderId
            self.title = title
            self.url = url
            super.init()
        }

3.  Override the `validateData` method to implement validation for each property 
    that needs it. You can use the 
    [`ValidationError` class](https://github.com/liferay/liferay-screens/blob/develop/ios/Framework/Core/Extensions/NSError%2BScreens.swift) 
    to encapsulate the errors. For example, the following `validateData` 
    implementation in `AddBookmarkLiferayConnector` ensures that `folderId` is 
    greater than `0`, and `title` and `url` contain values. This method also uses 
    `ValidationError` to represent the error: 

        override public func validateData() -> ValidationError? {
            let error = super.validateData()

            if error == nil {

                if folderId <= 0 {
                    return ValidationError("Undefined folderId")
                }

                if title.isEmpty {
                    return ValidationError("Title cannot be empty")
                }

                if url.isEmpty {
                    return ValidationError("URL cannot be empty")
                }
            }

            return error
        }

4.  Override the `doRun` method to call the Mobile SDK service you need to 
    call. This method should retrieve the result from the service and store it in 
    a public property. Also be sure to handle errors and empty results. For 
    example, the following code defines the `resultBookmarkInfo` property for 
    storing the service's results retrieved in the `doRun` method. Note that this 
    method's service call is identical to the one in the `AddBookmarkInteractor` 
    class's `start` method in the 
    [Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets). 
    The `doRun` method, however, takes the additional step of saving the result 
    to the `resultBookmarkInfo` property. Also note that this `doRun` method 
    handles errors as `NSError` objects: 

        public var resultBookmarkInfo: [String:AnyObject]?

        override public func doRun(session session: LRSession) {

            let service = LRBookmarksEntryService_v7(session: session)

            do {
                let result = try service.addEntryWithGroupId(LiferayServerContext.groupId,
                                                             folderId: folderId,
                                                             name: title,
                                                             url: url,
                                                             description: "Added from Liferay Screens",
                                                             serviceContext: nil)

                if let result = result as? [String: AnyObject] {
                    resultBookmarkInfo = result
                    lastError = nil
                }
                else {
                    lastError = NSError.errorWithCause(.InvalidServerResponse)
                    resultBookmarkInfo = nil
                }
            }
            catch let error as NSError {
                lastError = error
                resultBookmarkInfo = nil
            }

        }

Well done! Now you know how to create a Connector class. To see the finished 
example `AddBookmarkLiferayConnector` class, 
[click here](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Connector/AddBookmarkLiferayConnector.swift). 

## Using Connectors [](id=using-connectors)

To use a Connector, your Interactor class must extend the 
[`ServerConnectorInteractor` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerConnectorInteractor.swift) 
or one of its following subclasses: 

- [`ServerReadConnectorInteractor`:](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerReadConnectorInteractor.swift)
  Your Interactor class should extend this class when implementing an action 
  that retrieves information from a server or data source. 

- [`ServerWriteConnectorInteractor`:](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerWriteConnectorInteractor.swift)
  Your Interactor class should extend this class when implementing an action 
  that writes information to a server or data source. 

When extending `ServerConnectorInteractor` or one of its subclasses, your 
Interactor class only needs to override the `createConnector` and 
`completedConnector` methods. These methods create a Connector instance and 
recover the Connector's result, respectively. 

Follow these steps to use a Connector in your Interactor: 

1. Set your Interactor class's superclass to `ServerConnectorInteractor` or one 
   of its subclasses. You should also remove any code that conforms a callback 
   protocol, if it exists. For example, Add Bookmark Screenlet's Interactor 
   class (`AddBookmarkInteractor`) extends `ServerWriteConnectorInteractor` 
   because it writes data to a @product@ installation. At this point, your
   Interactor should contain only the properties and initializer that it
   requires:

        public class AddBookmarkInteractor: ServerWriteConnectorInteractor {

            public let folderId: Int64
            public let title: String
            public let url: String

            public var resultBookmark: Bookmark?

            //MARK: Initializer

            public init(screenlet: BaseScreenlet, folderId: Int64, title: String, url: String) {
                self.folderId = folderId
                self.title = title
                self.url = url
                super.init(screenlet: screenlet)
            }
        }

2. Override the `createConnector` method to return an instance of your 
   Connector. For example, the `createConnector` method in 
   `AddBookmarkInteractor` returns an `AddBookmarkLiferayConnector` instance 
   created with the `folderId`, `title`, and `url` properties: 

        public override func createConnector() -> ServerConnector? {
            return AddBookmarkLiferayConnector(folderId: folderId, title: title, url: url)
        }

3. Override the `completedConnector` method to get the result from the Connector 
   and store it in the appropriate property. For example, the 
   `completedConnector` method in `AddBookmarkInteractor` first casts its 
   `ServerConnector` argument to `AddBookmarkLiferayConnector`. It then gets the 
   Connector's `resultBookmarkInfo` property and sets it to the Interactor's 
   `resultBookmark` property: 

        override public func completedConnector(c: ServerConnector) {
            if let addCon = (c as? AddBookmarkLiferayConnector),
                bookmarkInfo = addCon.resultBookmarkInfo {
                self.resultBookmark = bookmarkInfo
            }
        }

That's it! To see the complete example `AddBookmarkInteractor`, 
[click here](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/Interactor/AddBookmarkInteractor.swift). 

If your Screenlet uses multiple Interactors, follow the same steps to use
Connectors. Also, Screens provides 
[the ready-to-use `HttpConnector`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/HttpConnector.swift) 
for interacting with non-Liferay URL's. To use this Connector, set your 
Interactor to use `HttpConnector`. For example, the Add Bookmark Screenlet 
action that retrieves a URL's title doesn't interact with a @product@
installation; it retrieves the title directly from the URL. Because this
action's Interactor class (`GetWebTitleInteractor`) retrieves data, it extends
`ServerReadConnectorInteractor`. It also overrides the `createConnector` and 
`completedConnector` methods to use `HttpConnector`. Here's the complete 
`GetWebTitleInteractor`:

    import UIKit
    import LiferayScreens

    public class GetWebTitleInteractor: ServerReadConnectorInteractor {

        public let url: String?

        // title from the webpage
        public var resultTitle: String?

        //MARK: Initializer

        public init(screenlet: BaseScreenlet, url: String) {
            self.url = url
            super.init(screenlet: screenlet)
        }

        //MARK: ServerConnectorInteractor

        public override func createConnector() -> ServerConnector? {
            if let url = url, URL = NSURL(string: url) {
                return HttpConnector(url: URL)
            }

            return nil
        }

        override public func completedConnector(c: ServerConnector) {
            if let httpCon = (c as? HttpConnector), data = httpCon.resultData,
                html = NSString(data: data, encoding: NSUTF8StringEncoding) {
                self.resultTitle = parseTitle(html)
            }
        }

        //MARK: Private methods

        // Parse the title from the webpage's HTML
        private func parseTitle(html: NSString) -> String {
            let range1 = html.rangeOfString("<title>")
            let range2 = html.rangeOfString("</title>")

            let start = range1.location + range1.length

            return html.substringWithRange(NSMakeRange(start, range2.location - start))
        }

    }

Awesome! Now you know how to create and use Connectors in your Screenlets. 

## Related Topics [](id=related-topics)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Adding Screenlet Actions](/develop/tutorials/-/knowledge_base/7-0/adding-screenlet-actions)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)
