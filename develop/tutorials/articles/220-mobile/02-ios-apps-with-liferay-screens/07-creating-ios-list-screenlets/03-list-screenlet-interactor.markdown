# Creating the iOS List Screenlet's Interactor [](id=creating-the-ios-list-screenlets-interactor)

Recall that Interactors implement your Screenlet's actions. In list Screenlets, 
loading entities is usually the only action a user can take. The Interactor 
class of a list Screenlet that implements fluent pagination must extend the 
[`BaseListPageLoadInteractor` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListPageLoadInteractor.swift). 
Your Interactor class must also contain any properties the Screenlet needs, and 
an initializer that sets them. This initializer also needs arguments for the 
following properties, which it passes to the superclass initializer: 

- `screenlet`: A `BaseListScreenlet` reference. This ensures the Interactor 
  always has a Screenlet reference. 
- `page`: The page number to retrieve. 
- `computeRowCount`: Whether to call the Connector's `doAddRowCountServiceCall` 
  method. 

For example, Bookmark List Screenlet's Interactor class contains the same 
`groupId` and `folderId` properties as the Connector, and an initializer that 
sets them. This initializer also passes the `screenlet`, `page`, and 
`computeRowCount` arguments to the superclass initializer: 

    public class BookmarkListPageLoadInteractor : BaseListPageLoadInteractor {

        private let groupId: Int64
        private let folderId: Int64

        init(screenlet: BaseListScreenlet,
            page: Int,
            computeRowCount: Bool,
            groupId: Int64,
            folderId: Int64) {

                self.groupId = (groupId != 0) ? groupId : LiferayServerContext.groupId
                self.folderId = folderId

                super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
        }
        ...

The Interactor class must also initiate the server request by instantiating the 
Connector, and convert the results into model objects. Override the 
`createListPageConnector` method to create and return an instance of your 
Connector. This method must first get a reference to the Screenlet via the 
`screenlet` property. When calling the Connector's initializer, use 
`screenlet.firstRowForPage` to convert the page number (`page`) to the page's 
start and end indices. You must also pass the initializer any other properties 
it needs, like `groupId`. For example, `BookmarkListPageLoadInteractor`'s 
`createListPageConnector` method does this to create a 
`BookmarkListPageLiferayConnector` instance: 

    public override func createListPageConnector() -> PaginationLiferayConnector {
        let screenlet = self.screenlet as! BaseListScreenlet

        return BookmarkListPageLiferayConnector(
            startRow: screenlet.firstRowForPage(self.page),
            endRow: screenlet.firstRowForPage(self.page + 1),
            computeRowCount: self.computeRowCount,
            groupId: groupId,
            folderId: folderId)
    }

Next, override the `convertResult` method to convert each `[String:AnyObject]` 
result into a model object. The Screenlet calls this method once for each entity 
retrieved from the server. For example, `BookmarkListPageLoadInteractor`'s 
`convertResult` method converts the `[String:AnyObject]` result into a 
`Bookmark` object: 

    override public func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {
        return Bookmark(attributes: serverResult)
    }

You may also want to support 
[offline mode](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens) 
in your Interactor. To do so, the Interactor must override the `cacheKey` method 
to return a cache key unique to your Screenlet. For example, 
`BookmarkListPageLoadInteractor`'s `cacheKey` method returns a cache key that 
includes the Screenlet's `groupId` and `folderId` settings: 

    override public func cacheKey(_ op: PaginationLiferayConnector) -> String {
        return "\(groupId)-\(folderId)"
    }

Great! Next, you'll create your Screenlet's delegate. 
