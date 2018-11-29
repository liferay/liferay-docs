# Creating the iOS List Screenlet's Class [](id=creating-the-ios-list-screenlets-class)

Now that your list Screenlet's other components exist, you can create the 
Screenlet class. A list Screenlet's Screenlet class must extend the 
[`BaseListScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListScreenlet.swift) 
and define the configurable properties the Screenlet needs. You should define 
these as `IBInspectable` properties. If you want to support offline mode, you 
should also add an `offlinePolicy` property. 

For example, 
[Bookmark List Screenlet's Screenlet class](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/BookmarkListScreenlet.swift) 
contains the `IBInspectable` properties `groupId`, `folderId`, and 
`offlinePolicy`: 

    public class BookmarkListScreenlet: BaseListScreenlet {

        @IBInspectable public var groupId: Int64 = 0
        @IBInspectable public var folderId: Int64 = 0
        @IBInspectable public var offlinePolicy: String? = CacheStrategyType.RemoteFirst.rawValue

        ...

Next, override the `createPageLoadInteractor` method to create and return the 
Interactor. If your Screenlet supports offline mode, you should also use 
`offlinePolicy` to pass a `CacheStrategyType` object to the Interactor. For 
example, the `createPageLoadInteractor` method in `BookmarkListScreenlet` 
creates and returns a `BookmarkListPageLoadInteractor` instance. This method 
also sets the Interactor's `cacheStrategy` property to a `CacheStrategyType` 
object created with `offlinePolicy`: 

    override public func createPageLoadInteractor(
        page page: Int, 
        computeRowCount: Bool) -> BaseListPageLoadInteractor {

        let interactor = BookmarkListPageLoadInteractor(screenlet: self,
                                                        page: page,
                                                        computeRowCount: computeRowCount,
                                                        groupId: self.groupId,
                                                        folderId: self.folderId)

        interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .RemoteFirst

        return interactor
    }

Now get a reference to your delegate. The `BaseScreenlet` class, which 
`BaseListScreenlet` extends, already defines the `delegate` property for the 
delegate object. Every list Screenlet therefore has this property, and any app 
developer using the Screenlet can access it. To avoid casting this property to 
your delegate every time you use it, add a computed property to your Screenlet 
class that does so. For example, the following `bookmarkListDelegate` property 
in `BookmarkListScreenlet` casts the `delegate` property to 
`BookmarkListScreenletDelegate`: 

    public var bookmarkListDelegate: BookmarkListScreenletDelegate? {
        return delegate as? BookmarkListScreenletDelegate
    }

Next, override the `BaseListScreenlet` methods that handle the Screenlet's 
events. Because these events correspond to the events your delegate methods 
handle, you'll call your delegate methods in these `BaseListScreenlet` methods: 

- `onLoadPageResult`: Called when the Screenlet loads a page successfully. 
  Override this method to call your delegate's 
  `screenlet(_:onBookmarkListResponse:)` method. For example, here's 
  `BookmarkListScreenlet`'s `onLoadPageResult` method: 

        override public func onLoadPageResult(page page: Int, rows: [AnyObject], rowCount: Int) {
            super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

            bookmarkListDelegate?.screenlet?(screenlet: self, onBookmarkListResponse: rows as! [Bookmark])
        }

- `onLoadPageError`: Called when the Screenlet fails to load a page. Override 
  this method to call your delegate's `screenlet(_:onBookmarkListError:)` 
  method. For example, here's `BookmarkListScreenlet`'s `onLoadPageError` 
  method: 

        override public func onLoadPageError(page page: Int, error: NSError) {
            super.onLoadPageError(page: page, error: error)

            bookmarkListDelegate?.screenlet?(screenlet: self, onBookmarkListError: error)
        }

- `onSelectedRow`: Called when an item is selected in the list. Override this 
  method to call your delegate's `screenlet(_:onBookmarkSelected:)` method. For 
  example, here's `BookmarkListScreenlet`'s `onSelectedRow` method: 

        override public func onSelectedRow(_ row: AnyObject) {
            bookmarkListDelegate?.screenlet?(screenlet: self, onBookmarkSelected: row as! Bookmark)
        }
