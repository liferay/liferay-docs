# Creating the Connector [](id=creating-the-connector)

Recall that Connectors make a server call. To support pagination, a List 
Screenlet's Connector class must extend the 
[`PaginationLiferayConnector` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/PaginationLiferayConnector.swift). 
The Connector class must also contain any properties it needs to make the server 
call, and an initializer that sets them. To support pagination, the initializer 
must also contain the following arguments, which you'll pass to the superclass 
initializer: 

- `startRow`: The number representing the page's first row. 
- `endRow`: The number representing the page's last row. 
- `computeRowCount`: Whether to call the Connector's `doAddRowCountServiceCall` 
  method (you'll learn about this method shortly). 

For example, Bookmark List Screenlet must retrieve bookmarks from a Bookmarks 
portlet folder in a specific site. The Screenlet's Connector class must 
therefore have properties for the `groupId` (site ID) and `folderId` (Bookmarks 
folder ID), and an initializer that sets them. The initializer also calls the 
superclass initializer with the `startRow`, `endRow`, and `computeRowCount` 
arguments: 

    import UIKit
    import LiferayScreens

    public class BookmarkListPageLiferayConnector: PaginationLiferayConnector {

        public let groupId: Int64
        public let folderId: Int64

        //MARK: Initializer

        public init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64, 
            folderId: Int64) {
            
                self.groupId = groupId
                self.folderId = folderId

                super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
        }
        ...

Next, if you want to validate any of your Screenlet's properties, override the 
`validateData` method as described in 
[the tutorial on creating Connectors](/develop/tutorials/-/knowledge_base/7-0/create-and-use-a-connector-with-your-screenlet#creating-connectors). 
Note that Bookmark List Screenlet only needs to validate the `folderId`: 

    override public func validateData() -> ValidationError? {
        let error = super.validateData()

        if error == nil {
            if folderId <= 0 {
                return ValidationError("Undefined folderId")
            }
        }

        return error
    }

Lastly, you must override the following two methods in the Connector class: 

- `doAddPageRowsServiceCall`: calls the Liferay Mobile SDK service method that 
  retrieves a page of entities. The `doAddPageRowsServiceCall` method's 
  `startRow` and `endRow` arguments specify the page's first and last entities, 
  respectively. Make the service call as you would in any Screenlet. For 
  example, the `doAddPageRowsServiceCall` method in 
  `BookmarkListPageLiferayConnector` calls the service's `getEntriesWithGroupId` 
  method to retrieve a page of bookmarks from the folder specified by 
  `folderId`: 

        public override func doAddPageRowsServiceCall(session session: LRBatchSession, 
            startRow: Int, endRow: Int, obc: LRJSONObjectWrapper?) {
                let service = LRBookmarksEntryService_v7(session: session)

            do {
                try service.getEntriesWithGroupId(groupId,
                                                  folderId: folderId,
                                                  start: Int32(startRow),
                                                  end: Int32(endRow))
            }
            catch  {
                // ignore error: the service method returns nil because
                // the request is sent later, in batch
            }
        }

    Note that you don't need to do anything in the `catch` statement because the 
    request is sent later, in batch. The `session` type `LRBatchSession` handles 
    this for you. You'll receive the request's results elsewhere, once the 
    request completes. 

- `doAddRowCountServiceCall`: calls the Liferay Mobile SDK service method that 
  retrieves the total number of entities. This supports pagination. Make the 
  service call as you would in any Screenlet. For example, the 
  `doAddRowCountServiceCall` in `BookmarkListPageLiferayConnector` calls the 
  service's `getEntriesCountWithGroupId` method to retrieve the total number of 
  bookmarks in the folder specified by `folderId`: 

        override public func doAddRowCountServiceCall(session session: LRBatchSession) {
            let service = LRBookmarksEntryService_v7(session: session)

            do {
                try service.getEntriesCountWithGroupId(groupId, folderId: folderId)
            }
            catch  {
                // ignore error: the service method returns nil because
                // the request is sent later, in batch
            }
        }

Now that you have your Connector class, you're ready to create the Interactor. 
