# Creating Entry List Screenlet's Connector [](id=creating-entry-list-screenlets-connector)

Recall that Connectors are Screenlet components that make server calls. Also 
recall that by making your server calls in Connectors instead of Interactors, 
you gain an additional layer of abstraction. 

In this article, you'll create Entry List Screenlet's Connector. Because this 
Connector is so similar to that of Guestbook List Screenlet, the steps to create 
it aren't explained in detail. Focus is instead placed on the few places in the 
code where the Connectors diverge. For a full explanation of the code, see 
[the article on creating Guestbook List Screenlet's Connector](/develop/tutorials/-/knowledge_base/7-0/creating-guestbook-list-screenlets-connector). 

## Creating Your Connector's Folder [](id=creating-your-connectors-folder)

Follow these steps to create your Connector's folder: 

1.  In the Finder, create the `Connector` folder inside your project's 
    `EntryListScreenlet` folder. 

2.  Drag and drop the `Connector` folder from the Finder into your Xcode 
    project, under the `EntryListScreenlet` folder. In the dialog that appears, 
    select *Copy items if needed*, *Create groups*, and the *Liferay Guestbook* 
    target. Then click *Finish*. The `Connector` folder now appears in your 
    project. 

Now you're ready to create the Connector. 

## Creating the Connector [](id=creating-the-connector)

Recall that list Screenlet Connectors must extend 
[the `PaginationLiferayConnector` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/PaginationLiferayConnector.swift). 
Your list Screenlet's Connector class must also contain any properties it needs 
to make the server call, and an initializer that sets them. To support 
pagination, the initializer must also contain the following arguments, which 
you'll pass to the superclass initializer: 

- `startRow`: The number representing the page's first row. 
- `endRow`: The number representing the page's last row. 
- `computeRowCount`: Whether to call the Connector's `doAddRowCountServiceCall` 
  method. 

Follow these steps to create Guestbook List Screenlet's Connector: 

1.  In the Project navigator, right-click the `Connector` folder you added above 
    and select *New File*. In the dialog that appears, fill out each screen as 
    follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `EntryListPageLiferayConnector`, set it to extend 
      `PaginationLiferayConnector`, select *Swift* for the language, and click 
      *Next*. 
    - Make sure the `Connector` folder and group is selected, as well as the 
      *Liferay Guestbook* target (these should be selected by default). Click 
      *Create*. 

2.  Replace the class file's contents with this code: 

        import UIKit
        import LiferayScreens

        class EntryListPageLiferayConnector: PaginationLiferayConnector {

            public let groupId: Int64
            public let guestbookId: Int64

            //MARK: Initializer

            public init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64, 
                guestbookId: Int64) {

                    self.groupId = groupId
                    self.guestbookId = guestbookId

                    super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
            }

            //MARK: PaginationLiferayConnector

            public override func doAddPageRowsServiceCall(session: LRBatchSession, startRow: Int, endRow: Int, 
                obc: LRJSONObjectWrapper?) {

                let service = LREntryService_v7(session: session)

                do {
                    try service!.getEntriesWithGroupId(groupId, guestbookId: guestbookId, 
                            start: Int32(startRow), end: Int32(endRow))
                }
                catch {
                    // the service method returns nil because the request is sent later, in batch
                }

            }

            override public func doAddRowCountServiceCall(session: LRBatchSession) {
                let service = LREntryService_v7(session: session)

                do {
                    try service!.getEntriesCount(withGroupId: groupId, guestbookId: guestbookId)
                }
                catch {
                    // the service method returns nil because the request is sent later, in batch
                }
            }

        }

    This class is almost identical to Guestbook List Screenlet's Connector 
    class, `GuestbookListPageLiferayConnector`. The only differences are due to 
    the service calls. To define the guestbook to retrieve entries from, 
    `EntryListPageLiferayConnector` needs a `guestbookId` property. It then uses 
    this property with the service methods `getEntriesWithGroupId` and 
    `getEntriesCount` to retrieve the entries and number of entries, 
    respectively. Also note that the service is an `LREntryService_v7` instance. 

Nicely done! Now that Entry List Screenlet has a Connector, you must create its 
Interactor. The next article shows you how to do this. 
